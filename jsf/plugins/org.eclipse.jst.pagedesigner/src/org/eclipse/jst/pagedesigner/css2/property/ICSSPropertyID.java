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
	public static final String ATTR_DISPLAY = "display"; //$NON-NLS-1$

	/**
	 * whitespace attribute name
	 */
	public static final String ATTR_WHITESPACE = "white-space"; //$NON-NLS-1$

	/**
	 * text decoration attribute name
	 */
	public static final String ATTR_TEXTDECORATION = "text-decoration"; //$NON-NLS-1$

	/**
	 * text align attribute name
	 */
	public static final String ATTR_TEXTALIGN = "text-align"; //$NON-NLS-1$

	/**
	 * width attribute name
	 */
	public static final String ATTR_WIDTH = "width"; //$NON-NLS-1$

	/**
	 * min-width attribute name
	 */
	public static final String ATTR_MIN_WIDTH = "min-width"; //$NON-NLS-1$

	/**
	 * height attribute name
	 */
	public static final String ATTR_HEIGHT = "height"; //$NON-NLS-1$

	/**
	 * min height attribute name
	 */
	public static final String ATTR_MIN_HEIGHT = "min-height"; //$NON-NLS-1$

	/**
	 * border-left-width attribute name
	 */
	public static final String ATTR_BORDER_LEFT_WIDTH = "border-left-width"; //$NON-NLS-1$

	/**
	 * border-right-width attribute name
	 */
	public static final String ATTR_BORDER_RIGHT_WIDTH = "border-right-width"; //$NON-NLS-1$

	/**
	 * border bottom width
	 */
	public static final String ATTR_BORDER_BOTTOM_WIDTH = "border-bottom-width"; //$NON-NLS-1$

	/**
	 * css style attribute
	 */
	public static final String ATTR_BORDER_TOP_WIDTH = "border-top-width"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_FONT_FAMILY = "font-family"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_FONT_SIZE = "font-size"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_FONT_STYLE = "font-style"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_FONT_WEIGHT = "font-weight"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_LEFT_STYLE = "border-left-style"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_RIGHT_STYLE = "border-right-style"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_BOTTOM_STYLE = "border-bottom-style"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_TOP_STYLE = "border-top-style"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_LEFT_COLOR = "border-left-color"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_RIGHT_COLOR = "border-right-color"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_BOTTOM_COLOR = "border-bottom-color"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_TOP_COLOR = "border-top-color"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_PADDING_RIGHT = "padding-right"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_PADDING_BOTTOM = "padding-bottom"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_PADDING_LEFT = "padding-left"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_PADDING_TOP = "padding-top"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_MARGIN_RIGHT = "margin-right"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_MARGIN_BOTTOM = "margin-bottom"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_MARGIN_LEFT = "margin-left"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_MARGIN_TOP = "margin-top"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_BACKGROUND_COLOR = "background-color"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_BACKGROUND_IMAGE = "background-image"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_COLOR = "color"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_TEXTCOLOR = "text"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_COLLAPSE = "border-collapse"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_BORDER_SPACING = "border-spacing"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_LIST_STYLE_TYPE = "list-style-type"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_LIST_STYLE_POSITION = "list-style-position"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_LIST_STYLE_IMAGE = "list-style-image"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_COUNTER_RESET = "counter-reset"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_COUNTER_INCREMENT = "counter-increment"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_EMPTY_CELLS = "empty-cells"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_VISIBILITY = "visibility"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_VERTICAL_ALIGN = "vertical-align"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_HORIZONTAL_ALIGN = "horizontal-align"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_CONTENT = "content"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_BGCOLOR = "bgcolor"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_TEXT = "text"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public final static String ATTR_MULTIPLE = "multiple"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public final static String ATTR_ITEMLABEL = "itemLabel"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public final static String ATTR_BINDING = "binding"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public final static String ATTR_ITEMVALUE = "itemValue"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public final static String ATTR_VALUE = "value"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_URI = "uri"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_PREFIX = "prefix"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_STYLECLASS = "styleClass"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_SIZE = "size"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_BASEFONT = "basefont"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_ROWSPAN = "rowSpan"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_COLSPAN = "colSpan"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_TYPE = "type"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_URL = "url"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_SRC = "src"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_TAGLIB_LOCATION = "taglib-location"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_TAGLIB_URI = "taglib-uri"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_IMAGE = "image"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_RESET = "reset"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_FOOTER = "footer"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_HEADER = "header"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_COLUMNS = "columns"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_COLUMNCLASSES = "columnClasses"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_CLASS = "class"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_STYLE = "style"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_PAGE = "page"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_HREF = "href"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_NAME = "name"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_BASENAME = "basename"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_ACTION = "action"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_TARGET = "target"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_TOP = "top"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_BOTTOM = "bottom"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_LEFT = "left"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_RIGHT = "right"; //$NON-NLS-1$

    /**
     * css style attribute
     */
	public static final String ATTR_POSITION = "position"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_TRANSPARENT = "transparent"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_COLLAPSE = "collapse"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_SEPARATE = "separate"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String ATTR_OVERFLOW = "overflow"; //$NON-NLS-1$

    /**
     * css style value
     */
	// border style values
	public static final String VAL_HIDDEN = "hidden"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_DOTTED = "dotted"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_DASHED = "dashed"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_SOLID = "solid"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_DOUBLE = "double"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_GROOVE = "groove"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_RIDGE = "ridge"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_INSET = "inset"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_OUTSET = "outset"; //$NON-NLS-1$

	// XXX: we introduce a new style for TD default border style, since it
	// seemed IE
	// is using none of the above style for td.
    /**
     * css style value
     */
	public static final String VAL_TDBORDERSTYLE = "__td_border_style__"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_THIN = "thin"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_THICK = "thick"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_INLINE = "inline"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_BLOCK = "block"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_INLINE_BLOCK = "inline-block"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_LIST_ITEM = "list-item"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_RUN_IN = "run-in"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_COMPACT = "compact"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_MARKER = "marker"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_TABLE = "table"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_INLINE_TABLE = "inline-table"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_TABLE_ROW_GROUP = "table-row-group"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_TABLE_HEADER_GROUP = "table-header-group"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_TABLE_FOOTER_GROUP = "table-footer-group"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_TABLE_ROW = "table-row"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_TABLE_COLUMN_GROUP = "table-column-group"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_TABLE_COLUMN = "table-column"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_TABLE_CELL = "table-cell"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_TABLE_CAPTION = "table-caption"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_NONE = "none"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_XX_SMALL = "xx-small"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_X_SMALL = "x-small"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_SMALL = "small"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_MEDIUM = "medium"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_LARGE = "large"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_X_LARGE = "x-large"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_XX_LARGE = "xx-large"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_LARGER = "larger"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_SMALLER = "smaller"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_ITALIC = "italic"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_OBLIQUE = "oblique"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_NORMAL = "normal"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_BOLD = "bold"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_BOLDER = "bolder"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_LIGHTER = "lighter"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_AUTO = "auto"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_OUTSIDE = "outside"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_INSIDE = "inside"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_LEFT = "left"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_RIGHT = "right"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_CENTER = "center"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_JUSTIFY = "justify"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_UNDERLINE = "underline"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_OVERLINE = "overline"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_LINETHROUGH = "line-through"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_BLINK = "blink"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_PRE = "pre"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_NOWRAP = "nowrap"; //$NON-NLS-1$

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
	public static final String VAL_MULTIPLE = "multiple"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_BUTTON = "button"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_CHECKBOX = "checkbox"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_RADIO = "radio"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_FILE = "file"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_RESET = "reset"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_SUBMIT = "submit"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_PASSWORD = "password"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_TEXT = "text"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_VISIBLE = "visible"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String VAL_SCROLL = "scroll"; //$NON-NLS-1$

    /**
     * css style value
     */
	public static final String TAG_TAGLIB = "taglib"; //$NON-NLS-1$

}
