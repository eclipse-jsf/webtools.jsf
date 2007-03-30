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

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * ITransformOperation implementation that executes child ITransformOperation
 * instances for each Element in the NodeList returned by the XPath expression,
 * which is evaluated against the source Element.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class IterateOverElementsOperation extends AbstractTransformOperation {

	private String xPathExpression;

	/**
	 * Constructs an instance with the specified XPath expression.
	 * 
	 * @param xPathExpression XPath expression to be evaluated against the
	 * source Element instance.
	 */
	public IterateOverElementsOperation(String xPathExpression) {
		this.xPathExpression = xPathExpression;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		Element retElement = curElement;
		if (srcElement != null) {
			XPath xPath = XPathFactory.newInstance().newXPath();
			try {
				Object resultObject = xPath.evaluate(xPathExpression, srcElement, XPathConstants.NODESET);
				if (resultObject instanceof NodeList) {
					NodeList nodes = (NodeList)resultObject;
					for (int i = 0; i < nodes.getLength(); i++) {
						Node node = nodes.item(i);
						if (node instanceof Element) {
							retElement = executeChildOperations((Element)node, retElement);
						}
					}
				}
			} catch(XPathExpressionException xee) {
				//could not evaluate - return curElement
			}
		}
		return retElement;
	}

}
