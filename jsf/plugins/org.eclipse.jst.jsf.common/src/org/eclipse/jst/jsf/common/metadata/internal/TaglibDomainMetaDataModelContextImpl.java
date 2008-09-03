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

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;

/**
 * Simple implementation of {@link ITaglibDomainMetaDataModelContext}
 */
public class TaglibDomainMetaDataModelContextImpl extends MetaDataModelContextImpl implements ITaglibDomainMetaDataModelContext, Cloneable
{
	private final IProject _project;
	private final String _uri;
	
	/**
	 * Constructor
	 * @param project
	 * @param domain
	 * @param uri
	 */
	public TaglibDomainMetaDataModelContextImpl(final String domain, final IProject project, final String uri){
		super(domain);
		this._project = project;
		this._uri = uri;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext#getProject()
	 */
	public IProject getProject() {		
		return _project;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext#getURI()
	 */
	public String getURI() {
		return _uri;
	}
	
	public Object getAdapter(Class adapter) {
		if (adapter.equals(ITaglibDomainMetaDataModelContext.class))
			return this;
		
		return super.getAdapter(adapter);
	}

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
