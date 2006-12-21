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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.facesconfig.emf.ActionListenerType;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultRenderKitIdType;
import org.eclipse.jst.jsf.facesconfig.emf.ELResolverType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.LocaleConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.MessageBundleType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationHandlerType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyResolverType;
import org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType;
import org.eclipse.jst.jsf.facesconfig.emf.StateManagerType;
import org.eclipse.jst.jsf.facesconfig.emf.VariableResolverType;
import org.eclipse.jst.jsf.facesconfig.emf.ViewHandlerType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Application Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationTypeImpl#getActionListener <em>Action Listener</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationTypeImpl#getDefaultRenderKitId <em>Default Render Kit Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationTypeImpl#getMessageBundle <em>Message Bundle</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationTypeImpl#getNavigationHandler <em>Navigation Handler</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationTypeImpl#getViewHandler <em>View Handler</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationTypeImpl#getStateManager <em>State Manager</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationTypeImpl#getPropertyResolver <em>Property Resolver</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationTypeImpl#getVariableResolver <em>Variable Resolver</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationTypeImpl#getLocaleConfig <em>Locale Config</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationTypeImpl#getELResolver <em>EL Resolver</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationTypeImpl#getResourceBundle <em>Resource Bundle</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationTypeImpl#getApplicationExtension <em>Application Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ApplicationTypeImpl extends EObjectImpl implements ApplicationType {
    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

    /**
     * The cached value of the '{@link #getActionListener() <em>Action Listener</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getActionListener()
     * @generated
     * @ordered
     */
	protected EList actionListener = null;

    /**
     * The cached value of the '{@link #getDefaultRenderKitId() <em>Default Render Kit Id</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefaultRenderKitId()
     * @generated
     * @ordered
     */
	protected EList defaultRenderKitId = null;

    /**
     * The cached value of the '{@link #getMessageBundle() <em>Message Bundle</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMessageBundle()
     * @generated
     * @ordered
     */
	protected EList messageBundle = null;

    /**
     * The cached value of the '{@link #getNavigationHandler() <em>Navigation Handler</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getNavigationHandler()
     * @generated
     * @ordered
     */
	protected EList navigationHandler = null;

    /**
     * The cached value of the '{@link #getViewHandler() <em>View Handler</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getViewHandler()
     * @generated
     * @ordered
     */
	protected EList viewHandler = null;

    /**
     * The cached value of the '{@link #getStateManager() <em>State Manager</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getStateManager()
     * @generated
     * @ordered
     */
	protected EList stateManager = null;

    /**
     * The cached value of the '{@link #getPropertyResolver() <em>Property Resolver</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPropertyResolver()
     * @generated
     * @ordered
     */
	protected EList propertyResolver = null;

    /**
     * The cached value of the '{@link #getVariableResolver() <em>Variable Resolver</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getVariableResolver()
     * @generated
     * @ordered
     */
	protected EList variableResolver = null;

    /**
     * The cached value of the '{@link #getLocaleConfig() <em>Locale Config</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLocaleConfig()
     * @generated
     * @ordered
     */
	protected EList localeConfig = null;

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
     * The cached value of the '{@link #getELResolver() <em>EL Resolver</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getELResolver()
     * @generated
     * @ordered
     */
    protected EList eLResolver = null;

    /**
     * The cached value of the '{@link #getResourceBundle() <em>Resource Bundle</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResourceBundle()
     * @generated
     * @ordered
     */
    protected EList resourceBundle = null;

    /**
     * The cached value of the '{@link #getApplicationExtension() <em>Application Extension</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getApplicationExtension()
     * @generated
     * @ordered
     */
    protected EList applicationExtension = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ApplicationTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return FacesConfigPackage.Literals.APPLICATION_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getActionListener() {
        if (actionListener == null) {
            actionListener = new EObjectContainmentEList(ActionListenerType.class, this, FacesConfigPackage.APPLICATION_TYPE__ACTION_LISTENER);
        }
        return actionListener;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getDefaultRenderKitId() {
        if (defaultRenderKitId == null) {
            defaultRenderKitId = new EObjectContainmentEList(DefaultRenderKitIdType.class, this, FacesConfigPackage.APPLICATION_TYPE__DEFAULT_RENDER_KIT_ID);
        }
        return defaultRenderKitId;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getMessageBundle() {
        if (messageBundle == null) {
            messageBundle = new EObjectContainmentEList(MessageBundleType.class, this, FacesConfigPackage.APPLICATION_TYPE__MESSAGE_BUNDLE);
        }
        return messageBundle;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getNavigationHandler() {
        if (navigationHandler == null) {
            navigationHandler = new EObjectContainmentEList(NavigationHandlerType.class, this, FacesConfigPackage.APPLICATION_TYPE__NAVIGATION_HANDLER);
        }
        return navigationHandler;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getViewHandler() {
        if (viewHandler == null) {
            viewHandler = new EObjectContainmentEList(ViewHandlerType.class, this, FacesConfigPackage.APPLICATION_TYPE__VIEW_HANDLER);
        }
        return viewHandler;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getStateManager() {
        if (stateManager == null) {
            stateManager = new EObjectContainmentEList(StateManagerType.class, this, FacesConfigPackage.APPLICATION_TYPE__STATE_MANAGER);
        }
        return stateManager;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getPropertyResolver() {
        if (propertyResolver == null) {
            propertyResolver = new EObjectContainmentEList(PropertyResolverType.class, this, FacesConfigPackage.APPLICATION_TYPE__PROPERTY_RESOLVER);
        }
        return propertyResolver;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getVariableResolver() {
        if (variableResolver == null) {
            variableResolver = new EObjectContainmentEList(VariableResolverType.class, this, FacesConfigPackage.APPLICATION_TYPE__VARIABLE_RESOLVER);
        }
        return variableResolver;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getLocaleConfig() {
        if (localeConfig == null) {
            localeConfig = new EObjectContainmentEList(LocaleConfigType.class, this, FacesConfigPackage.APPLICATION_TYPE__LOCALE_CONFIG);
        }
        return localeConfig;
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
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.APPLICATION_TYPE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getELResolver() {
        if (eLResolver == null) {
            eLResolver = new EObjectResolvingEList(ELResolverType.class, this, FacesConfigPackage.APPLICATION_TYPE__EL_RESOLVER);
        }
        return eLResolver;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getResourceBundle() {
        if (resourceBundle == null) {
            resourceBundle = new EObjectResolvingEList(ResourceBundleType.class, this, FacesConfigPackage.APPLICATION_TYPE__RESOURCE_BUNDLE);
        }
        return resourceBundle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getApplicationExtension() {
        if (applicationExtension == null) {
            applicationExtension = new EObjectResolvingEList(ApplicationExtensionType.class, this, FacesConfigPackage.APPLICATION_TYPE__APPLICATION_EXTENSION);
        }
        return applicationExtension;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FacesConfigPackage.APPLICATION_TYPE__ACTION_LISTENER:
                return ((InternalEList)getActionListener()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.APPLICATION_TYPE__DEFAULT_RENDER_KIT_ID:
                return ((InternalEList)getDefaultRenderKitId()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.APPLICATION_TYPE__MESSAGE_BUNDLE:
                return ((InternalEList)getMessageBundle()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.APPLICATION_TYPE__NAVIGATION_HANDLER:
                return ((InternalEList)getNavigationHandler()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.APPLICATION_TYPE__VIEW_HANDLER:
                return ((InternalEList)getViewHandler()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.APPLICATION_TYPE__STATE_MANAGER:
                return ((InternalEList)getStateManager()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.APPLICATION_TYPE__PROPERTY_RESOLVER:
                return ((InternalEList)getPropertyResolver()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.APPLICATION_TYPE__VARIABLE_RESOLVER:
                return ((InternalEList)getVariableResolver()).basicRemove(otherEnd, msgs);
            case FacesConfigPackage.APPLICATION_TYPE__LOCALE_CONFIG:
                return ((InternalEList)getLocaleConfig()).basicRemove(otherEnd, msgs);
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
            case FacesConfigPackage.APPLICATION_TYPE__ACTION_LISTENER:
                return getActionListener();
            case FacesConfigPackage.APPLICATION_TYPE__DEFAULT_RENDER_KIT_ID:
                return getDefaultRenderKitId();
            case FacesConfigPackage.APPLICATION_TYPE__MESSAGE_BUNDLE:
                return getMessageBundle();
            case FacesConfigPackage.APPLICATION_TYPE__NAVIGATION_HANDLER:
                return getNavigationHandler();
            case FacesConfigPackage.APPLICATION_TYPE__VIEW_HANDLER:
                return getViewHandler();
            case FacesConfigPackage.APPLICATION_TYPE__STATE_MANAGER:
                return getStateManager();
            case FacesConfigPackage.APPLICATION_TYPE__PROPERTY_RESOLVER:
                return getPropertyResolver();
            case FacesConfigPackage.APPLICATION_TYPE__VARIABLE_RESOLVER:
                return getVariableResolver();
            case FacesConfigPackage.APPLICATION_TYPE__LOCALE_CONFIG:
                return getLocaleConfig();
            case FacesConfigPackage.APPLICATION_TYPE__ID:
                return getId();
            case FacesConfigPackage.APPLICATION_TYPE__EL_RESOLVER:
                return getELResolver();
            case FacesConfigPackage.APPLICATION_TYPE__RESOURCE_BUNDLE:
                return getResourceBundle();
            case FacesConfigPackage.APPLICATION_TYPE__APPLICATION_EXTENSION:
                return getApplicationExtension();
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
            case FacesConfigPackage.APPLICATION_TYPE__ACTION_LISTENER:
                getActionListener().clear();
                getActionListener().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.APPLICATION_TYPE__DEFAULT_RENDER_KIT_ID:
                getDefaultRenderKitId().clear();
                getDefaultRenderKitId().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.APPLICATION_TYPE__MESSAGE_BUNDLE:
                getMessageBundle().clear();
                getMessageBundle().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.APPLICATION_TYPE__NAVIGATION_HANDLER:
                getNavigationHandler().clear();
                getNavigationHandler().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.APPLICATION_TYPE__VIEW_HANDLER:
                getViewHandler().clear();
                getViewHandler().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.APPLICATION_TYPE__STATE_MANAGER:
                getStateManager().clear();
                getStateManager().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.APPLICATION_TYPE__PROPERTY_RESOLVER:
                getPropertyResolver().clear();
                getPropertyResolver().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.APPLICATION_TYPE__VARIABLE_RESOLVER:
                getVariableResolver().clear();
                getVariableResolver().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.APPLICATION_TYPE__LOCALE_CONFIG:
                getLocaleConfig().clear();
                getLocaleConfig().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.APPLICATION_TYPE__ID:
                setId((String)newValue);
                return;
            case FacesConfigPackage.APPLICATION_TYPE__EL_RESOLVER:
                getELResolver().clear();
                getELResolver().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.APPLICATION_TYPE__RESOURCE_BUNDLE:
                getResourceBundle().clear();
                getResourceBundle().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.APPLICATION_TYPE__APPLICATION_EXTENSION:
                getApplicationExtension().clear();
                getApplicationExtension().addAll((Collection)newValue);
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
            case FacesConfigPackage.APPLICATION_TYPE__ACTION_LISTENER:
                getActionListener().clear();
                return;
            case FacesConfigPackage.APPLICATION_TYPE__DEFAULT_RENDER_KIT_ID:
                getDefaultRenderKitId().clear();
                return;
            case FacesConfigPackage.APPLICATION_TYPE__MESSAGE_BUNDLE:
                getMessageBundle().clear();
                return;
            case FacesConfigPackage.APPLICATION_TYPE__NAVIGATION_HANDLER:
                getNavigationHandler().clear();
                return;
            case FacesConfigPackage.APPLICATION_TYPE__VIEW_HANDLER:
                getViewHandler().clear();
                return;
            case FacesConfigPackage.APPLICATION_TYPE__STATE_MANAGER:
                getStateManager().clear();
                return;
            case FacesConfigPackage.APPLICATION_TYPE__PROPERTY_RESOLVER:
                getPropertyResolver().clear();
                return;
            case FacesConfigPackage.APPLICATION_TYPE__VARIABLE_RESOLVER:
                getVariableResolver().clear();
                return;
            case FacesConfigPackage.APPLICATION_TYPE__LOCALE_CONFIG:
                getLocaleConfig().clear();
                return;
            case FacesConfigPackage.APPLICATION_TYPE__ID:
                setId(ID_EDEFAULT);
                return;
            case FacesConfigPackage.APPLICATION_TYPE__EL_RESOLVER:
                getELResolver().clear();
                return;
            case FacesConfigPackage.APPLICATION_TYPE__RESOURCE_BUNDLE:
                getResourceBundle().clear();
                return;
            case FacesConfigPackage.APPLICATION_TYPE__APPLICATION_EXTENSION:
                getApplicationExtension().clear();
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
            case FacesConfigPackage.APPLICATION_TYPE__ACTION_LISTENER:
                return actionListener != null && !actionListener.isEmpty();
            case FacesConfigPackage.APPLICATION_TYPE__DEFAULT_RENDER_KIT_ID:
                return defaultRenderKitId != null && !defaultRenderKitId.isEmpty();
            case FacesConfigPackage.APPLICATION_TYPE__MESSAGE_BUNDLE:
                return messageBundle != null && !messageBundle.isEmpty();
            case FacesConfigPackage.APPLICATION_TYPE__NAVIGATION_HANDLER:
                return navigationHandler != null && !navigationHandler.isEmpty();
            case FacesConfigPackage.APPLICATION_TYPE__VIEW_HANDLER:
                return viewHandler != null && !viewHandler.isEmpty();
            case FacesConfigPackage.APPLICATION_TYPE__STATE_MANAGER:
                return stateManager != null && !stateManager.isEmpty();
            case FacesConfigPackage.APPLICATION_TYPE__PROPERTY_RESOLVER:
                return propertyResolver != null && !propertyResolver.isEmpty();
            case FacesConfigPackage.APPLICATION_TYPE__VARIABLE_RESOLVER:
                return variableResolver != null && !variableResolver.isEmpty();
            case FacesConfigPackage.APPLICATION_TYPE__LOCALE_CONFIG:
                return localeConfig != null && !localeConfig.isEmpty();
            case FacesConfigPackage.APPLICATION_TYPE__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case FacesConfigPackage.APPLICATION_TYPE__EL_RESOLVER:
                return eLResolver != null && !eLResolver.isEmpty();
            case FacesConfigPackage.APPLICATION_TYPE__RESOURCE_BUNDLE:
                return resourceBundle != null && !resourceBundle.isEmpty();
            case FacesConfigPackage.APPLICATION_TYPE__APPLICATION_EXTENSION:
                return applicationExtension != null && !applicationExtension.isEmpty();
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

} //ApplicationTypeImpl
