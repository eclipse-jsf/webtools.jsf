/*******************************************************************************
 * Copyright (c) 2006, 2015 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.validation.internal.facelet;

import org.eclipse.osgi.util.NLS;

class Messages extends NLS
{
    private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.validation.internal.facelet.messages"; //$NON-NLS-1$
    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
    /**
     * see messages.properties
     */
    public static String CANNOT_FIND_FACELET_TAGLIB;

    private Messages()
    {
        // do nothing; no external instantiation
    }
}
