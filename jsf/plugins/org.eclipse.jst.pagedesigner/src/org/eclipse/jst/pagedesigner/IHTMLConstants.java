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
	public static final String TAG_A = "a"; //$NON-NLS-1$

	/**
	 * abbreviated form (e.g.,WWW, HTTP, etc.) tag name
	 */
	public static final String TAG_ABBR = "abbr"; //  //$NON-NLS-1$

	/**
	 * acronym tag name
	 */
	public static final String TAG_ACRONYM = "acronym"; //$NON-NLS-1$

	/**
	 * address tag name
	 */
	public static final String TAG_ADDRESS = "address"; //$NON-NLS-1$

	/**
	 * Java applet tag name
	 */
	public static final String TAG_APPLET = "applet"; //$NON-NLS-1$

	/**
	 * client-side image map tag name
	 */
	public static final String TAG_AREA = "area"; //$NON-NLS-1$

	// area

	/**
	 * bold text style tag name
	 */
	public static final String TAG_B = "b"; //$NON-NLS-1$

	/**
	 * document base URI tag name
	 */
	public static final String TAG_BASE = "base"; //$NON-NLS-1$

	/**
	 * base font size tag name
	 */
	public static final String TAG_BASEFONT = "basefont"; //$NON-NLS-1$

	/**
	 * I18N BiDi over-ride tag name
	 */
	public static final String TAG_BDO = "bdo";  //$NON-NLS-1$

	/**
	 * large text style tag name
	 */
	public static final String TAG_BIG = "big"; //$NON-NLS-1$

	/**
	 * blockquote tag name
	 */
	public static final String TAG_BLOCKQUOTE = "blockquote"; //$NON-NLS-1$

	// quotation

	/**
	 * document body tag name
	 */
	public static final String TAG_BODY = "body"; //$NON-NLS-1$

	/**
	 * forced line break tag name
	 */
	public static final String TAG_BR = "br"; //$NON-NLS-1$

	/**
	 * push button tag name
	 */
	public static final String TAG_BUTTON = "button"; //$NON-NLS-1$

	/**
	 * table caption tag name
	 */
	public static final String TAG_CAPTION = "caption"; //$NON-NLS-1$

	/**
	 * shorthand for DIV align=center tag name
	 */
	public static final String TAG_CENTER = "center"; //$NON-NLS-1$

	/**
	 * citation tag name
	 */
	public static final String TAG_CITE = "cite"; //$NON-NLS-1$

	/**
	 * computer code fragment tag name
	 */
	public static final String TAG_CODE = "code"; //$NON-NLS-1$

	/**
	 * table column tag name
	 */
	public static final String TAG_COL = "col"; //$NON-NLS-1$

	/**
	 * table column group tag name
	 */
	public static final String TAG_COLGROUP = "colgroup"; //$NON-NLS-1$

	/**
	 * definition description tag name
	 */
	public static final String TAG_DD = "dd"; //$NON-NLS-1$

	/**
	 * deleted text tag name
	 */
	public static final String TAG_DEL = "del"; //$NON-NLS-1$

	/**
	 * instance definition tag name
	 */
	public static final String TAG_DFN = "dfn";  //$NON-NLS-1$

	/**
	 * directory list tag name
	 */
	public static final String TAG_DIR = "dir"; //$NON-NLS-1$

	/**
	 * generic language/style container (div) tag name
	 */
	public static final String TAG_DIV = "div"; //$NON-NLS-1$

	/**
	 * definition list tag name
	 */
	public static final String TAG_DL = "dl"; //$NON-NLS-1$

	/**
	 * definition term tag name
	 */
	public static final String TAG_DT = "dt"; //$NON-NLS-1$

	/**
	 * emphasis tag name
	 */
	public static final String TAG_EM = "em"; //$NON-NLS-1$

	/**
	 * fieldset tag name
	 */
	public static final String TAG_FIELDSET = "fieldset"; //$NON-NLS-1$

	/**
	 * local change to font (font) tag name
	 */
	public static final String TAG_FONT = "font";  //$NON-NLS-1$

	/**
	 * form tag name
	 */
	public static final String TAG_FORM = "form"; //$NON-NLS-1$

	/**
	 * frame tag name
	 */
	public static final String TAG_FRAME = "frame"; //$NON-NLS-1$

	/**
	 * frameset tag name
	 */
	public static final String TAG_FRAMESET = "frameset";  //$NON-NLS-1$
	
	/**
	 * h1 tag name
	 */
	public static final String TAG_H1 = "h1";  //$NON-NLS-1$

	/**
	 * h2 tag name
	 */
	public static final String TAG_H2 = "h2";  //$NON-NLS-1$

	/**
	 * h3 tag name
	 */
	public static final String TAG_H3 = "h3";  //$NON-NLS-1$

	/**
	 * h4 tag name
	 */
	public static final String TAG_H4 = "h4";  //$NON-NLS-1$
	
	/**
	 * h5 tag name
	 */
	public static final String TAG_H5 = "h5"; //$NON-NLS-1$

	/**
	 * h6 tag name
	 */
	public static final String TAG_H6 = "h6"; //$NON-NLS-1$

	/**
	 * head tag name
	 */
	public static final String TAG_HEAD = "head"; //$NON-NLS-1$

	/**
	 * horizontal rule tag name
	 */
	public static final String TAG_HR = "hr";  //$NON-NLS-1$

	/**
	 * document root element (html) tag name
	 */
	public static final String TAG_HTML = "html"; //$NON-NLS-1$

	/**
	 * italic text style tag name
	 */
	public static final String TAG_I = "i"; //$NON-NLS-1$

	/**
	 * inline subwindow (iframe) tag name
	 */
	public static final String TAG_IFRAME = "iframe"; //$NON-NLS-1$

	/**
	 * Embedded image tag name
	 */
	public static final String TAG_IMG = "img";  //$NON-NLS-1$

	/**
	 * input tag name
	 */
	public static final String TAG_INPUT = "input"; //$NON-NLS-1$

	/**
	 * inserted text tag name
	 */
	public static final String TAG_INS = "ins"; //$NON-NLS-1$

	/**
	 * isindex tag name
	 */
	public static final String TAG_ISINDEX = "isindex"; //$NON-NLS-1$

	/**
	 * text to be entered by the user tag name
	 */
	public static final String TAG_KBD = "kbd"; //$NON-NLS-1$

	/**
	 * form field label text tag name
	 */
	public static final String TAG_LABEL = "label";  //$NON-NLS-1$

	/**
	 * fieldset legend tag name
	 */
	public static final String TAG_LEGEND = "legend"; //$NON-NLS-1$

	/**
	 * list item tag name
	 */
	public static final String TAG_LI = "li";  //$NON-NLS-1$

	/**
	 * media-independent link tag name
	 */
	public static final String TAG_LINK = "link"; //$NON-NLS-1$

	/**
	 * client-side image map tag name
	 */
	public static final String TAG_MAP = "map";  //$NON-NLS-1$

	/**
	 * menu list tag name
	 */
	public static final String TAG_MENU = "menu"; //$NON-NLS-1$

	/**
	 * generic metainformation tag name
	 */
	public static final String TAG_META = "meta"; //$NON-NLS-1$

	/**
	 * noembed tag name
	 */
	public static final String TAG_NOEMBED = "noembed"; //$NON-NLS-1$

	/**
	 * container for non-frame-based
     * rendering (noframes) tag name
	 */
	public static final String TAG_NOFRAMES = "noframes"; //$NON-NLS-1$

	// 

	/**
	 * container for non-script-based
     * rendering (noscript) tag name
	 */
	public static final String TAG_NOSCRIPT = "noscript"; //$NON-NLS-1$

	// 

	/**
	 * generic embedded (object) tag name
	 */
	public static final String TAG_OBJECT = "object"; //$NON-NLS-1$

	/**
	 * ordered list tag name
	 */
	public static final String TAG_OL = "ol"; //$NON-NLS-1$

	/**
	 * option group tag name
	 */
	public static final String TAG_OPTGROUP = "optgroup";  //$NON-NLS-1$

	/**
	 * selectable choice tag name
	 */
	public static final String TAG_OPTION = "option"; //$NON-NLS-1$

	/**
	 * paragraph tag name
	 */
	public static final String TAG_P = "p"; //$NON-NLS-1$

	/**
	 * named property value tag name
	 */
	public static final String TAG_PARAM = "param"; //$NON-NLS-1$

	/**
	 * preformatted text tag name
	 */
	public static final String TAG_PRE = "pre"; //$NON-NLS-1$

	/**
	 * short inline quotation tag name
	 */
	public static final String TAG_Q = "q"; //$NON-NLS-1$

	/**
	 * strike-through text style tag name
	 */
	public static final String TAG_S = "s";  //$NON-NLS-1$

	/**
	 * sample program output tag name
	 */
	public static final String TAG_SAMP = "samp"; //$NON-NLS-1$

	// scripts, etc.

	/**
	 * script statements tag name
	 */
	public static final String TAG_SCRIPT = "script";  //$NON-NLS-1$

	/**
	 * option selector tag name
	 */
	public static final String TAG_SELECT = "select";  //$NON-NLS-1$

	/**
	 * small text style tag name
	 */
	public static final String TAG_SMALL = "small";  //$NON-NLS-1$

	/**
	 * generic language/style tag name
	 */
	public static final String TAG_SPAN = "span"; //$NON-NLS-1$

	// container

	/**
	 * strike-through text tag name
	 */
	public static final String TAG_STRIKE = "strike"; //$NON-NLS-1$

	/**
	 * strong emphasis tag name
	 */
	public static final String TAG_STRONG = "strong"; //$NON-NLS-1$

	/**
	 *  style info tag name
	 */
	public static final String TAG_STYLE = "style"; //$NON-NLS-1$

	/**
	 * subscript tag name
	 */
	public static final String TAG_SUB = "sub"; //$NON-NLS-1$

	/**
	 * superscript tag name
	 */
	public static final String TAG_SUP = "sup"; //$NON-NLS-1$

	/**
	 * table tag name
	 */
	public static final String TAG_TABLE = "table"; //$NON-NLS-1$

	/**
	 *  table body tag name
	 */
	public static final String TAG_TBODY = "tbody"; //$NON-NLS-1$

	/**
	 * table data cell tag name
	 */
	public static final String TAG_TD = "td"; //$NON-NLS-1$

	/**
	 * multi-line text
	 */
	public static final String TAG_TEXTAREA = "textarea"; //$NON-NLS-1$

	// field

	/**
	 * table footer tag name
	 */
	public static final String TAG_TFOOT = "tfoot"; //$NON-NLS-1$

	/**
	 * table header cell tag name
	 */
	public static final String TAG_TH = "th"; //$NON-NLS-1$

	/**
	 * table header tag name
	 */
	public static final String TAG_THEAD = "thead"; //$NON-NLS-1$

	/**
	 * document title tag name
	 */
	public static final String TAG_TITLE = "title"; //$NON-NLS-1$

	/**
	 * table row tag name
	 */
	public static final String TAG_TR = "tr"; //$NON-NLS-1$

	/**
	 * teletype or monospaced text tag name
	 */
	public static final String TAG_TT = "tt";  //$NON-NLS-1$

	// style

	/**
	 * underlined text style tag name
	 */
	public static final String TAG_U = "u";  //$NON-NLS-1$

	/**
	 * unordered list tag name
	 */
	public static final String TAG_UL = "ul"; //$NON-NLS-1$

	/**
	 * instance of a variable tag name
	 */
	public static final String TAG_VAR = "var"; //$NON-NLS-1$

	/**
	 * Tag identifier for an HTML form tag
	 */
	public static final TagIdentifier TAG_IDENTIFIER_HTML_FORM =
	    TagIdentifierFactory.createJSPTagWrapper(CMDocType.HTML_DOC_TYPE, TAG_FORM);
	
    // program argument

	/**
	 * abbr attribute
	 */
	public static final String ATTR_ABBR = "abbr"; //$NON-NLS-1$

	/**
	 * accept-charset attribute
	 */
	public static final String ATTR_ACCEPTCHARSET = "accept-charset"; //$NON-NLS-1$

	/**
	 * accept attribute
	 */
	public static final String ATTR_ACCEPT = "accept"; //$NON-NLS-1$

	/**
	 * access key attribute
	 */
	public static final String ATTR_ACCESSKEY = "accesskey"; //$NON-NLS-1$

	/**
	 * action attribute
	 */
	public static final String ATTR_ACTION = "action"; //$NON-NLS-1$

	/**
	 * align attribute
	 */
	public static final String ATTR_ALIGN = "align"; //$NON-NLS-1$

	/**
	 * alink attribute
	 */
	public static final String ATTR_ALINK = "alink"; //$NON-NLS-1$

	/**
	 * alt attribute
	 */
	public static final String ATTR_ALT = "alt"; //$NON-NLS-1$

	/**
	 * archive attribute
	 */
	public static final String ATTR_ARCHIVE = "archive"; //$NON-NLS-1$

	/**
	 * axis attribute
	 */
	public static final String ATTR_AXIS = "axis"; //$NON-NLS-1$

	/**
	 * background attribute
	 */
	public static final String ATTR_BACKGROUND = "background"; //$NON-NLS-1$

	/**
	 * bgcolor attribute
	 */
	public static final String ATTR_BGCOLOR = "bgcolor"; //$NON-NLS-1$

	/**
	 * border attribute
	 */
	public static final String ATTR_BORDER = "border"; //$NON-NLS-1$

	/**
	 * cellpadding attribute
	 */
	public static final String ATTR_CELLPADDING = "cellpadding"; //$NON-NLS-1$

	/**
	 * cellspacing attribute
	 */
	public static final String ATTR_CELLSPACING = "cellspacing"; //$NON-NLS-1$

	/**
	 * char attribute
	 */
	public static final String ATTR_CHAR = "char"; //$NON-NLS-1$

	/**
	 * charoff attribute
	 */
	public static final String ATTR_CHAROFF = "charoff"; //$NON-NLS-1$

	/**
	 * charset attribute
	 */
	public static final String ATTR_CHARSET = "charset"; //$NON-NLS-1$

	/**
	 * checked attribute
	 */
	public static final String ATTR_CHECKED = "checked"; //$NON-NLS-1$

	/**
	 * cite attribute
	 */
	public static final String ATTR_CITE = "cite"; //$NON-NLS-1$

	/**
	 * class attribute
	 */
	public static final String ATTR_CLASS = "class"; //$NON-NLS-1$

	/**
	 * classid attribute
	 */
	public static final String ATTR_CLASSID = "classid"; //$NON-NLS-1$

	/**
	 * clear attribute
	 */
	public static final String ATTR_CLEAR = "clear"; //$NON-NLS-1$

	/**
	 * code attribute
	 */
	public static final String ATTR_CODE = "code"; //$NON-NLS-1$

	/**
	 * codebase attribute
	 */
	public static final String ATTR_CODEBASE = "codebase"; //$NON-NLS-1$

	/**
	 * code type attribute
	 */
	public static final String ATTR_CODETYPE = "codetype"; //$NON-NLS-1$

	/**
	 * color attribute
	 */
	public static final String ATTR_COLOR = "color"; //$NON-NLS-1$

	/**
	 * cols attribute
	 */
	public static final String ATTR_COLS = "cols"; //$NON-NLS-1$

	/**
	 * colspan attribute
	 */
	public static final String ATTR_COLSPAN = "colspan"; //$NON-NLS-1$

	/**
	 * compact attribute
	 */
	public static final String ATTR_COMPACT = "compact"; //$NON-NLS-1$

	/**
	 * content attribute
	 */
	public static final String ATTR_CONTENT = "content"; //$NON-NLS-1$

	/**
	 * coords attribute
	 */
	public static final String ATTR_COORDS = "coords"; //$NON-NLS-1$

	/**
	 * data attribute
	 */
	public static final String ATTR_DATA = "data"; //$NON-NLS-1$

	/**
	 * datetime attribute
	 */
	public static final String ATTR_DATETIME = "datetime"; //$NON-NLS-1$

	/**
	 * declare attribute
	 */
	public static final String ATTR_DECLARE = "declare"; //$NON-NLS-1$

	/**
	 * defer attribute
	 */
	public static final String ATTR_DEFER = "defer"; //$NON-NLS-1$

	/**
	 * dir attribute
	 */
	public static final String ATTR_DIR = "dir"; //$NON-NLS-1$

	/**
	 * disabled attribute
	 */
	public static final String ATTR_DISABLED = "disabled"; //$NON-NLS-1$

	/**
	 * enctype attribute
	 */
	public static final String ATTR_ENCTYPE = "enctype"; //$NON-NLS-1$

	/**
	 * face attribute
	 */
	public static final String ATTR_FACE = "face"; //$NON-NLS-1$

	/**
	 * for  attribute
	 */
	public static final String ATTR_FOR = "for"; //$NON-NLS-1$

	/**
	 * frame attribute
	 */
	public static final String ATTR_FRAME = "frame"; //$NON-NLS-1$

	/**
	 * frameborder attribute
	 */
	public static final String ATTR_FRAMEBORDER = "frameborder"; //$NON-NLS-1$

	/**
	 * headers attribute
	 */
	public static final String ATTR_HEADERS = "headers"; //$NON-NLS-1$

	/**
	 * height attribute
	 */
	public static final String ATTR_HEIGHT = "height"; //$NON-NLS-1$

	/**
	 * href  attribute
	 */
	public static final String ATTR_HREF = "href"; //$NON-NLS-1$

	/**
	 * hreflang attribute
	 */
	public static final String ATTR_HREFLANG = "hreflang"; //$NON-NLS-1$

	/**
	 * hspace attribute
	 */
	public static final String ATTR_HSPACE = "hspace"; //$NON-NLS-1$

	/**
	 * http-equiv attribute
	 */
	public static final String ATTR_HTTPEQUIV = "http-equiv"; //$NON-NLS-1$

	/**
	 * id attribute
	 */
	public static final String ATTR_ID = "id"; //$NON-NLS-1$

	/**
	 * ismap attribute
	 */
	public static final String ATTR_ISMAP = "ismap"; //$NON-NLS-1$

	/**
	 * label attribute
	 */
	public static final String ATTR_LABEL = "label"; //$NON-NLS-1$

	/**
	 * lang attribute
	 */
	public static final String ATTR_LANG = "lang"; //$NON-NLS-1$

	/**
	 * language attribute
	 */
	public static final String ATTR_LANGUAGE = "language"; //$NON-NLS-1$

	/**
	 * link attribute
	 */
	public static final String ATTR_LINK = "link"; //$NON-NLS-1$

	/**
	 * longdesc attribute
	 */
	public static final String ATTR_LONGDESC = "longdesc"; //$NON-NLS-1$

	/**
	 *  marginheight attribute
	 */
	public static final String ATTR_MARGINHEIGHT = "marginheight"; //$NON-NLS-1$

	/**
	 * margin width attribute
	 */
	public static final String ATTR_MARGINWIDTH = "marginwidth"; //$NON-NLS-1$

	/**
	 * maxlength attribute
	 */
	public static final String ATTR_MAXLENGTH = "maxlength"; //$NON-NLS-1$

	/**
	 * media attribute
	 */
	public static final String ATTR_MEDIA = "media"; //$NON-NLS-1$

	/**
	 * method attribute
	 */
	public static final String ATTR_METHOD = "method"; //$NON-NLS-1$

	/**
	 * multiple attribute
	 */
	public static final String ATTR_MULTIPLE = "multiple"; //$NON-NLS-1$

	/**
	 * name attribute
	 */
	public static final String ATTR_NAME = "name"; //$NON-NLS-1$

	/**
	 * nohref attribute
	 */
	public static final String ATTR_NOHREF = "nohref"; //$NON-NLS-1$

	/**
	 * noresize attribute
	 */
	public static final String ATTR_NORESIZE = "noresize"; //$NON-NLS-1$

	/**
	 * noshade attribute
	 */
	public static final String ATTR_NOSHADE = "noshade"; //$NON-NLS-1$

	/**
	 * nowrap attribute
	 */
	public static final String ATTR_NOWRAP = "nowrap"; //$NON-NLS-1$

	/**
	 * object attribute
	 */
	public static final String ATTR_OBJECT = "object"; //$NON-NLS-1$

	/**
	 * onblur attribute
	 */
	public static final String ATTR_ONBLUR = "onblur"; //$NON-NLS-1$

	/**
	 * onchange attribute
	 */
	public static final String ATTR_ONCHANGE = "onchange"; //$NON-NLS-1$

	/**
	 * onclick attribute
	 */
	public static final String ATTR_ONCLICK = "onclick"; //$NON-NLS-1$

	/**
	 * ondblclick attribute
	 */
	public static final String ATTR_ONDBLCLICK = "ondblclick"; //$NON-NLS-1$

	/**
	 * onfocus attribute
	 */
	public static final String ATTR_ONFOCUS = "onfocus"; //$NON-NLS-1$

	/**
	 * onkeydown attribute
	 */
	public static final String ATTR_ONKEYDOWN = "onkeydown"; //$NON-NLS-1$

	/**
	 * onkeypress attribute
	 */
	public static final String ATTR_ONKEYPRESS = "onkeypress"; //$NON-NLS-1$

	/**
	 * onkeyup attribute
	 */
	public static final String ATTR_ONKEYUP = "onkeyup"; //$NON-NLS-1$

	/**
	 * onload attribute
	 */
	public static final String ATTR_ONLOAD = "onload"; //$NON-NLS-1$

	/**
	 * onmousedown attribute
	 */
	public static final String ATTR_ONMOUSEDOWN = "onmousedown"; //$NON-NLS-1$

	/**
	 * onmousemove attribute
	 */
	public static final String ATTR_ONMOUSEMOVE = "onmousemove"; //$NON-NLS-1$

	/**
	 * onmouseout attribute
	 */
	public static final String ATTR_ONMOUSEOUT = "onmouseout"; //$NON-NLS-1$

	/**
	 * onmouseover attribute
	 */
	public static final String ATTR_ONMOUSEOVER = "onmouseover"; //$NON-NLS-1$

	/**
	 * onmouseup attribute
	 */
	public static final String ATTR_ONMOUSEUP = "onmouseup"; //$NON-NLS-1$

	/**
	 * onreset attribute
	 */
	public static final String ATTR_ONRESET = "onreset"; //$NON-NLS-1$

	/**
	 * onselect attribute
	 */
	public static final String ATTR_ONSELECT = "onselect"; //$NON-NLS-1$

	/**
	 * onsubmit attribute
	 */
	public static final String ATTR_ONSUBMIT = "onsubmit"; //$NON-NLS-1$

	/**
	 * onunload attribute
	 */
	public static final String ATTR_ONUNLOAD = "onunload"; //$NON-NLS-1$

	/**
	 * profile attribute
	 */
	public static final String ATTR_PROFILE = "profile"; //$NON-NLS-1$

	/**
	 * prompt attribute
	 */
	public static final String ATTR_PROMPT = "prompt"; //$NON-NLS-1$

	/**
	 * readonly attribute
	 */
	public static final String ATTR_READONLY = "readonly"; //$NON-NLS-1$

	/**
	 * rel attribute
	 */
	public static final String ATTR_REL = "rel"; //$NON-NLS-1$

	/**
	 * rev attribute
	 */
	public static final String ATTR_REV = "rev"; //$NON-NLS-1$

	/**
	 * rows attribute
	 */
	public static final String ATTR_ROWS = "rows"; //$NON-NLS-1$

	/**
	 * rowspan attribute
	 */
	public static final String ATTR_ROWSPAN = "rowspan"; //$NON-NLS-1$

	/**
	 * rules attribute 
	 */
	public static final String ATTR_RULES = "rules"; //$NON-NLS-1$

	/**
	 * scheme attribute
	 */
	public static final String ATTR_SCHEME = "scheme"; //$NON-NLS-1$

	/**
	 * scope attribute
	 */
	public static final String ATTR_SCOPE = "scope"; //$NON-NLS-1$

	/**
	 * scrolling attribute
	 */
	public static final String ATTR_SCROLLING = "scrolling"; //$NON-NLS-1$

	/**
	 * selected attribute
	 */
	public static final String ATTR_SELECTED = "selected"; //$NON-NLS-1$

	/**
	 * shape attribute
	 */
	public static final String ATTR_SHAPE = "shape"; //$NON-NLS-1$

	/**
	 * size attribute
	 */
	public static final String ATTR_SIZE = "size"; //$NON-NLS-1$

	/**
	 * span attribute
	 */
	public static final String ATTR_SPAN = "span"; //$NON-NLS-1$

	/**
	 * src attribute
	 */
	public static final String ATTR_SRC = "src"; //$NON-NLS-1$

	/**
	 * standby attribute
	 */
	public static final String ATTR_STANDBY = "standby"; //$NON-NLS-1$

	/**
	 * start attribute
	 */
	public static final String ATTR_START = "start"; //$NON-NLS-1$

	/**
	 * style attribute
	 */
	public static final String ATTR_STYLE = "style"; //$NON-NLS-1$

	/**
	 * summary attribute
	 */
	public static final String ATTR_SUMMARY = "summary"; //$NON-NLS-1$

	/**
	 * tabindex attribute
	 */
	public static final String ATTR_TABINDEX = "tabindex"; //$NON-NLS-1$

	/**
	 * target attribute
	 */
	public static final String ATTR_TARGET = "target"; //$NON-NLS-1$

	/**
	 * text attribute
	 */
	public static final String ATTR_TEXT = "text"; //$NON-NLS-1$

	/**
	 * title attribute
	 */
	public static final String ATTR_TITLE = "title"; //$NON-NLS-1$

	/**
	 * type attribute
	 */
	public static final String ATTR_TYPE = "type"; //$NON-NLS-1$

	/**
	 * usemap attribute
	 */
	public static final String ATTR_USEMAP = "usemap"; //$NON-NLS-1$

	/**
	 * valign attribute
	 */
	public static final String ATTR_VALIGN = "valign"; //$NON-NLS-1$

	/**
	 * value attribute
	 */
	public static final String ATTR_VALUE = "value"; //$NON-NLS-1$

	/**
	 * valuetype attribute
	 */
	public static final String ATTR_VALUETYPE = "valuetype"; //$NON-NLS-1$

	/**
	 * version attribute
	 */
	public static final String ATTR_VERSION = "version"; //$NON-NLS-1$

	/**
	 * vlink attribute
	 */
	public static final String ATTR_VLINK = "vlink"; //$NON-NLS-1$

	/**
	 * vspace attribute
	 */
	public static final String ATTR_VSPACE = "vspace"; //$NON-NLS-1$

	/**
	 * width attribute
	 */
	public static final String ATTR_WIDTH = "width"; //$NON-NLS-1$

	/**
	 * submit type
	 */
	public static final String TYPE_SUBMIT = "submit"; //$NON-NLS-1$

	/**
	 * checkbox type
	 */
	public static final String TYPE_CHECKBOX = "checkbox"; //$NON-NLS-1$

	/**
	 * radio type
	 */
	public static final String TYPE_RADIO = "radio"; //$NON-NLS-1$

	/**
	 * image type
	 */
	public static final String TYPE_IMAGE = "image"; //$NON-NLS-1$

	/**
	 * password type
	 */
	public static final String TYPE_PASSWORD = "password"; //$NON-NLS-1$

	/**
	 * text type
	 */
	public static final String TYPE_TEXT = "text"; //$NON-NLS-1$

	/**
	 * hidden type
	 */
	public static final String TYPE_HIDDEN = "hidden"; //$NON-NLS-1$

	/**
	 * submit query label
	 */
	public static final String SUBMIT_LABEL = "Submit Query"; //$NON-NLS-1$

	/**
	 * reset label
	 */
	public static final String RESET_LABEL = "Reset"; //$NON-NLS-1$
}
