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

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;

/**
 * Tools for resolving content types of resources or other object linked from
 * the workspace (i.e. "files" in classpath entries).
 * 
 * @author cbateman
 * 
 */
public class ContentTypeResolver
{
    private final IContentTypeManager _typeManager;

    /**
     * Equivalent to Platform.getContentTypeManager().
     */
    public ContentTypeResolver()
    {
        this(Platform.getContentTypeManager());
    }

    /**
     * @param typeManager
     */
    public ContentTypeResolver(final IContentTypeManager typeManager)
    {
        _typeManager = typeManager;
    }

    /**
     * @param contentTypeId
     * @param fileName
     * @return true if the fileName matches the content type provided.  May also return
     * false if the fileName alone is not enough to determine type.
     */
    public boolean matchesType(final String contentTypeId, final String fileName)
    {
        IContentType jspContentType = _typeManager.getContentType(contentTypeId);
        if (jspContentType != null
                && jspContentType.isAssociatedWith(fileName))
        {
            return true;
        }

        return false;
    }
}
