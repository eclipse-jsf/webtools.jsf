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
package org.eclipse.jst.jsf.designtime.internal.view.model;

import java.util.Collection;
import java.util.Collections;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;

/**
 * A registry for tags for a given context TODO: generally tied to project, but
 * may also need a tie to specific tag-in-use type. For example, if JSPs and
 * Facelets are used at the same time?
 * 
 * @author cbateman
 * 
 */
public interface ITagRegistry
{

    /**
     * @return true if this registry is disposed. It is an error to use a
     *         disposed registry and behaviour of such a registry is not
     *         guaranteed.
     */
    public abstract boolean isDisposed();

    /**
     * This method may cause long-running operations to be executed. If runAfter
     * is non-null, any long running operations will schedule asynchronously on
     * a separate thread and on successful completion, runAfter will be executed
     * (no assumption should be made about what thread it is run on).
     * 
     * @param runAfter
     * @param flushCaches true indicates that any cached data should be flushed.
     * 
     */
    public abstract void refresh(final Runnable runAfter, final boolean flushCaches);

    /**
     * Callers should assume the collection is not modifiable.  Implementers
     * may return a collection whose mutator operations throw exceptions.
     * 
     * @return all tag libraries for a project tag registry
     */
    public abstract Collection<? extends Namespace> getAllTagLibraries();

    /**
     * @param uri
     * @return the tag library corresponding to uri or null if none.
     */
    public abstract Namespace getTagLibrary(final String uri);
    
    /**
     * Adds the listener to the list of objects that receive change events
     * from this tag registry instance.  If listener is already in the list,
     * it will not be added again.
     * 
     * @param listener
     */
    public abstract void addListener(ITagRegistryListener listener);
    
    /**
     * Remove listener from the list of objects that receive change events
     * from this tag registry instance.
     * 
     * @param listener
     */
    public abstract void removeListener(ITagRegistryListener listener);
    

    /**
     * Marks a listener that receives tag registry change events
     *
     */
    interface ITagRegistryListener extends EventListener
    {
        /**
         * @param changeEvent
         */
        void registryChanged(final TagRegistryChangeEvent changeEvent);
    }
    
    /**
     * A change event object that communicates a single type of change to
     * a single tag registry
     *
     */
    static class TagRegistryChangeEvent extends EventObject
    {
        public enum EventType
        {
            // if the type is any of these three, then getAffectedObjects()
            // will return one or more Namespace objects that have changed.
            ADDED_NAMESPACE,
            REMOVED_NAMESPACE,
            CHANGED_NAMESPACE,
            
            // if the type is is this one, then the event is being called
            // before dispose operation on getSource().
            REGISTRY_DISPOSED
        }
        /**
         * 
         */
        private static final long serialVersionUID = 6559096306615373552L;
        private final List<? extends Namespace> _affectedObjects;
        private final EventType _type;

        public TagRegistryChangeEvent(ITagRegistry source, EventType type)
        {
            this(source, type, Collections.EMPTY_LIST);
        }
        
        public TagRegistryChangeEvent(ITagRegistry source, EventType type, 
                List<? extends Namespace> affectedObjects)
        {
            super(source);
            _affectedObjects = affectedObjects;
            _type = type;
        }

        @Override
        public ITagRegistry getSource()
        {
            return (ITagRegistry) super.getSource();
        }

        public List<? extends Namespace> getAffectedObjects()
        {
            return _affectedObjects;
        }

        public EventType getType()
        {
            return _type;
        }
    }
}