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

package org.eclipse.jst.jsf.common.metadata.query.internal.taglib;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelManager;
import org.eclipse.jst.jsf.common.metadata.internal.MetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.internal.MetaDataModelManagerFactory;
import org.eclipse.jst.jsf.common.metadata.query.AbstractEntityQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.IResultSet;
import org.eclipse.jst.jsf.common.metadata.query.internal.HierarchicalSearchControl;
import org.eclipse.jst.jsf.common.metadata.query.internal.IHierarchicalEntityVisitor;
import org.eclipse.jst.jsf.common.metadata.query.internal.IPredicateMatcher.MATCH;
import org.eclipse.jst.jsf.common.metadata.query.internal.SearchControl;
import org.eclipse.jst.jsf.common.metadata.query.internal.SimpleResultSet;

/**
 * @author gekessle
 *
 */
public class TaglibDomainEntityQuerySpecVisitor extends AbstractEntityQueryVisitor implements IHierarchicalEntityVisitor {
	private HierarchicalSearchControl 			_control;
	private ITaglibDomainEntityPredicateMatcher	_matcher;
	private IMetaDataDomainContext 				_context;
	private List<Entity> 						_entityResults;
	private Model 								_initialEntity;
	private boolean 							_stop;

	/**
	 * Constructor with default SearchControl 
	 * @param context 
	 * @param matcher
	 */
	public TaglibDomainEntityQuerySpecVisitor(final IMetaDataDomainContext context, final ITaglibDomainEntityPredicateMatcher matcher) {
		this(context, matcher, new HierarchicalSearchControl());
	}
	
	/**
	 * Constructor 
	 * @param context 
	 * @param matcher
	 * @param control 
	 */
	public TaglibDomainEntityQuerySpecVisitor(final IMetaDataDomainContext context, final ITaglibDomainEntityPredicateMatcher matcher, final HierarchicalSearchControl control) {
		_matcher = matcher;
		_context = context;		
		_control = control;
	}

	/**
	 * @return Set of {@link Entity}s matching the entity query spec
	 * Note: does not support wild card match of models yet.
	 */
	public IResultSet<Entity> findEntities() {// throws InvalidQuerySpecification, MetaDataException {
		_matcher.reset();
		
		//first find model according to matcher...   
		//DOES NOT SUPPORT WILD CARDS FOR MODEL URI YET!!!
		//we cannot support wild cards because models are loaded lazily; even their id's.
		final IMetaDataModelManager mgr = MetaDataModelManagerFactory.getMetaDataModelManagerInstance(getProject());
		if (mgr != null) {
			final Model model= mgr.getModel(getModelContext());
			findEntities(model);			
		} else {
			//throw err?
		}
		return new SimpleResultSet<Entity>(getInternalEntityResults());
	}

	private void findEntities(final Model model) {

		if (model != null){
			_initialEntity = model;	
			_initialEntity.accept(this);			
		}

	}

	private List<Entity> getInternalEntityResults(){
		if (_entityResults == null){
			_entityResults = new ArrayList<Entity>();
		}
		return _entityResults;
	}
	
	private IMetaDataModelContext getModelContext() {
		return new MetaDataModelContext(getProject(), _context.getDomainId(), _matcher.getUri());
	}

	private IProject getProject() {		
		return (IProject)_context.getAdapter(IProject.class);
	}

	public boolean visitEnter(final Entity entity) {
		_matcher.pushLevel();
		if (_matcher.getCurrentLevel() <= _matcher.getMaxLevel()) {
			final MATCH match = _matcher.matches(entity);
			if (match == MATCH.FULLY) {
				getInternalEntityResults().add(entity);
				checkShouldStopVisitingEntities();
				return false;
			}
			else if (match == MATCH.PARTIALLY) {
				return true;
			}
		}
		return false;
	}

	public boolean stopVisiting() {
		return _stop;
	}

	private void checkShouldStopVisitingEntities(){
		//implement how to set stop to signal to the entity accept() to skip visiting
		if (_stop == false
				&& _control.getCountLimit()== getInternalEntityResults().size() 
				&& _control.getCountLimit() != SearchControl.COUNT_LIMIT_NONE )
			
			_stop = true;
	}

	public boolean visitLeave(Entity entity) {
//		_stop = _matcher.checkShouldStopVisitingEntities();
//		if (entity != _initialEntity)
//			_entityQuery.popLevel();
		if (entity != _initialEntity) 
			_matcher.popLevel();
		return true;
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.query.IEntityVisitor#visit(org.eclipse.jst.jsf.common.metadata.Entity)
	 */
	public void visit(final Entity key) {
		//do nothing... all work now done in visitEnter/visitLeave
	}
	
}
