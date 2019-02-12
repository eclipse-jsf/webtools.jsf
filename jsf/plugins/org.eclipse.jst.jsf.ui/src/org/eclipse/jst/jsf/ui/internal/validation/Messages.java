/*******************************************************************************
 * Copyright (c) 2008, 2010 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.ui.internal.validation;

import org.eclipse.osgi.util.NLS;

/**
 * String resource handler
 *
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.ui.internal.validation.messages"; //$NON-NLS-1$
	/**
	 * see messages.properties
	 */
	public static String ValidationMessageFactory_DefaultElSeverityDisplayName;
	/**
	 * see messages.properties
	 */
	public static String ValidationMessageFactory_DefaultTypeComparatorDisplayName;
	/**
	 * see messages.properties
	 */
	public static String ValidationMessageFactory_DefaultFaceletSeverityDisplayName;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
