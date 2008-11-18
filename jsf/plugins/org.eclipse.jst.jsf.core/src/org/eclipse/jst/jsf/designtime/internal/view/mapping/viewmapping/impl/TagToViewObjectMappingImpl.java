/**
 * <copyright>
 * </copyright>
 *
 * $Id: TagToViewObjectMappingImpl.java,v 1.2 2008/11/18 22:23:57 gkessler Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ClassTypeInfo_;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tag To View Object Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.TagToViewObjectMappingImpl#getTypeInfo <em>Type Info</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.TagToViewObjectMappingImpl#getMinJSFVersion <em>Min JSF Version</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.TagToViewObjectMappingImpl#getMinLibraryVersion <em>Min Library Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TagToViewObjectMappingImpl extends EObjectImpl implements TagToViewObjectMapping
{
    /**
     * The cached value of the '{@link #getTypeInfo() <em>Type Info</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypeInfo()
     * @generated
     * @ordered
     */
    protected ClassTypeInfo_ typeInfo;

    /**
     * The default value of the '{@link #getMinJSFVersion() <em>Min JSF Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinJSFVersion()
     * @generated
     * @ordered
     */
    protected static final String MIN_JSF_VERSION_EDEFAULT = "1.1"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getMinJSFVersion() <em>Min JSF Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinJSFVersion()
     * @generated
     * @ordered
     */
    protected String minJSFVersion = MIN_JSF_VERSION_EDEFAULT;

    /**
     * The default value of the '{@link #getMinLibraryVersion() <em>Min Library Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinLibraryVersion()
     * @generated
     * @ordered
     */
    protected static final String MIN_LIBRARY_VERSION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getMinLibraryVersion() <em>Min Library Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinLibraryVersion()
     * @generated
     * @ordered
     */
    protected String minLibraryVersion = MIN_LIBRARY_VERSION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TagToViewObjectMappingImpl()
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
        return ComponentMappingPackage.Literals.TAG_TO_VIEW_OBJECT_MAPPING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ClassTypeInfo_ getTypeInfo()
    {
        return typeInfo;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newTypeInfo 
     * @param msgs 
     * @return the notification chain
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTypeInfo(ClassTypeInfo_ newTypeInfo, NotificationChain msgs)
    {
        ClassTypeInfo_ oldTypeInfo = typeInfo;
        typeInfo = newTypeInfo;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__TYPE_INFO, oldTypeInfo, newTypeInfo);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTypeInfo(ClassTypeInfo_ newTypeInfo)
    {
        if (newTypeInfo != typeInfo)
        {
            NotificationChain msgs = null;
            if (typeInfo != null)
                msgs = ((InternalEObject)typeInfo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__TYPE_INFO, null, msgs);
            if (newTypeInfo != null)
                msgs = ((InternalEObject)newTypeInfo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__TYPE_INFO, null, msgs);
            msgs = basicSetTypeInfo(newTypeInfo, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__TYPE_INFO, newTypeInfo, newTypeInfo));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getMinJSFVersion()
    {
        return minJSFVersion;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMinJSFVersion(String newMinJSFVersion)
    {
        String oldMinJSFVersion = minJSFVersion;
        minJSFVersion = newMinJSFVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__MIN_JSF_VERSION, oldMinJSFVersion, minJSFVersion));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getMinLibraryVersion()
    {
        return minLibraryVersion;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMinLibraryVersion(String newMinLibraryVersion)
    {
        String oldMinLibraryVersion = minLibraryVersion;
        minLibraryVersion = newMinLibraryVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__MIN_LIBRARY_VERSION, oldMinLibraryVersion, minLibraryVersion));
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
            case ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__TYPE_INFO:
                return basicSetTypeInfo(null, msgs);
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
            case ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__TYPE_INFO:
                return getTypeInfo();
            case ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__MIN_JSF_VERSION:
                return getMinJSFVersion();
            case ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__MIN_LIBRARY_VERSION:
                return getMinLibraryVersion();
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
            case ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__TYPE_INFO:
                setTypeInfo((ClassTypeInfo_)newValue);
                return;
            case ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__MIN_JSF_VERSION:
                setMinJSFVersion((String)newValue);
                return;
            case ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__MIN_LIBRARY_VERSION:
                setMinLibraryVersion((String)newValue);
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
            case ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__TYPE_INFO:
                setTypeInfo((ClassTypeInfo_)null);
                return;
            case ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__MIN_JSF_VERSION:
                setMinJSFVersion(MIN_JSF_VERSION_EDEFAULT);
                return;
            case ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__MIN_LIBRARY_VERSION:
                setMinLibraryVersion(MIN_LIBRARY_VERSION_EDEFAULT);
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
            case ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__TYPE_INFO:
                return typeInfo != null;
            case ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__MIN_JSF_VERSION:
                return MIN_JSF_VERSION_EDEFAULT == null ? minJSFVersion != null : !MIN_JSF_VERSION_EDEFAULT.equals(minJSFVersion);
            case ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING__MIN_LIBRARY_VERSION:
                return MIN_LIBRARY_VERSION_EDEFAULT == null ? minLibraryVersion != null : !MIN_LIBRARY_VERSION_EDEFAULT.equals(minLibraryVersion);
        }
        return super.eIsSet(featureID);
    }

    @Override
    public String toString()
    {
        return "TagToViewObjectMapping: typeInfo="+getTypeInfo(); //$NON-NLS-1$
    }
} //TagToViewObjectMappingImpl
