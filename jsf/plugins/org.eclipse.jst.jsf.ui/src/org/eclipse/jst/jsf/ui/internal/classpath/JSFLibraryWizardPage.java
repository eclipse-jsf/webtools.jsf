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

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.ui.internal.Messages;
import org.eclipse.swt.widgets.Composite;

/**
 * Dialog for creating or editing a JSF Library or Implementation.
 * <br>
 * If the selection passed in init is not null then the item will be edit mode.
 * 
 * @author Gerry Kessler - Oracle
 */
public class JSFLibraryWizardPage extends WizardPage {

	private boolean modified = false;
	private JSFLibrary workingCopyLibrary;

	private static final String DESCRIPTION = Messages.JSFLibraryWizard_DESCRIPTION;

	/**
	 * Constructor
	 */
	public JSFLibraryWizardPage(){
		super("JSFLibrary");		
	}

	/**
	 * @return JSF Library working copy 
	 */
	public JSFLibrary getJSFLibrary() {
		return workingCopyLibrary;
	}

	/**
	 * Constructor
	 * @param pageName
	 */
	public JSFLibraryWizardPage(String pageName) {
		super(pageName);
		setDescription(DESCRIPTION);//implsOnly ? IMPLS_ONLY_DESC : 
		setTitle(Messages.JSFLibraryWizard_JSFLibrary);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		if (modified == false) {
			return false;
		}
		return super.isPageComplete();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);

		JSFLibraryEditControl editControl = new JSFLibraryEditControl(workingCopyLibrary, parent);		
		editControl.addValidationListener(new JSFLibraryValidationListener(){
			public void notifyValidation(JSFLibraryValidationEvent e) {
				setErrorMessage(e.getMessage());
				modified = true;
				setPageComplete(getErrorMessage()==null);					
			}			
		});
		setControl(editControl);
		setPageComplete(false);
	}


	/**
	 * @return true if can finish
	 */
	public boolean finish() {
	    // TODO: dead?
		return true;
	}


}
