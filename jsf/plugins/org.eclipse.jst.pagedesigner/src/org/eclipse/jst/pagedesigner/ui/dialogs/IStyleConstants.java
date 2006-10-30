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
	final public static String[] ATTACHMENT = { "fixed", "scroll" };

	final public static String[] AUTO = { "auto" };

	final public static String[] BORDER_STYLE = { "none", "dotted", "dashed",
			"solid", "double", "groove", "ridge", "inset", "outset" };

	final public static String[] BORDER_WIDTH = { "thin", "medium", "thick" };

	final public static String[] CLEAR = { "left", "right", "both", "none" };

	final public static String[] COLOR = { "black", "blue", "gray", "green",
			"orange", "red", "white", "yellow", "aqua", "fuchsia", "lime",
			"maroon", "navy", "olive", "purple", "silver", "teal" };

	final public static String[] DISPLAY = { "none", "inline", "block",
			"list-item", "run-in", "compact", "marker", "table",
			"inline-table", "table-row-group", "table-header-group",
			"table-footer-group", "table-row", "table-column-group",
			"table-column", "table-cell", "table-caption" };

	final public static String[] FLOAT = { "left", "right", "none" };

	final public static String[] FONT_FAMILY = { "Verdana, Arial, Sans-Serif",
			"Tahoma, Verdana, Arial, Sans-Serif", "\"Times New Roman\", Serif",
			"Georgia, \"Times New Roman\", Serif",
			"\"Book Antiqua\", \"Times New Roman\", Serif",
			"\"Comic Sans MS\", Sans-Serif", "\"Courier New\", Courier",
			"\"Trebuchet MS\", Sans-Serif" };

	final public static String[] FONT_SIZE_NUMBER = { "9", "10", "12", "14",
			"16", "18", "24", "36", "xx-small", "x-small", "small", "medium",
			"large", "x-large", "xx-large", "smaller", "larger" };

	final public static String[] FONT_STYLE = { "normal", "italic", "oblique" };

	final public static String[] FONT_TEXTTRANSFORM = { "capitalize",
			"uppercase", "lowercase", "none" };

	final public static String[] FONT_VARIANT = { "normal", "small-caps" };

	final public static String[] FONT_WEIGHT = { "normal", "bold", "bolder",
			"lighter", "100", "200", "300", "400", "500", "600", "700", "800",
			"900" };

	final public static String[] LIST_POSITION = { "inside", "outside" };

	final public static String[] LIST_TYPE = { "disc", "circle", "sqaure",
			"decimal", "lower-roman", "upper-roman", "lower-alpha",
			"upper-alpah", "none" };

	final public static String[] NONE = { "none" };

	final public static String[] NORMAL = { "normal" };

	final public static String[] PERCENT = { "%" };

	final public static String[] POSITION = { "left", "center", "right" };

	final public static String[] POSITIONING_TYPE = { "absolute", "relative",
			"static" };

	final public static String[] REPEAT = { "no-repeat", "repeat", "repeat-x",
			"repeat-y" };

	final public static String[] SIZE_UNIT = { "px", "pt", "in", "cm", "mm",
			"pc", "em", "ex", "%" };

	final public static String[] TEXT_ALIGN = { "left", "right", "center",
			"justify" };

	final public static String[] TEXT_DECORATION = { "underline", "overline",
			"line-through", "blink", "none" };

	final public static String[] VERTICAL_ALIGN = { "baseline", "sub", "super",
			"top", "text-top", "middle", "bottom", "text-bottom" };

	final public static String[] WHITE_SPACE = { "normal", "pre", "nowrap" };

	final public static String[] VISIBILITY = { "inherit", "visible", "hidden" };

	final public static String[] OVERFLOW = { "visible", "hidden", "scroll",
			"auto" };

	final public static String[] PAGE_BREAK = { "auto", "always", "left",
			"right" };

	final public static String[] CURSOR = { "hand", "crosshair", "text",
			"wait", "default", "help", "e-resize", "ne-resize", "n-resize",
			"nw-resize", "w-resize", "sw-resize", "s-resize", "se-resize",
			"auto" };
}
