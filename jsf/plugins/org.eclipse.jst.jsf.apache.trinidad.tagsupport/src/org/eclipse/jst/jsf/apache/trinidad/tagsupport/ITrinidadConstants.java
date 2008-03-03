/**
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
}
