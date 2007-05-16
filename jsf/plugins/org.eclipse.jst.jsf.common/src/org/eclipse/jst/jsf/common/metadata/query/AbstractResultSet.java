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

package org.eclipse.jst.jsf.common.metadata.query;

import java.util.Collection;
import java.util.Iterator;

/**
 * Default implementation of {@link IResultSet}.   Developers may subclass.
 * Currently MetaDataException is not being thrown but is in the interface for the future.
 * Users should assume that the results are only valid at the time of the query.  This may change in the future. 
 */
public abstract class AbstractResultSet/*<T>*/ implements IResultSet/*<T>*/ {
	private Iterator 	_iterator;
	private Collection	_results;
	
	
	public void close() throws MetaDataException {
		_results = null;
		_iterator = null;
	}

	public final int getSize(){
		initIfNecessary();
		return _results.size();		
	}

	public boolean hasNext() throws MetaDataException {
		initIfNecessary();
		return _iterator.hasNext();			
	}

	public Object next() throws MetaDataException {
		initIfNecessary();		
		return _iterator.next();
	}
	/**
	 * @return Collection of results.  Implementer must NOT return null.  Return Collections.EMPTY_LIST instead.
	 */
	protected abstract Collection getInternalResults();
	
	private void initIfNecessary() {
		if (_results == null) {
			_results = getInternalResults();
			_iterator = _results.iterator();
		}
	}

}
