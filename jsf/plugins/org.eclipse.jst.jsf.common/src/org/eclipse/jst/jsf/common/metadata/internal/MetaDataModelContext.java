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

package org.eclipse.jst.jsf.common.metadata.internal;

import org.eclipse.core.resources.IProject;

/**
 * Implementation of IMetaDataModelContext2
 *
 */
public final class MetaDataModelContext implements IMetaDataModelContext {

	private final IProject 	_project;
	private final String 	_domainId;
	private final String 	_modelId;

	/**
	 * @param project
	 * @param domainId
	 * @param modelId
	 */
	public MetaDataModelContext(final IProject project, final String domainId, final String modelId) {
		_project 	= project;
		_domainId 	= domainId;
		_modelId 	= fixJSPURIIfNecessary(modelId);  //major hack carried forward from ModelKeyDescriptor!
	}
	
	//this is a workaround for issue where jsp "uri" may upper or lower cased
	private String fixJSPURIIfNecessary(final String tempuri) {
		if (tempuri != null && tempuri.equals("jsp11")) //$NON-NLS-1$
			return tempuri.toUpperCase();
		return tempuri;
	}
	
	public Object getAdapter(Class adapter) {
		if (adapter == ModelKeyDescriptor.class)
			return new ModelKeyDescriptor(_project, _domainId, _modelId);
		return null;
	}

	public IProject getProject() {
		return _project;
	}

	public String getDomainId() {
		return _domainId;
	}

	public String getModelIdentifier() {
		return _modelId;
	}
	
	@Override
	public String toString() {
		final StringBuffer buf = new StringBuffer();
		if (getProject() != null) {
			buf.append(getProject().getName()).append(":"); //$NON-NLS-1$
		}
		buf.append(getDomainId()).append(":"); //$NON-NLS-1$
		buf.append(getModelIdentifier()).append(":"); //$NON-NLS-1$
		return buf.toString();
	}

}
