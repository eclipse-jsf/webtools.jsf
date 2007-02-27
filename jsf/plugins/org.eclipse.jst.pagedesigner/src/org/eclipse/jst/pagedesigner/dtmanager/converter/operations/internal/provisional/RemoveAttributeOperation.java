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

import org.w3c.dom.Element;

/**
 * ITransformOperation implementation that removes an attribute from the
 * current Element.
 * 
 * @author Ian Trimble - Oracle
 */
public class RemoveAttributeOperation extends AbstractTransformOperation {

	private String attributeName;

	/**
	 * Constructs an instance with the specified attribute name.
	 * 
	 * @param attributeName Name of attribute to be removed.
	 */
	public RemoveAttributeOperation(String attributeName) {
		this.attributeName = attributeName;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (curElement != null) {
			curElement.removeAttribute(attributeName);
		}
		return curElement;
	}

}
