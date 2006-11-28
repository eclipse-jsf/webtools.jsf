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
package org.eclipse.jst.pagedesigner.properties.attrgroup;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldGroup;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogFieldApplyListener;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogFieldChangeListener;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ISupportTextValue;
import org.eclipse.jst.pagedesigner.meta.AttributeDescriptor;
import org.eclipse.jst.pagedesigner.meta.EditorCreator;
import org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor;
import org.eclipse.jst.pagedesigner.meta.IElementDescriptor;
import org.eclipse.jst.pagedesigner.meta.internal.CMRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

/**
 * @author mengbo
 * @version 1.5
 */
public class AttributeGroup extends DialogFieldGroup {
	private static final Object KEY_ATTR = "KEY_ATTR"; //$NON-NLS-1$

	private String _uri;

	private String _tagName;

	private IAttributeDescriptor[] _attrs;

	private String _helpContextId;

	private List _dialogFields = null;

	private IDOMElement _ownerElement;

	/**
	 * @param pageName
	 * @param title
	 * @param titleImage
	 */
	public AttributeGroup(String uri, String tagName, String[] attrNames) {
		this._uri = uri;
		this._tagName = tagName;
		this._attrs = prepareAttributeDescriptors(uri, tagName, attrNames);
	}

	public String getTagName() {
		return this._tagName;
	}

	/**
	 * @return
	 */
	public String getURI() {
		return this._uri;
	}

	/**
	 * @param uri
	 * @param tagName
	 * @param attrNames
	 */
	private IAttributeDescriptor[] prepareAttributeDescriptors(String uri,
			String tagName, String[] attrNames) {
		IAttributeDescriptor[] attrs;
		IElementDescriptor eleDesc = CMRegistry.getInstance()
				.getElementDescriptor(uri, tagName);
		if (eleDesc != null) {
			_helpContextId = eleDesc.getHelpContextID();
			if (attrNames == null) {
				attrs = eleDesc.getAttributeDescriptors();
			} else {
				attrs = new IAttributeDescriptor[attrNames.length];
				for (int i = 0; i < attrs.length; i++) {
					attrs[i] = eleDesc.getAttributeDescriptor(attrNames[i]);
					if (attrs[i] == null) {
						attrs[i] = new AttributeDescriptor(attrNames[i]);
					}
				}
			}
		} else {
			if (attrNames == null) {
				attrs = new IAttributeDescriptor[0];
			} else {
				attrs = new IAttributeDescriptor[attrNames.length];
				for (int i = 0; i < attrs.length; i++) {
					attrs[i] = new AttributeDescriptor(attrNames[i]);
				}
			}
		}
		return attrs;
	}

	public IAttributeDescriptor getAttributeDescriptor(DialogField field) {
		Object obj = field.getAttachedData(KEY_ATTR);
		if (obj instanceof IAttributeDescriptor) {
			return (IAttributeDescriptor) obj;
		}
        return null;
	}

	/**
	 * Child class could override this method to create customized dialogField.
	 * Otherwise, system will use meta data to create default dailog field.
	 * 
	 * @param uri
	 * @param tag
	 * @param attr
	 * @return if null, system will create default dialog field.
	 */
	protected DialogField createDialogField(String uri, String tag,
			IAttributeDescriptor attr) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldGroup#initialize()
	 */
	public void initialize() {
		if (_dialogFields == null) {
			_dialogFields = new ArrayList();

			IAttributeDescriptor[] descriptors = _attrs;

			for (int i = 0; i < descriptors.length; i++) {
				DialogField field;
				field = createDialogField(this._uri, this._tagName,
						descriptors[i]);
				if (field == null) {
					EditorCreator creator = EditorCreator.getInstance();
					field = creator.createDialogFieldWithWrapper(this._uri,
							this._tagName, descriptors[i], null);
				}

				field.putAttachedData(KEY_ATTR, descriptors[i]);

				IDialogFieldApplyListener applyListener = getDialogFieldApplyListener(
						this._uri, this._tagName, descriptors[i]);
				if (applyListener == null) {
					applyListener = getDefaultApplyListener();
				}
				field.setDialogFieldApplyListener(applyListener);

				IDialogFieldChangeListener changeListener = getDialogFieldChangeListener(
						this._uri, this._tagName, descriptors[i]);
				if (changeListener == null) {
					changeListener = getDefaultChangeListener();
				}
				field.setDialogFieldChangeListener(changeListener);
				_dialogFields.add(field);
			}
		}
	}

	/**
	 * Child class can override the method to provide listener implementation
	 * 
	 * @param uri
	 * @param tag
	 * @param attr
	 * @return
	 */
	public IDialogFieldApplyListener getDialogFieldApplyListener(String uri,
			String tag, IAttributeDescriptor attr) {
		return null;
	}

	/**
	 * Child class can override the method to provide listener implementation
	 * 
	 * @param uri
	 * @param tag
	 * @param attr
	 * @return
	 */
	public IDialogFieldChangeListener getDialogFieldChangeListener(String uri,
			String tag, IAttributeDescriptor attr) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldGroup#refreshData()
	 */
	public void refreshData() {
		if (_ownerElement == null) {
			return;
		}
		initialize();
		for (int i = 0, size = _dialogFields.size(); i < size; i++) {
			DialogField field = (DialogField) _dialogFields.get(i);

			ISupportTextValue textValue = (ISupportTextValue) field;
			IAttributeDescriptor attr = (IAttributeDescriptor) field
					.getAttachedData(KEY_ATTR);
			String attrName = attr.getAttributeName();
			String attrValue = _ownerElement.getAttribute(attrName);
			textValue.setTextWithoutUpdate(attrValue);
		}
	}

	public IDOMElement getElement() {
		return _ownerElement;
	}

	public void setElementContext(IDOMNode context, IDOMElement owner) {
		initialize();
		this._ownerElement = owner;
		if (context != null) {
			for (int i = 0, size = _dialogFields.size(); i < size; i++) {
				DialogField field = (DialogField) _dialogFields.get(i);
				if (field instanceof IElementContextable) {
					((IElementContextable) field).setElementContext(context,
							owner);
				}
			}
		}
		refreshData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldGroup#layoutDialogFields(org.eclipse.ui.forms.widgets.FormToolkit,
	 *      org.eclipse.swt.widgets.Composite)
	 */
	public void layoutDialogFields(FormToolkit toolkit, Composite parent) {
		Composite top;
		if (toolkit == null) {
			top = new Composite(parent, SWT.NONE);
		} else {
			top = toolkit.createComposite(parent);
		}
		FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		parent.setLayout(fillLayout);

		if (this._helpContextId != null && this._helpContextId.length() > 0) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(top,
					_helpContextId);
		}

		GridLayout layout = new GridLayout();
		int numColumns = getNumColumns();
		layout.numColumns = numColumns;
		top.setLayout(layout);

		initialize();
		for (int i = 0, size = _dialogFields.size(); i < size; i++) {
			DialogField field = (DialogField) _dialogFields.get(i);
			field.doFillIntoGrid(toolkit, top, numColumns);
		}

		DialogField maxColumnField = null;
		int maxColumn = 0;
		for (int i = 0, size = _dialogFields.size(); i < size; i++) {
			DialogField field = (DialogField) _dialogFields.get(i);
			int c = field.getNumberOfControls();
			if (c > maxColumn) {
				maxColumn = c;
				maxColumnField = field;
			}
		}
		if (maxColumnField != null) {
			maxColumnField.handleGrabHorizontal();
		}
	}

	/**
	 * @return
	 */
	public int getNumColumns() {
		int columns = 1;
		initialize();
		for (int i = 0, size = _dialogFields.size(); i < size; i++) {
			DialogField field = (DialogField) _dialogFields.get(i);
			columns = Math.max(columns, field.getNumberOfControls());
		}
		return columns;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldGroup#validateDialogFields()
	 */
	public IStatus[] validateDialogFields() {
		return null;
	}

	/**
	 * @return
	 */
	public DialogField[] getDialogFields() {
		initialize();
		DialogField[] ret = new DialogField[_dialogFields.size()];
		_dialogFields.toArray(ret);
		return ret;
	}

	/**
	 * get the dialogfield for the corresponding attribute.
	 * 
	 * @param attrName
	 *            case sensitive attribute name.
	 * @return null if fail to find.
	 */
	public DialogField getDialogField(String attrName) {
		initialize();
		for (int i = 0, size = _dialogFields.size(); i < size; i++) {
			DialogField field = (DialogField) _dialogFields.get(i);
			IAttributeDescriptor attr = this.getAttributeDescriptor(field);
			if (attr != null && attr.getAttributeName().equals(attrName)) {
				return field;
			}
		}
		return null;
	}

}
