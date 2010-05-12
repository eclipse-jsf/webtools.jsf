package org.eclipse.jst.jsf.designtime.internal.resources;

import java.net.URL;

/**
 * @author cbateman
 *
 */
public interface IJarBasedJSFResource extends IJSFResource
{
    /**
     * @return the uri of the jar where the resource lives
     */
    public abstract URL getJarURL();

    /**
     * @return the zipEntry into the jar where the resource is.
     */
    public abstract String getJarEntryName();
}
