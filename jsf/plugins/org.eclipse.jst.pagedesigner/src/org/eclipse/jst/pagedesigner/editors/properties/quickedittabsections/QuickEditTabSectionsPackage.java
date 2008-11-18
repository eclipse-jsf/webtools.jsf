/**
 * Copyright (c) 2007 Oracle Corporation
 *
 * $Id: QuickEditTabSectionsPackage.java,v 1.5 2008/11/18 22:22:35 gkessler Exp $
 */
package org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSectionsFactory
 * @model kind="package"
 * @generated
 */
@SuppressWarnings("hiding")
public interface QuickEditTabSectionsPackage extends EPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "quickedittabsections"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.eclipse.jsf.pagedesigner/QuickEditTabSections.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "qe"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	QuickEditTabSectionsPackage eINSTANCE = org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl.QuickEditTabSectionsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl.QuickEditTabSectionsImpl <em>Quick Edit Tab Sections</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl.QuickEditTabSectionsImpl
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl.QuickEditTabSectionsPackageImpl#getQuickEditTabSections()
	 * @generated
	 */
	int QUICK_EDIT_TAB_SECTIONS = 0;

	/**
	 * The feature id for the '<em><b>Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUICK_EDIT_TAB_SECTIONS__SECTIONS = 0;

	/**
	 * The number of structural features of the '<em>Quick Edit Tab Sections</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUICK_EDIT_TAB_SECTIONS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl.SectionInfoImpl <em>Section Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl.SectionInfoImpl
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl.QuickEditTabSectionsPackageImpl#getSectionInfo()
	 * @generated
	 */
	int SECTION_INFO = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION_INFO__ID = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION_INFO__TYPE = 1;

	/**
	 * The number of structural features of the '<em>Section Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECTION_INFO_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SECTION_TYPE <em>SECTION TYPE</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SECTION_TYPE
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl.QuickEditTabSectionsPackageImpl#getSECTION_TYPE()
	 * @generated
	 */
	int SECTION_TYPE = 2;


	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSections <em>Quick Edit Tab Sections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Quick Edit Tab Sections</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSections
	 * @generated
	 */
	EClass getQuickEditTabSections();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSections#getSections <em>Sections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sections</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSections#getSections()
	 * @see #getQuickEditTabSections()
	 * @generated
	 */
	EReference getQuickEditTabSections_Sections();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SectionInfo <em>Section Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Section Info</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SectionInfo
	 * @generated
	 */
	EClass getSectionInfo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SectionInfo#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SectionInfo#getId()
	 * @see #getSectionInfo()
	 * @generated
	 */
	EAttribute getSectionInfo_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SectionInfo#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SectionInfo#getType()
	 * @see #getSectionInfo()
	 * @generated
	 */
	EAttribute getSectionInfo_Type();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SECTION_TYPE <em>SECTION TYPE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>SECTION TYPE</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SECTION_TYPE
	 * @generated
	 */
	EEnum getSECTION_TYPE();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	QuickEditTabSectionsFactory getQuickEditTabSectionsFactory();

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
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl.QuickEditTabSectionsImpl <em>Quick Edit Tab Sections</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl.QuickEditTabSectionsImpl
		 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl.QuickEditTabSectionsPackageImpl#getQuickEditTabSections()
		 * @generated
		 */
        EClass QUICK_EDIT_TAB_SECTIONS = eINSTANCE.getQuickEditTabSections();

		/**
		 * The meta object literal for the '<em><b>Sections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUICK_EDIT_TAB_SECTIONS__SECTIONS = eINSTANCE.getQuickEditTabSections_Sections();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl.SectionInfoImpl <em>Section Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl.SectionInfoImpl
		 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl.QuickEditTabSectionsPackageImpl#getSectionInfo()
		 * @generated
		 */
		EClass SECTION_INFO = eINSTANCE.getSectionInfo();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECTION_INFO__ID = eINSTANCE.getSectionInfo_Id();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECTION_INFO__TYPE = eINSTANCE.getSectionInfo_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SECTION_TYPE <em>SECTION TYPE</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SECTION_TYPE
		 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl.QuickEditTabSectionsPackageImpl#getSECTION_TYPE()
		 * @generated
		 */
		EEnum SECTION_TYPE = eINSTANCE.getSECTION_TYPE();

	}

} //QuickEditTabSectionsPackage
