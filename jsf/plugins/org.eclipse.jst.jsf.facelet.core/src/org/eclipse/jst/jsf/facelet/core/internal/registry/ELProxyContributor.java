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
package org.eclipse.jst.jsf.facelet.core.internal.registry;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.internal.proxy.core.ConfigurationContributorAdapter;
import org.eclipse.jem.internal.proxy.core.IConfigurationContributionController;
import org.eclipse.jst.jsf.facelet.core.internal.FaceletCorePlugin;
import org.osgi.framework.Bundle;

/**
 * Contributes additional, sometimes fake, jars to the JEM classpath to allow
 * for smooth instantiation of introspected objects.
 * 
 * @author cbateman
 *
 */
public class ELProxyContributor extends ConfigurationContributorAdapter
{
    private final IProject _project;

    /**
     * @param project
     */
    public ELProxyContributor(final IProject project)
    {
        _project = project;
    }

    @Override
    public void contributeClasspaths(
            final IConfigurationContributionController controller)
    throws CoreException
    {
        if (_project.isAccessible())
        {
            final IJavaProject javaProject = JavaCore.create(_project);
            final IType type  = javaProject.findType("java.el.ELException"); //$NON-NLS-1$

            // if we can't find ELException on the classpath,then inject
            // our fake el jar to aid linkage while introspecting facelet libs
            if (type == null)
            {
                final Bundle faceletBundle = FaceletCorePlugin.getDefault().getBundle();
                controller.contributeClasspath(faceletBundle, "/jars/fake_el.jar", //$NON-NLS-1$
                        IConfigurationContributionController.APPEND_USER_CLASSPATH,
                        false);
            }
        }
    }
}
