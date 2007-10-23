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
package org.eclipse.jst.pagedesigner.editors.pagedesigner;

/**
 * The designer constants
 * 
 * TODO: dead?
 *
 */
interface IPageDesignerConstants {
	/**
	 * JSP UI Component Tag Node
	 */
	public static final String DEFAULT_TAG_NAME = "tagname";

	/**
	 * keyword
	 */
	public static final String ATTR_TYPE_CDATA = "CDATA";

	/**
	 * basename attribute
	 */
	public static final String ATTR_LOADBUNDLE_1 = "basename";

	/**
	 * var attribute
	 */
	public static final String ATTR_LOADBUNDLE_2 = "var";

	/**
	 * columns attribute
	 */
	public static final String ATTR_PANELGRID_1 = "columns";

	/**
	 * summary attribute
	 */
	public static final String ATTR_PANELGRID_2 = "summary";

	/**
	 * title attribute
	 */
	public static final String ATTR_PANELGRID_3 = "title";

	/**
	 * action attribute
	 */
	public static final String ATTR_BUTTON_1 = "action";

	/**
	 * value attribute
	 */
	public static final String ATTR_BUTTON_2 = "value";

	/**
	 * url attribute
	 */
	public static final String ATTR_GRAPHICIMAGE_1 = "url";

	/**
	 * binding attribute
	 */
	public static final String ATTR_GRAPHICIMAGE_2 = "binding";

	/**
	 * binding attribute
	 */
	public static final String ATTR_OUTPUTTEXT_1 = "binding";

	/**
	 * value attribute
	 */
	public static final String ATTR_OUTPUTTEXT_2 = "value";

	/**
	 * styleClass attribute
	 */
	public static final String ATTR_OUTPUTTEXT_3 = "styleClass";

	/**
	 * dataWindowBean attribute
	 */
	public static final String ATTR_DATAWINDOW_1 = "dataWindowBean";

	/**
	 * scriptName attribute
	 */
	public static final String ATTR_DATAWINDOW_2 = "scriptName";

	/**
	 * page attribute
	 */
	public static final String ATTR_INCLUDE = "page";

	/**
	 * prefix atttribute
	 */
	public static final String ATTR_TAGLIB_1 = "prefix";

	/**
	 * uri attribute
	 */
	public static final String ATTR_TAGLIB_2 = "uri";

	/**
	 * bundle attribute
	 */
	public static final String RESOURCE_BUNDLE_MARK = "bundle";

	/**
	 * value attribute
	 */
	public static final String ATTR_RESOURCE_BUNDLE = "value";

	/**
	 * value attribute
	 */
	public static final String ATTR_FACET = "value";

	/**
	 * view tag
	 */
	public static final String TAG_VIEW_TYPE = "view";

	/**
	 * for tag
	 */
	public static final String TAG_FORM_TYPE = "form";

	/**
	 * text tag
	 */
	public static final String TAG_TEXT_TYPE = "text";

	/**
	 * loadBundle tag
	 */
	public static final String TAG_LOADBUNDLE_TYPE = "loadBundle";

	/**
	 * panelGrid tag
	 */
	public static final String TAG_PANELGRID_TYPE = "panelGrid";

	/**
	 * 
	 */
	public static final String TAG_IMAGE_TYPE = "image";

	/**
	 * outputText tag
	 */
	public static final String TAG_OUTPUTTEXT_TYPE = "outputText";

	/**
	 * 
	 */
	public static final String TAG_BUTTON_TYPE = "button";

	/**
	 * 
	 */
	public static final String TAG_DATAWINDOW_TYPE = "datawindow";

	/**
	 * 
	 */
	public static final String TAG_TAGLIB_TYPE = "taglib";

	/**
	 * 
	 */
	public static final String TAG_INCLUDE_TYPE = "include";

	/**
	 * 
	 */
	public static final String TAG_OTHERS_TYPE = "";

	/**
	 * 
	 */
	public static final String TAG_NAME_VIEW = "view";

	/**
	 * 
	 */
	public static final String TAG_NAME_FORM = "form";

	/**
	 * 
	 */
	public static final String TAG_NAME_PANELGRID = "panelGrid";

	/**
	 * 
	 */
	public static final String TAG_NAME_PANELGROUP = "panelGroup";

	/**
	 * 
	 */
	public static final String TAG_NAME_FACET = "facet";

	/**
	 * 
	 */
	public static final String TAG_NAME_DATAWINDOW = "dataWindow";

	/**
	 * 
	 */
	public static final String TAG_NAME_OUTPUTTEXT = "outputText";

	/**
	 * 
	 */
	public static final String TAG_NAME_INCLUDE = "include";

	/**
	 * 
	 */
	public static final String TAG_NAME_LOADBUNDLE = "loadBundle";

	/**
	 * 
	 */
	public static final String TAG_NAME_TAGLIB = "taglib";

	/**
	 * graphicImage tag
	 */
	public static final String TAG_NAME_GRAPHICIMAGE = "graphicImage";

	/**
	 * commandButton tag
	 */
	public static final String TAG_NAME_COMMANDBUTTON = "commandButton";

	/**
	 * 
	 */
	public static final String REQUEST_TYPE_SELECT = "selection";

	/**
	 * 
	 */
	public static final int EOF = -1;

	/**
	 * 
	 */
	public static final char CHAR_TAB = '\t';

	/**
	 * 
	 */
	public static final char CHAR_N_RETURN = '\n';

	/**
	 * 
	 */
	public static final String STRING_N_RETURN = "\n";

	/**
	 * 
	 */
	public static final String STRING_BLANK = " ";

	/**
	 * 
	 */
	public static final char CHAR_BLANK = ' ';

	/**
	 * 
	 */
	public static final String FONT_NAME_HELVETICA = "Helvetica";
}
