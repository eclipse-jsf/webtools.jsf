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

import org.eclipse.jst.jsf.common.metadata.query.IMetaDataModelContext;

/**
 * Simple implementation of {@link IMetaDataModelContext}
 *
 */
public class MetaDataModelContextImpl implements IMetaDataModelContext {
	private String _domain;
	
	/**
	 * Constructor
	 * @param domain id
	 */
	public MetaDataModelContextImpl(String domain){
		_domain = domain;
	}
	
	public String getDomainID() {
		return _domain;
	}

	public Object getAdapter(Class adapter) {
		if (adapter.equals(IMetaDataModelContext.class))
			return this;
		return null;
	}

}
