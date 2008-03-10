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
            throw new IllegalArgumentException("jsfVersion must not be null");
        }

    }

    @Override
    public void contributeClasspaths(
            final IConfigurationContributionController controller)
            throws CoreException
    {
        if (_jsfVersion != JSFVersion.V1_2)
        {
            final Bundle servletBundle = Platform.getBundle("javax.servlet");
            controller.contributeClasspath(servletBundle, (IPath) null,
                    IConfigurationContributionController.APPEND_USER_CLASSPATH,
                    true);

            final Bundle jspBundle = Platform.getBundle("javax.servlet.jsp");
            controller.contributeClasspath(jspBundle, (IPath) null,
                    IConfigurationContributionController.APPEND_USER_CLASSPATH,
                    true);
        }
        else
        {
            final Bundle coreBundle = JSFCorePlugin.getDefault().getBundle();
            final IJavaProject javaProject = JavaCore.create(_project);
            maybeAddJar(controller, "javax.servlet.jsp.tagext.JspIdConsumer",
                    javaProject, coreBundle, "/jars/fake_jsp_21.jar");
            maybeAddJar(controller, "javax.el.ELException", javaProject,
                    coreBundle, "/jars/fake_el.jar");
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
            if (FacetedProjectFramework.hasProjectFacet(project, "jst.jsf",
                    "1.0"))
            {
                return JSFVersion.V1_0;
            }
            else if (FacetedProjectFramework.hasProjectFacet(project,
                    "jst.jsf", "1.1"))
            {
                return JSFVersion.V1_1;
            }
            else if (FacetedProjectFramework.hasProjectFacet(project,
                    "jst.jsf", "1.2"))
            {
                return JSFVersion.V1_2;
            }
        }
        catch (final CoreException e)
        {
            JSFCorePlugin.log("checking project version", e);
            // fall-through
        }

        return null;
    }

}