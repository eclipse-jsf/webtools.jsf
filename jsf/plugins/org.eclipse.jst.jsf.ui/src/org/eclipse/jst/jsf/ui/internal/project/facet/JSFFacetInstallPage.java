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
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfigDialogSettingData;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfiglModelSource;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryInternalReference;
import org.eclipse.jst.jsf.core.internal.project.facet.IJSFFacetInstallDataModelProperties;
import org.eclipse.jst.jsf.ui.internal.JSFUiPlugin;
import org.eclipse.jst.jsf.ui.internal.Messages;
import org.eclipse.jst.jsf.ui.internal.jsflibraryconfig.JSFLibraryConfigControlChangeEvent;
import org.eclipse.jst.jsf.ui.internal.jsflibraryconfig.JSFLibraryConfigControlChangeListener;
import org.eclipse.jst.jsf.ui.internal.jsflibraryconfig.IJSFImplLibraryCreationListener;
import org.eclipse.jst.jsf.ui.internal.jsflibraryconfig.JSFImplLibraryCreationEvent;
import org.eclipse.jst.jsf.ui.internal.jsflibraryconfig.JSFLibraryConfigControl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
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
public class JSFFacetInstallPage extends DataModelWizardPage implements
		IJSFFacetInstallDataModelProperties, IFacetWizardPage {
	// UI
	private Label lblJSFImpl;
	private Label lblJSFConfig;
	private Text txtJSFConfig;
	private Label lblJSFServletName;
	private Text txtJSFServletName;
	private Label lblJSFServletClassName;
	private Text txtJSFServletClassName;	
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
	private static final String SETTINGS_SERVLET_CLASSNAME = "servletClassname"; //$NON-NLS-1$
	private static final String SETTINGS_URL_MAPPINGS = "urlMappings"; //$NON-NLS-1$
	private static final String SETTINGS_URL_PATTERN = "pattern"; //$NON-NLS-1$
	private static final String SETTINGS_DEPLOY_IMPL = "deployImplementation"; //$NON-NLS-1$
	private static final String SETTINGS_IMPL_TYPE = "deployImplType";//$NON-NLS-1$
	private static final String SETTINGS_COMPLIB = "selectedComponent"; //$NON-NLS-1$
	private static final String SETTINGS_COMPLIB_SELECT_DEPLOY = "selectdeploycomplib"; //$NON-NLS-1$

	private static final String SEPARATOR = ":"; //$NON-NLS-1$

	private JSFLibraryConfigControl jsfLibCfgComp = null;
	// private String projectName = null;
	private Composite composite = null;

	/**
	 * Zero argument constructor
	 */
	public JSFFacetInstallPage() {
		// FIXME: following WebFacetInstallPage pattern which will be fixed at
		// somepoint
		super(DataModelFactory.createDataModel(new AbstractDataModelProvider() {/*
																				 * do
																				 * nothing
																				 */
		}), "jsf.facet.install.page"); //$NON-NLS-1$
		setTitle(Messages.JSFFacetInstallPage_title);
		setDescription(Messages.JSFFacetInstallPage_description);
		dialogSettings = JSFUiPlugin.getDefault().getDialogSettings();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite(final Composite parent) {
		initializeDialogUnits(parent);
		composite = new Composite(parent, SWT.NONE);
		final GridLayout jsfCompositeLayout = new GridLayout(3, false);
		jsfCompositeLayout.marginTop = 0;
		jsfCompositeLayout.marginBottom = 0;
		jsfCompositeLayout.marginRight = 0;
		jsfCompositeLayout.marginLeft = 0;
		composite.setLayout(jsfCompositeLayout);

		lblJSFImpl = new Label(composite, SWT.None);
		GridData gdLbl = new GridData();
		gdLbl.verticalIndent = 5;
		gdLbl.verticalAlignment = SWT.BEGINNING;
		lblJSFImpl.setLayoutData(gdLbl);
		lblJSFImpl.setText(Messages.JSFFacetInstallPage_JSFLibraryLabel0);
		
		jsfLibCfgComp = new JSFLibraryConfigControl(composite, SWT.NONE);
		jsfLibCfgComp
				.addOkClickedListener(new IJSFImplLibraryCreationListener() {
					public void okClicked(JSFImplLibraryCreationEvent event) {
						if (event.isLibraryCreated()) {
							validatePage();
						}
					}
				});
		
		jsfLibCfgComp.addChangeListener( new JSFLibraryConfigControlChangeListener(){

			public void changed(JSFLibraryConfigControlChangeEvent e) {
				validatePage();				
			}
			
		});
		GridData gd_comp = new GridData();
		gd_comp.horizontalSpan = 2;
		gd_comp.grabExcessHorizontalSpace = true;
		gd_comp.grabExcessVerticalSpace = true;
		gd_comp.horizontalAlignment = SWT.FILL;
		jsfLibCfgComp.setLayoutData(gd_comp);

		lblJSFConfig = new Label(composite, SWT.NONE);
		lblJSFConfig.setText(Messages.JSFFacetInstallPage_JSFConfigLabel);
		lblJSFConfig.setLayoutData(new GridData(GridData.BEGINNING));

		txtJSFConfig = new Text(composite, SWT.BORDER);
		GridData gd1 = new GridData(GridData.FILL_HORIZONTAL);
		gd1.horizontalSpan = 2;
		txtJSFConfig.setLayoutData(gd1);

		lblJSFServletName = new Label(composite, SWT.NONE);
		lblJSFServletName
				.setText(Messages.JSFFacetInstallPage_JSFServletNameLabel);
		lblJSFServletName.setLayoutData(new GridData(GridData.BEGINNING));

		txtJSFServletName = new Text(composite, SWT.BORDER);
		GridData gd2 = new GridData(GridData.FILL_HORIZONTAL);
		gd2.horizontalSpan = 2;
		txtJSFServletName.setLayoutData(gd2);

		lblJSFServletClassName = new Label(composite, SWT.NONE);
		lblJSFServletClassName
				.setText(Messages.JSFFacetInstallPage_JSFServletClassNameLabel);
		lblJSFServletClassName.setLayoutData(new GridData(GridData.BEGINNING));

		txtJSFServletClassName = new Text(composite, SWT.BORDER);
		GridData gd2c = new GridData(GridData.FILL_HORIZONTAL);
		gd2c.horizontalSpan = 2;
		txtJSFServletClassName.setLayoutData(gd2c);
		
		lblJSFServletURLPatterns = new Label(composite, SWT.NULL);
		lblJSFServletURLPatterns
				.setText(Messages.JSFFacetInstallPage_JSFURLMappingLabel);
		lblJSFServletURLPatterns.setLayoutData(new GridData(GridData.BEGINNING
				| GridData.VERTICAL_ALIGN_BEGINNING));
		lstJSFServletURLPatterns = new List(composite, SWT.BORDER);
		GridData gd3 = new GridData(GridData.FILL_HORIZONTAL);
		gd3.heightHint = convertHeightInCharsToPixels(5);
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
				InputDialog dialog = new InputDialog(getShell(),
						Messages.JSFFacetInstallPage_PatternDialogTitle,
						Messages.JSFFacetInstallPage_PatternDialogDesc, null,
						new IInputValidator() {

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

		addModificationListeners();

		this.getContainer().getShell().pack();

		return composite;
	}

	private void initializeValues() {
		IDialogSettings root = dialogSettings.getSection(SETTINGS_ROOT);

		initJSFCfgCtrlValues(root);

		String conf = null;
		if (root != null)
			conf = root.get(SETTINGS_CONFIG);
		if (conf == null || conf.equals("")) { //$NON-NLS-1$
			conf = (String) model
					.getDefaultProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH);
		}
		txtJSFConfig.setText(conf);

		String servletName = null;
		if (root != null)
			servletName = root.get(SETTINGS_SERVLET);
		if (servletName == null || servletName.equals("")) { //$NON-NLS-1$
			servletName = (String) model
					.getDefaultProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME);
		}
		txtJSFServletName.setText(servletName);

		String servletClassname = null;
		if (root != null)
			servletClassname = root.get(SETTINGS_SERVLET_CLASSNAME);
		if (servletClassname == null || servletClassname.equals("")) { //$NON-NLS-1$
			servletClassname = (String) model
					.getDefaultProperty(IJSFFacetInstallDataModelProperties.SERVLET_CLASSNAME);
		}
		txtJSFServletClassName.setText(servletClassname);

		loadURLMappingPatterns(root);
	}

	private void initJSFCfgCtrlValues(IDialogSettings root) {
		IMPLEMENTATION_TYPE implType = IMPLEMENTATION_TYPE.UNKNOWN;
		String deployImpl = null;
		
		if (root != null) {
			implType = IMPLEMENTATION_TYPE.getValue(root.get(SETTINGS_IMPL_TYPE));
			deployImpl = root.get(SETTINGS_DEPLOY_IMPL);
			
		}

		if (deployImpl == null || deployImpl.equals("")) { //$NON-NLS-1$
			deployImpl = ((Boolean) model
					.getDefaultProperty(IJSFFacetInstallDataModelProperties.DEPLOY_IMPLEMENTATION))
					.toString();
		}

		IDialogSettings complibs = null;
		if (root != null) {
			complibs = root.getSection(SETTINGS_COMPLIB);
		}

		String[] selection = null;
		if (complibs != null) {
			selection = complibs.getArray(SETTINGS_COMPLIB_SELECT_DEPLOY);
		}

		JSFLibraryConfiglModelSource source = new JSFLibraryConfigDialogSettingData(implType,
				Boolean.valueOf(deployImpl).booleanValue(), selection);
		jsfLibCfgComp.loadControlValuesFromModel(source);
	}

	
	private void saveSettings() {
		DialogSettings root = new DialogSettings(SETTINGS_ROOT);
		dialogSettings.addSection(root);

		root.put(SETTINGS_IMPL_TYPE, getJSFImplType());
		root.put(SETTINGS_DEPLOY_IMPL, String.valueOf(getDeployJSFImpl()));
		root.put(SETTINGS_CONFIG, getJSFConfig());
		root.put(SETTINGS_SERVLET, getJSFServletName());
		root.put(SETTINGS_SERVLET_CLASSNAME, getJSFServletClassname());
		DialogSettings mappings = new DialogSettings(SETTINGS_URL_MAPPINGS);
		root.addSection(mappings);
		mappings.put(SETTINGS_URL_PATTERN, getJSFPatterns());

		DialogSettings complibs = new DialogSettings(SETTINGS_COMPLIB);
		root.addSection(complibs);
		complibs.put(SETTINGS_COMPLIB_SELECT_DEPLOY, getCompLibSelections());
	}

	private String getJSFImplType() {
		return IMPLEMENTATION_TYPE.getStringValue((IMPLEMENTATION_TYPE)model.getProperty(IMPLEMENTATION_TYPE_PROPERTY_NAME));
	}

	private boolean getDeployJSFImpl() {
		if (jsfLibCfgComp.getSelectedJSFLibImplementation() == null) {
			return false;
		}
		return jsfLibCfgComp.getSelectedJSFLibImplementation()
				.isCheckedToBeDeployed();
	}

	private String getJSFConfig() {
		return txtJSFConfig.getText().trim();
	}

	private String getJSFServletName() {
		return txtJSFServletName.getText().trim();
	}

	private String getJSFServletClassname() {
		return txtJSFServletClassName.getText().trim();
	}
	
	private String[] getJSFPatterns() {
		return lstJSFServletURLPatterns.getItems();
	}

	private String[] getCompLibSelections() {
		/*
		 * Iterate thru the selected component libraries and return selected
		 * component libraries and their deployment flags in a string array.
		 */
		JSFLibraryInternalReference complib = null;
		String str = null;
		ArrayList al = new ArrayList();

		java.util.List list = jsfLibCfgComp.getSelectedJSFLibComponents();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			complib = (JSFLibraryInternalReference) it.next();
			str = complib.getID() + SEPARATOR + complib.isCheckedToBeDeployed();
			al.add(str);
		}

		return (String[]) al.toArray(new String[al.size()]);
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
		saveSettings(); // convenient place for this. don't want to save if user
						// cancelled.
		// do nothing else now. being handled by synchHelper
		// config.setProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION,
		// getJSFImpl());
//		model
//				.setStringProperty(
//						IJSFFacetInstallDataModelProperties.CONFIG_PATH,
//						getJSFConfig());
//		model.setStringProperty(
//				IJSFFacetInstallDataModelProperties.SERVLET_NAME,
//				getJSFServletName());
		// config.setProperty(IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS,
		// getJSFPatterns());

//		java.util.List implLibs = new ArrayList();
//		implLibs.add(jsfLibCfgComp.getSelectedJSFLibImplementation());
//		java.util.List compLibs = jsfLibCfgComp.getSelectedJSFLibComponents();
//		model.setProperty(
//				IJSFFacetInstallDataModelProperties.IMPLEMENTATION_LIBRARIES,
//				implLibs);
//		model.setProperty(
//				IJSFFacetInstallDataModelProperties.COMPONENT_LIBRARIES,
//				compLibs);
	}

	private void addModificationListeners() {
		 jsfLibCfgComp.setSynchHelper(synchHelper);		 
		 synchHelper.synchText(txtJSFConfig, CONFIG_PATH, null);
		 synchHelper.synchText(txtJSFServletName, SERVLET_NAME, null);
		 synchHelper.synchText(txtJSFServletClassName, SERVLET_CLASSNAME, null);
		 synchHelper.synchList(lstJSFServletURLPatterns, SERVLET_URL_PATTERNS, null);
		 
		// synchHelper.synchCheckbox(chkDeployImpl, DEPLOY_IMPLEMENTATION,
		// null);
	}

	private String isValidPattern(String value) {
		if (value == null || value.trim().equals("")) //$NON-NLS-1$
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
			patterns = (String[]) model
					.getDefaultProperty(IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS);
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
		// When 119321 is fixed - remove code below
		updateModelForURLPattern();
	}

	private void removeItemFromList(String[] selection) {
		for (int i = 0; i < selection.length; i++) {
			String sel = selection[i];
			lstJSFServletURLPatterns.remove(sel);
		}
		// When 119321 is fixed - remove code below
		updateModelForURLPattern();
	}

	private void updateModelForURLPattern() {
		model.setProperty(
				IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS,
				lstJSFServletURLPatterns.getItems());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[] { IMPLEMENTATION_TYPE_PROPERTY_NAME, IMPLEMENTATION, DEPLOY_IMPLEMENTATION,
				CONFIG_PATH, SERVLET_NAME, SERVLET_CLASSNAME, COMPONENT_LIBRARIES };
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.project.facet.ui.IFacetWizardPage#setWizardContext(org.eclipse.wst.common.project.facet.ui.IWizardContext)
	 */
	public void setWizardContext(IWizardContext context) {
		// hook into web datamodel if new project wizard.
		Iterator it = context.getSelectedProjectFacets().iterator();
		IProjectFacetVersion webFacetVersion = null;
		while (it.hasNext()) {
			// find Web facet
			IProjectFacetVersion pfv = (IProjectFacetVersion) it.next();
			if (pfv.getProjectFacet().getId().equals("jst.web")) { //$NON-NLS-1$
				webFacetVersion = pfv;
				break;
			}
		}
		if (webFacetVersion != null) {
			try {
				webAppDataModel = (IDataModel) context.getConfig(
						webFacetVersion, IFacetedProject.Action.Type.INSTALL,
						context.getProjectName());
				if (webAppDataModel != null) {
					webAppDataModel.addListener(this);
				}
			} catch (CoreException e) {
				JSFUiPlugin.log(IStatus.ERROR,
						Messages.JSFFacetInstallPage_ErrorNoWebAppDataModel, e);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage#propertyChanged(org.eclipse.wst.common.frameworks.datamodel.DataModelEvent)
	 */
	public void propertyChanged(DataModelEvent event) {
		if (webAppDataModel != null) {
			String propertyName = event.getPropertyName();
			if (propertyName
					.equals(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER)) {
				model.setStringProperty(WEBCONTENT_DIR, event.getProperty()
						.toString());
			}
		}
		super.propertyChanged(event);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage#dispose()
	 */
	public void dispose() {
		if (webAppDataModel != null)
			webAppDataModel.removeListener(this);
		
		jsfLibCfgComp.dispose();
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage#restoreDefaultSettings()
	 */
	protected void restoreDefaultSettings() {
		initializeValues();

		checkToCompletePage(jsfLibCfgComp);
	}

	/*
	 * To force a JSF facet install page configuration to be performed    
	 * when the JSF facet is selected but no JSF implementation library exists.
	 */
	private void checkToCompletePage(Composite control) {
		boolean enableFinish = false;
		IMPLEMENTATION_TYPE implType = (IMPLEMENTATION_TYPE)model.getProperty(IMPLEMENTATION_TYPE_PROPERTY_NAME);
		if (implType == IMPLEMENTATION_TYPE.SERVER_SUPPLIED)
			enableFinish = true;
		else if (implType == IMPLEMENTATION_TYPE.CLIENT_SUPPLIED && 
				control != null && control instanceof JSFLibraryConfigControl) {
			enableFinish = (((JSFLibraryConfigControl)control).getSelectedJSFLibImplementation() != null);
		} //else must be unknown type and requires user interaction
		setPageComplete(enableFinish);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage#showValidationErrorsOnEnter()
	 */
	protected boolean showValidationErrorsOnEnter() {
		return true;
	}


}
