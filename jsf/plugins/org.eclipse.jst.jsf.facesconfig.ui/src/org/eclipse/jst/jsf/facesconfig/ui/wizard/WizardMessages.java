/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.wizard;

import org.eclipse.osgi.util.NLS;

/**
 * Message bundle for wizards
 *
 */
public final class WizardMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.facesconfig.ui.wizard.WizardMessages"; //$NON-NLS-1$

	private WizardMessages() {
		// Do not instantiate
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, WizardMessages.class);
	}

	/**
	 * see WizardMessages.properties
	 */
	public static String NewManagedBeanWizardBase_Type;

	/**
	 * see WizardMessages.properties
	 */
	public static String NewManagedBeanWizardBase_Description;

	/**
	 * see WizardMessages.properties
	 */
	public static String NewManagedBeanWizardBase_Title;

	/**
	 * see WizardMessages.properties
	 */
	public static String NewManagedBeanCreationWizard_Title;

	/**
	 * see WizardMessages.properties
	 */
	public static String IntroWizardPage_Title;

	/**
	 * see WizardMessages.properties
	 */
	public static String IntroWizardPage_Intro;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanWizardSelectionPage_Title;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanWizardSelectionPage_Description;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanWizardSelectionPage_Group;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanWizardSelectionPage_HelpContextID;

	/**
	 * see WizardMessages.properties
	 */
	public static String Registry_LoadWizard_Error;

	/**
	 * see WizardMessages.properties
	 */
	public static String NewWizard_Title_WizardDefault;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyWizardPage_Title;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyWizardPage_Description;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyWizardPage_General;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyWizardPage_ManagedBeanName;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyWizardPage_ManagedBeanScope;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyWizardPage_ManagedBeanDescription;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyWizardPage_ManagedBeanProperty;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyWizardPage_PropertyName;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyWizardPage_Type;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyWizardPage_Value;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyWizardPage_Button_Add;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyWizardPage_Button_Edit;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyWizardPage_Button_Remove;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyWizardPage_Warning_MissingManagedBeanName;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyWizardPage_Warning_InvalidManagedBeanName;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyWizardPage_Warning_DuplicateManagedBeanName;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyEditDialog_PropertyName;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyEditDialog_DataType;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyEditDialog_InitialValue;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyEditDialog_Title_NewPropertyEntry;

	/**
	 * see WizardMessages.properties
	 */
	public static String ManagedBeanPropertyEditDialog_Title_ExistingPropertyEntry;

	/**
	 * see WizardMessages.properties
	 */
	public static String FacesConfigSelectionWizardPage_Title;

	/**
	 * see WizardMessages.properties
	 */
	public static String FacesConfigSelectionWizardPage_Description;

	/**
	 * see WizardMessages.properties
	 */
	public static String FacesConfigSelectionWizardPage_Project;

	/**
	 * see WizardMessages.properties
	 */
	public static String FacesConfigSelectionWizardPage_FacesConfig;

	/**
	 * see WizardMessages.properties
	 */
	public static String FacesConfigSelectionWizardPage_FacesConfig_Browser;

	/**
	 * see WizardMessages.properties
	 */
	public static String FacesConfigSelectionWizardPage_Warning_MissingProjectName;

	/**
	 * see WizardMessages.properties
	 */
	public static String FacesConfigSelectionWizardPage_Warning_MissingFacesConfigName;

	/**
	 * see WizardMessages.properties
	 */
	public static String FacesConfigSelectionWizardPage_Warning_InvalidFacesConfigName;

	/**
	 * see WizardMessages.properties
	 */
	public static String FacesConfigSelectionWizardPage_Warning_InvalidFacesConfigContent;

	/**
	 * see WizardMessages.properties
	 */
	public static String FacesConfigSelectionWizardPage_Warning_FacesConfigNotDefinedInWebDotXML;

	/**
	 * see WizardMessages.properties
	 */
	public static String FacesConfigSelectionWizardPage_HelpContextID;

	/**
	 * see WizardMessages.properties
	 */
	public static String FacesConfigFileSelectionDialog_Title;

	/**
	 * see WizardMessages.properties
	 */
	public static String FacesConfigFileSelectionDialog_Description;

	/**
	 * see WizardMessages.properties
	 */
	public static String JavaSelectionWizardPage_Title;

	/**
	 * see WizardMessages.properties
	 */
	public static String JavaSelectionWizardPage_Description;

	/**
	 * see WizardMessages.properties
	 */
	public static String JavaSelectionWizardPage_Group;

	/**
	 * see WizardMessages.properties
	 */
	public static String JavaSelectionWizardPage_Search;

	/**
	 * see WizardMessages.properties
	 */
	public static String JavaSelectionWizardPage_Search_Description;

	/**
	 * see WizardMessages.properties
	 */
	public static String JavaSelectionWizardPage_Search_ClassName;

	/**
	 * see WizardMessages.properties
	 */
	public static String JavaSelectionWizardPage_Search_ClassName_Browse;

	/**
	 * see WizardMessages.properties
	 */
	public static String JavaSelectionWizardPage_Create;

	/**
	 * see WizardMessages.properties
	 */
	public static String JavaSelectionWizardPage_Create_Description;

	/**
	 * see WizardMessages.properties
	 */
	public static String JavaSelectionWizardPage_FindType;

	/**
	 * see WizardMessages.properties
	 */
	public static String JavaSelectionWizardPage_FindType_Filter;

	/**
	 * see WizardMessages.properties
	 */
	public static String JavaSelectionWizardPage_Error_ClassIsEmpty;

	/**
	 * see WizardMessages.properties
	 */
	public static String JavaSelectionWizardPage_Error_ClassIsNotDefined;

	/**
	 * see WizardMessages.properties
	 */
	public static String JavaSelectionWizardPage_HelpContextID;

	/**
	 * see WizardMessages.properties
	 */
	public static String NewJavaManagedBeanWizard_Summary_ProjectName;

	/**
	 * see WizardMessages.properties
	 */
	public static String NewJavaManagedBeanWizard_Summary_FacesConfigFileName;

	/**
	 * see WizardMessages.properties
	 */
	public static String NewJavaManagedBeanWizard_Summary_TypeName;

	/**
	 * see WizardMessages.properties
	 */
	public static String NewJavaManagedBeanWizard_Summary_PackageName;

	/**
	 * see WizardMessages.properties
	 */
	public static String NewJavaManagedBeanWizard_Summary_SourceFolder;

	/**
	 * see WizardMessages.properties
	 */
	public static String NewJavaManagedBeanWizard_Summary_ManagedBeanName;

	/**
	 * see WizardMessages.properties
	 */
	public static String NewJavaManagedBeanWizard_Summary_ManagedBeanScope;

	/**
	 * see WizardMessages.properties
	 */
	public static String NewJavaManagedBeanWizard_Summary_ClassName;

	/**
	 * see WizardMessages.properties
	 */
	public static String NewJavaManagedBeanWizard_Summary_Description;

	/**
	 * see WizardMessages.properties
	 */
	public static String NewJavaManagedBeanWizard_SummaryPage_Description;

	/**
	 * see WizardMessages.properties
	 */
	public static String NewJavaManagedBeanWizard_Task_CreateNewManagedBean;

	/**
	 * see WizardMessages.properties
	 */
	public static String NewJavaManagedBeanWizard_Task_CreateNewJavaClass;

	/**
	 * see WizardMessages.properties
	 */
	public static String DuplicateBeanWizard_Title;

	/**
	 * see WizardMessages.properties
	 */
	public static String DuplicateBeanPage_Title;

	/**
	 * see WizardMessages.properties
	 */
	public static String DuplicateBeanPage_Description;

	/**
	 * see WizardMessages.properties
	 */
	public static String DuplicateBeanGroup_Group_Label;

	/**
	 * see WizardMessages.properties
	 */
	public static String DuplicateBeanGroup_OverwriteButton_Text;

	/**
	 * see WizardMessages.properties
	 */
	public static String DuplicateBeanGroup_NewButton_Text;

	/**
	 * see WizardMessages.properties
	 */
	public static String DuplicateBeanGroup_NewNameDialogField_Label;

	/**
	 * see WizardMessages.properties
	 */
	public static String DuplicateBeanGroup_Error_EmptyManagedBeanName;

	/**
	 * see WizardMessages.properties
	 */
	public static String DuplicateBeanGroup_Error_InvalidManagedBeanName;

	/**
	 * see WizardMessages.properties
	 */
	public static String DuplicateBeanGroup_Error_DuplicatedManagedBeanName;

	/**
	 * see WizardMessages.properties
	 */
	public static String WizardSummaryPage_Title_WizardSummary;

	/**
	 * see WizardMessages.properties
	 */
	public static String WizardSummaryPage_Summary_SummaryDesc;

	/**
	 * see WizardMessages.properties
	 */
	public static String WizardSummaryPage_Label_Field;

	/**
	 * see WizardMessages.properties
	 */
	public static String WizardSummaryPage_Label_Value;

}
