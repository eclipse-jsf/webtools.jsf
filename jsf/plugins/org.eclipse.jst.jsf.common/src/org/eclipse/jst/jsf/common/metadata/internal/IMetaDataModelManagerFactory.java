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

package org.eclipse.jst.jsf.common.metadata.internal;

import org.eclipse.core.resources.IResource;

/**
 * A factory that will produce {@link IMetaDataModelManager}s
 */
public interface IMetaDataModelManagerFactory {
	/**
	 * @param projectOrWorkspaceRoot
	 * @return IMetaDataModelManager for the project, or shared IMetaDataModelManager for the workspace
	 */
	public IMetaDataModelManager getInstance(IResource projectOrWorkspaceRoot);
}
