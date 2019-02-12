/*******************************************************************************
 * Copyright (c) 2010, 2011 Oracle Corporation and others.
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

package org.eclipse.jst.jsf.common.metadata.internal;

import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.context.IModelContext;

/**
 * Domain context
 * <p>
 * @noimplement - see {@link MetaDataQueryContextFactory}
 *
 */
public interface IMetaDataDomainContext extends IModelContext{
	
	/**
	 * For convenience...
	 */
	public static final String TAGLIB_DOMAIN_CONTEXT_ID = "TagLibraryDomain"; //$NON-NLS-1$
	
	/**
	 * @return domain identifier for this context.  
	 */
	public String getDomainId();	
}
