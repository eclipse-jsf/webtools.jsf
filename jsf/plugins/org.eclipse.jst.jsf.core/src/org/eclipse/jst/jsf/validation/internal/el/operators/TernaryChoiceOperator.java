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
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.common.internal.types.LiteralType;
import org.eclipse.jst.jsf.common.internal.types.TypeCoercer;
import org.eclipse.jst.jsf.common.internal.types.TypeCoercionException;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.TypeTransformer;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Represents the ternary choice op in EL: a ? b :c
 * Based on JSP.2.3.8
 * 
 * @author cbateman
 *
 */
public class TernaryChoiceOperator 
{
    private final DiagnosticFactory     _diagnosticFactory;
    
    
    /**
     * @param diagnosticFactory
     */
    public TernaryChoiceOperator(DiagnosticFactory diagnosticFactory)
    {
        super();
        _diagnosticFactory = diagnosticFactory;
    }

    /**
     * @param choiceArg
     * @param whenTrueArg
     * @param whenFalseArg
     * @return the result of the choice arguments or null if indeterminant
     */
    public ValueType perform(ValueType choiceArg, ValueType whenTrueArg, ValueType whenFalseArg)
    {
        final boolean isChoiceBoolean = 
            TypeCoercer.canCoerceToBoolean(TypeTransformer.transformBoxPrimitives(choiceArg.getSignature()));
        
        if (isChoiceBoolean)
        {
            if (choiceArg instanceof LiteralType)
            {
                try
                {
                    Boolean result = ((LiteralType)choiceArg).coerceToBoolean();
                    
                    if (result != null)
                    {
                        return result.booleanValue() ? whenTrueArg : whenFalseArg;
                    }
                }
                catch (TypeCoercionException tce)
                {
                    return null;
                }
            }
            
            final String boxedWhenTrueArg =
                TypeTransformer.transformBoxPrimitives(whenTrueArg.getSignature());
            
            final String boxedWhenFalseArg =
                TypeTransformer.transformBoxPrimitives(whenFalseArg.getSignature());
            // check if the two results are the same type
            // TODO: could increase accuracy by appoximating type:i.e. if both
            // are numeric etc.
            if (boxedWhenTrueArg.equals(boxedWhenFalseArg))
            {
                return new ValueType(whenTrueArg, IAssignable.ASSIGNMENT_TYPE_RHS);
            }

            // otherwise, we have no idea what the resulting type is
            return null;
        }
        
        // if choice is not boolean, then can't resolve
        return null;
    }
    
    /**
     * @param choiceArg
     * @return a diagnostic validating the choice expr with these arguments
     */
    public Diagnostic validate(ValueType choiceArg)
    {
    	if (TypeConstants.TYPE_JAVAOBJECT.equals(choiceArg.getSignature())) {
    		return Diagnostic.OK_INSTANCE;
    	}

        final boolean isChoiceBoolean = 
            TypeCoercer.canCoerceToBoolean(TypeTransformer.transformBoxPrimitives(choiceArg.getSignature()));
        
        if (isChoiceBoolean)
        {
            if (choiceArg instanceof LiteralType)
            {
                try
                {
                    Boolean result = ((LiteralType)choiceArg).coerceToBoolean();
                    
                    if (result != null)
                    {
                        String whichSelected = result.booleanValue() ? "first" : "second"; //$NON-NLS-1$ //$NON-NLS-2$
                        return _diagnosticFactory.
                            create_TERNARY_OP_CHOICE_IS_ALWAYS_SAME
                                (result.booleanValue(), whichSelected);
                    }
                }
                catch (TypeCoercionException tce)
                {
                    return _diagnosticFactory.
                        create_TERNARY_OP_CANNOT_COERCE_CHOICE_TO_BOOLEAN();
                }
            }

            // in both the case where the two arguments are the same and when
            // they are different types, the best we can say is "OK", since
            // we really don't know
            return Diagnostic.OK_INSTANCE;
        }
        
        // if choice is not boolean, then can't resolve
        return _diagnosticFactory.
            create_TERNARY_OP_CANNOT_COERCE_CHOICE_TO_BOOLEAN();
    }
}
