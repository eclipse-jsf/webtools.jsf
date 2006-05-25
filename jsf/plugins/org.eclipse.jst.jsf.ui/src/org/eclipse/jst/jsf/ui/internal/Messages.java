/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *    Ian Trimble - changed to work correctly with org.eclipse.osgi.util.NLS
 *******************************************************************************/ 
package org.eclipse.jst.jsf.ui.internal;

import org.eclipse.osgi.util.NLS;

/**
 * String resource handler.
 * 
 * @author Gerry Kessler - Oracle, Ian Trimble - Oracle
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.ui.internal.messages"; //$NON-NLS-1$

	public static String JSFFacetInstallPage_JSFLibraryLabel0;

	public static String JSFFacetInstallPage_title;
	public static String JSFFacetInstallPage_description;
	public static String JSFFacetInstallPage_JSFImplLabel;
	public static String JSFFacetInstallPage_Add1;
	public static String JSFFacetInstallPage_Add2;
	public static String JSFFacetInstallPage_DeployJarsLabel;
	public static String JSFFacetInstallPage_JSFConfigLabel;
	public static String JSFFacetInstallPage_JSFServletNameLabel;
	public static String JSFFacetInstallPage_JSFURLMappingLabel;
	public static String JSFFacetInstallPage_PatternDialogTitle;
	public static String JSFFacetInstallPage_PatternDialogDesc;
	public static String JSFFacetInstallPage_Remove;
	public static String JSFFacetInstallPage_PatternEmptyMsg;
	public static String JSFFacetInstallPage_PatternSpecifiedMsg;
	public static String JSFFacetInstallPage_ErrorNoWebAppDataModel;

	public static String JSFLibrariesPreferencePage_DefinedJSFLibraries;
	public static String JSFLibrariesPreferencePage_New;
	public static String JSFLibrariesPreferencePage_Edit;
	public static String JSFLibrariesPreferencePage_Remove;
	public static String JSFLibrariesPreferencePage_CannotRemovePluginProvidedTitle;
	public static String JSFLibrariesPreferencePage_CannotRemovePluginProvidedMessage;
	public static String JSFLibrariesPreferencePage_MakeDefault;
	public static String JSFLibrariesPreferencePage_Description;
	public static String JSFLibrariesPreferencePage_CannotModifyPluginProvidedTitle;
	public static String JSFLibrariesPreferencePage_CannotModifyPluginProvidedMessage;

	public static String JSFLibraryConfigControl_Add;

	public static String JSFLibraryConfigControl_AddAll;

	public static String JSFLibraryConfigControl_ComponentLibrary;

	public static String JSFLibraryConfigControl_DeployJAR;

	public static String JSFLibraryConfigControl_ImplementationLibrary;

	public static String JSFLibraryConfigControl_NewComponentLibrary;

	public static String JSFLibraryConfigControl_NewImplementationLibrary;

	public static String JSFLibraryConfigControl_Remove;

	public static String JSFLibraryConfigControl_RemoveAll;

	public static String JSFLibraryConfigControl_TH_Deploy;

	public static String JSFLibraryConfigControl_TH_LibraryName;

	public static String JSFLibraryContainerWizardPage_PageName;
	public static String JSFLibraryContainerWizardPage_Title;
	public static String JSFLibraryContainerWizardPage_Description;
	public static String JSFLibraryContainerWizardPage_WarningNoJSFFacet;
	public static String JSFLibraryContainerWizardPage_JSFLibraries;
	public static String JSFLibraryContainerWizardPage_Add;
	public static String JSFLibraryContainerWizardPage_ErrorInitializing;

	public static String JSFLibraryWizard_DESCRIPTION;
	public static String JSFLibraryWizard_IMPLS_ONLY_DESC;
	public static String JSFLibraryWizard_CreateImplementation;
	public static String JSFLibraryWizard_CreateJSFLibrary;
	public static String JSFLibraryWizard_EditJSFLibrary;
	public static String JSFLibraryWizard_JSFLibrary;
	public static String JSFLibraryWizard_LibraryName;
	public static String JSFLibraryWizard_VersionSupported;
	public static String JSFLibraryWizard_LibraryJars;
	public static String JSFLibraryWizard_IsJSFImplementation;
	public static String JSFLibraryWizard_DeployJars;
	public static String JSFLibraryWizard_Add;
	public static String JSFLibraryWizard_Remove;
	public static String JSFLibraryWizard_ExtJarFileDialogTitle;
	public static String JSFLibraryWizard_ValidateNoJars;
	public static String JSFLibraryWizard_ValidateNoLibraryName;
	public static String JSFLibraryWizard_ValidateExistingLibraryName;

    /**
     * Title set on the el validation preference panel
     */
    public static String JSFValidationPreferencePage_ELPrefPanel_Title;
    /**
     * Title set on the checkbox that enables/disables build validation for EL
     */
    public static String JSFValidationPreferencePage_ELPrefPanel_BuildValidationCheckBoxTitle;
    /**
     * Title set on the checkbox that enables/disables incremental (as you type)
     * validation for EL
     */
    public static String JSFValidationPreferencePage_ELPrefPanel_IncrementalValidationCheckBoxTitle;

    static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
