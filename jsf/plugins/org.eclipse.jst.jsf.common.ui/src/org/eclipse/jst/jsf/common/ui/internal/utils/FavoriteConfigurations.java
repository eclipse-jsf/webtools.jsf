/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui.internal.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.XMLMemento;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * This provides a means to store common properties for pages.
 * 
 * Holds a <code>List</code> of <code>Properties</code> which can be
 * manipulated by the user.
 * 
 * @author mengbo
 */
public class FavoriteConfigurations {
	private static Logger _log = JSFUICommonPlugin
			.getLogger(FavoriteConfigurations.class);

	private final static String FAV_EXTENSIONS = ".xml"; //$NON-NLS-1$

	private final static String ELEMENT_ROOT = "favorite"; //$NON-NLS-1$

	private final static String ELEMENT_CONFIG = "config"; //$NON-NLS-1$

	private final static String ELEMENT_NAME = "name"; //$NON-NLS-1$

	private final static String ELEMENT_PROPERTY = "property"; //$NON-NLS-1$

	private final static String ELEMENT_VALUE = "value"; //$NON-NLS-1$

	private Map _favorites;

	private IPath _favoriteFile;

	private String _favoriteName;

	/**
	 * Constructor
	 * 
	 * @param plugin
	 *            used for location of favorite file.
	 * @param favoriteName
	 *            used for name of file and for mapping the properties of this
	 *            favorite.
	 */
	public FavoriteConfigurations(AbstractUIPlugin plugin, String favoriteName) {
		super();
		_favoriteFile = plugin.getStateLocation().append(
				scanFileName(favoriteName) + FAV_EXTENSIONS);
		readFavorites();
	}

	/**
	 * Returns the favorite entry that is mapped under the given name.
	 * 
	 * @param name
	 *            the favorite
	 * @return <code>Properties</code> config entry for the favorite.
	 */
	public Properties getFavorite(String name) {
		return (Properties) _favorites.get(name);
	}

	/**
	 * Stores the Favorites into the plugin directory.
	 */
	public void saveFavorites() {
		FileWriter fw = null;
		try {
			XMLMemento memento = XMLMemento.createWriteRoot(ELEMENT_ROOT);
			for (Iterator it = _favorites.keySet().iterator(); it.hasNext();) {
				String favName = (String) it.next();
				Map props = new HashMap((Properties) _favorites.get(favName));

				IMemento config = memento.createChild(ELEMENT_CONFIG);
				config.putString(ELEMENT_NAME, favName);

				for (Iterator it1 = props.keySet().iterator(); it1.hasNext();) {
					IMemento entry = config.createChild(ELEMENT_PROPERTY);
					String name = (String) it1.next();
					entry.putString(ELEMENT_NAME, name);
					entry.putString(ELEMENT_VALUE, (String) props.get(name));
				}
			}
			fw = new FileWriter(_favoriteFile.toFile());
			memento.save(fw);
		} catch (Exception ee) {
			// log.FavoriteConfigurations.save.error=Failed to save {0}
			// favorites. File={1}
			_log.info("log.FavoriteConfigurations.save.error", _favoriteName, //$NON-NLS-1$
					_favoriteFile.toOSString(), ee);
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException ee)// NOPMD
				{
					// nothing to do when IOException throwed in closing files.
				}
			}
		}
	}

	/**
	 * Add the favorite into the internal structure but don't save until
	 * saveFavorites() is called.
	 * 
	 * @param name
	 * @param config
	 */
	public void addFavorite(String name, Properties config) {
		_favorites.put(name, config);
	}

	/**
	 * Removes the Favorite that is mapped under the given name.
	 * 
	 * @param name
	 *            the name of the configuration
	 */
	public void removeFavorite(String name) {
		_favorites.remove(name);
	}

	/**
	 * Read the favorites from the disk into the memory structure.
	 */
	protected void readFavorites() {
		_favorites = new HashMap();
		FileReader fr = null;
		try {
			try {
				fr = new FileReader(_favoriteFile.toFile());
			} catch (FileNotFoundException ee)// NOPMD
			{
				// no error here since they don't have to have a favorites...
			}
			if (fr != null) {
				XMLMemento memento = XMLMemento.createReadRoot(fr);
				IMemento[] children = memento.getChildren(ELEMENT_CONFIG);
				for (int ii = 0; ii < children.length; ii++) {
					Properties props = new Properties();
					IMemento config = children[ii];
					String name = config.getString(ELEMENT_NAME);

					IMemento[] configProperties = config
							.getChildren(ELEMENT_PROPERTY);
					for (int jj = 0; jj < configProperties.length; jj++) {
						IMemento entry = configProperties[jj];
						setProperty(props, entry.getString(ELEMENT_NAME), entry
								.getString(ELEMENT_VALUE));
					}
					_favorites.put(name, props);
				}
			}
		} catch (Exception ee) {
			// log.FavoriteConfigurations.read.error=Failed to save {0}
			// favorites. File={1}
			_log.error("log.FavoriteConfigurations.read.error", _favoriteName, //$NON-NLS-1$
					_favoriteFile.toOSString(), ee);
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException ee)// NOPMD
				{
					// nothing to do when IOException throwed in closing files.
				}
			}
		}
	}

	/**
	 * Set the value in the properties object.
	 * 
	 * @param props
	 * @param name
	 * @param value
	 */
	private void setProperty(Properties props, String name, String value) {
		if (name != null && value != null) {
			props.setProperty(name, value);
		}
	}

	/**
	 * Get a list of all the favorites stored in this file.
	 * 
	 * @return list of favorites
	 */
	public String[] getFavoritesList() {
		Object[] objs = _favorites.keySet().toArray();
		String[] names = new String[objs.length];

		for (int ii = 0; ii < objs.length; ii++) {
			names[ii] = (String) objs[ii];
		}
		Arrays.sort(names);

		return names;
	}

	/**
	 * scan the file name for any bad character that would fail when trying to
	 * create the file. We replace bad characters with '_'
	 */
	private static String scanFileName(String filename) {
		StringBuffer strBuf = new StringBuffer();
		char[] chars = filename.toCharArray();

		for (int ii = 0; ii < chars.length; ii++) {
			switch (chars[ii]) {
			case '&':
			case '\\':
			case '/':
			case ' ':
			case '\t':
			case ':':
			case '.':
			case '\"':
			case '\'':
			case '@':
				strBuf.append('_');
				break;
			default:
				strBuf.append(chars[ii]);
				break;
			}
		}

		return strBuf.toString();
	}
}
