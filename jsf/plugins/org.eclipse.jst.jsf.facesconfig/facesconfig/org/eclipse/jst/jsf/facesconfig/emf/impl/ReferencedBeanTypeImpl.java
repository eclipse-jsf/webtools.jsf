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
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Referenced Bean Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanTypeImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanTypeImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanTypeImpl#getReferencedBeanName <em>Referenced Bean Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanTypeImpl#getReferencedBeanClass <em>Referenced Bean Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferencedBeanTypeImpl extends EObjectImpl implements ReferencedBeanType {
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
     * The cached value of the '{@link #getReferencedBeanName() <em>Referenced Bean Name</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getReferencedBeanName()
     * @generated
     * @ordered
     */
	protected ReferencedBeanNameType referencedBeanName = null;

    /**
     * The cached value of the '{@link #getReferencedBeanClass() <em>Referenced Bean Class</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getReferencedBeanClass()
     * @generated
     * @ordered
     */
	protected ReferencedBeanClassType referencedBeanClass = null;

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
	protected ReferencedBeanTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return FacesConfigPackage.Literals.REFERENCED_BEAN_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getDescription() {
        if (description == null) {
            description = new EObjectContainmentEList(DescriptionType.class, this, FacesConfigPackage.REFERENCED_BEAN_TYPE__DESCRIPTION);
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
            displayName = new EObjectContainmentEList(DisplayNameType.class, this, FacesConfigPackage.REFERENCED_BEAN_TYPE__DISPLAY_NAME);
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
            icon = new EObjectContainmentEList(IconType.class, this, FacesConfigPackage.REFERENCED_BEAN_TYPE__ICON);
        }
        return icon;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ReferencedBeanNameType getReferencedBeanName() {
        return referencedBeanName;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetReferencedBeanName(ReferencedBeanNameType newReferencedBeanName, NotificationChain msgs) {
        ReferencedBeanNameType oldReferencedBeanName = referencedBeanName;
        referencedBeanName = newReferencedBeanName;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_NAME, oldReferencedBeanName, newReferencedBeanName);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setReferencedBeanName(ReferencedBeanNameType newReferencedBeanName) {
        if (newReferencedBeanName != referencedBeanName) {
            NotificationChain msgs = null;
            if (referencedBeanName != null)
                msgs = ((InternalEObject)referencedBeanName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_NAME, null, msgs);
            if (newReferencedBeanName != null)
                msgs = ((InternalEObject)newReferencedBeanName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_NAME, null, msgs);
            msgs = basicSetReferencedBeanName(newReferencedBeanName, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_NAME, newReferencedBeanName, newReferencedBeanName));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ReferencedBeanClassType getReferencedBeanClass() {
        return referencedBeanClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetReferencedBeanClass(ReferencedBeanClassType newReferencedBeanClass, NotificationChain msgs) {
        ReferencedBeanClassType oldReferencedBeanClass = referencedBeanClass;
        referencedBeanClass = newReferencedBeanClass;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_CLASS, oldReferencedBeanClass, newReferencedBeanClass);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setReferencedBeanClass(ReferencedBeanClassType newReferencedBeanClass) {
        if (newReferencedBeanClass != referencedBeanClass) {
            NotificationChain msgs = null;
            if (referencedBeanClass != null)
                msgs = ((InternalEObject)referencedBeanClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_CLASS, null, msgs);
            if (newReferencedBeanClass != null)
                msgs = ((InternalEObject)newReferencedBeanClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_CLASS, null, msgs);
            msgs = basicSetReferencedBeanClass(newReferencedBeanClass, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_CLASS, newReferencedBeanClass, newReferencedBeanClass));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.REFERENCED_BEAN_TYPE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__DESCRIPTION:
                return ((InternalEList)getDescription()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__DISPLAY_NAME:
                return ((InternalEList)getDisplayName()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__ICON:
                return ((InternalEList)getIcon()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_NAME:
                return basicSetReferencedBeanName(null, msgs);
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_CLASS:
                return basicSetReferencedBeanClass(null, msgs);
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
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__DESCRIPTION:
                return getDescription();
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__DISPLAY_NAME:
                return getDisplayName();
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__ICON:
                return getIcon();
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_NAME:
                return getReferencedBeanName();
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_CLASS:
                return getReferencedBeanClass();
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__ID:
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
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__DESCRIPTION:
                getDescription().clear();
                getDescription().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__DISPLAY_NAME:
                getDisplayName().clear();
                getDisplayName().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__ICON:
                getIcon().clear();
                getIcon().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_NAME:
                setReferencedBeanName((ReferencedBeanNameType)newValue);
                return;
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_CLASS:
                setReferencedBeanClass((ReferencedBeanClassType)newValue);
                return;
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__ID:
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
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__DESCRIPTION:
                getDescription().clear();
                return;
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__DISPLAY_NAME:
                getDisplayName().clear();
                return;
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__ICON:
                getIcon().clear();
                return;
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_NAME:
                setReferencedBeanName((ReferencedBeanNameType)null);
                return;
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_CLASS:
                setReferencedBeanClass((ReferencedBeanClassType)null);
                return;
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__ID:
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
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__DESCRIPTION:
                return description != null && !description.isEmpty();
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__DISPLAY_NAME:
                return displayName != null && !displayName.isEmpty();
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__ICON:
                return icon != null && !icon.isEmpty();
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_NAME:
                return referencedBeanName != null;
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__REFERENCED_BEAN_CLASS:
                return referencedBeanClass != null;
            case FacesConfigPackage.REFERENCED_BEAN_TYPE__ID:
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

} //ReferencedBeanTypeImpl
