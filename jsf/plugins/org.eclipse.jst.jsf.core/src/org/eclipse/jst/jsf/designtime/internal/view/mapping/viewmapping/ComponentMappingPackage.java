/**
 * <copyright>
 * </copyright>
 *
 * $Id: ComponentMappingPackage.java,v 1.2 2008/11/18 22:24:15 gkessler Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping;

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
 * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingFactory
 * @model kind="package"
 * @generated
 */
public interface ComponentMappingPackage extends EPackage
{
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "viewmapping"; //$NON-NLS-1$

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://org.eclipse.jst.jsf.core/componentMapping.ecore"; //$NON-NLS-1$

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "viewMap"; //$NON-NLS-1$

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ComponentMappingPackage eINSTANCE = org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentMappingPackageImpl.init();

    /**
     * The meta object id for the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.TagMappingImpl <em>Tag Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.TagMappingImpl
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentMappingPackageImpl#getTagMapping()
     * @generated
     */
    int TAG_MAPPING = 0;

    /**
     * The feature id for the '<em><b>Versioned Tag To View Mappings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TAG_MAPPING__VERSIONED_TAG_TO_VIEW_MAPPINGS = 0;

    /**
     * The feature id for the '<em><b>Custom Conversion Factory Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TAG_MAPPING__CUSTOM_CONVERSION_FACTORY_ID = 1;

    /**
     * The feature id for the '<em><b>Bean Mapped Properties</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TAG_MAPPING__BEAN_MAPPED_PROPERTIES = 2;

    /**
     * The number of structural features of the '<em>Tag Mapping</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TAG_MAPPING_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.TagToViewObjectMappingImpl <em>Tag To View Object Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.TagToViewObjectMappingImpl
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentMappingPackageImpl#getTagToViewObjectMapping()
     * @generated
     */
    int TAG_TO_VIEW_OBJECT_MAPPING = 1;

    /**
     * The feature id for the '<em><b>Type Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TAG_TO_VIEW_OBJECT_MAPPING__TYPE_INFO = 0;

    /**
     * The feature id for the '<em><b>Min JSF Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TAG_TO_VIEW_OBJECT_MAPPING__MIN_JSF_VERSION = 1;

    /**
     * The feature id for the '<em><b>Min Library Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TAG_TO_VIEW_OBJECT_MAPPING__MIN_LIBRARY_VERSION = 2;

    /**
     * The number of structural features of the '<em>Tag To View Object Mapping</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TAG_TO_VIEW_OBJECT_MAPPING_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ClassTypeInfo_Impl <em>Class Type Info </em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ClassTypeInfo_Impl
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentMappingPackageImpl#getClassTypeInfo_()
     * @generated
     */
    int CLASS_TYPE_INFO_ = 2;

    /**
     * The feature id for the '<em><b>Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_TYPE_INFO___CLASS_NAME = 0;

    /**
     * The feature id for the '<em><b>Super Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_TYPE_INFO___SUPER_CLASSES = 1;

    /**
     * The feature id for the '<em><b>Interfaces</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_TYPE_INFO___INTERFACES = 2;

    /**
     * The number of structural features of the '<em>Class Type Info </em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_TYPE_INFO__FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentTypeInfo_Impl <em>Component Type Info </em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentTypeInfo_Impl
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentMappingPackageImpl#getComponentTypeInfo_()
     * @generated
     */
    int COMPONENT_TYPE_INFO_ = 3;

    /**
     * The feature id for the '<em><b>Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_TYPE_INFO___CLASS_NAME = CLASS_TYPE_INFO___CLASS_NAME;

    /**
     * The feature id for the '<em><b>Super Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_TYPE_INFO___SUPER_CLASSES = CLASS_TYPE_INFO___SUPER_CLASSES;

    /**
     * The feature id for the '<em><b>Interfaces</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_TYPE_INFO___INTERFACES = CLASS_TYPE_INFO___INTERFACES;

    /**
     * The feature id for the '<em><b>Component Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_TYPE_INFO___COMPONENT_TYPE = CLASS_TYPE_INFO__FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Component Family</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_TYPE_INFO___COMPONENT_FAMILY = CLASS_TYPE_INFO__FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Render Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_TYPE_INFO___RENDER_TYPE = CLASS_TYPE_INFO__FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Component Type Info </em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_TYPE_INFO__FEATURE_COUNT = CLASS_TYPE_INFO__FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ConverterTypeInfo_Impl <em>Converter Type Info </em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ConverterTypeInfo_Impl
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentMappingPackageImpl#getConverterTypeInfo_()
     * @generated
     */
    int CONVERTER_TYPE_INFO_ = 4;

    /**
     * The feature id for the '<em><b>Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONVERTER_TYPE_INFO___CLASS_NAME = CLASS_TYPE_INFO___CLASS_NAME;

    /**
     * The feature id for the '<em><b>Super Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONVERTER_TYPE_INFO___SUPER_CLASSES = CLASS_TYPE_INFO___SUPER_CLASSES;

    /**
     * The feature id for the '<em><b>Interfaces</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONVERTER_TYPE_INFO___INTERFACES = CLASS_TYPE_INFO___INTERFACES;

    /**
     * The feature id for the '<em><b>Converter Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONVERTER_TYPE_INFO___CONVERTER_ID = CLASS_TYPE_INFO__FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>For Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONVERTER_TYPE_INFO___FOR_CLASSES = CLASS_TYPE_INFO__FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Converter Type Info </em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONVERTER_TYPE_INFO__FEATURE_COUNT = CLASS_TYPE_INFO__FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ValidatorTypeInfo_Impl <em>Validator Type Info </em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ValidatorTypeInfo_Impl
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentMappingPackageImpl#getValidatorTypeInfo_()
     * @generated
     */
    int VALIDATOR_TYPE_INFO_ = 5;

    /**
     * The feature id for the '<em><b>Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALIDATOR_TYPE_INFO___CLASS_NAME = CLASS_TYPE_INFO___CLASS_NAME;

    /**
     * The feature id for the '<em><b>Super Classes</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALIDATOR_TYPE_INFO___SUPER_CLASSES = CLASS_TYPE_INFO___SUPER_CLASSES;

    /**
     * The feature id for the '<em><b>Interfaces</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALIDATOR_TYPE_INFO___INTERFACES = CLASS_TYPE_INFO___INTERFACES;

    /**
     * The feature id for the '<em><b>Validator Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALIDATOR_TYPE_INFO___VALIDATOR_ID = CLASS_TYPE_INFO__FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Validator Type Info </em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALIDATOR_TYPE_INFO__FEATURE_COUNT = CLASS_TYPE_INFO__FEATURE_COUNT + 1;


    /**
     * The meta object id for the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.AttributeToPropertyMappingImpl <em>Attribute To Property Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.AttributeToPropertyMappingImpl
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentMappingPackageImpl#getAttributeToPropertyMapping()
     * @generated
     */
    int ATTRIBUTE_TO_PROPERTY_MAPPING = 6;

    /**
     * The feature id for the '<em><b>Property Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE_TO_PROPERTY_MAPPING__PROPERTY_NAME = 0;

    /**
     * The feature id for the '<em><b>El Allowed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE_TO_PROPERTY_MAPPING__EL_ALLOWED = 1;

    /**
     * The feature id for the '<em><b>Custom Conversion Factory Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE_TO_PROPERTY_MAPPING__CUSTOM_CONVERSION_FACTORY_ID = 2;

    /**
     * The number of structural features of the '<em>Attribute To Property Mapping</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ATTRIBUTE_TO_PROPERTY_MAPPING_FEATURE_COUNT = 3;


    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping <em>Tag Mapping</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Tag Mapping</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping
     * @generated
     */
    EClass getTagMapping();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping#getVersionedTagToViewMappings <em>Versioned Tag To View Mappings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Versioned Tag To View Mappings</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping#getVersionedTagToViewMappings()
     * @see #getTagMapping()
     * @generated
     */
    EReference getTagMapping_VersionedTagToViewMappings();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping#getCustomConversionFactoryId <em>Custom Conversion Factory Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Custom Conversion Factory Id</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping#getCustomConversionFactoryId()
     * @see #getTagMapping()
     * @generated
     */
    EAttribute getTagMapping_CustomConversionFactoryId();

    /**
     * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping#getBeanMappedProperties <em>Bean Mapped Properties</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Bean Mapped Properties</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping#getBeanMappedProperties()
     * @see #getTagMapping()
     * @generated
     */
    EAttribute getTagMapping_BeanMappedProperties();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping <em>Tag To View Object Mapping</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Tag To View Object Mapping</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping
     * @generated
     */
    EClass getTagToViewObjectMapping();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping#getTypeInfo <em>Type Info</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Type Info</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping#getTypeInfo()
     * @see #getTagToViewObjectMapping()
     * @generated
     */
    EReference getTagToViewObjectMapping_TypeInfo();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping#getMinJSFVersion <em>Min JSF Version</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Min JSF Version</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping#getMinJSFVersion()
     * @see #getTagToViewObjectMapping()
     * @generated
     */
    EAttribute getTagToViewObjectMapping_MinJSFVersion();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping#getMinLibraryVersion <em>Min Library Version</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Min Library Version</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping#getMinLibraryVersion()
     * @see #getTagToViewObjectMapping()
     * @generated
     */
    EAttribute getTagToViewObjectMapping_MinLibraryVersion();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ClassTypeInfo_ <em>Class Type Info </em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Class Type Info </em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ClassTypeInfo_
     * @generated
     */
    EClass getClassTypeInfo_();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ClassTypeInfo_#getClassName <em>Class Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Class Name</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ClassTypeInfo_#getClassName()
     * @see #getClassTypeInfo_()
     * @generated
     */
    EAttribute getClassTypeInfo__ClassName();

    /**
     * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ClassTypeInfo_#getSuperClasses <em>Super Classes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Super Classes</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ClassTypeInfo_#getSuperClasses()
     * @see #getClassTypeInfo_()
     * @generated
     */
    EAttribute getClassTypeInfo__SuperClasses();

    /**
     * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ClassTypeInfo_#getInterfaces <em>Interfaces</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Interfaces</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ClassTypeInfo_#getInterfaces()
     * @see #getClassTypeInfo_()
     * @generated
     */
    EAttribute getClassTypeInfo__Interfaces();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_ <em>Component Type Info </em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Component Type Info </em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_
     * @generated
     */
    EClass getComponentTypeInfo_();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_#getComponentType <em>Component Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Component Type</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_#getComponentType()
     * @see #getComponentTypeInfo_()
     * @generated
     */
    EAttribute getComponentTypeInfo__ComponentType();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_#getComponentFamily <em>Component Family</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Component Family</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_#getComponentFamily()
     * @see #getComponentTypeInfo_()
     * @generated
     */
    EAttribute getComponentTypeInfo__ComponentFamily();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_#getRenderType <em>Render Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Render Type</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_#getRenderType()
     * @see #getComponentTypeInfo_()
     * @generated
     */
    EAttribute getComponentTypeInfo__RenderType();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ConverterTypeInfo_ <em>Converter Type Info </em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Converter Type Info </em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ConverterTypeInfo_
     * @generated
     */
    EClass getConverterTypeInfo_();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ConverterTypeInfo_#getConverterId <em>Converter Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Converter Id</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ConverterTypeInfo_#getConverterId()
     * @see #getConverterTypeInfo_()
     * @generated
     */
    EAttribute getConverterTypeInfo__ConverterId();

    /**
     * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ConverterTypeInfo_#getForClasses <em>For Classes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>For Classes</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ConverterTypeInfo_#getForClasses()
     * @see #getConverterTypeInfo_()
     * @generated
     */
    EAttribute getConverterTypeInfo__ForClasses();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ValidatorTypeInfo_ <em>Validator Type Info </em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Validator Type Info </em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ValidatorTypeInfo_
     * @generated
     */
    EClass getValidatorTypeInfo_();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ValidatorTypeInfo_#getValidatorId <em>Validator Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Validator Id</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ValidatorTypeInfo_#getValidatorId()
     * @see #getValidatorTypeInfo_()
     * @generated
     */
    EAttribute getValidatorTypeInfo__ValidatorId();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping <em>Attribute To Property Mapping</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Attribute To Property Mapping</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping
     * @generated
     */
    EClass getAttributeToPropertyMapping();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping#getPropertyName <em>Property Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Property Name</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping#getPropertyName()
     * @see #getAttributeToPropertyMapping()
     * @generated
     */
    EAttribute getAttributeToPropertyMapping_PropertyName();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping#isElAllowed <em>El Allowed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>El Allowed</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping#isElAllowed()
     * @see #getAttributeToPropertyMapping()
     * @generated
     */
    EAttribute getAttributeToPropertyMapping_ElAllowed();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping#getCustomConversionFactoryId <em>Custom Conversion Factory Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Custom Conversion Factory Id</em>'.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping#getCustomConversionFactoryId()
     * @see #getAttributeToPropertyMapping()
     * @generated
     */
    EAttribute getAttributeToPropertyMapping_CustomConversionFactoryId();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ComponentMappingFactory getComponentMappingFactory();

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
    @SuppressWarnings("hiding")
    interface Literals
    {
        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.TagMappingImpl <em>Tag Mapping</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.TagMappingImpl
         * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentMappingPackageImpl#getTagMapping()
         * @generated
         */
        EClass TAG_MAPPING = eINSTANCE.getTagMapping();

        /**
         * The meta object literal for the '<em><b>Versioned Tag To View Mappings</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TAG_MAPPING__VERSIONED_TAG_TO_VIEW_MAPPINGS = eINSTANCE.getTagMapping_VersionedTagToViewMappings();

        /**
         * The meta object literal for the '<em><b>Custom Conversion Factory Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TAG_MAPPING__CUSTOM_CONVERSION_FACTORY_ID = eINSTANCE.getTagMapping_CustomConversionFactoryId();

        /**
         * The meta object literal for the '<em><b>Bean Mapped Properties</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TAG_MAPPING__BEAN_MAPPED_PROPERTIES = eINSTANCE.getTagMapping_BeanMappedProperties();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.TagToViewObjectMappingImpl <em>Tag To View Object Mapping</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.TagToViewObjectMappingImpl
         * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentMappingPackageImpl#getTagToViewObjectMapping()
         * @generated
         */
        EClass TAG_TO_VIEW_OBJECT_MAPPING = eINSTANCE.getTagToViewObjectMapping();

        /**
         * The meta object literal for the '<em><b>Type Info</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TAG_TO_VIEW_OBJECT_MAPPING__TYPE_INFO = eINSTANCE.getTagToViewObjectMapping_TypeInfo();

        /**
         * The meta object literal for the '<em><b>Min JSF Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TAG_TO_VIEW_OBJECT_MAPPING__MIN_JSF_VERSION = eINSTANCE.getTagToViewObjectMapping_MinJSFVersion();

        /**
         * The meta object literal for the '<em><b>Min Library Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TAG_TO_VIEW_OBJECT_MAPPING__MIN_LIBRARY_VERSION = eINSTANCE.getTagToViewObjectMapping_MinLibraryVersion();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ClassTypeInfo_Impl <em>Class Type Info </em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ClassTypeInfo_Impl
         * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentMappingPackageImpl#getClassTypeInfo_()
         * @generated
         */
        EClass CLASS_TYPE_INFO_ = eINSTANCE.getClassTypeInfo_();

        /**
         * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CLASS_TYPE_INFO___CLASS_NAME = eINSTANCE.getClassTypeInfo__ClassName();

        /**
         * The meta object literal for the '<em><b>Super Classes</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CLASS_TYPE_INFO___SUPER_CLASSES = eINSTANCE.getClassTypeInfo__SuperClasses();

        /**
         * The meta object literal for the '<em><b>Interfaces</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CLASS_TYPE_INFO___INTERFACES = eINSTANCE.getClassTypeInfo__Interfaces();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentTypeInfo_Impl <em>Component Type Info </em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentTypeInfo_Impl
         * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentMappingPackageImpl#getComponentTypeInfo_()
         * @generated
         */
        EClass COMPONENT_TYPE_INFO_ = eINSTANCE.getComponentTypeInfo_();

        /**
         * The meta object literal for the '<em><b>Component Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COMPONENT_TYPE_INFO___COMPONENT_TYPE = eINSTANCE.getComponentTypeInfo__ComponentType();

        /**
         * The meta object literal for the '<em><b>Component Family</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COMPONENT_TYPE_INFO___COMPONENT_FAMILY = eINSTANCE.getComponentTypeInfo__ComponentFamily();

        /**
         * The meta object literal for the '<em><b>Render Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute COMPONENT_TYPE_INFO___RENDER_TYPE = eINSTANCE.getComponentTypeInfo__RenderType();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ConverterTypeInfo_Impl <em>Converter Type Info </em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ConverterTypeInfo_Impl
         * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentMappingPackageImpl#getConverterTypeInfo_()
         * @generated
         */
        EClass CONVERTER_TYPE_INFO_ = eINSTANCE.getConverterTypeInfo_();

        /**
         * The meta object literal for the '<em><b>Converter Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CONVERTER_TYPE_INFO___CONVERTER_ID = eINSTANCE.getConverterTypeInfo__ConverterId();

        /**
         * The meta object literal for the '<em><b>For Classes</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CONVERTER_TYPE_INFO___FOR_CLASSES = eINSTANCE.getConverterTypeInfo__ForClasses();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ValidatorTypeInfo_Impl <em>Validator Type Info </em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ValidatorTypeInfo_Impl
         * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentMappingPackageImpl#getValidatorTypeInfo_()
         * @generated
         */
        EClass VALIDATOR_TYPE_INFO_ = eINSTANCE.getValidatorTypeInfo_();

        /**
         * The meta object literal for the '<em><b>Validator Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute VALIDATOR_TYPE_INFO___VALIDATOR_ID = eINSTANCE.getValidatorTypeInfo__ValidatorId();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.AttributeToPropertyMappingImpl <em>Attribute To Property Mapping</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.AttributeToPropertyMappingImpl
         * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentMappingPackageImpl#getAttributeToPropertyMapping()
         * @generated
         */
        EClass ATTRIBUTE_TO_PROPERTY_MAPPING = eINSTANCE.getAttributeToPropertyMapping();

        /**
         * The meta object literal for the '<em><b>Property Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ATTRIBUTE_TO_PROPERTY_MAPPING__PROPERTY_NAME = eINSTANCE.getAttributeToPropertyMapping_PropertyName();

        /**
         * The meta object literal for the '<em><b>El Allowed</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ATTRIBUTE_TO_PROPERTY_MAPPING__EL_ALLOWED = eINSTANCE.getAttributeToPropertyMapping_ElAllowed();

        /**
         * The meta object literal for the '<em><b>Custom Conversion Factory Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ATTRIBUTE_TO_PROPERTY_MAPPING__CUSTOM_CONVERSION_FACTORY_ID = eINSTANCE.getAttributeToPropertyMapping_CustomConversionFactoryId();

    }

} //ComponentMappingPackage
