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
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelManager;
import org.eclipse.jst.jsf.common.metadata.query.IResultSet;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataException;
import org.eclipse.jst.jsf.common.metadata.query.internal.AbstractMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.query.internal.HierarchicalSearchControl;
import org.eclipse.jst.jsf.common.metadata.query.internal.IPredicateMatcher;
import org.eclipse.jst.jsf.common.metadata.query.internal.SearchControl;
import org.eclipse.jst.jsf.common.metadata.query.internal.TraitQuerySpecVisitor;

/**
 * Implements {@link ITaglibDomainMetaDataQuery}
 * 
 */
public class TaglibDomainMetaDataQuery 
		extends 	AbstractMetaDataQuery 
		implements 	ITaglibDomainMetaDataQuery {

	/**
	 * Constructor
	 * @param manager
	 * @param managerContext 
	 */
	public TaglibDomainMetaDataQuery(final IMetaDataModelManager manager, final IMetaDataDomainContext managerContext) {
		super(manager, managerContext);
	}

	public Model findTagLibraryModel(final String uri) {
		return getQueryHelper().getModel(uri);
	}

	public Entity findTagEntity(final Model tagLib, final String tagName) {
		return getQueryHelper().getEntity(tagLib, tagName);
	}

	public Entity findTagAttributeEntity(final Entity tagEntity, final String attributeName) {
		return getQueryHelper().getEntity(tagEntity, attributeName);
	}

	public Trait findTrait(final Entity entity, final String traitId) {
		return getQueryHelper().getTrait(entity, traitId);
	}

	public IResultSet<Entity> findEntities(final ITaglibDomainEntityPredicateMatcher matcher) {
		final TaglibDomainEntityQuerySpecVisitor visitor = new TaglibDomainEntityQuerySpecVisitor(getDomainContext(), matcher);
		return visitor.findEntities();
	}

	public IResultSet<Trait> findTraits(final ITaglibDomainEntityPredicateMatcher entityMatcher, final IPredicateMatcher<Trait> traitMatcher) throws MetaDataException {
		final TraitQuerySpecVisitor visitor = new TraitQuerySpecVisitor(findEntities(entityMatcher).getResults(), traitMatcher);
		return visitor.findTraits();	
	}

	public Entity findEntity(final ITaglibDomainEntityPredicateMatcher entityMatcher) {
		final HierarchicalSearchControl control =  new HierarchicalSearchControl();
		control.setCountLimit(1);
		final TaglibDomainEntityQuerySpecVisitor visitor = new TaglibDomainEntityQuerySpecVisitor(getDomainContext(), entityMatcher, control);
		final IResultSet<Entity> results = visitor.findEntities();
		return getQueryHelper().getFirstFromResultSet(results);
	}

	public Trait findTrait(final ITaglibDomainEntityPredicateMatcher entityMatcher, final IPredicateMatcher<Trait> traitMatcher) {
		TraitQuerySpecVisitor visitor;
		try {
			final SearchControl control =  new SearchControl();
			control.setCountLimit(1);
			visitor = new TraitQuerySpecVisitor(findEntities(entityMatcher).getResults(), traitMatcher, control);
			IResultSet<Trait> results = visitor.findTraits();
			return getQueryHelper().getFirstFromResultSet(results);
		} catch (MetaDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
