/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
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
package org.eclipse.jst.jsf.common.metadata.internal;


/**
 * Defines a strategy to use for loading a standard metadata model for a certain domain.
 * see <code>org.eclipse.jst.jsf.common.domainLoadingStrategies</code> ext-pt 
 */
public interface IDomainLoadingStrategy {
	
	/**
	 * Create a merged "standard" metadata model.  
	 * @param model to load
	 * 
	 */
	public void load(MetaDataModel model);
	
	/**
	 * Reload a merged "standard" metadata model.  
	 * Allows for some optimization for reload.
	 * @throws ModelNotSetException 
	 * 
	 */
	public void reload() throws ModelNotSetException;
	
	
	/**
	 * Opportunity to cleanup and should be called when strategy is being destroyed
	 */
	public void cleanup();

}
