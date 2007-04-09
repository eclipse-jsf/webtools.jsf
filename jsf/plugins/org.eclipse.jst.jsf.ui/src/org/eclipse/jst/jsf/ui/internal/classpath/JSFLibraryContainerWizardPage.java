/*******************************************************************************
 * Copyright (c) 2005 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.ui.internal.classpath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPage;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPageExtension;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPageExtension2;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.JSFLibrariesContainerInitializer;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.internal.FacetedProjectNature;

/**
 * Provides a classpath container wizard page for JSF Libraries.
 * 
 * @author Gerry Kessler - Oracle
 */
public class JSFLibraryContainerWizardPage extends WizardPage implements
		IClasspathContainerPage, IClasspathContainerPageExtension, IClasspathContainerPageExtension2{

	private CheckboxTableViewer lv;
	private JSFLibrariesTableViewerAdapter lvAdapter;
	private JSFLibrariesListLabelProvider lvLabelProvider;
	
	private boolean isJSFProject = false;
	private IClasspathEntry containerEntry;
	private IClasspathEntry[] currentEntries;
	private Map _currentLibs;
	private JSFLibrary currentLib;		

	/**
	 * Zero arg constructor
	 */
	public JSFLibraryContainerWizardPage(){
        super(Messages.JSFLibraryContainerWizardPage_PageName);        
        setTitle(Messages.JSFLibraryContainerWizardPage_Title);
        setDescription(Messages.JSFLibraryContainerWizardPage_Description);
        // TODO: Replace with a custom image.
        setImageDescriptor( JSFUiPlugin.getImageDescriptor("full/wizban/addlibrary_wiz.gif")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPageExtension#initialize(org.eclipse.jdt.core.IJavaProject, org.eclipse.jdt.core.IClasspathEntry[])
	 */
	public void initialize(IJavaProject project, IClasspathEntry[] currentEntries_) {		
		isJSFProject = false;
		this.currentEntries = currentEntries_;
		try {
			//check for faceted nature
			//NOTE: use of following constant produces warnings; this was known
			//but at time of writing no public API was available
			if (project.getProject().hasNature(FacetedProjectNature.NATURE_ID)){
				//check for jsf facet
				IFacetedProject fproj = ProjectFacetsManager.create(project.getProject());
				Iterator it = fproj.getProjectFacets().iterator();
				while (it.hasNext()){
					IProjectFacetVersion facetVersion = (IProjectFacetVersion)it.next();
					IProjectFacet facet = facetVersion.getProjectFacet();
					if (facet.getId().equals(JSFCorePlugin.FACET_ID)){
						isJSFProject = true;
						return;
					}
				}				
			}
		} catch (CoreException e) {
			JSFUiPlugin.log(
					IStatus.ERROR,
					Messages.JSFLibraryContainerWizardPage_ErrorInitializing,
					e);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPage#finish()
	 */
	public boolean finish() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPageExtension2#getNewContainers()
	 */
	public IClasspathEntry[] getNewContainers() {
		IPath cp = new Path(JSFLibrariesContainerInitializer.JSF_LIBRARY_CP_CONTAINER_ID);
		List res = new ArrayList();
		Object[] items = lv.getCheckedElements();
		for (int i=0;i<items.length;i++){
			JSFLibrary jsfLib = (JSFLibrary)items[i];
			if (getSelectedJSFLibariesForProject().get(jsfLib.getID()) == null){
				IPath path = cp.append(new Path(jsfLib.getID()));
				IClasspathEntry entry = JavaCore.newContainerEntry(path);
				// need to update wtp dependency in j2ee mod dependency ui
				res.add(entry);
			}
		}
		return (IClasspathEntry[])res.toArray(new IClasspathEntry[]{});
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		if (!isJSFProject) {
			return false;
		}
		if (isEditReference() && ! selectionHasChanged())
			return false;
		
		return isValid();
	}

	private boolean isValid() {		
		return isCheckedItems() && getErrorMessage() == null;
	}

	//to be used to know whether the selected library has changed when in "edit" mode
	private boolean selectionHasChanged() {
		JSFLibrary lib = getCurrentLibrarySelection();
		if (lib == null)
			return false;
		
		return (getJSFLibraryForEdit(containerEntry) != lib) ;

	}

	private JSFLibrary getCurrentLibrarySelection() {
		JSFLibrary lib = null;
		StructuredSelection ssel = (StructuredSelection)lv.getSelection();
		if (ssel != null && !ssel.isEmpty()){
			lib = (JSFLibrary)ssel.getFirstElement();
		}
		return lib;
	}

	private boolean isCheckedItems() {		
		return lv.getCheckedElements().length > 0;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPage#getSelection()
	 */
	public IClasspathEntry getSelection() {
		IClasspathEntry entry = null;
		if (isEditReference()){
			if (lv.getCheckedElements().length == 0)
				return containerEntry;
						
			JSFLibrary lib = (JSFLibrary)lv.getCheckedElements()[0];
			if (lib != null){
				if (lib == getJSFLibraryForEdit(containerEntry))
				{
					return containerEntry;
				}
                IPath path = new Path(JSFLibrariesContainerInitializer.JSF_LIBRARY_CP_CONTAINER_ID).append(new Path(lib.getID()));
                entry = JavaCore.newContainerEntry(path, containerEntry.getAccessRules(), containerEntry.getExtraAttributes(),containerEntry.isExported());
			}			
		}
		return entry;

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPage#setSelection(org.eclipse.jdt.core.IClasspathEntry)
	 */
	public void setSelection(IClasspathEntry containerEntry) {
		//this is signalling that this is an "edit"
		this.containerEntry = containerEntry;
	}

	public void createControl(Composite parent) {
		//Build UI to display JSF Lib components from registry
		Composite c = new Composite(parent, SWT.NONE);
		c.setLayout(new GridLayout(2, false));
		c.setLayoutData(new GridData(GridData.FILL_BOTH));

		//disable wizard if this is not a valid JSF project
		if (!isJSFProject){
			Label warning = new Label(c , SWT.NONE);
			warning.setText(Messages.JSFLibraryContainerWizardPage_WarningNoJSFFacet);
			setControl(c);			
			return;
		}

		Label lblViewer = new Label(c, SWT.NONE);
		lblViewer.setText(Messages.JSFLibraryContainerWizardPage_JSFLibraries);
		GridData gd1 = new GridData(GridData.BEGINNING);
		gd1.horizontalSpan = 2;
		lblViewer.setLayoutData(gd1);

		lv = createTableViewer(c);
		lv.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));

		lvAdapter = new JSFLibrariesTableViewerAdapter();
		lvLabelProvider = new JSFLibrariesListLabelProvider();
		lv.setContentProvider(lvAdapter);
		lv.setLabelProvider(lvLabelProvider);
		lv.addSelectionChangedListener(lvAdapter);
		lv.addDoubleClickListener(lvAdapter);
		lv.setComparator(lvAdapter);
		
		Composite buttons = new Composite(c, SWT.NONE);
		buttons.setLayout(new GridLayout(1, false));
		buttons.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		
		final Button addButton = new Button(buttons, SWT.NONE);
		addButton.setText(Messages.JSFLibraryContainerWizardPage_Add);
		addButton.setLayoutData(new GridData(GridData.END | GridData.VERTICAL_ALIGN_BEGINNING));
		addButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				openJSFLibraryWizard(null);				
			}
		});
		
		final Button editButton = new Button(buttons, SWT.NONE);
		editButton.setText(Messages.JSFLibraryContainerWizardPage_Edit);
		editButton.setLayoutData(new GridData(GridData.END | GridData.VERTICAL_ALIGN_BEGINNING));
		editButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				StructuredSelection sel = (StructuredSelection)lv.getSelection();
				if ((sel == null || sel.isEmpty()) && containerEntry != null){
					JSFLibrary jsfLib = getJSFLibraryForEdit(containerEntry);
					sel = new StructuredSelection(jsfLib);
				}
				openJSFLibraryWizard(sel);				
			}

		});
		editButton.setVisible(false);
		lv.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event) {
				setEditButton(event.getSelection());
			}

			private void setEditButton(final ISelection selection) {
				IStructuredSelection sel = (IStructuredSelection)selection;
				editButton.setVisible(sel.size()==1);		
				if (sel.size() == 1){					
					JSFLibrary lib = (JSFLibrary)sel.getFirstElement();
					boolean pp = lib instanceof PluginProvidedJSFLibrary;
					editButton.setEnabled(! pp);
					if (isEditReference()){
						lv.setAllChecked(false);
						lv.setChecked(lib, true);
					}
				}
				
			}			
		});
		setControl(c);
		
		if (isEditReference()){
			JSFLibrary lib = getJSFLibraryForEdit(containerEntry);
			lv.setInput(getAllUnselectedJSFLibrariesExceptReferencedLib(lib));	
			selectAndCheckCurrentLib(lib);
			setDescription("Select JSF Libary for this reference to use.  Choose 'Edit...' to modify contents of the selected libarary.");
		} 
		else {
			lv.setInput(getAllJSFLibraries());		
			lv.setCheckedElements(getSelectedJSFLibariesForProject().values().toArray(new Object[0]));
		}
	}

	private void selectAndCheckCurrentLib(final JSFLibrary lib) {
		if (lib != null){
			StructuredSelection ssel = new StructuredSelection(lib);	
			lv.setSelection(ssel);
			lv.setChecked(lib, true);
		}
	}

	private Object getAllUnselectedJSFLibrariesExceptReferencedLib(JSFLibrary referenceLib) {
		List allLibs = getAllJSFLibraries();
		Collection selLibs = getSelectedJSFLibariesForProject().values();
		for (Iterator it=selLibs.iterator();it.hasNext();){
			JSFLibrary aLib = (JSFLibrary)it.next();
			int i= allLibs.indexOf(aLib);
			//remove from allLibs unless it is the selected reference
			if (i >= 0 && ((referenceLib == null) || (aLib != null && ! aLib.getID().equals(referenceLib.getID())))){
				allLibs.remove(i);
			}
		}
		return allLibs;
	}

	private List getJSFLibraryEntries(IClasspathEntry[] entries) {
		List jsfLibs = new ArrayList();
		for (int i=0;i<entries.length;i++){
			IClasspathEntry entry = entries[i];
			if (isJSFLibrary(entry)){
				JSFLibrary lib = JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry(). getJSFLibraryByID(entry.getPath().segment(1));
				if (lib != null){
					jsfLibs.add(lib);
				}
			}
		}
		
		return jsfLibs;
	}

	private boolean isJSFLibrary(IClasspathEntry entry) {
		return entry.getPath().segment(0).equals(JSFLibrariesContainerInitializer.JSF_LIBRARY_CP_CONTAINER_ID);
	}

	private void openJSFLibraryWizard(IStructuredSelection element){
		IWorkbenchWizard wizard = new JSFLibraryWizard();
		IWorkbench wb = PlatformUI.getWorkbench();
		wizard.init(wb, element);
		WizardDialog dialog = new WizardDialog(wb.getActiveWorkbenchWindow().getShell(), wizard);
		int ret = dialog.open();
		if (ret == Window.OK){
			//FIXME: select returned object
			if (containerEntry == null){
				lv.setInput(getAllJSFLibraries());				
			}
			else {
				lv.setInput(getAllUnselectedJSFLibrariesExceptReferencedLib(getJSFLibraryForEdit(containerEntry)));
				lv.refresh(true);
			}
			lv.refresh();
		}
	}
	
	private CheckboxTableViewer createTableViewer(Composite parent) {
		Table table= new Table(parent, SWT.CHECK | SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		table.setFont(parent.getFont());
		CheckboxTableViewer tableViewer= new CheckboxTableViewer(table);
		tableViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent e) {
				if (! isEditReference()){
					//ensure that existing CP entries cannot be unchecked
					if (getSelectedJSFLibariesForProject().get(((JSFLibrary)e.getElement()).getID()) != null){
						if (containerEntry == null)
							e.getCheckable().setChecked(e.getElement(), true);
						else
							lv.setAllChecked(true);
					}
				}
				else {
					//select only one
					lv.setAllChecked(false);
					lv.setChecked(e.getElement(), true);
					if (isEditReference())
						lv.setSelection(new StructuredSelection(e.getElement()));
				}
				validate();				
			}
		});
		return tableViewer;
	}

	private Map getSelectedJSFLibariesForProject(){
		if (_currentLibs == null){
			List allLibs = getAllJSFLibraries();
			List curLibs = getJSFLibraryEntries(currentEntries);
			_currentLibs = new HashMap(curLibs.size());
			for (Iterator it=curLibs.iterator();it.hasNext();){
				JSFLibrary lib = (JSFLibrary)it.next();
				int index = getIndex(allLibs, lib);
				if (index >=0)
					_currentLibs.put(lib.getID(), allLibs.get(index));
			}
					
		}
		return _currentLibs;
	}
	
	private List getAllJSFLibraries() {
		List allLibs = JSFCorePlugin.getDefault().getJSFLibraryRegistry().getAllJSFLibraries();

		return allLibs;
	}


	private JSFLibrary getJSFLibraryForEdit(
			IClasspathEntry containerEntry_) {
		if (currentLib == null){
			String id = containerEntry_.getPath().segment(1);
			currentLib = JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry().getJSFLibraryByID(id);	
		}
		return currentLib;

	}
	
	private int getIndex(List libs, JSFLibrary lib) {
		for (int i=0;i<libs.size();i++){
			if (lib.getID().equals(((JSFLibrary)libs.get(i)).getID()))
				return i;
		}
		return -1;
	}

	private class JSFLibrariesTableViewerAdapter extends ViewerComparator implements IStructuredContentProvider, ISelectionChangedListener, IDoubleClickListener {

		private Object input;

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			input = newInput;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
            // do nothing
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object inputElement) {		
			return ((List)input).toArray();
		}		

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
		 */
		public void selectionChanged(SelectionChangedEvent event) {
			if (isEditReference()){
				setPageComplete(isPageComplete());
			}
			
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
		 */
		public void doubleClick(DoubleClickEvent event) {
			doDoubleClick(event);
		}
		
		public int compare(Viewer viewer, Object e1, Object e2) {
			JSFLibrary lib1 = (JSFLibrary)e1;
			JSFLibrary lib2 = (JSFLibrary)e2;
			
			//sort first by in selection already and then by name
			boolean lib1Sel = getSelectedJSFLibariesForProject().get(lib1.getID())!=null;
			boolean lib2Sel = getSelectedJSFLibariesForProject().get(lib2.getID())!= null;
			
			if ((lib1Sel && lib2Sel) || (!lib1Sel && !lib2Sel) ){
				return getComparator().compare(lib1.getName(), lib2.getName());
			}
			else if (lib1Sel)
				return -1;
			else
				return 1;
		}
	}
	
	private class JSFLibrariesListLabelProvider implements ILabelProvider{		
		Image libImg;
		public Image getImage(Object element) {
			if (libImg == null){
				ImageDescriptor libImgDesc = JSFUiPlugin.getImageDescriptor("obj16/library_obj.gif"); //$NON-NLS-1$
				libImg = libImgDesc.createImage();
			}
			return libImg;
		}

		public String getText(Object element) {
			if (element instanceof JSFLibrary) {
				JSFLibrary lib = (JSFLibrary)element;
				if (lib.isImplementation()) {
					return lib.getName() + " [implementation]"; //$NON-NLS-1$
				}
                return lib.getName();
			}
			return null;
		}

		public void dispose() {
			if (libImg != null)
				libImg.dispose();
		}

		public void addListener(ILabelProviderListener listener) {		
            // no listener support
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void removeListener(ILabelProviderListener listener) {
            // no listener support
		}
	}
	
	private void validate() {
		setErrorMessage(null);
		int implChosenCount = implSelectedCount();
		if (implChosenCount>1){
			setErrorMessage(Messages.JSFLibraryContainerWizardPage_ImplAlreadyPresent);
		}
        setPageComplete(isPageComplete());
	}


	private boolean isEditReference() {
		return (containerEntry != null);		
	}

	private int implSelectedCount() {
		int count = 0;
		for (int i=0;i<lv.getCheckedElements().length;i++){
			JSFLibrary lib = (JSFLibrary)lv.getCheckedElements()[i];
			if (lib.isImplementation())
				count++;
		}
		return count;
	}

	private void doDoubleClick(DoubleClickEvent event) {
		StructuredSelection ssel = (StructuredSelection)event.getSelection();
		if (ssel != null && 
				(! ((JSFLibrary)ssel.getFirstElement() instanceof PluginProvidedJSFLibrary)))
			openJSFLibraryWizard((IStructuredSelection)event.getSelection());
	}


}
