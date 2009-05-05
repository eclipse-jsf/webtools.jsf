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

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.internal.ui.palette.editparts.DrawerEditPart;
import org.eclipse.gef.ui.palette.PaletteContextMenuProvider;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.editors.palette.impl.TaglibPaletteDrawer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

/**
 * @author mengbo
 */
public class DesignerPaletteViewerProvider extends PaletteViewerProvider {
	/**
	 * @param graphicalViewerDomain
	 */
	public DesignerPaletteViewerProvider(EditDomain graphicalViewerDomain) {
		super(graphicalViewerDomain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.palette.PaletteViewerProvider#configurePaletteViewer(org.eclipse.gef.ui.palette.PaletteViewer)
	 */
	protected void configurePaletteViewer(PaletteViewer viewer) {
		// super.configurePaletteViewer(viewer);
		viewer.setContextMenu(new PaletteContextMenuProvider(viewer) {
			public void buildContextMenu(IMenuManager menu) {
				StructuredSelection sel = (StructuredSelection)getViewer().getSelection();
				if (sel != null 
						&& sel.getFirstElement() instanceof DrawerEditPart
						&& ((DrawerEditPart)sel.getFirstElement()).getDrawer() instanceof TaglibPaletteDrawer)
					menu.add(new HideTagLibAction((DrawerEditPart)sel.getFirstElement(), Messages.DesignerPaletteViewerProvider_Hide));
				super.buildContextMenu(menu);
			}
		});

		// XXX: should only use the following when we use Template
		viewer
				.addDragSourceListener(new TemplateTransferDragSourceListener(
						viewer));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.palette.PaletteViewerProvider#createPaletteViewer(org.eclipse.swt.widgets.Composite)
	 */
	public PaletteViewer createPaletteViewer(Composite parent) {
		PaletteViewer pViewer = new DesignerPaletteViewer();
		pViewer.createControl(parent);
		configurePaletteViewer(pViewer);
		pViewer.setCustomizer(new DesignerPaletteCustomizer());
		hookPaletteViewer(pViewer);

		PlatformUI
				.getWorkbench()
				.getHelpSystem()
				.setHelp(
						parent,
						PDPlugin
								.getResourceString("DesignerPaletteViewerProvider.help.id")); //$NON-NLS-1$

		return pViewer;
	}
	
	private static class HideTagLibAction extends Action {
		private DrawerEditPart tagLib;
		
		/**
		 * Constructor
		 * @param tagLibDrawer
		 * @param string
		 */
		public HideTagLibAction(DrawerEditPart tagLibDrawer, String string) {
			super(string);
			this.tagLib = tagLibDrawer;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.action.Action#run()
		 */
		public void run() {
			TaglibPaletteDrawer pd = (TaglibPaletteDrawer)tagLib.getDrawer();
			pd.setVisible(false);
			DesignerPaletteCustomizationsHelper.hideTaglibDrawer(pd);
		}		
		
	}
}
