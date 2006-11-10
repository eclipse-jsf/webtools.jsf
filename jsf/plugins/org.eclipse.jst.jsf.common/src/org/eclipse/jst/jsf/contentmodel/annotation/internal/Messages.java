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

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.contentmodel.annotation.internal.messages"; //$NON-NLS-1$

	public static String CMAnnotationFileParserHelper_class_not_found;

	public static String CMAnnotationFileParserHelper_illegal_access_exception;

	public static String CMAnnotationFileParserHelper_instantiation_exception;

	public static String CMAnnotationFileParserHelper_unable_to_find_bundleid;

	public static String CMAnnotationFileParserHelper_unable_to_parse;

	public static String CMAnnotationFileRegistry_load_error;

	public static String CMAnnotationFileRegistryReader_problem;

	public static String CMAnnotationMap_IOException;

	public static String CMAnnotationMap_key_not_found;

	public static String CMAnnotationMap_MissingResource_exception;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
        // no external instantiation
	}
}
