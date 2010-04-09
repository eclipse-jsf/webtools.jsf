package org.eclipse.jst.jsf.designtime.internal.resources;

import org.eclipse.osgi.util.NLS;

/**
 * NLS for resource identifier messages.
 * @author cbateman
 *
 */
public class Messages extends NLS
{
    private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.designtime.internal.resources.messages"; //$NON-NLS-1$
    /**
     * see messages.properties
     */
    public static String ResourceIdentifier_0;
    static
    {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages()
    {
        // no external instantiation
    }
}
