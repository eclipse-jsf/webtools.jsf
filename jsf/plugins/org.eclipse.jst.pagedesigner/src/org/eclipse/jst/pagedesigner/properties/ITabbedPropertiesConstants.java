/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.properties;

/**
 * @author mengbo
 * @version 1.5
 */
public interface ITabbedPropertiesConstants {
	static final String GENERAL_TAB_ID = "org.eclipse.jst.pagedesigner.tabQuickEdit";

	static final String ALL_TAB_ID = "org.eclipse.jst.pagedesigner.tabAttributes";

	static final String DEBUG_TAB_ID = "org.eclipse.jst.pagedesigner.pageDesignerTabDebug";

	public String ACCCESSIBILITY_SECTION_ID = "section.accessibility";

	public String ALL_PROPERTY_SECTION_ID = "section.allproperty";

	/** jsf core tags */
	public String JSF_CORE_ActionListener = "section.jsf_core_actionListener";

	public String JSF_CORE_Attribute = "section.jsf_core_attribute";

	public String JSF_CORE_ConvertDateTime = "section.jsf_core_convertDateTime";

	public String JSF_CORE_Converter = "section.jsf_core_converter";

	public String JSF_CORE_ConvertNumber = "section.jsf_core_convertNumber";

	public String JSF_CORE_Facet = "section.jsf_core_facet";

	public String JSF_CORE_LoadBundle = "section.jsf_core_loadBundle";

	public String JSF_CORE_Param = "section.jsf_core_param";

	public String JSF_CORE_SelectItem = "section.jsf_core_selectItem";

	public String JSF_CORE_SelectItems = "section.jsf_core_selectItems";

	public String JSF_CORE_Subview = "section.jsf_core_subview";

	public String JSF_CORE_ValidateDoubleRange = "section.jsf_core_validateDoubleRange";

	public String JSF_CORE_ValidateLength = "section.jsf_core_validateLength";

	public String JSF_CORE_ValidateLongRange = "section.jsf_core_validateLongRange";

	public String JSF_CORE_Validator = "section.jsf_core_validator";

	public String JSF_CORE_ValueChangeListener = "section.jsf_core_valueChangeListener";

	public String JSF_CORE_Verbatim = "section.jsf_core_verbatim";

	public String JSF_CORE_View = "section.jsf_core_view";

	/** jsf html tags */
	public String JSF_HTML_Column = "section.jsf_html_column";

	public String JSF_HTML_CommandButton = "section.jsf_html_commandButton";

	public String JSF_HTML_CommandLink = "section.jsf_html_commandLink";

	public String JSF_HTML_DataTable = "section.jsf_html_dataTable";

	public String JSF_HTML_Form = "section.jsf_html_form";

	public String JSF_HTML_GraphicImage = "section.jsf_html_graphicImage";

	public String JSF_HTML_InputHidden = "section.jsf_html_inputHidden";

	public String JSF_HTML_InputSecret = "section.jsf_html_inputSecret";

	public String JSF_HTML_InputText = "section.jsf_html_inputText";

	public String JSF_HTML_InputTextarea = "section.jsf_html_inputTextarea";

	public String JSF_HTML_Message = "section.jsf_html_message";

	public String JSF_HTML_Messages = "section.jsf_html_messages";

	public String JSF_HTML_OutputFormat = "section.jsf_html_outputFormat";

	public String JSF_HTML_OutputLabel = "section.jsf_html_outputLabel";

	public String JSF_HTML_OutputLink = "section.jsf_html_outputLink";

	public String JSF_HTML_OutputText = "section.jsf_html_outputText";

	public String JSF_HTML_PanelGrid = "section.jsf_html_panelGrid";

	public String JSF_HTML_PanelGroup = "section.jsf_html_panelGroup";

	public String JSF_HTML_SelectBooleanCheckbox = "section.jsf_html_selectBooleanCheckbox";

	public String JSF_HTML_SelectManyCheckbox = "section.jsf_html_selectManyCheckbox";

	public String JSF_HTML_SelectManyListbox = "section.jsf_html_selectManyListbox";

	public String JSF_HTML_SelectManyMenu = "section.jsf_html_selectManyMenu";

	public String JSF_HTML_SelectOneListbox = "section.jsf_html_selectOneListbox";

	public String JSF_HTML_SelectOneMenu = "section.jsf_html_selectOneMenu";

	public String JSF_HTML_SelectOneRadio = "section.jsf_html_selectOneRadio";

	/** sybase datawindow */
	public String DW_DataWindow = "section.dw_dataWindow";

	public String DW_Objectlink = "section.dw_objectlink";

	// FIXME: when return null, will in fact using "Misc". This may not garantee
	// it be the
	// last category, since is sorted by string order.
	static final String OTHER_CATEGORY = "Other";

}
