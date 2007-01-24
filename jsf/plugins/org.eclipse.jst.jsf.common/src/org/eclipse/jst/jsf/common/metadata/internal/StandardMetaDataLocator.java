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
package org.eclipse.jst.jsf.common.metadata.internal;

import java.util.List;


/**
 * Implementation of IMetaDataLocator for standard metadata sources.
 * Uses StandardMetaDataFileRegistry to locate instances.
 *
 */
public class StandardMetaDataLocator extends AbstractMetaDataLocator {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataLocator#locateMetaDataModelProviders(java.lang.String)
	 */
	public List locateMetaDataModelProviders(String uri) {
		return StandardMetaDataFileRegistry.getInstance().getStandardMetaDataModelProviders(uri);
	}

	/* 
	 * Does nothing.
	 */
	public void stopLocating() {
		//do nothing

	}


}
