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


import java.util.Collections;
import java.util.List;

import org.eclipse.jst.jsf.common.metadata.query.AbstractResultSet;

/**
 * Simple implementation of {@link org.eclipse.jst.jsf.common.metadata.query.IResultSet} by extending AbstractResultSet
 *
 */
public final class SimpleResultSet/*<T>*/ extends AbstractResultSet/*<T>*/ {	
	private List/*<T>*/ results;	
	
	/**
	 * Constructor passing a list to hold the results
	 * @param results 
	 */
	public SimpleResultSet(List/*<T>*/ results){
		super();
		this.results = results;
	}

	protected List getInternalResults(){
		if (results == null){
			results = Collections.EMPTY_LIST;
		}
		return results;
	}
	

}

