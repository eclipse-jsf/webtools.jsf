/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;

/**
 * Wizard page to create a new class.
 * 
 * @author Xiao-guang Zhang
 * 
 */
public class NewJavaClassPage extends org.eclipse.jdt.ui.wizards.NewClassWizardPage
		implements ISummaryDataSource {

	private IProject currentProject;

	public NewJavaClassPage(IProject project) {
		super();
		currentProject = project;
		setPageComplete(false);
	}

	public void initialize() {
		if (getPackageFragmentRoot() == null
				|| currentProject != getPackageFragmentRoot().getJavaProject()
						.getProject()) {
			IJavaProject jProject = JavaCore.create(currentProject);
			StructuredSelection selection = new StructuredSelection(jProject);
			init(selection);
		}
	}

	private boolean isSkipped() {
		if ((getPreviousPage() instanceof ManagedBeanClassSelectionPage)
				&& !((ManagedBeanClassSelectionPage) getPreviousPage()).isCreateNewJavaClass())
			return true;
		return false;
	}

	public List getSummaryData() {
		List data = new ArrayList();

		if (!isSkipped()) {
			data
					.add(new String[] {
							WizardsResourcesNLS.NewJavaManagedBeanWizard_Summary_SourceFolder,
							getPackageFragmentRootText(), }); //$NON-NLS-1$

			data
					.add(new String[] {
							WizardsResourcesNLS.NewJavaManagedBeanWizard_Summary_PackageName, //$NON-NLS-1$
							getPackageText(), });

			data
					.add(new String[] {
							WizardsResourcesNLS.NewJavaManagedBeanWizard_Summary_TypeName, //$NON-NLS-1$
							getTypeName() }); 
		}
		return data;
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
}
