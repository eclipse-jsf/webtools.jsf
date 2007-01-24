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
package org.eclipse.jst.jsf.common.metadata.internal.provisional.query;

/**
 * Simple class used by {@link SimpleMetaDataQueryVisitorImpl} allowing some control of a metadata query.  
 * A query visitor can use this to optimize the query results and signal completion.
 */
public class SearchControl {
	
	//scope levels
	/**
	 * Do not recurse.  
	 */
	public static final int SCOPE_CURRENT_LEVEL = 0;
	/**
	 * Allow for one level of children to be visited from initial context
	 */
	public static final int SCOPE_ONE_LEVEL = 1;
	/**
	 * Allow unlimited recursion of children
	 */
	public static final int SCOPE_ALL_LEVELS = 2;
	
	/**
	 * No limit on query results
	 */
	public static final int COUNT_LIMIT_NONE = -1;
	
	//default settings
	private int countLimit = COUNT_LIMIT_NONE;
	private int scope = SCOPE_ALL_LEVELS;
	
	/**
	 * Constructor using defaults of COUNT_LIMIT_NONE and SCOPE_ALL_LEVELS 
	 */
	public SearchControl(){
		//use default settings
	}
	
	/**
	 * Constructor
	 * @param countLimit
	 * @param scope
	 */
	public SearchControl(int countLimit, int scope){
		this.scope = scope;
		this.countLimit = countLimit;
	}
	
	/**
	 * @param query results count limit
	 */
	public void setCountLimit(int limit){
		this.countLimit = limit;
	}
	
	/**
	 * @return query results count limit
	 */
	public int getCountLimit(){
		return countLimit;
	}
	
	/**
	 * @param scope
	 */
	public void setScope(int scope){
		this.scope= scope;
	}
	
	/**
	 * @return scope
	 */
	public int getScope(){
		return scope;
	}
}
