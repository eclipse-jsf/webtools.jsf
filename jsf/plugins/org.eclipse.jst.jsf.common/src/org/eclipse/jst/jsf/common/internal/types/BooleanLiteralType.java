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

import org.eclipse.jdt.core.Signature;

/**
 * Represents a BooleanLiteral as defined by JSP.2.9
 * 
 * @author cbateman
 *
 */
public class BooleanLiteralType extends LiteralType 
{
    /**
     * The literal FALSE singleton
     */
    public final static BooleanLiteralType  FALSE = new BooleanLiteralType(false);
    /**
     * The literal TRUE singleton
     */
    public final static BooleanLiteralType  TRUE = new BooleanLiteralType(true);
    
    private final boolean       _literalValue;
    
    
    /**
     * @param value
     * @return a constant boolean literal type corresponding to value
     */
    public static BooleanLiteralType valueOf(boolean value)
    {
        return value ? TRUE : FALSE;
    }
    
    /**
     * @param literalValue
     */
    /*package*/BooleanLiteralType(boolean  literalValue)
    {
        super(Signature.SIG_BOOLEAN);
        _literalValue = literalValue;
    }
    
    public Number coerceToNumber(Class T) throws TypeCoercionException 
    {
        // illegal to coerce boolean to number per JSP.2.8.3 step 3
        throw new TypeCoercionException("Cannot coerce boolean to number"); //$NON-NLS-1$
    }

    public String getLiteralValue() 
    {
        return Boolean.toString(_literalValue);
    }

    public Object getLiteralValueRaw() 
    {
        return Boolean.valueOf(_literalValue);
    }

    public Boolean coerceToBoolean() throws TypeCoercionException 
    {
        return Boolean.valueOf(_literalValue);
    }

}
