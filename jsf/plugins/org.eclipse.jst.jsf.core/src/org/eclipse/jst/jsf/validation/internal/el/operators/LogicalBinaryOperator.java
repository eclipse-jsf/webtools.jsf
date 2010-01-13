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
 * Represents a logical binary operator per JSP.2.3.6.1 (logical and/or)
 * 
 * @author cbateman
 *
 */
/*package*/abstract class LogicalBinaryOperator extends BinaryOperator 
{
    LogicalBinaryOperator(DiagnosticFactory diagnosticFactory) {
        super(diagnosticFactory);
    }

    public ValueType performOperation(ValueType firstArg, ValueType secondArg) 
    {
        final boolean canCoerceFirstArg = 
            TypeCoercer.canCoerceToBoolean(TypeTransformer.transformBoxPrimitives(firstArg.getSignature()));
        final boolean canCoerceSecondArg = 
            TypeCoercer.canCoerceToBoolean(TypeTransformer.transformBoxPrimitives(secondArg.getSignature()));
        
        // if can't perform, must return null
        if (! (canCoerceFirstArg && canCoerceSecondArg))
        {
            return null;
        }

        Boolean  firstArgResolvedValue = null;
        
        if (firstArg instanceof LiteralType)
        {
            try
            {
                firstArgResolvedValue = ((LiteralType)firstArg).coerceToBoolean();
            }
            catch (TypeCoercionException tce)
            {
                // should never be throw due to already checking if can coerce
                throw new AssertionError("coercion already checked; this should never be reached"); //$NON-NLS-1$
            }
        }
    
        Boolean secondArgResolvedValue = null;
        if (secondArg instanceof LiteralType)
        {
            try
            {
                secondArgResolvedValue = ((LiteralType)secondArg).coerceToBoolean();
            }
            catch (TypeCoercionException tce)
            {
                // should never be throw due to already checking if can coerce
                throw new AssertionError("coercion already checked; this should never be reached"); //$NON-NLS-1$
            }
        }

        if (firstArgResolvedValue != null && secondArgResolvedValue != null)
        {
            boolean result = 
                doRealOperation(firstArgResolvedValue, secondArgResolvedValue);
            
            return result ? BooleanLiteralType.TRUE : BooleanLiteralType.FALSE;
            
        }
        
        // otherwise, just return a boolean
        // result can only be an rvalue
        return new ValueType(TypeConstants.TYPE_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS);
    }

    public Diagnostic validate(ValueType firstArg, ValueType secondArg) 
    {
    	if (TypeConstants.TYPE_JAVAOBJECT.equals(firstArg.getSignature()) ||
    			TypeConstants.TYPE_JAVAOBJECT.equals(secondArg.getSignature())) {
    		return Diagnostic.OK_INSTANCE;
    	}

        final boolean canCoerceFirstArg = 
            TypeCoercer.canCoerceToBoolean(TypeTransformer.transformBoxPrimitives(firstArg.getSignature()));
        final boolean canCoerceSecondArg = 
            TypeCoercer.canCoerceToBoolean(TypeTransformer.transformBoxPrimitives(secondArg.getSignature()));
       
        if (!canCoerceFirstArg)
        {
            return _diagnosticFactory.
                    create_BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN("first"); //$NON-NLS-1$
            //return new BasicDiagnostic(Diagnostic.ERROR, "", 0, "Cannot coerce first argument of "+readableOperatorName()+" to boolean", null);
        }
       
        if (!canCoerceSecondArg)
        {
            return _diagnosticFactory.
                create_BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN("first"); //$NON-NLS-1$

            //return new BasicDiagnostic(Diagnostic.ERROR, "", 0, "Cannot coerce second argument of "+readableOperatorName()+" to boolean", null);
        }
           
        if (firstArg instanceof LiteralType)
        {
            try
            {
                final Boolean boolValue = 
                    ((LiteralType)firstArg).coerceToBoolean();
         
                if (boolValue.booleanValue() == shortCircuitValue())
                {
                    return _diagnosticFactory.
                        create_BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS
                            (shortCircuitValue(), readableOperatorName());
                }
            }
            catch (TypeCoercionException tce)
            {
                // should never be throw due to already checking if can coerce
                throw new AssertionError("coercion already checked; this should never be reached"); //$NON-NLS-1$
            }
        }
        
        if (secondArg instanceof LiteralType)
        {
            try
            {
                final Boolean boolValue =
                    ((LiteralType)secondArg).coerceToBoolean();
 
                if (boolValue.booleanValue() == shortCircuitValue())
                {
                    return _diagnosticFactory.
                        create_BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME
                            (shortCircuitValue(), readableOperatorName());
                }
            }
            catch (TypeCoercionException tce)
            {
                // should never be throw due to already checking if can coerce
                throw new AssertionError("coercion already checked; this should never be reached"); //$NON-NLS-1$
            }
        }
           
        // otherwise, nothing to report
        return Diagnostic.OK_INSTANCE;
    }
    
    /**
     * @return a human readable name for the operator
     */
    protected abstract String readableOperatorName();
    
    /**
     * @return the boolean value on which the operator short-circuits
     */
    protected abstract boolean shortCircuitValue();
    
    /**
     * @param firstArg
     * @param secondArg
     * @return the result of performing the operator to two actual values
     */
    protected abstract boolean doRealOperation(Boolean firstArg, Boolean secondArg);
}
