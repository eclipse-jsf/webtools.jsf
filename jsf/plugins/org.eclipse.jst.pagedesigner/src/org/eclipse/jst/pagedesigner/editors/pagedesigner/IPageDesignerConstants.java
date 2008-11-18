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
	public static final String DEFAULT_TAG_NAME = "tagname"; //$NON-NLS-1$

	/**
	 * keyword
	 */
	public static final String ATTR_TYPE_CDATA = "CDATA"; //$NON-NLS-1$

	/**
	 * basename attribute
	 */
	public static final String ATTR_LOADBUNDLE_1 = "basename"; //$NON-NLS-1$

	/**
	 * var attribute
	 */
	public static final String ATTR_LOADBUNDLE_2 = "var"; //$NON-NLS-1$

	/**
	 * columns attribute
	 */
	public static final String ATTR_PANELGRID_1 = "columns"; //$NON-NLS-1$

	/**
	 * summary attribute
	 */
	public static final String ATTR_PANELGRID_2 = "summary"; //$NON-NLS-1$

	/**
	 * title attribute
	 */
	public static final String ATTR_PANELGRID_3 = "title"; //$NON-NLS-1$

	/**
	 * action attribute
	 */
	public static final String ATTR_BUTTON_1 = "action"; //$NON-NLS-1$

	/**
	 * value attribute
	 */
	public static final String ATTR_BUTTON_2 = "value"; //$NON-NLS-1$

	/**
	 * url attribute
	 */
	public static final String ATTR_GRAPHICIMAGE_1 = "url"; //$NON-NLS-1$

	/**
	 * binding attribute
	 */
	public static final String ATTR_GRAPHICIMAGE_2 = "binding"; //$NON-NLS-1$

	/**
	 * binding attribute
	 */
	public static final String ATTR_OUTPUTTEXT_1 = "binding"; //$NON-NLS-1$

	/**
	 * value attribute
	 */
	public static final String ATTR_OUTPUTTEXT_2 = "value"; //$NON-NLS-1$

	/**
	 * styleClass attribute
	 */
	public static final String ATTR_OUTPUTTEXT_3 = "styleClass"; //$NON-NLS-1$

	/**
	 * dataWindowBean attribute
	 */
	public static final String ATTR_DATAWINDOW_1 = "dataWindowBean"; //$NON-NLS-1$

	/**
	 * scriptName attribute
	 */
	public static final String ATTR_DATAWINDOW_2 = "scriptName"; //$NON-NLS-1$

	/**
	 * page attribute
	 */
	public static final String ATTR_INCLUDE = "page"; //$NON-NLS-1$

	/**
	 * prefix atttribute
	 */
	public static final String ATTR_TAGLIB_1 = "prefix"; //$NON-NLS-1$

	/**
	 * uri attribute
	 */
	public static final String ATTR_TAGLIB_2 = "uri"; //$NON-NLS-1$

	/**
	 * bundle attribute
	 */
	public static final String RESOURCE_BUNDLE_MARK = "bundle"; //$NON-NLS-1$

	/**
	 * value attribute
	 */
	public static final String ATTR_RESOURCE_BUNDLE = "value"; //$NON-NLS-1$

	/**
	 * value attribute
	 */
	public static final String ATTR_FACET = "value"; //$NON-NLS-1$

	/**
	 * view tag
	 */
	public static final String TAG_VIEW_TYPE = "view"; //$NON-NLS-1$

	/**
	 * for tag
	 */
	public static final String TAG_FORM_TYPE = "form"; //$NON-NLS-1$

	/**
	 * text tag
	 */
	public static final String TAG_TEXT_TYPE = "text"; //$NON-NLS-1$

	/**
	 * loadBundle tag
	 */
	public static final String TAG_LOADBUNDLE_TYPE = "loadBundle"; //$NON-NLS-1$

	/**
	 * panelGrid tag
	 */
	public static final String TAG_PANELGRID_TYPE = "panelGrid"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TAG_IMAGE_TYPE = "image"; //$NON-NLS-1$

	/**
	 * outputText tag
	 */
	public static final String TAG_OUTPUTTEXT_TYPE = "outputText"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TAG_BUTTON_TYPE = "button"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TAG_DATAWINDOW_TYPE = "datawindow"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TAG_TAGLIB_TYPE = "taglib"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TAG_INCLUDE_TYPE = "include"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TAG_OTHERS_TYPE = ""; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TAG_NAME_VIEW = "view"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TAG_NAME_FORM = "form"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TAG_NAME_PANELGRID = "panelGrid"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TAG_NAME_PANELGROUP = "panelGroup"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TAG_NAME_FACET = "facet"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TAG_NAME_DATAWINDOW = "dataWindow"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TAG_NAME_OUTPUTTEXT = "outputText"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TAG_NAME_INCLUDE = "include"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TAG_NAME_LOADBUNDLE = "loadBundle"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String TAG_NAME_TAGLIB = "taglib"; //$NON-NLS-1$

	/**
	 * graphicImage tag
	 */
	public static final String TAG_NAME_GRAPHICIMAGE = "graphicImage"; //$NON-NLS-1$

	/**
	 * commandButton tag
	 */
	public static final String TAG_NAME_COMMANDBUTTON = "commandButton"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String REQUEST_TYPE_SELECT = "selection"; //$NON-NLS-1$

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
	public static final String STRING_N_RETURN = "\n"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final String STRING_BLANK = " "; //$NON-NLS-1$

	/**
	 * 
	 */
	public static final char CHAR_BLANK = ' ';

	/**
	 * 
	 */
	public static final String FONT_NAME_HELVETICA = "Helvetica"; //$NON-NLS-1$
}
