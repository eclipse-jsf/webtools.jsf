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

import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.ui.palette.PaletteCustomizer;
import org.eclipse.gef.ui.palette.customize.PaletteSeparatorFactory;
import org.eclipse.gef.ui.palette.customize.PaletteStackFactory;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteItemManager;

/**
 * @author mengbo
 */
public class DesignerPaletteCustomizer extends PaletteCustomizer {

	
	private DesignerPaletteRoot root;

	/**
	 * Set the palette root.   Must be set before the customizer can be used
	 * @param root
	 */
	public void setPaletteRoot(DesignerPaletteRoot root){
		this.root = root;
	}
	
	/**
	 * Return the palette root for this customizer
	 * @return DesignerPaletteRoot
	 */
	public DesignerPaletteRoot getDesignerPaletteRoot(){
		return root;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.palette.PaletteCustomizer#canMoveDown(org.eclipse.gef.palette.PaletteEntry)
	 */
	public boolean canMoveDown(PaletteEntry entry) {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.palette.PaletteCustomizer#canMoveUp(org.eclipse.gef.palette.PaletteEntry)
	 */
	public boolean canMoveUp(PaletteEntry entry) {
		return false;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.palette.PaletteCustomizer#canMoveDown(org.eclipse.gef.palette.PaletteEntry)
	 */
//	public boolean canMoveDown(PaletteEntry entry) {
//		if (!(entry instanceof PaletteDrawer)) {
//			return false;
//		}
//		return super.canMoveDown(entry);
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.palette.PaletteCustomizer#canMoveUp(org.eclipse.gef.palette.PaletteEntry)
	 */
//	public boolean canMoveUp(PaletteEntry entry) {
//		if (!(entry instanceof PaletteDrawer)) {
//			return false;
//		}
//		if (entry.getParent().getChildren().indexOf(entry) == 1) {
//			return false;
//		}
//		return super.canMoveUp(entry);
//	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.eclipse.gef.ui.palette.PaletteCustomizer#performMoveDown(org.eclipse.gef.palette.PaletteEntry)
//	 */
//	public void performMoveDown(PaletteEntry entry) {
//		if (entry instanceof PaletteDrawer) {
//			String id = entry.getId();
//			TaglibPaletteDrawer cat = PaletteItemManager.getCurrentInstance().getTaglibPalletteDrawer(id);
//			movedown(PaletteItemManager.getCurrentInstance(), cat);
//			super.performMoveDown(entry);
//		}
//	}
//	private void moveup(PaletteItemManager paletteItemManager, TaglibPaletteDrawer cat) {
//		int i = paletteItemManager.getAllCategories().indexOf(cat);
//		TaglibPaletteDrawer upCat = (TaglibPaletteDrawer) paletteItemManager.getAllCategories()
//				.get(i - 1);
//		movedown(paletteItemManager, upCat);
//	}
//
//	private void movedown(PaletteItemManager paletteItemManager, TaglibPaletteDrawer cat) {
//		int i = paletteItemManager.getAllCategories().indexOf(cat);
//		paletteItemManager.getAllCategories().add(i + 2, cat);
//		paletteItemManager.getAllCategories().remove(i);
//	}
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.eclipse.gef.ui.palette.PaletteCustomizer#performMoveUp(org.eclipse.gef.palette.PaletteEntry)
//	 */
//	public void performMoveUp(PaletteEntry entry) {
//		if (entry instanceof PaletteDrawer) {
//			String id = entry.getId();
//			TaglibPaletteDrawer cat = PaletteItemManager.getCurrentInstance().getTaglibPalletteDrawer(id);
//			moveup(PaletteItemManager.getCurrentInstance(), cat);
//			super.performMoveUp(entry);
//		}
//	}

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
		//note that reset is not currently part of IPaletteItemManager interface...
		((PaletteItemManager)root.getPaletteManager()).reset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.palette.PaletteCustomizer#save()
	 */
	public void save() {
		DesignerPaletteCustomizationsHelper.save(root);
//		PaletteItemManager.getCurrentInstance().reset();
	}



}
