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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.commands.single.ChangeAttributeCommand;
import org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor;
import org.eclipse.jst.pagedesigner.meta.ICMRegistry;
import org.eclipse.jst.pagedesigner.meta.IElementDescriptor;
import org.eclipse.jst.pagedesigner.meta.internal.CMRegistry;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Element;

/**
 * 
 * @author mengbo
 */
public class AttributePropertySource implements IPropertySource {
	private IDOMElement _element;

	private IPropertySource _innerSource;

	/**
	 * @param ele
	 * @param source
	 */
	public AttributePropertySource(Element ele, IPropertySource source) {
		_element = (IDOMElement) ele;
		_innerSource = source;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
	 */
	public Object getEditableValue() {
		return _innerSource.getEditableValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		// CR377844: when the attribute in source is "a&gt;b", we would like to
		// display
		// "a>b" in cell editor. But _innerSource.getPropertyValue(id) will
		// return the source
		// of the attribute, so can't use that here.
		// read QTS log for detail.
		// return _innerSource.getPropertyValue(id);
		if (id == null) {
			return null;
		}
		String name = id.toString();
		String value = _element.getAttribute(name);
		if (value == null) {
			value = ""; //$NON-NLS-1$
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
	 */
	public boolean isPropertySet(Object id) {
		return _innerSource.isPropertySet(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
	 */
	public void resetPropertyValue(Object id) {
		_innerSource.resetPropertyValue(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object,
	 *      java.lang.Object)
	 */
	public void setPropertyValue(final Object id, final Object value) {
		Object oldValue = getPropertyValue(id);
		if (oldValue == value || (oldValue != null && oldValue.equals(value))) {
			return;
		}
		Command c = new ChangeAttributeCommand(
				PDPlugin
						.getResourceString("AttributePropertySource.CommandLabel.ChangeAttribute"), _element, (String) id, (String) value); //$NON-NLS-1$
		c.execute();
	}

	/**
	 * the major job of this wrapper is to provide
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		IElementDescriptor elementDescriptor = getElementDescriptor();
		List result = new ArrayList();

		if (elementDescriptor == null) {
			IPropertyDescriptor[] original = _innerSource
					.getPropertyDescriptors();
			for (int i = 0; i < original.length; i++) {
				result
						.add(new PropertyDescriptorWrapper(_element,
								original[i]));
			}
		} else {
			// put the inner property descriptors into a map.
			Map map = new HashMap();
			IPropertyDescriptor[] descs = _innerSource.getPropertyDescriptors();
			if (descs != null) {
				for (int i = 0; i < descs.length; i++) {
					map.put(descs[i].getId(), descs[i]);
				}
			}

			IAttributeDescriptor[] attrs = elementDescriptor
					.getAttributeDescriptors();
			if (attrs != null) {
				for (int i = 0; i < attrs.length; i++) {
					IPropertyDescriptor desc = (IPropertyDescriptor) map
							.remove(attrs[i].getAttributeName());
					result.add(new AttributePropertyDescriptor(_element,
							attrs[i], desc));
				}
			}
			// ok, we have handled all attributes declared in ElementDescriptor,
			// let's see the remaining
			for (Iterator iter = map.values().iterator(); iter.hasNext();) {
				IPropertyDescriptor desc = (IPropertyDescriptor) iter.next();
				IAttributeDescriptor attr = findReferencedAttribute(
						elementDescriptor, desc);
				if (attr != null) {
					result.add(new AttributePropertyDescriptor(_element, attr,
							desc));
				} else {
					result.add(new PropertyDescriptorWrapper(
							_element, desc));
				}
			}
		}
		IPropertyDescriptor[] ret = new IPropertyDescriptor[result.size()];
		result.toArray(ret);
		return ret;
	}

	private IAttributeDescriptor findReferencedAttribute(
			IElementDescriptor elementDescriptor, IPropertyDescriptor desc) {
		return null;
	}

	private IElementDescriptor getElementDescriptor() {
		ICMRegistry registry = CMRegistry.getInstance();
		String uri = CMUtil.getElementNamespaceURI(_element);
		return registry.getElementDescriptor(uri, _element.getLocalName());
	}

}
