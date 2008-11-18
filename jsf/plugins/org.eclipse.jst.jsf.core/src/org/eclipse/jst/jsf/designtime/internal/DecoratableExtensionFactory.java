/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 * 
 ********************************************************************************/

package org.eclipse.jst.jsf.designtime.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.osgi.framework.Bundle;

/**
 * Factory for extensions that include a runtime class for which they decorate.
 * 
 * @author cbateman
 * 
 * @param <EXTENSIONTYPE>
 */
public class DecoratableExtensionFactory<EXTENSIONTYPE> extends
        BasicExtensionFactory<EXTENSIONTYPE>
{
    private static final String             DECORATOR_ATTRIBUTE_NAME = "forRuntimeClass"; //$NON-NLS-1$
    private final Map<String, List<String>> _forNameToId;

    /**
     * @param bundle
     * @param extName
     * @param elementName
     * @param alwaysPerProject 
     */
    public DecoratableExtensionFactory(final Bundle bundle,
            final String extName, final String elementName, final boolean alwaysPerProject)
    {
        super(bundle, extName, elementName, alwaysPerProject);
        _forNameToId = new HashMap<String, List<String>>();
    }

    /**
     * @param name
     * @return a canonically sorted list of ids of extensions registered by the
     *         name.
     */
    public List<String> getIdsForName(final String name)
    {
        List<String> ids =  _forNameToId.get(name);
        if (ids == null)
        {
            ids = Collections.EMPTY_LIST;
        }
        return ids;
    }

    /**
     * @return the number of runtime resolvers for which there is at least one
     *         declarative variable resolver registered.
     */
    public int getNumDecorativeResolvers()
    {
        return _forNameToId.size();
    }

    @Override
    protected Map<String, ExtensionData<EXTENSIONTYPE>> loadRegisteredExtensions()
    {
        Map<String, ExtensionData<EXTENSIONTYPE>> extensions = super
                .loadRegisteredExtensions();

        // canonically sort forName's
        for (final Map.Entry<String, List<String>> entry : _forNameToId
                .entrySet())
        {
            Collections.sort(entry.getValue());
        }

        return extensions;
    }

    
    @Override
    protected ExtensionData processExtension(IConfigurationElement element, boolean alwaysPerProject)
    {
        final ExtensionData data = super.processExtension(element, alwaysPerProject);
        final String forRuntimeClass = element
                .getAttribute(DECORATOR_ATTRIBUTE_NAME);
        if (forRuntimeClass != null && !"".equals(forRuntimeClass.trim())){ //$NON-NLS-1$
            addRuntimeClassMapping(data.getId(), forRuntimeClass);
        }
        return data;
    }

    private void addRuntimeClassMapping(final String id,
            final String forRuntimeClass)
    {
        List mappings = _forNameToId.get(forRuntimeClass);

        if (mappings == null)
        {
            mappings = new ArrayList<String>(2);
            _forNameToId.put(forRuntimeClass, mappings);
        }
        mappings.add(id);
    }
}
