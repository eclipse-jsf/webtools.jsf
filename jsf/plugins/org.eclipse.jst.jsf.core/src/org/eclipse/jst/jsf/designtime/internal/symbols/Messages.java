/*
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.designtime.internal.symbols;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Message resource class
 * 
 * @author cbateman
 *
 */
/*package*/ final class Messages {
    private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.designtime.messages"; //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
            .getBundle(BUNDLE_NAME);

    private Messages() {
        // no external instatiation
    }

    /**
     * @param key
     * @return the message string for key or  !key! if not found
     */
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
