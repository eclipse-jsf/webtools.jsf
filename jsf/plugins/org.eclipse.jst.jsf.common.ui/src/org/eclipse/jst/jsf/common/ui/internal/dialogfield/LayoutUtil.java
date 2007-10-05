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
package org.eclipse.jst.jsf.common.ui.internal.dialogfield;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * A layout utility class
 *
 */
public final class LayoutUtil {
	// The Text control looks higher then Button control when they have the same
	// height,
	// Increasing the height of 5 pixels will make them looks the same height.
	private final static int BUTTON_HEIGHT_ADJUSTMENT = 5;


	/**
	 * @param control
	 * @param grab
	 */
	public static void setGrabHorizontal(Control control, boolean grab) {
		Object ld = control.getLayoutData();
		if (ld instanceof GridData) {
			((GridData) ld).grabExcessHorizontalSpace = grab;
		}
	}

	/**
	 * Sets the width hint of a control. Assumes that GridData is used.
	 * @param control 
	 * @param widthHint 
	 */
	public static void setWidthHint(Control control, int widthHint) {
		Object ld = control.getLayoutData();
		if (ld instanceof GridData) {
			((GridData) ld).widthHint = widthHint;
		}
	}


	/**
	 * Sets the horizontal indent of a control. Assumes that GridData is used.
	 * @param control 
	 * @param horizontalIndent 
	 */
	static void setHorizontalIndent(Control control, int horizontalIndent) {
		Object ld = control.getLayoutData();
		if (ld instanceof GridData) {
			((GridData) ld).horizontalIndent = horizontalIndent;
		}
	}

	/**
	 * Sets the horizontal indent of a control. Assumes that GridData is used.
	 * @param control 
	 */
	public static void setHorizontalGrabbing(Control control) {
		Object ld = control.getLayoutData();
		if (ld instanceof GridData) {
			((GridData) ld).grabExcessHorizontalSpace = true;
		}
	}

	/**
	 * Returns a width hint for a button control.
	 * @param button 
	 * @return the hint value
	 */
	static int getButtonWidthHint(Button button) {
		if (button.getFont().equals(JFaceResources.getDefaultFont()))
			button.setFont(JFaceResources.getDialogFont());

		GC gc = new GC(button);
		gc.setFont(button.getFont());
		FontMetrics fontMetrics = gc.getFontMetrics();
		gc.dispose();

		int length = button.getText().length();
		int widthHint = Dialog.convertWidthInCharsToPixels(fontMetrics,
				length < 2 ? 2 : length);
		return Math.max(widthHint, button.computeSize(SWT.DEFAULT, SWT.DEFAULT,
				true).x);
	}

	static int getButtonHeightHint(FormToolkit toolkit, Text text) {
		if (toolkit != null) {
			return text.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).y
					+ BUTTON_HEIGHT_ADJUSTMENT;
		}
        return text.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).y;
	}
	
	private LayoutUtil()
	{
		// static utility class; no instantiation
	}
}
