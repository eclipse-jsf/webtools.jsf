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
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacetType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeImpl#getComponentType <em>Component Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeImpl#getComponentClass <em>Component Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeImpl#getFacet <em>Facet</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeImpl#getComponentExtension <em>Component Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentTypeImpl extends EObjectImpl implements ComponentType {
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
	 * The cached value of the '{@link #getComponentType() <em>Component Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentType()
	 * @generated
	 * @ordered
	 */
	protected ComponentTypeType componentType;

    /**
	 * The cached value of the '{@link #getComponentClass() <em>Component Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentClass()
	 * @generated
	 * @ordered
	 */
	protected ComponentClassType componentClass;

    /**
	 * The cached value of the '{@link #getFacet() <em>Facet</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacet()
	 * @generated
	 * @ordered
	 */
	protected EList facet;

    /**
	 * The cached value of the '{@link #getAttribute() <em>Attribute</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttribute()
	 * @generated
	 * @ordered
	 */
	protected EList attribute;

    /**
	 * The cached value of the '{@link #getProperty() <em>Property</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected EList property;

    /**
	 * The cached value of the '{@link #getComponentExtension() <em>Component Extension</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentExtension()
	 * @generated
	 * @ordered
	 */
	protected EList componentExtension;

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
	protected ComponentTypeImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.Literals.COMPONENT_TYPE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescription() {
		if (description == null) {
			description = new EObjectContainmentEList(DescriptionType.class, this, FacesConfigPackage.COMPONENT_TYPE__DESCRIPTION);
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
			displayName = new EObjectContainmentEList(DisplayNameType.class, this, FacesConfigPackage.COMPONENT_TYPE__DISPLAY_NAME);
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
			icon = new EObjectContainmentEList(IconType.class, this, FacesConfigPackage.COMPONENT_TYPE__ICON);
		}
		return icon;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentTypeType getComponentType() {
		return componentType;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newComponentType 
     * @param msgs 
     * @return the notification chain
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponentType(ComponentTypeType newComponentType, NotificationChain msgs) {
		ComponentTypeType oldComponentType = componentType;
		componentType = newComponentType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.COMPONENT_TYPE__COMPONENT_TYPE, oldComponentType, newComponentType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentType(ComponentTypeType newComponentType) {
		if (newComponentType != componentType) {
			NotificationChain msgs = null;
			if (componentType != null)
				msgs = ((InternalEObject)componentType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.COMPONENT_TYPE__COMPONENT_TYPE, null, msgs);
			if (newComponentType != null)
				msgs = ((InternalEObject)newComponentType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.COMPONENT_TYPE__COMPONENT_TYPE, null, msgs);
			msgs = basicSetComponentType(newComponentType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.COMPONENT_TYPE__COMPONENT_TYPE, newComponentType, newComponentType));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentClassType getComponentClass() {
		return componentClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newComponentClass 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponentClass(ComponentClassType newComponentClass, NotificationChain msgs) {
		ComponentClassType oldComponentClass = componentClass;
		componentClass = newComponentClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.COMPONENT_TYPE__COMPONENT_CLASS, oldComponentClass, newComponentClass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentClass(ComponentClassType newComponentClass) {
		if (newComponentClass != componentClass) {
			NotificationChain msgs = null;
			if (componentClass != null)
				msgs = ((InternalEObject)componentClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.COMPONENT_TYPE__COMPONENT_CLASS, null, msgs);
			if (newComponentClass != null)
				msgs = ((InternalEObject)newComponentClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.COMPONENT_TYPE__COMPONENT_CLASS, null, msgs);
			msgs = basicSetComponentClass(newComponentClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.COMPONENT_TYPE__COMPONENT_CLASS, newComponentClass, newComponentClass));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getFacet() {
		if (facet == null) {
			facet = new EObjectContainmentEList(FacetType.class, this, FacesConfigPackage.COMPONENT_TYPE__FACET);
		}
		return facet;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getAttribute() {
		if (attribute == null) {
			attribute = new EObjectContainmentEList(AttributeType.class, this, FacesConfigPackage.COMPONENT_TYPE__ATTRIBUTE);
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
			property = new EObjectContainmentEList(PropertyType.class, this, FacesConfigPackage.COMPONENT_TYPE__PROPERTY);
		}
		return property;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getComponentExtension() {
		if (componentExtension == null) {
			componentExtension = new EObjectContainmentEList(ComponentExtensionType.class, this, FacesConfigPackage.COMPONENT_TYPE__COMPONENT_EXTENSION);
		}
		return componentExtension;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.COMPONENT_TYPE__ID, oldId, id));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FacesConfigPackage.COMPONENT_TYPE__DESCRIPTION:
				return ((InternalEList)getDescription()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.COMPONENT_TYPE__DISPLAY_NAME:
				return ((InternalEList)getDisplayName()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.COMPONENT_TYPE__ICON:
				return ((InternalEList)getIcon()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.COMPONENT_TYPE__COMPONENT_TYPE:
				return basicSetComponentType(null, msgs);
			case FacesConfigPackage.COMPONENT_TYPE__COMPONENT_CLASS:
				return basicSetComponentClass(null, msgs);
			case FacesConfigPackage.COMPONENT_TYPE__FACET:
				return ((InternalEList)getFacet()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.COMPONENT_TYPE__ATTRIBUTE:
				return ((InternalEList)getAttribute()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.COMPONENT_TYPE__PROPERTY:
				return ((InternalEList)getProperty()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.COMPONENT_TYPE__COMPONENT_EXTENSION:
				return ((InternalEList)getComponentExtension()).basicRemove(otherEnd, msgs);
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
			case FacesConfigPackage.COMPONENT_TYPE__DESCRIPTION:
				return getDescription();
			case FacesConfigPackage.COMPONENT_TYPE__DISPLAY_NAME:
				return getDisplayName();
			case FacesConfigPackage.COMPONENT_TYPE__ICON:
				return getIcon();
			case FacesConfigPackage.COMPONENT_TYPE__COMPONENT_TYPE:
				return getComponentType();
			case FacesConfigPackage.COMPONENT_TYPE__COMPONENT_CLASS:
				return getComponentClass();
			case FacesConfigPackage.COMPONENT_TYPE__FACET:
				return getFacet();
			case FacesConfigPackage.COMPONENT_TYPE__ATTRIBUTE:
				return getAttribute();
			case FacesConfigPackage.COMPONENT_TYPE__PROPERTY:
				return getProperty();
			case FacesConfigPackage.COMPONENT_TYPE__COMPONENT_EXTENSION:
				return getComponentExtension();
			case FacesConfigPackage.COMPONENT_TYPE__ID:
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
			case FacesConfigPackage.COMPONENT_TYPE__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.COMPONENT_TYPE__DISPLAY_NAME:
				getDisplayName().clear();
				getDisplayName().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.COMPONENT_TYPE__ICON:
				getIcon().clear();
				getIcon().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.COMPONENT_TYPE__COMPONENT_TYPE:
				setComponentType((ComponentTypeType)newValue);
				return;
			case FacesConfigPackage.COMPONENT_TYPE__COMPONENT_CLASS:
				setComponentClass((ComponentClassType)newValue);
				return;
			case FacesConfigPackage.COMPONENT_TYPE__FACET:
				getFacet().clear();
				getFacet().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.COMPONENT_TYPE__ATTRIBUTE:
				getAttribute().clear();
				getAttribute().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.COMPONENT_TYPE__PROPERTY:
				getProperty().clear();
				getProperty().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.COMPONENT_TYPE__COMPONENT_EXTENSION:
				getComponentExtension().clear();
				getComponentExtension().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.COMPONENT_TYPE__ID:
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
			case FacesConfigPackage.COMPONENT_TYPE__DESCRIPTION:
				getDescription().clear();
				return;
			case FacesConfigPackage.COMPONENT_TYPE__DISPLAY_NAME:
				getDisplayName().clear();
				return;
			case FacesConfigPackage.COMPONENT_TYPE__ICON:
				getIcon().clear();
				return;
			case FacesConfigPackage.COMPONENT_TYPE__COMPONENT_TYPE:
				setComponentType((ComponentTypeType)null);
				return;
			case FacesConfigPackage.COMPONENT_TYPE__COMPONENT_CLASS:
				setComponentClass((ComponentClassType)null);
				return;
			case FacesConfigPackage.COMPONENT_TYPE__FACET:
				getFacet().clear();
				return;
			case FacesConfigPackage.COMPONENT_TYPE__ATTRIBUTE:
				getAttribute().clear();
				return;
			case FacesConfigPackage.COMPONENT_TYPE__PROPERTY:
				getProperty().clear();
				return;
			case FacesConfigPackage.COMPONENT_TYPE__COMPONENT_EXTENSION:
				getComponentExtension().clear();
				return;
			case FacesConfigPackage.COMPONENT_TYPE__ID:
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
			case FacesConfigPackage.COMPONENT_TYPE__DESCRIPTION:
				return description != null && !description.isEmpty();
			case FacesConfigPackage.COMPONENT_TYPE__DISPLAY_NAME:
				return displayName != null && !displayName.isEmpty();
			case FacesConfigPackage.COMPONENT_TYPE__ICON:
				return icon != null && !icon.isEmpty();
			case FacesConfigPackage.COMPONENT_TYPE__COMPONENT_TYPE:
				return componentType != null;
			case FacesConfigPackage.COMPONENT_TYPE__COMPONENT_CLASS:
				return componentClass != null;
			case FacesConfigPackage.COMPONENT_TYPE__FACET:
				return facet != null && !facet.isEmpty();
			case FacesConfigPackage.COMPONENT_TYPE__ATTRIBUTE:
				return attribute != null && !attribute.isEmpty();
			case FacesConfigPackage.COMPONENT_TYPE__PROPERTY:
				return property != null && !property.isEmpty();
			case FacesConfigPackage.COMPONENT_TYPE__COMPONENT_EXTENSION:
				return componentExtension != null && !componentExtension.isEmpty();
			case FacesConfigPackage.COMPONENT_TYPE__ID:
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

} //ComponentTypeImpl
