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
import org.eclipse.jst.jsf.facesconfig.emf.FromActionType;
import org.eclipse.jst.jsf.facesconfig.emf.FromOutcomeType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.RedirectType;
import org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Navigation Case Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationCaseTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationCaseTypeImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationCaseTypeImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationCaseTypeImpl#getFromAction <em>From Action</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationCaseTypeImpl#getFromOutcome <em>From Outcome</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationCaseTypeImpl#getToViewId <em>To View Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationCaseTypeImpl#getRedirect <em>Redirect</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationCaseTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NavigationCaseTypeImpl extends EObjectImpl implements NavigationCaseType {
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
     * The cached value of the '{@link #getFromAction() <em>From Action</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFromAction()
     * @generated
     * @ordered
     */
	protected FromActionType fromAction = null;

    /**
     * The cached value of the '{@link #getFromOutcome() <em>From Outcome</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFromOutcome()
     * @generated
     * @ordered
     */
	protected FromOutcomeType fromOutcome = null;

    /**
     * The cached value of the '{@link #getToViewId() <em>To View Id</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getToViewId()
     * @generated
     * @ordered
     */
	protected ToViewIdType toViewId = null;

    /**
     * The cached value of the '{@link #getRedirect() <em>Redirect</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRedirect()
     * @generated
     * @ordered
     */
	protected RedirectType redirect = null;

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
	protected NavigationCaseTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return FacesConfigPackage.Literals.NAVIGATION_CASE_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getDescription() {
        if (description == null) {
            description = new EObjectContainmentEList(DescriptionType.class, this, FacesConfigPackage.NAVIGATION_CASE_TYPE__DESCRIPTION);
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
            displayName = new EObjectContainmentEList(DisplayNameType.class, this, FacesConfigPackage.NAVIGATION_CASE_TYPE__DISPLAY_NAME);
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
            icon = new EObjectContainmentEList(IconType.class, this, FacesConfigPackage.NAVIGATION_CASE_TYPE__ICON);
        }
        return icon;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FromActionType getFromAction() {
        return fromAction;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetFromAction(FromActionType newFromAction, NotificationChain msgs) {
        FromActionType oldFromAction = fromAction;
        fromAction = newFromAction;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_ACTION, oldFromAction, newFromAction);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setFromAction(FromActionType newFromAction) {
        if (newFromAction != fromAction) {
            NotificationChain msgs = null;
            if (fromAction != null)
                msgs = ((InternalEObject)fromAction).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_ACTION, null, msgs);
            if (newFromAction != null)
                msgs = ((InternalEObject)newFromAction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_ACTION, null, msgs);
            msgs = basicSetFromAction(newFromAction, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_ACTION, newFromAction, newFromAction));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FromOutcomeType getFromOutcome() {
        return fromOutcome;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetFromOutcome(FromOutcomeType newFromOutcome, NotificationChain msgs) {
        FromOutcomeType oldFromOutcome = fromOutcome;
        fromOutcome = newFromOutcome;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_OUTCOME, oldFromOutcome, newFromOutcome);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setFromOutcome(FromOutcomeType newFromOutcome) {
        if (newFromOutcome != fromOutcome) {
            NotificationChain msgs = null;
            if (fromOutcome != null)
                msgs = ((InternalEObject)fromOutcome).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_OUTCOME, null, msgs);
            if (newFromOutcome != null)
                msgs = ((InternalEObject)newFromOutcome).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_OUTCOME, null, msgs);
            msgs = basicSetFromOutcome(newFromOutcome, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_OUTCOME, newFromOutcome, newFromOutcome));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ToViewIdType getToViewId() {
        return toViewId;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetToViewId(ToViewIdType newToViewId, NotificationChain msgs) {
        ToViewIdType oldToViewId = toViewId;
        toViewId = newToViewId;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.NAVIGATION_CASE_TYPE__TO_VIEW_ID, oldToViewId, newToViewId);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setToViewId(ToViewIdType newToViewId) {
        if (newToViewId != toViewId) {
            NotificationChain msgs = null;
            if (toViewId != null)
                msgs = ((InternalEObject)toViewId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.NAVIGATION_CASE_TYPE__TO_VIEW_ID, null, msgs);
            if (newToViewId != null)
                msgs = ((InternalEObject)newToViewId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.NAVIGATION_CASE_TYPE__TO_VIEW_ID, null, msgs);
            msgs = basicSetToViewId(newToViewId, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.NAVIGATION_CASE_TYPE__TO_VIEW_ID, newToViewId, newToViewId));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public RedirectType getRedirect() {
        return redirect;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetRedirect(RedirectType newRedirect, NotificationChain msgs) {
        RedirectType oldRedirect = redirect;
        redirect = newRedirect;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.NAVIGATION_CASE_TYPE__REDIRECT, oldRedirect, newRedirect);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setRedirect(RedirectType newRedirect) {
        if (newRedirect != redirect) {
            NotificationChain msgs = null;
            if (redirect != null)
                msgs = ((InternalEObject)redirect).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.NAVIGATION_CASE_TYPE__REDIRECT, null, msgs);
            if (newRedirect != null)
                msgs = ((InternalEObject)newRedirect).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.NAVIGATION_CASE_TYPE__REDIRECT, null, msgs);
            msgs = basicSetRedirect(newRedirect, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.NAVIGATION_CASE_TYPE__REDIRECT, newRedirect, newRedirect));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.NAVIGATION_CASE_TYPE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__DESCRIPTION:
                return ((InternalEList)getDescription()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__DISPLAY_NAME:
                return ((InternalEList)getDisplayName()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__ICON:
                return ((InternalEList)getIcon()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_ACTION:
                return basicSetFromAction(null, msgs);
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_OUTCOME:
                return basicSetFromOutcome(null, msgs);
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__TO_VIEW_ID:
                return basicSetToViewId(null, msgs);
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__REDIRECT:
                return basicSetRedirect(null, msgs);
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
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__DESCRIPTION:
                return getDescription();
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__DISPLAY_NAME:
                return getDisplayName();
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__ICON:
                return getIcon();
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_ACTION:
                return getFromAction();
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_OUTCOME:
                return getFromOutcome();
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__TO_VIEW_ID:
                return getToViewId();
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__REDIRECT:
                return getRedirect();
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__ID:
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
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__DESCRIPTION:
                getDescription().clear();
                getDescription().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__DISPLAY_NAME:
                getDisplayName().clear();
                getDisplayName().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__ICON:
                getIcon().clear();
                getIcon().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_ACTION:
                setFromAction((FromActionType)newValue);
                return;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_OUTCOME:
                setFromOutcome((FromOutcomeType)newValue);
                return;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__TO_VIEW_ID:
                setToViewId((ToViewIdType)newValue);
                return;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__REDIRECT:
                setRedirect((RedirectType)newValue);
                return;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__ID:
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
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__DESCRIPTION:
                getDescription().clear();
                return;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__DISPLAY_NAME:
                getDisplayName().clear();
                return;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__ICON:
                getIcon().clear();
                return;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_ACTION:
                setFromAction((FromActionType)null);
                return;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_OUTCOME:
                setFromOutcome((FromOutcomeType)null);
                return;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__TO_VIEW_ID:
                setToViewId((ToViewIdType)null);
                return;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__REDIRECT:
                setRedirect((RedirectType)null);
                return;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__ID:
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
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__DESCRIPTION:
                return description != null && !description.isEmpty();
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__DISPLAY_NAME:
                return displayName != null && !displayName.isEmpty();
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__ICON:
                return icon != null && !icon.isEmpty();
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_ACTION:
                return fromAction != null;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__FROM_OUTCOME:
                return fromOutcome != null;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__TO_VIEW_ID:
                return toViewId != null;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__REDIRECT:
                return redirect != null;
            case FacesConfigPackage.NAVIGATION_CASE_TYPE__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
        }
        return super.eIsSet(featureID);
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

} //NavigationCaseTypeImpl
