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
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.jsf.facesconfig.emf.BaseNameType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType;
import org.eclipse.jst.jsf.facesconfig.emf.VarType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Bundle Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ResourceBundleTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ResourceBundleTypeImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ResourceBundleTypeImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ResourceBundleTypeImpl#getBaseName <em>Base Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ResourceBundleTypeImpl#getVar <em>Var</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ResourceBundleTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceBundleTypeImpl extends EObjectImpl implements ResourceBundleType {
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
     * The cached value of the '{@link #getBaseName() <em>Base Name</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBaseName()
     * @generated
     * @ordered
     */
    protected BaseNameType baseName = null;

    /**
     * The cached value of the '{@link #getVar() <em>Var</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVar()
     * @generated
     * @ordered
     */
    protected VarType var = null;

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
    protected ResourceBundleTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return FacesConfigPackage.Literals.RESOURCE_BUNDLE_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getDescription() {
        if (description == null) {
            description = new EObjectContainmentEList(DescriptionType.class, this, FacesConfigPackage.RESOURCE_BUNDLE_TYPE__DESCRIPTION);
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
            displayName = new EObjectContainmentEList(DisplayNameType.class, this, FacesConfigPackage.RESOURCE_BUNDLE_TYPE__DISPLAY_NAME);
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
            icon = new EObjectContainmentEList(IconType.class, this, FacesConfigPackage.RESOURCE_BUNDLE_TYPE__ICON);
        }
        return icon;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BaseNameType getBaseName() {
        if (baseName != null && baseName.eIsProxy()) {
            InternalEObject oldBaseName = (InternalEObject)baseName;
            baseName = (BaseNameType)eResolveProxy(oldBaseName);
            if (baseName != oldBaseName) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, FacesConfigPackage.RESOURCE_BUNDLE_TYPE__BASE_NAME, oldBaseName, baseName));
            }
        }
        return baseName;
    }

    /**
     * <!-- begin-user-doc -->
     * @return the base name 
     * <!-- end-user-doc -->
     * @generated
     */
    public BaseNameType basicGetBaseName() {
        return baseName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBaseName(BaseNameType newBaseName) {
        BaseNameType oldBaseName = baseName;
        baseName = newBaseName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.RESOURCE_BUNDLE_TYPE__BASE_NAME, oldBaseName, baseName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VarType getVar() {
        if (var != null && var.eIsProxy()) {
            InternalEObject oldVar = (InternalEObject)var;
            var = (VarType)eResolveProxy(oldVar);
            if (var != oldVar) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, FacesConfigPackage.RESOURCE_BUNDLE_TYPE__VAR, oldVar, var));
            }
        }
        return var;
    }

    /**
     * <!-- begin-user-doc -->
     * @return the variable 
     * <!-- end-user-doc -->
     * @generated
     */
    public VarType basicGetVar() {
        return var;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVar(VarType newVar) {
        VarType oldVar = var;
        var = newVar;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.RESOURCE_BUNDLE_TYPE__VAR, oldVar, var));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.RESOURCE_BUNDLE_TYPE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__DESCRIPTION:
                return ((InternalEList)getDescription()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__DISPLAY_NAME:
                return ((InternalEList)getDisplayName()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__ICON:
                return ((InternalEList)getIcon()).basicRemove(otherEnd, msgs);
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
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__DESCRIPTION:
                return getDescription();
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__DISPLAY_NAME:
                return getDisplayName();
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__ICON:
                return getIcon();
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__BASE_NAME:
                if (resolve) return getBaseName();
                return basicGetBaseName();
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__VAR:
                if (resolve) return getVar();
                return basicGetVar();
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__ID:
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
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__DESCRIPTION:
                getDescription().clear();
                getDescription().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__DISPLAY_NAME:
                getDisplayName().clear();
                getDisplayName().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__ICON:
                getIcon().clear();
                getIcon().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__BASE_NAME:
                setBaseName((BaseNameType)newValue);
                return;
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__VAR:
                setVar((VarType)newValue);
                return;
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__ID:
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
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__DESCRIPTION:
                getDescription().clear();
                return;
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__DISPLAY_NAME:
                getDisplayName().clear();
                return;
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__ICON:
                getIcon().clear();
                return;
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__BASE_NAME:
                setBaseName((BaseNameType)null);
                return;
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__VAR:
                setVar((VarType)null);
                return;
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__ID:
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
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__DESCRIPTION:
                return description != null && !description.isEmpty();
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__DISPLAY_NAME:
                return displayName != null && !displayName.isEmpty();
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__ICON:
                return icon != null && !icon.isEmpty();
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__BASE_NAME:
                return baseName != null;
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__VAR:
                return var != null;
            case FacesConfigPackage.RESOURCE_BUNDLE_TYPE__ID:
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
        result.append(" (id: "); //$NON-NLS-1$
        result.append(id);
        result.append(')');
        return result.toString();
    }

} //ResourceBundleTypeImpl
