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
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemDescriptor;

/**
 * @author mengbo
 */
public class ItemCreationRequest extends Request implements DropRequest {
	public static final String REQ_ITEM_CREATION = "Item Creation";

	private Point _location;

	private IPaletteItemDescriptor _itemDescriptor;

	/**
	 * 
	 */
	public ItemCreationRequest() {
		super(REQ_ITEM_CREATION);
	}

	/**
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
		return _location;
	}

	/**
	 * Sets the location where the new object will be placed.
	 * 
	 * @param location
	 *            the location
	 */
	public void setLocation(Point location) {
		this._location = location;
	}

	public void setItemDescriptor(IPaletteItemDescriptor desc) {
		this._itemDescriptor = desc;
	}

	public IPaletteItemDescriptor getItemDescriptor() {
		return this._itemDescriptor;
	}

}
