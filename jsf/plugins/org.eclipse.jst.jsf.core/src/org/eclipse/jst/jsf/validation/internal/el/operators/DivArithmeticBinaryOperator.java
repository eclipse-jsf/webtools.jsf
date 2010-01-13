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
 * Represents dividing EL binary operators: div and / (same operator)
 * Based on JSP.2.3.5.2
 * 
 * @author cbateman
 *
 */
/*package*/ class DivArithmeticBinaryOperator extends ArithmeticBinaryOperator 
{

    private static final String DIVISION = "division"; //$NON-NLS-1$

    DivArithmeticBinaryOperator(DiagnosticFactory diagnosticFactory) {
        super(diagnosticFactory);
        // TODO Auto-generated constructor stub
    }

    public ValueType performOperation(ValueType firstArg, ValueType secondArg) 
    {
        // JSP.2.3.5.2, step one: if both null then always 0
        if (TypeCoercer.typeIsNull(firstArg.getSignature())
                && TypeCoercer.typeIsNull(secondArg.getSignature()))
        {
            return new IntegerLiteralType(0);
        }
        
        final String boxedFirstArg = TypeTransformer.transformBoxPrimitives(firstArg.getSignature());
        final String boxedSecondArg = TypeTransformer.transformBoxPrimitives(secondArg.getSignature());
        
        // JSP.2.3.5.2, step 2: if one arg is BigInteger or BigDecimal
        // then coerce to BigDecimal and do div
        if (TypeConstants.TYPE_BIG_DOUBLE.equals(boxedFirstArg)
                || TypeConstants.TYPE_BIG_DOUBLE.equals(boxedSecondArg)
                || TypeConstants.TYPE_BIG_INTEGER.equals(boxedFirstArg)
                || TypeConstants.TYPE_BIG_INTEGER.equals(boxedSecondArg))
        {
            return performBigDecimal(firstArg, secondArg);
        }
        
        return performDouble(firstArg, secondArg);
    }

    public Diagnostic validate(ValueType firstArg, ValueType secondArg) {
    	if (TypeConstants.TYPE_JAVAOBJECT.equals(firstArg.getSignature()) ||
    			TypeConstants.TYPE_JAVAOBJECT.equals(secondArg.getSignature())) {
    		return Diagnostic.OK_INSTANCE;
    	}

    	// JSP.2.3.5.2, step one: if both null then always 0
        if (TypeCoercer.typeIsNull(firstArg.getSignature())
                && TypeCoercer.typeIsNull(secondArg.getSignature()))
        {
            return _diagnosticFactory.create_BINARY_OP_BOTH_OPERANDS_NULL(DIVISION);
        }
        
        final String boxedFirstArg = TypeTransformer.transformBoxPrimitives(firstArg.getSignature());
        final String boxedSecondArg = TypeTransformer.transformBoxPrimitives(secondArg.getSignature());
        
        // JSP.2.3.5.2, step 2: if one arg is BigInteger or BigDecimal
        // then coerce to BigDecimal and do div
        if (TypeConstants.TYPE_BIG_DOUBLE.equals(boxedFirstArg)
                || TypeConstants.TYPE_BIG_DOUBLE.equals(boxedSecondArg)
                || TypeConstants.TYPE_BIG_INTEGER.equals(boxedFirstArg)
                || TypeConstants.TYPE_BIG_INTEGER.equals(boxedSecondArg))
        {
            return validateBigDecimal(firstArg, secondArg);
        }
        
        return validateDouble(firstArg, secondArg);
    }
    
    private ValueType performBigDecimal(ValueType firstArg, ValueType secondArg)
    {
        // since one or the other args must be either big decimal or big int,
        // we don't have two literals, so it is sufficient to ensure that we can
        // coerce both to numbers and check for div by zero and div of zero
        try
        {
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(firstArg.getSignature()));
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(secondArg.getSignature()));

            // if we get to here, the result is always BigDecimal unless we have
            // a div by zero
            if (secondArg instanceof LiteralType)
            {
                final Number coercedValue = ((LiteralType)secondArg).coerceToNumber(BigDecimal.class);
                if (((BigDecimal)coercedValue).equals(new BigDecimal(0)))
                {
                    return null;
                }
            }
            
            return new ValueType(TypeConstants.TYPE_BIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS);
        }
        catch (TypeCoercionException ce)
        {
            return null;
        }
    }
    
    private ValueType performDouble(ValueType firstArg, ValueType secondArg)
    {
        try
        {
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(firstArg.getSignature()));
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(secondArg.getSignature()));
            
            Number secondValue = null;
            if (secondArg instanceof LiteralType)
            {
                secondValue = ((LiteralType)secondArg).coerceToNumber(Double.class);
                // if the second value is definitely 0, then return null since
                // we have a div by zero
                if (secondValue.doubleValue() == 0.0)
                {
                    return null;
                }
            }

            Number firstValue = null;
            
            if (firstArg instanceof LiteralType)
            {
                firstValue = ((LiteralType)firstArg).coerceToNumber(Double.class);
            }
            
            if (firstValue != null && secondValue != null)
            {
                return new FloatLiteralType(
                        doRealOperation(new Double(firstValue.doubleValue())
                                , new Double(secondValue.doubleValue())).doubleValue());
            }

            // if not both literals and could coerce, then the type is double
            return new ValueType(Signature.SIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS);
        }
        catch (TypeCoercionException ce)
        {
            // could not coerce for the operation
            return null;
        }
    }
    
    private Diagnostic validateBigDecimal(ValueType firstArg, ValueType secondArg)
    {
        // since one or the other args must be either big decimal or big int,
        // we don't have two literals, so it is sufficient to ensure that we can
        // coerce both to numbers and check for div by zero and div of zero
        try
        {
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(firstArg.getSignature()));
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(secondArg.getSignature()));

            // if we get to here, the result is always BigDecimal unless we have
            // a div by zero
            if (secondArg instanceof LiteralType)
            {
                final Number coercedValue = ((LiteralType)secondArg).coerceToNumber(BigDecimal.class);
                if (((BigDecimal)coercedValue).equals(new BigDecimal(0)))
                {
                    return _diagnosticFactory.create_BINARY_OP_POSSIBLE_DIVISION_BY_ZERO();
                }
            }
            
            // everything's okay if we get here
            return Diagnostic.OK_INSTANCE;
        }
        catch (TypeCoercionException ce)
        {
            return _diagnosticFactory.create_BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION(DIVISION);
        }
    }
    
    private Diagnostic validateDouble(ValueType firstArg, ValueType secondArg)
    {
        try
        {
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(firstArg.getSignature()));
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(secondArg.getSignature()));
            
            Number secondValue = null;
            if (secondArg instanceof LiteralType)
            {
                secondValue = ((LiteralType)secondArg).coerceToNumber(Double.class);
                // if the second value is definitely 0, then return null since
                // we have a div by zero
                if (secondValue.doubleValue() == 0.0)
                {
                    return _diagnosticFactory.create_BINARY_OP_POSSIBLE_DIVISION_BY_ZERO();
                }
            }

            Number firstValue = null;
            
            if (firstArg instanceof LiteralType)
            {
                firstValue = ((LiteralType)firstArg).coerceToNumber(Double.class);
            }
            
            if (firstValue != null && secondValue != null)
            {
                String result 
                    = Double.toString(
                            doRealOperation(new Double(firstValue.doubleValue()), 
                                            new Double(secondValue.doubleValue())).doubleValue());

                return _diagnosticFactory.create_BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME(DIVISION, result);
            }

            // if not both literals and could coerce, then the type is double
            return Diagnostic.OK_INSTANCE;
        }
        catch (TypeCoercionException ce)
        {
            // could not coerce for the operation
            return _diagnosticFactory.create_BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION(DIVISION);
        }
    }

    protected BigDecimal doRealOperation(BigDecimal firstArg, BigDecimal secondArg) {
        // per JSP.2.3.5.2, step 2
        return firstArg.divide(secondArg, BigDecimal.ROUND_HALF_UP);
    }

    protected Double doRealOperation(Double firstArg, Double secondArg) 
    {
        return new Double(firstArg.doubleValue() / secondArg.doubleValue());
    }

    protected Long doRealOperation(Long firstArg, Long secondArg) {
        return Long.valueOf(firstArg.longValue() / secondArg.longValue());
    }

    protected String getOperatorName() {
        return DIVISION;
    }
}
