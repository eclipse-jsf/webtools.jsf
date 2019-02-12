/***************************************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 *   Oracle Corporation - revision
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.emf;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAbsoluteOrdering <em>Absolute Ordering</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getActionListener <em>Action Listener</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getApplication <em>Application</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getApplicationFactory <em>Application Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAttributeClass <em>Attribute Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAttributeExtension <em>Attribute Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAttributeName <em>Attribute Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getBehavior <em>Behavior</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getBehaviorClass <em>Behavior Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getBehaviorId <em>Behavior Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getBehaviorExtension <em>Behavior Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponent <em>Component</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponentClass <em>Component Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponentExtension <em>Component Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponentFamily <em>Component Family</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponentType <em>Component Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getConverter <em>Converter</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getConverterClass <em>Converter Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getConverterForClass <em>Converter For Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getConverterId <em>Converter Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDefaultLocale <em>Default Locale</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDefaultRenderKitId <em>Default Render Kit Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDefaultValidators <em>Default Validators</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getExceptionHandlerFactory <em>Exception Handler Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getExternalContextFactory <em>External Context Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacesConfig <em>Faces Config</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacesContextFactory <em>Faces Context Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacet <em>Facet</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacetExtension <em>Facet Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacetName <em>Facet Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFactory <em>Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFromAction <em>From Action</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFromOutcome <em>From Outcome</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFromViewId <em>From View Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getIf <em>If</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getKeyClass <em>Key Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getLargeIcon <em>Large Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getLifecycle <em>Lifecycle</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getLifecycleFactory <em>Lifecycle Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getListEntries <em>List Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getLocaleConfig <em>Locale Config</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedBean <em>Managed Bean</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedBeanClass <em>Managed Bean Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedBeanName <em>Managed Bean Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedBeanScope <em>Managed Bean Scope</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedProperty <em>Managed Property</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getMapEntries <em>Map Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getMapEntry <em>Map Entry</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getMessageBundle <em>Message Bundle</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getNavigationCase <em>Navigation Case</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getNavigationHandler <em>Navigation Handler</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getNavigationRule <em>Navigation Rule</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getNullValue <em>Null Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getOrdering <em>Ordering</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getOrderingOrdering <em>Ordering Ordering</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getOthers <em>Others</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPartialViewContextFactory <em>Partial View Context Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPhaseListener <em>Phase Listener</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPropertyClass <em>Property Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPropertyExtension <em>Property Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPropertyResolver <em>Property Resolver</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRedirect <em>Redirect</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRedirectViewParam <em>Redirect View Param</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getReferencedBean <em>Referenced Bean</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getReferencedBeanClass <em>Referenced Bean Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getReferencedBeanName <em>Referenced Bean Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderer <em>Renderer</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRendererClass <em>Renderer Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRendererExtension <em>Renderer Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRendererType <em>Renderer Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderKit <em>Render Kit</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderKitClass <em>Render Kit Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderKitFactory <em>Render Kit Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderKitId <em>Render Kit Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getResourceHandler <em>Resource Handler</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSmallIcon <em>Small Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSourceClass <em>Source Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getStateManager <em>State Manager</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSuggestedValue <em>Suggested Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSupportedLocale <em>Supported Locale</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSystemEventClass <em>System Event Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSystemEventListener <em>System Event Listener</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSystemEventListenerClass <em>System Event Listener Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getTagHandlerDelegateFactory <em>Tag Handler Delegate Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getToViewId <em>To View Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValidator <em>Validator</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValidatorClass <em>Validator Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValidatorId <em>Validator Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValueClass <em>Value Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getVariableResolver <em>Variable Resolver</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getViewDeclarationLanguageFactory <em>View Declaration Language Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getViewHandler <em>View Handler</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getVisitContextFactory <em>Visit Context Factory</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

    /**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

    /**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry" keyType="java.lang.String" valueType="java.lang.String" transient="true"
	 *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
	EMap getXMLNSPrefixMap();

    /**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_XSISchemaLocation()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry" keyType="java.lang.String" valueType="java.lang.String" transient="true"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap getXSISchemaLocation();

    /**
	 * Returns the value of the '<em><b>Absolute Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Absolute Ordering</em>' containment reference.
	 * @see #setAbsoluteOrdering(AbsoluteOrderingType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_AbsoluteOrdering()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='absolute-ordering' namespace='##targetNamespace'"
	 * @generated
	 */
	AbsoluteOrderingType getAbsoluteOrdering();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAbsoluteOrdering <em>Absolute Ordering</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Absolute Ordering</em>' containment reference.
	 * @see #getAbsoluteOrdering()
	 * @generated
	 */
	void setAbsoluteOrdering(AbsoluteOrderingType value);

				/**
	 * Returns the value of the '<em><b>Action Listener</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  ==================== Subordinate
	 *                 Elements ============================       The
	 *                 "action-listener" element contains the fully
	 *                 qualified class name     of the concrete ActionListener
	 *                 implementation class that will be called     during the
	 *                 Invoke Application phase of the request processing
	 *                 lifecycle.     It must be of type "ClassName". 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Action Listener</em>' containment reference.
	 * @see #setActionListener(ActionListenerType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ActionListener()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='action-listener' namespace='##targetNamespace'"
	 * @generated
	 */
	ActionListenerType getActionListener();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getActionListener <em>Action Listener</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action Listener</em>' containment reference.
	 * @see #getActionListener()
	 * @generated
	 */
	void setActionListener(ActionListenerType value);

    /**
	 * Returns the value of the '<em><b>Application</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  ==================== Definition Elements
	 *                 =============================       The
	 *                 "application" element provides a mechanism to
	 *                 define the various     per-application-singleton
	 *                 implementation classes for a particular web
	 *                 application that is utilizing JavaServer Faces.  For
	 *                 nested elements     that are not specified, the JSF
	 *                 implementation must provide a suitable     default. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Application</em>' containment reference.
	 * @see #setApplication(ApplicationType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Application()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='application' namespace='##targetNamespace'"
	 * @generated
	 */
	ApplicationType getApplication();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getApplication <em>Application</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Application</em>' containment reference.
	 * @see #getApplication()
	 * @generated
	 */
	void setApplication(ApplicationType value);

    /**
	 * Returns the value of the '<em><b>Application Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "application-factory"
	 *                 element contains the fully qualified class     name of
	 *                 the concrete ApplicationFactory implementation class
	 *                 that     will be called when
	 *                 FactoryFinder.getFactory(APPLICATION_FACTORY) is
	 *                 called. It must be of type "ClassName". 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Application Factory</em>' containment reference.
	 * @see #setApplicationFactory(ApplicationFactoryType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ApplicationFactory()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='application-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	ApplicationFactoryType getApplicationFactory();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getApplicationFactory <em>Application Factory</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Application Factory</em>' containment reference.
	 * @see #getApplicationFactory()
	 * @generated
	 */
	void setApplicationFactory(ApplicationFactoryType value);

    /**
	 * Returns the value of the '<em><b>Attribute</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "attribute" element
	 *                 represents a named, typed, value associated with     the
	 *                 parent UIComponent via the generic attributes mechanism.
	 *                 Attribute names must be unique within the scope of the
	 *                 parent (or related)     component. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Attribute</em>' containment reference.
	 * @see #setAttribute(AttributeType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Attribute()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='attribute' namespace='##targetNamespace'"
	 * @generated
	 */
	AttributeType getAttribute();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAttribute <em>Attribute</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute</em>' containment reference.
	 * @see #getAttribute()
	 * @generated
	 */
	void setAttribute(AttributeType value);

    /**
	 * Returns the value of the '<em><b>Attribute Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "attribute-class" element represents the Java type of the value
	 *     associated with this attribute name.  It must be of type "ClassName".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Attribute Class</em>' containment reference.
	 * @see #setAttributeClass(AttributeClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_AttributeClass()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='attribute-class' namespace='##targetNamespace'"
	 * @generated
	 */
	AttributeClassType getAttributeClass();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAttributeClass <em>Attribute Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute Class</em>' containment reference.
	 * @see #getAttributeClass()
	 * @generated
	 */
	void setAttributeClass(AttributeClassType value);

    /**
	 * Returns the value of the '<em><b>Attribute Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *       Extension element for attribute.
	 *                 May contain implementation     specific content. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Attribute Extension</em>' containment reference.
	 * @see #setAttributeExtension(AttributeExtensionType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_AttributeExtension()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='attribute-extension' namespace='##targetNamespace'"
	 * @generated
	 */
	AttributeExtensionType getAttributeExtension();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAttributeExtension <em>Attribute Extension</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute Extension</em>' containment reference.
	 * @see #getAttributeExtension()
	 * @generated
	 */
	void setAttributeExtension(AttributeExtensionType value);

    /**
	 * Returns the value of the '<em><b>Attribute Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "attribute-name"
	 *                 element represents the name under which the
	 *                 corresponding value will be stored, in the generic
	 *                 attributes of the     UIComponent we are related to. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Attribute Name</em>' containment reference.
	 * @see #setAttributeName(AttributeNameType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_AttributeName()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='attribute-name' namespace='##targetNamespace'"
	 * @generated
	 */
	AttributeNameType getAttributeName();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAttributeName <em>Attribute Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute Name</em>' containment reference.
	 * @see #getAttributeName()
	 * @generated
	 */
	void setAttributeName(AttributeNameType value);

    /**
	 * Returns the value of the '<em><b>Behavior</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Behavior</em>' containment reference.
	 * @see #setBehavior(BehaviorType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Behavior()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='behavior' namespace='##targetNamespace'"
	 * @generated
	 */
	BehaviorType getBehavior();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getBehavior <em>Behavior</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Behavior</em>' containment reference.
	 * @see #getBehavior()
	 * @generated
	 */
	void setBehavior(BehaviorType value);

				/**
	 * Returns the value of the '<em><b>Behavior Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Behavior Class</em>' containment reference.
	 * @see #setBehaviorClass(BehaviorClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_BehaviorClass()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='behavior-class' namespace='##targetNamespace'"
	 * @generated
	 */
	BehaviorClassType getBehaviorClass();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getBehaviorClass <em>Behavior Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Behavior Class</em>' containment reference.
	 * @see #getBehaviorClass()
	 * @generated
	 */
	void setBehaviorClass(BehaviorClassType value);

				/**
	 * Returns the value of the '<em><b>Behavior Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Behavior Id</em>' containment reference.
	 * @see #setBehaviorId(BehaviorIdType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_BehaviorId()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='behavior-id' namespace='##targetNamespace'"
	 * @generated
	 */
	BehaviorIdType getBehaviorId();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getBehaviorId <em>Behavior Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Behavior Id</em>' containment reference.
	 * @see #getBehaviorId()
	 * @generated
	 */
	void setBehaviorId(BehaviorIdType value);

				/**
	 * Returns the value of the '<em><b>Behavior Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Behavior Extension</em>' containment reference.
	 * @see #setBehaviorExtension(BehaviorExtensionType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_BehaviorExtension()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='behavior-extension' namespace='##targetNamespace'"
	 * @generated
	 */
	BehaviorExtensionType getBehaviorExtension();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getBehaviorExtension <em>Behavior Extension</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Behavior Extension</em>' containment reference.
	 * @see #getBehaviorExtension()
	 * @generated
	 */
	void setBehaviorExtension(BehaviorExtensionType value);

				/**
	 * Returns the value of the '<em><b>Component</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "component" element
	 *                 represents a concrete UIComponent implementation
	 *                 class that should be registered under the specified type
	 *                 identifier,     along with its associated properties and
	 *                 attributes.  Component types must     be unique within
	 *                 the entire web application.      Nested
	 *                 "attribute" elements identify generic
	 *                 attributes that are recognized     by the implementation
	 *                 logic of this component.  Nested "property"
	 *                 elements     identify JavaBeans properties of the
	 *                 component class that may be exposed     for manipulation
	 *                 via tools. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Component</em>' containment reference.
	 * @see #setComponent(ComponentType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Component()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='component' namespace='##targetNamespace'"
	 * @generated
	 */
	ComponentType getComponent();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponent <em>Component</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component</em>' containment reference.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(ComponentType value);

    /**
	 * Returns the value of the '<em><b>Component Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "component-class"
	 *                 element represents the fully qualified class name     of
	 *                 a concrete UIComponent implementation class.  It must be
	 *                 of     type "ClassName". 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Component Class</em>' containment reference.
	 * @see #setComponentClass(ComponentClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ComponentClass()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='component-class' namespace='##targetNamespace'"
	 * @generated
	 */
	ComponentClassType getComponentClass();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponentClass <em>Component Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Class</em>' containment reference.
	 * @see #getComponentClass()
	 * @generated
	 */
	void setComponentClass(ComponentClassType value);

    /**
	 * Returns the value of the '<em><b>Component Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *       Extension element for component.
	 *                 May contain implementation     specific content. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Component Extension</em>' containment reference.
	 * @see #setComponentExtension(ComponentExtensionType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ComponentExtension()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='component-extension' namespace='##targetNamespace'"
	 * @generated
	 */
	ComponentExtensionType getComponentExtension();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponentExtension <em>Component Extension</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Extension</em>' containment reference.
	 * @see #getComponentExtension()
	 * @generated
	 */
	void setComponentExtension(ComponentExtensionType value);

    /**
	 * Returns the value of the '<em><b>Component Family</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "component-family" element represents the component family for
	 *     which the Renderer represented by the parent "renderer" element will be
	 *     used.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Component Family</em>' containment reference.
	 * @see #setComponentFamily(ComponentFamilyType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ComponentFamily()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='component-family' namespace='##targetNamespace'"
	 * @generated
	 */
	ComponentFamilyType getComponentFamily();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponentFamily <em>Component Family</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Family</em>' containment reference.
	 * @see #getComponentFamily()
	 * @generated
	 */
	void setComponentFamily(ComponentFamilyType value);

    /**
	 * Returns the value of the '<em><b>Component Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "component-type"
	 *                 element represents the name under which the
	 *                 corresponding UIComponent class should be registered. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Component Type</em>' containment reference.
	 * @see #setComponentType(ComponentTypeType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ComponentType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='component-type' namespace='##targetNamespace'"
	 * @generated
	 */
	ComponentTypeType getComponentType();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponentType <em>Component Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Type</em>' containment reference.
	 * @see #getComponentType()
	 * @generated
	 */
	void setComponentType(ComponentTypeType value);

    /**
	 * Returns the value of the '<em><b>Converter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "converter" element
	 *                 represents a concrete Converter implementation     class
	 *                 that should be registered under the specified converter
	 *                 identifier.     Converter identifiers must be unique
	 *                 within the entire web application.      Nested
	 *                 "attribute" elements identify generic
	 *                 attributes that may be     configured on the
	 *                 corresponding UIComponent in order to affect the
	 *                 operation of the Converter.  Nested "property"
	 *                 elements identify JavaBeans     properties of the
	 *                 Converter implementation class that may be configured
	 *                 to affect the operation of the Converter. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Converter</em>' containment reference.
	 * @see #setConverter(ConverterType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Converter()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='converter' namespace='##targetNamespace'"
	 * @generated
	 */
	ConverterType getConverter();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getConverter <em>Converter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Converter</em>' containment reference.
	 * @see #getConverter()
	 * @generated
	 */
	void setConverter(ConverterType value);

    /**
	 * Returns the value of the '<em><b>Converter Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "converter-class" element represents the fully qualified class name
	 *     of a concrete Converter implementation class.  It must be of
	 *     type "ClassName".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Converter Class</em>' containment reference.
	 * @see #setConverterClass(ConverterClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ConverterClass()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='converter-class' namespace='##targetNamespace'"
	 * @generated
	 */
	ConverterClassType getConverterClass();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getConverterClass <em>Converter Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Converter Class</em>' containment reference.
	 * @see #getConverterClass()
	 * @generated
	 */
	void setConverterClass(ConverterClassType value);

    /**
	 * Returns the value of the '<em><b>Converter For Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "converter-for-class" element represents the fully qualified class name
	 *     for which a Converter class will be registered.  It must be of
	 *     type "ClassName".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Converter For Class</em>' containment reference.
	 * @see #setConverterForClass(ConverterForClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ConverterForClass()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='converter-for-class' namespace='##targetNamespace'"
	 * @generated
	 */
	ConverterForClassType getConverterForClass();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getConverterForClass <em>Converter For Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Converter For Class</em>' containment reference.
	 * @see #getConverterForClass()
	 * @generated
	 */
	void setConverterForClass(ConverterForClassType value);

    /**
	 * Returns the value of the '<em><b>Converter Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "converter-id" element represents the identifier under which the
	 *     corresponding Converter class should be registered.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Converter Id</em>' containment reference.
	 * @see #setConverterId(ConverterIdType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ConverterId()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='converter-id' namespace='##targetNamespace'"
	 * @generated
	 */
	ConverterIdType getConverterId();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getConverterId <em>Converter Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Converter Id</em>' containment reference.
	 * @see #getConverterId()
	 * @generated
	 */
	void setConverterId(ConverterIdType value);

    /**
	 * Returns the value of the '<em><b>Default Locale</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *       The "default-locale"
	 *                 element declares the default locale for this
	 *                 application instance.  It must be specified as
	 *                 :language:[_:country:[_:variant:]] without the colons,
	 *                 for example      "ja_JP_SJIS".  The separators
	 *                 between the segments may be '-' or
	 *                 '_'. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Locale</em>' containment reference.
	 * @see #setDefaultLocale(DefaultLocaleType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_DefaultLocale()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='default-locale' namespace='##targetNamespace'"
	 * @generated
	 */
	DefaultLocaleType getDefaultLocale();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDefaultLocale <em>Default Locale</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Locale</em>' containment reference.
	 * @see #getDefaultLocale()
	 * @generated
	 */
	void setDefaultLocale(DefaultLocaleType value);

    /**
	 * Returns the value of the '<em><b>Default Render Kit Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "default-render-kit-id" element allows the application to define
	 *     a renderkit to be used other than the standard one. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Render Kit Id</em>' containment reference.
	 * @see #setDefaultRenderKitId(DefaultRenderKitIdType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_DefaultRenderKitId()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='default-render-kit-id' namespace='##targetNamespace'"
	 * @generated
	 */
	DefaultRenderKitIdType getDefaultRenderKitId();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDefaultRenderKitId <em>Default Render Kit Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Render Kit Id</em>' containment reference.
	 * @see #getDefaultRenderKitId()
	 * @generated
	 */
	void setDefaultRenderKitId(DefaultRenderKitIdType value);

    /**
	 * Returns the value of the '<em><b>Default Validators</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Validators</em>' containment reference.
	 * @see #setDefaultValidators(DefaultValidatorsType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_DefaultValidators()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='default-validators' namespace='##targetNamespace'"
	 * @generated
	 */
	DefaultValidatorsType getDefaultValidators();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDefaultValidators <em>Default Validators</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Validators</em>' containment reference.
	 * @see #getDefaultValidators()
	 * @generated
	 */
	void setDefaultValidators(DefaultValidatorsType value);

				/**
	 * Returns the value of the '<em><b>Default Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "default-value" contains the value for the property or attribute
	 *     in which this element resides.  This value differs from the
	 *     "suggested-value" in that the property or attribute must take the
	 *     value, whereas in "suggested-value" taking the value is optional.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Value</em>' containment reference.
	 * @see #setDefaultValue(DefaultValueType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_DefaultValue()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='default-value' namespace='##targetNamespace'"
	 * @generated
	 */
	DefaultValueType getDefaultValue();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDefaultValue <em>Default Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value</em>' containment reference.
	 * @see #getDefaultValue()
	 * @generated
	 */
	void setDefaultValue(DefaultValueType value);

    /**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "description" element
	 *                 contains a textual description of the element     it is
	 *                 nested in, optionally flagged with a language code using
	 *                 the     "xml:lang" attribute. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' containment reference.
	 * @see #setDescription(DescriptionType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Description()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='description' namespace='##targetNamespace'"
	 * @generated
	 */
	DescriptionType getDescription();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDescription <em>Description</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' containment reference.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(DescriptionType value);

    /**
	 * Returns the value of the '<em><b>Display Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "display-name" element
	 *                 is a short descriptive name describing the     entity
	 *                 associated with the element it is nested in, intended to
	 *                 be     displayed by tools, and optionally flagged with a
	 *                 language code using     the "xml:lang"
	 *                 attribute. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Display Name</em>' containment reference.
	 * @see #setDisplayName(DisplayNameType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_DisplayName()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='display-name' namespace='##targetNamespace'"
	 * @generated
	 */
	DisplayNameType getDisplayName();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDisplayName <em>Display Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Name</em>' containment reference.
	 * @see #getDisplayName()
	 * @generated
	 */
	void setDisplayName(DisplayNameType value);

    /**
	 * Returns the value of the '<em><b>Exception Handler Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Exception Handler Factory</em>' containment reference.
	 * @see #setExceptionHandlerFactory(ExceptionHandlerFactoryType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ExceptionHandlerFactory()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='exception-handler-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	ExceptionHandlerFactoryType getExceptionHandlerFactory();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getExceptionHandlerFactory <em>Exception Handler Factory</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exception Handler Factory</em>' containment reference.
	 * @see #getExceptionHandlerFactory()
	 * @generated
	 */
	void setExceptionHandlerFactory(ExceptionHandlerFactoryType value);

				/**
	 * Returns the value of the '<em><b>External Context Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>External Context Factory</em>' containment reference.
	 * @see #setExternalContextFactory(ExternalContextFactoryType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ExternalContextFactory()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='external-context-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	ExternalContextFactoryType getExternalContextFactory();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getExternalContextFactory <em>External Context Factory</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Context Factory</em>' containment reference.
	 * @see #getExternalContextFactory()
	 * @generated
	 */
	void setExternalContextFactory(ExternalContextFactoryType value);

				/**
	 * Returns the value of the '<em><b>Faces Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  ==================== Top Level Elements
	 *                 ==============================       The
	 *                 "faces-config" element is the root of the
	 *                 configuration information     hierarchy, and contains
	 *                 nested elements for all of the other configuration
	 *                 settings. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Faces Config</em>' containment reference.
	 * @see #setFacesConfig(FacesConfigType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_FacesConfig()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='faces-config' namespace='##targetNamespace'"
	 * @generated
	 */
	FacesConfigType getFacesConfig();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacesConfig <em>Faces Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Faces Config</em>' containment reference.
	 * @see #getFacesConfig()
	 * @generated
	 */
	void setFacesConfig(FacesConfigType value);

    /**
	 * Returns the value of the '<em><b>Faces Context Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The
	 *                 "faces-context-factory" element contains the
	 *                 fully qualified     class name of the concrete
	 *                 FacesContextFactory implementation class     that will
	 *                 be called when
	 *                 FactoryFinder.getFactory(FACES_CONTEXT_FACTORY) is
	 *                 called. It must     be of type "ClassName". 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Faces Context Factory</em>' containment reference.
	 * @see #setFacesContextFactory(FacesContextFactoryType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_FacesContextFactory()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='faces-context-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	FacesContextFactoryType getFacesContextFactory();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacesContextFactory <em>Faces Context Factory</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Faces Context Factory</em>' containment reference.
	 * @see #getFacesContextFactory()
	 * @generated
	 */
	void setFacesContextFactory(FacesContextFactoryType value);

    /**
	 * Returns the value of the '<em><b>Facet</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *    Define the name and other design-time information for a facet that is
	 *    associated with a renderer or a component.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Facet</em>' containment reference.
	 * @see #setFacet(FacetType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Facet()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='facet' namespace='##targetNamespace'"
	 * @generated
	 */
	FacetType getFacet();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacet <em>Facet</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Facet</em>' containment reference.
	 * @see #getFacet()
	 * @generated
	 */
	void setFacet(FacetType value);

    /**
	 * Returns the value of the '<em><b>Facet Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     Extension element for facet.  May contain implementation
	 *     specific content.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Facet Extension</em>' containment reference.
	 * @see #setFacetExtension(FacetExtensionType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_FacetExtension()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='facet-extension' namespace='##targetNamespace'"
	 * @generated
	 */
	FacetExtensionType getFacetExtension();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacetExtension <em>Facet Extension</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Facet Extension</em>' containment reference.
	 * @see #getFacetExtension()
	 * @generated
	 */
	void setFacetExtension(FacetExtensionType value);

    /**
	 * Returns the value of the '<em><b>Facet Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "facet-name" element represents the facet name under which a
	 *     UIComponent will be added to its parent.  It must be of type
	 *     "Identifier".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Facet Name</em>' containment reference.
	 * @see #setFacetName(FacetNameType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_FacetName()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='facet-name' namespace='##targetNamespace'"
	 * @generated
	 */
	FacetNameType getFacetName();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacetName <em>Facet Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Facet Name</em>' containment reference.
	 * @see #getFacetName()
	 * @generated
	 */
	void setFacetName(FacetNameType value);

    /**
	 * Returns the value of the '<em><b>Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "factory" element
	 *                 provides a mechanism to define the various     Factories
	 *                 that comprise parts of the implementation of JavaServer
	 *                 Faces.  For nested elements that are not specified, the
	 *                 JSF     implementation must provide a suitable default. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Factory</em>' containment reference.
	 * @see #setFactory(FactoryType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Factory()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='factory' namespace='##targetNamespace'"
	 * @generated
	 */
	FactoryType getFactory();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFactory <em>Factory</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Factory</em>' containment reference.
	 * @see #getFactory()
	 * @generated
	 */
	void setFactory(FactoryType value);

    /**
	 * Returns the value of the '<em><b>From Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "from-action" element contains an action reference expression
	 *     that must have been executed (by the default ActionListener for handling
	 *     application level events) in order to select this navigation rule.  If
	 *     not specified, this rule will be relevant no matter which action reference
	 *     was executed (or if no action reference was executed).
	 * 
	 *     This value must be of type "Action".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>From Action</em>' containment reference.
	 * @see #setFromAction(FromActionType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_FromAction()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='from-action' namespace='##targetNamespace'"
	 * @generated
	 */
	FromActionType getFromAction();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFromAction <em>From Action</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From Action</em>' containment reference.
	 * @see #getFromAction()
	 * @generated
	 */
	void setFromAction(FromActionType value);

    /**
	 * Returns the value of the '<em><b>From Outcome</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "from-outcome" element contains a logical outcome string returned
	 *     by the execution of an application action method selected via an
	 *     "actionRef" property (or a literal value specified by an "action"
	 *     property) of a UICommand component.  If specified, this rule will be
	 *     relevant only if the outcome value matches this element's value.  If
	 *     not specified, this rule will be relevant no matter what the outcome
	 *     value was.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>From Outcome</em>' containment reference.
	 * @see #setFromOutcome(FromOutcomeType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_FromOutcome()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='from-outcome' namespace='##targetNamespace'"
	 * @generated
	 */
	FromOutcomeType getFromOutcome();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFromOutcome <em>From Outcome</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From Outcome</em>' containment reference.
	 * @see #getFromOutcome()
	 * @generated
	 */
	void setFromOutcome(FromOutcomeType value);

    /**
	 * Returns the value of the '<em><b>From View Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "from-view-id" element contains the view identifier of the view
	 *     for which the containing navigation rule is relevant.  If no
	 *     "from-view" element is specified, this rule applies to navigation
	 *     decisions on all views.  If this element is not specified, a value
	 *     of "*" is assumed, meaning that this navigation rule applies to all
	 *     views.
	 * 
	 *     This value must be of type "ViewIdPattern".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>From View Id</em>' containment reference.
	 * @see #setFromViewId(FromViewIdType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_FromViewId()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='from-view-id' namespace='##targetNamespace'"
	 * @generated
	 */
	FromViewIdType getFromViewId();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFromViewId <em>From View Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From View Id</em>' containment reference.
	 * @see #getFromViewId()
	 * @generated
	 */
	void setFromViewId(FromViewIdType value);

    /**
	 * Returns the value of the '<em><b>Icon</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "icon" element
	 *                 contains "small-icon" and
	 *                 "large-icon" elements that     specify the
	 *                 resoruce paths for small and large GIF or JPG icon
	 *                 images     used to represent the parent element in a GUI
	 *                 tool. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Icon</em>' containment reference.
	 * @see #setIcon(IconType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Icon()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='icon' namespace='##targetNamespace'"
	 * @generated
	 */
	IconType getIcon();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getIcon <em>Icon</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Icon</em>' containment reference.
	 * @see #getIcon()
	 * @generated
	 */
	void setIcon(IconType value);

    /**
	 * Returns the value of the '<em><b>If</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>If</em>' containment reference.
	 * @see #setIf(IfType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_If()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='if' namespace='##targetNamespace'"
	 * @generated
	 */
	IfType getIf();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getIf <em>If</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>If</em>' containment reference.
	 * @see #getIf()
	 * @generated
	 */
	void setIf(IfType value);

				/**
	 * Returns the value of the '<em><b>Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "key" element is the String representation of a map key that
	 *     will be stored in a managed property of type java.util.Map.  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Key</em>' containment reference.
	 * @see #setKey(KeyType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Key()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='key' namespace='##targetNamespace'"
	 * @generated
	 */
	KeyType getKey();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getKey <em>Key</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' containment reference.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(KeyType value);

    /**
	 * Returns the value of the '<em><b>Key Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "key-class" element defines the Java type to which each "key"
	 *     element in a set of "map-entry" elements will be converted to.  It
	 *     must be of type "ClassName".  If omitted, "java.lang.String"
	 *     is assumed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Key Class</em>' containment reference.
	 * @see #setKeyClass(KeyClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_KeyClass()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='key-class' namespace='##targetNamespace'"
	 * @generated
	 */
	KeyClassType getKeyClass();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getKeyClass <em>Key Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key Class</em>' containment reference.
	 * @see #getKeyClass()
	 * @generated
	 */
	void setKeyClass(KeyClassType value);

    /**
	 * Returns the value of the '<em><b>Large Icon</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "large-icon" element contains the resource path to a large (32x32)
	 *     icon image.  The image may be in either GIF or JPG format.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Large Icon</em>' containment reference.
	 * @see #setLargeIcon(LargeIconType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_LargeIcon()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='large-icon' namespace='##targetNamespace'"
	 * @generated
	 */
	LargeIconType getLargeIcon();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getLargeIcon <em>Large Icon</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Large Icon</em>' containment reference.
	 * @see #getLargeIcon()
	 * @generated
	 */
	void setLargeIcon(LargeIconType value);

    /**
	 * Returns the value of the '<em><b>Lifecycle</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "lifecycle" element
	 *                 provides a mechanism to specify     modifications to the
	 *                 behaviour of the default Lifecycle     implementation
	 *                 for this web application. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lifecycle</em>' containment reference.
	 * @see #setLifecycle(LifecycleType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Lifecycle()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='lifecycle' namespace='##targetNamespace'"
	 * @generated
	 */
	LifecycleType getLifecycle();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getLifecycle <em>Lifecycle</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lifecycle</em>' containment reference.
	 * @see #getLifecycle()
	 * @generated
	 */
	void setLifecycle(LifecycleType value);

    /**
	 * Returns the value of the '<em><b>Lifecycle Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "lifecycle-factory"
	 *                 element contains the fully qualified class name     of
	 *                 the concrete LifecycleFactory implementation class that
	 *                 will be called     when
	 *                 FactoryFinder.getFactory(LIFECYCLE_FACTORY) is called.
	 *                 It must be      of type "ClassName". 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lifecycle Factory</em>' containment reference.
	 * @see #setLifecycleFactory(LifecycleFactoryType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_LifecycleFactory()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='lifecycle-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	LifecycleFactoryType getLifecycleFactory();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getLifecycleFactory <em>Lifecycle Factory</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lifecycle Factory</em>' containment reference.
	 * @see #getLifecycleFactory()
	 * @generated
	 */
	void setLifecycleFactory(LifecycleFactoryType value);

    /**
	 * Returns the value of the '<em><b>List Entries</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "list-entries" element
	 *                 represents a set of initialization     elements for a
	 *                 managed property that is a java.util.List or an
	 *                 array.  In the former case, the "value-class"
	 *                 element can optionally     be used to declare the Java
	 *                 type to which each value should be     converted before
	 *                 adding it to the Collection. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>List Entries</em>' containment reference.
	 * @see #setListEntries(ListEntriesType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ListEntries()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='list-entries' namespace='##targetNamespace'"
	 * @generated
	 */
	ListEntriesType getListEntries();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getListEntries <em>List Entries</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>List Entries</em>' containment reference.
	 * @see #getListEntries()
	 * @generated
	 */
	void setListEntries(ListEntriesType value);

    /**
	 * Returns the value of the '<em><b>Locale Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *       The "locale-config"
	 *                 element allows the app developer to declare the
	 *                 supported locales for this application.   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Locale Config</em>' containment reference.
	 * @see #setLocaleConfig(LocaleConfigType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_LocaleConfig()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='locale-config' namespace='##targetNamespace'"
	 * @generated
	 */
	LocaleConfigType getLocaleConfig();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getLocaleConfig <em>Locale Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Locale Config</em>' containment reference.
	 * @see #getLocaleConfig()
	 * @generated
	 */
	void setLocaleConfig(LocaleConfigType value);

    /**
	 * Returns the value of the '<em><b>Managed Bean</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "managed-bean" element
	 *                 represents a JavaBean, of a particular class,     that
	 *                 will be dynamically instantiated at runtime (by the
	 *                 default     VariableResolver implementation) if it is
	 *                 referenced as the first element     of a value reference
	 *                 expression, and no corresponding bean can be
	 *                 identified in any scope.  In addition to the creation of
	 *                 the managed bean,     and the optional storing of it
	 *                 into the specified scope, the nested
	 *                 managed-property elements can be used to initialize the
	 *                 contents of     settable JavaBeans properties of the
	 *                 created instance. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Managed Bean</em>' containment reference.
	 * @see #setManagedBean(ManagedBeanType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ManagedBean()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='managed-bean' namespace='##targetNamespace'"
	 * @generated
	 */
	ManagedBeanType getManagedBean();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedBean <em>Managed Bean</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Managed Bean</em>' containment reference.
	 * @see #getManagedBean()
	 * @generated
	 */
	void setManagedBean(ManagedBeanType value);

    /**
	 * Returns the value of the '<em><b>Managed Bean Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "managed-bean-class" element represents the fully qualified class
	 *     name of the Java class that will be used to instantiate a new instance
	 *     if creation of the specified managed bean is requested.  It must be of
	 *     type "ClassName".
	 * 
	 *     The specified class must conform to standard JavaBeans conventions.
	 *     In particular, it must have a public zero-arguments constructor, and
	 *     zero or more public property setters.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Managed Bean Class</em>' containment reference.
	 * @see #setManagedBeanClass(ManagedBeanClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ManagedBeanClass()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='managed-bean-class' namespace='##targetNamespace'"
	 * @generated
	 */
	ManagedBeanClassType getManagedBeanClass();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedBeanClass <em>Managed Bean Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Managed Bean Class</em>' containment reference.
	 * @see #getManagedBeanClass()
	 * @generated
	 */
	void setManagedBeanClass(ManagedBeanClassType value);

    /**
	 * Returns the value of the '<em><b>Managed Bean Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "managed-bean-name" element represents the attribute name under
	 *     which a managed bean will be searched for, as well as stored (unless
	 *     the "managed-bean-scope" value is "none").  It must be of type
	 *     "Identifier".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Managed Bean Name</em>' containment reference.
	 * @see #setManagedBeanName(ManagedBeanNameType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ManagedBeanName()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='managed-bean-name' namespace='##targetNamespace'"
	 * @generated
	 */
	ManagedBeanNameType getManagedBeanName();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedBeanName <em>Managed Bean Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Managed Bean Name</em>' containment reference.
	 * @see #getManagedBeanName()
	 * @generated
	 */
	void setManagedBeanName(ManagedBeanNameType value);

    /**
	 * Returns the value of the '<em><b>Managed Bean Scope</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "managed-bean-scope" element represents the scope into which a newly
	 *     created instance of the specified managed bean will be stored (unless
	 *     the value is "none").  It must be of type "ScopeOrNone".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Managed Bean Scope</em>' containment reference.
	 * @see #setManagedBeanScope(ManagedBeanScopeType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ManagedBeanScope()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='managed-bean-scope' namespace='##targetNamespace'"
	 * @generated
	 */
	ManagedBeanScopeType getManagedBeanScope();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedBeanScope <em>Managed Bean Scope</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Managed Bean Scope</em>' containment reference.
	 * @see #getManagedBeanScope()
	 * @generated
	 */
	void setManagedBeanScope(ManagedBeanScopeType value);

    /**
	 * Returns the value of the '<em><b>Managed Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "managed-property"
	 *                 element represents an individual property of a
	 *                 managed bean that will be configured to the specified
	 *                 value (or value set)     if the corresponding managed
	 *                 bean is automatically created. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Managed Property</em>' containment reference.
	 * @see #setManagedProperty(ManagedPropertyType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ManagedProperty()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='managed-property' namespace='##targetNamespace'"
	 * @generated
	 */
	ManagedPropertyType getManagedProperty();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedProperty <em>Managed Property</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Managed Property</em>' containment reference.
	 * @see #getManagedProperty()
	 * @generated
	 */
	void setManagedProperty(ManagedPropertyType value);

    /**
	 * Returns the value of the '<em><b>Map Entries</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "map-entries' element
	 *                 represents a set of key-entry pairs that     will be
	 *                 added to the computed value of a managed property of
	 *                 type     java.util.Map.  In addition, the Java class
	 *                 types of the key and entry     values may be optionally
	 *                 declared. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Map Entries</em>' containment reference.
	 * @see #setMapEntries(MapEntriesType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_MapEntries()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='map-entries' namespace='##targetNamespace'"
	 * @generated
	 */
	MapEntriesType getMapEntries();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getMapEntries <em>Map Entries</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Map Entries</em>' containment reference.
	 * @see #getMapEntries()
	 * @generated
	 */
	void setMapEntries(MapEntriesType value);

    /**
	 * Returns the value of the '<em><b>Map Entry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "map-entry" element
	 *                 reprsents a single key-entry pair that     will be added
	 *                 to the computed value of a managed property of type
	 *                 java.util.Map. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Map Entry</em>' containment reference.
	 * @see #setMapEntry(MapEntryType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_MapEntry()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='map-entry' namespace='##targetNamespace'"
	 * @generated
	 */
	MapEntryType getMapEntry();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getMapEntry <em>Map Entry</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Map Entry</em>' containment reference.
	 * @see #getMapEntry()
	 * @generated
	 */
	void setMapEntry(MapEntryType value);

    /**
	 * Returns the value of the '<em><b>Message Bundle</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The base name of a resource bundle
	 *                 representing the message resources     for this
	 *                 application.  See the JavaDocs for the
	 *                 "java.util.ResourceBundle"     class for more
	 *                 information on the syntax of resource bundle names. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Message Bundle</em>' containment reference.
	 * @see #setMessageBundle(MessageBundleType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_MessageBundle()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='message-bundle' namespace='##targetNamespace'"
	 * @generated
	 */
	MessageBundleType getMessageBundle();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getMessageBundle <em>Message Bundle</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Bundle</em>' containment reference.
	 * @see #getMessageBundle()
	 * @generated
	 */
	void setMessageBundle(MessageBundleType value);

    /**
	 * Returns the value of the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' containment reference.
	 * @see #setName(NameType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Name()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
	 * @generated
	 */
	NameType getName();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(NameType value);

				/**
	 * Returns the value of the '<em><b>Navigation Case</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "navigation-case"
	 *                 element describes a particular combination of
	 *                 conditions that must match for this case to be executed,
	 *                 and the     view id of the component tree that should be
	 *                 selected next. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Navigation Case</em>' containment reference.
	 * @see #setNavigationCase(NavigationCaseType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_NavigationCase()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='navigation-case' namespace='##targetNamespace'"
	 * @generated
	 */
	NavigationCaseType getNavigationCase();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getNavigationCase <em>Navigation Case</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Navigation Case</em>' containment reference.
	 * @see #getNavigationCase()
	 * @generated
	 */
	void setNavigationCase(NavigationCaseType value);

    /**
	 * Returns the value of the '<em><b>Navigation Handler</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "navigation-handler"
	 *                 element contains the fully qualified class name     of
	 *                 the concrete NavigationHandler implementation class that
	 *                 will be called     during the Invoke Application phase
	 *                 of the request processing lifecycle,     if the default
	 *                 ActionListener (provided by the JSF implementation) is
	 *                 used.     It must be of type "ClassName". 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Navigation Handler</em>' containment reference.
	 * @see #setNavigationHandler(NavigationHandlerType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_NavigationHandler()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='navigation-handler' namespace='##targetNamespace'"
	 * @generated
	 */
	NavigationHandlerType getNavigationHandler();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getNavigationHandler <em>Navigation Handler</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Navigation Handler</em>' containment reference.
	 * @see #getNavigationHandler()
	 * @generated
	 */
	void setNavigationHandler(NavigationHandlerType value);

    /**
	 * Returns the value of the '<em><b>Navigation Rule</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "navigation-rule"
	 *                 element represents an individual decision rule     that
	 *                 will be utilized by the default NavigationHandler
	 *                 implementation to make decisions on what view should be
	 *                 displayed     next, based on the view id being
	 *                 processed. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Navigation Rule</em>' containment reference.
	 * @see #setNavigationRule(NavigationRuleType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_NavigationRule()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='navigation-rule' namespace='##targetNamespace'"
	 * @generated
	 */
	NavigationRuleType getNavigationRule();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getNavigationRule <em>Navigation Rule</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Navigation Rule</em>' containment reference.
	 * @see #getNavigationRule()
	 * @generated
	 */
	void setNavigationRule(NavigationRuleType value);

    /**
	 * Returns the value of the '<em><b>Null Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "null-value" element
	 *                 indicates that the managed property in which we     are
	 *                 nested will be explicitly set to null if our managed
	 *                 bean is     automatically created.  This is different
	 *                 from omitting the managed     property element entirely,
	 *                 which will cause no property setter to be     called for
	 *                 this property.      The "null-value" element
	 *                 can only be used when the associated
	 *                 "property-class" identifies a Java class, not
	 *                 a Java primitive. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Null Value</em>' containment reference.
	 * @see #setNullValue(NullValueType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_NullValue()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='null-value' namespace='##targetNamespace'"
	 * @generated
	 */
	NullValueType getNullValue();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getNullValue <em>Null Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Null Value</em>' containment reference.
	 * @see #getNullValue()
	 * @generated
	 */
	void setNullValue(NullValueType value);

    /**
	 * Returns the value of the '<em><b>Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ordering</em>' containment reference.
	 * @see #setOrdering(OrderingType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Ordering()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ordering' namespace='##targetNamespace'"
	 * @generated
	 */
	OrderingType getOrdering();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getOrdering <em>Ordering</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ordering</em>' containment reference.
	 * @see #getOrdering()
	 * @generated
	 */
	void setOrdering(OrderingType value);

				/**
	 * Returns the value of the '<em><b>Ordering Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ordering Ordering</em>' containment reference.
	 * @see #setOrderingOrdering(OrderingOrderingType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_OrderingOrdering()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ordering-ordering' namespace='##targetNamespace'"
	 * @generated
	 */
	OrderingOrderingType getOrderingOrdering();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getOrderingOrdering <em>Ordering Ordering</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ordering Ordering</em>' containment reference.
	 * @see #getOrderingOrdering()
	 * @generated
	 */
	void setOrderingOrdering(OrderingOrderingType value);

				/**
	 * Returns the value of the '<em><b>Others</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Others</em>' containment reference.
	 * @see #setOthers(OrderingOthersType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Others()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='others' namespace='##targetNamespace'"
	 * @generated
	 */
	OrderingOthersType getOthers();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getOthers <em>Others</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Others</em>' containment reference.
	 * @see #getOthers()
	 * @generated
	 */
	void setOthers(OrderingOthersType value);

				/**
	 * Returns the value of the '<em><b>Partial View Context Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Partial View Context Factory</em>' containment reference.
	 * @see #setPartialViewContextFactory(PartialViewContextFactoryType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_PartialViewContextFactory()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='partial-view-context-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	PartialViewContextFactoryType getPartialViewContextFactory();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPartialViewContextFactory <em>Partial View Context Factory</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partial View Context Factory</em>' containment reference.
	 * @see #getPartialViewContextFactory()
	 * @generated
	 */
	void setPartialViewContextFactory(PartialViewContextFactoryType value);

				/**
	 * Returns the value of the '<em><b>Phase Listener</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  The "phase-listener" element
	 *                 contains the fully qualified class name of the concrete
	 *                 PhaseListener implementation class that will be
	 *                 registered on the Lifecycle. It must be of type
	 *                 "ClassName". 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Phase Listener</em>' containment reference.
	 * @see #setPhaseListener(PhaseListenerType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_PhaseListener()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='phase-listener' namespace='##targetNamespace'"
	 * @generated
	 */
	PhaseListenerType getPhaseListener();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPhaseListener <em>Phase Listener</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phase Listener</em>' containment reference.
	 * @see #getPhaseListener()
	 * @generated
	 */
	void setPhaseListener(PhaseListenerType value);

    /**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "property" element
	 *                 represents a JavaBean property of the Java class
	 *                 represented by our parent element.      Property names
	 *                 must be unique within the scope of the Java class
	 *                 that is represented by the parent element, and must
	 *                 correspond to     property names that will be recognized
	 *                 when performing introspection     against that class via
	 *                 java.beans.Introspector. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Property</em>' containment reference.
	 * @see #setProperty(PropertyType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Property()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='property' namespace='##targetNamespace'"
	 * @generated
	 */
	PropertyType getProperty();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getProperty <em>Property</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property</em>' containment reference.
	 * @see #getProperty()
	 * @generated
	 */
	void setProperty(PropertyType value);

    /**
	 * Returns the value of the '<em><b>Property Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "property-class" element represents the Java type of the value
	 *     associated with this property name.  It must be of type "JavaType".
	 *     If not specified, it can be inferred from existing classes; however,
	 *     this element should be specified if the configuration file is going
	 *     to be the source for generating the corresponding classes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Property Class</em>' containment reference.
	 * @see #setPropertyClass(PropertyClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_PropertyClass()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='property-class' namespace='##targetNamespace'"
	 * @generated
	 */
	PropertyClassType getPropertyClass();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPropertyClass <em>Property Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property Class</em>' containment reference.
	 * @see #getPropertyClass()
	 * @generated
	 */
	void setPropertyClass(PropertyClassType value);

    /**
	 * Returns the value of the '<em><b>Property Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *       Extension element for property.
	 *                 May contain implementation     specific content. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Property Extension</em>' containment reference.
	 * @see #setPropertyExtension(PropertyExtensionType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_PropertyExtension()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='property-extension' namespace='##targetNamespace'"
	 * @generated
	 */
	PropertyExtensionType getPropertyExtension();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPropertyExtension <em>Property Extension</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property Extension</em>' containment reference.
	 * @see #getPropertyExtension()
	 * @generated
	 */
	void setPropertyExtension(PropertyExtensionType value);

    /**
	 * Returns the value of the '<em><b>Property Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "property-name" element represents the JavaBeans property name
	 *     under which the corresponding value may be stored.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Property Name</em>' containment reference.
	 * @see #setPropertyName(PropertyNameType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_PropertyName()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='property-name' namespace='##targetNamespace'"
	 * @generated
	 */
	PropertyNameType getPropertyName();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPropertyName <em>Property Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property Name</em>' containment reference.
	 * @see #getPropertyName()
	 * @generated
	 */
	void setPropertyName(PropertyNameType value);

    /**
	 * Returns the value of the '<em><b>Property Resolver</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "property-resolver"
	 *                 element contains the fully qualified class name     of
	 *                 the concrete PropertyResolver implementation class that
	 *                 will be used     during the processing of value
	 *                 reference expressions.     It must be of type
	 *                 "ClassName". 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Property Resolver</em>' containment reference.
	 * @see #setPropertyResolver(PropertyResolverType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_PropertyResolver()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='property-resolver' namespace='##targetNamespace'"
	 * @generated
	 */
	PropertyResolverType getPropertyResolver();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPropertyResolver <em>Property Resolver</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property Resolver</em>' containment reference.
	 * @see #getPropertyResolver()
	 * @generated
	 */
	void setPropertyResolver(PropertyResolverType value);

    /**
	 * Returns the value of the '<em><b>Redirect</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "redirect" element
	 *                 indicates that navigation to the specified
	 *                 "to-view-id" should be accomplished by
	 *                 performing an HTTP redirect     rather than the usual
	 *                 ViewHandler mechanisms. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Redirect</em>' containment reference.
	 * @see #setRedirect(RedirectType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Redirect()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='redirect' namespace='##targetNamespace'"
	 * @generated
	 */
	RedirectType getRedirect();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRedirect <em>Redirect</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Redirect</em>' containment reference.
	 * @see #getRedirect()
	 * @generated
	 */
	void setRedirect(RedirectType value);

    /**
	 * Returns the value of the '<em><b>Redirect View Param</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Redirect View Param</em>' containment reference.
	 * @see #setRedirectViewParam(RedirectViewParamType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_RedirectViewParam()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='view-param' namespace='##targetNamespace'"
	 * @generated
	 */
	RedirectViewParamType getRedirectViewParam();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRedirectViewParam <em>Redirect View Param</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Redirect View Param</em>' containment reference.
	 * @see #getRedirectViewParam()
	 * @generated
	 */
	void setRedirectViewParam(RedirectViewParamType value);

				/**
	 * Returns the value of the '<em><b>Referenced Bean</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "referenced-bean"
	 *                 element represents at design time the promise     that a
	 *                 Java object of the specified type will exist at runtime
	 *                 in some     scope, under the specified key.  This can be
	 *                 used by design time tools     to construct user
	 *                 interface dialogs based on the properties of the
	 *                 specified class.  The presence or absence of a
	 *                 referenced bean     element has no impact on the
	 *                 JavaServer Faces runtime environment     inside a web
	 *                 application. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Referenced Bean</em>' containment reference.
	 * @see #setReferencedBean(ReferencedBeanType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ReferencedBean()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='referenced-bean' namespace='##targetNamespace'"
	 * @generated
	 */
	ReferencedBeanType getReferencedBean();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getReferencedBean <em>Referenced Bean</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Bean</em>' containment reference.
	 * @see #getReferencedBean()
	 * @generated
	 */
	void setReferencedBean(ReferencedBeanType value);

    /**
	 * Returns the value of the '<em><b>Referenced Bean Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "referenced-bean-class" element represents the fully qualified class
	 *     name of the Java class (either abstract or concrete) or Java interface
	 *     implemented by the corresponding referenced bean.  It must be of type
	 *     "ClassName".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Referenced Bean Class</em>' containment reference.
	 * @see #setReferencedBeanClass(ReferencedBeanClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ReferencedBeanClass()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='referenced-bean-class' namespace='##targetNamespace'"
	 * @generated
	 */
	ReferencedBeanClassType getReferencedBeanClass();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getReferencedBeanClass <em>Referenced Bean Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Bean Class</em>' containment reference.
	 * @see #getReferencedBeanClass()
	 * @generated
	 */
	void setReferencedBeanClass(ReferencedBeanClassType value);

    /**
	 * Returns the value of the '<em><b>Referenced Bean Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "referenced-bean-name" element represents the attribute name under
	 *     which the corresponding referenced bean may be assumed to be stored,
	 *     in one of the scopes defined by the "Scope" type.  It must be of type
	 *     "Identifier".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Referenced Bean Name</em>' containment reference.
	 * @see #setReferencedBeanName(ReferencedBeanNameType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ReferencedBeanName()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='referenced-bean-name' namespace='##targetNamespace'"
	 * @generated
	 */
	ReferencedBeanNameType getReferencedBeanName();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getReferencedBeanName <em>Referenced Bean Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Bean Name</em>' containment reference.
	 * @see #getReferencedBeanName()
	 * @generated
	 */
	void setReferencedBeanName(ReferencedBeanNameType value);

    /**
	 * Returns the value of the '<em><b>Renderer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "renderer" element
	 *                 represents a concrete Renderer implementation     class
	 *                 that should be registered under the specified type
	 *                 identifier,     in the RenderKit associated with the
	 *                 parent render-kit element.  Renderer     types must be
	 *                 unique within the RenderKit associated with the parent
	 *                 "render-kit" element.      Nested
	 *                 "attribute" elements identify generic
	 *                 component attributes that     are recognized by this
	 *                 renderer.  Nested "supported-component-type"
	 *                 and     "supported-component-class" elements
	 *                 identify supported component classes,     by their type
	 *                 identifiers or the implementation class name,
	 *                 respectively,     that are supported by this Renderer. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Renderer</em>' containment reference.
	 * @see #setRenderer(RendererType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Renderer()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='renderer' namespace='##targetNamespace'"
	 * @generated
	 */
	RendererType getRenderer();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderer <em>Renderer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Renderer</em>' containment reference.
	 * @see #getRenderer()
	 * @generated
	 */
	void setRenderer(RendererType value);

    /**
	 * Returns the value of the '<em><b>Renderer Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "renderer-class" element represents the fully qualified class name
	 *     of a concrete Renderer implementation class.  It must be of
	 *     type "ClassName".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Renderer Class</em>' containment reference.
	 * @see #setRendererClass(RendererClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_RendererClass()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='renderer-class' namespace='##targetNamespace'"
	 * @generated
	 */
	RendererClassType getRendererClass();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRendererClass <em>Renderer Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Renderer Class</em>' containment reference.
	 * @see #getRendererClass()
	 * @generated
	 */
	void setRendererClass(RendererClassType value);

    /**
	 * Returns the value of the '<em><b>Renderer Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *       Extension element for renderer.
	 *                 May contain implementation     specific content. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Renderer Extension</em>' containment reference.
	 * @see #setRendererExtension(RendererExtensionType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_RendererExtension()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='renderer-extension' namespace='##targetNamespace'"
	 * @generated
	 */
	RendererExtensionType getRendererExtension();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRendererExtension <em>Renderer Extension</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Renderer Extension</em>' containment reference.
	 * @see #getRendererExtension()
	 * @generated
	 */
	void setRendererExtension(RendererExtensionType value);

    /**
	 * Returns the value of the '<em><b>Renderer Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "renderer-type" element represents a renderer type identifier for the
	 *     Renderer represented by the parent "renderer" element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Renderer Type</em>' containment reference.
	 * @see #setRendererType(RendererTypeType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_RendererType()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='renderer-type' namespace='##targetNamespace'"
	 * @generated
	 */
	RendererTypeType getRendererType();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRendererType <em>Renderer Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Renderer Type</em>' containment reference.
	 * @see #getRendererType()
	 * @generated
	 */
	void setRendererType(RendererTypeType value);

    /**
	 * Returns the value of the '<em><b>Render Kit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "render-kit" element
	 *                 represents a concrete RenderKit implementation     that
	 *                 should be registered under the specified render-kit-id.
	 *                 If no     render-kit-id is specified, the identifier of
	 *                 the default RenderKit
	 *                 (RenderKitFactory.DEFAULT_RENDER_KIT) is assumed. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Render Kit</em>' containment reference.
	 * @see #setRenderKit(RenderKitType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_RenderKit()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='render-kit' namespace='##targetNamespace'"
	 * @generated
	 */
	RenderKitType getRenderKit();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderKit <em>Render Kit</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Render Kit</em>' containment reference.
	 * @see #getRenderKit()
	 * @generated
	 */
	void setRenderKit(RenderKitType value);

    /**
	 * Returns the value of the '<em><b>Render Kit Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "render-kit-class" element represents the fully qualified class name
	 *     of a concrete RenderKit implementation class.  It must be of
	 *     type "ClassName".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Render Kit Class</em>' containment reference.
	 * @see #setRenderKitClass(RenderKitClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_RenderKitClass()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='render-kit-class' namespace='##targetNamespace'"
	 * @generated
	 */
	RenderKitClassType getRenderKitClass();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderKitClass <em>Render Kit Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Render Kit Class</em>' containment reference.
	 * @see #getRenderKitClass()
	 * @generated
	 */
	void setRenderKitClass(RenderKitClassType value);

    /**
	 * Returns the value of the '<em><b>Render Kit Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "render-kit-factory"
	 *                 element contains the fully qualified class name     of
	 *                 the concrete RenderKitFactory implementation class that
	 *                 will be called     when
	 *                 FactoryFinder.getFactory(RENDER_KIT_FACTORY) is called.
	 *                 It must be      of type "ClassName". 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Render Kit Factory</em>' containment reference.
	 * @see #setRenderKitFactory(RenderKitFactoryType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_RenderKitFactory()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='render-kit-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	RenderKitFactoryType getRenderKitFactory();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderKitFactory <em>Render Kit Factory</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Render Kit Factory</em>' containment reference.
	 * @see #getRenderKitFactory()
	 * @generated
	 */
	void setRenderKitFactory(RenderKitFactoryType value);

    /**
	 * Returns the value of the '<em><b>Render Kit Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "render-kit-id" element represents an identifier for the
	 *     RenderKit represented by the parent "render-kit" element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Render Kit Id</em>' containment reference.
	 * @see #setRenderKitId(RenderKitIdType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_RenderKitId()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='render-kit-id' namespace='##targetNamespace'"
	 * @generated
	 */
	RenderKitIdType getRenderKitId();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderKitId <em>Render Kit Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Render Kit Id</em>' containment reference.
	 * @see #getRenderKitId()
	 * @generated
	 */
	void setRenderKitId(RenderKitIdType value);

    /**
	 * Returns the value of the '<em><b>Resource Handler</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resource Handler</em>' containment reference.
	 * @see #setResourceHandler(ResourceHandlerType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ResourceHandler()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='resource-handler' namespace='##targetNamespace'"
	 * @generated
	 */
	ResourceHandlerType getResourceHandler();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getResourceHandler <em>Resource Handler</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Handler</em>' containment reference.
	 * @see #getResourceHandler()
	 * @generated
	 */
	void setResourceHandler(ResourceHandlerType value);

				/**
	 * Returns the value of the '<em><b>Small Icon</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "small-icon" element contains the resource path to a small (16x16)
	 *     icon image.  The image may be in either GIF or JPG format.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Small Icon</em>' containment reference.
	 * @see #setSmallIcon(SmallIconType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_SmallIcon()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='small-icon' namespace='##targetNamespace'"
	 * @generated
	 */
	SmallIconType getSmallIcon();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSmallIcon <em>Small Icon</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Small Icon</em>' containment reference.
	 * @see #getSmallIcon()
	 * @generated
	 */
	void setSmallIcon(SmallIconType value);

    /**
	 * Returns the value of the '<em><b>Source Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source Class</em>' containment reference.
	 * @see #setSourceClass(SourceClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_SourceClass()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='source-class' namespace='##targetNamespace'"
	 * @generated
	 */
	SourceClassType getSourceClass();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSourceClass <em>Source Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Class</em>' containment reference.
	 * @see #getSourceClass()
	 * @generated
	 */
	void setSourceClass(SourceClassType value);

				/**
	 * Returns the value of the '<em><b>State Manager</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *       The "state-manager"
	 *                 element contains the fully qualified class name     of
	 *                 the concrete StateManager implementation class that will
	 *                 be called     during the Restore View and Render
	 *                 Response phases of the request     processing lifecycle.
	 *                 The faces implementation must provide a     default
	 *                 implementation of this class 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>State Manager</em>' containment reference.
	 * @see #setStateManager(StateManagerType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_StateManager()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='state-manager' namespace='##targetNamespace'"
	 * @generated
	 */
	StateManagerType getStateManager();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getStateManager <em>State Manager</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State Manager</em>' containment reference.
	 * @see #getStateManager()
	 * @generated
	 */
	void setStateManager(StateManagerType value);

    /**
	 * Returns the value of the '<em><b>Suggested Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "suggested-value" contains the value for the property or
	 *     attribute in which this element resides.  This value is advisory
	 *     only and is intended for tools to use when populating pallettes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Suggested Value</em>' containment reference.
	 * @see #setSuggestedValue(SuggestedValueType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_SuggestedValue()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='suggested-value' namespace='##targetNamespace'"
	 * @generated
	 */
	SuggestedValueType getSuggestedValue();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSuggestedValue <em>Suggested Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suggested Value</em>' containment reference.
	 * @see #getSuggestedValue()
	 * @generated
	 */
	void setSuggestedValue(SuggestedValueType value);

    /**
	 * Returns the value of the '<em><b>Supported Locale</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *       The "supported-locale"
	 *                 element allows authors to declare which      locales are
	 *                 supported in this application instance.       It must be
	 *                 specified as :language:[_:country:[_:variant:]] without
	 *                 the colons, for example "ja_JP_SJIS".  The
	 *                 separators between the      segments may be
	 *                 '-' or '_'. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Supported Locale</em>' containment reference.
	 * @see #setSupportedLocale(SupportedLocaleType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_SupportedLocale()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='supported-locale' namespace='##targetNamespace'"
	 * @generated
	 */
	SupportedLocaleType getSupportedLocale();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSupportedLocale <em>Supported Locale</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Supported Locale</em>' containment reference.
	 * @see #getSupportedLocale()
	 * @generated
	 */
	void setSupportedLocale(SupportedLocaleType value);

    /**
	 * Returns the value of the '<em><b>System Event Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>System Event Class</em>' containment reference.
	 * @see #setSystemEventClass(SystemEventClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_SystemEventClass()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='system-event-class' namespace='##targetNamespace'"
	 * @generated
	 */
	SystemEventClassType getSystemEventClass();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSystemEventClass <em>System Event Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Event Class</em>' containment reference.
	 * @see #getSystemEventClass()
	 * @generated
	 */
	void setSystemEventClass(SystemEventClassType value);

				/**
	 * Returns the value of the '<em><b>System Event Listener</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>System Event Listener</em>' containment reference.
	 * @see #setSystemEventListener(SystemEventListenerType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_SystemEventListener()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='system-event-listener' namespace='##targetNamespace'"
	 * @generated
	 */
	SystemEventListenerType getSystemEventListener();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSystemEventListener <em>System Event Listener</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Event Listener</em>' containment reference.
	 * @see #getSystemEventListener()
	 * @generated
	 */
	void setSystemEventListener(SystemEventListenerType value);

				/**
	 * Returns the value of the '<em><b>System Event Listener Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>System Event Listener Class</em>' containment reference.
	 * @see #setSystemEventListenerClass(SystemEventListenerClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_SystemEventListenerClass()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='system-event-listener-class' namespace='##targetNamespace'"
	 * @generated
	 */
	SystemEventListenerClassType getSystemEventListenerClass();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSystemEventListenerClass <em>System Event Listener Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Event Listener Class</em>' containment reference.
	 * @see #getSystemEventListenerClass()
	 * @generated
	 */
	void setSystemEventListenerClass(SystemEventListenerClassType value);

				/**
	 * Returns the value of the '<em><b>Tag Handler Delegate Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tag Handler Delegate Factory</em>' containment reference.
	 * @see #setTagHandlerDelegateFactory(TagHandlerDelegateFactoryType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_TagHandlerDelegateFactory()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='tag-handler-delegate-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	TagHandlerDelegateFactoryType getTagHandlerDelegateFactory();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getTagHandlerDelegateFactory <em>Tag Handler Delegate Factory</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tag Handler Delegate Factory</em>' containment reference.
	 * @see #getTagHandlerDelegateFactory()
	 * @generated
	 */
	void setTagHandlerDelegateFactory(TagHandlerDelegateFactoryType value);

				/**
	 * Returns the value of the '<em><b>To View Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "to-view" element contains the view identifier of the next view
	 *     that should be displayed if this navigation rule is matched.  It
	 *     must be of type "ViewId".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>To View Id</em>' containment reference.
	 * @see #setToViewId(ToViewIdType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ToViewId()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='to-view-id' namespace='##targetNamespace'"
	 * @generated
	 */
	ToViewIdType getToViewId();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getToViewId <em>To View Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To View Id</em>' containment reference.
	 * @see #getToViewId()
	 * @generated
	 */
	void setToViewId(ToViewIdType value);

    /**
	 * Returns the value of the '<em><b>Validator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "validator" element
	 *                 represents a concrete Validator implementation     class
	 *                 that should be registered under the specified validator
	 *                 identifier.     Validator identifiers must be unique
	 *                 within the entire web application.      Nested
	 *                 "attribute" elements identify generic
	 *                 attributes that may be     configured on the
	 *                 corresponding UIComponent in order to affect the
	 *                 operation of the Validator.  Nested "property"
	 *                 elements identify JavaBeans     properties of the
	 *                 Validator implementation class that may be configured
	 *                 to affect the operation of the Validator. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Validator</em>' containment reference.
	 * @see #setValidator(ValidatorType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Validator()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='validator' namespace='##targetNamespace'"
	 * @generated
	 */
	ValidatorType getValidator();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValidator <em>Validator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Validator</em>' containment reference.
	 * @see #getValidator()
	 * @generated
	 */
	void setValidator(ValidatorType value);

    /**
	 * Returns the value of the '<em><b>Validator Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "validator-class" element represents the fully qualified class name
	 *     of a concrete Validator implementation class.  It must be of
	 *     type "ClassName".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Validator Class</em>' containment reference.
	 * @see #setValidatorClass(ValidatorClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ValidatorClass()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='validator-class' namespace='##targetNamespace'"
	 * @generated
	 */
	ValidatorClassType getValidatorClass();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValidatorClass <em>Validator Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Validator Class</em>' containment reference.
	 * @see #getValidatorClass()
	 * @generated
	 */
	void setValidatorClass(ValidatorClassType value);

    /**
	 * Returns the value of the '<em><b>Validator Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "validator-id" element represents the identifier under which the
	 *     corresponding Validator class should be registered.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Validator Id</em>' containment reference.
	 * @see #setValidatorId(ValidatorIdType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ValidatorId()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='validator-id' namespace='##targetNamespace'"
	 * @generated
	 */
	ValidatorIdType getValidatorId();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValidatorId <em>Validator Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Validator Id</em>' containment reference.
	 * @see #getValidatorId()
	 * @generated
	 */
	void setValidatorId(ValidatorIdType value);

    /**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "value" element is the
	 *                 String representation of a literal     value to which a
	 *                 scalar managed property will be set, or a value
	 *                 reference expression ("#{...}") that will be
	 *                 used to calculate the     required value.  It will be
	 *                 converted as specified for the actual     property type. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(ValueType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_Value()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='value' namespace='##targetNamespace'"
	 * @generated
	 */
	ValueType getValue();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(ValueType value);

    /**
	 * Returns the value of the '<em><b>Value Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *     The "value-class" element defines the Java type to which each
	 *     "value" element's value will be converted to, prior to adding it to
	 *     the "list-entries" list for a managed property that is a
	 *     java.util.List, or a "map-entries" map for a managed property that
	 *     is a java.util.Map.  It must be of type "ClassName".  If omitted,
	 *     "java.lang.String" is assumed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value Class</em>' containment reference.
	 * @see #setValueClass(ValueClassType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ValueClass()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='value-class' namespace='##targetNamespace'"
	 * @generated
	 */
	ValueClassType getValueClass();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValueClass <em>Value Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Class</em>' containment reference.
	 * @see #getValueClass()
	 * @generated
	 */
	void setValueClass(ValueClassType value);

    /**
	 * Returns the value of the '<em><b>Variable Resolver</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *      The "variable-resolver"
	 *                 element contains the fully qualified class name     of
	 *                 the concrete VariableResolver implementation class that
	 *                 will be used     during the processing of value
	 *                 reference expressions.     It must be of type
	 *                 "ClassName". 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Variable Resolver</em>' containment reference.
	 * @see #setVariableResolver(VariableResolverType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_VariableResolver()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='variable-resolver' namespace='##targetNamespace'"
	 * @generated
	 */
	VariableResolverType getVariableResolver();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getVariableResolver <em>Variable Resolver</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable Resolver</em>' containment reference.
	 * @see #getVariableResolver()
	 * @generated
	 */
	void setVariableResolver(VariableResolverType value);

    /**
	 * Returns the value of the '<em><b>View Declaration Language Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>View Declaration Language Factory</em>' containment reference.
	 * @see #setViewDeclarationLanguageFactory(ViewDeclarationLanguageFactoryType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ViewDeclarationLanguageFactory()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='view-declaration-language-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	ViewDeclarationLanguageFactoryType getViewDeclarationLanguageFactory();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getViewDeclarationLanguageFactory <em>View Declaration Language Factory</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>View Declaration Language Factory</em>' containment reference.
	 * @see #getViewDeclarationLanguageFactory()
	 * @generated
	 */
	void setViewDeclarationLanguageFactory(ViewDeclarationLanguageFactoryType value);

				/**
	 * Returns the value of the '<em><b>View Handler</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *       The "view-handler"
	 *                 element contains the fully qualified class name     of
	 *                 the concrete ViewHandler implementation class that will
	 *                 be called     during the Restore View and Render
	 *                 Response phases of the request     processing lifecycle.
	 *                 The faces implementation must provide a     default
	 *                 implementation of this class 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>View Handler</em>' containment reference.
	 * @see #setViewHandler(ViewHandlerType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_ViewHandler()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='view-handler' namespace='##targetNamespace'"
	 * @generated
	 */
	ViewHandlerType getViewHandler();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getViewHandler <em>View Handler</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>View Handler</em>' containment reference.
	 * @see #getViewHandler()
	 * @generated
	 */
	void setViewHandler(ViewHandlerType value);

				/**
	 * Returns the value of the '<em><b>Visit Context Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Visit Context Factory</em>' containment reference.
	 * @see #setVisitContextFactory(VisitContextFactoryType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getDocumentRoot_VisitContextFactory()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='visit-context-factory' namespace='##targetNamespace'"
	 * @generated
	 */
	VisitContextFactoryType getVisitContextFactory();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getVisitContextFactory <em>Visit Context Factory</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visit Context Factory</em>' containment reference.
	 * @see #getVisitContextFactory()
	 * @generated
	 */
	void setVisitContextFactory(VisitContextFactoryType value);

} // DocumentRoot
