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
 * Value object representing a value binding type by it's type and assignability
 * information
 * 
 * @author cbateman
 *
 */
public class ValueType implements SignatureBasedType, IAssignable
{
    /**
     * A default empty string array
     */
    protected final static String[]        EMPTY_STRING_ARRAY = new String[0];
    
    private final String        _signature;
    private final int           _assignmentMask;
    private final String[]      _superTypes;
    private final String[]      _interfaceTypes;
    
    private String[]            _allTypes;  // lazy creation on getAllTypes

    /**
     * Construct a new ValueType object with the given
     * signature
     * 
     * @param signature
     * @param superTypes 
     * @param interfaceTypes 
     * @param assignmentMask 
     */
    public ValueType(final String signature,
                     final String[] superTypes,
                     final String[] interfaceTypes,
                     final int assignmentMask) 
    {
        if (signature == null)
        {
            throw new AssertionError("signature can never be null");
        }
    
        _signature = signature;
        _assignmentMask = assignmentMask;
        _superTypes = superTypes != null ? superTypes : EMPTY_STRING_ARRAY;
        _interfaceTypes = interfaceTypes != null ? interfaceTypes : EMPTY_STRING_ARRAY;
    }
    
    /**
     * Copy constructor equivilent to 
     * ValueType(template.getSignature(), template.getSuperTypes(), template.getInterfaceTypes(),assingmentMask)
     * 
     * @param template
     * @param assignmentMask
     */
    public ValueType(final ValueType  template, final int assignmentMask)
    {
        this(template._signature, template._superTypes, 
                template._interfaceTypes, assignmentMask);
    }
    
    /**
     * Convienence constructor for creating ValueType's with no supertype
     * or interface info.  Equivilent to:
     *  ValueType(signature, new String[0], new String[0], assignmentMask)
     *  
     * @param signature
     * @param assignmentMask
     */
    public ValueType(final String signature, final int assignmentMask)
    {
        this(signature, EMPTY_STRING_ARRAY, EMPTY_STRING_ARRAY, assignmentMask);
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.core.internal.types.SignatureBasedType#getSignature()
     */
    public String getSignature() 
    {
        return _signature;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.internal.types.IAssignable#getAssignability()
     */
    public int getAssignability() {
        return _assignmentMask;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.internal.types.IAssignable#isLHS()
     */
    public boolean isLHS() {
        return TypeUtil.matchesLHS(_assignmentMask);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.internal.types.IAssignable#isRHS()
     */
    public boolean isRHS() {
        return TypeUtil.matchesRHS(_assignmentMask);
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return Signature.getSignatureSimpleName
            (TypeTransformer.transformBoxPrimitives(_signature));
    }
    
    /**
     * @return an array of all signatures of all super types or empty
     * array if there are no super types for this type
     * 
     * Note: if isArray() == true, then these are the super types of 
     * the base element
     */
    public String[] getSuperTypes()
    {
        return _superTypes;
    }
    
    /**
     * @return an array of all interfaces implemented or empty array
     * if none
     * 
     * Note: if isArray() == true, then these are the interfacess of 
     * the base element
     */
    public String[] getInterfaceTypes()
    {
        return _interfaceTypes;
    }
    
    /**
     * @return all types including the base type, super types and interface
     * types.
     * 
     * Note: if isArray() == true, then these are the super types of 
     * the base element

     */
    public String[]  getAllTypes()
    {
        if (_allTypes == null)
        {
            int numberOfTypes = 1 + _superTypes.length + _interfaceTypes.length;
            _allTypes = new String[numberOfTypes];
            _allTypes[0] = _signature;
            System.arraycopy(_superTypes, 0, _allTypes, 1, _superTypes.length);
            System.arraycopy(_interfaceTypes, 0, _allTypes, 1+_superTypes.length, _interfaceTypes.length);
        }
        return _allTypes;
    }
    
    /**
     * 
     */
    public CompositeType toCompositeType() 
    {
        return new CompositeType(getAllTypes(), getAssignability());
    }

    /**
     * @param signature
     * @return true if an instance of this type would satisfy instanceof signature
     *  
     */
    public boolean isInstanceOf(final String signature)
    {
        // if this is an array, then the super types are for the base
        // type and we can't be an instance of anything but signature
        if (isArray())
        {
            return getSignature().equals(signature);
        }
        
        final String[]  allTypes = getAllTypes();
        
        for (int i = 0; i < allTypes.length; i++)
        {
            if (allTypes[i].equals(signature))
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * @return true if this is an array type
     */
    public boolean isArray()
    {
        return Signature.getArrayCount(getSignature()) > 0;
    }
}
