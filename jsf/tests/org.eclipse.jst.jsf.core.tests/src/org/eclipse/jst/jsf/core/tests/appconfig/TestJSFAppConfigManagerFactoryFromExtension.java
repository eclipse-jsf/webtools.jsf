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
import org.eclipse.jst.jsf.common.internal.managedobject.AbstractManagedObject;
import org.eclipse.jst.jsf.common.internal.managedobject.ObjectManager.ManagedObjectException;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManager;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManagerFactory;

public class TestJSFAppConfigManagerFactoryFromExtension 
		extends 	AbstractManagedObject
		implements 	IJSFAppConfigManagerFactory {
	
	public IJSFAppConfigManager getInstance(final IProject project) throws ManagedObjectException {
		return new TestJSFAppConfigManagerFromExtension(project);
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

}