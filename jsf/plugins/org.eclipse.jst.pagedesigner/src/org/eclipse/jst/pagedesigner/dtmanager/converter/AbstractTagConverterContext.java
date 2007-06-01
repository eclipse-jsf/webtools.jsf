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
package org.eclipse.jst.pagedesigner.dtmanager.converter;

import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * Abstract ITagConverter implementation of ITagConverterContext.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public abstract class AbstractTagConverterContext implements ITagConverterContext {

	/**
	 * ITagConverter instance.
	 */
	protected ITagConverter tagConverter;

	/**
	 * Instantiates an instance for the specified ITagConverter instance. 
	 * @param tagConverter ITagConverter instance.
	 */
	public AbstractTagConverterContext(ITagConverter tagConverter) {
		this.tagConverter = tagConverter;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.ITagConverterContext#addChild(org.w3c.dom.Node, org.eclipse.jst.pagedesigner.converter.ConvertPosition)
	 */
	public abstract void addChild(Node childNode, ConvertPosition position);

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.ITagConverterContext#copyChildren(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public abstract void copyChildren(Element srcElement, Element destElement);

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.ITagConverterContext#createElement(java.lang.String)
	 */
	public abstract Element createElement(String tag);

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.ITagConverterContext#createText(java.lang.String)
	 */
	public abstract Text createText(String content);

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.ITagConverterContext#getHostElement()
	 */
	public Element getHostElement() {
		Element element = null;
		if (tagConverter != null) {
			element = tagConverter.getHostElement(); 
		}
		return element;
	}

}
