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
package org.eclipse.jst.pagedesigner.jsf.ui.converter;

import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class DTTagConverterContext implements ITagConverterContext {

	private DTTagConverter tagConverter;

	public DTTagConverterContext(DTTagConverter tagConverter) {
		this.tagConverter = tagConverter;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.ITagConverterContext#getHostElement()
	 */
	public Element getHostElement() {
		Element hostElement = null;
		if (tagConverter != null) {
			hostElement = tagConverter.getHostElement();
		}
		return hostElement;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.ITagConverterContext#createElement(java.lang.String)
	 */
	public Element createElement(String tag) {
		Element element = null;
		if (tagConverter != null) {
			element = tagConverter.createElement(tag);
		}
		return element;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.ITagConverterContext#createText(java.lang.String)
	 */
	public Text createText(String content) {
		Text text = null;
		if (tagConverter != null) {
			text = tagConverter.createText(content);
		}
		return text;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.ITagConverterContext#addChild(org.w3c.dom.Node, org.eclipse.jst.pagedesigner.converter.ConvertPosition)
	 */
	public void addChild(Node childNode, ConvertPosition position) {
		if (tagConverter != null) {
			tagConverter.addChild(childNode, position);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.ITagConverterContext#copyChildren(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public void copyChildren(Element srcElement, Element destElement) {
		if (tagConverter != null) {
			tagConverter.copyChildren(srcElement, destElement);
		}
	}

}
