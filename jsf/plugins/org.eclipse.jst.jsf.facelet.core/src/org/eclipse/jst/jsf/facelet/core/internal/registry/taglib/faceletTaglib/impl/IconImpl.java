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
 * $Id: IconImpl.java,v 1.1 2010/03/18 06:24:37 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Path;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Icon</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.IconImpl#getSmallIcon <em>Small Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.IconImpl#getLargeIcon <em>Large Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.IconImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.IconImpl#getLang <em>Lang</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IconImpl extends EObjectImpl implements Icon
{
    /**
     * The cached value of the '{@link #getSmallIcon() <em>Small Icon</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSmallIcon()
     * @generated
     * @ordered
     */
    protected Path smallIcon;

    /**
     * The cached value of the '{@link #getLargeIcon() <em>Large Icon</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLargeIcon()
     * @generated
     * @ordered
     */
    protected Path largeIcon;

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
     * The default value of the '{@link #getLang() <em>Lang</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLang()
     * @generated
     * @ordered
     */
    protected static final String LANG_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLang() <em>Lang</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLang()
     * @generated
     * @ordered
     */
    protected String lang = LANG_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IconImpl()
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
        return FaceletTaglibPackage.Literals.ICON;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Path getSmallIcon()
    {
        return smallIcon;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newSmallIcon 
     * @param msgs 
     * @return the notification chain
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSmallIcon(Path newSmallIcon, NotificationChain msgs)
    {
        Path oldSmallIcon = smallIcon;
        smallIcon = newSmallIcon;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.ICON__SMALL_ICON, oldSmallIcon, newSmallIcon);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSmallIcon(Path newSmallIcon)
    {
        if (newSmallIcon != smallIcon)
        {
            NotificationChain msgs = null;
            if (smallIcon != null)
                msgs = ((InternalEObject)smallIcon).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.ICON__SMALL_ICON, null, msgs);
            if (newSmallIcon != null)
                msgs = ((InternalEObject)newSmallIcon).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.ICON__SMALL_ICON, null, msgs);
            msgs = basicSetSmallIcon(newSmallIcon, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.ICON__SMALL_ICON, newSmallIcon, newSmallIcon));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Path getLargeIcon()
    {
        return largeIcon;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newLargeIcon 
     * @param msgs 
     * @return the notification chain.
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLargeIcon(Path newLargeIcon, NotificationChain msgs)
    {
        Path oldLargeIcon = largeIcon;
        largeIcon = newLargeIcon;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.ICON__LARGE_ICON, oldLargeIcon, newLargeIcon);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLargeIcon(Path newLargeIcon)
    {
        if (newLargeIcon != largeIcon)
        {
            NotificationChain msgs = null;
            if (largeIcon != null)
                msgs = ((InternalEObject)largeIcon).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.ICON__LARGE_ICON, null, msgs);
            if (newLargeIcon != null)
                msgs = ((InternalEObject)newLargeIcon).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.ICON__LARGE_ICON, null, msgs);
            msgs = basicSetLargeIcon(newLargeIcon, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.ICON__LARGE_ICON, newLargeIcon, newLargeIcon));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getId()
    {
        return id;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setId(String newId)
    {
        String oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.ICON__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLang()
    {
        return lang;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLang(String newLang)
    {
        String oldLang = lang;
        lang = newLang;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.ICON__LANG, oldLang, lang));
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
            case FaceletTaglibPackage.ICON__SMALL_ICON:
                return basicSetSmallIcon(null, msgs);
            case FaceletTaglibPackage.ICON__LARGE_ICON:
                return basicSetLargeIcon(null, msgs);
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
            case FaceletTaglibPackage.ICON__SMALL_ICON:
                return getSmallIcon();
            case FaceletTaglibPackage.ICON__LARGE_ICON:
                return getLargeIcon();
            case FaceletTaglibPackage.ICON__ID:
                return getId();
            case FaceletTaglibPackage.ICON__LANG:
                return getLang();
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
            case FaceletTaglibPackage.ICON__SMALL_ICON:
                setSmallIcon((Path)newValue);
                return;
            case FaceletTaglibPackage.ICON__LARGE_ICON:
                setLargeIcon((Path)newValue);
                return;
            case FaceletTaglibPackage.ICON__ID:
                setId((String)newValue);
                return;
            case FaceletTaglibPackage.ICON__LANG:
                setLang((String)newValue);
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
            case FaceletTaglibPackage.ICON__SMALL_ICON:
                setSmallIcon((Path)null);
                return;
            case FaceletTaglibPackage.ICON__LARGE_ICON:
                setLargeIcon((Path)null);
                return;
            case FaceletTaglibPackage.ICON__ID:
                setId(ID_EDEFAULT);
                return;
            case FaceletTaglibPackage.ICON__LANG:
                setLang(LANG_EDEFAULT);
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
            case FaceletTaglibPackage.ICON__SMALL_ICON:
                return smallIcon != null;
            case FaceletTaglibPackage.ICON__LARGE_ICON:
                return largeIcon != null;
            case FaceletTaglibPackage.ICON__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case FaceletTaglibPackage.ICON__LANG:
                return LANG_EDEFAULT == null ? lang != null : !LANG_EDEFAULT.equals(lang);
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
        result.append(" (id: "); //$NON-NLS-1$
        result.append(id);
        result.append(", lang: "); //$NON-NLS-1$
        result.append(lang);
        result.append(')');
        return result.toString();
    }

} //IconImpl
