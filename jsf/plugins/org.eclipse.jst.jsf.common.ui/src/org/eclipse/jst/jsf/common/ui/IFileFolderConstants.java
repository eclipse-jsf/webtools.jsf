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
 * @author mengbo
 * @version 1.5
 */
public interface IFileFolderConstants {

	/**
	 * the dot character
	 */
	public static final String DOT = ".";

	/**
	 * the path separator
	 */
	public static final String PATH_SEPARATOR = "/";

	/** file extensions */
	/**
	 * class file extension
	 */
	public static final String EXT_CLASS = "class";

	/**
	 * jar file extension
	 */
	public static final String EXT_JAR = "jar";

	/**
	 * java file extension
	 */
	public static final String EXT_JAVA = "java";


	/**
	 * jsp file extension
	 */
	public static final String EXT_JSP = "jsp";

	/**
	 * properties file extension
	 */
	public static final String EXT_PROPERTIES = "properties";

	/**
	 * taglib file extension
	 */
	public static final String EXT_TAGLIB = "tld";

	/**
	 * standard web.xml file name
	 */
	public static final String FILE_WEB_XML = "web.xml";

	/** folders */

	/**
	 * classes folder name
	 */
	public static final String FOLDER_CLASS = "classes";

	/**
	 * icons folder name
	 */
	public static final String FOLDER_ICONS = "icons";

	/**
	 * the meta-inf folder name
	 */
	public static final String FOLDER_METAINF = "META-INF";

	/**
	 * the src folder name
	 */
	public static final String FOLDER_SOURCE = "src";

	/**
	 * the web-inf folder name
	 */
	public static final String FOLDER_WEBINF = "WEB-INF";

	/** the webroot folder depth relative to the project */
	public static final int WEBROOT_FOLDER_DEPTH = 2;
}
