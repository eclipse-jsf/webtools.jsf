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
package org.eclipse.jst.pagedesigner.editors.palette;

import java.util.List;

/**
 * @author mengbo
 */
public interface IPaletteItemCategory extends IPaletteItemEntry {

	public String getURI();

	/**
	 * return an list of items belong to this category.
	 * 
	 * @return
	 */
	public List getPaletteItems();

	/**
	 * @param tagName
	 * @return
	 */
	public IPaletteItemDescriptor getItemByTagName(String tagName);

	/**
	 * @param tagName
	 * @return
	 */
	public IPaletteItemDescriptor getItemByID(String id);

	/**
	 * @param tagName
	 * @return
	 */
	public IPaletteItemDescriptor createItem(String tagName);

	/**
	 * @param descriptor
	 */
	public void addPaletteItem(IPaletteItemDescriptor descriptor);

	/**
	 * @param prefix
	 */
	public void setDefaultPrefix(String prefix);

	public String getDefaultPrefix();

	public boolean isJSFComponentCategory();

	public void setJSFComponentCategory(boolean b);

}

// TODO: we may add methods for add/remove items into this interface to support
