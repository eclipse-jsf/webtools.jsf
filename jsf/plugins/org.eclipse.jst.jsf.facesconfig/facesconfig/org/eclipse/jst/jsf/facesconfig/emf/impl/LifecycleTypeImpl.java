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
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleType;
import org.eclipse.jst.jsf.facesconfig.emf.PhaseListenerType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lifecycle Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.LifecycleTypeImpl#getPhaseListener <em>Phase Listener</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.LifecycleTypeImpl#getLifecycleExtension <em>Lifecycle Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.LifecycleTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LifecycleTypeImpl extends EObjectImpl implements LifecycleType {
    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

    /**
     * The cached value of the '{@link #getPhaseListener() <em>Phase Listener</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPhaseListener()
     * @generated
     * @ordered
     */
	protected EList phaseListener = null;

    /**
     * The cached value of the '{@link #getLifecycleExtension() <em>Lifecycle Extension</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLifecycleExtension()
     * @generated
     * @ordered
     */
    protected EList lifecycleExtension = null;

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
	protected LifecycleTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return FacesConfigPackage.Literals.LIFECYCLE_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getPhaseListener() {
        if (phaseListener == null) {
            phaseListener = new EObjectContainmentEList(PhaseListenerType.class, this, FacesConfigPackage.LIFECYCLE_TYPE__PHASE_LISTENER);
        }
        return phaseListener;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getLifecycleExtension() {
        if (lifecycleExtension == null) {
            lifecycleExtension = new EObjectContainmentEList(LifecycleExtensionType.class, this, FacesConfigPackage.LIFECYCLE_TYPE__LIFECYCLE_EXTENSION);
        }
        return lifecycleExtension;
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
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.LIFECYCLE_TYPE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FacesConfigPackage.LIFECYCLE_TYPE__PHASE_LISTENER:
                return ((InternalEList)getPhaseListener()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.LIFECYCLE_TYPE__LIFECYCLE_EXTENSION:
                return ((InternalEList)getLifecycleExtension()).basicRemove(otherEnd, msgs);
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
            case FacesConfigPackage.LIFECYCLE_TYPE__PHASE_LISTENER:
                return getPhaseListener();
            case FacesConfigPackage.LIFECYCLE_TYPE__LIFECYCLE_EXTENSION:
                return getLifecycleExtension();
            case FacesConfigPackage.LIFECYCLE_TYPE__ID:
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
            case FacesConfigPackage.LIFECYCLE_TYPE__PHASE_LISTENER:
                getPhaseListener().clear();
                getPhaseListener().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.LIFECYCLE_TYPE__LIFECYCLE_EXTENSION:
                getLifecycleExtension().clear();
                getLifecycleExtension().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.LIFECYCLE_TYPE__ID:
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
            case FacesConfigPackage.LIFECYCLE_TYPE__PHASE_LISTENER:
                getPhaseListener().clear();
                return;
            case FacesConfigPackage.LIFECYCLE_TYPE__LIFECYCLE_EXTENSION:
                getLifecycleExtension().clear();
                return;
            case FacesConfigPackage.LIFECYCLE_TYPE__ID:
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
            case FacesConfigPackage.LIFECYCLE_TYPE__PHASE_LISTENER:
                return phaseListener != null && !phaseListener.isEmpty();
            case FacesConfigPackage.LIFECYCLE_TYPE__LIFECYCLE_EXTENSION:
                return lifecycleExtension != null && !lifecycleExtension.isEmpty();
            case FacesConfigPackage.LIFECYCLE_TYPE__ID:
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

} //LifecycleTypeImpl
