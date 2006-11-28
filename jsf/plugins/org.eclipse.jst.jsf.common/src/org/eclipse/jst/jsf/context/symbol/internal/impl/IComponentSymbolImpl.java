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
package org.eclipse.jst.jsf.context.symbol.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IComponent Symbol</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class IComponentSymbolImpl extends IInstanceSymbolImpl implements IComponentSymbol {
    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = "Copyright 2006 Oracle";

    /**
     * A human readable description of this symbol
     */
    protected String  _detailedDescription = null;
    
    /**
     * true if this component symbol is readable
     */
    protected boolean _isReadable = READABLE_EDEFAULT;
    
    /**
     * true if this component symbol is writable
     */
    protected boolean _isWritable = WRITABLE_EDEFAULT;
    
    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected IComponentSymbolImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * @return the static class 
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SymbolPackage.Literals.ICOMPONENT_SYMBOL;
    }

    public String getDetailedDescription() {
        return _detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        _detailedDescription = detailedDescription;
    }

    /**
     * @generated NOT
     */
    public ITypeDescriptor coerce(String typeSignature) {
        // TODO:
        return getTypeDescriptor();
    }

    /** 
     * @generated NOT
     */
    public boolean supportsCoercion(String typeSignature) {
        // TODO:
        if (getTypeDescriptor().instanceOf(typeSignature))
        {
            return true;
        }
        return false;
    }
} //IComponentSymbolImpl
