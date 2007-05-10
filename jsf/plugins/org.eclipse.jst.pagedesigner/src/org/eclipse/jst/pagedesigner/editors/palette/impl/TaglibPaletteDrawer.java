/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editors.palette.impl;

import java.util.Iterator;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;

/**
 * Palette Drawer for Tag lib items
 *
 */
public class TaglibPaletteDrawer extends PaletteDrawer {
	private String prefix;	

	/**
	 * Constructor
	 * @param uri
	 * @param label
	 */
	public TaglibPaletteDrawer(String uri, String label) {
		super(label);
		super.setId(uri);
		setDrawerType(ToolEntry.PALETTE_TYPE_TOOL);
	}
	
	/**
	 * @return default prefix for tag during creation
	 */
	public String getDefaultPrefix() {
		return prefix;
	}

	/**
	 * Set the default prefix for use during item creation
	 * @param prefix
	 */
	public void setDefaultPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return uri identifying the library of tags
	 */
	public String getURI(){
		return getId();
	}
	
	/**
	 * @param uri for the libary of tags
	 */
	public void setURI(String uri){
		setId(uri);
	}
	
	/**
	 * @param id
	 * @return TagToolPaletteEntry for the tag using the id
	 */
	public TagToolPaletteEntry getTagPaletteEntryById(String id){
		for (Iterator it=getChildren().iterator();it.hasNext();){
			TagToolPaletteEntry tag = (TagToolPaletteEntry)it.next();
			if (tag.getId().equals(id))
				return tag;
		}
		return null;
	}
	
	/**
	 * @param tagName
	 * @return TagToolPaletteEntry using the tag name
	 */
	public TagToolPaletteEntry getTagPaletteEntryByTagName(String tagName){
		for (Iterator it=getChildren().iterator();it.hasNext();){
			TagToolPaletteEntry tag = (TagToolPaletteEntry)it.next();
			if (tag.getTagName().equalsIgnoreCase(tagName))
				return tag;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.palette.PaletteDrawer#acceptsType(java.lang.Object)
	 */
	public boolean acceptsType(Object object){
			return true;
	}
	
	/**
	 * @return PERMISSION_HIDE_ONLY
	 * @see org.eclipse.gef.palette.PaletteEntry#getUserModificationPermission()
	 */
	public int getUserModificationPermission() {
		return PERMISSION_HIDE_ONLY;
	}
}
