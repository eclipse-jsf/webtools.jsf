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

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.editors.pagedesigner.PageDesignerResources;
import org.eclipse.jst.pagedesigner.itemcreation.ItemToolEntry;
import org.eclipse.jst.pagedesigner.tools.RangeSelectionTool;

/**
 * @author mengbo
 */
public class DesignerPaletteRoot extends PaletteRoot {
	private IPaletteItemManager _manager;

	private final static ImageDescriptor DEFAULT_SMALL_ICON = PDPlugin
			.getDefault().getImageDescriptor(
					"palette/GENERIC/small/PD_Palette_Default.gif");

	private final static ImageDescriptor DEFAULT_LARGE_ICON = PDPlugin
			.getDefault().getImageDescriptor(
					"palette/GENERIC/large/PD_Palette_Default.gif");

	// _showAll will be initialized in init()
	// private boolean _showAll;

	/**
	 * Creates a new JSFPaletteRoot instance.
	 */
	public DesignerPaletteRoot(IPaletteItemManager manager) {
		// create root
		super();

		this._manager = manager;
		setupBasicItems();
		loadItems();

		// TODO: register listener on the manager for toolpalette change event.

	}

	public IPaletteItemManager getPaletteManager() {
		return this._manager;
	}

	protected void setupBasicItems() {
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

	public void loadItems() {
		// _showAll =
		// PDPlugin.getDefault().getPluginPreferences().getBoolean(IJMTConstants.PREF_PALETTE_SHOW_ALL);
		// remove other things first.
		removeItems();

		PaletteEntry entry;

		List categories = _manager.getAllCategories();
		List drawers = new ArrayList(categories.size());
		for (int i = 0, m = categories.size(); i < m; i++) {
			IPaletteItemCategory cat = (IPaletteItemCategory) categories.get(i);
			PaletteDrawer drawer = new PaletteDrawer(cat.getLabel());
			cat.setPaletteEntry(drawer);
			drawer.setId(cat.getId());
			drawer.setLabel(cat.getLabel());
			drawer.setDescription(cat.getDescription());
			drawer.setVisible(cat.isVisible());
			drawer.setInitialState(cat.getInitialState());
			// if (i==categories.size()-1)
			// {
			// drawer.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
			// }
			// else
			// {
			// drawer.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
			// }
			List items = cat.getPaletteItems();
			for (int j = 0, n = items.size(); j < n; j++) {
				entry = createEntry((IPaletteItemDescriptor) items.get(j));
				if (entry != null) {
					drawer.add(entry);
				}
			}
			drawers.add(drawer);
		}
		// ok, add all the drawers at once
		this.addAll(drawers);
	}

	/**
	 * @param descriptor
	 * @return
	 */
	private PaletteEntry createEntry(IPaletteItemDescriptor descriptor) {
		// check expert flag.
		// if (descriptor.isVisible() && !_showAll)
		// return null;

		// XXX: it is possible to let descriptor to implement certain interface
		// to provide
		// special palette entry other than the default one.
		String label = descriptor.getLabel();
		if (label == null || label.length() == 0) {
			label = descriptor.getTagName();
		}
		String shortDesc = descriptor.getDescription();
		if (shortDesc == null || shortDesc.length() == 0) {
			shortDesc = label;
		}
		shortDesc = formatDescription(shortDesc);
		ImageDescriptor iconSmall = descriptor.getSmallIcon();
		if (iconSmall == null) {
			iconSmall = getDefaultSmallIcon();
		}
		ImageDescriptor iconLarge = descriptor.getLargeIcon();
		if (iconLarge == null) {
			iconLarge = getDefaultLargeIcon();
		}

		// TODO: suggested prefix is null now. We may also put that into
		// itemdescriptor
		// CreationFactory factory = new
		// NodeCreationFactory(descriptor.getURI(), descriptor.getTagName(),
		// null, descriptor.getInitialAttributes());

		// CreationToolEntry entry = new CreationToolEntry(label, shortDesc,
		// factory, iconSmall, iconLarge);
		ItemToolEntry entry = new ItemToolEntry(label, shortDesc, iconSmall,
				iconLarge, descriptor);
		entry.setId(descriptor.getId());

		boolean isVisible = descriptor.isVisible();
		entry.setVisible(isVisible);
		// if (_showAll)
		// {
		// entry.setVisible(true);
		// }
		// else
		// {
		// entry.setVisible(descriptor.isVisible());
		// }
		descriptor.setPaletteEntry(entry);
		return entry;
	}

	private String formatDescription(String desc) {
		if (desc.indexOf("\n") != -1) {
			String[] fragments = desc.split("\n");
			StringBuffer result = new StringBuffer();
			for (int i = 0, n = fragments.length; i < n; i++) {
				result.append(fragments[i].trim()).append(' ');
			}
			desc = result.toString().trim();
		}
		return desc;
	}

	/**
	 * @return
	 */
	private ImageDescriptor getDefaultLargeIcon() {
		return DEFAULT_LARGE_ICON;
	}

	/**
	 * @return
	 */
	private ImageDescriptor getDefaultSmallIcon() {
		return DEFAULT_SMALL_ICON;
	}

	/**
	 * remove everything from the paletteroot
	 * 
	 */
	protected void removeItems() {
		// we try to remove anything other than the basic
		// group that have the selectentry and marqeeentry
		List children = new ArrayList(getChildren());
		children.remove(0); // remove the first one
		for (int i = 0, n = children.size(); i < n; i++) {
			this.remove((PaletteEntry) children.get(i));
		}
	}

	/**
	 * refresh the palette, normally caused by preference change.
	 */
	public void refresh() {
		loadItems();
	}
}
