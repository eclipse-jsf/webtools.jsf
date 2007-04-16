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
import org.eclipse.jst.jsf.common.metadata.query.IMetaDataModelContext;

/**
 * Simple implementation of {@link IMetaDataModelContext}
 */
public class MetaDataModelContextImpl implements IMetaDataModelContext, Cloneable{
	private IProject project;
	private String domain;
	private String uri;
	
	/**
	 * Constructor
	 * @param project
	 * @param domain
	 * @param uri
	 */
	public MetaDataModelContextImpl(IProject project, String domain, String uri){
		this.project = project;
		this.domain = domain;
		this.uri = uri;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IMetaDataModelContext#getDomain()
	 */
	public String getDomain() {
		return domain;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IMetaDataModelContext#getProject()
	 */
	public IProject getProject() {		
		return project;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IMetaDataModelContext#getURI()
	 */
	public String getURI() {
		return uri;
	}

}
