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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * This will display a labal and a group of radio buttons. The group of radio
 * buttons will be layed out horizontally use a RowLayout, and them as a group
 * will use one cell in the GridLayout.
 * 
 * Whenever the radios selection change will fire both dialogFieldChanged() and
 * dialogFieldApplied() event.
 * 
 * @author mengbo
 */
public class RadiosDialogField extends DialogFieldBase {
	final static private String INDEXKEY = "INDEX";

	private Composite _group;

	private String[] _items;

	private Button[] _button;

	private int _selectIndex = -1;

	private boolean _fireEvent = true;

	/**
	 * Default constructor
	 */
	public RadiosDialogField() {
		super();
	}

	/**
	 * this method must be called before create control
	 * 
	 * @param items
	 */
	public void setItems(String[] items) {
		_items = items;
		_button = new Button[_items.length];
	}

	// ------- layout helpers

	/*
	 * @see DialogField#doFillIntoGrid
	 */
	public Control[] doFillIntoGrid(FormToolkit toolkit, Composite parent,
			int nColumns) {
		assertEnoughColumns(nColumns);

		Control requiredLabel = getRequiredLabelControl(toolkit, parent);
		requiredLabel.setLayoutData(gridDataForLabel(1));

		Control label = getLabelControl(toolkit, parent);
		label.setLayoutData(gridDataForLabel(1));

		_group = getGroup(toolkit, parent);
		_group.setLayoutData(gridDataForGroup(nColumns - 2));

		return new Control[] { requiredLabel, label, _group };
	}

	/*
	 * @see DialogField#getNumberOfControls
	 */
	public int getNumberOfControls() {
		return 3;
	}

	protected static GridData gridDataForGroup(int span) {
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = false;
		gd.horizontalSpan = span;
		return gd;
	}

	// ------- focus methods

	/*
	 * @see DialogField#setFocus
	 */
	public boolean setFocus() {
		if (isOkToUse(_group)) {
			_group.setFocus();
		}
		return true;
	}

	// ------- ui creation

	public Composite getGroup(FormToolkit toolkit, Composite parent) {
		if (_group == null) {
			assertCompositeNotNull(parent);
			if (toolkit != null) {
				_group = toolkit.createComposite(parent);
			} else {
				_group = new Composite(parent, SWT.NONE);
			}
			RowLayout layout = new RowLayout();
			layout.marginBottom = 0;
			_group.setLayout(layout);
			for (int i = 0; i < _items.length; i++) {
				if (toolkit != null) {
					_button[i] = toolkit.createButton(_group, _items[i],
							SWT.RADIO);
				} else {
					_button[i] = new Button(_group, SWT.RADIO);
					_button[i].setText(_items[i]);
				}
				_button[i].setData(INDEXKEY, new Integer(i));
				_button[i].addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						if (_fireEvent) {
							_selectIndex = ((Integer) e.widget
									.getData(INDEXKEY)).intValue();
							// FIXME: seemed will fire widgetSelected twice, one
							// for the deselect one,
							// one for the newly selected one. Need investigate.
							if (((Button) e.widget).getSelection()) {
								dialogFieldChangedAndApplied();
							}
						}
					}

				});
			}
		}
		return _group;
	}

	// ------ enable / disable management

	/*
	 * @see DialogField#updateEnableState
	 */
	protected void updateEnableState() {
		super.updateEnableState();
		if (isOkToUse(_group)) {
			_group.setEnabled(isEnabled());
		}
	}

	// ------ text access

	/**
	 * Sets the text. Triggers a dialog-changed event.
	 */
	public void setSelectedIndex(int index) {
		_selectIndex = index;
		if (isOkToUse(_group)) {
			if (_selectIndex >= 0 && _selectIndex < _items.length) {
				_button[_selectIndex].setSelection(true);
			} else {
				for (int i = 0; i < _items.length; i++) {
					if (_button[i].getSelection()) {
						_button[i].setSelection(false);
					}
				}
			}
		}
		dialogFieldChangedAndApplied();
	}

	/**
	 * Sets the text without triggering a dialog-changed event.
	 */
	public void setSelectedIndexWithoutUpdate(int index) {
		_selectIndex = index;
		if (isOkToUse(_group)) {
			_fireEvent = false;
			if (_selectIndex >= 0 && _selectIndex < _items.length) {
				_button[_selectIndex].setSelection(true);
			} else {
				for (int i = 0; i < _items.length; i++) {
					if (_button[i].getSelection()) {
						_button[i].setSelection(false);
					}
				}
			}
			_fireEvent = true;
		}
	}

	public int getSelectedIndex() {
		return _selectIndex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField#handleGrabHorizontal()
	 */
	public void handleGrabHorizontal() {
		LayoutUtil.setGrabHorizontal(this._group, true);
	}
}
