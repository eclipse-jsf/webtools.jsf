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
 * Represents the EL modulo operator: % or mod
 * Based JSP.2.3.5.3
 * 
 * @author cbateman
 *
 */
/*package*/ class ModArithmeticBinaryOperator extends ArithmeticBinaryOperator 
{
    private static final String MODULO = "modulo"; //$NON-NLS-1$

    ModArithmeticBinaryOperator(DiagnosticFactory diagnosticFactory) {
        super(diagnosticFactory);
    }

    public ValueType performOperation(ValueType firstArg, ValueType secondArg) 
    {
        // JSP.2.3.5.3, step 1 if both null, then return zero
        if (TypeCoercer.typeIsNull(firstArg.getSignature())
                && TypeCoercer.typeIsNull(secondArg.getSignature()))
        {
            return IntegerLiteralType.ZERO;
        }

        final String boxedFirstArg = TypeTransformer.transformBoxPrimitives(firstArg.getSignature());
        final String boxedSecondArg = TypeTransformer.transformBoxPrimitives(secondArg.getSignature());
        
        // JSP.2.3.5.3, step 2, if either arg is BigDecimal, Float, Double
        // or String (ignoring whether it is value coercable), then coerce
        // to Double and do op
        if (TypeConstants.TYPE_BIG_DOUBLE.equals(boxedFirstArg)
                || TypeConstants.TYPE_BIG_DOUBLE.equals(boxedSecondArg)
                || TypeConstants.TYPE_BOXED_DOUBLE.equals(boxedFirstArg)
                || TypeConstants.TYPE_BOXED_DOUBLE.equals(boxedSecondArg)
                || TypeConstants.TYPE_BOXED_FLOAT.equals(boxedFirstArg)
                || TypeConstants.TYPE_BOXED_FLOAT.equals(boxedSecondArg))
        {
            // TODO: handle case where one is a literal or resolvable string value
            // that containss ".", "e" or "E"
            return performDouble(firstArg, secondArg);
        }
        
        // JSP.2.3.5.3, step 3, if either arg is a BigInteger, coerce
        // both to BigInteger
        if (TypeConstants.TYPE_BIG_INTEGER.equals(boxedFirstArg)
                || TypeConstants.TYPE_BIG_INTEGER.equals(boxedSecondArg))
        {
            return performBigInteger(firstArg, secondArg);
        }
        
        // JSP.2.3.5.3, step 4, otherwise try to perform as a Long op
        return performLong(firstArg, secondArg);
    }

    public Diagnostic validate(ValueType firstArg, ValueType secondArg) {
    	if (TypeConstants.TYPE_JAVAOBJECT.equals(firstArg.getSignature()) ||
    			TypeConstants.TYPE_JAVAOBJECT.equals(secondArg.getSignature())) {
    		return Diagnostic.OK_INSTANCE;
    	}
    	
        // JSP.2.3.5.3, step 1 if both null, then return zero
        if (TypeCoercer.typeIsNull(firstArg.getSignature())
                && TypeCoercer.typeIsNull(secondArg.getSignature()))
        {
            return _diagnosticFactory.create_BINARY_OP_BOTH_OPERANDS_NULL(MODULO);
        }

        final String boxedFirstArg = TypeTransformer.transformBoxPrimitives(firstArg.getSignature());
        final String boxedSecondArg = TypeTransformer.transformBoxPrimitives(secondArg.getSignature());
        
        // JSP.2.3.5.3, step 2, if either arg is BigDecimal, Float, Double
        // or String (ignoring whether it is value coercable), then coerce
        // to Double and do op
        if (TypeConstants.TYPE_BIG_DOUBLE.equals(boxedFirstArg)
                || TypeConstants.TYPE_BIG_DOUBLE.equals(boxedSecondArg)
                || TypeConstants.TYPE_BOXED_DOUBLE.equals(boxedFirstArg)
                || TypeConstants.TYPE_BOXED_DOUBLE.equals(boxedSecondArg)
                || TypeConstants.TYPE_BOXED_FLOAT.equals(boxedFirstArg)
                || TypeConstants.TYPE_BOXED_FLOAT.equals(boxedSecondArg))
        {
            // TODO: handle case where one is a literal or resolvable string value
            // that containss ".", "e" or "E"
            return validateDouble(firstArg, secondArg);
        }
        
        // JSP.2.3.5.3, step 3, if either arg is a BigInteger, coerce
        // both to BigInteger
        if (TypeConstants.TYPE_BIG_INTEGER.equals(boxedFirstArg)
                || TypeConstants.TYPE_BIG_INTEGER.equals(boxedSecondArg))
        {
            return validateBigInteger(firstArg, secondArg);
        }
        
        // JSP.2.3.5.3, step 4, otherwise try to perform as a Long op
        return validateLong(firstArg, secondArg);
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
                
                if (secondValue.doubleValue() == 0.0)
                {
                    // division by zero
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
                        doRealOperation(new Double(firstValue.doubleValue()), 
                                        new Double(secondValue.doubleValue())).doubleValue());
            }

            // if we get to here, the coercion is valid, so a Double will be
            // returned
            return new ValueType(Signature.SIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS);
        }
        catch (TypeCoercionException tce)
        {
            // could not coerce, so null
            return null;
        }
    }
    
    private ValueType performBigInteger(ValueType firstArg, ValueType secondArg)
    {
        try
        {
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(firstArg.getSignature()));
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(secondArg.getSignature()));
            
            // check second arg for zero
            if (secondArg instanceof LiteralType)
            {
                if (((LiteralType)secondArg).coerceToNumber(BigInteger.class).equals(BigInteger.ZERO))
                {
                    // division by zero
                    return null;
                }
            }
            
            // since one of the args is BigInteger, they are not both literals,
            // so if we get to here, we have a successful mod of two
            // big integers to one big integer
            return new ValueType(TypeConstants.TYPE_BIG_INTEGER, IAssignable.ASSIGNMENT_TYPE_RHS);
        }
        catch (TypeCoercionException tce)
        {
            // no coercion
            return null;
        }
    }
    
    private ValueType performLong(ValueType firstArg, ValueType secondArg)
    {
        try
        {
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(firstArg.getSignature()));
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(secondArg.getSignature()));
        
            Number secondValue = null;
            
            if (secondArg instanceof LiteralType)
            {
                secondValue = ((LiteralType)secondArg).coerceToNumber(Long.class);
                
                if (secondValue.longValue() == 0)
                {
                    // division by zero
                    return null;
                }
            }
            
            Number firstValue = null;
            
            if (firstArg instanceof LiteralType)
            {
                firstValue = ((LiteralType)firstArg).coerceToNumber(Long.class);
            }
            
            if (firstValue != null && secondValue != null)
            {
                return new IntegerLiteralType(
                        doRealOperation(Long.valueOf(firstValue.longValue()), 
                                        Long.valueOf(secondValue.longValue())).longValue());
            }

            // if we get to here, the coercion is valid, so a Long will be
            // returned
            return new ValueType(Signature.SIG_LONG, IAssignable.ASSIGNMENT_TYPE_RHS);
        }
        catch (TypeCoercionException tce)
        {
            // could not coerce, so null
            return null;
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
                
                if (secondValue.doubleValue() == 0.0)
                {
                    // division by zero
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
                return _diagnosticFactory.
                    create_BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME
                        (MODULO, Double.toString(
                                firstValue.doubleValue()%secondValue.doubleValue()));
            }

            // if we get to here, the coercion is valid, so a Double will be
            // returned and everything is good
            return Diagnostic.OK_INSTANCE;
        }
        catch (TypeCoercionException tce)
        {
            // could not coerce, so error
            return _diagnosticFactory.
                create_BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION(MODULO);
        }
    }
    
    private Diagnostic validateBigInteger(ValueType firstArg, ValueType secondArg)
    {
        try
        {
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(firstArg.getSignature()));
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(secondArg.getSignature()));
            
            // check second arg for zero
            if (secondArg instanceof LiteralType)
            {
                if (((LiteralType)secondArg).coerceToNumber(BigInteger.class).equals(BigInteger.ZERO))
                {
                    // division by zero
                    return _diagnosticFactory.create_BINARY_OP_POSSIBLE_DIVISION_BY_ZERO();
                }
            }
            
            // since one of the args is BigInteger, they are not both literals,
            // so if we get to here, we have a successful mod of two
            // big integers to one big integer
            return Diagnostic.OK_INSTANCE;
        }
        catch (TypeCoercionException tce)
        {
            // no coercion
            return _diagnosticFactory.
                create_BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION(MODULO);
        }        
    }
    
    private Diagnostic validateLong(ValueType firstArg, ValueType secondArg)
    {
        try
        {
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(firstArg.getSignature()));
            TypeCoercer.coerceToNumber(TypeTransformer.transformBoxPrimitives(secondArg.getSignature()));
        
            Number secondValue = null;
            
            if (secondArg instanceof LiteralType)
            {
                secondValue = ((LiteralType)secondArg).coerceToNumber(Long.class);
                
                if (secondValue.longValue() == 0)
                {
                    // division by zero
                    return _diagnosticFactory.
                        create_BINARY_OP_POSSIBLE_DIVISION_BY_ZERO();
                }
            }
            
            Number firstValue = null;
            
            if (firstArg instanceof LiteralType)
            {
                firstValue = ((LiteralType)firstArg).coerceToNumber(Long.class);
            }
            
            if (firstValue != null && secondValue != null)
            {
                return _diagnosticFactory.
                    create_BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME
                        (MODULO, Long.toString(firstValue.longValue()%secondValue.longValue())); 
            }

            // if we get to here, the coercion is valid, so a Long will be
            // returned
            return Diagnostic.OK_INSTANCE;
        }
        catch (TypeCoercionException tce)
        {
            // could not coerce, so error
            return _diagnosticFactory.
                create_BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION(MODULO);
        }
    }
    
    protected Long doRealOperation(Long firstArg, Long secondArg) {
        return Long.valueOf(firstArg.longValue() % secondArg.longValue());
     }

     protected Double doRealOperation(Double firstArg, Double secondArg) {
         return new Double(firstArg.doubleValue() % secondArg.doubleValue());
     }

     protected BigDecimal doRealOperation(BigDecimal firstArg,
             BigDecimal secondArg) {
        return new BigDecimal(firstArg.doubleValue() % secondArg.doubleValue());
     }

    protected String getOperatorName() {
        return MODULO;
    }
     
}
