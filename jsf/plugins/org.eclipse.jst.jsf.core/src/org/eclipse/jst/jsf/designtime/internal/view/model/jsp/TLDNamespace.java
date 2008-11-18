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
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.jobs.ILock;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.persistence.SerializableTLDNamespace;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNamedNodeMap;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;

/**
 * Represents a JSP tag library namespace
 * 
 * @author cbateman
 * 
 */
public class TLDNamespace extends Namespace
{
    /**
     * 
     */
    private static final long          serialVersionUID = 9206460825737988441L;
    private TLDNamespaceData _tldData;

    /**
     * @param tldDoc
     * @param tagResolver
     */
    public TLDNamespace(
            final TLDDocument tldDoc,
            final ITagResolvingStrategy<TLDElementDeclaration, String> tagResolver)
    {
        _tldData = new DocumentTLDNamespaceData(tldDoc, tagResolver);
    }

    @Override
    public final String getDisplayName()
    {
        return _tldData.getDisplayName();
    }

    @Override
    public final String getNSUri()
    {
        return _tldData.getUri();
    }

    @Override
    public boolean isInitialized()
    {
        return _tldData.isInitialized();
    }

    public final ITagElement getViewElement(final String name)
    {
        // // bias the table small if this creates it
        // final Map<String, ITagElement> tags = getOrCreateMap(3);
        //
        // return _tldData.getOrCreateTagElement(name, tags);
        return _tldData.getViewElement(name);
    }

    @Override
    public final Collection<? extends ITagElement> getViewElements()
    {
        return Collections.unmodifiableCollection(_tldData.getAllViewElements().values());
    }

    /**
     * Differs from getViewElements because it won't include those elements
     * that haven't been lazily loaded.
     * 
     * @return an unmodifiable map of all elements currently calculated for
     * this namespaces
     */
    public final Map<String, ITagElement> getCurrentElements()
    {
        return Collections.unmodifiableMap(_tldData.getCurrentElements());
    }
    
    @Override
    public final boolean hasViewElements()
    {
        return _tldData.getNumTags() > 0;
    }
    

    private Object writeReplace()
    {
        return new SerializableTLDNamespace(this);
    }

    private static class DocumentTLDNamespaceData extends TLDNamespaceData
    {
        /**
         * 
         */
        private static final long serialVersionUID = -1098872687238068584L;
        private final transient TLDDocument                                          _tldDoc;
        private final transient ITagResolvingStrategy<TLDElementDeclaration, String> _tagResolver;
        private final transient ILock                                                _tagMapReadWriteLock;
        // should only be accessed directly in getOrCreateMap;
        private Map<String, ITagElement>                                             _tags; // lazy init in initializeTags

        public DocumentTLDNamespaceData(
                final TLDDocument tldDoc,
                final ITagResolvingStrategy<TLDElementDeclaration, String> tagResolver)
        {
            _tldDoc = tldDoc;
            _tagResolver = tagResolver;
            _tagMapReadWriteLock = Job.getJobManager().newLock();
        }

        @Override
        public String getDisplayName()
        {
            String displayName = _tldDoc.getDisplayName();
            if (displayName == null || "".equals(displayName.trim())) //$NON-NLS-1$
            {
                displayName = getUri();
            }
            return displayName;
        }

        @Override
        public String getUri()
        {
            return _tldDoc.getUri();
        }

        @Override
        public int getNumTags()
        {
            return _tldDoc.getElements().getLength();
        }

        private ITagElement createTagElement(final String name)
        {
            CMNode cmNode = _tldDoc.getElements().getNamedItem(name);
            ITagElement tagElement = null;
            if (cmNode instanceof TLDElementDeclaration)
            {
                tagElement = _tagResolver
                        .resolve((TLDElementDeclaration) cmNode);

                if (tagElement == _tagResolver.getNotFoundIndicator())
                {
                    // the not-found indicator may not be null.
                    tagElement = null;
                }
            }
            return tagElement;
        }

        public final Map<String, ITagElement> getAllViewElements()
        {
            final CMNamedNodeMap elementMap = _tldDoc.getElements();
            final Map<String, ITagElement> tags = getOrCreateMap(elementMap
                    .getLength());
            if (!isInitialized())
            {
                // if we create the table with this call, bias the table size
                // to be 1 bigger than the number of tags, since we know if it's
                // smaller, it'll cause at least one buffer resize
                
    
                for (int i = 0; i < elementMap.getLength(); i++)
                {
                    final CMNode cmNode = elementMap.item(i);
                    getViewElement(cmNode.getNodeName());
                }
            }
            return tags;
        }

        public ITagElement getViewElement(final String name)
        {
            ITagElement tagElement = null;

            _tagMapReadWriteLock.acquire();
            final Map<String, ITagElement> tags = getOrCreateMap(_tldDoc.getElements().getLength());
            try
            {
                tagElement = tags.get(name);

                if (tagElement == null)
                {
                    tagElement = createTagElement(name);

                    if (tagElement != null)
                    {
                        tags.put(tagElement.getName(), tagElement);
                    }
                }
            }
            finally
            {
                _tagMapReadWriteLock.release();
            }

            return tagElement;
        }

        /**
         * mapSizeHint will be used
         * 
         * @param mapSizeHint
         * @return the map
         */
        private synchronized Map<String, ITagElement> getOrCreateMap(
                final int mapSizeHint)
        {
            if (_tags == null)
            {
                final float loadFactor = 0.75f;
                final int initSize = ((int) (mapSizeHint / loadFactor)) + 1;
                _tags = Collections
                        .synchronizedMap(new HashMap<String, ITagElement>(
                                initSize));
            }
            return _tags;
        }

        @Override
        public boolean isInitialized()
        {
            int numTags = getNumTags();
            int tagMapSize = getOrCreateMap(3).size();

            // we are only initialized if the tag map is the equal in size
            // to the number of tags in the tld.
            return numTags == tagMapSize;
        }

        @Override
        public Map<String, ITagElement> getCurrentElements()
        {
            return getOrCreateMap(3);
        }
    }

    /**
     * Encapsulates all the data for a TLDNamespace.  Allows the model
     * to be separated from the Namespace interface for ease of serialization and 
     * controlled subclassing.
     *
     */
    public abstract static class TLDNamespaceData implements Serializable
    {
        /**
         * 
         */
        private static final long serialVersionUID = -1284294636936289804L;

        /**
         * @return the displayb
         */
        public abstract String getDisplayName();

//        public abstract ITagElement getOrCreateTagElement(String name,
//                Map<String, ITagElement> tags);

        /**
         * @return the number of tags
         */
        public abstract int getNumTags();

        /**
         * @return the namespace uri
         */
        public abstract String getUri();

        /**
         * @param name
         * @return the view element for name or null if not found.
         */
        public abstract ITagElement getViewElement(final String name);

        /**
         * May be long running since it will lazily calculate all unloaded
         * tags.
         * @return all view elements for this namespace
         */
        public abstract Map<String, ITagElement> getAllViewElements();

        /**
         * @return true if all elements have been lazily loaded
         */
        public abstract boolean isInitialized();
        
        /**
         * @return just the currently loaded elements for this namespace.
         */
        public abstract Map<String, ITagElement>  getCurrentElements();
    }
}
