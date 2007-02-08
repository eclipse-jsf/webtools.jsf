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
package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

public class CopyAttributeOperation extends AbstractTransformOperation {

	private String attributeName;
	private boolean create;
	private String newAttributeValue;

	public CopyAttributeOperation(String attributeName) {
		this(attributeName, false, null);
	}

	public CopyAttributeOperation(String attributeName, boolean create, String newAttributeValue) {
		this.attributeName = attributeName;
		this.create = create;
		this.newAttributeValue = newAttributeValue;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
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
