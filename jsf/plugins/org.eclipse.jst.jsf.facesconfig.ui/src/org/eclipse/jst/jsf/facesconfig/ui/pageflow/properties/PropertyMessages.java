/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties;

import org.eclipse.osgi.util.NLS;

/**
 * NLS Message bundle for properties
 *
 */
public final class PropertyMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.PropertyMessages"; //$NON-NLS-1$

	private PropertyMessages() {
		// Do not instantiate
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, PropertyMessages.class);
	}

	/**
	 * see PropertyMessages.properties
	 */
	public static String property_displayName;
	/**
	 * see PropertyMessages.properties
	 */
	public static String property_description;
	/**
	 * see PropertyMessages.properties
	 */
	public static String property_configFile;
	/**
	 * see PropertyMessages.properties
	 */
	public static String property_largeIcon;

	/**
	 * see PropertyMessages.properties
	 */
	public static String property_smallIcon;

	/**
	 * see PropertyMessages.properties
	 */
	public static String property_fromAction;

	/**
	 * see PropertyMessages.properties
	 */
	public static String property_fromView;

	/**
	 * see PropertyMessages.properties
	 */
	public static String property_fromOutcome;

	/**
	 * see PropertyMessages.properties
	 */
	public static String property_redirect;

	/**
	 * see PropertyMessages.properties
	 */
	public static String property_browseButton;

	/**
	 * see PropertyMessages.properties
	 */
	public static String property_Command_SetValue;

}
