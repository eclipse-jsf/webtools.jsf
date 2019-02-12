/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.core.internal.jsflibraryregistry.util;

import org.eclipse.osgi.util.NLS;

/**
 *  String resource handler.
 *
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.core.internal.jsflibraryregistry.util.messages"; //$NON-NLS-1$
	/**
	 * see messages.properties
	 */
	public static String JSFLibraryRegistryUpgradeUtil_Error;
	/**
	 * see messages.properties
	 */
	public static String JSFLibraryRegistryUpgradeUtil_Operation;
	/**
	 * see messages.properties
	 */
	public static String JSFLibraryRegistryUpgradeUtil_v1Tov2Operation;
	/**
	 * see messages.properties
	 */
	public static String UpgradeOperation_Success;
	/**
	 * see messages.properties
	 */
	public static String UpgradeStatus_Error;
	/**
	 * see messages.properties
	 */
	public static String UpgradeStatus_OK;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
		//
	}
}
