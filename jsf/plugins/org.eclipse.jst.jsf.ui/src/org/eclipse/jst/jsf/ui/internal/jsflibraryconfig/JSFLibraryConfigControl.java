/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Justin Chen - development check in
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.jsflibraryconfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfigModelAdapter;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryDecorator;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.ui.internal.JSFUiPlugin;
import org.eclipse.jst.jsf.ui.internal.Messages;
import org.eclipse.jst.jsf.ui.internal.classpath.JSFLibraryWizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * A custom control used in wizard and property pages.
 * 
 * @author Justin Chen - Oracle
 */
public class JSFLibraryConfigControl extends Composite { 
	final private int COLUMN_DEPLOY = 0;
	final private int COLUMN_LIB_NAME = 1;

	private JSFLibraryConfigModelAdapter provider = null;
	
	private ComboViewer cvImplLib;
	private CheckboxTableViewer ctvSelCompLib;
	private Button btnDeployJars;
	private TreeViewer tvCompLib;
	private TreeViewerAdapter tvAdapter;
	private TreeLabelProvider tvLabelProvider;
	private Combo comboImplLib;
		
	private Vector newJSFLibCreatedListeners = new Vector();	

	public void addOkClickedListener(IJSFImplLibraryCreationListener listener) {
		newJSFLibCreatedListeners.addElement(listener);
	}
	public void removeOkClickedListener(IJSFImplLibraryCreationListener listener) {
		newJSFLibCreatedListeners.removeElement(listener);
	}
	
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 * @param IProject
	 */	
	public JSFLibraryConfigControl(Composite parent, int style, IProject fProject) {
		super(parent, style);	
				
		this.provider = new JSFLibraryConfigModelAdapter(fProject);
		
		initControls();
	}	

	/**
	 * Return the working model.
	 * 
	 * @return JSFLibraryConfigModelAdapter
	 */
	public JSFLibraryConfigModelAdapter getModelProvider() {
		return provider;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Widget#dispose()
	 */
	public void dispose() {
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Composite#checkSubclass()
	 */
	protected void checkSubclass() {
	}
	
	/**
	 * Return current selected JSF Implementation Library.
	 * Otherwise, return null.
	 *  
	 * @return JSFLibraryDecorator
	 */
	public JSFLibraryDecorator getSelectedJSFLibImplementation() {
		return provider.getSelectedJSFImplLibrary();
	}
	
	/**
	 * Return a list of selected JSF Component Libraries.
	 * Otherwise, return an empty list.
	 * 
	 * @return a list of selected JSF Component Libraries
	 */
	public List getSelectedJSFLibComponents() {
		return provider.getSelectedJSFCompLibraries();
	}
					
	private void initializeControlValues() {
		loadJSFImplList();
		
		btnDeployJars.setSelection(false);
		if ( provider.getSavedJSFImplementationLibrary() != null ) {
			JSFLibraryDecorator selJSFImplLib = provider.getSavedJSFImplementationLibrary();
			cvImplLib.setSelection(new StructuredSelection(selJSFImplLib), true);
			btnDeployJars.setSelection(selJSFImplLib.isCheckedToBeDeployed());
		} else {
			JSFLibraryDecorator dftJSFImplLib = provider.getDefaultJSFImplemntationLibrary();
			if (dftJSFImplLib != null) {				
				cvImplLib.setSelection(new StructuredSelection(dftJSFImplLib), true);				
				btnDeployJars.setSelection(dftJSFImplLib.isCheckedToBeDeployed());				
			}
		}
		
		loadJSFCompList();

		JSFLibraryDecorator jsfLibDctr = null; 
		List jsfCompLibs = provider.getSavedJSFComponentLibraries();
		for (int i = 0; i < jsfCompLibs.size(); i++) {
			jsfLibDctr = (JSFLibraryDecorator)jsfCompLibs.get(i);
			ctvSelCompLib.setChecked(jsfLibDctr, jsfLibDctr.isCheckedToBeDeployed());
		} 
		
		redraw();
	}
	
	private void loadJSFImplList() {
		cvImplLib.setInput(provider.getJSFImplementationLibraries());
	}
	
	private void loadJSFCompList() {
		tvCompLib.setInput(provider.getJSFComponentLibraries());
		ctvSelCompLib.setInput(provider.getJSFComponentLibraries());		
	}
	
	private JSFLibraryDecorator getJSFImplLibwUpdatedState() {
		JSFLibraryDecorator selJSFImpl = null;
		StructuredSelection objs = (StructuredSelection)cvImplLib.getSelection();
		if (objs != null){
			if (objs.getFirstElement() instanceof JSFLibraryDecorator){
				selJSFImpl = (JSFLibraryDecorator)objs.getFirstElement();
				selJSFImpl.setToBeDeployed(btnDeployJars.getSelection());
			}
		}
		return selJSFImpl;		
	}
	
	private void initControls() {
		
		setRedraw(true);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		gridLayout.marginLeft = 0;
		gridLayout.marginRight = 0;
		gridLayout.marginWidth = 0;
		setLayout(gridLayout);
		
		final Label lblImplLib = new Label(this, SWT.NONE);
		final GridData gd_lbl_impl = new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 2, 1);
		lblImplLib.setLayoutData(gd_lbl_impl);
		lblImplLib.setText(Messages.JSFLibraryConfigControl_ImplementationLibrary);

		btnDeployJars = new Button(this, SWT.CHECK);
		btnDeployJars.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false, 2, 1));
		btnDeployJars.setText(Messages.JSFLibraryConfigControl_DeployJAR);
		btnDeployJars.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			public void widgetSelected(SelectionEvent e) {
				JSFLibraryDecorator jsflib = getJSFImplLibwUpdatedState();
				provider.updateJSFImplementationLibrary(jsflib);
			}
		}
		);
		
		final Composite compTest = new Composite(this, SWT.None);
		final GridLayout compgl = new GridLayout();
		compgl.numColumns = 2;
		compgl.marginLeft = 0;
		compgl.marginRight = 0;		
		compgl.marginWidth = 0;
		GridData gdCompTest = new GridData(SWT.FILL, SWT.FILL, true, false);		
		gdCompTest.horizontalSpan = 4;		
		compTest.setLayoutData(gdCompTest);
		compTest.setLayout(compgl);
		
		cvImplLib = new ComboViewer(compTest, SWT.READ_ONLY);
		cvImplLib.setLabelProvider(new ImplLibCVListLabelProvider());
		cvImplLib.setContentProvider(new ImplLibCVContentProvider());
		comboImplLib = cvImplLib.getCombo();
		final GridData gd_cvImplLib = new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1);
		comboImplLib.setLayoutData(gd_cvImplLib);
		cvImplLib.addSelectionChangedListener(
			new ISelectionChangedListener() {
				public void selectionChanged(SelectionChangedEvent event) {
					StructuredSelection ss = (StructuredSelection) event.getSelection();
					provider.updateJSFImplementationLibrary((JSFLibraryDecorator) ss.getFirstElement());
				}
			}
		);
				
		final Button btnNewImpl = new Button(compTest, SWT.NONE);
		btnNewImpl.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, false, false));
		btnNewImpl.setText(Messages.JSFLibraryConfigControl_NewImplementationLibrary);	
		btnNewImpl.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				JSFLibraryWizard wizard = new JSFLibraryWizard(true);
				IWorkbench wb = PlatformUI.getWorkbench();
				wizard.init(wb, null);
				WizardDialog dialog = new WizardDialog(wb
						.getActiveWorkbenchWindow().getShell(), wizard);
				int ret = dialog.open();
				if (ret == Window.OK) {					
					JSFLibraryDecorator jsfLibDctr = new JSFLibraryDecorator(wizard.getJSFLibrary(), true, true);
					JSFLibraryRegistry jsflibreg = JSFCorePlugin.getDefault().getJSFLibraryRegistry();
					jsflibreg.addJSFLibrary(wizard.getJSFLibrary());
					
					provider.addJSFLibrary(jsfLibDctr);
					provider.updateJSFImplementationLibrary(jsfLibDctr);
					
					loadJSFImplList();
					
					btnDeployJars.setSelection(true);
					cvImplLib.setSelection(new StructuredSelection(jsfLibDctr), true);
				}
				// notify listeners that a JSF implementation is created.
				JSFImplLibraryCreationEvent event = new JSFImplLibraryCreationEvent(this, (ret == Window.OK));
				int size = newJSFLibCreatedListeners.size();
				for (int i = 0; i < size; i++) {
					IJSFImplLibraryCreationListener listener = 
						(IJSFImplLibraryCreationListener) newJSFLibCreatedListeners.elementAt(i);
					listener.okClicked(event);
				}
			}
		});		

		final Label lblSeparator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd_lbl_spacer = new GridData(GridData.FILL, GridData.CENTER, true, false, 4, 1);
		lblSeparator.setLayoutData(gd_lbl_spacer);
		lblSeparator.setAlignment(SWT.CENTER);

		final Label lblCompLib = new Label(this, SWT.NONE);
		final GridData gd_lbl_complib = new GridData(GridData.FILL, GridData.CENTER, false, false, 4, 1);
		lblCompLib.setLayoutData(gd_lbl_complib);
		lblCompLib.setText(Messages.JSFLibraryConfigControl_ComponentLibrary);
				
		tvCompLib = new TreeViewer(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tvAdapter = new TreeViewerAdapter();
		tvLabelProvider = new TreeLabelProvider();
		tvCompLib.setContentProvider(tvAdapter);
		tvCompLib.setLabelProvider(tvLabelProvider);
		tvCompLib.addSelectionChangedListener(tvAdapter);
		tvCompLib.addDoubleClickListener(tvAdapter);
		tvCompLib.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		tvCompLib.addFilter(new TreeViewerFilter());		
		
		final Composite composite_buttons = new Composite(this, SWT.NONE);
		composite_buttons.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		composite_buttons.setLayout(new GridLayout());

		final Composite composite_Single = new Composite(composite_buttons, SWT.None);
		composite_Single.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		final GridLayout gl_Single = new GridLayout();
		gl_Single.marginHeight = 4;
		composite_Single.setLayout(gl_Single);
		
		final Button btnAdd = new Button(composite_Single, SWT.NONE);
		btnAdd.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		btnAdd.setText(Messages.JSFLibraryConfigControl_Add);

		final Button btnRemove = new Button(composite_Single, SWT.NONE);
		btnRemove.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		btnRemove.setText(Messages.JSFLibraryConfigControl_Remove);

		final Composite composite_All = new Composite(composite_buttons, SWT.None);
		composite_All.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		final GridLayout gl_All = new GridLayout();
		gl_Single.marginHeight = 4;
		composite_All.setLayout(gl_All);
		
		final Button btnAddAll = new Button(composite_All, SWT.NONE);
		btnAddAll.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		btnAddAll.setText(Messages.JSFLibraryConfigControl_AddAll);

		final Button btnRemoveAll = new Button(composite_All, SWT.NONE);
		btnRemoveAll.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		btnRemoveAll.setText(Messages.JSFLibraryConfigControl_RemoveAll);

		final Composite composite_New = new Composite(composite_buttons, SWT.None);
		composite_New.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		final GridLayout gl_New = new GridLayout();
		gl_Single.marginHeight = 4;
		composite_New.setLayout(gl_New);
		
		final Button btnNewCompLib = new Button(composite_New, SWT.NONE);
		btnNewCompLib.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
		btnNewCompLib.setText(Messages.JSFLibraryConfigControl_NewComponentLibrary);		
		btnNewCompLib.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				JSFLibraryWizard wizard = new JSFLibraryWizard(false);				
				IWorkbench wb = PlatformUI.getWorkbench();
				wizard.init(wb, null, true);
				WizardDialog dialog = new WizardDialog(wb
						.getActiveWorkbenchWindow().getShell(), wizard);						
				int ret = dialog.open();
				if (ret == Window.OK) {
					JSFLibrary newCompLib = wizard.getJSFLibrary();
					JSFLibraryDecorator jsfLibDctr = new JSFLibraryDecorator(newCompLib, true, true);
					JSFLibraryRegistry jsflibreg = JSFCorePlugin.getDefault().getJSFLibraryRegistry();
					jsflibreg.addJSFLibrary(newCompLib);
					
					provider.addJSFLibrary(jsfLibDctr);
					
					loadJSFCompList();
					
					ctvSelCompLib.setChecked(jsfLibDctr, true);
				}
			}
		});	

		ctvSelCompLib = CheckboxTableViewer.newCheckList(this, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		ctvSelCompLib.addFilter(new CheckedTableViewerFilter());
		final Table table = ctvSelCompLib.getTable();
		table.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));		
		table.setHeaderVisible(true);
		final TableColumn tcDeploy = new TableColumn(table, SWT.LEFT);
		tcDeploy.setWidth(50);
		tcDeploy.setText(Messages.JSFLibraryConfigControl_TH_Deploy);
		final TableColumn tcLibName = new TableColumn(table, SWT.LEFT);
		tcLibName.setWidth(150);
		tcLibName.setText(Messages.JSFLibraryConfigControl_TH_LibraryName);
		
		//ctvSelCompLib.setCellModifier(new CellModifierCTVSelCompLib());
		ctvSelCompLib.setSorter(new SelectedCompLibCTVSorter());
		ctvSelCompLib.setLabelProvider(new SelectedCompLibCTVLabelProvider());
		ctvSelCompLib.setContentProvider(new CompLibCTVContentProvider());
		ctvSelCompLib.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				JSFLibraryDecorator changedItem = (JSFLibraryDecorator) event.getElement();
				boolean isChecked4Deploy = event.getChecked();
				
				List list = provider.getJSFComponentLibraries();
				Iterator it = list.iterator();
				JSFLibraryDecorator crtjsflib = null;
				while (it.hasNext()) {
					crtjsflib = (JSFLibraryDecorator) it.next();
					if (crtjsflib.getID().equals(changedItem.getID())) {
						crtjsflib.setToBeDeployed(isChecked4Deploy);
						break;
					}
				}
			}
		});
		
		//initializeControlValues();
		btnAdd.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				resetComponentLibSelection((StructuredSelection)tvCompLib.getSelection(), 
						tvCompLib, 
						ctvSelCompLib, 
						true);	
			}
		});
		btnAddAll.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				resetCompontLibSelectionAll(tvCompLib, ctvSelCompLib, true);
			}
		});		
		btnRemove.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				resetComponentLibSelection((StructuredSelection)ctvSelCompLib.getSelection(), 
						tvCompLib, 
						ctvSelCompLib, 
						false);	
			}
		});
		btnRemoveAll.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				resetCompontLibSelectionAll(tvCompLib, ctvSelCompLib, false);
			}
		});
		
		initializeControlValues();
	}

	/*
	 * Event handling helper methods
	 */	
	
	// Set selected item to the given state on model and update viewers.  
	private void resetComponentLibSelection(StructuredSelection item, 
			TreeViewer srcViewer, 
			CheckboxTableViewer destViewer,
			boolean state) {
		if (item != null && item.getFirstElement() != null) {
			JSFLibraryDecorator jsfLibDctr = (JSFLibraryDecorator)item.getFirstElement();
			List list = provider.getJSFComponentLibraries();
			Iterator it = list.iterator();
			JSFLibraryDecorator crtjsfLibDctr = null;
			while(it.hasNext()) {
				crtjsfLibDctr = (JSFLibraryDecorator)it.next();
				if (crtjsfLibDctr.getID().equals(jsfLibDctr.getID())) {
					crtjsfLibDctr.setToBeDeployed(state);
					crtjsfLibDctr.setSelected(state);
				}
			}					
						
			loadJSFCompList();
			
			srcViewer.refresh();
			destViewer.refresh();	
			destViewer.setChecked(jsfLibDctr, state);
		}		
	}
	
	// Reset all component library from given state to model and update viewers.   
	private void resetCompontLibSelectionAll(TreeViewer srcViewer, 
			CheckboxTableViewer destViewer, 
			boolean state) {

			List list = provider.getJSFComponentLibraries();
			Iterator it = list.iterator();
			JSFLibraryDecorator jsfLibDctr;
			while(it.hasNext()) {
				jsfLibDctr = (JSFLibraryDecorator)it.next();
				jsfLibDctr.setSelected(state);
				jsfLibDctr.setToBeDeployed(state);
			}				
			
			loadJSFCompList();
			
			srcViewer.refresh();
			destViewer.refresh();
			destViewer.setAllChecked(state);					
	}
	
	/**
	 * 	Inner Classes for filtering.
	 *
	 */
	class CheckedTableViewerFilter extends ViewerFilter {
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof JSFLibraryDecorator) {
				return ((JSFLibraryDecorator)element).isSelected();
			}
			return false;
		}
	}
	class TreeViewerFilter extends ViewerFilter {

		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof JSFLibraryDecorator) {
				return !((JSFLibraryDecorator)element).isSelected();
			}
			return true;
		}
	}

	class CompLibCTVContentProvider implements IStructuredContentProvider {
		private List jsfComplLibs = new ArrayList(Collections.EMPTY_LIST);
		
		public Object[] getElements(Object inputElement) {
			return jsfComplLibs.toArray();
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			if (newInput == null) {
				jsfComplLibs = Collections.EMPTY_LIST;
			} else {
				jsfComplLibs = (List)newInput;
			}
		}
	}
	class ImplLibCVContentProvider implements IStructuredContentProvider {
		private List jsfImplLibs = new ArrayList(Collections.EMPTY_LIST);
		
		public Object[] getElements(Object inputElement) {
			return jsfImplLibs.toArray();
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			if (newInput == null) {
				jsfImplLibs = Collections.EMPTY_LIST;
			} else {
				jsfImplLibs = (List)newInput;
			}
		}
	}
	
	// Label Provider
	class SelectedCompLibCTVLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof JSFLibraryDecorator){
				
			    switch(columnIndex) {
			    case COLUMN_DEPLOY:
			    	return " ";	  //$NON-NLS-1$
			    case COLUMN_LIB_NAME:
			    	return ((JSFLibraryDecorator)element).getName();
			    }				
			}			
		    return ""; //$NON-NLS-1$
			
		}
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
	}
	class ImplLibCVListLabelProvider extends LabelProvider {
		private JSFLibrary defaultImpl = null;
		
		public String getText(Object element) {
			if (element instanceof JSFLibraryDecorator){
				StringBuffer nameBuf = new StringBuffer(((JSFLibraryDecorator)element).getName());
				if ((((JSFLibraryDecorator)element).getLibrary()) == getDefaultImpl())
					nameBuf.append(" ").append(JSFLibraryRegistry.DEFAULT_IMPL_LABEL); //$NON-NLS-1$
				return nameBuf.toString() ;
			}
			return null;
		}
		private JSFLibrary getDefaultImpl() {
			if (defaultImpl == null){
				JSFLibraryRegistry jsflibreg = JSFCorePlugin.getDefault().getJSFLibraryRegistry();
				defaultImpl = jsflibreg.getDefaultImplementation();
			}
			return defaultImpl;
		}
		public Image getImage(Object element) {
			return null;
		}
	}
	
	// Sorter
	class SelectedCompLibCTVSorter extends ViewerSorter {
		public int compare(Viewer viewer, Object e1, Object e2) {
			if (e1 instanceof JSFLibraryDecorator && 
					e2 instanceof JSFLibraryDecorator) {
			JSFLibraryDecorator item1 = (JSFLibraryDecorator)e1;
			JSFLibraryDecorator item2 = (JSFLibraryDecorator)e2;			
			return item1.getName().compareToIgnoreCase(item2.getName());
			}
			return 0;
		}
	}
	
	/*
	 * Content provider Adapter for TreeViewer
	 */
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
		}

		public Object[] getElements(Object obj) {
			return provider.getJSFComponentLibraries().toArray();
		}
		
		public Object[] getChildren(Object element) {
			if (element instanceof JSFLibraryDecorator) {
				return ((JSFLibraryDecorator)element).getArchiveFiles().toArray();				
			}
			return NO_ELEMENTS;
		}

		public Object getParent(Object element) {
			return null;
		}

		public boolean hasChildren(Object element) {
			if (element instanceof JSFLibraryDecorator) {
				return true;
			}
			return false;
		}		

		// ------- ISelectionChangedListener Interface ------------

		public void selectionChanged(SelectionChangedEvent event) {

		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
		 */
		public void doubleClick(DoubleClickEvent event) {

		}		
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
			if (element instanceof JSFLibraryDecorator)
				return libImg;
			else
				return jarImg;
		}

		public String getText(Object element) {
			StringBuffer labelBuf = new StringBuffer();
			if (element instanceof JSFLibraryDecorator) {
				JSFLibraryDecorator libWrapper = (JSFLibraryDecorator)element;
				JSFLibrary lib = (JSFLibrary)libWrapper.getLibrary();
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
		}
	}

}
