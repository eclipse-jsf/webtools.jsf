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
package org.eclipse.jst.pagedesigner;

/**
 * JMT constants that would be used through the JMT core plugin.
 * 
 * @author mengbo
 */
public interface IJMTConstants {
	/**
	 * bundle file name
	 */
	public static final String PAGEDESIGNER_RESOURCE_BUNDLE_FILE = "org.eclipse.jst.pagedesigner.editors.pagedesigner.JSPEditorMessages"; //$NON-NLS-1$

	// Properties contains general properties and defaults to preferences.
	/**
	 * root resource bundle name
	 */
	public static final String ROOT_RESOURCEBUNDLE = "org.eclipse.jst.pagedesigner.JMTResources"; //$NON-NLS-1$

	/**
	 * default property file name
	 */
	public static final String DEFAULT_PROPERTIES = "default.properties"; //$NON-NLS-1$

	/**
	 * default style sheet
	 */
	public static final String USERAGENT = "html4.css"; //$NON-NLS-1$

	/**
	 * html editor id
	 */
	public static final String EDITORID_HTML = "org.eclipse.jst.pagedesigner.PageDesignerEditor"; //$NON-NLS-1$

	/**
	 * show all action preference id
	 */
	public static final String PREF_PALETTE_SHOW_ALL = "pref.palette.showall"; //$NON-NLS-1$

	/**
	 * local name of page designer extension
	 */
	public static final String EXTENSION_POINT_PAGEDESIGNER = "pageDesignerExtension"; //$NON-NLS-1$

	/**
	 * local name of cm registry extension
	 */
	public static final String EXTENSION_POINT_CMREGISTRY = "cmRegistry"; //$NON-NLS-1$

	/**
	 * local name of DT resource provider extension
	 */
	public static final String DT_RESOURCE_PROVIDER = "dtResourceProvider"; //$NON-NLS-1$

	/**
	 * local name of local drop handler extension
	 */
	public static final String LOCAL_DROP_HANDLER = "localDropHandler"; //$NON-NLS-1$

	/**
	 * local name of tag converter factory extension
	 */
	public static final String TAG_CONVERTER_FACTORY = "tagConverterFactory"; //$NON-NLS-1$

	/**
	 * local name of attribute cell editor factory extension
	 * @deprecated and unused
	 */
	public static final String ATTRIBUTE_CELLEDITOR_FACTORY = "attributeCellEditorFactory"; //$NON-NLS-1$

	/**
	 * local name of tag attribute cell editor factory extension
	 */
	public static final String TAG_ATTRIBUTE_CELLEDITOR_FACTORY = "tagAttributeCellEditorFactory"; //$NON-NLS-1$
	
	/**
	 * local name of tag transform operation extension
	 */
	public static final String TAG_TRANSFORM_OPERATION = "tagTransformOperation"; //$NON-NLS-1$
	
	/**
	 * 
	 * local name of element edit factory extension
	 */
	public static final String ELEMENT_EDIT_FACTORY = "elementEditFactory"; //$NON-NLS-1$

	/**
	 * local name of link creator extension
	 */
	public static final String LINK_CREATOR = "linkCreator"; //$NON-NLS-1$
    
	/**
	 * extension for property page factory
	 */
	public static final String PROPERTY_PAGE_FACTORY = "propertyPageFactory"; //$NON-NLS-1$
	/**
	 * extension for palette viewer page factory
	 */
	public static final String PALETTE_FACTORY = "paletteFactory"; //$NON-NLS-1$
	
}
