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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ColorPalette;
import org.eclipse.swt.graphics.Color;

/**
 * @author mengbo
 * @version 1.5
 */
public class ColorUtil extends ColorPalette {
	private static final Map EXTENDED_COLORS = new HashMap(20);

	private static final Map BASIC_COLORS = new HashMap(143);

	static {
		BASIC_COLORS.put("Aqua", new Color(null, 0x00, 0xFF, 0xFF)); //$NON-NLS-1$
		BASIC_COLORS.put("Black", new Color(null, 0x00, 0x00, 0x00)); //$NON-NLS-1$
		BASIC_COLORS.put("Blue", new Color(null, 0x00, 0x00, 0xFF)); //$NON-NLS-1$
		BASIC_COLORS.put("Fuchsia", new Color(null, 0xFF, 0x00, 0xFF)); //$NON-NLS-1$
		BASIC_COLORS.put("Gray", new Color(null, 0x80, 0x80, 0x80)); //$NON-NLS-1$
		BASIC_COLORS.put("Green", new Color(null, 0x00, 0x80, 0x00)); //$NON-NLS-1$
		BASIC_COLORS.put("Lime", new Color(null, 0x00, 0xFF, 0x00)); //$NON-NLS-1$
		BASIC_COLORS.put("Maroon", new Color(null, 0x80, 0x00, 0x00)); //$NON-NLS-1$
		BASIC_COLORS.put("Navy", new Color(null, 0x00, 0x00, 0x80)); //$NON-NLS-1$
		BASIC_COLORS.put("Olive", new Color(null, 0x80, 0x80, 0x00)); //$NON-NLS-1$
		BASIC_COLORS.put("Purple", new Color(null, 0x80, 0x00, 0x80)); //$NON-NLS-1$
		BASIC_COLORS.put("Red", new Color(null, 0xFF, 0x00, 0x00)); //$NON-NLS-1$
		BASIC_COLORS.put("Silver", new Color(null, 0xC0, 0xC0, 0xC0)); //$NON-NLS-1$
		BASIC_COLORS.put("Teal", new Color(null, 0x00, 0x80, 0x80)); //$NON-NLS-1$
		BASIC_COLORS.put("White", new Color(null, 0xFF, 0xFF, 0xFF)); //$NON-NLS-1$
		BASIC_COLORS.put("Yellow", new Color(null, 0xFF, 0xFF, 0x00)); //$NON-NLS-1$
	}

	static {
		EXTENDED_COLORS.put("AliceBlue", new Color(null, 0xF0, 0xF8, 0xFF)); //$NON-NLS-1$
		EXTENDED_COLORS.put("AntiqueWhite", new Color(null, 0xFA, 0xEB, 0xD7)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Aqua", new Color(null, 0x00, 0xFF, 0xFF)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Aquamarine", new Color(null, 0x7F, 0xFF, 0xD4)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Azure", new Color(null, 0xF0, 0xFF, 0xFF)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Beige", new Color(null, 0xF5, 0xF5, 0xDC)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Bisque", new Color(null, 0xFF, 0xE4, 0xC4)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Black", new Color(null, 0x00, 0x00, 0x00)); //$NON-NLS-1$
		EXTENDED_COLORS
				.put("BlanchedAlmond", new Color(null, 0xFF, 0xEB, 0xCD)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Blue", new Color(null, 0x00, 0x00, 0xFF)); //$NON-NLS-1$
		EXTENDED_COLORS.put("BlueViolet", new Color(null, 0x8A, 0x2B, 0xE2)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Brown", new Color(null, 0xA5, 0x2A, 0x2A)); //$NON-NLS-1$
		EXTENDED_COLORS.put("BurlyWood", new Color(null, 0xDE, 0xB8, 0x87)); //$NON-NLS-1$
		EXTENDED_COLORS.put("CadetBlue", new Color(null, 0x5F, 0x9E, 0xA0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Chartreuse", new Color(null, 0x7F, 0xFF, 0x00)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Chocolate", new Color(null, 0xD2, 0x69, 0x1E)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Coral", new Color(null, 0xFF, 0x7F, 0x50)); //$NON-NLS-1$
		EXTENDED_COLORS
				.put("CornflowerBlue", new Color(null, 0x64, 0x95, 0xED)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Cornsilk", new Color(null, 0xFF, 0xF8, 0xDC)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Crimson", new Color(null, 0xDC, 0x14, 0x3C)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Cyan", new Color(null, 0x00, 0xFF, 0xFF)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DarkBlue", new Color(null, 0x00, 0x00, 0x8B)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DarkCyan", new Color(null, 0x00, 0x8B, 0x8B)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DarkGoldenRod", new Color(null, 0xB8, 0x86, 0x0B)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DarkGray", new Color(null, 0xA9, 0xA9, 0xA9)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DarkGreen", new Color(null, 0x00, 0x64, 0x00)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DarkKhaki", new Color(null, 0xBD, 0xB7, 0x6B)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DarkMagenta", new Color(null, 0x8B, 0x00, 0x8B)); //$NON-NLS-1$
		EXTENDED_COLORS
				.put("DarkOliveGreen", new Color(null, 0x55, 0x6B, 0x2F)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Darkorange", new Color(null, 0xFF, 0x8C, 0x00)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DarkOrchid", new Color(null, 0x99, 0x32, 0xCC)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DarkRed", new Color(null, 0x8B, 0x00, 0x00)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DarkSalmon", new Color(null, 0xE9, 0x96, 0x7A)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DarkSeaGreen", new Color(null, 0x8F, 0xBC, 0x8F)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DarkSlateBlue", new Color(null, 0x48, 0x3D, 0x8B)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DarkSlateGray", new Color(null, 0x2F, 0x4F, 0x4F)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DarkTurquoise", new Color(null, 0x00, 0xCE, 0xD1)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DarkViolet", new Color(null, 0x94, 0x00, 0xD3)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DeepPink", new Color(null, 0xFF, 0x14, 0x93)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DeepSkyBlue", new Color(null, 0x00, 0xBF, 0xFF)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DimGray", new Color(null, 0x69, 0x69, 0x69)); //$NON-NLS-1$
		EXTENDED_COLORS.put("DodgerBlue", new Color(null, 0x1E, 0x90, 0xFF)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Feldspar", new Color(null, 0xD1, 0x92, 0x75)); //$NON-NLS-1$
		EXTENDED_COLORS.put("FireBrick", new Color(null, 0xB2, 0x22, 0x22)); //$NON-NLS-1$
		EXTENDED_COLORS.put("FloralWhite", new Color(null, 0xFF, 0xFA, 0xF0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("ForestGreen", new Color(null, 0x22, 0x8B, 0x22)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Fuchsia", new Color(null, 0xFF, 0x00, 0xFF)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Gainsboro", new Color(null, 0xDC, 0xDC, 0xDC)); //$NON-NLS-1$
		EXTENDED_COLORS.put("GhostWhite", new Color(null, 0xF8, 0xF8, 0xFF)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Gold", new Color(null, 0xFF, 0xD7, 0x00)); //$NON-NLS-1$
		EXTENDED_COLORS.put("GoldenRod", new Color(null, 0xDA, 0xA5, 0x20)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Gray", new Color(null, 0x80, 0x80, 0x80)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Green", new Color(null, 0x00, 0x80, 0x00)); //$NON-NLS-1$
		EXTENDED_COLORS.put("GreenYellow", new Color(null, 0xAD, 0xFF, 0x2F)); //$NON-NLS-1$
		EXTENDED_COLORS.put("HoneyDew", new Color(null, 0xF0, 0xFF, 0xF0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("HotPink", new Color(null, 0xFF, 0x69, 0xB4)); //$NON-NLS-1$
		EXTENDED_COLORS.put("IndianRed", new Color(null, 0xCD, 0x5C, 0x5C)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Indigo", new Color(null, 0x4B, 0x00, 0x82)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Ivory", new Color(null, 0xFF, 0xFF, 0xF0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Khaki", new Color(null, 0xF0, 0xE6, 0x8C)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Lavender", new Color(null, 0xE6, 0xE6, 0xFA)); //$NON-NLS-1$
		EXTENDED_COLORS.put("LavenderBlush", new Color(null, 0xFF, 0xF0, 0xF5)); //$NON-NLS-1$
		EXTENDED_COLORS.put("LawnGreen", new Color(null, 0x7C, 0xFC, 0x00)); //$NON-NLS-1$
		EXTENDED_COLORS.put("LemonChiffon", new Color(null, 0xFF, 0xFA, 0xCD)); //$NON-NLS-1$
		EXTENDED_COLORS.put("LightBlue", new Color(null, 0xAD, 0xD8, 0xE6)); //$NON-NLS-1$
		EXTENDED_COLORS.put("LightCoral", new Color(null, 0xF0, 0x80, 0x80)); //$NON-NLS-1$
		EXTENDED_COLORS.put("LightCyan", new Color(null, 0xE0, 0xFF, 0xFF)); //$NON-NLS-1$
		EXTENDED_COLORS.put("LightGoldenRodYellow", new Color(null, 0xFA, 0xFA, //$NON-NLS-1$
				0xD2));
		EXTENDED_COLORS.put("LightGrey", new Color(null, 0xD3, 0xD3, 0xD3)); //$NON-NLS-1$
		EXTENDED_COLORS.put("LightGreen", new Color(null, 0x90, 0xEE, 0x90)); //$NON-NLS-1$
		EXTENDED_COLORS.put("LightPink", new Color(null, 0xFF, 0xB6, 0xC1)); //$NON-NLS-1$
		EXTENDED_COLORS.put("LightSalmon", new Color(null, 0xFF, 0xA0, 0x7A)); //$NON-NLS-1$
		EXTENDED_COLORS.put("LightSeaGreen", new Color(null, 0x20, 0xB2, 0xAA)); //$NON-NLS-1$
		EXTENDED_COLORS.put("LightSkyBlue", new Color(null, 0x87, 0xCE, 0xFA)); //$NON-NLS-1$
		EXTENDED_COLORS
				.put("LightSlateBlue", new Color(null, 0x84, 0x70, 0xFF)); //$NON-NLS-1$
		EXTENDED_COLORS
				.put("LightSlateGray", new Color(null, 0x77, 0x88, 0x99)); //$NON-NLS-1$
		EXTENDED_COLORS
				.put("LightSteelBlue", new Color(null, 0xB0, 0xC4, 0xDE)); //$NON-NLS-1$
		EXTENDED_COLORS.put("LightYellow", new Color(null, 0xFF, 0xFF, 0xE0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Lime", new Color(null, 0x00, 0xFF, 0x00)); //$NON-NLS-1$
		EXTENDED_COLORS.put("LimeGreen", new Color(null, 0x32, 0xCD, 0x32)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Linen", new Color(null, 0xFA, 0xF0, 0xE6)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Magenta", new Color(null, 0xFF, 0x00, 0xFF)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Maroon", new Color(null, 0x80, 0x00, 0x00)); //$NON-NLS-1$
		EXTENDED_COLORS.put("MediumAquaMarine", new Color(null, 0x66, 0xCD, //$NON-NLS-1$
				0xAA));
		EXTENDED_COLORS.put("MediumBlue", new Color(null, 0x00, 0x00, 0xCD)); //$NON-NLS-1$
		EXTENDED_COLORS.put("MediumOrchid", new Color(null, 0xBA, 0x55, 0xD3)); //$NON-NLS-1$
		EXTENDED_COLORS.put("MediumPurple", new Color(null, 0x93, 0x70, 0xD8)); //$NON-NLS-1$
		EXTENDED_COLORS
				.put("MediumSeaGreen", new Color(null, 0x3C, 0xB3, 0x71)); //$NON-NLS-1$
		EXTENDED_COLORS.put("MediumSlateBlue", //$NON-NLS-1$
				new Color(null, 0x7B, 0x68, 0xEE));
		EXTENDED_COLORS.put("MediumSpringGreen", new Color(null, 0x00, 0xFA, //$NON-NLS-1$
				0x9A));
		EXTENDED_COLORS.put("MediumTurquoise", //$NON-NLS-1$
				new Color(null, 0x48, 0xD1, 0xCC));
		EXTENDED_COLORS.put("MediumVioletRed", //$NON-NLS-1$
				new Color(null, 0xC7, 0x15, 0x85));
		EXTENDED_COLORS.put("MidnightBlue", new Color(null, 0x19, 0x19, 0x70)); //$NON-NLS-1$
		EXTENDED_COLORS.put("MintCream", new Color(null, 0xF5, 0xFF, 0xFA)); //$NON-NLS-1$
		EXTENDED_COLORS.put("MistyRose", new Color(null, 0xFF, 0xE4, 0xE1)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Moccasin", new Color(null, 0xFF, 0xE4, 0xB5)); //$NON-NLS-1$
		EXTENDED_COLORS.put("NavajoWhite", new Color(null, 0xFF, 0xDE, 0xAD)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Navy", new Color(null, 0x00, 0x00, 0x80)); //$NON-NLS-1$
		EXTENDED_COLORS.put("OldLace", new Color(null, 0xFD, 0xF5, 0xE6)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Olive", new Color(null, 0x80, 0x80, 0x00)); //$NON-NLS-1$
		EXTENDED_COLORS.put("OliveDrab", new Color(null, 0x6B, 0x8E, 0x23)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Orange", new Color(null, 0xFF, 0xA5, 0x00)); //$NON-NLS-1$
		EXTENDED_COLORS.put("OrangeRed", new Color(null, 0xFF, 0x45, 0x00)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Orchid", new Color(null, 0xDA, 0x70, 0xD6)); //$NON-NLS-1$
		EXTENDED_COLORS.put("PaleGoldenRod", new Color(null, 0xEE, 0xE8, 0xAA)); //$NON-NLS-1$
		EXTENDED_COLORS.put("PaleGreen", new Color(null, 0x98, 0xFB, 0x98)); //$NON-NLS-1$
		EXTENDED_COLORS.put("PaleTurquoise", new Color(null, 0xAF, 0xEE, 0xEE)); //$NON-NLS-1$
		EXTENDED_COLORS.put("PaleVioletRed", new Color(null, 0xD8, 0x70, 0x93)); //$NON-NLS-1$
		EXTENDED_COLORS.put("PapayaWhip", new Color(null, 0xFF, 0xEF, 0xD5)); //$NON-NLS-1$
		EXTENDED_COLORS.put("PeachPuff", new Color(null, 0xFF, 0xDA, 0xB9)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Peru", new Color(null, 0xCD, 0x85, 0x3F)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Pink", new Color(null, 0xFF, 0xC0, 0xCB)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Plum", new Color(null, 0xDD, 0xA0, 0xDD)); //$NON-NLS-1$
		EXTENDED_COLORS.put("PowderBlue", new Color(null, 0xB0, 0xE0, 0xE6)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Purple", new Color(null, 0x80, 0x00, 0x80)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Red", new Color(null, 0xFF, 0x00, 0x00)); //$NON-NLS-1$
		EXTENDED_COLORS.put("RosyBrown", new Color(null, 0xBC, 0x8F, 0x8F)); //$NON-NLS-1$
		EXTENDED_COLORS.put("RoyalBlue", new Color(null, 0x41, 0x69, 0xE1)); //$NON-NLS-1$
		EXTENDED_COLORS.put("SaddleBrown", new Color(null, 0x8B, 0x45, 0x13)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Salmon", new Color(null, 0xFA, 0x80, 0x72)); //$NON-NLS-1$
		EXTENDED_COLORS.put("SandyBrown", new Color(null, 0xF4, 0xA4, 0x60)); //$NON-NLS-1$
		EXTENDED_COLORS.put("SeaGreen", new Color(null, 0x2E, 0x8B, 0x57)); //$NON-NLS-1$
		EXTENDED_COLORS.put("SeaShell", new Color(null, 0xFF, 0xF5, 0xEE)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Sienna", new Color(null, 0xA0, 0x52, 0x2D)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Silver", new Color(null, 0xC0, 0xC0, 0xC0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("SkyBlue", new Color(null, 0x87, 0xCE, 0xEB)); //$NON-NLS-1$
		EXTENDED_COLORS.put("SlateBlue", new Color(null, 0x6A, 0x5A, 0xCD)); //$NON-NLS-1$
		EXTENDED_COLORS.put("SlateGray", new Color(null, 0x70, 0x80, 0x90)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Snow", new Color(null, 0xFF, 0xFA, 0xFA)); //$NON-NLS-1$
		EXTENDED_COLORS.put("SpringGreen", new Color(null, 0x00, 0xFF, 0x7F)); //$NON-NLS-1$
		EXTENDED_COLORS.put("SteelBlue", new Color(null, 0x46, 0x82, 0xB4)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Tan", new Color(null, 0xD2, 0xB4, 0x8C)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Teal", new Color(null, 0x00, 0x80, 0x80)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Thistle", new Color(null, 0xD8, 0xBF, 0xD8)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Tomato", new Color(null, 0xFF, 0x63, 0x47)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Turquoise", new Color(null, 0x40, 0xE0, 0xD0)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Violet", new Color(null, 0xEE, 0x82, 0xEE)); //$NON-NLS-1$
		EXTENDED_COLORS.put("VioletRed", new Color(null, 0xD0, 0x20, 0x90)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Wheat", new Color(null, 0xF5, 0xDE, 0xB3)); //$NON-NLS-1$
		EXTENDED_COLORS.put("White", new Color(null, 0xFF, 0xFF, 0xFF)); //$NON-NLS-1$
		EXTENDED_COLORS.put("WhiteSmoke", new Color(null, 0xF5, 0xF5, 0xF5)); //$NON-NLS-1$
		EXTENDED_COLORS.put("Yellow", new Color(null, 0xFF, 0xFF, 0x00)); //$NON-NLS-1$
		EXTENDED_COLORS.put("YellowGreen", new Color(null, 0x9A, 0xCD, 0x32)); //$NON-NLS-1$
	}

	public final Map getBasicColorMap() {
		return Collections.unmodifiableMap(BASIC_COLORS);
	}

	public final Map getExtendedColorMap() {
		return Collections.unmodifiableMap(EXTENDED_COLORS);
	}
}
