/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.metadata.query.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;

/**
 * Context for the meta data model manager
 *
 */
public interface IMetaDataModelManagerContext extends IMetaDataDomainContext {
	/**
	 * @return project - may be null
	 */
	public IProject getProject();
}
