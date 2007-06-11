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
import java.util.Stack;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.EntityGroup;
import org.eclipse.jst.jsf.common.metadata.query.AbstractEntityQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.IEntityQueryVisitor;
import org.eclipse.jst.jsf.common.metadata.query.IResultSet;
import org.eclipse.jst.jsf.common.metadata.query.ITraitQueryVisitor;


/**
 * A simple metadata query visitor implementing {@link IEntityQueryVisitor} and {@link ITraitQueryVisitor}.
 * - simple find entity and traits by id only 	
 * - Does not allow for wild card searchs
 */
public class SimpleEntityQueryVisitorImpl extends AbstractEntityQueryVisitor  {
	private HierarchicalSearchControl control;
	private boolean _stop;

	private EntityQueryComparator entityComparator;
	private List/*<Entity>*/ _entityResults;

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
	public SimpleEntityQueryVisitorImpl(HierarchicalSearchControl control) {
		super();
		this.control = control;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.query.IEntityQueryVisitor#findEntities(org.eclipse.jst.jsf.common.metadata.Entity, java.lang.String)
	 */
	public IResultSet/*<Entity>*/ findEntities(Entity initialEntityContext,
			String entityKey) {
		
		resetQuery();
		
		if (initialEntityContext != null){
			entityComparator = new EntityQueryComparator(entityKey);			
			initialEntityContext.accept(this);			
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

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.query.IEntityVisitor#visit(org.eclipse.jst.jsf.common.metadata.Entity)
	 */
	public void visit(Entity key) {		
		switch (entityComparator.compareTo(key)){
			case 0:
				getInternalEntityResults().add(key);
				break;
			default:
				break;

		}
		checkShouldStopVisitingEntities();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.query.IEntityVisitor#visitCompleted(Entity entity)
	 */
	public void visitCompleted(Entity entity) {
		entityComparator.popContext();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.query.IMetaDataVisitor#stopVisiting()
	 */
	public boolean stopVisiting() {
		return _stop;
	}

	private void checkShouldStopVisitingEntities(){
		//implement how to set stop to signal to the entity accept() to skip visiting
		if (control.getCountLimit()== getInternalEntityResults().size() && control.getCountLimit() != SearchControl.COUNT_LIMIT_NONE )
			_stop = true;
	}

	/**
	 * Simple comparator that compares that an entity's id for with another
	 */
	private class EntityQueryComparator implements Comparable/*<Entity>*/{

		private String entityKey;
		private EntityStack stack;

		/**
		 * Constructor
		 * @param entityKey
		 */
		public EntityQueryComparator(String entityKey){
			this.entityKey = entityKey;		
			stack = new EntityStack();
		}
		
		public int compareTo(Object entity) {			
			stack.push(entity);
			return entityKey.compareTo(getRelativeId());			
		}
		
		/**
		 * Pop stack
		 */
		public void popContext(){
			stack.pop();
		}
		
		private String getRelativeId(){
			int size = stack.size();
			int i = 1;
			StringBuffer buf = new StringBuffer();
			while(i < size){
				Entity e = (Entity)stack.elementAt(i);
				if (!(e instanceof EntityGroup)){
					if (buf.length()>0) 
						buf.append("/");
					buf.append(e.getId());
				}
				i++;
			}
			return buf.toString();
		}
	}
	
	/**
	 * Stack used for determining relative key of an entity from the initial context.
	 */
	private class EntityStack extends Stack/*<Entity>*/ {
		/**
         * 
         */
        private static final long serialVersionUID = -6010267544968175003L;

        /**
         * Constructor
         */
        public EntityStack(){
			super();
		}
	}

}
