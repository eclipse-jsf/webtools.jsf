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
import org.eclipse.jst.jsf.common.internal.types.BooleanLiteralType;
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.common.internal.types.LiteralType;
import org.eclipse.jst.jsf.common.internal.types.TypeCoercer;
import org.eclipse.jst.jsf.common.internal.types.TypeCoercionException;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.TypeTransformer;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * A relational binary operator for equality: "==" or "!="
 * 
 * @author cbateman
 *
 */
/*package*/ abstract class EqualityRelationalBinaryOperator extends RelationalBinaryOperator 
{
    /**
     * @param firstArg
     * @param secondArg
     * @return the result of the operation
     */
    protected abstract boolean doRealOperation(Boolean firstArg, Boolean secondArg);

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.validation.internal.el.operators.BinaryOperator#performOperation(org.eclipse.jst.jsf.core.internal.types.ValueType, org.eclipse.jst.jsf.core.internal.types.ValueType)
     */
    public ValueType performOperation(ValueType firstArg, ValueType secondArg) 
    {
        // JSP.2.3.5.7 step 2 if either operand is null, then not equal
        if (TypeCoercer.typeIsNull(firstArg.getSignature())
                || TypeCoercer.typeIsNull(secondArg.getSignature()))
        {
            return BooleanLiteralType.FALSE;
        }
        
        String boxedFirstType = TypeTransformer.transformBoxPrimitives(firstArg.getSignature());
        String boxedSecondType = TypeTransformer.transformBoxPrimitives(secondArg.getSignature());
        
        // JSP.2.3.5.7 step 3, if either is BigDecimal, promote both and compare
        if (TypeConstants.TYPE_BIG_DOUBLE.equals(boxedFirstType)
                || TypeConstants.TYPE_BIG_DOUBLE.equals(boxedSecondType))
        {
            return handleNumericComparison(firstArg, secondArg, BigDecimal.class);
        }
        
        // JSP.2.3.5.7, step 4 if either is a float or double, promote both to 
        // double and compare
        if (TypeConstants.TYPE_BOXED_DOUBLE.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_FLOAT.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_DOUBLE.equals(boxedSecondType)
                || TypeConstants.TYPE_BOXED_FLOAT.equals(boxedSecondType))
        {
            return handleNumericComparison(firstArg, secondArg, Double.class);
        }
        
        // JSP.2.3.5.7, step 5 if either is a big integer, promote and compare
        if (TypeConstants.TYPE_BIG_INTEGER.equals(boxedFirstType)
                || TypeConstants.TYPE_BIG_INTEGER.equals(boxedSecondType))
        {
            return handleNumericComparison(firstArg, secondArg, BigInteger.class);
        }
        
        // JSP.2.3.5.7, step 6 if either is Long or smaller, coerce both to Long
        if (TypeConstants.TYPE_BOXED_LONG.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_LONG.equals(boxedSecondType)
                || TypeConstants.TYPE_BOXED_INTEGER.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_INTEGER.equals(boxedSecondType)
                || TypeConstants.TYPE_BOXED_SHORT.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_SHORT.equals(boxedSecondType)
                || TypeConstants.TYPE_BOXED_BYTE.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_BYTE.equals(boxedSecondType)
                || TypeConstants.SIGNATURE_BOXED_CHARACTER.equals(boxedFirstType)
                || TypeConstants.SIGNATURE_BOXED_CHARACTER.equals(boxedSecondType))
        {
            return handleNumericComparison(firstArg, secondArg, Long.class);
        }
        
        // JSP.2.3.5.7, step 7 if either is a boolean, coerce to boolean
        if (TypeConstants.TYPE_BOXED_BOOLEAN.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_BOOLEAN.equals(boxedSecondType))
        {
            return handleBooleanComparison(firstArg, secondArg);
        }
        
        // JSP.2.3.5.7, step 8 if either is a string, coerce to string and
        // compare lexically
        if (TypeConstants.TYPE_STRING.equals(boxedFirstType)
                || TypeConstants.TYPE_STRING.equals(boxedSecondType))
        {
            return handleStringComparison(firstArg, secondArg);
        }
        
        // otherwise, an equal compare will be done A.equals(B).  Since 
        return new ValueType(TypeConstants.TYPE_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS);        
    }

    public Diagnostic validate(ValueType firstArg, ValueType secondArg) {
        
        // JSP.2.3.5.7 step 2 if either operand is null, then not equal
        if (TypeCoercer.typeIsNull(firstArg.getSignature())
                || TypeCoercer.typeIsNull(secondArg.getSignature()))
        {
            final boolean result = doRealOperation(new Integer(4), null);
            return DiagnosticFactory.create_BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME(Boolean.toString(result));
        }

        final String boxedFirstType = 
            TypeTransformer.transformBoxPrimitives(firstArg.getSignature());
        final String boxedSecondType = 
            TypeTransformer.transformBoxPrimitives(secondArg.getSignature());
        
        // JSP.2.3.5.7 step 3, if either is BigDecimal, promote both and compare
        if (TypeConstants.TYPE_BIG_DOUBLE.equals(boxedFirstType)
                || TypeConstants.TYPE_BIG_DOUBLE.equals(boxedSecondType))
        {
            return validateNumericComparison(firstArg, secondArg, BigDecimal.class);
        }
        
        // JSP.2.3.5.7, step 4 if either is a float or double, promote both to 
        // double and compare
        if (TypeConstants.TYPE_BOXED_DOUBLE.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_FLOAT.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_DOUBLE.equals(boxedSecondType)
                || TypeConstants.TYPE_BOXED_FLOAT.equals(boxedSecondType))
        {
            return validateNumericComparison(firstArg, secondArg, Double.class);
        }
        
        // JSP.2.3.5.7, step 5 if either is a big integer, promote and compare
        if (TypeConstants.TYPE_BIG_INTEGER.equals(boxedFirstType)
                || TypeConstants.TYPE_BIG_INTEGER.equals(boxedSecondType))
        {
            return validateNumericComparison(firstArg, secondArg, BigInteger.class);
        }
        
        // JSP.2.3.5.7, step 6 if either is Long or smaller, coerce both to Long
        if (TypeConstants.TYPE_BOXED_LONG.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_LONG.equals(boxedSecondType)
                || TypeConstants.TYPE_BOXED_INTEGER.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_INTEGER.equals(boxedSecondType)
                || TypeConstants.TYPE_BOXED_SHORT.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_SHORT.equals(boxedSecondType)
                || TypeConstants.TYPE_BOXED_BYTE.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_BYTE.equals(boxedSecondType)
                || TypeConstants.SIGNATURE_BOXED_CHARACTER.equals(boxedFirstType)
                || TypeConstants.SIGNATURE_BOXED_CHARACTER.equals(boxedSecondType))
        {
            return validateNumericComparison(firstArg, secondArg, Long.class);
        }
        
        // JSP.2.3.5.7, step 7 if either is a boolean, coerce to boolean
        if (TypeConstants.TYPE_BOXED_BOOLEAN.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_BOOLEAN.equals(boxedSecondType))
        {
            return validateBooleanComparison(firstArg, secondArg);
        }
        
        // JSP.2.3.5.7, step 8 if either is a string, coerce to string and
        // compare lexically
        if (TypeConstants.TYPE_STRING.equals(boxedFirstType)
                || TypeConstants.TYPE_STRING.equals(boxedSecondType))
        {
            return validateStringComparison(firstArg, secondArg);
        }

        // otherwise, an equal compare will be done A.equals(B).  Since 
        return Diagnostic.OK_INSTANCE;
    }

    
    /**
     * Both types are coerced to boolean before comparison
     * 
     * @param firstArg
     * @param secondArg
     * @return the result of the comparison
     */
    private ValueType handleBooleanComparison(ValueType firstArg, ValueType secondArg)
    {
        boolean canCoerceFirstArg =  
            TypeCoercer.canCoerceToBoolean(TypeTransformer.transformBoxPrimitives(firstArg.getSignature()));
        boolean canCoerceSecondArg = TypeCoercer.canCoerceToBoolean(TypeTransformer.transformBoxPrimitives(secondArg.getSignature()));

        if (! (canCoerceFirstArg && canCoerceSecondArg))
        {
            return null;
        }
        
        if (firstArg instanceof LiteralType && secondArg instanceof LiteralType)
        {
            try
            {
                Boolean firstValue = ((LiteralType)firstArg).coerceToBoolean();
                Boolean secondValue = ((LiteralType)secondArg).coerceToBoolean();
                
                if (firstValue != null && secondValue != null)
                {
                    boolean result = doRealOperation(firstValue, secondValue);
                    return result ? 
                               BooleanLiteralType.TRUE : 
                                       BooleanLiteralType.FALSE;
                }
            }
            catch (TypeCoercionException tce)
            {
                throw new AssertionError("should never get here; have already checked coercability above");
            }
        }
        
        // otherwise, we have a valid comparison that results in boolean
        return new ValueType(TypeConstants.TYPE_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS);
    }
    
    private Diagnostic validateBooleanComparison(ValueType firstType, ValueType secondType)
    {
        boolean canCoerceFirstArg =  
            TypeCoercer.canCoerceToBoolean(TypeTransformer.transformBoxPrimitives(firstType.getSignature()));
        boolean canCoerceSecondArg = TypeCoercer.canCoerceToBoolean(TypeTransformer.transformBoxPrimitives(secondType.getSignature()));

        if (!canCoerceFirstArg)
        {
            return DiagnosticFactory.create_BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN("first");
        }
        
        if (!canCoerceSecondArg)
        {
            return DiagnosticFactory.create_BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN("second");
        }
        
        if (firstType instanceof LiteralType && secondType instanceof LiteralType)
        {
            try
            {
                Boolean firstValue = ((LiteralType)firstType).coerceToBoolean();
                Boolean secondValue = ((LiteralType)secondType).coerceToBoolean();
                
                if (firstValue != null && secondValue != null)
                {
                    final boolean result = 
                        doRealOperation(firstValue, secondValue);
                    return DiagnosticFactory.
                        create_BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME(getOperationName(), Boolean.toString(result));
                }
            }
            catch (TypeCoercionException tce)
            {
                throw new AssertionError("should never get here; have already checked coercability above");
            }
        }
        
        // otherwise, we have a valid comparison
        return Diagnostic.OK_INSTANCE;
    }
}
