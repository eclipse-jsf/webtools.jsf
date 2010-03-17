/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 *   Oracle Corporation - revision
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.emf.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.OrderingOrderingType;
import org.eclipse.jst.jsf.facesconfig.emf.OrderingType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ordering Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.OrderingTypeImpl#getBefore <em>Before</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.OrderingTypeImpl#getAfter <em>After</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrderingTypeImpl extends EObjectImpl implements OrderingType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getBefore() <em>Before</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBefore()
	 * @generated
	 * @ordered
	 */
	protected OrderingOrderingType before;

	/**
	 * The cached value of the '{@link #getAfter() <em>After</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAfter()
	 * @generated
	 * @ordered
	 */
	protected OrderingOrderingType after;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OrderingTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.Literals.ORDERING_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrderingOrderingType getBefore() {
		return before;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param newBefore 
	 * @param msgs 
	 * @return NotificationChain
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBefore(OrderingOrderingType newBefore, NotificationChain msgs) {
		OrderingOrderingType oldBefore = before;
		before = newBefore;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ORDERING_TYPE__BEFORE, oldBefore, newBefore);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBefore(OrderingOrderingType newBefore) {
		if (newBefore != before) {
			NotificationChain msgs = null;
			if (before != null)
				msgs = ((InternalEObject)before).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ORDERING_TYPE__BEFORE, null, msgs);
			if (newBefore != null)
				msgs = ((InternalEObject)newBefore).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ORDERING_TYPE__BEFORE, null, msgs);
			msgs = basicSetBefore(newBefore, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ORDERING_TYPE__BEFORE, newBefore, newBefore));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrderingOrderingType getAfter() {
		return after;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param newAfter 
	 * @param msgs 
	 * @return NotificationChain
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAfter(OrderingOrderingType newAfter, NotificationChain msgs) {
		OrderingOrderingType oldAfter = after;
		after = newAfter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ORDERING_TYPE__AFTER, oldAfter, newAfter);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAfter(OrderingOrderingType newAfter) {
		if (newAfter != after) {
			NotificationChain msgs = null;
			if (after != null)
				msgs = ((InternalEObject)after).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ORDERING_TYPE__AFTER, null, msgs);
			if (newAfter != null)
				msgs = ((InternalEObject)newAfter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ORDERING_TYPE__AFTER, null, msgs);
			msgs = basicSetAfter(newAfter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ORDERING_TYPE__AFTER, newAfter, newAfter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FacesConfigPackage.ORDERING_TYPE__BEFORE:
				return basicSetBefore(null, msgs);
			case FacesConfigPackage.ORDERING_TYPE__AFTER:
				return basicSetAfter(null, msgs);
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
			case FacesConfigPackage.ORDERING_TYPE__BEFORE:
				return getBefore();
			case FacesConfigPackage.ORDERING_TYPE__AFTER:
				return getAfter();
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
			case FacesConfigPackage.ORDERING_TYPE__BEFORE:
				setBefore((OrderingOrderingType)newValue);
				return;
			case FacesConfigPackage.ORDERING_TYPE__AFTER:
				setAfter((OrderingOrderingType)newValue);
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
			case FacesConfigPackage.ORDERING_TYPE__BEFORE:
				setBefore((OrderingOrderingType)null);
				return;
			case FacesConfigPackage.ORDERING_TYPE__AFTER:
				setAfter((OrderingOrderingType)null);
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
			case FacesConfigPackage.ORDERING_TYPE__BEFORE:
				return before != null;
			case FacesConfigPackage.ORDERING_TYPE__AFTER:
				return after != null;
		}
		return super.eIsSet(featureID);
	}

} //OrderingTypeImpl
