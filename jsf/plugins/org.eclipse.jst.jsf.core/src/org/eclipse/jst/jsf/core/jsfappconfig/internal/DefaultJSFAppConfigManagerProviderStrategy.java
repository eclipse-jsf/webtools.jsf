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
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigManager;

/**
 * Default strategy for returning {@link IJSFAppConfigManager}s
 *
 */
@SuppressWarnings("deprecation")
public class DefaultJSFAppConfigManagerProviderStrategy extends
		AbstractJSFAppConfigManagerProviderStrategy {

	public IJSFAppConfigManagerFactory perform(final IProject input) throws Exception {
		return new DefaultProvider();
	}

	private static class DefaultProvider 
		implements IJSFAppConfigManagerFactory {
		
		public IJSFAppConfigManager getInstance(final IProject project) {
			//deprecated warning is to be expected here
			return JSFAppConfigManager.getInstance(project);
		}
				
	}
	
}
