/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.validation.internal.el;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.common.internal.types.SignatureBasedType;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.validation.internal.ELValidationPreferences;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.ValidationMessageFactory;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.JSPELParser;
import org.eclipse.jst.jsp.core.internal.java.jspel.ParseException;
import org.eclipse.jst.jsp.core.internal.java.jspel.Token;
import org.eclipse.jst.jsp.core.internal.java.jspel.TokenMgrError;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;

/**
 * Validates a single expression string
 * 
 * @author cbateman
 *
 */
public class ELExpressionValidator 
{
    private final IStructuredDocumentContext        _context;
    private final String                            _elText;
    private final IFile                             _file;
    private final DiagnosticFactory                 _diagnosticFactory;
    private final ELValidationPreferences           _prefs;
    
    private final List<IMessage>                    _syntaxProblems;
    private ASTSemanticValidator                    _semanticValidator;
    
    /**
     * @param context
     * @param elText
     * @param file
     * @param prefs 
     */
    public ELExpressionValidator(final IStructuredDocumentContext context,
                                 final String elText, 
                                 final IFile file,
                                 final ELValidationPreferences prefs)
    {
        _context = context;
        _elText = elText;
        _file = file;
        _prefs = prefs;
        _diagnosticFactory = new DiagnosticFactory();
        _syntaxProblems = new ArrayList<IMessage>();
    }
    
    /**
     * Validates a single EL expression in an XML attribute value
     * @return an ASTExpression for the node
     */
    public ASTExpression validateXMLNode() 
    {
        JSPELParser elParser = JSPELParser.createParser(_elText);
        // = 
        try {
            //final long startParsing = System.currentTimeMillis();
            final ASTExpression expr =  elParser.Expression();
            //final long endParsing = System.currentTimeMillis();
            //final long startSemantics = System.currentTimeMillis();
            validateSemantics(expr, _context);
            //final long endSemantics = System.currentTimeMillis();

            
            //System.out.println("Time to parse '"+elText+"' = "+(endParsing-startParsing));
            //System.out.println("Time to semantic checking '"+elText+"' = "+(endSemantics-startSemantics));
            
            // if the parser bailed before parsing the whole
            // expression, raise a warning that there is probably
            // some syntatical issue
            if (expr.getLastToken().endColumn < _elText.trim().length()-1)
            {
                int offset = _context.getDocumentPosition() + expr.getLastToken().endColumn;
                int length = _elText.trim().length() - expr.getLastToken().endColumn;

                _syntaxProblems.add(
                    ValidationMessageFactory.createFromDiagnostic(
                            _diagnosticFactory.create_GENERAL_SYNTAX_ERROR(), 
                                offset, length, _file, _prefs));
            }
            
            return expr;
        }
        catch (ParseException e) {
            Token curTok = e.currentToken;
            int offset = _context.getDocumentPosition() + curTok.beginColumn;
            int length = curTok.endColumn - curTok.beginColumn + 1;
            _syntaxProblems.add(
                    ValidationMessageFactory.createFromDiagnostic(
                            _diagnosticFactory.create_GENERAL_SYNTAX_ERROR(), 
                                offset, length, _file, _prefs));
            return null;
        }
        catch (TokenMgrError te) {
            final int offset = _context.getDocumentPosition();
            final int length = _elText.length();
            _syntaxProblems.add(
                    ValidationMessageFactory.createFromDiagnostic(
                            _diagnosticFactory.create_GENERAL_SYNTAX_ERROR(), 
                                offset, length, _file, _prefs));
            return null;
        }
    }

    /**
     * Report the results of this EL Expression validation to reportor
     * using validator as a source
     * 
     * @param validator
     * @param reporter
     */
    public void reportFindings(IValidator validator, IReporter reporter)
    {
        for (final Iterator<IMessage> it = _syntaxProblems.iterator(); it.hasNext();)
        {
            IMessage message = it.next();
            
            // don't report messages that have no severity level
            if ((message.getSeverity() & IMessage.ALL_MESSAGES) != 0)
            {
                reporter.addMessage(validator, message);
            }
        }
        
        if (_semanticValidator != null)
        {
            _semanticValidator.reportFindings(validator,reporter);
        }
    }

    /**
     * @return the type of the expression or null if 
     * the type could not be evaluated
     */
    public CompositeType getExpressionType()
    {
        if (_semanticValidator != null)
        {
            final SignatureBasedType type = 
                _semanticValidator.getExpressionType();
            
            if (type != null)
            {
                return type.toCompositeType();
            }
        }
        
        return null;
    }
    
    /**
     * @return an unmodifiable list containing all the detected syntax errors
     * or an empty list if validate has not yet been called.
     */
    public List<IMessage> getSyntaxProblems()
    {
        return Collections.unmodifiableList(_syntaxProblems);
    }
    
    /**
     * @return the EL semantic validator for this expression validitor or
     * null if one has not yet been constructor.  One will only be construct
     * if validate is called and the expression does not have any syntax errors.
     */
    public IExpressionSemanticValidator getSemanticValidator()
    {
        return _semanticValidator;
    }
    
    /**
     * Validates the context-specific data for one EL expressino
     * 
     * @param expr
     * @param file
     * @param reporter
     * @param context
     */
    private void validateSemantics(ASTExpression expr,IStructuredDocumentContext context)
    {
        _semanticValidator = new ASTSemanticValidator(expr, context, _prefs);
        _semanticValidator.validate();
    }
}
