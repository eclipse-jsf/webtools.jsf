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

import org.eclipse.jst.jsf.context.IModelContext;

/**
 * <p><b>Provisional API - subject to change</b></p>
 */
public interface IMetaDataModelContext extends IModelContext {
	/**
	 * @return domain identifier for this context.  
	 * <p>This must match the domain id from the org.eclipse.jst.jsf.common.DomainLoadingStrategies
	 */
	public String getDomainID();
}
