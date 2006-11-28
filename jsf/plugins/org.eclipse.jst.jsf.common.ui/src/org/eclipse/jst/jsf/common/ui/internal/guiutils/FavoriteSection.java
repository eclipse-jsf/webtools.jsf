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
package org.eclipse.jst.jsf.common.ui.internal.guiutils;

import java.util.Properties;

import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.utils.FavoriteConfigurations;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Builds a favorites with the given name in the plugins resources. This build a
 * generic looking favorites section.
 * 
 * @author mengbo
 */
public abstract class FavoriteSection {
	private FavoriteConfigurations _favorites;

	private Combo _favoriteCombo;

	public FavoriteSection(AbstractUIPlugin plugin, String favoriteName) {
		_favorites = new FavoriteConfigurations(plugin, favoriteName);
	}

	public Combo createFavoriteGroup(Composite parent, int horizontalSpan,
			int comboMinWidth) {
		Group favGroup = SWTUtils.createGroup(parent, JSFUICommonPlugin
				.getResourceString("favorites.group.label"), 4, horizontalSpan,
				GridData.BEGINNING | GridData.CENTER);

		// Add favorite text field
		SWTUtils.createLabel(favGroup, JSFUICommonPlugin
				.getResourceString("favorites.label"), 1);
		_favoriteCombo = SWTUtils.createCombo(favGroup, _favorites
				.getFavoritesList(), 1, comboMinWidth, true);
		_favoriteCombo.setToolTipText(JSFUICommonPlugin
				.getResourceString("favorites.tooltip"));
		_favoriteCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				String favName = _favoriteCombo.getText().trim();
				if (favName.length() > 0) {
					Properties favProp = _favorites.getFavorite(_favoriteCombo
							.getText().trim());
					if (favProp != null) {
						setFavorites(favProp);
					}
				}
			}
		});

		// Add Save button
		Button saveButton = SWTUtils.createPushButton(favGroup, JSFUICommonPlugin
				.getResourceString("button.save"));
		saveButton.setToolTipText(JSFUICommonPlugin
				.getResourceString("favorites.save.tooltip"));
		saveButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				String favName = _favoriteCombo.getText().trim();
				if (favName.length() == 0) {
					favName = JSFUICommonPlugin
							.getResourceString("favorites.default");
				}
				Properties favProp = new Properties();
				saveFavorites(favProp);

				_favorites.addFavorite(favName, favProp);
				_favorites.saveFavorites();

				_favoriteCombo.removeAll();
				String[] names = _favorites.getFavoritesList();

				_favoriteCombo.setItems(names);
				_favoriteCombo.setText(favName);
			}
		});

		// Add Remove button
		Button removeButton = SWTUtils.createPushButton(favGroup, JSFUICommonPlugin
				.getResourceString("button.remove"));
		removeButton.setToolTipText(JSFUICommonPlugin
				.getResourceString("favorites.remove.tooltip"));
		removeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				String favName = _favoriteCombo.getText().trim();
				if (favName.length() > 0) {
					_favorites.removeFavorite(favName);
					_favorites.saveFavorites();

					removeFavorites();

					_favoriteCombo.removeAll();
					String[] names = _favorites.getFavoritesList();
					_favoriteCombo.setItems(names);
				}
			}
		});

		return _favoriteCombo;
	}

	/**
	 * This method will be called when a new favorite is set. This allows the
	 * caller to set their values from the properties supplied.
	 * 
	 * @param prop
	 */
	public abstract void setFavorites(Properties prop);

	/**
	 * This method will be called when a new favorite is saved. This allows the
	 * caller to save their values into the properties supplied.
	 * 
	 * @param prop
	 */
	public abstract void saveFavorites(Properties prop);

	/**
	 * This method will be called when the active favorite is removed. It allows
	 * for clearing fields.
	 */
	public void removeFavorites() {
		// do nothing.
	}

}
