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
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Managed Bean Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanTypeImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanTypeImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanTypeImpl#getManagedBeanName <em>Managed Bean Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanTypeImpl#getManagedBeanClass <em>Managed Bean Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanTypeImpl#getManagedBeanScope <em>Managed Bean Scope</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanTypeImpl#getManagedProperty <em>Managed Property</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanTypeImpl#getMapEntries <em>Map Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanTypeImpl#getListEntries <em>List Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanTypeImpl#getManagedBeanExtension <em>Managed Bean Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ManagedBeanTypeImpl extends EObjectImpl implements ManagedBeanType {
    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
	protected EList description = null;

    /**
     * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDisplayName()
     * @generated
     * @ordered
     */
	protected EList displayName = null;

    /**
     * The cached value of the '{@link #getIcon() <em>Icon</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getIcon()
     * @generated
     * @ordered
     */
	protected EList icon = null;

    /**
     * The cached value of the '{@link #getManagedBeanName() <em>Managed Bean Name</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getManagedBeanName()
     * @generated
     * @ordered
     */
	protected ManagedBeanNameType managedBeanName = null;

    /**
     * The cached value of the '{@link #getManagedBeanClass() <em>Managed Bean Class</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getManagedBeanClass()
     * @generated
     * @ordered
     */
	protected ManagedBeanClassType managedBeanClass = null;

    /**
     * The cached value of the '{@link #getManagedBeanScope() <em>Managed Bean Scope</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getManagedBeanScope()
     * @generated
     * @ordered
     */
	protected ManagedBeanScopeType managedBeanScope = null;

    /**
     * The cached value of the '{@link #getManagedProperty() <em>Managed Property</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getManagedProperty()
     * @generated
     * @ordered
     */
	protected EList managedProperty = null;

    /**
     * The cached value of the '{@link #getMapEntries() <em>Map Entries</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMapEntries()
     * @generated
     * @ordered
     */
	protected MapEntriesType mapEntries = null;

    /**
     * The cached value of the '{@link #getListEntries() <em>List Entries</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getListEntries()
     * @generated
     * @ordered
     */
	protected ListEntriesType listEntries = null;

    /**
     * The cached value of the '{@link #getManagedBeanExtension() <em>Managed Bean Extension</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getManagedBeanExtension()
     * @generated
     * @ordered
     */
    protected EList managedBeanExtension = null;

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
	protected ManagedBeanTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return FacesConfigPackage.Literals.MANAGED_BEAN_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getDescription() {
        if (description == null) {
            description = new EObjectContainmentEList(DescriptionType.class, this, FacesConfigPackage.MANAGED_BEAN_TYPE__DESCRIPTION);
        }
        return description;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getDisplayName() {
        if (displayName == null) {
            displayName = new EObjectContainmentEList(DisplayNameType.class, this, FacesConfigPackage.MANAGED_BEAN_TYPE__DISPLAY_NAME);
        }
        return displayName;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getIcon() {
        if (icon == null) {
            icon = new EObjectContainmentEList(IconType.class, this, FacesConfigPackage.MANAGED_BEAN_TYPE__ICON);
        }
        return icon;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ManagedBeanNameType getManagedBeanName() {
        return managedBeanName;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetManagedBeanName(ManagedBeanNameType newManagedBeanName, NotificationChain msgs) {
        ManagedBeanNameType oldManagedBeanName = managedBeanName;
        managedBeanName = newManagedBeanName;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_NAME, oldManagedBeanName, newManagedBeanName);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setManagedBeanName(ManagedBeanNameType newManagedBeanName) {
        if (newManagedBeanName != managedBeanName) {
            NotificationChain msgs = null;
            if (managedBeanName != null)
                msgs = ((InternalEObject)managedBeanName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_NAME, null, msgs);
            if (newManagedBeanName != null)
                msgs = ((InternalEObject)newManagedBeanName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_NAME, null, msgs);
            msgs = basicSetManagedBeanName(newManagedBeanName, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_NAME, newManagedBeanName, newManagedBeanName));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ManagedBeanClassType getManagedBeanClass() {
        return managedBeanClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetManagedBeanClass(ManagedBeanClassType newManagedBeanClass, NotificationChain msgs) {
        ManagedBeanClassType oldManagedBeanClass = managedBeanClass;
        managedBeanClass = newManagedBeanClass;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_CLASS, oldManagedBeanClass, newManagedBeanClass);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setManagedBeanClass(ManagedBeanClassType newManagedBeanClass) {
        if (newManagedBeanClass != managedBeanClass) {
            NotificationChain msgs = null;
            if (managedBeanClass != null)
                msgs = ((InternalEObject)managedBeanClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_CLASS, null, msgs);
            if (newManagedBeanClass != null)
                msgs = ((InternalEObject)newManagedBeanClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_CLASS, null, msgs);
            msgs = basicSetManagedBeanClass(newManagedBeanClass, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_CLASS, newManagedBeanClass, newManagedBeanClass));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ManagedBeanScopeType getManagedBeanScope() {
        return managedBeanScope;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetManagedBeanScope(ManagedBeanScopeType newManagedBeanScope, NotificationChain msgs) {
        ManagedBeanScopeType oldManagedBeanScope = managedBeanScope;
        managedBeanScope = newManagedBeanScope;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_SCOPE, oldManagedBeanScope, newManagedBeanScope);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setManagedBeanScope(ManagedBeanScopeType newManagedBeanScope) {
        if (newManagedBeanScope != managedBeanScope) {
            NotificationChain msgs = null;
            if (managedBeanScope != null)
                msgs = ((InternalEObject)managedBeanScope).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_SCOPE, null, msgs);
            if (newManagedBeanScope != null)
                msgs = ((InternalEObject)newManagedBeanScope).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_SCOPE, null, msgs);
            msgs = basicSetManagedBeanScope(newManagedBeanScope, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_SCOPE, newManagedBeanScope, newManagedBeanScope));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getManagedProperty() {
        if (managedProperty == null) {
            managedProperty = new EObjectContainmentEList(ManagedPropertyType.class, this, FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_PROPERTY);
        }
        return managedProperty;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public MapEntriesType getMapEntries() {
        return mapEntries;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetMapEntries(MapEntriesType newMapEntries, NotificationChain msgs) {
        MapEntriesType oldMapEntries = mapEntries;
        mapEntries = newMapEntries;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_BEAN_TYPE__MAP_ENTRIES, oldMapEntries, newMapEntries);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setMapEntries(MapEntriesType newMapEntries) {
        if (newMapEntries != mapEntries) {
            NotificationChain msgs = null;
            if (mapEntries != null)
                msgs = ((InternalEObject)mapEntries).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_BEAN_TYPE__MAP_ENTRIES, null, msgs);
            if (newMapEntries != null)
                msgs = ((InternalEObject)newMapEntries).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_BEAN_TYPE__MAP_ENTRIES, null, msgs);
            msgs = basicSetMapEntries(newMapEntries, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_BEAN_TYPE__MAP_ENTRIES, newMapEntries, newMapEntries));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ListEntriesType getListEntries() {
        return listEntries;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetListEntries(ListEntriesType newListEntries, NotificationChain msgs) {
        ListEntriesType oldListEntries = listEntries;
        listEntries = newListEntries;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_BEAN_TYPE__LIST_ENTRIES, oldListEntries, newListEntries);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setListEntries(ListEntriesType newListEntries) {
        if (newListEntries != listEntries) {
            NotificationChain msgs = null;
            if (listEntries != null)
                msgs = ((InternalEObject)listEntries).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_BEAN_TYPE__LIST_ENTRIES, null, msgs);
            if (newListEntries != null)
                msgs = ((InternalEObject)newListEntries).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.MANAGED_BEAN_TYPE__LIST_ENTRIES, null, msgs);
            msgs = basicSetListEntries(newListEntries, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_BEAN_TYPE__LIST_ENTRIES, newListEntries, newListEntries));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getManagedBeanExtension() {
        if (managedBeanExtension == null) {
            managedBeanExtension = new EObjectContainmentEList(ManagedBeanExtensionType.class, this, FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_EXTENSION);
        }
        return managedBeanExtension;
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
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.MANAGED_BEAN_TYPE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FacesConfigPackage.MANAGED_BEAN_TYPE__DESCRIPTION:
                return ((InternalEList)getDescription()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.MANAGED_BEAN_TYPE__DISPLAY_NAME:
                return ((InternalEList)getDisplayName()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.MANAGED_BEAN_TYPE__ICON:
                return ((InternalEList)getIcon()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_NAME:
                return basicSetManagedBeanName(null, msgs);
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_CLASS:
                return basicSetManagedBeanClass(null, msgs);
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_SCOPE:
                return basicSetManagedBeanScope(null, msgs);
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_PROPERTY:
                return ((InternalEList)getManagedProperty()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MAP_ENTRIES:
                return basicSetMapEntries(null, msgs);
            case FacesConfigPackage.MANAGED_BEAN_TYPE__LIST_ENTRIES:
                return basicSetListEntries(null, msgs);
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_EXTENSION:
                return ((InternalEList)getManagedBeanExtension()).basicRemove(otherEnd, msgs);
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
            case FacesConfigPackage.MANAGED_BEAN_TYPE__DESCRIPTION:
                return getDescription();
            case FacesConfigPackage.MANAGED_BEAN_TYPE__DISPLAY_NAME:
                return getDisplayName();
            case FacesConfigPackage.MANAGED_BEAN_TYPE__ICON:
                return getIcon();
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_NAME:
                return getManagedBeanName();
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_CLASS:
                return getManagedBeanClass();
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_SCOPE:
                return getManagedBeanScope();
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_PROPERTY:
                return getManagedProperty();
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MAP_ENTRIES:
                return getMapEntries();
            case FacesConfigPackage.MANAGED_BEAN_TYPE__LIST_ENTRIES:
                return getListEntries();
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_EXTENSION:
                return getManagedBeanExtension();
            case FacesConfigPackage.MANAGED_BEAN_TYPE__ID:
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
            case FacesConfigPackage.MANAGED_BEAN_TYPE__DESCRIPTION:
                getDescription().clear();
                getDescription().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__DISPLAY_NAME:
                getDisplayName().clear();
                getDisplayName().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__ICON:
                getIcon().clear();
                getIcon().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_NAME:
                setManagedBeanName((ManagedBeanNameType)newValue);
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_CLASS:
                setManagedBeanClass((ManagedBeanClassType)newValue);
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_SCOPE:
                setManagedBeanScope((ManagedBeanScopeType)newValue);
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_PROPERTY:
                getManagedProperty().clear();
                getManagedProperty().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MAP_ENTRIES:
                setMapEntries((MapEntriesType)newValue);
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__LIST_ENTRIES:
                setListEntries((ListEntriesType)newValue);
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_EXTENSION:
                getManagedBeanExtension().clear();
                getManagedBeanExtension().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__ID:
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
            case FacesConfigPackage.MANAGED_BEAN_TYPE__DESCRIPTION:
                getDescription().clear();
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__DISPLAY_NAME:
                getDisplayName().clear();
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__ICON:
                getIcon().clear();
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_NAME:
                setManagedBeanName((ManagedBeanNameType)null);
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_CLASS:
                setManagedBeanClass((ManagedBeanClassType)null);
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_SCOPE:
                setManagedBeanScope((ManagedBeanScopeType)null);
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_PROPERTY:
                getManagedProperty().clear();
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MAP_ENTRIES:
                setMapEntries((MapEntriesType)null);
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__LIST_ENTRIES:
                setListEntries((ListEntriesType)null);
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_EXTENSION:
                getManagedBeanExtension().clear();
                return;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__ID:
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
            case FacesConfigPackage.MANAGED_BEAN_TYPE__DESCRIPTION:
                return description != null && !description.isEmpty();
            case FacesConfigPackage.MANAGED_BEAN_TYPE__DISPLAY_NAME:
                return displayName != null && !displayName.isEmpty();
            case FacesConfigPackage.MANAGED_BEAN_TYPE__ICON:
                return icon != null && !icon.isEmpty();
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_NAME:
                return managedBeanName != null;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_CLASS:
                return managedBeanClass != null;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_SCOPE:
                return managedBeanScope != null;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_PROPERTY:
                return managedProperty != null && !managedProperty.isEmpty();
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MAP_ENTRIES:
                return mapEntries != null;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__LIST_ENTRIES:
                return listEntries != null;
            case FacesConfigPackage.MANAGED_BEAN_TYPE__MANAGED_BEAN_EXTENSION:
                return managedBeanExtension != null && !managedBeanExtension.isEmpty();
            case FacesConfigPackage.MANAGED_BEAN_TYPE__ID:
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

} //ManagedBeanTypeImpl
