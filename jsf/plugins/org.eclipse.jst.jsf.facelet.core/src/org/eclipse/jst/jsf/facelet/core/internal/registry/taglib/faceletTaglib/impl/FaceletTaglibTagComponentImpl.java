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
 * $Id: FaceletTaglibTagComponentImpl.java,v 1.1 2010/03/18 06:24:39 cbateman Exp $
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
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponentExtension;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FullyQualifiedClass;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Facelet Taglib Tag Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagComponentImpl#getComponentType <em>Component Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagComponentImpl#getRendererType <em>Renderer Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagComponentImpl#getHandlerClass <em>Handler Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagComponentImpl#getComponentExtension <em>Component Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FaceletTaglibTagComponentImpl extends UserVisibleTaglibObjectImpl implements FaceletTaglibTagComponent
{
    /**
     * The cached value of the '{@link #getComponentType() <em>Component Type</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getComponentType()
     * @generated
     * @ordered
     */
    protected IdentifiableStringValue componentType;

    /**
     * The cached value of the '{@link #getRendererType() <em>Renderer Type</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRendererType()
     * @generated
     * @ordered
     */
    protected IdentifiableStringValue rendererType;

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
     * The cached value of the '{@link #getComponentExtension() <em>Component Extension</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getComponentExtension()
     * @generated
     * @ordered
     */
    protected EList<FaceletTaglibTagComponentExtension> componentExtension;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FaceletTaglibTagComponentImpl()
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
        return FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG_COMPONENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IdentifiableStringValue getComponentType()
    {
        return componentType;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newComponentType 
     * @param msgs 
     * @return the notification chain
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetComponentType(IdentifiableStringValue newComponentType, NotificationChain msgs)
    {
        IdentifiableStringValue oldComponentType = componentType;
        componentType = newComponentType;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_TYPE, oldComponentType, newComponentType);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setComponentType(IdentifiableStringValue newComponentType)
    {
        if (newComponentType != componentType)
        {
            NotificationChain msgs = null;
            if (componentType != null)
                msgs = ((InternalEObject)componentType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_TYPE, null, msgs);
            if (newComponentType != null)
                msgs = ((InternalEObject)newComponentType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_TYPE, null, msgs);
            msgs = basicSetComponentType(newComponentType, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_TYPE, newComponentType, newComponentType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IdentifiableStringValue getRendererType()
    {
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
    public NotificationChain basicSetRendererType(IdentifiableStringValue newRendererType, NotificationChain msgs)
    {
        IdentifiableStringValue oldRendererType = rendererType;
        rendererType = newRendererType;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__RENDERER_TYPE, oldRendererType, newRendererType);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRendererType(IdentifiableStringValue newRendererType)
    {
        if (newRendererType != rendererType)
        {
            NotificationChain msgs = null;
            if (rendererType != null)
                msgs = ((InternalEObject)rendererType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__RENDERER_TYPE, null, msgs);
            if (newRendererType != null)
                msgs = ((InternalEObject)newRendererType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__RENDERER_TYPE, null, msgs);
            msgs = basicSetRendererType(newRendererType, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__RENDERER_TYPE, newRendererType, newRendererType));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__HANDLER_CLASS, oldHandlerClass, newHandlerClass);
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
                msgs = ((InternalEObject)handlerClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__HANDLER_CLASS, null, msgs);
            if (newHandlerClass != null)
                msgs = ((InternalEObject)newHandlerClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__HANDLER_CLASS, null, msgs);
            msgs = basicSetHandlerClass(newHandlerClass, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__HANDLER_CLASS, newHandlerClass, newHandlerClass));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<FaceletTaglibTagComponentExtension> getComponentExtension()
    {
        if (componentExtension == null)
        {
            componentExtension = new EObjectContainmentEList<FaceletTaglibTagComponentExtension>(FaceletTaglibTagComponentExtension.class, this, FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_EXTENSION);
        }
        return componentExtension;
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
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_TYPE:
                return basicSetComponentType(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__RENDERER_TYPE:
                return basicSetRendererType(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__HANDLER_CLASS:
                return basicSetHandlerClass(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_EXTENSION:
                return ((InternalEList<?>)getComponentExtension()).basicRemove(otherEnd, msgs);
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
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_TYPE:
                return getComponentType();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__RENDERER_TYPE:
                return getRendererType();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__HANDLER_CLASS:
                return getHandlerClass();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_EXTENSION:
                return getComponentExtension();
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
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_TYPE:
                setComponentType((IdentifiableStringValue)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__RENDERER_TYPE:
                setRendererType((IdentifiableStringValue)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__HANDLER_CLASS:
                setHandlerClass((FullyQualifiedClass)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_EXTENSION:
                getComponentExtension().clear();
                getComponentExtension().addAll((Collection<? extends FaceletTaglibTagComponentExtension>)newValue);
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
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_TYPE:
                setComponentType((IdentifiableStringValue)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__RENDERER_TYPE:
                setRendererType((IdentifiableStringValue)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__HANDLER_CLASS:
                setHandlerClass((FullyQualifiedClass)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_EXTENSION:
                getComponentExtension().clear();
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
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_TYPE:
                return componentType != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__RENDERER_TYPE:
                return rendererType != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__HANDLER_CLASS:
                return handlerClass != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_COMPONENT__COMPONENT_EXTENSION:
                return componentExtension != null && !componentExtension.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //FaceletTaglibTagComponentImpl
