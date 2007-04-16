/**
 * <copyright>
 * </copyright>
 *
 * $Id: ModelImpl.java,v 1.2.4.1 2007/04/16 19:40:04 itrimble Exp $
 */
package org.eclipse.jst.jsf.common.metadata.internal.impl;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.EntityGroup;
import org.eclipse.jst.jsf.common.metadata.MetadataPackage;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.internal.ModelKeyDescriptor;
import org.eclipse.jst.jsf.common.metadata.query.IEntityVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.impl.ModelImpl#getEntityGroups <em>Entity Groups</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.impl.ModelImpl#getSourceModelProvider <em>Source Model Provider</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.impl.ModelImpl#getCurrentModelContext <em>Current Model Context</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelImpl extends EntityImpl implements Model {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2007 Oracle Corporation";

	/**
	 * The cached value of the '{@link #getEntityGroups() <em>Entity Groups</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntityGroups()
	 * @generated
	 * @ordered
	 */
	protected EList entityGroups = null;

	/**
	 * The default value of the '{@link #getSourceModelProvider() <em>Source Model Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceModelProvider()
	 * @generated
	 * @ordered
	 */
	protected static final IMetaDataSourceModelProvider SOURCE_MODEL_PROVIDER_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getCurrentModelContext() <em>Current Model Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrentModelContext()
	 * @generated
	 * @ordered
	 */
	protected static final ModelKeyDescriptor CURRENT_MODEL_CONTEXT_EDEFAULT = null;

	private IMetaDataSourceModelProvider sourceModelProvider;

	private ModelKeyDescriptor _modelContext;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * May return null.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public IMetaDataSourceModelProvider getSourceModelProvider() {
		return sourceModelProvider;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setSourceModelProvider(IMetaDataSourceModelProvider newSourceModelProvider) {
		sourceModelProvider = newSourceModelProvider;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ModelKeyDescriptor getCurrentModelContext() {
		return _modelContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setCurrentModelContext(ModelKeyDescriptor newCurrentModelContext) {
		_modelContext = newCurrentModelContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEntityGroups() {
		if (entityGroups == null) {
			entityGroups = new EObjectResolvingEList(EntityGroup.class, this, MetadataPackage.MODEL__ENTITY_GROUPS);
		}
		return entityGroups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetadataPackage.MODEL__ENTITY_GROUPS:
				return getEntityGroups();
			case MetadataPackage.MODEL__SOURCE_MODEL_PROVIDER:
				return getSourceModelProvider();
			case MetadataPackage.MODEL__CURRENT_MODEL_CONTEXT:
				return getCurrentModelContext();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MetadataPackage.MODEL__ENTITY_GROUPS:
				getEntityGroups().clear();
				getEntityGroups().addAll((Collection)newValue);
				return;
			case MetadataPackage.MODEL__SOURCE_MODEL_PROVIDER:
				setSourceModelProvider((IMetaDataSourceModelProvider)newValue);
				return;
			case MetadataPackage.MODEL__CURRENT_MODEL_CONTEXT:
				setCurrentModelContext((ModelKeyDescriptor)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EntityGroup findIncludeGroup(String groupId){
		EntityGroup ret = null;
		for (Iterator it=getEntityGroups().iterator();it.hasNext();){
			EntityGroup entityGroup = (EntityGroup)it.next();
			if (entityGroup.getId().equals(groupId)){
				ret = entityGroup;
				break;
			}
		}
		return ret;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case MetadataPackage.MODEL__ENTITY_GROUPS:
				getEntityGroups().clear();
				return;
			case MetadataPackage.MODEL__SOURCE_MODEL_PROVIDER:
				setSourceModelProvider(SOURCE_MODEL_PROVIDER_EDEFAULT);
				return;
			case MetadataPackage.MODEL__CURRENT_MODEL_CONTEXT:
				setCurrentModelContext(CURRENT_MODEL_CONTEXT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */

	public void accept(IEntityVisitor visitor) {
		if (visitor.stopVisiting())
			return;
		visitor.visit(this);
		
		if (!getChildEntities().isEmpty()){
			for (Iterator/*<Entity>*/ it = getChildEntities().iterator(); it.hasNext();){
				Entity k = (Entity)it.next();
				k.accept(visitor);
				if (visitor.stopVisiting())
					return;
			}
		}

		visitor.visitCompleted();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Model getModel() {
		return this;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MetadataPackage.MODEL__ENTITY_GROUPS:
				return entityGroups != null && !entityGroups.isEmpty();
			case MetadataPackage.MODEL__SOURCE_MODEL_PROVIDER:
				return SOURCE_MODEL_PROVIDER_EDEFAULT == null ? getSourceModelProvider() != null : !SOURCE_MODEL_PROVIDER_EDEFAULT.equals(getSourceModelProvider());
			case MetadataPackage.MODEL__CURRENT_MODEL_CONTEXT:
				return CURRENT_MODEL_CONTEXT_EDEFAULT == null ? getCurrentModelContext() != null : !CURRENT_MODEL_CONTEXT_EDEFAULT.equals(getCurrentModelContext());
		}
		return super.eIsSet(featureID);
	}

} //ModelImpl
