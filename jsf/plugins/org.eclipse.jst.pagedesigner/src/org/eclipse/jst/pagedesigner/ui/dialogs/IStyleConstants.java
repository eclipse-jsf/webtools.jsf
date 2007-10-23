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
package org.eclipse.jst.pagedesigner.ui.dialogs;

/**
 * @author mengbo
 * @version 1.5
 */
public interface IStyleConstants {
	/**
	 * attachment constants
	 */
	final public static String[] ATTACHMENT = { "fixed", "scroll" };

	/**
	 * auto constants
	 */
	final public static String[] AUTO = { "auto" };

	/**
	 * border style constants
	 */
	final public static String[] BORDER_STYLE = { "none", "dotted", "dashed",
			"solid", "double", "groove", "ridge", "inset", "outset" };

	/**
	 * border width constants
	 */
	final public static String[] BORDER_WIDTH = { "thin", "medium", "thick" };

	/**
	 * clear constants
	 */
	final public static String[] CLEAR = { "left", "right", "both", "none" };

	/**
	 * color constants
	 */
	final public static String[] COLOR = { "black", "blue", "gray", "green",
			"orange", "red", "white", "yellow", "aqua", "fuchsia", "lime",
			"maroon", "navy", "olive", "purple", "silver", "teal" };

	/**
	 * display constants
	 */
	final public static String[] DISPLAY = { "none", "inline", "block",
			"list-item", "run-in", "compact", "marker", "table",
			"inline-table", "table-row-group", "table-header-group",
			"table-footer-group", "table-row", "table-column-group",
			"table-column", "table-cell", "table-caption" };

	/**
	 * float constants
	 */
	final public static String[] FLOAT = { "left", "right", "none" };

	/**
	 * font family constants
	 */
	final public static String[] FONT_FAMILY = { "Verdana, Arial, Sans-Serif",
			"Tahoma, Verdana, Arial, Sans-Serif", "\"Times New Roman\", Serif",
			"Georgia, \"Times New Roman\", Serif",
			"\"Book Antiqua\", \"Times New Roman\", Serif",
			"\"Comic Sans MS\", Sans-Serif", "\"Courier New\", Courier",
			"\"Trebuchet MS\", Sans-Serif" };

	/**
	 * font size constants
	 */
	final public static String[] FONT_SIZE_NUMBER = { "9", "10", "12", "14",
			"16", "18", "24", "36", "xx-small", "x-small", "small", "medium",
			"large", "x-large", "xx-large", "smaller", "larger" };

	/**
	 * font style constants
	 */
	final public static String[] FONT_STYLE = { "normal", "italic", "oblique" };

	/**
	 * font transform constants
	 */
	final public static String[] FONT_TEXTTRANSFORM = { "capitalize",
			"uppercase", "lowercase", "none" };

	/**
	 * font variant constants
	 */
	final public static String[] FONT_VARIANT = { "normal", "small-caps" };

	/**
	 * font weight constants
	 */
	final public static String[] FONT_WEIGHT = { "normal", "bold", "bolder",
			"lighter", "100", "200", "300", "400", "500", "600", "700", "800",
			"900" };

	/**
	 * list position constants
	 */
	final public static String[] LIST_POSITION = { "inside", "outside" };

	/**
	 * list type constants
	 */
	final public static String[] LIST_TYPE = { "disc", "circle", "sqaure",
			"decimal", "lower-roman", "upper-roman", "lower-alpha",
			"upper-alpah", "none" };

	/**
	 * none
	 */
	final public static String[] NONE = { "none" };

	/**
	 * normal
	 */
	final public static String[] NORMAL = { "normal" };

	/**
	 * percent
	 */
	final public static String[] PERCENT = { "%" };

	/**
	 * position constants
	 */
	final public static String[] POSITION = { "left", "center", "right" };

	/**
	 * positioning type constants
	 */
	final public static String[] POSITIONING_TYPE = { "absolute", "relative",
			"static" };

	/**
	 * repeat constants
	 */
	final public static String[] REPEAT = { "no-repeat", "repeat", "repeat-x",
			"repeat-y" };

	/**
	 * unit of size constants
	 */
	final public static String[] SIZE_UNIT = { "px", "pt", "in", "cm", "mm",
			"pc", "em", "ex", "%" };

	/**
	 * text align constants
	 */
	final public static String[] TEXT_ALIGN = { "left", "right", "center",
			"justify" };

	/**
	 * text decoration constants
	 */
	final public static String[] TEXT_DECORATION = { "underline", "overline",
			"line-through", "blink", "none" };

	/**
	 * veritical align constants
	 */
	final public static String[] VERTICAL_ALIGN = { "baseline", "sub", "super",
			"top", "text-top", "middle", "bottom", "text-bottom" };

	/**
	 * white space constants
	 */
	final public static String[] WHITE_SPACE = { "normal", "pre", "nowrap" };

	/**
	 * visibility constants
	 */
	final public static String[] VISIBILITY = { "inherit", "visible", "hidden" };

	/**
	 * overflow constants
	 */
	final public static String[] OVERFLOW = { "visible", "hidden", "scroll",
			"auto" };

	/**
	 * page break constants
	 */
	final public static String[] PAGE_BREAK = { "auto", "always", "left",
			"right" };

	/**
	 * cursor constants
	 */
	final public static String[] CURSOR = { "hand", "crosshair", "text",
			"wait", "default", "help", "e-resize", "ne-resize", "n-resize",
			"nw-resize", "w-resize", "sw-resize", "s-resize", "se-resize",
			"auto" };
}
