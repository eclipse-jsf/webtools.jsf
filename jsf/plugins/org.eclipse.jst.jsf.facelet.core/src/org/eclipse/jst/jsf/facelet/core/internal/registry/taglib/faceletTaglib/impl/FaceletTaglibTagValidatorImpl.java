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
 * $Id: FaceletTaglibTagValidatorImpl.java,v 1.1 2010/03/18 06:24:38 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidator;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidatorExtension;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FullyQualifiedClass;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Facelet Taglib Tag Validator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagValidatorImpl#getValidatorId <em>Validator Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagValidatorImpl#getHandlerClass <em>Handler Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagValidatorImpl#getValidatorExtension <em>Validator Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FaceletTaglibTagValidatorImpl extends UserVisibleTaglibObjectImpl implements FaceletTaglibTagValidator
{
    /**
     * The cached value of the '{@link #getValidatorId() <em>Validator Id</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValidatorId()
     * @generated
     * @ordered
     */
    protected IdentifiableStringValue validatorId;

    /**
     * The cached value of the '{@link #getHandlerClass() <em>Handler Class</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHandlerClass()
     * @generated
     * @ordered
     */
    protected FullyQualifiedClass handlerClass;

    /**
     * The cached value of the '{@link #getValidatorExtension() <em>Validator Extension</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValidatorExtension()
     * @generated
     * @ordered
     */
    protected EList<FaceletTaglibTagValidatorExtension> validatorExtension;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FaceletTaglibTagValidatorImpl()
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
        return FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG_VALIDATOR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IdentifiableStringValue getValidatorId()
    {
        return validatorId;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newValidatorId 
     * @param msgs 
     * @return the notification chain
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetValidatorId(IdentifiableStringValue newValidatorId, NotificationChain msgs)
    {
        IdentifiableStringValue oldValidatorId = validatorId;
        validatorId = newValidatorId;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_ID, oldValidatorId, newValidatorId);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValidatorId(IdentifiableStringValue newValidatorId)
    {
        if (newValidatorId != validatorId)
        {
            NotificationChain msgs = null;
            if (validatorId != null)
                msgs = ((InternalEObject)validatorId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_ID, null, msgs);
            if (newValidatorId != null)
                msgs = ((InternalEObject)newValidatorId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_ID, null, msgs);
            msgs = basicSetValidatorId(newValidatorId, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_ID, newValidatorId, newValidatorId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FullyQualifiedClass getHandlerClass()
    {
        return handlerClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newHandlerClass 
     * @param msgs 
     * @return the notification chain
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetHandlerClass(FullyQualifiedClass newHandlerClass, NotificationChain msgs)
    {
        FullyQualifiedClass oldHandlerClass = handlerClass;
        handlerClass = newHandlerClass;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__HANDLER_CLASS, oldHandlerClass, newHandlerClass);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHandlerClass(FullyQualifiedClass newHandlerClass)
    {
        if (newHandlerClass != handlerClass)
        {
            NotificationChain msgs = null;
            if (handlerClass != null)
                msgs = ((InternalEObject)handlerClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__HANDLER_CLASS, null, msgs);
            if (newHandlerClass != null)
                msgs = ((InternalEObject)newHandlerClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__HANDLER_CLASS, null, msgs);
            msgs = basicSetHandlerClass(newHandlerClass, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__HANDLER_CLASS, newHandlerClass, newHandlerClass));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<FaceletTaglibTagValidatorExtension> getValidatorExtension()
    {
        if (validatorExtension == null)
        {
            validatorExtension = new EObjectContainmentEList<FaceletTaglibTagValidatorExtension>(FaceletTaglibTagValidatorExtension.class, this, FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_EXTENSION);
        }
        return validatorExtension;
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
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_ID:
                return basicSetValidatorId(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__HANDLER_CLASS:
                return basicSetHandlerClass(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_EXTENSION:
                return ((InternalEList<?>)getValidatorExtension()).basicRemove(otherEnd, msgs);
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
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_ID:
                return getValidatorId();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__HANDLER_CLASS:
                return getHandlerClass();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_EXTENSION:
                return getValidatorExtension();
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
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_ID:
                setValidatorId((IdentifiableStringValue)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__HANDLER_CLASS:
                setHandlerClass((FullyQualifiedClass)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_EXTENSION:
                getValidatorExtension().clear();
                getValidatorExtension().addAll((Collection<? extends FaceletTaglibTagValidatorExtension>)newValue);
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
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_ID:
                setValidatorId((IdentifiableStringValue)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__HANDLER_CLASS:
                setHandlerClass((FullyQualifiedClass)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_EXTENSION:
                getValidatorExtension().clear();
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
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_ID:
                return validatorId != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__HANDLER_CLASS:
                return handlerClass != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_VALIDATOR__VALIDATOR_EXTENSION:
                return validatorExtension != null && !validatorExtension.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //FaceletTaglibTagValidatorImpl
