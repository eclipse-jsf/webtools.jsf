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

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPage;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPageExtension;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
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
		IClasspathContainerPage, IClasspathContainerPageExtension{

	private TableViewer lv;
	private JSFLibrariesTableViewerAdapter lvAdapter;
	private JSFLibrariesListLabelProvider lvLabelProvider;
	private ArrayList elements;
	private boolean isJSFProject = false;		

	public JSFLibraryContainerWizardPage(){
        super(Messages.JSFLibraryContainerWizardPage_PageName);        
        setTitle(Messages.JSFLibraryContainerWizardPage_Title);
        setDescription(Messages.JSFLibraryContainerWizardPage_Description);
        // TODO: Replace with a custom image.
        setImageDescriptor( JSFUiPlugin.getImageDescriptor("full/wizban/addlibrary_wiz.gif")); //$NON-NLS-1$
	}

	public void initialize(IJavaProject project, IClasspathEntry[] currentEntries) {		
		isJSFProject = false;
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

	public boolean finish() {
		// TODO Create lib containers and verify single implementation selected
		return true;
	}

	public boolean isPageComplete() {
		if (!isJSFProject) {
			return false;
		}
		return isValid();
	}

	private boolean isValid() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPage#getSelection()
	 */
	public IClasspathEntry getSelection() {
	    // TODO: dead code?
        //		if (lv.getSelection()!= null){
//		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPage#setSelection(org.eclipse.jdt.core.IClasspathEntry)
	 */
	public void setSelection(IClasspathEntry containerEntry) {
        // getSelection always returns null
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

		lv.setInput(getJSFLibraries());

//		Composite btnBar = new Composite(c, SWT.NONE);
//		GridLayout gl = new GridLayout(1, false);
//		gl.marginHeight = 0;
//		gl.marginWidth = 0;

		Button addButton = new Button(c, SWT.NONE);
		addButton.setText(Messages.JSFLibraryContainerWizardPage_Add);
		addButton.setLayoutData(new GridData(GridData.END | GridData.VERTICAL_ALIGN_BEGINNING));
		addButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				openJSFLibraryWizard(null);
			}
		});

		setControl(c);
	}

	private void openJSFLibraryWizard(Object element){
		IWorkbenchWizard wizard = new JSFLibraryWizard();
		IWorkbench wb = PlatformUI.getWorkbench();
		wizard.init(wb, getStructuredElement(element));
		WizardDialog dialog = new WizardDialog(wb.getActiveWorkbenchWindow().getShell(), wizard);
		int ret = dialog.open();
		if (ret == Window.OK){
			//FIXME: select returned object
			lv.refresh();
		}
	}
	
	private IStructuredSelection getStructuredElement(Object element) {
		if (element != null  && element instanceof IStructuredSelection){
			//FIXME:
//			IStructuredSelection firstelement = (IStructuredSelection)((IStructuredSelection)element).getFirstElement();
//			return new StructuredSelection(firstelement);
		}
		return null;
	}

	private TableViewer createTableViewer(Composite parent) {
		Table table= new Table(parent, SWT.CHECK | SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		table.setFont(parent.getFont());
		CheckboxTableViewer tableViewer= new CheckboxTableViewer(table);
		tableViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent e) {
				validate();
			}
		});
		return tableViewer;
	}

	private Object getJSFLibraries() {
		elements = new ArrayList(10);
		elements.addAll(JSFCorePlugin.getDefault().getJSFLibraryRegistry().getAllJSFLibraries());
		return elements;
	}

	private class JSFLibrariesTableViewerAdapter implements IStructuredContentProvider, ISelectionChangedListener, IDoubleClickListener {
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			//should never happen
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
			return elements.toArray();
		}		

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
		 */
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
        // TODO: what's this for? seems dead
	}

	private void doListSelected(SelectionChangedEvent event) {
        // what's this for? seems dead.
	}

	private void doDoubleClick(DoubleClickEvent event) {
		openJSFLibraryWizard(lv.getSelection());
	}

}
