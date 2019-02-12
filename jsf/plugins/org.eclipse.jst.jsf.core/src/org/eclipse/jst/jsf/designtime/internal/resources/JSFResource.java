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
package org.eclipse.jst.jsf.designtime.internal.resources;

import org.eclipse.jst.jsf.common.internal.resource.ContentTypeResolver;

/**
 * Represents a JSF loadable resource (spec 2.6).
 * 
 * @author cbateman
 * 
 */
public abstract class JSFResource extends JSFResourceFragment implements IJSFResource
{
    private final ContentTypeResolver _contentTypeResolver;

    /**
     * @param id
     * @param contentTypeResolver
     */
    public JSFResource(final ResourceIdentifier id,
            final ContentTypeResolver contentTypeResolver)
    {
        super(id, Type.RESOURCE);
        _contentTypeResolver = contentTypeResolver;
    }

    /**
     * @return the identifier for this resource.
     */
    public final ResourceIdentifier getId()
    {
        return (ResourceIdentifier) super.getId();
    }

    /**
     * @return true if this jsf resource is currently accessible.
     */
    @Override
    public abstract boolean isAccessible();

    public boolean isContentType(final String contentTypeName)
    {
    	if (_contentTypeResolver != null && getId()!= null)
    	{
	        return _contentTypeResolver.matchesType(contentTypeName, getId()
	                .getResourceName());
    	}
    	return false;
    }

    @Override
    public String toString()
    {
        return getId().toString();
    }
}
