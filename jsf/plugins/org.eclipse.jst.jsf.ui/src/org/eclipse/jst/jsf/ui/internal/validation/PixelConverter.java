/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Oracle - copied for use in JSF validation tooling
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.validation;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Control;

import org.eclipse.jface.dialogs.Dialog;

/**
 * Copied from JDT.  Used by OptionsConfigurationBlock
 *
 */
class PixelConverter {
    
    private final FontMetrics fFontMetrics;
    
    /**
     * @param control
     */
    PixelConverter(Control control) {
        this(control.getFont());
    }
    
    /**
     * @param font
     */
    PixelConverter(Font font) {
        GC gc = new GC(font.getDevice());
        gc.setFont(font);
        fFontMetrics= gc.getFontMetrics();
        gc.dispose();
    }
    
    /*
     * see org.eclipse.jface.dialogs.DialogPage#convertHeightInCharsToPixels(int)
     */
    int convertHeightInCharsToPixels(int chars) {
        return Dialog.convertHeightInCharsToPixels(fFontMetrics, chars);
    }

    /*
     * see org.eclipse.jface.dialogs.DialogPage#convertHorizontalDLUsToPixels(int)
     */
    int convertHorizontalDLUsToPixels(int dlus) {
        return Dialog.convertHorizontalDLUsToPixels(fFontMetrics, dlus);
    }

    /*
     * see org.eclipse.jface.dialogs.DialogPage#convertVerticalDLUsToPixels(int)
     */
    int convertVerticalDLUsToPixels(int dlus) {
        return Dialog.convertVerticalDLUsToPixels(fFontMetrics, dlus);
    }
    
    /*
     * see org.eclipse.jface.dialogs.DialogPage#convertWidthInCharsToPixels(int)
     */
    int convertWidthInCharsToPixels(int chars) {
        return Dialog.convertWidthInCharsToPixels(fFontMetrics, chars);
    }   

}
