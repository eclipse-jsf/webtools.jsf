/*******************************************************************************
 * Copyright (c) 2004, 2011 Sybase, Inc. and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License 2.0 which accompanies this distribution, and is
 * available at https://www.eclipse.org/legal/epl-2.0/ Contributors: Sybase,
 * Inc. - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jst.jsf.common.ui.internal.guiutils.SWTUtils;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigUtils;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.section.ManagedBeanScopeTreeItem;
import org.eclipse.jst.jsf.facesconfig.ui.util.JavaClassUtils;
import org.eclipse.jst.jsf.facesconfig.ui.util.ManagedBeanUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * ManangedBeanProperty wizard page used to show properties of the previous
 * selected java class, suade services. and set the default values for each
 * property.
 * 
 * @author Xiao-guang Zhang, sfshi
 */
public class ManagedBeanPropertyPage extends WizardPage implements ISummaryDataSource {
	/** Default height of description Text Control */
	private static final int DESCRIPTION_TEXT_HEIGHT = 60;

	private static final int DEFAULT_WIDTHHINT = 10;

	/** The Text control for managed bean instance name */
	private Text managedBeanNameText;

	/** The Combo control for Project Selection */
	private Combo scopeCombo;

	/** The Text control for managed bean's description */
	private Text managedBeanDescriptionText;

	private String helpID = null;

	private IProject currentProject;

	private String defaultScope;

	/**
	 * @param defaultScope
	 *            the default scope, if be null, then use "session".
	 * @param project
	 */
	public ManagedBeanPropertyPage(String defaultScope, IProject project) {
		super("ManagedBeanPropertyWizardPage"); //$NON-NLS-1$);

		this.defaultScope = defaultScope;
		currentProject = project;
		setTitle(WizardMessages.ManagedBeanPropertyWizardPage_Title);
		setDescription(WizardMessages.ManagedBeanPropertyWizardPage_Description);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see WizardPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);

		Composite container = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 1;
		container.setLayout(gl);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		container.setLayoutData(gridData);

		createGeneralSection(container);

		setControl(container);

		setPageComplete(false);

		if (helpID != null) {
			EditorPlugin.getDefault().getWorkbench().getHelpSystem().setHelp(
					getControl(), helpID);
		}
	}

	/**
	 * create managed bean 's configuration group
	 * 
	 * @param container
	 */
	private void createGeneralSection(Composite container) {
		Group generalSection = new Group(container, SWT.NONE);

		// ManagedBeanPropertyWizardPage.General = General
		generalSection
				.setText(WizardMessages.ManagedBeanPropertyWizardPage_General);

		GridLayout gl = new GridLayout();
		// gl.marginHeight = 20;
		gl.numColumns = 2;
		generalSection.setLayout(gl);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		generalSection.setLayoutData(gridData);

		SWTUtils
				.createLabel(
						generalSection,
						WizardMessages.ManagedBeanPropertyWizardPage_ManagedBeanName,
						1);

		managedBeanNameText = SWTUtils.createTextBox(generalSection, 1);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 1;
		gd.widthHint = DEFAULT_WIDTHHINT;
		managedBeanNameText.setLayoutData(gd);

		managedBeanNameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				// dialogChanged();
				if (isValidManagedBeanName(true)) {
					setPageComplete(true);
				} else {
					setPageComplete(false);
				}
			}
		});

		SWTUtils
				.createLabel(
						generalSection,
						WizardMessages.ManagedBeanPropertyWizardPage_ManagedBeanScope,
						1);

		//Bug 312727 - [JSF2.0] Add view scope to FacesConfigEditor for Managed Beans
		String[] items = ManagedBeanScopeTreeItem.scopeItems;
		if (currentProject != null) {
			if (!JSFAppConfigUtils.isValidJSFProject(currentProject, IJSFCoreConstants.FACET_VERSION_2_0)) {
				items = ManagedBeanScopeTreeItem.scopeItemsPreJSF2;
			}
		}

		scopeCombo = SWTUtils.createCombo(generalSection, items, 1);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 1;
		gd.widthHint = DEFAULT_WIDTHHINT;
		scopeCombo.setLayoutData(gd);

		Label labelDesp = SWTUtils
				.createLabel(
						generalSection,
						WizardMessages.ManagedBeanPropertyWizardPage_ManagedBeanDescription,
						1);

		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		labelDesp.setLayoutData(gd);

		managedBeanDescriptionText = SWTUtils.createTextBoxScrollable(
				generalSection, 1, -1, DESCRIPTION_TEXT_HEIGHT);

	}

	/**
	 * Validates the managed bean Name entry
	 * 
	 * @param reportError -
	 *            true, to report errors.
	 * @return boolean - the valid state of the data entered
	 */
	private boolean isValidManagedBeanName(boolean reportError) {
		if (managedBeanNameText.getText().length() == 0) {
			if (reportError) {
				setErrorMessage(WizardMessages.ManagedBeanPropertyWizardPage_Warning_MissingManagedBeanName);
			}
			return false;
		} else if (JavaClassUtils.hasIllegalCharacters(managedBeanNameText
				.getText())) {
			if (reportError) {
				setErrorMessage(WizardMessages.ManagedBeanPropertyWizardPage_Warning_InvalidManagedBeanName);
			}
			return false;
		} else {
			if (ManagedBeanUtil.isBeanDuplicate(currentProject,
					managedBeanNameText.getText())) {
				if (reportError) {
					setErrorMessage(WizardMessages.ManagedBeanPropertyWizardPage_Warning_DuplicateManagedBeanName);
				}
				return false;
			}

		}

		setErrorMessage(null);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizardPage#setPreviousPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	public void setPreviousPage(IWizardPage page) {
		super.setPreviousPage(page);
		initialize();
	}

	private void initialize() {
		if (defaultScope != null && defaultScope.length() > 0)
			scopeCombo.setText(defaultScope);
		else
			scopeCombo.setText("session"); //$NON-NLS-1$

		if (((NewManagedBeanWizard) getWizard()).getSuggestedBeanName() != null) {
			this.managedBeanNameText
					.setText(((NewManagedBeanWizard) getWizard())
							.getSuggestedBeanName());
		} else {
			String beanName;
			if (this.getPreviousPage() instanceof ManagedBeanClassSelectionPage) {
				beanName = ((ManagedBeanClassSelectionPage) this.getPreviousPage())
						.getClassName();
			} else {
				beanName = ((NewJavaClassPage) this.getPreviousPage())
						.getTypeName();
			}
			beanName = beanName.substring(beanName.lastIndexOf(".") + 1); //$NON-NLS-1$
			if (beanName != null && beanName.length() > 0) {
				beanName = (beanName.substring(0, 1)).toLowerCase()
						+ (beanName.substring(1));

				beanName = ManagedBeanUtil.getDefaultManagedBeanName(
						currentProject, beanName);

			} else
				beanName = ""; //$NON-NLS-1$
			managedBeanNameText.setText(beanName);
		}
	}

	/**
	 * Returns key-value summary data.
	 * 
	 * @return List - Summary data.
	 */
	public List getSummaryData() {
		List data = new ArrayList();

		data
				.add(new String[] {
						WizardMessages.NewJavaManagedBeanWizard_Summary_ManagedBeanName,
						getManagedBeanName() });
		data
				.add(new String[] {
						WizardMessages.NewJavaManagedBeanWizard_Summary_ManagedBeanScope,
						getManagedBeanScope() });
		data.add(new String[] {
				WizardMessages.NewJavaManagedBeanWizard_Summary_ClassName,
				getManagedBeanClass() });

		data
				.add(new String[] {
						WizardMessages.NewJavaManagedBeanWizard_Summary_Description,
						getManagedBeanDescription() });
		return data;
	}

	/**
	 * @return the name of the managed bean
	 */
	public String getManagedBeanName() {
		return this.managedBeanNameText.getText().trim();
	}

	/**
	 * @return the managed bean class name
	 */
	public String getManagedBeanClass() {
		if (this.getPreviousPage() instanceof ManagedBeanClassSelectionPage) {
			return ((ManagedBeanClassSelectionPage) this.getPreviousPage()).getClassName();
		} else if (this.getPreviousPage() instanceof NewJavaClassPage) {
			NewJavaClassPage newJavaClassPage = (NewJavaClassPage) this.getPreviousPage();
			StringBuffer buffer = new StringBuffer();
			if (newJavaClassPage.getPackageText() != null
					&& newJavaClassPage.getPackageText().length() > 0) {
				buffer.append(newJavaClassPage.getPackageText());
				buffer.append("."); //$NON-NLS-1$
			}
			buffer.append(newJavaClassPage.getTypeName());
			return buffer.toString();
		}

		return "";//$NON-NLS-1$
	}

	/**
	 * @return the managed bean scope
	 */
	public String getManagedBeanScope() {
		return this.scopeCombo.getText().trim();
	}

	/**
	 * @return the managed bean description
	 */
	public String getManagedBeanDescription() {
		return this.managedBeanDescriptionText.getText().trim();
	}
}