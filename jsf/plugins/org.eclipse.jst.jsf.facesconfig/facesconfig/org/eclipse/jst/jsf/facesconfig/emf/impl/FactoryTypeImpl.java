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
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitFactoryType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Factory Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl#getApplicationFactory <em>Application Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl#getFacesContextFactory <em>Faces Context Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl#getLifecycleFactory <em>Lifecycle Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl#getRenderKitFactory <em>Render Kit Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FactoryTypeImpl extends EObjectImpl implements FactoryType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

	/**
	 * The cached value of the '{@link #getApplicationFactory() <em>Application Factory</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplicationFactory()
	 * @generated
	 * @ordered
	 */
	protected EList applicationFactory = null;

	/**
	 * The cached value of the '{@link #getFacesContextFactory() <em>Faces Context Factory</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacesContextFactory()
	 * @generated
	 * @ordered
	 */
	protected EList facesContextFactory = null;

	/**
	 * The cached value of the '{@link #getLifecycleFactory() <em>Lifecycle Factory</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLifecycleFactory()
	 * @generated
	 * @ordered
	 */
	protected EList lifecycleFactory = null;

	/**
	 * The cached value of the '{@link #getRenderKitFactory() <em>Render Kit Factory</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRenderKitFactory()
	 * @generated
	 * @ordered
	 */
	protected EList renderKitFactory = null;

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
	protected FactoryTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.eINSTANCE.getFactoryType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getApplicationFactory() {
		if (applicationFactory == null) {
			applicationFactory = new EObjectContainmentEList(ApplicationFactoryType.class, this, FacesConfigPackage.FACTORY_TYPE__APPLICATION_FACTORY);
		}
		return applicationFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getFacesContextFactory() {
		if (facesContextFactory == null) {
			facesContextFactory = new EObjectContainmentEList(FacesContextFactoryType.class, this, FacesConfigPackage.FACTORY_TYPE__FACES_CONTEXT_FACTORY);
		}
		return facesContextFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getLifecycleFactory() {
		if (lifecycleFactory == null) {
			lifecycleFactory = new EObjectContainmentEList(LifecycleFactoryType.class, this, FacesConfigPackage.FACTORY_TYPE__LIFECYCLE_FACTORY);
		}
		return lifecycleFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRenderKitFactory() {
		if (renderKitFactory == null) {
			renderKitFactory = new EObjectContainmentEList(RenderKitFactoryType.class, this, FacesConfigPackage.FACTORY_TYPE__RENDER_KIT_FACTORY);
		}
		return renderKitFactory;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.FACTORY_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case FacesConfigPackage.FACTORY_TYPE__APPLICATION_FACTORY:
					return ((InternalEList)getApplicationFactory()).basicRemove(otherEnd, msgs);
				case FacesConfigPackage.FACTORY_TYPE__FACES_CONTEXT_FACTORY:
					return ((InternalEList)getFacesContextFactory()).basicRemove(otherEnd, msgs);
				case FacesConfigPackage.FACTORY_TYPE__LIFECYCLE_FACTORY:
					return ((InternalEList)getLifecycleFactory()).basicRemove(otherEnd, msgs);
				case FacesConfigPackage.FACTORY_TYPE__RENDER_KIT_FACTORY:
					return ((InternalEList)getRenderKitFactory()).basicRemove(otherEnd, msgs);
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
			case FacesConfigPackage.FACTORY_TYPE__APPLICATION_FACTORY:
				return getApplicationFactory();
			case FacesConfigPackage.FACTORY_TYPE__FACES_CONTEXT_FACTORY:
				return getFacesContextFactory();
			case FacesConfigPackage.FACTORY_TYPE__LIFECYCLE_FACTORY:
				return getLifecycleFactory();
			case FacesConfigPackage.FACTORY_TYPE__RENDER_KIT_FACTORY:
				return getRenderKitFactory();
			case FacesConfigPackage.FACTORY_TYPE__ID:
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
			case FacesConfigPackage.FACTORY_TYPE__APPLICATION_FACTORY:
				getApplicationFactory().clear();
				getApplicationFactory().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACTORY_TYPE__FACES_CONTEXT_FACTORY:
				getFacesContextFactory().clear();
				getFacesContextFactory().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACTORY_TYPE__LIFECYCLE_FACTORY:
				getLifecycleFactory().clear();
				getLifecycleFactory().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACTORY_TYPE__RENDER_KIT_FACTORY:
				getRenderKitFactory().clear();
				getRenderKitFactory().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACTORY_TYPE__ID:
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
			case FacesConfigPackage.FACTORY_TYPE__APPLICATION_FACTORY:
				getApplicationFactory().clear();
				return;
			case FacesConfigPackage.FACTORY_TYPE__FACES_CONTEXT_FACTORY:
				getFacesContextFactory().clear();
				return;
			case FacesConfigPackage.FACTORY_TYPE__LIFECYCLE_FACTORY:
				getLifecycleFactory().clear();
				return;
			case FacesConfigPackage.FACTORY_TYPE__RENDER_KIT_FACTORY:
				getRenderKitFactory().clear();
				return;
			case FacesConfigPackage.FACTORY_TYPE__ID:
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
			case FacesConfigPackage.FACTORY_TYPE__APPLICATION_FACTORY:
				return applicationFactory != null && !applicationFactory.isEmpty();
			case FacesConfigPackage.FACTORY_TYPE__FACES_CONTEXT_FACTORY:
				return facesContextFactory != null && !facesContextFactory.isEmpty();
			case FacesConfigPackage.FACTORY_TYPE__LIFECYCLE_FACTORY:
				return lifecycleFactory != null && !lifecycleFactory.isEmpty();
			case FacesConfigPackage.FACTORY_TYPE__RENDER_KIT_FACTORY:
				return renderKitFactory != null && !renderKitFactory.isEmpty();
			case FacesConfigPackage.FACTORY_TYPE__ID:
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
		result.append(" (id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //FactoryTypeImpl
