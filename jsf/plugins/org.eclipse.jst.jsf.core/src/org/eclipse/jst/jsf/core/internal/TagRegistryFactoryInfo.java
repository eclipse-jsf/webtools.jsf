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
package org.eclipse.jst.jsf.core.internal;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.jst.jsf.designtime.internal.view.model.TagRegistryFactory;

/**
 * Encapsulates information about tag registry extensions
 * 
 */
public final class TagRegistryFactoryInfo implements ITagRegistryFactoryInfo
{
    private final String             _description;
    private final String             _id;
    private final TagRegistryFactory _tagRegistry;
    private final Set<IContentType>  _contentTypes;

    /**
     * @param element
     * @throws CoreException
     */
    TagRegistryFactoryInfo(IConfigurationElement element)
            throws CoreException
    {
        // do this first since it has highest potential to fail
        _tagRegistry = (TagRegistryFactory) element
                .createExecutableExtension("class"); //$NON-NLS-1$

        final String localId = element.getAttribute("id"); //$NON-NLS-1$
        _id = element.getContributor().getName() + "." + localId; //$NON-NLS-1$

        _description = element.getAttribute("description"); //$NON-NLS-1$

        final IConfigurationElement[] contentTypes = element
                .getChildren("content-type"); //$NON-NLS-1$

        final IContentTypeManager typeManager = Platform
                .getContentTypeManager();
        _contentTypes = new HashSet<IContentType>();
        for (IConfigurationElement contentTypeElement : contentTypes)
        {
            final String contentTypeId = contentTypeElement
                    .getAttribute("contentTypeId"); //$NON-NLS-1$

            final IContentType contentType = typeManager
                    .getContentType(contentTypeId);
            _contentTypes.add(contentType);
        }
    }

    /**
     * @return the user displyable description
     */
    public String getDescription()
    {
        return _description;
    }

    /**
     * @return the unique of the extension
     */
    public String getId()
    {
        return _id;
    }

    /**
     * @return the registry
     */
    public TagRegistryFactory getTagRegistryFactory()
    {
        return _tagRegistry;
    }

    /**
     * Set is immutable.
     * 
     * @return the content types this registry is registered against.
     */
    public Set<IContentType> getContentTypes()
    {
        return Collections.unmodifiableSet(_contentTypes);
    }
}