/*******************************************************************************
 * Copyright (c) 2001, 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui.internal.dialogfield;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

/**
 * Defines a two sets: one of basic and one of extended colors.  Clients extending
 * this class must provide the two maps
 * 
 * @author cbateman
 *
 */
public abstract class ColorPalette 
{
    /**
     * @param cssText
     * @return the basic color matching the cssText key or null if color doesn't exist
     */
    public final Color getBasicColor(String cssText) {
        return (Color) getBasicColorMap().get(cssText.toLowerCase());
    }

    /**
     * @param cssText
     * @return the extended color matching the cssText key or null if color doesn't exist
     */
    public final Color getExtendedColor(String cssText) {
        Set keys = getExtendedColorMap().keySet();
        for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            if (key.equalsIgnoreCase(cssText)) {
                return (Color) getExtendedColorMap().get(key);
            }
        }
        return null;
    }

    /**
     * @return the map of basic colors where the key is the color name (a string)
     * and the value is an RGB object
     */
    public abstract Map getBasicColorMap();

    /**
     * @return the map of extended colors where the key is the color name (a string)
     * and the value is an Color object
     */
    public abstract Map getExtendedColorMap();

    /**
     * @param textColor
     * @return same as getExtendedColor but returns value as an RGB object
     */
    public final RGB getExtendedColorRGB(String textColor) {
        if (textColor == null || textColor.length() == 0) {
            return null;
        }

        Color color = getExtendedColor(textColor);
        if (color != null) {
            return color.getRGB();
        }

        if (textColor.charAt(0) == '#' && textColor.length() == 4) {
            char[] rgbChars = textColor.toCharArray();
            char[] fullChars = { rgbChars[0], rgbChars[1], rgbChars[1],
                    rgbChars[2], rgbChars[2], rgbChars[3], rgbChars[3] };

            textColor = String.valueOf(fullChars);
        }

        if (textColor.charAt(0) == '#' && textColor.length() == 7) {
            try {
                int intColor = Integer.decode(textColor).intValue();

                if (intColor > 0xFFFFFF || intColor < 0) {
                    return null;
                }
                int r = intColor >> 16;
                int g = (intColor >> 8) & 0xFF;
                int b = intColor & 0xFF;
                return new RGB(r, g, b);

            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * @param rgb
     * @return the hex string for equivalent of the rgb color
     */
    public static String getStringColor(RGB rgb) {
        if (rgb == null) {
            return "";
        }

        StringBuffer buffer = new StringBuffer("#");
        int[] intRGBs = new int[] { rgb.red, rgb.green, rgb.blue };
        for (int i = 0; i < 3; i++) {
            if (intRGBs[i] < 16) {
                buffer.append("0");
            }
            buffer.append(Integer.toHexString(intRGBs[i]).toUpperCase());
        }
        return buffer.toString();
    }

}
