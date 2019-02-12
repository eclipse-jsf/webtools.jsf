/*******************************************************************************
 * Copyright (c) 2008, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui.internal.guiutils;

import org.eclipse.osgi.util.NLS;

/**
 * String resource handler.
 * 
 * @author Debajit Adhikary - Oracle
 */

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.common.ui.internal.guiutils.messages"; //$NON-NLS-1$
	
	/**
	 * See messages.properties.
	 */
	public static String IntroductionSection_noIntroDescription;
	
	/**
	 * See messages.properties.
	 */
	public static String IntroductionSection_noIntroTitle;
	
	/**
	 * See messages.properties.
	 */
	public static String BrowserWindow_loading;

	
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
