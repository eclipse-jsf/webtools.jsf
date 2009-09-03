/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagAttributeHandler;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.IAttributeAdvisor.UnknownAttributeException;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMAttributeDeclaration;

/**
 * Adapts TLDDocument attributes to a simple map of ITagAttributeHandler. Map is
 * unmodifiable.
 * 
 * @author cbateman
 * 
 */
public class CMNodeNamedMapAdapter implements
        Map<String, ITagAttributeHandler>
{
    /**
     * serializable id
     */
    private static final long                       serialVersionUID = -4188412823197830484L;
    private transient final TLDElementDeclaration   _tldElement;
    private transient final IAttributeAdvisor       _advisor;
    private transient final AtomicBoolean           _isInitialized = new AtomicBoolean(false);
    private final Map<String, ITagAttributeHandler> _cache;

    /**
     * @param tldDoc
     * @param advisor
     */
    public CMNodeNamedMapAdapter(final TLDElementDeclaration tldDoc,
            final IAttributeAdvisor advisor)
    {
        _tldElement = tldDoc;
        _advisor = advisor;
        _cache = new HashMap<String, ITagAttributeHandler>();
    }

    public boolean containsKey(Object key)
    {
        ensureAllAttributes();
        return _cache.containsKey(key);
    }

    public boolean containsValue(Object value)
    {
        ensureAllAttributes();
        return _cache.containsValue(value);
    }

    public Set<java.util.Map.Entry<String, ITagAttributeHandler>> entrySet()
    {
        ensureAllAttributes();
        return _cache.entrySet();
    }


    public ITagAttributeHandler get(Object key)
    {
        if (key instanceof String)
        {
            return getOrCreateAttribute((String) key);
        }
        return null;
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    public Set<String> keySet()
    {
        ensureAllAttributes();
        return Collections.unmodifiableSet(_cache.keySet());
    }

    public int size()
    {
        if (_tldElement != null)
        {
            return _tldElement.getAttributes().getLength();
        }
        return _cache.size();
    }

    public Collection<ITagAttributeHandler> values()
    {
        ensureAllAttributes();
        return Collections.unmodifiableCollection(_cache.values());
    }

    private synchronized ITagAttributeHandler getOrCreateAttribute(final String name)
    {
        ITagAttributeHandler tagAttr = _cache.get(name);
        
        if (tagAttr == null)
        {
            try
            {
                tagAttr = _advisor.createAttributeHandler(name);
                _cache.put(name, tagAttr);
            }
            catch (UnknownAttributeException e)
            {
                JSFCorePlugin.log(e, "Trying to get attribute for "+name); //$NON-NLS-1$
            }
        }
        
        return tagAttr;
    }

    private void ensureAllAttributes()
    {
        if (_isInitialized.compareAndSet(false, true))
        {
            for (final Iterator it = _tldElement.getAttributes().iterator(); it.hasNext();)
            {
                final CMAttributeDeclaration attrDecl = (CMAttributeDeclaration) it.next();
                getOrCreateAttribute(attrDecl.getAttrName());
            }
        }
    }

    public void clear()
    {
        throw new UnsupportedOperationException("Cannot modify map"); //$NON-NLS-1$
    }

    public ITagAttributeHandler put(String key, ITagAttributeHandler value)
    {
        throw new UnsupportedOperationException("Cannot modify map"); //$NON-NLS-1$
    }

    public void putAll(Map<? extends String, ? extends ITagAttributeHandler> t)
    {
        throw new UnsupportedOperationException("Cannot modify map"); //$NON-NLS-1$
    }

    public ITagAttributeHandler remove(Object key)
    {
        throw new UnsupportedOperationException("Cannot modify map"); //$NON-NLS-1$
    }
}
