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
package org.eclipse.jst.jsf.core.tests.appconfig;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManager;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManagerFactory;

public class TestJSFAppConfigManagerFactory implements IJSFAppConfigManagerFactory {

	public IJSFAppConfigManager getInstance(IProject project) {		
		try {
			return new TestJSFAppConfigManager(project, new TestLocatorProvider());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void checkpoint() {
		// TODO Auto-generated method stub
		
	}

	public boolean isDisposed() {
		// TODO Auto-generated method stub
		return false;
	}

}
