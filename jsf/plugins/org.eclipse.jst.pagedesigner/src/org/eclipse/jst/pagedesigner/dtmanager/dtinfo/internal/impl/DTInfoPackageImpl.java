/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfo;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoFactory;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.Operation;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.Parameter;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.ResolveAttributeValue;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagConvertInfo;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DTInfoPackageImpl extends EPackageImpl implements DTInfoPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dtInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tagConvertInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tagDecorateInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resolveAttributeValueEClass = null;

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
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DTInfoPackageImpl() {
		super(eNS_URI, DTInfoFactory.eINSTANCE);
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
     * @return the package 
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DTInfoPackage init() {
		if (isInited) return (DTInfoPackage)EPackage.Registry.INSTANCE.getEPackage(DTInfoPackage.eNS_URI);

		// Obtain or create and register package
		DTInfoPackageImpl theDTInfoPackage = (DTInfoPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof DTInfoPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new DTInfoPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theDTInfoPackage.createPackageContents();

		// Initialize created meta-data
		theDTInfoPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDTInfoPackage.freeze();

		return theDTInfoPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDTInfo() {
		return dtInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDTInfo_TagConvertInfo() {
		return (EReference)dtInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDTInfo_TagDecorateInfos() {
		return (EReference)dtInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTagConvertInfo() {
		return tagConvertInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTagConvertInfo_Operations() {
		return (EReference)tagConvertInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperation() {
		return operationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperation_Id() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperation_Parameters() {
		return (EReference)operationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperation_Operations() {
		return (EReference)operationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameter() {
		return parameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParameter_Value() {
		return (EAttribute)parameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTagDecorateInfo() {
		return tagDecorateInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagDecorateInfo_Id() {
		return (EAttribute)tagDecorateInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagDecorateInfo_MinHeight() {
		return (EAttribute)tagDecorateInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagDecorateInfo_MinWidth() {
		return (EAttribute)tagDecorateInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagDecorateInfo_MultiLevel() {
		return (EAttribute)tagDecorateInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagDecorateInfo_NeedBorderDecorator() {
		return (EAttribute)tagDecorateInfoEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagDecorateInfo_NeedTableDecorator() {
		return (EAttribute)tagDecorateInfoEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagDecorateInfo_NonVisual() {
		return (EAttribute)tagDecorateInfoEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagDecorateInfo_NonVisualImagePath() {
		return (EAttribute)tagDecorateInfoEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagDecorateInfo_ResolveChildText() {
		return (EAttribute)tagDecorateInfoEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTagDecorateInfo_ResolveAttributeValue() {
		return (EReference)tagDecorateInfoEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagDecorateInfo_SetNonVisualChildElements() {
		return (EAttribute)tagDecorateInfoEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagDecorateInfo_Widget() {
		return (EAttribute)tagDecorateInfoEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResolveAttributeValue() {
		return resolveAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResolveAttributeValue_AttributeName() {
		return (EAttribute)resolveAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DTInfoFactory getDTInfoFactory() {
		return (DTInfoFactory)getEFactoryInstance();
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
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		dtInfoEClass = createEClass(DT_INFO);
		createEReference(dtInfoEClass, DT_INFO__TAG_CONVERT_INFO);
		createEReference(dtInfoEClass, DT_INFO__TAG_DECORATE_INFOS);

		tagConvertInfoEClass = createEClass(TAG_CONVERT_INFO);
		createEReference(tagConvertInfoEClass, TAG_CONVERT_INFO__OPERATIONS);

		operationEClass = createEClass(OPERATION);
		createEAttribute(operationEClass, OPERATION__ID);
		createEReference(operationEClass, OPERATION__PARAMETERS);
		createEReference(operationEClass, OPERATION__OPERATIONS);

		parameterEClass = createEClass(PARAMETER);
		createEAttribute(parameterEClass, PARAMETER__VALUE);

		tagDecorateInfoEClass = createEClass(TAG_DECORATE_INFO);
		createEAttribute(tagDecorateInfoEClass, TAG_DECORATE_INFO__ID);
		createEAttribute(tagDecorateInfoEClass, TAG_DECORATE_INFO__MIN_HEIGHT);
		createEAttribute(tagDecorateInfoEClass, TAG_DECORATE_INFO__MIN_WIDTH);
		createEAttribute(tagDecorateInfoEClass, TAG_DECORATE_INFO__MULTI_LEVEL);
		createEAttribute(tagDecorateInfoEClass, TAG_DECORATE_INFO__NEED_BORDER_DECORATOR);
		createEAttribute(tagDecorateInfoEClass, TAG_DECORATE_INFO__NEED_TABLE_DECORATOR);
		createEAttribute(tagDecorateInfoEClass, TAG_DECORATE_INFO__NON_VISUAL);
		createEAttribute(tagDecorateInfoEClass, TAG_DECORATE_INFO__NON_VISUAL_IMAGE_PATH);
		createEAttribute(tagDecorateInfoEClass, TAG_DECORATE_INFO__RESOLVE_CHILD_TEXT);
		createEReference(tagDecorateInfoEClass, TAG_DECORATE_INFO__RESOLVE_ATTRIBUTE_VALUE);
		createEAttribute(tagDecorateInfoEClass, TAG_DECORATE_INFO__SET_NON_VISUAL_CHILD_ELEMENTS);
		createEAttribute(tagDecorateInfoEClass, TAG_DECORATE_INFO__WIDGET);

		resolveAttributeValueEClass = createEClass(RESOLVE_ATTRIBUTE_VALUE);
		createEAttribute(resolveAttributeValueEClass, RESOLVE_ATTRIBUTE_VALUE__ATTRIBUTE_NAME);
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
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(dtInfoEClass, DTInfo.class, "DTInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getDTInfo_TagConvertInfo(), this.getTagConvertInfo(), null, "tagConvertInfo", null, 1, 1, DTInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDTInfo_TagDecorateInfos(), this.getTagDecorateInfo(), null, "tagDecorateInfos", null, 0, -1, DTInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(tagConvertInfoEClass, TagConvertInfo.class, "TagConvertInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTagConvertInfo_Operations(), this.getOperation(), null, "operations", null, 0, -1, TagConvertInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(operationEClass, Operation.class, "Operation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getOperation_Id(), ecorePackage.getEString(), "id", null, 1, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getOperation_Parameters(), this.getParameter(), null, "parameters", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getOperation_Operations(), this.getOperation(), null, "operations", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getParameter_Value(), ecorePackage.getEString(), "value", null, 1, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(tagDecorateInfoEClass, TagDecorateInfo.class, "TagDecorateInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getTagDecorateInfo_Id(), ecorePackage.getEString(), "id", null, 1, 1, TagDecorateInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTagDecorateInfo_MinHeight(), ecorePackage.getEInt(), "minHeight", null, 0, 1, TagDecorateInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTagDecorateInfo_MinWidth(), ecorePackage.getEInt(), "minWidth", null, 0, 1, TagDecorateInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTagDecorateInfo_MultiLevel(), ecorePackage.getEBoolean(), "multiLevel", "false", 0, 1, TagDecorateInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getTagDecorateInfo_NeedBorderDecorator(), ecorePackage.getEBoolean(), "needBorderDecorator", "false", 0, 1, TagDecorateInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getTagDecorateInfo_NeedTableDecorator(), ecorePackage.getEBoolean(), "needTableDecorator", "false", 0, 1, TagDecorateInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getTagDecorateInfo_NonVisual(), ecorePackage.getEBoolean(), "nonVisual", "false", 0, 1, TagDecorateInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getTagDecorateInfo_NonVisualImagePath(), ecorePackage.getEString(), "nonVisualImagePath", null, 0, 1, TagDecorateInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTagDecorateInfo_ResolveChildText(), ecorePackage.getEBoolean(), "resolveChildText", "false", 0, 1, TagDecorateInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getTagDecorateInfo_ResolveAttributeValue(), this.getResolveAttributeValue(), null, "resolveAttributeValue", null, 0, 1, TagDecorateInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTagDecorateInfo_SetNonVisualChildElements(), ecorePackage.getEBoolean(), "setNonVisualChildElements", "false", 0, 1, TagDecorateInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getTagDecorateInfo_Widget(), ecorePackage.getEBoolean(), "widget", "false", 0, 1, TagDecorateInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(resolveAttributeValueEClass, ResolveAttributeValue.class, "ResolveAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getResolveAttributeValue_AttributeName(), ecorePackage.getEString(), "attributeName", null, 1, 1, ResolveAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

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
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		 //$NON-NLS-1$
		addAnnotation
		  (getDTInfo_TagConvertInfo(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "tag-convert-info" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getDTInfo_TagDecorateInfos(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "tag-decorate-info" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTagConvertInfo_Operations(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "operation" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getOperation_Parameters(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "parameter" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getOperation_Operations(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "operation" //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (getTagDecorateInfo_ResolveAttributeValue(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "resolve-attribute-value" //$NON-NLS-1$ //$NON-NLS-2$
		   });
	}

} //DTInfoPackageImpl
