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

import org.eclipse.core.resources.IProject;

/**
 * A factory that will produce {@link IMetaDataModelManager}s
 */
public interface IMetaDataModelManagerFactory {
	/**
	 * @param project
	 * @return IMetaDataModelManager for the project
	 */
	public IMetaDataModelManager getInstance(IProject project);
}
