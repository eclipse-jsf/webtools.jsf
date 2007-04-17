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
package org.eclipse.jst.pagedesigner.editors.palette;

/**
 * Constants used by palette customizations persistence
 * 
 * @author mengbo
 */
public interface IPaletteConstants {
	/**
	 * 
	 */
	public static final String BUNDLE_ID = "org.eclipse.jst.pagedesigner"; //$NON-NLS-1$

	/**
	 * contant for palett-item element name
	 */
	public static final String ROOT = "palette-item"; //$NON-NLS-1$

	/**
	 * constant for uri attribute
	 */
	public static final String URI = "uri"; //$NON-NLS-1$

	/**
	 * Constant for hidden attribute
	 */
	public static final String ISHIDDEN = "hidden"; //$NON-NLS-1$

	/**
	 * constant for id attribute
	 */
	public static final String ID = "id"; //$NON-NLS-1$
	
	/**
	 * constant for category element name
	 */
	public static final String CATEGORY_TAG = "category"; //$NON-NLS-1$
	
//OLD - no longer/currently used

//	public static final String EXTENSION_POINT_ID = "ResourceContributions"; //$NON-NLS-1$
//	public static final String FILENAME = "/tag.xml"; //$NON-NLS-1$
//	public static final String TAGNAME = "tagName"; //$NON-NLS-1$
//	public static final String ITEM_TAG = "item"; //$NON-NLS-1$
//	public static final String EXPERT = "expert"; //$NON-NLS-1$
//	public static final String LARGEICON = "largeIcon"; //$NON-NLS-1$
//	public static final String SMALLICON = "smallIcon"; //$NON-NLS-1$
//	public static final String LABEL = "label"; //$NON-NLS-1$
//	public static final String INITIALSTATE = "initialState"; //$NON-NLS-1$
//	public static final String SHORTDESC = "shortDesc"; //$NON-NLS-1$
//	public static final String ATTRIBUTE_TAG = "attribute"; //$NON-NLS-1$
//	public static final String NAME = "name"; //$NON-NLS-1$
//	public static final String VALUE = "value"; //$NON-NLS-1$
//	public static final String ICONPREFIX = "iconPrefix"; //$NON-NLS-1$
//	public static final String TAGLIBEXT = "capability"; //$NON-NLS-1$
//	public static final String JARPROTO = "jar:file://"; //$NON-NLS-1$
//	public static final String FILEPROTO = "file://"; //$NON-NLS-1$
//	public static final String REQUIREHFORM = "requireHForm"; //$NON-NLS-1$
//	public static final String JSFCOMPONENTCATEGORY = "jsfComponentCategory"; //$NON-NLS-1$
}
