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
package org.eclipse.jst.pagedesigner.meta;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.pagedesigner.meta.internal.DefaultEditorCreator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public abstract class EditorCreator {
	static EditorCreator _instance;

	static IBindingHandler _defaultHandler = new BindingHandlerDelegate();

	public static interface CellEditorHolder {
		public CellEditor createCellEditor(Composite parent);
	}

	/**
	 * Create a dialog field without databinding using the specified attribute
	 * descriptor.
	 * 
	 * @param attr
	 * @return
	 */
	public abstract DialogField createDialogField(IAttributeDescriptor attr);

	/**
	 * Create a dialog field that will have databinding support. Basically, this
	 * method will create a normal dialog field using the attribute descriptor,
	 * then make a wrapper on it.
	 * 
	 * @param uri
	 *            the namespace uri
	 * @param tagName
	 *            the local tag name
	 * @param attr
	 * @param handler
	 *            if null, system default mechanism will be used.
	 * @return
	 */
	public abstract DialogField createDialogFieldWithWrapper(String uri,
			String tagName, IAttributeDescriptor attr, IBindingHandler handler);

	/**
	 * Create a cell editor.
	 * 
	 * @param parent
	 * @param attr
	 * @param element
	 * @return
	 */
	public abstract CellEditor createCellEditor(Composite parent,
			IAttributeDescriptor attr, IDOMElement element);

	/**
	 * Create a cell editor that will have databinding support.
	 * 
	 * @param parent
	 * @param attr
	 * @param element
	 * @param handler
	 *            if null, system default mechanism will be used.
	 * @return
	 */
	public abstract CellEditor createCellEditorWithWrapper(Composite parent,
			IAttributeDescriptor attr, IDOMElement element,
			IBindingHandler handler);

	/**
	 * Create a cell edtior that will have databinding support. This method
	 * don't provide an attribute descriptor, but it provide a CellEditorHolder
	 * to create whatever normal cell editor it wants.
	 * 
	 * @param parent
	 * @param attr
	 *            could be null
	 * @param holder
	 * @param element
	 * @param handler
	 *            if null, system default mechanism will be used.
	 * @return
	 */
	public abstract CellEditor createCellEditorWithWrapper(Composite parent,
			IAttributeDescriptor attr, CellEditorHolder holder,
			IDOMElement element, IBindingHandler handler);

	public static EditorCreator getInstance() {
		if (_instance == null) {
			_instance = new DefaultEditorCreator();
		}
		return _instance;
	}

	public IBindingHandler getSystemDefaultBindingHandler() {
		return _defaultHandler;
	}
}
