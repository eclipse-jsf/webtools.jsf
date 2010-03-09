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

import org.eclipse.jst.jsf.core.internal.CompositeTagRegistryFactory.TagRegistryIdentifier;
import org.eclipse.jst.pagedesigner.editors.palette.impl.TaglibPaletteDrawer;

/**
 * PaletteItemManager interface.
 * 
 * NOT intended to be implemented by clients
 * 
 * @author mengbo
 */
public interface IPaletteItemManager {
	/**
	 * @return IProject
	 */
	public TagRegistryIdentifier getTagRegistryIdentifier();
	
	/**
	 * @return list of {@link TaglibPaletteDrawer}s being managed for this project 
	 */
	public List/*TaglibPaletteDrawer*/ getAllCategories();

	/**
	 * Create a TaglibPaletteDrawer with given label for given id
	 * 
	 * @param id
	 * @param label 
	 * @return TaglibPaletteDrawer
	 */
	public TaglibPaletteDrawer createTaglibPaletteDrawer(String id, String label);

	/**
	 * Retrieve the TaglibPaletteDrawer by id.  May be null.
	 * 
	 * @param id
	 * @return TaglibPaletteDrawer
	 */
	public TaglibPaletteDrawer getTaglibPalletteDrawer(String id);

	/**
	 * Locate by id, and if not found,  create a TaglibPaletteDrawer using label and id
	 * 
	 * @param id
	 * @param label
	 * @return TaglibPaletteDrawer
	 */
	public TaglibPaletteDrawer findOrCreateCategory(String id, String label);

	/**
	 * Adds a listener to the list of those notified when the model contents are
	 * replaced
	 * 
	 * @param listener -
	 *            the listener to add
	 */
	void addEntryChangeListener(IEntryChangeListener listener);

	/**
	 * Adds a listener to the list of those notified when the model contents are
	 * replaced
	 * 
	 * @param listener -
	 *            the listener to remove
	 */
	void removeEntryChangeListener(IEntryChangeListener listener);
}
