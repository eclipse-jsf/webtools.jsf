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
import org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.NullValueType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>List Entries Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ListEntriesTypeImpl#getValueClass <em>Value Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ListEntriesTypeImpl#getNullValue <em>Null Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ListEntriesTypeImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ListEntriesTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ListEntriesTypeImpl extends EObjectImpl implements ListEntriesType {
    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

    /**
     * The cached value of the '{@link #getValueClass() <em>Value Class</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getValueClass()
     * @generated
     * @ordered
     */
	protected ValueClassType valueClass = null;

    /**
     * The cached value of the '{@link #getNullValue() <em>Null Value</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getNullValue()
     * @generated
     * @ordered
     */
	protected EList nullValue = null;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
	protected EList value = null;

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
	protected ListEntriesTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return FacesConfigPackage.Literals.LIST_ENTRIES_TYPE;
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
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetValueClass(ValueClassType newValueClass, NotificationChain msgs) {
        ValueClassType oldValueClass = valueClass;
        valueClass = newValueClass;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.LIST_ENTRIES_TYPE__VALUE_CLASS, oldValueClass, newValueClass);
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
                msgs = ((InternalEObject)valueClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.LIST_ENTRIES_TYPE__VALUE_CLASS, null, msgs);
            if (newValueClass != null)
                msgs = ((InternalEObject)newValueClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.LIST_ENTRIES_TYPE__VALUE_CLASS, null, msgs);
            msgs = basicSetValueClass(newValueClass, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.LIST_ENTRIES_TYPE__VALUE_CLASS, newValueClass, newValueClass));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getNullValue() {
        if (nullValue == null) {
            nullValue = new EObjectContainmentEList(NullValueType.class, this, FacesConfigPackage.LIST_ENTRIES_TYPE__NULL_VALUE);
        }
        return nullValue;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getValue() {
        if (value == null) {
            value = new EObjectContainmentEList(ValueType.class, this, FacesConfigPackage.LIST_ENTRIES_TYPE__VALUE);
        }
        return value;
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
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.LIST_ENTRIES_TYPE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FacesConfigPackage.LIST_ENTRIES_TYPE__VALUE_CLASS:
                return basicSetValueClass(null, msgs);
            case FacesConfigPackage.LIST_ENTRIES_TYPE__NULL_VALUE:
                return ((InternalEList)getNullValue()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.LIST_ENTRIES_TYPE__VALUE:
                return ((InternalEList)getValue()).basicRemove(otherEnd, msgs);
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
            case FacesConfigPackage.LIST_ENTRIES_TYPE__VALUE_CLASS:
                return getValueClass();
            case FacesConfigPackage.LIST_ENTRIES_TYPE__NULL_VALUE:
                return getNullValue();
            case FacesConfigPackage.LIST_ENTRIES_TYPE__VALUE:
                return getValue();
            case FacesConfigPackage.LIST_ENTRIES_TYPE__ID:
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
            case FacesConfigPackage.LIST_ENTRIES_TYPE__VALUE_CLASS:
                setValueClass((ValueClassType)newValue);
                return;
            case FacesConfigPackage.LIST_ENTRIES_TYPE__NULL_VALUE:
                getNullValue().clear();
                getNullValue().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.LIST_ENTRIES_TYPE__VALUE:
                getValue().clear();
                getValue().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.LIST_ENTRIES_TYPE__ID:
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
            case FacesConfigPackage.LIST_ENTRIES_TYPE__VALUE_CLASS:
                setValueClass((ValueClassType)null);
                return;
            case FacesConfigPackage.LIST_ENTRIES_TYPE__NULL_VALUE:
                getNullValue().clear();
                return;
            case FacesConfigPackage.LIST_ENTRIES_TYPE__VALUE:
                getValue().clear();
                return;
            case FacesConfigPackage.LIST_ENTRIES_TYPE__ID:
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
            case FacesConfigPackage.LIST_ENTRIES_TYPE__VALUE_CLASS:
                return valueClass != null;
            case FacesConfigPackage.LIST_ENTRIES_TYPE__NULL_VALUE:
                return nullValue != null && !nullValue.isEmpty();
            case FacesConfigPackage.LIST_ENTRIES_TYPE__VALUE:
                return value != null && !value.isEmpty();
            case FacesConfigPackage.LIST_ENTRIES_TYPE__ID:
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

} //ListEntriesTypeImpl
