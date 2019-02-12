/**
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;

/**
 * Trinidad-related constants.
 * 
 * @author Ian Trimble - Oracle
 */
public interface ITrinidadConstants {

	/**
	 * Trinidad "core" URI.
	 */
	public static final String URI_CORE = "http://myfaces.apache.org/trinidad"; //$NON-NLS-1$

	/**
	 * Trinidad "html" URI.
	 */
	public static final String URI_HTML = "http://myfaces.apache.org/trinidad/html"; //$NON-NLS-1$

	// "Core" tags
	/**
	 * Tag name for "tr:forEach"
	 */
	public static final String TAG_FOREACH = "forEach"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:forEach"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_FOREACH = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_FOREACH);

	/**
	 * Tag name for "tr:setActionListener"
	 */
	public static final String TAG_SETACTIONLISTENER = "setActionListener"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:setActionListener"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SETACTIONLISTENER = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SETACTIONLISTENER);

	/**
	 * Tag name for "tr:fileDownloadActionListener"
	 */
	public static final String TAG_FILEDOWNLOADACTIONLISTENER = "fileDownloadActionListener"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:fileDownloadActionListener"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_FILEDOWNLOADACTIONLISTENER = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_FILEDOWNLOADACTIONLISTENER);

	/**
	 * Tag name for "tr:returnActionListener"
	 */
	public static final String TAG_RETURNACTIONLISTENER = "returnActionListener"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:returnActionListener"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_RETURNACTIONLISTENER = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_RETURNACTIONLISTENER);

	/**
	 * Tag name for "tr:resetActionListener"
	 */
	public static final String TAG_RESETACTIONLISTENER = "resetActionListener"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:resetActionListener"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_RESETACTIONLISTENER = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_RESETACTIONLISTENER);

	/**
	 * Tag name for "tr:componentRef"
	 */
	public static final String TAG_COMPONENTREF = "componentRef"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:componentRef"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_COMPONENTREF = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_COMPONENTREF);

	/**
	 * Tag name for "tr:componentDef"
	 */
	public static final String TAG_COMPONENTDEF = "componentDef"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:componentDef"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_COMPONENTDEF = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_COMPONENTDEF);

	/**
	 * Tag name for "tr:facetRef"
	 */
	public static final String TAG_FACETREF = "facetRef"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:facetRef"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_FACETREF = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_FACETREF);

	/**
	 * Tag name for "tr:breadCrumbs"
	 */
	public static final String TAG_BREADCRUMBS = "breadCrumbs"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:breadCrumbs"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_BREADCRUMBS = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_BREADCRUMBS);

	/**
	 * Tag name for "tr:chart"
	 */
	public static final String TAG_CHART = "chart"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:chart"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_CHART = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_CHART);

	/**
	 * Tag name for "tr:chooseColor"
	 */
	public static final String TAG_CHOOSECOLOR = "chooseColor"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:chooseColor"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_CHOOSECOLOR = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_CHOOSECOLOR);

	/**
	 * Tag name for "tr:chooseDate"
	 */
	public static final String TAG_CHOOSEDATE = "chooseDate"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:chooseDate"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_CHOOSEDATE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_CHOOSEDATE);

	/**
	 * Tag name for "tr:column"
	 */
	public static final String TAG_COLUMN = "column"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:column"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_COLUMN = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_COLUMN);

	/**
	 * Tag name for "tr:commandButton"
	 */
	public static final String TAG_COMMANDBUTTON = "commandButton"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:commandButton"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_COMMANDBUTTON = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_COMMANDBUTTON);

	/**
	 * Tag name for "tr:commandLink"
	 */
	public static final String TAG_COMMANDLINK = "commandLink"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:commandLink"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_COMMANDLINK = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_COMMANDLINK);

	/**
	 * Tag name for "tr:commandNavigationItem"
	 */
	public static final String TAG_COMMANDNAVIGATIONITEM = "commandNavigationItem"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:commandNavigationItem"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_COMMANDNAVIGATIONITEM = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_COMMANDNAVIGATIONITEM);

	/**
	 * Tag name for "tr:document"
	 */
	public static final String TAG_DOCUMENT = "document"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:document"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_DOCUMENT = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_DOCUMENT);

	/**
	 * Tag name for "tr:form"
	 */
	public static final String TAG_FORM = "form"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:form"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_FORM = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_FORM);

	/**
	 * Tag name for "tr:goButton"
	 */
	public static final String TAG_GOBUTTON = "goButton"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:goButton"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_GOBUTTON = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_GOBUTTON);

	/**
	 * Tag name for "tr:goLink"
	 */
	public static final String TAG_GOLINK = "goLink"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:goLink"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_GOLINK = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_GOLINK);

	/**
	 * Tag name for "tr:icon"
	 */
	public static final String TAG_ICON = "icon"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:icon"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_ICON = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_ICON);

	/**
	 * Tag name for "tr:image"
	 */
	public static final String TAG_IMAGE = "image"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:image"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_IMAGE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_IMAGE);

	/**
	 * Tag name for "tr:importScript"
	 */
	public static final String TAG_IMPORTSCRIPT = "importScript"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:importScript"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_IMPORTSCRIPT = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_IMPORTSCRIPT);

	/**
	 * Tag name for "tr:inputColor"
	 */
	public static final String TAG_INPUTCOLOR = "inputColor"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:inputColor"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_INPUTCOLOR = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_INPUTCOLOR);

	/**
	 * Tag name for "tr:inputDate"
	 */
	public static final String TAG_INPUTDATE = "inputDate"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:inputDate"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_INPUTDATE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_INPUTDATE);

	/**
	 * Tag name for "tr:inputFile"
	 */
	public static final String TAG_INPUTFILE = "inputFile"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:inputFile"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_INPUTFILE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_INPUTFILE);

	/**
	 * Tag name for "tr:inputHidden"
	 */
	public static final String TAG_INPUTHIDDEN = "inputHidden"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:inputHidden"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_INPUTHIDDEN = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_INPUTHIDDEN);

	/**
	 * Tag name for "tr:inputListOfValues"
	 */
	public static final String TAG_INPUTLISTOFVALUES = "inputListOfValues"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:inputListOfValues"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_INPUTLISTOFVALUES = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_INPUTLISTOFVALUES);

	/**
	 * Tag name for "tr:inputNumberSpinbox"
	 */
	public static final String TAG_INPUTNUMBERSPINBOX = "inputNumberSpinbox"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:inputNumberSpinbox"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_INPUTNUMBERSPINBOX = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_INPUTNUMBERSPINBOX);

	/**
	 * Tag name for "tr:inputText"
	 */
	public static final String TAG_INPUTTEXT = "inputText"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:inputText"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_INPUTTEXT = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_INPUTTEXT);

	/**
	 * Tag name for "tr:legend"
	 */
	public static final String TAG_LEGEND = "legend"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:legend"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_LEGEND = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_LEGEND);

	/**
	 * Tag name for "tr:media"
	 */
	public static final String TAG_MEDIA = "media"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:media"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_MEDIA = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_MEDIA);

	/**
	 * Tag name for "tr:message"
	 */
	public static final String TAG_MESSAGE = "message"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:message"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_MESSAGE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_MESSAGE);

	/**
	 * Tag name for "tr:messages"
	 */
	public static final String TAG_MESSAGES = "messages"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:messages"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_MESSAGES = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_MESSAGES);

	/**
	 * Tag name for "tr:navigationPane"
	 */
	public static final String TAG_NAVIGATIONPANE = "navigationPane"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:navigationPane"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_NAVIGATIONPANE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_NAVIGATIONPANE);

	/**
	 * Tag name for "tr:navigationTree"
	 */
	public static final String TAG_NAVIGATIONTREE = "navigationTree"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:navigationTree"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_NAVIGATIONTREE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_NAVIGATIONTREE);

	/**
	 * Tag name for "tr:outputDocument"
	 */
	public static final String TAG_OUTPUTDOCUMENT = "outputDocument"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:outputDocument"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_OUTPUTDOCUMENT = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_OUTPUTDOCUMENT);

	/**
	 * Tag name for "tr:outputFormatted"
	 */
	public static final String TAG_OUTPUTFORMATTED = "outputFormatted"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:outputFormatted"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_OUTPUTFORMATTED = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_OUTPUTFORMATTED);

	/**
	 * Tag name for "tr:outputLabel"
	 */
	public static final String TAG_OUTPUTLABEL = "outputLabel"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:outputLabel"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_OUTPUTLABEL = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_OUTPUTLABEL);

	/**
	 * Tag name for "tr:outputText"
	 */
	public static final String TAG_OUTPUTTEXT = "outputText"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:outputText"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_OUTPUTTEXT = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_OUTPUTTEXT);

	/**
	 * Tag name for "tr:page"
	 */
	public static final String TAG_PAGE = "page"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:page"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PAGE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PAGE);

	/**
	 * Tag name for "tr:panelAccordion"
	 */
	public static final String TAG_PANELACCORDION = "panelAccordion"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelAccordion"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELACCORDION = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELACCORDION);

	/**
	 * Tag name for "tr:panelBorderLayout"
	 */
	public static final String TAG_PANELBORDERLAYOUT = "panelBorderLayout"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelBorderLayout"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELBORDERLAYOUT = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELBORDERLAYOUT);

	/**
	 * Tag name for "tr:panelBox"
	 */
	public static final String TAG_PANELBOX = "panelBox"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelBox"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELBOX = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELBOX);

	/**
	 * Tag name for "tr:panelButtonBar"
	 */
	public static final String TAG_PANELBUTTONBAR = "panelButtonBar"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelButtonBar"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELBUTTONBAR = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELBUTTONBAR);

	/**
	 * Tag name for "tr:panelCaptionGroup"
	 */
	public static final String TAG_PANELCAPTIONGROUP = "panelCaptionGroup"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelCaptionGroup"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELCAPTIONGROUP = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELCAPTIONGROUP);

	/**
	 * Tag name for "tr:panelChoice"
	 */
	public static final String TAG_PANELCHOICE = "panelChoice"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelChoice"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELCHOICE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELCHOICE);

	/**
	 * Tag name for "tr:panelFormLayout"
	 */
	public static final String TAG_PANELFORMLAYOUT = "panelFormLayout"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelFormLayout"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELFORMLAYOUT = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELFORMLAYOUT);

	/**
	 * Tag name for "tr:panelGroupLayout"
	 */
	public static final String TAG_PANELGROUPLAYOUT = "panelGroupLayout"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelGroupLayout"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELGROUPLAYOUT = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELGROUPLAYOUT);

	/**
	 * Tag name for "tr:panelHeader"
	 */
	public static final String TAG_PANELHEADER = "panelHeader"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelHeader"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELHEADER = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELHEADER);

	/**
	 * Tag name for "tr:panelHorizontalLayout"
	 */
	public static final String TAG_PANELHORIZONTALLAYOUT = "panelHorizontalLayout"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelHorizontalLayout"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELHORIZONTALLAYOUT = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELHORIZONTALLAYOUT);

	/**
	 * Tag name for "tr:panelLabelAndMessage"
	 */
	public static final String TAG_PANELLABELANDMESSAGE = "panelLabelAndMessage"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelLabelAndMessage"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELLABELANDMESSAGE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELLABELANDMESSAGE);

	/**
	 * Tag name for "tr:panelList"
	 */
	public static final String TAG_PANELLIST = "panelList"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelList"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELLIST = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELLIST);

	/**
	 * Tag name for "tr:panelPage"
	 */
	public static final String TAG_PANELPAGE = "panelPage"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelPage"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELPAGE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELPAGE);

	/**
	 * Tag name for "tr:panelPageHeader"
	 */
	public static final String TAG_PANELPAGEHEADER = "panelPageHeader"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelPageHeader"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELPAGEHEADER = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELPAGEHEADER);

	/**
	 * Tag name for "tr:panelPopup"
	 */
	public static final String TAG_PANELPOPUP = "panelPopup"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelPopup"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELPOPUP = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELPOPUP);

	/**
	 * Tag name for "tr:panelRadio"
	 */
	public static final String TAG_PANELRADIO = "panelRadio"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelRadio"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELRADIO = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELRADIO);

	/**
	 * Tag name for "tr:panelSideBar"
	 */
	public static final String TAG_PANELSIDEBAR = "panelSideBar"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelSideBar"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELSIDEBAR = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELSIDEBAR);

	/**
	 * Tag name for "tr:panelTabbed"
	 */
	public static final String TAG_PANELTABBED = "panelTabbed"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelTabbed"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELTABBED = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELTABBED);

	/**
	 * Tag name for "tr:panelTip"
	 */
	public static final String TAG_PANELTIP = "panelTip"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelTip"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELTIP = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PANELTIP);

	/**
	 * Tag name for "tr:poll"
	 */
	public static final String TAG_POLL = "poll"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:poll"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_POLL = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_POLL);

	/**
	 * Tag name for "tr:processChoiceBar"
	 */
	public static final String TAG_PROCESSCHOICEBAR = "processChoiceBar"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:processChoiceBar"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PROCESSCHOICEBAR = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PROCESSCHOICEBAR);

	/**
	 * Tag name for "tr:progressIndicator"
	 */
	public static final String TAG_PROGRESSINDICATOR = "progressIndicator"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:progressIndicator"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PROGRESSINDICATOR = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_PROGRESSINDICATOR);

	/**
	 * Tag name for "tr:resetButton"
	 */
	public static final String TAG_RESETBUTTON = "resetButton"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:resetButton"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_RESETBUTTON = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_RESETBUTTON);

	/**
	 * Tag name for "tr:selectBooleanCheckbox"
	 */
	public static final String TAG_SELECTBOOLEANCHECKBOX = "selectBooleanCheckbox"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:selectBooleanCheckbox"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SELECTBOOLEANCHECKBOX = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SELECTBOOLEANCHECKBOX);

	/**
	 * Tag name for "tr:selectBooleanRadio"
	 */
	public static final String TAG_SELECTBOOLEANRADIO = "selectBooleanRadio"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:selectBooleanRadio"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SELECTBOOLEANRADIO = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SELECTBOOLEANRADIO);

	/**
	 * Tag name for "tr:selectItem"
	 */
	public static final String TAG_SELECTITEM = "selectItem"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:selectItem"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SELECTITEM = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SELECTITEM);

	/**
	 * Tag name for "tr:selectManyCheckbox"
	 */
	public static final String TAG_SELECTMANYCHECKBOX = "selectManyCheckbox"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:selectManyCheckbox"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SELECTMANYCHECKBOX = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SELECTMANYCHECKBOX);

	/**
	 * Tag name for "tr:selectManyListbox"
	 */
	public static final String TAG_SELECTMANYLISTBOX = "selectManyListbox"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:selectManyListbox"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SELECTMANYLISTBOX = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SELECTMANYLISTBOX);

	/**
	 * Tag name for "tr:selectManyShuttle"
	 */
	public static final String TAG_SELECTMANYSHUTTLE = "selectManyShuttle"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:selectManyShuttle"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SELECTMANYSHUTTLE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SELECTMANYSHUTTLE);

	/**
	 * Tag name for "tr:selectOneChoice"
	 */
	public static final String TAG_SELECTONECHOICE = "selectOneChoice"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:selectOneChoice"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SELECTONECHOICE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SELECTONECHOICE);

	/**
	 * Tag name for "tr:selectOneListbox"
	 */
	public static final String TAG_SELECTONELISTBOX = "selectOneListbox"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:selectOneListbox"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SELECTONELISTBOX = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SELECTONELISTBOX);

	/**
	 * Tag name for "tr:selectOneRadio"
	 */
	public static final String TAG_SELECTONERADIO = "selectOneRadio"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:selectOneRadio"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SELECTONERADIO = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SELECTONERADIO);

	/**
	 * Tag name for "tr:selectOrderShuttle"
	 */
	public static final String TAG_SELECTORDERSHUTTLE = "selectOrderShuttle"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:selectOrderShuttle"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SELECTORDERSHUTTLE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SELECTORDERSHUTTLE);

	/**
	 * Tag name for "tr:selectRangeChoiceBar"
	 */
	public static final String TAG_SELECTRANGECHOICEBAR = "selectRangeChoiceBar"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:selectRangeChoiceBar"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SELECTRANGECHOICEBAR = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SELECTRANGECHOICEBAR);

	/**
	 * Tag name for "tr:separator"
	 */
	public static final String TAG_SEPARATOR = "separator"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:separator"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SEPARATOR = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SEPARATOR);

	/**
	 * Tag name for "tr:showDetail"
	 */
	public static final String TAG_SHOWDETAIL = "showDetail"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:showDetail"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SHOWDETAIL = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SHOWDETAIL);

	/**
	 * Tag name for "tr:showDetailHeader"
	 */
	public static final String TAG_SHOWDETAILHEADER = "showDetailHeader"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:showDetailHeader"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SHOWDETAILHEADER = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SHOWDETAILHEADER);

	/**
	 * Tag name for "tr:showDetailItem"
	 */
	public static final String TAG_SHOWDETAILITEM = "showDetailItem"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:showDetailItem"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SHOWDETAILITEM = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SHOWDETAILITEM);

	/**
	 * Tag name for "tr:singleStepButtonBar"
	 */
	public static final String TAG_SINGLESTEPBUTTONBAR = "singleStepButtonBar"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:singleStepButtonBar"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SINGLESTEPBUTTONBAR = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SINGLESTEPBUTTONBAR);

	/**
	 * Tag name for "tr:spacer"
	 */
	public static final String TAG_SPACER = "spacer"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:spacer"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SPACER = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SPACER);

	/**
	 * Tag name for "tr:statusIndicator"
	 */
	public static final String TAG_STATUSINDICATOR = "statusIndicator"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:statusIndicator"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_STATUSINDICATOR = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_STATUSINDICATOR);

	/**
	 * Tag name for "tr:subform"
	 */
	public static final String TAG_SUBFORM = "subform"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:subform"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SUBFORM = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SUBFORM);

	/**
	 * Tag name for "tr:table"
	 */
	public static final String TAG_TABLE = "table"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:table"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_TABLE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_TABLE);

	/**
	 * Tag name for "tr:train"
	 */
	public static final String TAG_TRAIN = "train"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:train"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_TRAIN = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_TRAIN);

	/**
	 * Tag name for "tr:tree"
	 */
	public static final String TAG_TREE = "tree"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:tree"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_TREE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_TREE);

	/**
	 * Tag name for "tr:treeTable"
	 */
	public static final String TAG_TREETABLE = "treeTable"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:treeTable"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_TREETABLE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_TREETABLE);

	/**
	 * Tag name for "tr:group"
	 */
	public static final String TAG_GROUP = "group"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:group"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_GROUP = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_GROUP);

	/**
	 * Tag name for "tr:iterator"
	 */
	public static final String TAG_ITERATOR = "iterator"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:iterator"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_ITERATOR = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_ITERATOR);

	/**
	 * Tag name for "tr:switcher"
	 */
	public static final String TAG_SWITCHER = "switcher"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:switcher"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SWITCHER = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_SWITCHER);

	/**
	 * Tag name for "tr:convertColor"
	 */
	public static final String TAG_CONVERTCOLOR = "convertColor"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:convertColor"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_CONVERTCOLOR = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_CONVERTCOLOR);

	/**
	 * Tag name for "tr:convertDateTime"
	 */
	public static final String TAG_CONVERTDATETIME = "convertDateTime"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:convertDateTime"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_CONVERTDATETIME = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_CONVERTDATETIME);

	/**
	 * Tag name for "tr:convertNumber"
	 */
	public static final String TAG_CONVERTNUMBER = "convertNumber"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:convertNumber"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_CONVERTNUMBER = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_CONVERTNUMBER);

	/**
	 * Tag name for "tr:validateByteLength"
	 */
	public static final String TAG_VALIDATEBYTELENGTH = "validateByteLength"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:validateByteLength"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_VALIDATEBYTELENGTH = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_VALIDATEBYTELENGTH);

	/**
	 * Tag name for "tr:validateDateRestriction"
	 */
	public static final String TAG_VALIDATEDATERESTRICTION = "validateDateRestriction"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:validateDateRestriction"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_VALIDATEDATERESTRICTION = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_VALIDATEDATERESTRICTION);

	/**
	 * Tag name for "tr:validateDateTimeRange"
	 */
	public static final String TAG_VALIDATEDATETIMERANGE = "validateDateTimeRange"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:validateDateTimeRange"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_VALIDATEDATETIMERANGE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_VALIDATEDATETIMERANGE);

	/**
	 * Tag name for "tr:validateDoubleRange"
	 */
	public static final String TAG_VALIDATEDOUBLERANGE = "validateDoubleRange"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:validateDoubleRange"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_VALIDATEDOUBLERANGE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_VALIDATEDOUBLERANGE);

	/**
	 * Tag name for "tr:validateLength"
	 */
	public static final String TAG_VALIDATELENGTH = "validateLength"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:validateLength"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_VALIDATELENGTH = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_VALIDATELENGTH);

	/**
	 * Tag name for "tr:validateLongRange"
	 */
	public static final String TAG_VALIDATELONGRANGE = "validateLongRange"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:validateLongRange"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_VALIDATELONGRANGE = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_VALIDATELONGRANGE);

	/**
	 * Tag name for "tr:validateRegExp"
	 */
	public static final String TAG_VALIDATEREGEXP = "validateRegExp"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:validateRegExp"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_VALIDATEREGEXP = TagIdentifierFactory
			.createJSPTagWrapper(URI_CORE, TAG_VALIDATEREGEXP);

	// "HTML" tags
	/**
	 * Tag name for "trh:styleSheet"
	 */
	public static final String TAG_STYLESHEET = "styleSheet"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "trh:styleSheet"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_STYLESHEET = TagIdentifierFactory
			.createJSPTagWrapper(URI_HTML, TAG_STYLESHEET);

	/**
	 * Tag name for "trh:body"
	 */
	public static final String TAG_BODY = "body"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "trh:body"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_BODY = TagIdentifierFactory
			.createJSPTagWrapper(URI_HTML, TAG_BODY);

	/**
	 * Tag name for "trh:cellFormat"
	 */
	public static final String TAG_CELLFORMAT = "cellFormat"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "trh:cellFormat"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_CELLFORMAT = TagIdentifierFactory
			.createJSPTagWrapper(URI_HTML, TAG_CELLFORMAT);

	/**
	 * Tag name for "trh:frame"
	 */
	public static final String TAG_FRAME = "frame"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "trh:frame"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_FRAME = TagIdentifierFactory
			.createJSPTagWrapper(URI_HTML, TAG_FRAME);

	/**
	 * Tag name for "trh:frameBorderLayout"
	 */
	public static final String TAG_FRAMEBORDERLAYOUT = "frameBorderLayout"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "trh:frameBorderLayout"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_FRAMEBORDERLAYOUT = TagIdentifierFactory
			.createJSPTagWrapper(URI_HTML, TAG_FRAMEBORDERLAYOUT);

	/**
	 * Tag name for "trh:head"
	 */
	public static final String TAG_HEAD = "head"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "trh:head"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_HEAD = TagIdentifierFactory
			.createJSPTagWrapper(URI_HTML, TAG_HEAD);

	/**
	 * Tag name for "trh:html"
	 */
	public static final String TAG_HTML = "html"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "trh:html"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_HTML = TagIdentifierFactory
			.createJSPTagWrapper(URI_HTML, TAG_HTML);

	/**
	 * Tag name for "trh:rowLayout"
	 */
	public static final String TAG_ROWLAYOUT = "rowLayout"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "trh:rowLayout"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_ROWLAYOUT = TagIdentifierFactory
			.createJSPTagWrapper(URI_HTML, TAG_ROWLAYOUT);

	/**
	 * Tag name for "trh:script"
	 */
	public static final String TAG_SCRIPT = "script"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "trh:script"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SCRIPT = TagIdentifierFactory
			.createJSPTagWrapper(URI_HTML, TAG_SCRIPT);

	/**
	 * Tag name for "trh:tableLayout"
	 */
	public static final String TAG_TABLELAYOUT = "tableLayout"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "trh:tableLayout"
	 */
	public static final TagIdentifier TAG_IDENTIFIER_TABLELAYOUT = TagIdentifierFactory
			.createJSPTagWrapper(URI_HTML, TAG_TABLELAYOUT);

//Attributes
	/**
	* "summary" tag attribute name
	*/
	public static final String ATTR_SUMMARY = "summary"; //$NON-NLS-1$

	/**
	* "initialFocusId" tag attribute name
	*/
	public static final String ATTR_INITIALFOCUSID = "initialFocusId"; //$NON-NLS-1$

	/**
	* "accessKey" tag attribute name
	*/
	public static final String ATTR_ACCESSKEY = "accessKey"; //$NON-NLS-1$

	/**
	* "colorData" tag attribute name
	*/
	public static final String ATTR_COLORDATA = "colorData"; //$NON-NLS-1$

	/**
	* "messageDetailConvertBoth" tag attribute name
	*/
	public static final String ATTR_MESSAGEDETAILCONVERTBOTH = "messageDetailConvertBoth"; //$NON-NLS-1$

	/**
	* "defaultSortOrder" tag attribute name
	*/
	public static final String ATTR_DEFAULTSORTORDER = "defaultSortOrder"; //$NON-NLS-1$

	/**
	* "columns" tag attribute name
	*/
	public static final String ATTR_COLUMNS = "columns"; //$NON-NLS-1$

	/**
	* "chromeType" tag attribute name
	*/
	public static final String ATTR_CHROMETYPE = "chromeType"; //$NON-NLS-1$

	/**
	* "rowHeader" tag attribute name
	*/
	public static final String ATTR_ROWHEADER = "rowHeader"; //$NON-NLS-1$

	/**
	* "transparentAllowed" tag attribute name
	*/
	public static final String ATTR_TRANSPARENTALLOWED = "transparentAllowed"; //$NON-NLS-1$

	/**
	* "rootNodeRendered" tag attribute name
	*/
	public static final String ATTR_ROOTNODERENDERED = "rootNodeRendered"; //$NON-NLS-1$

	/**
	* "rangeChangeListener" tag attribute name
	*/
	public static final String ATTR_RANGECHANGELISTENER = "rangeChangeListener"; //$NON-NLS-1$

	/**
	* "messageDetailConvertCurrency" tag attribute name
	*/
	public static final String ATTR_MESSAGEDETAILCONVERTCURRENCY = "messageDetailConvertCurrency"; //$NON-NLS-1$

	/**
	* "patterns" tag attribute name
	*/
	public static final String ATTR_PATTERNS = "patterns"; //$NON-NLS-1$

	/**
	* "disclosedRowKeys" tag attribute name
	*/
	public static final String ATTR_DISCLOSEDROWKEYS = "disclosedRowKeys"; //$NON-NLS-1$

	/**
	* "ondblclick" tag attribute name
	*/
	public static final String ATTR_ONDBLCLICK = "ondblclick"; //$NON-NLS-1$

	/**
	* "windowWidth" tag attribute name
	*/
	public static final String ATTR_WINDOWWIDTH = "windowWidth"; //$NON-NLS-1$

	/**
	* "rendered" tag attribute name
	*/
	public static final String ATTR_RENDERED = "rendered"; //$NON-NLS-1$

	/**
	* "invalidDays" tag attribute name
	*/
	public static final String ATTR_INVALIDDAYS = "invalidDays"; //$NON-NLS-1$

	/**
	* "styleUsage" tag attribute name
	*/
	public static final String ATTR_STYLEUSAGE = "styleUsage"; //$NON-NLS-1$

	/**
	* "blocking" tag attribute name
	*/
	public static final String ATTR_BLOCKING = "blocking"; //$NON-NLS-1$

	/**
	* "gradientsUsed" tag attribute name
	*/
	public static final String ATTR_GRADIENTSUSED = "gradientsUsed"; //$NON-NLS-1$

	/**
	* "legendPosition" tag attribute name
	*/
	public static final String ATTR_LEGENDPOSITION = "legendPosition"; //$NON-NLS-1$

	/**
	* "rowDisclosureListener" tag attribute name
	*/
	public static final String ATTR_ROWDISCLOSURELISTENER = "rowDisclosureListener"; //$NON-NLS-1$

	/**
	* "binding" tag attribute name
	*/
	public static final String ATTR_BINDING = "binding"; //$NON-NLS-1$

	/**
	* "disclosureListener" tag attribute name
	*/
	public static final String ATTR_DISCLOSURELISTENER = "disclosureListener"; //$NON-NLS-1$

	/**
	* "separatorClass" tag attribute name
	*/
	public static final String ATTR_SEPARATORCLASS = "separatorClass"; //$NON-NLS-1$

	/**
	* "showRequired" tag attribute name
	*/
	public static final String ATTR_SHOWREQUIRED = "showRequired"; //$NON-NLS-1$

	/**
	* "messageDetailInvalidDaysOfWeek" tag attribute name
	*/
	public static final String ATTR_MESSAGEDETAILINVALIDDAYSOFWEEK = "messageDetailInvalidDaysOfWeek"; //$NON-NLS-1$

	/**
	* "minimum" tag attribute name
	*/
	public static final String ATTR_MINIMUM = "minimum"; //$NON-NLS-1$

	/**
	* "simple" tag attribute name
	*/
	public static final String ATTR_SIMPLE = "simple"; //$NON-NLS-1$

	/**
	* "sortable" tag attribute name
	*/
	public static final String ATTR_SORTABLE = "sortable"; //$NON-NLS-1$

	/**
	* "discloseMany" tag attribute name
	*/
	public static final String ATTR_DISCLOSEMANY = "discloseMany"; //$NON-NLS-1$

	/**
	* "maximumLength" tag attribute name
	*/
	public static final String ATTR_MAXIMUMLENGTH = "maximumLength"; //$NON-NLS-1$

	/**
	* "visited" tag attribute name
	*/
	public static final String ATTR_VISITED = "visited"; //$NON-NLS-1$

	/**
	* "truncateAt" tag attribute name
	*/
	public static final String ATTR_TRUNCATEAT = "truncateAt"; //$NON-NLS-1$

	/**
	* "auxiliary1Size" tag attribute name
	*/
	public static final String ATTR_AUXILIARY1SIZE = "auxiliary1Size"; //$NON-NLS-1$

	/**
	* "converter" tag attribute name
	*/
	public static final String ATTR_CONVERTER = "converter"; //$NON-NLS-1$

	/**
	* "reorderOnly" tag attribute name
	*/
	public static final String ATTR_REORDERONLY = "reorderOnly"; //$NON-NLS-1$

	/**
	* "maxValue" tag attribute name
	*/
	public static final String ATTR_MAXVALUE = "maxValue"; //$NON-NLS-1$

	/**
	* "rowSelection" tag attribute name
	*/
	public static final String ATTR_ROWSELECTION = "rowSelection"; //$NON-NLS-1$

	/**
	* "tooltipsVisible" tag attribute name
	*/
	public static final String ATTR_TOOLTIPSVISIBLE = "tooltipsVisible"; //$NON-NLS-1$

	/**
	* "sortProperty" tag attribute name
	*/
	public static final String ATTR_SORTPROPERTY = "sortProperty"; //$NON-NLS-1$

	/**
	* "YMajorGridLineCount" tag attribute name
	*/
	public static final String ATTR_YMAJORGRIDLINECOUNT = "YMajorGridLineCount"; //$NON-NLS-1$

	/**
	* "yoffset" tag attribute name
	*/
	public static final String ATTR_YOFFSET = "yoffset"; //$NON-NLS-1$

	/**
	* "shortDesc" tag attribute name
	*/
	public static final String ATTR_SHORTDESC = "shortDesc"; //$NON-NLS-1$

	/**
	* "messageDetailConvertNumber" tag attribute name
	*/
	public static final String ATTR_MESSAGEDETAILCONVERTNUMBER = "messageDetailConvertNumber"; //$NON-NLS-1$

	/**
	* "listStyle" tag attribute name
	*/
	public static final String ATTR_LISTSTYLE = "listStyle"; //$NON-NLS-1$

	/**
	* "readOnly" tag attribute name
	*/
	public static final String ATTR_READONLY = "readOnly"; //$NON-NLS-1$

	/**
	* "pattern" tag attribute name
	*/
	public static final String ATTR_PATTERN = "pattern"; //$NON-NLS-1$

	/**
	* "background" tag attribute name
	*/
	public static final String ATTR_BACKGROUND = "background"; //$NON-NLS-1$

	/**
	* "animationDuration" tag attribute name
	*/
	public static final String ATTR_ANIMATIONDURATION = "animationDuration"; //$NON-NLS-1$

	/**
	* "labelAndAccessKey" tag attribute name
	*/
	public static final String ATTR_LABELANDACCESSKEY = "labelAndAccessKey"; //$NON-NLS-1$

	/**
	* "titleClass" tag attribute name
	*/
	public static final String ATTR_TITLECLASS = "titleClass"; //$NON-NLS-1$

	/**
	* "minFractionDigits" tag attribute name
	*/
	public static final String ATTR_MINFRACTIONDIGITS = "minFractionDigits"; //$NON-NLS-1$

	/**
	* "to" tag attribute name
	*/
	public static final String ATTR_TO = "to"; //$NON-NLS-1$

	/**
	* "player" tag attribute name
	*/
	public static final String ATTR_PLAYER = "player"; //$NON-NLS-1$

	/**
	* "globalOnly" tag attribute name
	*/
	public static final String ATTR_GLOBALONLY = "globalOnly"; //$NON-NLS-1$

	/**
	* "messageDetailInvalidMonths" tag attribute name
	*/
	public static final String ATTR_MESSAGEDETAILINVALIDMONTHS = "messageDetailInvalidMonths"; //$NON-NLS-1$

	/**
	* "secondaryPattern" tag attribute name
	*/
	public static final String ATTR_SECONDARYPATTERN = "secondaryPattern"; //$NON-NLS-1$

	/**
	* "previousAction" tag attribute name
	*/
	public static final String ATTR_PREVIOUSACTION = "previousAction"; //$NON-NLS-1$

	/**
	* "columnBandingInterval" tag attribute name
	*/
	public static final String ATTR_COLUMNBANDINGINTERVAL = "columnBandingInterval"; //$NON-NLS-1$

	/**
	* "noWrap" tag attribute name
	*/
	public static final String ATTR_NOWRAP = "noWrap"; //$NON-NLS-1$

	/**
	* "startLevel" tag attribute name
	*/
	public static final String ATTR_STARTLEVEL = "startLevel"; //$NON-NLS-1$

	/**
	* "valueChangeListener" tag attribute name
	*/
	public static final String ATTR_VALUECHANGELISTENER = "valueChangeListener"; //$NON-NLS-1$

	/**
	* "compact" tag attribute name
	*/
	public static final String ATTR_COMPACT = "compact"; //$NON-NLS-1$

	/**
	* "autoSubmit" tag attribute name
	*/
	public static final String ATTR_AUTOSUBMIT = "autoSubmit"; //$NON-NLS-1$

	/**
	* "height" tag attribute name
	*/
	public static final String ATTR_HEIGHT = "height"; //$NON-NLS-1$

	/**
	* "horizontalGridVisible" tag attribute name
	*/
	public static final String ATTR_HORIZONTALGRIDVISIBLE = "horizontalGridVisible"; //$NON-NLS-1$

	/**
	* "YMinorGridLineCount" tag attribute name
	*/
	public static final String ATTR_YMINORGRIDLINECOUNT = "YMinorGridLineCount"; //$NON-NLS-1$

	/**
	* "description" tag attribute name
	*/
	public static final String ATTR_DESCRIPTION = "description"; //$NON-NLS-1$

	/**
	* "onblur" tag attribute name
	*/
	public static final String ATTR_ONBLUR = "onblur"; //$NON-NLS-1$

	/**
	* "longDesc" tag attribute name
	*/
	public static final String ATTR_LONGDESC = "longDesc"; //$NON-NLS-1$

	/**
	* "perspective" tag attribute name
	*/
	public static final String ATTR_PERSPECTIVE = "perspective"; //$NON-NLS-1$

	/**
	* "trailingHeader" tag attribute name
	*/
	public static final String ATTR_TRAILINGHEADER = "trailingHeader"; //$NON-NLS-1$

	/**
	* "text" tag attribute name
	*/
	public static final String ATTR_TEXT = "text"; //$NON-NLS-1$

	/**
	* "messageDetailConvert" tag attribute name
	*/
	public static final String ATTR_MESSAGEDETAILCONVERT = "messageDetailConvert"; //$NON-NLS-1$

	/**
	* "onmouseout" tag attribute name
	*/
	public static final String ATTR_ONMOUSEOUT = "onmouseout"; //$NON-NLS-1$

	/**
	* "partialSubmit" tag attribute name
	*/
	public static final String ATTR_PARTIALSUBMIT = "partialSubmit"; //$NON-NLS-1$

	/**
	* "align" tag attribute name
	*/
	public static final String ATTR_ALIGN = "align"; //$NON-NLS-1$

	/**
	* "width" tag attribute name
	*/
	public static final String ATTR_WIDTH = "width"; //$NON-NLS-1$

	/**
	* "onkeyup" tag attribute name
	*/
	public static final String ATTR_ONKEYUP = "onkeyup"; //$NON-NLS-1$

	/**
	* "onfocus" tag attribute name
	*/
	public static final String ATTR_ONFOCUS = "onfocus"; //$NON-NLS-1$

	/**
	* "label" tag attribute name
	*/
	public static final String ATTR_LABEL = "label"; //$NON-NLS-1$

	/**
	* "componentType" tag attribute name
	*/
	public static final String ATTR_COMPONENTTYPE = "componentType"; //$NON-NLS-1$

	/**
	* "autostart" tag attribute name
	*/
	public static final String ATTR_AUTOSTART = "autostart"; //$NON-NLS-1$

	/**
	* "discloseNone" tag attribute name
	*/
	public static final String ATTR_DISCLOSENONE = "discloseNone"; //$NON-NLS-1$

	/**
	* "size" tag attribute name
	*/
	public static final String ATTR_SIZE = "size"; //$NON-NLS-1$

	/**
	* "standbyText" tag attribute name
	*/
	public static final String ATTR_STANDBYTEXT = "standbyText"; //$NON-NLS-1$

	/**
	* "onselect" tag attribute name
	*/
	public static final String ATTR_ONSELECT = "onselect"; //$NON-NLS-1$

	/**
	* "items" tag attribute name
	*/
	public static final String ATTR_ITEMS = "items"; //$NON-NLS-1$

	/**
	* "names" tag attribute name
	*/
	public static final String ATTR_NAMES = "names"; //$NON-NLS-1$

	/**
	* "maximum" tag attribute name
	*/
	public static final String ATTR_MAXIMUM = "maximum"; //$NON-NLS-1$

	/**
	* "disclosed" tag attribute name
	*/
	public static final String ATTR_DISCLOSED = "disclosed"; //$NON-NLS-1$

	/**
	* "rowsByDepth" tag attribute name
	*/
	public static final String ATTR_ROWSBYDEPTH = "rowsByDepth"; //$NON-NLS-1$

	/**
	* "maxStep" tag attribute name
	*/
	public static final String ATTR_MAXSTEP = "maxStep"; //$NON-NLS-1$

	/**
	* "var" tag attribute name
	*/
	public static final String ATTR_VAR = "var"; //$NON-NLS-1$

	/**
	* "focusRowKey" tag attribute name
	*/
	public static final String ATTR_FOCUSROWKEY = "focusRowKey"; //$NON-NLS-1$

	/**
	* "unselectedLabel" tag attribute name
	*/
	public static final String ATTR_UNSELECTEDLABEL = "unselectedLabel"; //$NON-NLS-1$

	/**
	* "searchDesc" tag attribute name
	*/
	public static final String ATTR_SEARCHDESC = "searchDesc"; //$NON-NLS-1$

	/**
	* "pollListener" tag attribute name
	*/
	public static final String ATTR_POLLLISTENER = "pollListener"; //$NON-NLS-1$

	/**
	* "name" tag attribute name
	*/
	public static final String ATTR_NAME = "name"; //$NON-NLS-1$

	/**
	* "valueAndAccessKey" tag attribute name
	*/
	public static final String ATTR_VALUEANDACCESSKEY = "valueAndAccessKey"; //$NON-NLS-1$

	/**
	* "XMajorGridLineCount" tag attribute name
	*/
	public static final String ATTR_XMAJORGRIDLINECOUNT = "XMajorGridLineCount"; //$NON-NLS-1$

	/**
	* "nextActionListener" tag attribute name
	*/
	public static final String ATTR_NEXTACTIONLISTENER = "nextActionListener"; //$NON-NLS-1$

	/**
	* "maxIntegerDigits" tag attribute name
	*/
	public static final String ATTR_MAXINTEGERDIGITS = "maxIntegerDigits"; //$NON-NLS-1$

	/**
	* "messageDetailConvertTime" tag attribute name
	*/
	public static final String ATTR_MESSAGEDETAILCONVERTTIME = "messageDetailConvertTime"; //$NON-NLS-1$

	/**
	* "icon" tag attribute name
	*/
	public static final String ATTR_ICON = "icon"; //$NON-NLS-1$

	/**
	* "undisclosedText" tag attribute name
	*/
	public static final String ATTR_UNDISCLOSEDTEXT = "undisclosedText"; //$NON-NLS-1$

	/**
	* "disclosedText" tag attribute name
	*/
	public static final String ATTR_DISCLOSEDTEXT = "disclosedText"; //$NON-NLS-1$

	/**
	* "onmouseover" tag attribute name
	*/
	public static final String ATTR_ONMOUSEOVER = "onmouseover"; //$NON-NLS-1$

	/**
	* "messageDetailConvertPattern" tag attribute name
	*/
	public static final String ATTR_MESSAGEDETAILCONVERTPATTERN = "messageDetailConvertPattern"; //$NON-NLS-1$

	/**
	* "message" tag attribute name
	*/
	public static final String ATTR_MESSAGE = "message"; //$NON-NLS-1$

	/**
	* "immediate" tag attribute name
	*/
	public static final String ATTR_IMMEDIATE = "immediate"; //$NON-NLS-1$

	/**
	* "messageDetailNoMatch" tag attribute name
	*/
	public static final String ATTR_MESSAGEDETAILNOMATCH = "messageDetailNoMatch"; //$NON-NLS-1$

	/**
	* "source" tag attribute name
	*/
	public static final String ATTR_SOURCE = "source"; //$NON-NLS-1$

	/**
	* "usesUpload" tag attribute name
	*/
	public static final String ATTR_USESUPLOAD = "usesUpload"; //$NON-NLS-1$

	/**
	* "onclick" tag attribute name
	*/
	public static final String ATTR_ONCLICK = "onclick"; //$NON-NLS-1$

	/**
	* "valign" tag attribute name
	*/
	public static final String ATTR_VALIGN = "valign"; //$NON-NLS-1$

	/**
	* "disabled" tag attribute name
	*/
	public static final String ATTR_DISABLED = "disabled"; //$NON-NLS-1$

	/**
	* "nextAction" tag attribute name
	*/
	public static final String ATTR_NEXTACTION = "nextAction"; //$NON-NLS-1$

	/**
	* "facetName" tag attribute name
	*/
	public static final String ATTR_FACETNAME = "facetName"; //$NON-NLS-1$

	/**
	* "for" tag attribute name
	*/
	public static final String ATTR_FOR = "for"; //$NON-NLS-1$

	/**
	* "defaultFacet" tag attribute name
	*/
	public static final String ATTR_DEFAULTFACET = "defaultFacet"; //$NON-NLS-1$

	/**
	* "defaultCommand" tag attribute name
	*/
	public static final String ATTR_DEFAULTCOMMAND = "defaultCommand"; //$NON-NLS-1$

	/**
	* "minValue" tag attribute name
	*/
	public static final String ATTR_MINVALUE = "minValue"; //$NON-NLS-1$

	/**
	* "labelStyle" tag attribute name
	*/
	public static final String ATTR_LABELSTYLE = "labelStyle"; //$NON-NLS-1$

	/**
	* "textAndAccessKey" tag attribute name
	*/
	public static final String ATTR_TEXTANDACCESSKEY = "textAndAccessKey"; //$NON-NLS-1$

	/**
	* "messageDetailConvertPercent" tag attribute name
	*/
	public static final String ATTR_MESSAGEDETAILCONVERTPERCENT = "messageDetailConvertPercent"; //$NON-NLS-1$

	/**
	* "valuePassThru" tag attribute name
	*/
	public static final String ATTR_VALUEPASSTHRU = "valuePassThru"; //$NON-NLS-1$

	/**
	* "imageMapType" tag attribute name
	*/
	public static final String ATTR_IMAGEMAPTYPE = "imageMapType"; //$NON-NLS-1$

	/**
	* "chartDrillDownListener" tag attribute name
	*/
	public static final String ATTR_CHARTDRILLDOWNLISTENER = "chartDrillDownListener"; //$NON-NLS-1$

	/**
	* "fieldWidth" tag attribute name
	*/
	public static final String ATTR_FIELDWIDTH = "fieldWidth"; //$NON-NLS-1$

	/**
	* "auxiliaryGlobalSize" tag attribute name
	*/
	public static final String ATTR_AUXILIARYGLOBALSIZE = "auxiliaryGlobalSize"; //$NON-NLS-1$

	/**
	* "triggerType" tag attribute name
	*/
	public static final String ATTR_TRIGGERTYPE = "triggerType"; //$NON-NLS-1$

	/**
	* "orientation" tag attribute name
	*/
	public static final String ATTR_ORIENTATION = "orientation"; //$NON-NLS-1$

	/**
	* "inlineStyle" tag attribute name
	*/
	public static final String ATTR_INLINESTYLE = "inlineStyle"; //$NON-NLS-1$

	/**
	* "timeStyle" tag attribute name
	*/
	public static final String ATTR_TIMESTYLE = "timeStyle"; //$NON-NLS-1$

	/**
	* "flex" tag attribute name
	*/
	public static final String ATTR_FLEX = "flex"; //$NON-NLS-1$

	/**
	* "destination" tag attribute name
	*/
	public static final String ATTR_DESTINATION = "destination"; //$NON-NLS-1$

	/**
	* "innerWidth" tag attribute name
	*/
	public static final String ATTR_INNERWIDTH = "innerWidth"; //$NON-NLS-1$

	/**
	* "useWindow" tag attribute name
	*/
	public static final String ATTR_USEWINDOW = "useWindow"; //$NON-NLS-1$

	/**
	* "verticalGridVisible" tag attribute name
	*/
	public static final String ATTR_VERTICALGRIDVISIBLE = "verticalGridVisible"; //$NON-NLS-1$

	/**
	* "onmousedown" tag attribute name
	*/
	public static final String ATTR_ONMOUSEDOWN = "onmousedown"; //$NON-NLS-1$

	/**
	* "invalidDaysOfWeek" tag attribute name
	*/
	public static final String ATTR_INVALIDDAYSOFWEEK = "invalidDaysOfWeek"; //$NON-NLS-1$

	/**
	* "headerNoWrap" tag attribute name
	*/
	public static final String ATTR_HEADERNOWRAP = "headerNoWrap"; //$NON-NLS-1$

	/**
	* "invalidMonths" tag attribute name
	*/
	public static final String ATTR_INVALIDMONTHS = "invalidMonths"; //$NON-NLS-1$

	/**
	* "headerText" tag attribute name
	*/
	public static final String ATTR_HEADERTEXT = "headerText"; //$NON-NLS-1$

	/**
	* "actionListener" tag attribute name
	*/
	public static final String ATTR_ACTIONLISTENER = "actionListener"; //$NON-NLS-1$

	/**
	* "attributeChangeListener" tag attribute name
	*/
	public static final String ATTR_ATTRIBUTECHANGELISTENER = "attributeChangeListener"; //$NON-NLS-1$

	/**
	* "position" tag attribute name
	*/
	public static final String ATTR_POSITION = "position"; //$NON-NLS-1$

	/**
	* "previousActionListener" tag attribute name
	*/
	public static final String ATTR_PREVIOUSACTIONLISTENER = "previousActionListener"; //$NON-NLS-1$

	/**
	* "interval" tag attribute name
	*/
	public static final String ATTR_INTERVAL = "interval"; //$NON-NLS-1$

	/**
	* "allDetailsEnabled" tag attribute name
	*/
	public static final String ATTR_ALLDETAILSENABLED = "allDetailsEnabled"; //$NON-NLS-1$

	/**
	* "validator" tag attribute name
	*/
	public static final String ATTR_VALIDATOR = "validator"; //$NON-NLS-1$

	/**
	* "onunload" tag attribute name
	*/
	public static final String ATTR_ONUNLOAD = "onunload"; //$NON-NLS-1$

	/**
	* "maxFractionDigits" tag attribute name
	*/
	public static final String ATTR_MAXFRACTIONDIGITS = "maxFractionDigits"; //$NON-NLS-1$

	/**
	* "leadingDescShown" tag attribute name
	*/
	public static final String ATTR_LEADINGDESCSHOWN = "leadingDescShown"; //$NON-NLS-1$

	/**
	* "emptyText" tag attribute name
	*/
	public static final String ATTR_EMPTYTEXT = "emptyText"; //$NON-NLS-1$

	/**
	* "trailingDescShown" tag attribute name
	*/
	public static final String ATTR_TRAILINGDESCSHOWN = "trailingDescShown"; //$NON-NLS-1$

	/**
	* "escape" tag attribute name
	*/
	public static final String ATTR_ESCAPE = "escape"; //$NON-NLS-1$

	/**
	* "mode" tag attribute name
	*/
	public static final String ATTR_MODE = "mode"; //$NON-NLS-1$

	/**
	* "messageDetailInvalidDays" tag attribute name
	*/
	public static final String ATTR_MESSAGEDETAILINVALIDDAYS = "messageDetailInvalidDays"; //$NON-NLS-1$

	/**
	* "title" tag attribute name
	*/
	public static final String ATTR_TITLE = "title"; //$NON-NLS-1$

	/**
	* "captionText" tag attribute name
	*/
	public static final String ATTR_CAPTIONTEXT = "captionText"; //$NON-NLS-1$

	/**
	* "stepSize" tag attribute name
	*/
	public static final String ATTR_STEPSIZE = "stepSize"; //$NON-NLS-1$

	/**
	* "encoding" tag attribute name
	*/
	public static final String ATTR_ENCODING = "encoding"; //$NON-NLS-1$

	/**
	* "modal" tag attribute name
	*/
	public static final String ATTR_MODAL = "modal"; //$NON-NLS-1$

	/**
	* "end" tag attribute name
	*/
	public static final String ATTR_END = "end"; //$NON-NLS-1$

	/**
	* "onkeypress" tag attribute name
	*/
	public static final String ATTR_ONKEYPRESS = "onkeypress"; //$NON-NLS-1$

	/**
	* "onkeydown" tag attribute name
	*/
	public static final String ATTR_ONKEYDOWN = "onkeydown"; //$NON-NLS-1$

	/**
	* "innerHeight" tag attribute name
	*/
	public static final String ATTR_INNERHEIGHT = "innerHeight"; //$NON-NLS-1$

	/**
	* "messageType" tag attribute name
	*/
	public static final String ATTR_MESSAGETYPE = "messageType"; //$NON-NLS-1$

	/**
	* "onsubmit" tag attribute name
	*/
	public static final String ATTR_ONSUBMIT = "onsubmit"; //$NON-NLS-1$

	/**
	* "selectedStep" tag attribute name
	*/
	public static final String ATTR_SELECTEDSTEP = "selectedStep"; //$NON-NLS-1$

	/**
	* "rowBandingInterval" tag attribute name
	*/
	public static final String ATTR_ROWBANDINGINTERVAL = "rowBandingInterval"; //$NON-NLS-1$

	/**
	* "varStatus" tag attribute name
	*/
	public static final String ATTR_VARSTATUS = "varStatus"; //$NON-NLS-1$

	/**
	* "xoffset" tag attribute name
	*/
	public static final String ATTR_XOFFSET = "xoffset"; //$NON-NLS-1$

	/**
	* "currencySymbol" tag attribute name
	*/
	public static final String ATTR_CURRENCYSYMBOL = "currencySymbol"; //$NON-NLS-1$

	/**
	* "selectedRowKeys" tag attribute name
	*/
	public static final String ATTR_SELECTEDROWKEYS = "selectedRowKeys"; //$NON-NLS-1$

	/**
	* "method" tag attribute name
	*/
	public static final String ATTR_METHOD = "method"; //$NON-NLS-1$

	/**
	* "messageDetailExact" tag attribute name
	*/
	public static final String ATTR_MESSAGEDETAILEXACT = "messageDetailExact"; //$NON-NLS-1$

	/**
	* "messageDetailNotInRange" tag attribute name
	*/
	public static final String ATTR_MESSAGEDETAILNOTINRANGE = "messageDetailNotInRange"; //$NON-NLS-1$

	/**
	* "launchListener" tag attribute name
	*/
	public static final String ATTR_LAUNCHLISTENER = "launchListener"; //$NON-NLS-1$

	/**
	* "templateSource" tag attribute name
	*/
	public static final String ATTR_TEMPLATESOURCE = "templateSource"; //$NON-NLS-1$

	/**
	* "maxPrecision" tag attribute name
	*/
	public static final String ATTR_MAXPRECISION = "maxPrecision"; //$NON-NLS-1$

	/**
	* "targetFrame" tag attribute name
	*/
	public static final String ATTR_TARGETFRAME = "targetFrame"; //$NON-NLS-1$

	/**
	* "messageDetailConvertDate" tag attribute name
	*/
	public static final String ATTR_MESSAGEDETAILCONVERTDATE = "messageDetailConvertDate"; //$NON-NLS-1$

	/**
	* "focusListener" tag attribute name
	*/
	public static final String ATTR_FOCUSLISTENER = "focusListener"; //$NON-NLS-1$

	/**
	* "contentType" tag attribute name
	*/
	public static final String ATTR_CONTENTTYPE = "contentType"; //$NON-NLS-1$

	/**
	* "type" tag attribute name
	*/
	public static final String ATTR_TYPE = "type"; //$NON-NLS-1$

	/**
	* "initiallyExpanded" tag attribute name
	*/
	public static final String ATTR_INITIALLYEXPANDED = "initiallyExpanded"; //$NON-NLS-1$

	/**
	* "selectionListener" tag attribute name
	*/
	public static final String ATTR_SELECTIONLISTENER = "selectionListener"; //$NON-NLS-1$

	/**
	* "level" tag attribute name
	*/
	public static final String ATTR_LEVEL = "level"; //$NON-NLS-1$

	/**
	* "action" tag attribute name
	*/
	public static final String ATTR_ACTION = "action"; //$NON-NLS-1$

	/**
	* "value" tag attribute name
	*/
	public static final String ATTR_VALUE = "value"; //$NON-NLS-1$

	/**
	* "auxiliary2Size" tag attribute name
	*/
	public static final String ATTR_AUXILIARY2SIZE = "auxiliary2Size"; //$NON-NLS-1$

	/**
	* "required" tag attribute name
	*/
	public static final String ATTR_REQUIRED = "required"; //$NON-NLS-1$

	/**
	* "integerOnly" tag attribute name
	*/
	public static final String ATTR_INTEGERONLY = "integerOnly"; //$NON-NLS-1$

	/**
	* "wrap" tag attribute name
	*/
	public static final String ATTR_WRAP = "wrap"; //$NON-NLS-1$

	/**
	* "chooseId" tag attribute name
	*/
	public static final String ATTR_CHOOSEID = "chooseId"; //$NON-NLS-1$

	/**
	* "requiredMessageDetail" tag attribute name
	*/
	public static final String ATTR_REQUIREDMESSAGEDETAIL = "requiredMessageDetail"; //$NON-NLS-1$

	/**
	* "styleClass" tag attribute name
	*/
	public static final String ATTR_STYLECLASS = "styleClass"; //$NON-NLS-1$

	/**
	* "customColorData" tag attribute name
	*/
	public static final String ATTR_CUSTOMCOLORDATA = "customColorData"; //$NON-NLS-1$

	/**
	* "rows" tag attribute name
	*/
	public static final String ATTR_ROWS = "rows"; //$NON-NLS-1$

	/**
	* "groupingUsed" tag attribute name
	*/
	public static final String ATTR_GROUPINGUSED = "groupingUsed"; //$NON-NLS-1$

	/**
	* "playCount" tag attribute name
	*/
	public static final String ATTR_PLAYCOUNT = "playCount"; //$NON-NLS-1$

	/**
	* "default" tag attribute name
	*/
	public static final String ATTR_DEFAULT = "default"; //$NON-NLS-1$

	/**
	* "contentStyle" tag attribute name
	*/
	public static final String ATTR_CONTENTSTYLE = "contentStyle"; //$NON-NLS-1$

	/**
	* "maxColumns" tag attribute name
	*/
	public static final String ATTR_MAXCOLUMNS = "maxColumns"; //$NON-NLS-1$

	/**
	* "alignment" tag attribute name
	*/
	public static final String ATTR_ALIGNMENT = "alignment"; //$NON-NLS-1$

	/**
	* "leadingHeader" tag attribute name
	*/
	public static final String ATTR_LEADINGHEADER = "leadingHeader"; //$NON-NLS-1$

	/**
	* "locale" tag attribute name
	*/
	public static final String ATTR_LOCALE = "locale"; //$NON-NLS-1$

	/**
	* "messageDetailMaximum" tag attribute name
	*/
	public static final String ATTR_MESSAGEDETAILMAXIMUM = "messageDetailMaximum"; //$NON-NLS-1$

	/**
	* "windowHeight" tag attribute name
	*/
	public static final String ATTR_WINDOWHEIGHT = "windowHeight"; //$NON-NLS-1$

	/**
	* "timeZone" tag attribute name
	*/
	public static final String ATTR_TIMEZONE = "timeZone"; //$NON-NLS-1$

	/**
	* "from" tag attribute name
	*/
	public static final String ATTR_FROM = "from"; //$NON-NLS-1$

	/**
	* "messageDetailMinimum" tag attribute name
	*/
	public static final String ATTR_MESSAGEDETAILMINIMUM = "messageDetailMinimum"; //$NON-NLS-1$

	/**
	* "id" tag attribute name
	*/
	public static final String ATTR_ID = "id"; //$NON-NLS-1$

	/**
	* "returnListener" tag attribute name
	*/
	public static final String ATTR_RETURNLISTENER = "returnListener"; //$NON-NLS-1$

	/**
	* "controls" tag attribute name
	*/
	public static final String ATTR_CONTROLS = "controls"; //$NON-NLS-1$

	/**
	* "disclosedTransient" tag attribute name
	*/
	public static final String ATTR_DISCLOSEDTRANSIENT = "disclosedTransient"; //$NON-NLS-1$

	/**
	* "selected" tag attribute name
	*/
	public static final String ATTR_SELECTED = "selected"; //$NON-NLS-1$

	/**
	* "dateStyle" tag attribute name
	*/
	public static final String ATTR_DATESTYLE = "dateStyle"; //$NON-NLS-1$

	/**
	* "minIntegerDigits" tag attribute name
	*/
	public static final String ATTR_MININTEGERDIGITS = "minIntegerDigits"; //$NON-NLS-1$

	/**
	* "onmouseup" tag attribute name
	*/
	public static final String ATTR_ONMOUSEUP = "onmouseup"; //$NON-NLS-1$

	/**
	* "layout" tag attribute name
	*/
	public static final String ATTR_LAYOUT = "layout"; //$NON-NLS-1$

	/**
	* "expandAllEnabled" tag attribute name
	*/
	public static final String ATTR_EXPANDALLENABLED = "expandAllEnabled"; //$NON-NLS-1$

	/**
	* "onmousemove" tag attribute name
	*/
	public static final String ATTR_ONMOUSEMOVE = "onmousemove"; //$NON-NLS-1$

	/**
	* "halign" tag attribute name
	*/
	public static final String ATTR_HALIGN = "halign"; //$NON-NLS-1$

	/**
	* "labelWidth" tag attribute name
	*/
	public static final String ATTR_LABELWIDTH = "labelWidth"; //$NON-NLS-1$

	/**
	* "currencyCode" tag attribute name
	*/
	public static final String ATTR_CURRENCYCODE = "currencyCode"; //$NON-NLS-1$

	/**
	* "partialTriggers" tag attribute name
	*/
	public static final String ATTR_PARTIALTRIGGERS = "partialTriggers"; //$NON-NLS-1$

	/**
	* "separateRows" tag attribute name
	*/
	public static final String ATTR_SEPARATEROWS = "separateRows"; //$NON-NLS-1$

	/**
	* "hint" tag attribute name
	*/
	public static final String ATTR_HINT = "hint"; //$NON-NLS-1$

	/**
	* "onchange" tag attribute name
	*/
	public static final String ATTR_ONCHANGE = "onchange"; //$NON-NLS-1$

	/**
	* "begin" tag attribute name
	*/
	public static final String ATTR_BEGIN = "begin"; //$NON-NLS-1$

	/**
	* "longDescURL" tag attribute name
	*/
	public static final String ATTR_LONGDESCURL = "longDescURL"; //$NON-NLS-1$

	/**
	* "onload" tag attribute name
	*/
	public static final String ATTR_ONLOAD = "onload"; //$NON-NLS-1$

	/**
	* "sortListener" tag attribute name
	*/
	public static final String ATTR_SORTLISTENER = "sortListener"; //$NON-NLS-1$

	/**
	* "filename" tag attribute name
	*/
	public static final String ATTR_FILENAME = "filename"; //$NON-NLS-1$

	/**
	* "secret" tag attribute name
	*/
	public static final String ATTR_SECRET = "secret"; //$NON-NLS-1$

	/**
	* "group" tag attribute name
	*/
	public static final String ATTR_GROUP = "group"; //$NON-NLS-1$

	/**
	* "first" tag attribute name
	*/
	public static final String ATTR_FIRST = "first"; //$NON-NLS-1$

	/**
	* "step" tag attribute name
	*/
	public static final String ATTR_STEP = "step"; //$NON-NLS-1$

	/**
	* "headers" tag attribute name
	*/
	public static final String ATTR_HEADERS = "headers"; //$NON-NLS-1$

	/**
	* "generatesContent" tag attribute name
	*/
	public static final String ATTR_GENERATESCONTENT = "generatesContent"; //$NON-NLS-1$

	/**
	* "marginHeight" tag attribute name
	*/
	public static final String ATTR_MARGINHEIGHT = "marginHeight"; //$NON-NLS-1$

	/**
	* "cellPadding" tag attribute name
	*/
	public static final String ATTR_CELLPADDING = "cellPadding"; //$NON-NLS-1$

	/**
	* "wrappingDisabled" tag attribute name
	*/
	public static final String ATTR_WRAPPINGDISABLED = "wrappingDisabled"; //$NON-NLS-1$

	/**
	* "rowSpan" tag attribute name
	*/
	public static final String ATTR_ROWSPAN = "rowSpan"; //$NON-NLS-1$

	/**
	* "columnSpan" tag attribute name
	*/
	public static final String ATTR_COLUMNSPAN = "columnSpan"; //$NON-NLS-1$

	/**
	* "frameSpacing" tag attribute name
	*/
	public static final String ATTR_FRAMESPACING = "frameSpacing"; //$NON-NLS-1$

	/**
	* "cellSpacing" tag attribute name
	*/
	public static final String ATTR_CELLSPACING = "cellSpacing"; //$NON-NLS-1$

	/**
	* "header" tag attribute name
	*/
	public static final String ATTR_HEADER = "header"; //$NON-NLS-1$

	/**
	* "scrolling" tag attribute name
	*/
	public static final String ATTR_SCROLLING = "scrolling"; //$NON-NLS-1$

	/**
	* "borderWidth" tag attribute name
	*/
	public static final String ATTR_BORDERWIDTH = "borderWidth"; //$NON-NLS-1$

	/**
	* "frameBorderWidth" tag attribute name
	*/
	public static final String ATTR_FRAMEBORDERWIDTH = "frameBorderWidth"; //$NON-NLS-1$

	/**
	* "shortText" tag attribute name
	*/
	public static final String ATTR_SHORTTEXT = "shortText"; //$NON-NLS-1$

	/**
	* "marginWidth" tag attribute name
	*/
	public static final String ATTR_MARGINWIDTH = "marginWidth"; //$NON-NLS-1$

	/**
	* "firstClickPassed" tag attribute name
	*/
	public static final String ATTR_FIRSTCLICKPASSED = "firstClickPassed"; //$NON-NLS-1$

	/**
	 * Value for "style" attribute on elements used to indicate an empty source
	 * element.
	 */
	public static final String STYLE_EMPTYELEMENT = "color:silver;font-family:Arial,Helvetica,Geneva,sans-serif;font-size:8pt;"; //$NON-NLS-1$

}
