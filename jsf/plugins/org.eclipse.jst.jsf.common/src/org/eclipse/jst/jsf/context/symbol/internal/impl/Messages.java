package org.eclipse.jst.jsf.context.symbol.internal.impl;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/*package*/ class Messages
{
    private static final String         BUNDLE_NAME     = "org.eclipse.jst.jsf.context.symbol.internal.impl.messages"; //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
                                                                .getBundle(BUNDLE_NAME);

    private Messages()
    {
        // no external instantiation
    }

    public static String getString(String key)
    {
        try
        {
            return RESOURCE_BUNDLE.getString(key);
        }
        catch (MissingResourceException e)
        {
            return '!' + key + '!';
        }
    }
}
