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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Navigation Rule Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationRuleTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationRuleTypeImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationRuleTypeImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationRuleTypeImpl#getFromViewId <em>From View Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationRuleTypeImpl#getNavigationCase <em>Navigation Case</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationRuleTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NavigationRuleTypeImpl extends EObjectImpl implements NavigationRuleType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected EList description = null;

	/**
	 * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected EList displayName = null;

	/**
	 * The cached value of the '{@link #getIcon() <em>Icon</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcon()
	 * @generated
	 * @ordered
	 */
	protected EList icon = null;

	/**
	 * The cached value of the '{@link #getFromViewId() <em>From View Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFromViewId()
	 * @generated
	 * @ordered
	 */
	protected FromViewIdType fromViewId = null;

	/**
	 * The cached value of the '{@link #getNavigationCase() <em>Navigation Case</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNavigationCase()
	 * @generated
	 * @ordered
	 */
	protected EList navigationCase = null;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NavigationRuleTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.eINSTANCE.getNavigationRuleType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescription() {
		if (description == null) {
			description = new EObjectContainmentEList(DescriptionType.class, this, FacesConfigPackage.NAVIGATION_RULE_TYPE__DESCRIPTION);
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
			displayName = new EObjectContainmentEList(DisplayNameType.class, this, FacesConfigPackage.NAVIGATION_RULE_TYPE__DISPLAY_NAME);
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
			icon = new EObjectContainmentEList(IconType.class, this, FacesConfigPackage.NAVIGATION_RULE_TYPE__ICON);
		}
		return icon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FromViewIdType getFromViewId() {
		return fromViewId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFromViewId(FromViewIdType newFromViewId, NotificationChain msgs) {
		FromViewIdType oldFromViewId = fromViewId;
		fromViewId = newFromViewId;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.NAVIGATION_RULE_TYPE__FROM_VIEW_ID, oldFromViewId, newFromViewId);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFromViewId(FromViewIdType newFromViewId) {
		if (newFromViewId != fromViewId) {
			NotificationChain msgs = null;
			if (fromViewId != null)
				msgs = ((InternalEObject)fromViewId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.NAVIGATION_RULE_TYPE__FROM_VIEW_ID, null, msgs);
			if (newFromViewId != null)
				msgs = ((InternalEObject)newFromViewId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.NAVIGATION_RULE_TYPE__FROM_VIEW_ID, null, msgs);
			msgs = basicSetFromViewId(newFromViewId, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.NAVIGATION_RULE_TYPE__FROM_VIEW_ID, newFromViewId, newFromViewId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getNavigationCase() {
		if (navigationCase == null) {
			navigationCase = new EObjectContainmentEList(NavigationCaseType.class, this, FacesConfigPackage.NAVIGATION_RULE_TYPE__NAVIGATION_CASE);
		}
		return navigationCase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.NAVIGATION_RULE_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case FacesConfigPackage.NAVIGATION_RULE_TYPE__DESCRIPTION:
					return ((InternalEList)getDescription()).basicRemove(otherEnd, msgs);
				case FacesConfigPackage.NAVIGATION_RULE_TYPE__DISPLAY_NAME:
					return ((InternalEList)getDisplayName()).basicRemove(otherEnd, msgs);
				case FacesConfigPackage.NAVIGATION_RULE_TYPE__ICON:
					return ((InternalEList)getIcon()).basicRemove(otherEnd, msgs);
				case FacesConfigPackage.NAVIGATION_RULE_TYPE__FROM_VIEW_ID:
					return basicSetFromViewId(null, msgs);
				case FacesConfigPackage.NAVIGATION_RULE_TYPE__NAVIGATION_CASE:
					return ((InternalEList)getNavigationCase()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__DESCRIPTION:
				return getDescription();
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__DISPLAY_NAME:
				return getDisplayName();
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__ICON:
				return getIcon();
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__FROM_VIEW_ID:
				return getFromViewId();
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__NAVIGATION_CASE:
				return getNavigationCase();
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__ID:
				return getId();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__DISPLAY_NAME:
				getDisplayName().clear();
				getDisplayName().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__ICON:
				getIcon().clear();
				getIcon().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__FROM_VIEW_ID:
				setFromViewId((FromViewIdType)newValue);
				return;
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__NAVIGATION_CASE:
				getNavigationCase().clear();
				getNavigationCase().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__ID:
				setId((String)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__DESCRIPTION:
				getDescription().clear();
				return;
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__DISPLAY_NAME:
				getDisplayName().clear();
				return;
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__ICON:
				getIcon().clear();
				return;
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__FROM_VIEW_ID:
				setFromViewId((FromViewIdType)null);
				return;
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__NAVIGATION_CASE:
				getNavigationCase().clear();
				return;
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__DESCRIPTION:
				return description != null && !description.isEmpty();
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__DISPLAY_NAME:
				return displayName != null && !displayName.isEmpty();
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__ICON:
				return icon != null && !icon.isEmpty();
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__FROM_VIEW_ID:
				return fromViewId != null;
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__NAVIGATION_CASE:
				return navigationCase != null && !navigationCase.isEmpty();
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //NavigationRuleTypeImpl
