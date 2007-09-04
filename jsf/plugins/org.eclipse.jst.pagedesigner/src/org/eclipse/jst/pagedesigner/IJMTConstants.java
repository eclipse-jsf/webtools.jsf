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
	public static final String PAGEDESIGNER_RESOURCE_BUNDLE_FILE = "org.eclipse.jst.pagedesigner.editors.pagedesigner.JSPEditorMessages"; //$NON-NLS-1$

	// Properties contains general properties and defaults to preferences.
	public static final String ROOT_RESOURCEBUNDLE = "org.eclipse.jst.pagedesigner.JMTResources"; //$NON-NLS-1$

	public static final String DEFAULT_PROPERTIES = "default.properties"; //$NON-NLS-1$

	public static final String USERAGENT = "html4.css"; //$NON-NLS-1$

	public static final String EDITORID_HTML = "org.eclipse.jst.pagedesigner.PageDesignerEditor"; //$NON-NLS-1$

	public static final String PREF_PALETTE_SHOW_ALL = "pref.palette.showall"; //$NON-NLS-1$

	public static final String EXTENSION_POINT_PAGEDESIGNER = "pageDesignerExtension"; //$NON-NLS-1$

	public static final String EXTENSION_POINT_CMREGISTRY = "cmRegistry"; //$NON-NLS-1$

	public static final String EXTENSION_POINT_PALETTEITEMCONFIG = "PaletteItemConfigContributions"; //$NON-NLS-1$

	public static final String ATTRIBUTE_PATH_PALETTEITEMCONFIG = "path"; //$NON-NLS-1$

	public static final String ATTRIBUTE_INDEX_PALETTEITEMCONFIG = "index"; //$NON-NLS-1$

	public static final String LOCAL_DROP_HANDLER = "localDropHandler"; //$NON-NLS-1$

	public static final String TAG_CONVERTER_FACTORY = "tagConverterFactory"; //$NON-NLS-1$

	public static final String ATTRIBUTE_CELLEDITOR_FACTORY = "attributeCellEditorFactory"; //$NON-NLS-1$

	public static final String ELEMENT_EDIT_FACTORY = "elementEditFactory"; //$NON-NLS-1$

	public static final String LINK_CREATOR = "linkCreator"; //$NON-NLS-1$

    public static final String TAG_CREATOR_FACTORY_EXT_NAME = "tagCreationFactories"; // $NON-NLS-1$

    public static final Object TAG_CREATOR_FACTORY_ELEMENT_NAME = "tagCreatorFactory"; // $NON-NLS-1$
    
}
