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
 * $Id: FaceletTaglibTagConverterImpl.java,v 1.1 2010/03/18 06:24:38 cbateman Exp $
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
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverterExtension;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FullyQualifiedClass;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Facelet Taglib Tag Converter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagConverterImpl#getConverterId <em>Converter Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagConverterImpl#getHandlerClass <em>Handler Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagConverterImpl#getConverterExtension <em>Converter Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FaceletTaglibTagConverterImpl extends UserVisibleTaglibObjectImpl implements FaceletTaglibTagConverter
{
    /**
     * The cached value of the '{@link #getConverterId() <em>Converter Id</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConverterId()
     * @generated
     * @ordered
     */
    protected IdentifiableStringValue converterId;

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
     * The cached value of the '{@link #getConverterExtension() <em>Converter Extension</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConverterExtension()
     * @generated
     * @ordered
     */
    protected EList<FaceletTaglibTagConverterExtension> converterExtension;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FaceletTaglibTagConverterImpl()
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
        return FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG_CONVERTER;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IdentifiableStringValue getConverterId()
    {
        return converterId;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newConverterId 
     * @param msgs 
     * @return the notification chain
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetConverterId(IdentifiableStringValue newConverterId, NotificationChain msgs)
    {
        IdentifiableStringValue oldConverterId = converterId;
        converterId = newConverterId;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_ID, oldConverterId, newConverterId);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConverterId(IdentifiableStringValue newConverterId)
    {
        if (newConverterId != converterId)
        {
            NotificationChain msgs = null;
            if (converterId != null)
                msgs = ((InternalEObject)converterId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_ID, null, msgs);
            if (newConverterId != null)
                msgs = ((InternalEObject)newConverterId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_ID, null, msgs);
            msgs = basicSetConverterId(newConverterId, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_ID, newConverterId, newConverterId));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__HANDLER_CLASS, oldHandlerClass, newHandlerClass);
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
                msgs = ((InternalEObject)handlerClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__HANDLER_CLASS, null, msgs);
            if (newHandlerClass != null)
                msgs = ((InternalEObject)newHandlerClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__HANDLER_CLASS, null, msgs);
            msgs = basicSetHandlerClass(newHandlerClass, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__HANDLER_CLASS, newHandlerClass, newHandlerClass));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<FaceletTaglibTagConverterExtension> getConverterExtension()
    {
        if (converterExtension == null)
        {
            converterExtension = new EObjectContainmentEList<FaceletTaglibTagConverterExtension>(FaceletTaglibTagConverterExtension.class, this, FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_EXTENSION);
        }
        return converterExtension;
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
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_ID:
                return basicSetConverterId(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__HANDLER_CLASS:
                return basicSetHandlerClass(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_EXTENSION:
                return ((InternalEList<?>)getConverterExtension()).basicRemove(otherEnd, msgs);
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
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_ID:
                return getConverterId();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__HANDLER_CLASS:
                return getHandlerClass();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_EXTENSION:
                return getConverterExtension();
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
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_ID:
                setConverterId((IdentifiableStringValue)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__HANDLER_CLASS:
                setHandlerClass((FullyQualifiedClass)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_EXTENSION:
                getConverterExtension().clear();
                getConverterExtension().addAll((Collection<? extends FaceletTaglibTagConverterExtension>)newValue);
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
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_ID:
                setConverterId((IdentifiableStringValue)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__HANDLER_CLASS:
                setHandlerClass((FullyQualifiedClass)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_EXTENSION:
                getConverterExtension().clear();
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
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_ID:
                return converterId != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__HANDLER_CLASS:
                return handlerClass != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_CONVERTER__CONVERTER_EXTENSION:
                return converterExtension != null && !converterExtension.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //FaceletTaglibTagConverterImpl
