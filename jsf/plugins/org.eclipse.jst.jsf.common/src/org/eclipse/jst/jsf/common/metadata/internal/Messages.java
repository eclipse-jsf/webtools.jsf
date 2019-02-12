/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.common.metadata.internal;

import org.eclipse.osgi.util.NLS;

/**
 * Externalized strings for Metadata
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.common.metadata.internal.messages"; //$NON-NLS-1$

	/**
	 * Property key not found in bundle
	 */
	public static String Key_not_found;

	/**
	 * Missing resource
	 */
	public static String MissingResource_exception;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
        // no external instantiation
	}
}
