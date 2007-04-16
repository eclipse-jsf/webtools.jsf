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

import org.w3c.dom.Element;

/**
 * ITransformOperation implementation that creates a new attribute on the
 * current Element.
 * 
 * @author Ian Trimble - Oracle
 * API: should this be public or should we restrict so can only be constructed
 * through a factory?
 */
public class CreateAttributeOperation extends AbstractTransformOperation {

	private String attributeName;
	private String attributeValue;

	/**
	 * Constructs an instance with the specified attribute name and value.
	 * 
	 * @param attributeName Name of attribute to be created.
	 * @param attributeValue Value of attribute to be set.
	 */
	public CreateAttributeOperation(String attributeName, String attributeValue) {
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (curElement != null) {
			curElement.setAttribute(attributeName, attributeValue);
		}
		return curElement;
	}

}
