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
import org.eclipse.core.resources.IProject;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.ui.palette.PaletteCustomizer;
import org.eclipse.gef.ui.palette.customize.PaletteSeparatorFactory;
import org.eclipse.gef.ui.palette.customize.PaletteStackFactory;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteItemManager;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;

/**
 * @author mengbo
 */
public class DesignerPaletteCustomizer extends PaletteCustomizer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.palette.PaletteCustomizer#canMoveDown(org.eclipse.gef.palette.PaletteEntry)
	 */
	public boolean canMoveDown(PaletteEntry entry) {
		if (!(entry instanceof PaletteDrawer)) {
			return false;
		}
		return super.canMoveDown(entry);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.palette.PaletteCustomizer#canMoveUp(org.eclipse.gef.palette.PaletteEntry)
	 */
	public boolean canMoveUp(PaletteEntry entry) {
		if (!(entry instanceof PaletteDrawer)) {
			return false;
		}
		if (entry.getParent().getChildren().indexOf(entry) == 1) {
			return false;
		}
		return super.canMoveUp(entry);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.palette.PaletteCustomizer#performMoveDown(org.eclipse.gef.palette.PaletteEntry)
	 */
	public void performMoveDown(PaletteEntry entry) {
		if (entry instanceof PaletteDrawer) {
			String id = entry.getId();
			IPaletteItemCategory cat = PaletteItemManager.getInstance(
					getCurrentProject()).getCategoryByURI(id);
			PaletteItemManager.getInstance(getCurrentProject()).movedown(cat);
			super.performMoveDown(entry);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.palette.PaletteCustomizer#performMoveUp(org.eclipse.gef.palette.PaletteEntry)
	 */
	public void performMoveUp(PaletteEntry entry) {
		if (entry instanceof PaletteDrawer) {
			String id = entry.getId();
			IPaletteItemCategory cat = PaletteItemManager.getInstance(
					getCurrentProject()).getCategoryByURI(id);
			PaletteItemManager.getInstance(getCurrentProject()).moveup(cat);
			super.performMoveUp(entry);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.palette.PaletteCustomizer#canDelete(org.eclipse.gef.palette.PaletteEntry)
	 */
	public boolean canDelete(PaletteEntry entry) {
		return false;
	}

	public List getNewEntryFactories() {
		List list = new ArrayList(4);
		list.add(new PaletteSeparatorFactory());
		list.add(new PaletteStackFactory());
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.palette.PaletteCustomizer#revertToSaved()
	 */
	public void revertToSaved() {
		// PaletteItemManager.getInstance(getCurrentProject()).
		PaletteItemManager.getInstance(getCurrentProject()).reset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.palette.PaletteCustomizer#save()
	 */
	public void save() {
		PaletteItemManager.getInstance(getCurrentProject()).save();
		PaletteItemManager.getInstance(getCurrentProject()).reset();
	}

	private IProject getCurrentProject() {
		IProject curProject = null;
		IEditorPart editor = PDPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		IEditorInput input = editor.getEditorInput();
		IFile inputFile = null;
		if (input instanceof IFileEditorInput) {
			inputFile = ((IFileEditorInput) input).getFile();
			curProject = inputFile.getProject();
		}
		return curProject;
	}

}
