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
public interface IPaletteItemManager {
	public List getAllCategories();

	/**
	 * @param tldURI
	 * @return
	 */
	public IPaletteItemCategory createCategory(String tldURI);

	/**
	 * @param tldURI
	 * @return
	 */
	public IPaletteItemCategory getCategoryByURI(String tldURI);

	/**
	 * @param uri
	 * @param catlabel
	 * @return
	 */
	public IPaletteItemCategory findOrCreateCategory(String uri, String catlabel);

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
