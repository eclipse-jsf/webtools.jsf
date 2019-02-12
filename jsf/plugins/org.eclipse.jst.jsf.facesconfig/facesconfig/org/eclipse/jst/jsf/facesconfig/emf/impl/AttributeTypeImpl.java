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
import org.eclipse.jst.jsf.facesconfig.emf.AttributeClassType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeNameType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultValueType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeTypeImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeTypeImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeTypeImpl#getAttributeName <em>Attribute Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeTypeImpl#getAttributeClass <em>Attribute Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeTypeImpl#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeTypeImpl#getSuggestedValue <em>Suggested Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeTypeImpl#getAttributeExtension <em>Attribute Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AttributeTypeImpl extends EObjectImpl implements AttributeType {
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
	 * The cached value of the '{@link #getAttributeName() <em>Attribute Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeName()
	 * @generated
	 * @ordered
	 */
	protected AttributeNameType attributeName;

    /**
	 * The cached value of the '{@link #getAttributeClass() <em>Attribute Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeClass()
	 * @generated
	 * @ordered
	 */
	protected AttributeClassType attributeClass;

    /**
	 * The cached value of the '{@link #getDefaultValue() <em>Default Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected DefaultValueType defaultValue;

    /**
	 * The cached value of the '{@link #getSuggestedValue() <em>Suggested Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuggestedValue()
	 * @generated
	 * @ordered
	 */
	protected SuggestedValueType suggestedValue;

    /**
	 * The cached value of the '{@link #getAttributeExtension() <em>Attribute Extension</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeExtension()
	 * @generated
	 * @ordered
	 */
	protected EList attributeExtension;

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
	protected AttributeTypeImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.Literals.ATTRIBUTE_TYPE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescription() {
		if (description == null) {
			description = new EObjectContainmentEList(DescriptionType.class, this, FacesConfigPackage.ATTRIBUTE_TYPE__DESCRIPTION);
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
			displayName = new EObjectContainmentEList(DisplayNameType.class, this, FacesConfigPackage.ATTRIBUTE_TYPE__DISPLAY_NAME);
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
			icon = new EObjectContainmentEList(IconType.class, this, FacesConfigPackage.ATTRIBUTE_TYPE__ICON);
		}
		return icon;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeNameType getAttributeName() {
		return attributeName;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newAttributeName 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAttributeName(AttributeNameType newAttributeName, NotificationChain msgs) {
		AttributeNameType oldAttributeName = attributeName;
		attributeName = newAttributeName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_NAME, oldAttributeName, newAttributeName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttributeName(AttributeNameType newAttributeName) {
		if (newAttributeName != attributeName) {
			NotificationChain msgs = null;
			if (attributeName != null)
				msgs = ((InternalEObject)attributeName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_NAME, null, msgs);
			if (newAttributeName != null)
				msgs = ((InternalEObject)newAttributeName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_NAME, null, msgs);
			msgs = basicSetAttributeName(newAttributeName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_NAME, newAttributeName, newAttributeName));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeClassType getAttributeClass() {
		return attributeClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newAttributeClass 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAttributeClass(AttributeClassType newAttributeClass, NotificationChain msgs) {
		AttributeClassType oldAttributeClass = attributeClass;
		attributeClass = newAttributeClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_CLASS, oldAttributeClass, newAttributeClass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttributeClass(AttributeClassType newAttributeClass) {
		if (newAttributeClass != attributeClass) {
			NotificationChain msgs = null;
			if (attributeClass != null)
				msgs = ((InternalEObject)attributeClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_CLASS, null, msgs);
			if (newAttributeClass != null)
				msgs = ((InternalEObject)newAttributeClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_CLASS, null, msgs);
			msgs = basicSetAttributeClass(newAttributeClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_CLASS, newAttributeClass, newAttributeClass));
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
     * @param newDefaultValue 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefaultValue(DefaultValueType newDefaultValue, NotificationChain msgs) {
		DefaultValueType oldDefaultValue = defaultValue;
		defaultValue = newDefaultValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ATTRIBUTE_TYPE__DEFAULT_VALUE, oldDefaultValue, newDefaultValue);
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
				msgs = ((InternalEObject)defaultValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ATTRIBUTE_TYPE__DEFAULT_VALUE, null, msgs);
			if (newDefaultValue != null)
				msgs = ((InternalEObject)newDefaultValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ATTRIBUTE_TYPE__DEFAULT_VALUE, null, msgs);
			msgs = basicSetDefaultValue(newDefaultValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ATTRIBUTE_TYPE__DEFAULT_VALUE, newDefaultValue, newDefaultValue));
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
     * @param newSuggestedValue 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSuggestedValue(SuggestedValueType newSuggestedValue, NotificationChain msgs) {
		SuggestedValueType oldSuggestedValue = suggestedValue;
		suggestedValue = newSuggestedValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ATTRIBUTE_TYPE__SUGGESTED_VALUE, oldSuggestedValue, newSuggestedValue);
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
				msgs = ((InternalEObject)suggestedValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ATTRIBUTE_TYPE__SUGGESTED_VALUE, null, msgs);
			if (newSuggestedValue != null)
				msgs = ((InternalEObject)newSuggestedValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ATTRIBUTE_TYPE__SUGGESTED_VALUE, null, msgs);
			msgs = basicSetSuggestedValue(newSuggestedValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ATTRIBUTE_TYPE__SUGGESTED_VALUE, newSuggestedValue, newSuggestedValue));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getAttributeExtension() {
		if (attributeExtension == null) {
			attributeExtension = new EObjectContainmentEList(AttributeExtensionType.class, this, FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_EXTENSION);
		}
		return attributeExtension;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ATTRIBUTE_TYPE__ID, oldId, id));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FacesConfigPackage.ATTRIBUTE_TYPE__DESCRIPTION:
				return ((InternalEList)getDescription()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.ATTRIBUTE_TYPE__DISPLAY_NAME:
				return ((InternalEList)getDisplayName()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.ATTRIBUTE_TYPE__ICON:
				return ((InternalEList)getIcon()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_NAME:
				return basicSetAttributeName(null, msgs);
			case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_CLASS:
				return basicSetAttributeClass(null, msgs);
			case FacesConfigPackage.ATTRIBUTE_TYPE__DEFAULT_VALUE:
				return basicSetDefaultValue(null, msgs);
			case FacesConfigPackage.ATTRIBUTE_TYPE__SUGGESTED_VALUE:
				return basicSetSuggestedValue(null, msgs);
			case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_EXTENSION:
				return ((InternalEList)getAttributeExtension()).basicRemove(otherEnd, msgs);
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
			case FacesConfigPackage.ATTRIBUTE_TYPE__DESCRIPTION:
				return getDescription();
			case FacesConfigPackage.ATTRIBUTE_TYPE__DISPLAY_NAME:
				return getDisplayName();
			case FacesConfigPackage.ATTRIBUTE_TYPE__ICON:
				return getIcon();
			case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_NAME:
				return getAttributeName();
			case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_CLASS:
				return getAttributeClass();
			case FacesConfigPackage.ATTRIBUTE_TYPE__DEFAULT_VALUE:
				return getDefaultValue();
			case FacesConfigPackage.ATTRIBUTE_TYPE__SUGGESTED_VALUE:
				return getSuggestedValue();
			case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_EXTENSION:
				return getAttributeExtension();
			case FacesConfigPackage.ATTRIBUTE_TYPE__ID:
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
			case FacesConfigPackage.ATTRIBUTE_TYPE__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.ATTRIBUTE_TYPE__DISPLAY_NAME:
				getDisplayName().clear();
				getDisplayName().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.ATTRIBUTE_TYPE__ICON:
				getIcon().clear();
				getIcon().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_NAME:
				setAttributeName((AttributeNameType)newValue);
				return;
			case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_CLASS:
				setAttributeClass((AttributeClassType)newValue);
				return;
			case FacesConfigPackage.ATTRIBUTE_TYPE__DEFAULT_VALUE:
				setDefaultValue((DefaultValueType)newValue);
				return;
			case FacesConfigPackage.ATTRIBUTE_TYPE__SUGGESTED_VALUE:
				setSuggestedValue((SuggestedValueType)newValue);
				return;
			case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_EXTENSION:
				getAttributeExtension().clear();
				getAttributeExtension().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.ATTRIBUTE_TYPE__ID:
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
			case FacesConfigPackage.ATTRIBUTE_TYPE__DESCRIPTION:
				getDescription().clear();
				return;
			case FacesConfigPackage.ATTRIBUTE_TYPE__DISPLAY_NAME:
				getDisplayName().clear();
				return;
			case FacesConfigPackage.ATTRIBUTE_TYPE__ICON:
				getIcon().clear();
				return;
			case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_NAME:
				setAttributeName((AttributeNameType)null);
				return;
			case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_CLASS:
				setAttributeClass((AttributeClassType)null);
				return;
			case FacesConfigPackage.ATTRIBUTE_TYPE__DEFAULT_VALUE:
				setDefaultValue((DefaultValueType)null);
				return;
			case FacesConfigPackage.ATTRIBUTE_TYPE__SUGGESTED_VALUE:
				setSuggestedValue((SuggestedValueType)null);
				return;
			case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_EXTENSION:
				getAttributeExtension().clear();
				return;
			case FacesConfigPackage.ATTRIBUTE_TYPE__ID:
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
			case FacesConfigPackage.ATTRIBUTE_TYPE__DESCRIPTION:
				return description != null && !description.isEmpty();
			case FacesConfigPackage.ATTRIBUTE_TYPE__DISPLAY_NAME:
				return displayName != null && !displayName.isEmpty();
			case FacesConfigPackage.ATTRIBUTE_TYPE__ICON:
				return icon != null && !icon.isEmpty();
			case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_NAME:
				return attributeName != null;
			case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_CLASS:
				return attributeClass != null;
			case FacesConfigPackage.ATTRIBUTE_TYPE__DEFAULT_VALUE:
				return defaultValue != null;
			case FacesConfigPackage.ATTRIBUTE_TYPE__SUGGESTED_VALUE:
				return suggestedValue != null;
			case FacesConfigPackage.ATTRIBUTE_TYPE__ATTRIBUTE_EXTENSION:
				return attributeExtension != null && !attributeExtension.isEmpty();
			case FacesConfigPackage.ATTRIBUTE_TYPE__ID:
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
		result.append(" (id: ");//$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //AttributeTypeImpl
