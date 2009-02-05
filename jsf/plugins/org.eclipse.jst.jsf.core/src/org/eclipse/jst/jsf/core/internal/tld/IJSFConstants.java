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
package org.eclipse.jst.jsf.core.internal.tld;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;

/**
 * Constants covering the JSF Core and JSF HTML tag and tag attribute names
 */
public interface IJSFConstants 
{

	/**
	 * actionListener tagname
	 */
	final static public String TAG_ACTIONLISTENER = "actionListener";

	/**
	 * attribute tagname
	 */
	final static public String TAG_ATTRIBUTE = "attribute";

	/**
	 * convertDateTime tagname
	 */
	final static public String TAG_CONVERTDATETIME = "convertDateTime";

	/**
	 * converter tagname
	 */
	final static public String TAG_CONVERTER = "converter";

	/**
	 * convertNumber tagname
	 */
	final static public String TAG_CONVERTNUMBER = "convertNumber";

	/**
	 * facet tagname
	 */
	final static public String TAG_FACET = "facet";

	/**
	 * loadBundle tagname
	 */
	final static public String TAG_LOADBUNDLE = "loadBundle";

	/**
	 * param tagname
	 */
	final static public String TAG_PARAM = "param";

    /**
     * phaseListener tagname
     */
	final static public String TAG_PHASELISTENER = "phaseListener";
	
	/**
	 * selectItem tagname
	 */
	final static public String TAG_SELECTITEM = "selectItem";

	/**
	 * selectItems tagname
	 */
	final static public String TAG_SELECTITEMS = "selectItems";

	/**
	 * setPropertyActionListener tagname
	 */
	final static public String TAG_SETPROPERTYACTIONLISTENER = "setPropertyActionListener";
	
	/**
	 * subview tagname
	 */
	final static public String TAG_SUBVIEW = "subview";

	/**
	 * validateDoubleRange tagname
	 */
	final static public String TAG_VALIDATEDOUBLERANGE = "validateDoubleRange";

	/**
	 * validateLength tagname
	 */
	final static public String TAG_VALIDATELENGTH = "validateLength";

	/**
	 * validateLongRange tagname
	 */
	final static public String TAG_VALIDATELONGRANGE = "validateLongRange";

	/**
	 * validator tagname
	 */
	final static public String TAG_VALIDATOR = "validator";

	/**
	 * valueChangeListener tagname
	 */
	final static public String TAG_VALUECHANGELISTENER = "valueChangeListener";

	/**
	 * verbatim tagname
	 */
	final static public String TAG_VERBATIM = "verbatim";

	/**
	 * view tagname
	 */
	final static public String TAG_VIEW = "view";

	/**
	 * column tagname
	 */
	final static public String TAG_COLUMN = "column";

	/**
	 * commandButton tagname
	 */
	final static public String TAG_COMMANDBUTTON = "commandButton";

	/**
	 * commandLink tagname
	 */
	final static public String TAG_COMMANDLINK = "commandLink";

	/**
	 * dataTable tagname
	 */
	final static public String TAG_DATATABLE = "dataTable";

	/**
	 * form tagname
	 */
	final static public String TAG_FORM = "form";

	/**
	 * graphicImage tagname
	 */
	final static public String TAG_GRAPHICIMAGE = "graphicImage";

	/**
	 * inputHidden tagname
	 */
	final static public String TAG_INPUTHIDDEN = "inputHidden";

	/**
	 * inputSecret tagname
	 */
	final static public String TAG_INPUTSECRET = "inputSecret";

	/**
	 * inputText tagname
	 */
	final static public String TAG_INPUTTEXT = "inputText";

	/**
	 * inputTextarea tagname
	 */
	final static public String TAG_INPUTTEXTAREA = "inputTextarea";

	/**
	 * message tagname
	 */
	final static public String TAG_MESSAGE = "message";

	/**
	 * messages tagname
	 */
	final static public String TAG_MESSAGES = "messages";

	/**
	 * outputFormat tagname
	 */
	final static public String TAG_OUTPUTFORMAT = "outputFormat";

	/**
	 * outputLabel tagname
	 */
	final static public String TAG_OUTPUTLABEL = "outputLabel";

	/**
	 * outputLink tagname
	 */
	final static public String TAG_OUTPUTLINK = "outputLink";

	/**
	 * outputText tagname
	 */
	final static public String TAG_OUTPUTTEXT = "outputText";

	/**
	 * panelGrid tagname
	 */
	final static public String TAG_PANELGRID = "panelGrid";

	/**
	 * panelGroup tagname
	 */
	final static public String TAG_PANELGROUP = "panelGroup";

	/**
	 * selectBooleanCheckbox tagname
	 */
	final static public String TAG_SELECTBOOLEANCHECKBOX = "selectBooleanCheckbox";

	/**
	 * selectManyCheckbox tagname
	 */
	final static public String TAG_SELECTMANYCHECKBOX = "selectManyCheckbox";

	/**
	 * selectManyListbox tagname
	 */
	final static public String TAG_SELECTMANYLISTBOX = "selectManyListbox";

	/**
	 * selectManyMenu tagname
	 */
	final static public String TAG_SELECTMANYMENU = "selectManyMenu";

	/**
	 * selectOneListbox tagname
	 */
	final static public String TAG_SELECTONELISTBOX = "selectOneListbox";

	/**
	 * selectOneMenu tagname
	 */
	final static public String TAG_SELECTONEMENU = "selectOneMenu";

	/**
	 * selectOneMenu tagname
	 */
	final static public String TAG_SELECTONERADIO = "selectOneRadio";

    // tag identifiers
    /**
     * TagIdentifier for TAG_VIEW
     */
    final static TagIdentifier TAG_IDENTIFIER_VIEW =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VIEW);

    /**
     * TagIdentifier for TAG_LOADBUNDLE
     */
    final static TagIdentifier TAG_IDENTIFIER_LOADBUNDLE =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_LOADBUNDLE);

    /**
     * TagIdentifier for TAG_FACET
     */
    final static TagIdentifier TAG_IDENTIFIER_FACET =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_FACET);
    
    /**
     * TagIdentifier for TAG_VERBATIM
     */
    final static TagIdentifier TAG_IDENTIFIER_VERBATIM =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VERBATIM);
    
    /**
     * TagIdentifier for TAG_DATATABLE
     */
    final static TagIdentifier TAG_IDENTIFIER_DATA_TABLE = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_DATATABLE);

    /**
     * TagIdentifier for TAG_PANELGRID
     */
    final static TagIdentifier TAG_IDENTIFIER_PANEL_GRID =
       TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_PANELGRID);

    /**
     * TagIdentifier for TAG_COLUMN
     */
    final static TagIdentifier TAG_IDENTIFIER_COLUMN =
       TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_COLUMN);

    /**
     * TagIdentifier for TAG_FORM
     */
    final static TagIdentifier TAG_IDENTIFIER_FORM =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_FORM);

    /**
     * TagIdentifier for TAG_INPUTTEXT
     */
    final static TagIdentifier TAG_IDENTIFIER_INPUTTEXT =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_INPUTTEXT);
    
    /**
     * TagIdentifier for TAG_INPUTSECRET
     */
    final static TagIdentifier TAG_IDENTIFIER_INPUTSECRET =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_INPUTSECRET);

    /**
     * TagIdentifier for TAG_INPUTTEXTAREA
     */
    final static TagIdentifier TAG_IDENTIFIER_INPUTTEXTAREA =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_INPUTTEXTAREA);

    /**
     * TagIdentifier for TAG_OUTPUTTEXT
     */
    final static TagIdentifier TAG_IDENTIFIER_OUTPUTTEXT =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_OUTPUTTEXT);
    
    /**
     * TagIdentifier for TAG_OUTPUTLABEL
     */
    final static TagIdentifier TAG_IDENTIFIER_OUTPUTLABEL =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_OUTPUTLABEL);
    
    /**
     * TagIdentifier for TAG_GRAPHICIMAGE
     */
    final static TagIdentifier TAG_IDENTIFIER_GRAPHICIMAGE =
    	TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_GRAPHICIMAGE);

    /**
     * TagIdentifier for TAG_COMMANDBUTTON
     */
    final static TagIdentifier TAG_IDENTIFIER_COMMANDBUTTON =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_COMMANDBUTTON);

    /**
     * TagIdentifier for TAG_COMMANDLINK
     */
    final static TagIdentifier TAG_IDENTIFIER_COMMANDLINK =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_COMMANDLINK);

    /**
     * TagIdentifier for TAG_INPUTHIDDEN
     */
    final static TagIdentifier TAG_IDENTIFIER_INPUTHIDDEN =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_INPUTHIDDEN);

    /**
     * TagIdentifier for TAG_MESSAGE
     */
    final static TagIdentifier TAG_IDENTIFIER_MESSAGE =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_MESSAGE);

    /**
     * TagIdentifier for TAG_MESSAGES
     */
    final static  TagIdentifier TAG_IDENTIFIER_MESSAGES =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_MESSAGES);

    /**
     * TagIdentifier for TAG_OUTPUTFORMAT
     */
    final static TagIdentifier TAG_IDENTIFIER_OUTPUTFORMAT =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_OUTPUTFORMAT);

    /**
     * TagIdentifier for TAG_OUTPUTLINK
     */
    final static TagIdentifier TAG_IDENTIFIER_OUTPUTLINK =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_OUTPUTLINK);

    /**
     * TagIdentifier for TAG_PANELGROUP
     */
    final static TagIdentifier TAG_IDENTIFIER_PANEL_GROUP =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_PANELGROUP);

    /**
     * TagIdentifier for TAG_SELECTBOOLEANCHECKBOX
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTBOOLEANCHECKBOX = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_SELECTBOOLEANCHECKBOX);

    /**
     * TagIdentifier for TAG_SELECTMANYCHECKBOX
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTMANYCHECKBOX = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_SELECTMANYCHECKBOX);

    /**
     * TagIdentifier for TAG_SELECTMANYLISTBOX
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTMANYLISTBOX = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_SELECTMANYLISTBOX);

    /**
     * TagIdentifier for TAG_SELECTMANYMENU
     */
    final static  TagIdentifier TAG_IDENTIFIER_SELECTMANYMENU =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_SELECTMANYMENU);

    /**
     * TagIdentifier for TAG_SELECTONELISTBOX
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTONELISTBOX =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_SELECTONELISTBOX);
    
    /**
     * TagIdentifier for TAG_SELECTONEMENU
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTONEMENU =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_SELECTONEMENU);

    /**
     * TagIdentifier for TAG_SELECTONERADIO
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTONERADIO =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, TAG_SELECTONERADIO);

    /**
     * TagIdentifier for TAG_ACTIONLISTENER
     */
    final static TagIdentifier TAG_IDENTIFIER_ACTIONLISTENER = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_ACTIONLISTENER);

    /**
     * TagIdentifier for TAG_ATTRIBUTE
     */
    final static TagIdentifier TAG_IDENTIFIER_ATTRIBUTE = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_ATTRIBUTE);

    /**
     * TagIdentifier for TAG_CONVERTDATETIME
     */
    final static TagIdentifier TAG_IDENTIFIER_CONVERTDATETIME = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_CONVERTDATETIME);

    /**
     * TagIdentifier for TAG_CONVERTNUMBER
     */
    final static TagIdentifier TAG_IDENTIFIER_CONVERTNUMBER = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_CONVERTNUMBER);

    /**
     * TagIdentifier for TAG_CONVERTER
     */
    final static TagIdentifier TAG_IDENTIFIER_CONVERTER = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_CONVERTER);

    /**
     * TagIdentifier for TAG_PARAM
     */
    final static TagIdentifier TAG_IDENTIFIER_PARAM =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_PARAM);

    /**
     * TagIdentifier for TAG_PHASELISTENER
     */
    final static TagIdentifier TAG_IDENTIFIER_PHASELISTENER =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_PHASELISTENER);

    /**
     * TagIdentifier for TAG_SELECTITEM
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTITEM = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_SELECTITEM);

    /**
     * TagIdentifier for TAG_SELECTITEMS
     */
    final static TagIdentifier TAG_IDENTIFIER_SELECTITEMS =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_SELECTITEMS);

    /**
     * TagIdentifier for TAG_SETPROPERTYACTIONLISTENER
     */
    final static TagIdentifier TAG_IDENTIFIER_SETPROPERTYACTIONLISTENER =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_SETPROPERTYACTIONLISTENER);

    /**
     * TagIdentifier for TAG_SUBVIEW
     */
    final static TagIdentifier TAG_IDENTIFIER_SUBVIEW =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_SUBVIEW);

    /**
     * TagIdentifier for TAG_VALIDATEDOUBLERANGE
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATEDOUBLERANGE =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VALIDATEDOUBLERANGE);

    /**
     * TagIdentifier for TAG_VALIDATELENGTH
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATELENGTH =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VALIDATELENGTH);

    /**
     * TagIdentifier for TAG_VALIDATELONGRANGE
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATELONGRANGE =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VALIDATELONGRANGE);

    /**
     * TagIdentifier for TAG_VALIDATOR
     */
    final static TagIdentifier TAG_IDENTIFIER_VALIDATOR =
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VALIDATOR);

    /**
     * TagIdentifier for TAG_VALUECHANGELISTENER
     */
    final static TagIdentifier TAG_IDENTIFIER_VALUECHANGELISTENER = 
        TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, TAG_VALUECHANGELISTENER);

    // attribute names
	/**
	 * accept tag attribute name
	 */
	final static public String ATTR_ACCEPT = "accept";

	/**
	 * acceptcharset tag attribute name
	 */
	final static public String ATTR_ACCEPTCHARSET = "acceptcharset";

	/**
	 * accesskey tag attribute name
	 */
	final static public String ATTR_ACCESSKEY = "accesskey";

	/**
	 * action tag attribute name
	 */
	final static public String ATTR_ACTION = "action";

	/**
	 * actionListener tag attribute name
	 */
	final static public String ATTR_ACTIONLISTENER = "actionListener";

	/**
	 * alt tag attribute name
	 */
	final static public String ATTR_ALT = "alt";

	/**
	 * basename tag attribute name
	 */
	final static public String ATTR_BASENAME = "basename";

	/**
	 * bgcolor tag attribute name
	 */
	final static public String ATTR_BGCOLOR = "bgcolor";

	/**
	 * binding tag attribute name
	 */
	final static public String ATTR_BINDING = "binding";

	/**
	 * border tag attribute name
	 */
	final static public String ATTR_BORDER = "border";

	/**
	 * cellpadding tag attribute name
	 */
	final static public String ATTR_CELLPADDING = "cellpadding";

	/**
	 * cellspacing tag attribute name
	 */
	final static public String ATTR_CELLSPACING = "cellspacing";

	/**
	 * charset tag attribute name
	 */
	final static public String ATTR_CHARSET = "charset";

	/**
	 * cols tag attribute name
	 */
	final static public String ATTR_COLS = "cols";

	/**
	 * columnClasses tag attribute name
	 */
	final static public String ATTR_COLUMNCLASSES = "columnClasses";

	/**
	 * columns tag attribute name
	 */
	final static public String ATTR_COLUMNS = "columns";

	/**
	 * converterId tag attribute name
	 */
	final static public String ATTR_CONVERTERID = "converterId";

	/**
	 * coords tag attribute name
	 */
	final static public String ATTR_COORDS = "coords";

	/**
	 * currencyCode tag attribute name
	 */
	final static public String ATTR_CURRENCYCODE = "currencyCode";

	/**
	 * currencySymbol tag attribute name
	 */
	final static public String ATTR_CURRENCYSYMBOL = "currencySymbol";

	/**
	 * dateStyle tag attribute name
	 */
	final static public String ATTR_DATESTYLE = "dateStyle";

	/**
	 * dir tag attribute name
	 */
	final static public String ATTR_DIR = "dir";

	/**
	 * disabled tag attribute name
	 */
	final static public String ATTR_DISABLED = "disabled";

	/**
	 * errorClass tag attribute name
	 */
	final static public String ATTR_ERRORCLASS = "errorClass";

	/**
	 * errorStyle tag attribute name
	 */
	final static public String ATTR_ERRORSTYLE = "errorStyle";

	/**
	 * escape tag attribute name
	 */
	final static public String ATTR_ESCAPE = "escape";

	/**
	 * fatalClass tag attribute name
	 */
	final static public String ATTR_FATALCLASS = "fatalClass";

	/**
	 * fatalStyle tag attribute name
	 */
	final static public String ATTR_FATALSTYLE = "fatalStyle";

	/**
	 * first tag attribute name
	 */
	final static public String ATTR_FIRST = "first";

	/**
	 * footerClass tag attribute name
	 */
	final static public String ATTR_FOOTERCLASS = "footerClass";

	/**
	 * for tag attribute name
	 */
	final static public String ATTR_FOR = "for";

	/**
	 * frame tag attribute name
	 */
	final static public String ATTR_FRAME = "frame";

	/**
	 * globalOnly tag attribute name
	 */
	final static public String ATTR_GLOBEONLY = "globalOnly";

	/**
	 * headerClass tag attribute name
	 */
	final static public String ATTR_HEADERCLASS = "headerClass";

	/**
	 * hreflang tag attribute name
	 */
	final static public String ATTR_HREFLANG = "hreflang";

	/**
	 * id tag attribute name
	 */
	final static public String ATTR_ID = "id";

	/**
	 * image tag attribute name
	 */
	final static public String ATTR_IMAGE = "image";

	/**
	 * immediate tag attribute name
	 */
	final static public String ATTR_IMMEDIATE = "immediate";

	/**
	 * infoClass tag attribute name
	 */
	final static public String ATTR_INFOCLASS = "infoClass";

	/**
	 * infoStyle tag attribute name
	 */
	final static public String ATTR_INFOSTYLE = "infoStyle";

	/**
	 * itemDescription tag attribute name
	 */
	final static public String ATTR_ITEMDESCRIPTION = "itemDescription";

	/**
	 * itemDisabled tag attribute name
	 */
	final static public String ATTR_ITEMDISABLED = "itemDisabled";

	/**
	 * itemLabel tag attribute name
	 */
	final static public String ATTR_ITEMLABEL = "itemLabel";

	/**
	 * itemValue tag attribute name
	 */
	final static public String ATTR_ITEMVALUE = "itemValue";

	/**
	 * lang tag attribute name
	 */
	final static public String ATTR_LANG = "lang";

	/**
	 * layout tag attribute name
	 */
	final static public String ATTR_LAYOUT = "layout";

	/**
	 * locale tag attribute name
	 */
	final static public String ATTR_LOCALE = "locale";

	/**
	 * maximum tag attribute name
	 */
	final static public String ATTR_MAXIMUM = "maximum";

	/**
	 * minimum tag attribute name
	 */
	final static public String ATTR_MINIMUM = "minimum";

	/**
	 * name tag attribute name
	 */
	final static public String ATTR_NAME = "name";

	/**
	 * onblur tag attribute name
	 */
	final static public String ATTR_ONBLUR = "onblur";

	/**
	 * onchange tag attribute name
	 */
	final static public String ATTR_ONCHANGE = "onchange";

	/**
	 * onclick tag attribute name
	 */
	final static public String ATTR_ONCLICK = "onclick";

	/**
	 * ondblclick tag attribute name
	 */
	final static public String ATTR_ONDBLCLICK = "ondblclick";

	/**
	 * onfocus tag attribute name
	 */
	final static public String ATTR_ONFOCUS = "onfocus";

	/**
	 * onkeydown tag attribute name
	 */
	final static public String ATTR_ONKEYDOWN = "onkeydown";

	/**
	 * onkeypress tag attribute name
	 */
	final static public String ATTR_ONKEYPRESS = "onkeypress";

	/**
	 * onkeyup tag attribute name
	 */
	final static public String ATTR_ONKEYUP = "onkeyup";

	/**
	 * onmousedown tag attribute name
	 */
	final static public String ATTR_ONMOUSEDOWN = "onmousedown";

	/**
	 * onmousemove tag attribute name
	 */
	final static public String ATTR_ONMOUSEMOVE = "onmousemove";

	/**
	 * onmouseout tag attribute name
	 */
	final static public String ATTR_ONMOUSEOUT = "onmouseout";

	/**
	 * onmouseover tag attribute name
	 */
	final static public String ATTR_ONMOUSEOVER = "onmouseover";

	/**
	 * onmouseup tag attribute name
	 */
	final static public String ATTR_ONMOUSEUP = "onmouseup";

	/**
	 * onselect tag attribute name
	 */
	final static public String ATTR_ONSELECT = "onselect";

	/**
	 * pattern tag attribute name
	 */
	final static public String ATTR_PATTERN = "pattern";

	/**
	 * readonly tag attribute name
	 */
	final static public String ATTR_READONLY = "readonly";

	/**
	 * rel tag attribute name
	 */
	final static public String ATTR_REL = "rel";

	/**
	 * rendered tag attribute name
	 */
	final static public String ATTR_RENDERED = "rendered";

	/**
	 * rev tag attribute name
	 */
	final static public String ATTR_REV = "rev";

	/**
	 * rowClasses tag attribute name
	 */
	final static public String ATTR_ROWCLASSES = "rowClasses";

	/**
	 * rows tag attribute name
	 */
	final static public String ATTR_ROWS = "rows";

	/**
	 * rules tag attribute name
	 */
	final static public String ATTR_RULES = "rules";

	/**
	 * shape tag attribute name
	 */
	final static public String ATTR_SHAPE = "shape";

	/**
	 *  showDetail tag attribute name
	 */
	final static public String ATTR_SHOWDETAIL = "showDetail";

	/**
	 * showSummary tag attribute name
	 */
	final static public String ATTR_SHOWSUMMARY = "showSummary";

	/**
	 * size tag attribute name
	 */
	final static public String ATTR_SIZE = "size";

	/**
	 * style tag attribute name
	 */
	final static public String ATTR_STYLE = "style";

	/**
	 * styleClass tag attribute name
	 */
	final static public String ATTR_STYLECLASS = "styleClass";

	/**
	 * summary tag attribute name
	 */
	final static public String ATTR_SUMMARY = "summary";

	/**
	 * tabindex tag attribute name
	 */
	final static public String ATTR_TABINDEX = "tabindex";

	/**
	 * target tag attribute name
	 */
	final static public String ATTR_TARGET = "target";

	/**
	 * timeStyle tag attribute name
	 */
	final static public String ATTR_TIMESTYLE = "timeStyle";

	/**
	 * title tag attribute name
	 */
	final static public String ATTR_TITLE = "title";

	/**
	 * tooltip tag attribute name
	 */
	final static public String ATTR_TOOLTIP = "tooltip";

	/**
	 * type tag attribute name
	 */
	final static public String ATTR_TYPE = "type";

	/**
	 * url tag attribute name
	 */
	final static public String ATTR_URL = "url";

	/**
	 * validatorId tag attribute name
	 */
	final static public String ATTR_VALIDATORID = "validatorId";

	/**
	 * value tag attribute name
	 */
	final static public String ATTR_VALUE = "value";

	/**
	 * var tag attribute name
	 */
	final static public String ATTR_VAR = "var";

	/**
	 * warnClass tag attribute name
	 */
	final static public String ATTR_WARNCLASS = "warnClass";

	/**
	 * warnStyle tag attribute name
	 */
	final static public String ATTR_WARNSTYLE = "warnStyle";

	/**
	 * width tag attribute name
	 */
	final static public String ATTR_WIDTH = "width";

}
