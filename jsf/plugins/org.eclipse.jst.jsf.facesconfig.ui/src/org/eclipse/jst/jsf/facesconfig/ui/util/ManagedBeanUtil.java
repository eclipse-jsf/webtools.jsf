/*******************************************************************************
 * Copyright (c) 2004, 2010 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.util;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManager;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.JSFAppConfigManagerFactory;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;

/**
 * 
 * @author sfshi
 * 
 */
public class ManagedBeanUtil {

	/**
	 * Determines if the new bean to be added is already a member of the
	 * configuration set.
	 * @param project 
	 * 
	 * @param beanName -
	 *            The name of the bean being added
	 * @return int - 0 if bean doesn't exist, otherwise the choice from the
	 *         Duplicate Bean dialog
	 */
	public static boolean isBeanDuplicate(final IProject project, final String beanName) 
    {
	    final IJSFAppConfigManager appCfgMgr = JSFAppConfigManagerFactory.getJSFAppConfigManagerInstance(project);
        
		if (appCfgMgr != null) 
        {
			final List<ManagedBeanType> beans = appCfgMgr.getManagedBeans();

			// Iterate through the bean list
			for (final ManagedBeanType mbti : beans) {
				if (mbti.getManagedBeanName() != null) {
					final String name = mbti.getManagedBeanName()
							.getTextContent();
					if (name != null && name.equals(beanName)) {
						return true;
					}
				}
			}

		}
		return false;
	}

	/**
	 * get the default managed bean name in the current project according to
	 * reference name
	 * 
	 * @param project -
	 *            current project
	 * @param refName -
	 *            seed reference name
	 * @return String - default managed bean name
	 */
	public static String getDefaultManagedBeanName(final IProject project,
			final String refName) {
		String defaultName = refName;

		int newRefNameIndex = 1;
		while (isBeanDuplicate(project, defaultName)) {
			defaultName = refName + newRefNameIndex;
			newRefNameIndex++;
		}
		return defaultName;
	}
}
