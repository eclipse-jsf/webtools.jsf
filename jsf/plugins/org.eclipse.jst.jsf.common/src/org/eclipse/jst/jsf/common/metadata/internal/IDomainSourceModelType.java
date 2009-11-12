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
package org.eclipse.jst.jsf.common.metadata.internal;

import java.util.Set;

/**
 * Binds a domain of metadata to a source model type which is defined by a {@link IMetaDataLocator} 
 * and set of {@link IMetaDataTranslator}s
 * Not intended to be implemented by clients.  Created from ext-pts.
 */
public interface IDomainSourceModelType {
	/**
	 * @return domain id
	 */
	public String getDomain();
	/**
	 * @return IMetaDataLocator
	 */
	public IMetaDataLocator getLocator();
//	public int getOrdinal();
	/**
	 * @return set of {@link IMetaDataTranslator}s for the domain source model type
	 */
	public Set/*<IMetaDataTranslator>*/ getTranslators();
}
