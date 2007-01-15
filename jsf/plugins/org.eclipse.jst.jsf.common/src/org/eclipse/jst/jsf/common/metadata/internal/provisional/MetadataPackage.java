/**
 * <copyright>
 * </copyright>
 *
 * $Id: MetadataPackage.java,v 1.1 2007/01/15 23:26:14 gkessler Exp $
 */
package org.eclipse.jst.jsf.common.metadata.internal.provisional;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataFactory
 * @model kind="package"
 * @generated
 */
public interface MetadataPackage extends EPackage {
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
	String eNAME = "metadata";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.eclipse.jst.jsf.common.metadata/metadata.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "md";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MetadataPackage eINSTANCE = org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.common.metadata.internal.impl.TraitImpl <em>Trait</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.TraitImpl
	 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getTrait()
	 * @generated
	 */
	int TRAIT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAIT__ID = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAIT__VALUE = 1;

	/**
	 * The feature id for the '<em><b>Source Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAIT__SOURCE_MODEL = 2;

	/**
	 * The number of structural features of the '<em>Trait</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAIT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.common.metadata.internal.impl.EntityImpl <em>Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.EntityImpl
	 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getEntity()
	 * @generated
	 */
	int ENTITY = 2;

	/**
	 * The feature id for the '<em><b>Child Entities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__CHILD_ENTITIES = 0;

	/**
	 * The feature id for the '<em><b>Traits</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__TRAITS = 1;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__PARENT = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__ID = 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__TYPE = 4;

	/**
	 * The number of structural features of the '<em>Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.common.metadata.internal.impl.ModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.ModelImpl
	 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getModel()
	 * @generated
	 */
	int MODEL = 1;

	/**
	 * The feature id for the '<em><b>Child Entities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__CHILD_ENTITIES = ENTITY__CHILD_ENTITIES;

	/**
	 * The feature id for the '<em><b>Traits</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__TRAITS = ENTITY__TRAITS;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__PARENT = ENTITY__PARENT;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__ID = ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__TYPE = ENTITY__TYPE;

	/**
	 * The feature id for the '<em><b>Source Model Provider</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__SOURCE_MODEL_PROVIDER = ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_FEATURE_COUNT = ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '<em>ITrait Visitor</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.query.ITraitVisitor
	 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getITraitVisitor()
	 * @generated
	 */
	int ITRAIT_VISITOR = 3;

	/**
	 * The meta object id for the '<em>IEntity Visitor</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IEntityVisitor
	 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getIEntityVisitor()
	 * @generated
	 */
	int IENTITY_VISITOR = 4;

	/**
	 * The meta object id for the '<em>IMeta Data Source Model Provider</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider
	 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getIMetaDataSourceModelProvider()
	 * @generated
	 */
	int IMETA_DATA_SOURCE_MODEL_PROVIDER = 5;


	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait <em>Trait</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trait</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait
	 * @generated
	 */
	EClass getTrait();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait#getId()
	 * @see #getTrait()
	 * @generated
	 */
	EAttribute getTrait_Id();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait#getValue()
	 * @see #getTrait()
	 * @generated
	 */
	EReference getTrait_Value();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait#getSourceModel <em>Source Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Model</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait#getSourceModel()
	 * @see #getTrait()
	 * @generated
	 */
	EReference getTrait_SourceModel();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Model#getSourceModelProvider <em>Source Model Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Model Provider</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.Model#getSourceModelProvider()
	 * @see #getModel()
	 * @generated
	 */
	EAttribute getModel_SourceModelProvider();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity
	 * @generated
	 */
	EClass getEntity();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getChildEntities <em>Child Entities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Child Entities</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getChildEntities()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_ChildEntities();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getTraits <em>Traits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Traits</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getTraits()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_Traits();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getParent()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_Parent();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getId()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getType()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Type();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.query.ITraitVisitor <em>ITrait Visitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>ITrait Visitor</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.query.ITraitVisitor
	 * @model instanceClass="org.eclipse.jst.jsf.common.metadata.internal.provisional.query.ITraitVisitor" serializable="false"
	 * @generated
	 */
	EDataType getITraitVisitor();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IEntityVisitor <em>IEntity Visitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IEntity Visitor</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IEntityVisitor
	 * @model instanceClass="org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IEntityVisitor" serializable="false"
	 * @generated
	 */
	EDataType getIEntityVisitor();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider <em>IMeta Data Source Model Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IMeta Data Source Model Provider</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider
	 * @model instanceClass="org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider"
	 * @generated
	 */
	EDataType getIMetaDataSourceModelProvider();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MetadataFactory getMetadataFactory();

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
	interface Literals  {
		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.common.metadata.internal.impl.TraitImpl <em>Trait</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.TraitImpl
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getTrait()
		 * @generated
		 */
		EClass TRAIT = eINSTANCE.getTrait();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRAIT__ID = eINSTANCE.getTrait_Id();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRAIT__VALUE = eINSTANCE.getTrait_Value();

		/**
		 * The meta object literal for the '<em><b>Source Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRAIT__SOURCE_MODEL = eINSTANCE.getTrait_SourceModel();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.common.metadata.internal.impl.ModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.ModelImpl
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getModel()
		 * @generated
		 */
		EClass MODEL = eINSTANCE.getModel();

		/**
		 * The meta object literal for the '<em><b>Source Model Provider</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL__SOURCE_MODEL_PROVIDER = eINSTANCE.getModel_SourceModelProvider();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.common.metadata.internal.impl.EntityImpl <em>Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.EntityImpl
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getEntity()
		 * @generated
		 */
		EClass ENTITY = eINSTANCE.getEntity();

		/**
		 * The meta object literal for the '<em><b>Child Entities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__CHILD_ENTITIES = eINSTANCE.getEntity_ChildEntities();

		/**
		 * The meta object literal for the '<em><b>Traits</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__TRAITS = eINSTANCE.getEntity_Traits();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__PARENT = eINSTANCE.getEntity_Parent();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__ID = eINSTANCE.getEntity_Id();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__TYPE = eINSTANCE.getEntity_Type();

		/**
		 * The meta object literal for the '<em>ITrait Visitor</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.query.ITraitVisitor
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getITraitVisitor()
		 * @generated
		 */
		EDataType ITRAIT_VISITOR = eINSTANCE.getITraitVisitor();

		/**
		 * The meta object literal for the '<em>IEntity Visitor</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IEntityVisitor
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getIEntityVisitor()
		 * @generated
		 */
		EDataType IENTITY_VISITOR = eINSTANCE.getIEntityVisitor();

		/**
		 * The meta object literal for the '<em>IMeta Data Source Model Provider</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getIMetaDataSourceModelProvider()
		 * @generated
		 */
		EDataType IMETA_DATA_SOURCE_MODEL_PROVIDER = eINSTANCE.getIMetaDataSourceModelProvider();

	}

} //MetadataPackage
