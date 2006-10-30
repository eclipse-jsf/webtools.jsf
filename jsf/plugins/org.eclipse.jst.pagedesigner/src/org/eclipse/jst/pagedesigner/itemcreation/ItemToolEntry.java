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
package org.eclipse.jst.pagedesigner.itemcreation;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemDescriptor;

/**
 * ItemToolEntry is used to create an item. We are not using CreationToolEntry
 * for creating item, since the default GEF implementation require creating of
 * the object before drop into the view. We do not want to create the XML
 * element (and possibly its taglib declaration) before the drop is really
 * performed.)
 * 
 * @author mengbo
 */
public class ItemToolEntry extends ToolEntry {
	private IPaletteItemDescriptor _itemDesc;

	/**
	 * @param label
	 * @param shortDesc
	 * @param iconSmall
	 * @param iconLarge
	 */
	public ItemToolEntry(String label, String shortDesc,
			ImageDescriptor iconSmall, ImageDescriptor iconLarge,
			IPaletteItemDescriptor itemDesc) {
		super(label, shortDesc, iconSmall, iconLarge);
		this._itemDesc = itemDesc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.palette.ToolEntry#createTool()
	 */
	public Tool createTool() {
		return new ItemCreationTool(_itemDesc);
	}

	public IPaletteItemDescriptor getItemDesc() {
		return _itemDesc;
	}
}
