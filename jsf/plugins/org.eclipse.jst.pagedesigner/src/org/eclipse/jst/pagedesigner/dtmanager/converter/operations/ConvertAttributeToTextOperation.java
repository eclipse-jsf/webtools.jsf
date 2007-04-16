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
import org.w3c.dom.Text;

/**
 * ITransformOperation implementation that converts an attribute to a child
 * Text Node and optionally removes the specified attribute.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 * API: should this be public or should we restrict so can only be constructed
 * through a factory?
 */
public class ConvertAttributeToTextOperation extends AbstractTransformOperation {

	private String attributeName;
	private boolean removeAttribute;

	/**
	 * Constructs an instance with the specified attribute name, and defaults
	 * to removing the attribute.
	 * 
	 * @param attributeName Name of attribute to be converted to a child Text
	 * Node.
	 */
	public ConvertAttributeToTextOperation(String attributeName) {
		this(attributeName, true);
	}

	/**
	 * Constructs an instance with the specified attribute name and optionally
	 * removes the attribute.
	 * 
	 * @param attributeName Name of attribute to be converted to a child Text
	 * Node.
	 * @param removeAttribute It true, attribute is removed after child Text
	 * Node is created.
	 */
	public ConvertAttributeToTextOperation(String attributeName, boolean removeAttribute) {
		this.attributeName = attributeName;
		this.removeAttribute = removeAttribute;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (tagConverterContext != null && srcElement != null && curElement != null) {
			String content = srcElement.getAttribute(attributeName);
			if (content != null && content.length() > 0) {
				Text text = tagConverterContext.createText(content);
				curElement.appendChild(text);
				if (removeAttribute) {
					curElement.removeAttribute(attributeName);
				}
			}
		}
		return curElement;
	}

}
