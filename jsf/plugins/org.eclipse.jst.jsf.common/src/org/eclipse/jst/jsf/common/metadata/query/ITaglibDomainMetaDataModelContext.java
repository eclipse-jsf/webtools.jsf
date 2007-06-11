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
package org.eclipse.jst.jsf.common.metadata.query;

import org.eclipse.core.resources.IProject;

/**
 * Context for determining the Model to locate in the MetaDataManager
 * <p>Should NOT be implemented by clients
 * <p><b>Provisional API - subject to change</b></p>
 */
public interface ITaglibDomainMetaDataModelContext extends IMetaDataModelContext {
	/**
	 * @return project 
	 */
	public IProject getProject();
	/**
	 * @return the namespace URI as String
	 */
	public String getURI();
}
