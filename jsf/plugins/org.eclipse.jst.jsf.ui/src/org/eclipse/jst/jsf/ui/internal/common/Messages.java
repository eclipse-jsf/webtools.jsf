/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
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
