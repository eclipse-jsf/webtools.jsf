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
	public static final String DOT = "."; //$NON-NLS-1$

	/**
	 * the path separator
	 */
	public static final String PATH_SEPARATOR = "/"; //$NON-NLS-1$

	/** file extensions */
	/**
	 * class file extension
	 */
	public static final String EXT_CLASS = "class"; //$NON-NLS-1$

	/**
	 * jar file extension
	 */
	public static final String EXT_JAR = "jar"; //$NON-NLS-1$

	/**
	 * java file extension
	 */
	public static final String EXT_JAVA = "java"; //$NON-NLS-1$


	/**
	 * jsp file extension
	 */
	public static final String EXT_JSP = "jsp"; //$NON-NLS-1$

	/**
	 * properties file extension
	 */
	public static final String EXT_PROPERTIES = "properties"; //$NON-NLS-1$

	/**
	 * taglib file extension
	 */
	public static final String EXT_TAGLIB = "tld"; //$NON-NLS-1$

	/**
	 * standard web.xml file name
	 */
	public static final String FILE_WEB_XML = "web.xml"; //$NON-NLS-1$

	/** folders */

	/**
	 * classes folder name
	 */
	public static final String FOLDER_CLASS = "classes"; //$NON-NLS-1$

	/**
	 * icons folder name
	 */
	public static final String FOLDER_ICONS = "icons"; //$NON-NLS-1$

	/**
	 * the meta-inf folder name
	 */
	public static final String FOLDER_METAINF = "META-INF"; //$NON-NLS-1$

	/**
	 * the src folder name
	 */
	public static final String FOLDER_SOURCE = "src"; //$NON-NLS-1$

	/**
	 * the web-inf folder name
	 */
	public static final String FOLDER_WEBINF = "WEB-INF"; //$NON-NLS-1$

	/** the webroot folder depth relative to the project */
	public static final int WEBROOT_FOLDER_DEPTH = 2;
}
