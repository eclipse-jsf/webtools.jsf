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
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.KeyClassType;
import org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.MapEntryType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueClassType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Map Entries Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.MapEntriesTypeImpl#getKeyClass <em>Key Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.MapEntriesTypeImpl#getValueClass <em>Value Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.MapEntriesTypeImpl#getMapEntry <em>Map Entry</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.MapEntriesTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MapEntriesTypeImpl extends EObjectImpl implements MapEntriesType {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

    /**
	 * The cached value of the '{@link #getKeyClass() <em>Key Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeyClass()
	 * @generated
	 * @ordered
	 */
	protected KeyClassType keyClass;

    /**
	 * The cached value of the '{@link #getValueClass() <em>Value Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueClass()
	 * @generated
	 * @ordered
	 */
	protected ValueClassType valueClass;

    /**
	 * The cached value of the '{@link #getMapEntry() <em>Map Entry</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMapEntry()
	 * @generated
	 * @ordered
	 */
	protected EList mapEntry;

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
	protected MapEntriesTypeImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.Literals.MAP_ENTRIES_TYPE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KeyClassType getKeyClass() {
		return keyClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newKeyClass 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetKeyClass(KeyClassType newKeyClass, NotificationChain msgs) {
		KeyClassType oldKeyClass = keyClass;
		keyClass = newKeyClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MAP_ENTRIES_TYPE__KEY_CLASS, oldKeyClass, newKeyClass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKeyClass(KeyClassType newKeyClass) {
		if (newKeyClass != keyClass) {
			NotificationChain msgs = null;
			if (keyClass != null)
				msgs = ((InternalEObject)keyClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MAP_ENTRIES_TYPE__KEY_CLASS, null, msgs);
			if (newKeyClass != null)
				msgs = ((InternalEObject)newKeyClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MAP_ENTRIES_TYPE__KEY_CLASS, null, msgs);
			msgs = basicSetKeyClass(newKeyClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MAP_ENTRIES_TYPE__KEY_CLASS, newKeyClass, newKeyClass));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueClassType getValueClass() {
		return valueClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newValueClass 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValueClass(ValueClassType newValueClass, NotificationChain msgs) {
		ValueClassType oldValueClass = valueClass;
		valueClass = newValueClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MAP_ENTRIES_TYPE__VALUE_CLASS, oldValueClass, newValueClass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueClass(ValueClassType newValueClass) {
		if (newValueClass != valueClass) {
			NotificationChain msgs = null;
			if (valueClass != null)
				msgs = ((InternalEObject)valueClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MAP_ENTRIES_TYPE__VALUE_CLASS, null, msgs);
			if (newValueClass != null)
				msgs = ((InternalEObject)newValueClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MAP_ENTRIES_TYPE__VALUE_CLASS, null, msgs);
			msgs = basicSetValueClass(newValueClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MAP_ENTRIES_TYPE__VALUE_CLASS, newValueClass, newValueClass));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMapEntry() {
		if (mapEntry == null) {
			mapEntry = new EObjectContainmentEList(MapEntryType.class, this, FacesConfigPackage.MAP_ENTRIES_TYPE__MAP_ENTRY);
		}
		return mapEntry;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MAP_ENTRIES_TYPE__ID, oldId, id));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FacesConfigPackage.MAP_ENTRIES_TYPE__KEY_CLASS:
				return basicSetKeyClass(null, msgs);
			case FacesConfigPackage.MAP_ENTRIES_TYPE__VALUE_CLASS:
				return basicSetValueClass(null, msgs);
			case FacesConfigPackage.MAP_ENTRIES_TYPE__MAP_ENTRY:
				return ((InternalEList)getMapEntry()).basicRemove(otherEnd, msgs);
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
			case FacesConfigPackage.MAP_ENTRIES_TYPE__KEY_CLASS:
				return getKeyClass();
			case FacesConfigPackage.MAP_ENTRIES_TYPE__VALUE_CLASS:
				return getValueClass();
			case FacesConfigPackage.MAP_ENTRIES_TYPE__MAP_ENTRY:
				return getMapEntry();
			case FacesConfigPackage.MAP_ENTRIES_TYPE__ID:
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
			case FacesConfigPackage.MAP_ENTRIES_TYPE__KEY_CLASS:
				setKeyClass((KeyClassType)newValue);
				return;
			case FacesConfigPackage.MAP_ENTRIES_TYPE__VALUE_CLASS:
				setValueClass((ValueClassType)newValue);
				return;
			case FacesConfigPackage.MAP_ENTRIES_TYPE__MAP_ENTRY:
				getMapEntry().clear();
				getMapEntry().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.MAP_ENTRIES_TYPE__ID:
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
			case FacesConfigPackage.MAP_ENTRIES_TYPE__KEY_CLASS:
				setKeyClass((KeyClassType)null);
				return;
			case FacesConfigPackage.MAP_ENTRIES_TYPE__VALUE_CLASS:
				setValueClass((ValueClassType)null);
				return;
			case FacesConfigPackage.MAP_ENTRIES_TYPE__MAP_ENTRY:
				getMapEntry().clear();
				return;
			case FacesConfigPackage.MAP_ENTRIES_TYPE__ID:
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
			case FacesConfigPackage.MAP_ENTRIES_TYPE__KEY_CLASS:
				return keyClass != null;
			case FacesConfigPackage.MAP_ENTRIES_TYPE__VALUE_CLASS:
				return valueClass != null;
			case FacesConfigPackage.MAP_ENTRIES_TYPE__MAP_ENTRY:
				return mapEntry != null && !mapEntry.isEmpty();
			case FacesConfigPackage.MAP_ENTRIES_TYPE__ID:
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

} //MapEntriesTypeImpl
