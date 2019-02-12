/***************************************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 *   Oracle Corporation - revision
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.emf.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.NameType;
import org.eclipse.jst.jsf.facesconfig.emf.OrderingOrderingType;
import org.eclipse.jst.jsf.facesconfig.emf.OrderingOthersType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ordering Ordering Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.OrderingOrderingTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.OrderingOrderingTypeImpl#getOthers <em>Others</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrderingOrderingTypeImpl extends EObjectImpl implements OrderingOrderingType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected EList name;

	/**
	 * The cached value of the '{@link #getOthers() <em>Others</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOthers()
	 * @generated
	 * @ordered
	 */
	protected OrderingOthersType others;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OrderingOrderingTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.Literals.ORDERING_ORDERING_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getName() {
		if (name == null) {
			name = new EObjectContainmentEList(NameType.class, this, FacesConfigPackage.ORDERING_ORDERING_TYPE__NAME);
		}
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrderingOthersType getOthers() {
		return others;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param newOthers 
	 * @param msgs 
	 * @return NotificationChain
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOthers(OrderingOthersType newOthers, NotificationChain msgs) {
		OrderingOthersType oldOthers = others;
		others = newOthers;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ORDERING_ORDERING_TYPE__OTHERS, oldOthers, newOthers);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOthers(OrderingOthersType newOthers) {
		if (newOthers != others) {
			NotificationChain msgs = null;
			if (others != null)
				msgs = ((InternalEObject)others).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ORDERING_ORDERING_TYPE__OTHERS, null, msgs);
			if (newOthers != null)
				msgs = ((InternalEObject)newOthers).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ORDERING_ORDERING_TYPE__OTHERS, null, msgs);
			msgs = basicSetOthers(newOthers, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ORDERING_ORDERING_TYPE__OTHERS, newOthers, newOthers));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FacesConfigPackage.ORDERING_ORDERING_TYPE__NAME:
				return ((InternalEList)getName()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.ORDERING_ORDERING_TYPE__OTHERS:
				return basicSetOthers(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FacesConfigPackage.ORDERING_ORDERING_TYPE__NAME:
				return getName();
			case FacesConfigPackage.ORDERING_ORDERING_TYPE__OTHERS:
				return getOthers();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case FacesConfigPackage.ORDERING_ORDERING_TYPE__NAME:
				getName().clear();
				getName().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.ORDERING_ORDERING_TYPE__OTHERS:
				setOthers((OrderingOthersType)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case FacesConfigPackage.ORDERING_ORDERING_TYPE__NAME:
				getName().clear();
				return;
			case FacesConfigPackage.ORDERING_ORDERING_TYPE__OTHERS:
				setOthers((OrderingOthersType)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case FacesConfigPackage.ORDERING_ORDERING_TYPE__NAME:
				return name != null && !name.isEmpty();
			case FacesConfigPackage.ORDERING_ORDERING_TYPE__OTHERS:
				return others != null;
		}
		return super.eIsSet(featureID);
	}

} //OrderingOrderingTypeImpl
