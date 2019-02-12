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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 *    See http://www.w3.org/XML/1998/namespace.html and
 *    http://www.w3.org/TR/REC-xml for information about this namespace.
 * 
 *     This schema document describes the XML namespace, in a form
 *     suitable for import by other schema documents.  
 * 
 *     Note that local names in this namespace are intended to be defined
 *     only by the World Wide Web Consortium or its subgroups.  The
 *     following names are currently defined in this namespace and should
 *     not be used with conflicting semantics by any Working Group,
 *     specification, or document instance:
 * 
 *     base (as an attribute name): denotes an attribute whose value
 *          provides a URI to be used as the base for interpreting any
 *          relative URIs in the scope of the element on which it
 *          appears; its value is inherited.  This name is reserved
 *          by virtue of its definition in the XML Base specification.
 * 
 *     id   (as an attribute name): denotes an attribute whose value
 *          should be interpreted as if declared to be of type ID.
 *          The xml:id specification is not yet a W3C Recommendation,
 *          but this attribute is included here to facilitate experimentation
 *          with the mechanisms it proposes.  Note that it is _not_ included
 *          in the specialAttrs attribute group.
 * 
 *     lang (as an attribute name): denotes an attribute whose value
 *          is a language code for the natural language of the content of
 *          any element; its value is inherited.  This name is reserved
 *          by virtue of its definition in the XML specification.
 *   
 *     space (as an attribute name): denotes an attribute whose
 *          value is a keyword indicating what whitespace processing
 *          discipline is intended for the content of the element; its
 *          value is inherited.  This name is reserved by virtue of its
 *          definition in the XML specification.
 * 
 *     Father (in any context at all): denotes Jon Bosak, the chair of 
 *          the original XML Working Group.  This name is reserved by 
 *          the following decision of the W3C XML Plenary and 
 *          XML Coordination groups:
 * 
 *              In appreciation for his vision, leadership and dedication
 *              the W3C XML Plenary on this 10th day of February, 2000
 *              reserves for Jon Bosak in perpetuity the XML name
 *              xml:Father
 *   
 * This schema defines attributes and an attribute group
 *         suitable for use by
 *         schemas wishing to allow xml:base, xml:lang, xml:space or xml:id
 *         attributes on elements they define.
 * 
 *         To enable this, such a schema must import this schema
 *         for the XML namespace, e.g. as follows:
 *         &lt;schema . . .&gt;
 *          . . .
 *          &lt;import namespace="http://www.w3.org/XML/1998/namespace"
 *                     schemaLocation="http://www.w3.org/2001/xml.xsd"/&gt;
 * 
 *         Subsequently, qualified reference to any of the attributes
 *         or the group defined below will have the desired effect, e.g.
 * 
 *         &lt;type . . .&gt;
 *          . . .
 *          &lt;attributeGroup ref="xml:specialAttrs"/&gt;
 *  
 *          will define a type which will schema-validate an instance
 *          element with any of those attributes
 * In keeping with the XML Schema WG's standard versioning
 *    policy, this schema document will persist at
 *    http://www.w3.org/2005/08/xml.xsd.
 *    At the date of issue it can also be found at
 *    http://www.w3.org/2001/xml.xsd.
 *    The schema document at that URI may however change in the future,
 *    in order to remain compatible with the latest version of XML Schema
 *    itself, or with the XML namespace itself.  In other words, if the XML
 *    Schema or XML namespaces change, the version of this document at
 *    http://www.w3.org/2001/xml.xsd will change
 *    accordingly; the version at
 *    http://www.w3.org/2005/08/xml.xsd will not change.
 *   
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory
 * @model kind="package"
 *        extendedMetaData="qualified='false'"
 * @generated
 */
@SuppressWarnings("hiding")
public interface FacesConfigPackage extends EPackage {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

    /**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "facesconfig"; //$NON-NLS-1$

    /**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/webtools/jsf/schema/facesconfig.xsd"; //$NON-NLS-1$

    /**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "facesconfig"; //$NON-NLS-1$

    /**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FacesConfigPackage eINSTANCE = org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl.init();

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AbsoluteOrderingTypeImpl <em>Absolute Ordering Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.AbsoluteOrderingTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getAbsoluteOrderingType()
	 * @generated
	 */
	int ABSOLUTE_ORDERING_TYPE = 0;

				/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_ORDERING_TYPE__NAME = 0;

				/**
	 * The feature id for the '<em><b>Others</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_ORDERING_TYPE__OTHERS = 1;

				/**
	 * The number of structural features of the '<em>Absolute Ordering Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_ORDERING_TYPE_FEATURE_COUNT = 2;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ActionListenerTypeImpl <em>Action Listener Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ActionListenerTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getActionListenerType()
	 * @generated
	 */
	int ACTION_LISTENER_TYPE = 1;

    /**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_LISTENER_TYPE__TEXT_CONTENT = 0;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_LISTENER_TYPE__ID = 1;

    /**
	 * The number of structural features of the '<em>Action Listener Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_LISTENER_TYPE_FEATURE_COUNT = 2;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationFactoryTypeImpl <em>Application Factory Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationFactoryTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getApplicationFactoryType()
	 * @generated
	 */
	int APPLICATION_FACTORY_TYPE = 2;

    /**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_FACTORY_TYPE__TEXT_CONTENT = 0;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_FACTORY_TYPE__ID = 1;

    /**
	 * The number of structural features of the '<em>Application Factory Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_FACTORY_TYPE_FEATURE_COUNT = 2;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationTypeImpl <em>Application Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getApplicationType()
	 * @generated
	 */
	int APPLICATION_TYPE = 3;

    /**
	 * The feature id for the '<em><b>Action Listener</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__ACTION_LISTENER = 0;

    /**
	 * The feature id for the '<em><b>Default Render Kit Id</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__DEFAULT_RENDER_KIT_ID = 1;

    /**
	 * The feature id for the '<em><b>Message Bundle</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__MESSAGE_BUNDLE = 2;

    /**
	 * The feature id for the '<em><b>Navigation Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__NAVIGATION_HANDLER = 3;

    /**
	 * The feature id for the '<em><b>View Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__VIEW_HANDLER = 4;

    /**
	 * The feature id for the '<em><b>State Manager</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__STATE_MANAGER = 5;

    /**
	 * The feature id for the '<em><b>EL Resolver</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int APPLICATION_TYPE__EL_RESOLVER = 6;

				/**
	 * The feature id for the '<em><b>Property Resolver</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__PROPERTY_RESOLVER = 7;

				/**
	 * The feature id for the '<em><b>Variable Resolver</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__VARIABLE_RESOLVER = 8;

				/**
	 * The feature id for the '<em><b>Resource Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__RESOURCE_HANDLER = 9;

				/**
	 * The feature id for the '<em><b>System Event Listener</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__SYSTEM_EVENT_LISTENER = 10;

				/**
	 * The feature id for the '<em><b>Locale Config</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__LOCALE_CONFIG = 11;

    /**
	 * The feature id for the '<em><b>Resource Bundle</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int APPLICATION_TYPE__RESOURCE_BUNDLE = 12;

    /**
	 * The feature id for the '<em><b>Application Extension</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int APPLICATION_TYPE__APPLICATION_EXTENSION = 13;

    /**
	 * The feature id for the '<em><b>Default Validators</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__DEFAULT_VALIDATORS = 14;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE__ID = 15;

				/**
	 * The number of structural features of the '<em>Application Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_TYPE_FEATURE_COUNT = 16;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeClassTypeImpl <em>Attribute Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeClassTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getAttributeClassType()
	 * @generated
	 */
	int ATTRIBUTE_CLASS_TYPE = 5;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeExtensionTypeImpl <em>Attribute Extension Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeExtensionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getAttributeExtensionType()
	 * @generated
	 */
	int ATTRIBUTE_EXTENSION_TYPE = 6;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeNameTypeImpl <em>Attribute Name Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeNameTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getAttributeNameType()
	 * @generated
	 */
	int ATTRIBUTE_NAME_TYPE = 7;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeTypeImpl <em>Attribute Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getAttributeType()
	 * @generated
	 */
	int ATTRIBUTE_TYPE = 8;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentClassTypeImpl <em>Component Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentClassTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getComponentClassType()
	 * @generated
	 */
	int COMPONENT_CLASS_TYPE = 16;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ExtensionTypeImpl <em>Extension Type</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ExtensionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getExtensionType()
	 * @generated
	 */
    int EXTENSION_TYPE = 37;

    /**
	 * The feature id for the '<em><b>Child Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EXTENSION_TYPE__CHILD_NODES = 0;

    /**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EXTENSION_TYPE__TEXT_CONTENT = 1;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EXTENSION_TYPE__ID = 2;

    /**
	 * The number of structural features of the '<em>Extension Type</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EXTENSION_TYPE_FEATURE_COUNT = 3;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorClassTypeImpl <em>Behavior Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorClassTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getBehaviorClassType()
	 * @generated
	 */
	int BEHAVIOR_CLASS_TYPE = 9;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorIdTypeImpl <em>Behavior Id Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorIdTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getBehaviorIdType()
	 * @generated
	 */
	int BEHAVIOR_ID_TYPE = 10;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorTypeImpl <em>Behavior Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getBehaviorType()
	 * @generated
	 */
	int BEHAVIOR_TYPE = 11;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorExtensionTypeImpl <em>Behavior Extension Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorExtensionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getBehaviorExtensionType()
	 * @generated
	 */
	int BEHAVIOR_EXTENSION_TYPE = 12;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ClientBehaviorRendererClassTypeImpl <em>Client Behavior Renderer Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ClientBehaviorRendererClassTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getClientBehaviorRendererClassType()
	 * @generated
	 */
	int CLIENT_BEHAVIOR_RENDERER_CLASS_TYPE = 13;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ClientBehaviorRendererTypeImpl <em>Client Behavior Renderer Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ClientBehaviorRendererTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getClientBehaviorRendererType()
	 * @generated
	 */
	int CLIENT_BEHAVIOR_RENDERER_TYPE = 14;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ClientBehaviorRendererTypeTypeImpl <em>Client Behavior Renderer Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ClientBehaviorRendererTypeTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getClientBehaviorRendererTypeType()
	 * @generated
	 */
	int CLIENT_BEHAVIOR_RENDERER_TYPE_TYPE = 15;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentExtensionTypeImpl <em>Component Extension Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentExtensionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getComponentExtensionType()
	 * @generated
	 */
	int COMPONENT_EXTENSION_TYPE = 17;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentFamilyTypeImpl <em>Component Family Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentFamilyTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getComponentFamilyType()
	 * @generated
	 */
	int COMPONENT_FAMILY_TYPE = 18;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeImpl <em>Component Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getComponentType()
	 * @generated
	 */
	int COMPONENT_TYPE = 19;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeTypeImpl <em>Component Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getComponentTypeType()
	 * @generated
	 */
	int COMPONENT_TYPE_TYPE = 20;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterClassTypeImpl <em>Converter Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterClassTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getConverterClassType()
	 * @generated
	 */
	int CONVERTER_CLASS_TYPE = 21;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterForClassTypeImpl <em>Converter For Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterForClassTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getConverterForClassType()
	 * @generated
	 */
	int CONVERTER_FOR_CLASS_TYPE = 22;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterIdTypeImpl <em>Converter Id Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterIdTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getConverterIdType()
	 * @generated
	 */
	int CONVERTER_ID_TYPE = 23;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterTypeImpl <em>Converter Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getConverterType()
	 * @generated
	 */
	int CONVERTER_TYPE = 24;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DefaultLocaleTypeImpl <em>Default Locale Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DefaultLocaleTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDefaultLocaleType()
	 * @generated
	 */
	int DEFAULT_LOCALE_TYPE = 26;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DefaultRenderKitIdTypeImpl <em>Default Render Kit Id Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DefaultRenderKitIdTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDefaultRenderKitIdType()
	 * @generated
	 */
	int DEFAULT_RENDER_KIT_ID_TYPE = 27;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DefaultValidatorsTypeImpl <em>Default Validators Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DefaultValidatorsTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDefaultValidatorsType()
	 * @generated
	 */
	int DEFAULT_VALIDATORS_TYPE = 28;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DefaultValueTypeImpl <em>Default Value Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DefaultValueTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDefaultValueType()
	 * @generated
	 */
	int DEFAULT_VALUE_TYPE = 29;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DescriptionTypeImpl <em>Description Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DescriptionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDescriptionType()
	 * @generated
	 */
	int DESCRIPTION_TYPE = 30;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DisplayNameTypeImpl <em>Display Name Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DisplayNameTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDisplayNameType()
	 * @generated
	 */
	int DISPLAY_NAME_TYPE = 31;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 32;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DynamicAttributeImpl <em>Dynamic Attribute</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DynamicAttributeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDynamicAttribute()
	 * @generated
	 */
    int DYNAMIC_ATTRIBUTE = 33;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DynamicElementImpl <em>Dynamic Element</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DynamicElementImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDynamicElement()
	 * @generated
	 */
    int DYNAMIC_ELEMENT = 34;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ELResolverTypeImpl <em>EL Resolver Type</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ELResolverTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getELResolverType()
	 * @generated
	 */
    int EL_RESOLVER_TYPE = 35;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl <em>Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFacesConfigType()
	 * @generated
	 */
	int FACES_CONFIG_TYPE = 39;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesContextFactoryTypeImpl <em>Faces Context Factory Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesContextFactoryTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFacesContextFactoryType()
	 * @generated
	 */
	int FACES_CONTEXT_FACTORY_TYPE = 41;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacetExtensionTypeImpl <em>Facet Extension Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacetExtensionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFacetExtensionType()
	 * @generated
	 */
	int FACET_EXTENSION_TYPE = 42;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacetNameTypeImpl <em>Facet Name Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacetNameTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFacetNameType()
	 * @generated
	 */
	int FACET_NAME_TYPE = 43;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacetTypeImpl <em>Facet Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacetTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFacetType()
	 * @generated
	 */
	int FACET_TYPE = 44;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl <em>Factory Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFactoryType()
	 * @generated
	 */
	int FACTORY_TYPE = 45;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FromActionTypeImpl <em>From Action Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FromActionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFromActionType()
	 * @generated
	 */
	int FROM_ACTION_TYPE = 47;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FromOutcomeTypeImpl <em>From Outcome Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FromOutcomeTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFromOutcomeType()
	 * @generated
	 */
	int FROM_OUTCOME_TYPE = 48;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FromViewIdTypeImpl <em>From View Id Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FromViewIdTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFromViewIdType()
	 * @generated
	 */
	int FROM_VIEW_ID_TYPE = 49;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.IconTypeImpl <em>Icon Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.IconTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getIconType()
	 * @generated
	 */
	int ICON_TYPE = 50;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.IfTypeImpl <em>If Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.IfTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getIfType()
	 * @generated
	 */
	int IF_TYPE = 51;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.KeyClassTypeImpl <em>Key Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.KeyClassTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getKeyClassType()
	 * @generated
	 */
	int KEY_CLASS_TYPE = 52;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.KeyTypeImpl <em>Key Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.KeyTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getKeyType()
	 * @generated
	 */
	int KEY_TYPE = 53;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.LargeIconTypeImpl <em>Large Icon Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.LargeIconTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getLargeIconType()
	 * @generated
	 */
	int LARGE_ICON_TYPE = 54;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.LifecycleFactoryTypeImpl <em>Lifecycle Factory Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.LifecycleFactoryTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getLifecycleFactoryType()
	 * @generated
	 */
	int LIFECYCLE_FACTORY_TYPE = 55;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.LifecycleTypeImpl <em>Lifecycle Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.LifecycleTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getLifecycleType()
	 * @generated
	 */
	int LIFECYCLE_TYPE = 56;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ListEntriesTypeImpl <em>List Entries Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ListEntriesTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getListEntriesType()
	 * @generated
	 */
	int LIST_ENTRIES_TYPE = 58;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.LocaleConfigTypeImpl <em>Locale Config Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.LocaleConfigTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getLocaleConfigType()
	 * @generated
	 */
	int LOCALE_CONFIG_TYPE = 59;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanClassTypeImpl <em>Managed Bean Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanClassTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getManagedBeanClassType()
	 * @generated
	 */
	int MANAGED_BEAN_CLASS_TYPE = 60;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanNameTypeImpl <em>Managed Bean Name Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanNameTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getManagedBeanNameType()
	 * @generated
	 */
	int MANAGED_BEAN_NAME_TYPE = 61;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanScopeTypeImpl <em>Managed Bean Scope Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanScopeTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getManagedBeanScopeType()
	 * @generated
	 */
	int MANAGED_BEAN_SCOPE_TYPE = 62;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanTypeImpl <em>Managed Bean Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getManagedBeanType()
	 * @generated
	 */
	int MANAGED_BEAN_TYPE = 63;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedPropertyTypeImpl <em>Managed Property Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedPropertyTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getManagedPropertyType()
	 * @generated
	 */
	int MANAGED_PROPERTY_TYPE = 65;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.MapEntriesTypeImpl <em>Map Entries Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.MapEntriesTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getMapEntriesType()
	 * @generated
	 */
	int MAP_ENTRIES_TYPE = 66;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.MapEntryTypeImpl <em>Map Entry Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.MapEntryTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getMapEntryType()
	 * @generated
	 */
	int MAP_ENTRY_TYPE = 67;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.MessageBundleTypeImpl <em>Message Bundle Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.MessageBundleTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getMessageBundleType()
	 * @generated
	 */
	int MESSAGE_BUNDLE_TYPE = 68;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NameTypeImpl <em>Name Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.NameTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getNameType()
	 * @generated
	 */
	int NAME_TYPE = 69;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationCaseTypeImpl <em>Navigation Case Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationCaseTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getNavigationCaseType()
	 * @generated
	 */
	int NAVIGATION_CASE_TYPE = 70;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationHandlerTypeImpl <em>Navigation Handler Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationHandlerTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getNavigationHandlerType()
	 * @generated
	 */
	int NAVIGATION_HANDLER_TYPE = 71;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationRuleTypeImpl <em>Navigation Rule Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationRuleTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getNavigationRuleType()
	 * @generated
	 */
	int NAVIGATION_RULE_TYPE = 72;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NullValueTypeImpl <em>Null Value Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.NullValueTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getNullValueType()
	 * @generated
	 */
	int NULL_VALUE_TYPE = 74;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.OrderingTypeImpl <em>Ordering Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.OrderingTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getOrderingType()
	 * @generated
	 */
	int ORDERING_TYPE = 75;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.OrderingOrderingTypeImpl <em>Ordering Ordering Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.OrderingOrderingTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getOrderingOrderingType()
	 * @generated
	 */
	int ORDERING_ORDERING_TYPE = 76;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.OrderingOthersTypeImpl <em>Ordering Others Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.OrderingOthersTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getOrderingOthersType()
	 * @generated
	 */
	int ORDERING_OTHERS_TYPE = 77;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PartialViewContextFactoryTypeImpl <em>Partial View Context Factory Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.PartialViewContextFactoryTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getPartialViewContextFactoryType()
	 * @generated
	 */
	int PARTIAL_VIEW_CONTEXT_FACTORY_TYPE = 78;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PhaseListenerTypeImpl <em>Phase Listener Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.PhaseListenerTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getPhaseListenerType()
	 * @generated
	 */
	int PHASE_LISTENER_TYPE = 79;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyClassTypeImpl <em>Property Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyClassTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getPropertyClassType()
	 * @generated
	 */
	int PROPERTY_CLASS_TYPE = 80;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyExtensionTypeImpl <em>Property Extension Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyExtensionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getPropertyExtensionType()
	 * @generated
	 */
	int PROPERTY_EXTENSION_TYPE = 81;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyNameTypeImpl <em>Property Name Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyNameTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getPropertyNameType()
	 * @generated
	 */
	int PROPERTY_NAME_TYPE = 82;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyResolverTypeImpl <em>Property Resolver Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyResolverTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getPropertyResolverType()
	 * @generated
	 */
	int PROPERTY_RESOLVER_TYPE = 83;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyTypeImpl <em>Property Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getPropertyType()
	 * @generated
	 */
	int PROPERTY_TYPE = 84;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RedirectTypeImpl <em>Redirect Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RedirectTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRedirectType()
	 * @generated
	 */
	int REDIRECT_TYPE = 85;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RedirectViewParamTypeImpl <em>Redirect View Param Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RedirectViewParamTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRedirectViewParamType()
	 * @generated
	 */
	int REDIRECT_VIEW_PARAM_TYPE = 86;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanClassTypeImpl <em>Referenced Bean Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanClassTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getReferencedBeanClassType()
	 * @generated
	 */
	int REFERENCED_BEAN_CLASS_TYPE = 87;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanNameTypeImpl <em>Referenced Bean Name Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanNameTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getReferencedBeanNameType()
	 * @generated
	 */
	int REFERENCED_BEAN_NAME_TYPE = 88;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanTypeImpl <em>Referenced Bean Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getReferencedBeanType()
	 * @generated
	 */
	int REFERENCED_BEAN_TYPE = 89;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererClassTypeImpl <em>Renderer Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RendererClassTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRendererClassType()
	 * @generated
	 */
	int RENDERER_CLASS_TYPE = 90;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererExtensionTypeImpl <em>Renderer Extension Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RendererExtensionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRendererExtensionType()
	 * @generated
	 */
	int RENDERER_EXTENSION_TYPE = 91;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeImpl <em>Renderer Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRendererType()
	 * @generated
	 */
	int RENDERER_TYPE = 92;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeTypeImpl <em>Renderer Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRendererTypeType()
	 * @generated
	 */
	int RENDERER_TYPE_TYPE = 93;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitClassTypeImpl <em>Render Kit Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitClassTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRenderKitClassType()
	 * @generated
	 */
	int RENDER_KIT_CLASS_TYPE = 94;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitFactoryTypeImpl <em>Render Kit Factory Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitFactoryTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRenderKitFactoryType()
	 * @generated
	 */
	int RENDER_KIT_FACTORY_TYPE = 95;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitIdTypeImpl <em>Render Kit Id Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitIdTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRenderKitIdType()
	 * @generated
	 */
	int RENDER_KIT_ID_TYPE = 96;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitTypeImpl <em>Render Kit Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRenderKitType()
	 * @generated
	 */
	int RENDER_KIT_TYPE = 97;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SmallIconTypeImpl <em>Small Icon Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.SmallIconTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getSmallIconType()
	 * @generated
	 */
	int SMALL_ICON_TYPE = 100;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SourceClassTypeImpl <em>Source Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.SourceClassTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getSourceClassType()
	 * @generated
	 */
	int SOURCE_CLASS_TYPE = 101;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.StateManagerTypeImpl <em>State Manager Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.StateManagerTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getStateManagerType()
	 * @generated
	 */
	int STATE_MANAGER_TYPE = 102;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SuggestedValueTypeImpl <em>Suggested Value Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.SuggestedValueTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getSuggestedValueType()
	 * @generated
	 */
	int SUGGESTED_VALUE_TYPE = 103;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SupportedLocaleTypeImpl <em>Supported Locale Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.SupportedLocaleTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getSupportedLocaleType()
	 * @generated
	 */
	int SUPPORTED_LOCALE_TYPE = 104;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SystemEventClassTypeImpl <em>System Event Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.SystemEventClassTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getSystemEventClassType()
	 * @generated
	 */
	int SYSTEM_EVENT_CLASS_TYPE = 105;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SystemEventListenerClassTypeImpl <em>System Event Listener Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.SystemEventListenerClassTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getSystemEventListenerClassType()
	 * @generated
	 */
	int SYSTEM_EVENT_LISTENER_CLASS_TYPE = 106;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SystemEventListenerTypeImpl <em>System Event Listener Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.SystemEventListenerTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getSystemEventListenerType()
	 * @generated
	 */
	int SYSTEM_EVENT_LISTENER_TYPE = 107;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.TagHandlerDelegateFactoryTypeImpl <em>Tag Handler Delegate Factory Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.TagHandlerDelegateFactoryTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getTagHandlerDelegateFactoryType()
	 * @generated
	 */
	int TAG_HANDLER_DELEGATE_FACTORY_TYPE = 108;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ToViewIdTypeImpl <em>To View Id Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ToViewIdTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getToViewIdType()
	 * @generated
	 */
	int TO_VIEW_ID_TYPE = 109;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorClassTypeImpl <em>Validator Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorClassTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getValidatorClassType()
	 * @generated
	 */
	int VALIDATOR_CLASS_TYPE = 110;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorIdTypeImpl <em>Validator Id Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorIdTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getValidatorIdType()
	 * @generated
	 */
	int VALIDATOR_ID_TYPE = 111;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorTypeImpl <em>Validator Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getValidatorType()
	 * @generated
	 */
	int VALIDATOR_TYPE = 112;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValueClassTypeImpl <em>Value Class Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ValueClassTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getValueClassType()
	 * @generated
	 */
	int VALUE_CLASS_TYPE = 114;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValueTypeImpl <em>Value Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ValueTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getValueType()
	 * @generated
	 */
	int VALUE_TYPE = 115;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.VariableResolverTypeImpl <em>Variable Resolver Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.VariableResolverTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getVariableResolverType()
	 * @generated
	 */
	int VARIABLE_RESOLVER_TYPE = 116;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ViewHandlerTypeImpl <em>View Handler Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ViewHandlerTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getViewHandlerType()
	 * @generated
	 */
	int VIEW_HANDLER_TYPE = 117;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationExtensionTypeImpl <em>Application Extension Type</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationExtensionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getApplicationExtensionType()
	 * @generated
	 */
    int APPLICATION_EXTENSION_TYPE = 4;

    /**
	 * The feature id for the '<em><b>Child Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int APPLICATION_EXTENSION_TYPE__CHILD_NODES = EXTENSION_TYPE__CHILD_NODES;

    /**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int APPLICATION_EXTENSION_TYPE__TEXT_CONTENT = EXTENSION_TYPE__TEXT_CONTENT;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int APPLICATION_EXTENSION_TYPE__ID = EXTENSION_TYPE__ID;

    /**
	 * The number of structural features of the '<em>Application Extension Type</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int APPLICATION_EXTENSION_TYPE_FEATURE_COUNT = EXTENSION_TYPE_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CLASS_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CLASS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Attribute Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CLASS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Child Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ATTRIBUTE_EXTENSION_TYPE__CHILD_NODES = EXTENSION_TYPE__CHILD_NODES;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ATTRIBUTE_EXTENSION_TYPE__TEXT_CONTENT = EXTENSION_TYPE__TEXT_CONTENT;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_EXTENSION_TYPE__ID = EXTENSION_TYPE__ID;

				/**
	 * The number of structural features of the '<em>Attribute Extension Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_EXTENSION_TYPE_FEATURE_COUNT = EXTENSION_TYPE_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NAME_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NAME_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Attribute Name Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_NAME_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__DESCRIPTION = 0;

				/**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__DISPLAY_NAME = 1;

				/**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__ICON = 2;

				/**
	 * The feature id for the '<em><b>Attribute Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__ATTRIBUTE_NAME = 3;

				/**
	 * The feature id for the '<em><b>Attribute Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__ATTRIBUTE_CLASS = 4;

				/**
	 * The feature id for the '<em><b>Default Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__DEFAULT_VALUE = 5;

				/**
	 * The feature id for the '<em><b>Suggested Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__SUGGESTED_VALUE = 6;

				/**
	 * The feature id for the '<em><b>Attribute Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__ATTRIBUTE_EXTENSION = 7;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__ID = 8;

				/**
	 * The number of structural features of the '<em>Attribute Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE_FEATURE_COUNT = 9;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_CLASS_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_CLASS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Behavior Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_CLASS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_ID_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_ID_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Behavior Id Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_ID_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_TYPE__DESCRIPTION = 0;

				/**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_TYPE__DISPLAY_NAME = 1;

				/**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_TYPE__ICON = 2;

				/**
	 * The feature id for the '<em><b>Behavior Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_TYPE__BEHAVIOR_ID = 3;

				/**
	 * The feature id for the '<em><b>Behavior Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_TYPE__BEHAVIOR_CLASS = 4;

				/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_TYPE__ATTRIBUTE = 5;

				/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_TYPE__PROPERTY = 6;

				/**
	 * The feature id for the '<em><b>Behavior Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_TYPE__BEHAVIOR_EXTENSION = 7;

				/**
	 * The number of structural features of the '<em>Behavior Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_TYPE_FEATURE_COUNT = 8;

				/**
	 * The feature id for the '<em><b>Child Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXTENSION_TYPE__CHILD_NODES = EXTENSION_TYPE__CHILD_NODES;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXTENSION_TYPE__TEXT_CONTENT = EXTENSION_TYPE__TEXT_CONTENT;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXTENSION_TYPE__ID = EXTENSION_TYPE__ID;

				/**
	 * The number of structural features of the '<em>Behavior Extension Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXTENSION_TYPE_FEATURE_COUNT = EXTENSION_TYPE_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_BEHAVIOR_RENDERER_CLASS_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_BEHAVIOR_RENDERER_CLASS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Client Behavior Renderer Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_BEHAVIOR_RENDERER_CLASS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Client Behavior Renderer Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_TYPE = 0;

				/**
	 * The feature id for the '<em><b>Client Behavior Renderer Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_CLASS = 1;

				/**
	 * The number of structural features of the '<em>Client Behavior Renderer Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_BEHAVIOR_RENDERER_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_BEHAVIOR_RENDERER_TYPE_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_BEHAVIOR_RENDERER_TYPE_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Client Behavior Renderer Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLIENT_BEHAVIOR_RENDERER_TYPE_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CLASS_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CLASS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Component Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CLASS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Child Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT_EXTENSION_TYPE__CHILD_NODES = EXTENSION_TYPE__CHILD_NODES;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT_EXTENSION_TYPE__TEXT_CONTENT = EXTENSION_TYPE__TEXT_CONTENT;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_EXTENSION_TYPE__ID = EXTENSION_TYPE__ID;

				/**
	 * The number of structural features of the '<em>Component Extension Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_EXTENSION_TYPE_FEATURE_COUNT = EXTENSION_TYPE_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FAMILY_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FAMILY_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Component Family Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FAMILY_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__DESCRIPTION = 0;

				/**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__DISPLAY_NAME = 1;

				/**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__ICON = 2;

				/**
	 * The feature id for the '<em><b>Component Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__COMPONENT_TYPE = 3;

				/**
	 * The feature id for the '<em><b>Component Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__COMPONENT_CLASS = 4;

				/**
	 * The feature id for the '<em><b>Facet</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__FACET = 5;

				/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__ATTRIBUTE = 6;

				/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__PROPERTY = 7;

				/**
	 * The feature id for the '<em><b>Component Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__COMPONENT_EXTENSION = 8;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__ID = 9;

				/**
	 * The number of structural features of the '<em>Component Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE_FEATURE_COUNT = 10;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Component Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_CLASS_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_CLASS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Converter Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_CLASS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_FOR_CLASS_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_FOR_CLASS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Converter For Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_FOR_CLASS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_ID_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_ID_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Converter Id Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_ID_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_TYPE__DESCRIPTION = 0;

				/**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_TYPE__DISPLAY_NAME = 1;

				/**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_TYPE__ICON = 2;

				/**
	 * The feature id for the '<em><b>Converter Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_TYPE__CONVERTER_ID = 3;

				/**
	 * The feature id for the '<em><b>Converter For Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_TYPE__CONVERTER_FOR_CLASS = 4;

				/**
	 * The feature id for the '<em><b>Converter Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_TYPE__CONVERTER_CLASS = 5;

				/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_TYPE__ATTRIBUTE = 6;

				/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_TYPE__PROPERTY = 7;

				/**
	 * The feature id for the '<em><b>Converter Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONVERTER_TYPE__CONVERTER_EXTENSION = 8;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_TYPE__ID = 9;

				/**
	 * The number of structural features of the '<em>Converter Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERTER_TYPE_FEATURE_COUNT = 10;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ResourceBundleTypeImpl <em>Resource Bundle Type</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ResourceBundleTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getResourceBundleType()
	 * @generated
	 */
    int RESOURCE_BUNDLE_TYPE = 118;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BaseNameTypeImpl <em>Base Name Type</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.BaseNameTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getBaseNameType()
	 * @generated
	 */
    int BASE_NAME_TYPE = 119;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.VarTypeImpl <em>Var Type</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.VarTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getVarType()
	 * @generated
	 */
    int VAR_TYPE = 120;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigExtensionTypeImpl <em>Extension Type</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigExtensionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFacesConfigExtensionType()
	 * @generated
	 */
    int FACES_CONFIG_EXTENSION_TYPE = 40;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryExtensionTypeImpl <em>Factory Extension Type</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryExtensionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFactoryExtensionType()
	 * @generated
	 */
    int FACTORY_EXTENSION_TYPE = 46;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.LifecycleExtensionTypeImpl <em>Lifecycle Extension Type</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.LifecycleExtensionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getLifecycleExtensionType()
	 * @generated
	 */
    int LIFECYCLE_EXTENSION_TYPE = 57;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterExtensionTypeImpl <em>Converter Extension Type</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterExtensionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getConverterExtensionType()
	 * @generated
	 */
    int CONVERTER_EXTENSION_TYPE = 25;

    /**
	 * The feature id for the '<em><b>Child Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONVERTER_EXTENSION_TYPE__CHILD_NODES = EXTENSION_TYPE__CHILD_NODES;

    /**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONVERTER_EXTENSION_TYPE__TEXT_CONTENT = EXTENSION_TYPE__TEXT_CONTENT;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONVERTER_EXTENSION_TYPE__ID = EXTENSION_TYPE__ID;

    /**
	 * The number of structural features of the '<em>Converter Extension Type</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONVERTER_EXTENSION_TYPE_FEATURE_COUNT = EXTENSION_TYPE_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_LOCALE_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_LOCALE_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Default Locale Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_LOCALE_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_RENDER_KIT_ID_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_RENDER_KIT_ID_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Default Render Kit Id Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_RENDER_KIT_ID_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Validator Id</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_VALIDATORS_TYPE__VALIDATOR_ID = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_VALIDATORS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Default Validators Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_VALIDATORS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_VALUE_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_VALUE_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Default Value Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_VALUE_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_TYPE__LANG = 1;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_TYPE__ID = 2;

				/**
	 * The number of structural features of the '<em>Description Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_TYPE_FEATURE_COUNT = 3;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_NAME_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_NAME_TYPE__LANG = 1;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_NAME_TYPE__ID = 2;

				/**
	 * The number of structural features of the '<em>Display Name Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_NAME_TYPE_FEATURE_COUNT = 3;

				/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

				/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

				/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

				/**
	 * The feature id for the '<em><b>Absolute Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ABSOLUTE_ORDERING = 3;

				/**
	 * The feature id for the '<em><b>Action Listener</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ACTION_LISTENER = 4;

				/**
	 * The feature id for the '<em><b>Application</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__APPLICATION = 5;

				/**
	 * The feature id for the '<em><b>Application Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__APPLICATION_FACTORY = 6;

				/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ATTRIBUTE = 7;

				/**
	 * The feature id for the '<em><b>Attribute Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ATTRIBUTE_CLASS = 8;

				/**
	 * The feature id for the '<em><b>Attribute Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ATTRIBUTE_EXTENSION = 9;

				/**
	 * The feature id for the '<em><b>Attribute Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ATTRIBUTE_NAME = 10;

				/**
	 * The feature id for the '<em><b>Behavior</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BEHAVIOR = 11;

				/**
	 * The feature id for the '<em><b>Behavior Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BEHAVIOR_CLASS = 12;

				/**
	 * The feature id for the '<em><b>Behavior Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BEHAVIOR_ID = 13;

				/**
	 * The feature id for the '<em><b>Behavior Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BEHAVIOR_EXTENSION = 14;

				/**
	 * The feature id for the '<em><b>Component</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COMPONENT = 15;

				/**
	 * The feature id for the '<em><b>Component Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COMPONENT_CLASS = 16;

				/**
	 * The feature id for the '<em><b>Component Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COMPONENT_EXTENSION = 17;

				/**
	 * The feature id for the '<em><b>Component Family</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COMPONENT_FAMILY = 18;

				/**
	 * The feature id for the '<em><b>Component Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COMPONENT_TYPE = 19;

				/**
	 * The feature id for the '<em><b>Converter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONVERTER = 20;

				/**
	 * The feature id for the '<em><b>Converter Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONVERTER_CLASS = 21;

				/**
	 * The feature id for the '<em><b>Converter For Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONVERTER_FOR_CLASS = 22;

				/**
	 * The feature id for the '<em><b>Converter Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONVERTER_ID = 23;

				/**
	 * The feature id for the '<em><b>Default Locale</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DEFAULT_LOCALE = 24;

				/**
	 * The feature id for the '<em><b>Default Render Kit Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DEFAULT_RENDER_KIT_ID = 25;

				/**
	 * The feature id for the '<em><b>Default Validators</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DEFAULT_VALIDATORS = 26;

				/**
	 * The feature id for the '<em><b>Default Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DEFAULT_VALUE = 27;

				/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DESCRIPTION = 28;

				/**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DISPLAY_NAME = 29;

				/**
	 * The feature id for the '<em><b>Exception Handler Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EXCEPTION_HANDLER_FACTORY = 30;

				/**
	 * The feature id for the '<em><b>External Context Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EXTERNAL_CONTEXT_FACTORY = 31;

				/**
	 * The feature id for the '<em><b>Faces Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FACES_CONFIG = 32;

				/**
	 * The feature id for the '<em><b>Faces Context Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FACES_CONTEXT_FACTORY = 33;

				/**
	 * The feature id for the '<em><b>Facet</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FACET = 34;

				/**
	 * The feature id for the '<em><b>Facet Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FACET_EXTENSION = 35;

				/**
	 * The feature id for the '<em><b>Facet Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FACET_NAME = 36;

				/**
	 * The feature id for the '<em><b>Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FACTORY = 37;

				/**
	 * The feature id for the '<em><b>From Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FROM_ACTION = 38;

				/**
	 * The feature id for the '<em><b>From Outcome</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FROM_OUTCOME = 39;

				/**
	 * The feature id for the '<em><b>From View Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FROM_VIEW_ID = 40;

				/**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ICON = 41;

				/**
	 * The feature id for the '<em><b>If</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__IF = 42;

				/**
	 * The feature id for the '<em><b>Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__KEY = 43;

				/**
	 * The feature id for the '<em><b>Key Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__KEY_CLASS = 44;

				/**
	 * The feature id for the '<em><b>Large Icon</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__LARGE_ICON = 45;

				/**
	 * The feature id for the '<em><b>Lifecycle</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__LIFECYCLE = 46;

				/**
	 * The feature id for the '<em><b>Lifecycle Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__LIFECYCLE_FACTORY = 47;

				/**
	 * The feature id for the '<em><b>List Entries</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__LIST_ENTRIES = 48;

				/**
	 * The feature id for the '<em><b>Locale Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__LOCALE_CONFIG = 49;

				/**
	 * The feature id for the '<em><b>Managed Bean</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MANAGED_BEAN = 50;

				/**
	 * The feature id for the '<em><b>Managed Bean Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MANAGED_BEAN_CLASS = 51;

				/**
	 * The feature id for the '<em><b>Managed Bean Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MANAGED_BEAN_NAME = 52;

				/**
	 * The feature id for the '<em><b>Managed Bean Scope</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MANAGED_BEAN_SCOPE = 53;

				/**
	 * The feature id for the '<em><b>Managed Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MANAGED_PROPERTY = 54;

				/**
	 * The feature id for the '<em><b>Map Entries</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MAP_ENTRIES = 55;

				/**
	 * The feature id for the '<em><b>Map Entry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MAP_ENTRY = 56;

				/**
	 * The feature id for the '<em><b>Message Bundle</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MESSAGE_BUNDLE = 57;

				/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__NAME = 58;

				/**
	 * The feature id for the '<em><b>Navigation Case</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__NAVIGATION_CASE = 59;

				/**
	 * The feature id for the '<em><b>Navigation Handler</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__NAVIGATION_HANDLER = 60;

				/**
	 * The feature id for the '<em><b>Navigation Rule</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__NAVIGATION_RULE = 61;

				/**
	 * The feature id for the '<em><b>Null Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__NULL_VALUE = 62;

				/**
	 * The feature id for the '<em><b>Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ORDERING = 63;

				/**
	 * The feature id for the '<em><b>Ordering Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ORDERING_ORDERING = 64;

				/**
	 * The feature id for the '<em><b>Others</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__OTHERS = 65;

				/**
	 * The feature id for the '<em><b>Partial View Context Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PARTIAL_VIEW_CONTEXT_FACTORY = 66;

				/**
	 * The feature id for the '<em><b>Phase Listener</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PHASE_LISTENER = 67;

				/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PROPERTY = 68;

				/**
	 * The feature id for the '<em><b>Property Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PROPERTY_CLASS = 69;

				/**
	 * The feature id for the '<em><b>Property Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PROPERTY_EXTENSION = 70;

				/**
	 * The feature id for the '<em><b>Property Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PROPERTY_NAME = 71;

				/**
	 * The feature id for the '<em><b>Property Resolver</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PROPERTY_RESOLVER = 72;

				/**
	 * The feature id for the '<em><b>Redirect</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__REDIRECT = 73;

				/**
	 * The feature id for the '<em><b>Redirect View Param</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__REDIRECT_VIEW_PARAM = 74;

				/**
	 * The feature id for the '<em><b>Referenced Bean</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__REFERENCED_BEAN = 75;

				/**
	 * The feature id for the '<em><b>Referenced Bean Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__REFERENCED_BEAN_CLASS = 76;

				/**
	 * The feature id for the '<em><b>Referenced Bean Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__REFERENCED_BEAN_NAME = 77;

				/**
	 * The feature id for the '<em><b>Renderer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RENDERER = 78;

				/**
	 * The feature id for the '<em><b>Renderer Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RENDERER_CLASS = 79;

				/**
	 * The feature id for the '<em><b>Renderer Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RENDERER_EXTENSION = 80;

				/**
	 * The feature id for the '<em><b>Renderer Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RENDERER_TYPE = 81;

				/**
	 * The feature id for the '<em><b>Render Kit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RENDER_KIT = 82;

				/**
	 * The feature id for the '<em><b>Render Kit Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RENDER_KIT_CLASS = 83;

				/**
	 * The feature id for the '<em><b>Render Kit Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RENDER_KIT_FACTORY = 84;

				/**
	 * The feature id for the '<em><b>Render Kit Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RENDER_KIT_ID = 85;

				/**
	 * The feature id for the '<em><b>Resource Handler</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RESOURCE_HANDLER = 86;

				/**
	 * The feature id for the '<em><b>Small Icon</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SMALL_ICON = 87;

				/**
	 * The feature id for the '<em><b>Source Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SOURCE_CLASS = 88;

				/**
	 * The feature id for the '<em><b>State Manager</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STATE_MANAGER = 89;

				/**
	 * The feature id for the '<em><b>Suggested Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SUGGESTED_VALUE = 90;

				/**
	 * The feature id for the '<em><b>Supported Locale</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SUPPORTED_LOCALE = 91;

				/**
	 * The feature id for the '<em><b>System Event Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SYSTEM_EVENT_CLASS = 92;

				/**
	 * The feature id for the '<em><b>System Event Listener</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SYSTEM_EVENT_LISTENER = 93;

				/**
	 * The feature id for the '<em><b>System Event Listener Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SYSTEM_EVENT_LISTENER_CLASS = 94;

				/**
	 * The feature id for the '<em><b>Tag Handler Delegate Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TAG_HANDLER_DELEGATE_FACTORY = 95;

				/**
	 * The feature id for the '<em><b>To View Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TO_VIEW_ID = 96;

				/**
	 * The feature id for the '<em><b>Validator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VALIDATOR = 97;

				/**
	 * The feature id for the '<em><b>Validator Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VALIDATOR_CLASS = 98;

				/**
	 * The feature id for the '<em><b>Validator Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VALIDATOR_ID = 99;

				/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VALUE = 100;

				/**
	 * The feature id for the '<em><b>Value Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VALUE_CLASS = 101;

				/**
	 * The feature id for the '<em><b>Variable Resolver</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VARIABLE_RESOLVER = 102;

				/**
	 * The feature id for the '<em><b>View Declaration Language Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VIEW_DECLARATION_LANGUAGE_FACTORY = 103;

				/**
	 * The feature id for the '<em><b>View Handler</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VIEW_HANDLER = 104;

				/**
	 * The feature id for the '<em><b>Visit Context Factory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VISIT_CONTEXT_FACTORY = 105;

				/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 106;

				/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DYNAMIC_ATTRIBUTE__NAME = 0;

				/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DYNAMIC_ATTRIBUTE__VALUE = 1;

				/**
	 * The number of structural features of the '<em>Dynamic Attribute</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DYNAMIC_ATTRIBUTE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Child Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DYNAMIC_ELEMENT__CHILD_NODES = 0;

				/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DYNAMIC_ELEMENT__NAME = 1;

				/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DYNAMIC_ELEMENT__ATTRIBUTES = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DYNAMIC_ELEMENT__TEXT_CONTENT = 3;

				/**
	 * The number of structural features of the '<em>Dynamic Element</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DYNAMIC_ELEMENT_FEATURE_COUNT = 4;


    /**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EL_RESOLVER_TYPE__TEXT_CONTENT = 0;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EL_RESOLVER_TYPE__ID = 1;

    /**
	 * The number of structural features of the '<em>EL Resolver Type</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EL_RESOLVER_TYPE_FEATURE_COUNT = 2;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ExceptionHandlerFactoryTypeImpl <em>Exception Handler Factory Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ExceptionHandlerFactoryTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getExceptionHandlerFactoryType()
	 * @generated
	 */
	int EXCEPTION_HANDLER_FACTORY_TYPE = 36;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_HANDLER_FACTORY_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_HANDLER_FACTORY_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Exception Handler Factory Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_HANDLER_FACTORY_TYPE_FEATURE_COUNT = 2;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ExternalContextFactoryTypeImpl <em>External Context Factory Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ExternalContextFactoryTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getExternalContextFactoryType()
	 * @generated
	 */
	int EXTERNAL_CONTEXT_FACTORY_TYPE = 38;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONTEXT_FACTORY_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONTEXT_FACTORY_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>External Context Factory Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_CONTEXT_FACTORY_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Application</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE__APPLICATION = 0;

				/**
	 * The feature id for the '<em><b>Ordering</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE__ORDERING = 1;

				/**
	 * The feature id for the '<em><b>Absolute Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE__ABSOLUTE_ORDERING = 2;

				/**
	 * The feature id for the '<em><b>Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE__FACTORY = 3;

				/**
	 * The feature id for the '<em><b>Component</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE__COMPONENT = 4;

				/**
	 * The feature id for the '<em><b>Converter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE__CONVERTER = 5;

				/**
	 * The feature id for the '<em><b>Managed Bean</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE__MANAGED_BEAN = 6;

				/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE__NAME = 7;

				/**
	 * The feature id for the '<em><b>Navigation Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE__NAVIGATION_RULE = 8;

				/**
	 * The feature id for the '<em><b>Referenced Bean</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE__REFERENCED_BEAN = 9;

				/**
	 * The feature id for the '<em><b>Render Kit</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE__RENDER_KIT = 10;

				/**
	 * The feature id for the '<em><b>Lifecycle</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE__LIFECYCLE = 11;

				/**
	 * The feature id for the '<em><b>Validator</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE__VALIDATOR = 12;

				/**
	 * The feature id for the '<em><b>Behavior</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE__BEHAVIOR = 13;

				/**
	 * The feature id for the '<em><b>Faces Config Extension</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACES_CONFIG_TYPE__FACES_CONFIG_EXTENSION = 14;

				/**
	 * The feature id for the '<em><b>Xmlns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE__XMLNS = 15;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE__ID = 16;

				/**
	 * The feature id for the '<em><b>Metadata Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE__METADATA_COMPLETE = 17;

				/**
	 * The number of structural features of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONFIG_TYPE_FEATURE_COUNT = 18;

				/**
	 * The feature id for the '<em><b>Child Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACES_CONFIG_EXTENSION_TYPE__CHILD_NODES = EXTENSION_TYPE__CHILD_NODES;

    /**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACES_CONFIG_EXTENSION_TYPE__TEXT_CONTENT = EXTENSION_TYPE__TEXT_CONTENT;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACES_CONFIG_EXTENSION_TYPE__ID = EXTENSION_TYPE__ID;

    /**
	 * The number of structural features of the '<em>Extension Type</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACES_CONFIG_EXTENSION_TYPE_FEATURE_COUNT = EXTENSION_TYPE_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONTEXT_FACTORY_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONTEXT_FACTORY_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Faces Context Factory Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACES_CONTEXT_FACTORY_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Child Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACET_EXTENSION_TYPE__CHILD_NODES = EXTENSION_TYPE__CHILD_NODES;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACET_EXTENSION_TYPE__TEXT_CONTENT = EXTENSION_TYPE__TEXT_CONTENT;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_EXTENSION_TYPE__ID = EXTENSION_TYPE__ID;

				/**
	 * The number of structural features of the '<em>Facet Extension Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_EXTENSION_TYPE_FEATURE_COUNT = EXTENSION_TYPE_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_NAME_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_NAME_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Facet Name Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_NAME_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_TYPE__DESCRIPTION = 0;

				/**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_TYPE__DISPLAY_NAME = 1;

				/**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_TYPE__ICON = 2;

				/**
	 * The feature id for the '<em><b>Facet Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_TYPE__FACET_NAME = 3;

				/**
	 * The feature id for the '<em><b>Facet Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_TYPE__FACET_EXTENSION = 4;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_TYPE__ID = 5;

				/**
	 * The number of structural features of the '<em>Facet Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_TYPE_FEATURE_COUNT = 6;

				/**
	 * The feature id for the '<em><b>Application Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTORY_TYPE__APPLICATION_FACTORY = 0;

				/**
	 * The feature id for the '<em><b>Exception Handler Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTORY_TYPE__EXCEPTION_HANDLER_FACTORY = 1;

				/**
	 * The feature id for the '<em><b>External Context Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTORY_TYPE__EXTERNAL_CONTEXT_FACTORY = 2;

				/**
	 * The feature id for the '<em><b>Faces Context Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTORY_TYPE__FACES_CONTEXT_FACTORY = 3;

				/**
	 * The feature id for the '<em><b>Partial View Context Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTORY_TYPE__PARTIAL_VIEW_CONTEXT_FACTORY = 4;

				/**
	 * The feature id for the '<em><b>Lifecycle Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTORY_TYPE__LIFECYCLE_FACTORY = 5;

				/**
	 * The feature id for the '<em><b>View Declaration Language Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTORY_TYPE__VIEW_DECLARATION_LANGUAGE_FACTORY = 6;

				/**
	 * The feature id for the '<em><b>Tag Handler Delegate Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTORY_TYPE__TAG_HANDLER_DELEGATE_FACTORY = 7;

				/**
	 * The feature id for the '<em><b>Render Kit Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTORY_TYPE__RENDER_KIT_FACTORY = 8;

				/**
	 * The feature id for the '<em><b>Visit Context Factory</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTORY_TYPE__VISIT_CONTEXT_FACTORY = 9;

				/**
	 * The feature id for the '<em><b>Factory Extension</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACTORY_TYPE__FACTORY_EXTENSION = 10;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTORY_TYPE__ID = 11;

				/**
	 * The number of structural features of the '<em>Factory Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACTORY_TYPE_FEATURE_COUNT = 12;


    /**
	 * The feature id for the '<em><b>Child Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACTORY_EXTENSION_TYPE__CHILD_NODES = EXTENSION_TYPE__CHILD_NODES;

    /**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACTORY_EXTENSION_TYPE__TEXT_CONTENT = EXTENSION_TYPE__TEXT_CONTENT;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACTORY_EXTENSION_TYPE__ID = EXTENSION_TYPE__ID;

    /**
	 * The number of structural features of the '<em>Factory Extension Type</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int FACTORY_EXTENSION_TYPE_FEATURE_COUNT = EXTENSION_TYPE_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_ACTION_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_ACTION_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>From Action Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_ACTION_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_OUTCOME_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_OUTCOME_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>From Outcome Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_OUTCOME_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_VIEW_ID_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_VIEW_ID_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>From View Id Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_VIEW_ID_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Small Icon</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICON_TYPE__SMALL_ICON = 0;

				/**
	 * The feature id for the '<em><b>Large Icon</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICON_TYPE__LARGE_ICON = 1;

				/**
	 * The feature id for the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICON_TYPE__LANG = 2;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICON_TYPE__ID = 3;

				/**
	 * The number of structural features of the '<em>Icon Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICON_TYPE_FEATURE_COUNT = 4;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>If Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_CLASS_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_CLASS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Key Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_CLASS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Key Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LARGE_ICON_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LARGE_ICON_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Large Icon Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LARGE_ICON_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIFECYCLE_FACTORY_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIFECYCLE_FACTORY_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Lifecycle Factory Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIFECYCLE_FACTORY_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Phase Listener</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIFECYCLE_TYPE__PHASE_LISTENER = 0;

				/**
	 * The feature id for the '<em><b>Lifecycle Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LIFECYCLE_TYPE__LIFECYCLE_EXTENSION = 1;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIFECYCLE_TYPE__ID = 2;

				/**
	 * The number of structural features of the '<em>Lifecycle Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIFECYCLE_TYPE_FEATURE_COUNT = 3;


    /**
	 * The feature id for the '<em><b>Child Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LIFECYCLE_EXTENSION_TYPE__CHILD_NODES = EXTENSION_TYPE__CHILD_NODES;

    /**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LIFECYCLE_EXTENSION_TYPE__TEXT_CONTENT = EXTENSION_TYPE__TEXT_CONTENT;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LIFECYCLE_EXTENSION_TYPE__ID = EXTENSION_TYPE__ID;

    /**
	 * The number of structural features of the '<em>Lifecycle Extension Type</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LIFECYCLE_EXTENSION_TYPE_FEATURE_COUNT = EXTENSION_TYPE_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Value Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_ENTRIES_TYPE__VALUE_CLASS = 0;

				/**
	 * The feature id for the '<em><b>Null Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_ENTRIES_TYPE__NULL_VALUE = 1;

				/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_ENTRIES_TYPE__VALUE = 2;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_ENTRIES_TYPE__ID = 3;

				/**
	 * The number of structural features of the '<em>List Entries Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_ENTRIES_TYPE_FEATURE_COUNT = 4;

				/**
	 * The feature id for the '<em><b>Default Locale</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCALE_CONFIG_TYPE__DEFAULT_LOCALE = 0;

				/**
	 * The feature id for the '<em><b>Supported Locale</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCALE_CONFIG_TYPE__SUPPORTED_LOCALE = 1;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCALE_CONFIG_TYPE__ID = 2;

				/**
	 * The number of structural features of the '<em>Locale Config Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCALE_CONFIG_TYPE_FEATURE_COUNT = 3;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_CLASS_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_CLASS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Managed Bean Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_CLASS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_NAME_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_NAME_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Managed Bean Name Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_NAME_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_SCOPE_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_SCOPE_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Managed Bean Scope Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_SCOPE_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_TYPE__DESCRIPTION = 0;

				/**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_TYPE__DISPLAY_NAME = 1;

				/**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_TYPE__ICON = 2;

				/**
	 * The feature id for the '<em><b>Managed Bean Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_TYPE__MANAGED_BEAN_NAME = 3;

				/**
	 * The feature id for the '<em><b>Managed Bean Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_TYPE__MANAGED_BEAN_CLASS = 4;

				/**
	 * The feature id for the '<em><b>Managed Bean Scope</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_TYPE__MANAGED_BEAN_SCOPE = 5;

				/**
	 * The feature id for the '<em><b>Managed Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_TYPE__MANAGED_PROPERTY = 6;

				/**
	 * The feature id for the '<em><b>Map Entries</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_TYPE__MAP_ENTRIES = 7;

				/**
	 * The feature id for the '<em><b>List Entries</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_TYPE__LIST_ENTRIES = 8;

				/**
	 * The feature id for the '<em><b>Managed Bean Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int MANAGED_BEAN_TYPE__MANAGED_BEAN_EXTENSION = 9;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_TYPE__ID = 10;

				/**
	 * The feature id for the '<em><b>Eager</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_TYPE__EAGER = 11;

				/**
	 * The number of structural features of the '<em>Managed Bean Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_BEAN_TYPE_FEATURE_COUNT = 12;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanExtensionTypeImpl <em>Managed Bean Extension Type</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanExtensionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getManagedBeanExtensionType()
	 * @generated
	 */
    int MANAGED_BEAN_EXTENSION_TYPE = 64;

    /**
	 * The feature id for the '<em><b>Child Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int MANAGED_BEAN_EXTENSION_TYPE__CHILD_NODES = EXTENSION_TYPE__CHILD_NODES;

    /**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int MANAGED_BEAN_EXTENSION_TYPE__TEXT_CONTENT = EXTENSION_TYPE__TEXT_CONTENT;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int MANAGED_BEAN_EXTENSION_TYPE__ID = EXTENSION_TYPE__ID;

    /**
	 * The number of structural features of the '<em>Managed Bean Extension Type</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int MANAGED_BEAN_EXTENSION_TYPE_FEATURE_COUNT = EXTENSION_TYPE_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_PROPERTY_TYPE__DESCRIPTION = 0;

				/**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_PROPERTY_TYPE__DISPLAY_NAME = 1;

				/**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_PROPERTY_TYPE__ICON = 2;

				/**
	 * The feature id for the '<em><b>Property Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_PROPERTY_TYPE__PROPERTY_NAME = 3;

				/**
	 * The feature id for the '<em><b>Property Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_PROPERTY_TYPE__PROPERTY_CLASS = 4;

				/**
	 * The feature id for the '<em><b>Map Entries</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_PROPERTY_TYPE__MAP_ENTRIES = 5;

				/**
	 * The feature id for the '<em><b>Null Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_PROPERTY_TYPE__NULL_VALUE = 6;

				/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_PROPERTY_TYPE__VALUE = 7;

				/**
	 * The feature id for the '<em><b>List Entries</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_PROPERTY_TYPE__LIST_ENTRIES = 8;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_PROPERTY_TYPE__ID = 9;

				/**
	 * The number of structural features of the '<em>Managed Property Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANAGED_PROPERTY_TYPE_FEATURE_COUNT = 10;

				/**
	 * The feature id for the '<em><b>Key Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ENTRIES_TYPE__KEY_CLASS = 0;

				/**
	 * The feature id for the '<em><b>Value Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ENTRIES_TYPE__VALUE_CLASS = 1;

				/**
	 * The feature id for the '<em><b>Map Entry</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ENTRIES_TYPE__MAP_ENTRY = 2;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ENTRIES_TYPE__ID = 3;

				/**
	 * The number of structural features of the '<em>Map Entries Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ENTRIES_TYPE_FEATURE_COUNT = 4;

				/**
	 * The feature id for the '<em><b>Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ENTRY_TYPE__KEY = 0;

				/**
	 * The feature id for the '<em><b>Null Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ENTRY_TYPE__NULL_VALUE = 1;

				/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ENTRY_TYPE__VALUE = 2;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ENTRY_TYPE__ID = 3;

				/**
	 * The number of structural features of the '<em>Map Entry Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_ENTRY_TYPE_FEATURE_COUNT = 4;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_BUNDLE_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_BUNDLE_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Message Bundle Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_BUNDLE_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Name Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CASE_TYPE__DESCRIPTION = 0;

				/**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CASE_TYPE__DISPLAY_NAME = 1;

				/**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CASE_TYPE__ICON = 2;

				/**
	 * The feature id for the '<em><b>From Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CASE_TYPE__FROM_ACTION = 3;

				/**
	 * The feature id for the '<em><b>From Outcome</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CASE_TYPE__FROM_OUTCOME = 4;

				/**
	 * The feature id for the '<em><b>If</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CASE_TYPE__IF = 5;

				/**
	 * The feature id for the '<em><b>To View Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CASE_TYPE__TO_VIEW_ID = 6;

				/**
	 * The feature id for the '<em><b>Redirect</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CASE_TYPE__REDIRECT = 7;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CASE_TYPE__ID = 8;

				/**
	 * The number of structural features of the '<em>Navigation Case Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CASE_TYPE_FEATURE_COUNT = 9;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_HANDLER_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_HANDLER_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Navigation Handler Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_HANDLER_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_RULE_TYPE__DESCRIPTION = 0;

				/**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_RULE_TYPE__DISPLAY_NAME = 1;

				/**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_RULE_TYPE__ICON = 2;

				/**
	 * The feature id for the '<em><b>From View Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_RULE_TYPE__FROM_VIEW_ID = 3;

				/**
	 * The feature id for the '<em><b>Navigation Case</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_RULE_TYPE__NAVIGATION_CASE = 4;

				/**
	 * The feature id for the '<em><b>Navigation Rule Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NAVIGATION_RULE_TYPE__NAVIGATION_RULE_EXTENSION = 5;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_RULE_TYPE__ID = 6;

				/**
	 * The number of structural features of the '<em>Navigation Rule Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_RULE_TYPE_FEATURE_COUNT = 7;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationRuleExtensionTypeImpl <em>Navigation Rule Extension Type</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationRuleExtensionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getNavigationRuleExtensionType()
	 * @generated
	 */
    int NAVIGATION_RULE_EXTENSION_TYPE = 73;

    /**
	 * The feature id for the '<em><b>Child Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NAVIGATION_RULE_EXTENSION_TYPE__CHILD_NODES = EXTENSION_TYPE__CHILD_NODES;

    /**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NAVIGATION_RULE_EXTENSION_TYPE__TEXT_CONTENT = EXTENSION_TYPE__TEXT_CONTENT;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NAVIGATION_RULE_EXTENSION_TYPE__ID = EXTENSION_TYPE__ID;

    /**
	 * The number of structural features of the '<em>Navigation Rule Extension Type</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NAVIGATION_RULE_EXTENSION_TYPE_FEATURE_COUNT = EXTENSION_TYPE_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_VALUE_TYPE__ID = 0;

				/**
	 * The number of structural features of the '<em>Null Value Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_VALUE_TYPE_FEATURE_COUNT = 1;

				/**
	 * The feature id for the '<em><b>Before</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERING_TYPE__BEFORE = 0;

				/**
	 * The feature id for the '<em><b>After</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERING_TYPE__AFTER = 1;

				/**
	 * The number of structural features of the '<em>Ordering Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERING_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERING_ORDERING_TYPE__NAME = 0;

				/**
	 * The feature id for the '<em><b>Others</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERING_ORDERING_TYPE__OTHERS = 1;

				/**
	 * The number of structural features of the '<em>Ordering Ordering Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERING_ORDERING_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERING_OTHERS_TYPE__ID = 0;

				/**
	 * The number of structural features of the '<em>Ordering Others Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERING_OTHERS_TYPE_FEATURE_COUNT = 1;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTIAL_VIEW_CONTEXT_FACTORY_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTIAL_VIEW_CONTEXT_FACTORY_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Partial View Context Factory Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTIAL_VIEW_CONTEXT_FACTORY_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE_LISTENER_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE_LISTENER_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Phase Listener Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHASE_LISTENER_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CLASS_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CLASS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Property Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CLASS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Child Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PROPERTY_EXTENSION_TYPE__CHILD_NODES = EXTENSION_TYPE__CHILD_NODES;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PROPERTY_EXTENSION_TYPE__TEXT_CONTENT = EXTENSION_TYPE__TEXT_CONTENT;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_EXTENSION_TYPE__ID = EXTENSION_TYPE__ID;

				/**
	 * The number of structural features of the '<em>Property Extension Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_EXTENSION_TYPE_FEATURE_COUNT = EXTENSION_TYPE_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAME_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAME_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Property Name Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAME_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_RESOLVER_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_RESOLVER_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Property Resolver Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_RESOLVER_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TYPE__DESCRIPTION = 0;

				/**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TYPE__DISPLAY_NAME = 1;

				/**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TYPE__ICON = 2;

				/**
	 * The feature id for the '<em><b>Property Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TYPE__PROPERTY_NAME = 3;

				/**
	 * The feature id for the '<em><b>Property Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TYPE__PROPERTY_CLASS = 4;

				/**
	 * The feature id for the '<em><b>Default Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TYPE__DEFAULT_VALUE = 5;

				/**
	 * The feature id for the '<em><b>Suggested Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TYPE__SUGGESTED_VALUE = 6;

				/**
	 * The feature id for the '<em><b>Property Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TYPE__PROPERTY_EXTENSION = 7;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TYPE__ID = 8;

				/**
	 * The number of structural features of the '<em>Property Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TYPE_FEATURE_COUNT = 9;

				/**
	 * The feature id for the '<em><b>View Param</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDIRECT_TYPE__VIEW_PARAM = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDIRECT_TYPE__ID = 1;

				/**
	 * The feature id for the '<em><b>Include View Params</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDIRECT_TYPE__INCLUDE_VIEW_PARAMS = 2;

				/**
	 * The number of structural features of the '<em>Redirect Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDIRECT_TYPE_FEATURE_COUNT = 3;

				/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDIRECT_VIEW_PARAM_TYPE__NAME = 0;

				/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDIRECT_VIEW_PARAM_TYPE__VALUE = 1;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDIRECT_VIEW_PARAM_TYPE__ID = 2;

				/**
	 * The number of structural features of the '<em>Redirect View Param Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REDIRECT_VIEW_PARAM_TYPE_FEATURE_COUNT = 3;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_BEAN_CLASS_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_BEAN_CLASS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Referenced Bean Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_BEAN_CLASS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_BEAN_NAME_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_BEAN_NAME_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Referenced Bean Name Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_BEAN_NAME_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_BEAN_TYPE__DESCRIPTION = 0;

				/**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_BEAN_TYPE__DISPLAY_NAME = 1;

				/**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_BEAN_TYPE__ICON = 2;

				/**
	 * The feature id for the '<em><b>Referenced Bean Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_BEAN_TYPE__REFERENCED_BEAN_NAME = 3;

				/**
	 * The feature id for the '<em><b>Referenced Bean Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_BEAN_TYPE__REFERENCED_BEAN_CLASS = 4;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_BEAN_TYPE__ID = 5;

				/**
	 * The number of structural features of the '<em>Referenced Bean Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_BEAN_TYPE_FEATURE_COUNT = 6;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_CLASS_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_CLASS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Renderer Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_CLASS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Child Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RENDERER_EXTENSION_TYPE__CHILD_NODES = EXTENSION_TYPE__CHILD_NODES;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RENDERER_EXTENSION_TYPE__TEXT_CONTENT = EXTENSION_TYPE__TEXT_CONTENT;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_EXTENSION_TYPE__ID = EXTENSION_TYPE__ID;

				/**
	 * The number of structural features of the '<em>Renderer Extension Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_EXTENSION_TYPE_FEATURE_COUNT = EXTENSION_TYPE_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_TYPE__DESCRIPTION = 0;

				/**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_TYPE__DISPLAY_NAME = 1;

				/**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_TYPE__ICON = 2;

				/**
	 * The feature id for the '<em><b>Component Family</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_TYPE__COMPONENT_FAMILY = 3;

				/**
	 * The feature id for the '<em><b>Renderer Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_TYPE__RENDERER_TYPE = 4;

				/**
	 * The feature id for the '<em><b>Renderer Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_TYPE__RENDERER_CLASS = 5;

				/**
	 * The feature id for the '<em><b>Facet</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_TYPE__FACET = 6;

				/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_TYPE__ATTRIBUTE = 7;

				/**
	 * The feature id for the '<em><b>Renderer Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_TYPE__RENDERER_EXTENSION = 8;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_TYPE__ID = 9;

				/**
	 * The number of structural features of the '<em>Renderer Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_TYPE_FEATURE_COUNT = 10;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_TYPE_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_TYPE_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Renderer Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERER_TYPE_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_CLASS_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_CLASS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Render Kit Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_CLASS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_FACTORY_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_FACTORY_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Render Kit Factory Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_FACTORY_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_ID_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_ID_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Render Kit Id Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_ID_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_TYPE__DESCRIPTION = 0;

				/**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_TYPE__DISPLAY_NAME = 1;

				/**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_TYPE__ICON = 2;

				/**
	 * The feature id for the '<em><b>Render Kit Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_TYPE__RENDER_KIT_ID = 3;

				/**
	 * The feature id for the '<em><b>Render Kit Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_TYPE__RENDER_KIT_CLASS = 4;

				/**
	 * The feature id for the '<em><b>Renderer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_TYPE__RENDERER = 5;

				/**
	 * The feature id for the '<em><b>Client Behavior Renderer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_TYPE__CLIENT_BEHAVIOR_RENDERER = 6;

				/**
	 * The feature id for the '<em><b>Render Kit Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RENDER_KIT_TYPE__RENDER_KIT_EXTENSION = 7;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_TYPE__ID = 8;

				/**
	 * The number of structural features of the '<em>Render Kit Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDER_KIT_TYPE_FEATURE_COUNT = 9;


    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorExtensionTypeImpl <em>Validator Extension Type</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorExtensionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getValidatorExtensionType()
	 * @generated
	 */
    int VALIDATOR_EXTENSION_TYPE = 113;

    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ViewDeclarationLanguageFactoryTypeImpl <em>View Declaration Language Factory Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ViewDeclarationLanguageFactoryTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getViewDeclarationLanguageFactoryType()
	 * @generated
	 */
	int VIEW_DECLARATION_LANGUAGE_FACTORY_TYPE = 121;

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.VisitContextFactoryTypeImpl <em>Visit Context Factory Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.VisitContextFactoryTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getVisitContextFactoryType()
	 * @generated
	 */
	int VISIT_CONTEXT_FACTORY_TYPE = 122;


				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.AbsoluteOrderingType <em>Absolute Ordering Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Absolute Ordering Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AbsoluteOrderingType
	 * @generated
	 */
	EClass getAbsoluteOrderingType();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.AbsoluteOrderingType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AbsoluteOrderingType#getName()
	 * @see #getAbsoluteOrderingType()
	 * @generated
	 */
	EReference getAbsoluteOrderingType_Name();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.AbsoluteOrderingType#getOthers <em>Others</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Others</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AbsoluteOrderingType#getOthers()
	 * @see #getAbsoluteOrderingType()
	 * @generated
	 */
	EReference getAbsoluteOrderingType_Others();

				/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitExtensionTypeImpl <em>Render Kit Extension Type</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitExtensionTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRenderKitExtensionType()
	 * @generated
	 */
    int RENDER_KIT_EXTENSION_TYPE = 98;

    /**
	 * The feature id for the '<em><b>Child Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RENDER_KIT_EXTENSION_TYPE__CHILD_NODES = EXTENSION_TYPE__CHILD_NODES;

    /**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RENDER_KIT_EXTENSION_TYPE__TEXT_CONTENT = EXTENSION_TYPE__TEXT_CONTENT;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RENDER_KIT_EXTENSION_TYPE__ID = EXTENSION_TYPE__ID;

    /**
	 * The number of structural features of the '<em>Render Kit Extension Type</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RENDER_KIT_EXTENSION_TYPE_FEATURE_COUNT = EXTENSION_TYPE_FEATURE_COUNT + 0;


    /**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ResourceHandlerTypeImpl <em>Resource Handler Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ResourceHandlerTypeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getResourceHandlerType()
	 * @generated
	 */
	int RESOURCE_HANDLER_TYPE = 99;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_HANDLER_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_HANDLER_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Resource Handler Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_HANDLER_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SMALL_ICON_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SMALL_ICON_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Small Icon Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SMALL_ICON_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_CLASS_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_CLASS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Source Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_CLASS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MANAGER_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MANAGER_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>State Manager Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MANAGER_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUGGESTED_VALUE_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUGGESTED_VALUE_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Suggested Value Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUGGESTED_VALUE_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTED_LOCALE_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTED_LOCALE_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Supported Locale Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPORTED_LOCALE_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_EVENT_CLASS_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_EVENT_CLASS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>System Event Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_EVENT_CLASS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_EVENT_LISTENER_CLASS_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_EVENT_LISTENER_CLASS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>System Event Listener Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_EVENT_LISTENER_CLASS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>System Event Listener Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_LISTENER_CLASS = 0;

				/**
	 * The feature id for the '<em><b>System Event Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_CLASS = 1;

				/**
	 * The feature id for the '<em><b>Source Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_EVENT_LISTENER_TYPE__SOURCE_CLASS = 2;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_EVENT_LISTENER_TYPE__ID = 3;

				/**
	 * The number of structural features of the '<em>System Event Listener Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_EVENT_LISTENER_TYPE_FEATURE_COUNT = 4;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_HANDLER_DELEGATE_FACTORY_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_HANDLER_DELEGATE_FACTORY_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Tag Handler Delegate Factory Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_HANDLER_DELEGATE_FACTORY_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO_VIEW_ID_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO_VIEW_ID_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>To View Id Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO_VIEW_ID_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_CLASS_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_CLASS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Validator Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_CLASS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_ID_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_ID_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Validator Id Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_ID_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_TYPE__DESCRIPTION = 0;

				/**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_TYPE__DISPLAY_NAME = 1;

				/**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_TYPE__ICON = 2;

				/**
	 * The feature id for the '<em><b>Validator Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_TYPE__VALIDATOR_ID = 3;

				/**
	 * The feature id for the '<em><b>Validator Class</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_TYPE__VALIDATOR_CLASS = 4;

				/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_TYPE__ATTRIBUTE = 5;

				/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_TYPE__PROPERTY = 6;

				/**
	 * The feature id for the '<em><b>Validator Extension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VALIDATOR_TYPE__VALIDATOR_EXTENSION = 7;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_TYPE__ID = 8;

				/**
	 * The number of structural features of the '<em>Validator Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_TYPE_FEATURE_COUNT = 9;

				/**
	 * The feature id for the '<em><b>Child Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VALIDATOR_EXTENSION_TYPE__CHILD_NODES = EXTENSION_TYPE__CHILD_NODES;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VALIDATOR_EXTENSION_TYPE__TEXT_CONTENT = EXTENSION_TYPE__TEXT_CONTENT;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VALIDATOR_EXTENSION_TYPE__ID = EXTENSION_TYPE__ID;

				/**
	 * The number of structural features of the '<em>Validator Extension Type</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VALIDATOR_EXTENSION_TYPE_FEATURE_COUNT = EXTENSION_TYPE_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_CLASS_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_CLASS_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Value Class Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_CLASS_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Value Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_RESOLVER_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_RESOLVER_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Variable Resolver Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_RESOLVER_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_HANDLER_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_HANDLER_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>View Handler Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_HANDLER_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESOURCE_BUNDLE_TYPE__DESCRIPTION = 0;

				/**
	 * The feature id for the '<em><b>Display Name</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESOURCE_BUNDLE_TYPE__DISPLAY_NAME = 1;

				/**
	 * The feature id for the '<em><b>Icon</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESOURCE_BUNDLE_TYPE__ICON = 2;

				/**
	 * The feature id for the '<em><b>Base Name</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESOURCE_BUNDLE_TYPE__BASE_NAME = 3;

				/**
	 * The feature id for the '<em><b>Var</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESOURCE_BUNDLE_TYPE__VAR = 4;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESOURCE_BUNDLE_TYPE__ID = 5;

				/**
	 * The number of structural features of the '<em>Resource Bundle Type</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESOURCE_BUNDLE_TYPE_FEATURE_COUNT = 6;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BASE_NAME_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BASE_NAME_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Base Name Type</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BASE_NAME_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VAR_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VAR_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Var Type</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VAR_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_DECLARATION_LANGUAGE_FACTORY_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_DECLARATION_LANGUAGE_FACTORY_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>View Declaration Language Factory Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_DECLARATION_LANGUAGE_FACTORY_TYPE_FEATURE_COUNT = 2;

				/**
	 * The feature id for the '<em><b>Text Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIT_CONTEXT_FACTORY_TYPE__TEXT_CONTENT = 0;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIT_CONTEXT_FACTORY_TYPE__ID = 1;

				/**
	 * The number of structural features of the '<em>Visit Context Factory Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIT_CONTEXT_FACTORY_TYPE_FEATURE_COUNT = 2;


				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ActionListenerType <em>Action Listener Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Listener Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ActionListenerType
	 * @generated
	 */
	EClass getActionListenerType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ActionListenerType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ActionListenerType#getTextContent()
	 * @see #getActionListenerType()
	 * @generated
	 */
	EAttribute getActionListenerType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ActionListenerType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ActionListenerType#getId()
	 * @see #getActionListenerType()
	 * @generated
	 */
	EAttribute getActionListenerType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType <em>Application Factory Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application Factory Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType
	 * @generated
	 */
	EClass getApplicationFactoryType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType#getTextContent()
	 * @see #getApplicationFactoryType()
	 * @generated
	 */
	EAttribute getApplicationFactoryType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType#getId()
	 * @see #getApplicationFactoryType()
	 * @generated
	 */
	EAttribute getApplicationFactoryType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType <em>Application Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationType
	 * @generated
	 */
	EClass getApplicationType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getActionListener <em>Action Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Action Listener</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getActionListener()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_ActionListener();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getDefaultRenderKitId <em>Default Render Kit Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Default Render Kit Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getDefaultRenderKitId()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_DefaultRenderKitId();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getMessageBundle <em>Message Bundle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Bundle</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getMessageBundle()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_MessageBundle();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getNavigationHandler <em>Navigation Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Navigation Handler</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getNavigationHandler()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_NavigationHandler();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getViewHandler <em>View Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>View Handler</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getViewHandler()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_ViewHandler();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getStateManager <em>State Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>State Manager</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getStateManager()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_StateManager();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getPropertyResolver <em>Property Resolver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property Resolver</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getPropertyResolver()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_PropertyResolver();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getVariableResolver <em>Variable Resolver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variable Resolver</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getVariableResolver()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_VariableResolver();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getResourceHandler <em>Resource Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Handler</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getResourceHandler()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_ResourceHandler();

				/**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getSystemEventListener <em>System Event Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>System Event Listener</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getSystemEventListener()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_SystemEventListener();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getLocaleConfig <em>Locale Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Locale Config</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getLocaleConfig()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_LocaleConfig();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getId()
	 * @see #getApplicationType()
	 * @generated
	 */
	EAttribute getApplicationType_Id();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getELResolver <em>EL Resolver</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>EL Resolver</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getELResolver()
	 * @see #getApplicationType()
	 * @generated
	 */
    EReference getApplicationType_ELResolver();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getResourceBundle <em>Resource Bundle</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Resource Bundle</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getResourceBundle()
	 * @see #getApplicationType()
	 * @generated
	 */
    EReference getApplicationType_ResourceBundle();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getApplicationExtension <em>Application Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Application Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getApplicationExtension()
	 * @see #getApplicationType()
	 * @generated
	 */
    EReference getApplicationType_ApplicationExtension();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getDefaultValidators <em>Default Validators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Default Validators</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationType#getDefaultValidators()
	 * @see #getApplicationType()
	 * @generated
	 */
	EReference getApplicationType_DefaultValidators();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeClassType <em>Attribute Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Class Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AttributeClassType
	 * @generated
	 */
	EClass getAttributeClassType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeClassType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AttributeClassType#getTextContent()
	 * @see #getAttributeClassType()
	 * @generated
	 */
	EAttribute getAttributeClassType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeClassType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AttributeClassType#getId()
	 * @see #getAttributeClassType()
	 * @generated
	 */
	EAttribute getAttributeClassType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeExtensionType <em>Attribute Extension Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Extension Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AttributeExtensionType
	 * @generated
	 */
	EClass getAttributeExtensionType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeNameType <em>Attribute Name Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Name Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AttributeNameType
	 * @generated
	 */
	EClass getAttributeNameType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeNameType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AttributeNameType#getTextContent()
	 * @see #getAttributeNameType()
	 * @generated
	 */
	EAttribute getAttributeNameType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeNameType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AttributeNameType#getId()
	 * @see #getAttributeNameType()
	 * @generated
	 */
	EAttribute getAttributeNameType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType <em>Attribute Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AttributeType
	 * @generated
	 */
	EClass getAttributeType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getDescription()
	 * @see #getAttributeType()
	 * @generated
	 */
	EReference getAttributeType_Description();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getDisplayName()
	 * @see #getAttributeType()
	 * @generated
	 */
	EReference getAttributeType_DisplayName();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getIcon()
	 * @see #getAttributeType()
	 * @generated
	 */
	EReference getAttributeType_Icon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getAttributeName <em>Attribute Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Attribute Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getAttributeName()
	 * @see #getAttributeType()
	 * @generated
	 */
	EReference getAttributeType_AttributeName();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getAttributeClass <em>Attribute Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Attribute Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getAttributeClass()
	 * @see #getAttributeType()
	 * @generated
	 */
	EReference getAttributeType_AttributeClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Default Value</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getDefaultValue()
	 * @see #getAttributeType()
	 * @generated
	 */
	EReference getAttributeType_DefaultValue();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getSuggestedValue <em>Suggested Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Suggested Value</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getSuggestedValue()
	 * @see #getAttributeType()
	 * @generated
	 */
	EReference getAttributeType_SuggestedValue();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getAttributeExtension <em>Attribute Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attribute Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getAttributeExtension()
	 * @see #getAttributeType()
	 * @generated
	 */
	EReference getAttributeType_AttributeExtension();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.AttributeType#getId()
	 * @see #getAttributeType()
	 * @generated
	 */
	EAttribute getAttributeType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorClassType <em>Behavior Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behavior Class Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BehaviorClassType
	 * @generated
	 */
	EClass getBehaviorClassType();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorClassType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BehaviorClassType#getTextContent()
	 * @see #getBehaviorClassType()
	 * @generated
	 */
	EAttribute getBehaviorClassType_TextContent();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorClassType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BehaviorClassType#getId()
	 * @see #getBehaviorClassType()
	 * @generated
	 */
	EAttribute getBehaviorClassType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorIdType <em>Behavior Id Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behavior Id Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BehaviorIdType
	 * @generated
	 */
	EClass getBehaviorIdType();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorIdType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BehaviorIdType#getTextContent()
	 * @see #getBehaviorIdType()
	 * @generated
	 */
	EAttribute getBehaviorIdType_TextContent();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorIdType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BehaviorIdType#getId()
	 * @see #getBehaviorIdType()
	 * @generated
	 */
	EAttribute getBehaviorIdType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType <em>Behavior Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behavior Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BehaviorType
	 * @generated
	 */
	EClass getBehaviorType();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getDescription()
	 * @see #getBehaviorType()
	 * @generated
	 */
	EReference getBehaviorType_Description();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getDisplayName()
	 * @see #getBehaviorType()
	 * @generated
	 */
	EReference getBehaviorType_DisplayName();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getIcon()
	 * @see #getBehaviorType()
	 * @generated
	 */
	EReference getBehaviorType_Icon();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getBehaviorId <em>Behavior Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Behavior Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getBehaviorId()
	 * @see #getBehaviorType()
	 * @generated
	 */
	EReference getBehaviorType_BehaviorId();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getBehaviorClass <em>Behavior Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Behavior Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getBehaviorClass()
	 * @see #getBehaviorType()
	 * @generated
	 */
	EReference getBehaviorType_BehaviorClass();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attribute</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getAttribute()
	 * @see #getBehaviorType()
	 * @generated
	 */
	EReference getBehaviorType_Attribute();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getProperty()
	 * @see #getBehaviorType()
	 * @generated
	 */
	EReference getBehaviorType_Property();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getBehaviorExtension <em>Behavior Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Behavior Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BehaviorType#getBehaviorExtension()
	 * @see #getBehaviorType()
	 * @generated
	 */
	EReference getBehaviorType_BehaviorExtension();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorExtensionType <em>Behavior Extension Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behavior Extension Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BehaviorExtensionType
	 * @generated
	 */
	EClass getBehaviorExtensionType();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererClassType <em>Client Behavior Renderer Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Client Behavior Renderer Class Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererClassType
	 * @generated
	 */
	EClass getClientBehaviorRendererClassType();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererClassType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererClassType#getTextContent()
	 * @see #getClientBehaviorRendererClassType()
	 * @generated
	 */
	EAttribute getClientBehaviorRendererClassType_TextContent();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererClassType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererClassType#getId()
	 * @see #getClientBehaviorRendererClassType()
	 * @generated
	 */
	EAttribute getClientBehaviorRendererClassType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererType <em>Client Behavior Renderer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Client Behavior Renderer Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererType
	 * @generated
	 */
	EClass getClientBehaviorRendererType();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererType#getClientBehaviorRendererType <em>Client Behavior Renderer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Client Behavior Renderer Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererType#getClientBehaviorRendererType()
	 * @see #getClientBehaviorRendererType()
	 * @generated
	 */
	EReference getClientBehaviorRendererType_ClientBehaviorRendererType();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererType#getClientBehaviorRendererClass <em>Client Behavior Renderer Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Client Behavior Renderer Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererType#getClientBehaviorRendererClass()
	 * @see #getClientBehaviorRendererType()
	 * @generated
	 */
	EReference getClientBehaviorRendererType_ClientBehaviorRendererClass();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererTypeType <em>Client Behavior Renderer Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Client Behavior Renderer Type Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererTypeType
	 * @generated
	 */
	EClass getClientBehaviorRendererTypeType();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererTypeType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererTypeType#getTextContent()
	 * @see #getClientBehaviorRendererTypeType()
	 * @generated
	 */
	EAttribute getClientBehaviorRendererTypeType_TextContent();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererTypeType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererTypeType#getId()
	 * @see #getClientBehaviorRendererTypeType()
	 * @generated
	 */
	EAttribute getClientBehaviorRendererTypeType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentClassType <em>Component Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Class Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentClassType
	 * @generated
	 */
	EClass getComponentClassType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentClassType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentClassType#getTextContent()
	 * @see #getComponentClassType()
	 * @generated
	 */
	EAttribute getComponentClassType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentClassType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentClassType#getId()
	 * @see #getComponentClassType()
	 * @generated
	 */
	EAttribute getComponentClassType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentExtensionType <em>Component Extension Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Extension Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentExtensionType
	 * @generated
	 */
	EClass getComponentExtensionType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentFamilyType <em>Component Family Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Family Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentFamilyType
	 * @generated
	 */
	EClass getComponentFamilyType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentFamilyType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentFamilyType#getTextContent()
	 * @see #getComponentFamilyType()
	 * @generated
	 */
	EAttribute getComponentFamilyType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentFamilyType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentFamilyType#getId()
	 * @see #getComponentFamilyType()
	 * @generated
	 */
	EAttribute getComponentFamilyType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentType
	 * @generated
	 */
	EClass getComponentType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getDescription()
	 * @see #getComponentType()
	 * @generated
	 */
	EReference getComponentType_Description();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getDisplayName()
	 * @see #getComponentType()
	 * @generated
	 */
	EReference getComponentType_DisplayName();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getIcon()
	 * @see #getComponentType()
	 * @generated
	 */
	EReference getComponentType_Icon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getComponentType()
	 * @see #getComponentType()
	 * @generated
	 */
	EReference getComponentType_ComponentType();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getComponentClass <em>Component Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getComponentClass()
	 * @see #getComponentType()
	 * @generated
	 */
	EReference getComponentType_ComponentClass();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getFacet <em>Facet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Facet</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getFacet()
	 * @see #getComponentType()
	 * @generated
	 */
	EReference getComponentType_Facet();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attribute</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getAttribute()
	 * @see #getComponentType()
	 * @generated
	 */
	EReference getComponentType_Attribute();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getProperty()
	 * @see #getComponentType()
	 * @generated
	 */
	EReference getComponentType_Property();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getComponentExtension <em>Component Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Component Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getComponentExtension()
	 * @see #getComponentType()
	 * @generated
	 */
	EReference getComponentType_ComponentExtension();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentType#getId()
	 * @see #getComponentType()
	 * @generated
	 */
	EAttribute getComponentType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentTypeType <em>Component Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Type Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentTypeType
	 * @generated
	 */
	EClass getComponentTypeType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentTypeType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentTypeType#getTextContent()
	 * @see #getComponentTypeType()
	 * @generated
	 */
	EAttribute getComponentTypeType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ComponentTypeType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ComponentTypeType#getId()
	 * @see #getComponentTypeType()
	 * @generated
	 */
	EAttribute getComponentTypeType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterClassType <em>Converter Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Converter Class Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterClassType
	 * @generated
	 */
	EClass getConverterClassType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterClassType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterClassType#getTextContent()
	 * @see #getConverterClassType()
	 * @generated
	 */
	EAttribute getConverterClassType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterClassType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterClassType#getId()
	 * @see #getConverterClassType()
	 * @generated
	 */
	EAttribute getConverterClassType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterForClassType <em>Converter For Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Converter For Class Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterForClassType
	 * @generated
	 */
	EClass getConverterForClassType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterForClassType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterForClassType#getTextContent()
	 * @see #getConverterForClassType()
	 * @generated
	 */
	EAttribute getConverterForClassType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterForClassType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterForClassType#getId()
	 * @see #getConverterForClassType()
	 * @generated
	 */
	EAttribute getConverterForClassType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterIdType <em>Converter Id Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Converter Id Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterIdType
	 * @generated
	 */
	EClass getConverterIdType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterIdType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterIdType#getTextContent()
	 * @see #getConverterIdType()
	 * @generated
	 */
	EAttribute getConverterIdType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterIdType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterIdType#getId()
	 * @see #getConverterIdType()
	 * @generated
	 */
	EAttribute getConverterIdType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType <em>Converter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Converter Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterType
	 * @generated
	 */
	EClass getConverterType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getDescription()
	 * @see #getConverterType()
	 * @generated
	 */
	EReference getConverterType_Description();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getDisplayName()
	 * @see #getConverterType()
	 * @generated
	 */
	EReference getConverterType_DisplayName();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getIcon()
	 * @see #getConverterType()
	 * @generated
	 */
	EReference getConverterType_Icon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getConverterId <em>Converter Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Converter Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getConverterId()
	 * @see #getConverterType()
	 * @generated
	 */
	EReference getConverterType_ConverterId();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getConverterForClass <em>Converter For Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Converter For Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getConverterForClass()
	 * @see #getConverterType()
	 * @generated
	 */
	EReference getConverterType_ConverterForClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getConverterClass <em>Converter Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Converter Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getConverterClass()
	 * @see #getConverterType()
	 * @generated
	 */
	EReference getConverterType_ConverterClass();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attribute</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getAttribute()
	 * @see #getConverterType()
	 * @generated
	 */
	EReference getConverterType_Attribute();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getProperty()
	 * @see #getConverterType()
	 * @generated
	 */
	EReference getConverterType_Property();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getConverterExtension <em>Converter Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Converter Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getConverterExtension()
	 * @see #getConverterType()
	 * @generated
	 */
    EReference getConverterType_ConverterExtension();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterType#getId()
	 * @see #getConverterType()
	 * @generated
	 */
	EAttribute getConverterType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.DefaultLocaleType <em>Default Locale Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Default Locale Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DefaultLocaleType
	 * @generated
	 */
	EClass getDefaultLocaleType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.DefaultLocaleType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DefaultLocaleType#getTextContent()
	 * @see #getDefaultLocaleType()
	 * @generated
	 */
	EAttribute getDefaultLocaleType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.DefaultLocaleType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DefaultLocaleType#getId()
	 * @see #getDefaultLocaleType()
	 * @generated
	 */
	EAttribute getDefaultLocaleType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.DefaultRenderKitIdType <em>Default Render Kit Id Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Default Render Kit Id Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DefaultRenderKitIdType
	 * @generated
	 */
	EClass getDefaultRenderKitIdType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.DefaultRenderKitIdType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DefaultRenderKitIdType#getTextContent()
	 * @see #getDefaultRenderKitIdType()
	 * @generated
	 */
	EAttribute getDefaultRenderKitIdType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.DefaultRenderKitIdType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DefaultRenderKitIdType#getId()
	 * @see #getDefaultRenderKitIdType()
	 * @generated
	 */
	EAttribute getDefaultRenderKitIdType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.DefaultValidatorsType <em>Default Validators Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Default Validators Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DefaultValidatorsType
	 * @generated
	 */
	EClass getDefaultValidatorsType();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.DefaultValidatorsType#getValidatorId <em>Validator Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Validator Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DefaultValidatorsType#getValidatorId()
	 * @see #getDefaultValidatorsType()
	 * @generated
	 */
	EReference getDefaultValidatorsType_ValidatorId();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.DefaultValidatorsType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DefaultValidatorsType#getId()
	 * @see #getDefaultValidatorsType()
	 * @generated
	 */
	EAttribute getDefaultValidatorsType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.DefaultValueType <em>Default Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Default Value Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DefaultValueType
	 * @generated
	 */
	EClass getDefaultValueType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.DefaultValueType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DefaultValueType#getTextContent()
	 * @see #getDefaultValueType()
	 * @generated
	 */
	EAttribute getDefaultValueType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.DefaultValueType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DefaultValueType#getId()
	 * @see #getDefaultValueType()
	 * @generated
	 */
	EAttribute getDefaultValueType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.DescriptionType <em>Description Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Description Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DescriptionType
	 * @generated
	 */
	EClass getDescriptionType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.DescriptionType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DescriptionType#getTextContent()
	 * @see #getDescriptionType()
	 * @generated
	 */
	EAttribute getDescriptionType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.DescriptionType#getLang <em>Lang</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lang</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DescriptionType#getLang()
	 * @see #getDescriptionType()
	 * @generated
	 */
	EAttribute getDescriptionType_Lang();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.DescriptionType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DescriptionType#getId()
	 * @see #getDescriptionType()
	 * @generated
	 */
	EAttribute getDescriptionType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType <em>Display Name Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Display Name Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType
	 * @generated
	 */
	EClass getDisplayNameType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType#getTextContent()
	 * @see #getDisplayNameType()
	 * @generated
	 */
	EAttribute getDisplayNameType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType#getLang <em>Lang</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lang</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType#getLang()
	 * @see #getDisplayNameType()
	 * @generated
	 */
	EAttribute getDisplayNameType_Lang();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType#getId()
	 * @see #getDisplayNameType()
	 * @generated
	 */
	EAttribute getDisplayNameType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

    /**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

    /**
	 * Returns the meta object for the map '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

    /**
	 * Returns the meta object for the map '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAbsoluteOrdering <em>Absolute Ordering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Absolute Ordering</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAbsoluteOrdering()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_AbsoluteOrdering();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getActionListener <em>Action Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action Listener</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getActionListener()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ActionListener();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getApplication <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Application</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getApplication()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Application();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getApplicationFactory <em>Application Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Application Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getApplicationFactory()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ApplicationFactory();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Attribute</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAttribute()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Attribute();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAttributeClass <em>Attribute Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Attribute Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAttributeClass()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_AttributeClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAttributeExtension <em>Attribute Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Attribute Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAttributeExtension()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_AttributeExtension();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAttributeName <em>Attribute Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Attribute Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getAttributeName()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_AttributeName();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getBehavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Behavior</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getBehavior()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Behavior();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getBehaviorClass <em>Behavior Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Behavior Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getBehaviorClass()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_BehaviorClass();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getBehaviorId <em>Behavior Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Behavior Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getBehaviorId()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_BehaviorId();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getBehaviorExtension <em>Behavior Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Behavior Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getBehaviorExtension()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_BehaviorExtension();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponent()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Component();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponentClass <em>Component Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponentClass()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ComponentClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponentExtension <em>Component Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponentExtension()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ComponentExtension();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponentFamily <em>Component Family</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component Family</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponentFamily()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ComponentFamily();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getComponentType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ComponentType();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getConverter <em>Converter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Converter</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getConverter()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Converter();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getConverterClass <em>Converter Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Converter Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getConverterClass()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ConverterClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getConverterForClass <em>Converter For Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Converter For Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getConverterForClass()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ConverterForClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getConverterId <em>Converter Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Converter Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getConverterId()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ConverterId();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDefaultLocale <em>Default Locale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Default Locale</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDefaultLocale()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DefaultLocale();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDefaultRenderKitId <em>Default Render Kit Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Default Render Kit Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDefaultRenderKitId()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DefaultRenderKitId();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDefaultValidators <em>Default Validators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Default Validators</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDefaultValidators()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DefaultValidators();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Default Value</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDefaultValue()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DefaultValue();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDescription()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Description();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getDisplayName()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_DisplayName();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getExceptionHandlerFactory <em>Exception Handler Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Exception Handler Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getExceptionHandlerFactory()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ExceptionHandlerFactory();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getExternalContextFactory <em>External Context Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>External Context Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getExternalContextFactory()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ExternalContextFactory();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacesConfig <em>Faces Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Faces Config</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacesConfig()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FacesConfig();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacesContextFactory <em>Faces Context Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Faces Context Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacesContextFactory()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FacesContextFactory();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacet <em>Facet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Facet</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacet()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Facet();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacetExtension <em>Facet Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Facet Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacetExtension()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FacetExtension();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacetName <em>Facet Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Facet Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFacetName()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FacetName();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFactory <em>Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFactory()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Factory();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFromAction <em>From Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>From Action</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFromAction()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FromAction();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFromOutcome <em>From Outcome</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>From Outcome</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFromOutcome()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FromOutcome();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFromViewId <em>From View Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>From View Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getFromViewId()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FromViewId();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getIcon()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Icon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getIf <em>If</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>If</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getIf()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_If();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Key</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getKey()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Key();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getKeyClass <em>Key Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Key Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getKeyClass()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_KeyClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getLargeIcon <em>Large Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Large Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getLargeIcon()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_LargeIcon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getLifecycle <em>Lifecycle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Lifecycle</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getLifecycle()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Lifecycle();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getLifecycleFactory <em>Lifecycle Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Lifecycle Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getLifecycleFactory()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_LifecycleFactory();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getListEntries <em>List Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>List Entries</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getListEntries()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ListEntries();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getLocaleConfig <em>Locale Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Locale Config</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getLocaleConfig()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_LocaleConfig();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedBean <em>Managed Bean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Managed Bean</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedBean()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ManagedBean();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedBeanClass <em>Managed Bean Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Managed Bean Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedBeanClass()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ManagedBeanClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedBeanName <em>Managed Bean Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Managed Bean Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedBeanName()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ManagedBeanName();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedBeanScope <em>Managed Bean Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Managed Bean Scope</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedBeanScope()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ManagedBeanScope();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedProperty <em>Managed Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Managed Property</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getManagedProperty()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ManagedProperty();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getMapEntries <em>Map Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Map Entries</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getMapEntries()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MapEntries();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getMapEntry <em>Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Map Entry</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getMapEntry()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MapEntry();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getMessageBundle <em>Message Bundle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Message Bundle</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getMessageBundle()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MessageBundle();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getName()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Name();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getNavigationCase <em>Navigation Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Navigation Case</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getNavigationCase()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_NavigationCase();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getNavigationHandler <em>Navigation Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Navigation Handler</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getNavigationHandler()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_NavigationHandler();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getNavigationRule <em>Navigation Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Navigation Rule</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getNavigationRule()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_NavigationRule();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getNullValue <em>Null Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Null Value</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getNullValue()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_NullValue();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getOrdering <em>Ordering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ordering</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getOrdering()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Ordering();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getOrderingOrdering <em>Ordering Ordering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ordering Ordering</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getOrderingOrdering()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_OrderingOrdering();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getOthers <em>Others</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Others</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getOthers()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Others();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPartialViewContextFactory <em>Partial View Context Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Partial View Context Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPartialViewContextFactory()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_PartialViewContextFactory();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPhaseListener <em>Phase Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Phase Listener</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPhaseListener()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_PhaseListener();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getProperty()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Property();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPropertyClass <em>Property Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPropertyClass()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_PropertyClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPropertyExtension <em>Property Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPropertyExtension()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_PropertyExtension();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPropertyName <em>Property Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPropertyName()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_PropertyName();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPropertyResolver <em>Property Resolver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property Resolver</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getPropertyResolver()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_PropertyResolver();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRedirect <em>Redirect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Redirect</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRedirect()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Redirect();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRedirectViewParam <em>Redirect View Param</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Redirect View Param</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRedirectViewParam()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_RedirectViewParam();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getReferencedBean <em>Referenced Bean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Referenced Bean</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getReferencedBean()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ReferencedBean();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getReferencedBeanClass <em>Referenced Bean Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Referenced Bean Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getReferencedBeanClass()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ReferencedBeanClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getReferencedBeanName <em>Referenced Bean Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Referenced Bean Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getReferencedBeanName()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ReferencedBeanName();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderer <em>Renderer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Renderer</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderer()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Renderer();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRendererClass <em>Renderer Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Renderer Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRendererClass()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_RendererClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRendererExtension <em>Renderer Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Renderer Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRendererExtension()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_RendererExtension();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRendererType <em>Renderer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Renderer Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRendererType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_RendererType();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderKit <em>Render Kit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Render Kit</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderKit()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_RenderKit();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderKitClass <em>Render Kit Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Render Kit Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderKitClass()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_RenderKitClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderKitFactory <em>Render Kit Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Render Kit Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderKitFactory()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_RenderKitFactory();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderKitId <em>Render Kit Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Render Kit Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getRenderKitId()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_RenderKitId();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getResourceHandler <em>Resource Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resource Handler</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getResourceHandler()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ResourceHandler();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSmallIcon <em>Small Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Small Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSmallIcon()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SmallIcon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSourceClass <em>Source Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Source Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSourceClass()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SourceClass();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getStateManager <em>State Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>State Manager</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getStateManager()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_StateManager();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSuggestedValue <em>Suggested Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Suggested Value</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSuggestedValue()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SuggestedValue();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSupportedLocale <em>Supported Locale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Supported Locale</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSupportedLocale()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SupportedLocale();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSystemEventClass <em>System Event Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>System Event Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSystemEventClass()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SystemEventClass();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSystemEventListener <em>System Event Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>System Event Listener</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSystemEventListener()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SystemEventListener();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSystemEventListenerClass <em>System Event Listener Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>System Event Listener Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getSystemEventListenerClass()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SystemEventListenerClass();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getTagHandlerDelegateFactory <em>Tag Handler Delegate Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tag Handler Delegate Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getTagHandlerDelegateFactory()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TagHandlerDelegateFactory();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getToViewId <em>To View Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>To View Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getToViewId()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ToViewId();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValidator <em>Validator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Validator</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValidator()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Validator();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValidatorClass <em>Validator Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Validator Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValidatorClass()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ValidatorClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValidatorId <em>Validator Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Validator Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValidatorId()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ValidatorId();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValue()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Value();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValueClass <em>Value Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getValueClass()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ValueClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getVariableResolver <em>Variable Resolver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Variable Resolver</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getVariableResolver()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_VariableResolver();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getViewDeclarationLanguageFactory <em>View Declaration Language Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>View Declaration Language Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getViewDeclarationLanguageFactory()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ViewDeclarationLanguageFactory();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getViewHandler <em>View Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>View Handler</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getViewHandler()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ViewHandler();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getVisitContextFactory <em>Visit Context Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Visit Context Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DocumentRoot#getVisitContextFactory()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_VisitContextFactory();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.DynamicAttribute <em>Dynamic Attribute</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic Attribute</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DynamicAttribute
	 * @generated
	 */
    EClass getDynamicAttribute();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.DynamicAttribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DynamicAttribute#getName()
	 * @see #getDynamicAttribute()
	 * @generated
	 */
    EAttribute getDynamicAttribute_Name();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.DynamicAttribute#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DynamicAttribute#getValue()
	 * @see #getDynamicAttribute()
	 * @generated
	 */
    EAttribute getDynamicAttribute_Value();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.DynamicElement <em>Dynamic Element</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic Element</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DynamicElement
	 * @generated
	 */
    EClass getDynamicElement();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.DynamicElement#getChildNodes <em>Child Nodes</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Child Nodes</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DynamicElement#getChildNodes()
	 * @see #getDynamicElement()
	 * @generated
	 */
    EReference getDynamicElement_ChildNodes();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.DynamicElement#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Attributes</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DynamicElement#getAttributes()
	 * @see #getDynamicElement()
	 * @generated
	 */
    EReference getDynamicElement_Attributes();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.DynamicElement#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DynamicElement#getTextContent()
	 * @see #getDynamicElement()
	 * @generated
	 */
    EAttribute getDynamicElement_TextContent();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ELResolverType <em>EL Resolver Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EL Resolver Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ELResolverType
	 * @generated
	 */
    EClass getELResolverType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ELResolverType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ELResolverType#getTextContent()
	 * @see #getELResolverType()
	 * @generated
	 */
    EAttribute getELResolverType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ELResolverType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ELResolverType#getId()
	 * @see #getELResolverType()
	 * @generated
	 */
    EAttribute getELResolverType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ExceptionHandlerFactoryType <em>Exception Handler Factory Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exception Handler Factory Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ExceptionHandlerFactoryType
	 * @generated
	 */
	EClass getExceptionHandlerFactoryType();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ExceptionHandlerFactoryType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ExceptionHandlerFactoryType#getTextContent()
	 * @see #getExceptionHandlerFactoryType()
	 * @generated
	 */
	EAttribute getExceptionHandlerFactoryType_TextContent();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ExceptionHandlerFactoryType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ExceptionHandlerFactoryType#getId()
	 * @see #getExceptionHandlerFactoryType()
	 * @generated
	 */
	EAttribute getExceptionHandlerFactoryType_Id();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.DynamicElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.DynamicElement#getName()
	 * @see #getDynamicElement()
	 * @generated
	 */
    EAttribute getDynamicElement_Name();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType
	 * @generated
	 */
	EClass getFacesConfigType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getApplication <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Application</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getApplication()
	 * @see #getFacesConfigType()
	 * @generated
	 */
	EReference getFacesConfigType_Application();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getOrdering <em>Ordering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ordering</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getOrdering()
	 * @see #getFacesConfigType()
	 * @generated
	 */
	EReference getFacesConfigType_Ordering();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getAbsoluteOrdering <em>Absolute Ordering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Absolute Ordering</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getAbsoluteOrdering()
	 * @see #getFacesConfigType()
	 * @generated
	 */
	EReference getFacesConfigType_AbsoluteOrdering();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getFactory <em>Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getFactory()
	 * @see #getFacesConfigType()
	 * @generated
	 */
	EReference getFacesConfigType_Factory();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Component</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getComponent()
	 * @see #getFacesConfigType()
	 * @generated
	 */
	EReference getFacesConfigType_Component();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getConverter <em>Converter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Converter</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getConverter()
	 * @see #getFacesConfigType()
	 * @generated
	 */
	EReference getFacesConfigType_Converter();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getManagedBean <em>Managed Bean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Managed Bean</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getManagedBean()
	 * @see #getFacesConfigType()
	 * @generated
	 */
	EReference getFacesConfigType_ManagedBean();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getName()
	 * @see #getFacesConfigType()
	 * @generated
	 */
	EReference getFacesConfigType_Name();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getNavigationRule <em>Navigation Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Navigation Rule</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getNavigationRule()
	 * @see #getFacesConfigType()
	 * @generated
	 */
	EReference getFacesConfigType_NavigationRule();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getReferencedBean <em>Referenced Bean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Referenced Bean</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getReferencedBean()
	 * @see #getFacesConfigType()
	 * @generated
	 */
	EReference getFacesConfigType_ReferencedBean();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getRenderKit <em>Render Kit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Render Kit</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getRenderKit()
	 * @see #getFacesConfigType()
	 * @generated
	 */
	EReference getFacesConfigType_RenderKit();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getLifecycle <em>Lifecycle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Lifecycle</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getLifecycle()
	 * @see #getFacesConfigType()
	 * @generated
	 */
	EReference getFacesConfigType_Lifecycle();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getValidator <em>Validator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Validator</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getValidator()
	 * @see #getFacesConfigType()
	 * @generated
	 */
	EReference getFacesConfigType_Validator();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getBehavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Behavior</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getBehavior()
	 * @see #getFacesConfigType()
	 * @generated
	 */
	EReference getFacesConfigType_Behavior();

				/**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getFacesConfigExtension <em>Faces Config Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Faces Config Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getFacesConfigExtension()
	 * @see #getFacesConfigType()
	 * @generated
	 */
    EReference getFacesConfigType_FacesConfigExtension();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getXmlns <em>Xmlns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xmlns</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getXmlns()
	 * @see #getFacesConfigType()
	 * @generated
	 */
	EAttribute getFacesConfigType_Xmlns();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getId()
	 * @see #getFacesConfigType()
	 * @generated
	 */
	EAttribute getFacesConfigType_Id();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#isMetadataComplete <em>Metadata Complete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Metadata Complete</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#isMetadataComplete()
	 * @see #getFacesConfigType()
	 * @generated
	 */
	EAttribute getFacesConfigType_MetadataComplete();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType <em>Faces Context Factory Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Faces Context Factory Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType
	 * @generated
	 */
	EClass getFacesContextFactoryType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType#getTextContent()
	 * @see #getFacesContextFactoryType()
	 * @generated
	 */
	EAttribute getFacesContextFactoryType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType#getId()
	 * @see #getFacesContextFactoryType()
	 * @generated
	 */
	EAttribute getFacesContextFactoryType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.FacetExtensionType <em>Facet Extension Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Facet Extension Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacetExtensionType
	 * @generated
	 */
	EClass getFacetExtensionType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.FacetNameType <em>Facet Name Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Facet Name Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacetNameType
	 * @generated
	 */
	EClass getFacetNameType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.FacetNameType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacetNameType#getTextContent()
	 * @see #getFacetNameType()
	 * @generated
	 */
	EAttribute getFacetNameType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.FacetNameType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacetNameType#getId()
	 * @see #getFacetNameType()
	 * @generated
	 */
	EAttribute getFacetNameType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.FacetType <em>Facet Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Facet Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacetType
	 * @generated
	 */
	EClass getFacetType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FacetType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacetType#getDescription()
	 * @see #getFacetType()
	 * @generated
	 */
	EReference getFacetType_Description();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FacetType#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacetType#getDisplayName()
	 * @see #getFacetType()
	 * @generated
	 */
	EReference getFacetType_DisplayName();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FacetType#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacetType#getIcon()
	 * @see #getFacetType()
	 * @generated
	 */
	EReference getFacetType_Icon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.FacetType#getFacetName <em>Facet Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Facet Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacetType#getFacetName()
	 * @see #getFacetType()
	 * @generated
	 */
	EReference getFacetType_FacetName();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FacetType#getFacetExtension <em>Facet Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Facet Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacetType#getFacetExtension()
	 * @see #getFacetType()
	 * @generated
	 */
	EReference getFacetType_FacetExtension();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.FacetType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacetType#getId()
	 * @see #getFacetType()
	 * @generated
	 */
	EAttribute getFacetType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType <em>Factory Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Factory Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FactoryType
	 * @generated
	 */
	EClass getFactoryType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getApplicationFactory <em>Application Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Application Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getApplicationFactory()
	 * @see #getFactoryType()
	 * @generated
	 */
	EReference getFactoryType_ApplicationFactory();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getExceptionHandlerFactory <em>Exception Handler Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handler Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getExceptionHandlerFactory()
	 * @see #getFactoryType()
	 * @generated
	 */
	EReference getFactoryType_ExceptionHandlerFactory();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getExternalContextFactory <em>External Context Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>External Context Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getExternalContextFactory()
	 * @see #getFactoryType()
	 * @generated
	 */
	EReference getFactoryType_ExternalContextFactory();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getFacesContextFactory <em>Faces Context Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Faces Context Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getFacesContextFactory()
	 * @see #getFactoryType()
	 * @generated
	 */
	EReference getFactoryType_FacesContextFactory();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getPartialViewContextFactory <em>Partial View Context Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Partial View Context Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getPartialViewContextFactory()
	 * @see #getFactoryType()
	 * @generated
	 */
	EReference getFactoryType_PartialViewContextFactory();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getLifecycleFactory <em>Lifecycle Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Lifecycle Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getLifecycleFactory()
	 * @see #getFactoryType()
	 * @generated
	 */
	EReference getFactoryType_LifecycleFactory();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getViewDeclarationLanguageFactory <em>View Declaration Language Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>View Declaration Language Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getViewDeclarationLanguageFactory()
	 * @see #getFactoryType()
	 * @generated
	 */
	EReference getFactoryType_ViewDeclarationLanguageFactory();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getTagHandlerDelegateFactory <em>Tag Handler Delegate Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tag Handler Delegate Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getTagHandlerDelegateFactory()
	 * @see #getFactoryType()
	 * @generated
	 */
	EReference getFactoryType_TagHandlerDelegateFactory();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getRenderKitFactory <em>Render Kit Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Render Kit Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getRenderKitFactory()
	 * @see #getFactoryType()
	 * @generated
	 */
	EReference getFactoryType_RenderKitFactory();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getVisitContextFactory <em>Visit Context Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Visit Context Factory</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getVisitContextFactory()
	 * @see #getFactoryType()
	 * @generated
	 */
	EReference getFactoryType_VisitContextFactory();

				/**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getFactoryExtension <em>Factory Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Factory Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getFactoryExtension()
	 * @see #getFactoryType()
	 * @generated
	 */
    EReference getFactoryType_FactoryExtension();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FactoryType#getId()
	 * @see #getFactoryType()
	 * @generated
	 */
	EAttribute getFactoryType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.FromActionType <em>From Action Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>From Action Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FromActionType
	 * @generated
	 */
	EClass getFromActionType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.FromActionType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FromActionType#getTextContent()
	 * @see #getFromActionType()
	 * @generated
	 */
	EAttribute getFromActionType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.FromActionType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FromActionType#getId()
	 * @see #getFromActionType()
	 * @generated
	 */
	EAttribute getFromActionType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.FromOutcomeType <em>From Outcome Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>From Outcome Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FromOutcomeType
	 * @generated
	 */
	EClass getFromOutcomeType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.FromOutcomeType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FromOutcomeType#getTextContent()
	 * @see #getFromOutcomeType()
	 * @generated
	 */
	EAttribute getFromOutcomeType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.FromOutcomeType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FromOutcomeType#getId()
	 * @see #getFromOutcomeType()
	 * @generated
	 */
	EAttribute getFromOutcomeType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType <em>From View Id Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>From View Id Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType
	 * @generated
	 */
	EClass getFromViewIdType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType#getTextContent()
	 * @see #getFromViewIdType()
	 * @generated
	 */
	EAttribute getFromViewIdType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType#getId()
	 * @see #getFromViewIdType()
	 * @generated
	 */
	EAttribute getFromViewIdType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.IconType <em>Icon Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Icon Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.IconType
	 * @generated
	 */
	EClass getIconType();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.IconType#getSmallIcon <em>Small Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Small Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.IconType#getSmallIcon()
	 * @see #getIconType()
	 * @generated
	 */
	EReference getIconType_SmallIcon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.IconType#getLargeIcon <em>Large Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Large Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.IconType#getLargeIcon()
	 * @see #getIconType()
	 * @generated
	 */
	EReference getIconType_LargeIcon();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.IconType#getLang <em>Lang</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lang</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.IconType#getLang()
	 * @see #getIconType()
	 * @generated
	 */
	EAttribute getIconType_Lang();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.IconType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.IconType#getId()
	 * @see #getIconType()
	 * @generated
	 */
	EAttribute getIconType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.IfType <em>If Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>If Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.IfType
	 * @generated
	 */
	EClass getIfType();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.IfType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.IfType#getTextContent()
	 * @see #getIfType()
	 * @generated
	 */
	EAttribute getIfType_TextContent();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.IfType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.IfType#getId()
	 * @see #getIfType()
	 * @generated
	 */
	EAttribute getIfType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.KeyClassType <em>Key Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Key Class Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.KeyClassType
	 * @generated
	 */
	EClass getKeyClassType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.KeyClassType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.KeyClassType#getTextContent()
	 * @see #getKeyClassType()
	 * @generated
	 */
	EAttribute getKeyClassType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.KeyClassType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.KeyClassType#getId()
	 * @see #getKeyClassType()
	 * @generated
	 */
	EAttribute getKeyClassType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.KeyType <em>Key Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Key Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.KeyType
	 * @generated
	 */
	EClass getKeyType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.KeyType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.KeyType#getTextContent()
	 * @see #getKeyType()
	 * @generated
	 */
	EAttribute getKeyType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.KeyType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.KeyType#getId()
	 * @see #getKeyType()
	 * @generated
	 */
	EAttribute getKeyType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.LargeIconType <em>Large Icon Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Large Icon Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.LargeIconType
	 * @generated
	 */
	EClass getLargeIconType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.LargeIconType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.LargeIconType#getTextContent()
	 * @see #getLargeIconType()
	 * @generated
	 */
	EAttribute getLargeIconType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.LargeIconType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.LargeIconType#getId()
	 * @see #getLargeIconType()
	 * @generated
	 */
	EAttribute getLargeIconType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType <em>Lifecycle Factory Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lifecycle Factory Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType
	 * @generated
	 */
	EClass getLifecycleFactoryType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType#getTextContent()
	 * @see #getLifecycleFactoryType()
	 * @generated
	 */
	EAttribute getLifecycleFactoryType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType#getId()
	 * @see #getLifecycleFactoryType()
	 * @generated
	 */
	EAttribute getLifecycleFactoryType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.LifecycleType <em>Lifecycle Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lifecycle Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.LifecycleType
	 * @generated
	 */
	EClass getLifecycleType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.LifecycleType#getPhaseListener <em>Phase Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Phase Listener</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.LifecycleType#getPhaseListener()
	 * @see #getLifecycleType()
	 * @generated
	 */
	EReference getLifecycleType_PhaseListener();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.LifecycleType#getLifecycleExtension <em>Lifecycle Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Lifecycle Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.LifecycleType#getLifecycleExtension()
	 * @see #getLifecycleType()
	 * @generated
	 */
    EReference getLifecycleType_LifecycleExtension();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.LifecycleType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.LifecycleType#getId()
	 * @see #getLifecycleType()
	 * @generated
	 */
	EAttribute getLifecycleType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType <em>List Entries Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Entries Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType
	 * @generated
	 */
	EClass getListEntriesType();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType#getValueClass <em>Value Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType#getValueClass()
	 * @see #getListEntriesType()
	 * @generated
	 */
	EReference getListEntriesType_ValueClass();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType#getNullValue <em>Null Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Null Value</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType#getNullValue()
	 * @see #getListEntriesType()
	 * @generated
	 */
	EReference getListEntriesType_NullValue();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Value</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType#getValue()
	 * @see #getListEntriesType()
	 * @generated
	 */
	EReference getListEntriesType_Value();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType#getId()
	 * @see #getListEntriesType()
	 * @generated
	 */
	EAttribute getListEntriesType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.LocaleConfigType <em>Locale Config Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Locale Config Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.LocaleConfigType
	 * @generated
	 */
	EClass getLocaleConfigType();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.LocaleConfigType#getDefaultLocale <em>Default Locale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Default Locale</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.LocaleConfigType#getDefaultLocale()
	 * @see #getLocaleConfigType()
	 * @generated
	 */
	EReference getLocaleConfigType_DefaultLocale();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.LocaleConfigType#getSupportedLocale <em>Supported Locale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Supported Locale</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.LocaleConfigType#getSupportedLocale()
	 * @see #getLocaleConfigType()
	 * @generated
	 */
	EReference getLocaleConfigType_SupportedLocale();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.LocaleConfigType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.LocaleConfigType#getId()
	 * @see #getLocaleConfigType()
	 * @generated
	 */
	EAttribute getLocaleConfigType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType <em>Managed Bean Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Managed Bean Class Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType
	 * @generated
	 */
	EClass getManagedBeanClassType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType#getTextContent()
	 * @see #getManagedBeanClassType()
	 * @generated
	 */
	EAttribute getManagedBeanClassType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType#getId()
	 * @see #getManagedBeanClassType()
	 * @generated
	 */
	EAttribute getManagedBeanClassType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType <em>Managed Bean Name Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Managed Bean Name Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType
	 * @generated
	 */
	EClass getManagedBeanNameType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType#getTextContent()
	 * @see #getManagedBeanNameType()
	 * @generated
	 */
	EAttribute getManagedBeanNameType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType#getId()
	 * @see #getManagedBeanNameType()
	 * @generated
	 */
	EAttribute getManagedBeanNameType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType <em>Managed Bean Scope Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Managed Bean Scope Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType
	 * @generated
	 */
	EClass getManagedBeanScopeType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType#getTextContent()
	 * @see #getManagedBeanScopeType()
	 * @generated
	 */
	EAttribute getManagedBeanScopeType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType#getId()
	 * @see #getManagedBeanScopeType()
	 * @generated
	 */
	EAttribute getManagedBeanScopeType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType <em>Managed Bean Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Managed Bean Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType
	 * @generated
	 */
	EClass getManagedBeanType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getDescription()
	 * @see #getManagedBeanType()
	 * @generated
	 */
	EReference getManagedBeanType_Description();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getDisplayName()
	 * @see #getManagedBeanType()
	 * @generated
	 */
	EReference getManagedBeanType_DisplayName();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getIcon()
	 * @see #getManagedBeanType()
	 * @generated
	 */
	EReference getManagedBeanType_Icon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedBeanName <em>Managed Bean Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Managed Bean Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedBeanName()
	 * @see #getManagedBeanType()
	 * @generated
	 */
	EReference getManagedBeanType_ManagedBeanName();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedBeanClass <em>Managed Bean Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Managed Bean Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedBeanClass()
	 * @see #getManagedBeanType()
	 * @generated
	 */
	EReference getManagedBeanType_ManagedBeanClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedBeanScope <em>Managed Bean Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Managed Bean Scope</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedBeanScope()
	 * @see #getManagedBeanType()
	 * @generated
	 */
	EReference getManagedBeanType_ManagedBeanScope();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedProperty <em>Managed Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Managed Property</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedProperty()
	 * @see #getManagedBeanType()
	 * @generated
	 */
	EReference getManagedBeanType_ManagedProperty();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getMapEntries <em>Map Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Map Entries</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getMapEntries()
	 * @see #getManagedBeanType()
	 * @generated
	 */
	EReference getManagedBeanType_MapEntries();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getListEntries <em>List Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>List Entries</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getListEntries()
	 * @see #getManagedBeanType()
	 * @generated
	 */
	EReference getManagedBeanType_ListEntries();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedBeanExtension <em>Managed Bean Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Managed Bean Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getManagedBeanExtension()
	 * @see #getManagedBeanType()
	 * @generated
	 */
    EReference getManagedBeanType_ManagedBeanExtension();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#getId()
	 * @see #getManagedBeanType()
	 * @generated
	 */
	EAttribute getManagedBeanType_Id();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#isEager <em>Eager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Eager</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType#isEager()
	 * @see #getManagedBeanType()
	 * @generated
	 */
	EAttribute getManagedBeanType_Eager();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType <em>Managed Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Managed Property Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType
	 * @generated
	 */
	EClass getManagedPropertyType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getDescription()
	 * @see #getManagedPropertyType()
	 * @generated
	 */
	EReference getManagedPropertyType_Description();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getDisplayName()
	 * @see #getManagedPropertyType()
	 * @generated
	 */
	EReference getManagedPropertyType_DisplayName();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getIcon()
	 * @see #getManagedPropertyType()
	 * @generated
	 */
	EReference getManagedPropertyType_Icon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getPropertyName <em>Property Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getPropertyName()
	 * @see #getManagedPropertyType()
	 * @generated
	 */
	EReference getManagedPropertyType_PropertyName();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getPropertyClass <em>Property Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getPropertyClass()
	 * @see #getManagedPropertyType()
	 * @generated
	 */
	EReference getManagedPropertyType_PropertyClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getMapEntries <em>Map Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Map Entries</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getMapEntries()
	 * @see #getManagedPropertyType()
	 * @generated
	 */
	EReference getManagedPropertyType_MapEntries();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getNullValue <em>Null Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Null Value</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getNullValue()
	 * @see #getManagedPropertyType()
	 * @generated
	 */
	EReference getManagedPropertyType_NullValue();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getValue()
	 * @see #getManagedPropertyType()
	 * @generated
	 */
	EReference getManagedPropertyType_Value();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getListEntries <em>List Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>List Entries</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getListEntries()
	 * @see #getManagedPropertyType()
	 * @generated
	 */
	EReference getManagedPropertyType_ListEntries();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType#getId()
	 * @see #getManagedPropertyType()
	 * @generated
	 */
	EAttribute getManagedPropertyType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType <em>Map Entries Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map Entries Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType
	 * @generated
	 */
	EClass getMapEntriesType();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType#getKeyClass <em>Key Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Key Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType#getKeyClass()
	 * @see #getMapEntriesType()
	 * @generated
	 */
	EReference getMapEntriesType_KeyClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType#getValueClass <em>Value Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType#getValueClass()
	 * @see #getMapEntriesType()
	 * @generated
	 */
	EReference getMapEntriesType_ValueClass();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType#getMapEntry <em>Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Map Entry</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType#getMapEntry()
	 * @see #getMapEntriesType()
	 * @generated
	 */
	EReference getMapEntriesType_MapEntry();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType#getId()
	 * @see #getMapEntriesType()
	 * @generated
	 */
	EAttribute getMapEntriesType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.MapEntryType <em>Map Entry Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map Entry Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.MapEntryType
	 * @generated
	 */
	EClass getMapEntryType();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.MapEntryType#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Key</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.MapEntryType#getKey()
	 * @see #getMapEntryType()
	 * @generated
	 */
	EReference getMapEntryType_Key();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.MapEntryType#getNullValue <em>Null Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Null Value</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.MapEntryType#getNullValue()
	 * @see #getMapEntryType()
	 * @generated
	 */
	EReference getMapEntryType_NullValue();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.MapEntryType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.MapEntryType#getValue()
	 * @see #getMapEntryType()
	 * @generated
	 */
	EReference getMapEntryType_Value();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.MapEntryType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.MapEntryType#getId()
	 * @see #getMapEntryType()
	 * @generated
	 */
	EAttribute getMapEntryType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.MessageBundleType <em>Message Bundle Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message Bundle Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.MessageBundleType
	 * @generated
	 */
	EClass getMessageBundleType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.MessageBundleType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.MessageBundleType#getTextContent()
	 * @see #getMessageBundleType()
	 * @generated
	 */
	EAttribute getMessageBundleType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.MessageBundleType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.MessageBundleType#getId()
	 * @see #getMessageBundleType()
	 * @generated
	 */
	EAttribute getMessageBundleType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.NameType <em>Name Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Name Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NameType
	 * @generated
	 */
	EClass getNameType();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.NameType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NameType#getTextContent()
	 * @see #getNameType()
	 * @generated
	 */
	EAttribute getNameType_TextContent();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.NameType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NameType#getId()
	 * @see #getNameType()
	 * @generated
	 */
	EAttribute getNameType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType <em>Navigation Case Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation Case Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType
	 * @generated
	 */
	EClass getNavigationCaseType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getDescription()
	 * @see #getNavigationCaseType()
	 * @generated
	 */
	EReference getNavigationCaseType_Description();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getDisplayName()
	 * @see #getNavigationCaseType()
	 * @generated
	 */
	EReference getNavigationCaseType_DisplayName();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getIcon()
	 * @see #getNavigationCaseType()
	 * @generated
	 */
	EReference getNavigationCaseType_Icon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getFromAction <em>From Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>From Action</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getFromAction()
	 * @see #getNavigationCaseType()
	 * @generated
	 */
	EReference getNavigationCaseType_FromAction();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getFromOutcome <em>From Outcome</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>From Outcome</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getFromOutcome()
	 * @see #getNavigationCaseType()
	 * @generated
	 */
	EReference getNavigationCaseType_FromOutcome();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getIf <em>If</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>If</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getIf()
	 * @see #getNavigationCaseType()
	 * @generated
	 */
	EReference getNavigationCaseType_If();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getToViewId <em>To View Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>To View Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getToViewId()
	 * @see #getNavigationCaseType()
	 * @generated
	 */
	EReference getNavigationCaseType_ToViewId();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getRedirect <em>Redirect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Redirect</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getRedirect()
	 * @see #getNavigationCaseType()
	 * @generated
	 */
	EReference getNavigationCaseType_Redirect();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType#getId()
	 * @see #getNavigationCaseType()
	 * @generated
	 */
	EAttribute getNavigationCaseType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationHandlerType <em>Navigation Handler Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation Handler Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationHandlerType
	 * @generated
	 */
	EClass getNavigationHandlerType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationHandlerType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationHandlerType#getTextContent()
	 * @see #getNavigationHandlerType()
	 * @generated
	 */
	EAttribute getNavigationHandlerType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationHandlerType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationHandlerType#getId()
	 * @see #getNavigationHandlerType()
	 * @generated
	 */
	EAttribute getNavigationHandlerType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType <em>Navigation Rule Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation Rule Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType
	 * @generated
	 */
	EClass getNavigationRuleType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getDescription()
	 * @see #getNavigationRuleType()
	 * @generated
	 */
	EReference getNavigationRuleType_Description();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getDisplayName()
	 * @see #getNavigationRuleType()
	 * @generated
	 */
	EReference getNavigationRuleType_DisplayName();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getIcon()
	 * @see #getNavigationRuleType()
	 * @generated
	 */
	EReference getNavigationRuleType_Icon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getFromViewId <em>From View Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>From View Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getFromViewId()
	 * @see #getNavigationRuleType()
	 * @generated
	 */
	EReference getNavigationRuleType_FromViewId();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getNavigationCase <em>Navigation Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Navigation Case</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getNavigationCase()
	 * @see #getNavigationRuleType()
	 * @generated
	 */
	EReference getNavigationRuleType_NavigationCase();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getNavigationRuleExtension <em>Navigation Rule Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Navigation Rule Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getNavigationRuleExtension()
	 * @see #getNavigationRuleType()
	 * @generated
	 */
    EReference getNavigationRuleType_NavigationRuleExtension();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType#getId()
	 * @see #getNavigationRuleType()
	 * @generated
	 */
	EAttribute getNavigationRuleType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.NullValueType <em>Null Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Null Value Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NullValueType
	 * @generated
	 */
	EClass getNullValueType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.NullValueType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NullValueType#getId()
	 * @see #getNullValueType()
	 * @generated
	 */
	EAttribute getNullValueType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.OrderingType <em>Ordering Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ordering Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.OrderingType
	 * @generated
	 */
	EClass getOrderingType();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.OrderingType#getBefore <em>Before</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Before</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.OrderingType#getBefore()
	 * @see #getOrderingType()
	 * @generated
	 */
	EReference getOrderingType_Before();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.OrderingType#getAfter <em>After</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>After</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.OrderingType#getAfter()
	 * @see #getOrderingType()
	 * @generated
	 */
	EReference getOrderingType_After();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.OrderingOrderingType <em>Ordering Ordering Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ordering Ordering Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.OrderingOrderingType
	 * @generated
	 */
	EClass getOrderingOrderingType();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.OrderingOrderingType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.OrderingOrderingType#getName()
	 * @see #getOrderingOrderingType()
	 * @generated
	 */
	EReference getOrderingOrderingType_Name();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.OrderingOrderingType#getOthers <em>Others</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Others</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.OrderingOrderingType#getOthers()
	 * @see #getOrderingOrderingType()
	 * @generated
	 */
	EReference getOrderingOrderingType_Others();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.OrderingOthersType <em>Ordering Others Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ordering Others Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.OrderingOthersType
	 * @generated
	 */
	EClass getOrderingOthersType();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.OrderingOthersType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.OrderingOthersType#getId()
	 * @see #getOrderingOthersType()
	 * @generated
	 */
	EAttribute getOrderingOthersType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.PartialViewContextFactoryType <em>Partial View Context Factory Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Partial View Context Factory Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PartialViewContextFactoryType
	 * @generated
	 */
	EClass getPartialViewContextFactoryType();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.PartialViewContextFactoryType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PartialViewContextFactoryType#getTextContent()
	 * @see #getPartialViewContextFactoryType()
	 * @generated
	 */
	EAttribute getPartialViewContextFactoryType_TextContent();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.PartialViewContextFactoryType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PartialViewContextFactoryType#getId()
	 * @see #getPartialViewContextFactoryType()
	 * @generated
	 */
	EAttribute getPartialViewContextFactoryType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.PhaseListenerType <em>Phase Listener Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Phase Listener Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PhaseListenerType
	 * @generated
	 */
	EClass getPhaseListenerType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.PhaseListenerType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PhaseListenerType#getTextContent()
	 * @see #getPhaseListenerType()
	 * @generated
	 */
	EAttribute getPhaseListenerType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.PhaseListenerType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PhaseListenerType#getId()
	 * @see #getPhaseListenerType()
	 * @generated
	 */
	EAttribute getPhaseListenerType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyClassType <em>Property Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Class Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyClassType
	 * @generated
	 */
	EClass getPropertyClassType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyClassType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyClassType#getTextContent()
	 * @see #getPropertyClassType()
	 * @generated
	 */
	EAttribute getPropertyClassType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyClassType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyClassType#getId()
	 * @see #getPropertyClassType()
	 * @generated
	 */
	EAttribute getPropertyClassType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyExtensionType <em>Property Extension Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Extension Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyExtensionType
	 * @generated
	 */
	EClass getPropertyExtensionType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType <em>Property Name Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Name Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType
	 * @generated
	 */
	EClass getPropertyNameType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType#getTextContent()
	 * @see #getPropertyNameType()
	 * @generated
	 */
	EAttribute getPropertyNameType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType#getId()
	 * @see #getPropertyNameType()
	 * @generated
	 */
	EAttribute getPropertyNameType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyResolverType <em>Property Resolver Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Resolver Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyResolverType
	 * @generated
	 */
	EClass getPropertyResolverType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyResolverType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyResolverType#getTextContent()
	 * @see #getPropertyResolverType()
	 * @generated
	 */
	EAttribute getPropertyResolverType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyResolverType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyResolverType#getId()
	 * @see #getPropertyResolverType()
	 * @generated
	 */
	EAttribute getPropertyResolverType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyType <em>Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyType
	 * @generated
	 */
	EClass getPropertyType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getDescription()
	 * @see #getPropertyType()
	 * @generated
	 */
	EReference getPropertyType_Description();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getDisplayName()
	 * @see #getPropertyType()
	 * @generated
	 */
	EReference getPropertyType_DisplayName();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getIcon()
	 * @see #getPropertyType()
	 * @generated
	 */
	EReference getPropertyType_Icon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getPropertyName <em>Property Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getPropertyName()
	 * @see #getPropertyType()
	 * @generated
	 */
	EReference getPropertyType_PropertyName();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getPropertyClass <em>Property Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getPropertyClass()
	 * @see #getPropertyType()
	 * @generated
	 */
	EReference getPropertyType_PropertyClass();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Default Value</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getDefaultValue()
	 * @see #getPropertyType()
	 * @generated
	 */
	EReference getPropertyType_DefaultValue();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getSuggestedValue <em>Suggested Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Suggested Value</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getSuggestedValue()
	 * @see #getPropertyType()
	 * @generated
	 */
	EReference getPropertyType_SuggestedValue();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getPropertyExtension <em>Property Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getPropertyExtension()
	 * @see #getPropertyType()
	 * @generated
	 */
	EReference getPropertyType_PropertyExtension();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.PropertyType#getId()
	 * @see #getPropertyType()
	 * @generated
	 */
	EAttribute getPropertyType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.RedirectType <em>Redirect Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Redirect Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RedirectType
	 * @generated
	 */
	EClass getRedirectType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.RedirectType#getViewParam <em>View Param</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>View Param</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RedirectType#getViewParam()
	 * @see #getRedirectType()
	 * @generated
	 */
	EReference getRedirectType_ViewParam();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.RedirectType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RedirectType#getId()
	 * @see #getRedirectType()
	 * @generated
	 */
	EAttribute getRedirectType_Id();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.RedirectType#isIncludeViewParams <em>Include View Params</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Include View Params</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RedirectType#isIncludeViewParams()
	 * @see #getRedirectType()
	 * @generated
	 */
	EAttribute getRedirectType_IncludeViewParams();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.RedirectViewParamType <em>Redirect View Param Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Redirect View Param Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RedirectViewParamType
	 * @generated
	 */
	EClass getRedirectViewParamType();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.RedirectViewParamType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RedirectViewParamType#getName()
	 * @see #getRedirectViewParamType()
	 * @generated
	 */
	EReference getRedirectViewParamType_Name();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.RedirectViewParamType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RedirectViewParamType#getValue()
	 * @see #getRedirectViewParamType()
	 * @generated
	 */
	EReference getRedirectViewParamType_Value();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.RedirectViewParamType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RedirectViewParamType#getId()
	 * @see #getRedirectViewParamType()
	 * @generated
	 */
	EAttribute getRedirectViewParamType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanClassType <em>Referenced Bean Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Referenced Bean Class Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanClassType
	 * @generated
	 */
	EClass getReferencedBeanClassType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanClassType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanClassType#getTextContent()
	 * @see #getReferencedBeanClassType()
	 * @generated
	 */
	EAttribute getReferencedBeanClassType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanClassType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanClassType#getId()
	 * @see #getReferencedBeanClassType()
	 * @generated
	 */
	EAttribute getReferencedBeanClassType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanNameType <em>Referenced Bean Name Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Referenced Bean Name Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanNameType
	 * @generated
	 */
	EClass getReferencedBeanNameType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanNameType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanNameType#getTextContent()
	 * @see #getReferencedBeanNameType()
	 * @generated
	 */
	EAttribute getReferencedBeanNameType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanNameType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanNameType#getId()
	 * @see #getReferencedBeanNameType()
	 * @generated
	 */
	EAttribute getReferencedBeanNameType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType <em>Referenced Bean Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Referenced Bean Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType
	 * @generated
	 */
	EClass getReferencedBeanType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType#getDescription()
	 * @see #getReferencedBeanType()
	 * @generated
	 */
	EReference getReferencedBeanType_Description();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType#getDisplayName()
	 * @see #getReferencedBeanType()
	 * @generated
	 */
	EReference getReferencedBeanType_DisplayName();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType#getIcon()
	 * @see #getReferencedBeanType()
	 * @generated
	 */
	EReference getReferencedBeanType_Icon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType#getReferencedBeanName <em>Referenced Bean Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Referenced Bean Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType#getReferencedBeanName()
	 * @see #getReferencedBeanType()
	 * @generated
	 */
	EReference getReferencedBeanType_ReferencedBeanName();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType#getReferencedBeanClass <em>Referenced Bean Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Referenced Bean Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType#getReferencedBeanClass()
	 * @see #getReferencedBeanType()
	 * @generated
	 */
	EReference getReferencedBeanType_ReferencedBeanClass();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType#getId()
	 * @see #getReferencedBeanType()
	 * @generated
	 */
	EAttribute getReferencedBeanType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererClassType <em>Renderer Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Renderer Class Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererClassType
	 * @generated
	 */
	EClass getRendererClassType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererClassType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererClassType#getTextContent()
	 * @see #getRendererClassType()
	 * @generated
	 */
	EAttribute getRendererClassType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererClassType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererClassType#getId()
	 * @see #getRendererClassType()
	 * @generated
	 */
	EAttribute getRendererClassType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererExtensionType <em>Renderer Extension Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Renderer Extension Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererExtensionType
	 * @generated
	 */
	EClass getRendererExtensionType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType <em>Renderer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Renderer Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererType
	 * @generated
	 */
	EClass getRendererType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererType#getDescription()
	 * @see #getRendererType()
	 * @generated
	 */
	EReference getRendererType_Description();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererType#getDisplayName()
	 * @see #getRendererType()
	 * @generated
	 */
	EReference getRendererType_DisplayName();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererType#getIcon()
	 * @see #getRendererType()
	 * @generated
	 */
	EReference getRendererType_Icon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getComponentFamily <em>Component Family</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component Family</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererType#getComponentFamily()
	 * @see #getRendererType()
	 * @generated
	 */
	EReference getRendererType_ComponentFamily();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getRendererType <em>Renderer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Renderer Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererType#getRendererType()
	 * @see #getRendererType()
	 * @generated
	 */
	EReference getRendererType_RendererType();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getRendererClass <em>Renderer Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Renderer Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererType#getRendererClass()
	 * @see #getRendererType()
	 * @generated
	 */
	EReference getRendererType_RendererClass();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getFacet <em>Facet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Facet</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererType#getFacet()
	 * @see #getRendererType()
	 * @generated
	 */
	EReference getRendererType_Facet();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attribute</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererType#getAttribute()
	 * @see #getRendererType()
	 * @generated
	 */
	EReference getRendererType_Attribute();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getRendererExtension <em>Renderer Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Renderer Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererType#getRendererExtension()
	 * @see #getRendererType()
	 * @generated
	 */
	EReference getRendererType_RendererExtension();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererType#getId()
	 * @see #getRendererType()
	 * @generated
	 */
	EAttribute getRendererType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererTypeType <em>Renderer Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Renderer Type Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererTypeType
	 * @generated
	 */
	EClass getRendererTypeType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererTypeType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererTypeType#getTextContent()
	 * @see #getRendererTypeType()
	 * @generated
	 */
	EAttribute getRendererTypeType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.RendererTypeType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RendererTypeType#getId()
	 * @see #getRendererTypeType()
	 * @generated
	 */
	EAttribute getRendererTypeType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitClassType <em>Render Kit Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Render Kit Class Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitClassType
	 * @generated
	 */
	EClass getRenderKitClassType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitClassType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitClassType#getTextContent()
	 * @see #getRenderKitClassType()
	 * @generated
	 */
	EAttribute getRenderKitClassType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitClassType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitClassType#getId()
	 * @see #getRenderKitClassType()
	 * @generated
	 */
	EAttribute getRenderKitClassType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitFactoryType <em>Render Kit Factory Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Render Kit Factory Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitFactoryType
	 * @generated
	 */
	EClass getRenderKitFactoryType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitFactoryType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitFactoryType#getTextContent()
	 * @see #getRenderKitFactoryType()
	 * @generated
	 */
	EAttribute getRenderKitFactoryType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitFactoryType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitFactoryType#getId()
	 * @see #getRenderKitFactoryType()
	 * @generated
	 */
	EAttribute getRenderKitFactoryType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitIdType <em>Render Kit Id Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Render Kit Id Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitIdType
	 * @generated
	 */
	EClass getRenderKitIdType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitIdType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitIdType#getTextContent()
	 * @see #getRenderKitIdType()
	 * @generated
	 */
	EAttribute getRenderKitIdType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitIdType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitIdType#getId()
	 * @see #getRenderKitIdType()
	 * @generated
	 */
	EAttribute getRenderKitIdType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType <em>Render Kit Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Render Kit Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitType
	 * @generated
	 */
	EClass getRenderKitType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getDescription()
	 * @see #getRenderKitType()
	 * @generated
	 */
	EReference getRenderKitType_Description();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getDisplayName()
	 * @see #getRenderKitType()
	 * @generated
	 */
	EReference getRenderKitType_DisplayName();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getIcon()
	 * @see #getRenderKitType()
	 * @generated
	 */
	EReference getRenderKitType_Icon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getRenderKitId <em>Render Kit Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Render Kit Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getRenderKitId()
	 * @see #getRenderKitType()
	 * @generated
	 */
	EReference getRenderKitType_RenderKitId();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getRenderKitClass <em>Render Kit Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Render Kit Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getRenderKitClass()
	 * @see #getRenderKitType()
	 * @generated
	 */
	EReference getRenderKitType_RenderKitClass();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getRenderer <em>Renderer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Renderer</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getRenderer()
	 * @see #getRenderKitType()
	 * @generated
	 */
	EReference getRenderKitType_Renderer();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getClientBehaviorRenderer <em>Client Behavior Renderer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Client Behavior Renderer</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getClientBehaviorRenderer()
	 * @see #getRenderKitType()
	 * @generated
	 */
	EReference getRenderKitType_ClientBehaviorRenderer();

				/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getRenderKitExtension <em>Render Kit Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Render Kit Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getRenderKitExtension()
	 * @see #getRenderKitType()
	 * @generated
	 */
    EReference getRenderKitType_RenderKitExtension();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitType#getId()
	 * @see #getRenderKitType()
	 * @generated
	 */
	EAttribute getRenderKitType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.SmallIconType <em>Small Icon Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Small Icon Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SmallIconType
	 * @generated
	 */
	EClass getSmallIconType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.SmallIconType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SmallIconType#getTextContent()
	 * @see #getSmallIconType()
	 * @generated
	 */
	EAttribute getSmallIconType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.SmallIconType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SmallIconType#getId()
	 * @see #getSmallIconType()
	 * @generated
	 */
	EAttribute getSmallIconType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.SourceClassType <em>Source Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Source Class Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SourceClassType
	 * @generated
	 */
	EClass getSourceClassType();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.SourceClassType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SourceClassType#getTextContent()
	 * @see #getSourceClassType()
	 * @generated
	 */
	EAttribute getSourceClassType_TextContent();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.SourceClassType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SourceClassType#getId()
	 * @see #getSourceClassType()
	 * @generated
	 */
	EAttribute getSourceClassType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.StateManagerType <em>State Manager Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Manager Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.StateManagerType
	 * @generated
	 */
	EClass getStateManagerType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.StateManagerType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.StateManagerType#getTextContent()
	 * @see #getStateManagerType()
	 * @generated
	 */
	EAttribute getStateManagerType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.StateManagerType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.StateManagerType#getId()
	 * @see #getStateManagerType()
	 * @generated
	 */
	EAttribute getStateManagerType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType <em>Suggested Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Suggested Value Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType
	 * @generated
	 */
	EClass getSuggestedValueType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType#getTextContent()
	 * @see #getSuggestedValueType()
	 * @generated
	 */
	EAttribute getSuggestedValueType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType#getId()
	 * @see #getSuggestedValueType()
	 * @generated
	 */
	EAttribute getSuggestedValueType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.SupportedLocaleType <em>Supported Locale Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Supported Locale Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SupportedLocaleType
	 * @generated
	 */
	EClass getSupportedLocaleType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.SupportedLocaleType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SupportedLocaleType#getTextContent()
	 * @see #getSupportedLocaleType()
	 * @generated
	 */
	EAttribute getSupportedLocaleType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.SupportedLocaleType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SupportedLocaleType#getId()
	 * @see #getSupportedLocaleType()
	 * @generated
	 */
	EAttribute getSupportedLocaleType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventClassType <em>System Event Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Event Class Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SystemEventClassType
	 * @generated
	 */
	EClass getSystemEventClassType();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventClassType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SystemEventClassType#getTextContent()
	 * @see #getSystemEventClassType()
	 * @generated
	 */
	EAttribute getSystemEventClassType_TextContent();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventClassType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SystemEventClassType#getId()
	 * @see #getSystemEventClassType()
	 * @generated
	 */
	EAttribute getSystemEventClassType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerClassType <em>System Event Listener Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Event Listener Class Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerClassType
	 * @generated
	 */
	EClass getSystemEventListenerClassType();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerClassType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerClassType#getTextContent()
	 * @see #getSystemEventListenerClassType()
	 * @generated
	 */
	EAttribute getSystemEventListenerClassType_TextContent();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerClassType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerClassType#getId()
	 * @see #getSystemEventListenerClassType()
	 * @generated
	 */
	EAttribute getSystemEventListenerClassType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType <em>System Event Listener Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Event Listener Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType
	 * @generated
	 */
	EClass getSystemEventListenerType();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType#getSystemEventListenerClass <em>System Event Listener Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>System Event Listener Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType#getSystemEventListenerClass()
	 * @see #getSystemEventListenerType()
	 * @generated
	 */
	EReference getSystemEventListenerType_SystemEventListenerClass();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType#getSystemEventClass <em>System Event Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>System Event Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType#getSystemEventClass()
	 * @see #getSystemEventListenerType()
	 * @generated
	 */
	EReference getSystemEventListenerType_SystemEventClass();

				/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType#getSourceClass <em>Source Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Source Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType#getSourceClass()
	 * @see #getSystemEventListenerType()
	 * @generated
	 */
	EReference getSystemEventListenerType_SourceClass();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.SystemEventListenerType#getId()
	 * @see #getSystemEventListenerType()
	 * @generated
	 */
	EAttribute getSystemEventListenerType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.TagHandlerDelegateFactoryType <em>Tag Handler Delegate Factory Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Handler Delegate Factory Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.TagHandlerDelegateFactoryType
	 * @generated
	 */
	EClass getTagHandlerDelegateFactoryType();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.TagHandlerDelegateFactoryType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.TagHandlerDelegateFactoryType#getTextContent()
	 * @see #getTagHandlerDelegateFactoryType()
	 * @generated
	 */
	EAttribute getTagHandlerDelegateFactoryType_TextContent();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.TagHandlerDelegateFactoryType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.TagHandlerDelegateFactoryType#getId()
	 * @see #getTagHandlerDelegateFactoryType()
	 * @generated
	 */
	EAttribute getTagHandlerDelegateFactoryType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType <em>To View Id Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>To View Id Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType
	 * @generated
	 */
	EClass getToViewIdType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType#getTextContent()
	 * @see #getToViewIdType()
	 * @generated
	 */
	EAttribute getToViewIdType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType#getId()
	 * @see #getToViewIdType()
	 * @generated
	 */
	EAttribute getToViewIdType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorClassType <em>Validator Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validator Class Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValidatorClassType
	 * @generated
	 */
	EClass getValidatorClassType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorClassType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValidatorClassType#getTextContent()
	 * @see #getValidatorClassType()
	 * @generated
	 */
	EAttribute getValidatorClassType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorClassType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValidatorClassType#getId()
	 * @see #getValidatorClassType()
	 * @generated
	 */
	EAttribute getValidatorClassType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorIdType <em>Validator Id Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validator Id Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValidatorIdType
	 * @generated
	 */
	EClass getValidatorIdType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorIdType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValidatorIdType#getTextContent()
	 * @see #getValidatorIdType()
	 * @generated
	 */
	EAttribute getValidatorIdType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorIdType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValidatorIdType#getId()
	 * @see #getValidatorIdType()
	 * @generated
	 */
	EAttribute getValidatorIdType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorType <em>Validator Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validator Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValidatorType
	 * @generated
	 */
	EClass getValidatorType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getDescription()
	 * @see #getValidatorType()
	 * @generated
	 */
	EReference getValidatorType_Description();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getDisplayName()
	 * @see #getValidatorType()
	 * @generated
	 */
	EReference getValidatorType_DisplayName();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getIcon()
	 * @see #getValidatorType()
	 * @generated
	 */
	EReference getValidatorType_Icon();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getValidatorId <em>Validator Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Validator Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getValidatorId()
	 * @see #getValidatorType()
	 * @generated
	 */
	EReference getValidatorType_ValidatorId();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getValidatorClass <em>Validator Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Validator Class</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getValidatorClass()
	 * @see #getValidatorType()
	 * @generated
	 */
	EReference getValidatorType_ValidatorClass();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attribute</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getAttribute()
	 * @see #getValidatorType()
	 * @generated
	 */
	EReference getValidatorType_Attribute();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getProperty()
	 * @see #getValidatorType()
	 * @generated
	 */
	EReference getValidatorType_Property();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getValidatorExtension <em>Validator Extension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Validator Extension</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getValidatorExtension()
	 * @see #getValidatorType()
	 * @generated
	 */
    EReference getValidatorType_ValidatorExtension();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValidatorType#getId()
	 * @see #getValidatorType()
	 * @generated
	 */
	EAttribute getValidatorType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ValueClassType <em>Value Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Class Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValueClassType
	 * @generated
	 */
	EClass getValueClassType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ValueClassType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValueClassType#getTextContent()
	 * @see #getValueClassType()
	 * @generated
	 */
	EAttribute getValueClassType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ValueClassType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValueClassType#getId()
	 * @see #getValueClassType()
	 * @generated
	 */
	EAttribute getValueClassType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ValueType <em>Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValueType
	 * @generated
	 */
	EClass getValueType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ValueType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValueType#getTextContent()
	 * @see #getValueType()
	 * @generated
	 */
	EAttribute getValueType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ValueType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValueType#getId()
	 * @see #getValueType()
	 * @generated
	 */
	EAttribute getValueType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.VariableResolverType <em>Variable Resolver Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Resolver Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.VariableResolverType
	 * @generated
	 */
	EClass getVariableResolverType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.VariableResolverType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.VariableResolverType#getTextContent()
	 * @see #getVariableResolverType()
	 * @generated
	 */
	EAttribute getVariableResolverType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.VariableResolverType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.VariableResolverType#getId()
	 * @see #getVariableResolverType()
	 * @generated
	 */
	EAttribute getVariableResolverType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ViewHandlerType <em>View Handler Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>View Handler Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ViewHandlerType
	 * @generated
	 */
	EClass getViewHandlerType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ViewHandlerType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ViewHandlerType#getTextContent()
	 * @see #getViewHandlerType()
	 * @generated
	 */
	EAttribute getViewHandlerType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ViewHandlerType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ViewHandlerType#getId()
	 * @see #getViewHandlerType()
	 * @generated
	 */
	EAttribute getViewHandlerType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType <em>Resource Bundle Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Bundle Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType
	 * @generated
	 */
    EClass getResourceBundleType();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Description</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getDescription()
	 * @see #getResourceBundleType()
	 * @generated
	 */
    EReference getResourceBundleType_Description();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getDisplayName()
	 * @see #getResourceBundleType()
	 * @generated
	 */
    EReference getResourceBundleType_DisplayName();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getIcon()
	 * @see #getResourceBundleType()
	 * @generated
	 */
    EReference getResourceBundleType_Icon();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getBaseName <em>Base Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getBaseName()
	 * @see #getResourceBundleType()
	 * @generated
	 */
    EReference getResourceBundleType_BaseName();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getVar <em>Var</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Var</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getVar()
	 * @see #getResourceBundleType()
	 * @generated
	 */
    EReference getResourceBundleType_Var();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType#getId()
	 * @see #getResourceBundleType()
	 * @generated
	 */
    EAttribute getResourceBundleType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.BaseNameType <em>Base Name Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Base Name Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BaseNameType
	 * @generated
	 */
    EClass getBaseNameType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.BaseNameType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BaseNameType#getTextContent()
	 * @see #getBaseNameType()
	 * @generated
	 */
    EAttribute getBaseNameType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.BaseNameType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.BaseNameType#getId()
	 * @see #getBaseNameType()
	 * @generated
	 */
    EAttribute getBaseNameType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.VarType <em>Var Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Var Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.VarType
	 * @generated
	 */
    EClass getVarType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.VarType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.VarType#getTextContent()
	 * @see #getVarType()
	 * @generated
	 */
    EAttribute getVarType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.VarType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.VarType#getId()
	 * @see #getVarType()
	 * @generated
	 */
    EAttribute getVarType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ViewDeclarationLanguageFactoryType <em>View Declaration Language Factory Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>View Declaration Language Factory Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ViewDeclarationLanguageFactoryType
	 * @generated
	 */
	EClass getViewDeclarationLanguageFactoryType();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ViewDeclarationLanguageFactoryType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ViewDeclarationLanguageFactoryType#getTextContent()
	 * @see #getViewDeclarationLanguageFactoryType()
	 * @generated
	 */
	EAttribute getViewDeclarationLanguageFactoryType_TextContent();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ViewDeclarationLanguageFactoryType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ViewDeclarationLanguageFactoryType#getId()
	 * @see #getViewDeclarationLanguageFactoryType()
	 * @generated
	 */
	EAttribute getViewDeclarationLanguageFactoryType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.VisitContextFactoryType <em>Visit Context Factory Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Visit Context Factory Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.VisitContextFactoryType
	 * @generated
	 */
	EClass getVisitContextFactoryType();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.VisitContextFactoryType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.VisitContextFactoryType#getTextContent()
	 * @see #getVisitContextFactoryType()
	 * @generated
	 */
	EAttribute getVisitContextFactoryType_TextContent();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.VisitContextFactoryType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.VisitContextFactoryType#getId()
	 * @see #getVisitContextFactoryType()
	 * @generated
	 */
	EAttribute getVisitContextFactoryType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitExtensionType <em>Render Kit Extension Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Render Kit Extension Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.RenderKitExtensionType
	 * @generated
	 */
    EClass getRenderKitExtensionType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceHandlerType <em>Resource Handler Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Handler Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ResourceHandlerType
	 * @generated
	 */
	EClass getResourceHandlerType();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceHandlerType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ResourceHandlerType#getTextContent()
	 * @see #getResourceHandlerType()
	 * @generated
	 */
	EAttribute getResourceHandlerType_TextContent();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ResourceHandlerType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ResourceHandlerType#getId()
	 * @see #getResourceHandlerType()
	 * @generated
	 */
	EAttribute getResourceHandlerType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleExtensionType <em>Navigation Rule Extension Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation Rule Extension Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleExtensionType
	 * @generated
	 */
    EClass getNavigationRuleExtensionType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorExtensionType <em>Validator Extension Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validator Extension Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ValidatorExtensionType
	 * @generated
	 */
    EClass getValidatorExtensionType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigExtensionType <em>Extension Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extension Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigExtensionType
	 * @generated
	 */
    EClass getFacesConfigExtensionType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.FactoryExtensionType <em>Factory Extension Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Factory Extension Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FactoryExtensionType
	 * @generated
	 */
    EClass getFactoryExtensionType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.LifecycleExtensionType <em>Lifecycle Extension Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lifecycle Extension Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.LifecycleExtensionType
	 * @generated
	 */
    EClass getLifecycleExtensionType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanExtensionType <em>Managed Bean Extension Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Managed Bean Extension Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanExtensionType
	 * @generated
	 */
    EClass getManagedBeanExtensionType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ConverterExtensionType <em>Converter Extension Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Converter Extension Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ConverterExtensionType
	 * @generated
	 */
    EClass getConverterExtensionType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ExtensionType <em>Extension Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extension Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ExtensionType
	 * @generated
	 */
    EClass getExtensionType();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.facesconfig.emf.ExtensionType#getChildNodes <em>Child Nodes</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Child Nodes</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ExtensionType#getChildNodes()
	 * @see #getExtensionType()
	 * @generated
	 */
    EReference getExtensionType_ChildNodes();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ExtensionType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ExtensionType#getTextContent()
	 * @see #getExtensionType()
	 * @generated
	 */
    EAttribute getExtensionType_TextContent();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ExtensionType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ExtensionType#getId()
	 * @see #getExtensionType()
	 * @generated
	 */
    EAttribute getExtensionType_Id();

    /**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ExternalContextFactoryType <em>External Context Factory Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Context Factory Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ExternalContextFactoryType
	 * @generated
	 */
	EClass getExternalContextFactoryType();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ExternalContextFactoryType#getTextContent <em>Text Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text Content</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ExternalContextFactoryType#getTextContent()
	 * @see #getExternalContextFactoryType()
	 * @generated
	 */
	EAttribute getExternalContextFactoryType_TextContent();

				/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.emf.ExternalContextFactoryType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ExternalContextFactoryType#getId()
	 * @see #getExternalContextFactoryType()
	 * @generated
	 */
	EAttribute getExternalContextFactoryType_Id();

				/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationExtensionType <em>Application Extension Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application Extension Type</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.ApplicationExtensionType
	 * @generated
	 */
    EClass getApplicationExtensionType();

    /**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	FacesConfigFactory getFacesConfigFactory();

    /**
	 * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
	 * @generated
	 */
//    @SuppressWarnings("hiding")
    interface Literals  {
        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AbsoluteOrderingTypeImpl <em>Absolute Ordering Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.AbsoluteOrderingTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getAbsoluteOrderingType()
		 * @generated
		 */
		EClass ABSOLUTE_ORDERING_TYPE = eINSTANCE.getAbsoluteOrderingType();

					/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSOLUTE_ORDERING_TYPE__NAME = eINSTANCE.getAbsoluteOrderingType_Name();

					/**
		 * The meta object literal for the '<em><b>Others</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSOLUTE_ORDERING_TYPE__OTHERS = eINSTANCE.getAbsoluteOrderingType_Others();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ActionListenerTypeImpl <em>Action Listener Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ActionListenerTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getActionListenerType()
		 * @generated
		 */
        EClass ACTION_LISTENER_TYPE = eINSTANCE.getActionListenerType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ACTION_LISTENER_TYPE__TEXT_CONTENT = eINSTANCE.getActionListenerType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ACTION_LISTENER_TYPE__ID = eINSTANCE.getActionListenerType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationFactoryTypeImpl <em>Application Factory Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationFactoryTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getApplicationFactoryType()
		 * @generated
		 */
        EClass APPLICATION_FACTORY_TYPE = eINSTANCE.getApplicationFactoryType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute APPLICATION_FACTORY_TYPE__TEXT_CONTENT = eINSTANCE.getApplicationFactoryType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute APPLICATION_FACTORY_TYPE__ID = eINSTANCE.getApplicationFactoryType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationTypeImpl <em>Application Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getApplicationType()
		 * @generated
		 */
        EClass APPLICATION_TYPE = eINSTANCE.getApplicationType();

        /**
		 * The meta object literal for the '<em><b>Action Listener</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference APPLICATION_TYPE__ACTION_LISTENER = eINSTANCE.getApplicationType_ActionListener();

        /**
		 * The meta object literal for the '<em><b>Default Render Kit Id</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference APPLICATION_TYPE__DEFAULT_RENDER_KIT_ID = eINSTANCE.getApplicationType_DefaultRenderKitId();

        /**
		 * The meta object literal for the '<em><b>Message Bundle</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference APPLICATION_TYPE__MESSAGE_BUNDLE = eINSTANCE.getApplicationType_MessageBundle();

        /**
		 * The meta object literal for the '<em><b>Navigation Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference APPLICATION_TYPE__NAVIGATION_HANDLER = eINSTANCE.getApplicationType_NavigationHandler();

        /**
		 * The meta object literal for the '<em><b>View Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference APPLICATION_TYPE__VIEW_HANDLER = eINSTANCE.getApplicationType_ViewHandler();

        /**
		 * The meta object literal for the '<em><b>State Manager</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference APPLICATION_TYPE__STATE_MANAGER = eINSTANCE.getApplicationType_StateManager();

        /**
		 * The meta object literal for the '<em><b>Property Resolver</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference APPLICATION_TYPE__PROPERTY_RESOLVER = eINSTANCE.getApplicationType_PropertyResolver();

        /**
		 * The meta object literal for the '<em><b>Variable Resolver</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference APPLICATION_TYPE__VARIABLE_RESOLVER = eINSTANCE.getApplicationType_VariableResolver();

        /**
		 * The meta object literal for the '<em><b>Resource Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_TYPE__RESOURCE_HANDLER = eINSTANCE.getApplicationType_ResourceHandler();

								/**
		 * The meta object literal for the '<em><b>System Event Listener</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_TYPE__SYSTEM_EVENT_LISTENER = eINSTANCE.getApplicationType_SystemEventListener();

								/**
		 * The meta object literal for the '<em><b>Locale Config</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference APPLICATION_TYPE__LOCALE_CONFIG = eINSTANCE.getApplicationType_LocaleConfig();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute APPLICATION_TYPE__ID = eINSTANCE.getApplicationType_Id();

        /**
		 * The meta object literal for the '<em><b>EL Resolver</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference APPLICATION_TYPE__EL_RESOLVER = eINSTANCE.getApplicationType_ELResolver();

        /**
		 * The meta object literal for the '<em><b>Resource Bundle</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference APPLICATION_TYPE__RESOURCE_BUNDLE = eINSTANCE.getApplicationType_ResourceBundle();

        /**
		 * The meta object literal for the '<em><b>Application Extension</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference APPLICATION_TYPE__APPLICATION_EXTENSION = eINSTANCE.getApplicationType_ApplicationExtension();

        /**
		 * The meta object literal for the '<em><b>Default Validators</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_TYPE__DEFAULT_VALIDATORS = eINSTANCE.getApplicationType_DefaultValidators();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeClassTypeImpl <em>Attribute Class Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeClassTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getAttributeClassType()
		 * @generated
		 */
        EClass ATTRIBUTE_CLASS_TYPE = eINSTANCE.getAttributeClassType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ATTRIBUTE_CLASS_TYPE__TEXT_CONTENT = eINSTANCE.getAttributeClassType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ATTRIBUTE_CLASS_TYPE__ID = eINSTANCE.getAttributeClassType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeExtensionTypeImpl <em>Attribute Extension Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeExtensionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getAttributeExtensionType()
		 * @generated
		 */
        EClass ATTRIBUTE_EXTENSION_TYPE = eINSTANCE.getAttributeExtensionType();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeNameTypeImpl <em>Attribute Name Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeNameTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getAttributeNameType()
		 * @generated
		 */
        EClass ATTRIBUTE_NAME_TYPE = eINSTANCE.getAttributeNameType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ATTRIBUTE_NAME_TYPE__TEXT_CONTENT = eINSTANCE.getAttributeNameType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ATTRIBUTE_NAME_TYPE__ID = eINSTANCE.getAttributeNameType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeTypeImpl <em>Attribute Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.AttributeTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getAttributeType()
		 * @generated
		 */
        EClass ATTRIBUTE_TYPE = eINSTANCE.getAttributeType();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference ATTRIBUTE_TYPE__DESCRIPTION = eINSTANCE.getAttributeType_Description();

        /**
		 * The meta object literal for the '<em><b>Display Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference ATTRIBUTE_TYPE__DISPLAY_NAME = eINSTANCE.getAttributeType_DisplayName();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference ATTRIBUTE_TYPE__ICON = eINSTANCE.getAttributeType_Icon();

        /**
		 * The meta object literal for the '<em><b>Attribute Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference ATTRIBUTE_TYPE__ATTRIBUTE_NAME = eINSTANCE.getAttributeType_AttributeName();

        /**
		 * The meta object literal for the '<em><b>Attribute Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference ATTRIBUTE_TYPE__ATTRIBUTE_CLASS = eINSTANCE.getAttributeType_AttributeClass();

        /**
		 * The meta object literal for the '<em><b>Default Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference ATTRIBUTE_TYPE__DEFAULT_VALUE = eINSTANCE.getAttributeType_DefaultValue();

        /**
		 * The meta object literal for the '<em><b>Suggested Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference ATTRIBUTE_TYPE__SUGGESTED_VALUE = eINSTANCE.getAttributeType_SuggestedValue();

        /**
		 * The meta object literal for the '<em><b>Attribute Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference ATTRIBUTE_TYPE__ATTRIBUTE_EXTENSION = eINSTANCE.getAttributeType_AttributeExtension();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ATTRIBUTE_TYPE__ID = eINSTANCE.getAttributeType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorClassTypeImpl <em>Behavior Class Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorClassTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getBehaviorClassType()
		 * @generated
		 */
		EClass BEHAVIOR_CLASS_TYPE = eINSTANCE.getBehaviorClassType();

								/**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEHAVIOR_CLASS_TYPE__TEXT_CONTENT = eINSTANCE.getBehaviorClassType_TextContent();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEHAVIOR_CLASS_TYPE__ID = eINSTANCE.getBehaviorClassType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorIdTypeImpl <em>Behavior Id Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorIdTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getBehaviorIdType()
		 * @generated
		 */
		EClass BEHAVIOR_ID_TYPE = eINSTANCE.getBehaviorIdType();

								/**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEHAVIOR_ID_TYPE__TEXT_CONTENT = eINSTANCE.getBehaviorIdType_TextContent();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEHAVIOR_ID_TYPE__ID = eINSTANCE.getBehaviorIdType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorTypeImpl <em>Behavior Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getBehaviorType()
		 * @generated
		 */
		EClass BEHAVIOR_TYPE = eINSTANCE.getBehaviorType();

								/**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR_TYPE__DESCRIPTION = eINSTANCE.getBehaviorType_Description();

								/**
		 * The meta object literal for the '<em><b>Display Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR_TYPE__DISPLAY_NAME = eINSTANCE.getBehaviorType_DisplayName();

								/**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR_TYPE__ICON = eINSTANCE.getBehaviorType_Icon();

								/**
		 * The meta object literal for the '<em><b>Behavior Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR_TYPE__BEHAVIOR_ID = eINSTANCE.getBehaviorType_BehaviorId();

								/**
		 * The meta object literal for the '<em><b>Behavior Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR_TYPE__BEHAVIOR_CLASS = eINSTANCE.getBehaviorType_BehaviorClass();

								/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR_TYPE__ATTRIBUTE = eINSTANCE.getBehaviorType_Attribute();

								/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR_TYPE__PROPERTY = eINSTANCE.getBehaviorType_Property();

								/**
		 * The meta object literal for the '<em><b>Behavior Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR_TYPE__BEHAVIOR_EXTENSION = eINSTANCE.getBehaviorType_BehaviorExtension();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorExtensionTypeImpl <em>Behavior Extension Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.BehaviorExtensionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getBehaviorExtensionType()
		 * @generated
		 */
		EClass BEHAVIOR_EXTENSION_TYPE = eINSTANCE.getBehaviorExtensionType();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ClientBehaviorRendererClassTypeImpl <em>Client Behavior Renderer Class Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ClientBehaviorRendererClassTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getClientBehaviorRendererClassType()
		 * @generated
		 */
		EClass CLIENT_BEHAVIOR_RENDERER_CLASS_TYPE = eINSTANCE.getClientBehaviorRendererClassType();

								/**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLIENT_BEHAVIOR_RENDERER_CLASS_TYPE__TEXT_CONTENT = eINSTANCE.getClientBehaviorRendererClassType_TextContent();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLIENT_BEHAVIOR_RENDERER_CLASS_TYPE__ID = eINSTANCE.getClientBehaviorRendererClassType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ClientBehaviorRendererTypeImpl <em>Client Behavior Renderer Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ClientBehaviorRendererTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getClientBehaviorRendererType()
		 * @generated
		 */
		EClass CLIENT_BEHAVIOR_RENDERER_TYPE = eINSTANCE.getClientBehaviorRendererType();

								/**
		 * The meta object literal for the '<em><b>Client Behavior Renderer Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_TYPE = eINSTANCE.getClientBehaviorRendererType_ClientBehaviorRendererType();

								/**
		 * The meta object literal for the '<em><b>Client Behavior Renderer Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLIENT_BEHAVIOR_RENDERER_TYPE__CLIENT_BEHAVIOR_RENDERER_CLASS = eINSTANCE.getClientBehaviorRendererType_ClientBehaviorRendererClass();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ClientBehaviorRendererTypeTypeImpl <em>Client Behavior Renderer Type Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ClientBehaviorRendererTypeTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getClientBehaviorRendererTypeType()
		 * @generated
		 */
		EClass CLIENT_BEHAVIOR_RENDERER_TYPE_TYPE = eINSTANCE.getClientBehaviorRendererTypeType();

								/**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLIENT_BEHAVIOR_RENDERER_TYPE_TYPE__TEXT_CONTENT = eINSTANCE.getClientBehaviorRendererTypeType_TextContent();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLIENT_BEHAVIOR_RENDERER_TYPE_TYPE__ID = eINSTANCE.getClientBehaviorRendererTypeType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentClassTypeImpl <em>Component Class Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentClassTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getComponentClassType()
		 * @generated
		 */
        EClass COMPONENT_CLASS_TYPE = eINSTANCE.getComponentClassType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute COMPONENT_CLASS_TYPE__TEXT_CONTENT = eINSTANCE.getComponentClassType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute COMPONENT_CLASS_TYPE__ID = eINSTANCE.getComponentClassType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentExtensionTypeImpl <em>Component Extension Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentExtensionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getComponentExtensionType()
		 * @generated
		 */
        EClass COMPONENT_EXTENSION_TYPE = eINSTANCE.getComponentExtensionType();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentFamilyTypeImpl <em>Component Family Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentFamilyTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getComponentFamilyType()
		 * @generated
		 */
        EClass COMPONENT_FAMILY_TYPE = eINSTANCE.getComponentFamilyType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute COMPONENT_FAMILY_TYPE__TEXT_CONTENT = eINSTANCE.getComponentFamilyType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute COMPONENT_FAMILY_TYPE__ID = eINSTANCE.getComponentFamilyType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeImpl <em>Component Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getComponentType()
		 * @generated
		 */
        EClass COMPONENT_TYPE = eINSTANCE.getComponentType();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_TYPE__DESCRIPTION = eINSTANCE.getComponentType_Description();

        /**
		 * The meta object literal for the '<em><b>Display Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference COMPONENT_TYPE__DISPLAY_NAME = eINSTANCE.getComponentType_DisplayName();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference COMPONENT_TYPE__ICON = eINSTANCE.getComponentType_Icon();

        /**
		 * The meta object literal for the '<em><b>Component Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference COMPONENT_TYPE__COMPONENT_TYPE = eINSTANCE.getComponentType_ComponentType();

        /**
		 * The meta object literal for the '<em><b>Component Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference COMPONENT_TYPE__COMPONENT_CLASS = eINSTANCE.getComponentType_ComponentClass();

        /**
		 * The meta object literal for the '<em><b>Facet</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference COMPONENT_TYPE__FACET = eINSTANCE.getComponentType_Facet();

        /**
		 * The meta object literal for the '<em><b>Attribute</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference COMPONENT_TYPE__ATTRIBUTE = eINSTANCE.getComponentType_Attribute();

        /**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference COMPONENT_TYPE__PROPERTY = eINSTANCE.getComponentType_Property();

        /**
		 * The meta object literal for the '<em><b>Component Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference COMPONENT_TYPE__COMPONENT_EXTENSION = eINSTANCE.getComponentType_ComponentExtension();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute COMPONENT_TYPE__ID = eINSTANCE.getComponentType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeTypeImpl <em>Component Type Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ComponentTypeTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getComponentTypeType()
		 * @generated
		 */
        EClass COMPONENT_TYPE_TYPE = eINSTANCE.getComponentTypeType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute COMPONENT_TYPE_TYPE__TEXT_CONTENT = eINSTANCE.getComponentTypeType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute COMPONENT_TYPE_TYPE__ID = eINSTANCE.getComponentTypeType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterClassTypeImpl <em>Converter Class Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterClassTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getConverterClassType()
		 * @generated
		 */
        EClass CONVERTER_CLASS_TYPE = eINSTANCE.getConverterClassType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONVERTER_CLASS_TYPE__TEXT_CONTENT = eINSTANCE.getConverterClassType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONVERTER_CLASS_TYPE__ID = eINSTANCE.getConverterClassType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterForClassTypeImpl <em>Converter For Class Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterForClassTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getConverterForClassType()
		 * @generated
		 */
        EClass CONVERTER_FOR_CLASS_TYPE = eINSTANCE.getConverterForClassType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONVERTER_FOR_CLASS_TYPE__TEXT_CONTENT = eINSTANCE.getConverterForClassType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONVERTER_FOR_CLASS_TYPE__ID = eINSTANCE.getConverterForClassType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterIdTypeImpl <em>Converter Id Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterIdTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getConverterIdType()
		 * @generated
		 */
        EClass CONVERTER_ID_TYPE = eINSTANCE.getConverterIdType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONVERTER_ID_TYPE__TEXT_CONTENT = eINSTANCE.getConverterIdType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONVERTER_ID_TYPE__ID = eINSTANCE.getConverterIdType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterTypeImpl <em>Converter Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getConverterType()
		 * @generated
		 */
        EClass CONVERTER_TYPE = eINSTANCE.getConverterType();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference CONVERTER_TYPE__DESCRIPTION = eINSTANCE.getConverterType_Description();

        /**
		 * The meta object literal for the '<em><b>Display Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference CONVERTER_TYPE__DISPLAY_NAME = eINSTANCE.getConverterType_DisplayName();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference CONVERTER_TYPE__ICON = eINSTANCE.getConverterType_Icon();

        /**
		 * The meta object literal for the '<em><b>Converter Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference CONVERTER_TYPE__CONVERTER_ID = eINSTANCE.getConverterType_ConverterId();

        /**
		 * The meta object literal for the '<em><b>Converter For Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference CONVERTER_TYPE__CONVERTER_FOR_CLASS = eINSTANCE.getConverterType_ConverterForClass();

        /**
		 * The meta object literal for the '<em><b>Converter Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference CONVERTER_TYPE__CONVERTER_CLASS = eINSTANCE.getConverterType_ConverterClass();

        /**
		 * The meta object literal for the '<em><b>Attribute</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference CONVERTER_TYPE__ATTRIBUTE = eINSTANCE.getConverterType_Attribute();

        /**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference CONVERTER_TYPE__PROPERTY = eINSTANCE.getConverterType_Property();

        /**
		 * The meta object literal for the '<em><b>Converter Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference CONVERTER_TYPE__CONVERTER_EXTENSION = eINSTANCE.getConverterType_ConverterExtension();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONVERTER_TYPE__ID = eINSTANCE.getConverterType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DefaultLocaleTypeImpl <em>Default Locale Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DefaultLocaleTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDefaultLocaleType()
		 * @generated
		 */
        EClass DEFAULT_LOCALE_TYPE = eINSTANCE.getDefaultLocaleType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DEFAULT_LOCALE_TYPE__TEXT_CONTENT = eINSTANCE.getDefaultLocaleType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DEFAULT_LOCALE_TYPE__ID = eINSTANCE.getDefaultLocaleType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DefaultRenderKitIdTypeImpl <em>Default Render Kit Id Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DefaultRenderKitIdTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDefaultRenderKitIdType()
		 * @generated
		 */
        EClass DEFAULT_RENDER_KIT_ID_TYPE = eINSTANCE.getDefaultRenderKitIdType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DEFAULT_RENDER_KIT_ID_TYPE__TEXT_CONTENT = eINSTANCE.getDefaultRenderKitIdType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DEFAULT_RENDER_KIT_ID_TYPE__ID = eINSTANCE.getDefaultRenderKitIdType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DefaultValidatorsTypeImpl <em>Default Validators Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DefaultValidatorsTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDefaultValidatorsType()
		 * @generated
		 */
		EClass DEFAULT_VALIDATORS_TYPE = eINSTANCE.getDefaultValidatorsType();

								/**
		 * The meta object literal for the '<em><b>Validator Id</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEFAULT_VALIDATORS_TYPE__VALIDATOR_ID = eINSTANCE.getDefaultValidatorsType_ValidatorId();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEFAULT_VALIDATORS_TYPE__ID = eINSTANCE.getDefaultValidatorsType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DefaultValueTypeImpl <em>Default Value Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DefaultValueTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDefaultValueType()
		 * @generated
		 */
        EClass DEFAULT_VALUE_TYPE = eINSTANCE.getDefaultValueType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DEFAULT_VALUE_TYPE__TEXT_CONTENT = eINSTANCE.getDefaultValueType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DEFAULT_VALUE_TYPE__ID = eINSTANCE.getDefaultValueType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DescriptionTypeImpl <em>Description Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DescriptionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDescriptionType()
		 * @generated
		 */
        EClass DESCRIPTION_TYPE = eINSTANCE.getDescriptionType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DESCRIPTION_TYPE__TEXT_CONTENT = eINSTANCE.getDescriptionType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Lang</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DESCRIPTION_TYPE__LANG = eINSTANCE.getDescriptionType_Lang();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DESCRIPTION_TYPE__ID = eINSTANCE.getDescriptionType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DisplayNameTypeImpl <em>Display Name Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DisplayNameTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDisplayNameType()
		 * @generated
		 */
        EClass DISPLAY_NAME_TYPE = eINSTANCE.getDisplayNameType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DISPLAY_NAME_TYPE__TEXT_CONTENT = eINSTANCE.getDisplayNameType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Lang</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DISPLAY_NAME_TYPE__LANG = eINSTANCE.getDisplayNameType_Lang();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DISPLAY_NAME_TYPE__ID = eINSTANCE.getDisplayNameType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DocumentRootImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDocumentRoot()
		 * @generated
		 */
        EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

        /**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

        /**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

        /**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

        /**
		 * The meta object literal for the '<em><b>Absolute Ordering</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ABSOLUTE_ORDERING = eINSTANCE.getDocumentRoot_AbsoluteOrdering();

								/**
		 * The meta object literal for the '<em><b>Action Listener</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__ACTION_LISTENER = eINSTANCE.getDocumentRoot_ActionListener();

        /**
		 * The meta object literal for the '<em><b>Application</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__APPLICATION = eINSTANCE.getDocumentRoot_Application();

        /**
		 * The meta object literal for the '<em><b>Application Factory</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__APPLICATION_FACTORY = eINSTANCE.getDocumentRoot_ApplicationFactory();

        /**
		 * The meta object literal for the '<em><b>Attribute</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__ATTRIBUTE = eINSTANCE.getDocumentRoot_Attribute();

        /**
		 * The meta object literal for the '<em><b>Attribute Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__ATTRIBUTE_CLASS = eINSTANCE.getDocumentRoot_AttributeClass();

        /**
		 * The meta object literal for the '<em><b>Attribute Extension</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__ATTRIBUTE_EXTENSION = eINSTANCE.getDocumentRoot_AttributeExtension();

        /**
		 * The meta object literal for the '<em><b>Attribute Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__ATTRIBUTE_NAME = eINSTANCE.getDocumentRoot_AttributeName();

        /**
		 * The meta object literal for the '<em><b>Behavior</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__BEHAVIOR = eINSTANCE.getDocumentRoot_Behavior();

								/**
		 * The meta object literal for the '<em><b>Behavior Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__BEHAVIOR_CLASS = eINSTANCE.getDocumentRoot_BehaviorClass();

								/**
		 * The meta object literal for the '<em><b>Behavior Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__BEHAVIOR_ID = eINSTANCE.getDocumentRoot_BehaviorId();

								/**
		 * The meta object literal for the '<em><b>Behavior Extension</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__BEHAVIOR_EXTENSION = eINSTANCE.getDocumentRoot_BehaviorExtension();

								/**
		 * The meta object literal for the '<em><b>Component</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__COMPONENT = eINSTANCE.getDocumentRoot_Component();

        /**
		 * The meta object literal for the '<em><b>Component Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__COMPONENT_CLASS = eINSTANCE.getDocumentRoot_ComponentClass();

        /**
		 * The meta object literal for the '<em><b>Component Extension</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__COMPONENT_EXTENSION = eINSTANCE.getDocumentRoot_ComponentExtension();

        /**
		 * The meta object literal for the '<em><b>Component Family</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__COMPONENT_FAMILY = eINSTANCE.getDocumentRoot_ComponentFamily();

        /**
		 * The meta object literal for the '<em><b>Component Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__COMPONENT_TYPE = eINSTANCE.getDocumentRoot_ComponentType();

        /**
		 * The meta object literal for the '<em><b>Converter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__CONVERTER = eINSTANCE.getDocumentRoot_Converter();

        /**
		 * The meta object literal for the '<em><b>Converter Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__CONVERTER_CLASS = eINSTANCE.getDocumentRoot_ConverterClass();

        /**
		 * The meta object literal for the '<em><b>Converter For Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__CONVERTER_FOR_CLASS = eINSTANCE.getDocumentRoot_ConverterForClass();

        /**
		 * The meta object literal for the '<em><b>Converter Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__CONVERTER_ID = eINSTANCE.getDocumentRoot_ConverterId();

        /**
		 * The meta object literal for the '<em><b>Default Locale</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__DEFAULT_LOCALE = eINSTANCE.getDocumentRoot_DefaultLocale();

        /**
		 * The meta object literal for the '<em><b>Default Render Kit Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__DEFAULT_RENDER_KIT_ID = eINSTANCE.getDocumentRoot_DefaultRenderKitId();

        /**
		 * The meta object literal for the '<em><b>Default Validators</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DEFAULT_VALIDATORS = eINSTANCE.getDocumentRoot_DefaultValidators();

								/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__DEFAULT_VALUE = eINSTANCE.getDocumentRoot_DefaultValue();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__DESCRIPTION = eINSTANCE.getDocumentRoot_Description();

        /**
		 * The meta object literal for the '<em><b>Display Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__DISPLAY_NAME = eINSTANCE.getDocumentRoot_DisplayName();

        /**
		 * The meta object literal for the '<em><b>Exception Handler Factory</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EXCEPTION_HANDLER_FACTORY = eINSTANCE.getDocumentRoot_ExceptionHandlerFactory();

								/**
		 * The meta object literal for the '<em><b>External Context Factory</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EXTERNAL_CONTEXT_FACTORY = eINSTANCE.getDocumentRoot_ExternalContextFactory();

								/**
		 * The meta object literal for the '<em><b>Faces Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__FACES_CONFIG = eINSTANCE.getDocumentRoot_FacesConfig();

        /**
		 * The meta object literal for the '<em><b>Faces Context Factory</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__FACES_CONTEXT_FACTORY = eINSTANCE.getDocumentRoot_FacesContextFactory();

        /**
		 * The meta object literal for the '<em><b>Facet</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__FACET = eINSTANCE.getDocumentRoot_Facet();

        /**
		 * The meta object literal for the '<em><b>Facet Extension</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__FACET_EXTENSION = eINSTANCE.getDocumentRoot_FacetExtension();

        /**
		 * The meta object literal for the '<em><b>Facet Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__FACET_NAME = eINSTANCE.getDocumentRoot_FacetName();

        /**
		 * The meta object literal for the '<em><b>Factory</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__FACTORY = eINSTANCE.getDocumentRoot_Factory();

        /**
		 * The meta object literal for the '<em><b>From Action</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__FROM_ACTION = eINSTANCE.getDocumentRoot_FromAction();

        /**
		 * The meta object literal for the '<em><b>From Outcome</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__FROM_OUTCOME = eINSTANCE.getDocumentRoot_FromOutcome();

        /**
		 * The meta object literal for the '<em><b>From View Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__FROM_VIEW_ID = eINSTANCE.getDocumentRoot_FromViewId();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__ICON = eINSTANCE.getDocumentRoot_Icon();

        /**
		 * The meta object literal for the '<em><b>If</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__IF = eINSTANCE.getDocumentRoot_If();

								/**
		 * The meta object literal for the '<em><b>Key</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__KEY = eINSTANCE.getDocumentRoot_Key();

        /**
		 * The meta object literal for the '<em><b>Key Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__KEY_CLASS = eINSTANCE.getDocumentRoot_KeyClass();

        /**
		 * The meta object literal for the '<em><b>Large Icon</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__LARGE_ICON = eINSTANCE.getDocumentRoot_LargeIcon();

        /**
		 * The meta object literal for the '<em><b>Lifecycle</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__LIFECYCLE = eINSTANCE.getDocumentRoot_Lifecycle();

        /**
		 * The meta object literal for the '<em><b>Lifecycle Factory</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__LIFECYCLE_FACTORY = eINSTANCE.getDocumentRoot_LifecycleFactory();

        /**
		 * The meta object literal for the '<em><b>List Entries</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__LIST_ENTRIES = eINSTANCE.getDocumentRoot_ListEntries();

        /**
		 * The meta object literal for the '<em><b>Locale Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__LOCALE_CONFIG = eINSTANCE.getDocumentRoot_LocaleConfig();

        /**
		 * The meta object literal for the '<em><b>Managed Bean</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__MANAGED_BEAN = eINSTANCE.getDocumentRoot_ManagedBean();

        /**
		 * The meta object literal for the '<em><b>Managed Bean Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__MANAGED_BEAN_CLASS = eINSTANCE.getDocumentRoot_ManagedBeanClass();

        /**
		 * The meta object literal for the '<em><b>Managed Bean Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__MANAGED_BEAN_NAME = eINSTANCE.getDocumentRoot_ManagedBeanName();

        /**
		 * The meta object literal for the '<em><b>Managed Bean Scope</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__MANAGED_BEAN_SCOPE = eINSTANCE.getDocumentRoot_ManagedBeanScope();

        /**
		 * The meta object literal for the '<em><b>Managed Property</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__MANAGED_PROPERTY = eINSTANCE.getDocumentRoot_ManagedProperty();

        /**
		 * The meta object literal for the '<em><b>Map Entries</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__MAP_ENTRIES = eINSTANCE.getDocumentRoot_MapEntries();

        /**
		 * The meta object literal for the '<em><b>Map Entry</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__MAP_ENTRY = eINSTANCE.getDocumentRoot_MapEntry();

        /**
		 * The meta object literal for the '<em><b>Message Bundle</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__MESSAGE_BUNDLE = eINSTANCE.getDocumentRoot_MessageBundle();

        /**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__NAME = eINSTANCE.getDocumentRoot_Name();

								/**
		 * The meta object literal for the '<em><b>Navigation Case</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__NAVIGATION_CASE = eINSTANCE.getDocumentRoot_NavigationCase();

        /**
		 * The meta object literal for the '<em><b>Navigation Handler</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__NAVIGATION_HANDLER = eINSTANCE.getDocumentRoot_NavigationHandler();

        /**
		 * The meta object literal for the '<em><b>Navigation Rule</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__NAVIGATION_RULE = eINSTANCE.getDocumentRoot_NavigationRule();

        /**
		 * The meta object literal for the '<em><b>Null Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__NULL_VALUE = eINSTANCE.getDocumentRoot_NullValue();

        /**
		 * The meta object literal for the '<em><b>Ordering</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ORDERING = eINSTANCE.getDocumentRoot_Ordering();

								/**
		 * The meta object literal for the '<em><b>Ordering Ordering</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ORDERING_ORDERING = eINSTANCE.getDocumentRoot_OrderingOrdering();

								/**
		 * The meta object literal for the '<em><b>Others</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__OTHERS = eINSTANCE.getDocumentRoot_Others();

								/**
		 * The meta object literal for the '<em><b>Partial View Context Factory</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PARTIAL_VIEW_CONTEXT_FACTORY = eINSTANCE.getDocumentRoot_PartialViewContextFactory();

								/**
		 * The meta object literal for the '<em><b>Phase Listener</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__PHASE_LISTENER = eINSTANCE.getDocumentRoot_PhaseListener();

        /**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__PROPERTY = eINSTANCE.getDocumentRoot_Property();

        /**
		 * The meta object literal for the '<em><b>Property Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__PROPERTY_CLASS = eINSTANCE.getDocumentRoot_PropertyClass();

        /**
		 * The meta object literal for the '<em><b>Property Extension</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__PROPERTY_EXTENSION = eINSTANCE.getDocumentRoot_PropertyExtension();

        /**
		 * The meta object literal for the '<em><b>Property Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__PROPERTY_NAME = eINSTANCE.getDocumentRoot_PropertyName();

        /**
		 * The meta object literal for the '<em><b>Property Resolver</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__PROPERTY_RESOLVER = eINSTANCE.getDocumentRoot_PropertyResolver();

        /**
		 * The meta object literal for the '<em><b>Redirect</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__REDIRECT = eINSTANCE.getDocumentRoot_Redirect();

        /**
		 * The meta object literal for the '<em><b>Redirect View Param</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__REDIRECT_VIEW_PARAM = eINSTANCE.getDocumentRoot_RedirectViewParam();

								/**
		 * The meta object literal for the '<em><b>Referenced Bean</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__REFERENCED_BEAN = eINSTANCE.getDocumentRoot_ReferencedBean();

        /**
		 * The meta object literal for the '<em><b>Referenced Bean Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__REFERENCED_BEAN_CLASS = eINSTANCE.getDocumentRoot_ReferencedBeanClass();

        /**
		 * The meta object literal for the '<em><b>Referenced Bean Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__REFERENCED_BEAN_NAME = eINSTANCE.getDocumentRoot_ReferencedBeanName();

        /**
		 * The meta object literal for the '<em><b>Renderer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__RENDERER = eINSTANCE.getDocumentRoot_Renderer();

        /**
		 * The meta object literal for the '<em><b>Renderer Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__RENDERER_CLASS = eINSTANCE.getDocumentRoot_RendererClass();

        /**
		 * The meta object literal for the '<em><b>Renderer Extension</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__RENDERER_EXTENSION = eINSTANCE.getDocumentRoot_RendererExtension();

        /**
		 * The meta object literal for the '<em><b>Renderer Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__RENDERER_TYPE = eINSTANCE.getDocumentRoot_RendererType();

        /**
		 * The meta object literal for the '<em><b>Render Kit</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__RENDER_KIT = eINSTANCE.getDocumentRoot_RenderKit();

        /**
		 * The meta object literal for the '<em><b>Render Kit Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__RENDER_KIT_CLASS = eINSTANCE.getDocumentRoot_RenderKitClass();

        /**
		 * The meta object literal for the '<em><b>Render Kit Factory</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__RENDER_KIT_FACTORY = eINSTANCE.getDocumentRoot_RenderKitFactory();

        /**
		 * The meta object literal for the '<em><b>Render Kit Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__RENDER_KIT_ID = eINSTANCE.getDocumentRoot_RenderKitId();

        /**
		 * The meta object literal for the '<em><b>Resource Handler</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RESOURCE_HANDLER = eINSTANCE.getDocumentRoot_ResourceHandler();

								/**
		 * The meta object literal for the '<em><b>Small Icon</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__SMALL_ICON = eINSTANCE.getDocumentRoot_SmallIcon();

        /**
		 * The meta object literal for the '<em><b>Source Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SOURCE_CLASS = eINSTANCE.getDocumentRoot_SourceClass();

								/**
		 * The meta object literal for the '<em><b>State Manager</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__STATE_MANAGER = eINSTANCE.getDocumentRoot_StateManager();

        /**
		 * The meta object literal for the '<em><b>Suggested Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__SUGGESTED_VALUE = eINSTANCE.getDocumentRoot_SuggestedValue();

        /**
		 * The meta object literal for the '<em><b>Supported Locale</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__SUPPORTED_LOCALE = eINSTANCE.getDocumentRoot_SupportedLocale();

        /**
		 * The meta object literal for the '<em><b>System Event Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SYSTEM_EVENT_CLASS = eINSTANCE.getDocumentRoot_SystemEventClass();

								/**
		 * The meta object literal for the '<em><b>System Event Listener</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SYSTEM_EVENT_LISTENER = eINSTANCE.getDocumentRoot_SystemEventListener();

								/**
		 * The meta object literal for the '<em><b>System Event Listener Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SYSTEM_EVENT_LISTENER_CLASS = eINSTANCE.getDocumentRoot_SystemEventListenerClass();

								/**
		 * The meta object literal for the '<em><b>Tag Handler Delegate Factory</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TAG_HANDLER_DELEGATE_FACTORY = eINSTANCE.getDocumentRoot_TagHandlerDelegateFactory();

								/**
		 * The meta object literal for the '<em><b>To View Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__TO_VIEW_ID = eINSTANCE.getDocumentRoot_ToViewId();

        /**
		 * The meta object literal for the '<em><b>Validator</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__VALIDATOR = eINSTANCE.getDocumentRoot_Validator();

        /**
		 * The meta object literal for the '<em><b>Validator Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__VALIDATOR_CLASS = eINSTANCE.getDocumentRoot_ValidatorClass();

        /**
		 * The meta object literal for the '<em><b>Validator Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__VALIDATOR_ID = eINSTANCE.getDocumentRoot_ValidatorId();

        /**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__VALUE = eINSTANCE.getDocumentRoot_Value();

        /**
		 * The meta object literal for the '<em><b>Value Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__VALUE_CLASS = eINSTANCE.getDocumentRoot_ValueClass();

        /**
		 * The meta object literal for the '<em><b>Variable Resolver</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__VARIABLE_RESOLVER = eINSTANCE.getDocumentRoot_VariableResolver();

        /**
		 * The meta object literal for the '<em><b>View Declaration Language Factory</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__VIEW_DECLARATION_LANGUAGE_FACTORY = eINSTANCE.getDocumentRoot_ViewDeclarationLanguageFactory();

								/**
		 * The meta object literal for the '<em><b>View Handler</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__VIEW_HANDLER = eINSTANCE.getDocumentRoot_ViewHandler();

        /**
		 * The meta object literal for the '<em><b>Visit Context Factory</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__VISIT_CONTEXT_FACTORY = eINSTANCE.getDocumentRoot_VisitContextFactory();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DynamicAttributeImpl <em>Dynamic Attribute</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DynamicAttributeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDynamicAttribute()
		 * @generated
		 */
        EClass DYNAMIC_ATTRIBUTE = eINSTANCE.getDynamicAttribute();

        /**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DYNAMIC_ATTRIBUTE__NAME = eINSTANCE.getDynamicAttribute_Name();

        /**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DYNAMIC_ATTRIBUTE__VALUE = eINSTANCE.getDynamicAttribute_Value();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DynamicElementImpl <em>Dynamic Element</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.DynamicElementImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getDynamicElement()
		 * @generated
		 */
        EClass DYNAMIC_ELEMENT = eINSTANCE.getDynamicElement();

        /**
		 * The meta object literal for the '<em><b>Child Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DYNAMIC_ELEMENT__CHILD_NODES = eINSTANCE.getDynamicElement_ChildNodes();

        /**
		 * The meta object literal for the '<em><b>Attributes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DYNAMIC_ELEMENT__ATTRIBUTES = eINSTANCE.getDynamicElement_Attributes();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DYNAMIC_ELEMENT__TEXT_CONTENT = eINSTANCE.getDynamicElement_TextContent();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ELResolverTypeImpl <em>EL Resolver Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ELResolverTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getELResolverType()
		 * @generated
		 */
        EClass EL_RESOLVER_TYPE = eINSTANCE.getELResolverType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute EL_RESOLVER_TYPE__TEXT_CONTENT = eINSTANCE.getELResolverType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute EL_RESOLVER_TYPE__ID = eINSTANCE.getELResolverType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ExceptionHandlerFactoryTypeImpl <em>Exception Handler Factory Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ExceptionHandlerFactoryTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getExceptionHandlerFactoryType()
		 * @generated
		 */
		EClass EXCEPTION_HANDLER_FACTORY_TYPE = eINSTANCE.getExceptionHandlerFactoryType();

								/**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXCEPTION_HANDLER_FACTORY_TYPE__TEXT_CONTENT = eINSTANCE.getExceptionHandlerFactoryType_TextContent();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXCEPTION_HANDLER_FACTORY_TYPE__ID = eINSTANCE.getExceptionHandlerFactoryType_Id();

								/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DYNAMIC_ELEMENT__NAME = eINSTANCE.getDynamicElement_Name();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl <em>Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFacesConfigType()
		 * @generated
		 */
        EClass FACES_CONFIG_TYPE = eINSTANCE.getFacesConfigType();

        /**
		 * The meta object literal for the '<em><b>Application</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACES_CONFIG_TYPE__APPLICATION = eINSTANCE.getFacesConfigType_Application();

        /**
		 * The meta object literal for the '<em><b>Ordering</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACES_CONFIG_TYPE__ORDERING = eINSTANCE.getFacesConfigType_Ordering();

								/**
		 * The meta object literal for the '<em><b>Absolute Ordering</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACES_CONFIG_TYPE__ABSOLUTE_ORDERING = eINSTANCE.getFacesConfigType_AbsoluteOrdering();

								/**
		 * The meta object literal for the '<em><b>Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACES_CONFIG_TYPE__FACTORY = eINSTANCE.getFacesConfigType_Factory();

        /**
		 * The meta object literal for the '<em><b>Component</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACES_CONFIG_TYPE__COMPONENT = eINSTANCE.getFacesConfigType_Component();

        /**
		 * The meta object literal for the '<em><b>Converter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACES_CONFIG_TYPE__CONVERTER = eINSTANCE.getFacesConfigType_Converter();

        /**
		 * The meta object literal for the '<em><b>Managed Bean</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACES_CONFIG_TYPE__MANAGED_BEAN = eINSTANCE.getFacesConfigType_ManagedBean();

        /**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACES_CONFIG_TYPE__NAME = eINSTANCE.getFacesConfigType_Name();

								/**
		 * The meta object literal for the '<em><b>Navigation Rule</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACES_CONFIG_TYPE__NAVIGATION_RULE = eINSTANCE.getFacesConfigType_NavigationRule();

        /**
		 * The meta object literal for the '<em><b>Referenced Bean</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACES_CONFIG_TYPE__REFERENCED_BEAN = eINSTANCE.getFacesConfigType_ReferencedBean();

        /**
		 * The meta object literal for the '<em><b>Render Kit</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACES_CONFIG_TYPE__RENDER_KIT = eINSTANCE.getFacesConfigType_RenderKit();

        /**
		 * The meta object literal for the '<em><b>Lifecycle</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACES_CONFIG_TYPE__LIFECYCLE = eINSTANCE.getFacesConfigType_Lifecycle();

        /**
		 * The meta object literal for the '<em><b>Validator</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACES_CONFIG_TYPE__VALIDATOR = eINSTANCE.getFacesConfigType_Validator();

        /**
		 * The meta object literal for the '<em><b>Behavior</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACES_CONFIG_TYPE__BEHAVIOR = eINSTANCE.getFacesConfigType_Behavior();

								/**
		 * The meta object literal for the '<em><b>Faces Config Extension</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACES_CONFIG_TYPE__FACES_CONFIG_EXTENSION = eINSTANCE.getFacesConfigType_FacesConfigExtension();

        /**
		 * The meta object literal for the '<em><b>Xmlns</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACES_CONFIG_TYPE__XMLNS = eINSTANCE.getFacesConfigType_Xmlns();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACES_CONFIG_TYPE__ID = eINSTANCE.getFacesConfigType_Id();

        /**
		 * The meta object literal for the '<em><b>Metadata Complete</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FACES_CONFIG_TYPE__METADATA_COMPLETE = eINSTANCE.getFacesConfigType_MetadataComplete();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesContextFactoryTypeImpl <em>Faces Context Factory Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesContextFactoryTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFacesContextFactoryType()
		 * @generated
		 */
        EClass FACES_CONTEXT_FACTORY_TYPE = eINSTANCE.getFacesContextFactoryType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACES_CONTEXT_FACTORY_TYPE__TEXT_CONTENT = eINSTANCE.getFacesContextFactoryType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACES_CONTEXT_FACTORY_TYPE__ID = eINSTANCE.getFacesContextFactoryType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacetExtensionTypeImpl <em>Facet Extension Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacetExtensionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFacetExtensionType()
		 * @generated
		 */
        EClass FACET_EXTENSION_TYPE = eINSTANCE.getFacetExtensionType();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacetNameTypeImpl <em>Facet Name Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacetNameTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFacetNameType()
		 * @generated
		 */
        EClass FACET_NAME_TYPE = eINSTANCE.getFacetNameType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACET_NAME_TYPE__TEXT_CONTENT = eINSTANCE.getFacetNameType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACET_NAME_TYPE__ID = eINSTANCE.getFacetNameType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacetTypeImpl <em>Facet Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacetTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFacetType()
		 * @generated
		 */
        EClass FACET_TYPE = eINSTANCE.getFacetType();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACET_TYPE__DESCRIPTION = eINSTANCE.getFacetType_Description();

        /**
		 * The meta object literal for the '<em><b>Display Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACET_TYPE__DISPLAY_NAME = eINSTANCE.getFacetType_DisplayName();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACET_TYPE__ICON = eINSTANCE.getFacetType_Icon();

        /**
		 * The meta object literal for the '<em><b>Facet Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACET_TYPE__FACET_NAME = eINSTANCE.getFacetType_FacetName();

        /**
		 * The meta object literal for the '<em><b>Facet Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACET_TYPE__FACET_EXTENSION = eINSTANCE.getFacetType_FacetExtension();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACET_TYPE__ID = eINSTANCE.getFacetType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl <em>Factory Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFactoryType()
		 * @generated
		 */
        EClass FACTORY_TYPE = eINSTANCE.getFactoryType();

        /**
		 * The meta object literal for the '<em><b>Application Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACTORY_TYPE__APPLICATION_FACTORY = eINSTANCE.getFactoryType_ApplicationFactory();

        /**
		 * The meta object literal for the '<em><b>Exception Handler Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACTORY_TYPE__EXCEPTION_HANDLER_FACTORY = eINSTANCE.getFactoryType_ExceptionHandlerFactory();

								/**
		 * The meta object literal for the '<em><b>External Context Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACTORY_TYPE__EXTERNAL_CONTEXT_FACTORY = eINSTANCE.getFactoryType_ExternalContextFactory();

								/**
		 * The meta object literal for the '<em><b>Faces Context Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACTORY_TYPE__FACES_CONTEXT_FACTORY = eINSTANCE.getFactoryType_FacesContextFactory();

        /**
		 * The meta object literal for the '<em><b>Partial View Context Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACTORY_TYPE__PARTIAL_VIEW_CONTEXT_FACTORY = eINSTANCE.getFactoryType_PartialViewContextFactory();

								/**
		 * The meta object literal for the '<em><b>Lifecycle Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACTORY_TYPE__LIFECYCLE_FACTORY = eINSTANCE.getFactoryType_LifecycleFactory();

        /**
		 * The meta object literal for the '<em><b>View Declaration Language Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACTORY_TYPE__VIEW_DECLARATION_LANGUAGE_FACTORY = eINSTANCE.getFactoryType_ViewDeclarationLanguageFactory();

								/**
		 * The meta object literal for the '<em><b>Tag Handler Delegate Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACTORY_TYPE__TAG_HANDLER_DELEGATE_FACTORY = eINSTANCE.getFactoryType_TagHandlerDelegateFactory();

								/**
		 * The meta object literal for the '<em><b>Render Kit Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACTORY_TYPE__RENDER_KIT_FACTORY = eINSTANCE.getFactoryType_RenderKitFactory();

        /**
		 * The meta object literal for the '<em><b>Visit Context Factory</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FACTORY_TYPE__VISIT_CONTEXT_FACTORY = eINSTANCE.getFactoryType_VisitContextFactory();

								/**
		 * The meta object literal for the '<em><b>Factory Extension</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference FACTORY_TYPE__FACTORY_EXTENSION = eINSTANCE.getFactoryType_FactoryExtension();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FACTORY_TYPE__ID = eINSTANCE.getFactoryType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FromActionTypeImpl <em>From Action Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FromActionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFromActionType()
		 * @generated
		 */
        EClass FROM_ACTION_TYPE = eINSTANCE.getFromActionType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FROM_ACTION_TYPE__TEXT_CONTENT = eINSTANCE.getFromActionType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FROM_ACTION_TYPE__ID = eINSTANCE.getFromActionType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FromOutcomeTypeImpl <em>From Outcome Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FromOutcomeTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFromOutcomeType()
		 * @generated
		 */
        EClass FROM_OUTCOME_TYPE = eINSTANCE.getFromOutcomeType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FROM_OUTCOME_TYPE__TEXT_CONTENT = eINSTANCE.getFromOutcomeType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FROM_OUTCOME_TYPE__ID = eINSTANCE.getFromOutcomeType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FromViewIdTypeImpl <em>From View Id Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FromViewIdTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFromViewIdType()
		 * @generated
		 */
        EClass FROM_VIEW_ID_TYPE = eINSTANCE.getFromViewIdType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FROM_VIEW_ID_TYPE__TEXT_CONTENT = eINSTANCE.getFromViewIdType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute FROM_VIEW_ID_TYPE__ID = eINSTANCE.getFromViewIdType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.IconTypeImpl <em>Icon Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.IconTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getIconType()
		 * @generated
		 */
        EClass ICON_TYPE = eINSTANCE.getIconType();

        /**
		 * The meta object literal for the '<em><b>Small Icon</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference ICON_TYPE__SMALL_ICON = eINSTANCE.getIconType_SmallIcon();

        /**
		 * The meta object literal for the '<em><b>Large Icon</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference ICON_TYPE__LARGE_ICON = eINSTANCE.getIconType_LargeIcon();

        /**
		 * The meta object literal for the '<em><b>Lang</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ICON_TYPE__LANG = eINSTANCE.getIconType_Lang();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ICON_TYPE__ID = eINSTANCE.getIconType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.IfTypeImpl <em>If Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.IfTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getIfType()
		 * @generated
		 */
		EClass IF_TYPE = eINSTANCE.getIfType();

								/**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IF_TYPE__TEXT_CONTENT = eINSTANCE.getIfType_TextContent();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IF_TYPE__ID = eINSTANCE.getIfType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.KeyClassTypeImpl <em>Key Class Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.KeyClassTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getKeyClassType()
		 * @generated
		 */
        EClass KEY_CLASS_TYPE = eINSTANCE.getKeyClassType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute KEY_CLASS_TYPE__TEXT_CONTENT = eINSTANCE.getKeyClassType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute KEY_CLASS_TYPE__ID = eINSTANCE.getKeyClassType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.KeyTypeImpl <em>Key Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.KeyTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getKeyType()
		 * @generated
		 */
        EClass KEY_TYPE = eINSTANCE.getKeyType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute KEY_TYPE__TEXT_CONTENT = eINSTANCE.getKeyType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute KEY_TYPE__ID = eINSTANCE.getKeyType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.LargeIconTypeImpl <em>Large Icon Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.LargeIconTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getLargeIconType()
		 * @generated
		 */
        EClass LARGE_ICON_TYPE = eINSTANCE.getLargeIconType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute LARGE_ICON_TYPE__TEXT_CONTENT = eINSTANCE.getLargeIconType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute LARGE_ICON_TYPE__ID = eINSTANCE.getLargeIconType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.LifecycleFactoryTypeImpl <em>Lifecycle Factory Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.LifecycleFactoryTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getLifecycleFactoryType()
		 * @generated
		 */
        EClass LIFECYCLE_FACTORY_TYPE = eINSTANCE.getLifecycleFactoryType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute LIFECYCLE_FACTORY_TYPE__TEXT_CONTENT = eINSTANCE.getLifecycleFactoryType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute LIFECYCLE_FACTORY_TYPE__ID = eINSTANCE.getLifecycleFactoryType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.LifecycleTypeImpl <em>Lifecycle Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.LifecycleTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getLifecycleType()
		 * @generated
		 */
        EClass LIFECYCLE_TYPE = eINSTANCE.getLifecycleType();

        /**
		 * The meta object literal for the '<em><b>Phase Listener</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference LIFECYCLE_TYPE__PHASE_LISTENER = eINSTANCE.getLifecycleType_PhaseListener();

        /**
		 * The meta object literal for the '<em><b>Lifecycle Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference LIFECYCLE_TYPE__LIFECYCLE_EXTENSION = eINSTANCE.getLifecycleType_LifecycleExtension();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute LIFECYCLE_TYPE__ID = eINSTANCE.getLifecycleType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ListEntriesTypeImpl <em>List Entries Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ListEntriesTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getListEntriesType()
		 * @generated
		 */
        EClass LIST_ENTRIES_TYPE = eINSTANCE.getListEntriesType();

        /**
		 * The meta object literal for the '<em><b>Value Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference LIST_ENTRIES_TYPE__VALUE_CLASS = eINSTANCE.getListEntriesType_ValueClass();

        /**
		 * The meta object literal for the '<em><b>Null Value</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference LIST_ENTRIES_TYPE__NULL_VALUE = eINSTANCE.getListEntriesType_NullValue();

        /**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference LIST_ENTRIES_TYPE__VALUE = eINSTANCE.getListEntriesType_Value();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute LIST_ENTRIES_TYPE__ID = eINSTANCE.getListEntriesType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.LocaleConfigTypeImpl <em>Locale Config Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.LocaleConfigTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getLocaleConfigType()
		 * @generated
		 */
        EClass LOCALE_CONFIG_TYPE = eINSTANCE.getLocaleConfigType();

        /**
		 * The meta object literal for the '<em><b>Default Locale</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference LOCALE_CONFIG_TYPE__DEFAULT_LOCALE = eINSTANCE.getLocaleConfigType_DefaultLocale();

        /**
		 * The meta object literal for the '<em><b>Supported Locale</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference LOCALE_CONFIG_TYPE__SUPPORTED_LOCALE = eINSTANCE.getLocaleConfigType_SupportedLocale();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute LOCALE_CONFIG_TYPE__ID = eINSTANCE.getLocaleConfigType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanClassTypeImpl <em>Managed Bean Class Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanClassTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getManagedBeanClassType()
		 * @generated
		 */
        EClass MANAGED_BEAN_CLASS_TYPE = eINSTANCE.getManagedBeanClassType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute MANAGED_BEAN_CLASS_TYPE__TEXT_CONTENT = eINSTANCE.getManagedBeanClassType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute MANAGED_BEAN_CLASS_TYPE__ID = eINSTANCE.getManagedBeanClassType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanNameTypeImpl <em>Managed Bean Name Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanNameTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getManagedBeanNameType()
		 * @generated
		 */
        EClass MANAGED_BEAN_NAME_TYPE = eINSTANCE.getManagedBeanNameType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute MANAGED_BEAN_NAME_TYPE__TEXT_CONTENT = eINSTANCE.getManagedBeanNameType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute MANAGED_BEAN_NAME_TYPE__ID = eINSTANCE.getManagedBeanNameType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanScopeTypeImpl <em>Managed Bean Scope Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanScopeTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getManagedBeanScopeType()
		 * @generated
		 */
        EClass MANAGED_BEAN_SCOPE_TYPE = eINSTANCE.getManagedBeanScopeType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute MANAGED_BEAN_SCOPE_TYPE__TEXT_CONTENT = eINSTANCE.getManagedBeanScopeType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute MANAGED_BEAN_SCOPE_TYPE__ID = eINSTANCE.getManagedBeanScopeType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanTypeImpl <em>Managed Bean Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getManagedBeanType()
		 * @generated
		 */
        EClass MANAGED_BEAN_TYPE = eINSTANCE.getManagedBeanType();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_BEAN_TYPE__DESCRIPTION = eINSTANCE.getManagedBeanType_Description();

        /**
		 * The meta object literal for the '<em><b>Display Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_BEAN_TYPE__DISPLAY_NAME = eINSTANCE.getManagedBeanType_DisplayName();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_BEAN_TYPE__ICON = eINSTANCE.getManagedBeanType_Icon();

        /**
		 * The meta object literal for the '<em><b>Managed Bean Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_BEAN_TYPE__MANAGED_BEAN_NAME = eINSTANCE.getManagedBeanType_ManagedBeanName();

        /**
		 * The meta object literal for the '<em><b>Managed Bean Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_BEAN_TYPE__MANAGED_BEAN_CLASS = eINSTANCE.getManagedBeanType_ManagedBeanClass();

        /**
		 * The meta object literal for the '<em><b>Managed Bean Scope</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_BEAN_TYPE__MANAGED_BEAN_SCOPE = eINSTANCE.getManagedBeanType_ManagedBeanScope();

        /**
		 * The meta object literal for the '<em><b>Managed Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_BEAN_TYPE__MANAGED_PROPERTY = eINSTANCE.getManagedBeanType_ManagedProperty();

        /**
		 * The meta object literal for the '<em><b>Map Entries</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_BEAN_TYPE__MAP_ENTRIES = eINSTANCE.getManagedBeanType_MapEntries();

        /**
		 * The meta object literal for the '<em><b>List Entries</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_BEAN_TYPE__LIST_ENTRIES = eINSTANCE.getManagedBeanType_ListEntries();

        /**
		 * The meta object literal for the '<em><b>Managed Bean Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_BEAN_TYPE__MANAGED_BEAN_EXTENSION = eINSTANCE.getManagedBeanType_ManagedBeanExtension();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute MANAGED_BEAN_TYPE__ID = eINSTANCE.getManagedBeanType_Id();

        /**
		 * The meta object literal for the '<em><b>Eager</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANAGED_BEAN_TYPE__EAGER = eINSTANCE.getManagedBeanType_Eager();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedPropertyTypeImpl <em>Managed Property Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedPropertyTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getManagedPropertyType()
		 * @generated
		 */
        EClass MANAGED_PROPERTY_TYPE = eINSTANCE.getManagedPropertyType();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_PROPERTY_TYPE__DESCRIPTION = eINSTANCE.getManagedPropertyType_Description();

        /**
		 * The meta object literal for the '<em><b>Display Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_PROPERTY_TYPE__DISPLAY_NAME = eINSTANCE.getManagedPropertyType_DisplayName();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_PROPERTY_TYPE__ICON = eINSTANCE.getManagedPropertyType_Icon();

        /**
		 * The meta object literal for the '<em><b>Property Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_PROPERTY_TYPE__PROPERTY_NAME = eINSTANCE.getManagedPropertyType_PropertyName();

        /**
		 * The meta object literal for the '<em><b>Property Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_PROPERTY_TYPE__PROPERTY_CLASS = eINSTANCE.getManagedPropertyType_PropertyClass();

        /**
		 * The meta object literal for the '<em><b>Map Entries</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_PROPERTY_TYPE__MAP_ENTRIES = eINSTANCE.getManagedPropertyType_MapEntries();

        /**
		 * The meta object literal for the '<em><b>Null Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_PROPERTY_TYPE__NULL_VALUE = eINSTANCE.getManagedPropertyType_NullValue();

        /**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_PROPERTY_TYPE__VALUE = eINSTANCE.getManagedPropertyType_Value();

        /**
		 * The meta object literal for the '<em><b>List Entries</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MANAGED_PROPERTY_TYPE__LIST_ENTRIES = eINSTANCE.getManagedPropertyType_ListEntries();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute MANAGED_PROPERTY_TYPE__ID = eINSTANCE.getManagedPropertyType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.MapEntriesTypeImpl <em>Map Entries Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.MapEntriesTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getMapEntriesType()
		 * @generated
		 */
        EClass MAP_ENTRIES_TYPE = eINSTANCE.getMapEntriesType();

        /**
		 * The meta object literal for the '<em><b>Key Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MAP_ENTRIES_TYPE__KEY_CLASS = eINSTANCE.getMapEntriesType_KeyClass();

        /**
		 * The meta object literal for the '<em><b>Value Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MAP_ENTRIES_TYPE__VALUE_CLASS = eINSTANCE.getMapEntriesType_ValueClass();

        /**
		 * The meta object literal for the '<em><b>Map Entry</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MAP_ENTRIES_TYPE__MAP_ENTRY = eINSTANCE.getMapEntriesType_MapEntry();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute MAP_ENTRIES_TYPE__ID = eINSTANCE.getMapEntriesType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.MapEntryTypeImpl <em>Map Entry Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.MapEntryTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getMapEntryType()
		 * @generated
		 */
        EClass MAP_ENTRY_TYPE = eINSTANCE.getMapEntryType();

        /**
		 * The meta object literal for the '<em><b>Key</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MAP_ENTRY_TYPE__KEY = eINSTANCE.getMapEntryType_Key();

        /**
		 * The meta object literal for the '<em><b>Null Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MAP_ENTRY_TYPE__NULL_VALUE = eINSTANCE.getMapEntryType_NullValue();

        /**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference MAP_ENTRY_TYPE__VALUE = eINSTANCE.getMapEntryType_Value();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute MAP_ENTRY_TYPE__ID = eINSTANCE.getMapEntryType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.MessageBundleTypeImpl <em>Message Bundle Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.MessageBundleTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getMessageBundleType()
		 * @generated
		 */
        EClass MESSAGE_BUNDLE_TYPE = eINSTANCE.getMessageBundleType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute MESSAGE_BUNDLE_TYPE__TEXT_CONTENT = eINSTANCE.getMessageBundleType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute MESSAGE_BUNDLE_TYPE__ID = eINSTANCE.getMessageBundleType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NameTypeImpl <em>Name Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.NameTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getNameType()
		 * @generated
		 */
		EClass NAME_TYPE = eINSTANCE.getNameType();

								/**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAME_TYPE__TEXT_CONTENT = eINSTANCE.getNameType_TextContent();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAME_TYPE__ID = eINSTANCE.getNameType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationCaseTypeImpl <em>Navigation Case Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationCaseTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getNavigationCaseType()
		 * @generated
		 */
        EClass NAVIGATION_CASE_TYPE = eINSTANCE.getNavigationCaseType();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference NAVIGATION_CASE_TYPE__DESCRIPTION = eINSTANCE.getNavigationCaseType_Description();

        /**
		 * The meta object literal for the '<em><b>Display Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference NAVIGATION_CASE_TYPE__DISPLAY_NAME = eINSTANCE.getNavigationCaseType_DisplayName();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference NAVIGATION_CASE_TYPE__ICON = eINSTANCE.getNavigationCaseType_Icon();

        /**
		 * The meta object literal for the '<em><b>From Action</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference NAVIGATION_CASE_TYPE__FROM_ACTION = eINSTANCE.getNavigationCaseType_FromAction();

        /**
		 * The meta object literal for the '<em><b>From Outcome</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference NAVIGATION_CASE_TYPE__FROM_OUTCOME = eINSTANCE.getNavigationCaseType_FromOutcome();

        /**
		 * The meta object literal for the '<em><b>If</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATION_CASE_TYPE__IF = eINSTANCE.getNavigationCaseType_If();

								/**
		 * The meta object literal for the '<em><b>To View Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference NAVIGATION_CASE_TYPE__TO_VIEW_ID = eINSTANCE.getNavigationCaseType_ToViewId();

        /**
		 * The meta object literal for the '<em><b>Redirect</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference NAVIGATION_CASE_TYPE__REDIRECT = eINSTANCE.getNavigationCaseType_Redirect();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute NAVIGATION_CASE_TYPE__ID = eINSTANCE.getNavigationCaseType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationHandlerTypeImpl <em>Navigation Handler Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationHandlerTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getNavigationHandlerType()
		 * @generated
		 */
        EClass NAVIGATION_HANDLER_TYPE = eINSTANCE.getNavigationHandlerType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute NAVIGATION_HANDLER_TYPE__TEXT_CONTENT = eINSTANCE.getNavigationHandlerType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute NAVIGATION_HANDLER_TYPE__ID = eINSTANCE.getNavigationHandlerType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationRuleTypeImpl <em>Navigation Rule Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationRuleTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getNavigationRuleType()
		 * @generated
		 */
        EClass NAVIGATION_RULE_TYPE = eINSTANCE.getNavigationRuleType();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference NAVIGATION_RULE_TYPE__DESCRIPTION = eINSTANCE.getNavigationRuleType_Description();

        /**
		 * The meta object literal for the '<em><b>Display Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference NAVIGATION_RULE_TYPE__DISPLAY_NAME = eINSTANCE.getNavigationRuleType_DisplayName();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference NAVIGATION_RULE_TYPE__ICON = eINSTANCE.getNavigationRuleType_Icon();

        /**
		 * The meta object literal for the '<em><b>From View Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference NAVIGATION_RULE_TYPE__FROM_VIEW_ID = eINSTANCE.getNavigationRuleType_FromViewId();

        /**
		 * The meta object literal for the '<em><b>Navigation Case</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference NAVIGATION_RULE_TYPE__NAVIGATION_CASE = eINSTANCE.getNavigationRuleType_NavigationCase();

        /**
		 * The meta object literal for the '<em><b>Navigation Rule Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference NAVIGATION_RULE_TYPE__NAVIGATION_RULE_EXTENSION = eINSTANCE.getNavigationRuleType_NavigationRuleExtension();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute NAVIGATION_RULE_TYPE__ID = eINSTANCE.getNavigationRuleType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NullValueTypeImpl <em>Null Value Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.NullValueTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getNullValueType()
		 * @generated
		 */
        EClass NULL_VALUE_TYPE = eINSTANCE.getNullValueType();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute NULL_VALUE_TYPE__ID = eINSTANCE.getNullValueType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.OrderingTypeImpl <em>Ordering Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.OrderingTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getOrderingType()
		 * @generated
		 */
		EClass ORDERING_TYPE = eINSTANCE.getOrderingType();

								/**
		 * The meta object literal for the '<em><b>Before</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORDERING_TYPE__BEFORE = eINSTANCE.getOrderingType_Before();

								/**
		 * The meta object literal for the '<em><b>After</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORDERING_TYPE__AFTER = eINSTANCE.getOrderingType_After();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.OrderingOrderingTypeImpl <em>Ordering Ordering Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.OrderingOrderingTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getOrderingOrderingType()
		 * @generated
		 */
		EClass ORDERING_ORDERING_TYPE = eINSTANCE.getOrderingOrderingType();

								/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORDERING_ORDERING_TYPE__NAME = eINSTANCE.getOrderingOrderingType_Name();

								/**
		 * The meta object literal for the '<em><b>Others</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ORDERING_ORDERING_TYPE__OTHERS = eINSTANCE.getOrderingOrderingType_Others();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.OrderingOthersTypeImpl <em>Ordering Others Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.OrderingOthersTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getOrderingOthersType()
		 * @generated
		 */
		EClass ORDERING_OTHERS_TYPE = eINSTANCE.getOrderingOthersType();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORDERING_OTHERS_TYPE__ID = eINSTANCE.getOrderingOthersType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PartialViewContextFactoryTypeImpl <em>Partial View Context Factory Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.PartialViewContextFactoryTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getPartialViewContextFactoryType()
		 * @generated
		 */
		EClass PARTIAL_VIEW_CONTEXT_FACTORY_TYPE = eINSTANCE.getPartialViewContextFactoryType();

								/**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTIAL_VIEW_CONTEXT_FACTORY_TYPE__TEXT_CONTENT = eINSTANCE.getPartialViewContextFactoryType_TextContent();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTIAL_VIEW_CONTEXT_FACTORY_TYPE__ID = eINSTANCE.getPartialViewContextFactoryType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PhaseListenerTypeImpl <em>Phase Listener Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.PhaseListenerTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getPhaseListenerType()
		 * @generated
		 */
        EClass PHASE_LISTENER_TYPE = eINSTANCE.getPhaseListenerType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute PHASE_LISTENER_TYPE__TEXT_CONTENT = eINSTANCE.getPhaseListenerType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute PHASE_LISTENER_TYPE__ID = eINSTANCE.getPhaseListenerType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyClassTypeImpl <em>Property Class Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyClassTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getPropertyClassType()
		 * @generated
		 */
        EClass PROPERTY_CLASS_TYPE = eINSTANCE.getPropertyClassType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute PROPERTY_CLASS_TYPE__TEXT_CONTENT = eINSTANCE.getPropertyClassType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute PROPERTY_CLASS_TYPE__ID = eINSTANCE.getPropertyClassType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyExtensionTypeImpl <em>Property Extension Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyExtensionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getPropertyExtensionType()
		 * @generated
		 */
        EClass PROPERTY_EXTENSION_TYPE = eINSTANCE.getPropertyExtensionType();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyNameTypeImpl <em>Property Name Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyNameTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getPropertyNameType()
		 * @generated
		 */
        EClass PROPERTY_NAME_TYPE = eINSTANCE.getPropertyNameType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute PROPERTY_NAME_TYPE__TEXT_CONTENT = eINSTANCE.getPropertyNameType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute PROPERTY_NAME_TYPE__ID = eINSTANCE.getPropertyNameType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyResolverTypeImpl <em>Property Resolver Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyResolverTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getPropertyResolverType()
		 * @generated
		 */
        EClass PROPERTY_RESOLVER_TYPE = eINSTANCE.getPropertyResolverType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute PROPERTY_RESOLVER_TYPE__TEXT_CONTENT = eINSTANCE.getPropertyResolverType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute PROPERTY_RESOLVER_TYPE__ID = eINSTANCE.getPropertyResolverType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyTypeImpl <em>Property Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.PropertyTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getPropertyType()
		 * @generated
		 */
        EClass PROPERTY_TYPE = eINSTANCE.getPropertyType();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference PROPERTY_TYPE__DESCRIPTION = eINSTANCE.getPropertyType_Description();

        /**
		 * The meta object literal for the '<em><b>Display Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference PROPERTY_TYPE__DISPLAY_NAME = eINSTANCE.getPropertyType_DisplayName();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference PROPERTY_TYPE__ICON = eINSTANCE.getPropertyType_Icon();

        /**
		 * The meta object literal for the '<em><b>Property Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference PROPERTY_TYPE__PROPERTY_NAME = eINSTANCE.getPropertyType_PropertyName();

        /**
		 * The meta object literal for the '<em><b>Property Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference PROPERTY_TYPE__PROPERTY_CLASS = eINSTANCE.getPropertyType_PropertyClass();

        /**
		 * The meta object literal for the '<em><b>Default Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference PROPERTY_TYPE__DEFAULT_VALUE = eINSTANCE.getPropertyType_DefaultValue();

        /**
		 * The meta object literal for the '<em><b>Suggested Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference PROPERTY_TYPE__SUGGESTED_VALUE = eINSTANCE.getPropertyType_SuggestedValue();

        /**
		 * The meta object literal for the '<em><b>Property Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference PROPERTY_TYPE__PROPERTY_EXTENSION = eINSTANCE.getPropertyType_PropertyExtension();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute PROPERTY_TYPE__ID = eINSTANCE.getPropertyType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RedirectTypeImpl <em>Redirect Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RedirectTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRedirectType()
		 * @generated
		 */
        EClass REDIRECT_TYPE = eINSTANCE.getRedirectType();

        /**
		 * The meta object literal for the '<em><b>View Param</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REDIRECT_TYPE__VIEW_PARAM = eINSTANCE.getRedirectType_ViewParam();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute REDIRECT_TYPE__ID = eINSTANCE.getRedirectType_Id();

        /**
		 * The meta object literal for the '<em><b>Include View Params</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REDIRECT_TYPE__INCLUDE_VIEW_PARAMS = eINSTANCE.getRedirectType_IncludeViewParams();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RedirectViewParamTypeImpl <em>Redirect View Param Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RedirectViewParamTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRedirectViewParamType()
		 * @generated
		 */
		EClass REDIRECT_VIEW_PARAM_TYPE = eINSTANCE.getRedirectViewParamType();

								/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REDIRECT_VIEW_PARAM_TYPE__NAME = eINSTANCE.getRedirectViewParamType_Name();

								/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REDIRECT_VIEW_PARAM_TYPE__VALUE = eINSTANCE.getRedirectViewParamType_Value();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REDIRECT_VIEW_PARAM_TYPE__ID = eINSTANCE.getRedirectViewParamType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanClassTypeImpl <em>Referenced Bean Class Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanClassTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getReferencedBeanClassType()
		 * @generated
		 */
        EClass REFERENCED_BEAN_CLASS_TYPE = eINSTANCE.getReferencedBeanClassType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute REFERENCED_BEAN_CLASS_TYPE__TEXT_CONTENT = eINSTANCE.getReferencedBeanClassType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute REFERENCED_BEAN_CLASS_TYPE__ID = eINSTANCE.getReferencedBeanClassType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanNameTypeImpl <em>Referenced Bean Name Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanNameTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getReferencedBeanNameType()
		 * @generated
		 */
        EClass REFERENCED_BEAN_NAME_TYPE = eINSTANCE.getReferencedBeanNameType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute REFERENCED_BEAN_NAME_TYPE__TEXT_CONTENT = eINSTANCE.getReferencedBeanNameType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute REFERENCED_BEAN_NAME_TYPE__ID = eINSTANCE.getReferencedBeanNameType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanTypeImpl <em>Referenced Bean Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ReferencedBeanTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getReferencedBeanType()
		 * @generated
		 */
        EClass REFERENCED_BEAN_TYPE = eINSTANCE.getReferencedBeanType();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference REFERENCED_BEAN_TYPE__DESCRIPTION = eINSTANCE.getReferencedBeanType_Description();

        /**
		 * The meta object literal for the '<em><b>Display Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference REFERENCED_BEAN_TYPE__DISPLAY_NAME = eINSTANCE.getReferencedBeanType_DisplayName();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference REFERENCED_BEAN_TYPE__ICON = eINSTANCE.getReferencedBeanType_Icon();

        /**
		 * The meta object literal for the '<em><b>Referenced Bean Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference REFERENCED_BEAN_TYPE__REFERENCED_BEAN_NAME = eINSTANCE.getReferencedBeanType_ReferencedBeanName();

        /**
		 * The meta object literal for the '<em><b>Referenced Bean Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference REFERENCED_BEAN_TYPE__REFERENCED_BEAN_CLASS = eINSTANCE.getReferencedBeanType_ReferencedBeanClass();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute REFERENCED_BEAN_TYPE__ID = eINSTANCE.getReferencedBeanType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererClassTypeImpl <em>Renderer Class Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RendererClassTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRendererClassType()
		 * @generated
		 */
        EClass RENDERER_CLASS_TYPE = eINSTANCE.getRendererClassType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute RENDERER_CLASS_TYPE__TEXT_CONTENT = eINSTANCE.getRendererClassType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute RENDERER_CLASS_TYPE__ID = eINSTANCE.getRendererClassType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererExtensionTypeImpl <em>Renderer Extension Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RendererExtensionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRendererExtensionType()
		 * @generated
		 */
        EClass RENDERER_EXTENSION_TYPE = eINSTANCE.getRendererExtensionType();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeImpl <em>Renderer Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRendererType()
		 * @generated
		 */
        EClass RENDERER_TYPE = eINSTANCE.getRendererType();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RENDERER_TYPE__DESCRIPTION = eINSTANCE.getRendererType_Description();

        /**
		 * The meta object literal for the '<em><b>Display Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RENDERER_TYPE__DISPLAY_NAME = eINSTANCE.getRendererType_DisplayName();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RENDERER_TYPE__ICON = eINSTANCE.getRendererType_Icon();

        /**
		 * The meta object literal for the '<em><b>Component Family</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RENDERER_TYPE__COMPONENT_FAMILY = eINSTANCE.getRendererType_ComponentFamily();

        /**
		 * The meta object literal for the '<em><b>Renderer Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RENDERER_TYPE__RENDERER_TYPE = eINSTANCE.getRendererType_RendererType();

        /**
		 * The meta object literal for the '<em><b>Renderer Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RENDERER_TYPE__RENDERER_CLASS = eINSTANCE.getRendererType_RendererClass();

        /**
		 * The meta object literal for the '<em><b>Facet</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RENDERER_TYPE__FACET = eINSTANCE.getRendererType_Facet();

        /**
		 * The meta object literal for the '<em><b>Attribute</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RENDERER_TYPE__ATTRIBUTE = eINSTANCE.getRendererType_Attribute();

        /**
		 * The meta object literal for the '<em><b>Renderer Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RENDERER_TYPE__RENDERER_EXTENSION = eINSTANCE.getRendererType_RendererExtension();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute RENDERER_TYPE__ID = eINSTANCE.getRendererType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeTypeImpl <em>Renderer Type Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RendererTypeTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRendererTypeType()
		 * @generated
		 */
        EClass RENDERER_TYPE_TYPE = eINSTANCE.getRendererTypeType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute RENDERER_TYPE_TYPE__TEXT_CONTENT = eINSTANCE.getRendererTypeType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute RENDERER_TYPE_TYPE__ID = eINSTANCE.getRendererTypeType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitClassTypeImpl <em>Render Kit Class Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitClassTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRenderKitClassType()
		 * @generated
		 */
        EClass RENDER_KIT_CLASS_TYPE = eINSTANCE.getRenderKitClassType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute RENDER_KIT_CLASS_TYPE__TEXT_CONTENT = eINSTANCE.getRenderKitClassType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute RENDER_KIT_CLASS_TYPE__ID = eINSTANCE.getRenderKitClassType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitFactoryTypeImpl <em>Render Kit Factory Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitFactoryTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRenderKitFactoryType()
		 * @generated
		 */
        EClass RENDER_KIT_FACTORY_TYPE = eINSTANCE.getRenderKitFactoryType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute RENDER_KIT_FACTORY_TYPE__TEXT_CONTENT = eINSTANCE.getRenderKitFactoryType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute RENDER_KIT_FACTORY_TYPE__ID = eINSTANCE.getRenderKitFactoryType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitIdTypeImpl <em>Render Kit Id Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitIdTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRenderKitIdType()
		 * @generated
		 */
        EClass RENDER_KIT_ID_TYPE = eINSTANCE.getRenderKitIdType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute RENDER_KIT_ID_TYPE__TEXT_CONTENT = eINSTANCE.getRenderKitIdType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute RENDER_KIT_ID_TYPE__ID = eINSTANCE.getRenderKitIdType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitTypeImpl <em>Render Kit Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRenderKitType()
		 * @generated
		 */
        EClass RENDER_KIT_TYPE = eINSTANCE.getRenderKitType();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RENDER_KIT_TYPE__DESCRIPTION = eINSTANCE.getRenderKitType_Description();

        /**
		 * The meta object literal for the '<em><b>Display Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RENDER_KIT_TYPE__DISPLAY_NAME = eINSTANCE.getRenderKitType_DisplayName();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RENDER_KIT_TYPE__ICON = eINSTANCE.getRenderKitType_Icon();

        /**
		 * The meta object literal for the '<em><b>Render Kit Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RENDER_KIT_TYPE__RENDER_KIT_ID = eINSTANCE.getRenderKitType_RenderKitId();

        /**
		 * The meta object literal for the '<em><b>Render Kit Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RENDER_KIT_TYPE__RENDER_KIT_CLASS = eINSTANCE.getRenderKitType_RenderKitClass();

        /**
		 * The meta object literal for the '<em><b>Renderer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RENDER_KIT_TYPE__RENDERER = eINSTANCE.getRenderKitType_Renderer();

        /**
		 * The meta object literal for the '<em><b>Client Behavior Renderer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENDER_KIT_TYPE__CLIENT_BEHAVIOR_RENDERER = eINSTANCE.getRenderKitType_ClientBehaviorRenderer();

								/**
		 * The meta object literal for the '<em><b>Render Kit Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RENDER_KIT_TYPE__RENDER_KIT_EXTENSION = eINSTANCE.getRenderKitType_RenderKitExtension();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute RENDER_KIT_TYPE__ID = eINSTANCE.getRenderKitType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SmallIconTypeImpl <em>Small Icon Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.SmallIconTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getSmallIconType()
		 * @generated
		 */
        EClass SMALL_ICON_TYPE = eINSTANCE.getSmallIconType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SMALL_ICON_TYPE__TEXT_CONTENT = eINSTANCE.getSmallIconType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SMALL_ICON_TYPE__ID = eINSTANCE.getSmallIconType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SourceClassTypeImpl <em>Source Class Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.SourceClassTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getSourceClassType()
		 * @generated
		 */
		EClass SOURCE_CLASS_TYPE = eINSTANCE.getSourceClassType();

								/**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_CLASS_TYPE__TEXT_CONTENT = eINSTANCE.getSourceClassType_TextContent();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE_CLASS_TYPE__ID = eINSTANCE.getSourceClassType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.StateManagerTypeImpl <em>State Manager Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.StateManagerTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getStateManagerType()
		 * @generated
		 */
        EClass STATE_MANAGER_TYPE = eINSTANCE.getStateManagerType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute STATE_MANAGER_TYPE__TEXT_CONTENT = eINSTANCE.getStateManagerType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute STATE_MANAGER_TYPE__ID = eINSTANCE.getStateManagerType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SuggestedValueTypeImpl <em>Suggested Value Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.SuggestedValueTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getSuggestedValueType()
		 * @generated
		 */
        EClass SUGGESTED_VALUE_TYPE = eINSTANCE.getSuggestedValueType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SUGGESTED_VALUE_TYPE__TEXT_CONTENT = eINSTANCE.getSuggestedValueType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SUGGESTED_VALUE_TYPE__ID = eINSTANCE.getSuggestedValueType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SupportedLocaleTypeImpl <em>Supported Locale Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.SupportedLocaleTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getSupportedLocaleType()
		 * @generated
		 */
        EClass SUPPORTED_LOCALE_TYPE = eINSTANCE.getSupportedLocaleType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SUPPORTED_LOCALE_TYPE__TEXT_CONTENT = eINSTANCE.getSupportedLocaleType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SUPPORTED_LOCALE_TYPE__ID = eINSTANCE.getSupportedLocaleType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SystemEventClassTypeImpl <em>System Event Class Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.SystemEventClassTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getSystemEventClassType()
		 * @generated
		 */
		EClass SYSTEM_EVENT_CLASS_TYPE = eINSTANCE.getSystemEventClassType();

								/**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYSTEM_EVENT_CLASS_TYPE__TEXT_CONTENT = eINSTANCE.getSystemEventClassType_TextContent();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYSTEM_EVENT_CLASS_TYPE__ID = eINSTANCE.getSystemEventClassType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SystemEventListenerClassTypeImpl <em>System Event Listener Class Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.SystemEventListenerClassTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getSystemEventListenerClassType()
		 * @generated
		 */
		EClass SYSTEM_EVENT_LISTENER_CLASS_TYPE = eINSTANCE.getSystemEventListenerClassType();

								/**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYSTEM_EVENT_LISTENER_CLASS_TYPE__TEXT_CONTENT = eINSTANCE.getSystemEventListenerClassType_TextContent();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYSTEM_EVENT_LISTENER_CLASS_TYPE__ID = eINSTANCE.getSystemEventListenerClassType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.SystemEventListenerTypeImpl <em>System Event Listener Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.SystemEventListenerTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getSystemEventListenerType()
		 * @generated
		 */
		EClass SYSTEM_EVENT_LISTENER_TYPE = eINSTANCE.getSystemEventListenerType();

								/**
		 * The meta object literal for the '<em><b>System Event Listener Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_LISTENER_CLASS = eINSTANCE.getSystemEventListenerType_SystemEventListenerClass();

								/**
		 * The meta object literal for the '<em><b>System Event Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_EVENT_LISTENER_TYPE__SYSTEM_EVENT_CLASS = eINSTANCE.getSystemEventListenerType_SystemEventClass();

								/**
		 * The meta object literal for the '<em><b>Source Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_EVENT_LISTENER_TYPE__SOURCE_CLASS = eINSTANCE.getSystemEventListenerType_SourceClass();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYSTEM_EVENT_LISTENER_TYPE__ID = eINSTANCE.getSystemEventListenerType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.TagHandlerDelegateFactoryTypeImpl <em>Tag Handler Delegate Factory Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.TagHandlerDelegateFactoryTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getTagHandlerDelegateFactoryType()
		 * @generated
		 */
		EClass TAG_HANDLER_DELEGATE_FACTORY_TYPE = eINSTANCE.getTagHandlerDelegateFactoryType();

								/**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_HANDLER_DELEGATE_FACTORY_TYPE__TEXT_CONTENT = eINSTANCE.getTagHandlerDelegateFactoryType_TextContent();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_HANDLER_DELEGATE_FACTORY_TYPE__ID = eINSTANCE.getTagHandlerDelegateFactoryType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ToViewIdTypeImpl <em>To View Id Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ToViewIdTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getToViewIdType()
		 * @generated
		 */
        EClass TO_VIEW_ID_TYPE = eINSTANCE.getToViewIdType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute TO_VIEW_ID_TYPE__TEXT_CONTENT = eINSTANCE.getToViewIdType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute TO_VIEW_ID_TYPE__ID = eINSTANCE.getToViewIdType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorClassTypeImpl <em>Validator Class Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorClassTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getValidatorClassType()
		 * @generated
		 */
        EClass VALIDATOR_CLASS_TYPE = eINSTANCE.getValidatorClassType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute VALIDATOR_CLASS_TYPE__TEXT_CONTENT = eINSTANCE.getValidatorClassType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute VALIDATOR_CLASS_TYPE__ID = eINSTANCE.getValidatorClassType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorIdTypeImpl <em>Validator Id Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorIdTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getValidatorIdType()
		 * @generated
		 */
        EClass VALIDATOR_ID_TYPE = eINSTANCE.getValidatorIdType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute VALIDATOR_ID_TYPE__TEXT_CONTENT = eINSTANCE.getValidatorIdType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute VALIDATOR_ID_TYPE__ID = eINSTANCE.getValidatorIdType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorTypeImpl <em>Validator Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getValidatorType()
		 * @generated
		 */
        EClass VALIDATOR_TYPE = eINSTANCE.getValidatorType();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference VALIDATOR_TYPE__DESCRIPTION = eINSTANCE.getValidatorType_Description();

        /**
		 * The meta object literal for the '<em><b>Display Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference VALIDATOR_TYPE__DISPLAY_NAME = eINSTANCE.getValidatorType_DisplayName();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference VALIDATOR_TYPE__ICON = eINSTANCE.getValidatorType_Icon();

        /**
		 * The meta object literal for the '<em><b>Validator Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference VALIDATOR_TYPE__VALIDATOR_ID = eINSTANCE.getValidatorType_ValidatorId();

        /**
		 * The meta object literal for the '<em><b>Validator Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference VALIDATOR_TYPE__VALIDATOR_CLASS = eINSTANCE.getValidatorType_ValidatorClass();

        /**
		 * The meta object literal for the '<em><b>Attribute</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference VALIDATOR_TYPE__ATTRIBUTE = eINSTANCE.getValidatorType_Attribute();

        /**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference VALIDATOR_TYPE__PROPERTY = eINSTANCE.getValidatorType_Property();

        /**
		 * The meta object literal for the '<em><b>Validator Extension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference VALIDATOR_TYPE__VALIDATOR_EXTENSION = eINSTANCE.getValidatorType_ValidatorExtension();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute VALIDATOR_TYPE__ID = eINSTANCE.getValidatorType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValueClassTypeImpl <em>Value Class Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ValueClassTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getValueClassType()
		 * @generated
		 */
        EClass VALUE_CLASS_TYPE = eINSTANCE.getValueClassType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute VALUE_CLASS_TYPE__TEXT_CONTENT = eINSTANCE.getValueClassType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute VALUE_CLASS_TYPE__ID = eINSTANCE.getValueClassType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValueTypeImpl <em>Value Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ValueTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getValueType()
		 * @generated
		 */
        EClass VALUE_TYPE = eINSTANCE.getValueType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute VALUE_TYPE__TEXT_CONTENT = eINSTANCE.getValueType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute VALUE_TYPE__ID = eINSTANCE.getValueType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.VariableResolverTypeImpl <em>Variable Resolver Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.VariableResolverTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getVariableResolverType()
		 * @generated
		 */
        EClass VARIABLE_RESOLVER_TYPE = eINSTANCE.getVariableResolverType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute VARIABLE_RESOLVER_TYPE__TEXT_CONTENT = eINSTANCE.getVariableResolverType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute VARIABLE_RESOLVER_TYPE__ID = eINSTANCE.getVariableResolverType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ViewHandlerTypeImpl <em>View Handler Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ViewHandlerTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getViewHandlerType()
		 * @generated
		 */
        EClass VIEW_HANDLER_TYPE = eINSTANCE.getViewHandlerType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute VIEW_HANDLER_TYPE__TEXT_CONTENT = eINSTANCE.getViewHandlerType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute VIEW_HANDLER_TYPE__ID = eINSTANCE.getViewHandlerType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ResourceBundleTypeImpl <em>Resource Bundle Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ResourceBundleTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getResourceBundleType()
		 * @generated
		 */
        EClass RESOURCE_BUNDLE_TYPE = eINSTANCE.getResourceBundleType();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RESOURCE_BUNDLE_TYPE__DESCRIPTION = eINSTANCE.getResourceBundleType_Description();

        /**
		 * The meta object literal for the '<em><b>Display Name</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RESOURCE_BUNDLE_TYPE__DISPLAY_NAME = eINSTANCE.getResourceBundleType_DisplayName();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RESOURCE_BUNDLE_TYPE__ICON = eINSTANCE.getResourceBundleType_Icon();

        /**
		 * The meta object literal for the '<em><b>Base Name</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RESOURCE_BUNDLE_TYPE__BASE_NAME = eINSTANCE.getResourceBundleType_BaseName();

        /**
		 * The meta object literal for the '<em><b>Var</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RESOURCE_BUNDLE_TYPE__VAR = eINSTANCE.getResourceBundleType_Var();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute RESOURCE_BUNDLE_TYPE__ID = eINSTANCE.getResourceBundleType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.BaseNameTypeImpl <em>Base Name Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.BaseNameTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getBaseNameType()
		 * @generated
		 */
        EClass BASE_NAME_TYPE = eINSTANCE.getBaseNameType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute BASE_NAME_TYPE__TEXT_CONTENT = eINSTANCE.getBaseNameType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute BASE_NAME_TYPE__ID = eINSTANCE.getBaseNameType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.VarTypeImpl <em>Var Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.VarTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getVarType()
		 * @generated
		 */
        EClass VAR_TYPE = eINSTANCE.getVarType();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute VAR_TYPE__TEXT_CONTENT = eINSTANCE.getVarType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute VAR_TYPE__ID = eINSTANCE.getVarType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ViewDeclarationLanguageFactoryTypeImpl <em>View Declaration Language Factory Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ViewDeclarationLanguageFactoryTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getViewDeclarationLanguageFactoryType()
		 * @generated
		 */
		EClass VIEW_DECLARATION_LANGUAGE_FACTORY_TYPE = eINSTANCE.getViewDeclarationLanguageFactoryType();

								/**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW_DECLARATION_LANGUAGE_FACTORY_TYPE__TEXT_CONTENT = eINSTANCE.getViewDeclarationLanguageFactoryType_TextContent();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW_DECLARATION_LANGUAGE_FACTORY_TYPE__ID = eINSTANCE.getViewDeclarationLanguageFactoryType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.VisitContextFactoryTypeImpl <em>Visit Context Factory Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.VisitContextFactoryTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getVisitContextFactoryType()
		 * @generated
		 */
		EClass VISIT_CONTEXT_FACTORY_TYPE = eINSTANCE.getVisitContextFactoryType();

								/**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VISIT_CONTEXT_FACTORY_TYPE__TEXT_CONTENT = eINSTANCE.getVisitContextFactoryType_TextContent();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VISIT_CONTEXT_FACTORY_TYPE__ID = eINSTANCE.getVisitContextFactoryType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitExtensionTypeImpl <em>Render Kit Extension Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitExtensionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getRenderKitExtensionType()
		 * @generated
		 */
        EClass RENDER_KIT_EXTENSION_TYPE = eINSTANCE.getRenderKitExtensionType();

            /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ResourceHandlerTypeImpl <em>Resource Handler Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ResourceHandlerTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getResourceHandlerType()
		 * @generated
		 */
		EClass RESOURCE_HANDLER_TYPE = eINSTANCE.getResourceHandlerType();

								/**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_HANDLER_TYPE__TEXT_CONTENT = eINSTANCE.getResourceHandlerType_TextContent();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_HANDLER_TYPE__ID = eINSTANCE.getResourceHandlerType_Id();

												/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationRuleExtensionTypeImpl <em>Navigation Rule Extension Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.NavigationRuleExtensionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getNavigationRuleExtensionType()
		 * @generated
		 */
        EClass NAVIGATION_RULE_EXTENSION_TYPE = eINSTANCE.getNavigationRuleExtensionType();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorExtensionTypeImpl <em>Validator Extension Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ValidatorExtensionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getValidatorExtensionType()
		 * @generated
		 */
        EClass VALIDATOR_EXTENSION_TYPE = eINSTANCE.getValidatorExtensionType();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigExtensionTypeImpl <em>Extension Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigExtensionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFacesConfigExtensionType()
		 * @generated
		 */
        EClass FACES_CONFIG_EXTENSION_TYPE = eINSTANCE.getFacesConfigExtensionType();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryExtensionTypeImpl <em>Factory Extension Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryExtensionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getFactoryExtensionType()
		 * @generated
		 */
        EClass FACTORY_EXTENSION_TYPE = eINSTANCE.getFactoryExtensionType();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.LifecycleExtensionTypeImpl <em>Lifecycle Extension Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.LifecycleExtensionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getLifecycleExtensionType()
		 * @generated
		 */
        EClass LIFECYCLE_EXTENSION_TYPE = eINSTANCE.getLifecycleExtensionType();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanExtensionTypeImpl <em>Managed Bean Extension Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ManagedBeanExtensionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getManagedBeanExtensionType()
		 * @generated
		 */
        EClass MANAGED_BEAN_EXTENSION_TYPE = eINSTANCE.getManagedBeanExtensionType();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterExtensionTypeImpl <em>Converter Extension Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ConverterExtensionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getConverterExtensionType()
		 * @generated
		 */
        EClass CONVERTER_EXTENSION_TYPE = eINSTANCE.getConverterExtensionType();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ExtensionTypeImpl <em>Extension Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ExtensionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getExtensionType()
		 * @generated
		 */
        EClass EXTENSION_TYPE = eINSTANCE.getExtensionType();

        /**
		 * The meta object literal for the '<em><b>Child Nodes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference EXTENSION_TYPE__CHILD_NODES = eINSTANCE.getExtensionType_ChildNodes();

        /**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute EXTENSION_TYPE__TEXT_CONTENT = eINSTANCE.getExtensionType_TextContent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute EXTENSION_TYPE__ID = eINSTANCE.getExtensionType_Id();

        /**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ExternalContextFactoryTypeImpl <em>External Context Factory Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ExternalContextFactoryTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getExternalContextFactoryType()
		 * @generated
		 */
		EClass EXTERNAL_CONTEXT_FACTORY_TYPE = eINSTANCE.getExternalContextFactoryType();

								/**
		 * The meta object literal for the '<em><b>Text Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_CONTEXT_FACTORY_TYPE__TEXT_CONTENT = eINSTANCE.getExternalContextFactoryType_TextContent();

								/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_CONTEXT_FACTORY_TYPE__ID = eINSTANCE.getExternalContextFactoryType_Id();

								/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationExtensionTypeImpl <em>Application Extension Type</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.ApplicationExtensionTypeImpl
		 * @see org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl#getApplicationExtensionType()
		 * @generated
		 */
        EClass APPLICATION_EXTENSION_TYPE = eINSTANCE.getApplicationExtensionType();

}

} //FacesConfigPackage
