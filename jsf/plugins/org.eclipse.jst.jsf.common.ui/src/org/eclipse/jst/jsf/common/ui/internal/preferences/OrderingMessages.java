package org.eclipse.jst.jsf.common.ui.internal.preferences;

import org.eclipse.osgi.util.NLS;

/**
 * @author cbateman
 *
 */
public class OrderingMessages extends NLS
{
    private static final String BUNDLE_NAME= "org.eclipse.jst.jsf.common.ui.internal.preferences.OrderingMessages";//$NON-NLS-1$

    private OrderingMessages() {
        // Do not instantiate
    }

    /**
     * see OrderMessages.properties
     */
    public static String Ordering_Up;
    /**
     * see OrderMessages.properties
     */
    public static String Ordering_Down;
    
    static {
        NLS.initializeMessages(BUNDLE_NAME, OrderingMessages.class);
    }

}
