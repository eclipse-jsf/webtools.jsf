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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.SourceClassType;
import org.eclipse.jst.jsf.facesconfig.emf.SystemEventClassType;
import org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerClassType;
import org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System Event Listener Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SystemEventListenerTypeImpl#getSystemEventListenerClass <em>System Event Listener Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SystemEventListenerTypeImpl#getSystemEventClass <em>System Event Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SystemEventListenerTypeImpl#getSourceClass <em>Source Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SystemEventListenerTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SystemEventListenerTypeImpl extends EObjectImpl implements SystemEventListenerType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getSystemEventListenerClass() <em>System Event Listener Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemEventListenerClass()
	 * @generated
	 * @ordered
	 */
	protected SystemEventListenerClassType systemEventListenerClass;

	/**
	 * The cached value of the '{@link #getSystemEventClass() <em>System Event Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemEventClass()
	 * @generated
	 * @ordered
	 */
	protected SystemEventClassType systemEventClass;

	/**
	 * The cached value of the '{@link #getSourceClass() <em>Source Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceClass()
	 * @generated
	 * @ordered
	 */
	protected SourceClassType sourceClass;

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
	protected SystemEventListenerTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.Literals.SYSTEM_EVENT_LISTENER_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemEventListenerClassType getSystemEventListenerClass() {
		return systemEventListenerClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param newSystemEventListenerClass 
	 * @param msgs 
	 * @return NotificationChain
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSystemEventListenerClass(SystemEventListenerClassType newSystemEventListenerClass, NotificationChain msgs) {
		SystemEventListenerClassType oldSystemEventListenerClass = systemEventListenerClass;
		systemEventListenerClass = newSystemEventListenerClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_LISTENER_CLASS, oldSystemEventListenerClass, newSystemEventListenerClass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSystemEventListenerClass(SystemEventListenerClassType newSystemEventListenerClass) {
		if (newSystemEventListenerClass != systemEventListenerClass) {
			NotificationChain msgs = null;
			if (systemEventListenerClass != null)
				msgs = ((InternalEObject)systemEventListenerClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_LISTENER_CLASS, null, msgs);
			if (newSystemEventListenerClass != null)
				msgs = ((InternalEObject)newSystemEventListenerClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_LISTENER_CLASS, null, msgs);
			msgs = basicSetSystemEventListenerClass(newSystemEventListenerClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_LISTENER_CLASS, newSystemEventListenerClass, newSystemEventListenerClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemEventClassType getSystemEventClass() {
		return systemEventClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param newSystemEventClass 
	 * @param msgs 
	 * @return NotificationChain
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSystemEventClass(SystemEventClassType newSystemEventClass, NotificationChain msgs) {
		SystemEventClassType oldSystemEventClass = systemEventClass;
		systemEventClass = newSystemEventClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_CLASS, oldSystemEventClass, newSystemEventClass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSystemEventClass(SystemEventClassType newSystemEventClass) {
		if (newSystemEventClass != systemEventClass) {
			NotificationChain msgs = null;
			if (systemEventClass != null)
				msgs = ((InternalEObject)systemEventClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_CLASS, null, msgs);
			if (newSystemEventClass != null)
				msgs = ((InternalEObject)newSystemEventClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_CLASS, null, msgs);
			msgs = basicSetSystemEventClass(newSystemEventClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_CLASS, newSystemEventClass, newSystemEventClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourceClassType getSourceClass() {
		return sourceClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param newSourceClass 
	 * @param msgs 
	 * @return NotificationChain
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSourceClass(SourceClassType newSourceClass, NotificationChain msgs) {
		SourceClassType oldSourceClass = sourceClass;
		sourceClass = newSourceClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SOURCE_CLASS, oldSourceClass, newSourceClass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceClass(SourceClassType newSourceClass) {
		if (newSourceClass != sourceClass) {
			NotificationChain msgs = null;
			if (sourceClass != null)
				msgs = ((InternalEObject)sourceClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SOURCE_CLASS, null, msgs);
			if (newSourceClass != null)
				msgs = ((InternalEObject)newSourceClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SOURCE_CLASS, null, msgs);
			msgs = basicSetSourceClass(newSourceClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SOURCE_CLASS, newSourceClass, newSourceClass));
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
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_LISTENER_CLASS:
				return basicSetSystemEventListenerClass(null, msgs);
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_CLASS:
				return basicSetSystemEventClass(null, msgs);
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SOURCE_CLASS:
				return basicSetSourceClass(null, msgs);
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
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_LISTENER_CLASS:
				return getSystemEventListenerClass();
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_CLASS:
				return getSystemEventClass();
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SOURCE_CLASS:
				return getSourceClass();
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__ID:
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
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_LISTENER_CLASS:
				setSystemEventListenerClass((SystemEventListenerClassType)newValue);
				return;
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_CLASS:
				setSystemEventClass((SystemEventClassType)newValue);
				return;
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SOURCE_CLASS:
				setSourceClass((SourceClassType)newValue);
				return;
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__ID:
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
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_LISTENER_CLASS:
				setSystemEventListenerClass((SystemEventListenerClassType)null);
				return;
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_CLASS:
				setSystemEventClass((SystemEventClassType)null);
				return;
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SOURCE_CLASS:
				setSourceClass((SourceClassType)null);
				return;
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__ID:
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
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_LISTENER_CLASS:
				return systemEventListenerClass != null;
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_CLASS:
				return systemEventClass != null;
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__SOURCE_CLASS:
				return sourceClass != null;
			case FacesConfigPackage.SYSTEM_EVENT_LISTENER_TYPE__ID:
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

} //SystemEventListenerTypeImpl
