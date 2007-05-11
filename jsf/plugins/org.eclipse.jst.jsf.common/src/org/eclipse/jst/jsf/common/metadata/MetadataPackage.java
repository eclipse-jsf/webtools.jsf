/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.jsf.common.metadata;

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
 * @see org.eclipse.jst.jsf.common.metadata.MetadataFactory
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
	int TRAIT = 3;

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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__ID = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__TYPE = 3;

	/**
	 * The feature id for the '<em><b>Include Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__INCLUDE_GROUPS = 4;

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
	int MODEL = 0;

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
	 * The feature id for the '<em><b>Include Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__INCLUDE_GROUPS = ENTITY__INCLUDE_GROUPS;

	/**
	 * The feature id for the '<em><b>Entity Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__ENTITY_GROUPS = ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source Model Provider</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__SOURCE_MODEL_PROVIDER = ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Current Model Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__CURRENT_MODEL_CONTEXT = ENTITY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_FEATURE_COUNT = ENTITY_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.common.metadata.internal.impl.IncludeEntityGroupImpl <em>Include Entity Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.IncludeEntityGroupImpl
	 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getIncludeEntityGroup()
	 * @generated
	 */
	int INCLUDE_ENTITY_GROUP = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.common.metadata.internal.impl.EntityGroupImpl <em>Entity Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.EntityGroupImpl
	 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getEntityGroup()
	 * @generated
	 */
	int ENTITY_GROUP = 1;

	/**
	 * The feature id for the '<em><b>Child Entities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_GROUP__CHILD_ENTITIES = ENTITY__CHILD_ENTITIES;

	/**
	 * The feature id for the '<em><b>Traits</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_GROUP__TRAITS = ENTITY__TRAITS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_GROUP__ID = ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_GROUP__TYPE = ENTITY__TYPE;

	/**
	 * The feature id for the '<em><b>Include Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_GROUP__INCLUDE_GROUPS = ENTITY__INCLUDE_GROUPS;

	/**
	 * The number of structural features of the '<em>Entity Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_GROUP_FEATURE_COUNT = ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAIT__ID = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAIT__VALUE = 1;

	/**
	 * The feature id for the '<em><b>Source Model Provider</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAIT__SOURCE_MODEL_PROVIDER = 2;

	/**
	 * The number of structural features of the '<em>Trait</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRAIT_FEATURE_COUNT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUDE_ENTITY_GROUP__ID = 0;

	/**
	 * The feature id for the '<em><b>Model Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUDE_ENTITY_GROUP__MODEL_URI = 1;

	/**
	 * The number of structural features of the '<em>Include Entity Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUDE_ENTITY_GROUP_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '<em>ITrait Visitor</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.common.metadata.query.ITraitVisitor
	 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getITraitVisitor()
	 * @generated
	 */
	int ITRAIT_VISITOR = 5;

	/**
	 * The meta object id for the '<em>IEntity Visitor</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.common.metadata.query.IEntityVisitor
	 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getIEntityVisitor()
	 * @generated
	 */
	int IENTITY_VISITOR = 6;

	/**
	 * The meta object id for the '<em>IMeta Data Source Model Provider</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider
	 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getIMetaDataSourceModelProvider()
	 * @generated
	 */
	int IMETA_DATA_SOURCE_MODEL_PROVIDER = 7;


	/**
	 * The meta object id for the '<em>Model Context</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.common.metadata.internal.ModelKeyDescriptor
	 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getModelContext()
	 * @generated
	 */
	int MODEL_CONTEXT = 8;


	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.common.metadata.Trait <em>Trait</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trait</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.Trait
	 * @generated
	 */
	EClass getTrait();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.common.metadata.Trait#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.Trait#getId()
	 * @see #getTrait()
	 * @generated
	 */
	EAttribute getTrait_Id();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.jsf.common.metadata.Trait#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.Trait#getValue()
	 * @see #getTrait()
	 * @generated
	 */
	EReference getTrait_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.common.metadata.Trait#getSourceModelProvider <em>Source Model Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Model Provider</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.Trait#getSourceModelProvider()
	 * @see #getTrait()
	 * @generated
	 */
	EAttribute getTrait_SourceModelProvider();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.common.metadata.IncludeEntityGroup <em>Include Entity Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Include Entity Group</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.IncludeEntityGroup
	 * @generated
	 */
	EClass getIncludeEntityGroup();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.common.metadata.IncludeEntityGroup#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.IncludeEntityGroup#getId()
	 * @see #getIncludeEntityGroup()
	 * @generated
	 */
	EAttribute getIncludeEntityGroup_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.common.metadata.IncludeEntityGroup#getModelUri <em>Model Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model Uri</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.IncludeEntityGroup#getModelUri()
	 * @see #getIncludeEntityGroup()
	 * @generated
	 */
	EAttribute getIncludeEntityGroup_ModelUri();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.common.metadata.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.common.metadata.Model#getSourceModelProvider <em>Source Model Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Model Provider</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.Model#getSourceModelProvider()
	 * @see #getModel()
	 * @generated
	 */
	EAttribute getModel_SourceModelProvider();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.common.metadata.Model#getCurrentModelContext <em>Current Model Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Current Model Context</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.Model#getCurrentModelContext()
	 * @see #getModel()
	 * @generated
	 */
	EAttribute getModel_CurrentModelContext();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.common.metadata.Model#getEntityGroups <em>Entity Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Entity Groups</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.Model#getEntityGroups()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_EntityGroups();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.common.metadata.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.Entity
	 * @generated
	 */
	EClass getEntity();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.common.metadata.Entity#getChildEntities <em>Child Entities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Child Entities</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.Entity#getChildEntities()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_ChildEntities();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.common.metadata.Entity#getTraits <em>Traits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Traits</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.Entity#getTraits()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_Traits();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.common.metadata.Entity#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.Entity#getId()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.common.metadata.Entity#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.Entity#getType()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Type();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.common.metadata.Entity#getIncludeGroups <em>Include Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Include Groups</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.Entity#getIncludeGroups()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_IncludeGroups();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.common.metadata.EntityGroup <em>Entity Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Group</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.EntityGroup
	 * @generated
	 */
	EClass getEntityGroup();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.jst.jsf.common.metadata.query.ITraitVisitor <em>ITrait Visitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>ITrait Visitor</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.query.ITraitVisitor
	 * @model instanceClass="org.eclipse.jst.jsf.common.metadata.query.ITraitVisitor" serializable="false"
	 * @generated
	 */
	EDataType getITraitVisitor();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.jst.jsf.common.metadata.query.IEntityVisitor <em>IEntity Visitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IEntity Visitor</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.query.IEntityVisitor
	 * @model instanceClass="org.eclipse.jst.jsf.common.metadata.query.IEntityVisitor" serializable="false"
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
	 * Returns the meta object for data type '{@link org.eclipse.jst.jsf.common.metadata.internal.ModelKeyDescriptor <em>Model Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Model Context</em>'.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.ModelKeyDescriptor
	 * @model instanceClass="org.eclipse.jst.jsf.common.metadata.internal.ModelKeyDescriptor"
	 * @generated
	 */
	EDataType getModelContext();

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
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.common.metadata.internal.impl.TraitImpl <em>Trait</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.TraitImpl
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getTrait()
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EClass TRAIT = eINSTANCE.getTrait();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EAttribute TRAIT__ID = eINSTANCE.getTrait_Id();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EReference TRAIT__VALUE = eINSTANCE.getTrait_Value();

		/**
		 * The meta object literal for the '<em><b>Source Model Provider</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EAttribute TRAIT__SOURCE_MODEL_PROVIDER = eINSTANCE.getTrait_SourceModelProvider();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.common.metadata.internal.impl.IncludeEntityGroupImpl <em>Include Entity Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.IncludeEntityGroupImpl
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getIncludeEntityGroup()
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EClass INCLUDE_ENTITY_GROUP = eINSTANCE.getIncludeEntityGroup();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EAttribute INCLUDE_ENTITY_GROUP__ID = eINSTANCE.getIncludeEntityGroup_Id();

		/**
		 * The meta object literal for the '<em><b>Model Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EAttribute INCLUDE_ENTITY_GROUP__MODEL_URI = eINSTANCE.getIncludeEntityGroup_ModelUri();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.common.metadata.internal.impl.ModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.ModelImpl
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getModel()
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EClass MODEL = eINSTANCE.getModel();

		/**
		 * The meta object literal for the '<em><b>Source Model Provider</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EAttribute MODEL__SOURCE_MODEL_PROVIDER = eINSTANCE.getModel_SourceModelProvider();

		/**
		 * The meta object literal for the '<em><b>Current Model Context</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EAttribute MODEL__CURRENT_MODEL_CONTEXT = eINSTANCE.getModel_CurrentModelContext();

		/**
		 * The meta object literal for the '<em><b>Entity Groups</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EReference MODEL__ENTITY_GROUPS = eINSTANCE.getModel_EntityGroups();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.common.metadata.internal.impl.EntityImpl <em>Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.EntityImpl
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getEntity()
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EClass ENTITY = eINSTANCE.getEntity();

		/**
		 * The meta object literal for the '<em><b>Child Entities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EReference ENTITY__CHILD_ENTITIES = eINSTANCE.getEntity_ChildEntities();

		/**
		 * The meta object literal for the '<em><b>Traits</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EReference ENTITY__TRAITS = eINSTANCE.getEntity_Traits();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EAttribute ENTITY__ID = eINSTANCE.getEntity_Id();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EAttribute ENTITY__TYPE = eINSTANCE.getEntity_Type();

		/**
		 * The meta object literal for the '<em><b>Include Groups</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EReference ENTITY__INCLUDE_GROUPS = eINSTANCE.getEntity_IncludeGroups();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.common.metadata.internal.impl.EntityGroupImpl <em>Entity Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.EntityGroupImpl
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getEntityGroup()
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EClass ENTITY_GROUP = eINSTANCE.getEntityGroup();

		/**
		 * The meta object literal for the '<em>ITrait Visitor</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.common.metadata.query.ITraitVisitor
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getITraitVisitor()
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EDataType ITRAIT_VISITOR = eINSTANCE.getITraitVisitor();

		/**
		 * The meta object literal for the '<em>IEntity Visitor</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.common.metadata.query.IEntityVisitor
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getIEntityVisitor()
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EDataType IENTITY_VISITOR = eINSTANCE.getIEntityVisitor();

		/**
		 * The meta object literal for the '<em>IMeta Data Source Model Provider</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getIMetaDataSourceModelProvider()
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EDataType IMETA_DATA_SOURCE_MODEL_PROVIDER = eINSTANCE.getIMetaDataSourceModelProvider();

		/**
		 * The meta object literal for the '<em>Model Context</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.common.metadata.internal.ModelKeyDescriptor
		 * @see org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataPackageImpl#getModelContext()
		 * @generated
		 */
		@SuppressWarnings("hiding")
		EDataType MODEL_CONTEXT = eINSTANCE.getModelContext();

	}

} //MetadataPackage
