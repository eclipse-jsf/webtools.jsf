package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import org.eclipse.osgi.util.NLS;

/**
 * Package NLS manager.
 * @author cbateman
 *
 */
public class Messages extends NLS
{
    private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.messages"; //$NON-NLS-1$
    /**
     * see messages.properties.
     */
    public static String ContextParamSpecifiedFaceletTaglibLocator_0;
    /**
     * see messages.properties/
     */
    public static String JarFileFaceletTaglibLocator_0;
    static
    {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages()
    {
        // no external instantiation.
    }
}
