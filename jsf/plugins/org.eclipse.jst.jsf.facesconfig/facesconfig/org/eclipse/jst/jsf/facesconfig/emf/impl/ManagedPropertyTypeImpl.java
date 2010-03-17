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
import org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.NullValueType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyClassType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Managed Property Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedPropertyTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedPropertyTypeImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedPropertyTypeImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedPropertyTypeImpl#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedPropertyTypeImpl#getPropertyClass <em>Property Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedPropertyTypeImpl#getMapEntries <em>Map Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedPropertyTypeImpl#getNullValue <em>Null Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedPropertyTypeImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedPropertyTypeImpl#getListEntries <em>List Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedPropertyTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ManagedPropertyTypeImpl extends EObjectImpl implements ManagedPropertyType {
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
	 * The cached value of the '{@link #getPropertyName() <em>Property Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyName()
	 * @generated
	 * @ordered
	 */
	protected PropertyNameType propertyName;

    /**
	 * The cached value of the '{@link #getPropertyClass() <em>Property Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyClass()
	 * @generated
	 * @ordered
	 */
	protected PropertyClassType propertyClass;

    /**
	 * The cached value of the '{@link #getMapEntries() <em>Map Entries</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMapEntries()
	 * @generated
	 * @ordered
	 */
	protected MapEntriesType mapEntries;

    /**
	 * The cached value of the '{@link #getNullValue() <em>Null Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNullValue()
	 * @generated
	 * @ordered
	 */
	protected NullValueType nullValue;

    /**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected ValueType value;

    /**
	 * The cached value of the '{@link #getListEntries() <em>List Entries</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getListEntries()
	 * @generated
	 * @ordered
	 */
	protected ListEntriesType listEntries;

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
	protected ManagedPropertyTypeImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.Literals.MANAGED_PROPERTY_TYPE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescription() {
		if (description == null) {
			description = new EObjectContainmentEList(DescriptionType.class, this, FacesConfigPackage.MANAGED_PROPERTY_TYPE__DESCRIPTION);
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
			displayName = new EObjectContainmentEList(DisplayNameType.class, this, FacesConfigPackage.MANAGED_PROPERTY_TYPE__DISPLAY_NAME);
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
			icon = new EObjectContainmentEList(IconType.class, this, FacesConfigPackage.MANAGED_PROPERTY_TYPE__ICON);
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
     * @param newPropertyName 
     * @param msgs 
     * @return  the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPropertyName(PropertyNameType newPropertyName, NotificationChain msgs) {
		PropertyNameType oldPropertyName = propertyName;
		propertyName = newPropertyName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_NAME, oldPropertyName, newPropertyName);
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
				msgs = ((InternalEObject)propertyName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_NAME, null, msgs);
			if (newPropertyName != null)
				msgs = ((InternalEObject)newPropertyName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_NAME, null, msgs);
			msgs = basicSetPropertyName(newPropertyName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_NAME, newPropertyName, newPropertyName));
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
     * @param newPropertyClass 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPropertyClass(PropertyClassType newPropertyClass, NotificationChain msgs) {
		PropertyClassType oldPropertyClass = propertyClass;
		propertyClass = newPropertyClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_CLASS, oldPropertyClass, newPropertyClass);
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
				msgs = ((InternalEObject)propertyClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_CLASS, null, msgs);
			if (newPropertyClass != null)
				msgs = ((InternalEObject)newPropertyClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_CLASS, null, msgs);
			msgs = basicSetPropertyClass(newPropertyClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_CLASS, newPropertyClass, newPropertyClass));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapEntriesType getMapEntries() {
		return mapEntries;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newMapEntries 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMapEntries(MapEntriesType newMapEntries, NotificationChain msgs) {
		MapEntriesType oldMapEntries = mapEntries;
		mapEntries = newMapEntries;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_PROPERTY_TYPE__MAP_ENTRIES, oldMapEntries, newMapEntries);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMapEntries(MapEntriesType newMapEntries) {
		if (newMapEntries != mapEntries) {
			NotificationChain msgs = null;
			if (mapEntries != null)
				msgs = ((InternalEObject)mapEntries).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_PROPERTY_TYPE__MAP_ENTRIES, null, msgs);
			if (newMapEntries != null)
				msgs = ((InternalEObject)newMapEntries).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_PROPERTY_TYPE__MAP_ENTRIES, null, msgs);
			msgs = basicSetMapEntries(newMapEntries, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_PROPERTY_TYPE__MAP_ENTRIES, newMapEntries, newMapEntries));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NullValueType getNullValue() {
		return nullValue;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newNullValue 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNullValue(NullValueType newNullValue, NotificationChain msgs) {
		NullValueType oldNullValue = nullValue;
		nullValue = newNullValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_PROPERTY_TYPE__NULL_VALUE, oldNullValue, newNullValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNullValue(NullValueType newNullValue) {
		if (newNullValue != nullValue) {
			NotificationChain msgs = null;
			if (nullValue != null)
				msgs = ((InternalEObject)nullValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_PROPERTY_TYPE__NULL_VALUE, null, msgs);
			if (newNullValue != null)
				msgs = ((InternalEObject)newNullValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_PROPERTY_TYPE__NULL_VALUE, null, msgs);
			msgs = basicSetNullValue(newNullValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_PROPERTY_TYPE__NULL_VALUE, newNullValue, newNullValue));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueType getValue() {
		return value;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newValue 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValue(ValueType newValue, NotificationChain msgs) {
		ValueType oldValue = value;
		value = newValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_PROPERTY_TYPE__VALUE, oldValue, newValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(ValueType newValue) {
		if (newValue != value) {
			NotificationChain msgs = null;
			if (value != null)
				msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_PROPERTY_TYPE__VALUE, null, msgs);
			if (newValue != null)
				msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_PROPERTY_TYPE__VALUE, null, msgs);
			msgs = basicSetValue(newValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_PROPERTY_TYPE__VALUE, newValue, newValue));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListEntriesType getListEntries() {
		return listEntries;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newListEntries 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetListEntries(ListEntriesType newListEntries, NotificationChain msgs) {
		ListEntriesType oldListEntries = listEntries;
		listEntries = newListEntries;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_PROPERTY_TYPE__LIST_ENTRIES, oldListEntries, newListEntries);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setListEntries(ListEntriesType newListEntries) {
		if (newListEntries != listEntries) {
			NotificationChain msgs = null;
			if (listEntries != null)
				msgs = ((InternalEObject)listEntries).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_PROPERTY_TYPE__LIST_ENTRIES, null, msgs);
			if (newListEntries != null)
				msgs = ((InternalEObject)newListEntries).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_PROPERTY_TYPE__LIST_ENTRIES, null, msgs);
			msgs = basicSetListEntries(newListEntries, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_PROPERTY_TYPE__LIST_ENTRIES, newListEntries, newListEntries));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_PROPERTY_TYPE__ID, oldId, id));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__DESCRIPTION:
				return ((InternalEList)getDescription()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__DISPLAY_NAME:
				return ((InternalEList)getDisplayName()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__ICON:
				return ((InternalEList)getIcon()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_NAME:
				return basicSetPropertyName(null, msgs);
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_CLASS:
				return basicSetPropertyClass(null, msgs);
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__MAP_ENTRIES:
				return basicSetMapEntries(null, msgs);
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__NULL_VALUE:
				return basicSetNullValue(null, msgs);
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__VALUE:
				return basicSetValue(null, msgs);
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__LIST_ENTRIES:
				return basicSetListEntries(null, msgs);
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
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__DESCRIPTION:
				return getDescription();
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__DISPLAY_NAME:
				return getDisplayName();
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__ICON:
				return getIcon();
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_NAME:
				return getPropertyName();
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_CLASS:
				return getPropertyClass();
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__MAP_ENTRIES:
				return getMapEntries();
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__NULL_VALUE:
				return getNullValue();
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__VALUE:
				return getValue();
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__LIST_ENTRIES:
				return getListEntries();
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__ID:
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
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__DISPLAY_NAME:
				getDisplayName().clear();
				getDisplayName().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__ICON:
				getIcon().clear();
				getIcon().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_NAME:
				setPropertyName((PropertyNameType)newValue);
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_CLASS:
				setPropertyClass((PropertyClassType)newValue);
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__MAP_ENTRIES:
				setMapEntries((MapEntriesType)newValue);
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__NULL_VALUE:
				setNullValue((NullValueType)newValue);
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__VALUE:
				setValue((ValueType)newValue);
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__LIST_ENTRIES:
				setListEntries((ListEntriesType)newValue);
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__ID:
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
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__DESCRIPTION:
				getDescription().clear();
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__DISPLAY_NAME:
				getDisplayName().clear();
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__ICON:
				getIcon().clear();
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_NAME:
				setPropertyName((PropertyNameType)null);
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_CLASS:
				setPropertyClass((PropertyClassType)null);
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__MAP_ENTRIES:
				setMapEntries((MapEntriesType)null);
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__NULL_VALUE:
				setNullValue((NullValueType)null);
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__VALUE:
				setValue((ValueType)null);
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__LIST_ENTRIES:
				setListEntries((ListEntriesType)null);
				return;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__ID:
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
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__DESCRIPTION:
				return description != null && !description.isEmpty();
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__DISPLAY_NAME:
				return displayName != null && !displayName.isEmpty();
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__ICON:
				return icon != null && !icon.isEmpty();
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_NAME:
				return propertyName != null;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__PROPERTY_CLASS:
				return propertyClass != null;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__MAP_ENTRIES:
				return mapEntries != null;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__NULL_VALUE:
				return nullValue != null;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__VALUE:
				return value != null;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__LIST_ENTRIES:
				return listEntries != null;
			case FacesConfigPackage.MANAGED_PROPERTY_TYPE__ID:
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

} //ManagedPropertyTypeImpl
