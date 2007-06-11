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

/**
 * Simple class used by {@link SimpleEntityQueryVisitorImpl} allowing some control of a metadata query.  
 * A query visitor can use this to optimize the query results and signal completion.
 */
public class SearchControl {	

	/**
	 * No limit on query results
	 */
	public static final int COUNT_LIMIT_NONE = -1;
	
	//default settings
	private int countLimit = COUNT_LIMIT_NONE;

	
	/**
	 * Constructor using defaults of COUNT_LIMIT_NONE
	 */
	public SearchControl(){
		//use default settings
	}
	
	/**
	 * Constructor
	 * @param countLimit
	 */
	public SearchControl(int countLimit){
		this.countLimit = countLimit;
	}
	
	/**
	 * @param limit results count limit
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

}
