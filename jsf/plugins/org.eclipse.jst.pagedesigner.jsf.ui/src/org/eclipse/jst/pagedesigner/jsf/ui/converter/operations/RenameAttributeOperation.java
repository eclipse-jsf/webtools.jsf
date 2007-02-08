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

public class RenameAttributeOperation extends AbstractTransformOperation {

	private String oldAttributeName;
	private String newAttributeName;

	public RenameAttributeOperation(String oldAttributeName, String newAttributeName) {
		this.oldAttributeName = oldAttributeName;
		this.newAttributeName = newAttributeName;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (curElement != null) {
			Attr oldAttribute = curElement.getAttributeNode(oldAttributeName);
			if (oldAttribute != null) {
				curElement.setAttribute(newAttributeName, oldAttribute.getValue());
				curElement.removeAttribute(oldAttributeName);
			}
		}
		return curElement;
	}

}
