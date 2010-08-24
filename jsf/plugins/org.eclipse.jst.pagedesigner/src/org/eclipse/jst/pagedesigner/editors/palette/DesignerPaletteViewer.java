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

import java.util.List;

import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.PaletteCustomizer;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.customize.PaletteCustomizerDialog;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteItemManager;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * XXX: currently create this class is just for add/remove listener to
 * preference change, so can refresh the palette.
 * 
 * @author mengbo
 * @author mengbo
 * @version 1.5
 */
public class DesignerPaletteViewer extends PaletteViewer {

	private PaletteCustomizerDialog _customizerDialog = null;

	private DesignerPaletteCustomizer _customizer;

	/**
	 * 
	 */
	public DesignerPaletteViewer() {
		super();

		this.enableVerticalScrollbar(true);
	}

//	Preferences.IPropertyChangeListener listener = new Preferences.IPropertyChangeListener() {
//		public void propertyChange(PropertyChangeEvent event) {
//			PaletteRoot root = getPaletteRoot();
//			if (root instanceof DesignerPaletteRoot) {
////				((DesignerPaletteRoot) root).refresh();
//				// XXX: setActiveTool to null to workaround GEF bug of NPE
//				// setActiveTool(null);
//			}
//		}
//	};
	
	final IEntryChangeListener _paletteModelListener = new IEntryChangeListener() {

		public void modelChanged(List oldDefinitions, List newDefinitions) {
			final PaletteRoot root = getPaletteRoot();
			if (root instanceof DesignerPaletteRoot) {
                Control viewerControl = getControl();
                
                if (viewerControl != null && !viewerControl.isDisposed())
                {
                    Display  display = viewerControl.getDisplay();
                    
                    if (display != null && !display.isDisposed())
                    {
                        // this updates the UI, so it must be run on
                        // the display thread
                        display.asyncExec(new Runnable()
                        {
                            public void run() {
                                ((DesignerPaletteRoot) root).refresh();
                            }
                        });
                    }
                }
			}
		}

	};


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.palette.PaletteViewer#hookControl()
	 */
	protected void hookControl() {
		super.hookControl();
//		PDPlugin.getDefault().getPluginPreferences().addPropertyChangeListener(
//				listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.palette.PaletteViewer#unhookControl()
	 */
	protected void unhookControl() {
//		PDPlugin.getDefault().getPluginPreferences()
//				.removePropertyChangeListener(listener);
		// remove palette model change listener
		PaletteRoot root = getPaletteRoot();
		if (root instanceof DesignerPaletteRoot) {
			if (_customizer != null){
				_customizer.setPaletteRoot(null);
			}
			IPaletteItemManager imanager = ((DesignerPaletteRoot) root)
					.getPaletteManager();
			if (imanager instanceof PaletteItemManager) {
				PaletteItemManager manager = (PaletteItemManager) imanager;
//				ResourcesPlugin.getWorkspace().removeResourceChangeListener(
//						_resourceChangeListener);
				manager.removeEntryChangeListener(_paletteModelListener);				
//				PaletteItemManager.clearPaletteItemManager();
				manager.release(((DesignerPaletteRoot)root).getPaletteContext());
			}
		}
		super.unhookControl();
	}

	public PaletteCustomizerDialog getCustomizerDialog() {
		if (_customizerDialog == null){
			_customizerDialog = DesignerPaletteCustomizationsHelper.getNewCustomizerDialog(this, getCustomizer() );	
		}
		return _customizerDialog;
	}

	public PaletteCustomizer getCustomizer() {
		if (_customizer == null){
			_customizer = DesignerPaletteCustomizationsHelper.getNewCustomizer();
			_customizer.setPaletteRoot((DesignerPaletteRoot)this.getPaletteRoot());
		}
		return _customizer;
	}
	
	public void setPaletteRoot(PaletteRoot root) {
		super.setPaletteRoot(root);
		// add palette model change listener
		// PaletteRoot root = getPaletteRoot();
		if (root instanceof DesignerPaletteRoot && ((DesignerPaletteRoot) root).getPaletteManager() != null) {
			((DesignerPaletteRoot) root).getPaletteManager()
					.addEntryChangeListener(_paletteModelListener);
		}

	}
}
