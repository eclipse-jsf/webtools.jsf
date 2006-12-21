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
import org.eclipse.jst.jsf.facesconfig.emf.ConverterClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterForClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterIdType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Converter Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterTypeImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterTypeImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterTypeImpl#getConverterId <em>Converter Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterTypeImpl#getConverterForClass <em>Converter For Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterTypeImpl#getConverterClass <em>Converter Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterTypeImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterTypeImpl#getConverterExtension <em>Converter Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConverterTypeImpl extends EObjectImpl implements ConverterType {
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
     * The cached value of the '{@link #getConverterId() <em>Converter Id</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getConverterId()
     * @generated
     * @ordered
     */
	protected ConverterIdType converterId = null;

    /**
     * The cached value of the '{@link #getConverterForClass() <em>Converter For Class</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getConverterForClass()
     * @generated
     * @ordered
     */
	protected ConverterForClassType converterForClass = null;

    /**
     * The cached value of the '{@link #getConverterClass() <em>Converter Class</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getConverterClass()
     * @generated
     * @ordered
     */
	protected ConverterClassType converterClass = null;

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
     * The cached value of the '{@link #getConverterExtension() <em>Converter Extension</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConverterExtension()
     * @generated
     * @ordered
     */
    protected EList converterExtension = null;

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
	protected ConverterTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return FacesConfigPackage.Literals.CONVERTER_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getDescription() {
        if (description == null) {
            description = new EObjectContainmentEList(DescriptionType.class, this, FacesConfigPackage.CONVERTER_TYPE__DESCRIPTION);
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
            displayName = new EObjectContainmentEList(DisplayNameType.class, this, FacesConfigPackage.CONVERTER_TYPE__DISPLAY_NAME);
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
            icon = new EObjectContainmentEList(IconType.class, this, FacesConfigPackage.CONVERTER_TYPE__ICON);
        }
        return icon;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ConverterIdType getConverterId() {
        return converterId;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetConverterId(ConverterIdType newConverterId, NotificationChain msgs) {
        ConverterIdType oldConverterId = converterId;
        converterId = newConverterId;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.CONVERTER_TYPE__CONVERTER_ID, oldConverterId, newConverterId);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setConverterId(ConverterIdType newConverterId) {
        if (newConverterId != converterId) {
            NotificationChain msgs = null;
            if (converterId != null)
                msgs = ((InternalEObject)converterId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.CONVERTER_TYPE__CONVERTER_ID, null, msgs);
            if (newConverterId != null)
                msgs = ((InternalEObject)newConverterId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.CONVERTER_TYPE__CONVERTER_ID, null, msgs);
            msgs = basicSetConverterId(newConverterId, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.CONVERTER_TYPE__CONVERTER_ID, newConverterId, newConverterId));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ConverterForClassType getConverterForClass() {
        return converterForClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetConverterForClass(ConverterForClassType newConverterForClass, NotificationChain msgs) {
        ConverterForClassType oldConverterForClass = converterForClass;
        converterForClass = newConverterForClass;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.CONVERTER_TYPE__CONVERTER_FOR_CLASS, oldConverterForClass, newConverterForClass);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setConverterForClass(ConverterForClassType newConverterForClass) {
        if (newConverterForClass != converterForClass) {
            NotificationChain msgs = null;
            if (converterForClass != null)
                msgs = ((InternalEObject)converterForClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.CONVERTER_TYPE__CONVERTER_FOR_CLASS, null, msgs);
            if (newConverterForClass != null)
                msgs = ((InternalEObject)newConverterForClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.CONVERTER_TYPE__CONVERTER_FOR_CLASS, null, msgs);
            msgs = basicSetConverterForClass(newConverterForClass, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.CONVERTER_TYPE__CONVERTER_FOR_CLASS, newConverterForClass, newConverterForClass));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ConverterClassType getConverterClass() {
        return converterClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetConverterClass(ConverterClassType newConverterClass, NotificationChain msgs) {
        ConverterClassType oldConverterClass = converterClass;
        converterClass = newConverterClass;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.CONVERTER_TYPE__CONVERTER_CLASS, oldConverterClass, newConverterClass);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setConverterClass(ConverterClassType newConverterClass) {
        if (newConverterClass != converterClass) {
            NotificationChain msgs = null;
            if (converterClass != null)
                msgs = ((InternalEObject)converterClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.CONVERTER_TYPE__CONVERTER_CLASS, null, msgs);
            if (newConverterClass != null)
                msgs = ((InternalEObject)newConverterClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.CONVERTER_TYPE__CONVERTER_CLASS, null, msgs);
            msgs = basicSetConverterClass(newConverterClass, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.CONVERTER_TYPE__CONVERTER_CLASS, newConverterClass, newConverterClass));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getAttribute() {
        if (attribute == null) {
            attribute = new EObjectContainmentEList(AttributeType.class, this, FacesConfigPackage.CONVERTER_TYPE__ATTRIBUTE);
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
            property = new EObjectContainmentEList(PropertyType.class, this, FacesConfigPackage.CONVERTER_TYPE__PROPERTY);
        }
        return property;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getConverterExtension() {
        if (converterExtension == null) {
            converterExtension = new EObjectContainmentEList(ConverterExtensionType.class, this, FacesConfigPackage.CONVERTER_TYPE__CONVERTER_EXTENSION);
        }
        return converterExtension;
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
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.CONVERTER_TYPE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FacesConfigPackage.CONVERTER_TYPE__DESCRIPTION:
                return ((InternalEList)getDescription()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.CONVERTER_TYPE__DISPLAY_NAME:
                return ((InternalEList)getDisplayName()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.CONVERTER_TYPE__ICON:
                return ((InternalEList)getIcon()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_ID:
                return basicSetConverterId(null, msgs);
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_FOR_CLASS:
                return basicSetConverterForClass(null, msgs);
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_CLASS:
                return basicSetConverterClass(null, msgs);
            case FacesConfigPackage.CONVERTER_TYPE__ATTRIBUTE:
                return ((InternalEList)getAttribute()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.CONVERTER_TYPE__PROPERTY:
                return ((InternalEList)getProperty()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_EXTENSION:
                return ((InternalEList)getConverterExtension()).basicRemove(otherEnd, msgs);
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
            case FacesConfigPackage.CONVERTER_TYPE__DESCRIPTION:
                return getDescription();
            case FacesConfigPackage.CONVERTER_TYPE__DISPLAY_NAME:
                return getDisplayName();
            case FacesConfigPackage.CONVERTER_TYPE__ICON:
                return getIcon();
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_ID:
                return getConverterId();
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_FOR_CLASS:
                return getConverterForClass();
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_CLASS:
                return getConverterClass();
            case FacesConfigPackage.CONVERTER_TYPE__ATTRIBUTE:
                return getAttribute();
            case FacesConfigPackage.CONVERTER_TYPE__PROPERTY:
                return getProperty();
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_EXTENSION:
                return getConverterExtension();
            case FacesConfigPackage.CONVERTER_TYPE__ID:
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
            case FacesConfigPackage.CONVERTER_TYPE__DESCRIPTION:
                getDescription().clear();
                getDescription().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.CONVERTER_TYPE__DISPLAY_NAME:
                getDisplayName().clear();
                getDisplayName().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.CONVERTER_TYPE__ICON:
                getIcon().clear();
                getIcon().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_ID:
                setConverterId((ConverterIdType)newValue);
                return;
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_FOR_CLASS:
                setConverterForClass((ConverterForClassType)newValue);
                return;
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_CLASS:
                setConverterClass((ConverterClassType)newValue);
                return;
            case FacesConfigPackage.CONVERTER_TYPE__ATTRIBUTE:
                getAttribute().clear();
                getAttribute().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.CONVERTER_TYPE__PROPERTY:
                getProperty().clear();
                getProperty().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_EXTENSION:
                getConverterExtension().clear();
                getConverterExtension().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.CONVERTER_TYPE__ID:
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
            case FacesConfigPackage.CONVERTER_TYPE__DESCRIPTION:
                getDescription().clear();
                return;
            case FacesConfigPackage.CONVERTER_TYPE__DISPLAY_NAME:
                getDisplayName().clear();
                return;
            case FacesConfigPackage.CONVERTER_TYPE__ICON:
                getIcon().clear();
                return;
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_ID:
                setConverterId((ConverterIdType)null);
                return;
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_FOR_CLASS:
                setConverterForClass((ConverterForClassType)null);
                return;
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_CLASS:
                setConverterClass((ConverterClassType)null);
                return;
            case FacesConfigPackage.CONVERTER_TYPE__ATTRIBUTE:
                getAttribute().clear();
                return;
            case FacesConfigPackage.CONVERTER_TYPE__PROPERTY:
                getProperty().clear();
                return;
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_EXTENSION:
                getConverterExtension().clear();
                return;
            case FacesConfigPackage.CONVERTER_TYPE__ID:
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
            case FacesConfigPackage.CONVERTER_TYPE__DESCRIPTION:
                return description != null && !description.isEmpty();
            case FacesConfigPackage.CONVERTER_TYPE__DISPLAY_NAME:
                return displayName != null && !displayName.isEmpty();
            case FacesConfigPackage.CONVERTER_TYPE__ICON:
                return icon != null && !icon.isEmpty();
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_ID:
                return converterId != null;
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_FOR_CLASS:
                return converterForClass != null;
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_CLASS:
                return converterClass != null;
            case FacesConfigPackage.CONVERTER_TYPE__ATTRIBUTE:
                return attribute != null && !attribute.isEmpty();
            case FacesConfigPackage.CONVERTER_TYPE__PROPERTY:
                return property != null && !property.isEmpty();
            case FacesConfigPackage.CONVERTER_TYPE__CONVERTER_EXTENSION:
                return converterExtension != null && !converterExtension.isEmpty();
            case FacesConfigPackage.CONVERTER_TYPE__ID:
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

} //ConverterTypeImpl
