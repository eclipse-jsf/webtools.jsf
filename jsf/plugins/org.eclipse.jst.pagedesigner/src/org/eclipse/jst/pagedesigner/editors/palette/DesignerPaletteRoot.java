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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jst.pagedesigner.editors.pagedesigner.PageDesignerResources;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteItemManager;
import org.eclipse.jst.pagedesigner.tools.RangeSelectionTool;

/**
 * @author mengbo
 */
public class DesignerPaletteRoot extends PaletteRoot {
	private IPaletteItemManager _manager;
	private IFile _file;
	private IPaletteContext _paletteContext;
    
	/**
	 * Creates a new DesignerPaletteRoot instance.
	 * @param file 
	 */
	public DesignerPaletteRoot(final IFile file) {
		// create root
		super();
		
		this._paletteContext = PaletteItemManager.createPaletteContext(file);
		this._manager = PaletteItemManager.getInstance(_paletteContext);
		if (this._manager != null) {
			setupBasicItems();
			loadItems();
		}
		// TODO: register listener on the manager for toolpalette change event.

	}

	/**
	 * @return the paletteContext 
	 */
	public IPaletteContext getPaletteContext() {
		return _paletteContext;
	}
	
	/**
	 * @return the paletteContext 
	 */
	public IFile getFile() {
		return _file;
	}
	/**
	 * @return IPaletteItemManager instance for this root
	 */
	public IPaletteItemManager getPaletteManager() {
		return this._manager;
	}

	private void setupBasicItems() {
		// Preferences prefs = PDPlugin.getDefault().getPluginPreferences();
		// _showAll = prefs.getBoolean(IJMTConstants.PREF_PALETTE_SHOW_ALL);

		// a group of default control tools
		// JSFPalette.DefaultGroup.LabelJSFPallete=Controls
		PaletteGroup controls = new PaletteGroup(PageDesignerResources
				.getInstance().getString(
						"JSFPalette.DefaultGroup.LabelJSFPallete")); //$NON-NLS-1$
		add(controls);
		// the selection tool
		ToolEntry tool = new SelectionToolEntry() {
			public Tool createTool() {
				return new RangeSelectionTool();
			}
		};
		controls.add(tool);

		// use selection tool as default entry
		setDefaultEntry(tool);

		// the marquee selection tool
		controls.add(new MarqueeToolEntry());
	}

	private void loadItems() {
		// _showAll =
		// PDPlugin.getDefault().getPluginPreferences().getBoolean(IJMTConstants.PREF_PALETTE_SHOW_ALL);
		// remove other things first.
		removeItems();
		
		if (_manager != null) {
			this.addAll(_manager.getAllCategories());
		};
	}

	/**
	 * remove everything from the paletteroot
	 * 
	 */
	protected void removeItems() {
		// we try to remove anything other than the basic
		// group that have the selectentry and marqeeentry
		List children1 = new ArrayList(getChildren());
		children1.remove(0); // remove the first one
		for (int i = 0, n = children1.size(); i < n; i++) {
			this.remove((PaletteEntry) children1.get(i));
		}
	}

	/**
	 * refresh the palette, normally caused by preference change.
	 */
	public void refresh() {
		loadItems();
	}
}
