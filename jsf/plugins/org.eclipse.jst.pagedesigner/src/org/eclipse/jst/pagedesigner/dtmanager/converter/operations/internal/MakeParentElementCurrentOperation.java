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
package org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal;

import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * ITransformOperation implementation that makes the current Element's parent
 * Element the new current Element.
 * 
 * @author Ian Trimble - Oracle
 */
public class MakeParentElementCurrentOperation extends AbstractTransformOperation {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		Element resultElement = null;
		if (curElement != null) {
			Node parentNode = curElement.getParentNode();
			while (parentNode != null && parentNode.getNodeType() != Node.DOCUMENT_NODE) {
				if (parentNode.getNodeType() == Node.ELEMENT_NODE) {
					resultElement = (Element)parentNode;
					break;
				}
                parentNode = parentNode.getParentNode();
			}
		}
		return resultElement;
	}

}
