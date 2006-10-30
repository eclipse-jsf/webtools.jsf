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
 */
public interface IValueType {
	// shared for html and jsf
	public static final String CSSSTYLE = "CSSSTYLE";

	public static final String CSSCLASS = "CSSCLASS";

	public static final String CSSID = "CSSID";

	public static final String ENUMERATED = "ENUMERATED";

	public static final String BOOLEAN = "BOOLEAN";

	public static final String RELATIVEPATH = "RELATIVEPATH";

	public static final String WEBPATH = "WEBPATH";

	public static final String COLOR = "COLOR";

	public static final String NAMED_BOOLEAN = "NAMED-BOOLEAN";

	// for jsf only
	public static final String METHODBINDING = "METHODBINDING";

	public static final String CLASSNAME = "CLASSNAME";

	// new types
	public static final String LINK = "LINK";

	public static final String JAVASCRIPT = "JAVASCRIPT";

	public static final String PROPERTYBINDING = "PROPERTYBINDING";

	public static final String TIMEZONE = "TIMEZONE";

	public static final String CLASSPATH_RESOURCE = "CLASSPATH_RESOURCE";

	public static final String CURRENCYCODE = "CURRENCYCODE";

	public static final String LOCALE = "LOCALE";

	public static final String MULTICHOICE = "MULTICHOICE";
}
