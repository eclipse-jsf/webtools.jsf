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
 * @deprecated - Helios
 *
 */
public class MetaDataModelContextImpl implements IMetaDataModelContext, Cloneable {
	private final String _domain;
	
	/**
	 * Constructor
	 * @param domain id
	 */
	public MetaDataModelContextImpl(final String domain){
		_domain = domain;
	}
	
	public String getDomainID() {
		return _domain;
	}

	public Object getAdapter(final Class adapter) {
		if (IMetaDataModelContext.class.equals(adapter))
			return this;
		return null;
	}

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
