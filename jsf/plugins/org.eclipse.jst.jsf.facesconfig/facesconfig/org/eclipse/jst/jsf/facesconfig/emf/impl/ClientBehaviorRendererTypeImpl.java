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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererType;
import org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Client Behavior Renderer Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ClientBehaviorRendererTypeImpl#getClientBehaviorRendererType <em>Client Behavior Renderer Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ClientBehaviorRendererTypeImpl#getClientBehaviorRendererClass <em>Client Behavior Renderer Class</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClientBehaviorRendererTypeImpl extends EObjectImpl implements ClientBehaviorRendererType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getClientBehaviorRendererType() <em>Client Behavior Renderer Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClientBehaviorRendererType()
	 * @generated
	 * @ordered
	 */
	protected ClientBehaviorRendererTypeType clientBehaviorRendererType;

	/**
	 * The cached value of the '{@link #getClientBehaviorRendererClass() <em>Client Behavior Renderer Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClientBehaviorRendererClass()
	 * @generated
	 * @ordered
	 */
	protected ClientBehaviorRendererClassType clientBehaviorRendererClass;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClientBehaviorRendererTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.Literals.CLIENT_BEHAVIOR_RENDERER_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClientBehaviorRendererTypeType getClientBehaviorRendererType() {
		return clientBehaviorRendererType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @param newClientBehaviorRendererType 
	 * @param msgs 
	 * @return NotificationChain
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetClientBehaviorRendererType(ClientBehaviorRendererTypeType newClientBehaviorRendererType, NotificationChain msgs) {
		ClientBehaviorRendererTypeType oldClientBehaviorRendererType = clientBehaviorRendererType;
		clientBehaviorRendererType = newClientBehaviorRendererType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_TYPE, oldClientBehaviorRendererType, newClientBehaviorRendererType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClientBehaviorRendererType(ClientBehaviorRendererTypeType newClientBehaviorRendererType) {
		if (newClientBehaviorRendererType != clientBehaviorRendererType) {
			NotificationChain msgs = null;
			if (clientBehaviorRendererType != null)
				msgs = ((InternalEObject)clientBehaviorRendererType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_TYPE, null, msgs);
			if (newClientBehaviorRendererType != null)
				msgs = ((InternalEObject)newClientBehaviorRendererType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_TYPE, null, msgs);
			msgs = basicSetClientBehaviorRendererType(newClientBehaviorRendererType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_TYPE, newClientBehaviorRendererType, newClientBehaviorRendererType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClientBehaviorRendererClassType getClientBehaviorRendererClass() {
		return clientBehaviorRendererClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @param newClientBehaviorRendererClass 
	 * @param msgs 
	 * @return NotificationChain
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetClientBehaviorRendererClass(ClientBehaviorRendererClassType newClientBehaviorRendererClass, NotificationChain msgs) {
		ClientBehaviorRendererClassType oldClientBehaviorRendererClass = clientBehaviorRendererClass;
		clientBehaviorRendererClass = newClientBehaviorRendererClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_CLASS, oldClientBehaviorRendererClass, newClientBehaviorRendererClass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClientBehaviorRendererClass(ClientBehaviorRendererClassType newClientBehaviorRendererClass) {
		if (newClientBehaviorRendererClass != clientBehaviorRendererClass) {
			NotificationChain msgs = null;
			if (clientBehaviorRendererClass != null)
				msgs = ((InternalEObject)clientBehaviorRendererClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_CLASS, null, msgs);
			if (newClientBehaviorRendererClass != null)
				msgs = ((InternalEObject)newClientBehaviorRendererClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_CLASS, null, msgs);
			msgs = basicSetClientBehaviorRendererClass(newClientBehaviorRendererClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_CLASS, newClientBehaviorRendererClass, newClientBehaviorRendererClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_TYPE:
				return basicSetClientBehaviorRendererType(null, msgs);
			case FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_CLASS:
				return basicSetClientBehaviorRendererClass(null, msgs);
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
			case FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_TYPE:
				return getClientBehaviorRendererType();
			case FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_CLASS:
				return getClientBehaviorRendererClass();
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
			case FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_TYPE:
				setClientBehaviorRendererType((ClientBehaviorRendererTypeType)newValue);
				return;
			case FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_CLASS:
				setClientBehaviorRendererClass((ClientBehaviorRendererClassType)newValue);
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
			case FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_TYPE:
				setClientBehaviorRendererType((ClientBehaviorRendererTypeType)null);
				return;
			case FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_CLASS:
				setClientBehaviorRendererClass((ClientBehaviorRendererClassType)null);
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
			case FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_TYPE:
				return clientBehaviorRendererType != null;
			case FacesConfigPackage.CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_CLASS:
				return clientBehaviorRendererClass != null;
		}
		return super.eIsSet(featureID);
	}

} //ClientBehaviorRendererTypeImpl
