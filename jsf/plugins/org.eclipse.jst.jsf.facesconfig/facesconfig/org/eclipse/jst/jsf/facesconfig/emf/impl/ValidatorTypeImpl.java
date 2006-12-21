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
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorIdType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Validator Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorTypeImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorTypeImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorTypeImpl#getValidatorId <em>Validator Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorTypeImpl#getValidatorClass <em>Validator Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorTypeImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorTypeImpl#getValidatorExtension <em>Validator Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValidatorTypeImpl extends EObjectImpl implements ValidatorType {
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
     * The cached value of the '{@link #getValidatorId() <em>Validator Id</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getValidatorId()
     * @generated
     * @ordered
     */
	protected ValidatorIdType validatorId = null;

    /**
     * The cached value of the '{@link #getValidatorClass() <em>Validator Class</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getValidatorClass()
     * @generated
     * @ordered
     */
	protected ValidatorClassType validatorClass = null;

    /**
     * The cached value of the '{@link #getAttribute() <em>Attribute</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAttribute()
     * @generated
     * @ordered
     */
	protected EList attribute = null;

    /**
     * The cached value of the '{@link #getProperty() <em>Property</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getProperty()
     * @generated
     * @ordered
     */
	protected EList property = null;

    /**
     * The cached value of the '{@link #getValidatorExtension() <em>Validator Extension</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValidatorExtension()
     * @generated
     * @ordered
     */
    protected EList validatorExtension = null;

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
	protected ValidatorTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return FacesConfigPackage.Literals.VALIDATOR_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getDescription() {
        if (description == null) {
            description = new EObjectContainmentEList(DescriptionType.class, this, FacesConfigPackage.VALIDATOR_TYPE__DESCRIPTION);
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
            displayName = new EObjectContainmentEList(DisplayNameType.class, this, FacesConfigPackage.VALIDATOR_TYPE__DISPLAY_NAME);
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
            icon = new EObjectContainmentEList(IconType.class, this, FacesConfigPackage.VALIDATOR_TYPE__ICON);
        }
        return icon;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValidatorIdType getValidatorId() {
        return validatorId;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValidatorId(ValidatorIdType newValidatorId, NotificationChain msgs) {
        ValidatorIdType oldValidatorId = validatorId;
        validatorId = newValidatorId;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_ID, oldValidatorId, newValidatorId);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setValidatorId(ValidatorIdType newValidatorId) {
        if (newValidatorId != validatorId) {
            NotificationChain msgs = null;
            if (validatorId != null)
                msgs = ((InternalEObject)validatorId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_ID, null, msgs);
            if (newValidatorId != null)
                msgs = ((InternalEObject)newValidatorId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_ID, null, msgs);
            msgs = basicSetValidatorId(newValidatorId, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_ID, newValidatorId, newValidatorId));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ValidatorClassType getValidatorClass() {
        return validatorClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValidatorClass(ValidatorClassType newValidatorClass, NotificationChain msgs) {
        ValidatorClassType oldValidatorClass = validatorClass;
        validatorClass = newValidatorClass;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_CLASS, oldValidatorClass, newValidatorClass);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setValidatorClass(ValidatorClassType newValidatorClass) {
        if (newValidatorClass != validatorClass) {
            NotificationChain msgs = null;
            if (validatorClass != null)
                msgs = ((InternalEObject)validatorClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_CLASS, null, msgs);
            if (newValidatorClass != null)
                msgs = ((InternalEObject)newValidatorClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_CLASS, null, msgs);
            msgs = basicSetValidatorClass(newValidatorClass, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_CLASS, newValidatorClass, newValidatorClass));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getAttribute() {
        if (attribute == null) {
            attribute = new EObjectContainmentEList(AttributeType.class, this, FacesConfigPackage.VALIDATOR_TYPE__ATTRIBUTE);
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
            property = new EObjectContainmentEList(PropertyType.class, this, FacesConfigPackage.VALIDATOR_TYPE__PROPERTY);
        }
        return property;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getValidatorExtension() {
        if (validatorExtension == null) {
            validatorExtension = new EObjectContainmentEList(ValidatorExtensionType.class, this, FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_EXTENSION);
        }
        return validatorExtension;
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
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.VALIDATOR_TYPE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FacesConfigPackage.VALIDATOR_TYPE__DESCRIPTION:
                return ((InternalEList)getDescription()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.VALIDATOR_TYPE__DISPLAY_NAME:
                return ((InternalEList)getDisplayName()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.VALIDATOR_TYPE__ICON:
                return ((InternalEList)getIcon()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_ID:
                return basicSetValidatorId(null, msgs);
            case FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_CLASS:
                return basicSetValidatorClass(null, msgs);
            case FacesConfigPackage.VALIDATOR_TYPE__ATTRIBUTE:
                return ((InternalEList)getAttribute()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.VALIDATOR_TYPE__PROPERTY:
                return ((InternalEList)getProperty()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_EXTENSION:
                return ((InternalEList)getValidatorExtension()).basicRemove(otherEnd, msgs);
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
            case FacesConfigPackage.VALIDATOR_TYPE__DESCRIPTION:
                return getDescription();
            case FacesConfigPackage.VALIDATOR_TYPE__DISPLAY_NAME:
                return getDisplayName();
            case FacesConfigPackage.VALIDATOR_TYPE__ICON:
                return getIcon();
            case FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_ID:
                return getValidatorId();
            case FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_CLASS:
                return getValidatorClass();
            case FacesConfigPackage.VALIDATOR_TYPE__ATTRIBUTE:
                return getAttribute();
            case FacesConfigPackage.VALIDATOR_TYPE__PROPERTY:
                return getProperty();
            case FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_EXTENSION:
                return getValidatorExtension();
            case FacesConfigPackage.VALIDATOR_TYPE__ID:
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
            case FacesConfigPackage.VALIDATOR_TYPE__DESCRIPTION:
                getDescription().clear();
                getDescription().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.VALIDATOR_TYPE__DISPLAY_NAME:
                getDisplayName().clear();
                getDisplayName().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.VALIDATOR_TYPE__ICON:
                getIcon().clear();
                getIcon().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_ID:
                setValidatorId((ValidatorIdType)newValue);
                return;
            case FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_CLASS:
                setValidatorClass((ValidatorClassType)newValue);
                return;
            case FacesConfigPackage.VALIDATOR_TYPE__ATTRIBUTE:
                getAttribute().clear();
                getAttribute().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.VALIDATOR_TYPE__PROPERTY:
                getProperty().clear();
                getProperty().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_EXTENSION:
                getValidatorExtension().clear();
                getValidatorExtension().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.VALIDATOR_TYPE__ID:
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
            case FacesConfigPackage.VALIDATOR_TYPE__DESCRIPTION:
                getDescription().clear();
                return;
            case FacesConfigPackage.VALIDATOR_TYPE__DISPLAY_NAME:
                getDisplayName().clear();
                return;
            case FacesConfigPackage.VALIDATOR_TYPE__ICON:
                getIcon().clear();
                return;
            case FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_ID:
                setValidatorId((ValidatorIdType)null);
                return;
            case FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_CLASS:
                setValidatorClass((ValidatorClassType)null);
                return;
            case FacesConfigPackage.VALIDATOR_TYPE__ATTRIBUTE:
                getAttribute().clear();
                return;
            case FacesConfigPackage.VALIDATOR_TYPE__PROPERTY:
                getProperty().clear();
                return;
            case FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_EXTENSION:
                getValidatorExtension().clear();
                return;
            case FacesConfigPackage.VALIDATOR_TYPE__ID:
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
            case FacesConfigPackage.VALIDATOR_TYPE__DESCRIPTION:
                return description != null && !description.isEmpty();
            case FacesConfigPackage.VALIDATOR_TYPE__DISPLAY_NAME:
                return displayName != null && !displayName.isEmpty();
            case FacesConfigPackage.VALIDATOR_TYPE__ICON:
                return icon != null && !icon.isEmpty();
            case FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_ID:
                return validatorId != null;
            case FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_CLASS:
                return validatorClass != null;
            case FacesConfigPackage.VALIDATOR_TYPE__ATTRIBUTE:
                return attribute != null && !attribute.isEmpty();
            case FacesConfigPackage.VALIDATOR_TYPE__PROPERTY:
                return property != null && !property.isEmpty();
            case FacesConfigPackage.VALIDATOR_TYPE__VALIDATOR_EXTENSION:
                return validatorExtension != null && !validatorExtension.isEmpty();
            case FacesConfigPackage.VALIDATOR_TYPE__ID:
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

} //ValidatorTypeImpl
