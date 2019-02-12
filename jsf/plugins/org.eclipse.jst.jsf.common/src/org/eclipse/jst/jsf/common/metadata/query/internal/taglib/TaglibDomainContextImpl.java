/*******************************************************************************
 * Copyright (c) 2010, 2011 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.query.internal.taglib;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.IMetaDataModelManagerContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataDomainContext;

/**
 * Implements {@link IMetaDataModelManagerContext} for tag library domain
 *
 */
public class TaglibDomainContextImpl 
		extends MetaDataDomainContext
		implements IMetaDataModelManagerContext {

	
	private final IProject 	_project;
	private final IFile		_file;

	/**
	 * Constructor
	 * @param project
	 */
	public TaglibDomainContextImpl(final IProject project) {
		super(IMetaDataModelContext.TAGLIB_DOMAIN_CONTEXT_ID);
		_project = project;
		_file = null;
	}
	
	/**
	 * Constructor
	 * @param file
	 */
	public TaglibDomainContextImpl(final IFile file) {
		super(IMetaDataModelContext.TAGLIB_DOMAIN_CONTEXT_ID);
		_project = file.getProject();
		_file = file;
	}
	
	public Object getAdapter(Class adapter) {
		if (adapter == IProject.class)
			return _project;
		if (adapter == IFile.class)
			return _file;
		return null;
	}

	public IProject getProject() {
		return _project;
	}
}
