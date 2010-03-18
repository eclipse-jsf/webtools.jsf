/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglib_1_0PackageImpl.java,v 1.1 2010/03/18 06:24:27 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ComponentTagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ConverterTagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletLibraryClassTagLib;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglibDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Factory;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletXMLDefnTaglib;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FunctionDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.HandlerTagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.SourceTagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.TagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ValidatorTagDefn;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FaceletTaglib_1_0PackageImpl extends EPackageImpl implements FaceletTaglib_1_0Package
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass faceletLibraryClassTagLibEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass faceletXMLDefnTaglibEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass faceletTaglibDefnEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass componentTagDefnEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass validatorTagDefnEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass converterTagDefnEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass handlerTagDefnEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass sourceTagDefnEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass tagDefnEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass functionDefnEClass = null;

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
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package#eNS_URI
     * @see #init()
     * @generated
     */
    private FaceletTaglib_1_0PackageImpl()
    {
        super(eNS_URI, FaceletTaglib_1_0Factory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link FaceletTaglib_1_0Package#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * @return the package.
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static FaceletTaglib_1_0Package init()
    {
        if (isInited) return (FaceletTaglib_1_0Package)EPackage.Registry.INSTANCE.getEPackage(FaceletTaglib_1_0Package.eNS_URI);

        // Obtain or create and register package
        FaceletTaglib_1_0PackageImpl theFaceletTaglib_1_0Package = (FaceletTaglib_1_0PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof FaceletTaglib_1_0PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new FaceletTaglib_1_0PackageImpl());

        isInited = true;

        // Create package meta-data objects
        theFaceletTaglib_1_0Package.createPackageContents();

        // Initialize created meta-data
        theFaceletTaglib_1_0Package.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theFaceletTaglib_1_0Package.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(FaceletTaglib_1_0Package.eNS_URI, theFaceletTaglib_1_0Package);
        return theFaceletTaglib_1_0Package;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFaceletLibraryClassTagLib()
    {
        return faceletLibraryClassTagLibEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFaceletLibraryClassTagLib_LibraryClass()
    {
        return (EAttribute)faceletLibraryClassTagLibEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFaceletXMLDefnTaglib()
    {
        return faceletXMLDefnTaglibEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFaceletXMLDefnTaglib_Namespace()
    {
        return (EAttribute)faceletXMLDefnTaglibEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFaceletXMLDefnTaglib_Tags()
    {
        return (EReference)faceletXMLDefnTaglibEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFaceletXMLDefnTaglib_Functions()
    {
        return (EReference)faceletXMLDefnTaglibEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFaceletTaglibDefn()
    {
        return faceletTaglibDefnEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getComponentTagDefn()
    {
        return componentTagDefnEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponentTagDefn_ComponentType()
    {
        return (EAttribute)componentTagDefnEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponentTagDefn_RendererType()
    {
        return (EAttribute)componentTagDefnEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getValidatorTagDefn()
    {
        return validatorTagDefnEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getValidatorTagDefn_ValidatorId()
    {
        return (EAttribute)validatorTagDefnEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getConverterTagDefn()
    {
        return converterTagDefnEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getConverterTagDefn_ConverterId()
    {
        return (EAttribute)converterTagDefnEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getHandlerTagDefn()
    {
        return handlerTagDefnEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getHandlerTagDefn_HandlerClass()
    {
        return (EAttribute)handlerTagDefnEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSourceTagDefn()
    {
        return sourceTagDefnEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSourceTagDefn_Source()
    {
        return (EAttribute)sourceTagDefnEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTagDefn()
    {
        return tagDefnEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTagDefn_Name()
    {
        return (EAttribute)tagDefnEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFunctionDefn()
    {
        return functionDefnEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFunctionDefn_FunctionName()
    {
        return (EAttribute)functionDefnEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFunctionDefn_FunctionClass()
    {
        return (EAttribute)functionDefnEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFunctionDefn_FunctionSignature()
    {
        return (EAttribute)functionDefnEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FaceletTaglib_1_0Factory getFaceletTaglib_1_0Factory()
    {
        return (FaceletTaglib_1_0Factory)getEFactoryInstance();
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
        faceletLibraryClassTagLibEClass = createEClass(FACELET_LIBRARY_CLASS_TAG_LIB);
        createEAttribute(faceletLibraryClassTagLibEClass, FACELET_LIBRARY_CLASS_TAG_LIB__LIBRARY_CLASS);

        faceletXMLDefnTaglibEClass = createEClass(FACELET_XML_DEFN_TAGLIB);
        createEAttribute(faceletXMLDefnTaglibEClass, FACELET_XML_DEFN_TAGLIB__NAMESPACE);
        createEReference(faceletXMLDefnTaglibEClass, FACELET_XML_DEFN_TAGLIB__TAGS);
        createEReference(faceletXMLDefnTaglibEClass, FACELET_XML_DEFN_TAGLIB__FUNCTIONS);

        faceletTaglibDefnEClass = createEClass(FACELET_TAGLIB_DEFN);

        componentTagDefnEClass = createEClass(COMPONENT_TAG_DEFN);
        createEAttribute(componentTagDefnEClass, COMPONENT_TAG_DEFN__COMPONENT_TYPE);
        createEAttribute(componentTagDefnEClass, COMPONENT_TAG_DEFN__RENDERER_TYPE);

        validatorTagDefnEClass = createEClass(VALIDATOR_TAG_DEFN);
        createEAttribute(validatorTagDefnEClass, VALIDATOR_TAG_DEFN__VALIDATOR_ID);

        converterTagDefnEClass = createEClass(CONVERTER_TAG_DEFN);
        createEAttribute(converterTagDefnEClass, CONVERTER_TAG_DEFN__CONVERTER_ID);

        handlerTagDefnEClass = createEClass(HANDLER_TAG_DEFN);
        createEAttribute(handlerTagDefnEClass, HANDLER_TAG_DEFN__HANDLER_CLASS);

        sourceTagDefnEClass = createEClass(SOURCE_TAG_DEFN);
        createEAttribute(sourceTagDefnEClass, SOURCE_TAG_DEFN__SOURCE);

        tagDefnEClass = createEClass(TAG_DEFN);
        createEAttribute(tagDefnEClass, TAG_DEFN__NAME);

        functionDefnEClass = createEClass(FUNCTION_DEFN);
        createEAttribute(functionDefnEClass, FUNCTION_DEFN__FUNCTION_NAME);
        createEAttribute(functionDefnEClass, FUNCTION_DEFN__FUNCTION_CLASS);
        createEAttribute(functionDefnEClass, FUNCTION_DEFN__FUNCTION_SIGNATURE);
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
        faceletLibraryClassTagLibEClass.getESuperTypes().add(this.getFaceletTaglibDefn());
        faceletXMLDefnTaglibEClass.getESuperTypes().add(this.getFaceletTaglibDefn());
        componentTagDefnEClass.getESuperTypes().add(this.getHandlerTagDefn());
        validatorTagDefnEClass.getESuperTypes().add(this.getHandlerTagDefn());
        converterTagDefnEClass.getESuperTypes().add(this.getHandlerTagDefn());
        handlerTagDefnEClass.getESuperTypes().add(this.getTagDefn());
        sourceTagDefnEClass.getESuperTypes().add(this.getTagDefn());

        // Initialize classes and features; add operations and parameters
        initEClass(faceletLibraryClassTagLibEClass, FaceletLibraryClassTagLib.class, "FaceletLibraryClassTagLib", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getFaceletLibraryClassTagLib_LibraryClass(), ecorePackage.getEString(), "libraryClass", null, 0, 1, FaceletLibraryClassTagLib.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(faceletXMLDefnTaglibEClass, FaceletXMLDefnTaglib.class, "FaceletXMLDefnTaglib", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getFaceletXMLDefnTaglib_Namespace(), ecorePackage.getEString(), "namespace", null, 0, 1, FaceletXMLDefnTaglib.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFaceletXMLDefnTaglib_Tags(), this.getTagDefn(), null, "tags", null, 0, -1, FaceletXMLDefnTaglib.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFaceletXMLDefnTaglib_Functions(), this.getFunctionDefn(), null, "functions", null, 0, -1, FaceletXMLDefnTaglib.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(faceletTaglibDefnEClass, FaceletTaglibDefn.class, "FaceletTaglibDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(componentTagDefnEClass, ComponentTagDefn.class, "ComponentTagDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getComponentTagDefn_ComponentType(), ecorePackage.getEString(), "componentType", null, 0, 1, ComponentTagDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getComponentTagDefn_RendererType(), ecorePackage.getEString(), "rendererType", null, 0, 1, ComponentTagDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(validatorTagDefnEClass, ValidatorTagDefn.class, "ValidatorTagDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getValidatorTagDefn_ValidatorId(), ecorePackage.getEString(), "validatorId", null, 0, 1, ValidatorTagDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(converterTagDefnEClass, ConverterTagDefn.class, "ConverterTagDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getConverterTagDefn_ConverterId(), ecorePackage.getEString(), "converterId", null, 0, 1, ConverterTagDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(handlerTagDefnEClass, HandlerTagDefn.class, "HandlerTagDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getHandlerTagDefn_HandlerClass(), ecorePackage.getEString(), "handlerClass", null, 0, 1, HandlerTagDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(sourceTagDefnEClass, SourceTagDefn.class, "SourceTagDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSourceTagDefn_Source(), ecorePackage.getEString(), "source", null, 0, 1, SourceTagDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(tagDefnEClass, TagDefn.class, "TagDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getTagDefn_Name(), ecorePackage.getEString(), "name", null, 0, 1, TagDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(functionDefnEClass, FunctionDefn.class, "FunctionDefn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getFunctionDefn_FunctionName(), ecorePackage.getEString(), "functionName", null, 0, 1, FunctionDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getFunctionDefn_FunctionClass(), ecorePackage.getEString(), "functionClass", null, 0, 1, FunctionDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getFunctionDefn_FunctionSignature(), ecorePackage.getEString(), "functionSignature", null, 0, 1, FunctionDefn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        // Create resource
        createResource(eNS_URI);
    }

} //FaceletTaglib_1_0PackageImpl
