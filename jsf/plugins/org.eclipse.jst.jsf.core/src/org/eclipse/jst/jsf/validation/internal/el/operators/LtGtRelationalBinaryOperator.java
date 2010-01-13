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
import org.eclipse.jst.jsf.common.internal.types.BooleanLiteralType;
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.common.internal.types.TypeCoercer;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.TypeTransformer;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.common.util.TypeUtil;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Encapsulates the EL binary operators "<", ">", "<=", ">="
 * 
 * @author cbateman
 *
 */
/*package*/ abstract class LtGtRelationalBinaryOperator extends RelationalBinaryOperator 
{
    LtGtRelationalBinaryOperator(final DiagnosticFactory diagnosticFactory, String jsfVersion) 
    {
        super(diagnosticFactory, jsfVersion);
    }

    protected abstract boolean doRealOperation(Number firstArg, Number secondArg);

    protected abstract boolean doRealOperation(String firstArg, String secondArg); 

    public ValueType performOperation(ValueType firstArg, ValueType secondArg) 
    {
        // JSP.2.3.5.6 step 2 if either operand is null, then always false
        if (TypeCoercer.typeIsNull(firstArg.getSignature())
                || TypeCoercer.typeIsNull(secondArg.getSignature()))
        {
            return BooleanLiteralType.FALSE;
        }
        
        String boxedFirstType = TypeTransformer.transformBoxPrimitives(firstArg.getSignature());
        String boxedSecondType = TypeTransformer.transformBoxPrimitives(secondArg.getSignature());
        
        // JSP.2.3.5.6 step 3, if either is BigDecimal, promote both and compare
        if (TypeConstants.TYPE_BIG_DOUBLE.equals(boxedFirstType)
                || TypeConstants.TYPE_BIG_DOUBLE.equals(boxedSecondType))
        {
            return handleNumericComparison(firstArg, secondArg, BigDecimal.class);
        }
        
        // JSP.2.3.5.6, step 4 if either is a float or double, promote both to 
        // double and compare
        if (TypeConstants.TYPE_BOXED_DOUBLE.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_FLOAT.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_DOUBLE.equals(boxedSecondType)
                || TypeConstants.TYPE_BOXED_FLOAT.equals(boxedSecondType))
        {
            return handleNumericComparison(firstArg, secondArg, Double.class);
        }
        
        // JSP.2.3.5.6, step 5 if either is a big integer, promote and compare
        if (TypeConstants.TYPE_BIG_INTEGER.equals(boxedFirstType)
                || TypeConstants.TYPE_BIG_INTEGER.equals(boxedSecondType))
        {
            return handleNumericComparison(firstArg, secondArg, BigInteger.class);
        }
        
        // JSP.2.3.5.6, step 6 if either is Long or smaller, coerce both to Long
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
        
        // JSP.2.3.5.7, step 7 if either is a string, coerce to string and
        // compare lexically
        if (TypeConstants.TYPE_STRING.equals(boxedFirstType)
                || TypeConstants.TYPE_STRING.equals(boxedSecondType))
        {
            return handleStringComparison(firstArg, secondArg);
        }
        
        // JSP.2.3.5.7, steps 8 and 9 -- if either one implements the
        // Comparable interface, then as far as we can determine statically
        // (compareTo may not work on the other arg, but who knows),
        // we are good
        if (firstArg.isInstanceOf(TypeConstants.TYPE_COMPARABLE)
                || secondArg.isInstanceOf(TypeConstants.TYPE_COMPARABLE))
        {
            if (checkIfIncompatibleEnums(firstArg, secondArg))
            {
                // error: no point in validating further since expr will probably throw an exception
                return null;
            }
            
            return new ValueType(Signature.SIG_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS);
        }
        
        // JSP.2.3.5.6, step 10 -- otherwise, error
        return null;

    }

    public Diagnostic validate(ValueType firstArg, ValueType secondArg) 
    {
    	if (TypeConstants.TYPE_JAVAOBJECT.equals(firstArg.getSignature()) ||
    			TypeConstants.TYPE_JAVAOBJECT.equals(secondArg.getSignature())) {
    		return Diagnostic.OK_INSTANCE;
    	}

    	// JSP.2.3.5.6 step 2 if either operand is null, then always false
        if (TypeCoercer.typeIsNull(firstArg.getSignature())
                || TypeCoercer.typeIsNull(secondArg.getSignature()))
        {
            return _diagnosticFactory.
                create_BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME(Messages.getString("LtGtRelationalBinaryOperator.ConstantName.False")); //$NON-NLS-1$
        }
        
        String boxedFirstType = TypeTransformer.transformBoxPrimitives(firstArg.getSignature());
        String boxedSecondType = TypeTransformer.transformBoxPrimitives(secondArg.getSignature());
        
        // JSP.2.3.5.6 step 3, if either is BigDecimal, promote both and compare
        if (TypeConstants.TYPE_BIG_DOUBLE.equals(boxedFirstType)
                || TypeConstants.TYPE_BIG_DOUBLE.equals(boxedSecondType))
        {
            return validateNumericComparison(firstArg, secondArg, BigDecimal.class);
        }
        
        // JSP.2.3.5.6, step 4 if either is a float or double, promote both to 
        // double and compare
        if (TypeConstants.TYPE_BOXED_DOUBLE.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_FLOAT.equals(boxedFirstType)
                || TypeConstants.TYPE_BOXED_DOUBLE.equals(boxedSecondType)
                || TypeConstants.TYPE_BOXED_FLOAT.equals(boxedSecondType))
        {
            return validateNumericComparison(firstArg, secondArg, Double.class);
        }
        
        // JSP.2.3.5.6, step 5 if either is a big integer, promote and compare
        if (TypeConstants.TYPE_BIG_INTEGER.equals(boxedFirstType)
                || TypeConstants.TYPE_BIG_INTEGER.equals(boxedSecondType))
        {
            return validateNumericComparison(firstArg, secondArg, BigInteger.class);
        }
        
        // JSP.2.3.5.6, step 6 if either is Long or smaller, coerce both to Long
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
        
        // JSP.2.3.5.7, step 7 if either is a string, coerce to string and
        // compare lexically
        if (TypeConstants.TYPE_STRING.equals(boxedFirstType)
                || TypeConstants.TYPE_STRING.equals(boxedSecondType))
        {
            return validateStringComparison(firstArg, secondArg);
        }
        
        // JSP.2.3.5.7, steps 8 and 9 -- if either one implements the
        // Comparable interface, then as far as we can determine statically
        // (compareTo may not work on the other arg, but who knows),
        // we are good
        if (firstArg.isInstanceOf(TypeConstants.TYPE_COMPARABLE)
                || secondArg.isInstanceOf(TypeConstants.TYPE_COMPARABLE))
        {
            Diagnostic diag = Diagnostic.OK_INSTANCE;
            if(checkIfIncompatibleEnums(firstArg, secondArg))
            {
                diag = _diagnosticFactory.create_BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE();
            }
            return diag;
        }
        
        // JSP.2.3.5.6, step 10 -- otherwise, error
        return _diagnosticFactory.create_BINARY_OP_NO_AVAILABLE_TYPE_COERCION();
    }

    /**
     * @param firstArg
     * @param secondArg
     * @return diagnostic if firstArg and secondArg are incompatible with each other
     * for compareTo purpose or OK if not
     */
    private boolean checkIfIncompatibleEnums(ValueType firstArg,
            ValueType secondArg) 
    {
        if (firstArg.isEnumType()
                && secondArg.isEnumType()
                && !TypeUtil.isEnumsCompareCompatible(firstArg.getSignature()
                                                    , secondArg.getSignature()))
        {
            return true;
        }
        
        return false;
    }

}
