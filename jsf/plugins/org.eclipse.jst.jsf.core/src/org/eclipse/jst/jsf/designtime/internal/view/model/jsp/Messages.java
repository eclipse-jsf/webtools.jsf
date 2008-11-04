package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.osgi.util.NLS;

/**
 * NLS message getter.
 * 
 * @author cbateman
 *
 */
public class Messages extends NLS
{
    private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.designtime.internal.view.model.jsp.messages"; //$NON-NLS-1$
    /**
     * see messages.properties
     */
    public static String UnresolvedJSPTagResolvingStrategy_1;
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
