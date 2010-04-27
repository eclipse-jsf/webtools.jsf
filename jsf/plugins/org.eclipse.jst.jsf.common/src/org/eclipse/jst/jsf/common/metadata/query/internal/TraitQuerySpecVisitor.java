/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.query.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.query.AbstractTraitQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.IResultSet;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataException;
import org.eclipse.jst.jsf.common.metadata.query.internal.IPredicateMatcher.MATCH;

/**
 * Visits a set of entities matching by trait predicate matcher
 *
 */
public class TraitQuerySpecVisitor extends AbstractTraitQueryVisitor {

	private List<Trait> 					_traitResults;
	private Collection<Entity> 				_entities;
	private IPredicateMatcher<Trait> 		_matcher;
	private SearchControl 					_control;
	private boolean 						_stop;
	

	/**
	 * Constructor using default search control
	 * @param entities
	 * @param traitMatcher
	 */
	public TraitQuerySpecVisitor(final Collection<Entity> entities, final IPredicateMatcher<Trait> traitMatcher) {
		this(entities, traitMatcher, new SearchControl());
	}

	/**
	 * Constructor using search control for a single match
	 * @param entities
	 * @param traitMatcher
	 * @param control
	 */
	public TraitQuerySpecVisitor(final Collection<Entity> entities, final IPredicateMatcher<Trait> traitMatcher, final SearchControl control) {
		super();
		_entities = entities;
		_matcher = traitMatcher;
		_traitResults = new ArrayList<Trait>();
		_control = control;
	}

	/**
	 * @return IResultSet<Trait> - will not be null
	 * @throws MetaDataException
	 */
	public IResultSet<Trait> findTraits() throws MetaDataException {
		
		_matcher.reset();
		
		if (_entities != null && _entities.size() > 0){
			ENTITIES_LOOP: for (final Entity entity : _entities) {
				final Iterator<Trait> it = entity.getTraits().iterator();
				while (it.hasNext()) {
					Trait trait = it.next();
					trait.accept(this);
					checkShouldStopVisiting();
					if (stopVisiting())
						break ENTITIES_LOOP;
				}
			}			
		}		
		
		return new SimpleResultSet(_traitResults);
	}

	public boolean stopVisiting() {
		return _stop;
	}

	private void checkShouldStopVisiting(){
		//implement how to set stop to signal to the entity accept() to skip visiting
		if (_stop == false
				&& _control.getCountLimit()== _traitResults.size() 
				&& _control.getCountLimit() != SearchControl.COUNT_LIMIT_NONE )
			
			_stop = true;
	}

	@Override
	public void visit(final Trait trait) {
		final MATCH match = _matcher.matches(trait);
		if (match == MATCH.FULLY) {
			_traitResults.add(trait);			
		}
	}

}
