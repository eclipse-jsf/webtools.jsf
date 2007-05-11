/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.EntityGroup;
import org.eclipse.jst.jsf.common.metadata.Trait;


/**
 * Helper class to merge source models into a single merged model.
 * Not intended to be implemented by clients
 *
 */
public interface IMetaDataModelMergeAssistant {
	/**
	 * @return merged model
	 */
	public MetaDataModel getMergedModel();
	/**
	 * Method that will first check to see if an entity with the same id exists in the merged model. 
	 * If not, it will add it.   The entities includeGroups are then also merged.
	 * @param entity
	 * @return flag indicating whether or not the entity was new and therefore added to the merged model
	 *  
	 */
	public boolean addEntity(Entity entity);
	/**
	 * Method will add an entity if not already exsiting in the merged model, and then check for an exisiting trait by id on the merged model's entity.
	 * @param entity
	 * @param trait
	 * @return flag indicating whether or not the trais was new and therefore added to the merged model's entity
	 */
	public boolean addTrait(Entity entity, Trait trait);
	
	/**
	 * Method will add an entityGroup to the model if not already exiting in the merged model (by id).
	 * @param entityGroup
	 */
	public void addEntityGroup(EntityGroup entityGroup);
	/**
	 * Signal that the merge is complete so that any post-processing may occur.  
	 * This should be the last call made on the merge assistant and should be done before client calls for the merged model result.
	 */
	public void setMergeComplete();

	/**
	 * @param mds - {@link IMetaDataSourceModelProvider}
	 */
	public void setSourceModelProvider(IMetaDataSourceModelProvider mds);
	/**
	 * @return {@link IMetaDataSourceModelProvider} for current operation
	 */
	public IMetaDataSourceModelProvider getSourceModelProvider();
	

}
