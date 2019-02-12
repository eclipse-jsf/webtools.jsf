/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
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

package org.eclipse.jst.jsf.common.metadata.query.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainQueryFactory;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelManager;
import org.eclipse.jst.jsf.common.metadata.internal.MetaDataModelManagerFactory;

/**
 * Abstract query factory for a domain of metadata that all implementers of 
 * {@link IMetaDataDomainQueryFactory} must extend
 *
 */
public abstract class AbstractMetaDataDomainQueryFactory implements
		IMetaDataDomainQueryFactory {
	
	private final String _domainId;
	
	/**
	 * Constructor
	 * @param domainId 
	 */
	public AbstractMetaDataDomainQueryFactory(final String domainId) {
		_domainId = domainId;
	}
	
	public final String getDomainIdentifier() {
		return _domainId;
	}
	
	/**
	 * @param context
	 * @return IMetaDataModelManager
	 */
	protected IMetaDataModelManager getManager(final IMetaDataDomainContext context) {
		final IProject project = (IProject) context.getAdapter(IProject.class);
		return MetaDataModelManagerFactory.getMetaDataModelManagerInstance(project);				
	}

}
