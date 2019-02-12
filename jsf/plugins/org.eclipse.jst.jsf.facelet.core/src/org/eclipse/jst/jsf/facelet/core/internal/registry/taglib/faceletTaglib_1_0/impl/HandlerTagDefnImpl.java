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
 * $Id: HandlerTagDefnImpl.java,v 1.1 2010/03/18 06:24:28 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.HandlerTagDefn;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Handler Tag Defn</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.impl.HandlerTagDefnImpl#getHandlerClass <em>Handler Class</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HandlerTagDefnImpl extends TagDefnImpl implements HandlerTagDefn
{
    /**
     * The default value of the '{@link #getHandlerClass() <em>Handler Class</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHandlerClass()
     * @generated
     * @ordered
     */
    protected static final String HANDLER_CLASS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getHandlerClass() <em>Handler Class</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHandlerClass()
     * @generated
     * @ordered
     */
    protected String handlerClass = HANDLER_CLASS_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected HandlerTagDefnImpl()
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
        return FaceletTaglib_1_0Package.Literals.HANDLER_TAG_DEFN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getHandlerClass()
    {
        return handlerClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHandlerClass(String newHandlerClass)
    {
        String oldHandlerClass = handlerClass;
        handlerClass = newHandlerClass;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglib_1_0Package.HANDLER_TAG_DEFN__HANDLER_CLASS, oldHandlerClass, handlerClass));
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
            case FaceletTaglib_1_0Package.HANDLER_TAG_DEFN__HANDLER_CLASS:
                return getHandlerClass();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue)
    {
        switch (featureID)
        {
            case FaceletTaglib_1_0Package.HANDLER_TAG_DEFN__HANDLER_CLASS:
                setHandlerClass((String)newValue);
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
            case FaceletTaglib_1_0Package.HANDLER_TAG_DEFN__HANDLER_CLASS:
                setHandlerClass(HANDLER_CLASS_EDEFAULT);
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
            case FaceletTaglib_1_0Package.HANDLER_TAG_DEFN__HANDLER_CLASS:
                return HANDLER_CLASS_EDEFAULT == null ? handlerClass != null : !HANDLER_CLASS_EDEFAULT.equals(handlerClass);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString()
    {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (handlerClass: "); //$NON-NLS-1$
        result.append(handlerClass);
        result.append(')');
        return result.toString();
    }

} //HandlerTagDefnImpl
