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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryFactory;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion;
import org.eclipse.jst.jsf.ui.internal.JSFUiPlugin;
import org.eclipse.jst.jsf.ui.internal.Messages;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * Dialog for creating or editing a JSF Library or Implementation.
 * <br>
 * If the selection passed in init is not null then the item will be edit mode.
 * 
 * @author Gerry Kessler - Oracle
 */
public class JSFLibraryWizard extends Wizard implements INewWizard {

	/**
	 * Implementation libaries to be included
	 */
	public static int IMPLS = 1;
	/**
	 * Non-Implementation libaries to be included
	 */
	public static int NONIMPLS = 2;
	
	private JSFLibraryEditControl c;
	
	private boolean isNew = false;
	private boolean modified = false;

	private JSFLibrary curLibrary;
	private JSFLibrary workingCopyLibrary;

	private JSFLibraryWizardPage page;

	private static final String DESCRIPTION = Messages.JSFLibraryWizard_DESCRIPTION;
	private static final String IMPLS_ONLY_DESC = Messages.JSFLibraryWizard_IMPLS_ONLY_DESC;

	private boolean _impls;	
	private boolean _nonimpls;
	
	/**
	 * Constructor
	 * see IMPLS
	 * see NONIMPLS
	 * @param libTypes 
	 */
	public JSFLibraryWizard(int libTypes) {
		super();
		if ((libTypes & IMPLS) == IMPLS)
			_impls = true;

		if ((libTypes & NONIMPLS) == NONIMPLS)
			_nonimpls = true;
	}

	
	/**
	 * Constructor.   List will include all JSF Libraries.
	 */
	public JSFLibraryWizard() {
		super();
		_impls = true;
		_nonimpls = true;
	}

	private boolean isImplsOnly(){
		if (_impls && ! _nonimpls)
			return true;
		return false;		
	}
	
	private boolean isNonImplsOnly(){
		if (_nonimpls && ! _impls)
			return true;
		return false;		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		if (selection != null
				&& selection.getFirstElement() instanceof JSFLibrary) {
			curLibrary = (JSFLibrary) selection.getFirstElement();
			workingCopyLibrary = curLibrary.getWorkingCopy();
		} else {
			isNew = true;
			workingCopyLibrary = JSFLibraryRegistryFactory.eINSTANCE.createJSFLibrary();
		}
		if (isImplsOnly()) {
			setWindowTitle(Messages.JSFLibraryWizard_CreateImplementation);
		} else {
			setWindowTitle(isNew ? Messages.JSFLibraryWizard_CreateJSFLibrary : Messages.JSFLibraryWizard_EditJSFLibrary);
		}
	}

	/**
	 * Updates the JSF Library instance with the values from the working copy and 
	 * persists the registry.
	 * 
	 * If editing a library reference, referencing java models will be updated.
	 *  
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {	
		final String name = c.getJSFLibraryName();
		final boolean isDeployed = c.getIsDeployed();
		final boolean isImplementation = c.getIsImplementation();
		final JSFVersion version = c.getJSFVersion();
		
		workingCopyLibrary.setName(name);
		workingCopyLibrary.setDeployed(isDeployed);
		workingCopyLibrary.setImplementation(isImplementation);
		workingCopyLibrary.setJSFVersion(version);
		
		final String originalID = curLibrary != null ? curLibrary.getID() :workingCopyLibrary.getID();
		
		if (isNew){
			JSFCorePlugin.getDefault().getJSFLibraryRegistry().addJSFLibrary(workingCopyLibrary);
		}
		else {
			curLibrary.updateValues(workingCopyLibrary);
			try {
				JSFLibraryRegistryUtil.rebindClasspathContainerEntries(originalID, workingCopyLibrary.getID(), false, null);
			} catch (JavaModelException e) {
				JSFUiPlugin.log(IStatus.ERROR, "Exception while updating JSF Library containers", e);
			}
		}
		JSFCorePlugin.getDefault().saveJSFLibraryRegistry();
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void addPages() {
		page = new JSFLibraryWizardPage(Messages.JSFLibraryWizard_JSFLibrary);
		super.addPage(page);
		page.setWizard(this);
	}

	/**
	 * @return the JSFLibrary being modified by this wizard
	 */
	public JSFLibrary getJSFLibrary() {
		return workingCopyLibrary;
	}

	private class JSFLibraryWizardPage extends WizardPage {

		/**
		 * @param pageName
		 */
		protected JSFLibraryWizardPage(String pageName) {
			super(pageName);
			setDescription(isImplsOnly() ? IMPLS_ONLY_DESC : DESCRIPTION);
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

			c = new JSFLibraryEditControl(workingCopyLibrary, parent);
			c.setImplOnly(isImplsOnly());
			c.setNonImplOnly(isNonImplsOnly());
			c.setLayout(new GridLayout(2, false));
			c.setLayoutData(new GridData(GridData.FILL_BOTH));

			c.addValidationListener(new JSFLibraryValidationListener(){
				public void notifyValidation(JSFLibraryValidationEvent e) {
					setErrorMessage(e.getMessage());
					modified = true;
					setPageComplete(getErrorMessage()==null);
				}				
			});

			setControl(c);
			setPageComplete(false);
		}

	}

}
