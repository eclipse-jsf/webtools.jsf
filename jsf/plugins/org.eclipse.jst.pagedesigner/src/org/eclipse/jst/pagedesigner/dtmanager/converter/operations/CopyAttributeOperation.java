/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.dtmanager.converter.operations;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/**
 * Copies a single attribute from the source Element instance to the current
 * Element instance, and optionally creates a new attribute on the current
 * Element instance if no such attribute exists on the source Element instance.
 * 
 * @author Ian Trimble - Oracle
 * API: should this be public or should we restrict so can only be constructed
 * through a factory?
 */
public class CopyAttributeOperation extends AbstractTransformOperation {

	private String attributeName;
	private boolean create;
	private String newAttributeValue;

	/**
	 * Constructs an instance with the specified attribute name, and defaults
	 * to not creating an attribute unless the named attribute exists on the
	 * source Element instance.
	 * 
	 * @param attributeName Name of attribute to be copied.
	 */
	public CopyAttributeOperation(String attributeName) {
		this(attributeName, false, null);
	}

	/**
	 * Constructs an instance with the specified attribute name and optionally
	 * forces creation of a new attribute on the current Element even if the
	 * named attribute does not exist on the source Element instance.
	 * 
	 * @param attributeName Name of attribute to be copied.
	 * @param create If true, create attribute on the current Element even if
	 * the named attribute does not exist on the source Element.
	 * @param newAttributeValue Value to set for the new attribute if not found
	 * on the source Element. 
	 */
	public CopyAttributeOperation(String attributeName, boolean create, String newAttributeValue) {
		this.attributeName = attributeName;
		this.create = create;
		this.newAttributeValue = newAttributeValue;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (srcElement != null && curElement != null) {
			Attr attribute = srcElement.getAttributeNode(attributeName);
			if (attribute != null) {
				curElement.setAttribute(attributeName, attribute.getValue());
			} else if (create && newAttributeValue != null) {
				curElement.setAttribute(attributeName, newAttributeValue);
			}
		}
		return curElement;
	}

}
