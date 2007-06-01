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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * Represents ITagConverter-specific context and functionality.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public interface ITagConverterContext {

	/**
	 * Gets ITagConverter instance's host Element instance.
	 * 
	 * @return ITagConverter instance's host Element instance.
	 */
	public Element getHostElement();

	/**
	 * Creates an Element instance to be used in ITagConverter's Document
	 * instance.
	 * 
	 * @param tag Name of element to be created.
	 * @return Element instance to be used in ITagConverter's Document
	 * instance.
	 */
	public Element createElement(String tag);

	/**
	 * Creates a Text node instance to be used in ITagConverter's Document
	 * instance.
	 * 
	 * @param content Textual content of the created text node.
	 * @return Text node instance to be used in ITagConverter's Document
	 * instance.
	 */
	public Text createText(String content);

	/**
	 * Adds a child Node instance to ITagConverter's collection of Nodes
	 * requiring subsequent processing.
	 * 
	 * @param childNode Child Node instance requiring subsequent processing.
	 * @param position ConvertPosition instance describing child Node
	 * instance's position, relative to another Node instance.
	 */
	public void addChild(Node childNode, ConvertPosition position);

	/**
	 * Add all child Element instances of srcElement to ITagConverter's
	 * collection of Nodes requiring subsequent processing.
	 * 
	 * @param srcElement Source Element instance from which child Elements are
	 * copied.
	 * @param destElement Destination Element instance to which child Elements
	 * will be relative.
	 */
	public void copyChildren(Element srcElement, Element destElement);

}
