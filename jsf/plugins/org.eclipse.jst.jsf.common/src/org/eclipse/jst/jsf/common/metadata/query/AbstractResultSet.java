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

import java.util.Collections;
import java.util.List;

/**
 * Abstract implementation of {@link IResultSet} that developers may subclass.
 * Users should assume that the results are only valid at the time of the query.  This may change in the future.
 * <p><b>Provisional API - subject to change</b></p>
 * @param <T> 
 */
public abstract class AbstractResultSet<T> implements IResultSet<T> {
	private List		_results;
	private boolean 	_isClosed = false;
	
	public List<T> getResults() throws MetaDataException {
		initIfNecessary();
		return _results;
	}
	
	public final void close() throws MetaDataException {
		doClose();
		_isClosed = true;
		_results = null;
	}

	/**
	 * Overridable method to close resultset.  This is called by close()
	 * @throws MetaDataException
	 */
	protected void doClose() throws MetaDataException {
		//subclasses to provide override
	}
	
	public final boolean isClosed() {		
		return _isClosed;
	}

	/**
	 * @return List of results.  Null is tolerated.
	 */
	protected abstract List<T> getInternalResults();
	
	private void initIfNecessary() throws MetaDataException {
		if (_isClosed)
			throw new MetaDataException("Attempt to access a closed resultset."); //$NON-NLS-1$
		
		if (_results == null) {
			List<T> checkNullResults = getInternalResults();
			if (checkNullResults == null){
				checkNullResults = Collections.EMPTY_LIST;
			} 
			_results = Collections.unmodifiableList(checkNullResults);
		}
	}

}
