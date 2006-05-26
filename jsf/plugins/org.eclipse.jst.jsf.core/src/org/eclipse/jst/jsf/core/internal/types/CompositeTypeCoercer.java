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
 * Follows type coercion rules codified in section JSP.2.8 of the 
 * JSP 2.0 Specification.
 * 
 * This class operates on CompositeType's and returns raw
 * Java signatures for the single resulting type coercion.
 * 
 * The rules are stretched a little bit since JSP.2.8 defines how to
 * coerce an *instance* A to a type T.  But since we have no runtime instances,
 * only their types, we approximate by taking what we know about the type of A
 * and coercing it T as best we can.  
 * 
 * Also, whereas the spec says to throw errors when coercion is not possible,
 * we have two cases:
 * 
 *  1) We can determine definitively that there is no coercion
 *  2) We cannot determine whether there is or isn't a coercion
 *  
 *  In case 1, we always throw an exception.  In case 2, we return null to indicate
 *  "indeterminate" result, rather than error.
 * 
 * @author cbateman
 *
 */
public class CompositeTypeCoercer 
{
    /**
     * This method follows JSP.2.8.3 except that rather than returning a specific
     * type that has been coerced to, it determines the most exact possible type
     * that typeOfA can be coerced to, to be number compatible.  The caller must
     * decide what do with the return value compared to the type (N in the spec)
     * that they want to coerce to.
     * 
     * @param typeOfA
     * @return a new signature for the type of A after being coerced to a Number
     * @throws TypeCoercionException if A can definitively not be coerced to
     * a number
     */
    public static String coerceToNumber(final CompositeType typeOfA)
                            throws TypeCoercionException
    {
        String coercedType = null;
        boolean errorDetected = true;       // assume error: only false if we 
                                            // find a member of typeOfA that 
                                            // coerces to number
        
        // JSP.2.8.1 -- auto-box primitives
        final CompositeType boxedTypeOfA = 
            TypeTransformer.transformBoxPrimitives(typeOfA);
        final boolean[] typesigs = boxedTypeOfA.getIsTypeSignature();
        
        // iterate through all of the signatures that represent types
        // and find at least one that can be coerced to a number
        for (int i = 0; i < typesigs.length; i++)
        {
            if (typesigs[i])
            {
                try
                {
                    final String testType = 
                        TypeCoercer.coerceToNumber(boxedTypeOfA.getSignatures()[i]);

                    if (testType != null)
                    {
                        // if we have already found a coercible type, then 
                        // we need to return null, since we have a conflict that
                        // we don't know how to resolve to a type??????
                        if (coercedType != null)
                        {
                            return null;
                        }
                        
                        coercedType = testType;
                    }
                    errorDetected = false; // we have found a number coercion or indeterminate
                }
                catch (TypeCoercionException tce)
                {
                    // do nothing: so far error still detected
                }
            }
        }
        
        // we have three choices:
        // 1: if errorDetected was never cleared, we definitely never found
        // a coerceable type, so throw exception
        if (errorDetected)
        {
            throw new TypeCoercionException();
        }
        // otherwise the flag was cleared return what we found
        else
        {
            if (coercedType != null)
            {
                // need to unbox per JSP.2.8.1
                coercedType =
                    TypeTransformer.transformUnboxPrimitives(coercedType);
            }
            
            return coercedType;
        }
    }
    
    
//    public static String coerceToBoolean(CompositeType compositeType)
//    {
//        
//    }
 
}
