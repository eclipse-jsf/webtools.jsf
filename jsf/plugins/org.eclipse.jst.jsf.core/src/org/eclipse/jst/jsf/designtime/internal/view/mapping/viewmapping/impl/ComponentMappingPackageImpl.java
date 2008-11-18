/**
 * <copyright>
 * </copyright>
 *
 * $Id: ComponentMappingPackageImpl.java,v 1.2 2008/11/18 22:23:57 gkessler Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ClassTypeInfo_;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingFactory;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ConverterTypeInfo_;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ValidatorTypeInfo_;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentMappingPackageImpl extends EPackageImpl implements ComponentMappingPackage
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass tagMappingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass tagToViewObjectMappingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass classTypeInfo_EClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass componentTypeInfo_EClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass converterTypeInfo_EClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass validatorTypeInfo_EClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass attributeToPropertyMappingEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ComponentMappingPackageImpl()
    {
        super(eNS_URI, ComponentMappingFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this
     * model, and for any others upon which it depends.  Simple
     * dependencies are satisfied by calling this method on all
     * dependent packages before doing anything else.  This method drives
     * initialization for interdependent packages directly, in parallel
     * with this package, itself.
     * <p>Of this package and its interdependencies, all packages which
     * have not yet been registered by their URI values are first created
     * and registered.  The packages are then initialized in two steps:
     * meta-model objects for all of the packages are created before any
     * are initialized, since one package's meta-model objects may refer to
     * those of another.
     * <p>Invocation of this method will not affect any packages that have
     * already been initialized.
     * <!-- begin-user-doc -->
     * @return the initialized package 
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ComponentMappingPackage init()
    {
        if (isInited) return (ComponentMappingPackage)EPackage.Registry.INSTANCE.getEPackage(ComponentMappingPackage.eNS_URI);

        // Obtain or create and register package
        ComponentMappingPackageImpl theComponentMappingPackage = (ComponentMappingPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof ComponentMappingPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ComponentMappingPackageImpl());

        isInited = true;

        // Create package meta-data objects
        theComponentMappingPackage.createPackageContents();

        // Initialize created meta-data
        theComponentMappingPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theComponentMappingPackage.freeze();

        return theComponentMappingPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTagMapping()
    {
        return tagMappingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTagMapping_VersionedTagToViewMappings()
    {
        return (EReference)tagMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTagMapping_CustomConversionFactoryId()
    {
        return (EAttribute)tagMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTagMapping_BeanMappedProperties()
    {
        return (EAttribute)tagMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTagToViewObjectMapping()
    {
        return tagToViewObjectMappingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTagToViewObjectMapping_TypeInfo()
    {
        return (EReference)tagToViewObjectMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTagToViewObjectMapping_MinJSFVersion()
    {
        return (EAttribute)tagToViewObjectMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTagToViewObjectMapping_MinLibraryVersion()
    {
        return (EAttribute)tagToViewObjectMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getClassTypeInfo_()
    {
        return classTypeInfo_EClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getClassTypeInfo__ClassName()
    {
        return (EAttribute)classTypeInfo_EClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getClassTypeInfo__SuperClasses()
    {
        return (EAttribute)classTypeInfo_EClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getClassTypeInfo__Interfaces()
    {
        return (EAttribute)classTypeInfo_EClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getComponentTypeInfo_()
    {
        return componentTypeInfo_EClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponentTypeInfo__ComponentType()
    {
        return (EAttribute)componentTypeInfo_EClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponentTypeInfo__ComponentFamily()
    {
        return (EAttribute)componentTypeInfo_EClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponentTypeInfo__RenderType()
    {
        return (EAttribute)componentTypeInfo_EClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getConverterTypeInfo_()
    {
        return converterTypeInfo_EClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getConverterTypeInfo__ConverterId()
    {
        return (EAttribute)converterTypeInfo_EClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getConverterTypeInfo__ForClasses()
    {
        return (EAttribute)converterTypeInfo_EClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getValidatorTypeInfo_()
    {
        return validatorTypeInfo_EClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getValidatorTypeInfo__ValidatorId()
    {
        return (EAttribute)validatorTypeInfo_EClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAttributeToPropertyMapping()
    {
        return attributeToPropertyMappingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAttributeToPropertyMapping_PropertyName()
    {
        return (EAttribute)attributeToPropertyMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAttributeToPropertyMapping_ElAllowed()
    {
        return (EAttribute)attributeToPropertyMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAttributeToPropertyMapping_CustomConversionFactoryId()
    {
        return (EAttribute)attributeToPropertyMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentMappingFactory getComponentMappingFactory()
    {
        return (ComponentMappingFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents()
    {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        tagMappingEClass = createEClass(TAG_MAPPING);
        createEReference(tagMappingEClass, TAG_MAPPING__VERSIONED_TAG_TO_VIEW_MAPPINGS);
        createEAttribute(tagMappingEClass, TAG_MAPPING__CUSTOM_CONVERSION_FACTORY_ID);
        createEAttribute(tagMappingEClass, TAG_MAPPING__BEAN_MAPPED_PROPERTIES);

        tagToViewObjectMappingEClass = createEClass(TAG_TO_VIEW_OBJECT_MAPPING);
        createEReference(tagToViewObjectMappingEClass, TAG_TO_VIEW_OBJECT_MAPPING__TYPE_INFO);
        createEAttribute(tagToViewObjectMappingEClass, TAG_TO_VIEW_OBJECT_MAPPING__MIN_JSF_VERSION);
        createEAttribute(tagToViewObjectMappingEClass, TAG_TO_VIEW_OBJECT_MAPPING__MIN_LIBRARY_VERSION);

        classTypeInfo_EClass = createEClass(CLASS_TYPE_INFO_);
        createEAttribute(classTypeInfo_EClass, CLASS_TYPE_INFO___CLASS_NAME);
        createEAttribute(classTypeInfo_EClass, CLASS_TYPE_INFO___SUPER_CLASSES);
        createEAttribute(classTypeInfo_EClass, CLASS_TYPE_INFO___INTERFACES);

        componentTypeInfo_EClass = createEClass(COMPONENT_TYPE_INFO_);
        createEAttribute(componentTypeInfo_EClass, COMPONENT_TYPE_INFO___COMPONENT_TYPE);
        createEAttribute(componentTypeInfo_EClass, COMPONENT_TYPE_INFO___COMPONENT_FAMILY);
        createEAttribute(componentTypeInfo_EClass, COMPONENT_TYPE_INFO___RENDER_TYPE);

        converterTypeInfo_EClass = createEClass(CONVERTER_TYPE_INFO_);
        createEAttribute(converterTypeInfo_EClass, CONVERTER_TYPE_INFO___CONVERTER_ID);
        createEAttribute(converterTypeInfo_EClass, CONVERTER_TYPE_INFO___FOR_CLASSES);

        validatorTypeInfo_EClass = createEClass(VALIDATOR_TYPE_INFO_);
        createEAttribute(validatorTypeInfo_EClass, VALIDATOR_TYPE_INFO___VALIDATOR_ID);

        attributeToPropertyMappingEClass = createEClass(ATTRIBUTE_TO_PROPERTY_MAPPING);
        createEAttribute(attributeToPropertyMappingEClass, ATTRIBUTE_TO_PROPERTY_MAPPING__PROPERTY_NAME);
        createEAttribute(attributeToPropertyMappingEClass, ATTRIBUTE_TO_PROPERTY_MAPPING__EL_ALLOWED);
        createEAttribute(attributeToPropertyMappingEClass, ATTRIBUTE_TO_PROPERTY_MAPPING__CUSTOM_CONVERSION_FACTORY_ID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents()
    {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        componentTypeInfo_EClass.getESuperTypes().add(this.getClassTypeInfo_());
        converterTypeInfo_EClass.getESuperTypes().add(this.getClassTypeInfo_());
        validatorTypeInfo_EClass.getESuperTypes().add(this.getClassTypeInfo_());

        // Initialize classes and features; add operations and parameters
        initEClass(tagMappingEClass, TagMapping.class, "TagMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getTagMapping_VersionedTagToViewMappings(), this.getTagToViewObjectMapping(), null, "versionedTagToViewMappings", null, 0, -1, TagMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getTagMapping_CustomConversionFactoryId(), ecorePackage.getEString(), "customConversionFactoryId", null, 0, 1, TagMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getTagMapping_BeanMappedProperties(), ecorePackage.getEString(), "beanMappedProperties", "", 0, -1, TagMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        EOperation op = addEOperation(tagMappingEClass, null, "findBestMapping", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, ecorePackage.getEString(), "jsfVersion", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, ecorePackage.getEString(), "libVersion", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

        initEClass(tagToViewObjectMappingEClass, TagToViewObjectMapping.class, "TagToViewObjectMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getTagToViewObjectMapping_TypeInfo(), this.getClassTypeInfo_(), null, "typeInfo", null, 0, 1, TagToViewObjectMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getTagToViewObjectMapping_MinJSFVersion(), ecorePackage.getEString(), "minJSFVersion", "1.1", 0, 1, TagToViewObjectMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getTagToViewObjectMapping_MinLibraryVersion(), ecorePackage.getEString(), "minLibraryVersion", "", 0, 1, TagToViewObjectMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(classTypeInfo_EClass, ClassTypeInfo_.class, "ClassTypeInfo_", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getClassTypeInfo__ClassName(), ecorePackage.getEString(), "className", null, 0, 1, ClassTypeInfo_.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getClassTypeInfo__SuperClasses(), ecorePackage.getEString(), "superClasses", null, 0, -1, ClassTypeInfo_.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getClassTypeInfo__Interfaces(), ecorePackage.getEString(), "interfaces", null, 0, -1, ClassTypeInfo_.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(componentTypeInfo_EClass, ComponentTypeInfo_.class, "ComponentTypeInfo_", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getComponentTypeInfo__ComponentType(), ecorePackage.getEString(), "componentType", null, 0, 1, ComponentTypeInfo_.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getComponentTypeInfo__ComponentFamily(), ecorePackage.getEString(), "componentFamily", null, 0, 1, ComponentTypeInfo_.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getComponentTypeInfo__RenderType(), ecorePackage.getEString(), "renderType", null, 0, 1, ComponentTypeInfo_.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(converterTypeInfo_EClass, ConverterTypeInfo_.class, "ConverterTypeInfo_", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getConverterTypeInfo__ConverterId(), ecorePackage.getEString(), "converterId", null, 0, 1, ConverterTypeInfo_.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConverterTypeInfo__ForClasses(), ecorePackage.getEString(), "forClasses", null, 0, -1, ConverterTypeInfo_.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(validatorTypeInfo_EClass, ValidatorTypeInfo_.class, "ValidatorTypeInfo_", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getValidatorTypeInfo__ValidatorId(), ecorePackage.getEString(), "validatorId", null, 0, 1, ValidatorTypeInfo_.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(attributeToPropertyMappingEClass, AttributeToPropertyMapping.class, "AttributeToPropertyMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getAttributeToPropertyMapping_PropertyName(), ecorePackage.getEString(), "propertyName", null, 0, 1, AttributeToPropertyMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getAttributeToPropertyMapping_ElAllowed(), ecorePackage.getEBoolean(), "elAllowed", "true", 0, 1, AttributeToPropertyMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getAttributeToPropertyMapping_CustomConversionFactoryId(), ecorePackage.getEString(), "customConversionFactoryId", null, 0, 1, AttributeToPropertyMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createExtendedMetaDataAnnotations()
    {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		 //$NON-NLS-1$
        addAnnotation
          (getTagMapping_VersionedTagToViewMappings(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "versionedTagToViewMappings" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getTagMapping_CustomConversionFactoryId(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "customConversionFactoryId" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getTagMapping_BeanMappedProperties(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "beanMappedProperties" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getTagToViewObjectMapping_TypeInfo(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "typeInfo" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getTagToViewObjectMapping_MinJSFVersion(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "minVersion" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getTagToViewObjectMapping_MinLibraryVersion(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "minLibraryVersion" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getClassTypeInfo__ClassName(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "className" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getClassTypeInfo__SuperClasses(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "superClasses" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getClassTypeInfo__Interfaces(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "interfaces" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentTypeInfo__ComponentType(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "componentType" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentTypeInfo__ComponentFamily(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "componentFamily" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentTypeInfo__RenderType(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "renderType" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterTypeInfo__ConverterId(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "converterId" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterTypeInfo__ForClasses(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "forClass" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValidatorTypeInfo__ValidatorId(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "validatorId" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getAttributeToPropertyMapping_PropertyName(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "propertyName" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getAttributeToPropertyMapping_ElAllowed(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "elAllowed" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getAttributeToPropertyMapping_CustomConversionFactoryId(), 
           source, 
           new String[] 
           {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "customConversionFactoryId" //$NON-NLS-1$ //$NON-NLS-2$
           });
    }

} //ComponentMappingPackageImpl
