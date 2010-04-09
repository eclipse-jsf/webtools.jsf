package org.eclipse.jst.jsf.common.internal.resource;

import java.util.Collection;
import java.util.jar.JarFile;

import org.eclipse.core.resources.IProject;

/**
 * Provider of jars for use by the locator. Exists to abstract the locator
 * from JDT for test purposes.
 * 
 */
public interface IJarProvider
{
    /**
     * @param project
     * @return a list of valid jar files.
     */
    Collection<? extends JarFile> getJars(final IProject project);
}