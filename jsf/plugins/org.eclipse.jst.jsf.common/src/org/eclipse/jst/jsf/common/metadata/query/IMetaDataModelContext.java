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

import org.eclipse.jst.jsf.context.IModelContext;

/**
 * <p><b>Provisional API - subject to change</b></p>
 * @deprecated - Helios
 */
public interface IMetaDataModelContext extends IModelContext {
	/**
	 * @return domain identifier for this context.  
	 * <p>This must match the domain id from the org.eclipse.jst.jsf.common.DomainLoadingStrategies
	 */
	public String getDomainID();
}
