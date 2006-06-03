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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jdt.core.Signature;

/**
 * Transforms one CompositeType to another according to particular 
 * transform rules.
 * 
 * In general, every transformation should be of the form:
 * 
 *   CompositeType  transform(CompositeType original, other inputs...);
 * 
 * @author cbateman
 *
 */
public class TypeTransformer 
{
    private final static Map  boxConversions = new HashMap();
    private final static Map  unBoxConversions = new HashMap();
    
    static
    {
        // see jdt.core.Signature or JVM link spec for more details
        boxConversions.put("B", "Ljava.lang.Byte;");
        boxConversions.put("C", "Ljava.lang.Character;");
        boxConversions.put("D", "Ljava.lang.Double;");
        boxConversions.put("F", "Ljava.lang.Float;");
        boxConversions.put("I", "Ljava.lang.Integer;");
        boxConversions.put("J", "Ljava.lang.Long;");
        boxConversions.put("S", "Ljava.lang.Short;");
        boxConversions.put("Z", "Ljava.lang.Boolean;");
        // don't box V
        boxConversions.put("V", "V");
    
        // invert hte box conversions
        for (final Iterator it = boxConversions.keySet().iterator(); it.hasNext();)
        {
            final String newValue = (String) it.next();
            final String newKey = (String) boxConversions.get(newValue);
            if (unBoxConversions.put(newKey, newValue) != null)
            {
                // if put returns non-null then we have replaced a key
                // added on a previous iteration.  This implies
                // that box mappings are no longer one-to-one
                throw new AssertionError("Duplicated boxing value");
            }
        }
    }
    
    /**
     * @param compositeType
     * @return an equivilent form of compositeType with all primitive type
     * signatures converted to their fully qualified boxed equivilent but
     * otherwise unchanged.
     * 
     * Example:   J -> Ljava.lang.Long;
     */
    public static CompositeType transformBoxPrimitives(CompositeType compositeType)
    {
        String[] signatures = compositeType.getSignatures();
        String[] newsignatures = new String[signatures.length];
        
        for (int i = 0; i < signatures.length; i++)
        {
            newsignatures[i] = transformBoxPrimitives(signatures[i]);
        }
        
        return new CompositeType(newsignatures, compositeType.getAssignmentTypeMask());
    }
    
    /**
     * Performs boxing for a single typeSignature string
     * @param curSignature
     * @return the boxed signature
     */
    public static String transformBoxPrimitives(final String curSignature)
    {
        String newSignature = curSignature;
        
        // first determine if we have a type or method signature
        try
        {
            int kind = Signature.getTypeSignatureKind(curSignature);
            
            // interested in base types, since these need boxing
            if (kind == Signature.BASE_TYPE_SIGNATURE)
            {
                // grab the box for the primitive
                newSignature = (String) boxConversions.get(curSignature);
            }
            else if (kind == Signature.ARRAY_TYPE_SIGNATURE)
            {
                // check if it's array of primitives
                final String baseType = Signature.getElementType(curSignature);
                
                if (Signature.getTypeSignatureKind(baseType) == Signature.BASE_TYPE_SIGNATURE)
                {
                    // it is, so box it
                    final String newBaseType = (String) boxConversions.get(baseType);
                    final int numBraces = Signature.getArrayCount(curSignature);
                    newSignature = "";
                    for (int j = 0; j < numBraces; j++)
                    {
                        newSignature += "[";
                    }
                    
                    newSignature += newBaseType;
                }
            }
        }
        catch (IllegalArgumentException e)
        {
            // signature was not a type signature, so must be a method sig
            // do nothing: don't box method types
        }

        return newSignature;
    }
    
    /**
     * Performs the exact inverse of transformBoxPrimitives -- takes all
     * boxing type signatures and replaces them with their primitive equivilent
     * @param compositeType
     * @return a new composite with all boxed primitives unboxed
     */
    public static CompositeType transformUnboxPrimitives(CompositeType compositeType)
    {
        String[] signatures = compositeType.getSignatures();
        String[] newsignatures = new String[signatures.length];
        
        for (int i = 0; i < signatures.length; i++)
        {
            newsignatures[i] = transformUnboxPrimitives(signatures[i]);
        }
        
        return new CompositeType(newsignatures, compositeType.getAssignmentTypeMask());
    }
    
    /**
     * Performs unboxing for a single typeSignature string
     * 
     * @param typeSignature
     * @return the transformed signature
     */
    public static String transformUnboxPrimitives(final String typeSignature)
    {
        String newSignature = typeSignature;
        
        // first determine if we have a type or method signature
        try
        {
            int kind = Signature.getTypeSignatureKind(typeSignature);
            
            // interested in class types, since these need boxing
            if (kind == Signature.CLASS_TYPE_SIGNATURE)
            {
                // grab the box for the primitive
                String checkForUnbox  = (String) unBoxConversions.get(typeSignature);
                
                if (checkForUnbox != null)
                {
                    newSignature = checkForUnbox;
                }
            }
            else if (kind == Signature.ARRAY_TYPE_SIGNATURE)
            {
                // check if it's array of objects
                final String baseType = Signature.getElementType(typeSignature);
                
                if (Signature.getTypeSignatureKind(baseType) == Signature.CLASS_TYPE_SIGNATURE)
                {
                    // it is, so unbox it
                    final String newBaseTypeCandidate = (String) unBoxConversions.get(baseType);
                    
                    if (newBaseTypeCandidate != null)
                    {
                        final int numBraces = Signature.getArrayCount(typeSignature);
                        newSignature = "";
                        for (int j = 0; j < numBraces; j++)
                        {
                            newSignature += "[";
                        }
                        
                        newSignature += newBaseTypeCandidate;
                    }
                }
            }
        }
        catch (IllegalArgumentException e)
        {
            // signature was not a type signature, so must be a method sig
            // do nothing: don't box method types
        }
        
        return newSignature;
    }
}
