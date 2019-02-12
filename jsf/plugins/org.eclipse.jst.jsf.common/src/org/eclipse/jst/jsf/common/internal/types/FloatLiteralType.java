/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.common.internal.types;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.jdt.core.Signature;

/**
 * Represents an FloatLiteral as defined by JSP.2.9
 * @author cbateman
 *
 */
public class FloatLiteralType extends NumericTypeLiteral 
{
    private final double     _literalValue;
    
    /**
     * @param literalValue
     */
    public FloatLiteralType(double literalValue)
    {
        // according to the notes to JSP.2.9, bullet 5, float literals are doubles
        super(Signature.SIG_DOUBLE);
        _literalValue = literalValue;
    }
    
    protected Number getBoxedValue() 
    {
        return new Double(_literalValue);
    }

    public Number coerceToNumber(Class T) throws TypeCoercionException 
    {
        if (T == BigInteger.class)
        {
            return new BigDecimal(_literalValue).toBigInteger();
        }
        else if (T == BigDecimal.class)
        {
            return new BigDecimal(_literalValue);
        }
        
        Number commonCoercion = super.coerceToNumber(T);

        if (commonCoercion == null)
        {
            throw new IllegalArgumentException("Not a target numeric type: "+T); //$NON-NLS-1$
        }

        return commonCoercion;
    }

}
