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

import java.util.Enumeration;

/**
 * An enumeration of results from a metadata query
 *
 */
public interface IResultSet/*<T>*/ extends Enumeration/*<T>*/{
	/**
	 * Signal that the query results are no longer required allowing for any cleanup that may be required
	 */
	public void close();
}
