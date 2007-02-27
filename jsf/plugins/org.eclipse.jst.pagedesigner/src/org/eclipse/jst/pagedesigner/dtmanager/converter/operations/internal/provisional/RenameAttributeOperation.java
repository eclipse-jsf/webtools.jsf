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
package org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/**
 * ITransformOperation implementation that renames an attribute of the current
 * Element.
 * 
 * @author Ian Trimble - Oracle
 */
public class RenameAttributeOperation extends AbstractTransformOperation {

	private String oldAttributeName;
	private String newAttributeName;

	/**
	 * Constructs an instance with the specified old and new attribute names.
	 * 
	 * @param oldAttributeName Old name of the attribute to be renamed.
	 * @param newAttributeName New name of the attribute to be renamed.
	 */
	public RenameAttributeOperation(String oldAttributeName, String newAttributeName) {
		this.oldAttributeName = oldAttributeName;
		this.newAttributeName = newAttributeName;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
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
