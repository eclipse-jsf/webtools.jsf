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
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleExtensionType;
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
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationRuleTypeImpl#getNavigationRuleExtension <em>Navigation Rule Extension</em>}</li>
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
	 * The cached value of the '{@link #getFromViewId() <em>From View Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFromViewId()
	 * @generated
	 * @ordered
	 */
	protected FromViewIdType fromViewId;

    /**
	 * The cached value of the '{@link #getNavigationCase() <em>Navigation Case</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNavigationCase()
	 * @generated
	 * @ordered
	 */
	protected EList navigationCase;

    /**
	 * The cached value of the '{@link #getNavigationRuleExtension() <em>Navigation Rule Extension</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNavigationRuleExtension()
	 * @generated
	 * @ordered
	 */
    protected EList navigationRuleExtension;

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
		return FacesConfigPackage.Literals.NAVIGATION_RULE_TYPE;
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
     * @param newFromViewId 
     * @param msgs 
     * @return the notification chain 
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
    public EList getNavigationRuleExtension() {
		if (navigationRuleExtension == null) {
			navigationRuleExtension = new EObjectContainmentEList(NavigationRuleExtensionType.class, this, FacesConfigPackage.NAVIGATION_RULE_TYPE__NAVIGATION_RULE_EXTENSION);
		}
		return navigationRuleExtension;
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
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
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
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__NAVIGATION_RULE_EXTENSION:
				return ((InternalEList)getNavigationRuleExtension()).basicRemove(otherEnd, msgs);
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
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__NAVIGATION_RULE_EXTENSION:
				return getNavigationRuleExtension();
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__ID:
				return getId();
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
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__NAVIGATION_RULE_EXTENSION:
				getNavigationRuleExtension().clear();
				getNavigationRuleExtension().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__ID:
				setId((String)newValue);
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
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__NAVIGATION_RULE_EXTENSION:
				getNavigationRuleExtension().clear();
				return;
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__ID:
				setId(ID_EDEFAULT);
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
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__NAVIGATION_RULE_EXTENSION:
				return navigationRuleExtension != null && !navigationRuleExtension.isEmpty();
			case FacesConfigPackage.NAVIGATION_RULE_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //NavigationRuleTypeImpl
