/**
 * <copyright>
 * </copyright>
 *
 * $Id: PaletteInfosPackageImpl.java,v 1.2 2007/04/09 02:23:03 cbateman Exp $
 */
package org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfo;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfos;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfosFactory;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfosPackage;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.TagCreationAttribute;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.TagCreationInfo;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.TagCreationTemplate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PaletteInfosPackageImpl extends EPackageImpl implements PaletteInfosPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2007 Oracle Corporation";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass paletteInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tagCreationInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tagCreationTemplateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tagCreationAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass paletteInfosEClass = null;

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
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfosPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PaletteInfosPackageImpl() {
		super(eNS_URI, PaletteInfosFactory.eINSTANCE);
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
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PaletteInfosPackage init() {
		if (isInited) return (PaletteInfosPackage)EPackage.Registry.INSTANCE.getEPackage(PaletteInfosPackage.eNS_URI);

		// Obtain or create and register package
		PaletteInfosPackageImpl thePaletteInfosPackage = (PaletteInfosPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof PaletteInfosPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new PaletteInfosPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		thePaletteInfosPackage.createPackageContents();

		// Initialize created meta-data
		thePaletteInfosPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePaletteInfosPackage.freeze();

		return thePaletteInfosPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPaletteInfo() {
		return paletteInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPaletteInfo_Id() {
		return (EAttribute)paletteInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPaletteInfo_Tag() {
		return (EAttribute)paletteInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPaletteInfo_DisplayLabel() {
		return (EAttribute)paletteInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPaletteInfo_Description() {
		return (EAttribute)paletteInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPaletteInfo_Expert() {
		return (EAttribute)paletteInfoEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPaletteInfo_Hidden() {
		return (EAttribute)paletteInfoEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPaletteInfo_SmallIcon() {
		return (EAttribute)paletteInfoEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPaletteInfo_LargeIcon() {
		return (EAttribute)paletteInfoEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPaletteInfo_TagCreation() {
		return (EReference)paletteInfoEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTagCreationInfo() {
		return tagCreationInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagCreationInfo_Template() {
		return (EAttribute)tagCreationInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTagCreationInfo_Attributes() {
		return (EReference)tagCreationInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTagCreationTemplate() {
		return tagCreationTemplateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTagCreationTemplate_Template() {
		return (EReference)tagCreationTemplateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTagCreationAttribute() {
		return tagCreationAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagCreationAttribute_Id() {
		return (EAttribute)tagCreationAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagCreationAttribute_Value() {
		return (EAttribute)tagCreationAttributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPaletteInfos() {
		return paletteInfosEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPaletteInfos_Infos() {
		return (EReference)paletteInfosEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PaletteInfosFactory getPaletteInfosFactory() {
		return (PaletteInfosFactory)getEFactoryInstance();
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
		paletteInfosEClass = createEClass(PALETTE_INFOS);
		createEReference(paletteInfosEClass, PALETTE_INFOS__INFOS);

		paletteInfoEClass = createEClass(PALETTE_INFO);
		createEAttribute(paletteInfoEClass, PALETTE_INFO__ID);
		createEAttribute(paletteInfoEClass, PALETTE_INFO__TAG);
		createEAttribute(paletteInfoEClass, PALETTE_INFO__DISPLAY_LABEL);
		createEAttribute(paletteInfoEClass, PALETTE_INFO__DESCRIPTION);
		createEAttribute(paletteInfoEClass, PALETTE_INFO__EXPERT);
		createEAttribute(paletteInfoEClass, PALETTE_INFO__HIDDEN);
		createEAttribute(paletteInfoEClass, PALETTE_INFO__SMALL_ICON);
		createEAttribute(paletteInfoEClass, PALETTE_INFO__LARGE_ICON);
		createEReference(paletteInfoEClass, PALETTE_INFO__TAG_CREATION);

		tagCreationInfoEClass = createEClass(TAG_CREATION_INFO);
		createEReference(tagCreationInfoEClass, TAG_CREATION_INFO__ATTRIBUTES);
		createEAttribute(tagCreationInfoEClass, TAG_CREATION_INFO__TEMPLATE);

		tagCreationTemplateEClass = createEClass(TAG_CREATION_TEMPLATE);
		createEReference(tagCreationTemplateEClass, TAG_CREATION_TEMPLATE__TEMPLATE);

		tagCreationAttributeEClass = createEClass(TAG_CREATION_ATTRIBUTE);
		createEAttribute(tagCreationAttributeEClass, TAG_CREATION_ATTRIBUTE__ID);
		createEAttribute(tagCreationAttributeEClass, TAG_CREATION_ATTRIBUTE__VALUE);
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

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(paletteInfosEClass, PaletteInfos.class, "PaletteInfos", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPaletteInfos_Infos(), this.getPaletteInfo(), null, "infos", null, 0, -1, PaletteInfos.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(paletteInfosEClass, this.getPaletteInfo(), "findPaletteInfoById", 0, 1);
		addEParameter(op, ecorePackage.getEString(), "id", 1, 1);

		initEClass(paletteInfoEClass, PaletteInfo.class, "PaletteInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPaletteInfo_Id(), ecorePackage.getEString(), "id", null, 1, 1, PaletteInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPaletteInfo_Tag(), ecorePackage.getEString(), "tag", null, 0, 1, PaletteInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPaletteInfo_DisplayLabel(), ecorePackage.getEString(), "displayLabel", null, 0, 1, PaletteInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPaletteInfo_Description(), ecorePackage.getEString(), "description", null, 0, 1, PaletteInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPaletteInfo_Expert(), ecorePackage.getEBooleanObject(), "expert", null, 0, 1, PaletteInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPaletteInfo_Hidden(), ecorePackage.getEBooleanObject(), "hidden", null, 0, 1, PaletteInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPaletteInfo_SmallIcon(), ecorePackage.getEString(), "smallIcon", null, 0, 1, PaletteInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPaletteInfo_LargeIcon(), ecorePackage.getEString(), "largeIcon", null, 0, 1, PaletteInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPaletteInfo_TagCreation(), this.getTagCreationInfo(), null, "tagCreation", null, 0, 1, PaletteInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(paletteInfoEClass, ecorePackage.getEBoolean(), "isExpert", 0, 1);

		op = addEOperation(paletteInfoEClass, ecorePackage.getEBoolean(), "isHidden", 0, 1);

		initEClass(tagCreationInfoEClass, TagCreationInfo.class, "TagCreationInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTagCreationInfo_Attributes(), this.getTagCreationAttribute(), null, "attributes", null, 0, -1, TagCreationInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTagCreationInfo_Template(), theXMLTypePackage.getAnySimpleType(), "template", null, 0, 1, TagCreationInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tagCreationTemplateEClass, TagCreationTemplate.class, "TagCreationTemplate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTagCreationTemplate_Template(), ecorePackage.getEObject(), null, "template", null, 0, 1, TagCreationTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tagCreationAttributeEClass, TagCreationAttribute.class, "TagCreationAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTagCreationAttribute_Id(), ecorePackage.getEString(), "id", null, 1, 1, TagCreationAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTagCreationAttribute_Value(), ecorePackage.getEString(), "value", null, 0, 1, TagCreationAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (getPaletteInfos_Infos(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "item"
		   });		
		addAnnotation
		  (getPaletteInfo_DisplayLabel(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "display-label"
		   });		
		addAnnotation
		  (getPaletteInfo_Description(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getPaletteInfo_Expert(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getPaletteInfo_Hidden(), 
		   source, 
		   new String[] {
			 "kind", "element"
		   });		
		addAnnotation
		  (getPaletteInfo_SmallIcon(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "small-icon"
		   });		
		addAnnotation
		  (getPaletteInfo_LargeIcon(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "large-icon"
		   });		
		addAnnotation
		  (getPaletteInfo_TagCreation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "tag-create"
		   });		
		addAnnotation
		  (getTagCreationInfo_Attributes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "attribute"
		   });
	}

} //PaletteInfosPackageImpl
