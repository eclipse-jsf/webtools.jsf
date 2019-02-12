/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.common.metadata.query.internal;

/**
 * Sets controls on how a tree of nodes can be searched
 */
public class HierarchicalSearchControl extends SearchControl {
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
	
	private int scope = SCOPE_ALL_LEVELS;
	
	/**
	 * Constructor using defaults of COUNT_LIMIT_NONE and SCOPE_ALL_LEVELS 
	 */
	public HierarchicalSearchControl(){
		super();
	}
	/**
	 * Constructor
	 * @param countLimit
	 * @param scope
	 */
	public HierarchicalSearchControl(int countLimit, int scope){
		super(countLimit);
		this.scope = scope;
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
