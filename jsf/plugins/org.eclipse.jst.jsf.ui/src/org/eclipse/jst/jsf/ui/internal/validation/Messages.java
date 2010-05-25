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
