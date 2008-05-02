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
package org.eclipse.jst.jsf.designtime.internal.view;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.jst.jsf.core.internal.CompositeTagRegistryFactory;
import org.eclipse.jst.jsf.core.internal.CompositeTagRegistryFactory.TagRegistryIdentifier;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;

/**
 * Factories that create view definition adapters must extend this
 * class.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractViewDefnAdapterFactory implements IViewDefnAdapterFactory 
{
    /**
     * @param context
     * @param viewId
     * @return a view adapter for the viewid or null if this factory
     * cannot produce an adapter for the underlying view definition.
     */
    public abstract IViewDefnAdapter createAdapter(DTFacesContext context, String viewId);
    
    /**
     * TODO: since this implicitly, XML-specific, perhaps this belongs
     * in a util class
     * Sub-classes may use to locate a tag registry for their view adapter
     * @param file
     * @return a tag registry for the file (based on class path and
     * content type) or null if none.
     */
    protected final ITagRegistry findTagRegistry(final IFile file)
    {
        final IContentTypeManager typeManager = Platform.getContentTypeManager();
        final IContentType contentType = 
            typeManager.findContentTypeFor(file.getName());
        ITagRegistry tagRegistry = null;
        if (contentType != null)
        {
            final TagRegistryIdentifier id =
                new TagRegistryIdentifier(file.getProject(), contentType);
            tagRegistry = CompositeTagRegistryFactory.getInstance().getRegistry(id);
        }
        return tagRegistry;
    }
}
