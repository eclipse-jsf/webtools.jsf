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
package org.eclipse.jst.pagedesigner;

import org.eclipse.jst.jsf.common.internal.provisional.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;

/**
 * @author mengbo
 */
public interface IJSFConstants {
    
    
	// tag name
	final static public String TAG_ACTIONLISTENER = "actionListener";

	final static public String TAG_ATTRIBUTE = "attribute";

	final static public String TAG_CONVERTDATETIME = "convertDateTime";

	final static public String TAG_CONVERTER = "converter";

	final static public String TAG_CONVERTNUMBER = "convertNumber";

	final static public String TAG_FACET = "facet";

	final static public String TAG_LOADBUNDLE = "loadBundle";

	final static public String TAG_PARAM = "param";

	final static public String TAG_SELECTITEM = "selectItem";

	final static public String TAG_SELECTITEMS = "selectItems";

	final static public String TAG_SUBVIEW = "subview";

	final static public String TAG_VALIDATEDOUBLERANGE = "validateDoubleRange";

	final static public String TAG_VALIDATELENGTH = "validateLength";

	final static public String TAG_VALIDATELONGRANGE = "validateLongRange";

	final static public String TAG_VALIDATOR = "validator";

	final static public String TAG_VALUECHANGELISTENER = "valueChangeListener";

	final static public String TAG_VERBATIM = "verbatim";

	final static public String TAG_VIEW = "view";

	final static public String TAG_COLUMN = "column";

	final static public String TAG_COMMANDBUTTON = "commandButton";

	final static public String TAG_COMMANDLINK = "commandLink";

	final static public String TAG_DATATABLE = "dataTable";

	final static public String TAG_FORM = "form";

	final static public String TAG_GRAPHICIMAGE = "graphicImage";

	final static public String TAG_INPUTHIDDEN = "inputHidden";

	final static public String TAG_INPUTSECRET = "inputSecret";

	final static public String TAG_INPUTTEXT = "inputText";

	final static public String TAG_INPUTTEXTAREA = "inputTextarea";

	final static public String TAG_MESSAGE = "message";

	final static public String TAG_MESSAGES = "messages";

	final static public String TAG_OUTPUTFORMAT = "outputFormat";

	final static public String TAG_OUTPUTLABEL = "outputLabel";

	final static public String TAG_OUTPUTLINK = "outputLink";

	final static public String TAG_OUTPUTTEXT = "outputText";

	final static public String TAG_PANELGRID = "panelGrid";

	final static public String TAG_PANELGROUP = "panelGroup";

	final static public String TAG_SELECTBOOLEANCHECKBOX = "selectBooleanCheckbox";

	final static public String TAG_SELECTMANYCHECKBOX = "selectManyCheckbox";

	final static public String TAG_SELECTMANYLISTBOX = "selectManyListbox";

	final static public String TAG_SELECTMANYMENU = "selectManyMenu";

	final static public String TAG_SELECTONELISTBOX = "selectOneListbox";

	final static public String TAG_SELECTONEMENU = "selectOneMenu";

	final static public String TAG_SELECTONERADIO = "selectOneRadio";

    // tag identifiers
    final static TagIdentifier TAG_IDENTIFIER_VIEW =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VIEW);

    final static TagIdentifier TAG_IDENTIFIER_LOADBUNDLE =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_LOADBUNDLE);

    final static TagIdentifier TAG_IDENTIFIER_FACET =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_FACET);
    
    final static TagIdentifier TAG_IDENTIFIER_VERBATIM =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VERBATIM);
    
    final static TagIdentifier TAG_IDENTIFIER_DATA_TABLE = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_DATATABLE);

    final static TagIdentifier TAG_IDENTIFIER_PANEL_GRID =
       TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_PANELGRID);

    final static TagIdentifier TAG_IDENTIFIER_COLUMN =
       TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_COLUMN);

    final static TagIdentifier TAG_IDENTIFIER_FORM =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_FORM);

    final static TagIdentifier TAG_IDENTIFIER_INPUTTEXT =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_INPUTTEXT);
    
    final static TagIdentifier TAG_IDENTIFIER_INPUTSECRET =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_INPUTSECRET);

    final static TagIdentifier TAG_IDENTIFIER_INPUTTEXTAREA =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_INPUTTEXTAREA);

    final static TagIdentifier TAG_IDENTIFIER_OUTPUTTEXT =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_OUTPUTTEXT);
    
    final static TagIdentifier TAG_IDENTIFIER_OUTPUTLABEL =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_OUTPUTLABEL);
    
    final static TagIdentifier TAG_IDENTIFIER_GRAPHICIMAGE =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_GRAPHICIMAGE);

	// attribute name
	final static public String ATTR_ACCEPT = "accept";

	final static public String ATTR_ACCEPTCHARSET = "acceptcharset";

	final static public String ATTR_ACCESSKEY = "accesskey";

	final static public String ATTR_ACTION = "action";

	final static public String ATTR_ACTIONLISTENER = "actionListener";

	final static public String ATTR_ALT = "alt";

	final static public String ATTR_BASENAME = "basename";

	final static public String ATTR_BGCOLOR = "bgcolor";

	final static public String ATTR_BINDING = "binding";

	final static public String ATTR_BORDER = "border";

	final static public String ATTR_CELLPADDING = "cellpadding";

	final static public String ATTR_CELLSPACING = "cellspacing";

	final static public String ATTR_CHARSET = "charset";

	final static public String ATTR_COLS = "cols";

	final static public String ATTR_COLUMNCLASSES = "columnClasses";

	final static public String ATTR_COLUMNS = "columns";

	final static public String ATTR_CONVERTERID = "converterId";

	final static public String ATTR_COORDS = "coords";

	final static public String ATTR_CURRENCYCODE = "currencyCode";

	final static public String ATTR_CURRENCYSYMBOL = "currencySymbol";

	final static public String ATTR_DATESTYLE = "dateStyle";

	final static public String ATTR_DIR = "dir";

	final static public String ATTR_DISABLED = "disabled";

	final static public String ATTR_ERRORCLASS = "errorClass";

	final static public String ATTR_ERRORSTYLE = "errorStyle";

	final static public String ATTR_ESCAPE = "escape";

	final static public String ATTR_FATALCLASS = "fatalClass";

	final static public String ATTR_FATALSTYLE = "fatalStyle";

	final static public String ATTR_FIRST = "first";

	final static public String ATTR_FOOTERCLASS = "footerClass";

	final static public String ATTR_FOR = "for";

	final static public String ATTR_FRAME = "frame";

	final static public String ATTR_GLOBEONLY = "globalOnly";

	final static public String ATTR_HEADERCLASS = "headerClass";

	final static public String ATTR_HREFLANG = "hreflang";

	final static public String ATTR_ID = "id";

	final static public String ATTR_IMAGE = "image";

	final static public String ATTR_IMMEDIATE = "immediate";

	final static public String ATTR_INFOCLASS = "infoClass";

	final static public String ATTR_INFOSTYLE = "infoStyle";

	final static public String ATTR_ITEMDESCRIPTION = "itemDescription";

	final static public String ATTR_ITEMDISABLED = "itemDisabled";

	final static public String ATTR_ITEMLABEL = "itemLabel";

	final static public String ATTR_ITEMVALUE = "itemValue";

	final static public String ATTR_LANG = "lang";

	final static public String ATTR_LAYOUT = "layout";

	final static public String ATTR_LOCALE = "locale";

	final static public String ATTR_MAXIMUM = "maximum";

	final static public String ATTR_MINIMUM = "minimum";

	final static public String ATTR_NAME = "name";

	final static public String ATTR_ONBLUR = "onblur";

	final static public String ATTR_ONCHANGE = "onchange";

	final static public String ATTR_ONCLICK = "onclick";

	final static public String ATTR_ONDBLCLICK = "ondblclick";

	final static public String ATTR_ONFOCUS = "onfocus";

	final static public String ATTR_ONKEYDOWN = "onkeydown";

	final static public String ATTR_ONKEYPRESS = "onkeypress";

	final static public String ATTR_ONKEYUP = "onkeyup";

	final static public String ATTR_ONMOUSEDOWN = "onmousedown";

	final static public String ATTR_ONMOUSEMOVE = "onmousemove";

	final static public String ATTR_ONMOUSEOUT = "onmouseout";

	final static public String ATTR_ONMOUSEOVER = "onmouseover";

	final static public String ATTR_ONMOUSEUP = "onmouseup";

	final static public String ATTR_ONSELECT = "onselect";

	final static public String ATTR_PATTERN = "pattern";

	final static public String ATTR_READONLY = "readonly";

	final static public String ATTR_REL = "rel";

	final static public String ATTR_RENDERED = "rendered";

	final static public String ATTR_REV = "rev";

	final static public String ATTR_ROWCLASSES = "rowClasses";

	final static public String ATTR_ROWS = "rows";

	final static public String ATTR_RULES = "rules";

	final static public String ATTR_SHAPE = "shape";

	final static public String ATTR_SHOWDETAIL = "showDetail";

	final static public String ATTR_SHOWSUMMARY = "showSummary";

	final static public String ATTR_SIZE = "size";

	final static public String ATTR_STYLE = "style";

	final static public String ATTR_STYLECLASS = "styleClass";

	final static public String ATTR_SUMMARY = "summary";

	final static public String ATTR_TABINDEX = "tabindex";

	final static public String ATTR_TARGET = "target";

	final static public String ATTR_TIMESTYLE = "timeStyle";

	final static public String ATTR_TITLE = "title";

	final static public String ATTR_TOOLTIP = "tooltip";

	final static public String ATTR_TYPE = "type";

	final static public String ATTR_URL = "url";

	final static public String ATTR_VALIDATORID = "validatorId";

	final static public String ATTR_VALUE = "value";

	final static public String ATTR_VAR = "var";

	final static public String ATTR_WARNCLASS = "warnClass";

	final static public String ATTR_WARNSTYLE = "warnStyle";

	final static public String ATTR_WIDTH = "width";
}
