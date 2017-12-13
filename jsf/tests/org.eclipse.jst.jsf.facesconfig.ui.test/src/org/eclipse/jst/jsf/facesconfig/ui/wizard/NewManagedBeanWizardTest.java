/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
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

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.test.FacesConfigEditorTest;
import org.eclipse.swt.widgets.Shell;

/**
 * @author sfshi
 * 
 */
public class NewManagedBeanWizardTest extends FacesConfigEditorTest {

	NewManagedBeanWizard wizard;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.test.FacesConfigEditorTest#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.test.FacesConfigEditorTest#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testManagedBeanWizard() {
		NewManagedBeanWizard wizard1 = new NewManagedBeanWizard(project);
		wizard1.setForcePreviousAndNextButtons(true);

		Shell shell = EditorPlugin.getActiveShell();
		WizardDialog wizardDialog = new WizardDialog(shell, wizard1);
		wizardDialog.create();
		wizardDialog.setBlockOnOpen(true);

		assertTrue(wizard1.getStartingPage() instanceof ManagedBeanClassSelectionPage);
		ManagedBeanClassSelectionPage page1 = (ManagedBeanClassSelectionPage) wizard1
				.getStartingPage();
		IWizardPage page2 = wizard1.getNextPage(page1);
		assertTrue(page2 instanceof ManagedBeanPropertyPage);
		// page1.searchRadioButton.setSelection(true);
		// assertFalse(page1.isPageComplete());
		// page1.createRadioButton.setSelection(true);

		// assertTrue(page1.isPageComplete());
	}
}
