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

package org.eclipse.jst.jsf.common.metadata.query.internal.taglib;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.query.IResultSet;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataException;
import org.eclipse.jst.jsf.common.metadata.query.internal.IMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.query.internal.IPredicateMatcher;

/**
 * Interface for querying in a tag library domain context
 * <p>
 * @noimplement - not intended to be implemented by clients
 * 
 */
public interface ITaglibDomainMetaDataQuery extends IMetaDataQuery {
	
	/**
	 * Find the tag library model for the passed uri which may be different from the one from the initial context
	 * @param uri - may not be null
	 * @return {@link Model} for this tag library's uri.  May be null.
	 */
	public Model findTagLibraryModel(final String uri);
	
	
	/**
	 * @param tagLib - may not be null
	 * @param tagName - may not be null
	 * @return {@link Entity} for the tag name in the library.  May be null
	 */
	public Entity findTagEntity(final Model tagLib, final String tagName);
	
	/**
	 * @param tagEntity - may not be null
	 * @param attributeName - may not be null
	 * @return {@link Entity} for the tag attribute name of this tag.  May be null.
	 */
	public Entity findTagAttributeEntity(final Entity tagEntity, final String attributeName);
		
	/**
	 * @param entityMatcher
	 * @return Set of {@link Entity}s matching the query specification
	 */
	public IResultSet<Entity> findEntities(final ITaglibDomainEntityPredicateMatcher entityMatcher);
	
	/**
	 * @param entityMatcher
	 * @return First {@link Entity} matching the query specification.   May be null.
	 */
	public Entity findEntity(final ITaglibDomainEntityPredicateMatcher entityMatcher);
	
	/**
	 * @param entity - may not be null
	 * @param traitId - may not be null
	 * @return {@link Trait} - may be null
	 */
	public Trait findTrait(final Entity entity, final String traitId);
	
	/**
	 * @param entityMatcher
	 * @param traitMatcher
	 * @return Set of {@link Trait}s matching both the entity and trait query specifications.  Will not be null.
	 * @throws MetaDataException 
	 */
	public IResultSet<Trait> findTraits(final ITaglibDomainEntityPredicateMatcher entityMatcher, final IPredicateMatcher<Trait> traitMatcher) throws MetaDataException;
	
	
	/**
	 * @param entityMatcher
	 * @param traitMatcher
	 * @return First {@link Trait} matching both the entity and trait query specifications.  May return null.
	 */
	public Trait findTrait(final ITaglibDomainEntityPredicateMatcher entityMatcher, final IPredicateMatcher<Trait> traitMatcher);

}
