/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui.internal.dialogfield;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * Dialog field describing a separator.
 */
public class Separator extends DialogFieldBase {

	private Label _separator;

	private int _style;

	/**
	 * Default constructor
	 */
	public Separator() {
		this(SWT.NONE);
	}

	/**
	 * @param style
	 *            of the separator. See <code>Label</code> for possible
	 *            styles.
	 */
	public Separator(int style) {
		_style = style;
	}

	// ------- layout helpers

	/**
	 * Creates the separator and fills it in a MGridLayout.
	 * @param toolkit 
	 * @param parent 
	 * @param nColumns 
	 * 
	 * @param height
	 *            The heigth of the separator
	 * @return the controls or empty array
	 */
	public Control[] doFillIntoGrid(FormToolkit toolkit, Composite parent,
			int nColumns, int height) {
		assertEnoughColumns(nColumns);

		Control separator = getSeparator(toolkit, parent);
		separator.setLayoutData(gridDataForSeperator(nColumns, height));

		return new Control[] { separator };
	}

	/*
	 * @see DialogField#doFillIntoGrid
	 */
	public Control[] doFillIntoGrid(FormToolkit toolkit, Composite parent,
			int nColumns) {
		return doFillIntoGrid(toolkit, parent, nColumns, 4);
	}

	/*
	 * @see DialogField#getNumberOfControls
	 */
	public int getNumberOfControls() {
		return 1;
	}

	/**
	 * @param span
	 * @param height
	 * @return the grid data
	 */
	protected static GridData gridDataForSeperator(int span, int height) {
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.BEGINNING;
		gd.heightHint = height;
		gd.horizontalSpan = span;
		return gd;
	}

	// ------- ui creation

	/**
	 * Creates or returns the created separator.
	 * @param toolkit 
	 * 
	 * @param parent
	 *            The parent composite or <code>null</code> if the widget has
	 *            already been created.
	 * @return  the separator
	 */
	private Control getSeparator(FormToolkit toolkit, Composite parent) {
		if (_separator == null || _separator.isDisposed()) {
			assertCompositeNotNull(parent);
			if (toolkit != null) {
				_separator = toolkit.createSeparator(parent, _style);
			} else {
				_separator = new Label(parent, _style);
			}
		}
		return _separator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField#handleGrabHorizontal()
	 */
	public void handleGrabHorizontal() {
		// do nothing.
	}
}
