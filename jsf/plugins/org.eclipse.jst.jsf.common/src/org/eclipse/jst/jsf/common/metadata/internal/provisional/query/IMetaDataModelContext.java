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
package org.eclipse.jst.jsf.common.metadata.internal.provisional.query;

import org.eclipse.core.resources.IProject;

/**
 * Context for determining the Model to locate in the MetaDataManager
 * Should not be implemented by clients
 * API: should it extend IModelContext?
 */
public interface IMetaDataModelContext{
	/**
	 * @return project
     * API: if this becomes an IModelContext, may be able take
     * IProject off the interface and push down into an workspace resolver 
	 */
	public IProject getProject();
	/**
	 * @return domain id as defined by the DomainLoading
     * API: what's DomainLoading?  I would prefer to see a 
     * type instead of an open-ended string
	 */
	public String getDomain();
	/**
	 * @return the namespace URI as String
     * API: isn't URI part of the domain?  For example, package not URI
     * would be the hiearchy id in a Java class domain.
	 */
	public String getURI();
}
