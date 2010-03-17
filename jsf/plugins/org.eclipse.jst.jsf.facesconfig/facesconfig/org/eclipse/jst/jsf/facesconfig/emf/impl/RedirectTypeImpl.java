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
import org.eclipse.jst.jsf.facesconfig.emf.RedirectType;
import org.eclipse.jst.jsf.facesconfig.emf.RedirectViewParamType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Redirect Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RedirectTypeImpl#getViewParam <em>View Param</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RedirectTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RedirectTypeImpl#isIncludeViewParams <em>Include View Params</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RedirectTypeImpl extends EObjectImpl implements RedirectType {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

    /**
	 * The cached value of the '{@link #getViewParam() <em>View Param</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewParam()
	 * @generated
	 * @ordered
	 */
	protected EList viewParam;

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
	 * The default value of the '{@link #isIncludeViewParams() <em>Include View Params</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIncludeViewParams()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INCLUDE_VIEW_PARAMS_EDEFAULT = false;

				/**
	 * The cached value of the '{@link #isIncludeViewParams() <em>Include View Params</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIncludeViewParams()
	 * @generated
	 * @ordered
	 */
	protected boolean includeViewParams = INCLUDE_VIEW_PARAMS_EDEFAULT;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RedirectTypeImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.Literals.REDIRECT_TYPE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getViewParam() {
		if (viewParam == null) {
			viewParam = new EObjectContainmentEList(RedirectViewParamType.class, this, FacesConfigPackage.REDIRECT_TYPE__VIEW_PARAM);
		}
		return viewParam;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.REDIRECT_TYPE__ID, oldId, id));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIncludeViewParams() {
		return includeViewParams;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncludeViewParams(boolean newIncludeViewParams) {
		boolean oldIncludeViewParams = includeViewParams;
		includeViewParams = newIncludeViewParams;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.REDIRECT_TYPE__INCLUDE_VIEW_PARAMS, oldIncludeViewParams, includeViewParams));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FacesConfigPackage.REDIRECT_TYPE__VIEW_PARAM:
				return ((InternalEList)getViewParam()).basicRemove(otherEnd, msgs);
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
			case FacesConfigPackage.REDIRECT_TYPE__VIEW_PARAM:
				return getViewParam();
			case FacesConfigPackage.REDIRECT_TYPE__ID:
				return getId();
			case FacesConfigPackage.REDIRECT_TYPE__INCLUDE_VIEW_PARAMS:
				return isIncludeViewParams() ? Boolean.TRUE : Boolean.FALSE;
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
			case FacesConfigPackage.REDIRECT_TYPE__VIEW_PARAM:
				getViewParam().clear();
				getViewParam().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.REDIRECT_TYPE__ID:
				setId((String)newValue);
				return;
			case FacesConfigPackage.REDIRECT_TYPE__INCLUDE_VIEW_PARAMS:
				setIncludeViewParams(((Boolean)newValue).booleanValue());
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
			case FacesConfigPackage.REDIRECT_TYPE__VIEW_PARAM:
				getViewParam().clear();
				return;
			case FacesConfigPackage.REDIRECT_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
			case FacesConfigPackage.REDIRECT_TYPE__INCLUDE_VIEW_PARAMS:
				setIncludeViewParams(INCLUDE_VIEW_PARAMS_EDEFAULT);
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
			case FacesConfigPackage.REDIRECT_TYPE__VIEW_PARAM:
				return viewParam != null && !viewParam.isEmpty();
			case FacesConfigPackage.REDIRECT_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case FacesConfigPackage.REDIRECT_TYPE__INCLUDE_VIEW_PARAMS:
				return includeViewParams != INCLUDE_VIEW_PARAMS_EDEFAULT;
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
		result.append(", includeViewParams: "); //$NON-NLS-1$
		result.append(includeViewParams);
		result.append(')');
		return result.toString();
	}

} //RedirectTypeImpl
