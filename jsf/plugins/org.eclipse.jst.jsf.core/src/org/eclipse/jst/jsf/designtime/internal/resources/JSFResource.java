package org.eclipse.jst.jsf.designtime.internal.resources;

import org.eclipse.jst.jsf.common.internal.resource.ContentTypeResolver;

/**
 * Represents a JSF loadable resource (spec 2.6).
 * 
 * @author cbateman
 *
 */
public abstract class JSFResource
{
    private final ResourceIdentifier _id;
    private final ContentTypeResolver _contentTypeResolver;

    /**
     * @param id
     * @param contentTypeResolver 
     */
    public JSFResource(final ResourceIdentifier id, final ContentTypeResolver contentTypeResolver)
    {
        _id = id;
        _contentTypeResolver = contentTypeResolver;
    }

    /**
     * @return the identifier for this resource.
     */
    public final ResourceIdentifier getId()
    {
        return _id;
    }

    /**
     * @return true if this jsf resource is currently accessible.
     */
    public abstract boolean isAccessible();

    /**
     * @param contentTypeName
     * @return true if the resources matches the content type indicated
     * by the contentTypeName.
     */
    public boolean isContentType(final String contentTypeName)
    {
        return _contentTypeResolver.matchesType(contentTypeName, _id.getResourceName());
    }
}
