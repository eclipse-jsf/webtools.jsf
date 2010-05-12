package org.eclipse.jst.jsf.common.internal.resource;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IElementChangedListener;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

/**
 * Implements pass-through methods to the static JavaCore class. This allows us
 * to decouple from JavaCore for testing and other dep-injection purposes.
 * 
 * @author cbateman
 * 
 */
public class JavaCoreMediator
{
    /**
     * @param project
     * @return a java project for project. Return null if project is null.
     */
    public IJavaProject create(final IProject project)
    {
        return JavaCore.create(project);
    }

    /**
     * @param listener
     */
    public void addElementChangedListener(final IElementChangedListener listener)
    {
        JavaCore.addElementChangedListener(listener);
    }

    /**
     * @param listener
     */
    public void removeElementChangeListener(
            final ClasspathEntryLifecycleListener listener)
    {
        JavaCore.removeElementChangedListener(listener);
    }
}
