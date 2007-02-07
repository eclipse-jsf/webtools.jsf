/**
 * <copyright>
 * </copyright>
 *
 * $Id: TraitTypesPackage.java,v 1.1 2007/02/07 00:03:50 gkessler Exp $
 */
package org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.provisional;

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
 * @see org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.provisional.TraitTypesFactory
 * @model kind="package"
 * @generated
 */
public interface TraitTypesPackage extends EPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation";

	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "traittypes";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.eclipse.jst.jsf.common.metadata/metadataTraitTypes.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "mdt";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TraitTypesPackage eINSTANCE = org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.impl.TraitTypesPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.impl.ListOfValuesImpl <em>List Of Values</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.impl.ListOfValuesImpl
	 * @see org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.impl.TraitTypesPackageImpl#getListOfValues()
	 * @generated
	 */
	int LIST_OF_VALUES = 0;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_OF_VALUES__ENTRIES = 0;

	/**
	 * The number of structural features of the '<em>List Of Values</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_OF_VALUES_FEATURE_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.provisional.ListOfValues <em>List Of Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Of Values</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.provisional.ListOfValues
	 * @generated
	 */
	EClass getListOfValues();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.provisional.ListOfValues#getEntries <em>Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entries</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.provisional.ListOfValues#getEntries()
	 * @see #getListOfValues()
	 * @generated
	 */
	EReference getListOfValues_Entries();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TraitTypesFactory getTraitTypesFactory();

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
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.impl.ListOfValuesImpl <em>List Of Values</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.impl.ListOfValuesImpl
		 * @see org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.impl.TraitTypesPackageImpl#getListOfValues()
		 * @generated
		 */
		EClass LIST_OF_VALUES = eINSTANCE.getListOfValues();

		/**
		 * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_OF_VALUES__ENTRIES = eINSTANCE.getListOfValues_Entries();

	}

} //TraitTypesPackage
