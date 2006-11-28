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
 * Coercer for simple type signatures
 * 
 * @author cbateman
 *
 */
public class TypeCoercer 
{
    
    /**
     * Based on JSP.2.8.3 in JSP 2.0 specification
     * 
     * @param boxedTypeSignature -- an auto-boxed type signature
     * @return the coerced type or null if cannot be resolved.  No unboxing
     * is performed on the return.
     * @throws TypeCoercionException if boxedTypeSignature is
     */
    public static String coerceToNumber(final String boxedTypeSignature)
                             throws TypeCoercionException
    {
        String boxedTypeSignature_ = boxedTypeSignature;
        
        // can't coerce arrays to numbers
        if (Signature.getTypeSignatureKind(boxedTypeSignature_)
                == Signature.ARRAY_TYPE_SIGNATURE)
        {
            throw new TypeCoercionException("Cannot coerce arrays to numbers");
        }
        // if it's character, pre-coerce to short per step 2
        if (TypeConstants.SIGNATURE_BOXED_CHARACTER.equals(boxedTypeSignature))
        {
            boxedTypeSignature_ = TypeConstants.TYPE_BOXED_SHORT;
        }

        if (TypeCoercer.typeIsNumeric(boxedTypeSignature_)
                || TypeCoercer.typeIsNull(boxedTypeSignature_))
        {
            return boxedTypeSignature_;
        }
        else if (typeIsString(boxedTypeSignature_))
        {
            // undetermined a string may or not resolve to a number
            // depending on its runtime value
            return null;
        }
        else
        {
            throw new TypeCoercionException();
        }
    }
    
    /**
     * @param boxedTypeSignature
     * @return true if type can be coerced to boolean; null if indeterminate
     */
    public static boolean canCoerceToBoolean(String boxedTypeSignature)
    {
        // JSP.2.8.5 -- boolean is always boolean; string is converted by Boolean.valueOf(String)
        if (typeIsBoolean(boxedTypeSignature)
                || typeIsString(boxedTypeSignature)
                || typeIsNull(boxedTypeSignature))
        {
            return true;
        }
        // nothing else really convertible besides null
        return false;
    }
    
    /**
     * @param typeSignature -- boxed type signature
     * @return true if the typeSignature is numeric
     */
    public static boolean typeIsNumeric(final String typeSignature)
    {
        return (TypeConstants.TYPE_BOXED_BYTE.equals(typeSignature) ||
                TypeConstants.TYPE_BOXED_SHORT.equals(typeSignature) ||
                TypeConstants.TYPE_BOXED_INTEGER.equals(typeSignature) ||
                TypeConstants.TYPE_BOXED_LONG.equals(typeSignature) ||
                TypeConstants.TYPE_BOXED_FLOAT.equals(typeSignature) ||
                TypeConstants.TYPE_BOXED_DOUBLE.equals(typeSignature) ||
                TypeConstants.TYPE_BIG_INTEGER.equals(typeSignature) ||
                TypeConstants.TYPE_BIG_DOUBLE.equals(typeSignature));
    }
    
    /**
     * @param typeSignature
     * @return true if the typeSignature represents a String
     */
    public static boolean typeIsString(final String typeSignature)
    {
        return (TypeConstants.TYPE_STRING.equals(typeSignature));
    }
    
    /**
     * @param typeSignature -- boxed type signature
     * @return true if the typeSignature represents a boxed boolean
     */
    public static boolean typeIsBoolean(final String typeSignature)
    {
        return (TypeConstants.TYPE_BOXED_BOOLEAN.equals(typeSignature));
    }
    
    /**
     * @param typeSignature
     * @return true if type is the EL null type
     */
    public static boolean typeIsNull(final String typeSignature)
    {
        return (TypeConstants.TYPE_NULL.equals(typeSignature));
    }
}
