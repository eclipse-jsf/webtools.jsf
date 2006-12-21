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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.LargeIconType;
import org.eclipse.jst.jsf.facesconfig.emf.SmallIconType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Icon Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.IconTypeImpl#getSmallIcon <em>Small Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.IconTypeImpl#getLargeIcon <em>Large Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.IconTypeImpl#getLang <em>Lang</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.IconTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IconTypeImpl extends EObjectImpl implements IconType {
    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

    /**
     * The cached value of the '{@link #getSmallIcon() <em>Small Icon</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSmallIcon()
     * @generated
     * @ordered
     */
	protected SmallIconType smallIcon = null;

    /**
     * The cached value of the '{@link #getLargeIcon() <em>Large Icon</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLargeIcon()
     * @generated
     * @ordered
     */
	protected LargeIconType largeIcon = null;

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
	protected IconTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return FacesConfigPackage.Literals.ICON_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SmallIconType getSmallIcon() {
        return smallIcon;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetSmallIcon(SmallIconType newSmallIcon, NotificationChain msgs) {
        SmallIconType oldSmallIcon = smallIcon;
        smallIcon = newSmallIcon;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ICON_TYPE__SMALL_ICON, oldSmallIcon, newSmallIcon);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSmallIcon(SmallIconType newSmallIcon) {
        if (newSmallIcon != smallIcon) {
            NotificationChain msgs = null;
            if (smallIcon != null)
                msgs = ((InternalEObject)smallIcon).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ICON_TYPE__SMALL_ICON, null, msgs);
            if (newSmallIcon != null)
                msgs = ((InternalEObject)newSmallIcon).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ICON_TYPE__SMALL_ICON, null, msgs);
            msgs = basicSetSmallIcon(newSmallIcon, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ICON_TYPE__SMALL_ICON, newSmallIcon, newSmallIcon));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public LargeIconType getLargeIcon() {
        return largeIcon;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetLargeIcon(LargeIconType newLargeIcon, NotificationChain msgs) {
        LargeIconType oldLargeIcon = largeIcon;
        largeIcon = newLargeIcon;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ICON_TYPE__LARGE_ICON, oldLargeIcon, newLargeIcon);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setLargeIcon(LargeIconType newLargeIcon) {
        if (newLargeIcon != largeIcon) {
            NotificationChain msgs = null;
            if (largeIcon != null)
                msgs = ((InternalEObject)largeIcon).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ICON_TYPE__LARGE_ICON, null, msgs);
            if (newLargeIcon != null)
                msgs = ((InternalEObject)newLargeIcon).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.ICON_TYPE__LARGE_ICON, null, msgs);
            msgs = basicSetLargeIcon(newLargeIcon, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ICON_TYPE__LARGE_ICON, newLargeIcon, newLargeIcon));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getLang() {
        return lang;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setLang(String newLang) {
        String oldLang = lang;
        lang = newLang;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ICON_TYPE__LANG, oldLang, lang));
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
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.ICON_TYPE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FacesConfigPackage.ICON_TYPE__SMALL_ICON:
                return basicSetSmallIcon(null, msgs);
            case FacesConfigPackage.ICON_TYPE__LARGE_ICON:
                return basicSetLargeIcon(null, msgs);
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
            case FacesConfigPackage.ICON_TYPE__SMALL_ICON:
                return getSmallIcon();
            case FacesConfigPackage.ICON_TYPE__LARGE_ICON:
                return getLargeIcon();
            case FacesConfigPackage.ICON_TYPE__LANG:
                return getLang();
            case FacesConfigPackage.ICON_TYPE__ID:
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
            case FacesConfigPackage.ICON_TYPE__SMALL_ICON:
                setSmallIcon((SmallIconType)newValue);
                return;
            case FacesConfigPackage.ICON_TYPE__LARGE_ICON:
                setLargeIcon((LargeIconType)newValue);
                return;
            case FacesConfigPackage.ICON_TYPE__LANG:
                setLang((String)newValue);
                return;
            case FacesConfigPackage.ICON_TYPE__ID:
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
            case FacesConfigPackage.ICON_TYPE__SMALL_ICON:
                setSmallIcon((SmallIconType)null);
                return;
            case FacesConfigPackage.ICON_TYPE__LARGE_ICON:
                setLargeIcon((LargeIconType)null);
                return;
            case FacesConfigPackage.ICON_TYPE__LANG:
                setLang(LANG_EDEFAULT);
                return;
            case FacesConfigPackage.ICON_TYPE__ID:
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
            case FacesConfigPackage.ICON_TYPE__SMALL_ICON:
                return smallIcon != null;
            case FacesConfigPackage.ICON_TYPE__LARGE_ICON:
                return largeIcon != null;
            case FacesConfigPackage.ICON_TYPE__LANG:
                return LANG_EDEFAULT == null ? lang != null : !LANG_EDEFAULT.equals(lang);
            case FacesConfigPackage.ICON_TYPE__ID:
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
        result.append(" (lang: ");
        result.append(lang);
        result.append(", id: ");
        result.append(id);
        result.append(')');
        return result.toString();
    }

} //IconTypeImpl
