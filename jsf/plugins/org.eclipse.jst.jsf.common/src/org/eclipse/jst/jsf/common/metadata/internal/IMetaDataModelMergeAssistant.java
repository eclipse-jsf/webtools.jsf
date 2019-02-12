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
package org.eclipse.jst.jsf.common.metadata.internal;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.EntityGroup;
import org.eclipse.jst.jsf.common.metadata.Trait;


/**
 * Helper class interface to merge source models into a single merged model.
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
	 * @return the merged model entity
	 *  
	 */
	public Entity addEntity(Entity entity);
	/**
	 * Method will add an entity if not already existing in the merged model, and then check for an existing trait by id on the merged model's entity.
	 * @param entity
	 * @param trait
	 * @return flag indicating whether or not the traits was new and therefore added to the merged model's entity
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
	 * This should signal that entityGroups processing should begin.
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

	/**
	 * @param queryRoot
	 * @param entityKey - key relative to queryRoot
	 * @return entity - will return null if not found
	 */
	public Entity getMergedEntity(Entity queryRoot, String entityKey);
	
}
