/*******************************************************************************
 * Copyright (c) 2011 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.query.internal;

import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelContext;

/**
 * Simple implementation of {@link IMetaDataModelContext}
 *
 */
public class MetaDataModelContext implements IMetaDataModelContext {
	
	private String _id;
	private IMetaDataDomainContext _domainContext;

	/**
	 * @param identifier
	 * @param domainContext
	 */
	public MetaDataModelContext(final String identifier, final IMetaDataDomainContext domainContext) {
		_id = identifier;
		_domainContext = domainContext;
	}
	
	public String getDomainId() {
		return _domainContext.getDomainId();
	}

	public Object getAdapter(Class adapter) {
		return _domainContext.getAdapter(adapter);
	}

	public String getModelIdentifier() {
		return _id;
	}

}
