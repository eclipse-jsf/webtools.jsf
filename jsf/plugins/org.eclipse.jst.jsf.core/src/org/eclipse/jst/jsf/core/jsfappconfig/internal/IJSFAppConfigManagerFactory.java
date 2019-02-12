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
package org.eclipse.jst.jsf.core.jsfappconfig.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.managedobject.ObjectManager.ManagedObjectException;

/**
 * Factory for creating {@link IJSFAppConfigManager} instances.
 * <p>
 * NOT API
 */
public interface IJSFAppConfigManagerFactory {
	/**
	 * @param project - may be null
	 * @return instance of an {@link IJSFAppConfigManager} for the project.
	 * @throws ManagedObjectException 
	 */
	IJSFAppConfigManager getInstance(IProject project) throws ManagedObjectException;
}
