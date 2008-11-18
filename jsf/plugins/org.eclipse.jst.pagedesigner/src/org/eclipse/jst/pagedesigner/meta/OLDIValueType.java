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
package org.eclipse.jst.pagedesigner.meta;

/**
 * All value types will be upcased.
 * 
 * @author mengbo
 * @deprecated
 */
public interface OLDIValueType {
	// shared for html and jsf
	/**
	 * 
	 */
	public static final String CSSSTYLE = "CSSSTYLE"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String CSSCLASS = "CSSCLASS"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String CSSID = "CSSID"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String ENUMERATED = "ENUMERATED"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String BOOLEAN = "BOOLEAN"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String RELATIVEPATH = "RELATIVEPATH"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String WEBPATH = "WEBPATH"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String COLOR = "COLOR"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String NAMED_BOOLEAN = "NAMED-BOOLEAN"; //$NON-NLS-1$

	// for jsf only
	/**
	 * 
	 */
	public static final String METHODBINDING = "METHODBINDING"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String CLASSNAME = "CLASSNAME"; //$NON-NLS-1$

	// new types
	/**
	 * 
	 */
	public static final String LINK = "LINK"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String JAVASCRIPT = "JAVASCRIPT"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String PROPERTYBINDING = "PROPERTYBINDING"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TIMEZONE = "TIMEZONE"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String CLASSPATH_RESOURCE = "CLASSPATH_RESOURCE"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String CURRENCYCODE = "CURRENCYCODE"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String LOCALE = "LOCALE"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String MULTICHOICE = "MULTICHOICE"; //$NON-NLS-1$
}
