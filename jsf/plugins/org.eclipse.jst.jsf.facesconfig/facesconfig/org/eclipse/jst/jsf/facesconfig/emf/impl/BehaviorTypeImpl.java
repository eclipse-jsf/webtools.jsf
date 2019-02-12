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
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.BehaviorClassType;
import org.eclipse.jst.jsf.facesconfig.emf.BehaviorExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.BehaviorIdType;
import org.eclipse.jst.jsf.facesconfig.emf.BehaviorType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Behavior Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorTypeImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorTypeImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorTypeImpl#getBehaviorId <em>Behavior Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorTypeImpl#getBehaviorClass <em>Behavior Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorTypeImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorTypeImpl#getBehaviorExtension <em>Behavior Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BehaviorTypeImpl extends EObjectImpl implements BehaviorType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected EList description;

	/**
	 * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected EList displayName;

	/**
	 * The cached value of the '{@link #getIcon() <em>Icon</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcon()
	 * @generated
	 * @ordered
	 */
	protected EList icon;

	/**
	 * The cached value of the '{@link #getBehaviorId() <em>Behavior Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBehaviorId()
	 * @generated
	 * @ordered
	 */
	protected BehaviorIdType behaviorId;

	/**
	 * The cached value of the '{@link #getBehaviorClass() <em>Behavior Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBehaviorClass()
	 * @generated
	 * @ordered
	 */
	protected BehaviorClassType behaviorClass;

	/**
	 * The cached value of the '{@link #getAttribute() <em>Attribute</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttribute()
	 * @generated
	 * @ordered
	 */
	protected EList attribute;

	/**
	 * The cached value of the '{@link #getProperty() <em>Property</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected EList property;

	/**
	 * The cached value of the '{@link #getBehaviorExtension() <em>Behavior Extension</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBehaviorExtension()
	 * @generated
	 * @ordered
	 */
	protected EList behaviorExtension;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BehaviorTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.Literals.BEHAVIOR_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescription() {
		if (description == null) {
			description = new EObjectContainmentEList(DescriptionType.class, this, FacesConfigPackage.BEHAVIOR_TYPE__DESCRIPTION);
		}
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDisplayName() {
		if (displayName == null) {
			displayName = new EObjectContainmentEList(DisplayNameType.class, this, FacesConfigPackage.BEHAVIOR_TYPE__DISPLAY_NAME);
		}
		return displayName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIcon() {
		if (icon == null) {
			icon = new EObjectContainmentEList(IconType.class, this, FacesConfigPackage.BEHAVIOR_TYPE__ICON);
		}
		return icon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehaviorIdType getBehaviorId() {
		return behaviorId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newBehaviorId 
	 * @param msgs 
	 * @return NotificationChain
	 * @generated NOT
	 */
	public NotificationChain basicSetBehaviorId(BehaviorIdType newBehaviorId, NotificationChain msgs) {
		BehaviorIdType oldBehaviorId = behaviorId;
		behaviorId = newBehaviorId;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_ID, oldBehaviorId, newBehaviorId);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBehaviorId(BehaviorIdType newBehaviorId) {
		if (newBehaviorId != behaviorId) {
			NotificationChain msgs = null;
			if (behaviorId != null)
				msgs = ((InternalEObject)behaviorId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_ID, null, msgs);
			if (newBehaviorId != null)
				msgs = ((InternalEObject)newBehaviorId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_ID, null, msgs);
			msgs = basicSetBehaviorId(newBehaviorId, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_ID, newBehaviorId, newBehaviorId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehaviorClassType getBehaviorClass() {
		return behaviorClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newBehaviorClass 
	 * @param msgs 
	 * @return NotificationChain
	 * @generated NOT
	 */
	public NotificationChain basicSetBehaviorClass(BehaviorClassType newBehaviorClass, NotificationChain msgs) {
		BehaviorClassType oldBehaviorClass = behaviorClass;
		behaviorClass = newBehaviorClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_CLASS, oldBehaviorClass, newBehaviorClass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBehaviorClass(BehaviorClassType newBehaviorClass) {
		if (newBehaviorClass != behaviorClass) {
			NotificationChain msgs = null;
			if (behaviorClass != null)
				msgs = ((InternalEObject)behaviorClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_CLASS, null, msgs);
			if (newBehaviorClass != null)
				msgs = ((InternalEObject)newBehaviorClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_CLASS, null, msgs);
			msgs = basicSetBehaviorClass(newBehaviorClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_CLASS, newBehaviorClass, newBehaviorClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getAttribute() {
		if (attribute == null) {
			attribute = new EObjectContainmentEList(AttributeType.class, this, FacesConfigPackage.BEHAVIOR_TYPE__ATTRIBUTE);
		}
		return attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getProperty() {
		if (property == null) {
			property = new EObjectContainmentEList(PropertyType.class, this, FacesConfigPackage.BEHAVIOR_TYPE__PROPERTY);
		}
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getBehaviorExtension() {
		if (behaviorExtension == null) {
			behaviorExtension = new EObjectContainmentEList(BehaviorExtensionType.class, this, FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_EXTENSION);
		}
		return behaviorExtension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FacesConfigPackage.BEHAVIOR_TYPE__DESCRIPTION:
				return ((InternalEList)getDescription()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.BEHAVIOR_TYPE__DISPLAY_NAME:
				return ((InternalEList)getDisplayName()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.BEHAVIOR_TYPE__ICON:
				return ((InternalEList)getIcon()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_ID:
				return basicSetBehaviorId(null, msgs);
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_CLASS:
				return basicSetBehaviorClass(null, msgs);
			case FacesConfigPackage.BEHAVIOR_TYPE__ATTRIBUTE:
				return ((InternalEList)getAttribute()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.BEHAVIOR_TYPE__PROPERTY:
				return ((InternalEList)getProperty()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_EXTENSION:
				return ((InternalEList)getBehaviorExtension()).basicRemove(otherEnd, msgs);
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
			case FacesConfigPackage.BEHAVIOR_TYPE__DESCRIPTION:
				return getDescription();
			case FacesConfigPackage.BEHAVIOR_TYPE__DISPLAY_NAME:
				return getDisplayName();
			case FacesConfigPackage.BEHAVIOR_TYPE__ICON:
				return getIcon();
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_ID:
				return getBehaviorId();
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_CLASS:
				return getBehaviorClass();
			case FacesConfigPackage.BEHAVIOR_TYPE__ATTRIBUTE:
				return getAttribute();
			case FacesConfigPackage.BEHAVIOR_TYPE__PROPERTY:
				return getProperty();
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_EXTENSION:
				return getBehaviorExtension();
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
			case FacesConfigPackage.BEHAVIOR_TYPE__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.BEHAVIOR_TYPE__DISPLAY_NAME:
				getDisplayName().clear();
				getDisplayName().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.BEHAVIOR_TYPE__ICON:
				getIcon().clear();
				getIcon().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_ID:
				setBehaviorId((BehaviorIdType)newValue);
				return;
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_CLASS:
				setBehaviorClass((BehaviorClassType)newValue);
				return;
			case FacesConfigPackage.BEHAVIOR_TYPE__ATTRIBUTE:
				getAttribute().clear();
				getAttribute().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.BEHAVIOR_TYPE__PROPERTY:
				getProperty().clear();
				getProperty().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_EXTENSION:
				getBehaviorExtension().clear();
				getBehaviorExtension().addAll((Collection)newValue);
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
			case FacesConfigPackage.BEHAVIOR_TYPE__DESCRIPTION:
				getDescription().clear();
				return;
			case FacesConfigPackage.BEHAVIOR_TYPE__DISPLAY_NAME:
				getDisplayName().clear();
				return;
			case FacesConfigPackage.BEHAVIOR_TYPE__ICON:
				getIcon().clear();
				return;
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_ID:
				setBehaviorId((BehaviorIdType)null);
				return;
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_CLASS:
				setBehaviorClass((BehaviorClassType)null);
				return;
			case FacesConfigPackage.BEHAVIOR_TYPE__ATTRIBUTE:
				getAttribute().clear();
				return;
			case FacesConfigPackage.BEHAVIOR_TYPE__PROPERTY:
				getProperty().clear();
				return;
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_EXTENSION:
				getBehaviorExtension().clear();
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
			case FacesConfigPackage.BEHAVIOR_TYPE__DESCRIPTION:
				return description != null && !description.isEmpty();
			case FacesConfigPackage.BEHAVIOR_TYPE__DISPLAY_NAME:
				return displayName != null && !displayName.isEmpty();
			case FacesConfigPackage.BEHAVIOR_TYPE__ICON:
				return icon != null && !icon.isEmpty();
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_ID:
				return behaviorId != null;
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_CLASS:
				return behaviorClass != null;
			case FacesConfigPackage.BEHAVIOR_TYPE__ATTRIBUTE:
				return attribute != null && !attribute.isEmpty();
			case FacesConfigPackage.BEHAVIOR_TYPE__PROPERTY:
				return property != null && !property.isEmpty();
			case FacesConfigPackage.BEHAVIOR_TYPE__BEHAVIOR_EXTENSION:
				return behaviorExtension != null && !behaviorExtension.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //BehaviorTypeImpl
