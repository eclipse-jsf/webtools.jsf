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

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.context.structureddocument.internal.provisional.IStructuredDocumentContext;
import org.eclipse.jst.jsp.core.internal.java.jspel.JSPELParserConstants;
import org.eclipse.jst.jsp.core.internal.java.jspel.Token;

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
     * @param operatorToken
     * @param context -- the current EL document context; must not be null
     * @return a binary operator based on the provided token
     * @throws IllegalArgumentException if the token is not a recognized
     * EL binary operator token or if context is null
     */
    public static BinaryOperator getBinaryOperator(Token operatorToken, IStructuredDocumentContext context)
    {
        if (context == null)
        {
            throw new IllegalArgumentException("Context must not be null");
        }
        
        switch (operatorToken.kind)
        {
            case JSPELParserConstants.AND1:
            case JSPELParserConstants.AND2:
                return new AndBinaryOperator();
                
            case JSPELParserConstants.OR1:
            case JSPELParserConstants.OR2:
                return new OrBinaryOperator();
                
            case JSPELParserConstants.EQ1:
            case JSPELParserConstants.EQ2:
                return new EqualsBinaryRelationalOperator();
                
            case JSPELParserConstants.NEQ1:
            case JSPELParserConstants.NEQ2:
                return new NotEqualsBinaryRelationalOperator();
                
            case JSPELParserConstants.GT1:
            case JSPELParserConstants.GT2:
                return new GreaterThanRelationalBinaryOperator();
                
            case JSPELParserConstants.GE1:
            case JSPELParserConstants.GE2:
                return new GreaterThanEqRelationalBinaryOperator();
                
            case JSPELParserConstants.LT1:
            case JSPELParserConstants.LT2:
                return new LessThanRelationalBinaryOperator();
                
            case JSPELParserConstants.LE1:
            case JSPELParserConstants.LE2:
                return new LessThanEqRelationalBinaryOperator();
                
            case JSPELParserConstants.PLUS:
                return new AddArithmeticBinaryOperator();
                
            case JSPELParserConstants.MINUS:
                return new SubtractArithmeticBinaryOperator();
                
            case JSPELParserConstants.MULTIPLY:
                return new MultiplyArithmeticBinaryOperator();
                
            case JSPELParserConstants.DIVIDE1:
            case JSPELParserConstants.DIVIDE2:
                return new DivArithmeticBinaryOperator();
            
            case JSPELParserConstants.MODULUS1:
            case JSPELParserConstants.MODULUS2:
                return new ModArithmeticBinaryOperator();
        }
        
        throw new IllegalArgumentException("Unknown binary operator: "+operatorToken.image);
    }
    
    /**
     * 
     * Constructor
     */
    BinaryOperator() {/* no construction or sub-classing outside package*/}
    
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
}
