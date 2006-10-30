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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.common.dialogfield.DialogField;
import org.eclipse.jst.pagedesigner.common.dialogfield.ISupportTextValue;
import org.eclipse.jst.pagedesigner.common.dialogfield.StringDialogField;
import org.eclipse.jst.pagedesigner.common.logging.Logger;
import org.eclipse.jst.pagedesigner.meta.IAttributeCellEditorFactory;
import org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor;
import org.eclipse.jst.pagedesigner.meta.IValueType;
import org.eclipse.jst.pagedesigner.properties.celleditors.CellEditorFactory;
import org.eclipse.swt.widgets.Composite;
import org.w3c.dom.Element;

/**
 * CellEditorFactoryRegistry also read information from plugin.xml extension to
 * allow other plugins to contribute new kinds of cell editors.
 * 
 * @author mengbo
 */
public class CellEditorFactoryRegistry {
	private static final Logger _log = PDPlugin
			.getLogger(CellEditorFactoryRegistry.class);

	private static CellEditorFactoryRegistry _instance;

	private Map _factoryMap = new HashMap();

	private List _defaultFactories = new ArrayList();

	public static CellEditorFactoryRegistry getInstance() {
		if (_instance == null) {
			_instance = new CellEditorFactoryRegistry();
		}
		return _instance;
	}

	private CellEditorFactoryRegistry() {
		IAttributeCellEditorFactory[] facs = CellEditorFacRegistryReader
				.getAllFactories();
		if (facs != null) {
			for (int i = 0; i < facs.length; i++) {
				addCellEditorFactory(facs[i]);
			}
		}
		addCellEditorFactory(new CellEditorFactory());
	}

	public void addCellEditorFactory(IAttributeCellEditorFactory fac) {
		String[] types = fac.getSupportedValueTypes();
		if (types == null || types.length == 0) {
			_defaultFactories.add(fac);
		} else {
			for (int i = 0; i < types.length; i++) {
				_factoryMap.put(types[i].toUpperCase(), fac);
			}
		}
	}

	public CellEditor createCellEditor(Composite parent,
			IAttributeDescriptor attr, Element element) {
		String type = attr.getValueType();
		if (type == null || type.length() == 0)
			return null;
		type = type.toUpperCase();

		CellEditor result = null;
		IAttributeCellEditorFactory fac = (IAttributeCellEditorFactory) _factoryMap
				.get(type);
		if (fac != null) {
			result = fac.createCellEditor(parent, attr, element);
		}
		if (result == null) {
			for (int i = 0, size = _defaultFactories.size(); i < size; i++) {
				result = ((IAttributeCellEditorFactory) _defaultFactories
						.get(i)).createCellEditor(parent, attr, element);
				if (result != null)
					break;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param attr
	 * @param context
	 * @param ele
	 * @return will never be null
	 */
	public DialogField createDialogField(IAttributeDescriptor attr) {
		String type = attr.getValueType();
		if (type == null || type.length() == 0) {
			DialogField result = createTextDialogField(attr);
			result.setLabelText(attr.getLabelString() + ":"); //$NON-NLS-1$
			return result;
		}
		type = type.toUpperCase();

		DialogField result = null;
		IAttributeCellEditorFactory fac = (IAttributeCellEditorFactory) _factoryMap
				.get(type);
		if (fac != null) {
			result = fac.createDialogField(attr);
		}
		if (result == null) {
			for (int i = 0, size = _defaultFactories.size(); i < size; i++) {
				result = ((IAttributeCellEditorFactory) _defaultFactories
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
		result.setLabelText(attr.getLabelString() + ":"); //$NON-NLS-1$
		return result;
	}

	/**
	 * @param attr
	 * @param context
	 * @param ele
	 * @return
	 */
	public DialogField createTextDialogField(IAttributeDescriptor attr) {
		StringDialogField field = new StringDialogField();
		field.setLabelText(attr.getLabelString());
		field.setRequired(attr.isRequired());
		field.setToolTip(attr.getDescription());
		return field;
	}

	/**
	 * This is NOT a product method. It should only be used by testing code.
	 * 
	 * @return
	 */
	public String[] getAllValueTypes() {
		Set valueTypes = new HashSet();
		for (Iterator iter = _factoryMap.values().iterator(); iter.hasNext();) {
			IAttributeCellEditorFactory fac = (IAttributeCellEditorFactory) iter
					.next();
			String[] supportedTypes = fac.getSupportedValueTypes();

			if (supportedTypes != null) {
				for (int i = 0; i < supportedTypes.length; i++) {
					valueTypes.add(supportedTypes[i]);
				}
			}
		}
		// add those default ones.
		Field[] fields = IValueType.class.getFields();
		for (int i = 0; i < fields.length; i++) {
			int modifiers = fields[i].getModifiers();
			if (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)) {
				if (fields[i].getType() == String.class) {
					try {
						valueTypes.add((String) fields[i].get(null));
					} catch (IllegalArgumentException ex) {
						// "Error in fields retrieving:"
						_log.info("CellEditorFactoryRegistry.Info.2", ex); //$NON-NLS-1$
					} catch (IllegalAccessException ex) {
						// "Error in fields retrieving:"
						_log.info("CellEditorFactoryRegistry.Info.3", ex); //$NON-NLS-1$
					}
				}
			}
		}
		String[] ret = new String[valueTypes.size()];
		valueTypes.toArray(ret);
		return ret;
	}
}
