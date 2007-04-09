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
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jst.pagedesigner.editors.pagedesigner.PageDesignerResources;
import org.eclipse.jst.pagedesigner.tools.RangeSelectionTool;

/**
 * @author mengbo
 */
public class DesignerPaletteRoot extends PaletteRoot {
	private IPaletteItemManager _manager;

	// FIXME: unused
//	private final static ImageDescriptor DEFAULT_SMALL_ICON = PDPlugin
//			.getDefault().getImageDescriptor(
//					"palette/GENERIC/small/PD_Palette_Default.gif");
//
//	private final static ImageDescriptor DEFAULT_LARGE_ICON = PDPlugin
//			.getDefault().getImageDescriptor(
//					"palette/GENERIC/large/PD_Palette_Default.gif");

    // how many characters to truncate a palette item's description to.
    // TODO: add preference?
    // the soft length is the ideal length we try to truncate to. We first
    // try to find a period (end of sentence; TODO: should have a character class)
    // inside the first SOFT_LENGTH chars at which to truncate a description string.
    // if we can't find one then we search for the first one between SOFT_LENGTH
    // and min(HARD_LENGTH, str.length()).  If found, we truncate there.  If not,
    // we truncate to HARD_LENGTH-" ...".length() and append the ellipsis.
    // In all cases the truncated description string returned should <= HARD_LENGTH.
// FIXME: unused    private final static int  DESCRIPTION_TRUNCATE_SOFT_LENGTH = 150;
//    private final static int  DESCRIPTION_TRUNCATE_HARD_LENGTH = 250;
    
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

		// FIXME: unused PaletteEntry entry;

		List categories = _manager.getAllCategories();
		// FIXME: unused List drawers = new ArrayList(categories.size());
		this.addAll(categories);
	}

// FIXME: unused
//	private String formatDescription(String desc) {
//        // first, truncate the string
//        if (desc.length() > DESCRIPTION_TRUNCATE_SOFT_LENGTH)
//        {
//            // find the closest period to the soft length but before the hard length
//            // TODO: note that this is not localizable
//            String softDesc = desc.substring(0, DESCRIPTION_TRUNCATE_SOFT_LENGTH);
//            String ellipsis = "";
//            int truncPos = softDesc.lastIndexOf('.');
//            if (truncPos<0)
//            {
//                int endPos = Math.min(DESCRIPTION_TRUNCATE_HARD_LENGTH, desc.length()-1);
//                String fuzzyString = desc.substring(DESCRIPTION_TRUNCATE_SOFT_LENGTH, endPos);
//                truncPos = fuzzyString.lastIndexOf('.');
//                if (truncPos<0)
//                {
//                    ellipsis = " ...";
//                    truncPos = endPos-ellipsis.length();
//                }
//            }
//
//            desc = desc.substring(0, truncPos+1);
//            desc+=ellipsis;
//        }
//        StringBuffer result = new StringBuffer();
//        
//		if (desc.indexOf("\n") != -1) {
//			String[] fragments = desc.split("\n");
//			
//			for (int i = 0, n = fragments.length; i < n; i++) {
//				result.append(fragments[i].trim()).append(' ');
//			}
//		}
//        
//		return result.toString().trim();
//	}

	/**
	 * @return FIXME: unused
	 */
//	private ImageDescriptor getDefaultLargeIcon() {
//		return DEFAULT_LARGE_ICON;
//	}

	/**
	 * @return FIXME: unused
	 */
//	private ImageDescriptor getDefaultSmallIcon() {
//		return DEFAULT_SMALL_ICON;
//	}

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
