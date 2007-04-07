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
import org.eclipse.jst.jsf.facesconfig.emf.DefaultValueType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyClassType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyTypeImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyTypeImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyTypeImpl#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyTypeImpl#getPropertyClass <em>Property Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyTypeImpl#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyTypeImpl#getSuggestedValue <em>Suggested Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyTypeImpl#getPropertyExtension <em>Property Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PropertyTypeImpl extends EObjectImpl implements PropertyType {
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
     * The cached value of the '{@link #getPropertyName() <em>Property Name</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPropertyName()
     * @generated
     * @ordered
     */
	protected PropertyNameType propertyName = null;

    /**
     * The cached value of the '{@link #getPropertyClass() <em>Property Class</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPropertyClass()
     * @generated
     * @ordered
     */
	protected PropertyClassType propertyClass = null;

    /**
     * The cached value of the '{@link #getDefaultValue() <em>Default Value</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefaultValue()
     * @generated
     * @ordered
     */
	protected DefaultValueType defaultValue = null;

    /**
     * The cached value of the '{@link #getSuggestedValue() <em>Suggested Value</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSuggestedValue()
     * @generated
     * @ordered
     */
	protected SuggestedValueType suggestedValue = null;

    /**
     * The cached value of the '{@link #getPropertyExtension() <em>Property Extension</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPropertyExtension()
     * @generated
     * @ordered
     */
	protected EList propertyExtension = null;

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
	protected PropertyTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return FacesConfigPackage.Literals.PROPERTY_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getDescription() {
        if (description == null) {
            description = new EObjectContainmentEList(DescriptionType.class, this, FacesConfigPackage.PROPERTY_TYPE__DESCRIPTION);
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
            displayName = new EObjectContainmentEList(DisplayNameType.class, this, FacesConfigPackage.PROPERTY_TYPE__DISPLAY_NAME);
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
            icon = new EObjectContainmentEList(IconType.class, this, FacesConfigPackage.PROPERTY_TYPE__ICON);
        }
        return icon;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PropertyNameType getPropertyName() {
        return propertyName;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetPropertyName(PropertyNameType newPropertyName, NotificationChain msgs) {
        PropertyNameType oldPropertyName = propertyName;
        propertyName = newPropertyName;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.PROPERTY_TYPE__PROPERTY_NAME, oldPropertyName, newPropertyName);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setPropertyName(PropertyNameType newPropertyName) {
        if (newPropertyName != propertyName) {
            NotificationChain msgs = null;
            if (propertyName != null)
                msgs = ((InternalEObject)propertyName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.PROPERTY_TYPE__PROPERTY_NAME, null, msgs);
            if (newPropertyName != null)
                msgs = ((InternalEObject)newPropertyName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.PROPERTY_TYPE__PROPERTY_NAME, null, msgs);
            msgs = basicSetPropertyName(newPropertyName, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.PROPERTY_TYPE__PROPERTY_NAME, newPropertyName, newPropertyName));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PropertyClassType getPropertyClass() {
        return propertyClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetPropertyClass(PropertyClassType newPropertyClass, NotificationChain msgs) {
        PropertyClassType oldPropertyClass = propertyClass;
        propertyClass = newPropertyClass;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.PROPERTY_TYPE__PROPERTY_CLASS, oldPropertyClass, newPropertyClass);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setPropertyClass(PropertyClassType newPropertyClass) {
        if (newPropertyClass != propertyClass) {
            NotificationChain msgs = null;
            if (propertyClass != null)
                msgs = ((InternalEObject)propertyClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.PROPERTY_TYPE__PROPERTY_CLASS, null, msgs);
            if (newPropertyClass != null)
                msgs = ((InternalEObject)newPropertyClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.PROPERTY_TYPE__PROPERTY_CLASS, null, msgs);
            msgs = basicSetPropertyClass(newPropertyClass, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.PROPERTY_TYPE__PROPERTY_CLASS, newPropertyClass, newPropertyClass));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public DefaultValueType getDefaultValue() {
        return defaultValue;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDefaultValue(DefaultValueType newDefaultValue, NotificationChain msgs) {
        DefaultValueType oldDefaultValue = defaultValue;
        defaultValue = newDefaultValue;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.PROPERTY_TYPE__DEFAULT_VALUE, oldDefaultValue, newDefaultValue);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setDefaultValue(DefaultValueType newDefaultValue) {
        if (newDefaultValue != defaultValue) {
            NotificationChain msgs = null;
            if (defaultValue != null)
                msgs = ((InternalEObject)defaultValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.PROPERTY_TYPE__DEFAULT_VALUE, null, msgs);
            if (newDefaultValue != null)
                msgs = ((InternalEObject)newDefaultValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.PROPERTY_TYPE__DEFAULT_VALUE, null, msgs);
            msgs = basicSetDefaultValue(newDefaultValue, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.PROPERTY_TYPE__DEFAULT_VALUE, newDefaultValue, newDefaultValue));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SuggestedValueType getSuggestedValue() {
        return suggestedValue;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetSuggestedValue(SuggestedValueType newSuggestedValue, NotificationChain msgs) {
        SuggestedValueType oldSuggestedValue = suggestedValue;
        suggestedValue = newSuggestedValue;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.PROPERTY_TYPE__SUGGESTED_VALUE, oldSuggestedValue, newSuggestedValue);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSuggestedValue(SuggestedValueType newSuggestedValue) {
        if (newSuggestedValue != suggestedValue) {
            NotificationChain msgs = null;
            if (suggestedValue != null)
                msgs = ((InternalEObject)suggestedValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.PROPERTY_TYPE__SUGGESTED_VALUE, null, msgs);
            if (newSuggestedValue != null)
                msgs = ((InternalEObject)newSuggestedValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.PROPERTY_TYPE__SUGGESTED_VALUE, null, msgs);
            msgs = basicSetSuggestedValue(newSuggestedValue, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.PROPERTY_TYPE__SUGGESTED_VALUE, newSuggestedValue, newSuggestedValue));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getPropertyExtension() {
        if (propertyExtension == null) {
            propertyExtension = new EObjectContainmentEList(PropertyExtensionType.class, this, FacesConfigPackage.PROPERTY_TYPE__PROPERTY_EXTENSION);
        }
        return propertyExtension;
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
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.PROPERTY_TYPE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FacesConfigPackage.PROPERTY_TYPE__DESCRIPTION:
                return ((InternalEList)getDescription()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.PROPERTY_TYPE__DISPLAY_NAME:
                return ((InternalEList)getDisplayName()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.PROPERTY_TYPE__ICON:
                return ((InternalEList)getIcon()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.PROPERTY_TYPE__PROPERTY_NAME:
                return basicSetPropertyName(null, msgs);
            case FacesConfigPackage.PROPERTY_TYPE__PROPERTY_CLASS:
                return basicSetPropertyClass(null, msgs);
            case FacesConfigPackage.PROPERTY_TYPE__DEFAULT_VALUE:
                return basicSetDefaultValue(null, msgs);
            case FacesConfigPackage.PROPERTY_TYPE__SUGGESTED_VALUE:
                return basicSetSuggestedValue(null, msgs);
            case FacesConfigPackage.PROPERTY_TYPE__PROPERTY_EXTENSION:
                return ((InternalEList)getPropertyExtension()).basicRemove(otherEnd, msgs);
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
            case FacesConfigPackage.PROPERTY_TYPE__DESCRIPTION:
                return getDescription();
            case FacesConfigPackage.PROPERTY_TYPE__DISPLAY_NAME:
                return getDisplayName();
            case FacesConfigPackage.PROPERTY_TYPE__ICON:
                return getIcon();
            case FacesConfigPackage.PROPERTY_TYPE__PROPERTY_NAME:
                return getPropertyName();
            case FacesConfigPackage.PROPERTY_TYPE__PROPERTY_CLASS:
                return getPropertyClass();
            case FacesConfigPackage.PROPERTY_TYPE__DEFAULT_VALUE:
                return getDefaultValue();
            case FacesConfigPackage.PROPERTY_TYPE__SUGGESTED_VALUE:
                return getSuggestedValue();
            case FacesConfigPackage.PROPERTY_TYPE__PROPERTY_EXTENSION:
                return getPropertyExtension();
            case FacesConfigPackage.PROPERTY_TYPE__ID:
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
            case FacesConfigPackage.PROPERTY_TYPE__DESCRIPTION:
                getDescription().clear();
                getDescription().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.PROPERTY_TYPE__DISPLAY_NAME:
                getDisplayName().clear();
                getDisplayName().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.PROPERTY_TYPE__ICON:
                getIcon().clear();
                getIcon().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.PROPERTY_TYPE__PROPERTY_NAME:
                setPropertyName((PropertyNameType)newValue);
                return;
            case FacesConfigPackage.PROPERTY_TYPE__PROPERTY_CLASS:
                setPropertyClass((PropertyClassType)newValue);
                return;
            case FacesConfigPackage.PROPERTY_TYPE__DEFAULT_VALUE:
                setDefaultValue((DefaultValueType)newValue);
                return;
            case FacesConfigPackage.PROPERTY_TYPE__SUGGESTED_VALUE:
                setSuggestedValue((SuggestedValueType)newValue);
                return;
            case FacesConfigPackage.PROPERTY_TYPE__PROPERTY_EXTENSION:
                getPropertyExtension().clear();
                getPropertyExtension().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.PROPERTY_TYPE__ID:
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
            case FacesConfigPackage.PROPERTY_TYPE__DESCRIPTION:
                getDescription().clear();
                return;
            case FacesConfigPackage.PROPERTY_TYPE__DISPLAY_NAME:
                getDisplayName().clear();
                return;
            case FacesConfigPackage.PROPERTY_TYPE__ICON:
                getIcon().clear();
                return;
            case FacesConfigPackage.PROPERTY_TYPE__PROPERTY_NAME:
                setPropertyName((PropertyNameType)null);
                return;
            case FacesConfigPackage.PROPERTY_TYPE__PROPERTY_CLASS:
                setPropertyClass((PropertyClassType)null);
                return;
            case FacesConfigPackage.PROPERTY_TYPE__DEFAULT_VALUE:
                setDefaultValue((DefaultValueType)null);
                return;
            case FacesConfigPackage.PROPERTY_TYPE__SUGGESTED_VALUE:
                setSuggestedValue((SuggestedValueType)null);
                return;
            case FacesConfigPackage.PROPERTY_TYPE__PROPERTY_EXTENSION:
                getPropertyExtension().clear();
                return;
            case FacesConfigPackage.PROPERTY_TYPE__ID:
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
            case FacesConfigPackage.PROPERTY_TYPE__DESCRIPTION:
                return description != null && !description.isEmpty();
            case FacesConfigPackage.PROPERTY_TYPE__DISPLAY_NAME:
                return displayName != null && !displayName.isEmpty();
            case FacesConfigPackage.PROPERTY_TYPE__ICON:
                return icon != null && !icon.isEmpty();
            case FacesConfigPackage.PROPERTY_TYPE__PROPERTY_NAME:
                return propertyName != null;
            case FacesConfigPackage.PROPERTY_TYPE__PROPERTY_CLASS:
                return propertyClass != null;
            case FacesConfigPackage.PROPERTY_TYPE__DEFAULT_VALUE:
                return defaultValue != null;
            case FacesConfigPackage.PROPERTY_TYPE__SUGGESTED_VALUE:
                return suggestedValue != null;
            case FacesConfigPackage.PROPERTY_TYPE__PROPERTY_EXTENSION:
                return propertyExtension != null && !propertyExtension.isEmpty();
            case FacesConfigPackage.PROPERTY_TYPE__ID:
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

} //PropertyTypeImpl
