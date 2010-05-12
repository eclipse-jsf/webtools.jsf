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
package org.eclipse.jst.jsf.common.internal.resource;

import java.util.EventObject;

import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jst.jsf.common.internal.resource.IClasspathLifecycleListener.ClasspathLifecycleEvent;

/**
 * Listener can register for events ona particular LifecycleListener
 * 
 * @author cbateman
 * 
 */
public interface IClasspathLifecycleListener extends
        ILifecycleListener<ClasspathLifecycleEvent>
{
    /**
     * Listener accepts the classpath lifecycle event
     * 
     * @param event
     */
    EventResult acceptEvent(ClasspathLifecycleEvent event);

    /**
     * An event indicating a change on the classpath
     * 
     */
    public static class ClasspathLifecycleEvent extends EventObject
    {
        /**
         * Indicates the type of change.
         * 
         */
        public enum Type
        {
            /**
             * The affected element was added to the classpath
             */
            ADDED,
            /**
             * The affected element was removed from the classpath
             */
            REMOVED,
            /**
             * A classpath object has been deleted from the workspace. The
             * affected element is the owning IJavaProject. The actual deleted
             * object can be obtained by query getAffectedResource. Note that
             * this is a post change event, so the underlying resources most
             * likely don't exist any more.
             * 
             */
            REMOVED_DELTA
        }

        private final IJavaElement _affectedElement;
        private final IResource _affectedResource;
        private final Type _type;

        /**
         * @param source
         * @param affectedElement
         * @param type
         */
        public ClasspathLifecycleEvent(
                final ClasspathEntryLifecycleListener source,
                final IJavaElement affectedElement, final Type type)
        {
            this(source, affectedElement, type, null);
        }

        /**
         * @param source
         * @param affectedElement
         * @param type
         * @param affectedResource
         * @throws IllegalArgumentException
         *             if affectedResource is non-null but type doesn't match
         *             one that has affectedResources (see
         *             isAffectResourceEvent)
         */
        public ClasspathLifecycleEvent(
                final ClasspathEntryLifecycleListener source,
                final IJavaElement affectedElement, final Type type,
                final IResource affectedResource)
        {
            super(source);
            _affectedElement = affectedElement;
            _type = type;
            _affectedResource = affectedResource;
            if (affectedResource != null && !isAffectResourceEvent(type))
            {
                throw new IllegalArgumentException();
            }
        }

        @Override
        public ClasspathEntryLifecycleListener getSource()
        {
            return (ClasspathEntryLifecycleListener) super.getSource();
        }

        /**
         * @return the type of event.
         */
        public Type getType()
        {
            return _type;
        }

        /**
         * @return the affected resource or null if none.
         */
        public IResource getAffectedResource()
        {
            return _affectedResource;
        }

        /**
         * 
         */
        private static final long serialVersionUID = -2510218872082581659L;

        /**
         * @return the element that changed.
         */
        public IJavaElement getAffectedElement()
        {
            return _affectedElement;
        }

        /**
         * @param type
         * @return true if the type instance matches one for which
         *         affectedResource's are valid in the change event.
         */
        public boolean isAffectResourceEvent(final Type type)
        {
            return type == Type.REMOVED_DELTA;
        }

        @Override
        public String toString()
        {
            return String.format(
                    "ClasspathLifecycleEvent: IJavaElement=%s, Res = %s, Event=%s", //$NON-NLS-1$
                    getAffectedElement(), getAffectedResource(), getType());
        }
        
        
    }
}
