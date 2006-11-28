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
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class LayoutUtil {
	// The Text control looks higher then Button control when they have the same
	// height,
	// Increasing the height of 5 pixels will make them looks the same height.
	private final static int BUTTON_HEIGHT_ADJUSTMENT = 5;

	public static Control createEmptySpace(FormToolkit kit, Composite parent,
			int span) {
		Label label;
		if (kit != null) {
			label = kit.createLabel(parent, "");
		} else {
			label = new Label(parent, SWT.LEFT);
		}
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.BEGINNING;
		gd.grabExcessHorizontalSpace = false;
		gd.horizontalSpan = span;
		gd.horizontalIndent = 0;
		gd.widthHint = 0;
		gd.heightHint = 0;
		label.setLayoutData(gd);
		return label;
	}

	public static Composite createComposite(FormToolkit kit, Composite parent,
			int span, int internalSpan) {
		Composite c;
		if (kit != null) {
			c = kit.createComposite(parent);
			kit.paintBordersFor(c);
		} else {
			c = new Composite(parent, SWT.NONE);
		}
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = span;
		gd.horizontalIndent = 0;
		c.setLayoutData(gd);

		GridLayout layout = new GridLayout();
		layout.numColumns = internalSpan;
		c.setLayout(layout);

		return c;
	}

	/**
	 * Calculates the number of columns needed by field editors
	 */
	public static int getNumberOfColumns(DialogField[] editors) {
		int nCulumns = 0;
		for (int i = 0; i < editors.length; i++) {
			nCulumns = Math.max(editors[i].getNumberOfControls(), nCulumns);
		}
		return nCulumns;
	}

	/**
	 * Creates a composite and fills in the given editors.
	 * 
	 * @param labelOnTop
	 *            Defines if the label of all fields should be on top of the
	 *            fields
	 */
	public static void doDefaultLayout(FormToolkit toolkit, Composite parent,
			DialogField[] editors, boolean labelOnTop) {
		doDefaultLayout(toolkit, parent, editors, labelOnTop, 0, 0, 0, 0);
	}

	/**
	 * Creates a composite and fills in the given editors.
	 * 
	 * @param labelOnTop
	 *            Defines if the label of all fields should be on top of the
	 *            fields
	 * @param minWidth
	 *            The minimal width of the composite
	 * @param minHeight
	 *            The minimal height of the composite
	 */
	public static void doDefaultLayout(FormToolkit toolkit, Composite parent,
			DialogField[] editors, boolean labelOnTop, int minWidth,
			int minHeight) {
		doDefaultLayout(toolkit, parent, editors, labelOnTop, minWidth,
				minHeight, 0, 0);
	}

	/**
	 * Creates a composite and fills in the given editors.
	 * 
	 * @param labelOnTop
	 *            Defines if the label of all fields should be on top of the
	 *            fields
	 * @param minWidth
	 *            The minimal width of the composite
	 * @param minHeight
	 *            The minimal height of the composite
	 * @param marginWidth
	 *            The margin width to be used by the composite
	 * @param marginHeight
	 *            The margin height to be used by the composite
	 * @deprecated
	 */
	public static void doDefaultLayout(FormToolkit toolkit, Composite parent,
			DialogField[] editors, boolean labelOnTop, int minWidth,
			int minHeight, int marginWidth, int marginHeight) {
		int nCulumns = getNumberOfColumns(editors);
		Control[][] controls = new Control[editors.length][];
		for (int i = 0; i < editors.length; i++) {
			controls[i] = editors[i].doFillIntoGrid(toolkit, parent, nCulumns);
		}
		if (labelOnTop) {
			nCulumns--;
			modifyLabelSpans(controls, nCulumns);
		}
		GridLayout layout = new GridLayout();
		if (marginWidth != SWT.DEFAULT) {
			layout.marginWidth = marginWidth;
		}
		if (marginHeight != SWT.DEFAULT) {
			layout.marginHeight = marginHeight;
		}
		layout.numColumns = nCulumns;
		parent.setLayout(layout);
	}

	private static void modifyLabelSpans(Control[][] controls, int nCulumns) {
		for (int i = 0; i < controls.length; i++) {
			setHorizontalSpan(controls[i][0], nCulumns);
		}
	}

	/**
	 * Sets the span of a control. Assumes that GridData is used.
	 */
	public static void setHorizontalSpan(Control control, int span) {
		Object ld = control.getLayoutData();
		if (ld instanceof GridData) {
			((GridData) ld).horizontalSpan = span;
		} else if (span != 1) {
			GridData gd = new GridData();
			gd.horizontalSpan = span;
			control.setLayoutData(gd);
		}
	}

	public static void setGrabHorizontal(Control control, boolean grab) {
		Object ld = control.getLayoutData();
		if (ld instanceof GridData) {
			((GridData) ld).grabExcessHorizontalSpace = grab;
		}
	}

	/**
	 * Sets the width hint of a control. Assumes that GridData is used.
	 */
	public static void setWidthHint(Control control, int widthHint) {
		Object ld = control.getLayoutData();
		if (ld instanceof GridData) {
			((GridData) ld).widthHint = widthHint;
		}
	}

	/**
	 * Sets the heigthHint hint of a control. Assumes that GridData is used.
	 */
	public static void setHeigthHint(Control control, int heigthHint) {
		Object ld = control.getLayoutData();
		if (ld instanceof GridData) {
			((GridData) ld).heightHint = heigthHint;
		}
	}

	/**
	 * Sets the horizontal indent of a control. Assumes that GridData is used.
	 */
	public static void setHorizontalIndent(Control control, int horizontalIndent) {
		Object ld = control.getLayoutData();
		if (ld instanceof GridData) {
			((GridData) ld).horizontalIndent = horizontalIndent;
		}
	}

	/**
	 * Sets the horizontal indent of a control. Assumes that GridData is used.
	 */
	public static void setHorizontalGrabbing(Control control) {
		Object ld = control.getLayoutData();
		if (ld instanceof GridData) {
			((GridData) ld).grabExcessHorizontalSpace = true;
		}
	}

	/**
	 * Returns a width hint for a button control.
	 */
	public static int getButtonWidthHint(Button button) {
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

	public static int getButtonHeightHint(FormToolkit toolkit, Text text) {
		if (toolkit != null) {
			return text.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).y
					+ BUTTON_HEIGHT_ADJUSTMENT;
		}
        return text.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).y;
	}
}
