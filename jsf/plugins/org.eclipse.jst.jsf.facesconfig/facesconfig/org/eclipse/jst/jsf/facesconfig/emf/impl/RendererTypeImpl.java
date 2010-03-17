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
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentFamilyType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacetType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererClassType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererTypeType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Renderer Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeImpl#getComponentFamily <em>Component Family</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeImpl#getRendererType <em>Renderer Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeImpl#getRendererClass <em>Renderer Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeImpl#getFacet <em>Facet</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeImpl#getRendererExtension <em>Renderer Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RendererTypeImpl extends EObjectImpl implements RendererType {
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
	 * The cached value of the '{@link #getComponentFamily() <em>Component Family</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentFamily()
	 * @generated
	 * @ordered
	 */
	protected ComponentFamilyType componentFamily;

    /**
	 * The cached value of the '{@link #getRendererType() <em>Renderer Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRendererType()
	 * @generated
	 * @ordered
	 */
	protected RendererTypeType rendererType;

    /**
	 * The cached value of the '{@link #getRendererClass() <em>Renderer Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRendererClass()
	 * @generated
	 * @ordered
	 */
	protected RendererClassType rendererClass;

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
	 * The cached value of the '{@link #getRendererExtension() <em>Renderer Extension</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRendererExtension()
	 * @generated
	 * @ordered
	 */
	protected EList rendererExtension;

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
	protected RendererTypeImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.Literals.RENDERER_TYPE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescription() {
		if (description == null) {
			description = new EObjectContainmentEList(DescriptionType.class, this, FacesConfigPackage.RENDERER_TYPE__DESCRIPTION);
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
			displayName = new EObjectContainmentEList(DisplayNameType.class, this, FacesConfigPackage.RENDERER_TYPE__DISPLAY_NAME);
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
			icon = new EObjectContainmentEList(IconType.class, this, FacesConfigPackage.RENDERER_TYPE__ICON);
		}
		return icon;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentFamilyType getComponentFamily() {
		return componentFamily;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newComponentFamily 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponentFamily(ComponentFamilyType newComponentFamily, NotificationChain msgs) {
		ComponentFamilyType oldComponentFamily = componentFamily;
		componentFamily = newComponentFamily;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.RENDERER_TYPE__COMPONENT_FAMILY, oldComponentFamily, newComponentFamily);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentFamily(ComponentFamilyType newComponentFamily) {
		if (newComponentFamily != componentFamily) {
			NotificationChain msgs = null;
			if (componentFamily != null)
				msgs = ((InternalEObject)componentFamily).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.RENDERER_TYPE__COMPONENT_FAMILY, null, msgs);
			if (newComponentFamily != null)
				msgs = ((InternalEObject)newComponentFamily).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.RENDERER_TYPE__COMPONENT_FAMILY, null, msgs);
			msgs = basicSetComponentFamily(newComponentFamily, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.RENDERER_TYPE__COMPONENT_FAMILY, newComponentFamily, newComponentFamily));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RendererTypeType getRendererType() {
		return rendererType;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newRendererType 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRendererType(RendererTypeType newRendererType, NotificationChain msgs) {
		RendererTypeType oldRendererType = rendererType;
		rendererType = newRendererType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.RENDERER_TYPE__RENDERER_TYPE, oldRendererType, newRendererType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRendererType(RendererTypeType newRendererType) {
		if (newRendererType != rendererType) {
			NotificationChain msgs = null;
			if (rendererType != null)
				msgs = ((InternalEObject)rendererType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.RENDERER_TYPE__RENDERER_TYPE, null, msgs);
			if (newRendererType != null)
				msgs = ((InternalEObject)newRendererType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.RENDERER_TYPE__RENDERER_TYPE, null, msgs);
			msgs = basicSetRendererType(newRendererType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.RENDERER_TYPE__RENDERER_TYPE, newRendererType, newRendererType));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RendererClassType getRendererClass() {
		return rendererClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newRendererClass 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRendererClass(RendererClassType newRendererClass, NotificationChain msgs) {
		RendererClassType oldRendererClass = rendererClass;
		rendererClass = newRendererClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.RENDERER_TYPE__RENDERER_CLASS, oldRendererClass, newRendererClass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRendererClass(RendererClassType newRendererClass) {
		if (newRendererClass != rendererClass) {
			NotificationChain msgs = null;
			if (rendererClass != null)
				msgs = ((InternalEObject)rendererClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.RENDERER_TYPE__RENDERER_CLASS, null, msgs);
			if (newRendererClass != null)
				msgs = ((InternalEObject)newRendererClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.RENDERER_TYPE__RENDERER_CLASS, null, msgs);
			msgs = basicSetRendererClass(newRendererClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.RENDERER_TYPE__RENDERER_CLASS, newRendererClass, newRendererClass));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getFacet() {
		if (facet == null) {
			facet = new EObjectContainmentEList(FacetType.class, this, FacesConfigPackage.RENDERER_TYPE__FACET);
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
			attribute = new EObjectContainmentEList(AttributeType.class, this, FacesConfigPackage.RENDERER_TYPE__ATTRIBUTE);
		}
		return attribute;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRendererExtension() {
		if (rendererExtension == null) {
			rendererExtension = new EObjectContainmentEList(RendererExtensionType.class, this, FacesConfigPackage.RENDERER_TYPE__RENDERER_EXTENSION);
		}
		return rendererExtension;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.RENDERER_TYPE__ID, oldId, id));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FacesConfigPackage.RENDERER_TYPE__DESCRIPTION:
				return ((InternalEList)getDescription()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.RENDERER_TYPE__DISPLAY_NAME:
				return ((InternalEList)getDisplayName()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.RENDERER_TYPE__ICON:
				return ((InternalEList)getIcon()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.RENDERER_TYPE__COMPONENT_FAMILY:
				return basicSetComponentFamily(null, msgs);
			case FacesConfigPackage.RENDERER_TYPE__RENDERER_TYPE:
				return basicSetRendererType(null, msgs);
			case FacesConfigPackage.RENDERER_TYPE__RENDERER_CLASS:
				return basicSetRendererClass(null, msgs);
			case FacesConfigPackage.RENDERER_TYPE__FACET:
				return ((InternalEList)getFacet()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.RENDERER_TYPE__ATTRIBUTE:
				return ((InternalEList)getAttribute()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.RENDERER_TYPE__RENDERER_EXTENSION:
				return ((InternalEList)getRendererExtension()).basicRemove(otherEnd, msgs);
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
			case FacesConfigPackage.RENDERER_TYPE__DESCRIPTION:
				return getDescription();
			case FacesConfigPackage.RENDERER_TYPE__DISPLAY_NAME:
				return getDisplayName();
			case FacesConfigPackage.RENDERER_TYPE__ICON:
				return getIcon();
			case FacesConfigPackage.RENDERER_TYPE__COMPONENT_FAMILY:
				return getComponentFamily();
			case FacesConfigPackage.RENDERER_TYPE__RENDERER_TYPE:
				return getRendererType();
			case FacesConfigPackage.RENDERER_TYPE__RENDERER_CLASS:
				return getRendererClass();
			case FacesConfigPackage.RENDERER_TYPE__FACET:
				return getFacet();
			case FacesConfigPackage.RENDERER_TYPE__ATTRIBUTE:
				return getAttribute();
			case FacesConfigPackage.RENDERER_TYPE__RENDERER_EXTENSION:
				return getRendererExtension();
			case FacesConfigPackage.RENDERER_TYPE__ID:
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
			case FacesConfigPackage.RENDERER_TYPE__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.RENDERER_TYPE__DISPLAY_NAME:
				getDisplayName().clear();
				getDisplayName().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.RENDERER_TYPE__ICON:
				getIcon().clear();
				getIcon().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.RENDERER_TYPE__COMPONENT_FAMILY:
				setComponentFamily((ComponentFamilyType)newValue);
				return;
			case FacesConfigPackage.RENDERER_TYPE__RENDERER_TYPE:
				setRendererType((RendererTypeType)newValue);
				return;
			case FacesConfigPackage.RENDERER_TYPE__RENDERER_CLASS:
				setRendererClass((RendererClassType)newValue);
				return;
			case FacesConfigPackage.RENDERER_TYPE__FACET:
				getFacet().clear();
				getFacet().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.RENDERER_TYPE__ATTRIBUTE:
				getAttribute().clear();
				getAttribute().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.RENDERER_TYPE__RENDERER_EXTENSION:
				getRendererExtension().clear();
				getRendererExtension().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.RENDERER_TYPE__ID:
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
			case FacesConfigPackage.RENDERER_TYPE__DESCRIPTION:
				getDescription().clear();
				return;
			case FacesConfigPackage.RENDERER_TYPE__DISPLAY_NAME:
				getDisplayName().clear();
				return;
			case FacesConfigPackage.RENDERER_TYPE__ICON:
				getIcon().clear();
				return;
			case FacesConfigPackage.RENDERER_TYPE__COMPONENT_FAMILY:
				setComponentFamily((ComponentFamilyType)null);
				return;
			case FacesConfigPackage.RENDERER_TYPE__RENDERER_TYPE:
				setRendererType((RendererTypeType)null);
				return;
			case FacesConfigPackage.RENDERER_TYPE__RENDERER_CLASS:
				setRendererClass((RendererClassType)null);
				return;
			case FacesConfigPackage.RENDERER_TYPE__FACET:
				getFacet().clear();
				return;
			case FacesConfigPackage.RENDERER_TYPE__ATTRIBUTE:
				getAttribute().clear();
				return;
			case FacesConfigPackage.RENDERER_TYPE__RENDERER_EXTENSION:
				getRendererExtension().clear();
				return;
			case FacesConfigPackage.RENDERER_TYPE__ID:
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
			case FacesConfigPackage.RENDERER_TYPE__DESCRIPTION:
				return description != null && !description.isEmpty();
			case FacesConfigPackage.RENDERER_TYPE__DISPLAY_NAME:
				return displayName != null && !displayName.isEmpty();
			case FacesConfigPackage.RENDERER_TYPE__ICON:
				return icon != null && !icon.isEmpty();
			case FacesConfigPackage.RENDERER_TYPE__COMPONENT_FAMILY:
				return componentFamily != null;
			case FacesConfigPackage.RENDERER_TYPE__RENDERER_TYPE:
				return rendererType != null;
			case FacesConfigPackage.RENDERER_TYPE__RENDERER_CLASS:
				return rendererClass != null;
			case FacesConfigPackage.RENDERER_TYPE__FACET:
				return facet != null && !facet.isEmpty();
			case FacesConfigPackage.RENDERER_TYPE__ATTRIBUTE:
				return attribute != null && !attribute.isEmpty();
			case FacesConfigPackage.RENDERER_TYPE__RENDERER_EXTENSION:
				return rendererExtension != null && !rendererExtension.isEmpty();
			case FacesConfigPackage.RENDERER_TYPE__ID:
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

} //RendererTypeImpl
