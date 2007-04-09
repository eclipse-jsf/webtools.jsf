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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.PaletteCustomizer;
import org.eclipse.gef.ui.palette.customize.PaletteCustomizationAction;
import org.eclipse.gef.ui.palette.customize.PaletteCustomizerDialog;
import org.eclipse.jface.action.Action;
import org.eclipse.jst.jsf.common.ui.internal.utils.PluginImageHelper;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.editors.pagedesigner.PageDesignerResources;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteItemManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * @author mengbo
 * @version 1.5
 */
public class DesignerPaletteCustomizerDialog extends PaletteCustomizerDialog {
	private static String DEFAULTEXTENSION = ".xml";//$NON-NLS-1$ 

	public DesignerPaletteCustomizerDialog(Shell shell,
			PaletteCustomizer customizer, PaletteRoot root) {
		super(shell, customizer, root);

	}

	private PropertyChangeListener applyButtonUpdater = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			Button applyButton = getButton(PaletteCustomizerDialog.APPLY_ID);
			if (applyButton == null) {
				return;
			}
			applyButton.setEnabled(true);
		}
	};

	public int open() {
		// save the current state before open
		// save();

		return super.open();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.palette.customize.PaletteCustomizerDialog#setActiveEntry(org.eclipse.gef.palette.PaletteEntry)
	 */
	protected void setActiveEntry(PaletteEntry entry) {
		PaletteEntry pre = getSelectedPaletteEntry();
		if (pre != null) {
			pre.removePropertyChangeListener(applyButtonUpdater);
		}
		entry.addPropertyChangeListener(applyButtonUpdater);
		super.setActiveEntry(entry);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.palette.customize.PaletteCustomizerDialog#save()
	 */
	protected void save() {
		super.save();
		Button applyButton = getButton(PaletteCustomizerDialog.APPLY_ID);
		if (applyButton != null) {
			applyButton.setEnabled(false);
		}
	}

	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		// save();
	}

	private class ExportAction extends PaletteCustomizationAction {
		public ExportAction() {
			setEnabled(true);
			setText(PageDesignerResources.getInstance().getString(
					"DesignerPaletteCustomizerDialog.label.export")); //$NON-NLS-1$
			setImageDescriptor(PluginImageHelper.getInstance()
					.getImageDescriptor(DesignerPaletteImages.IMG_ELCL_EXPORT,
							PDPlugin.getPluginId()));
			setDisabledImageDescriptor(PluginImageHelper.getInstance()
					.getImageDescriptor(DesignerPaletteImages.IMG_DLCL_EXPORT,
							PDPlugin.getPluginId()));
			setHoverImageDescriptor(PluginImageHelper.getInstance()
					.getImageDescriptor(DesignerPaletteImages.IMG_CLCL_EXPORT,
							PDPlugin.getPluginId()));
		}

		protected void handleExport() {
			final FileDialog fileDialog = new FileDialog(PDPlugin
					.getActiveWorkbenchShell());
			fileDialog.setFileName("tag.xml"); //$NON-NLS-1$
			String[] filterExtensions = new String[2];
			filterExtensions[0] = "*.xml"; //$NON-NLS-1$
			filterExtensions[1] = "*.*"; //$NON-NLS-1$
			fileDialog.setFilterExtensions(filterExtensions);
			String filename = fileDialog.open();
			if (filename != null) {
				if (!filename.endsWith(DEFAULTEXTENSION)) {
					filename = filename + DEFAULTEXTENSION;
				}
				PaletteItemManager.getInstance(PDPlugin.getCurrentProject())
						.setFilename(filename);
				PaletteItemManager.getInstance(PDPlugin.getCurrentProject())
						.save();
				PaletteItemManager.getInstance(PDPlugin.getCurrentProject())
						.setFilename(null);
				updateActions();
			}
		}

		public void run() {
			handleExport();
		}

		public void update() {
			//boolean enabled = false;
			PaletteEntry entry = getSelectedPaletteEntry();
			if (entry != null) {
				// if (getCustomizer() instanceof DesignerPaletteCustomizer)
				// enabled = ((DesignerPaletteCustomizer)
				// getCustomizer()).canExport(entry);
			}
			setEnabled(true);
		}

	}

	private class ImportAction extends PaletteCustomizationAction {
		public ImportAction() {
			setEnabled(true);
			setText(PageDesignerResources.getInstance().getString(
					"DesignerPaletteCustomizerDialog.label.import")); //$NON-NLS-1$
			setImageDescriptor(PluginImageHelper.getInstance()
					.getImageDescriptor(DesignerPaletteImages.IMG_ELCL_IMPORT,
							PDPlugin.getPluginId()));
			setDisabledImageDescriptor(PluginImageHelper.getInstance()
					.getImageDescriptor(DesignerPaletteImages.IMG_DLCL_IMPORT,
							PDPlugin.getPluginId()));
			setHoverImageDescriptor(PluginImageHelper.getInstance()
					.getImageDescriptor(DesignerPaletteImages.IMG_CLCL_IMPORT,
							PDPlugin.getPluginId()));
		}

		protected void handleImport() {
			final FileDialog fileDialog = new FileDialog(PDPlugin
					.getActiveWorkbenchShell());
			fileDialog.setFileName("tag.xml"); //$NON-NLS-1$
			String[] filterExtensions = new String[2];
			filterExtensions[0] = "*.xml"; //$NON-NLS-1$
			filterExtensions[1] = "*.*"; //$NON-NLS-1$
			fileDialog.setFilterExtensions(filterExtensions);
			String filename = fileDialog.open();
			PaletteItemManager.getInstance(PDPlugin.getCurrentProject())
					.setFilename(filename);
			PaletteItemManager.getInstance(PDPlugin.getCurrentProject())
					.reset();
			PaletteItemManager.getInstance(PDPlugin.getCurrentProject())
					.setFilename(null);
		}

		public void run() {
			handleImport();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.gef.ui.palette.customize.PaletteCustomizationAction#update()
		 */
		public void update() {
			// no update requirements.
		}
	}

	public Action getImportAction() {
		return new ImportAction();
	}

	public Action getExportAction() {
		return new ExportAction();
	}

	protected List createOutlineActions() {
		List actions = super.createOutlineActions();
		actions.remove(0);// remove new action
		actions.remove(0);// remove delete action
		actions.add(new ImportAction());
		actions.add(new ExportAction());

		return actions;
	}
}
