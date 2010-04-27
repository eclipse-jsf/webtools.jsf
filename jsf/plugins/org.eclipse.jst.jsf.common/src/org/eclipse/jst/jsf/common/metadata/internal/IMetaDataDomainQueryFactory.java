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

import org.eclipse.jst.jsf.common.metadata.query.internal.IMetaDataQuery;

/**
 * Produces {@link IMetaDataQuery}s for a metadata domain
 *
 */
public interface IMetaDataDomainQueryFactory {
	/**
	 * @param context 
	 * @return {@link IMetaDataQuery}
	 */
	public IMetaDataQuery createQuery(final IMetaDataDomainContext context);
	
	/**
	 * @return the domain id that this factory handles
	 */
	public String getDomainIdentifier();
}
