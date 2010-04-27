/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.query.internal.taglib;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.query.internal.IMetaDataModelManagerContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataDomainContext;

/**
 * Implements {@link IMetaDataModelManagerContext} for tag library domain
 *
 */
public class TaglibDomainContextImpl 
		extends MetaDataDomainContext
		implements IMetaDataModelManagerContext {

	
	private static final String TAGLIB_DOMAIN_ID = "TagLibraryDomain"; //$NON-NLS-1$

	private final IProject 	_project;

	/**
	 * Constructor
	 * @param project
	 */
	public TaglibDomainContextImpl(final IProject project) {
		super(TAGLIB_DOMAIN_ID);
		_project = project;
	}
	
	public Object getAdapter(Class adapter) {
		if (adapter == IProject.class)
			return _project;
		return null;
	}

	public IProject getProject() {
		return _project;
	}
}
