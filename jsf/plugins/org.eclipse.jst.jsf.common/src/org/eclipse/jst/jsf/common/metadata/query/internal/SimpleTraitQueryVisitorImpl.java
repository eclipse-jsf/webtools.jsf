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
package org.eclipse.jst.jsf.common.metadata.query.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.query.AbstractTraitQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.IResultSet;
import org.eclipse.jst.jsf.common.metadata.query.ITraitQueryVisitor;


/**
 * A simple metadata query visitor implementing {@link ITraitQueryVisitor}.
 * - simple find traits by id only 	
 * - Does not allow for wild card searchs
 */
public class SimpleTraitQueryVisitorImpl extends AbstractTraitQueryVisitor  {

	private String _traitQuery;
	private SearchControl _control;
	private boolean _stop;
	private List/*<Trait>*/ _traitResults;

	/**
	 * Constructor that also creates a default SearchControl
	 */
	public SimpleTraitQueryVisitorImpl() {
		super();
		_control = new SearchControl();
	}
	
	/**
	 * Constructor
	 * @param control
	 */
	public SimpleTraitQueryVisitorImpl(SearchControl control) {
		super();
		this._control = control;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.query.ITraitQueryVisitor#findTraits(org.eclipse.jst.jsf.common.metadata.Entity, java.lang.String)
	 */
	public IResultSet/*<Trait>*/ findTraits(final Entity entity, final String traitQuery){
		
		resetQuery();
		if (entity != null){			
			this._traitQuery = traitQuery;			
			for (Iterator/*<Trait>*/ it=entity.getTraits().iterator();it.hasNext();){
				Trait t = (Trait)it.next();
				t.accept(this);
				if (stopVisiting())
					break;
			}
		}
		return new SimpleResultSet(getInternalTraitResults());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.query.ITraitVisitor#visit(org.eclipse.jst.jsf.common.metadata.Trait)
	 */
	public void visit(Trait trait) {		
		if (trait.equals(_traitQuery))
			getInternalTraitResults().add(trait);		
		
		checkShouldStopVisitingTraits();
	}

	/**
	 * 
	 */
	private void resetQuery() {
		_stop = false;
		_traitResults = null;
	}

	/**
	 * @return lazy init of a SimpleResultSet of Traits
	 */
	private List/*<Trait>*/ getInternalTraitResults(){
		if (_traitResults == null){
			_traitResults = new ArrayList/*<Trait>*/();
		}
		return _traitResults;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.query.IMetaDataVisitor#stopVisiting()
	 */
	public boolean stopVisiting() {
		return _stop;
	}

	private void checkShouldStopVisitingTraits(){
		if (_control.getCountLimit()== getInternalTraitResults().size() && _control.getCountLimit() != SearchControl.COUNT_LIMIT_NONE)
			_stop = true;
	}
	
}
