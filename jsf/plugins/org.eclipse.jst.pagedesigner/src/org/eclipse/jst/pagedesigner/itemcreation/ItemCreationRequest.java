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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;

/**
 * Tag tool item creation request class
 * 
 * @author mengbo
 */
public class ItemCreationRequest extends Request implements DropRequest {
	/**
	 * Constant used for item creation request
	 */
	public static final String REQ_ITEM_CREATION = "Item Creation";
	/**
	 * Constant used for tag tool item
	 */
	public static final String TAG_TOOL_PALETTE_ENTRY = "TagToolPaletteEntry";
	/**
	 * Constant used for location during creation request
	 */
	public static final String LOCATION = "location";
	

	/**
	 * Constructor
	 */
	public ItemCreationRequest() {
		super(REQ_ITEM_CREATION);
	}

	/**
	 * Constructor
	 * @param type
	 */
	public ItemCreationRequest(Object type) {
		super(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.requests.DropRequest#getLocation()
	 */
	public Point getLocation() {
		return (Point)getExtendedData().get(LOCATION);
	}

	/**
	 * Sets the location where the new object will be placed.
	 * 
	 * @param location
	 *            the location
	 */
	public void setLocation(Point location) {
		getExtendedData().remove(LOCATION);
		getExtendedData().put(LOCATION, location);
	}

	/**
	 * Set the tag tool item for creation request
	 * @param tagToolPaletteEntryItem
	 */
	public void setTagToolPaletteEntry(TagToolPaletteEntry tagToolPaletteEntryItem) {
		getExtendedData().remove(TAG_TOOL_PALETTE_ENTRY);
		getExtendedData().put(TAG_TOOL_PALETTE_ENTRY, tagToolPaletteEntryItem);
	}

	/**
	 * @return {@link TagToolPaletteEntry} requesting creation
	 */
	public TagToolPaletteEntry getTagToolPaletteEntry() {
		return (TagToolPaletteEntry)getExtendedData().get(TAG_TOOL_PALETTE_ENTRY);
	}

}
