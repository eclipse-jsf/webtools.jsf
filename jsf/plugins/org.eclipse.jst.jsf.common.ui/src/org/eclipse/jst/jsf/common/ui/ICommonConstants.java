/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui;

/**
 * Common constants that would be used through the swad world. This might
 * include file extensions, ids
 * 
 * @author mengbo
 */
public interface ICommonConstants {
	/**
	 * These are the defines for the logging preference pages.
	 */
	public static final String P_LOGGING = "logging"; // all logging

	// preferences will
	// contain this string
	// in the key.

	public static final String P_CONSOLE_LOGGING = "console.logging.on";

	public static final String P_CONSOLE_LOG_LEVEL = "console.logging.max.level";

	public static final String P_ECLIPSE_LOGGING = "eclipse.logging.on";

	public static final String P_ECLIPSE_LOG_LEVEL = "eclipse.logging.max.level";

	public static final String P_FILE_LOGGING = "file.logging.on";

	public static final String P_FILE_LOG_LEVEL = "file.logging.max.level";

	public static final String P_FILE_PATH = "file.logging.path";

	public static final String P_FILE_CLEAR = "file.logging.startup.clear";

	public static final String P_FILE_ROLLOVER_FREQUENCY = "file.logging.rollover.frequency";

	public static final String P_REMOTE_LOGGING = "remote.logging.on";

	public static final String P_REMOTE_LOG_LEVEL = "remote.logging.max.level";

	public static final String P_REMOTE_HOST = "remote.logging.host";

	public static final String P_REMOTE_PORT = "remote.logging.port";

	/** Debug levels */
	public static final int DEBUG_LEVEL = 0;

	public static final int INFO_LEVEL = 1;

	public static final int WARN_LEVEL = 2;

	public static final int ERROR_LEVEL = 3;

	public static final int FATAL_LEVEL = 4;

	/** Rollover Frequency */
	public static final int DAILY_FREQ = 0;

	public static final int WEEKLY_FREQ = 1;

	public static final int MONTHLY_FREQ = 2;

	/** Sybase provided taglib id, defined in plugin.xml */
	public static final String SYBASE_TAGLIB_ID = "category_jsf";

	/** DataWindow taglib id, defined in plugin.xml */
	public static final String DATAWINDOW_TAGLIB_ID = "category_datawindow";

	/** Soap Service support category id, defined in plugin.xml of jmt */
	public static String SOAP_SERVICE_CATEGORY_ID = "category_soapservice";

	/** An nature for jsf based webapp project */
	public static final String NATURE_WEBAPP = "org.eclipse.jst.pagedesigner.WebappNature";

	public static final String XML_DECL = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";

	/** default faces-config.xml file, for version 1.0 */
	public static final String FACES_CONFIG_10 = XML_DECL
			+ "<!DOCTYPE faces-config PUBLIC \"-//Sun Microsystems, Inc.//DTD JavaServer Faces Config 1.0//EN\" \"http://java.sun.com/dtd/web-facesconfig_1_0.dtd\">\n<faces-config/>";

	/** default faces-config.xml file, for version 1.1 */
	public static final String FACES_CONFIG_11 = XML_DECL
			+ "<!DOCTYPE faces-config PUBLIC \"-//Sun Microsystems, Inc.//DTD JavaServer Faces Config 1.1//EN\" \"http://java.sun.com/dtd/web-facesconfig_1_1.dtd\">\n<faces-config/>";

	/** default web.xml, for version 2.2 */
	public static final String WEBAPP_22 = XML_DECL
			+ "<!DOCTYPE web-app PUBLIC \"-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN\" \"http://java.sun.com/j2ee/dtds/web-app_2_2.dtd\">";

	/** default web.xml, for version 2.3 */
	public static final String WEBAPP_23 = XML_DECL
			+ "<!DOCTYPE web-app PUBLIC \"-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN\" \"http://java.sun.com/dtd/web-app_2_3.dtd\">";

	/** default web.xml, for version 2.4 */
	public static final String WEBAPP_24 = XML_DECL
			+ "<web-app xmlns=\"http://java.sun.com/xml/ns/j2ee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" version=\"2.4\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd\">";
}
