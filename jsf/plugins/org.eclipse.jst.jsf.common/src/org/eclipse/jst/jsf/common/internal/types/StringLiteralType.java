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

package org.eclipse.jst.jsf.common.internal.types;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Represents a StringLiteral as defined by JSP.2.9
 * 
 * @author cbateman
 */
public class StringLiteralType extends LiteralType 
{
    private final String        _literalValue;
    
    /**
     * @param value
     */
    public StringLiteralType(String value)
    {
        super(TypeConstants.TYPE_STRING);
        _literalValue = value;
    }
    
    public Number coerceToNumber(Class T) throws TypeCoercionException 
    {
        try
        {
            if (T == BigInteger.class)
            {
                if ("".equals(_literalValue)) //$NON-NLS-1$
                {
                    return BigInteger.ZERO;
                }
                return new BigInteger(_literalValue);
            }
            else if (T == BigDecimal.class)
            {
                if ("".equals(_literalValue)) //$NON-NLS-1$
                {
                    return new BigDecimal(0.0);
                }
                return new BigDecimal(_literalValue);
            }
            else if (T == Double.class || T == Double.TYPE)
            {
                if ("".equals(_literalValue)) //$NON-NLS-1$
                {
                    return new Double(0.0);
                }
           
                return Double.valueOf(_literalValue);
            }
            else if (T == Float.class || T == Float.TYPE)
            {
                if ("".equals(_literalValue)) //$NON-NLS-1$
                {
                    return new Float(0.0);
                }
                return Float.valueOf(_literalValue);
            }
            else if (T == Long.class || T == Long.TYPE)
            {
                if ("".equals(_literalValue)) //$NON-NLS-1$
                {
                    return new Long(0L);
                }
                return Long.valueOf(_literalValue);
            }
            else if (T == Integer.class || T == Integer.TYPE)
            {
                if ("".equals(_literalValue)) //$NON-NLS-1$
                {
                    return new Integer(0);
                }
                return Integer.valueOf(_literalValue);
            }
            else if (T == Short.class || T == Short.TYPE)
            {
                if ("".equals(_literalValue)) //$NON-NLS-1$
                {
                    return new Short((short)0);
                }
                return Short.valueOf(_literalValue);
            }
            else if (T == Byte.class || T == Byte.TYPE)
            {
                if ("".equals(_literalValue)) //$NON-NLS-1$
                {
                    return new Byte((byte)0);
                }
                return Byte.valueOf(_literalValue);
            }
            else
            {
                throw new IllegalArgumentException("Not a target numeric type: "+T); //$NON-NLS-1$
            }
        }
        catch (NumberFormatException nfe)
        {
            throw new TypeCoercionException(nfe);
        }
    }

    public String getLiteralValue() 
    {
        return _literalValue;
    }

    public Object getLiteralValueRaw() 
    {
        return _literalValue;
    }

    public Boolean coerceToBoolean() throws TypeCoercionException 
    {
        // JSP.2.8.5
        return Boolean.valueOf(_literalValue);
    }
}
