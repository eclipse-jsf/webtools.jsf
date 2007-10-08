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

package org.eclipse.jst.jsf.facesconfig.ui;

import org.eclipse.osgi.util.NLS;

/**
 * Provides localized messages from EditorMessages.properties.
 */
public final class EditorMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.facesconfig.ui.EditorMessages";

	private EditorMessages() {
		// Do not instantiate
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, EditorMessages.class);
	}

	/**
	 * see EditorMessages.properties
	 */
	public static String AddEditManagedPropertyDialog_Add;

	/**
	 * see EditorMessages.properties
	 */
	public static String AddEditManagedPropertyDialog_Edit;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacesConfigEditor_ErrorHandlingUndoConflicts_DialogMessage;

	/**
	 * see EditorMessages.properties
	 */
    public static String FacesConfigEditor_ErrorHandlingUndoConflicts_DialogTitle;

	/**
	 * see EditorMessages.properties
	 */
    public static String FacesConfigEditor_WaitForLoad_EditorTabTitle;

	/**
	 * see EditorMessages.properties
	 */
    public static String UI_Button_Add;

	/**
	 * see EditorMessages.properties
	 */
	public static String UI_Button_Edit;

	/**
	 * see EditorMessages.properties
	 */
	public static String UI_Button_Remove;

	/**
	 * see EditorMessages.properties
	 */
	public static String UI_Button_Add_more;

	/**
	 * see EditorMessages.properties
	 */
	public static String UI_Button_Edit_more;

	/**
	 * see EditorMessages.properties
	 */
	public static String Remove_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String Remove_Msg;

	/**
	 * see EditorMessages.properties
	 */
	public static String FindType;

	/**
	 * see EditorMessages.properties
	 */
	public static String FindType_Filter;

	/**
	 * see EditorMessages.properties
	 */
	public static String FindType_Error_CannotFindType;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacesConfigEditor_Introduction_TabName;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacesConfigEditor_Overview_TabName;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacesConfigEditor_Pageflow_TabName;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacesConfigEditor_ManagedBeans_TabName;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacesConfigEditor_Components_TabName;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacesConfigEditor_Others_TabName;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacesConfigEditor_Source_TabName;

	/**
	 * see EditorMessages.properties
	 */
	public static String editor_pageflow_page_intro_name;

	/**
	 * see EditorMessages.properties
	 */
	public static String editor_pageflow_page_intro_title;

	/**
	 * see EditorMessages.properties
	 */
	public static String editor_pageflow_page_intro_help_HelpContextID;

	/**
	 * see EditorMessages.properties
	 */
	public static String editor_pageflow_page_intro_help_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanPage_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanMasterSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanMasterSection_Description;

//	public static String ManagedBeanMasterSection_HelpContextID;
//
//	public static String ManagedBeanMasterSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanGeneralSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanGeneralSection_Desc;

//	public static String ManagedBeanGeneralSection_HelpContextID;
//
//	public static String ManagedBeanGeneralSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanGeneralSection_ManagedBeanName;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanGeneralSection_ManagedBeanClass;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanGeneralSection_ManagedBeanScope;

	/**
	 * see EditorMessages.properties
	 */
	public static String InitializationSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String InitializationSection_Description;

//	public static String InitializationSection_HelpContextID;
//
//	public static String InitializationSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanPropertyEditDialog_Title_NewPropertyEntry;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanPropertyEditDialog_Title_ExistingPropertyEntry;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanPropertyEditDialog_PropertyName;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanPropertyEditDialog_PropertyClass;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanPropertyEditDialog_PropertyClass_Browse;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanPropertyEditDialog_ValueType;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanProeprtyEditPage_Description;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanPropertyEditDialog_Value;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanPropertyEditWizard_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanProeprtyEditPage_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanPropertyEditDialog_Value_Change;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanPropertyEditDialog_FindType;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanPropertyEditDialog_FindType_Filter;

	/**
	 * see EditorMessages.properties
	 */
	public static String InitializationSection_ClassType;

	/**
	 * see EditorMessages.properties
	 */
	public static String InitializationSection_ClassType_General;

	/**
	 * see EditorMessages.properties
	 */
	public static String InitializationSection_ClassType_Map;

	/**
	 * see EditorMessages.properties
	 */
	public static String InitializationSection_ClassType_List;

	/**
	 * see EditorMessages.properties
	 */
	public static String InitializationSection_PropertyTable_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String InitializationSection_PropertyTable_Class;

	/**
	 * see EditorMessages.properties
	 */
	public static String InitializationSection_PropertyTable_Value;

	/**
	 * see EditorMessages.properties
	 */
	public static String InitializationSection_MapType_KeyClass;

	/**
	 * see EditorMessages.properties
	 */
	public static String InitializationSection_MapType_ValueClass;

	/**
	 * see EditorMessages.properties
	 */
	public static String InitializationSection_MapTable_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String InitializationSection_MapTable_Key;

	/**
	 * see EditorMessages.properties
	 */
	public static String InitializationSection_MapTable_Value;

	/**
	 * see EditorMessages.properties
	 */
	public static String InitializationSection_ListType_ValueClass;

	/**
	 * see EditorMessages.properties
	 */
	public static String InitializationSection_ListTable_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String InitializationSection_ListTable_Value;

	/**
	 * see EditorMessages.properties
	 */
	public static String ListEntriesEditPage_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String ListEntriesWizard_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String ListEntriesEditPage_Description;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_page_name;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_page_id;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_page_title;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_PageflowOverview_summary;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_EditorSection_name;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_EditorSection_description;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_EditorSection_noplugin;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_EditorSection_nooverview;

//	public static String OverviewPage_EditorSection_Help_HelpContextID;
//
//	public static String OverviewPage_EditorSection_Help_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_GeneralSection_name;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_GeneralSection_description;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_GeneralSection_label_name;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_GeneralSection_label_version;

//	public static String OverviewPage_GeneralSection_Help_HelpContextID;
//
//	public static String OverviewPage_GeneralSection_Help_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_NavigationSection_name;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_NavigationSection_description;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_NavigationSection_table_col1;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_NavigationSection_table_col2;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_NavigationSection_table_col3;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_NavigationSection_pageflowPageID;

//	public static String OverviewPage_NavigationSection_Help_HelpContextID;
//
//	public static String OverviewPage_NavigationSection_Help_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_ManagedBeanSection_name;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_ManagedBeanSection_description;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_ManagedBeanSection_table_namecol;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_ManagedBeanSection_table_scopecol;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_ManagedBeanSection_table_classcol;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_ManagedBeanSection_managedbeanPageID;

//	public static String OverviewPage_ManagedBeanSection_Help_HelpContextID;
//
//	public static String OverviewPage_ManagedBeanSection_Help_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_ComponentsSection_name;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_ComponentsSection_description;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_ComponentsSection_table_typecol;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_ComponentsSection_table_namecol;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_ComponentsSection_componentsPageID;

//	public static String OverviewPage_ComponentsSection_Help_HelpContextID;
//
//	public static String OverviewPage_ComponentsSection_Help_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_OthersSection_name;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_OthersSection_description;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_OthersSection_table_typecol;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_OthersSection_table_namecol;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_OthersSection_table_valuecol;

	/**
	 * see EditorMessages.properties
	 */
	public static String OverviewPage_OthersSection_othersPageID;

//	public static String OverviewPage_OthersSection_Help_HelpContextID;
//
//	public static String OverviewPage_OthersSection_Help_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanPropertyGroup_error_EmptyName;

	/**
	 * see EditorMessages.properties
	 */
	public static String ManagedBeanPropertyGroup_error_EmptyClass;

	/**
	 * see EditorMessages.properties
	 */
	public static String MapEntriesEditGroup_Edit;

	/**
	 * see EditorMessages.properties
	 */
	public static String MapEntriesEditPage_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String MapEntriesWizard_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String MapEntryEditGroup_Key;

	/**
	 * see EditorMessages.properties
	 */
	public static String MapEntryEditGroup_error_EmptyKey;

	/**
	 * see EditorMessages.properties
	 */
	public static String MapEntryEditGroup_error_DuplicateKey;

	/**
	 * see EditorMessages.properties
	 */
	public static String MapEntryEditPage_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String MapEntryEditWizard_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String MapEntriesEditPage_Description;

	/**
	 * see EditorMessages.properties
	 */
	public static String MapEntryEditPage_Description;

	/**
	 * see EditorMessages.properties
	 */
	public static String ValueEditDialog_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String ValueEditWizard_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String ValueEditPage_Description;

	/**
	 * see EditorMessages.properties
	 */
	public static String ValueEditGroup_Value;

	/**
	 * see EditorMessages.properties
	 */
	public static String ValueEditPage_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String ComponentsPage_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String ComponentMasterSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String ComponentMasterSection_Description;

//	public static String ComponentMasterSection_HelpContextID;
//
//	public static String ComponentMasterSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String ComponentGeneralSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String ComponentGeneralSection_Description;

//	public static String ComponentGeneralSection_HelpContextID;
//
//	public static String ComponentGeneralSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String ComponentGeneralSection_Label_DisplayName;

	/**
	 * see EditorMessages.properties
	 */
	public static String ComponentGeneralSection_Label_Description;

	/**
	 * see EditorMessages.properties
	 */
	public static String ComponentGeneralSection_Label_ComponentType;

	/**
	 * see EditorMessages.properties
	 */
	public static String ComponentGeneralSection_Label_ComponentClass;

	/**
	 * see EditorMessages.properties
	 */
	public static String RenderKitMasterSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String RenderKitMasterSection_Description;

//	public static String RenderKitMasterSection_HelpContextID;
//
//	public static String RenderKitMasterSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String RenderKitGeneralSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String RenderKitGeneralSection_Description;

//	public static String RenderKitGeneralSection_HelpContextID;
//
//	public static String RenderKitGeneralSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String RenderKitGeneralSection_Label_DisplayName;

	/**
	 * see EditorMessages.properties
	 */
	public static String RenderKitGeneralSection_Label_Description;

	/**
	 * see EditorMessages.properties
	 */
	public static String RenderKitGeneralSection_Label_RenderKitID;

	/**
	 * see EditorMessages.properties
	 */
	public static String RenderKitGeneralSection_Label_RenderKitClass;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Description;

//	public static String RendererSection_HelpContextID;
//
//	public static String RendererSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Table_NameColumn;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Table_ComponentFamilyColumn;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Table_TypeColumn;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Table_ClassColumn;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Dialog_Title_Add;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Dialog_Title_Edit;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Dialog_Tab_General;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Dialog_DisplayName;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Dialog_ComponentFamilyValue;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Dialog_TypeValue;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Dialog_RendererClass;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Dialog_Error_ComponentFamilyEmpty;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Dialog_Error_RendererTypeEmpty;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Dialog_Error_RendererClassEmpty;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Dialog_SelectComponentFamilyDialog_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Dialog_ComponentFamilyCaption;

	/**
	 * see EditorMessages.properties
	 */
	public static String RendererSection_Dialog_ComponentFamilyLabel;

	/**
	 * see EditorMessages.properties
	 */
	public static String ConverterMasterSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String ConverterMasterSection_Description;

//	public static String ConverterMasterSection_HelpContextID;
//
//	public static String ConverterMasterSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String ConverterGeneralSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String ConverterGeneralSection_Description;

//	public static String ConverterGeneralSection_HelpContextID;
//
//	public static String ConverterGeneralSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String ConverterGeneralSection_Label_DisplayName;

	/**
	 * see EditorMessages.properties
	 */
	public static String ConverterGeneralSection_Label_Description;

	/**
	 * see EditorMessages.properties
	 */
	public static String ConverterGeneralSection_Label_ConverterID;

	/**
	 * see EditorMessages.properties
	 */
	public static String ConverterGeneralSection_Label_ConverterForClass;

	/**
	 * see EditorMessages.properties
	 */
	public static String ConverterGeneralSection_Label_ConverterClass;

	/**
	 * see EditorMessages.properties
	 */
	public static String ValidatorMasterSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String ValidatorMasterSection_Description;

//	public static String ValidatorMasterSection_HelpContextID;
//
//	public static String ValidatorMasterSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String ValidatorGeneralSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String ValidatorGeneralSection_Description;

//	public static String ValidatorGeneralSection_HelpContextID;
//
//	public static String ValidatorGeneralSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String ValidatorGeneralSection_Label_DisplayName;

	/**
	 * see EditorMessages.properties
	 */
	public static String ValidatorGeneralSection_Label_Description;

	/**
	 * see EditorMessages.properties
	 */
	public static String ValidatorGeneralSection_Label_ValidatorID;

	/**
	 * see EditorMessages.properties
	 */
	public static String ValidatorGeneralSection_Label_ValidatorClass;

	/**
	 * see EditorMessages.properties
	 */
	public static String AttributeSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String AttributeSection_Description;

//	public static String AttributeSection_HelpContextID;
//
//	public static String AttributeSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String AttributeSection_Table_NameColumn;

	/**
	 * see EditorMessages.properties
	 */
	public static String AttributeSection_Table_ClassColumn;

	/**
	 * see EditorMessages.properties
	 */
	public static String AttributeSection_Table_DefaultValueColumn;

	/**
	 * see EditorMessages.properties
	 */
	public static String AttributeSection_Table_SuggestedValueColumn;

	/**
	 * see EditorMessages.properties
	 */
	public static String AttributeSection_Dialog_Title_Add;

	/**
	 * see EditorMessages.properties
	 */
	public static String AttributeSection_Dialog_Title_Edit;

	/**
	 * see EditorMessages.properties
	 */
	public static String AttributeSection_Dialog_Tab_General;

	/**
	 * see EditorMessages.properties
	 */
	public static String AttributeSection_Dialog_AttributeName;

	/**
	 * see EditorMessages.properties
	 */
	public static String AttributeSection_Dialog_AttributeClass;

	/**
	 * see EditorMessages.properties
	 */
	public static String AttributeSection_Dialog_DefaultValue;

	/**
	 * see EditorMessages.properties
	 */
	public static String AttributeSection_Dialog_SuggestedValue;

	/**
	 * see EditorMessages.properties
	 */
	public static String AttributeSection_Dialog_Error_NameEmpty;

	/**
	 * see EditorMessages.properties
	 */
	public static String AttributeSection_Dialog_Error_ClassEmpty;

	/**
	 * see EditorMessages.properties
	 */
	public static String PropertySection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String PropertySection_Description;

//	public static String PropertySection_HelpContextID;
//
//	public static String PropertySection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String PropertySection_Table_NameColumn;

	/**
	 * see EditorMessages.properties
	 */
	public static String PropertySection_Table_ClassColumn;

	/**
	 * see EditorMessages.properties
	 */
	public static String PropertySection_Table_DefaultValueColumn;

	/**
	 * see EditorMessages.properties
	 */
	public static String PropertySection_Table_SuggestedValueColumn;

	/**
	 * see EditorMessages.properties
	 */
	public static String PropertySection_Dialog_Title_Add;

	/**
	 * see EditorMessages.properties
	 */
	public static String PropertySection_Dialog_Title_Edit;

	/**
	 * see EditorMessages.properties
	 */
	public static String PropertySection_Dialog_Tab_General;

	/**
	 * see EditorMessages.properties
	 */
	public static String PropertySection_Dialog_PropertyName;

	/**
	 * see EditorMessages.properties
	 */
	public static String PropertySection_Dialog_PropertyClass;

	/**
	 * see EditorMessages.properties
	 */
	public static String PropertySection_Dialog_DefaultValue;

	/**
	 * see EditorMessages.properties
	 */
	public static String PropertySection_Dialog_SuggestedValue;

	/**
	 * see EditorMessages.properties
	 */
	public static String PropertySection_Dialog_Error_NameEmpty;

	/**
	 * see EditorMessages.properties
	 */
	public static String PropertySection_Dialog_Error_ClassEmpty;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacetSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacetSection_Description;

//	public static String FacetSection_HelpContextID;
//
//	public static String FacetsSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacetSection_Table_DisplayNameColumn;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacetSection_Table_FacetNameColumn;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacetSection_Table_DescriptionColumn;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacetSection_Dialog_Title_Add;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacetSection_Dialog_Title_Edit;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacetSection_Dialog_Tab_General;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacetSection_Dialog_FacetName;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacetSection_Dialog_DisplayName;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacetSection_Dialog_Description;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacetSection_Dialog_Error_NameEmpty;

	/**
	 * see EditorMessages.properties
	 */
	public static String OthersPage_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String ActionListenerSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String ActionListenerSection_Description;

//	public static String ActionListenerSection_HelpContextID;
//
//	public static String ActionListenerSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String MessageBundleSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String MessageBundleSection_Description;

//	public static String MessageBundleSection_HelpContextID;
//
//	public static String MessageBundleSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String MessageBundleSection_Dialog_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String MessageBundleSection_Dialog_Message_SelectPropertyFile;

	/**
	 * see EditorMessages.properties
	 */
	public static String MessageBundleSection_Dialog_Message_AlreadyExists;

	/**
	 * see EditorMessages.properties
	 */
	public static String LocaleConfigSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String LocaleConfigSection_Description;

//	public static String LocaleConfigSection_HelpContextID;
//
//	public static String LocaleConfigSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String LocaleConfigSection_Wizard_WindowTitle;

	/**
	 * see EditorMessages.properties
	 */
	public static String LocaleConfigSection_Wizard_Page_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String LocaleConfigSection_Wizard_Page_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String LocaleConfigSection_Wizard_Page_Description;

	/**
	 * see EditorMessages.properties
	 */
	public static String LocaleConfigSection_Wizard_Page_LabelText;

	/**
	 * see EditorMessages.properties
	 */
	public static String LocaleConfigSection_Wizard_Page_AlreadyExistsError;

	/**
	 * see EditorMessages.properties
	 */
	public static String DefaultRenderKitIDSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String DefaultRenderKitIDSection_Description;

//	public static String DefaultRenderKitIDSection_HelpContextID;
//
//	public static String DefaultRenderKitIDSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String DefaultRenderKitIDSection_Wizard_WindowTitle;

	/**
	 * see EditorMessages.properties
	 */
	public static String DefaultRenderKitIDSection_Wizard_Page_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String DefaultRenderKitIDSection_Wizard_Page_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String DefaultRenderKitIDSection_Wizard_Page_Description;

	/**
	 * see EditorMessages.properties
	 */
	public static String DefaultRenderKitIDSection_Wizard_Page_LabelText;

	/**
	 * see EditorMessages.properties
	 */
	public static String DefaultRenderKitIDSection_Wizard_Page_AlreadyExistsError;

	/**
	 * see EditorMessages.properties
	 */
	public static String NavigationHandlerSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String NavigationHandlerSection_Description;

//	public static String NavigationHandlerSection_HelpContextID;
//
//	public static String NavigationHandlerSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String ViewHandlerSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String ViewHandlerSection_Description;

//	public static String ViewHandlerSection_HelpContextID;
//
//	public static String ViewHandlerSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String StateManagerSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String StateManagerSection_Description;

//	public static String StateManagerSection_HelpContextID;
//
//	public static String StateManagerSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String PropertyResolverSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String PropertyResolverSection_Description;

//	public static String PropertyResolverSection_HelpContextID;
//
//	public static String PropertyResolverSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String VariableResolverSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String VariableResolverSection_Description;

//	public static String VariableResolverSection_HelpContextID;
//
//	public static String VariableResolverSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String ApplicationFactorySection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String ApplicationFactorySection_Description;

//	public static String ApplicationFactorySection_HelpContextID;
//
//	public static String ApplicationFactorySection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacesContextFactorySection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacesContextFactorySection_Description;

//	public static String FacesContextFactorySection_HelpContextID;
//
//	public static String FacesContextFactorySection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String LifecycleFactorySection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String LifecycleFactorySection_Description;

//	public static String LifecycleFactorySection_HelpContextID;
//
//	public static String LifecycleFactorySection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String RenderKitFactorySection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String RenderKitFactorySection_Description;

//	public static String RenderKitFactorySection_HelpContextID;
//
//	public static String RenderKitFactorySection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String PhaseListenerSection_Name;

	/**
	 * see EditorMessages.properties
	 */
	public static String PhaseListenerSection_Description;

//	public static String PhaseListenerSection_HelpContextID;
//
//	public static String PhaseListenerSection_HelpToolTip;

	/**
	 * see EditorMessages.properties
	 */
	public static String MultiPageEditorOutlinePage_noOutline;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacesConfigEditor_Error_OpenModel_Title;

	/**
	 * see EditorMessages.properties
	 */
	public static String FacesConfigEditor_Error_OpenModel;
	
	/**
	 * see EditorMessages.properties
	 */
	public static String FacesConfigEditor_WaitForLoadPage_Title;
	
	/**
	 * see EditorMessages.properties
	 */
	public static String ModelLoader_LoadingModelJobName;

	/**
	 * Align bottom action label.
	 */
	public static String AlignmentAction_AlignBottomAction_Label;

	/**
	 * Align bottom action tooltip.
	 */
	public static String AlignmentAction_AlignBottomAction_Tooltip;

	/**
	 * Align center action label.
	 */
	public static String AlignmentAction_AlignCenterAction_Label;

	/**
	 * Align center action tooltip.
	 */
	public static String AlignmentAction_AlignCenterAction_Tooltip;

	/**
	 * Align left action label.
	 */
	public static String AlignmentAction_AlignLeftAction_Label;

	/**
	 * Align left action tooltip.
	 */
	public static String AlignmentAction_AlignLeftAction_Tooltip;

	/**
	 * Align middle action label.
	 */
	public static String AlignmentAction_AlignMiddleAction_Label;

	/**
	 * Align middle action tooltip.
	 */
	public static String AlignmentAction_AlignMiddleAction_Tooltip;

	/**
	 * Align right action label.
	 */
	public static String AlignmentAction_AlignRightAction_Label;

	/**
	 * Align right action tooltip.
	 */
	public static String AlignmentAction_AlignRightAction_Tooltip;

	/**
	 * Align top action label.
	 */
	public static String AlignmentAction_AlignTopAction_Label;

	/**
	 * Align top action tooltip.
	 */
	public static String AlignmentAction_AlignTopAction_Tooltip;

}
