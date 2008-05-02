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
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp.persistence;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.TLDNamespace;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.TLDNamespace.TLDNamespaceData;

/**
 * A serializable tld namespace. This is distinct from TLDNamespace in that it
 * takes a snapshot of a TLDNamespace at a point in time. Any elements not
 * lazily resolved by the TLDNamespace will not be resolved by this namespace.
 * 
 */
public class SerializableTLDNamespace extends Namespace
{
    private final SerializedNamespaceData _data;

    /**
     * @param namespace
     */
    public SerializableTLDNamespace(final TLDNamespace namespace)
    {
        _data = new SerializedNamespaceData(namespace.getDisplayName(),
                namespace.getCurrentElements(), namespace.getNSUri());
    }

    /**
     * 
     */
    private static final long serialVersionUID = 5364814479459691353L;

    @Override
    public String getDisplayName()
    {
        return _data.getDisplayName();
    }

    @Override
    public String getNSUri()
    {
        return _data.getUri();
    }

    @Override
    public ITagElement getViewElement(String name)
    {
        return _data.getViewElement(name);
    }

    @Override
    public Collection<? extends ITagElement> getViewElements()
    {
        return Collections.unmodifiableCollection(_data.getAllViewElements()
                .values());
    }

    @Override
    public boolean hasViewElements()
    {
        return _data.getNumTags() > 0;
    }

    @Override
    public boolean isInitialized()
    {
        return _data.isInitialized();
    }

    /* package */void put(final String name, final ITagElement tagElement)
    {
        _data._tags.put(name, tagElement);
    }

    private static class SerializedNamespaceData extends TLDNamespaceData
    {
        /**
         * serializable id
         */
        private static final long              serialVersionUID = -6723194339788215607L;
        private final String                   _displayName;
        private final String                   _uri;
        private final Map<String, ITagElement> _tags;

        /**
         * @param displayName
         * @param tags
         * @param uri
         */
        private SerializedNamespaceData(String displayName,
                Map<String, ITagElement> tags, String uri)
        {
            super();
            _displayName = displayName;
            _tags = new HashMap<String, ITagElement>();
            for (final Map.Entry<String, ITagElement> tagEntry : tags.entrySet())
            {
                _tags.put(tagEntry.getKey(), tagEntry.getValue());
            }
            _uri = uri;
        }

        @Override
        public String getDisplayName()
        {
            return _displayName;
        }

        @Override
        public int getNumTags()
        {
            return _tags.size();
        }

        @Override
        public ITagElement getViewElement(String name)
        {
            return _tags.get(name);
        }

        @Override
        public String getUri()
        {
            return _uri;
        }

        @Override
        public Map<String, ITagElement> getAllViewElements()
        {
            return _tags;
        }

        @Override
        public boolean isInitialized()
        {
            return true;
        }

        @Override
        public Map<String, ITagElement> getCurrentElements()
        {
            return _tags;
        }
    }
}
