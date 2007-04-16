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

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.context.symbol.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.ITypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IType Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.ITypeDescriptorImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.ITypeDescriptorImpl#getTypeSignature <em>Type Signature</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.ITypeDescriptorImpl#getSuperTypeSignatures <em>Super Type Signatures</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.ITypeDescriptorImpl#getInterfaceTypeSignatures <em>Interface Type Signatures</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.ITypeDescriptorImpl#getTypeSignatureDelegate <em>Type Signature Delegate</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.ITypeDescriptorImpl#getMethods <em>Methods</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ITypeDescriptorImpl extends EObjectImpl implements ITypeDescriptor {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright 2006 Oracle";

    /**
     * The default value of the '{@link #getTypeSignature() <em>Type Signature</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypeSignature()
     * @generated
     * @ordered
     */
    protected static final String TYPE_SIGNATURE_EDEFAULT = "";

    /**
     * The default value of the '{@link #getTypeSignatureDelegate() <em>Type Signature Delegate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypeSignatureDelegate()
     * @generated
     * @ordered
     */
    protected static final String TYPE_SIGNATURE_DELEGATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTypeSignatureDelegate() <em>Type Signature Delegate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypeSignatureDelegate()
     * @generated
     * @ordered
     */
    protected String typeSignatureDelegate = TYPE_SIGNATURE_DELEGATE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ITypeDescriptorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * @return the static class 
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SymbolPackage.Literals.ITYPE_DESCRIPTOR;
    }

    /**
     * <!-- begin-user-doc -->
     * @return get properties for this type 
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public abstract EList getProperties();

    /**
     * <!-- begin-user-doc -->
     * @return the type signature in JVM/JDT signature form 
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public abstract String getTypeSignature();

    /**
     * <!-- begin-user-doc -->
     * @return the list of super types 
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public abstract EList getSuperTypeSignatures();

    /**
     * <!-- begin-user-doc -->
     * @return the list of implemented interfaces
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public abstract EList getInterfaceTypeSignatures();

    /**
     * <!-- begin-user-doc -->
     * @return the type signature delegate.  The descriptor may choose to use
     * this delegate it if it is set and it cannot derive this information
     * from other properties
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTypeSignatureDelegate() {
        return typeSignatureDelegate;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newTypeSignatureDelegate 
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTypeSignatureDelegate(String newTypeSignatureDelegate) {
        String oldTypeSignatureDelegate = typeSignatureDelegate;
        typeSignatureDelegate = newTypeSignatureDelegate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.ITYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE, oldTypeSignatureDelegate, typeSignatureDelegate));
    }

    /**
     * <!-- begin-user-doc -->
     * @return the methods for this type or empty list if none exist 
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public abstract EList getMethods();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean instanceOf(final String typeSignature) 
    {
        if (getTypeSignature() != null
                && getTypeSignature().equals(typeSignature))
        {
            return true;
        }
        
        for (final Iterator it = getSuperTypeSignatures().iterator(); it.hasNext();)
        {
            final String superType = (String) it.next();
            
            if (superType.equals(typeSignature))
            {
                return true;
            }
        }
        
        for (final Iterator it = getInterfaceTypeSignatures().iterator(); it.hasNext();)
        {
            final String superType = (String) it.next();
            
            if (superType.equals(typeSignature))
            {
                return true;
            }
        }
        
        return false;
    }

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isArray()
	{
		return Signature.getArrayCount(getTypeSignature()) > 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public IObjectSymbol getArrayElement() 
	{
		if (isArray())
		{
			// sub-class of ITypeDescriptor must implement this if they which to provide
			// array element support
			throw new UnsupportedOperationException("Base type descriptor does not support this function");
		}
		
		// shouldn't be called if not an array
		return null;
	}

    /**
     * <!-- begin-user-doc -->
     * @param featureID 
     * @param resolve 
     * @param coreType 
     * @return get the feature 
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SymbolPackage.ITYPE_DESCRIPTOR__PROPERTIES:
                return getProperties();
            case SymbolPackage.ITYPE_DESCRIPTOR__TYPE_SIGNATURE:
                return getTypeSignature();
            case SymbolPackage.ITYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES:
                return getSuperTypeSignatures();
            case SymbolPackage.ITYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES:
                return getInterfaceTypeSignatures();
            case SymbolPackage.ITYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE:
                return getTypeSignatureDelegate();
            case SymbolPackage.ITYPE_DESCRIPTOR__METHODS:
                return getMethods();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * @param featureID 
     * @param newValue 
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case SymbolPackage.ITYPE_DESCRIPTOR__PROPERTIES:
                getProperties().clear();
                getProperties().addAll((Collection)newValue);
                return;
            case SymbolPackage.ITYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES:
                getSuperTypeSignatures().clear();
                getSuperTypeSignatures().addAll((Collection)newValue);
                return;
            case SymbolPackage.ITYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES:
                getInterfaceTypeSignatures().clear();
                getInterfaceTypeSignatures().addAll((Collection)newValue);
                return;
            case SymbolPackage.ITYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE:
                setTypeSignatureDelegate((String)newValue);
                return;
            case SymbolPackage.ITYPE_DESCRIPTOR__METHODS:
                getMethods().clear();
                getMethods().addAll((Collection)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * @param featureID 
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case SymbolPackage.ITYPE_DESCRIPTOR__PROPERTIES:
                getProperties().clear();
                return;
            case SymbolPackage.ITYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES:
                getSuperTypeSignatures().clear();
                return;
            case SymbolPackage.ITYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES:
                getInterfaceTypeSignatures().clear();
                return;
            case SymbolPackage.ITYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE:
                setTypeSignatureDelegate(TYPE_SIGNATURE_DELEGATE_EDEFAULT);
                return;
            case SymbolPackage.ITYPE_DESCRIPTOR__METHODS:
                getMethods().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * @param featureID 
     * @return true if feature has been set 
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SymbolPackage.ITYPE_DESCRIPTOR__PROPERTIES:
                return !getProperties().isEmpty();
            case SymbolPackage.ITYPE_DESCRIPTOR__TYPE_SIGNATURE:
                return TYPE_SIGNATURE_EDEFAULT == null ? getTypeSignature() != null : !TYPE_SIGNATURE_EDEFAULT.equals(getTypeSignature());
            case SymbolPackage.ITYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES:
                return !getSuperTypeSignatures().isEmpty();
            case SymbolPackage.ITYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES:
                return !getInterfaceTypeSignatures().isEmpty();
            case SymbolPackage.ITYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE:
                return TYPE_SIGNATURE_DELEGATE_EDEFAULT == null ? typeSignatureDelegate != null : !TYPE_SIGNATURE_DELEGATE_EDEFAULT.equals(typeSignatureDelegate);
            case SymbolPackage.ITYPE_DESCRIPTOR__METHODS:
                return !getMethods().isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * @return the debug string format of the type signature 
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (typeSignatureDelegate: ");
        result.append(typeSignatureDelegate);
        result.append(')');
        return result.toString();
    }

} //ITypeDescriptorImpl

