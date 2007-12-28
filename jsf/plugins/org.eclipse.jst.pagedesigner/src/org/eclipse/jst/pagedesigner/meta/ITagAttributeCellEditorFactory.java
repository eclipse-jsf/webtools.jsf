/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.meta;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.w3c.dom.Element;

/**
 * Factory for creating CellEditors and DialogFields to edit element attributes.
 */
public interface ITagAttributeCellEditorFactory {
	/**
	 * create cell editor
	 * 
	 * @param parent
	 * @param attr
	 * @param ele
	 * @return null means failed to create cell editor
	 */
	public CellEditor createCellEditor(Composite parent,
			IPropertyPageDescriptor attr, Element ele);

	/**
	 * Normally, the DialogField for an attribute may appear in the following
	 * places.
	 * <ol>
	 * <li>In the Quick Editor properties view, used to edit an element.
	 * <li>In a dialog to edit an element
	 * <li>In a dialog, to create an element
	 * </ol>
	 * 
	 * It is the caller's responsibility to add valueChanged listener to the
	 * dialog field to decide how to apply the value.
	 * 
	 * It is also the caller's responsibility to set the initial value of the
	 * field.
	 * 
	 * The field should always be an instanceof <code>ISupportTextValue</code>,
	 * it could also optionally implement <code>IElementContextable</code>
	 * 
	 * @param attr
	 *            the attribute descriptor, meta data
	 * @return A dialog field. null means this factory can't create one.
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.ISupportTextValue
	 */
	public DialogField createDialogField(IPropertyPageDescriptor attr);

	/**
	 * The attribute runtime value types supported by this factory.
	 * 
	 * @return null means this factory can behave as default factory.
	 */
	public String[] getSupportedValueTypes();
}
