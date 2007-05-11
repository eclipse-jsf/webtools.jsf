/**
 * <copyright>
 * </copyright>
 *
 * $Id: MetadataFactoryImpl.java,v 1.4 2007/05/11 17:54:55 gkessler Exp $
 */
package org.eclipse.jst.jsf.common.metadata.internal.impl;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.EntityGroup;
import org.eclipse.jst.jsf.common.metadata.IncludeEntityGroup;
import org.eclipse.jst.jsf.common.metadata.MetadataFactory;
import org.eclipse.jst.jsf.common.metadata.MetadataPackage;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.internal.ModelKeyDescriptor;



/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MetadataFactoryImpl extends EFactoryImpl implements MetadataFactory {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2007 Oracle Corporation";

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return MetadataFactory
	 * @generated
	 */
	public static MetadataFactory init() {
		try {
			MetadataFactory theMetadataFactory = (MetadataFactory)EPackage.Registry.INSTANCE.getEFactory("http://org.eclipse.jst.jsf.common.metadata/metadata.ecore"); 
			if (theMetadataFactory != null) {
				return theMetadataFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MetadataFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetadataFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case MetadataPackage.MODEL: return createModel();
			case MetadataPackage.ENTITY_GROUP: return createEntityGroup();
			case MetadataPackage.ENTITY: return createEntity();
			case MetadataPackage.TRAIT: return createTrait();
			case MetadataPackage.INCLUDE_ENTITY_GROUP: return createIncludeEntityGroup();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case MetadataPackage.IMETA_DATA_SOURCE_MODEL_PROVIDER:
				return createIMetaDataSourceModelProviderFromString(eDataType, initialValue);
			case MetadataPackage.MODEL_CONTEXT:
				return createModelContextFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case MetadataPackage.IMETA_DATA_SOURCE_MODEL_PROVIDER:
				return convertIMetaDataSourceModelProviderToString(eDataType, instanceValue);
			case MetadataPackage.MODEL_CONTEXT:
				return convertModelContextToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Trait createTrait() {
		TraitImpl trait = new TraitImpl();
		return trait;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IncludeEntityGroup createIncludeEntityGroup() {
		IncludeEntityGroupImpl includeEntityGroup = new IncludeEntityGroupImpl();
		return includeEntityGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model createModel() {
		ModelImpl model = new ModelImpl();
		return model;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entity createEntity() {
		EntityImpl entity = new EntityImpl();
		return entity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityGroup createEntityGroup() {
		EntityGroupImpl entityGroup = new EntityGroupImpl();
		return entityGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param eDataType IMetaDataSourceModelProvider
	 * @param initialValue 
	 * @return IMetaDataSourceModelProvider
	 * @generated
	 */
	public IMetaDataSourceModelProvider createIMetaDataSourceModelProviderFromString(EDataType eDataType, String initialValue) {
		return (IMetaDataSourceModelProvider)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param eDataType 
	 * @param instanceValue 
	 * @return String
	 * @generated
	 */
	public String convertIMetaDataSourceModelProviderToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param eDataType 
	 * @param initialValue 
	 * @return ModelKeyDescriptor
	 * @generated
	 */
	public ModelKeyDescriptor createModelContextFromString(EDataType eDataType, String initialValue) {
		return (ModelKeyDescriptor)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param eDataType 
	 * @param instanceValue 
	 * @return String
	 * @generated
	 */
	public String convertModelContextToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetadataPackage getMetadataPackage() {
		return (MetadataPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return MetadataPackage
	 * @deprecated
	 * @generated
	 */
	public static MetadataPackage getPackage() {
		return MetadataPackage.eINSTANCE;
	}

} //MetadataFactoryImpl
