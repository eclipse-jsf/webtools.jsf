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
package org.eclipse.jst.jsf.common.internal.resource;

import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.locator.ILocator;
import org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener;
import org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener.LocatorChangeEvent;

/**
 * Provider of jars for use by the locator. Exists to abstract the locator from
 * JDT for test purposes.
 * 
 */
public interface IJarLocator extends
        ILocator<Collection<? extends ClasspathJarFile>, IProject, String>
{
    /**
     * @param project
     * @return a list of valid jar files.
     */
    Collection<? extends ClasspathJarFile> getJars(final IProject project);

    /**
     * @param listener
     */
    void addListener(final JarChangeListener listener);

    /**
     * @param listener
     */
    void removeListener(final JarChangeListener listener);

    /**
     * Disposes the provider.
     */
    void dispose();

    /**
     * Implemented by classes that want receive events signalling a change on
     * the classpath.
     * 
     */
    public abstract class JarChangeListener implements ILocatorChangeListener
    {
        /**
         * @param event
         */
        public abstract void changed(JarChangeEvent event);

        public final void changed(final LocatorChangeEvent event)
        {
            changed((JarChangeEvent) event);
        }
    }

    /**
     * Indicates a change on the class path.
     * 
     */
    public class JarChangeEvent extends LocatorChangeEvent
    {
        /**
         * Indicates the type of event
         * 
         */
        public enum Type
        {
            /**
             * Indicates a jar has been added.
             */
            JAR_ADDED,
            /**
             * Indicates a jar has been removed.
             */
            JAR_REMOVED;
        }

        private final Type _type;
        private final ClasspathJarFile _jar;

        /**
         * @param source
         * @param type 
         * @param jar 
         */
        public JarChangeEvent(final ILocator source, final Type type,
                final ClasspathJarFile jar)
        {
            super(source);
            _type = type;
            _jar = jar;
        }

        /**
         * @return the type of the event
         */
        public Type getType()
        {
            return _type;
        }

        /**
         * @return the affected jar.
         */
        public ClasspathJarFile getJar()
        {
            return _jar;
        }

        /**
         * 
         */
        private static final long serialVersionUID = 8163703453414731319L;
    }
}