/*******************************************************************************
 * Copyright (c) 2007, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.query.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelManager;
import org.eclipse.jst.jsf.common.metadata.internal.MetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.IEntityQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.IResultSet;
import org.eclipse.jst.jsf.common.metadata.query.ITraitQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataException;


/**
 * Helper class with static methods to simplify querying of a metadata model. 
 * 
 * @see IResultSet
 * @see IEntityQueryVisitor
 * @see ITraitQueryVisitor
 * @see Model
 * @see Entity
 * @see Trait
 * <p>
 * May be subclassed. 
 * 
 */
public class MetaDataQueryHelper implements IMetaDataQueryHelper{
	
	private IMetaDataModelManager 		_manager;
	private IMetaDataDomainContext 		_managerContext;

	/**
	 * Constructor
	 * @param manager 
	 * @param managerContext 
	 */
	public MetaDataQueryHelper (final IMetaDataModelManager manager,final IMetaDataDomainContext managerContext){
		_manager 		= manager;
		_managerContext = managerContext;
	}

	public Model getModel(final String modelId) {
		return getMDModel(modelId);
	}

	public Entity getEntity(final String modelId, final String entityKey) {
		final IEntityQueryVisitor visitor = new SimpleEntityQueryVisitorImpl(new HierarchicalSearchControl(1, HierarchicalSearchControl.SCOPE_ALL_LEVELS));
		final IResultSet<Entity> rs = getEntities(modelId,entityKey,  visitor);
		return getFirstFromResultSet(rs);
	}

	public IResultSet<Entity> getEntities(final String modelId, final String entityKey, final IEntityQueryVisitor visitor){
		final Model model = getModel(modelId);
		//we may want to throw error that model is empty
		return getEntities(model, entityKey, visitor);		
	}

	public Trait getTrait(final Entity entity, final String traitKey){
		final ITraitQueryVisitor visitor = new SimpleTraitQueryVisitorImpl();	
		final IResultSet<Trait> rs = getTraits(entity, traitKey, visitor);
		return getFirstFromResultSet(rs);
	}

	public IResultSet<Trait> getTraits(final Entity entity, final String traitKey, final ITraitQueryVisitor traitQueryVisitor) { 
		final IResultSet<Trait> rs = traitQueryVisitor.findTraits(entity, traitKey);
		return rs;
	}

	public Entity getEntity(final Entity initialEntityContext, final String entityKey) {
		final IEntityQueryVisitor visitor = new SimpleEntityQueryVisitorImpl(new HierarchicalSearchControl(1, HierarchicalSearchControl.SCOPE_ALL_LEVELS));
		final IResultSet<Entity> rs = getEntities(initialEntityContext, entityKey, visitor);
		return getFirstFromResultSet(rs);	
	}

	public IResultSet<Entity> getEntities(final Entity initialEntityContext, final String entityQuery, final IEntityQueryVisitor entityKeyQueryVisitor) {				
		return entityKeyQueryVisitor.findEntities(initialEntityContext, entityQuery);	
	}

	public Trait getTrait(final String modelId, final String entityKey, final String traitKey) { 
		final Entity entity = getEntity(modelId, entityKey);
		Trait t = null;
		if (entity != null){			
			t = getTrait(entity, traitKey);
		}
		return t;
	}	
	
	public <T> T getFirstFromResultSet(final IResultSet<T> results) {
		T result = null;
		try {
			if (! results.getResults().isEmpty()){
				result = results.getResults().get(0);				
			}
			results.close();
		} catch (MetaDataException ex) {
			JSFCommonPlugin.log(IStatus.ERROR, "Error in getFirstFromResultSet()", ex); //$NON-NLS-1$
		}
		return result;
	}
	
	/**
	 * Retrieve Model from the ModelManager for given key
	 * @param modelId
	 * @return Model
	 */
	private Model getMDModel(final String modelId){
		if (_manager != null) 
			return _manager.getModel(getModelContext(modelId));
		
		return null;
	}

	/**
	 * @param modelId
	 * @return IMetaDataModelContext2
	 */
	protected IMetaDataModelContext getModelContext(final String modelId) {
		return new MetaDataModelContext((
				IProject)_managerContext.getAdapter(IProject.class), 
				_managerContext.getDomainId(), 
				modelId);
	}
//	public QName getQNameForEntity(final Entity tagEntity) {
//		Assert.isTrue(tagEntity != null);
//		return new QName(tagEntity.getModel().getId(), tagEntity.getId());
//	}
}
