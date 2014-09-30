/*******************************************************************************
 * Copyright (c) 2009 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.ui.preferences;

import java.util.Arrays;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.editors.HTMLEditor;

/**
 * Utility class for handling preferences related to the Web Page Editor plug-in.
 * <br>
 * <p><b>Provisional API - subject to change</b></p>
 */
public class PDPreferences extends AbstractPreferenceInitializer {

    /**
     * Preference used to set modes for the page designer when displayed in a
     * sash editor.
     */
    public static final String SASH_EDITOR_MODE_PREF = PDPreferences.class
            .getName()
            + ".sash_editor_mode"; //$NON-NLS-1$
    /**
     * The number of pixels of artificial cell padding to use Default = 0
     */
    public final static String CSS_USE_ARTIFICAL_CELL_PADDING = PDPreferences.class
            .getName()
            + ".CSSArtificalCellPadding"; //$NON-NLS-1$
    /**
     * Whether or not to enable absolute positioning Default = false
     */
    public final static String CSS_ENABLE_ABSOLUTE_POSITIONING = PDPreferences.class
            .getName()
            + ".CSSEnableAbsolutePositioning"; //$NON-NLS-1$
    /**
     * Content types (as comma-delimited String) for which to hide preview page (default is the empty String).
     */
    public static final String HIDE_PREVIEW_PAGE_FOR_CONTENT_TYPES = PDPreferences.class.
    		getName() +
    		".hidePreviewPageForContentTypes"; //$NON-NLS-1$
    /**
     * The default value for absolute positioning enablement
     */
    public static final boolean DEFAULT_CSS_ENABLE_ABSOLUTE_POSITIONING = false;
    /**
     * The default value for artificial cell padding.
     */
    public static final int DEFAULT_CSS_USE_ARTIFICIAL_CELL_PADDING = 4;
    /**
     * Default value for content types for which to hide preview page.
     */
    public static final String DEFAULT_HIDE_PREVIEW_PAGE_FOR_CONTENT_TYPES = ""; //$NON-NLS-1$

    @Override
    public void initializeDefaultPreferences()
    {
        // Set default HTML editor split vertical (i.e. with top and bottom
        // pane)
        IPreferenceStore store = getPreferenceStore();
        store.setDefault(SASH_EDITOR_MODE_PREF, HTMLEditor.MODE_SASH_VERTICAL);
        store.setDefault(CSS_USE_ARTIFICAL_CELL_PADDING,
                DEFAULT_CSS_USE_ARTIFICIAL_CELL_PADDING);
        store.setDefault(CSS_ENABLE_ABSOLUTE_POSITIONING,
                DEFAULT_CSS_ENABLE_ABSOLUTE_POSITIONING);
        store.setDefault(HIDE_PREVIEW_PAGE_FOR_CONTENT_TYPES,
        		DEFAULT_HIDE_PREVIEW_PAGE_FOR_CONTENT_TYPES);
    }

    /**
     * @return the css absolute positioning enablement flag.
     */
    public boolean isCssAbsolutePositioningEnabled()
    {
        final IPreferenceStore store = getPreferenceStore();
        return store.getBoolean(CSS_ENABLE_ABSOLUTE_POSITIONING);
    }

    /**
     * @return the css artificial cell padding preference
     */
    public int getCssArtificialCellPadding()
    {
        final IPreferenceStore store = getPreferenceStore();
        return store.getInt(CSS_USE_ARTIFICAL_CELL_PADDING);
    }

    /**
     * Get list of content types for which to hide preview page. 
     * @return Content types (as String array) for which to hide preview page. 
     */
    public static String[] getHiddenPreviewPageContentTypes()
    {
    	String[] hiddenTypes = new String[0];
    	final IPreferenceStore store = getPreferenceStore();
    	final String hiddenTypesString = store.getString(HIDE_PREVIEW_PAGE_FOR_CONTENT_TYPES);
    	if (hiddenTypesString != null && hiddenTypesString.length() > 0)
    	{
    		hiddenTypes = hiddenTypesString.split(","); //$NON-NLS-1$
    	}
    	return hiddenTypes;
    }

    /**
     * Adds content type to list of types for which to hide preview page.
     * @param contentTypeId Content type to add to list.
     */
    public static void addHiddenPreviewPageContentType(String contentTypeId)
    {
    	if (contentTypeId != null && contentTypeId.length() > 0)
    	{
	    	final String[] hiddenContentTypes = getHiddenPreviewPageContentTypes();
	    	if (Arrays.binarySearch(hiddenContentTypes, contentTypeId) < 0)
	    	{
	    		String hiddenTypesString = arrayToString(hiddenContentTypes);
	    		if (hiddenTypesString.length() > 0)
	    		{
	    			hiddenTypesString = hiddenTypesString.concat(","); //$NON-NLS-1$
	    		}
	    		hiddenTypesString = hiddenTypesString.concat(contentTypeId.trim());
	        	final IPreferenceStore store = getPreferenceStore();
	        	store.setValue(HIDE_PREVIEW_PAGE_FOR_CONTENT_TYPES, hiddenTypesString);
	    	}
    	}
    }

    /**
     * Removes content type from list of types for which to hide preview page.
     * @param contentTypeId Content type to remove from list.
     */
    public static void removeHiddenPreviewPageContentType(String contentTypeId)
    {
    	if (contentTypeId != null && contentTypeId.length() > 0) {
    		final String[] hiddenContentTypes = getHiddenPreviewPageContentTypes();
    		if (Arrays.binarySearch(hiddenContentTypes, contentTypeId) >= 0)
    		{
    			final String hiddenTypesString = arrayToString(hiddenContentTypes);
    			hiddenTypesString.replace(contentTypeId, ""); //$NON-NLS-1$
    			hiddenTypesString.replace(",,", ","); //$NON-NLS-1$ //$NON-NLS-2$
	        	final IPreferenceStore store = getPreferenceStore();
	        	store.setValue(HIDE_PREVIEW_PAGE_FOR_CONTENT_TYPES, hiddenTypesString);
    		}
    	}
    }

    private static IPreferenceStore getPreferenceStore()
    {
        return PDPlugin.getDefault().getPreferenceStore();
    }

    private static String arrayToString(String[] array)
    {
    	String string = ""; //$NON-NLS-1$
    	if (array != null && array.length > 0)
    	{
    		StringBuilder builder = new StringBuilder();
    		for (int i = 0; i < array.length; i++)
    		{
    			if (i > 0)
    			{
    				builder.append(',');
    			}
    			builder.append(array[i]);
    		}
    		string = builder.toString();
    	}
    	return string;
    }

}
