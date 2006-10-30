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
package org.eclipse.jst.pagedesigner.css2.property;

/**
 * @author mengbo
 */
public interface ICSSPropertyID {
	public static final String ATTR_DISPLAY = "display";

	public static final String ATTR_WHITESPACE = "white-space";

	public static final String ATTR_TEXTDECORATION = "text-decoration";

	public static final String ATTR_TEXTALIGN = "text-align";

	public static final String ATTR_WIDTH = "width";

	public static final String ATTR_MIN_WIDTH = "min-width";

	public static final String ATTR_HEIGHT = "height";

	public static final String ATTR_MIN_HEIGHT = "min-height";

	public static final String ATTR_BORDER_LEFT_WIDTH = "border-left-width";

	public static final String ATTR_BORDER_RIGHT_WIDTH = "border-right-width";

	public static final String ATTR_BORDER_BOTTOM_WIDTH = "border-bottom-width";

	public static final String ATTR_BORDER_TOP_WIDTH = "border-top-width";

	public static final String ATTR_FONT_FAMILY = "font-family";

	public static final String ATTR_FONT_SIZE = "font-size";

	public static final String ATTR_FONT_STYLE = "font-style";

	public static final String ATTR_FONT_WEIGHT = "font-weight";

	public static final String ATTR_BORDER_LEFT_STYLE = "border-left-style";

	public static final String ATTR_BORDER_RIGHT_STYLE = "border-right-style";

	public static final String ATTR_BORDER_BOTTOM_STYLE = "border-bottom-style";

	public static final String ATTR_BORDER_TOP_STYLE = "border-top-style";

	public static final String ATTR_BORDER_LEFT_COLOR = "border-left-color";

	public static final String ATTR_BORDER_RIGHT_COLOR = "border-right-color";

	public static final String ATTR_BORDER_BOTTOM_COLOR = "border-bottom-color";

	public static final String ATTR_BORDER_TOP_COLOR = "border-top-color";

	public static final String ATTR_PADDING_RIGHT = "padding-right";

	public static final String ATTR_PADDING_BOTTOM = "padding-bottom";

	public static final String ATTR_PADDING_LEFT = "padding-left";

	public static final String ATTR_PADDING_TOP = "padding-top";

	public static final String ATTR_MARGIN_RIGHT = "margin-right";

	public static final String ATTR_MARGIN_BOTTOM = "margin-bottom";

	public static final String ATTR_MARGIN_LEFT = "margin-left";

	public static final String ATTR_MARGIN_TOP = "margin-top";

	public static final String ATTR_BACKGROUND_COLOR = "background-color";

	public static final String ATTR_COLOR = "color";

	public static final String ATTR_TEXTCOLOR = "text";

	public static final String ATTR_BORDER_COLLAPSE = "border-collapse";

	public static final String ATTR_BORDER_SPACING = "border-spacing";

	public static final String ATTR_LIST_STYLE_TYPE = "list-style-type";

	public static final String ATTR_LIST_STYLE_POSITION = "list-style-position";

	public static final String ATTR_LIST_STYLE_IMAGE = "list-style-image";

	public static final String ATTR_COUNTER_RESET = "counter-reset";

	public static final String ATTR_COUNTER_INCREMENT = "counter-increment";

	public static final String ATTR_EMPTY_CELLS = "empty-cells";

	public static final String ATTR_VISIBILITY = "visibility";

	public static final String ATTR_VERTICAL_ALIGN = "vertical-align";

	public static final String ATTR_HORIZONTAL_ALIGN = "horizontal-align";

	public static final String ATTR_CONTENT = "content";

	public static final String ATTR_BGCOLOR = "bgcolor";

	public static final String ATTR_TEXT = "text";

	public final static String ATTR_MULTIPLE = "multiple";

	public final static String ATTR_ITEMLABEL = "itemLabel";

	public final static String ATTR_BINDING = "binding";

	public final static String ATTR_ITEMVALUE = "itemValue";

	public final static String ATTR_VALUE = "value";

	public static final String ATTR_URI = "uri";

	public static final String ATTR_PREFIX = "prefix";

	public static final String ATTR_STYLECLASS = "styleClass";

	public static final String ATTR_SIZE = "size";

	public static final String ATTR_BASEFONT = "basefont";

	public static final String ATTR_ROWSPAN = "rowSpan";

	public static final String ATTR_COLSPAN = "colSpan";

	public static final String ATTR_TYPE = "type";

	public static final String ATTR_URL = "url";

	public static final String ATTR_SRC = "src";

	public static final String ATTR_TAGLIB_LOCATION = "taglib-location";

	public static final String ATTR_TAGLIB_URI = "taglib-uri";

	public static final String ATTR_IMAGE = "image";

	public static final String ATTR_RESET = "reset";

	public static final String ATTR_FOOTER = "footer";

	public static final String ATTR_HEADER = "header";

	public static final String ATTR_COLUMNS = "columns";

	public static final String ATTR_COLUMNCLASSES = "columnClasses";

	public static final String ATTR_CLASS = "class";

	public static final String ATTR_STYLE = "style";

	public static final String ATTR_PAGE = "page";

	public static final String ATTR_HREF = "href";

	public static final String ATTR_NAME = "name";

	public static final String ATTR_BASENAME = "basename";

	public static final String ATTR_ACTION = "action";

	public static final String ATTR_TARGET = "target";

	public static final String ATTR_TOP = "top";

	public static final String ATTR_BOTTOM = "bottom";

	public static final String ATTR_LEFT = "left";

	public static final String ATTR_RIGHT = "right";

	public static final String ATTR_POSITION = "position";

	public static final String VAL_TRANSPARENT = "transparent";

	public static final String VAL_COLLAPSE = "collapse";

	public static final String VAL_SEPARATE = "separate";

	public static final String ATTR_OVERFLOW = "overflow";

	// border style values
	public static final String VAL_HIDDEN = "hidden";

	public static final String VAL_DOTTED = "dotted";

	public static final String VAL_DASHED = "dashed";

	public static final String VAL_SOLID = "solid";

	public static final String VAL_DOUBLE = "double";

	public static final String VAL_GROOVE = "groove";

	public static final String VAL_RIDGE = "ridge";

	public static final String VAL_INSET = "inset";

	public static final String VAL_OUTSET = "outset";

	// XXX: we introduce a new style for TD default border style, since it
	// seemed IE
	// is using none of the above style for td.
	public static final String VAL_TDBORDERSTYLE = "__td_border_style__";

	public static final String VAL_THIN = "thin";

	public static final String VAL_THICK = "thick";

	public static final String VAL_INLINE = "inline";

	public static final String VAL_BLOCK = "block";

	public static final String VAL_INLINE_BLOCK = "inline-block";

	public static final String VAL_LIST_ITEM = "list-item";

	public static final String VAL_RUN_IN = "run-in";

	public static final String VAL_COMPACT = "compact";

	public static final String VAL_MARKER = "marker";

	public static final String VAL_TABLE = "table";

	public static final String VAL_INLINE_TABLE = "inline-table";

	public static final String VAL_TABLE_ROW_GROUP = "table-row-group";

	public static final String VAL_TABLE_HEADER_GROUP = "table-header-group";

	public static final String VAL_TABLE_FOOTER_GROUP = "table-footer-group";

	public static final String VAL_TABLE_ROW = "table-row";

	public static final String VAL_TABLE_COLUMN_GROUP = "table-column-group";

	public static final String VAL_TABLE_COLUMN = "table-column";

	public static final String VAL_TABLE_CELL = "table-cell";

	public static final String VAL_TABLE_CAPTION = "table-caption";

	public static final String VAL_NONE = "none";

	public static final String VAL_XX_SMALL = "xx-small";

	public static final String VAL_X_SMALL = "x-small";

	public static final String VAL_SMALL = "small";

	public static final String VAL_MEDIUM = "medium";

	public static final String VAL_LARGE = "large";

	public static final String VAL_X_LARGE = "x-large";

	public static final String VAL_XX_LARGE = "xx-large";

	public static final String VAL_LARGER = "larger";

	public static final String VAL_SMALLER = "smaller";

	public static final String VAL_ITALIC = "italic";

	public static final String VAL_OBLIQUE = "oblique";

	public static final String VAL_NORMAL = "normal";

	public static final String VAL_BOLD = "bold";

	public static final String VAL_BOLDER = "bolder";

	public static final String VAL_LIGHTER = "lighter";

	public static final String VAL_AUTO = "auto";

	public static final String VAL_OUTSIDE = "outside";

	public static final String VAL_INSIDE = "inside";

	public static final String VAL_LEFT = "left";

	public static final String VAL_RIGHT = "right";

	public static final String VAL_CENTER = "center";

	public static final String VAL_JUSTIFY = "justify";

	public static final String VAL_UNDERLINE = "underline";

	public static final String VAL_OVERLINE = "overline";

	public static final String VAL_LINETHROUGH = "line-through";

	public static final String VAL_BLINK = "blink";

	public static final String VAL_PRE = "pre";

	public static final String VAL_NOWRAP = "nowrap";

	public final static String VAL_DISC = "disc"; //$NON-NLS-1$

	public final static String VAL_CIRCLE = "circle"; //$NON-NLS-1$

	public final static String VAL_DECIMAL = "decimal"; //$NON-NLS-1$

	public final static String VAL_CJK_IDEOGRAPHIC = "cjk-ideographic"; //$NON-NLS-1$

	public final static String VAL_DECIMAL_LEADING_ZERO = "decimal-leading-zero"; //$NON-NLS-1$

	public final static String VAL_ARMENIAN = "armenian"; //$NON-NLS-1$

	public final static String VAL_LOWER_ALPHA = "lower-alpha"; //$NON-NLS-1$

	public final static String VAL_LOWER_GREEK = "lower-greek"; //$NON-NLS-1$

	public final static String VAL_LOWER_LATIN = "lower-latin"; //$NON-NLS-1$

	public final static String VAL_LOWER_ROMAN = "lower-roman"; //$NON-NLS-1$

	public final static String VAL_UPPER_ALPHA = "upper-alpha"; //$NON-NLS-1$

	public final static String VAL_UPPER_LATIN = "upper-latin"; //$NON-NLS-1$

	public final static String VAL_UPPER_ROMAN = "upper-roman"; //$NON-NLS-1$

	public final static String VAL_HIRAGANA = "hiragana"; //$NON-NLS-1$

	public final static String VAL_HIRAGANA_IROHA = "hiragana-iroha"; //$NON-NLS-1$

	public final static String VAL_HEBREW = "hebrew"; //$NON-NLS-1$

	public final static String VAL_GEORGIAN = "georgian"; //$NON-NLS-1$

	public final static String VAL_KATAKANA = "katakana"; //$NON-NLS-1$

	public final static String VAL_KATAKANA_IROHA = "katakana-iroha"; //$NON-NLS-1$

	public final static String VAL_SQUARE = "square"; //$NON-NLS-1$

	public final static String VAL_IMAGE = "image"; //$NON-NLS-1$

	public static final String VAL_MULTIPLE = "multiple";

	public static final String VAL_BUTTON = "button";

	public static final String VAL_CHECKBOX = "checkbox";

	public static final String VAL_RADIO = "radio";

	public static final String VAL_FILE = "file";

	public static final String VAL_RESET = "reset";

	public static final String VAL_SUBMIT = "submit";

	public static final String VAL_PASSWORD = "password";

	public static final String VAL_TEXT = "text";

	public static final String VAL_VISIBLE = "visible";

	public static final String VAL_SCROLL = "scroll";

	public static final String TAG_TAGLIB = "taglib";

}
