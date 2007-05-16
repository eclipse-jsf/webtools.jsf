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
 * Implementation of an empty result set
 */
public class EmptyResultSet implements IResultSet {

	public void close() {//
	}

	public int getSize() throws MetaDataException {
		return 0;
	}

	public boolean hasNext() throws MetaDataException {				
		return false;
	}

	public Object next() throws MetaDataException {
		return null;
	}

}
