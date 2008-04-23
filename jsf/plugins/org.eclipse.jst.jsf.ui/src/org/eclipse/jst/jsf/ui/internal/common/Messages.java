package org.eclipse.jst.jsf.ui.internal.common;

import org.eclipse.osgi.util.NLS;

/**
 * @author cbateman
 *
 */
public class Messages extends NLS
{
    private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.ui.internal.common.messages"; //$NON-NLS-1$
    /**
     * see messages.properties
     */
    public static String        ViewObjectPresenter_ComponentClass;
    /**
     * see messages.properties
     */
    public static String        ViewObjectPresenter_ComponentFamily;
    /**
     * see messages.properties
     */
    public static String        ViewObjectPresenter_ComponentType;
    /**
     * see messages.properties
     */
    public static String        ViewObjectPresenter_RenderType;
    static
    {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages()
    {
        // no instantiation
    }
}
