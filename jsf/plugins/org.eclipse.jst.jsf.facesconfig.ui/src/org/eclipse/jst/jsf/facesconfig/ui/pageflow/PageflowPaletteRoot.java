/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.pageflow;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.EditorResources;
import org.eclipse.jst.jsf.facesconfig.ui.IconResources;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.ModelCreationFactory;

/**
 * This is the root of the palette used by pageflow editors.
 */
public class PageflowPaletteRoot extends PaletteRoot {

	/**
	 * Creates a new PageflowPaletteRoot instance.
	 */
	public PageflowPaletteRoot() {
		// create root
		super();

		// a group of default control tools
		// Pageflow.PaletteTool.Group.Controls = Controls
		PaletteGroup controls = new PaletteGroup(EditorResources.getInstance()
				.getString("Pageflow.PaletteTool.Group.Controls")); //$NON-NLS-1$
		add(controls);

		// the selection tool
		// Pageflow.PaletteTool.Select.Label = Select
		// Pageflow.PaletteTool.Select.Description = Select one or more items
		ToolEntry tool = new SelectionToolEntry(EditorResources.getInstance()
				.getString("Pageflow.PaletteTool.Select.Label"), //$NON-NLS-1$
				EditorResources.getInstance().getString(
						"Pageflow.PaletteTool.Select.Description")); //$NON-NLS-1$

		controls.add(tool);

		// use selection tool as default entry
		setDefaultEntry(tool);

		// the marquee selection tool
		// Pageflow.PaletteTool.Marquee.Label = Marquee
		// Pageflow.PaletteTool.Marquee.Description = Marquee one or more items
		controls.add(new MarqueeToolEntry(EditorResources.getInstance()
				.getString("Pageflow.PaletteTool.Marquee.Label"), //$NON-NLS-1$
				EditorResources.getInstance().getString(
						"Pageflow.PaletteTool.Marquee.Description"))); //$NON-NLS-1$

		controls.add(new PaletteSeparator());

		// conection creation
		// Pageflow.PaletteTool.Link.Label = Link
		// Pageflow.PaletteTool.Link.Description = Creates an link
		ConnectionCreationToolEntry linkEntry = new ConnectionCreationToolEntry(
				EditorResources.getInstance().getString(
						"Pageflow.PaletteTool.Link.Label"), //$NON-NLS-1$
				EditorResources.getInstance().getString(
						"Pageflow.PaletteTool.Link.Description"), //$NON-NLS-1$
				new ModelCreationFactory(PFLink.class),
				EditorPlugin.getDefault().getImageDescriptor(
						IconResources.getString("Pageflow.link.small")), //$NON-NLS-1$
				EditorPlugin.getDefault().getImageDescriptor(
						IconResources.getString("Pageflow.link.mid"))); //$NON-NLS-1$
		linkEntry.setUserModificationPermission(PERMISSION_FULL_MODIFICATION);
		controls.add(linkEntry);

		controls.add(new PaletteSeparator());

		// Pageflow.PaletteTool.Group.Nodes = Nodes
		PaletteDrawer nodes = new PaletteDrawer(EditorResources.getInstance()
				.getString("Pageflow.PaletteTool.Group.Nodes")); //$NON-NLS-1$
		add(nodes);

		// use CombinedTemplateCreationEntry which can be also dragged
		CombinedTemplateCreationEntry entry;

		// Pageflow.PaletteTool.Page.Label = Page
		// Pageflow.PaletteTool.Page.Description = Creates a pageflow page
		entry = new CombinedTemplateCreationEntry(
				EditorResources.getInstance().getString(
						"Pageflow.PaletteTool.Page.Label"), //$NON-NLS-1$
				EditorResources.getInstance().getString(
						"Pageflow.PaletteTool.Page.Description"), //$NON-NLS-1$
				PFPage.class, new ModelCreationFactory(PFPage.class),
				EditorPlugin.getDefault().getImageDescriptor(
						IconResources.getString("Pageflow.page.small")), //$NON-NLS-1$
				EditorPlugin.getDefault().getImageDescriptor(
						IconResources.getString("Pageflow.page.mid"))); //$NON-NLS-1$
		nodes.add(entry);
	}
}
