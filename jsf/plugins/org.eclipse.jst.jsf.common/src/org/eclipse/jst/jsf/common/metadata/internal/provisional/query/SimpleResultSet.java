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

import java.util.ArrayList;
import java.util.List;


/**
 * Simple implementation of {@link IResultSet}
 *
 */
public class SimpleResultSet/*<T>*/ implements IResultSet/*<T>*/ {
	
	private List/*<T>*/ results;
	private int pos = -1;
	
	/**
	 * Constructor passing a list to hold the results
	 * @param results 
	 */
	public SimpleResultSet(List/*<T>*/ results){
		super();
		this.results = results;
	}
	
	/**
	 * Constructor
	 */
	public SimpleResultSet(){
		results = new ArrayList/*<T>*/();
	}
	
	/* (non-Javadoc)
	 * @see java.util.Enumeration#hasMoreElements()
	 */
	public boolean hasMoreElements() {
		if (results == null || results.size() ==0)
			return false;
		
		if (pos == results.size())
			return false;
				
		return true;
	}

	//public T nextElement() {
	/* (non-Javadoc)
	 * @see java.util.Enumeration#nextElement()
	 */
	public Object nextElement() {
		if (pos == -1) pos = 0;
		//T ret = (T)results.get(pos);
		Object ret = results.get(pos);
		pos++;
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IResultSet#close()
	 */
	public void close() {
		//nothing to do really
		results.clear();
		pos = -1;
	}
	
	//public void addItem(T item){
	/**
	 * @param item
	 */
	public void addItem(Object item){
		getResults().add(item);
	}

	private List getResults() {
		if (results == null)
			results = new ArrayList/*<T>*/();
	
		return results;
	}

	/**
	 * @return resultset size
	 */
	public int size(){
		return getResults().size();
	}
}
