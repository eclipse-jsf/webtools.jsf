/*******************************************************************************
 * Copyright (c) 2007, 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
			throw new MetaDataException("Resultset is closed"); //$NON-NLS-1$
		
		return Collections.EMPTY_LIST;		
	}

	public boolean isClosed() {
		return _isClosed;
	}

}
