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
package org.eclipse.jst.pagedesigner.properties.celleditors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.sse.ui.internal.Logger;

/**
 * A simple ComboBoxCellEditor, which allow value and display string to be
 * different.
 * 
 * @author mengbo
 */
public class LabeledComboBoxCellEditor extends ComboBoxCellEditor {
	private boolean _fSettingValue = false;

	private Object[] _values;

	/**
	 * Create LabeledComboBoxCellEditor
	 * @param parent
	 * @param valueLabelMap
	 * @param style
	 * @return LabeledComboBoxCellEditor
	 */
	public static LabeledComboBoxCellEditor newInstance(Composite parent,
			Map valueLabelMap, int style) {
		// we'll sort according to label. since that is what being show to user.
		List list = new ArrayList();
		for (Iterator iter = valueLabelMap.keySet().iterator(); iter.hasNext();) {
			Object key = iter.next();
			String label = (String) valueLabelMap.get(key);
			list.add(new Object[] { key, label });
		}
		// sort by label
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				String label1 = (String) ((Object[]) o1)[1];
				String label2 = (String) ((Object[]) o2)[1];
				return label1.compareTo(label2);
			}
		});
		Object[] values = new Object[list.size()];
		String[] labels = new String[list.size()];
		for (int i = 0, n = list.size(); i < n; i++) {
			values[i] = ((Object[]) list.get(i))[0];
			labels[i] = (String) ((Object[]) list.get(i))[1];
		}
		return new LabeledComboBoxCellEditor(parent, values, labels, style);
	}

	/**
	 * Constructor
	 * 
	 * @param parent
	 * @param values
	 * @param labels
	 */
	public LabeledComboBoxCellEditor(Composite parent, Object[] values,
			String[] labels) {
		this(parent, values, labels, SWT.NONE);
	}

	/**
	 * Constructor
	 * 
	 * @param parent
	 * @param values
	 * @param labels
	 * @param style
	 */
	public LabeledComboBoxCellEditor(Composite parent, Object[] values,
			String[] labels, int style) {
		super(parent, labels, style);
		_values = values;
	}

	protected Object doGetValue() {
		// otherwise limits to set of valid values
		Object index = super.doGetValue();
		int selection = -1;
		if (index instanceof Integer) {
			selection = ((Integer) index).intValue();
		}
		if (selection >= 0) {
			return _values[selection];
		} else if (getControl() instanceof CCombo) {
			// retrieve the actual text as the list of valid items doesn't
			// contain the value
			return ((CCombo) getControl()).getText();
		}
		return null;
	}

	protected void doSetValue(Object value) {
		if (_fSettingValue) {
			return;
		}
		_fSettingValue = true;
		if (value instanceof Integer) {
			super.doSetValue(value);
		} else {
			String stringValue = value.toString();
			int selection = -1;
			for (int i = 0; i < _values.length; i++) {
				if (_values[i].equals(stringValue)) {
					selection = i;
				}
			}
			if (selection >= 0) {
				super.doSetValue(new Integer(selection));
			} else {
				super.doSetValue(new Integer(-1));
				if (getControl() instanceof CCombo
						&& !stringValue.equals(((CCombo) getControl())
								.getText())) {
					// update the Text widget
					((CCombo) getControl()).setText(stringValue);
				}
			}
		}
		_fSettingValue = false;
	}

	public void setItems(String[] newItems) {
		if (getControl() == null || getControl().isDisposed()) {
			Logger.log(Logger.ERROR,
					"Attempted to update item list for disposed cell editor"); //$NON-NLS-1$
			return;
		}

		// keep selection if possible
		Object previousSelectedValue = getValue();
		super.setItems(newItems);
		if (previousSelectedValue != null && getControl() instanceof CCombo) {
			for (int i = 0; i < newItems.length; i++) {
				if (newItems[i].equals(previousSelectedValue)) {
					setValue(previousSelectedValue);
				}
			}
		}
	}
}
