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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.ui.palette.PaletteCustomizer;
import org.eclipse.gef.ui.palette.customize.PaletteCustomizationAction;
import org.eclipse.gef.ui.palette.customize.PaletteCustomizerDialog;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.jsf.common.ui.internal.utils.PluginImageHelper;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.editors.pagedesigner.PageDesignerResources;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteItemManager;
import org.eclipse.jst.pagedesigner.editors.palette.impl.TaglibPaletteDrawer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * Customization dialog for Web Page Designer palette items
 * @author mengbo
 * @version 1.5
 */
public class DesignerPaletteCustomizerDialog extends PaletteCustomizerDialog {
	private static final String DEFAULT_CUSTOMIZATIONS_EXPORT_FILENAME = "WPDPaletteCustomizations.xml"; //$NON-NLS-1$
	
	private static String DEFAULTEXTENSION = ".xml";//$NON-NLS-1$ 

	/**
	 * Constructor
	 * @param shell
	 * @param customizer
	 * @param root
	 */
	public DesignerPaletteCustomizerDialog(Shell shell,
			PaletteCustomizer customizer, DesignerPaletteRoot root) {
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

	/* (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#open()
	 */
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
		//remove listener on previous entry before adding to new entry
		PaletteEntry pre = getSelectedPaletteEntry();
		if (pre != null) {
			pre.removePropertyChangeListener(applyButtonUpdater);
		} else {
			getButton(APPLY_ID).setEnabled(false);
		}
		
		if (entry != null)
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

	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.palette.customize.PaletteCustomizerDialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		getButton(APPLY_ID).setEnabled(false);
		// save();
	}

	/* (non-Javadoc)
	 * Export action
	 *
	 */
	private class ExportAction extends PaletteCustomizationAction {
		
		/**
		 * Constructor
		 */
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

		private void handleExport() {
			final FileDialog fileDialog = new FileDialog(PDPlugin
					.getActiveWorkbenchShell());
			fileDialog.setFileName(DEFAULT_CUSTOMIZATIONS_EXPORT_FILENAME);
			String[] filterExtensions = new String[2];
			filterExtensions[0] = "*.xml"; //$NON-NLS-1$
			filterExtensions[1] = "*.*"; //$NON-NLS-1$
			fileDialog.setFilterExtensions(filterExtensions);
			fileDialog.setText(Messages.DesignerPaletteCustomizerDialog_ExportCustomizations);
			String filename = fileDialog.open();
			if (filename != null) {
				if (!filename.endsWith(DEFAULTEXTENSION)) {
					filename = filename + DEFAULTEXTENSION;
				}
				final IPaletteContext context = PaletteItemManager.createPaletteContext(((DesignerPaletteRoot)getPaletteRoot()).getFile());
				DesignerPaletteCustomizationsHelper
					.exportCustomizations(PaletteItemManager.getInstance(context), filename);


				updateActions();
			}
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.action.Action#run()
		 */
		public void run() {
			handleExport();
		}

		/* (non-Javadoc)
		 * @see org.eclipse.gef.ui.palette.customize.PaletteCustomizationAction#update()
		 */
		public void update() {
			//boolean enabled = false;
//			PaletteEntry entry = getSelectedPaletteEntry();
//			if (entry != null) {
				// if (getCustomizer() instanceof DesignerPaletteCustomizer)
				// enabled = ((DesignerPaletteCustomizer)
				// getCustomizer()).canExport(entry);
//			}
			setEnabled(true);
		}

	}

	/* (non-Javadoc)
	 * Import action
	 */
	private class ImportAction extends PaletteCustomizationAction {
		private DesignerPaletteCustomizerDialog designerPaletteCustomizerDialog;
		
		/**
		 * Constructor
		 * @param designerPaletteCustomizerDialog
		 */
		public ImportAction(DesignerPaletteCustomizerDialog designerPaletteCustomizerDialog) {
			super();
			this.designerPaletteCustomizerDialog = designerPaletteCustomizerDialog;
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

		private void handleImport() {
//			PaletteEntry entry = designerPaletteCustomizerDialog.getSelectedPaletteEntry();
			final FileDialog fileDialog = new FileDialog(PDPlugin
					.getActiveWorkbenchShell());
			fileDialog.setFileName(DEFAULT_CUSTOMIZATIONS_EXPORT_FILENAME);
			String[] filterExtensions = new String[2];
			filterExtensions[0] = "*.xml"; //$NON-NLS-1$
			filterExtensions[1] = "*.*"; //$NON-NLS-1$
			fileDialog.setFilterExtensions(filterExtensions);
			fileDialog.setText(Messages.DesignerPaletteCustomizerDialog_ImportCustomizations);
			String filename = fileDialog.open();
			if (filename != null) {		
					//deselect current entry first
					designerPaletteCustomizerDialog.close();		
					
//					getCustomizer().revertToSaved();
					DesignerPaletteCustomizationsHelper
						.importCustomizations((DesignerPaletteCustomizer)getCustomizer(), filename);
					
//					designerPaletteCustomizerDialog.setActiveEntry(entry);
					((DesignerPaletteCustomizer)getCustomizer()).getDesignerPaletteRoot().refresh();
					designerPaletteCustomizerDialog.open();
//					designerPaletteCustomizerDialog.setActiveEntry(entry);
			}
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
			//nothing to do
		}
	}

	/**
	 * @return import action
	 */
	public Action getImportAction() {
		return new ImportAction(this);
	}

	/**
	 * @return export action
	 */
	public Action getExportAction() {
		return new ExportAction();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.palette.customize.PaletteCustomizerDialog#createOutlineActions()
	 */
	protected List createOutlineActions() {
		List actions = super.createOutlineActions();
		actions.remove(0);// remove new action
		actions.remove(0);// remove delete action
		actions.add(new ImportAction(this));
		actions.add(new ExportAction());

		return actions;
	}
	
	@Override
	protected TreeViewer createOutlineTreeViewer(Composite composite) {
		TreeViewer viewer = super.createOutlineTreeViewer(composite);
		viewer.setContentProvider(new DesignerPaletteContentProvider());
		return viewer;
	}

	
	/**
	 * Content provider for dialog.
	 * Displays only {@link TaglibPaletteDrawer}s
	 */
	private static class DesignerPaletteContentProvider implements ITreeContentProvider {

		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof DesignerPaletteRoot){
				List libs = new ArrayList();
				List children = ((DesignerPaletteRoot)parentElement).getChildren();				
				if (!children.isEmpty()) {
					for (Iterator it=children.iterator();it.hasNext();){
						PaletteEntry entry = (PaletteEntry)it.next();
						if (entry instanceof TaglibPaletteDrawer)
							libs.add(entry);
					}
					if (!libs.isEmpty())
						return libs.toArray();
				}
			}
			return null;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
		 */
		public Object getParent(Object element) {
			return ((PaletteEntry)element).getParent();
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
		 */
		public boolean hasChildren(Object element) {
			return false;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object inputElement) {
			Object[] elements = new Object[0];
			if (inputElement instanceof DesignerPaletteRoot){
				elements = getChildren(inputElement);
				if (elements == null) {
					elements = new Object[0];
				}
			}
			return elements;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
			//nothing to do
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			//won't change			
		}

		
	}
}
