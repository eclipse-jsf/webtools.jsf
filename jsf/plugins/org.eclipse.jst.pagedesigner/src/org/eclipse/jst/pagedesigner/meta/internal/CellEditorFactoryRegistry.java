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
package org.eclipse.jst.pagedesigner.meta.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ISupportTextValue;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StringDialogField;
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;
import org.eclipse.jst.pagedesigner.meta.ITagAttributeCellEditorFactory;
import org.eclipse.jst.pagedesigner.properties.celleditors.CellEditorFactory;
import org.eclipse.swt.widgets.Composite;
import org.w3c.dom.Element;

/**
 * CellEditorFactoryRegistry also read information from plugin.xml extension to
 * allow other plugins to contribute new kinds of cell editors.
 * 
 */
public class CellEditorFactoryRegistry {
//	private static final Logger _log = PDPlugin
//			.getLogger(CellEditorFactoryRegistry.class);

	private static CellEditorFactoryRegistry _instance;

	private Map _factoryMap = new HashMap();

	private List _defaultFactories = new ArrayList();

	/**
	 * @return singleton CellEditorFactoryRegistry
	 */
	public static CellEditorFactoryRegistry getInstance() {
		if (_instance == null) {
			_instance = new CellEditorFactoryRegistry();
		}
		return _instance;
	}

	private CellEditorFactoryRegistry() {
		ITagAttributeCellEditorFactory[] facs = CellEditorFacRegistryReader
				.getAllFactories();
		if (facs != null) {
			for (int i = 0; i < facs.length; i++) {
				addCellEditorFactory(facs[i]);
			}
		}
		addCellEditorFactory(new CellEditorFactory());
	}

	/**
	 * @param fac
	 */
	public void addCellEditorFactory(ITagAttributeCellEditorFactory fac) {
		String[] types = fac.getSupportedValueTypes();
		if (types == null || types.length == 0) {
			_defaultFactories.add(fac);
		} else {
			for (int i = 0; i < types.length; i++) {
				_factoryMap.put(types[i], fac);
			}
		}
	}

	/**
	 * Return cell editor for attribute based upon runtime value type
	 * @param parent
	 * @param attr
	 * @param element
	 * @return CellEditor
	 */
	public CellEditor createCellEditor(Composite parent,
			IPropertyPageDescriptor attr, Element element) {
		String type = attr.getValueType();
		if (type == null || type.length() == 0)
			return null;

		CellEditor result = null;
		ITagAttributeCellEditorFactory fac = (ITagAttributeCellEditorFactory) _factoryMap
				.get(type);
		
		if (fac != null) {
			result = fac.createCellEditor(parent, attr, element);
		}
		if (result == null) {
			for (int i = 0, size = _defaultFactories.size(); i < size; i++) {
				result = ((ITagAttributeCellEditorFactory) _defaultFactories
						.get(i)).createCellEditor(parent, attr, element);
				if (result != null)
					break;
			}
		}
		return result;
	}

	/**
	 * @param attr
	 * @return DialogField
	 */
	public DialogField createDialogField(IPropertyPageDescriptor attr) {
		String type = attr.getValueType();
		if (type == null || type.length() == 0) {
			DialogField result = createTextDialogField(attr);
			result.setLabelText(attr.getLabel()); //labelProvider???
			return result;
		}
//		type = type.toUpperCase();

		DialogField result = null;
		ITagAttributeCellEditorFactory fac = (ITagAttributeCellEditorFactory) _factoryMap
				.get(type);
		if (fac != null) {
			result = fac.createDialogField(attr);
		}
		if (result == null) {
			for (int i = 0, size = _defaultFactories.size(); i < size; i++) {
				result = ((ITagAttributeCellEditorFactory) _defaultFactories
						.get(i)).createDialogField(attr);
				if (result != null) {
					break;
				}
			}
		}
		if (result == null) {
			result = createTextDialogField(attr);
		}
		if (!(result instanceof ISupportTextValue)) {
			result = createTextDialogField(attr);
		}
//		result.setLabelText(attr.getLabel() + ":"); //$NON-NLS-1$
		return result;
	}

	/**
	 * @param attr
	 * @return DialogField
	 */
	public DialogField createTextDialogField(IPropertyPageDescriptor attr) {
		StringDialogField field = new StringDialogField();
		field.setLabelText(attr.getLabel());
		field.setRequired(attr.isRequired());
		field.setToolTip(attr.getDescription());
		return field;
	}

}
