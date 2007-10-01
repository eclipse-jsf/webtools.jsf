/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.contentmodel.annotation.internal;

import org.eclipse.osgi.util.NLS;

/**
 * @deprecated see common.metadata package
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.contentmodel.annotation.internal.messages"; //$NON-NLS-1$

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	/**
	 * see message.properties
	 */
	public static String CMAnnotationFileParserHelper_class_not_found;

	/**
	 * see message.properties
	 */
	public static String CMAnnotationFileParserHelper_illegal_access_exception;

	/**
	 * see message.properties
	 */
	public static String CMAnnotationFileParserHelper_instantiation_exception;

	/**
	 * see message.properties
	 */
	public static String CMAnnotationFileParserHelper_unable_to_find_bundleid;

	/**
	 * see message.properties
	 */
	public static String CMAnnotationFileParserHelper_unable_to_parse;

	/**
	 * see message.properties
	 */
	public static String CMAnnotationFileRegistry_load_error;

	/**
	 * see message.properties
	 */
	public static String CMAnnotationFileRegistryReader_problem;

	/**
	 * see message.properties
	 */
	public static String CMAnnotationMap_IOException;

	/**
	 * see message.properties
	 */
	public static String CMAnnotationMap_key_not_found;

	/**
	 * see message.properties
	 */
	public static String CMAnnotationMap_MissingResource_exception;

	private Messages() {
        // no external instantiation
	}
}
