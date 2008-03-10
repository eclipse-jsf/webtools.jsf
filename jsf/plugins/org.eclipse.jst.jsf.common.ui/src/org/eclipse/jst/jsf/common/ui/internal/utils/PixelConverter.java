/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Oracle -- copied into common ui codebase for JSF use
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui.internal.utils;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Control;

import org.eclipse.jface.dialogs.Dialog;

/**
 * @author cbateman
 *
 */
public class PixelConverter {
    
    private final FontMetrics fFontMetrics;
    
    /**
     * @param control
     */
    public PixelConverter(Control control) {
        this(control.getFont());
    }
    
    /**
     * @param font
     */
    public PixelConverter(Font font) {
        GC gc = new GC(font.getDevice());
        gc.setFont(font);
        fFontMetrics= gc.getFontMetrics();
        gc.dispose();
    }
    

    /**
     * @param chars
     * @return the convert height in  pixels
     */
    public int convertHeightInCharsToPixels(int chars) {
        return Dialog.convertHeightInCharsToPixels(fFontMetrics, chars);
    }

    /**
     * @param dlus
     * @return the pixel
     */ 
    public int convertHorizontalDLUsToPixels(int dlus) {
        return Dialog.convertHorizontalDLUsToPixels(fFontMetrics, dlus);
    }

    /**
     * @param dlus
     * @return the vertical pixels
     */
    public int convertVerticalDLUsToPixels(int dlus) {
        return Dialog.convertVerticalDLUsToPixels(fFontMetrics, dlus);
    }
    

    /**
     * @param chars
     * @return the pixel value
     */
    public int convertWidthInCharsToPixels(int chars) {
        return Dialog.convertWidthInCharsToPixels(fFontMetrics, chars);
    }   

}
