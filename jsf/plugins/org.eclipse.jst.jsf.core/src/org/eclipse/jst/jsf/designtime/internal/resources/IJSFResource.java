package org.eclipse.jst.jsf.designtime.internal.resources;

/**
 * Represents a JSF loadable resource (spec 2.6).
 * 
 * @author cbateman
 *
 */
public interface IJSFResource extends IJSFResourceFragment
{
    /**
     * @param contentTypeName
     * @return true if the resources matches the content type indicated by the
     *         contentTypeName.
     */
    public abstract boolean isContentType(final String contentTypeName);

}
