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
 * Represents a NullLiteralType per JSP.2.9
 * 
 * @author cbateman
 *
 */
public class NullLiteralType extends LiteralType 
{
    /**
     * The singleton null literal
     */
    public final static NullLiteralType SINGLETON = new NullLiteralType();
    
    /**
     * Construct a new null
     */
    private NullLiteralType()
    {
        super(TypeConstants.TYPE_NULL); // use void to indicate null in this situation
    }
    
    /**
     * Per JSP.2.8.5 null is always false
     * 
     * @see org.eclipse.jst.jsf.common.internal.types.LiteralType#coerceToBoolean()
     */
    public Boolean coerceToBoolean() throws TypeCoercionException 
    {
        return Boolean.FALSE;
    }

    /**
     * Per JSP.2.8.3, null is always 0
     * 
     * @see org.eclipse.jst.jsf.common.internal.types.LiteralType#coerceToNumber(java.lang.Class)
     */
    public Number coerceToNumber(Class T) throws TypeCoercionException {
        if (T == BigInteger.class)
        {
            return BigInteger.ZERO;
        }
        else if (T == BigDecimal.class)
        {
            return new BigDecimal(0.0);
        }
        else if (T == Double.class || T == Double.TYPE)
        {
            return new Double(0.0);
        }
        else if (T == Float.class || T == Float.TYPE)
        {
            return new Float(0.0);
        }
        else if (T == Long.class || T == Long.TYPE)
        {
            return Long.valueOf(0L);
        }
        else if (T == Integer.class || T == Integer.TYPE)
        {
            return Integer.valueOf(0);
        }
        else if (T == Short.class || T == Short.TYPE)
        {
            return Short.valueOf((short)0);
        }
        else if (T == Byte.class || T == Byte.TYPE)
        {
            return Byte.valueOf((byte)0);
        }
        else
        {
            throw new IllegalArgumentException("Not a target numeric type: "+T); //$NON-NLS-1$
        }
    }

    /**
     * Per JSP.2.8.2 null is always an empty string
     * @see org.eclipse.jst.jsf.common.internal.types.LiteralType#getLiteralValue()
     */
    public String getLiteralValue() 
    {
        return ""; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.core.internal.types.LiteralType#getLiteralValueRaw()
     */
    public Object getLiteralValueRaw() 
    {
        return null;
    }

}
