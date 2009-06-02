/*******************************************************************************
 * Copyright (c) 2009 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.dtresourceprovider;

import java.util.List;

import org.w3c.dom.stylesheets.StyleSheet;

/**
 * Interface that a design-time (DT) "skin" must implement.
 * 
 * @author Ian Trimble - Oracle
 */
public interface IDTSkin {

	/**
	 * Returns the human-readable name of the skin.
	 * 
	 * @return The human-readable name of the skin.
	 */
	public String getName();

	/**
	 * Returns a List of StyleSheet instances.
	 * 
	 * @return A List of StyleSheet instances. May return an empty List, but
	 * should not return null.
	 */
	public List<StyleSheet> getStyleSheets();

	/**
	 * Returns a List of Strings representing locations (typically URIs) of
	 * stylesheets.
	 * 
	 * @return List of Strings representing locations (typically URIs) of
	 * stylesheets. May return an empty List, but should not return null.
	 */
	public List<String> getStyleSheetLocations();

	/**
	 * This will be called before an instance goes out of scope, allowing it to
	 * clean up any necessary resources. The instance should still be usable,
	 * recreating any released resources as necessary on subsequent calls to its
	 * methods.
	 */
	public void releaseResources();

	/**
	 * Returns true if this skin is considered the default for its provider.
	 * 
	 * @return true if this skin is considered the default for its provider,
	 * else false.
	 */
	public boolean isDefault();

}
