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
package org.eclipse.jst.jsf.facesconfig.emf;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Application Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getActionListener <em>Action Listener</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getDefaultRenderKitId <em>Default Render Kit Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getMessageBundle <em>Message Bundle</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getNavigationHandler <em>Navigation Handler</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getViewHandler <em>View Handler</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getStateManager <em>State Manager</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getPropertyResolver <em>Property Resolver</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getVariableResolver <em>Variable Resolver</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getLocaleConfig <em>Locale Config</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getELResolver <em>EL Resolver</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getResourceBundle <em>Resource Bundle</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getApplicationExtension <em>Application Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getApplicationType()
 * @model extendedMetaData="name='application_._type' kind='elementOnly'"
 * @generated
 */
public interface ApplicationType extends EObject {
    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Action Listener</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ActionListenerType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action Listener</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Action Listener</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getApplicationType_ActionListener()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.ActionListenerType" containment="true"
     *        extendedMetaData="kind='element' name='action-listener' namespace='##targetNamespace'"
     * @generated
     */
	EList getActionListener();

    /**
     * Returns the value of the '<em><b>Default Render Kit Id</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.DefaultRenderKitIdType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Render Kit Id</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Default Render Kit Id</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getApplicationType_DefaultRenderKitId()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.DefaultRenderKitIdType" containment="true"
     *        extendedMetaData="kind='element' name='default-render-kit-id' namespace='##targetNamespace'"
     * @generated
     */
	EList getDefaultRenderKitId();

    /**
     * Returns the value of the '<em><b>Message Bundle</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.MessageBundleType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Bundle</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Message Bundle</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getApplicationType_MessageBundle()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.MessageBundleType" containment="true"
     *        extendedMetaData="kind='element' name='message-bundle' namespace='##targetNamespace'"
     * @generated
     */
	EList getMessageBundle();

    /**
     * Returns the value of the '<em><b>Navigation Handler</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.NavigationHandlerType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Navigation Handler</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Navigation Handler</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getApplicationType_NavigationHandler()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.NavigationHandlerType" containment="true"
     *        extendedMetaData="kind='element' name='navigation-handler' namespace='##targetNamespace'"
     * @generated
     */
	EList getNavigationHandler();

    /**
     * Returns the value of the '<em><b>View Handler</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ViewHandlerType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View Handler</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>View Handler</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getApplicationType_ViewHandler()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.ViewHandlerType" containment="true"
     *        extendedMetaData="kind='element' name='view-handler' namespace='##targetNamespace'"
     * @generated
     */
	EList getViewHandler();

    /**
     * Returns the value of the '<em><b>State Manager</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.StateManagerType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State Manager</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>State Manager</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getApplicationType_StateManager()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.StateManagerType" containment="true"
     *        extendedMetaData="kind='element' name='state-manager' namespace='##targetNamespace'"
     * @generated
     */
	EList getStateManager();

    /**
     * Returns the value of the '<em><b>Property Resolver</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.PropertyResolverType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Resolver</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Property Resolver</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getApplicationType_PropertyResolver()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.PropertyResolverType" containment="true"
     *        extendedMetaData="kind='element' name='property-resolver' namespace='##targetNamespace'"
     * @generated
     */
	EList getPropertyResolver();

    /**
     * Returns the value of the '<em><b>Variable Resolver</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.VariableResolverType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable Resolver</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Variable Resolver</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getApplicationType_VariableResolver()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.VariableResolverType" containment="true"
     *        extendedMetaData="kind='element' name='variable-resolver' namespace='##targetNamespace'"
     * @generated
     */
	EList getVariableResolver();

    /**
     * Returns the value of the '<em><b>Locale Config</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.LocaleConfigType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Locale Config</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Locale Config</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getApplicationType_LocaleConfig()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.LocaleConfigType" containment="true"
     *        extendedMetaData="kind='element' name='locale-config' namespace='##targetNamespace'"
     * @generated
     */
	EList getLocaleConfig();

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getApplicationType_Id()
     * @model unique="false" id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
     * @generated
     */
	String getId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
	void setId(String value);

    /**
     * Returns the value of the '<em><b>EL Resolver</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ELResolverType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>EL Resolver</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>EL Resolver</em>' reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getApplicationType_ELResolver()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.ELResolverType"
     *        extendedMetaData="kind='element' name='el-resolver' namespace='##targetNamespace'"
     * @generated
     */
    EList getELResolver();

    /**
     * Returns the value of the '<em><b>Resource Bundle</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resource Bundle</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resource Bundle</em>' reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getApplicationType_ResourceBundle()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType"
     *        extendedMetaData="kind='element' name='resource-bundle' namespace='##targetNamespace'"
     * @generated
     */
    EList getResourceBundle();

    /**
     * Returns the value of the '<em><b>Application Extension</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationExtensionType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Application Extension</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Application Extension</em>' reference list.
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getApplicationType_ApplicationExtension()
     * @model type="org.eclipse.jst.jsf.facesconfig.emf.ApplicationExtensionType"
     *        extendedMetaData="kind='element' name='application-extension' namespace='##targetNamespace'"
     * @generated
     */
    EList getApplicationExtension();

} // ApplicationType
