/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.context.symbol.internal.util;

import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.context.symbol.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;

/**
 * A value type adapter class for IObjectSymbol's
 * 
 * @author cbateman
 *
 */
public class IObjectSymbolBasedValueType extends ValueType 
{
    private final IObjectSymbol _symbol;
    
    /**
     * @param symbol
     * @return a properly configured instance based on the sub-type of symbol.
     * Returns null if symbol is not of an appropriate IObjectSymbol type
     */
    public static IObjectSymbolBasedValueType getInstance(ISymbol symbol)
    {
        if (symbol instanceof IInstanceSymbol)
        {
            if (((IInstanceSymbol)symbol).getTypeDescriptor() != null)
            {
                return new IObjectSymbolBasedValueType((IInstanceSymbol) symbol);
            }
        }
        else if (symbol instanceof IPropertySymbol)
        {
            if (((IPropertySymbol)symbol).getTypeDescriptor() != null)
            {
                return new IObjectSymbolBasedValueType((IPropertySymbol) symbol);
            }
        }
        
        return null;
    }
    
    /**
     * Constructor for instance symbols (model objects)
     * @param symbol
     */
    public IObjectSymbolBasedValueType(final IInstanceSymbol symbol)
    {
        this(symbol.getTypeDescriptor().getTypeSignature(), 
                (String[])symbol.getTypeDescriptor().getTypeParameterSignatures().toArray(EMPTY_STRING_ARRAY),
                (String[])symbol.getTypeDescriptor().getSuperTypeSignatures().toArray(EMPTY_STRING_ARRAY),
                (String[])symbol.getTypeDescriptor().getInterfaceTypeSignatures().toArray(EMPTY_STRING_ARRAY),
                symbol.getTypeDescriptor().isEnumType(),
                IAssignable.ASSIGNMENT_TYPE_RHS /* variables are not assignable */
                , symbol);
    }
    
    /**
     * Constructor for property symbols
     * 
     * @param symbol
     */
    public IObjectSymbolBasedValueType(final IPropertySymbol symbol)
    {
        this(symbol.getTypeDescriptor().getTypeSignature(), 
                (String[])symbol.getTypeDescriptor().getTypeParameterSignatures().toArray(EMPTY_STRING_ARRAY),
                (String[])symbol.getTypeDescriptor().getSuperTypeSignatures().toArray(EMPTY_STRING_ARRAY),
                (String[])symbol.getTypeDescriptor().getInterfaceTypeSignatures().toArray(EMPTY_STRING_ARRAY),
                symbol.getTypeDescriptor().isEnumType(),
                    ASSIGNMENT_TYPE_NONE | (symbol.isReadable()?IAssignable.ASSIGNMENT_TYPE_RHS:ASSIGNMENT_TYPE_NONE)|(symbol.isWritable()?IAssignable.ASSIGNMENT_TYPE_LHS:ASSIGNMENT_TYPE_NONE)
        , symbol);
    }
    
    private IObjectSymbolBasedValueType(final String signature,
                     final String[] typeArgs,
                     final String[] superTypes,
                     final String[] interfaceTypes,
                     final boolean isEnumType,
                     final int assignmentMask,
                     final IObjectSymbol symbol)
    {
        super(signature, typeArgs, superTypes, interfaceTypes, isEnumType, assignmentMask);
        _symbol = symbol;
    }
    
    /**
     * @return the underlying IObjectSymbol
     */
    public IObjectSymbol getSymbol()
    {
        return _symbol;
    }
}
