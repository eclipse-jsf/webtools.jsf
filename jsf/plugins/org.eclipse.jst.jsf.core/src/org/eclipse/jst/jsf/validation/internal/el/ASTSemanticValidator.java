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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.internal.types.BooleanLiteralType;
import org.eclipse.jst.jsf.common.internal.types.FloatLiteralType;
import org.eclipse.jst.jsf.common.internal.types.IntegerLiteralType;
import org.eclipse.jst.jsf.common.internal.types.LiteralType;
import org.eclipse.jst.jsf.common.internal.types.MethodType;
import org.eclipse.jst.jsf.common.internal.types.NullLiteralType;
import org.eclipse.jst.jsf.common.internal.types.SignatureBasedType;
import org.eclipse.jst.jsf.common.internal.types.StringLiteralType;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.internal.provisional.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol;
import org.eclipse.jst.jsf.context.symbol.internal.util.IObjectSymbolBasedValueType;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.internal.provisional.resolver.ISymbolContextResolver;
import org.eclipse.jst.jsf.designtime.internal.provisional.resolver.StructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.ValidationMessageFactory;
import org.eclipse.jst.jsf.validation.internal.el.operators.BinaryOperator;
import org.eclipse.jst.jsf.validation.internal.el.operators.BracketOperator;
import org.eclipse.jst.jsf.validation.internal.el.operators.DotOperator;
import org.eclipse.jst.jsf.validation.internal.el.operators.TernaryChoiceOperator;
import org.eclipse.jst.jsf.validation.internal.el.operators.UnaryOperator;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTAddExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTAndExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTChoiceExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTEqualityExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTFunctionInvocation;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTLiteral;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTMultiplyExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTOperatorExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTOrExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTRelationalExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTUnaryExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTValue;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTValuePrefix;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTValueSuffix;
import org.eclipse.jst.jsp.core.internal.java.jspel.JSPELParser;
import org.eclipse.jst.jsp.core.internal.java.jspel.JSPELParserConstants;
import org.eclipse.jst.jsp.core.internal.java.jspel.JSPELParserVisitor;
import org.eclipse.jst.jsp.core.internal.java.jspel.SimpleNode;
import org.eclipse.jst.jsp.core.internal.java.jspel.Token;
import org.eclipse.wst.validation.internal.core.Message;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;


class ASTSemanticValidator implements JSPELParserVisitor, IExpressionSemanticValidator
{
	private final IFile								_targetFile;
	private final ASTExpression 					_expr;
	private final IStructuredDocumentContext		_context;
	private final ISymbolContextResolver			_symbolResolver;
	private final List								_messages;
	private final EvaluationTracker                 _tracker;
    private boolean                                 _validatorHasBeenCalled; //=false
    
	ASTSemanticValidator(ASTExpression expr, IStructuredDocumentContext context)
	{
		final IWorkspaceContextResolver resolver = 
            IStructuredDocumentContextResolverFactory.
                INSTANCE.getWorkspaceContextResolver(context);
        
		if (resolver != null)
		{
			_targetFile = (IFile) resolver.getResource();
		}
		else
		{
			_targetFile = null;
		}
        
		_expr = expr;
		_context = context;
		_symbolResolver = StructuredDocumentSymbolResolverFactory.getInstance().getSymbolContextResolver(_context);
		_messages = new ArrayList();
        _tracker = new EvaluationTracker();
	}
	
	/* (non-Javadoc)
     * @see org.eclipse.jst.jsf.validation.internal.el.IExpressionSemanticValidator#validate()
     */
	public void validate()
	{
		_expr.jjtAccept(this, _tracker);
        _validatorHasBeenCalled = true;
	}
	
	public Object visit(ASTAddExpression node, Object data) 
	{
        performBinaryEvaluation(node, (EvaluationTracker)data);
        return data;
	}

	public Object visit(ASTAndExpression node, Object data) 
	{
        performBinaryEvaluation(node, (EvaluationTracker)data);
        return data;
	}

	public Object visit(ASTChoiceExpression node, Object data) 
    {
        if (node.jjtGetNumChildren() != 3)
        {
            throw new AssertionError("Binary operators should always have two sub-expressions");
        }
        
        // evaluate choice argument
        node.jjtGetChild(0).jjtAccept(this, data);
        final ValueType choiceArg = ((EvaluationTracker)data).getValueType();
        // evaluate when true argument
        node.jjtGetChild(1).jjtAccept(this, data);
        final ValueType whenTrueArg = ((EvaluationTracker)data).getValueType();
        //evaluate when false argument
        node.jjtGetChild(2).jjtAccept(this, data);
        final ValueType whenFalseArg = ((EvaluationTracker)data).getValueType();
        
        
        if (choiceArg != null && whenTrueArg != null && whenFalseArg != null)
        {
            final TernaryChoiceOperator operator = 
                new TernaryChoiceOperator();
            
            final Diagnostic diagnostic = 
                operator.validate(choiceArg/* whenTrueArg, whenFalseArg*/);
            
            if (diagnostic.getSeverity() != Diagnostic.OK)
            {
                final Token firstToken = node.getFirstToken();
                final int offset = _context.getDocumentPosition() + firstToken.beginColumn - 1;
                final int length = node.getLastToken().endColumn - firstToken.beginColumn+1;
                final Message message = 
                    ValidationMessageFactory.createFromDiagnostic(diagnostic, 
                                                   offset, length, _targetFile);
                _messages.add(message);
            }
            
            ((EvaluationTracker)data).setType(operator.perform(choiceArg, whenTrueArg, whenFalseArg));
        }
        else
        {
            ((EvaluationTracker)data).setType(null);
        }

        return data;
	}

	public Object visit(ASTEqualityExpression node, Object data) 
    {
        performBinaryEvaluation(node, (EvaluationTracker)data);
        return data;
    }

	public Object visit(ASTExpression node, Object data) {
		return node.childrenAccept(this, data);
	}

	public Object visit(ASTFunctionInvocation node, Object data) 
    {
	    // when we see a function invocation, null the type
        // we do not validate function invocations currently
        Object retVal = node.childrenAccept(this, data);
        ((EvaluationTracker)data).setType(null);
        return retVal;
	}

	public Object visit(ASTLiteral node, Object data) 
    {
        // note, there is an implicit assumption here that literals
        // are all terminals (leafs in the tree)
        if (node.jjtGetNumChildren() > 0)
        {
            throw new AssertionError("Literals should be terminal");
        }
        
        LiteralType type = null;
        
        Token  literalToken = node.getFirstToken();
        
        switch (literalToken.kind)
        {
            case JSPELParserConstants.STRING_LITERAL:
                type = new StringLiteralType(stripQuotes(literalToken.image));
            break;
            
            case JSPELParserConstants.INTEGER_LITERAL:
                type = new IntegerLiteralType(Long.parseLong(literalToken.image));
            break;
        
            case JSPELParserConstants.FLOATING_POINT_LITERAL:
                type = new FloatLiteralType(Double.parseDouble(literalToken.image));
            break;
            
            case JSPELParserConstants.FALSE:
                type = BooleanLiteralType.FALSE;
            break;
            
            case JSPELParserConstants.TRUE:
                type = BooleanLiteralType.TRUE;
            break;
            
            case JSPELParserConstants.NULL:
                type = NullLiteralType.SINGLETON;
            break;
            
            default:
                JSFCorePlugin.log("Unknown EL literal: " +literalToken.toString(), new Throwable("This throwable simply used to mark a stack trace"));
        }

        ((EvaluationTracker)data).setType(type);
        return data;
	}

    private String stripQuotes(String stringLiteral)
    {
        if (stringLiteral.startsWith("'")
                || stringLiteral.startsWith("\""))
                        
        {
            if (stringLiteral.length() > 2)
            {
                // take 'literal' -> literal
                return stringLiteral.substring(1, stringLiteral.length()-1);
            }
            // if only two characters, then the empty string
            return "";
        }
        
        return stringLiteral;
    }
    
	public Object visit(ASTMultiplyExpression node, Object data) 
    {
        performBinaryEvaluation(node, (EvaluationTracker)data);
        return data;
	}

	public Object visit(ASTOrExpression node, Object data) 
    {
	    performBinaryEvaluation(node, (EvaluationTracker)data);
        return data;
	}

	public Object visit(ASTRelationalExpression node, Object data) 
    {
        performBinaryEvaluation(node, (EvaluationTracker)data);
        return data;
	}

	public Object visit(ASTUnaryExpression node, Object data) 
    {
        // assertion here is that this expression decomposes:
        // UnaryExpr -> Value
        // UnaryExpr -> UnaryOp UnaryExpression
        // since UnaryOp is a terminal (-,!,not,empty) node will
        // always have exactly one child
        node.childrenAccept(this, data);
        final SignatureBasedType type = ((EvaluationTracker)data).getType();
        
        if (type != null)
        {
            final Token  firstToken = node.getFirstToken();
            if (UnaryOperator.isUnaryOperator(firstToken))
            {
                if (type instanceof ValueType)
                {
                    final UnaryOperator unaryOp = UnaryOperator.createUnaryOperator(firstToken);
                    final Diagnostic diagnostic = unaryOp.validate((ValueType)type);
                    
                    if (diagnostic.getSeverity() != Diagnostic.OK)
                    {
                        final int offset = _context.getDocumentPosition() + firstToken.beginColumn - 1;
                        final int length = node.getLastToken().endColumn - firstToken.beginColumn+1;
                        final Message message = 
                            ValidationMessageFactory.createFromDiagnostic(diagnostic, offset, length, _targetFile);
                        _messages.add(message);
                    }
   
                    ((EvaluationTracker)data).
                            setType(unaryOp.performOperation ((ValueType)type));
                }
                // cannot apply operations to method bindings
                else
                {
                    final int offset = _context.getDocumentPosition() + 
                                    firstToken.beginColumn - 1;
                    final int length = node.getLastToken().endColumn - 
                                        firstToken.beginColumn+1;
                    
                    _messages.add(ValidationMessageFactory.
                            createFromDiagnostic(DiagnosticFactory.create_CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING(),
                                    offset, length, _targetFile));
                }
            }
        }

        return data;
	}

	public Object visit(ASTValue node, final Object data) {
        ValueExpressionTracker  tracker = new ValueExpressionTracker();
        
        ((EvaluationTracker)data).setValueTracker(tracker);
        
		node.childrenAccept(this, data);
        
        SignatureBasedType type = ((EvaluationTracker)data).getType();
        
        // now check the tracker.  If the last property in the expression
        // is non-null (i.e. the value has one or more suffices) then we
        // to very the leaf node (i.e. 'z' in #{x.y.z}) is more than just
        // an intermediate value used to get to other properties
        if (type instanceof IObjectSymbolBasedValueType
                && ((IObjectSymbolBasedValueType)type).getSymbol() instanceof IPropertySymbol
                && ((IPropertySymbol)((IObjectSymbolBasedValueType)type).getSymbol()).isIntermediate())
        {
            final int problemStartOffset = tracker.getCurPropertySymbolOffset();
            final int length = tracker.getCurPropertySymbolLength();
            _messages.add(ValidationMessageFactory.createFromDiagnostic(
                    DiagnosticFactory.create_MEMBER_NOT_FOUND(
                        ((IPropertySymbol)((IObjectSymbolBasedValueType)type).getSymbol()).getName()
                        , tracker.getRootSymbolName())
                        , problemStartOffset, length, _targetFile));
        }
            
        return data;
	}

	public Object visit(ASTValuePrefix node, final Object data) 
    {
		if (node.jjtGetNumChildren() == 0)
		{
			final Token token = node.getFirstToken();
			final String image = token.image;

			final ISymbol symbol = _symbolResolver.getVariable(image);

			if (symbol == null)
			{
                final int problemStartOffset = 
                    _context.getDocumentPosition() + token.beginColumn - 1;
                final int length = token.endColumn - token.beginColumn + 1;
 
				_messages.add(ValidationMessageFactory.createFromDiagnostic(
                        DiagnosticFactory.create_VARIABLE_NOT_FOUND(image), 
                        problemStartOffset, length, _targetFile));
			}
			else if (symbol instanceof IInstanceSymbol)
			{
                final IObjectSymbolBasedValueType symbolType =
                    IObjectSymbolBasedValueType.getInstance(symbol);
                ((EvaluationTracker) data).setType(symbolType);
			}
		}

		return node.childrenAccept(this, data);
	}

	public Object visit(final ASTValueSuffix node, final Object data) 
    {
        final ValueExpressionTracker tracker = ((EvaluationTracker) data).getValueTracker();
        final SignatureBasedType type = ((EvaluationTracker) data).getType();
        
        if (type instanceof IObjectSymbolBasedValueType)
        {
            final IObjectSymbolBasedValueType symbolType = 
                (IObjectSymbolBasedValueType) type;
    		final Token firstToken = node.getFirstToken();
    
    		if (node.jjtGetNumChildren() == 0
    				&& firstToken.kind == JSPELParserConstants.DOT)
    		{
    			final Token dotId = node.getLastToken();
    
                final int startOffset = 
                    _context.getDocumentPosition() + dotId.beginColumn - 1;
                final int length = dotId.endColumn - dotId.beginColumn + 1;

                final DotOperator dotOp = new DotOperator(_targetFile);

                final StringLiteralType  suffixLiteral = new StringLiteralType(dotId.image);
                Diagnostic diag = 
                    dotOp.validate(symbolType, 
                                        suffixLiteral);
                
			    if (diag.getSeverity() != Diagnostic.OK)
			    {
			        _messages.add(ValidationMessageFactory.createFromDiagnostic(
                         diag, startOffset, length, _targetFile));
                    ((EvaluationTracker) data).setType(null);
                }
                else
                {
//                    // if the base (value-a) is a map, then using the bracket value-a['y'] type
//                    // syntax is recommended.  Note that we do this here instead of 
//                    // DotOperator so that we don't tie the default property resolver
//                    // behaviour to that operator class.  If someone changes the rules
//                    // of how the prop resolver interprets the base, then they may want to
//                    // write their own validator that doesn't do this
//                    if (symbolType.getSymbol().supportsCoercion(TypeConstants.TYPE_MAP))
//                    {
//                        _messages.add(ValidationMessageFactory.createFromDiagnostic(
//                                DiagnosticFactory.create_BINARY_OP_DOT_WITH_VALUEA_MAP_SHOULD_USE_ARRAY
//                                    (symbolType.getSymbol().getName(), dotId.image), 
//                                        startOffset, length, _targetFile));
//                    }
                    
                    ((EvaluationTracker) data).setType(dotOp.performOperation(symbolType, 
                            suffixLiteral));
                    tracker.setCurMemberSymbol(startOffset, length);
                }
                
                // we finished with the single dot suffix here
                return data;
    		}
            else if (firstToken.kind == JSPELParserConstants.LBRACKET)
            {
                final EvaluationTracker subExprTracker = new EvaluationTracker();
                node.childrenAccept(this, subExprTracker);

                final SignatureBasedType subExprType = subExprTracker.getType();

                if (subExprType instanceof ValueType)
                {
                    final Token lastToken = node.getLastToken();
                    final int startOffset = 
                        _context.getDocumentPosition() + firstToken.beginColumn - 1;
                    final int length = lastToken.endColumn - firstToken.beginColumn + 1;

                    final BracketOperator  bracketOperator = new BracketOperator(_targetFile);
                    
                    final Diagnostic diag = 
                        bracketOperator.validate(symbolType, 
                                            (ValueType)subExprType);

                    if (diag.getSeverity() != Diagnostic.OK)
                    {
                        _messages.add(ValidationMessageFactory.createFromDiagnostic(
                                diag, 
                                startOffset, length, _targetFile));
                        ((EvaluationTracker) data).setType(null);
                    }
                    else
                    {
                        ((EvaluationTracker) data).setType(bracketOperator.performOperation(symbolType, 
                                (ValueType)subExprType));
                        tracker.setCurMemberSymbol(startOffset, length);
                    }
                }
                // we are finished with the bracketed suffix at this point.
                return data;
            }
        }
        
        // don't bother to accept children, since if we haven't done
        // something above, there's not much sensible we can do with it
        // clear the type first though
        ((EvaluationTracker) data).setType(null);
		return data; //node.childrenAccept(this, data);
	}

	public Object visit(SimpleNode node, Object data) {
		return node.childrenAccept(this, data);
	}

	/**
	 * Copies stored messages into the validation reporter 
	 * @param validator
	 * @param reporter
	 */
	public void reportFindings(IValidator validator, IReporter reporter)
	{
		for (final Iterator it = _messages.iterator(); it.hasNext();)
		{
			reporter.addMessage(validator, (IMessage) it.next());
		}
	}
    
    private void performBinaryEvaluation(ASTOperatorExpression node, EvaluationTracker tracker)
    {
        if (node.jjtGetNumChildren() < 2)
        {
            throw new AssertionError("Binary operators should always have at least two sub-expressions");
        }
        else if (node.getOperatorTokens().size() != node.jjtGetNumChildren()-1)
        {
            throw new AssertionError("Binary operators should always have one operator token less than number of sub-expressions");
        }
        
        // evaluate left-most argument
        node.jjtGetChild(0).jjtAccept(this, tracker);
        
        ValueType curType = getValueTypeForBinaryOperation(tracker.getType(), (SimpleNode) node.jjtGetChild(0)); 
            
        for (int child = 1; child < node.jjtGetNumChildren(); child++)
        {
            // evaluate next argument running left-to-right
            node.jjtGetChild(child).jjtAccept(this, tracker);
            final ValueType secondType = 
                getValueTypeForBinaryOperation(tracker.getType(), (SimpleNode) node.jjtGetChild(child));
            
            if (curType != null && secondType != null)
            {
                final BinaryOperator operator = 
                    BinaryOperator.getBinaryOperator((Token)node.getOperatorTokens().get(child-1), _context);
                
                final Diagnostic diagnostic = operator.validate(curType, secondType);
                
                if (diagnostic.getSeverity() != Diagnostic.OK)
                {
                    final Token firstToken = node.getFirstToken();
                    final int offset = _context.getDocumentPosition() + firstToken.beginColumn - 1;
                    final int length = node.getLastToken().endColumn - firstToken.beginColumn+1;
                    final Message message = 
                        ValidationMessageFactory.createFromDiagnostic
                                (diagnostic, offset, length, _targetFile);
                    _messages.add(message);
                }
                
                curType = operator.performOperation(curType, secondType);
            }
        }
        
        tracker.setType(curType);
    }

    private ValueType getValueTypeForBinaryOperation(SignatureBasedType type, SimpleNode node)
    {
        if (type instanceof ValueType)
        {
            return (ValueType) type;
        }
        else if (type instanceof MethodType)
        {
            final int offset = _context.getDocumentPosition() + node.getFirstToken().beginColumn - 1;
            final int length = node.getLastToken().endColumn - node.getFirstToken().beginColumn+1;
            
            _messages.add(ValidationMessageFactory.createFromDiagnostic
                    (DiagnosticFactory.create_CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING(), 
                            offset, length, _targetFile));
        }

        return null;
    }
    
	/* (non-Javadoc)
     * @see org.eclipse.jst.jsf.validation.internal.el.IExpressionSemanticValidator#getMessages()
     */
	public List getMessages()
	{
	    if (!_validatorHasBeenCalled)
        {
	        throw new AssertionError("Should not call getMessages before validate has been called");
        }
		return _messages;
	}
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.validation.internal.el.IExpressionSemanticValidator#getExpressionType()
     */
    public SignatureBasedType getExpressionType()
    {
        return _tracker.getType();
    }
    
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        String elText = "";
        int nextCharacter;
        
        while(((nextCharacter = System.in.read()) != -1))
        {
            char nextChar = (char) nextCharacter;
            
            if (nextChar == '\n')
            {
                try
                {
                    JSPELParser parser = JSPELParser.createParser(elText);
                    ASTExpression expr = parser.Expression();
                    expr.dump("");
                }
                catch (Throwable t)
                {
                    t.printStackTrace(System.err);
                }
                
                elText = "";
            }
            else
            {
                elText += nextChar;
            }   
        }
    }
}
