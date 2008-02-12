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
import org.eclipse.jst.pagedesigner.meta.internal.CellEditorFactoryRegistry;
import org.eclipse.jst.pagedesigner.ui.dialogfields.DialogFieldWrapper;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public class DefaultEditorCreator extends EditorCreator {
	static IPropertyPageDescriptor _staticAttr;

	static IDOMElement _staticElement;

	//static IBindingHandler _staticHandler;

	static CellEditorHolder _staticHolder;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.meta.EditorCreator#createCellEditor(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.jst.pagedesigner.meta.IPropertyPageDescriptor,
	 *      org.w3c.dom.Element)
	 */
	public CellEditor createCellEditor(Composite parent,
			IPropertyPageDescriptor attr, IDOMElement element) {
		return CellEditorFactoryRegistry.getInstance().createCellEditor(parent,
				attr, element);
	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.eclipse.jst.pagedesigner.meta.EditorCreator#createDialogField(org.eclipse.jst.pagedesigner.meta.IPropertyPageDescriptor)
//	 */
//	public DialogField createDialogField(IPropertyPageDescriptor attr) {
//		return CellEditorFactoryRegistry.getInstance().createDialogField(attr);
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.meta.EditorCreator#createCellEditorWithWrapper(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.jst.pagedesigner.meta.IPropertyPageDescriptor,
	 *      org.w3c.dom.Element,
	 *      org.eclipse.jst.pagedesigner.meta.IBindingHandler,
	 *      org.eclipse.swt.graphics.Image)
	 */
	@SuppressWarnings("deprecation")
    public CellEditor createCellEditorWithWrapper(Composite parent,
			IPropertyPageDescriptor attr, final IDOMElement element,
			IBindingHandler handler1) {
//		final IBindingHandler handler = (handler1 == null ? getSystemDefaultBindingHandler()
//				: handler1);
//		String uri = CMUtil.getElementNamespaceURI(element);
//		String tagName = element.getLocalName();
		
		return createCellEditor(parent, attr, element);
//		
//		if (!handler.isEnabled(element, element, uri, tagName, attr)) {
//			// should not enabled, so directly return original cell editor
//			return createCellEditor(parent, attr, element);
//		}
//		try {
//			// since "createWrappedCellEditor()" and "getBindingImage()" is
//			// called from the constructor of CellEditorWrapper, at that time,
//			// can't reference this DefaultEditorCreator and final fields yet,
//			// so use static variable for it.
//			_staticAttr = attr;
//			_staticElement = element;
//			_staticHandler = handler;
//
//			return new CellEditorWrapper(parent) {
//				/*
//				 * (non-Javadoc)
//				 * 
//				 * @see org.eclipse.jst.pagedesigner.properties.celleditors.CellEditorWrapper#createWrappedCellEditor(org.eclipse.swt.widgets.Composite)
//				 */
//				protected CellEditor createWrappedCellEditor(Composite cell) {
//					return EditorCreator.getInstance().createCellEditor(cell,
//							_staticAttr, _staticElement);
//				}
//
//				/*
//				 * (non-Javadoc)
//				 * 
//				 * @see org.eclipse.jst.pagedesigner.properties.celleditors.CellEditorWrapper#openDialogBox(org.eclipse.swt.widgets.Control)
//				 */
//				protected Object openDialogBox(Control cellEditorWindow) {
//					return handler.handleBinding(cellEditorWindow.getShell(),
//							element, element, convertToString(this.getValue()));
//				}
//
//				/*
//				 * (non-Javadoc)
//				 * 
//				 * @see org.eclipse.jst.pagedesigner.properties.celleditors.CellEditorWrapper#getBindingImage()
//				 */
//				protected Image getBindingImage() {
//					return _staticHandler.getImage();
//				}
//			};
//		} finally {
//			_staticAttr = null;
//			_staticElement = null;
//			_staticHandler = null;
//			_staticHolder = null;
//		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.meta.EditorCreator#createCellEditorWithWrapper(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.jst.pagedesigner.meta.IPropertyPageDescriptor,
	 *      org.eclipse.jst.pagedesigner.meta.EditorCreator.CellEditorHolder,
	 *      org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement,
	 *      org.eclipse.jst.pagedesigner.meta.IBindingHandler)
	 */
	@SuppressWarnings("deprecation")
    public CellEditor createCellEditorWithWrapper(Composite parent,
			IPropertyPageDescriptor attr, CellEditorHolder holder,
			final IDOMElement element, IBindingHandler handler1) {
//		final IBindingHandler handler = (handler1 == null ? getSystemDefaultBindingHandler()
//				: handler1);
//		String uri = CMUtil.getElementNamespaceURI(element);
//		String tagName = element.getLocalName();
		
		return holder.createCellEditor(parent);
		
//		if (!handler.isEnabled(element, element, uri, tagName, attr)) {
//			// should not enabled, so directly return original cell editor
//			return holder.createCellEditor(parent);
//		}
//		try {
//			// since "createWrappedCellEditor()" and "getBindingImage()" is
//			// called from the constructor of CellEditorWrapper, at that time,
//			// can't reference this DefaultEditorCreator and final fields yet,
//			// so use static variable for it.
//			_staticElement = element;
//			_staticHandler = handler;
//			_staticHolder = holder;
//
//			return new CellEditorWrapper(parent) {
//				/*
//				 * (non-Javadoc)
//				 * 
//				 * @see org.eclipse.jst.pagedesigner.properties.celleditors.CellEditorWrapper#createWrappedCellEditor(org.eclipse.swt.widgets.Composite)
//				 */
//				protected CellEditor createWrappedCellEditor(Composite cell) {
//					return _staticHolder.createCellEditor(cell);
//				}
//
//				/*
//				 * (non-Javadoc)
//				 * 
//				 * @see org.eclipse.jst.pagedesigner.properties.celleditors.CellEditorWrapper#openDialogBox(org.eclipse.swt.widgets.Control)
//				 */
//				protected Object openDialogBox(Control cellEditorWindow) {
//					return handler.handleBinding(cellEditorWindow.getShell(),
//							element, element, convertToString(this.getValue()));
//				}
//
//				/*
//				 * (non-Javadoc)
//				 * 
//				 * @see org.eclipse.jst.pagedesigner.properties.celleditors.CellEditorWrapper#getBindingImage()
//				 */
//				protected Image getBindingImage() {
//					return _staticHandler.getImage();
//				}
//			};
//		} finally {
//			_staticAttr = null;
//			_staticElement = null;
//			_staticHandler = null;
//			_staticHolder = null;
//		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.meta.EditorCreator#createDialogFieldWithWrapper(java.lang.String,
	 *      java.lang.String,
	 *      org.eclipse.jst.pagedesigner.meta.IPropertyPageDescriptor,
	 *      org.eclipse.jst.pagedesigner.meta.IBindingHandler)
	 */
	@SuppressWarnings("deprecation")
    public DialogField createDialogFieldWithWrapper(String uri, String tagName,
			IPropertyPageDescriptor attr, IBindingHandler handler1) {
		final IBindingHandler handler = (handler1 == null ? getSystemDefaultBindingHandler()
				: handler1);
		DialogField field = createDialogField(attr);
		// if (field instanceof StringButtonDialogField)
		// {
		// ((StringButtonDialogField) field).setButtonLabel("...");
		// }
		DialogFieldWrapper wrapper = new DialogFieldWrapper(field, handler
				.getImage(), handler.getDisabledImage(), uri, tagName, attr,
				handler);
		wrapper.setDatabindingEnabled(true);

		return wrapper;
	}


//	private String convertToString(Object value) {
//		if (value == null) {
//			return null;
//		}
//        return value.toString();
//	}

	@SuppressWarnings("deprecation")
    @Override
	public DialogField createDialogFieldWithWrapper(
			IPropertyPageDescriptor attr, IBindingHandler handler1) {
		final IBindingHandler handler = (handler1 == null ? getSystemDefaultBindingHandler()
				: handler1);
		DialogField field = createDialogField(attr);
		// if (field instanceof StringButtonDialogField)
		// {
		// ((StringButtonDialogField) field).setButtonLabel("...");
		// }
		DialogFieldWrapper wrapper = new DialogFieldWrapper(field, handler
				.getImage(), handler.getDisabledImage(), attr.getUri(), attr.getTagName(), attr,
				handler);
		wrapper.setDatabindingEnabled(true);

		return wrapper;
	}

	@Override
	public DialogField createDialogField(IPropertyPageDescriptor attr) {
		return CellEditorFactoryRegistry.getInstance().createDialogField(attr);		
	}

}
