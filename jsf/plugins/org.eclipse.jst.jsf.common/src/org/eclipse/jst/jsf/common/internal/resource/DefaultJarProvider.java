package org.eclipse.jst.jsf.common.internal.resource;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarFile;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.internal.finder.AbstractMatcher.IMatcher;
import org.eclipse.jst.jsf.common.internal.finder.VisitorMatcher;
import org.eclipse.jst.jsf.common.internal.finder.acceptor.JarMatchingAcceptor;

/**
 * A default jar provider that traverses a project and returns all found jars on the classpath
 * that a list of matcher criteria.
 * 
 * @author cbateman
 *
 */
public class DefaultJarProvider implements IJarProvider
{
    private VisitorMatcher<IProject, JarFile, String> _matcher;

    /**
     * @param matchers
     */
    public DefaultJarProvider(final List<? extends IMatcher> matchers)
    {
        _matcher = new VisitorMatcher<IProject, JarFile, String>("", "", new JarMatchingAcceptor(), matchers); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public Collection<? extends JarFile> getJars(final IProject project)
    {
        try
        {
            return _matcher.find(project);
        } catch (final Exception e)
        {
            JSFCommonPlugin.log(e, 
                    "While getting jars for project: " + project); //$NON-NLS-1$
            return Collections.EMPTY_LIST;
        }
    }
}