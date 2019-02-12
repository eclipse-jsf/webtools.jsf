/*******************************************************************************
 * Copyright (c) 2007, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
 * @deprecated - Helios
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
