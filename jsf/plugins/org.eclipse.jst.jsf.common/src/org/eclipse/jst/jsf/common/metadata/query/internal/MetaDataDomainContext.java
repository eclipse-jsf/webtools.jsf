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

import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;

/**
 * Implements {@link IMetaDataDomainContext}
 *
 */
public class MetaDataDomainContext implements IMetaDataDomainContext {

	private final String _domainId;

	/**
	 * Constructor
	 * @param domainId
	 */
	public MetaDataDomainContext(final String domainId) {
		_domainId = domainId;
	}
	public Object getAdapter(final Class adapter) {
		return null;
	}

	public final String getDomainId() {
		return _domainId;
	}		

}
