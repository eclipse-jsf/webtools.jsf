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

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.pagedesigner.editors.palette.impl.TaglibPaletteDrawer;

/**
 * @author mengbo
 */
public interface IPaletteItemManager {
	public IProject getProject();
	
	public List/*TaglibPaletteDrawer*/ getAllCategories();

	/**
	 * @param id
	 * @return
	 */
	public TaglibPaletteDrawer createTaglibPaletteDrawer(String id, String label);

	/**
	 * @param id
	 * @return
	 */
	public TaglibPaletteDrawer getTaglibPalletteDrawer(String id);

	/**
	 * @param id
	 * @param label
	 * @return
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
