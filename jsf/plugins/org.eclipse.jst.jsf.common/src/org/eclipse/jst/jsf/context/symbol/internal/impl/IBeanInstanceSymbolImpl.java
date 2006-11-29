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

import java.util.HashMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IBean Instance Symbol</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanInstanceSymbolImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanInstanceSymbolImpl#getMethods <em>Methods</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IBeanInstanceSymbolImpl extends IInstanceSymbolImpl implements IBeanInstanceSymbol {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final String copyright = "Copyright 2006 Oracle";

	/**
	 * A detailed description (human readable) about this instance symbol
	 */
	protected String			  detailedDescription = null;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IBeanInstanceSymbolImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the EClass
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SymbolPackage.Literals.IBEAN_INSTANCE_SYMBOL;
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the list of bean properties 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getProperties() 
	{
		return getJavaTypeDescriptor().getBeanProperties();
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the list of methods 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getMethods() {
		return getJavaTypeDescriptor().getBeanMethods();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public IJavaTypeDescriptor2 getJavaTypeDescriptor() {
        return (IJavaTypeDescriptor2) getTypeDescriptor();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setJavaTypeDescriptor(IJavaTypeDescriptor2 newTypeDescriptor) {
        setTypeDescriptor(newTypeDescriptor);
    }

	/**
	 * <!-- begin-user-doc -->
     * @param featureID 
     * @param resolve 
     * @param coreType 
     * @return the object related to featureID
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SymbolPackage.IBEAN_INSTANCE_SYMBOL__PROPERTIES:
				return getProperties();
			case SymbolPackage.IBEAN_INSTANCE_SYMBOL__METHODS:
				return getMethods();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
     * @param featureID 
     * @return whether the corresponding feature is set 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SymbolPackage.IBEAN_INSTANCE_SYMBOL__PROPERTIES:
				return !getProperties().isEmpty();
			case SymbolPackage.IBEAN_INSTANCE_SYMBOL__METHODS:
				return !getMethods().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	public String getDetailedDescription() 
	{
		return detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) 
	{
		this.detailedDescription = detailedDescription;
	}

	/**
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IInstanceSymbolImpl#isTypeResolved()
	 * @generated NOT
	 */
	// @Override
	public boolean isTypeResolved() 
	{
		return getJavaTypeDescriptor() != null;
	}

    /**
     * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IInstanceSymbolImpl#setTypeDescriptor(org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor)
     * @generated NOT
     */
    public void setTypeDescriptor(ITypeDescriptor newTypeDescriptor) 
    {
        if (newTypeDescriptor instanceof IJavaTypeDescriptor2)
        {
            super.setTypeDescriptor(newTypeDescriptor);
        }
        else
        {
            throw new IllegalArgumentException("Bean instance symbol requires a IJavaTypeDescriptor2");
        }
    }

    public boolean isReadable() 
    {
        // beans always readable
        return true;
    }

    public boolean isWritable() 
    {
        // beans not writable
        return false;
    }

    public void setReadable(boolean value) {
        // do nothing
    }

    public void setWritable(boolean value) {
        // do nothing
    }

    /* 
     * @generated NOT
     */
    public ITypeDescriptor coerce(String typeSignature) 
    {
        if (supportsCoercion(typeSignature))
        {
            if (TypeConstants.TYPE_MAP.equals(typeSignature))
            {
                IBoundedMapTypeDescriptor mapDesc =  
                    SymbolFactory.eINSTANCE.createIBoundedMapTypeDescriptor();
                // bean maps are generally writable
                mapDesc.setImmutable(true);
                mapDesc.setMapSource(new HashMap());  // give it an empty map
                return mapDesc;
            }
            // TODO: add support for arrays
            else if (TypeConstants.TYPE_LIST.equals(typeSignature))
            {
                // TODO: 
            }
        }
        
        return null;
    }

} //IBeanInstanceSymbolImpl
