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

import org.eclipse.jst.jsf.common.metadata.Model;

/**
 * Context for establishing the metadata model.   
 * TODO: Should probably be moved to query internal package
 * <p>
 * @noimplement - not intended to be implemented, users must subclass {@link MetaDataModelContext} if necessary
 */
public interface IMetaDataModelContext extends IMetaDataDomainContext {
	
	/**
	 * @return the string that will uniquely identify the {@link Model} within this domain of metadata
	 */
	public String getModelIdentifier();
}
