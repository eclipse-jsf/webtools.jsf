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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaSymbol;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IJava Symbol</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IJavaSymbolImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IJavaSymbolImpl#getJavaElement <em>Java Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IJavaSymbolImpl extends EObjectImpl implements IJavaSymbol {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2006 Oracle";

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getJavaElement() <em>Java Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaElement()
	 * @generated
	 * @ordered
	 */
	protected static final IJavaElement JAVA_ELEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJavaElement() <em>Java Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaElement()
	 * @generated
	 * @ordered
	 */
	protected IJavaElement javaElement = JAVA_ELEMENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IJavaSymbolImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the static eclass 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SymbolPackage.Literals.IJAVA_SYMBOL;
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the symbol name 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
     * @param newName 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IJAVA_SYMBOL__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the IJavaElement associated with this symbol
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IJavaElement getJavaElement() {
		return javaElement;
	}

	/**
	 * <!-- begin-user-doc -->
     * @param newJavaElement 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavaElement(IJavaElement newJavaElement) {
		IJavaElement oldJavaElement = javaElement;
		javaElement = newJavaElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SymbolPackage.IJAVA_SYMBOL__JAVA_ELEMENT, oldJavaElement, javaElement));
	}

	/**
	 * <!-- begin-user-doc -->
     * @param featureID 
     * @param resolve 
     * @param coreType 
     * @return the feature 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SymbolPackage.IJAVA_SYMBOL__NAME:
				return getName();
			case SymbolPackage.IJAVA_SYMBOL__JAVA_ELEMENT:
				return getJavaElement();
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
			case SymbolPackage.IJAVA_SYMBOL__NAME:
				setName((String)newValue);
				return;
			case SymbolPackage.IJAVA_SYMBOL__JAVA_ELEMENT:
				setJavaElement((IJavaElement)newValue);
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
			case SymbolPackage.IJAVA_SYMBOL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SymbolPackage.IJAVA_SYMBOL__JAVA_ELEMENT:
				setJavaElement(JAVA_ELEMENT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
     * @param featureID 
     * @return true if featureID's feature has been set 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SymbolPackage.IJAVA_SYMBOL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SymbolPackage.IJAVA_SYMBOL__JAVA_ELEMENT:
				return JAVA_ELEMENT_EDEFAULT == null ? javaElement != null : !JAVA_ELEMENT_EDEFAULT.equals(javaElement);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the default string representation 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", javaElement: ");
		result.append(javaElement);
		result.append(')');
		return result.toString();
	}

} //IJavaSymbolImpl
