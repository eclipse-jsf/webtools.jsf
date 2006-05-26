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

package org.eclipse.jst.jsf.core.internal.types;


/**
 * Common super-type for Float and Integer literals
 * @author cbateman
 *
 */
public abstract class NumericTypeLiteral extends LiteralType 
{
    /**
     * @param signature
     */
    protected NumericTypeLiteral(String signature)
    {
        super(signature);
    }
    
    /**
     * @return the boxed form of the numeric literal value
     */
    protected abstract Number getBoxedValue();
    
    public Number coerceToNumber(Class T) throws TypeCoercionException 
    {
        Number boxedLiteralValue = getBoxedValue();
        
        if (T == Double.class || T == Double.TYPE)
        {
            return new Double(boxedLiteralValue.doubleValue());
        }
        else if (T == Float.class || T == Float.TYPE)
        {
            return new Float(boxedLiteralValue.floatValue());
        }
        else if (T == Long.class || T == Long.TYPE)
        {
            return boxedLiteralValue;
        }
        else if (T == Integer.class || T == Integer.TYPE)
        {
            return new Integer(boxedLiteralValue.intValue());
        }
        else if (T == Short.class || T == Short.TYPE)
        {
            return new Short(boxedLiteralValue.shortValue());
        }
        else if (T == Byte.class || T == Byte.TYPE)
        {
            return new Byte(boxedLiteralValue.byteValue());
        }
        else
        {
            return null;
        }
    }

    public String getLiteralValue() 
    {
        return getBoxedValue().toString();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.core.internal.types.LiteralType#coerceToBoolean()
     */
    public Boolean coerceToBoolean() throws TypeCoercionException {
        // JSP.2.8.5 does not provide for number -> boolean coercion
        throw new TypeCoercionException("Cannot coerce number to boolean");
    }

    
}
