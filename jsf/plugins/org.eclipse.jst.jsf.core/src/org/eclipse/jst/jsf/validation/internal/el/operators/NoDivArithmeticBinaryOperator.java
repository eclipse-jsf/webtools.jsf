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

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.FloatLiteralType;
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.common.internal.types.IntegerLiteralType;
import org.eclipse.jst.jsf.common.internal.types.LiteralType;
import org.eclipse.jst.jsf.common.internal.types.TypeCoercer;
import org.eclipse.jst.jsf.common.internal.types.TypeCoercionException;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.TypeTransformer;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Represents non-dividing arithmetic EL operators: +,-,*
 * Based on JSP.2.3.5.1
 * 
 * @author cbateman
 *
 */
/*package*/ abstract class NoDivArithmeticBinaryOperator extends ArithmeticBinaryOperator 
{
    NoDivArithmeticBinaryOperator(DiagnosticFactory diagnosticFactory) {
        super(diagnosticFactory);
    }

    protected abstract Long doRealOperation(Long firstArg, Long secondArg);
    
    protected abstract Double doRealOperation(Double firstArg, Double secondArg);

    public ValueType performOperation(ValueType firstArg, ValueType secondArg)
    {
        // JSP.2.3.5.1, step 1, if either arg is null, return (Long) 0
        if (TypeCoercer.typeIsNull(firstArg.getSignature())
                && TypeCoercer.typeIsNull(secondArg.getSignature()))
        {
            return new IntegerLiteralType(0);
        }
        
        final String boxedFirstArg = TypeTransformer.transformBoxPrimitives(firstArg.getSignature());
        final String boxedSecondArg = TypeTransformer.transformBoxPrimitives(secondArg.getSignature());
        
        // JSP.2.3.5.1, step 2, if either arg is a BigDecimal, coerce to BigDecimal
        // and apply
        if (TypeConstants.TYPE_BIG_DOUBLE.equals(boxedFirstArg)
                || TypeConstants.TYPE_BIG_DOUBLE.equals(boxedSecondArg))
        {
            return handleNumericArithmetic(firstArg, secondArg, BigDecimal.class);
        }
        
        // JSP.2.3.5.1, step 3, if either arg is float or double or 
        // a String containing "., e or E", then coerce if the other is
        // a big int, coerce up to BigDecimal, else to Double
        // Note: we are ignoring strings we can't resolve to figure out
        // if the contain "., e or E".  Assume they always do
        if (TypeConstants.TYPE_BOXED_DOUBLE.equals(boxedFirstArg)
                ||TypeConstants.TYPE_BOXED_DOUBLE.equals(boxedSecondArg)
                ||TypeConstants.TYPE_BOXED_FLOAT.equals(boxedFirstArg)
                ||TypeConstants.TYPE_BOXED_FLOAT.equals(boxedSecondArg))
        {
            if (TypeConstants.TYPE_BIG_INTEGER.equals(boxedFirstArg)
                    ||TypeConstants.TYPE_BIG_INTEGER.equals(boxedSecondArg))
            {
                // if the other operand is BigInteger, treat as BigDecimal
                return handleNumericArithmetic(firstArg, secondArg, BigDecimal.class);
            }

            // otherwise as double
            return handleNumericArithmetic(firstArg, secondArg, Double.class);
        }
        
        // JSP.2.3.5.1, step 4, if one is a big integer, coerce to big integer
        if (TypeConstants.TYPE_BIG_INTEGER.equals(boxedFirstArg)
                || TypeConstants.TYPE_BIG_INTEGER.equals(boxedSecondArg))
        {
            return handleNumericArithmetic(firstArg, secondArg, BigInteger.class);
        }
        
        // JSP.2.3.5.1, step 5, otherwise, try to coerce to Long
        return handleNumericArithmetic(firstArg, secondArg, Long.class);
    }

    public Diagnostic validate(ValueType firstArg, ValueType secondArg) 
    {
    	if (TypeConstants.TYPE_JAVAOBJECT.equals(firstArg.getSignature()) ||
    			TypeConstants.TYPE_JAVAOBJECT.equals(secondArg.getSignature())) {
    		return Diagnostic.OK_INSTANCE;
    	}

    	// JSP.2.3.5.1, step 1, if either arg is null, return (Long) 0
        if (TypeCoercer.typeIsNull(firstArg.getSignature())
                && TypeCoercer.typeIsNull(secondArg.getSignature()))
        {
            return _diagnosticFactory.create_BINARY_OP_BOTH_OPERANDS_NULL(getOperatorName());
        }
        
        final String boxedFirstArg = TypeTransformer.transformBoxPrimitives(firstArg.getSignature());
        final String boxedSecondArg = TypeTransformer.transformBoxPrimitives(secondArg.getSignature());
        
        // JSP.2.3.5.1, step 2, if either arg is a BigDecimal, coerce to BigDecimal
        // and apply
        if (TypeConstants.TYPE_BIG_DOUBLE.equals(boxedFirstArg)
                || TypeConstants.TYPE_BIG_DOUBLE.equals(boxedSecondArg))
        {
            return validateNumericArithmetic(firstArg, secondArg, BigDecimal.class);
        }
        
        // JSP.2.3.5.1, step 3, if either arg is float or double or 
        // a String containing "., e or E", then coerce if the other is
        // a big int, coerce up to BigDecimal, else to Double
        // Note: we are ignoring strings we can't resolve to figure out
        // if the contain "., e or E".  Assume they always do
        if (TypeConstants.TYPE_BOXED_DOUBLE.equals(boxedFirstArg)
                ||TypeConstants.TYPE_BOXED_DOUBLE.equals(boxedSecondArg)
                ||TypeConstants.TYPE_BOXED_FLOAT.equals(boxedFirstArg)
                ||TypeConstants.TYPE_BOXED_FLOAT.equals(boxedSecondArg))
        {
            if (TypeConstants.TYPE_BIG_INTEGER.equals(boxedFirstArg)
                    ||TypeConstants.TYPE_BIG_INTEGER.equals(boxedSecondArg))
            {
                // if the other operand is BigInteger, treat as BigDecimal
                return validateNumericArithmetic(firstArg, secondArg, BigDecimal.class);
            }

            // otherwise as double
            return validateNumericArithmetic(firstArg, secondArg, Double.class);
        }
        
        // JSP.2.3.5.1, step 4, if one is a big integer, coerce to big integer
        if (TypeConstants.TYPE_BIG_INTEGER.equals(boxedFirstArg)
                || TypeConstants.TYPE_BIG_INTEGER.equals(boxedSecondArg))
        {
            return validateNumericArithmetic(firstArg, secondArg, BigInteger.class);
        }
        
        // JSP.2.3.5.1, step 5, otherwise, try to coerce to Long
        return validateNumericArithmetic(firstArg, secondArg, Long.class);
    }

    /**
     * @param firstArg
     * @param secondArg
     * @param numberType
     * @return a value type based on the result of the arithmetic operation
     */
    protected ValueType handleNumericArithmetic(ValueType firstArg, ValueType secondArg, Class numberType)
    {
        try
        {
//            final String coercedFirstArg = 
                TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(firstArg.getSignature()));
//            final String coercedSecondArg = 
                TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(secondArg.getSignature()));
            
            if (firstArg instanceof LiteralType && secondArg instanceof LiteralType)
            {
                try
                {
                    Number firstValue = 
                        ((LiteralType)firstArg).coerceToNumber(numberType);
                    Number secondValue = 
                        ((LiteralType)secondArg).coerceToNumber(numberType);
                    
                    LiteralType result = null;
                    
                    if (numberType == Double.class)
                    {
                        Double resultValue = 
                            doRealOperation((Double)firstValue, 
                                    (Double) secondValue);
                        result = new FloatLiteralType(resultValue.doubleValue());
                    }
                    else if (numberType == Long.class)
                    {
                        Long resultValue = 
                            doRealOperation((Long) firstValue, (Long) secondValue);
                        result = new IntegerLiteralType(resultValue.longValue());
                    }
                    else
                    {
                        throw new AssertionError("unsupport arithmetic upcast type"); //$NON-NLS-1$
                    }
                    
                    return result;  
                }
                catch (TypeCoercionException tce)
                {
                    // could happen if two string literals passed
                    return null;
                }
            }
            
            // if we get to here, then we have two valid numeric arith
            // types, but at least one is not a literal, so the best we can
            // say is that the return will be the same asthe type of numeric
            // coercion
            if (numberType == BigDecimal.class)
            {
                return new ValueType(TypeConstants.TYPE_BIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS);
            }
            else if (numberType == Double.class)
            {
                return new ValueType(Signature.SIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS);
            }
            else if (numberType == BigInteger.class)
            {
                return new ValueType(TypeConstants.TYPE_BIG_INTEGER, IAssignable.ASSIGNMENT_TYPE_RHS);
            }
            else
            {
                return new ValueType(Signature.SIG_LONG, IAssignable.ASSIGNMENT_TYPE_RHS);
            }
        }
        catch (TypeCoercionException tce)
        {
            // coercion to number failed, so no go
            return null;
        }
    }
    
    /**
     * @param firstArg
     * @param secondArg
     * @param numberType
     * @return a diagnostic validating the arithmetic expr firstArg op secondArg
     */
    protected Diagnostic validateNumericArithmetic(ValueType firstArg, ValueType secondArg, Class numberType)
    {
        try
        {
//            final String coercedFirstArg = 
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(firstArg.getSignature()));
//            final String coercedSecondArg = 
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(secondArg.getSignature()));
            
            if (firstArg instanceof LiteralType && secondArg instanceof LiteralType)
            {
                try
                {
                    Number firstValue = 
                        ((LiteralType)firstArg).coerceToNumber(numberType);
                    Number secondValue = 
                        ((LiteralType)secondArg).coerceToNumber(numberType);
                    
                    Number result = null;
                    
                    if (numberType == Double.class)
                    {
                        result =
                            doRealOperation((Double)firstValue, 
                                    (Double) secondValue);
                    }
                    else if (numberType == Long.class)
                    {
                        result =
                            doRealOperation((Long) firstValue, (Long) secondValue);
                    }
                    else
                    {
                        throw new AssertionError("unsupport arithmetic upcast type"); //$NON-NLS-1$
                    }
                    
                    return _diagnosticFactory.
                        create_BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME
                            (getOperatorName(), result.toString());  
                }
                catch (TypeCoercionException tce)
                {
                    // could happen when two strings are passed
                    return _diagnosticFactory.
                        create_BINARY_OP_COULD_NOT_COERCE_LITERALS_TO_NUMBERS();
                }
            }
            
            // if we get to here, then we have two valid numeric arith
            // types, but at least one is not a literal
            // everything should be ok
            return Diagnostic.OK_INSTANCE;
        }
        catch (TypeCoercionException tce)
        {
            // coercion to number failed, so no go
            return _diagnosticFactory.
                create_BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION(getOperatorName());
        }
    }

}
