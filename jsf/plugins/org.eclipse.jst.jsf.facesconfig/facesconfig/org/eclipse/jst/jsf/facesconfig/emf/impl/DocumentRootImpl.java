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

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.facesconfig.emf.ActionListenerType;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeClassType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeNameType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentFamilyType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterForClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterIdType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultLocaleType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultRenderKitIdType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultValueType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FacetExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.FacetNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacetType;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FromActionType;
import org.eclipse.jst.jsf.facesconfig.emf.FromOutcomeType;
import org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.KeyClassType;
import org.eclipse.jst.jsf.facesconfig.emf.KeyType;
import org.eclipse.jst.jsf.facesconfig.emf.LargeIconType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleType;
import org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.LocaleConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.MapEntryType;
import org.eclipse.jst.jsf.facesconfig.emf.MessageBundleType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationHandlerType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.NullValueType;
import org.eclipse.jst.jsf.facesconfig.emf.PhaseListenerType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyClassType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyResolverType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.RedirectType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitClassType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitIdType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererClassType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.SmallIconType;
import org.eclipse.jst.jsf.facesconfig.emf.StateManagerType;
import org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType;
import org.eclipse.jst.jsf.facesconfig.emf.SupportedLocaleType;
import org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorIdType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueType;
import org.eclipse.jst.jsf.facesconfig.emf.VariableResolverType;
import org.eclipse.jst.jsf.facesconfig.emf.ViewHandlerType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getActionListener <em>Action Listener</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getApplication <em>Application</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getApplicationFactory <em>Application Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getAttributeClass <em>Attribute Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getAttributeExtension <em>Attribute Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getAttributeName <em>Attribute Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getComponent <em>Component</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getComponentClass <em>Component Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getComponentExtension <em>Component Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getComponentFamily <em>Component Family</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getComponentType <em>Component Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getConverter <em>Converter</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getConverterClass <em>Converter Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getConverterForClass <em>Converter For Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getConverterId <em>Converter Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getDefaultLocale <em>Default Locale</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getDefaultRenderKitId <em>Default Render Kit Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getFacesConfig <em>Faces Config</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getFacesContextFactory <em>Faces Context Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getFacet <em>Facet</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getFacetExtension <em>Facet Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getFacetName <em>Facet Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getFactory <em>Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getFromAction <em>From Action</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getFromOutcome <em>From Outcome</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getFromViewId <em>From View Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getKeyClass <em>Key Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getLargeIcon <em>Large Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getLifecycle <em>Lifecycle</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getLifecycleFactory <em>Lifecycle Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getListEntries <em>List Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getLocaleConfig <em>Locale Config</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getManagedBean <em>Managed Bean</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getManagedBeanClass <em>Managed Bean Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getManagedBeanName <em>Managed Bean Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getManagedBeanScope <em>Managed Bean Scope</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getManagedProperty <em>Managed Property</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getMapEntries <em>Map Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getMapEntry <em>Map Entry</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getMessageBundle <em>Message Bundle</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getNavigationCase <em>Navigation Case</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getNavigationHandler <em>Navigation Handler</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getNavigationRule <em>Navigation Rule</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getNullValue <em>Null Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getPhaseListener <em>Phase Listener</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getPropertyClass <em>Property Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getPropertyExtension <em>Property Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getPropertyResolver <em>Property Resolver</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getRedirect <em>Redirect</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getReferencedBean <em>Referenced Bean</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getReferencedBeanClass <em>Referenced Bean Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getReferencedBeanName <em>Referenced Bean Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getRenderer <em>Renderer</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getRendererClass <em>Renderer Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getRendererExtension <em>Renderer Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getRendererType <em>Renderer Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getRenderKit <em>Render Kit</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getRenderKitClass <em>Render Kit Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getRenderKitFactory <em>Render Kit Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getRenderKitId <em>Render Kit Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getSmallIcon <em>Small Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getStateManager <em>State Manager</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getSuggestedValue <em>Suggested Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getSupportedLocale <em>Supported Locale</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getToViewId <em>To View Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getValidator <em>Validator</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getValidatorClass <em>Validator Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getValidatorId <em>Validator Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getValueClass <em>Value Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getVariableResolver <em>Variable Resolver</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl#getViewHandler <em>View Handler</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed = null;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap xMLNSPrefixMap = null;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap xSISchemaLocation = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.eINSTANCE.getDocumentRoot();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, FacesConfigPackage.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap(EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class, this, FacesConfigPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap(EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class, this, FacesConfigPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionListenerType getActionListener() {
		return (ActionListenerType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ActionListener(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActionListener(ActionListenerType newActionListener, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ActionListener(), newActionListener, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActionListener(ActionListenerType newActionListener) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ActionListener(), newActionListener);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationType getApplication() {
		return (ApplicationType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_Application(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetApplication(ApplicationType newApplication, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_Application(), newApplication, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setApplication(ApplicationType newApplication) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_Application(), newApplication);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationFactoryType getApplicationFactory() {
		return (ApplicationFactoryType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ApplicationFactory(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetApplicationFactory(ApplicationFactoryType newApplicationFactory, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ApplicationFactory(), newApplicationFactory, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setApplicationFactory(ApplicationFactoryType newApplicationFactory) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ApplicationFactory(), newApplicationFactory);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeType getAttribute() {
		return (AttributeType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_Attribute(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAttribute(AttributeType newAttribute, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_Attribute(), newAttribute, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttribute(AttributeType newAttribute) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_Attribute(), newAttribute);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeClassType getAttributeClass() {
		return (AttributeClassType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_AttributeClass(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAttributeClass(AttributeClassType newAttributeClass, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_AttributeClass(), newAttributeClass, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttributeClass(AttributeClassType newAttributeClass) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_AttributeClass(), newAttributeClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeExtensionType getAttributeExtension() {
		return (AttributeExtensionType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_AttributeExtension(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAttributeExtension(AttributeExtensionType newAttributeExtension, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_AttributeExtension(), newAttributeExtension, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttributeExtension(AttributeExtensionType newAttributeExtension) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_AttributeExtension(), newAttributeExtension);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeNameType getAttributeName() {
		return (AttributeNameType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_AttributeName(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAttributeName(AttributeNameType newAttributeName, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_AttributeName(), newAttributeName, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttributeName(AttributeNameType newAttributeName) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_AttributeName(), newAttributeName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentType getComponent() {
		return (ComponentType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_Component(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponent(ComponentType newComponent, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_Component(), newComponent, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponent(ComponentType newComponent) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_Component(), newComponent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentClassType getComponentClass() {
		return (ComponentClassType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ComponentClass(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponentClass(ComponentClassType newComponentClass, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ComponentClass(), newComponentClass, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentClass(ComponentClassType newComponentClass) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ComponentClass(), newComponentClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentExtensionType getComponentExtension() {
		return (ComponentExtensionType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ComponentExtension(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponentExtension(ComponentExtensionType newComponentExtension, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ComponentExtension(), newComponentExtension, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentExtension(ComponentExtensionType newComponentExtension) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ComponentExtension(), newComponentExtension);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentFamilyType getComponentFamily() {
		return (ComponentFamilyType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ComponentFamily(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponentFamily(ComponentFamilyType newComponentFamily, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ComponentFamily(), newComponentFamily, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentFamily(ComponentFamilyType newComponentFamily) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ComponentFamily(), newComponentFamily);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentTypeType getComponentType() {
		return (ComponentTypeType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ComponentType(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponentType(ComponentTypeType newComponentType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ComponentType(), newComponentType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentType(ComponentTypeType newComponentType) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ComponentType(), newComponentType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConverterType getConverter() {
		return (ConverterType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_Converter(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConverter(ConverterType newConverter, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_Converter(), newConverter, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConverter(ConverterType newConverter) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_Converter(), newConverter);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConverterClassType getConverterClass() {
		return (ConverterClassType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ConverterClass(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConverterClass(ConverterClassType newConverterClass, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ConverterClass(), newConverterClass, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConverterClass(ConverterClassType newConverterClass) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ConverterClass(), newConverterClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConverterForClassType getConverterForClass() {
		return (ConverterForClassType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ConverterForClass(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConverterForClass(ConverterForClassType newConverterForClass, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ConverterForClass(), newConverterForClass, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConverterForClass(ConverterForClassType newConverterForClass) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ConverterForClass(), newConverterForClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConverterIdType getConverterId() {
		return (ConverterIdType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ConverterId(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConverterId(ConverterIdType newConverterId, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ConverterId(), newConverterId, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConverterId(ConverterIdType newConverterId) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ConverterId(), newConverterId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DefaultLocaleType getDefaultLocale() {
		return (DefaultLocaleType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_DefaultLocale(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefaultLocale(DefaultLocaleType newDefaultLocale, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_DefaultLocale(), newDefaultLocale, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultLocale(DefaultLocaleType newDefaultLocale) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_DefaultLocale(), newDefaultLocale);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DefaultRenderKitIdType getDefaultRenderKitId() {
		return (DefaultRenderKitIdType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_DefaultRenderKitId(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefaultRenderKitId(DefaultRenderKitIdType newDefaultRenderKitId, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_DefaultRenderKitId(), newDefaultRenderKitId, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultRenderKitId(DefaultRenderKitIdType newDefaultRenderKitId) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_DefaultRenderKitId(), newDefaultRenderKitId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DefaultValueType getDefaultValue() {
		return (DefaultValueType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_DefaultValue(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefaultValue(DefaultValueType newDefaultValue, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_DefaultValue(), newDefaultValue, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultValue(DefaultValueType newDefaultValue) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_DefaultValue(), newDefaultValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DescriptionType getDescription() {
		return (DescriptionType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_Description(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDescription(DescriptionType newDescription, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_Description(), newDescription, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(DescriptionType newDescription) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_Description(), newDescription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisplayNameType getDisplayName() {
		return (DisplayNameType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_DisplayName(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDisplayName(DisplayNameType newDisplayName, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_DisplayName(), newDisplayName, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisplayName(DisplayNameType newDisplayName) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_DisplayName(), newDisplayName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FacesConfigType getFacesConfig() {
		return (FacesConfigType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_FacesConfig(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFacesConfig(FacesConfigType newFacesConfig, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_FacesConfig(), newFacesConfig, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacesConfig(FacesConfigType newFacesConfig) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_FacesConfig(), newFacesConfig);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FacesContextFactoryType getFacesContextFactory() {
		return (FacesContextFactoryType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_FacesContextFactory(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFacesContextFactory(FacesContextFactoryType newFacesContextFactory, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_FacesContextFactory(), newFacesContextFactory, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacesContextFactory(FacesContextFactoryType newFacesContextFactory) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_FacesContextFactory(), newFacesContextFactory);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FacetType getFacet() {
		return (FacetType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_Facet(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFacet(FacetType newFacet, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_Facet(), newFacet, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacet(FacetType newFacet) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_Facet(), newFacet);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FacetExtensionType getFacetExtension() {
		return (FacetExtensionType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_FacetExtension(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFacetExtension(FacetExtensionType newFacetExtension, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_FacetExtension(), newFacetExtension, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacetExtension(FacetExtensionType newFacetExtension) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_FacetExtension(), newFacetExtension);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FacetNameType getFacetName() {
		return (FacetNameType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_FacetName(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFacetName(FacetNameType newFacetName, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_FacetName(), newFacetName, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacetName(FacetNameType newFacetName) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_FacetName(), newFacetName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FactoryType getFactory() {
		return (FactoryType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_Factory(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFactory(FactoryType newFactory, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_Factory(), newFactory, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFactory(FactoryType newFactory) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_Factory(), newFactory);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FromActionType getFromAction() {
		return (FromActionType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_FromAction(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFromAction(FromActionType newFromAction, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_FromAction(), newFromAction, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFromAction(FromActionType newFromAction) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_FromAction(), newFromAction);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FromOutcomeType getFromOutcome() {
		return (FromOutcomeType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_FromOutcome(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFromOutcome(FromOutcomeType newFromOutcome, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_FromOutcome(), newFromOutcome, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFromOutcome(FromOutcomeType newFromOutcome) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_FromOutcome(), newFromOutcome);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FromViewIdType getFromViewId() {
		return (FromViewIdType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_FromViewId(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFromViewId(FromViewIdType newFromViewId, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_FromViewId(), newFromViewId, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFromViewId(FromViewIdType newFromViewId) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_FromViewId(), newFromViewId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IconType getIcon() {
		return (IconType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_Icon(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIcon(IconType newIcon, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_Icon(), newIcon, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIcon(IconType newIcon) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_Icon(), newIcon);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KeyType getKey() {
		return (KeyType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_Key(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetKey(KeyType newKey, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_Key(), newKey, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKey(KeyType newKey) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_Key(), newKey);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KeyClassType getKeyClass() {
		return (KeyClassType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_KeyClass(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetKeyClass(KeyClassType newKeyClass, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_KeyClass(), newKeyClass, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKeyClass(KeyClassType newKeyClass) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_KeyClass(), newKeyClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LargeIconType getLargeIcon() {
		return (LargeIconType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_LargeIcon(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLargeIcon(LargeIconType newLargeIcon, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_LargeIcon(), newLargeIcon, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLargeIcon(LargeIconType newLargeIcon) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_LargeIcon(), newLargeIcon);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LifecycleType getLifecycle() {
		return (LifecycleType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_Lifecycle(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLifecycle(LifecycleType newLifecycle, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_Lifecycle(), newLifecycle, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLifecycle(LifecycleType newLifecycle) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_Lifecycle(), newLifecycle);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LifecycleFactoryType getLifecycleFactory() {
		return (LifecycleFactoryType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_LifecycleFactory(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLifecycleFactory(LifecycleFactoryType newLifecycleFactory, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_LifecycleFactory(), newLifecycleFactory, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLifecycleFactory(LifecycleFactoryType newLifecycleFactory) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_LifecycleFactory(), newLifecycleFactory);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListEntriesType getListEntries() {
		return (ListEntriesType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ListEntries(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetListEntries(ListEntriesType newListEntries, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ListEntries(), newListEntries, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setListEntries(ListEntriesType newListEntries) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ListEntries(), newListEntries);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocaleConfigType getLocaleConfig() {
		return (LocaleConfigType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_LocaleConfig(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLocaleConfig(LocaleConfigType newLocaleConfig, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_LocaleConfig(), newLocaleConfig, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocaleConfig(LocaleConfigType newLocaleConfig) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_LocaleConfig(), newLocaleConfig);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManagedBeanType getManagedBean() {
		return (ManagedBeanType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ManagedBean(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetManagedBean(ManagedBeanType newManagedBean, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ManagedBean(), newManagedBean, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManagedBean(ManagedBeanType newManagedBean) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ManagedBean(), newManagedBean);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManagedBeanClassType getManagedBeanClass() {
		return (ManagedBeanClassType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ManagedBeanClass(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetManagedBeanClass(ManagedBeanClassType newManagedBeanClass, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ManagedBeanClass(), newManagedBeanClass, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManagedBeanClass(ManagedBeanClassType newManagedBeanClass) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ManagedBeanClass(), newManagedBeanClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManagedBeanNameType getManagedBeanName() {
		return (ManagedBeanNameType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ManagedBeanName(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetManagedBeanName(ManagedBeanNameType newManagedBeanName, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ManagedBeanName(), newManagedBeanName, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManagedBeanName(ManagedBeanNameType newManagedBeanName) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ManagedBeanName(), newManagedBeanName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManagedBeanScopeType getManagedBeanScope() {
		return (ManagedBeanScopeType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ManagedBeanScope(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetManagedBeanScope(ManagedBeanScopeType newManagedBeanScope, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ManagedBeanScope(), newManagedBeanScope, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManagedBeanScope(ManagedBeanScopeType newManagedBeanScope) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ManagedBeanScope(), newManagedBeanScope);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManagedPropertyType getManagedProperty() {
		return (ManagedPropertyType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ManagedProperty(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetManagedProperty(ManagedPropertyType newManagedProperty, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ManagedProperty(), newManagedProperty, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManagedProperty(ManagedPropertyType newManagedProperty) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ManagedProperty(), newManagedProperty);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapEntriesType getMapEntries() {
		return (MapEntriesType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_MapEntries(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMapEntries(MapEntriesType newMapEntries, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_MapEntries(), newMapEntries, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMapEntries(MapEntriesType newMapEntries) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_MapEntries(), newMapEntries);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapEntryType getMapEntry() {
		return (MapEntryType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_MapEntry(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMapEntry(MapEntryType newMapEntry, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_MapEntry(), newMapEntry, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMapEntry(MapEntryType newMapEntry) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_MapEntry(), newMapEntry);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageBundleType getMessageBundle() {
		return (MessageBundleType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_MessageBundle(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMessageBundle(MessageBundleType newMessageBundle, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_MessageBundle(), newMessageBundle, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMessageBundle(MessageBundleType newMessageBundle) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_MessageBundle(), newMessageBundle);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationCaseType getNavigationCase() {
		return (NavigationCaseType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_NavigationCase(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNavigationCase(NavigationCaseType newNavigationCase, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_NavigationCase(), newNavigationCase, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNavigationCase(NavigationCaseType newNavigationCase) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_NavigationCase(), newNavigationCase);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationHandlerType getNavigationHandler() {
		return (NavigationHandlerType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_NavigationHandler(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNavigationHandler(NavigationHandlerType newNavigationHandler, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_NavigationHandler(), newNavigationHandler, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNavigationHandler(NavigationHandlerType newNavigationHandler) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_NavigationHandler(), newNavigationHandler);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationRuleType getNavigationRule() {
		return (NavigationRuleType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_NavigationRule(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNavigationRule(NavigationRuleType newNavigationRule, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_NavigationRule(), newNavigationRule, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNavigationRule(NavigationRuleType newNavigationRule) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_NavigationRule(), newNavigationRule);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NullValueType getNullValue() {
		return (NullValueType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_NullValue(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNullValue(NullValueType newNullValue, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_NullValue(), newNullValue, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNullValue(NullValueType newNullValue) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_NullValue(), newNullValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhaseListenerType getPhaseListener() {
		return (PhaseListenerType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_PhaseListener(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPhaseListener(PhaseListenerType newPhaseListener, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_PhaseListener(), newPhaseListener, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPhaseListener(PhaseListenerType newPhaseListener) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_PhaseListener(), newPhaseListener);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyType getProperty() {
		return (PropertyType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_Property(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProperty(PropertyType newProperty, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_Property(), newProperty, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProperty(PropertyType newProperty) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_Property(), newProperty);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyClassType getPropertyClass() {
		return (PropertyClassType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_PropertyClass(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPropertyClass(PropertyClassType newPropertyClass, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_PropertyClass(), newPropertyClass, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPropertyClass(PropertyClassType newPropertyClass) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_PropertyClass(), newPropertyClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyExtensionType getPropertyExtension() {
		return (PropertyExtensionType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_PropertyExtension(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPropertyExtension(PropertyExtensionType newPropertyExtension, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_PropertyExtension(), newPropertyExtension, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPropertyExtension(PropertyExtensionType newPropertyExtension) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_PropertyExtension(), newPropertyExtension);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyNameType getPropertyName() {
		return (PropertyNameType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_PropertyName(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPropertyName(PropertyNameType newPropertyName, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_PropertyName(), newPropertyName, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPropertyName(PropertyNameType newPropertyName) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_PropertyName(), newPropertyName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyResolverType getPropertyResolver() {
		return (PropertyResolverType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_PropertyResolver(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPropertyResolver(PropertyResolverType newPropertyResolver, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_PropertyResolver(), newPropertyResolver, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPropertyResolver(PropertyResolverType newPropertyResolver) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_PropertyResolver(), newPropertyResolver);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RedirectType getRedirect() {
		return (RedirectType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_Redirect(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRedirect(RedirectType newRedirect, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_Redirect(), newRedirect, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRedirect(RedirectType newRedirect) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_Redirect(), newRedirect);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferencedBeanType getReferencedBean() {
		return (ReferencedBeanType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ReferencedBean(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReferencedBean(ReferencedBeanType newReferencedBean, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ReferencedBean(), newReferencedBean, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferencedBean(ReferencedBeanType newReferencedBean) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ReferencedBean(), newReferencedBean);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferencedBeanClassType getReferencedBeanClass() {
		return (ReferencedBeanClassType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ReferencedBeanClass(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReferencedBeanClass(ReferencedBeanClassType newReferencedBeanClass, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ReferencedBeanClass(), newReferencedBeanClass, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferencedBeanClass(ReferencedBeanClassType newReferencedBeanClass) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ReferencedBeanClass(), newReferencedBeanClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferencedBeanNameType getReferencedBeanName() {
		return (ReferencedBeanNameType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ReferencedBeanName(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReferencedBeanName(ReferencedBeanNameType newReferencedBeanName, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ReferencedBeanName(), newReferencedBeanName, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferencedBeanName(ReferencedBeanNameType newReferencedBeanName) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ReferencedBeanName(), newReferencedBeanName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RendererType getRenderer() {
		return (RendererType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_Renderer(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRenderer(RendererType newRenderer, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_Renderer(), newRenderer, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRenderer(RendererType newRenderer) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_Renderer(), newRenderer);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RendererClassType getRendererClass() {
		return (RendererClassType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_RendererClass(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRendererClass(RendererClassType newRendererClass, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_RendererClass(), newRendererClass, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRendererClass(RendererClassType newRendererClass) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_RendererClass(), newRendererClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RendererExtensionType getRendererExtension() {
		return (RendererExtensionType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_RendererExtension(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRendererExtension(RendererExtensionType newRendererExtension, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_RendererExtension(), newRendererExtension, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRendererExtension(RendererExtensionType newRendererExtension) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_RendererExtension(), newRendererExtension);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RendererTypeType getRendererType() {
		return (RendererTypeType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_RendererType(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRendererType(RendererTypeType newRendererType, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_RendererType(), newRendererType, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRendererType(RendererTypeType newRendererType) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_RendererType(), newRendererType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RenderKitType getRenderKit() {
		return (RenderKitType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_RenderKit(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRenderKit(RenderKitType newRenderKit, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_RenderKit(), newRenderKit, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRenderKit(RenderKitType newRenderKit) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_RenderKit(), newRenderKit);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RenderKitClassType getRenderKitClass() {
		return (RenderKitClassType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_RenderKitClass(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRenderKitClass(RenderKitClassType newRenderKitClass, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_RenderKitClass(), newRenderKitClass, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRenderKitClass(RenderKitClassType newRenderKitClass) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_RenderKitClass(), newRenderKitClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RenderKitFactoryType getRenderKitFactory() {
		return (RenderKitFactoryType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_RenderKitFactory(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRenderKitFactory(RenderKitFactoryType newRenderKitFactory, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_RenderKitFactory(), newRenderKitFactory, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRenderKitFactory(RenderKitFactoryType newRenderKitFactory) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_RenderKitFactory(), newRenderKitFactory);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RenderKitIdType getRenderKitId() {
		return (RenderKitIdType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_RenderKitId(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRenderKitId(RenderKitIdType newRenderKitId, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_RenderKitId(), newRenderKitId, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRenderKitId(RenderKitIdType newRenderKitId) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_RenderKitId(), newRenderKitId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SmallIconType getSmallIcon() {
		return (SmallIconType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_SmallIcon(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSmallIcon(SmallIconType newSmallIcon, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_SmallIcon(), newSmallIcon, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSmallIcon(SmallIconType newSmallIcon) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_SmallIcon(), newSmallIcon);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateManagerType getStateManager() {
		return (StateManagerType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_StateManager(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStateManager(StateManagerType newStateManager, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_StateManager(), newStateManager, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStateManager(StateManagerType newStateManager) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_StateManager(), newStateManager);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SuggestedValueType getSuggestedValue() {
		return (SuggestedValueType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_SuggestedValue(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSuggestedValue(SuggestedValueType newSuggestedValue, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_SuggestedValue(), newSuggestedValue, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuggestedValue(SuggestedValueType newSuggestedValue) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_SuggestedValue(), newSuggestedValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SupportedLocaleType getSupportedLocale() {
		return (SupportedLocaleType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_SupportedLocale(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSupportedLocale(SupportedLocaleType newSupportedLocale, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_SupportedLocale(), newSupportedLocale, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSupportedLocale(SupportedLocaleType newSupportedLocale) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_SupportedLocale(), newSupportedLocale);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ToViewIdType getToViewId() {
		return (ToViewIdType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ToViewId(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetToViewId(ToViewIdType newToViewId, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ToViewId(), newToViewId, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setToViewId(ToViewIdType newToViewId) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ToViewId(), newToViewId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidatorType getValidator() {
		return (ValidatorType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_Validator(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValidator(ValidatorType newValidator, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_Validator(), newValidator, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidator(ValidatorType newValidator) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_Validator(), newValidator);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidatorClassType getValidatorClass() {
		return (ValidatorClassType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ValidatorClass(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValidatorClass(ValidatorClassType newValidatorClass, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ValidatorClass(), newValidatorClass, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidatorClass(ValidatorClassType newValidatorClass) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ValidatorClass(), newValidatorClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidatorIdType getValidatorId() {
		return (ValidatorIdType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ValidatorId(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValidatorId(ValidatorIdType newValidatorId, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ValidatorId(), newValidatorId, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidatorId(ValidatorIdType newValidatorId) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ValidatorId(), newValidatorId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueType getValue() {
		return (ValueType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_Value(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValue(ValueType newValue, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_Value(), newValue, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(ValueType newValue) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_Value(), newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueClassType getValueClass() {
		return (ValueClassType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ValueClass(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValueClass(ValueClassType newValueClass, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ValueClass(), newValueClass, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueClass(ValueClassType newValueClass) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ValueClass(), newValueClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableResolverType getVariableResolver() {
		return (VariableResolverType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_VariableResolver(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVariableResolver(VariableResolverType newVariableResolver, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_VariableResolver(), newVariableResolver, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariableResolver(VariableResolverType newVariableResolver) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_VariableResolver(), newVariableResolver);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewHandlerType getViewHandler() {
		return (ViewHandlerType)getMixed().get(FacesConfigPackage.eINSTANCE.getDocumentRoot_ViewHandler(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetViewHandler(ViewHandlerType newViewHandler, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(FacesConfigPackage.eINSTANCE.getDocumentRoot_ViewHandler(), newViewHandler, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setViewHandler(ViewHandlerType newViewHandler) {
		((FeatureMap.Internal)getMixed()).set(FacesConfigPackage.eINSTANCE.getDocumentRoot_ViewHandler(), newViewHandler);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case FacesConfigPackage.DOCUMENT_ROOT__MIXED:
					return ((InternalEList)getMixed()).basicRemove(otherEnd, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
					return ((InternalEList)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
					return ((InternalEList)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__ACTION_LISTENER:
					return basicSetActionListener(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__APPLICATION:
					return basicSetApplication(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__APPLICATION_FACTORY:
					return basicSetApplicationFactory(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE:
					return basicSetAttribute(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_CLASS:
					return basicSetAttributeClass(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_EXTENSION:
					return basicSetAttributeExtension(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_NAME:
					return basicSetAttributeName(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT:
					return basicSetComponent(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_CLASS:
					return basicSetComponentClass(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_EXTENSION:
					return basicSetComponentExtension(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_FAMILY:
					return basicSetComponentFamily(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_TYPE:
					return basicSetComponentType(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER:
					return basicSetConverter(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_CLASS:
					return basicSetConverterClass(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_FOR_CLASS:
					return basicSetConverterForClass(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_ID:
					return basicSetConverterId(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_LOCALE:
					return basicSetDefaultLocale(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_RENDER_KIT_ID:
					return basicSetDefaultRenderKitId(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_VALUE:
					return basicSetDefaultValue(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__DESCRIPTION:
					return basicSetDescription(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__DISPLAY_NAME:
					return basicSetDisplayName(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__FACES_CONFIG:
					return basicSetFacesConfig(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__FACES_CONTEXT_FACTORY:
					return basicSetFacesContextFactory(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__FACET:
					return basicSetFacet(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__FACET_EXTENSION:
					return basicSetFacetExtension(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__FACET_NAME:
					return basicSetFacetName(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__FACTORY:
					return basicSetFactory(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__FROM_ACTION:
					return basicSetFromAction(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__FROM_OUTCOME:
					return basicSetFromOutcome(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__FROM_VIEW_ID:
					return basicSetFromViewId(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__ICON:
					return basicSetIcon(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__KEY:
					return basicSetKey(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__KEY_CLASS:
					return basicSetKeyClass(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__LARGE_ICON:
					return basicSetLargeIcon(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__LIFECYCLE:
					return basicSetLifecycle(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__LIFECYCLE_FACTORY:
					return basicSetLifecycleFactory(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__LIST_ENTRIES:
					return basicSetListEntries(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__LOCALE_CONFIG:
					return basicSetLocaleConfig(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN:
					return basicSetManagedBean(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_CLASS:
					return basicSetManagedBeanClass(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_NAME:
					return basicSetManagedBeanName(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_SCOPE:
					return basicSetManagedBeanScope(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_PROPERTY:
					return basicSetManagedProperty(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__MAP_ENTRIES:
					return basicSetMapEntries(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__MAP_ENTRY:
					return basicSetMapEntry(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__MESSAGE_BUNDLE:
					return basicSetMessageBundle(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_CASE:
					return basicSetNavigationCase(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_HANDLER:
					return basicSetNavigationHandler(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_RULE:
					return basicSetNavigationRule(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__NULL_VALUE:
					return basicSetNullValue(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__PHASE_LISTENER:
					return basicSetPhaseListener(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY:
					return basicSetProperty(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_CLASS:
					return basicSetPropertyClass(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_EXTENSION:
					return basicSetPropertyExtension(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_NAME:
					return basicSetPropertyName(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_RESOLVER:
					return basicSetPropertyResolver(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__REDIRECT:
					return basicSetRedirect(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN:
					return basicSetReferencedBean(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN_CLASS:
					return basicSetReferencedBeanClass(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN_NAME:
					return basicSetReferencedBeanName(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__RENDERER:
					return basicSetRenderer(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_CLASS:
					return basicSetRendererClass(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_EXTENSION:
					return basicSetRendererExtension(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_TYPE:
					return basicSetRendererType(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT:
					return basicSetRenderKit(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_CLASS:
					return basicSetRenderKitClass(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_FACTORY:
					return basicSetRenderKitFactory(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_ID:
					return basicSetRenderKitId(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__SMALL_ICON:
					return basicSetSmallIcon(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__STATE_MANAGER:
					return basicSetStateManager(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__SUGGESTED_VALUE:
					return basicSetSuggestedValue(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__SUPPORTED_LOCALE:
					return basicSetSupportedLocale(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__TO_VIEW_ID:
					return basicSetToViewId(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR:
					return basicSetValidator(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR_CLASS:
					return basicSetValidatorClass(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR_ID:
					return basicSetValidatorId(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__VALUE:
					return basicSetValue(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__VALUE_CLASS:
					return basicSetValueClass(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__VARIABLE_RESOLVER:
					return basicSetVariableResolver(null, msgs);
				case FacesConfigPackage.DOCUMENT_ROOT__VIEW_HANDLER:
					return basicSetViewHandler(null, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case FacesConfigPackage.DOCUMENT_ROOT__MIXED:
				return getMixed();
			case FacesConfigPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return getXMLNSPrefixMap();
			case FacesConfigPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return getXSISchemaLocation();
			case FacesConfigPackage.DOCUMENT_ROOT__ACTION_LISTENER:
				return getActionListener();
			case FacesConfigPackage.DOCUMENT_ROOT__APPLICATION:
				return getApplication();
			case FacesConfigPackage.DOCUMENT_ROOT__APPLICATION_FACTORY:
				return getApplicationFactory();
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE:
				return getAttribute();
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_CLASS:
				return getAttributeClass();
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_EXTENSION:
				return getAttributeExtension();
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_NAME:
				return getAttributeName();
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT:
				return getComponent();
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_CLASS:
				return getComponentClass();
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_EXTENSION:
				return getComponentExtension();
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_FAMILY:
				return getComponentFamily();
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_TYPE:
				return getComponentType();
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER:
				return getConverter();
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_CLASS:
				return getConverterClass();
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_FOR_CLASS:
				return getConverterForClass();
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_ID:
				return getConverterId();
			case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_LOCALE:
				return getDefaultLocale();
			case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_RENDER_KIT_ID:
				return getDefaultRenderKitId();
			case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_VALUE:
				return getDefaultValue();
			case FacesConfigPackage.DOCUMENT_ROOT__DESCRIPTION:
				return getDescription();
			case FacesConfigPackage.DOCUMENT_ROOT__DISPLAY_NAME:
				return getDisplayName();
			case FacesConfigPackage.DOCUMENT_ROOT__FACES_CONFIG:
				return getFacesConfig();
			case FacesConfigPackage.DOCUMENT_ROOT__FACES_CONTEXT_FACTORY:
				return getFacesContextFactory();
			case FacesConfigPackage.DOCUMENT_ROOT__FACET:
				return getFacet();
			case FacesConfigPackage.DOCUMENT_ROOT__FACET_EXTENSION:
				return getFacetExtension();
			case FacesConfigPackage.DOCUMENT_ROOT__FACET_NAME:
				return getFacetName();
			case FacesConfigPackage.DOCUMENT_ROOT__FACTORY:
				return getFactory();
			case FacesConfigPackage.DOCUMENT_ROOT__FROM_ACTION:
				return getFromAction();
			case FacesConfigPackage.DOCUMENT_ROOT__FROM_OUTCOME:
				return getFromOutcome();
			case FacesConfigPackage.DOCUMENT_ROOT__FROM_VIEW_ID:
				return getFromViewId();
			case FacesConfigPackage.DOCUMENT_ROOT__ICON:
				return getIcon();
			case FacesConfigPackage.DOCUMENT_ROOT__KEY:
				return getKey();
			case FacesConfigPackage.DOCUMENT_ROOT__KEY_CLASS:
				return getKeyClass();
			case FacesConfigPackage.DOCUMENT_ROOT__LARGE_ICON:
				return getLargeIcon();
			case FacesConfigPackage.DOCUMENT_ROOT__LIFECYCLE:
				return getLifecycle();
			case FacesConfigPackage.DOCUMENT_ROOT__LIFECYCLE_FACTORY:
				return getLifecycleFactory();
			case FacesConfigPackage.DOCUMENT_ROOT__LIST_ENTRIES:
				return getListEntries();
			case FacesConfigPackage.DOCUMENT_ROOT__LOCALE_CONFIG:
				return getLocaleConfig();
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN:
				return getManagedBean();
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_CLASS:
				return getManagedBeanClass();
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_NAME:
				return getManagedBeanName();
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_SCOPE:
				return getManagedBeanScope();
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_PROPERTY:
				return getManagedProperty();
			case FacesConfigPackage.DOCUMENT_ROOT__MAP_ENTRIES:
				return getMapEntries();
			case FacesConfigPackage.DOCUMENT_ROOT__MAP_ENTRY:
				return getMapEntry();
			case FacesConfigPackage.DOCUMENT_ROOT__MESSAGE_BUNDLE:
				return getMessageBundle();
			case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_CASE:
				return getNavigationCase();
			case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_HANDLER:
				return getNavigationHandler();
			case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_RULE:
				return getNavigationRule();
			case FacesConfigPackage.DOCUMENT_ROOT__NULL_VALUE:
				return getNullValue();
			case FacesConfigPackage.DOCUMENT_ROOT__PHASE_LISTENER:
				return getPhaseListener();
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY:
				return getProperty();
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_CLASS:
				return getPropertyClass();
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_EXTENSION:
				return getPropertyExtension();
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_NAME:
				return getPropertyName();
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_RESOLVER:
				return getPropertyResolver();
			case FacesConfigPackage.DOCUMENT_ROOT__REDIRECT:
				return getRedirect();
			case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN:
				return getReferencedBean();
			case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN_CLASS:
				return getReferencedBeanClass();
			case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN_NAME:
				return getReferencedBeanName();
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER:
				return getRenderer();
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_CLASS:
				return getRendererClass();
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_EXTENSION:
				return getRendererExtension();
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_TYPE:
				return getRendererType();
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT:
				return getRenderKit();
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_CLASS:
				return getRenderKitClass();
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_FACTORY:
				return getRenderKitFactory();
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_ID:
				return getRenderKitId();
			case FacesConfigPackage.DOCUMENT_ROOT__SMALL_ICON:
				return getSmallIcon();
			case FacesConfigPackage.DOCUMENT_ROOT__STATE_MANAGER:
				return getStateManager();
			case FacesConfigPackage.DOCUMENT_ROOT__SUGGESTED_VALUE:
				return getSuggestedValue();
			case FacesConfigPackage.DOCUMENT_ROOT__SUPPORTED_LOCALE:
				return getSupportedLocale();
			case FacesConfigPackage.DOCUMENT_ROOT__TO_VIEW_ID:
				return getToViewId();
			case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR:
				return getValidator();
			case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR_CLASS:
				return getValidatorClass();
			case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR_ID:
				return getValidatorId();
			case FacesConfigPackage.DOCUMENT_ROOT__VALUE:
				return getValue();
			case FacesConfigPackage.DOCUMENT_ROOT__VALUE_CLASS:
				return getValueClass();
			case FacesConfigPackage.DOCUMENT_ROOT__VARIABLE_RESOLVER:
				return getVariableResolver();
			case FacesConfigPackage.DOCUMENT_ROOT__VIEW_HANDLER:
				return getViewHandler();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case FacesConfigPackage.DOCUMENT_ROOT__MIXED:
				getMixed().clear();
				getMixed().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				getXMLNSPrefixMap().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				getXSISchemaLocation().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__ACTION_LISTENER:
				setActionListener((ActionListenerType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__APPLICATION:
				setApplication((ApplicationType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__APPLICATION_FACTORY:
				setApplicationFactory((ApplicationFactoryType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE:
				setAttribute((AttributeType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_CLASS:
				setAttributeClass((AttributeClassType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_EXTENSION:
				setAttributeExtension((AttributeExtensionType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_NAME:
				setAttributeName((AttributeNameType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT:
				setComponent((ComponentType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_CLASS:
				setComponentClass((ComponentClassType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_EXTENSION:
				setComponentExtension((ComponentExtensionType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_FAMILY:
				setComponentFamily((ComponentFamilyType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_TYPE:
				setComponentType((ComponentTypeType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER:
				setConverter((ConverterType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_CLASS:
				setConverterClass((ConverterClassType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_FOR_CLASS:
				setConverterForClass((ConverterForClassType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_ID:
				setConverterId((ConverterIdType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_LOCALE:
				setDefaultLocale((DefaultLocaleType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_RENDER_KIT_ID:
				setDefaultRenderKitId((DefaultRenderKitIdType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_VALUE:
				setDefaultValue((DefaultValueType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__DESCRIPTION:
				setDescription((DescriptionType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__DISPLAY_NAME:
				setDisplayName((DisplayNameType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FACES_CONFIG:
				setFacesConfig((FacesConfigType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FACES_CONTEXT_FACTORY:
				setFacesContextFactory((FacesContextFactoryType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FACET:
				setFacet((FacetType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FACET_EXTENSION:
				setFacetExtension((FacetExtensionType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FACET_NAME:
				setFacetName((FacetNameType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FACTORY:
				setFactory((FactoryType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FROM_ACTION:
				setFromAction((FromActionType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FROM_OUTCOME:
				setFromOutcome((FromOutcomeType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FROM_VIEW_ID:
				setFromViewId((FromViewIdType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__ICON:
				setIcon((IconType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__KEY:
				setKey((KeyType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__KEY_CLASS:
				setKeyClass((KeyClassType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__LARGE_ICON:
				setLargeIcon((LargeIconType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__LIFECYCLE:
				setLifecycle((LifecycleType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__LIFECYCLE_FACTORY:
				setLifecycleFactory((LifecycleFactoryType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__LIST_ENTRIES:
				setListEntries((ListEntriesType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__LOCALE_CONFIG:
				setLocaleConfig((LocaleConfigType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN:
				setManagedBean((ManagedBeanType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_CLASS:
				setManagedBeanClass((ManagedBeanClassType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_NAME:
				setManagedBeanName((ManagedBeanNameType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_SCOPE:
				setManagedBeanScope((ManagedBeanScopeType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_PROPERTY:
				setManagedProperty((ManagedPropertyType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__MAP_ENTRIES:
				setMapEntries((MapEntriesType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__MAP_ENTRY:
				setMapEntry((MapEntryType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__MESSAGE_BUNDLE:
				setMessageBundle((MessageBundleType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_CASE:
				setNavigationCase((NavigationCaseType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_HANDLER:
				setNavigationHandler((NavigationHandlerType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_RULE:
				setNavigationRule((NavigationRuleType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__NULL_VALUE:
				setNullValue((NullValueType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__PHASE_LISTENER:
				setPhaseListener((PhaseListenerType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY:
				setProperty((PropertyType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_CLASS:
				setPropertyClass((PropertyClassType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_EXTENSION:
				setPropertyExtension((PropertyExtensionType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_NAME:
				setPropertyName((PropertyNameType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_RESOLVER:
				setPropertyResolver((PropertyResolverType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__REDIRECT:
				setRedirect((RedirectType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN:
				setReferencedBean((ReferencedBeanType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN_CLASS:
				setReferencedBeanClass((ReferencedBeanClassType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN_NAME:
				setReferencedBeanName((ReferencedBeanNameType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER:
				setRenderer((RendererType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_CLASS:
				setRendererClass((RendererClassType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_EXTENSION:
				setRendererExtension((RendererExtensionType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_TYPE:
				setRendererType((RendererTypeType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT:
				setRenderKit((RenderKitType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_CLASS:
				setRenderKitClass((RenderKitClassType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_FACTORY:
				setRenderKitFactory((RenderKitFactoryType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_ID:
				setRenderKitId((RenderKitIdType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__SMALL_ICON:
				setSmallIcon((SmallIconType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__STATE_MANAGER:
				setStateManager((StateManagerType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__SUGGESTED_VALUE:
				setSuggestedValue((SuggestedValueType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__SUPPORTED_LOCALE:
				setSupportedLocale((SupportedLocaleType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__TO_VIEW_ID:
				setToViewId((ToViewIdType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR:
				setValidator((ValidatorType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR_CLASS:
				setValidatorClass((ValidatorClassType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR_ID:
				setValidatorId((ValidatorIdType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__VALUE:
				setValue((ValueType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__VALUE_CLASS:
				setValueClass((ValueClassType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__VARIABLE_RESOLVER:
				setVariableResolver((VariableResolverType)newValue);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__VIEW_HANDLER:
				setViewHandler((ViewHandlerType)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case FacesConfigPackage.DOCUMENT_ROOT__MIXED:
				getMixed().clear();
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__ACTION_LISTENER:
				setActionListener((ActionListenerType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__APPLICATION:
				setApplication((ApplicationType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__APPLICATION_FACTORY:
				setApplicationFactory((ApplicationFactoryType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE:
				setAttribute((AttributeType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_CLASS:
				setAttributeClass((AttributeClassType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_EXTENSION:
				setAttributeExtension((AttributeExtensionType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_NAME:
				setAttributeName((AttributeNameType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT:
				setComponent((ComponentType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_CLASS:
				setComponentClass((ComponentClassType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_EXTENSION:
				setComponentExtension((ComponentExtensionType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_FAMILY:
				setComponentFamily((ComponentFamilyType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_TYPE:
				setComponentType((ComponentTypeType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER:
				setConverter((ConverterType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_CLASS:
				setConverterClass((ConverterClassType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_FOR_CLASS:
				setConverterForClass((ConverterForClassType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_ID:
				setConverterId((ConverterIdType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_LOCALE:
				setDefaultLocale((DefaultLocaleType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_RENDER_KIT_ID:
				setDefaultRenderKitId((DefaultRenderKitIdType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_VALUE:
				setDefaultValue((DefaultValueType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__DESCRIPTION:
				setDescription((DescriptionType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__DISPLAY_NAME:
				setDisplayName((DisplayNameType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FACES_CONFIG:
				setFacesConfig((FacesConfigType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FACES_CONTEXT_FACTORY:
				setFacesContextFactory((FacesContextFactoryType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FACET:
				setFacet((FacetType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FACET_EXTENSION:
				setFacetExtension((FacetExtensionType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FACET_NAME:
				setFacetName((FacetNameType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FACTORY:
				setFactory((FactoryType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FROM_ACTION:
				setFromAction((FromActionType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FROM_OUTCOME:
				setFromOutcome((FromOutcomeType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__FROM_VIEW_ID:
				setFromViewId((FromViewIdType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__ICON:
				setIcon((IconType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__KEY:
				setKey((KeyType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__KEY_CLASS:
				setKeyClass((KeyClassType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__LARGE_ICON:
				setLargeIcon((LargeIconType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__LIFECYCLE:
				setLifecycle((LifecycleType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__LIFECYCLE_FACTORY:
				setLifecycleFactory((LifecycleFactoryType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__LIST_ENTRIES:
				setListEntries((ListEntriesType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__LOCALE_CONFIG:
				setLocaleConfig((LocaleConfigType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN:
				setManagedBean((ManagedBeanType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_CLASS:
				setManagedBeanClass((ManagedBeanClassType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_NAME:
				setManagedBeanName((ManagedBeanNameType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_SCOPE:
				setManagedBeanScope((ManagedBeanScopeType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_PROPERTY:
				setManagedProperty((ManagedPropertyType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__MAP_ENTRIES:
				setMapEntries((MapEntriesType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__MAP_ENTRY:
				setMapEntry((MapEntryType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__MESSAGE_BUNDLE:
				setMessageBundle((MessageBundleType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_CASE:
				setNavigationCase((NavigationCaseType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_HANDLER:
				setNavigationHandler((NavigationHandlerType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_RULE:
				setNavigationRule((NavigationRuleType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__NULL_VALUE:
				setNullValue((NullValueType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__PHASE_LISTENER:
				setPhaseListener((PhaseListenerType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY:
				setProperty((PropertyType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_CLASS:
				setPropertyClass((PropertyClassType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_EXTENSION:
				setPropertyExtension((PropertyExtensionType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_NAME:
				setPropertyName((PropertyNameType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_RESOLVER:
				setPropertyResolver((PropertyResolverType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__REDIRECT:
				setRedirect((RedirectType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN:
				setReferencedBean((ReferencedBeanType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN_CLASS:
				setReferencedBeanClass((ReferencedBeanClassType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN_NAME:
				setReferencedBeanName((ReferencedBeanNameType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER:
				setRenderer((RendererType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_CLASS:
				setRendererClass((RendererClassType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_EXTENSION:
				setRendererExtension((RendererExtensionType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_TYPE:
				setRendererType((RendererTypeType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT:
				setRenderKit((RenderKitType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_CLASS:
				setRenderKitClass((RenderKitClassType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_FACTORY:
				setRenderKitFactory((RenderKitFactoryType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_ID:
				setRenderKitId((RenderKitIdType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__SMALL_ICON:
				setSmallIcon((SmallIconType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__STATE_MANAGER:
				setStateManager((StateManagerType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__SUGGESTED_VALUE:
				setSuggestedValue((SuggestedValueType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__SUPPORTED_LOCALE:
				setSupportedLocale((SupportedLocaleType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__TO_VIEW_ID:
				setToViewId((ToViewIdType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR:
				setValidator((ValidatorType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR_CLASS:
				setValidatorClass((ValidatorClassType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR_ID:
				setValidatorId((ValidatorIdType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__VALUE:
				setValue((ValueType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__VALUE_CLASS:
				setValueClass((ValueClassType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__VARIABLE_RESOLVER:
				setVariableResolver((VariableResolverType)null);
				return;
			case FacesConfigPackage.DOCUMENT_ROOT__VIEW_HANDLER:
				setViewHandler((ViewHandlerType)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case FacesConfigPackage.DOCUMENT_ROOT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case FacesConfigPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case FacesConfigPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case FacesConfigPackage.DOCUMENT_ROOT__ACTION_LISTENER:
				return getActionListener() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__APPLICATION:
				return getApplication() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__APPLICATION_FACTORY:
				return getApplicationFactory() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE:
				return getAttribute() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_CLASS:
				return getAttributeClass() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_EXTENSION:
				return getAttributeExtension() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__ATTRIBUTE_NAME:
				return getAttributeName() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT:
				return getComponent() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_CLASS:
				return getComponentClass() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_EXTENSION:
				return getComponentExtension() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_FAMILY:
				return getComponentFamily() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__COMPONENT_TYPE:
				return getComponentType() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER:
				return getConverter() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_CLASS:
				return getConverterClass() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_FOR_CLASS:
				return getConverterForClass() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__CONVERTER_ID:
				return getConverterId() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_LOCALE:
				return getDefaultLocale() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_RENDER_KIT_ID:
				return getDefaultRenderKitId() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__DEFAULT_VALUE:
				return getDefaultValue() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__DESCRIPTION:
				return getDescription() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__DISPLAY_NAME:
				return getDisplayName() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__FACES_CONFIG:
				return getFacesConfig() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__FACES_CONTEXT_FACTORY:
				return getFacesContextFactory() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__FACET:
				return getFacet() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__FACET_EXTENSION:
				return getFacetExtension() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__FACET_NAME:
				return getFacetName() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__FACTORY:
				return getFactory() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__FROM_ACTION:
				return getFromAction() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__FROM_OUTCOME:
				return getFromOutcome() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__FROM_VIEW_ID:
				return getFromViewId() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__ICON:
				return getIcon() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__KEY:
				return getKey() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__KEY_CLASS:
				return getKeyClass() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__LARGE_ICON:
				return getLargeIcon() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__LIFECYCLE:
				return getLifecycle() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__LIFECYCLE_FACTORY:
				return getLifecycleFactory() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__LIST_ENTRIES:
				return getListEntries() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__LOCALE_CONFIG:
				return getLocaleConfig() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN:
				return getManagedBean() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_CLASS:
				return getManagedBeanClass() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_NAME:
				return getManagedBeanName() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_BEAN_SCOPE:
				return getManagedBeanScope() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__MANAGED_PROPERTY:
				return getManagedProperty() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__MAP_ENTRIES:
				return getMapEntries() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__MAP_ENTRY:
				return getMapEntry() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__MESSAGE_BUNDLE:
				return getMessageBundle() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_CASE:
				return getNavigationCase() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_HANDLER:
				return getNavigationHandler() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__NAVIGATION_RULE:
				return getNavigationRule() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__NULL_VALUE:
				return getNullValue() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__PHASE_LISTENER:
				return getPhaseListener() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY:
				return getProperty() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_CLASS:
				return getPropertyClass() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_EXTENSION:
				return getPropertyExtension() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_NAME:
				return getPropertyName() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__PROPERTY_RESOLVER:
				return getPropertyResolver() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__REDIRECT:
				return getRedirect() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN:
				return getReferencedBean() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN_CLASS:
				return getReferencedBeanClass() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__REFERENCED_BEAN_NAME:
				return getReferencedBeanName() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER:
				return getRenderer() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_CLASS:
				return getRendererClass() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_EXTENSION:
				return getRendererExtension() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDERER_TYPE:
				return getRendererType() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT:
				return getRenderKit() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_CLASS:
				return getRenderKitClass() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_FACTORY:
				return getRenderKitFactory() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__RENDER_KIT_ID:
				return getRenderKitId() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__SMALL_ICON:
				return getSmallIcon() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__STATE_MANAGER:
				return getStateManager() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__SUGGESTED_VALUE:
				return getSuggestedValue() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__SUPPORTED_LOCALE:
				return getSupportedLocale() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__TO_VIEW_ID:
				return getToViewId() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR:
				return getValidator() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR_CLASS:
				return getValidatorClass() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__VALIDATOR_ID:
				return getValidatorId() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__VALUE:
				return getValue() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__VALUE_CLASS:
				return getValueClass() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__VARIABLE_RESOLVER:
				return getVariableResolver() != null;
			case FacesConfigPackage.DOCUMENT_ROOT__VIEW_HANDLER:
				return getViewHandler() != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
