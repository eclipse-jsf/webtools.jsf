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
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.query.AbstractEntityQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.IResultSet;


/**
 * A simple metadata query visitor implementing {@link org.eclipse.jst.jsf.common.metadata.query.IEntityQueryVisitor} and {@link org.eclipse.jst.jsf.common.metadata.query.ITraitQueryVisitor}.<p>
 * - simple find entity and traits by id only <br>	
 * - does not allow for wild card searchs<br>
 * <p>
 * 	TODO - fix for case-sensitivity   https://bugs.eclipse.org/bugs/show_bug.cgi?id=212794
 * 
 */
public class SimpleEntityQueryVisitorImpl extends AbstractEntityQueryVisitor implements IHierarchicalEntityVisitor {
	private HierarchicalSearchControl control;
	private boolean _stop;

	private EntityQueryFilterVisitor entityQuery;
	private List/*<Entity>*/ _entityResults;
	private Entity initialEntityContext;

	/**
	 * Constructor that also creates a default SearchControl
	 */
	public SimpleEntityQueryVisitorImpl() {
		super();
		control = new HierarchicalSearchControl();
	}
	
	/**
	 * Constructor
	 * @param control
	 */
	public SimpleEntityQueryVisitorImpl(final HierarchicalSearchControl control) {
		super();
		this.control = control;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.query.IEntityQueryVisitor#findEntities(org.eclipse.jst.jsf.common.metadata.Entity, java.lang.String)
	 */
	public IResultSet/*<Entity>*/ findEntities(final Entity initialEntity,
			final String entityKey) {
		
		resetQuery();
		
		if (initialEntity != null){
			this.initialEntityContext = initialEntity;
			entityQuery = new EntityQueryFilterVisitor(initialEntity.getId(), entityKey);			
			initialEntity.accept(this);			
		}
		
		return new SimpleResultSet(getInternalEntityResults());
	}

	private void resetQuery() {
		_stop = false;
		_entityResults = null;
	}

	private List/*<Entity>*/ getInternalEntityResults(){
		if (_entityResults == null){
			_entityResults = new ArrayList/*<Entity>*/();
		}
		return _entityResults;
	}


	public boolean visitEnter(final Entity entity) {
		
		if (entity == initialEntityContext)
			return true;
		
		entityQuery.pushLevel();
		if (entityQuery.canVisit(entity)) 
			return entityQuery.visit(entity);
		
		return false;
	}

	public boolean visitLeave(Entity entity) {
		checkShouldStopVisitingEntities();
		if (entity != initialEntityContext)
			entityQuery.popLevel();
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.query.IEntityVisitor#visit(org.eclipse.jst.jsf.common.metadata.Entity)
	 */
	public void visit(final Entity key) {
		//do nothing... all work now done in visitEnter/visitLeave
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.query.IMetaDataVisitor#stopVisiting()
	 */
	public boolean stopVisiting() {
		return _stop;
	}

	private void checkShouldStopVisitingEntities(){
		//implement how to set stop to signal to the entity accept() to skip visiting
		if (_stop == false
				&& control.getCountLimit()== getInternalEntityResults().size() 
				&& control.getCountLimit() != SearchControl.COUNT_LIMIT_NONE )
			
			_stop = true;
	}

	/**
	 * Visitor that filters and acts upon hierarchical data that compares that an entity's id for with another with case-insensitive compare
	 */
	private class EntityQueryFilterVisitor {

		private String entityId;
		private List<String> entityQueue;
		private int curLevel = 0;
		
		/**
		 * Constructor
		 * @param initialContextId - Entity id from which the query is rooted
		 * @param queryKey - query key which may be compound ("A/B/C")
		 */
		public EntityQueryFilterVisitor(final String initialContextId, final String queryKey){			
			init(initialContextId, queryKey);			
		}
		
		private void init(final String initialContextId, final String key) {
			entityQueue = new ArrayList<String>(3);
			addLevel(initialContextId);
			if (key == null || key.trim().equals("") || key.trim().equals("/")){  //$NON-NLS-1$ //$NON-NLS-2$
				addLevel(""); //$NON-NLS-1$
			}
			else {
				final StringTokenizer st = new StringTokenizer(key, "/"); //$NON-NLS-1$
				String partialKey = st.nextToken();
				addLevel(partialKey);
				while (st.hasMoreElements()){
					partialKey = st.nextToken();
					addLevel(partialKey);
				}
			}
		}

		/**
		 * @param entity
		 * @return flag indicating that filter was passed and children may be visited
		 */
		public boolean canVisit(final Entity entity) {
			// only one filter rule... does this entity id match the current level's entity id (case-insensitive) 
			return entityId.compareTo(entity.getId().toUpperCase()) == 0;
		}

		/**
		 * Operates on passed entity and determines if it should be part of the query results
		 * @param entity
		 * @return true if children of entity should be visited
		 */
		public boolean visit(final Entity entity) {
			//one operation... if we have found the leaf-most entity in the query, add it to the results and go no deeper
			if (curLevel == entityQueue.size() - 1) {
				getInternalEntityResults().add(entity);
				return false;
			}
			return true;
		}

		private void addLevel(final String key) {
			entityQueue.add(key.toUpperCase());
		}
		
		/**
		 * Move up one level in the query 
		 */
		public void popLevel(){
			entityId = entityQueue.get(--curLevel);
		}
		
		/**
		 * Move down one level in the query
		 */
		public void pushLevel() {
			entityId = entityQueue.get(++curLevel);						
		}
	}

}
