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
	/**
	 * display attribute name
	 */
	public static final String ATTR_DISPLAY = "display";

	/**
	 * whitespace attribute name
	 */
	public static final String ATTR_WHITESPACE = "white-space";

	/**
	 * text decoration attribute name
	 */
	public static final String ATTR_TEXTDECORATION = "text-decoration";

	/**
	 * text align attribute name
	 */
	public static final String ATTR_TEXTALIGN = "text-align";

	/**
	 * width attribute name
	 */
	public static final String ATTR_WIDTH = "width";

	/**
	 * min-width attribute name
	 */
	public static final String ATTR_MIN_WIDTH = "min-width";

	/**
	 * height attribute name
	 */
	public static final String ATTR_HEIGHT = "height";

	/**
	 * min height attribute name
	 */
	public static final String ATTR_MIN_HEIGHT = "min-height";

	/**
	 * border-left-width attribute name
	 */
	public static final String ATTR_BORDER_LEFT_WIDTH = "border-left-width";

	/**
	 * border-right-width attribute name
	 */
	public static final String ATTR_BORDER_RIGHT_WIDTH = "border-right-width";

	/**
	 * border bottom width
	 */
	public static final String ATTR_BORDER_BOTTOM_WIDTH = "border-bottom-width";

	/**
	 * css style attribute
	 */
	public static final String ATTR_BORDER_TOP_WIDTH = "border-top-width";

    /**
     * css style attribute
     */
	public static final String ATTR_FONT_FAMILY = "font-family";

    /**
     * css style attribute
     */
	public static final String ATTR_FONT_SIZE = "font-size";

    /**
     * css style attribute
     */
	public static final String ATTR_FONT_STYLE = "font-style";

    /**
     * css style attribute
     */
	public static final String ATTR_FONT_WEIGHT = "font-weight";

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_LEFT_STYLE = "border-left-style";

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_RIGHT_STYLE = "border-right-style";

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_BOTTOM_STYLE = "border-bottom-style";

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_TOP_STYLE = "border-top-style";

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_LEFT_COLOR = "border-left-color";

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_RIGHT_COLOR = "border-right-color";

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_BOTTOM_COLOR = "border-bottom-color";

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_TOP_COLOR = "border-top-color";

    /**
     * css style attribute
     */
	public static final String ATTR_PADDING_RIGHT = "padding-right";

    /**
     * css style attribute
     */
	public static final String ATTR_PADDING_BOTTOM = "padding-bottom";

    /**
     * css style attribute
     */
	public static final String ATTR_PADDING_LEFT = "padding-left";

    /**
     * css style attribute
     */
	public static final String ATTR_PADDING_TOP = "padding-top";

    /**
     * css style attribute
     */
	public static final String ATTR_MARGIN_RIGHT = "margin-right";

    /**
     * css style attribute
     */
	public static final String ATTR_MARGIN_BOTTOM = "margin-bottom";

    /**
     * css style attribute
     */
	public static final String ATTR_MARGIN_LEFT = "margin-left";

    /**
     * css style attribute
     */
	public static final String ATTR_MARGIN_TOP = "margin-top";

    /**
     * css style attribute
     */
	public static final String ATTR_BACKGROUND_COLOR = "background-color";

    /**
     * css style attribute
     */
	public static final String ATTR_COLOR = "color";

    /**
     * css style attribute
     */
	public static final String ATTR_TEXTCOLOR = "text";

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_COLLAPSE = "border-collapse";

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_SPACING = "border-spacing";

    /**
     * css style attribute
     */
	public static final String ATTR_LIST_STYLE_TYPE = "list-style-type";

    /**
     * css style attribute
     */
	public static final String ATTR_LIST_STYLE_POSITION = "list-style-position";

    /**
     * css style attribute
     */
	public static final String ATTR_LIST_STYLE_IMAGE = "list-style-image";

    /**
     * css style attribute
     */
	public static final String ATTR_COUNTER_RESET = "counter-reset";

    /**
     * css style attribute
     */
	public static final String ATTR_COUNTER_INCREMENT = "counter-increment";

    /**
     * css style attribute
     */
	public static final String ATTR_EMPTY_CELLS = "empty-cells";

    /**
     * css style attribute
     */
	public static final String ATTR_VISIBILITY = "visibility";

    /**
     * css style attribute
     */
	public static final String ATTR_VERTICAL_ALIGN = "vertical-align";

    /**
     * css style attribute
     */
	public static final String ATTR_HORIZONTAL_ALIGN = "horizontal-align";

    /**
     * css style attribute
     */
	public static final String ATTR_CONTENT = "content";

    /**
     * css style attribute
     */
	public static final String ATTR_BGCOLOR = "bgcolor";

    /**
     * css style attribute
     */
	public static final String ATTR_TEXT = "text";

    /**
     * css style attribute
     */
	public final static String ATTR_MULTIPLE = "multiple";

    /**
     * css style attribute
     */
	public final static String ATTR_ITEMLABEL = "itemLabel";

    /**
     * css style attribute
     */
	public final static String ATTR_BINDING = "binding";

    /**
     * css style attribute
     */
	public final static String ATTR_ITEMVALUE = "itemValue";

    /**
     * css style attribute
     */
	public final static String ATTR_VALUE = "value";

    /**
     * css style attribute
     */
	public static final String ATTR_URI = "uri";

    /**
     * css style attribute
     */
	public static final String ATTR_PREFIX = "prefix";

    /**
     * css style attribute
     */
	public static final String ATTR_STYLECLASS = "styleClass";

    /**
     * css style attribute
     */
	public static final String ATTR_SIZE = "size";

    /**
     * css style attribute
     */
	public static final String ATTR_BASEFONT = "basefont";

    /**
     * css style attribute
     */
	public static final String ATTR_ROWSPAN = "rowSpan";

    /**
     * css style attribute
     */
	public static final String ATTR_COLSPAN = "colSpan";

    /**
     * css style attribute
     */
	public static final String ATTR_TYPE = "type";

    /**
     * css style attribute
     */
	public static final String ATTR_URL = "url";

    /**
     * css style attribute
     */
	public static final String ATTR_SRC = "src";

    /**
     * css style attribute
     */
	public static final String ATTR_TAGLIB_LOCATION = "taglib-location";

    /**
     * css style attribute
     */
	public static final String ATTR_TAGLIB_URI = "taglib-uri";

    /**
     * css style attribute
     */
	public static final String ATTR_IMAGE = "image";

    /**
     * css style attribute
     */
	public static final String ATTR_RESET = "reset";

    /**
     * css style attribute
     */
	public static final String ATTR_FOOTER = "footer";

    /**
     * css style attribute
     */
	public static final String ATTR_HEADER = "header";

    /**
     * css style attribute
     */
	public static final String ATTR_COLUMNS = "columns";

    /**
     * css style attribute
     */
	public static final String ATTR_COLUMNCLASSES = "columnClasses";

    /**
     * css style attribute
     */
	public static final String ATTR_CLASS = "class";

    /**
     * css style attribute
     */
	public static final String ATTR_STYLE = "style";

    /**
     * css style attribute
     */
	public static final String ATTR_PAGE = "page";

    /**
     * css style attribute
     */
	public static final String ATTR_HREF = "href";

    /**
     * css style attribute
     */
	public static final String ATTR_NAME = "name";

    /**
     * css style attribute
     */
	public static final String ATTR_BASENAME = "basename";

    /**
     * css style attribute
     */
	public static final String ATTR_ACTION = "action";

    /**
     * css style attribute
     */
	public static final String ATTR_TARGET = "target";

    /**
     * css style attribute
     */
	public static final String ATTR_TOP = "top";

    /**
     * css style attribute
     */
	public static final String ATTR_BOTTOM = "bottom";

    /**
     * css style attribute
     */
	public static final String ATTR_LEFT = "left";

    /**
     * css style attribute
     */
	public static final String ATTR_RIGHT = "right";

    /**
     * css style attribute
     */
	public static final String ATTR_POSITION = "position";

    /**
     * css style value
     */
	public static final String VAL_TRANSPARENT = "transparent";

    /**
     * css style value
     */
	public static final String VAL_COLLAPSE = "collapse";

    /**
     * css style value
     */
	public static final String VAL_SEPARATE = "separate";

    /**
     * css style value
     */
	public static final String ATTR_OVERFLOW = "overflow";

    /**
     * css style value
     */
	// border style values
	public static final String VAL_HIDDEN = "hidden";

    /**
     * css style value
     */
	public static final String VAL_DOTTED = "dotted";

    /**
     * css style value
     */
	public static final String VAL_DASHED = "dashed";

    /**
     * css style value
     */
	public static final String VAL_SOLID = "solid";

    /**
     * css style value
     */
	public static final String VAL_DOUBLE = "double";

    /**
     * css style value
     */
	public static final String VAL_GROOVE = "groove";

    /**
     * css style value
     */
	public static final String VAL_RIDGE = "ridge";

    /**
     * css style value
     */
	public static final String VAL_INSET = "inset";

    /**
     * css style value
     */
	public static final String VAL_OUTSET = "outset";

	// XXX: we introduce a new style for TD default border style, since it
	// seemed IE
	// is using none of the above style for td.
    /**
     * css style value
     */
	public static final String VAL_TDBORDERSTYLE = "__td_border_style__";

    /**
     * css style value
     */
	public static final String VAL_THIN = "thin";

    /**
     * css style value
     */
	public static final String VAL_THICK = "thick";

    /**
     * css style value
     */
	public static final String VAL_INLINE = "inline";

    /**
     * css style value
     */
	public static final String VAL_BLOCK = "block";

    /**
     * css style value
     */
	public static final String VAL_INLINE_BLOCK = "inline-block";

    /**
     * css style value
     */
	public static final String VAL_LIST_ITEM = "list-item";

    /**
     * css style value
     */
	public static final String VAL_RUN_IN = "run-in";

    /**
     * css style value
     */
	public static final String VAL_COMPACT = "compact";

    /**
     * css style value
     */
	public static final String VAL_MARKER = "marker";

    /**
     * css style value
     */
	public static final String VAL_TABLE = "table";

    /**
     * css style value
     */
	public static final String VAL_INLINE_TABLE = "inline-table";

    /**
     * css style value
     */
	public static final String VAL_TABLE_ROW_GROUP = "table-row-group";

    /**
     * css style value
     */
	public static final String VAL_TABLE_HEADER_GROUP = "table-header-group";

    /**
     * css style value
     */
	public static final String VAL_TABLE_FOOTER_GROUP = "table-footer-group";

    /**
     * css style value
     */
	public static final String VAL_TABLE_ROW = "table-row";

    /**
     * css style value
     */
	public static final String VAL_TABLE_COLUMN_GROUP = "table-column-group";

    /**
     * css style value
     */
	public static final String VAL_TABLE_COLUMN = "table-column";

    /**
     * css style value
     */
	public static final String VAL_TABLE_CELL = "table-cell";

    /**
     * css style value
     */
	public static final String VAL_TABLE_CAPTION = "table-caption";

    /**
     * css style value
     */
	public static final String VAL_NONE = "none";

    /**
     * css style value
     */
	public static final String VAL_XX_SMALL = "xx-small";

    /**
     * css style value
     */
	public static final String VAL_X_SMALL = "x-small";

    /**
     * css style value
     */
	public static final String VAL_SMALL = "small";

    /**
     * css style value
     */
	public static final String VAL_MEDIUM = "medium";

    /**
     * css style value
     */
	public static final String VAL_LARGE = "large";

    /**
     * css style value
     */
	public static final String VAL_X_LARGE = "x-large";

    /**
     * css style value
     */
	public static final String VAL_XX_LARGE = "xx-large";

    /**
     * css style value
     */
	public static final String VAL_LARGER = "larger";

    /**
     * css style value
     */
	public static final String VAL_SMALLER = "smaller";

    /**
     * css style value
     */
	public static final String VAL_ITALIC = "italic";

    /**
     * css style value
     */
	public static final String VAL_OBLIQUE = "oblique";

    /**
     * css style value
     */
	public static final String VAL_NORMAL = "normal";

    /**
     * css style value
     */
	public static final String VAL_BOLD = "bold";

    /**
     * css style value
     */
	public static final String VAL_BOLDER = "bolder";

    /**
     * css style value
     */
	public static final String VAL_LIGHTER = "lighter";

    /**
     * css style value
     */
	public static final String VAL_AUTO = "auto";

    /**
     * css style value
     */
	public static final String VAL_OUTSIDE = "outside";

    /**
     * css style value
     */
	public static final String VAL_INSIDE = "inside";

    /**
     * css style value
     */
	public static final String VAL_LEFT = "left";

    /**
     * css style value
     */
	public static final String VAL_RIGHT = "right";

    /**
     * css style value
     */
	public static final String VAL_CENTER = "center";

    /**
     * css style value
     */
	public static final String VAL_JUSTIFY = "justify";

    /**
     * css style value
     */
	public static final String VAL_UNDERLINE = "underline";

    /**
     * css style value
     */
	public static final String VAL_OVERLINE = "overline";

    /**
     * css style value
     */
	public static final String VAL_LINETHROUGH = "line-through";

    /**
     * css style value
     */
	public static final String VAL_BLINK = "blink";

    /**
     * css style value
     */
	public static final String VAL_PRE = "pre";

    /**
     * css style value
     */
	public static final String VAL_NOWRAP = "nowrap";

    /**
     * css style value
     */
	public final static String VAL_DISC = "disc"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_CIRCLE = "circle"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_DECIMAL = "decimal"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_CJK_IDEOGRAPHIC = "cjk-ideographic"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_DECIMAL_LEADING_ZERO = "decimal-leading-zero"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_ARMENIAN = "armenian"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_LOWER_ALPHA = "lower-alpha"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_LOWER_GREEK = "lower-greek"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_LOWER_LATIN = "lower-latin"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_LOWER_ROMAN = "lower-roman"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_UPPER_ALPHA = "upper-alpha"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_UPPER_LATIN = "upper-latin"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_UPPER_ROMAN = "upper-roman"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_HIRAGANA = "hiragana"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_HIRAGANA_IROHA = "hiragana-iroha"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_HEBREW = "hebrew"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_GEORGIAN = "georgian"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_KATAKANA = "katakana"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_KATAKANA_IROHA = "katakana-iroha"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_SQUARE = "square"; //$NON-NLS-1$

    /**
     * css style value
     */
	public final static String VAL_IMAGE = "image"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_MULTIPLE = "multiple";

    /**
     * css style value
     */
	public static final String VAL_BUTTON = "button";

    /**
     * css style value
     */
	public static final String VAL_CHECKBOX = "checkbox";

    /**
     * css style value
     */
	public static final String VAL_RADIO = "radio";

    /**
     * css style value
     */
	public static final String VAL_FILE = "file";

    /**
     * css style value
     */
	public static final String VAL_RESET = "reset";

    /**
     * css style value
     */
	public static final String VAL_SUBMIT = "submit";

    /**
     * css style value
     */
	public static final String VAL_PASSWORD = "password";

    /**
     * css style value
     */
	public static final String VAL_TEXT = "text";

    /**
     * css style value
     */
	public static final String VAL_VISIBLE = "visible";

    /**
     * css style value
     */
	public static final String VAL_SCROLL = "scroll";

    /**
     * css style value
     */
	public static final String TAG_TAGLIB = "taglib";

}
