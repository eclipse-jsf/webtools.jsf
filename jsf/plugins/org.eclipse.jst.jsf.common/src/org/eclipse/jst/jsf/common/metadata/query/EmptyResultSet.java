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

	public void close() {//
	}
	
	public List getResults(){
		return Collections.EMPTY_LIST;		
	}
//
//	public int getSize() throws MetaDataException {
//		return 0;
//	}
//
//	public boolean hasNext() throws MetaDataException {				
//		return false;
//	}
//
//	public Object next() throws MetaDataException {
//		return null;
//	}

	public boolean isClosed() {
		return false;
	}

}
