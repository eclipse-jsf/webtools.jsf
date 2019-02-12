/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: ModelImpl.java,v 1.10 2011/04/04 22:22:38 gkessler Exp $
 */
package org.eclipse.jst.jsf.common.metadata.internal.impl;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
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
@SuppressWarnings("hiding")
public class ModelImpl extends EntityImpl implements Model {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getEntityGroups() <em>Entity Groups</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntityGroups()
	 * @generated
	 * @ordered
	 */
	protected EList<EntityGroup> entityGroups;

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
	@Override
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
	public EList<EntityGroup> getEntityGroups() {
		if (entityGroups == null) {
			entityGroups = new EObjectContainmentEList<EntityGroup>(EntityGroup.class, this, MetadataPackage.MODEL__ENTITY_GROUPS);
		}
		return entityGroups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MetadataPackage.MODEL__ENTITY_GROUPS:
				getEntityGroups().clear();
				getEntityGroups().addAll((Collection<? extends EntityGroup>)newValue);
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MetadataPackage.MODEL__ENTITY_GROUPS:
				return ((InternalEList<?>)getEntityGroups()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	 * Due to a mistake in the EMF model, Model is not inheriting accept method from Entity.   This should be fixed.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */

	public void accept(IEntityVisitor visitor) {
		super.accept(visitor);
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
	@Override
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
