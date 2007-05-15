package org.eclipse.jst.jsf.validation.internal.el.operators;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

class Messages {
    private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.validation.internal.el.operators.messages"; //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
            .getBundle(BUNDLE_NAME);

    private Messages() {
        // no external instantiation
    }

    /**
     * @param key
     * @return the string or !key! if not found
     */
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
