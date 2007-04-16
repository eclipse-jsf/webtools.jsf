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
 * Results from a metadata query.
 * 
 * Not intended to be implemented directly by clients.  Developers may extend {@link AbstractResultSet} instead.
 */
public interface IResultSet/*<T>*/{
	
	/**
	 * @return List of results.  May NOT be null.  Implementer must return Collections.EMPTY_LIST instead.
	 */
	public List/*<T>*/ getResults();
	
	/**
	 * Signal that the query results are no longer required allowing for any cleanup that may be required
     * API: event to signal that result set is no longer valid?
	 */
	public void close();
}
