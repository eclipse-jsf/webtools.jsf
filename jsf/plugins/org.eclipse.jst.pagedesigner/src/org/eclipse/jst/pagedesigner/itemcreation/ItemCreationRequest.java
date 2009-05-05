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
import org.eclipse.jst.pagedesigner.editors.palette.IDropSourceData;
import org.eclipse.jst.pagedesigner.editors.palette.ITagDropSourceData;

/**
 * Tag tool item creation request class
 * 
 * @author mengbo
 */
public class ItemCreationRequest extends Request implements DropRequest {
	/**
	 * Constant used for item creation request
	 */
	public static final String REQ_ITEM_CREATION = "Item Creation"; //$NON-NLS-1$
	/**
	 * Constant used for tag tool item
	 */
	private static final String DROP_SOURCE_DATA = "TagToolPaletteEntry"; //$NON-NLS-1$
	/**
	 * Constant used for location during creation request
	 */
	public static final String LOCATION = "location"; //$NON-NLS-1$
	

	/**
	 * Constructor
	 */
	public ItemCreationRequest() {
		super(REQ_ITEM_CREATION);
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
	 * @param creationProvider
	 */
	public void setTagCreationProvider(final IDropSourceData creationProvider) {
		getExtendedData().remove(DROP_SOURCE_DATA);
		getExtendedData().put(DROP_SOURCE_DATA, creationProvider);
	}

	/**
	 * @return {@link ITagDropSourceData} requesting creation
	 */
	public IDropSourceData getTagCreationProvider() {
		return (IDropSourceData)getExtendedData().get(DROP_SOURCE_DATA);
	}

}
