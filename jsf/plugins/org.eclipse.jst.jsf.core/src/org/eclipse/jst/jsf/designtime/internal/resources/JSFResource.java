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
    public JSFResource(final ResourceIdentifier id,
            final ContentTypeResolver contentTypeResolver)
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
     * @return true if the resources matches the content type indicated by the
     *         contentTypeName.
     */
    public boolean isContentType(final String contentTypeName)
    {
        return _contentTypeResolver.matchesType(contentTypeName, _id
                .getResourceName());
    }

    /**
     * @return a resource fragment is something which does not represent
     * a JSF resource but which is a concrete object in the underlying design time system
     * that maps to part or a "fragment" of a JSF resource.  The most common instance
     * is a workspace folder or jar entry that represents the library part of a resource id.
     */
    public abstract boolean isFragment();

    @Override
    public String toString()
    {
        return _id.toString();
    }
}
