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
	final public static String[] ATTACHMENT = { "fixed", "scroll" }; //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * auto constants
	 */
	final public static String[] AUTO = { "auto" }; //$NON-NLS-1$

	/**
	 * border style constants
	 */
	final public static String[] BORDER_STYLE = { "none", "dotted", "dashed", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			"solid", "double", "groove", "ridge", "inset", "outset" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

	/**
	 * border width constants
	 */
	final public static String[] BORDER_WIDTH = { "thin", "medium", "thick" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	/**
	 * clear constants
	 */
	final public static String[] CLEAR = { "left", "right", "both", "none" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

	/**
	 * color constants
	 */
	final public static String[] COLOR = { "black", "blue", "gray", "green", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			"orange", "red", "white", "yellow", "aqua", "fuchsia", "lime", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
			"maroon", "navy", "olive", "purple", "silver", "teal" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

	/**
	 * display constants
	 */
	final public static String[] DISPLAY = { "none", "inline", "block", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			"list-item", "run-in", "compact", "marker", "table", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			"inline-table", "table-row-group", "table-header-group", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			"table-footer-group", "table-row", "table-column-group", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			"table-column", "table-cell", "table-caption" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	/**
	 * float constants
	 */
	final public static String[] FLOAT = { "left", "right", "none" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	/**
	 * font family constants
	 */
	final public static String[] FONT_FAMILY = { "Verdana, Arial, Sans-Serif", //$NON-NLS-1$
			"Tahoma, Verdana, Arial, Sans-Serif", "\"Times New Roman\", Serif", //$NON-NLS-1$ //$NON-NLS-2$
			"Georgia, \"Times New Roman\", Serif", //$NON-NLS-1$
			"\"Book Antiqua\", \"Times New Roman\", Serif", //$NON-NLS-1$
			"\"Comic Sans MS\", Sans-Serif", "\"Courier New\", Courier", //$NON-NLS-1$ //$NON-NLS-2$
			"\"Trebuchet MS\", Sans-Serif" }; //$NON-NLS-1$

	/**
	 * font size constants
	 */
	final public static String[] FONT_SIZE_NUMBER = { "9", "10", "12", "14", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			"16", "18", "24", "36", "xx-small", "x-small", "small", "medium", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"large", "x-large", "xx-large", "smaller", "larger" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

	/**
	 * font style constants
	 */
	final public static String[] FONT_STYLE = { "normal", "italic", "oblique" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	/**
	 * font transform constants
	 */
	final public static String[] FONT_TEXTTRANSFORM = { "capitalize", //$NON-NLS-1$
			"uppercase", "lowercase", "none" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	/**
	 * font variant constants
	 */
	final public static String[] FONT_VARIANT = { "normal", "small-caps" }; //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * font weight constants
	 */
	final public static String[] FONT_WEIGHT = { "normal", "bold", "bolder", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			"lighter", "100", "200", "300", "400", "500", "600", "700", "800", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
			"900" }; //$NON-NLS-1$

	/**
	 * list position constants
	 */
	final public static String[] LIST_POSITION = { "inside", "outside" }; //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * list type constants
	 */
	final public static String[] LIST_TYPE = { "disc", "circle", "sqaure", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			"decimal", "lower-roman", "upper-roman", "lower-alpha", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			"upper-alpah", "none" }; //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * none
	 */
	final public static String[] NONE = { "none" }; //$NON-NLS-1$

	/**
	 * normal
	 */
	final public static String[] NORMAL = { "normal" }; //$NON-NLS-1$

	/**
	 * percent
	 */
	final public static String[] PERCENT = { "%" }; //$NON-NLS-1$

	/**
	 * position constants
	 */
	final public static String[] POSITION = { "left", "center", "right" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	/**
	 * positioning type constants
	 */
	final public static String[] POSITIONING_TYPE = { "absolute", "relative", //$NON-NLS-1$ //$NON-NLS-2$
			"static" }; //$NON-NLS-1$

	/**
	 * repeat constants
	 */
	final public static String[] REPEAT = { "no-repeat", "repeat", "repeat-x", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			"repeat-y" }; //$NON-NLS-1$

	/**
	 * unit of size constants
	 */
	final public static String[] SIZE_UNIT = { "px", "pt", "in", "cm", "mm", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			"pc", "em", "ex", "%" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

	/**
	 * text align constants
	 */
	final public static String[] TEXT_ALIGN = { "left", "right", "center", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			"justify" }; //$NON-NLS-1$

	/**
	 * text decoration constants
	 */
	final public static String[] TEXT_DECORATION = { "underline", "overline", //$NON-NLS-1$ //$NON-NLS-2$
			"line-through", "blink", "none" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	/**
	 * veritical align constants
	 */
	final public static String[] VERTICAL_ALIGN = { "baseline", "sub", "super", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			"top", "text-top", "middle", "bottom", "text-bottom" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

	/**
	 * white space constants
	 */
	final public static String[] WHITE_SPACE = { "normal", "pre", "nowrap" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	/**
	 * visibility constants
	 */
	final public static String[] VISIBILITY = { "inherit", "visible", "hidden" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	/**
	 * overflow constants
	 */
	final public static String[] OVERFLOW = { "visible", "hidden", "scroll", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			"auto" }; //$NON-NLS-1$

	/**
	 * page break constants
	 */
	final public static String[] PAGE_BREAK = { "auto", "always", "left", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			"right" }; //$NON-NLS-1$

	/**
	 * cursor constants
	 */
	final public static String[] CURSOR = { "hand", "crosshair", "text", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			"wait", "default", "help", "e-resize", "ne-resize", "n-resize", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
			"nw-resize", "w-resize", "sw-resize", "s-resize", "se-resize", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			"auto" }; //$NON-NLS-1$
}
