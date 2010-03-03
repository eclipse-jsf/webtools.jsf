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
package org.eclipse.jst.jsf.core.internal;

import org.eclipse.osgi.util.NLS;

/**
 * String resource handler.
 * 
 * @author Gerry Kessler - Oracle, Ian Trimble - Oracle
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.core.internal.messages"; //$NON-NLS-1$

    /**
     * see messages.properties
     */
    public static String Could_Not_GetJSFVersion;

	/**
	 * see messages.properties
	 */
	public static String JSFFacetInstallDataModelProvider_DupeJarValidation;

	/**
	 * see messages.properties
	 */
	public static String JSFFacetInstallDataModelProvider_INITIAL_VALIDATION_IMPL_TYPE;

    /**
     * see messages.properties
     */
    public static String JSFFacetInstallDataModelProvider_INVALID_JSF_CONFIG_FILE_NAME;


	/**
	 * see messages.properties
	 */
	public static String JSFLibCfgDialogSettingData_Sticky_Component_Lib_Not_Exist;

	/**
	 * see messages.properties
	 */
	public static String JSFLibrariesContainerInitializer_missing_library;

	/**
	 * see messages.properties
	 */
	public static String JSFLibraryClasspathContainer_IMPL_LIBRARY;

	/**
	 * see messages.properties
	 */
	public static String JSFLibraryClasspathContainer_NON_IMPL_LIBRARY;

    /**
     * see messages.properties
     */
	public static String JSFLibraryConfigModel_Null_Data_Source;

    /**
     * see messages.properties
     */
	public static String JSFLibraryConfigPersistData_SAVED_COMPLIB_NOT_FOUND;

    /**
     * see messages.properties
     */
	public static String JSFLibraryConfigPersistData_SAVED_IMPLLIB_NOT_FOUND;

    /**
     * see messages.properties
     */
	public static String JSFLibraryRegistry_ErrorCreatingURL;
    /**
     * see messages.properties
     */
	public static String JSFLibraryRegistry_NoLoadCreatingNew;
    /**
     * see messages.properties
     */
	public static String JSFLibraryRegistry_ErrorSaving;
    /**
     * see messages.properties
     */
	public static String JSFLibraryRegistry_DEFAULT_IMPL_LABEL;
    /**
     * see messages.properties
     */
	public static String JSFLibraryRegistry_ErrorLoadingFromExtPt;

    /**
     * see messages.properties
     */
	public static String JSFFacetInstallDataModelProvider_ValidateServletName;
    /**
     * see messages.properties
     */
	public static String JSFFacetInstallDataModelProvider_ValidateConfigFileEmpty;
    /**
     * see messages.properties
     */
	public static String JSFFacetInstallDataModelProvider_ValidateConfigFilePath;
    /**
     * see messages.properties
     */
	public static String JSFFacetInstallDataModelProvider_ValidateConfigFileRelative1;
    /**
     * see messages.properties
     */
	public static String JSFFacetInstallDataModelProvider_ValidateConfigFileRelative2;
    /**
     * see messages.properties
     */
	public static String JSFFacetInstallDataModelProvider_ValidateConfigFileXML;
    /**
     * see messages.properties
     */
	public static String JSFFacetInstallDataModelProvider_ValidateConfigFileSlashes;

    /**
     * see messages.properties
     */
	public static String ArchiveFileImpl_CannotCopyFile;
    /**
     * see messages.properties
     */
	public static String ArchiveFileImpl_CannotCloseFile;
    /**
     * see messages.properties
     */
	public static String ArchiveFileImpl_CannotLocatePluginRelativeFile;

    /**
     * see messages.properties
     */
	public static String JSFUtils_MissingJAR;

    /**
     * see messages.properties
     */
	public static String PluginProvidedJSFLibraryCreationHelper_ErrorCreating;
    /**
     * see messages.properties
     */
	public static String PluginProvidedJSFLibraryCreationHelper_ErrorMultipleDefinition;

    /**
     * see messages.properties
     */
	public static String JSFUtils_ErrorCreatingConfigFile;
    /**
     * see messages.properties
     */
	public static String JSFUtils_ErrorClosingConfigFile;

    /**
     * see messages.properties
     */
	public static String JSFAppConfigUtils_ErrorOpeningJarFile;

    /**
     * see messages.properties
     */
	public static String JARFileJSFAppConfigProvider_ErrorLoadingModel;

    /**
     * see messages.properties
     */
	public static String J2EEModuleDependencyDelegate_UpdatingJ2EEModuleDependencies;
    /**
     * see messages.properties
     */
	public static String J2EEModuleDependencyDelegate_UpdatingJ2EEModuleDependenciesForProject;

    /**
     * see messages.properties
     */
	public static String JSFRegistryMigration05_to_10_title;
    /**
     * see messages.properties
     */
	public static String JSFRegistryMigration05_to_10_message;

    /**
     * see messages.properties
     */
    public static String JSFRegistryMigration05_to_10_customMessage;
    

    /**
     * see messages.properties
     */
	public static String JSFRegistryMigrationCannot05_to_10_title;
    /**
     * see messages.properties
     */
	public static String JSFRegistryMigrationCannot05_to_10_message;

    /**
     * see messages.properties
     */
    public static String JSFRegistryMigrationCannot05_to_10_customMessage;


	/**
	 * see messages.properties
	 */
	public static String RegistryMigrationStartupHandler_Dialog_Abort_And_Exit_Migration;

    /**
     * see messages.properties
     */
	public static String RegistryMigrationStartupHandler_Dialog_Confirm_Migration;

    /**
     * see messages.properties
     */
	public static String RegistryMigrationStartupHandler_Error_committing_migration;

    /**
     * see messages.properties
     */
	public static String RegistryMigrationStartupHandler_Error_Rolling_Back_Migration;

    /**
     * see messages.properties
     */
	public static String RegistryMigrationStartupHandler_Launch_Migration_Doc_On_Confirm;
	/**
	 * see messages.properties
	 */
    public static String RegistryUpgradeCommitHandler_Text;
	/**
	 * see messages.properties
	 */
	public static String RegistryUpgradeCommitHandler_Title;

	/**
     * see messages.properties
     */
	public static String JSFFacet11_presetLabel;
	
    /**
     * see messages.properties
     */
	public static String JSFFacet11_presetDescription;
	
	/**
	 * see messages.properties
	 */
	public static String JSFFacetInstallDelegate_AllowJavascriptDescription;

    /**
     * see messages.properties
     */
    public static String JSFFacetInstallDelegate_AutoScrollDescription;

    /**
	 * see messages.properties
	 */
	public static String JSFFacetInstallDelegate_InternalErr;
	/**
	 * see messages.properties
	 */
	public static String JSFFacetInstallDelegate_ConfigErr;

    /**
     * see messages.properties
     */
    public static String JSFFacetInstallDelegate_MaxFileSizeDescription;
	/**
	 * see messages.properties
	 */
	public static String JSFFacetInstallDelegate_NonUpdateableWebXML;

    /**
     * see messages.properties
     */
    public static String JSFFacetInstallDelegate_PrettyHtmlDescription;

    /**
     * see messages.properties
     */
    public static String JSFFacetInstallDelegate_StateSavingMethod;
	/**
	 * see messages.properties
	 */
	public static String JSFFacetUninstallDelegate_ConfigErr;
	/**
	 * see messages.properties
	 */
	public static String JSFFacetUninstallDelegate_NonUpdateableWebXML;	
	/**
	 * see messages.properties
	 */
	public static String JSFLibraryValidator_MISSING_JSF_IMPLEMENTATION_CLASSES;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
