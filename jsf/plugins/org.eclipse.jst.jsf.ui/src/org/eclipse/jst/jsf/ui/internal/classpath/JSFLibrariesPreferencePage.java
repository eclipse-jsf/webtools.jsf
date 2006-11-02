/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.ui.internal.classpath;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary;
import org.eclipse.jst.jsf.ui.internal.JSFUiPlugin;
import org.eclipse.jst.jsf.ui.internal.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbenchWizard;

/**
 * Provides a preference page for JSF Libraries.
 * 
 * @author Gerry Kessler - Oracle
 */
public class JSFLibrariesPreferencePage extends PreferencePage implements IWorkbenchPreferencePage{
	private IWorkbench wb;

	private TreeViewer tv;
	private TreeViewerAdapter tvAdapter;
	private TreeLabelProvider tvLabelProvider;
	
	private Composite btnComp;

	private Button btnNew;
	private Button btnEdit;
	private Button btnDelete;
	private Button btnMakeDefaultImpl;
	
	protected Control createContents(Composite parent) {
		Composite c = new Composite(parent, SWT.NONE);
		c.setLayout(new GridLayout(2, false)); 
		c.setLayoutData(new GridData(GridData.FILL_BOTH));				
		
		Label lblLibs = new Label(c, SWT.NONE);
		lblLibs.setText(Messages.JSFLibrariesPreferencePage_DefinedJSFLibraries);
		GridData gd1 = new GridData();
		gd1.horizontalSpan = 2;
		lblLibs.setLayoutData(gd1);
		
		tv = new TreeViewer(c, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tvAdapter = new TreeViewerAdapter();
		tvLabelProvider = new TreeLabelProvider();
		tv.setContentProvider(tvAdapter);
		tv.setLabelProvider(tvLabelProvider);
		tv.addSelectionChangedListener(tvAdapter);
		tv.addDoubleClickListener(tvAdapter);
		tv.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		tv.setInput(getJSFLibraries());
		
		createButtons(c);
		
		return c;
	}

	private void createButtons(Composite c){		
		btnComp = new Composite(c, SWT.NONE);
		GridLayout gl1 = new GridLayout(1, false);
		gl1.marginHeight = 0;
		gl1.marginWidth = 0;
		btnComp.setLayout(gl1);
		btnComp.setLayoutData(new GridData(GridData.END | GridData.VERTICAL_ALIGN_FILL));
		
		btnNew = new Button(btnComp, SWT.NONE);
		btnNew.setText(Messages.JSFLibrariesPreferencePage_New);
		btnNew.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		btnNew.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				openJSFLibraryEditDialog(null);
			}
		});
		
		btnEdit = new Button(btnComp, SWT.NONE);
		btnEdit.setText(Messages.JSFLibrariesPreferencePage_Edit);
		btnEdit.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		btnEdit.setEnabled(false);
		btnEdit.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] element = tv.getTree().getSelection();
				if (element != null){
					openJSFLibraryEditDialog(element[0]);
				}

			}
		});
		
		btnDelete = new Button(btnComp, SWT.NONE);
		btnDelete.setText(Messages.JSFLibrariesPreferencePage_Remove);
		btnDelete.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING));
		btnDelete.setEnabled(false);
		btnDelete.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				boolean modified = false;
				if (tv.getSelection() instanceof StructuredSelection){
					StructuredSelection objs = (StructuredSelection)tv.getSelection();
					if (objs != null){
						Iterator it = objs.iterator();
						while (it.hasNext()){
							JSFLibrary lib = (JSFLibrary)it.next();
							if (lib instanceof PluginProvidedJSFLibrary)
								MessageDialog.openInformation(
										getShell(),
										Messages.JSFLibrariesPreferencePage_CannotRemovePluginProvidedTitle,
										Messages.JSFLibrariesPreferencePage_CannotRemovePluginProvidedMessage);
	
							else {
								JSFCorePlugin.getDefault().getJSFLibraryRegistry().removeJSFLibrary(lib);
								modified = true;
							}
						}
						if (modified){
							JSFCorePlugin.getDefault().saveJSFLibraryRegistry();
							tv.refresh();
						}
					}
				}
			}
		});
		
		btnMakeDefaultImpl = new Button(btnComp, SWT.NONE);
		btnMakeDefaultImpl.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_END));
		btnMakeDefaultImpl.setText(Messages.JSFLibrariesPreferencePage_MakeDefault);
		btnMakeDefaultImpl.setVisible(false);
		btnMakeDefaultImpl.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (tv.getSelection() instanceof StructuredSelection){
					StructuredSelection objs = (StructuredSelection)tv.getSelection();
					if (objs != null){
						if (objs.getFirstElement() instanceof JSFLibrary){
							 JSFLibrary lib = (JSFLibrary)objs.getFirstElement();
							 JSFCorePlugin.getDefault().getJSFLibraryRegistry().setDefaultImplementation(lib);							 							
						 }
						 JSFCorePlugin.getDefault().saveJSFLibraryRegistry();
						 tv.refresh();
					}
				}
			}
		});
		
	}
	private Object getJSFLibraries() {
		return JSFCorePlugin.getDefault().getJSFLibraryRegistry().getAllJSFLibraries();
	}

	public void init(IWorkbench workbench) {
		wb = workbench;
		setDescription(Messages.JSFLibrariesPreferencePage_Description);
		noDefaultAndApplyButton();
	}
	
	/**
	 * Getter created only for JUnit tests.  Should not be used otherwise.
	 * @return the TreeViewer of JSF Libraries
	 */
	public Viewer getLibraryViewer(){
		return tv;
	}
	
	private class TreeViewerAdapter implements ITreeContentProvider, ISelectionChangedListener, IDoubleClickListener {
		private final Object[] NO_ELEMENTS= new Object[0];

		// ------- ITreeContentProvider Interface ------------

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// will never happen
		}

		public boolean isDeleted(Object element) {
			return false;
		}

		public void dispose() {
            // do nothing
		}

		public Object[] getElements(Object obj) {
			return ((List)getJSFLibraries()).toArray();
		}
		
		public Object[] getChildren(Object element) {
			if (element instanceof JSFLibrary) {
				return ((JSFLibrary)element).getArchiveFiles().toArray();
			}
			return NO_ELEMENTS;
		}

		public Object getParent(Object element) {
//			if (elements instanceof JSFLibrary) {
//				return tvAdapter.getParent(tv.getTree().class, element);
//			}
			return null;//fParentElement;
		}

		public boolean hasChildren(Object element) {
			if (element instanceof JSFLibrary) {
				return true;
			}
			return false;
		}		

		// ------- ISelectionChangedListener Interface ------------

		public void selectionChanged(SelectionChangedEvent event) {
			doListSelected(event);
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
		 */
		public void doubleClick(DoubleClickEvent event) {
			doDoubleClick(event);
		}		
	}

	protected void doListSelected(SelectionChangedEvent event) {
		updateButtonState();
	}

	protected void doDoubleClick(DoubleClickEvent event) {
		openJSFLibraryEditDialog(tv.getTree().getSelection()[0]);
	}
	
	private void updateButtonState() {
		btnEdit.setEnabled(tv.getTree().getSelectionCount() == 1);	
		if (tv.getTree().getSelectionCount() == 1 && tv.getTree().getSelection()[0].getData() instanceof JSFLibrary){	
			btnDelete.setEnabled(true);
			btnMakeDefaultImpl.setVisible(false);
			JSFLibrary lib = (JSFLibrary)tv.getTree().getSelection()[0].getData();
			btnMakeDefaultImpl.setVisible(lib.isImplementation());
		} else {
			btnDelete.setEnabled(false);
			btnMakeDefaultImpl.setVisible(false);
		}
	}
	
	private void openJSFLibraryEditDialog(Object element) {
		if (isPluginProvidedJSFLibrary(element)){
			MessageDialog.openInformation(
					getShell(),
					Messages.JSFLibrariesPreferencePage_CannotModifyPluginProvidedTitle,
					Messages.JSFLibrariesPreferencePage_CannotModifyPluginProvidedMessage);
			return;
		}
		IWorkbenchWizard wizard = new JSFLibraryWizard();
		wizard.init(wb, getStructuredElement(element));
		WizardDialog dialog = new WizardDialog(wb.getActiveWorkbenchWindow().getShell(), wizard);
		int ret = dialog.open();
		if (ret == Window.OK){
			tv.refresh();
		}
	}
	
	private IStructuredSelection getStructuredElement(Object element) {
		if (element instanceof TreeItem){
			Object item = ((TreeItem)element).getData();
			if (item instanceof ArchiveFile){
				JSFLibrary parent = ((ArchiveFile)item).getJSFLibrary();
				return new StructuredSelection(parent);
			} else if (item instanceof JSFLibrary) {
				return new StructuredSelection(item);
			}
		}
		return null;
	}

	private boolean isPluginProvidedJSFLibrary(Object treeElement){
		if (treeElement instanceof TreeItem){
			Object item = ((TreeItem)treeElement).getData();
			if (item instanceof PluginProvidedJSFLibrary){
				return true;
			} else if (item instanceof ArchiveFile) {
				return (((ArchiveFile)item).getJSFLibrary() instanceof PluginProvidedJSFLibrary);
			}
		}
		return false;
	}

	private class TreeLabelProvider implements ILabelProvider {
		Image libImg;
		Image jarImg;

		TreeLabelProvider(){
			if (jarImg == null){
				ImageDescriptor jarImgDesc = JSFUiPlugin.getImageDescriptor("obj16/jar_obj.gif"); //$NON-NLS-1$
				jarImg = jarImgDesc.createImage();
			}
			if (libImg == null){
				ImageDescriptor libImgDesc = JSFUiPlugin.getImageDescriptor("obj16/library_obj.gif"); //$NON-NLS-1$
				libImg = libImgDesc.createImage();
			}
		}
		
		public Image getImage(Object element) {
			if (element instanceof JSFLibrary)
            {
				return libImg;
            }
			return jarImg;
		}

		public String getText(Object element) {
			StringBuffer labelBuf = new StringBuffer();
			if (element instanceof JSFLibrary) {
				JSFLibrary lib = (JSFLibrary)element;
				labelBuf.append(lib.getName());
				if (lib.isImplementation()) {
					labelBuf.append(" [implementation"); //$NON-NLS-1$
					if (lib == JSFCorePlugin.getDefault().getJSFLibraryRegistry().getDefaultImplementation()) {
						labelBuf.append(" - default"); //$NON-NLS-1$
					}
					labelBuf.append("]"); //$NON-NLS-1$
				}
			}
			if (element instanceof ArchiveFile) {
				ArchiveFile jar = (ArchiveFile)element;
				labelBuf.append(jar.getName());
				if (!jar.exists())
					labelBuf.append("[missing]"); //$NON-NLS-1$
				labelBuf.append(" - ").append(((ArchiveFile)element).getSourceLocation()); //$NON-NLS-1$
			}
			return labelBuf.toString();
		}

		public void addListener(ILabelProviderListener listener) {
            // no listeners supported
		}

		public void dispose() {
			if (libImg != null){
				libImg.dispose();
			}			
			if (jarImg != null){
				jarImg.dispose();
			}		
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void removeListener(ILabelProviderListener listener) {
            // no listeners supported
		}
	}

}
