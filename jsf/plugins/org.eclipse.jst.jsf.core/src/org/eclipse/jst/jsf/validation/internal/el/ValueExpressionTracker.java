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

package org.eclipse.jst.jsf.validation.internal.el;


class ValueExpressionTracker
{
    private String      				_rootSymbol;
    
    private int                         _curPropertySymbolOffset;
    private int                         _curPropertySymbolLength;
    
	/**
	 * @return the root symbol or null if not set
	 */
	public String getRootSymbolName() {
		return _rootSymbol;
	}
	
    /**
     * @param newName
     */
    public void setRootSymbolName(String newName)
    {
        _rootSymbol = newName;
    }
    
    /**
     * @param offset 
     * @param length 
     */
    public void setCurMemberSymbol(int offset, int length) 
    {
        _curPropertySymbolOffset = offset;
        _curPropertySymbolLength = length;
    }
    
    /**
     * @return the offset of the current property symbol or 0 if no current property symbol
     */
    public int getCurPropertySymbolOffset()
    {
        return _curPropertySymbolOffset;
    }
    
    /**
     * @return the length of the current property symbol or 0 if no current property symbol
     */
    public int getCurPropertySymbolLength()
    {
        return _curPropertySymbolLength;
    }

    /**
     * @return the type of this value expression or null if it cannot be
     * resolved
     * @throws IllegalStateException if root symbol is not yet set
     */
//    public SignatureBasedType resolveType()
//    {
//        if (!_isRootValid || _rootSymbol == null)
//        {
//            throw new IllegalStateException("Cannot resolve type without root symbol");
//        }
//        
//        // if there's been an error resolving the expression semantics, don't
//        // bother trying to determine type
//        if (_errorFlag)
//        {
//            return null;
//        }
//        
//        if (_curMemberSymbol instanceof IMethodSymbol)
//        {
//            return new MethodType(_curMemberSymbol.getName(),
//                                  ((IMethodSymbol)_curMemberSymbol).getSignature());
//        }
//
//        IObjectSymbol  objectSymbol = null;
//        
//        // no properties, so resolve root's type
//        if (_curMemberSymbol == null)
//        {
//            objectSymbol = _rootSymbol;
//        }
//        else 
//        {
//            objectSymbol = (IObjectSymbol) _curMemberSymbol;
//        }
//
//        final ITypeDescriptor typeDesc = objectSymbol.getTypeDescriptor();
//        int   assignability = 0;
//        assignability |= objectSymbol.isReadable() 
//                            ? IAssignable.ASSIGNMENT_TYPE_RHS
//                            : 0;
//        assignability |= objectSymbol.isWritable() 
//                            ? IAssignable.ASSIGNMENT_TYPE_LHS
//                            : 0;
//        final String typeDescString = typeDesc.getTypeSignature();
//        final String[] superTypes = (String[]) 
//            typeDesc.getSuperTypeSignatures().toArray(new String[0]);
//        final String[] interfaceTypes = (String[]) 
//            typeDesc.getInterfaceTypeSignatures().toArray(new String[0]);
//
//        return new ValueType(typeDescString, superTypes, interfaceTypes,
//                assignability);
//    }
}
