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
package org.eclipse.jst.pagedesigner.dtmanager.converter.internal;

import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.eclipse.jst.pagedesigner.dtmanager.converter.AbstractTagConverterContext;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * Design-time tag converter implementation of ITagConverterContext.
 * 
 * @author Ian Trimble - Oracle
 */
public class DTTagConverterContext extends AbstractTagConverterContext {

	/**
	 * Constructs an instance for the specified DTTagConverter instance.
	 * 
	 * @param tagConverter DTTagConverter instance.
	 */
	public DTTagConverterContext(DTTagConverter tagConverter) {
		super(tagConverter);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.ITagConverterContext#createElement(java.lang.String)
	 */
	public Element createElement(String tag) {
		Element element = null;
		if (tagConverter != null) {
			element = ((DTTagConverter)tagConverter).createElement(tag);
		}
		return element;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.ITagConverterContext#createText(java.lang.String)
	 */
	public Text createText(String content) {
		Text text = null;
		if (tagConverter != null) {
			text = ((DTTagConverter)tagConverter).createText(content);
		}
		return text;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.ITagConverterContext#addChild(org.w3c.dom.Node, org.eclipse.jst.pagedesigner.converter.ConvertPosition)
	 */
	public void addChild(Node childNode, ConvertPosition position) {
		if (tagConverter != null) {
			((DTTagConverter)tagConverter).addChild(childNode, position);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.ITagConverterContext#copyChildren(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public void copyChildren(Element srcElement, Element destElement) {
		if (tagConverter != null) {
			((DTTagConverter)tagConverter).copyChildren(srcElement, destElement);
		}
	}

}
