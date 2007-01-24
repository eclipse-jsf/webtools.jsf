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

import org.eclipse.core.resources.IProject;

/**
 * A <code>IMetaDataLocator</code> that is sensitive to the project context 
 */
public interface IPathSensitiveMetaDataLocator extends IMetaDataLocator {
	/**
	 * @param project
	 */
	public void setProjectContext(IProject project);
}
