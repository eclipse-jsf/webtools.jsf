/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Justin Chen
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.jsflibraryconfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.SafeRunnable;
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
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfigModel;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfiglModelSource;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryReference;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.internal.project.facet.IJSFFacetInstallDataModelProperties;
import org.eclipse.jst.jsf.ui.internal.JSFUiPlugin;
import org.eclipse.jst.jsf.ui.internal.Messages;
import org.eclipse.jst.jsf.ui.internal.classpath.JSFLibraryWizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelSynchHelper;

/**
 * A custom control used in wizard and property pages.
 * 
 * @author Justin Chen
 */
public class JSFLibraryConfigControl extends Composite { 
	final private int COLUMN_DEPLOY = 0;
	final private int COLUMN_LIB_NAME = 1;

	private JSFLibraryConfigModel workingCopyModel = null;
	
	private ComboViewer cvImplLib;
	private CheckboxTableViewer ctvSelCompLib;
	private Button btnDeployJars;
	private TreeViewer tvCompLib;
	private TreeViewerAdapter tvAdapter;
	private TreeLabelProvider tvLabelProvider;
	private Combo comboImplLib;
	private Button btnAddAll;
	private Button btnRemoveAll;
	
	private Vector newJSFLibCreatedListeners = new Vector();
	private Set _changeListeners;
	private boolean _initing;
	private IDataModel model;	

	/**
	 * @param listener
	 */
	public void addOkClickedListener(IJSFImplLibraryCreationListener listener) {
		newJSFLibCreatedListeners.addElement(listener);
	}
	/**
	 * @param listener
	 */
	public void removeOkClickedListener(IJSFImplLibraryCreationListener listener) {
		newJSFLibCreatedListeners.removeElement(listener);
	}
	
	/**
	 * @param listener
	 */
	public void addChangeListener(JSFLibraryConfigControlChangeListener listener){
		getChangeListeners().add(listener);
	}
	
	/**
	 * @param listener
	 */
	public void removeChangeListener(JSFLibraryConfigControlChangeListener listener){
		if (getChangeListeners().contains(listener))
			getChangeListeners().remove(listener);
	}
	
	private Set getChangeListeners() {
		if (_changeListeners == null){
			_changeListeners = new HashSet();
		}
		return _changeListeners;
	}
	
	private void fireChangedEvent(final EventObject e){
		if (_initing) return;
		SafeRunnable.run(new ISafeRunnable(){
			public void handleException(Throwable exception) {
			}
			public void run() throws Exception {
				for (Iterator it=getChangeListeners().iterator();it.hasNext();){
					((JSFLibraryConfigControlChangeListener)it.next()).changed(new JSFLibraryConfigControlChangeEvent(e));
				}
			}
		});
	}
	
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */	
	public JSFLibraryConfigControl(Composite parent, int style) {
		super(parent, style);
		_initing = true;
		createControls();
	}	
	
	/**
	 * set control values from provided model.
	 * 
	 * @param source
	 */
	public void loadControlValuesFromModel(JSFLibraryConfiglModelSource source) {
		if (source != null) {
			// never read persistentModel = source;
			workingCopyModel = JSFLibraryConfigModel.JSFLibraryConfigModelFactory.createInstance(source);
			initializeControlValues();
			_initing = false;
		} else {
			JSFUiPlugin.log(IStatus.ERROR, Messages.JSFLibraryConfigControl_NullProject);
		}
		
	}
		
	/* (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Widget#dispose()
	 */
	public void dispose() {
		super.dispose();
	}
	
	/**
	 * Return current selected JSF Implementation Library.
	 * Otherwise, return null.
	 *  
	 * @return JSFLibraryDecorator
	 */
	public JSFLibraryReference getSelectedJSFLibImplementation() {
		return workingCopyModel.getCurrentJSFImplementationLibrarySelection();
	}
	 
	/**
	 * Return a list of selected JSF Component Libraries.
	 * Otherwise, return an empty list.
	 * 
	 * @return a list of selected JSF Component Libraries
	*/
	public List getSelectedJSFLibComponents() {
		return workingCopyModel.getCurrentJSFComponentLibrarySelection();
	}
	
	/**
	 * 
	 * @return JSFLibraryConfigModelAdapter
	 */
	public JSFLibraryConfigModel getWorkingModel() {
		return workingCopyModel;
	}
	
	private void initializeControlValues() {
		loadJSFImplList();
		
		btnDeployJars.setSelection(false);
		//JSFLibraryDecorator savedImplLib = persistentModel.getJSFImplementationLibrary();
		JSFLibraryReference savedImplLib = workingCopyModel.getSavedJSFImplementationLibrary();
		if ( savedImplLib != null ) {
			/*
			 * Get the input for the control to set selection.
			 */
			JSFLibraryReference selected = JSFLibraryRegistryUtil.getInstance().getJSFLibraryReferencebyID(savedImplLib.getID());
			if (selected != null) {
				btnDeployJars.setSelection(selected.isCheckedToBeDeployed());			
				cvImplLib.setSelection(new StructuredSelection(selected), true);
			}
		} else {
			JSFLibraryReference dftJSFImplLib = JSFLibraryRegistryUtil.getInstance().getDefaultJSFImplementationLibrary();
			if (dftJSFImplLib != null) {			
				btnDeployJars.setSelection(dftJSFImplLib.isCheckedToBeDeployed());	
				cvImplLib.setSelection(new StructuredSelection(dftJSFImplLib), true);				
			}
		}
		
		loadJSFCompList();

		JSFLibraryReference savedCompLib = null; 
		JSFLibraryReference selected = null;
		//Iterator it = persistentModel.getJSFComponentLibraries().iterator();
		Iterator it = workingCopyModel.getJSFComponentLibraries().iterator();
		while (it.hasNext()) {
			savedCompLib = (JSFLibraryReference) it.next();
			selected = JSFLibraryRegistryUtil.getInstance().getJSFLibraryReferencebyID(savedCompLib.getID());
			if (selected != null) {
				ctvSelCompLib.setChecked(selected, selected.isCheckedToBeDeployed());
			}
		}

		setCompListModelProperty();
		redraw();
	}
	
	private void loadJSFImplList() {
		cvImplLib.setInput(workingCopyModel.getJSFImplementationLibraries());
	}
	
	private void loadJSFCompList() {
		tvCompLib.setInput(workingCopyModel.getJSFComponentLibraries());
		ctvSelCompLib.setInput(workingCopyModel.getJSFComponentLibraries());		
	}
	
	private JSFLibraryReference getCurrentSelectedJSFImplLib() {
		JSFLibraryReference selJSFImpl = null;
		StructuredSelection objs = (StructuredSelection)cvImplLib.getSelection();
		if (objs != null){
			if (objs.getFirstElement() instanceof JSFLibraryReference){
				selJSFImpl = (JSFLibraryReference)objs.getFirstElement();
			}
		}
		return selJSFImpl;		
	}

	private void createControls() {
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
		btnDeployJars.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (! _initing){
					JSFLibraryReference jsflib = getCurrentSelectedJSFImplLib();
					jsflib.setToBeDeployed(btnDeployJars.getSelection());
					workingCopyModel.setCurrentJSFImplementationLibrarySelection(jsflib);//why r we doing this here???
	//				model.setProperty(IJSFFacetInstallDataModelProperties.DEPLOY_IMPLEMENTATION, btnDeployJars.getSelection());
					fireChangedEvent(e);
				}
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
					JSFLibraryReference crtSelImplLib = (JSFLibraryReference) ss.getFirstElement();
					crtSelImplLib.setToBeDeployed(btnDeployJars.getSelection());
					workingCopyModel.setCurrentJSFImplementationLibrarySelection(crtSelImplLib);
					model.setProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION, crtSelImplLib);
					fireChangedEvent(event);
				}
			}
		);
				
		final Button btnNewImpl = new Button(compTest, SWT.NONE);
		btnNewImpl.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, false, false));
		btnNewImpl.setText(Messages.JSFLibraryConfigControl_NewImplementationLibrary);	
		btnNewImpl.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				JSFLibraryWizard wizard = new JSFLibraryWizard(JSFLibraryWizard.IMPLS);
				IWorkbench wb = PlatformUI.getWorkbench();
				wizard.init(wb, null);
				WizardDialog dialog = new WizardDialog(wb
						.getActiveWorkbenchWindow().getShell(), wizard);
				int ret = dialog.open();
				if (ret == Window.OK) {	
					JSFLibraryReference lib = new JSFLibraryReference(wizard.getJSFLibrary(), true, true);
					JSFLibraryRegistryUtil.getInstance().addJSFLibrary(lib);
					workingCopyModel.getJSFImplementationLibraries().add(lib);
					workingCopyModel.setCurrentJSFImplementationLibrarySelection(lib);
					
					loadJSFImplList();
					btnDeployJars.setSelection(true);
					cvImplLib.setSelection(new StructuredSelection(lib), true);
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
		tvCompLib.addDoubleClickListener(new IDoubleClickListener(){
			public void doubleClick(DoubleClickEvent event) {
				resetComponentLibSelection((StructuredSelection)event.getSelection(), 
						tvCompLib, 
						ctvSelCompLib, 
						true);	
				fireChangedEvent(event);
			}			
		});
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
		btnAdd.setEnabled(false);

		final Button btnRemove = new Button(composite_Single, SWT.NONE);
		btnRemove.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		btnRemove.setText(Messages.JSFLibraryConfigControl_Remove);
		btnRemove.setEnabled(false);
		
		final Composite composite_All = new Composite(composite_buttons, SWT.None);
		composite_All.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		final GridLayout gl_All = new GridLayout();
		gl_Single.marginHeight = 4;
		composite_All.setLayout(gl_All);
		
		btnAddAll = new Button(composite_All, SWT.NONE);
		btnAddAll.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		btnAddAll.setText(Messages.JSFLibraryConfigControl_AddAll);

		btnRemoveAll = new Button(composite_All, SWT.NONE);
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
				JSFLibraryWizard wizard = new JSFLibraryWizard(JSFLibraryWizard.NONIMPLS);				
				IWorkbench wb = PlatformUI.getWorkbench();
				wizard.init(wb, null);
				WizardDialog dialog = new WizardDialog(wb
						.getActiveWorkbenchWindow().getShell(), wizard);						
				int ret = dialog.open();
				if (ret == Window.OK) {
					JSFLibraryReference lib = new JSFLibraryReference(
							wizard.getJSFLibrary(), 
							true, 
							true);
					JSFLibraryRegistryUtil.getInstance().addJSFLibrary(lib);					
					workingCopyModel.getJSFComponentLibraries().add(lib);
					
					loadJSFCompList();
					setCompListModelProperty();
					ctvSelCompLib.setChecked(lib, true);
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
		ctvSelCompLib.addDoubleClickListener(new IDoubleClickListener(){
			public void doubleClick(DoubleClickEvent event) {
				resetComponentLibSelection((StructuredSelection)event.getSelection(), 
						tvCompLib, 
						ctvSelCompLib, 
						false);	
				fireChangedEvent(event);
			}			
		});
		ctvSelCompLib.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				JSFLibraryReference changedItem = (JSFLibraryReference) event.getElement();
				boolean isChecked4Deploy = event.getChecked();
				
				List list = workingCopyModel.getJSFComponentLibraries();
				Iterator it = list.iterator();
				JSFLibraryReference crtjsflib = null;
				while (it.hasNext()) {
					crtjsflib = (JSFLibraryReference) it.next();
					if (crtjsflib.getID().equals(changedItem.getID())) {
						crtjsflib.setToBeDeployed(isChecked4Deploy);
						fireChangedEvent(event);
						break;
					}
				}
			}
		});
		
		btnAdd.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				resetComponentLibSelection((StructuredSelection)tvCompLib.getSelection(), 
						tvCompLib, 
						ctvSelCompLib, 
						true);	
				fireChangedEvent(e);
			}
		});
		
		btnAddAll.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				resetCompontLibSelectionAll(tvCompLib, ctvSelCompLib, true);
				fireChangedEvent(e);
			}
		});		
		btnRemove.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				resetComponentLibSelection((StructuredSelection)ctvSelCompLib.getSelection(), 
						tvCompLib, 
						ctvSelCompLib, 
						false);	
				fireChangedEvent(e);
			}
		});
		
		btnRemoveAll.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				resetCompontLibSelectionAll(tvCompLib, ctvSelCompLib, false);
				fireChangedEvent(e);
			}
		});
		
		tvCompLib.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection sel= (StructuredSelection)event.getSelection();
				btnAdd.setEnabled(!sel.isEmpty() && sel.getFirstElement() instanceof JSFLibraryReference);
				btnAddAll.setEnabled(tvCompLib.getTree().getItemCount() > 0);
			}			
		});
		
		ctvSelCompLib.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection sel= (StructuredSelection)event.getSelection();
				btnRemove.setEnabled(!sel.isEmpty());
				btnRemoveAll.setEnabled(ctvSelCompLib.getTable().getItemCount() > 0);
			}			
		});
	}

	/*
	 * Event handling helper methods
	 */	
	
	// Set selected item to the given state on model and update viewers.  
	private void resetComponentLibSelection(StructuredSelection item, 
			TreeViewer srcViewer, 
			CheckboxTableViewer destViewer,
			boolean state) {
		if (item != null && !item.isEmpty()) {
			List selected = new ArrayList(item.size());
			for (Iterator sel=item.iterator();sel.hasNext();){
				JSFLibraryReference jsfLibDctr = (JSFLibraryReference)sel.next();
				selected.add(jsfLibDctr);
				List list = workingCopyModel.getJSFComponentLibraries();
				Iterator it = list.iterator();
				JSFLibraryReference crtjsfLibDctr = null;
				while(it.hasNext()) {
					crtjsfLibDctr = (JSFLibraryReference)it.next();
					if (crtjsfLibDctr.getID().equals(jsfLibDctr.getID())) {
						crtjsfLibDctr.setToBeDeployed(state);
						crtjsfLibDctr.setSelected(state);
					}
				}					
			}
						
			loadJSFCompList();
			
			srcViewer.refresh();
			destViewer.refresh();	
			for (Iterator it=selected.iterator();it.hasNext();){
				destViewer.setChecked(it.next(), state);
			}
			
			setCompListModelProperty();
		}		
	}
	
	// Reset all component library from given state to model and update viewers.   
	private void resetCompontLibSelectionAll(TreeViewer srcViewer, 
			CheckboxTableViewer destViewer, 
			boolean state) {

			List list = workingCopyModel.getJSFComponentLibraries();
			Iterator it = list.iterator();
			JSFLibraryReference jsfLibDctr;
			while(it.hasNext()) {
				jsfLibDctr = (JSFLibraryReference)it.next();
				jsfLibDctr.setSelected(state);
				jsfLibDctr.setToBeDeployed(state);
			}				
			
			loadJSFCompList();

			srcViewer.refresh();
			destViewer.refresh();
			destViewer.setAllChecked(state);		
			
			btnAddAll.setEnabled(! state);
			btnRemoveAll.setEnabled(state);
			
			setCompListModelProperty();
	}
	
	//synchHelper is not able to track changes to data elements in tableviewer... manual set of property
	private void setCompListModelProperty() {		
		TableItem[] tableItems = ctvSelCompLib.getTable().getItems();
		List compLibs = new ArrayList(tableItems.length);
		for (int i=0;i<tableItems.length;i++){
			compLibs.add(tableItems[i].getData());
		}
		JSFLibraryReference[] libs = (JSFLibraryReference[])compLibs.toArray(new JSFLibraryReference[0]);		
		model.setProperty(IJSFFacetInstallDataModelProperties.COMPONENT_LIBRARIES, libs);		
	}
	

	/**
	 * Configure the JSFLibraryConfigControl elements to used the containers synchHelper
	 * @param synchHelper
	 */
	public void setSynchHelper(DataModelSynchHelper synchHelper) {	
		model = synchHelper.getDataModel();
		synchHelper.synchCombo(cvImplLib.getCombo(), IJSFFacetInstallDataModelProperties.IMPLEMENTATION_LIBRARIES, null);
		synchHelper.synchCheckbox(btnDeployJars, IJSFFacetInstallDataModelProperties.DEPLOY_IMPLEMENTATION, null);
//		synchHelper.synchCheckBoxTableViewer(ctvSelCompLib, IJSFFacetInstallDataModelProperties.COMPONENT_LIBRARIES, new Control[]{hiddenList});
	}

	/**
	 * 	Inner Classes for filtering.
	 *
	 */
	class CheckedTableViewerFilter extends ViewerFilter {
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof JSFLibraryReference) {
				return ((JSFLibraryReference)element).isSelected();
			}
			return false;
		}
	}
	class TreeViewerFilter extends ViewerFilter {

		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof JSFLibraryReference) {
				return !((JSFLibraryReference)element).isSelected();
			}
			return true;
		}
	}

	class CompLibCTVContentProvider implements IStructuredContentProvider {
		private List jsfComplLibs = new ArrayList(0);
		
		public Object[] getElements(Object inputElement) {						
			return jsfComplLibs.toArray();
		}
		public void dispose() {
            // do nothing
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
		private List jsfImplLibs = new ArrayList(0);
		
		public Object[] getElements(Object inputElement) {
			return jsfImplLibs.toArray();
		}
		public void dispose() {
            // do nothing
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
			if (element instanceof JSFLibraryReference){
				
			    switch(columnIndex) {
			    case COLUMN_DEPLOY:
			    	return " ";	  //$NON-NLS-1$
			    case COLUMN_LIB_NAME:
			    	return ((JSFLibraryReference)element).getName();
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
			if (element instanceof JSFLibraryReference){
				StringBuffer nameBuf = new StringBuffer(((JSFLibraryReference)element).getName());
				if ((((JSFLibraryReference)element).getLibrary()).getID().equals(getDefaultImpl().getID()))
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
			if (e1 instanceof JSFLibraryReference && 
					e2 instanceof JSFLibraryReference) {
			JSFLibraryReference item1 = (JSFLibraryReference)e1;
			JSFLibraryReference item2 = (JSFLibraryReference)e2;			
			return item1.getName().compareToIgnoreCase(item2.getName());
			}
			return 0;
		}
	}
	
	/*
	 * Content provider Adapter for TreeViewer
	 */
	private class TreeViewerAdapter implements ITreeContentProvider {
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
			return workingCopyModel.getJSFComponentLibraries().toArray();
		}
		
		public Object[] getChildren(Object element) {
			if (element instanceof JSFLibraryReference) {
				return ((JSFLibraryReference)element).getArchiveFiles().toArray();				
			}
			return NO_ELEMENTS;
		}

		public Object getParent(Object element) {
			return null;
		}

		public boolean hasChildren(Object element) {
			if (element instanceof JSFLibraryReference) {
				return true;
			}
			return false;
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
			if (element instanceof JSFLibraryReference)
            {
			    return libImg;
            }
			return jarImg;
		}

		public String getText(Object element) {
			StringBuffer labelBuf = new StringBuffer();
			if (element instanceof JSFLibraryReference) {
				JSFLibraryReference libWrapper = (JSFLibraryReference)element;
				JSFLibrary lib = libWrapper.getLibrary();
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
            // not handling listeners
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
            // not handling listeners
		}
	}
	
}
