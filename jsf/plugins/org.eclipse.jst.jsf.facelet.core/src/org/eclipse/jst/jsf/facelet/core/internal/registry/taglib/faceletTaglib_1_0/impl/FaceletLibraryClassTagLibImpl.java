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
 * $Id: FaceletLibraryClassTagLibImpl.java,v 1.1 2010/03/18 06:24:28 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletLibraryClassTagLib;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Facelet Library Class Tag Lib</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.impl.FaceletLibraryClassTagLibImpl#getLibraryClass <em>Library Class</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FaceletLibraryClassTagLibImpl extends FaceletTaglibDefnImpl implements FaceletLibraryClassTagLib
{
    /**
     * The default value of the '{@link #getLibraryClass() <em>Library Class</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLibraryClass()
     * @generated
     * @ordered
     */
    protected static final String LIBRARY_CLASS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLibraryClass() <em>Library Class</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLibraryClass()
     * @generated
     * @ordered
     */
    protected String libraryClass = LIBRARY_CLASS_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FaceletLibraryClassTagLibImpl()
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
        return FaceletTaglib_1_0Package.Literals.FACELET_LIBRARY_CLASS_TAG_LIB;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLibraryClass()
    {
        return libraryClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLibraryClass(String newLibraryClass)
    {
        String oldLibraryClass = libraryClass;
        libraryClass = newLibraryClass;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglib_1_0Package.FACELET_LIBRARY_CLASS_TAG_LIB__LIBRARY_CLASS, oldLibraryClass, libraryClass));
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
            case FaceletTaglib_1_0Package.FACELET_LIBRARY_CLASS_TAG_LIB__LIBRARY_CLASS:
                return getLibraryClass();
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
            case FaceletTaglib_1_0Package.FACELET_LIBRARY_CLASS_TAG_LIB__LIBRARY_CLASS:
                setLibraryClass((String)newValue);
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
            case FaceletTaglib_1_0Package.FACELET_LIBRARY_CLASS_TAG_LIB__LIBRARY_CLASS:
                setLibraryClass(LIBRARY_CLASS_EDEFAULT);
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
            case FaceletTaglib_1_0Package.FACELET_LIBRARY_CLASS_TAG_LIB__LIBRARY_CLASS:
                return LIBRARY_CLASS_EDEFAULT == null ? libraryClass != null : !LIBRARY_CLASS_EDEFAULT.equals(libraryClass);
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
        result.append(" (libraryClass: "); //$NON-NLS-1$
        result.append(libraryClass);
        result.append(')');
        return result.toString();
    }

} //FaceletLibraryClassTagLibImpl
