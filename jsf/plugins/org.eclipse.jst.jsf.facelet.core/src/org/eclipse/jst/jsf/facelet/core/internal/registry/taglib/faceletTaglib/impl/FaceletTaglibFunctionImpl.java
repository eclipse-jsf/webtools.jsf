/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglibFunctionImpl.java,v 1.1 2010/03/18 06:24:38 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FullyQualifiedClass;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Facelet Taglib Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibFunctionImpl#getFunctionName <em>Function Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibFunctionImpl#getFunctionClass <em>Function Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibFunctionImpl#getFunctionSignature <em>Function Signature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FaceletTaglibFunctionImpl extends UserVisibleTaglibObjectImpl implements FaceletTaglibFunction
{
    /**
     * The cached value of the '{@link #getFunctionName() <em>Function Name</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFunctionName()
     * @generated
     * @ordered
     */
    protected IdentifiableStringValue functionName;

    /**
     * The cached value of the '{@link #getFunctionClass() <em>Function Class</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFunctionClass()
     * @generated
     * @ordered
     */
    protected FullyQualifiedClass functionClass;

    /**
     * The cached value of the '{@link #getFunctionSignature() <em>Function Signature</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFunctionSignature()
     * @generated
     * @ordered
     */
    protected IdentifiableStringValue functionSignature;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FaceletTaglibFunctionImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return FaceletTaglibPackage.Literals.FACELET_TAGLIB_FUNCTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IdentifiableStringValue getFunctionName()
    {
        return functionName;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newFunctionName 
     * @param msgs 
     * @return the notification chain
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFunctionName(IdentifiableStringValue newFunctionName, NotificationChain msgs)
    {
        IdentifiableStringValue oldFunctionName = functionName;
        functionName = newFunctionName;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_NAME, oldFunctionName, newFunctionName);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFunctionName(IdentifiableStringValue newFunctionName)
    {
        if (newFunctionName != functionName)
        {
            NotificationChain msgs = null;
            if (functionName != null)
                msgs = ((InternalEObject)functionName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_NAME, null, msgs);
            if (newFunctionName != null)
                msgs = ((InternalEObject)newFunctionName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_NAME, null, msgs);
            msgs = basicSetFunctionName(newFunctionName, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_NAME, newFunctionName, newFunctionName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FullyQualifiedClass getFunctionClass()
    {
        return functionClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newFunctionClass 
     * @param msgs 
     * @return the notification chain. 
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFunctionClass(FullyQualifiedClass newFunctionClass, NotificationChain msgs)
    {
        FullyQualifiedClass oldFunctionClass = functionClass;
        functionClass = newFunctionClass;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_CLASS, oldFunctionClass, newFunctionClass);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFunctionClass(FullyQualifiedClass newFunctionClass)
    {
        if (newFunctionClass != functionClass)
        {
            NotificationChain msgs = null;
            if (functionClass != null)
                msgs = ((InternalEObject)functionClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_CLASS, null, msgs);
            if (newFunctionClass != null)
                msgs = ((InternalEObject)newFunctionClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_CLASS, null, msgs);
            msgs = basicSetFunctionClass(newFunctionClass, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_CLASS, newFunctionClass, newFunctionClass));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IdentifiableStringValue getFunctionSignature()
    {
        return functionSignature;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newFunctionSignature 
     * @param msgs 
     * @return the notification chain.
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFunctionSignature(IdentifiableStringValue newFunctionSignature, NotificationChain msgs)
    {
        IdentifiableStringValue oldFunctionSignature = functionSignature;
        functionSignature = newFunctionSignature;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_SIGNATURE, oldFunctionSignature, newFunctionSignature);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFunctionSignature(IdentifiableStringValue newFunctionSignature)
    {
        if (newFunctionSignature != functionSignature)
        {
            NotificationChain msgs = null;
            if (functionSignature != null)
                msgs = ((InternalEObject)functionSignature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_SIGNATURE, null, msgs);
            if (newFunctionSignature != null)
                msgs = ((InternalEObject)newFunctionSignature).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_SIGNATURE, null, msgs);
            msgs = basicSetFunctionSignature(newFunctionSignature, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_SIGNATURE, newFunctionSignature, newFunctionSignature));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_NAME:
                return basicSetFunctionName(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_CLASS:
                return basicSetFunctionClass(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_SIGNATURE:
                return basicSetFunctionSignature(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_NAME:
                return getFunctionName();
            case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_CLASS:
                return getFunctionClass();
            case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_SIGNATURE:
                return getFunctionSignature();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue)
    {
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_NAME:
                setFunctionName((IdentifiableStringValue)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_CLASS:
                setFunctionClass((FullyQualifiedClass)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_SIGNATURE:
                setFunctionSignature((IdentifiableStringValue)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID)
    {
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_NAME:
                setFunctionName((IdentifiableStringValue)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_CLASS:
                setFunctionClass((FullyQualifiedClass)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_SIGNATURE:
                setFunctionSignature((IdentifiableStringValue)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID)
    {
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_NAME:
                return functionName != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_CLASS:
                return functionClass != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_FUNCTION__FUNCTION_SIGNATURE:
                return functionSignature != null;
        }
        return super.eIsSet(featureID);
    }

} //FaceletTaglibFunctionImpl
