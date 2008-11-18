/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.preferences;

import org.eclipse.osgi.util.NLS;

/**
 * NLS messages
 * @author cbateman
 *
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.ui.internal.preferences.messages"; //$NON-NLS-1$
	/**
	 * see messages.properties
	 */
	public static String JSPTagRegistryPreferencePage_0;
    /**
     * see messages.properties
     */
    public static String JSPTagRegistryPreferencePage_1;
    /**
     * see messages.properties
     */
    public static String JSPTagRegistryPreferencePage_2;
    /**
	 * see messages.properties
	 */
	public static String JSPTagRegistryPreferencePage_PanelDescription;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
