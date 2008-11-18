/**
 * Copyright (c) 2007 Oracle Corporation
 *
 * $Id: QuickEditTabSectionsPackageImpl.java,v 1.3 2008/11/18 22:22:35 gkessler Exp $
 */
package org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSections;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSectionsFactory;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSectionsPackage;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SectionInfo;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class QuickEditTabSectionsPackageImpl extends EPackageImpl implements QuickEditTabSectionsPackage {
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
	private EClass quickEditTabSectionsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sectionInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum sectioN_TYPEEEnum = null;

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
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSectionsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private QuickEditTabSectionsPackageImpl() {
		super(eNS_URI, QuickEditTabSectionsFactory.eINSTANCE);
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
	public static QuickEditTabSectionsPackage init() {
		if (isInited) return (QuickEditTabSectionsPackage)EPackage.Registry.INSTANCE.getEPackage(QuickEditTabSectionsPackage.eNS_URI);

		// Obtain or create and register package
		QuickEditTabSectionsPackageImpl theQuickEditTabSectionsPackage = (QuickEditTabSectionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof QuickEditTabSectionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new QuickEditTabSectionsPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theQuickEditTabSectionsPackage.createPackageContents();

		// Initialize created meta-data
		theQuickEditTabSectionsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theQuickEditTabSectionsPackage.freeze();

		return theQuickEditTabSectionsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQuickEditTabSections() {
		return quickEditTabSectionsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQuickEditTabSections_Sections() {
		return (EReference)quickEditTabSectionsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSectionInfo() {
		return sectionInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSectionInfo_Id() {
		return (EAttribute)sectionInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSectionInfo_Type() {
		return (EAttribute)sectionInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSECTION_TYPE() {
		return sectioN_TYPEEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QuickEditTabSectionsFactory getQuickEditTabSectionsFactory() {
		return (QuickEditTabSectionsFactory)getEFactoryInstance();
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
		quickEditTabSectionsEClass = createEClass(QUICK_EDIT_TAB_SECTIONS);
		createEReference(quickEditTabSectionsEClass, QUICK_EDIT_TAB_SECTIONS__SECTIONS);

		sectionInfoEClass = createEClass(SECTION_INFO);
		createEAttribute(sectionInfoEClass, SECTION_INFO__ID);
		createEAttribute(sectionInfoEClass, SECTION_INFO__TYPE);

		// Create enums
		sectioN_TYPEEEnum = createEEnum(SECTION_TYPE);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(quickEditTabSectionsEClass, QuickEditTabSections.class, "QuickEditTabSections", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getQuickEditTabSections_Sections(), this.getSectionInfo(), null, "sections", null, 0, -1, QuickEditTabSections.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sectionInfoEClass, SectionInfo.class, "SectionInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSectionInfo_Id(), ecorePackage.getEString(), "id", null, 1, 1, SectionInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSectionInfo_Type(), this.getSECTION_TYPE(), "type", "ATTRIBUTE", 0, 1, SectionInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		// Initialize enums and add enum literals
		initEEnum(sectioN_TYPEEEnum, org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SECTION_TYPE.class, "SECTION_TYPE"); //$NON-NLS-1$
		addEEnumLiteral(sectioN_TYPEEEnum, org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SECTION_TYPE.ATTRIBUTE);
		addEEnumLiteral(sectioN_TYPEEEnum, org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SECTION_TYPE.SECTION);

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
		  (getQuickEditTabSections_Sections(), 
		   source, 
		   new String[] {
			 "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
			 "name", "section" //$NON-NLS-1$ //$NON-NLS-2$
		   });
	}

} //QuickEditTabSectionsPackageImpl
