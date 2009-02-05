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
package org.eclipse.jst.pagedesigner.editors.palette;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.editors.palette.impl.TaglibPaletteDrawer;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationTool;

/**
 * Represents a tag palette item entry in the web page designer palette
 *
 */
public class TagToolPaletteEntry extends ToolEntry {

	private String tagName;
	
	/**
	 * Constructor
	 * @param tagName
	 * @param label
	 * @param shortDescription
	 * @param iconSmall
	 * @param iconLarge
	 */
	public TagToolPaletteEntry(String tagName, String label, String shortDescription,
			ImageDescriptor iconSmall, ImageDescriptor iconLarge) {
		super(label, shortDescription, iconSmall, iconLarge);
		this.tagName = tagName;
	}

	/**
	 * @return tagName
	 */
	public String getTagName(){
		return tagName;
	}
	
	/**
	 * Convenience method returning the tag libraries default prefix, if applicable
	 * @return default prefix 
	 */
	public String getDefaultPrefix(){
		if (getParent() instanceof TaglibPaletteDrawer)
			return ((TaglibPaletteDrawer)getParent()).getDefaultPrefix();
		return "";
	}

	/**
	 * @return uri of the tag's library
	 */
	public String getURI(){
		return ((TaglibPaletteDrawer)getParent()).getURI();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.palette.ToolEntry#createTool()
	 */
	public Tool createTool() {
		return new ItemCreationTool(this);
	}
	

}
