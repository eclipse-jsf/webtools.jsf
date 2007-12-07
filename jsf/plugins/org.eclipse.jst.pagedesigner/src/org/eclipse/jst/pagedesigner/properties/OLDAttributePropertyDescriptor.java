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
package org.eclipse.jst.pagedesigner.properties;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor;
import org.eclipse.jst.pagedesigner.meta.XXXEditorCreator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Element;

/**
 * This is the PropertyDescriptor for an attribute. Getting information from a
 * IAttributeDescriptor and an IPropertyDescriptor.
 * 
 * @author mengbo
 * @deprecated
 */
public class OLDAttributePropertyDescriptor implements IPropertyDescriptor {
	private IAttributeDescriptor _attr;

	private IPropertyDescriptor _inner; // this could be null

	private Element _element;

	/**
	 * @param element
	 *            the owner of the attribute.
	 * @param descriptor
	 *            can't be null
	 * @param desc
	 *            could be null
	 */
	public OLDAttributePropertyDescriptor(Element element,
			IAttributeDescriptor descriptor, IPropertyDescriptor desc) {
		_element = element;
		_attr = descriptor;
		_inner = desc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
	 */
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor;
		editor = XXXEditorCreator.getInstance().createCellEditorWithWrapper(
				parent, _attr, (IDOMElement) _element, null);

		if (editor != null) {
			return editor;
		}
        XXXEditorCreator.CellEditorHolder holder = new XXXEditorCreator.CellEditorHolder() {
        	/*
        	 * (non-Javadoc)
        	 * 
        	 * @see org.eclipse.jst.pagedesigner.meta.EditorCreator.CellEditorHolder#createCellEditor(org.eclipse.swt.widgets.Composite)
        	 */
        	public CellEditor createCellEditor(Composite parent1) {
        		if (_inner != null) {
        			return _inner.createPropertyEditor(parent1);
        		}
                return new TextCellEditor(parent1);
        	}
        };
        return XXXEditorCreator.getInstance().createCellEditorWithWrapper(
        		parent, _attr, holder, (IDOMElement) _element, null);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getCategory()
	 */
	public String getCategory() {
		String cat = _attr.getCategory();
		if (cat != null) {
			return cat;
		}
        return ITabbedPropertiesConstants.OTHER_CATEGORY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getDescription()
	 */
	public String getDescription() {
		String s = _attr.getDescription();
		if (s == null && _inner != null) {
			return _inner.getDescription();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getDisplayName()
	 */
	public String getDisplayName() {
		return _attr.getAttributeName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getFilterFlags()
	 */
	public String[] getFilterFlags() {
		if (_inner != null) {
			return _inner.getFilterFlags();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getHelpContextIds()
	 */
	public Object getHelpContextIds() {
		if (_inner != null) {
			return _inner.getHelpContextIds();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getId()
	 */
	public Object getId() {
		return _attr.getAttributeName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getLabelProvider()
	 */
	public ILabelProvider getLabelProvider() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#isCompatibleWith(org.eclipse.ui.views.properties.IPropertyDescriptor)
	 */
	public boolean isCompatibleWith(IPropertyDescriptor anotherProperty) {
		return false;
	}

}
