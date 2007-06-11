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

package org.eclipse.jst.jsf.validation.internal.el.operators;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigUtils;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.jst.jsp.core.internal.java.jspel.JSPELParserConstants;
import org.eclipse.jst.jsp.core.internal.java.jspel.Token;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * Represents an abstract EL binary operator that always
 * takes arguments and produces a single results
 * 
 * @author cbateman
 *
 */
public abstract class BinaryOperator 
{
    /**
     * the factory used to construct diagnostics
     */
    protected final DiagnosticFactory   _diagnosticFactory;
    
    /**
     * @param operatorToken
     * @param diagnosticFactory 
     * @param context -- the current EL document context; must not be null
     * @return a binary operator based on the provided token
     * @throws IllegalArgumentException if the token is not a recognized
     * EL binary operator token or if context is null
     */
    public static BinaryOperator getBinaryOperator(Token operatorToken, DiagnosticFactory diagnosticFactory, IStructuredDocumentContext context)
    {
        if (context == null)
        {
            throw new IllegalArgumentException("Context must not be null"); //$NON-NLS-1$
        }
        final String facetVersion = determineJSFVersion(context);
        
        switch (operatorToken.kind)
        {
            case JSPELParserConstants.AND1:
            case JSPELParserConstants.AND2:
                return new AndBinaryOperator(diagnosticFactory);
                
            case JSPELParserConstants.OR1:
            case JSPELParserConstants.OR2:
                return new OrBinaryOperator(diagnosticFactory);
                
            case JSPELParserConstants.EQ1:
            case JSPELParserConstants.EQ2:
                return new EqualsBinaryRelationalOperator(diagnosticFactory,facetVersion);
                
            case JSPELParserConstants.NEQ1:
            case JSPELParserConstants.NEQ2:
                return new NotEqualsBinaryRelationalOperator(diagnosticFactory,facetVersion);
                
            case JSPELParserConstants.GT1:
            case JSPELParserConstants.GT2:
                return new GreaterThanRelationalBinaryOperator(diagnosticFactory,facetVersion);
                
            case JSPELParserConstants.GE1:
            case JSPELParserConstants.GE2:
                return new GreaterThanEqRelationalBinaryOperator(diagnosticFactory,facetVersion);
                
            case JSPELParserConstants.LT1:
            case JSPELParserConstants.LT2:
                return new LessThanRelationalBinaryOperator(diagnosticFactory,facetVersion);
                
            case JSPELParserConstants.LE1:
            case JSPELParserConstants.LE2:
                return new LessThanEqRelationalBinaryOperator(diagnosticFactory,facetVersion);
                
            case JSPELParserConstants.PLUS:
                return new AddArithmeticBinaryOperator(diagnosticFactory);
                
            case JSPELParserConstants.MINUS:
                return new SubtractArithmeticBinaryOperator(diagnosticFactory);
                
            case JSPELParserConstants.MULTIPLY:
                return new MultiplyArithmeticBinaryOperator(diagnosticFactory);
                
            case JSPELParserConstants.DIVIDE1:
            case JSPELParserConstants.DIVIDE2:
                return new DivArithmeticBinaryOperator(diagnosticFactory);
            
            case JSPELParserConstants.MODULUS1:
            case JSPELParserConstants.MODULUS2:
                return new ModArithmeticBinaryOperator(diagnosticFactory);
        }
        
        throw new IllegalArgumentException("Unknown binary operator: "+operatorToken.image); //$NON-NLS-1$
    }
    
    /**
     * 
     * Constructor
     */
    BinaryOperator(DiagnosticFactory diagnosticFactory) 
    {
        /* no construction or sub-classing outside package*/
        _diagnosticFactory = diagnosticFactory;
    }
    
    /**
     * If both arguments are literals and the operation can be performed, then
     * the return must be a new LiteralType transformed using this operator.
     * 
     * If one or both of the arg is not a literal and the operaton can be performed, then
     * the return is a new ValueType transformed per the rules of the operator
     * 
     * If the operation cannot be performed on ValueType, return null
     * 
     * @param firstArg 
     * @param secondArg 
     * @return a new value type after the operation is performed
     */
    public abstract ValueType performOperation(ValueType firstArg, ValueType secondArg);
    
    
    /**
     * @param firstArg 
     * @param secondArg 
     * @return a Diagnostic interpreting whether it is valid to perform the
     * operation on the two arguments
     */
    public abstract Diagnostic validate(ValueType firstArg, ValueType secondArg);
    
    private static String determineJSFVersion(IStructuredDocumentContext context)
    {
        final IWorkspaceContextResolver wkResolver = 
            IStructuredDocumentContextResolverFactory.
                INSTANCE.getWorkspaceContextResolver(context);
        
        IProject project = wkResolver.getProject();
        
        IProjectFacetVersion  projectVersion = JSFAppConfigUtils.getProjectFacet(project);
        
        if (projectVersion != null)
        {
            return projectVersion.getVersionString();
        }
        
        return null;
    }
}
