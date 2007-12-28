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

import org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 * Unused???
 */
public class NamedBooleanCellEditor extends LabeledComboBoxCellEditor {
	/**
	 * @param parent
	 * @param items
	 * @param style
	 */
	private NamedBooleanCellEditor(Composite parent, Object[] values,
			String[] labels, int style) {
		super(parent, values, labels, style);
	}

	/**
	 * @param parent
	 * @param style
	 * @param element
	 * @param attribute
	 * @return the new instance
	 */
	public static NamedBooleanCellEditor newInstance(Composite parent,
			int style, IDOMElement element, IAttributeDescriptor attribute) {
		String[] values = new String[] { "", attribute.getAttributeName() }; //$NON-NLS-1$
		return new NamedBooleanCellEditor(parent, values, values, style);
	}
}
