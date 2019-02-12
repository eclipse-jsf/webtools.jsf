/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.query.internal;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.query.IEntityQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.IResultSet;
import org.eclipse.jst.jsf.common.metadata.query.ITraitQueryVisitor;

/**
 * Interface for accessing 'raw' meta data query capabilities.
 * <p>
 * May be subclassed, but implementers must extend {@link MetaDataQueryHelper}
 *
 */
public interface IMetaDataQueryHelper {

	/**
	 * @param modelId
	 * @return Model object for given context.   May return null if not located.
	 */
	public Model getModel(final String modelId);

	/**
	 * @param modelId
	 * @param entityKey relative to root of the model
	 * @return the first entity match from the root of the model.   May return null.
	 */
	public Entity getEntity(final String modelId,
			final String entityKey);

	/**
	 * @param modelId 
	 * @param entityKey relative to root of model 
	 * @param visitor 
	 * @return an IResultSet of entity objects
	 */
	public IResultSet<Entity> getEntities(
			final String modelId, final String entityKey,
			final IEntityQueryVisitor visitor);

	/**
	 * @param entity
	 * @param traitKey
	 * @return a trait or null for the given entity and traitKey using a SimpleEntityQueryVisitorImpl 
	 */
	public Trait getTrait(final Entity entity, final String traitKey);

	/**
	 * @param entity
	 * @param traitKey
	 * @param traitQueryVisitor
	 * @return an IResultSet of trait objects using supplied traitQueryVisitor.  IResultSet should NOT be null.
	 */
	public IResultSet<Trait> getTraits(Entity entity, String traitKey,
			ITraitQueryVisitor traitQueryVisitor);

	/**
	 * @param initialEntityContext
	 * @param entityKey relative to initial passed entity
	 * @return the first entity located by key using SimpleEntityQueryVisitorImpl
	 */
	public Entity getEntity(Entity initialEntityContext, String entityKey);

	/**
	 * @param initialEntityContext
	 * @param entityQuery relative to initial passed entity
	 * @param entityKeyQueryVisitor
	 * @return IResultSet of entities located by key using entityQueryVisitor.  IResultSet should NOT be null.
	 */
	public IResultSet<Entity> getEntities(Entity initialEntityContext,
			String entityQuery, IEntityQueryVisitor entityKeyQueryVisitor);

	/**
	 * @param modelId
	 * @param entityKey
	 * @param traitKey
	 * @return first trait found for entity and trait key starting from root of the model using SimpleMetaDataQueryImpl
	 */
	public Trait getTrait(final String modelId,
			final String entityKey, final String traitKey);

	/**
	 * @param <T>
	 * @param results
	 * @return first <T> from result set.  May return null.
	 */
	public <T> T getFirstFromResultSet(final IResultSet<T> results);
//	/**
//	 * @param tagEntity
//	 * @return QName for entity
//	 */
//	public QName getQNameForEntity(final Entity tagEntity);

}