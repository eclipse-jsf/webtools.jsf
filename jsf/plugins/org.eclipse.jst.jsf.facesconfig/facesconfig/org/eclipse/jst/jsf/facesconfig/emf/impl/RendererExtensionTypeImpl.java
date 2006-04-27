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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.RendererExtensionType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Renderer Extension Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererExtensionTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererExtensionTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererExtensionTypeImpl#getAny <em>Any</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererExtensionTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RendererExtensionTypeImpl extends EObjectImpl implements RendererExtensionType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed = null;

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
	protected RendererExtensionTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.eINSTANCE.getRendererExtensionType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, FacesConfigPackage.RENDERER_EXTENSION_TYPE__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		return (FeatureMap)((FeatureMap)getMixed()).list(FacesConfigPackage.eINSTANCE.getRendererExtensionType_Group());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getAny() {
		return (FeatureMap)((FeatureMap)getGroup()).list(FacesConfigPackage.eINSTANCE.getRendererExtensionType_Any());
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
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.RENDERER_EXTENSION_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case FacesConfigPackage.RENDERER_EXTENSION_TYPE__MIXED:
					return ((InternalEList)getMixed()).basicRemove(otherEnd, msgs);
				case FacesConfigPackage.RENDERER_EXTENSION_TYPE__GROUP:
					return ((InternalEList)getGroup()).basicRemove(otherEnd, msgs);
				case FacesConfigPackage.RENDERER_EXTENSION_TYPE__ANY:
					return ((InternalEList)getAny()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case FacesConfigPackage.RENDERER_EXTENSION_TYPE__MIXED:
				return getMixed();
			case FacesConfigPackage.RENDERER_EXTENSION_TYPE__GROUP:
				return getGroup();
			case FacesConfigPackage.RENDERER_EXTENSION_TYPE__ANY:
				return getAny();
			case FacesConfigPackage.RENDERER_EXTENSION_TYPE__ID:
				return getId();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case FacesConfigPackage.RENDERER_EXTENSION_TYPE__MIXED:
				getMixed().clear();
				getMixed().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.RENDERER_EXTENSION_TYPE__GROUP:
				getGroup().clear();
				getGroup().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.RENDERER_EXTENSION_TYPE__ANY:
				getAny().clear();
				getAny().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.RENDERER_EXTENSION_TYPE__ID:
				setId((String)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case FacesConfigPackage.RENDERER_EXTENSION_TYPE__MIXED:
				getMixed().clear();
				return;
			case FacesConfigPackage.RENDERER_EXTENSION_TYPE__GROUP:
				getGroup().clear();
				return;
			case FacesConfigPackage.RENDERER_EXTENSION_TYPE__ANY:
				getAny().clear();
				return;
			case FacesConfigPackage.RENDERER_EXTENSION_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case FacesConfigPackage.RENDERER_EXTENSION_TYPE__MIXED:
				return mixed != null && !mixed.isEmpty();
			case FacesConfigPackage.RENDERER_EXTENSION_TYPE__GROUP:
				return !getGroup().isEmpty();
			case FacesConfigPackage.RENDERER_EXTENSION_TYPE__ANY:
				return !getAny().isEmpty();
			case FacesConfigPackage.RENDERER_EXTENSION_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //RendererExtensionTypeImpl
