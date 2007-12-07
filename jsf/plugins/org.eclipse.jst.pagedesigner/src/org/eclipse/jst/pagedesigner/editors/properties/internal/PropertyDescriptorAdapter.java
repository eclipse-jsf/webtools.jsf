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
package org.eclipse.jst.pagedesigner.editors.properties.internal;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

/**
 * Adapts the MetadataEnabledFeature <code>org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor</code>, to a <code>org.eclipse.ui.views.properties.IPropertyDescriptor</code> 
 *
 */
public class PropertyDescriptorAdapter implements IPropertyDescriptor{

	private IPropertyPageDescriptor _ppd;

	/**
	 * Constructor
	 * @param propertyPageDescriptor
	 */
	public PropertyDescriptorAdapter(IPropertyPageDescriptor propertyPageDescriptor) {
		_ppd = propertyPageDescriptor;
	}

	public CellEditor createPropertyEditor(Composite parent) {
		return _ppd.getCellEditor(parent);
	}

	public String getCategory() {
		return _ppd.getCategory();
	}

	public String getDescription() {
		return _ppd.getDescription();
	}

	public String getDisplayName() {
		return _ppd.getAttributeName();
	}

	public String[] getFilterFlags() {
		return null;
	}

	public Object getHelpContextIds() {
		return null;//FIXME
	}

	public Object getId() {
		return _ppd.getAttributeName();
	}

	public ILabelProvider getLabelProvider() {	
		return null;//FIXME: do better?  Used to supply labels for attribute property sheet (not quick edit)
	}

	public boolean isCompatibleWith(IPropertyDescriptor anotherProperty) {
		return false;
	}

}
