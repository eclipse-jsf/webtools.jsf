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

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.wst.xml.core.internal.provisional.contentmodel.CMDocType;

/**
 * @author mengbo
 */
public class IHTMLConstants {
	/**
	 * anchor tag name
	 */
	public static final String TAG_A = "a";

	/**
	 * abbreviated form (e.g.,WWW, HTTP, etc.) tag name
	 */
	public static final String TAG_ABBR = "abbr"; // 

	/**
	 * acronym tag name
	 */
	public static final String TAG_ACRONYM = "acronym";

	/**
	 * address tag name
	 */
	public static final String TAG_ADDRESS = "address";

	/**
	 * Java applet tag name
	 */
	public static final String TAG_APPLET = "applet";

	/**
	 * client-side image map tag name
	 */
	public static final String TAG_AREA = "area";

	// area

	/**
	 * bold text style tag name
	 */
	public static final String TAG_B = "b";

	/**
	 * document base URI tag name
	 */
	public static final String TAG_BASE = "base";

	/**
	 * base font size tag name
	 */
	public static final String TAG_BASEFONT = "basefont";

	/**
	 * I18N BiDi over-ride tag name
	 */
	public static final String TAG_BDO = "bdo"; 

	/**
	 * large text style tag name
	 */
	public static final String TAG_BIG = "big";

	/**
	 * blockquote tag name
	 */
	public static final String TAG_BLOCKQUOTE = "blockquote";

	// quotation

	/**
	 * document body tag name
	 */
	public static final String TAG_BODY = "body";

	/**
	 * forced line break tag name
	 */
	public static final String TAG_BR = "br";

	/**
	 * push button tag name
	 */
	public static final String TAG_BUTTON = "button";

	/**
	 * table caption tag name
	 */
	public static final String TAG_CAPTION = "caption";

	/**
	 * shorthand for DIV align=center tag name
	 */
	public static final String TAG_CENTER = "center";

	/**
	 * citation tag name
	 */
	public static final String TAG_CITE = "cite";

	/**
	 * computer code fragment tag name
	 */
	public static final String TAG_CODE = "code";

	/**
	 * table column tag name
	 */
	public static final String TAG_COL = "col";

	/**
	 * table column group tag name
	 */
	public static final String TAG_COLGROUP = "colgroup";

	/**
	 * definition description tag name
	 */
	public static final String TAG_DD = "dd";

	/**
	 * deleted text tag name
	 */
	public static final String TAG_DEL = "del";

	/**
	 * instance definition tag name
	 */
	public static final String TAG_DFN = "dfn"; 

	/**
	 * directory list tag name
	 */
	public static final String TAG_DIR = "dir";

	/**
	 * generic language/style container (div) tag name
	 */
	public static final String TAG_DIV = "div";

	/**
	 * definition list tag name
	 */
	public static final String TAG_DL = "dl";

	/**
	 * definition term tag name
	 */
	public static final String TAG_DT = "dt";

	/**
	 * emphasis tag name
	 */
	public static final String TAG_EM = "em";

	/**
	 * fieldset tag name
	 */
	public static final String TAG_FIELDSET = "fieldset";

	/**
	 * local change to font (font) tag name
	 */
	public static final String TAG_FONT = "font"; 

	/**
	 * form tag name
	 */
	public static final String TAG_FORM = "form";

	/**
	 * frame tag name
	 */
	public static final String TAG_FRAME = "frame";

	/**
	 * frameset tag name
	 */
	public static final String TAG_FRAMESET = "frameset"; 
	
	/**
	 * h1 tag name
	 */
	public static final String TAG_H1 = "h1"; 

	/**
	 * h2 tag name
	 */
	public static final String TAG_H2 = "h2"; 

	/**
	 * h3 tag name
	 */
	public static final String TAG_H3 = "h3"; 

	/**
	 * h4 tag name
	 */
	public static final String TAG_H4 = "h4"; 
	
	/**
	 * h5 tag name
	 */
	public static final String TAG_H5 = "h5";

	/**
	 * h6 tag name
	 */
	public static final String TAG_H6 = "h6";

	/**
	 * head tag name
	 */
	public static final String TAG_HEAD = "head";

	/**
	 * horizontal rule tag name
	 */
	public static final String TAG_HR = "hr"; 

	/**
	 * document root element (html) tag name
	 */
	public static final String TAG_HTML = "html";

	/**
	 * italic text style tag name
	 */
	public static final String TAG_I = "i";

	/**
	 * inline subwindow (iframe) tag name
	 */
	public static final String TAG_IFRAME = "iframe";

	/**
	 * Embedded image tag name
	 */
	public static final String TAG_IMG = "img"; 

	/**
	 * input tag name
	 */
	public static final String TAG_INPUT = "input";

	/**
	 * inserted text tag name
	 */
	public static final String TAG_INS = "ins";

	/**
	 * isindex tag name
	 */
	public static final String TAG_ISINDEX = "isindex";

	/**
	 * text to be entered by the user tag name
	 */
	public static final String TAG_KBD = "kbd";

	/**
	 * form field label text tag name
	 */
	public static final String TAG_LABEL = "label"; 

	/**
	 * fieldset legend tag name
	 */
	public static final String TAG_LEGEND = "legend";

	/**
	 * list item tag name
	 */
	public static final String TAG_LI = "li"; 

	/**
	 * media-independent link tag name
	 */
	public static final String TAG_LINK = "link";

	/**
	 * client-side image map tag name
	 */
	public static final String TAG_MAP = "map"; 

	/**
	 * menu list tag name
	 */
	public static final String TAG_MENU = "menu";

	/**
	 * generic metainformation tag name
	 */
	public static final String TAG_META = "meta";

	/**
	 * noembed tag name
	 */
	public static final String TAG_NOEMBED = "noembed";

	/**
	 * container for non-frame-based
     * rendering (noframes) tag name
	 */
	public static final String TAG_NOFRAMES = "noframes";

	// 

	/**
	 * container for non-script-based
     * rendering (noscript) tag name
	 */
	public static final String TAG_NOSCRIPT = "noscript";

	// 

	/**
	 * generic embedded (object) tag name
	 */
	public static final String TAG_OBJECT = "object";

	/**
	 * ordered list tag name
	 */
	public static final String TAG_OL = "ol";

	/**
	 * option group tag name
	 */
	public static final String TAG_OPTGROUP = "optgroup"; 

	/**
	 * selectable choice tag name
	 */
	public static final String TAG_OPTION = "option";

	/**
	 * paragraph tag name
	 */
	public static final String TAG_P = "p";

	/**
	 * named property value tag name
	 */
	public static final String TAG_PARAM = "param";

	/**
	 * preformatted text tag name
	 */
	public static final String TAG_PRE = "pre";

	/**
	 * short inline quotation tag name
	 */
	public static final String TAG_Q = "q";

	/**
	 * strike-through text style tag name
	 */
	public static final String TAG_S = "s"; 

	/**
	 * sample program output tag name
	 */
	public static final String TAG_SAMP = "samp";

	// scripts, etc.

	/**
	 * script statements tag name
	 */
	public static final String TAG_SCRIPT = "script"; 

	/**
	 * option selector tag name
	 */
	public static final String TAG_SELECT = "select"; 

	/**
	 * small text style tag name
	 */
	public static final String TAG_SMALL = "small"; 

	/**
	 * generic language/style tag name
	 */
	public static final String TAG_SPAN = "span";

	// container

	/**
	 * strike-through text tag name
	 */
	public static final String TAG_STRIKE = "strike";

	/**
	 * strong emphasis tag name
	 */
	public static final String TAG_STRONG = "strong";

	/**
	 *  style info tag name
	 */
	public static final String TAG_STYLE = "style";

	/**
	 * subscript tag name
	 */
	public static final String TAG_SUB = "sub";

	/**
	 * superscript tag name
	 */
	public static final String TAG_SUP = "sup";

	/**
	 * table tag name
	 */
	public static final String TAG_TABLE = "table";

	/**
	 *  table body tag name
	 */
	public static final String TAG_TBODY = "tbody";

	/**
	 * table data cell tag name
	 */
	public static final String TAG_TD = "td";

	/**
	 * multi-line text
	 */
	public static final String TAG_TEXTAREA = "textarea";

	// field

	/**
	 * table footer tag name
	 */
	public static final String TAG_TFOOT = "tfoot";

	/**
	 * table header cell tag name
	 */
	public static final String TAG_TH = "th";

	/**
	 * table header tag name
	 */
	public static final String TAG_THEAD = "thead";

	/**
	 * document title tag name
	 */
	public static final String TAG_TITLE = "title";

	/**
	 * table row tag name
	 */
	public static final String TAG_TR = "tr";

	/**
	 * teletype or monospaced text tag name
	 */
	public static final String TAG_TT = "tt"; 

	// style

	/**
	 * underlined text style tag name
	 */
	public static final String TAG_U = "u"; 

	/**
	 * unordered list tag name
	 */
	public static final String TAG_UL = "ul";

	/**
	 * instance of a variable tag name
	 */
	public static final String TAG_VAR = "var";

	/**
	 * Tag identifier for an HTML form tag
	 */
	public static final TagIdentifier TAG_IDENTIFIER_HTML_FORM =
	    TagIdentifierFactory.createJSPTagWrapper(CMDocType.HTML_DOC_TYPE, TAG_FORM);
	
    // program argument

	/**
	 * abbr attribute
	 */
	public static final String ATTR_ABBR = "abbr";

	/**
	 * accept-charset attribute
	 */
	public static final String ATTR_ACCEPTCHARSET = "accept-charset";

	/**
	 * accept attribute
	 */
	public static final String ATTR_ACCEPT = "accept";

	/**
	 * access key attribute
	 */
	public static final String ATTR_ACCESSKEY = "accesskey";

	/**
	 * action attribute
	 */
	public static final String ATTR_ACTION = "action";

	/**
	 * align attribute
	 */
	public static final String ATTR_ALIGN = "align";

	/**
	 * alink attribute
	 */
	public static final String ATTR_ALINK = "alink";

	/**
	 * alt attribute
	 */
	public static final String ATTR_ALT = "alt";

	/**
	 * archive attribute
	 */
	public static final String ATTR_ARCHIVE = "archive";

	/**
	 * axis attribute
	 */
	public static final String ATTR_AXIS = "axis";

	/**
	 * background attribute
	 */
	public static final String ATTR_BACKGROUND = "background";

	/**
	 * bgcolor attribute
	 */
	public static final String ATTR_BGCOLOR = "bgcolor";

	/**
	 * border attribute
	 */
	public static final String ATTR_BORDER = "border";

	/**
	 * cellpadding attribute
	 */
	public static final String ATTR_CELLPADDING = "cellpadding";

	/**
	 * cellspacing attribute
	 */
	public static final String ATTR_CELLSPACING = "cellspacing";

	/**
	 * char attribute
	 */
	public static final String ATTR_CHAR = "char";

	/**
	 * charoff attribute
	 */
	public static final String ATTR_CHAROFF = "charoff";

	/**
	 * charset attribute
	 */
	public static final String ATTR_CHARSET = "charset";

	/**
	 * checked attribute
	 */
	public static final String ATTR_CHECKED = "checked";

	/**
	 * cite attribute
	 */
	public static final String ATTR_CITE = "cite";

	/**
	 * class attribute
	 */
	public static final String ATTR_CLASS = "class";

	/**
	 * classid attribute
	 */
	public static final String ATTR_CLASSID = "classid";

	/**
	 * clear attribute
	 */
	public static final String ATTR_CLEAR = "clear";

	/**
	 * code attribute
	 */
	public static final String ATTR_CODE = "code";

	/**
	 * codebase attribute
	 */
	public static final String ATTR_CODEBASE = "codebase";

	/**
	 * code type attribute
	 */
	public static final String ATTR_CODETYPE = "codetype";

	/**
	 * color attribute
	 */
	public static final String ATTR_COLOR = "color";

	/**
	 * cols attribute
	 */
	public static final String ATTR_COLS = "cols";

	/**
	 * colspan attribute
	 */
	public static final String ATTR_COLSPAN = "colspan";

	/**
	 * compact attribute
	 */
	public static final String ATTR_COMPACT = "compact";

	/**
	 * content attribute
	 */
	public static final String ATTR_CONTENT = "content";

	/**
	 * coords attribute
	 */
	public static final String ATTR_COORDS = "coords";

	/**
	 * data attribute
	 */
	public static final String ATTR_DATA = "data";

	/**
	 * datetime attribute
	 */
	public static final String ATTR_DATETIME = "datetime";

	/**
	 * declare attribute
	 */
	public static final String ATTR_DECLARE = "declare";

	/**
	 * defer attribute
	 */
	public static final String ATTR_DEFER = "defer";

	/**
	 * dir attribute
	 */
	public static final String ATTR_DIR = "dir";

	/**
	 * disabled attribute
	 */
	public static final String ATTR_DISABLED = "disabled";

	/**
	 * enctype attribute
	 */
	public static final String ATTR_ENCTYPE = "enctype";

	/**
	 * face attribute
	 */
	public static final String ATTR_FACE = "face";

	/**
	 * for  attribute
	 */
	public static final String ATTR_FOR = "for";

	/**
	 * frame attribute
	 */
	public static final String ATTR_FRAME = "frame";

	/**
	 * frameborder attribute
	 */
	public static final String ATTR_FRAMEBORDER = "frameborder";

	/**
	 * headers attribute
	 */
	public static final String ATTR_HEADERS = "headers";

	/**
	 * height attribute
	 */
	public static final String ATTR_HEIGHT = "height";

	/**
	 * href  attribute
	 */
	public static final String ATTR_HREF = "href";

	/**
	 * hreflang attribute
	 */
	public static final String ATTR_HREFLANG = "hreflang";

	/**
	 * hspace attribute
	 */
	public static final String ATTR_HSPACE = "hspace";

	/**
	 * http-equiv attribute
	 */
	public static final String ATTR_HTTPEQUIV = "http-equiv";

	/**
	 * id attribute
	 */
	public static final String ATTR_ID = "id";

	/**
	 * ismap attribute
	 */
	public static final String ATTR_ISMAP = "ismap";

	/**
	 * label attribute
	 */
	public static final String ATTR_LABEL = "label";

	/**
	 * lang attribute
	 */
	public static final String ATTR_LANG = "lang";

	/**
	 * language attribute
	 */
	public static final String ATTR_LANGUAGE = "language";

	/**
	 * link attribute
	 */
	public static final String ATTR_LINK = "link";

	/**
	 * longdesc attribute
	 */
	public static final String ATTR_LONGDESC = "longdesc";

	/**
	 *  marginheight attribute
	 */
	public static final String ATTR_MARGINHEIGHT = "marginheight";

	/**
	 * margin width attribute
	 */
	public static final String ATTR_MARGINWIDTH = "marginwidth";

	/**
	 * maxlength attribute
	 */
	public static final String ATTR_MAXLENGTH = "maxlength";

	/**
	 * media attribute
	 */
	public static final String ATTR_MEDIA = "media";

	/**
	 * method attribute
	 */
	public static final String ATTR_METHOD = "method";

	/**
	 * multiple attribute
	 */
	public static final String ATTR_MULTIPLE = "multiple";

	/**
	 * name attribute
	 */
	public static final String ATTR_NAME = "name";

	/**
	 * nohref attribute
	 */
	public static final String ATTR_NOHREF = "nohref";

	/**
	 * noresize attribute
	 */
	public static final String ATTR_NORESIZE = "noresize";

	/**
	 * noshade attribute
	 */
	public static final String ATTR_NOSHADE = "noshade";

	/**
	 * nowrap attribute
	 */
	public static final String ATTR_NOWRAP = "nowrap";

	/**
	 * object attribute
	 */
	public static final String ATTR_OBJECT = "object";

	/**
	 * onblur attribute
	 */
	public static final String ATTR_ONBLUR = "onblur";

	/**
	 * onchange attribute
	 */
	public static final String ATTR_ONCHANGE = "onchange";

	/**
	 * onclick attribute
	 */
	public static final String ATTR_ONCLICK = "onclick";

	/**
	 * ondblclick attribute
	 */
	public static final String ATTR_ONDBLCLICK = "ondblclick";

	/**
	 * onfocus attribute
	 */
	public static final String ATTR_ONFOCUS = "onfocus";

	/**
	 * onkeydown attribute
	 */
	public static final String ATTR_ONKEYDOWN = "onkeydown";

	/**
	 * onkeypress attribute
	 */
	public static final String ATTR_ONKEYPRESS = "onkeypress";

	/**
	 * onkeyup attribute
	 */
	public static final String ATTR_ONKEYUP = "onkeyup";

	/**
	 * onload attribute
	 */
	public static final String ATTR_ONLOAD = "onload";

	/**
	 * onmousedown attribute
	 */
	public static final String ATTR_ONMOUSEDOWN = "onmousedown";

	/**
	 * onmousemove attribute
	 */
	public static final String ATTR_ONMOUSEMOVE = "onmousemove";

	/**
	 * onmouseout attribute
	 */
	public static final String ATTR_ONMOUSEOUT = "onmouseout";

	/**
	 * onmouseover attribute
	 */
	public static final String ATTR_ONMOUSEOVER = "onmouseover";

	/**
	 * onmouseup attribute
	 */
	public static final String ATTR_ONMOUSEUP = "onmouseup";

	/**
	 * onreset attribute
	 */
	public static final String ATTR_ONRESET = "onreset";

	/**
	 * onselect attribute
	 */
	public static final String ATTR_ONSELECT = "onselect";

	/**
	 * onsubmit attribute
	 */
	public static final String ATTR_ONSUBMIT = "onsubmit";

	/**
	 * onunload attribute
	 */
	public static final String ATTR_ONUNLOAD = "onunload";

	/**
	 * profile attribute
	 */
	public static final String ATTR_PROFILE = "profile";

	/**
	 * prompt attribute
	 */
	public static final String ATTR_PROMPT = "prompt";

	/**
	 * readonly attribute
	 */
	public static final String ATTR_READONLY = "readonly";

	/**
	 * rel attribute
	 */
	public static final String ATTR_REL = "rel";

	/**
	 * rev attribute
	 */
	public static final String ATTR_REV = "rev";

	/**
	 * rows attribute
	 */
	public static final String ATTR_ROWS = "rows";

	/**
	 * rowspan attribute
	 */
	public static final String ATTR_ROWSPAN = "rowspan";

	/**
	 * rules attribute 
	 */
	public static final String ATTR_RULES = "rules";

	/**
	 * scheme attribute
	 */
	public static final String ATTR_SCHEME = "scheme";

	/**
	 * scope attribute
	 */
	public static final String ATTR_SCOPE = "scope";

	/**
	 * scrolling attribute
	 */
	public static final String ATTR_SCROLLING = "scrolling";

	/**
	 * selected attribute
	 */
	public static final String ATTR_SELECTED = "selected";

	/**
	 * shape attribute
	 */
	public static final String ATTR_SHAPE = "shape";

	/**
	 * size attribute
	 */
	public static final String ATTR_SIZE = "size";

	/**
	 * span attribute
	 */
	public static final String ATTR_SPAN = "span";

	/**
	 * src attribute
	 */
	public static final String ATTR_SRC = "src";

	/**
	 * standby attribute
	 */
	public static final String ATTR_STANDBY = "standby";

	/**
	 * start attribute
	 */
	public static final String ATTR_START = "start";

	/**
	 * style attribute
	 */
	public static final String ATTR_STYLE = "style";

	/**
	 * summary attribute
	 */
	public static final String ATTR_SUMMARY = "summary";

	/**
	 * tabindex attribute
	 */
	public static final String ATTR_TABINDEX = "tabindex";

	/**
	 * target attribute
	 */
	public static final String ATTR_TARGET = "target";

	/**
	 * text attribute
	 */
	public static final String ATTR_TEXT = "text";

	/**
	 * title attribute
	 */
	public static final String ATTR_TITLE = "title";

	/**
	 * type attribute
	 */
	public static final String ATTR_TYPE = "type";

	/**
	 * usemap attribute
	 */
	public static final String ATTR_USEMAP = "usemap";

	/**
	 * valign attribute
	 */
	public static final String ATTR_VALIGN = "valign";

	/**
	 * value attribute
	 */
	public static final String ATTR_VALUE = "value";

	/**
	 * valuetype attribute
	 */
	public static final String ATTR_VALUETYPE = "valuetype";

	/**
	 * version attribute
	 */
	public static final String ATTR_VERSION = "version";

	/**
	 * vlink attribute
	 */
	public static final String ATTR_VLINK = "vlink";

	/**
	 * vspace attribute
	 */
	public static final String ATTR_VSPACE = "vspace";

	/**
	 * width attribute
	 */
	public static final String ATTR_WIDTH = "width";

	/**
	 * submit type
	 */
	public static final String TYPE_SUBMIT = "submit";

	/**
	 * checkbox type
	 */
	public static final String TYPE_CHECKBOX = "checkbox";

	/**
	 * radio type
	 */
	public static final String TYPE_RADIO = "radio";

	/**
	 * image type
	 */
	public static final String TYPE_IMAGE = "image";

	/**
	 * password type
	 */
	public static final String TYPE_PASSWORD = "password";

	/**
	 * text type
	 */
	public static final String TYPE_TEXT = "text";

	/**
	 * hidden type
	 */
	public static final String TYPE_HIDDEN = "hidden";

	/**
	 * submit query label
	 */
	public static final String SUBMIT_LABEL = "Submit Query";

	/**
	 * reset label
	 */
	public static final String RESET_LABEL = "Reset";
}
