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

import java.util.List;



/**
 * Results from a metadata query.  The resultset should be considered valid only at the time that the query is performed.
 * Once "closed", the resultset should not be accessed again. 
 * 
 * <p>Not intended to be implemented directly by clients.  Developers should extend {@link AbstractResultSet} instead.
 * <p><b>Provisional API - subject to change</b></p>
 */
public interface IResultSet/*<T>*/{
	
	/**
	 * @return unmodifiable List of results.  May NOT be null.  Implementer must return Collections.EMPTY_LIST instead.
	 * @throws MetaDataException 
	 */
	public List/*<T>*/ getResults() throws MetaDataException;

	/**
	 * Signal that the query results are no longer required allowing for any cleanup that may be required
	 * Once a resultset is closed, a MetaDataException should be thrown if the next() or hasNext() is called.  Clients can check isClosed() first.
	 * @throws MetaDataException 
	 */
	public void close() throws MetaDataException;
	
	/**
	 * @return true if this resultset has been closed. 
	 */
	public boolean isClosed();
}
