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
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public abstract class EditorCreator {
	static EditorCreator _instance;

	@SuppressWarnings("deprecation")
    static IBindingHandler _defaultHandler = new BindingHandlerDelegate();

	/**
	 * 
	 */
	public static interface CellEditorHolder {
		/**
		 * @param parent
		 * @return the cell editor
		 */
		public CellEditor createCellEditor(Composite parent);
	}

	/**
	 * Create a dialog field without databinding using the specified attribute
	 * descriptor.
	 * 
	 * @param attr
	 * @return DialogField
	 */
	public abstract DialogField createDialogField(IPropertyPageDescriptor attr);

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
	 * @return DialogField
	 */
	@SuppressWarnings("deprecation")
    public abstract DialogField createDialogFieldWithWrapper(String uri,
			String tagName, IPropertyPageDescriptor attr, IBindingHandler handler);


	/**
	 * @param descriptor
	 * @param handler
	 * @return the dialog field
	 */
	@SuppressWarnings("deprecation")
    public abstract DialogField createDialogFieldWithWrapper(IPropertyPageDescriptor descriptor, IBindingHandler handler);
//	
//	/**
//	 * Create a dialog field that will have databinding support. Basically, this
//	 * method will create a normal dialog field using the attribute descriptor,
//	 * then make a wrapper on it.
//	 *
//	 * @param uri
//	 *            the namespace uri
//	 * @param tagName
//	 *            the local tag name
//	 * @param attrName
//	 * @param handler
//	 *            if null, system default mechanism will be used.
//	 * @return DialogField
//	 */
//	public abstract DialogField createDialogFieldWithWrapper(String uri,
//			String tagName, String attrName, IBindingHandler handler);
	/**
	 * Create a cell editor.
	 * 
	 * @param parent
	 * @param attr
	 * @param element
	 * @return CellEditor
	 */
	public abstract CellEditor createCellEditor(Composite parent,
			IPropertyPageDescriptor attr, IDOMElement element);

	/**
	 * Create a cell editor that will have databinding support.
	 * 
	 * @param parent
	 * @param attr
	 * @param element
	 * @param handler
	 *            if null, system default mechanism will be used.
	 * @return CellEditor
	 */
	@SuppressWarnings("deprecation")
    public abstract CellEditor createCellEditorWithWrapper(Composite parent,
			IPropertyPageDescriptor attr, IDOMElement element,
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
	 * @return CellEditor
	 */
	@SuppressWarnings("deprecation")
    public abstract CellEditor createCellEditorWithWrapper(Composite parent,
			IPropertyPageDescriptor attr, CellEditorHolder holder,
			IDOMElement element, IBindingHandler handler);

	/**
	 * @return the singleton instance
	 */
	public static EditorCreator getInstance() {
		if (_instance == null) {
			_instance = new DefaultEditorCreator();
		}
		return _instance;
	}

	/**
	 * @return the binding handler
	 */
	@SuppressWarnings("deprecation")
    public IBindingHandler getSystemDefaultBindingHandler() {
		return _defaultHandler;
	}
}
