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
package org.eclipse.jst.pagedesigner.css2.color;

import java.util.HashMap;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * @author mengbo
 */
public class CSSColorDefaults {
	static final HashMap SYSTEM_DEFAULT_COLORS = new HashMap(20);

	static final HashMap EXTENDED_COLORS = new HashMap(20);

    // TODO C.B.: there is overlap here between the default and extended
    // colors.  Also, for the custom colors, should use a ColorRegistry
    // that allows other components to share values here
	static {
		SYSTEM_DEFAULT_COLORS.put("black", ColorConstants.black); //$NON-NLS-1$
		SYSTEM_DEFAULT_COLORS.put("blue", ColorConstants.blue); //$NON-NLS-1$
		SYSTEM_DEFAULT_COLORS.put("gray", ColorConstants.gray); //$NON-NLS-1$
		SYSTEM_DEFAULT_COLORS.put("green", new Color(null, 0, 128, 0)); //$NON-NLS-1$
		SYSTEM_DEFAULT_COLORS.put("orange", ColorConstants.orange); //$NON-NLS-1$
		SYSTEM_DEFAULT_COLORS.put("red", ColorConstants.red); //$NON-NLS-1$
		SYSTEM_DEFAULT_COLORS.put("white", ColorConstants.white); //$NON-NLS-1$
		SYSTEM_DEFAULT_COLORS.put("yellow", ColorConstants.yellow); //$NON-NLS-1$
		SYSTEM_DEFAULT_COLORS.put("aqua", ColorConstants.cyan); //$NON-NLS-1$
		SYSTEM_DEFAULT_COLORS.put("fuchsia", new Color(null, 255, 0, 255)); //$NON-NLS-1$
		SYSTEM_DEFAULT_COLORS.put("lime", ColorConstants.green); //$NON-NLS-1$
		SYSTEM_DEFAULT_COLORS.put("maroon", new Color(null, 128, 0, 0)); //$NON-NLS-1$
		SYSTEM_DEFAULT_COLORS.put("navy", new Color(null, 0, 0, 128)); //$NON-NLS-1$
		SYSTEM_DEFAULT_COLORS.put("olive", new Color(null, 128, 128, 0)); //$NON-NLS-1$
		SYSTEM_DEFAULT_COLORS.put("purple", new Color(null, 128, 0, 128)); //$NON-NLS-1$
		SYSTEM_DEFAULT_COLORS.put("silver", ColorConstants.lightGray); //$NON-NLS-1$
		SYSTEM_DEFAULT_COLORS.put("teal", new Color(null, 0, 128, 128)); //$NON-NLS-1$

		SYSTEM_DEFAULT_COLORS.put("activeborder", Display.getCurrent() //$NON-NLS-1$
				.getSystemColor(SWT.COLOR_WIDGET_BORDER));
		// Active window border.
		SYSTEM_DEFAULT_COLORS.put("activecaption", //$NON-NLS-1$
				ColorConstants.titleBackground);
		// Active window caption.
		SYSTEM_DEFAULT_COLORS
				.put("appworkspace", ColorConstants.listBackground); //$NON-NLS-1$
		// Background color of multiple document interface.
		SYSTEM_DEFAULT_COLORS.put("background", ColorConstants.listBackground); //$NON-NLS-1$
		// Desktop background.
		SYSTEM_DEFAULT_COLORS.put("buttonface", ColorConstants.button); //$NON-NLS-1$
		// Face color for three-dimensional display elements.
		SYSTEM_DEFAULT_COLORS.put("buttonhighlight", //$NON-NLS-1$
				ColorConstants.buttonLightest);
		// Dark shadow for three-dimensional display elements (for edges facing
		// away from the light source).
		SYSTEM_DEFAULT_COLORS.put("buttonshadow", ColorConstants.buttonDarker); //$NON-NLS-1$
		// Shadow color for three-dimensional display elements.
		SYSTEM_DEFAULT_COLORS.put("buttontext", Display.getCurrent() //$NON-NLS-1$
				.getSystemColor(SWT.COLOR_WIDGET_FOREGROUND));
		// Text on push buttons.
		SYSTEM_DEFAULT_COLORS
				.put("captiontext", ColorConstants.titleForeground); //$NON-NLS-1$
		// Text in caption, size box, and scrollbar arrow box.
		SYSTEM_DEFAULT_COLORS.put("graytext", //$NON-NLS-1$
				ColorConstants.titleInactiveForeground);
		// Grayed (disabled) text. This color is set to #000 if the current
		// display driver does not support a solid gray color.
		SYSTEM_DEFAULT_COLORS.put("highlight", //$NON-NLS-1$
				ColorConstants.menuBackgroundSelected);
		// Item(s) selected in a control.
		SYSTEM_DEFAULT_COLORS.put("highlighttext", //$NON-NLS-1$
				ColorConstants.menuForegroundSelected);
		// Text of item(s) selected in a control.
		SYSTEM_DEFAULT_COLORS.put("inactiveborder", Display.getCurrent() //$NON-NLS-1$
				.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		// Inactive window border.
		SYSTEM_DEFAULT_COLORS.put("inactivecaption", //$NON-NLS-1$
				ColorConstants.titleInactiveBackground);
		// Inactive window caption.
		SYSTEM_DEFAULT_COLORS.put("inactivecaptiontext", //$NON-NLS-1$
				ColorConstants.titleInactiveForeground);
		// Color of text in an inactive caption.
		SYSTEM_DEFAULT_COLORS.put("infobackground", //$NON-NLS-1$
				ColorConstants.tooltipBackground);
		// Background color for tooltip controls.
		SYSTEM_DEFAULT_COLORS.put("infotext", ColorConstants.tooltipForeground); //$NON-NLS-1$
		// Text color for tooltip controls.
		SYSTEM_DEFAULT_COLORS.put("menu", ColorConstants.menuBackground); //$NON-NLS-1$
		// Menu background.
		SYSTEM_DEFAULT_COLORS.put("menutext", ColorConstants.menuForeground); //$NON-NLS-1$
		// Text in menus.
		SYSTEM_DEFAULT_COLORS.put("scrollbar", Display.getCurrent() //$NON-NLS-1$
				.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		// Scroll bar gray area.
		SYSTEM_DEFAULT_COLORS.put("threeddarkshadow", Display.getCurrent() //$NON-NLS-1$
				.getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		// Dark shadow for three-dimensional display elements.
		SYSTEM_DEFAULT_COLORS.put("threedface", Display.getCurrent() //$NON-NLS-1$
				.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		// Face color for three-dimensional display elements.
		SYSTEM_DEFAULT_COLORS.put("threedhighlight", Display.getCurrent() //$NON-NLS-1$
				.getSystemColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		// Highlight color for three-dimensional display elements.
		SYSTEM_DEFAULT_COLORS.put("threedlightshadow", Display.getCurrent() //$NON-NLS-1$
				.getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		// Light color for three-dimensional display elements (for edges facing
		// the light source).
		SYSTEM_DEFAULT_COLORS.put("threedshadow", Display.getCurrent() //$NON-NLS-1$
				.getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		// Dark shadow for three-dimensional display elements.
		SYSTEM_DEFAULT_COLORS.put("window", Display.getCurrent() //$NON-NLS-1$
				.getSystemColor(SWT.COLOR_LIST_BACKGROUND));
		// Window background.
		SYSTEM_DEFAULT_COLORS.put("windowframe", Display.getCurrent() //$NON-NLS-1$
				.getSystemColor(SWT.COLOR_WIDGET_BORDER));
		// Window frame.
		SYSTEM_DEFAULT_COLORS.put("windowtext", Display.getCurrent() //$NON-NLS-1$
				.getSystemColor(SWT.COLOR_LIST_FOREGROUND));
		// Window text
		SYSTEM_DEFAULT_COLORS.put("hyperlink", ColorConstants.blue); //$NON-NLS-1$
	}

    // populate the extended color palette.  Where the extended 
    // color is the same as a constant color, use that to save
    // on SWT resource handles
	static {
		EXTENDED_COLORS.put("aliceblue", new Color(null, 240, 248, 255)); //$NON-NLS-1$
		EXTENDED_COLORS.put("antiquewhite", new Color(null, 250, 235, 215)); //$NON-NLS-1$
		EXTENDED_COLORS.put("aqua", ColorConstants.cyan); //$NON-NLS-1$
		EXTENDED_COLORS.put("aquamarine", new Color(null, 127, 255, 212)); //$NON-NLS-1$
		EXTENDED_COLORS.put("azure", new Color(null, 240, 255, 255)); //$NON-NLS-1$
		EXTENDED_COLORS.put("beige", new Color(null, 245, 245, 220)); //$NON-NLS-1$
		EXTENDED_COLORS.put("bisque", new Color(null, 255, 228, 196)); //$NON-NLS-1$
		EXTENDED_COLORS.put("black", ColorConstants.black); //$NON-NLS-1$
		EXTENDED_COLORS.put("blanchedalmond", new Color(null, 255, 235, 205)); //$NON-NLS-1$
		EXTENDED_COLORS.put("blue", ColorConstants.blue); //$NON-NLS-1$
		EXTENDED_COLORS.put("blueviolet", new Color(null, 138, 43, 226)); //$NON-NLS-1$
		EXTENDED_COLORS.put("brown", new Color(null, 165, 42, 42)); //$NON-NLS-1$
		EXTENDED_COLORS.put("burlywood", new Color(null, 222, 184, 135)); //$NON-NLS-1$
		EXTENDED_COLORS.put("cadetblue", new Color(null, 95, 158, 160)); //$NON-NLS-1$
		EXTENDED_COLORS.put("chartreuse", new Color(null, 127, 255, 0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("chocolate", new Color(null, 210, 105, 30)); //$NON-NLS-1$
		EXTENDED_COLORS.put("coral", new Color(null, 255, 127, 80)); //$NON-NLS-1$
		EXTENDED_COLORS.put("cornflowerblue", new Color(null, 100, 149, 237)); //$NON-NLS-1$
		EXTENDED_COLORS.put("cornsilk", new Color(null, 255, 248, 220)); //$NON-NLS-1$
		EXTENDED_COLORS.put("crimson", new Color(null, 220, 20, 60)); //$NON-NLS-1$
		EXTENDED_COLORS.put("cyan", new Color(null, 0, 255, 255)); //$NON-NLS-1$
		EXTENDED_COLORS.put("darkblue", new Color(null, 0, 0, 139)); //$NON-NLS-1$
		EXTENDED_COLORS.put("darkcyan", new Color(null, 0, 139, 139)); //$NON-NLS-1$
		EXTENDED_COLORS.put("darkgoldenrod", new Color(null, 184, 134, 11)); //$NON-NLS-1$
		EXTENDED_COLORS.put("darkgray", new Color(null, 169, 169, 169)); //$NON-NLS-1$
		EXTENDED_COLORS.put("darkgreen", new Color(null, 0, 100, 0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("darkkhaki", new Color(null, 189, 183, 107)); //$NON-NLS-1$
		EXTENDED_COLORS.put("darkmagenta", new Color(null, 139, 0, 139)); //$NON-NLS-1$
		EXTENDED_COLORS.put("darkolivegreen", new Color(null, 85, 107, 47)); //$NON-NLS-1$
		EXTENDED_COLORS.put("darkorange", new Color(null, 255, 140, 0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("darkorchid", new Color(null, 153, 50, 204)); //$NON-NLS-1$
		EXTENDED_COLORS.put("darkred", new Color(null, 139, 0, 0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("darksalmon", new Color(null, 233, 150, 122)); //$NON-NLS-1$
		EXTENDED_COLORS.put("darkseagreen", new Color(null, 143, 188, 143)); //$NON-NLS-1$
		EXTENDED_COLORS.put("darkslateblue", new Color(null, 72, 61, 139)); //$NON-NLS-1$
		EXTENDED_COLORS.put("darkslategray", new Color(null, 47, 79, 79)); //$NON-NLS-1$
		EXTENDED_COLORS.put("darkturquoise", new Color(null, 0, 206, 209)); //$NON-NLS-1$
		EXTENDED_COLORS.put("darkviolet", new Color(null, 148, 0, 211)); //$NON-NLS-1$
		EXTENDED_COLORS.put("deeppink", new Color(null, 255, 20, 147)); //$NON-NLS-1$
		EXTENDED_COLORS.put("deepskyblue", new Color(null, 0, 191, 255)); //$NON-NLS-1$
		EXTENDED_COLORS.put("dimgray", new Color(null, 105, 105, 105)); //$NON-NLS-1$
		EXTENDED_COLORS.put("dodgerblue", new Color(null, 30, 144, 255)); //$NON-NLS-1$
		EXTENDED_COLORS.put("feldspar", new Color(null, 209, 146, 117)); //$NON-NLS-1$
		EXTENDED_COLORS.put("firebrick", new Color(null, 178, 34, 34)); //$NON-NLS-1$
		EXTENDED_COLORS.put("floralwhite", new Color(null, 255, 250, 240)); //$NON-NLS-1$
		EXTENDED_COLORS.put("forestgreen", new Color(null, 34, 139, 34)); //$NON-NLS-1$
		EXTENDED_COLORS.put("fuchsia", new Color(null, 255, 0, 255)); //$NON-NLS-1$
		EXTENDED_COLORS.put("gainsboro", new Color(null, 220, 220, 220)); //$NON-NLS-1$
		EXTENDED_COLORS.put("ghostwhite", new Color(null, 248, 248, 255)); //$NON-NLS-1$
		EXTENDED_COLORS.put("gold", new Color(null, 255, 215, 0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("goldenrod", new Color(null, 218, 165, 32)); //$NON-NLS-1$
		EXTENDED_COLORS.put("gray", ColorConstants.gray); //$NON-NLS-1$
		EXTENDED_COLORS.put("green", new Color(null, 0, 128, 0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("greenyellow", new Color(null, 173, 255, 47)); //$NON-NLS-1$
		EXTENDED_COLORS.put("honeydew", new Color(null, 240, 255, 240)); //$NON-NLS-1$
		EXTENDED_COLORS.put("hotpink", new Color(null, 255, 105, 180)); //$NON-NLS-1$
		EXTENDED_COLORS.put("indianred", new Color(null, 205, 92, 92)); //$NON-NLS-1$
		EXTENDED_COLORS.put("indigo", new Color(null, 75, 0, 130)); //$NON-NLS-1$
		EXTENDED_COLORS.put("ivory", new Color(null, 255, 255, 240)); //$NON-NLS-1$
		EXTENDED_COLORS.put("khaki", new Color(null, 240, 230, 140)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lavender", new Color(null, 230, 230, 250)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lavenderblush", new Color(null, 255, 240, 245)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lawngreen", new Color(null, 124, 252, 0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lemonchiffon", new Color(null, 255, 250, 205)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lightblue", new Color(null, 173, 216, 230)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lightcoral", new Color(null, 240, 128, 128)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lightcyan", new Color(null, 224, 255, 255)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lightgoldenrodyellow", new Color(null, 250, 250, //$NON-NLS-1$
				210));
		EXTENDED_COLORS.put("lightgrey", new Color(null, 211, 211, 211)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lightgreen", new Color(null, 144, 238, 144)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lightpink", new Color(null, 255, 182, 193)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lightsalmon", new Color(null, 255, 160, 122)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lightseagreen", new Color(null, 32, 178, 170)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lightskyblue", new Color(null, 135, 206, 250)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lightslateblue", new Color(null, 132, 112, 255)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lightslategray", new Color(null, 119, 136, 153)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lightsteelblue", new Color(null, 176, 196, 222)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lightyellow", new Color(null, 255, 255, 224)); //$NON-NLS-1$
		EXTENDED_COLORS.put("lime", ColorConstants.green); //$NON-NLS-1$
		EXTENDED_COLORS.put("limegreen", new Color(null, 50, 205, 50)); //$NON-NLS-1$
		EXTENDED_COLORS.put("linen", new Color(null, 250, 240, 230)); //$NON-NLS-1$
		EXTENDED_COLORS.put("magenta", new Color(null, 255, 0, 255)); //$NON-NLS-1$
		EXTENDED_COLORS.put("maroon", new Color(null, 128, 0, 0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("mediumaquamarine", new Color(null, 102, 205, 170)); //$NON-NLS-1$
		EXTENDED_COLORS.put("mediumblue", new Color(null, 0, 0, 205)); //$NON-NLS-1$
		EXTENDED_COLORS.put("mediumorchid", new Color(null, 186, 85, 211)); //$NON-NLS-1$
		EXTENDED_COLORS.put("mediumpurple", new Color(null, 147, 112, 216)); //$NON-NLS-1$
		EXTENDED_COLORS.put("mediumseagreen", new Color(null, 60, 179, 113)); //$NON-NLS-1$
		EXTENDED_COLORS.put("mediumslateblue", new Color(null, 123, 104, 238)); //$NON-NLS-1$
		EXTENDED_COLORS.put("mediumspringgreen", new Color(null, 0, 250, 154)); //$NON-NLS-1$
		EXTENDED_COLORS.put("mediumturquoise", new Color(null, 72, 209, 204)); //$NON-NLS-1$
		EXTENDED_COLORS.put("mediumvioletred", new Color(null, 199, 21, 133)); //$NON-NLS-1$
		EXTENDED_COLORS.put("midnightblue", new Color(null, 25, 25, 112)); //$NON-NLS-1$
		EXTENDED_COLORS.put("mintcream", new Color(null, 245, 255, 250)); //$NON-NLS-1$
		EXTENDED_COLORS.put("mistyrose", new Color(null, 255, 228, 225)); //$NON-NLS-1$
		EXTENDED_COLORS.put("moccasin", new Color(null, 255, 228, 181)); //$NON-NLS-1$
		EXTENDED_COLORS.put("navajowhite", new Color(null, 255, 222, 173)); //$NON-NLS-1$
		EXTENDED_COLORS.put("navy", new Color(null, 0, 0, 128)); //$NON-NLS-1$
		EXTENDED_COLORS.put("oldlace", new Color(null, 253, 245, 230)); //$NON-NLS-1$
		EXTENDED_COLORS.put("olive", new Color(null, 128, 128, 0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("olivedrab", new Color(null, 107, 142, 35)); //$NON-NLS-1$
		EXTENDED_COLORS.put("orange", new Color(null, 255, 165, 0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("orangeted", new Color(null, 255, 69, 0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("orchid", new Color(null, 218, 112, 214)); //$NON-NLS-1$
		EXTENDED_COLORS.put("ralegoldenrod", new Color(null, 238, 232, 170)); //$NON-NLS-1$
		EXTENDED_COLORS.put("palegreen", new Color(null, 152, 251, 152)); //$NON-NLS-1$
		EXTENDED_COLORS.put("paleturquoise", new Color(null, 175, 238, 238)); //$NON-NLS-1$
		EXTENDED_COLORS.put("palevioletred", new Color(null, 216, 112, 147)); //$NON-NLS-1$
		EXTENDED_COLORS.put("papayawhip", new Color(null, 255, 239, 213)); //$NON-NLS-1$
		EXTENDED_COLORS.put("peachpuff", new Color(null, 255, 218, 185)); //$NON-NLS-1$
		EXTENDED_COLORS.put("peru", new Color(null, 205, 133, 63)); //$NON-NLS-1$
		EXTENDED_COLORS.put("pink", new Color(null, 255, 192, 203)); //$NON-NLS-1$
		EXTENDED_COLORS.put("plum", new Color(null, 221, 160, 221)); //$NON-NLS-1$
		EXTENDED_COLORS.put("powderblue", new Color(null, 176, 224, 230)); //$NON-NLS-1$
		EXTENDED_COLORS.put("purple", new Color(null, 128, 0, 128)); //$NON-NLS-1$
		EXTENDED_COLORS.put("red", ColorConstants.red); //$NON-NLS-1$
		EXTENDED_COLORS.put("rosybrown", new Color(null, 188, 143, 143)); //$NON-NLS-1$
		EXTENDED_COLORS.put("royalblue", new Color(null, 65, 105, 225)); //$NON-NLS-1$
		EXTENDED_COLORS.put("saddlebrown", new Color(null, 139, 69, 19)); //$NON-NLS-1$
		EXTENDED_COLORS.put("salmon", new Color(null, 250, 128, 114)); //$NON-NLS-1$
		EXTENDED_COLORS.put("sandybrown", new Color(null, 244, 164, 96)); //$NON-NLS-1$
		EXTENDED_COLORS.put("seagreen", new Color(null, 46, 139, 87)); //$NON-NLS-1$
		EXTENDED_COLORS.put("seashell", new Color(null, 255, 245, 238)); //$NON-NLS-1$
		EXTENDED_COLORS.put("sienna", new Color(null, 160, 82, 45)); //$NON-NLS-1$
		EXTENDED_COLORS.put("silver", new Color(null, 192, 192, 192)); //$NON-NLS-1$
		EXTENDED_COLORS.put("skyblue", new Color(null, 135, 206, 235)); //$NON-NLS-1$
		EXTENDED_COLORS.put("slateblue", new Color(null, 106, 90, 205)); //$NON-NLS-1$
		EXTENDED_COLORS.put("slategray", new Color(null, 112, 128, 144)); //$NON-NLS-1$
		EXTENDED_COLORS.put("snow", new Color(null, 255, 250, 250)); //$NON-NLS-1$
		EXTENDED_COLORS.put("springgreen", new Color(null, 0, 255, 127)); //$NON-NLS-1$
		EXTENDED_COLORS.put("steelblue", new Color(null, 70, 130, 180)); //$NON-NLS-1$
		EXTENDED_COLORS.put("tan", new Color(null, 210, 180, 140)); //$NON-NLS-1$
		EXTENDED_COLORS.put("teal", new Color(null, 0, 128, 128)); //$NON-NLS-1$
		EXTENDED_COLORS.put("thistle", new Color(null, 216, 191, 216)); //$NON-NLS-1$
		EXTENDED_COLORS.put("tomato", new Color(null, 255, 99, 71)); //$NON-NLS-1$
		EXTENDED_COLORS.put("turquoise", new Color(null, 64, 224, 208)); //$NON-NLS-1$
		EXTENDED_COLORS.put("violet", new Color(null, 238, 130, 238)); //$NON-NLS-1$
		EXTENDED_COLORS.put("violetred", new Color(null, 208, 32, 144)); //$NON-NLS-1$
		EXTENDED_COLORS.put("wheat", new Color(null, 245, 222, 179)); //$NON-NLS-1$
		EXTENDED_COLORS.put("white", ColorConstants.white); //$NON-NLS-1$
		EXTENDED_COLORS.put("whitesmoke", new Color(null, 245, 245, 245)); //$NON-NLS-1$
		EXTENDED_COLORS.put("yellow", ColorConstants.yellow); //$NON-NLS-1$
		EXTENDED_COLORS.put("yellowgreen", new Color(null, 154, 205, 50)); //$NON-NLS-1$
	}
}
