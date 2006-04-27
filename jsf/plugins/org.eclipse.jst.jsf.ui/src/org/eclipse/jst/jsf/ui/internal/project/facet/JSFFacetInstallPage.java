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
package org.eclipse.jst.jsf.ui.internal.project.facet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.ui.IFacetWizardPage;
import org.eclipse.wst.common.project.facet.ui.IWizardContext;

/**
 * JSF Facet installation wizard page.
 * 
 * @author Gerry Kessler - Oracle
 */
public class JSFFacetInstallPage extends DataModelWizardPage implements IJSFFacetInstallDataModelProperties, IFacetWizardPage {
	// UI
	private Label lblJSFImpl;
	private ComboViewer cboJSFImplViewer;
	private Button chkDeployImpl;
	private Button btnAddJSFImpl;
	private Label lblJSFConfig;
	private Text txtJSFConfig;
	private Label lblJSFServletName;
	private Text txtJSFServletName;
	private Label lblJSFServletURLPatterns;
	private List lstJSFServletURLPatterns;
	private Button btnAddPattern;
	private Button btnRemovePattern;

	private IDialogSettings dialogSettings;
	private IDataModel webAppDataModel;
	private static final String SETTINGS_ROOT = JSFUiPlugin.PLUGIN_ID
			+ ".jsfFacetInstall"; //$NON-NLS-1$
	private static final String SETTINGS_CONFIG = "configPath"; //$NON-NLS-1$
	private static final String SETTINGS_SERVLET = "servletName"; //$NON-NLS-1$
	private static final String SETTINGS_URL_MAPPINGS = "urlMappings"; //$NON-NLS-1$
	private static final String SETTINGS_URL_PATTERN = "pattern"; //$NON-NLS-1$
	private static final String SETTINGS_DEPLOY_IMPL = "deployImplementation"; //$NON-NLS-1$

	/**
	 * Zero argument constructor
	 */
	public JSFFacetInstallPage() {
		//FIXME: following WebFacetInstallPage pattern which will be fixed at somepoint
    	super(DataModelFactory.createDataModel(new AbstractDataModelProvider(){}), "jsf.facet.install.page"); //$NON-NLS-1$
		setTitle(Messages.JSFFacetInstallPage_title);
		setDescription(Messages.JSFFacetInstallPage_description);
		dialogSettings = JSFUiPlugin.getDefault().getDialogSettings();

	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite (final Composite parent) {
		initializeDialogUnits(parent);
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));

		lblJSFImpl = new Label(composite, SWT.None);
		lblJSFImpl.setText(Messages.JSFFacetInstallPage_JSFImplLabel);
		lblJSFImpl.setLayoutData(new GridData(GridData.BEGINNING));

		cboJSFImplViewer = new ComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);
		cboJSFImplViewer.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		cboJSFImplViewer.setContentProvider(new IStructuredContentProvider(){
			private java.util.List jsfImpls = new ArrayList(Collections.EMPTY_LIST);

			public void dispose() {
			}

			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				if (newInput == null)
					jsfImpls = Collections.EMPTY_LIST;
				else {
					jsfImpls.clear();
					jsfImpls.addAll((EList)newInput);
				}
			}			
			public Object[] getElements(Object inputElement) {				
				return jsfImpls.toArray(); 
			}			
		});
		cboJSFImplViewer.setLabelProvider(new ILabelProvider(){
			private JSFLibrary defaultImpl = null;

			public String getText(Object element) {
				if (element instanceof JSFLibrary){
					StringBuffer nameBuf = new StringBuffer(((JSFLibrary)element).getName());
					if (((JSFLibrary)element) == getDefaultImpl())
						nameBuf.append(" ").append(JSFLibraryRegistry.DEFAULT_IMPL_LABEL); //$NON-NLS-1$
					return nameBuf.toString() ;
				}
				return null;
			}

			private JSFLibrary getDefaultImpl() {
				if (defaultImpl == null){
					defaultImpl = JSFCorePlugin.getDefault().getJSFLibraryRegistry().getDefaultImplementation();
				}
				return defaultImpl;
			}

			public Image getImage(Object element) {return null;}
			public void addListener(ILabelProviderListener listener) {}
			public void dispose() {}
			public boolean isLabelProperty(Object element, String property) {return false;}
			public void removeListener(ILabelProviderListener listener) {}			
		});

		btnAddJSFImpl = new Button(composite, SWT.COMMAND);
		btnAddJSFImpl.setText(Messages.JSFFacetInstallPage_Add1);
		btnAddJSFImpl.setLayoutData(new GridData(GridData.END));
		btnAddJSFImpl.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				JSFLibraryWizard wizard = new JSFLibraryWizard(true);
				IWorkbench wb = PlatformUI.getWorkbench();
				wizard.init(wb, null);
				WizardDialog dialog = new WizardDialog(wb
						.getActiveWorkbenchWindow().getShell(), wizard);
				int ret = dialog.open();
				if (ret == Window.OK) {
					loadJSFImplList();
					//select the new item
					cboJSFImplViewer.setSelection(new StructuredSelection(wizard.getJSFLibrary()), true);
//					addItemToList(wizard.getJSFLibrary().getName(), true);
					validatePage();
				}
			}
		});

		Label lblSpacer = new Label(composite, SWT.NONE);
		lblSpacer.setLayoutData(new GridData(GridData.BEGINNING));

		chkDeployImpl = new Button(composite, SWT.CHECK);
		chkDeployImpl.setText(Messages.JSFFacetInstallPage_DeployJarsLabel);
		GridData gdd = new GridData(GridData.FILL_HORIZONTAL);
		gdd.horizontalSpan = 2;
		chkDeployImpl.setLayoutData(gdd);

		lblJSFConfig = new Label(composite, SWT.NONE);
		lblJSFConfig.setText(Messages.JSFFacetInstallPage_JSFConfigLabel);
		lblJSFConfig.setLayoutData(new GridData(GridData.BEGINNING));

		txtJSFConfig = new Text(composite, SWT.BORDER);
		// txtJSFConfig.setText(config.getDefaultJSFConfigPath());
		GridData gd1 = new GridData(GridData.FILL_HORIZONTAL);
		gd1.horizontalSpan = 2;
		txtJSFConfig.setLayoutData(gd1);

		lblJSFServletName = new Label(composite, SWT.NONE);
		lblJSFServletName.setText(Messages.JSFFacetInstallPage_JSFServletNameLabel);
		lblJSFServletName.setLayoutData(new GridData(GridData.BEGINNING));

		txtJSFServletName = new Text(composite, SWT.BORDER);
		// txtJSFServletName.setText(config.getDefaultJSFServletName());
		GridData gd2 = new GridData(GridData.FILL_HORIZONTAL);
		gd2.horizontalSpan = 2;
		txtJSFServletName.setLayoutData(gd2);

		lblJSFServletURLPatterns = new Label(composite, SWT.NULL);
		lblJSFServletURLPatterns.setText(Messages.JSFFacetInstallPage_JSFURLMappingLabel);
		lblJSFServletURLPatterns.setLayoutData(new GridData(GridData.BEGINNING
				| GridData.VERTICAL_ALIGN_BEGINNING));
		lstJSFServletURLPatterns = new List(composite, SWT.BORDER);
		GridData gd3 = new GridData(GridData.FILL_HORIZONTAL);
		gd3.heightHint = convertHeightInCharsToPixels(10);
		lstJSFServletURLPatterns.setLayoutData(gd3);
		lstJSFServletURLPatterns.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				btnRemovePattern.setEnabled(lstJSFServletURLPatterns
						.getSelectionCount() > 0);
			}
		});

		Composite btnComposite = new Composite(composite, SWT.NONE);
		GridLayout gl = new GridLayout(1, false);
		// gl.marginBottom = 0;
		// gl.marginTop = 0;
		// gl.marginRight = 0;
		gl.marginLeft = 0;
		btnComposite.setLayout(gl);
		btnComposite.setLayoutData(new GridData(GridData.END
				| GridData.VERTICAL_ALIGN_FILL));

		btnAddPattern = new Button(btnComposite, SWT.NONE);
		btnAddPattern.setText(Messages.JSFFacetInstallPage_Add2);
		btnAddPattern.setLayoutData(new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING));
		btnAddPattern.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				InputDialog dialog = new InputDialog(
						getShell(),
						Messages.JSFFacetInstallPage_PatternDialogTitle,
						Messages.JSFFacetInstallPage_PatternDialogDesc,
						null, new IInputValidator() {

							public String isValid(String newText) {
								return isValidPattern(newText);
							}

						});
				dialog.open();
				if (dialog.getReturnCode() == Window.OK) {
					addItemToList(dialog.getValue(), true);
				}
			}
		});

		btnRemovePattern = new Button(btnComposite, SWT.NONE);
		btnRemovePattern.setText(Messages.JSFFacetInstallPage_Remove);
		btnRemovePattern.setLayoutData(new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING));
		btnRemovePattern.setEnabled(false);
		btnRemovePattern.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				removeItemFromList(lstJSFServletURLPatterns.getSelection());
				btnRemovePattern.setEnabled(false);
			}
		});

		loadJSFImplList();
		addModificationListeners();

		return composite;
	}

	private void initializeValues() {
		IDialogSettings root = dialogSettings.getSection(SETTINGS_ROOT);

		JSFLibrary impl = (JSFLibrary)model.getDefaultProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION);
		if (impl != null){
			cboJSFImplViewer.setSelection(new StructuredSelection(impl), true);
		} else {
			cboJSFImplViewer.setSelection(StructuredSelection.EMPTY);
		}

		boolean deploy = ((Boolean)model.getDefaultProperty(IJSFFacetInstallDataModelProperties.DEPLOY_IMPLEMENTATION)).booleanValue();
		String deployStr = null;
		if (root != null)
			deployStr = root.get(SETTINGS_DEPLOY_IMPL);
		if (deployStr != null && !deployStr.equals("")) { //$NON-NLS-1$
			deploy = Boolean.valueOf(deployStr).booleanValue();
		}
		chkDeployImpl.setSelection(deploy);

		String conf = null;
		if (root != null)
			conf = root.get(SETTINGS_CONFIG);
		if (conf == null || conf.equals("")) { //$NON-NLS-1$
			conf = (String)model.getDefaultProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH);
		}
		txtJSFConfig.setText(conf);

		String servletName = null;
		if (root != null)
			servletName = root.get(SETTINGS_SERVLET);
		if (servletName == null || servletName.equals("")) { //$NON-NLS-1$
			servletName = (String)model.getDefaultProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME);
		}
		txtJSFServletName.setText(servletName);

		loadURLMappingPatterns(root);
	}

	private void saveSettings() {
		DialogSettings root = new DialogSettings(SETTINGS_ROOT);
		dialogSettings.addSection(root);

		root.put(SETTINGS_DEPLOY_IMPL, String.valueOf(getDeployJSFImpl()));
		root.put(SETTINGS_CONFIG, getJSFConfig());
		root.put(SETTINGS_SERVLET, getJSFServletName());
		DialogSettings mappings = new DialogSettings(SETTINGS_URL_MAPPINGS);
		root.addSection(mappings);
		mappings.put(SETTINGS_URL_PATTERN, getJSFPatterns());
	}

	private boolean getDeployJSFImpl() {
		return chkDeployImpl.getSelection();
	}

	private String getJSFConfig() {
		return txtJSFConfig.getText().trim();
	}

	private String getJSFServletName() {
		return txtJSFServletName.getText().trim();
	}

	private String[] getJSFPatterns() {
		return lstJSFServletURLPatterns.getItems();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.project.facet.ui.IFacetWizardPage#setConfig(java.lang.Object)
	 */
	public void setConfig(Object config) {
    	model.removeListener(this);
    	synchHelper.dispose();
    	
        model = (IDataModel) config;
        model.addListener(this);
		synchHelper = initializeSynchHelper(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.project.facet.ui.IFacetWizardPage#transferStateToConfig()
	 */
	public void transferStateToConfig() {
		saveSettings(); //convenient place for this.  don't want to save if user cancelled.
		//do nothing else now.  being handled by synchHelper
//		config.setProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION, getJSFImpl());
//		config.setStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH, getJSFConfig());
//		config.setStringProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME, getJSFServletName());
//		config.setProperty(IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS, getJSFPatterns());
	}

	private void addModificationListeners() {
//		119330 - enhancement request for ComboViewer support.  Manually update model for now
		addJSFImplComboListeners();
//		synchHelper.synchComboViewer(cboJSFImplViewer, IMPLEMENTATION, null);
		synchHelper.synchText(txtJSFConfig, CONFIG_PATH, null);
		synchHelper.synchText(txtJSFServletName, SERVLET_NAME, null);
		synchHelper.synchCheckbox(chkDeployImpl, DEPLOY_IMPLEMENTATION, null);
//		Until 119321 is fixed, need to comment out below and handle model updates 'manually'.  
//		This is being done on Add and Remove, currently
//		synchHelper.synchList(lstJSFServletURLPatterns, SERVLET_URL_PATTERNS, null);
	}

	private void addJSFImplComboListeners() {
		cboJSFImplViewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event) {
				if (cboJSFImplViewer.getSelection() != null  && cboJSFImplViewer.getSelection() instanceof StructuredSelection){
					StructuredSelection sel = (StructuredSelection)cboJSFImplViewer.getSelection();
					if (sel.getFirstElement() != null){
						JSFLibrary lib = (JSFLibrary)sel.getFirstElement();
						model.setProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION, lib);
						return;
					}
				}
				model.setProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION, null );
			}			
		});
	}

	private String isValidPattern(String value) {
		if (value == null || value.trim().equals(""))  //$NON-NLS-1$
			return Messages.JSFFacetInstallPage_PatternEmptyMsg;
		if (lstJSFServletURLPatterns.indexOf(value) >= 0)
			return Messages.JSFFacetInstallPage_PatternSpecifiedMsg;

		return null;
	}

	private void loadURLMappingPatterns(IDialogSettings root) {
		lstJSFServletURLPatterns.removeAll();
		IDialogSettings mappings = null;
		if (root != null)
			mappings = root.getSection(SETTINGS_URL_MAPPINGS);
		String[] patterns = null;
		if (mappings != null)
			patterns = mappings.getArray(SETTINGS_URL_PATTERN);

		if (patterns == null || patterns.length == 0) {
			patterns = (String[])model.getDefaultProperty(IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS);
		}
		for (int i = 0; i < patterns.length; i++) {
			addItemToList(patterns[i], false);
		}
	}

	private void addItemToList(String pattern, boolean selectMe) {
		lstJSFServletURLPatterns.add(pattern == null ? "" : pattern); //$NON-NLS-1$
		if (pattern == null && selectMe)
			lstJSFServletURLPatterns.setSelection(lstJSFServletURLPatterns
					.getItemCount() - 1);
//		When 119321 is fixed - remove code below
		updateModelForURLPattern();
	}

	private void removeItemFromList(String[] selection) {
		for (int i = 0; i < selection.length; i++) {
			String sel = selection[i];
			lstJSFServletURLPatterns.remove(sel);
		}
//		When 119321 is fixed - remove code below 
		updateModelForURLPattern();
	}

	private void updateModelForURLPattern() {
		model.setProperty(IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS, lstJSFServletURLPatterns.getItems());
	}

	private void loadJSFImplList() {
		cboJSFImplViewer.setInput(JSFCorePlugin.getDefault().getJSFLibraryRegistry()
			.getImplJSFLibraries());		
	}

	protected String[] getValidationPropertyNames() {
		return new String[]{IMPLEMENTATION, DEPLOY_IMPLEMENTATION, CONFIG_PATH, SERVLET_NAME};
	}

	public void setWizardContext(IWizardContext context) {
		//hook into web datamodel if new project wizard.
		Iterator it = context.getSelectedProjectFacets().iterator();
		IProjectFacetVersion webFacetVersion = null;
		while (it.hasNext()){
			//find Web facet
			IProjectFacetVersion pfv = (IProjectFacetVersion)it.next();
			if (pfv.getProjectFacet().getId().equals("jst.web")){ //$NON-NLS-1$
				webFacetVersion = pfv;
				break;
			}
		}
		if (webFacetVersion != null){
			try {
				webAppDataModel = (IDataModel)context.getConfig(webFacetVersion, IFacetedProject.Action.Type.INSTALL, 
						context.getProjectName());
				if (webAppDataModel != null){
					webAppDataModel.addListener(this);
				}
			} catch (CoreException e) {
				JSFUiPlugin.log(
						IStatus.ERROR,
						Messages.JSFFacetInstallPage_ErrorNoWebAppDataModel,
						e);
			}
		}
	}

	public void propertyChanged(DataModelEvent event) {
		if (webAppDataModel != null){
			String propertyName = event.getPropertyName();
			if (propertyName.equals(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER)){
				model.setStringProperty(WEBCONTENT_DIR, event.getProperty().toString());
			}
		}
		super.propertyChanged(event);
	}

	public void dispose() {
		if (webAppDataModel != null)
			webAppDataModel.removeListener(this);
		super.dispose();
	}
	protected void restoreDefaultSettings() {
		initializeValues();
	}
}
