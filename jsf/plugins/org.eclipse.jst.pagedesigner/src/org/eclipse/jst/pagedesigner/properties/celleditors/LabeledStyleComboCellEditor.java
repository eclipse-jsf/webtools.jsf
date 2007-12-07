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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jst.jsf.common.ui.internal.utils.StyleCombo;
import org.eclipse.jst.jsf.metadataprocessors.features.IDefaultValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.sse.ui.internal.Logger;

/**
 * A combo cell editor
 *
 */
public class LabeledStyleComboCellEditor extends StyleComboCellEditor {
	private boolean _fSettingValue = false;

	private Object[] _values;

	/**
	 * @param parent
	 * @param valueLabelMap
	 * @param defaultValue
	 * @param style
	 * @return the new instance
	 */
	public static LabeledStyleComboCellEditor newInstance(Composite parent,
			Map valueLabelMap, String defaultValue, int style) {
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
		return new LabeledStyleComboCellEditor(parent, values, labels,
				defaultValue, style);
	}

	/**
	 * Constructor
	 * 
	 * @param parent
	 * @param values
	 * @param labels
	 * @param defaultValue 
	 * @param style
	 */
	public LabeledStyleComboCellEditor(Composite parent, Object[] values,
			String[] labels, String defaultValue, int style) {
		super(parent, labels, style);
		StyleCombo combo = (StyleCombo) getControl();
		if (defaultValue != null)
			combo.setDefaultValue(defaultValue);
		_values = values;
	}

	protected Object doGetValue() {
		// otherwise limits to set of valid values
		Object index = super.doGetValue();
		int selection1 = -1;
		if (index instanceof Integer) {
			selection1 = ((Integer) index).intValue();
		}
		if (selection1 >= 0) {
			return _values[selection1];
		} else if (getControl() instanceof StyleCombo) {
			// retrieve the actual text as the list of valid items doesn't
			// contain the value
			return ((StyleCombo) getControl()).getText();
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
			int selection1 = -1;
			for (int i = 0; i < _values.length; i++) {
				if (_values[i].equals(stringValue)) {
					selection1 = i;
				}
			}
			if (selection1 >= 0) {
				super.doSetValue(new Integer(selection1));
			} else {
				super.doSetValue(new Integer(-1));
				if (getControl() instanceof StyleCombo
						&& !stringValue.equals(((StyleCombo) getControl())
								.getText())) {
					// update the Text widget
					((StyleCombo) getControl()).setText(stringValue);
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
		if (previousSelectedValue != null && getControl() instanceof StyleCombo) {
			for (int i = 0; i < newItems.length; i++) {
				if (newItems[i].equals(previousSelectedValue)) {
					setValue(previousSelectedValue);
				}
			}
		}
	}

	/**
	 * Create CellEditor
	 * @param parent
	 * @param pvs
	 * @param defaultValue
	 * @param style
	 * @return CellEditor
	 */
	public static CellEditor newInstance(Composite parent, IPossibleValues pvs,
			IDefaultValue defaultValue, int style) {
	
		CellEditor ed = null;
		if (pvs != null) {
			Map map = getPossibleValueMap(pvs);
			ed = newInstance(parent, map, defaultValue != null ? defaultValue.getDefaultValue() : null, style);
		}
		
		return ed;
	}

	private static Map getPossibleValueMap(IPossibleValues pvs) {
		Map<String, String> map = new HashMap<String, String>(pvs.getPossibleValues().size());
		for (Iterator it = pvs.getPossibleValues().iterator();it.hasNext();){
			IPossibleValue pv = (IPossibleValue)it.next();
			map.put(pv.getValue(), pv.getDisplayValue());
		}
		return map;
	}
}
