/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties;

import org.eclipse.osgi.util.NLS;

public final class PropertyMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.PropertyMessages";

	private PropertyMessages() {
		// Do not instantiate
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, PropertyMessages.class);
	}

	public static String property_displayName;

	public static String property_description;

	public static String property_configFile;

	public static String property_largeIcon;

	public static String property_smallIcon;

	public static String property_fromAction;

	public static String property_fromView;

	public static String property_fromOutcome;

	public static String property_redirect;

	public static String property_browseButton;

	public static String property_Command_SetValue;

}
