/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp.analyzer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.internal.proxy.core.ConfigurationContributorAdapter;
import org.eclipse.jem.internal.proxy.core.IConfigurationContributionController;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.wst.common.project.facet.core.FacetedProjectFramework;
import org.osgi.framework.Bundle;

class ServletBeanProxyContributor extends ConfigurationContributorAdapter
{
    private final IProject   _project;
    private final JSFVersion _jsfVersion;

    public ServletBeanProxyContributor(final IProject project)
    {
        _project = project;
        _jsfVersion = getProjectVersion(project);
        if (_jsfVersion == null)
        {
            throw new IllegalArgumentException("jsfVersion must not be null"); //$NON-NLS-1$
        }

    }

    @Override
    public void contributeClasspaths(
            final IConfigurationContributionController controller)
            throws CoreException
    {
        if (_jsfVersion != JSFVersion.V1_2)
        {
            final Bundle servletBundle = Platform.getBundle("javax.servlet"); //$NON-NLS-1$
            controller.contributeClasspath(servletBundle, (IPath) null,
                    IConfigurationContributionController.APPEND_USER_CLASSPATH,
                    true);

            final Bundle jspBundle = Platform.getBundle("javax.servlet.jsp"); //$NON-NLS-1$
            controller.contributeClasspath(jspBundle, (IPath) null,
                    IConfigurationContributionController.APPEND_USER_CLASSPATH,
                    true);
        }
        else
        {
            final Bundle coreBundle = JSFCorePlugin.getDefault().getBundle();
            final IJavaProject javaProject = JavaCore.create(_project);
            maybeAddJar(controller, "javax.servlet.jsp.tagext.JspIdConsumer", //$NON-NLS-1$
                    javaProject, coreBundle, "/jars/fake_jsp_21.jar"); //$NON-NLS-1$
            maybeAddJar(controller, "javax.el.ELException", javaProject, //$NON-NLS-1$
                    coreBundle, "/jars/fake_el.jar"); //$NON-NLS-1$
        }
    }

    private void maybeAddJar(
            final IConfigurationContributionController controller,
            final String addIfTypeNameNotFound, final IJavaProject javaProject,
            final Bundle bundle, final String path)
    {
        try
        {
            final IType type = javaProject.findType(addIfTypeNameNotFound);
            // if we can't find the type name on the classpath,then inject
            // our fake jar to aid linkage while introspecting facelet libs
            if (type == null)
            {

                controller
                        .contributeClasspath(
                                bundle,
                                path,
                                IConfigurationContributionController.APPEND_USER_CLASSPATH,
                                false);
            }
        }
        catch (final JavaModelException jme)
        {
            // suppress
        }
    }

    static JSFVersion getProjectVersion(final IProject project)
    {
        try
        {
            if (FacetedProjectFramework.hasProjectFacet(project, "jst.jsf", //$NON-NLS-1$
                    "1.0")) //$NON-NLS-1$
            {
                return JSFVersion.V1_0;
            }
            else if (FacetedProjectFramework.hasProjectFacet(project,
                    "jst.jsf", "1.1")) //$NON-NLS-1$ //$NON-NLS-2$
            {
                return JSFVersion.V1_1;
            }
            else if (FacetedProjectFramework.hasProjectFacet(project,
                    "jst.jsf", "1.2")) //$NON-NLS-1$ //$NON-NLS-2$
            {
                return JSFVersion.V1_2;
            }
        }
        catch (final CoreException e)
        {
            JSFCorePlugin.log("checking project version", e); //$NON-NLS-1$
            // fall-through
        }

        return null;
    }

}