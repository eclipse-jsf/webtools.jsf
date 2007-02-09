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

import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class ConvertAttributeToTextOperation extends AbstractTransformOperation {

	private String attributeName;
	private boolean removeAttribute;

	public ConvertAttributeToTextOperation(String attributeName) {
		this(attributeName, true);
	}

	public ConvertAttributeToTextOperation(String attributeName, boolean removeAttribute) {
		this.attributeName = attributeName;
		this.removeAttribute = removeAttribute;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
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
