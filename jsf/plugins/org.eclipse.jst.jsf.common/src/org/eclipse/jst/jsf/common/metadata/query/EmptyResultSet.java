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
 * Implementation of an empty result set
 * <p><b>Provisional API - subject to change</b></p>
 */
public final class EmptyResultSet implements IResultSet {
	private boolean _isClosed = false;
	public void close() {
		_isClosed = true;
	}
	
	public List getResults() throws MetaDataException{
		if (_isClosed)
			throw new MetaDataException("Resultset is closed");
		
		return Collections.EMPTY_LIST;		
	}

	public boolean isClosed() {
		return _isClosed;
	}

}
