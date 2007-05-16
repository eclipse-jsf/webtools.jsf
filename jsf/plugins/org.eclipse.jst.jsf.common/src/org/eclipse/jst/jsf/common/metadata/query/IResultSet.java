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



/**
 * Results from a metadata query.  The resultset should be considered valid only at the time that the query is performed.
 * 
 * Not intended to be implemented directly by clients.  Developers should extend {@link AbstractResultSet} instead.
 */
public interface IResultSet/*<T>*/{
	
	/**
	 * @return size of the resultset.  
	 * @throws MetaDataException 
	 */
	public int getSize() throws MetaDataException;
		
	/**
	 * @return next result.   Clients should check hasNext() before calling.
	 * @throws MetaDataException 
	 */
	public Object/*<T>*/ next() throws MetaDataException;
	
	/**
	 * @return true if the resultset has more elements
	 * @throws MetaDataException
	 */
	public boolean hasNext() throws MetaDataException;

	/**
	 * Signal that the query results are no longer required allowing for any cleanup that may be required
	 * @throws MetaDataException 
	 */
	public void close() throws MetaDataException;
}
