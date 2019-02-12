/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License 2.0 which accompanies this distribution, and is
 * available at https://www.eclipse.org/legal/epl-2.0/ Contributors: Sybase,
 * Inc. - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;

/**
 * The wizard for creating a managed bean on faces config model.
 * 
 * @author Xiao-guang Zhang, sfshi
 */
public class NewManagedBeanWizard extends Wizard implements ISummaryDataSource {

	/** log instance */
	private static final Logger log = EditorPlugin
			.getLogger(NewManagedBeanWizard.class);

	/**
	 * The wizard page where user can select a Java class for the managed bean
	 * to be created.
	 */
	private ManagedBeanClassSelectionPage managedBeanClassSelectionPage;

	/**
	 * The wizard page where user can create a new Java class for the managed
	 * bean to be created.
	 */
	private NewJavaClassPage newJavaClassPage;

	private ManagedBeanPropertyPage managedBeanPropertyPage;

	private String managedBeanName;

	private String managedBeanScope;

	private String managedBeanClass;

	private String managedBeanDescription;

	private IProject project;

	/**
	 * the suggested name for the managed bean, if setted, then use it in
	 * ManagedBeanPropertyWizardPage.
	 */
	private String suggestedBeanName;

	private String defaultScope;

	/**
	 * Constructor
	 * 
	 * @param project
	 *            The project.
	 */
	public NewManagedBeanWizard(IProject project) {
		super();
		setWindowTitle(WizardMessages.NewManagedBeanWizardBase_Title);
		this.project = project;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.wizards.NewWizard#addPages()
	 */
	public void addPages() {
		setDialogSettings(EditorPlugin.getDefault().getDialogSettings());

		// add java source selection page.
		managedBeanClassSelectionPage = new ManagedBeanClassSelectionPage(project);
		addPage(managedBeanClassSelectionPage);

		// add the class type page
		newJavaClassPage = new NewJavaClassPage(project);
		addPage(newJavaClassPage);

		managedBeanPropertyPage = new ManagedBeanPropertyPage(defaultScope, project);
		addPage(managedBeanPropertyPage);

		// // add the summary page
		SummaryPage summaryPage = new SummaryPage(this);
		addPage(summaryPage);
		summaryPage
				.setDescription(WizardMessages.NewJavaManagedBeanWizard_SummaryPage_Description);
	}

	/**
	 * Skip the NewJavaClassPage when user select "Using an existing class" on the
	 * ManagedBeanClassSelectionPage.
	 */
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage nextPage = super.getNextPage(page);
		if (page instanceof ManagedBeanClassSelectionPage
				&& !((ManagedBeanClassSelectionPage) page).isCreateNewJavaClass()) {
			((WizardPage) nextPage).setPageComplete(true);
			return getNextPage(nextPage);
		}
		return nextPage;
	}

	public boolean performFinish() {

		managedBeanName = managedBeanPropertyPage.getManagedBeanName();
		managedBeanClass = managedBeanPropertyPage.getManagedBeanClass();
		managedBeanScope = managedBeanPropertyPage.getManagedBeanScope();
		managedBeanDescription = managedBeanPropertyPage.getManagedBeanDescription();
		if (managedBeanClassSelectionPage.isCreateNewJavaClass())
			createNewJavaClass();

		return true;
	}

	/**
	 * create a new java class according to current inputs in
	 * NewClassWizardPage.
	 */
	private void createNewJavaClass() {
		try {
			newJavaClassPage.createType(new NullProgressMonitor());
		} catch (CoreException e) {
			log.info("NewJavaManagedBeanWizard.Error.CreateType", e); //$NON-NLS-1$

			EditorPlugin.getAlerts().error(
					"NewJavaManagedBeanWizard.Alert.CreateType.Title", //$NON-NLS-1$
					"NewJavaManagedBeanWizard.Alert.CreateType.Description"); //$NON-NLS-1$
			return;
		} catch (InterruptedException e) {
			log.info("NewJavaManagedBeanWizard.Error.CreateType", e); //$NON-NLS-1$
			EditorPlugin.getAlerts().error(
					"NewJavaManagedBeanWizard.Alert.CreateType.Title", //$NON-NLS-1$
					"NewJavaManagedBeanWizard.Alert.CreateType.Description"); //$NON-NLS-1$
			return;
		}
		newJavaClassPage.getCreatedType();
	}

	/**
	 * @return the suggested bean name
	 */
	public String getSuggestedBeanName() {
		return suggestedBeanName;
	}

	/**
	 * Set a name for the new managed bean, this name will be shown in the
	 * ManagedBeanPropertyWizardPage. If don't set the name, then will get a
	 * default bean name from the managed bean class name.
	 * 
	 * @param suggestedBeanName
	 */
	public void setSuggestedBeanName(String suggestedBeanName) {
		this.suggestedBeanName = suggestedBeanName;
	}

	/**
	 * @return the default scope value
	 */
	public String getDefaultScope() {
		return defaultScope;
	}

	/**
	 * set a default scope value for this new managed bean.
	 * 
	 * @param defaultScope
	 */
	public void setDefaultScope(String defaultScope) {
		this.defaultScope = defaultScope;
	}

	/**
	 * @return the class name
	 */
	public String getManagedBeanClass() {
		return managedBeanClass;
	}

	/**
	 * @return the bean description
	 */
	public String getManagedBeanDescription() {
		return managedBeanDescription;
	}

	/**
	 * @return the managed bean's symbolic name
	 */
	public String getManagedBeanName() {
		return managedBeanName;
	}

	/**
	 * @return the managed bean's scope
	 */
	public String getManagedBeanScope() {
		return managedBeanScope;
	}

	public List getSummaryData() {
		List data = new ArrayList();
		IWizardPage[] pages = getPages();

		for (int i = 0; i < pages.length; i++) {
			if (pages[i] instanceof ISummaryDataSource) {

				data.addAll(((ISummaryDataSource) pages[i]).getSummaryData());
			}
		}
		return data;
	}

}
