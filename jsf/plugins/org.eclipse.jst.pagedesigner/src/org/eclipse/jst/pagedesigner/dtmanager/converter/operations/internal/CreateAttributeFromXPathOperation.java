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

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation;
import org.w3c.dom.Element;

/**
 * ITransformOperation implementation that creates a new attribute on the
 * current Element by getting a value from the specified XPath expression.
 * 
 * @author Ian Trimble - Oracle
 */
public class CreateAttributeFromXPathOperation extends AbstractTransformOperation {

	private String attributeName;
	private String xPathExpression;

	/**
	 * Constructs an instance with the specified XPath expression.
	 * 
	 * @param attributeName Name of attribute to be created.
	 * @param xPathExpression XPath expression to be evaluated against the
	 * source Element instance.
	 */
	public CreateAttributeFromXPathOperation(String attributeName, String xPathExpression) {
		this.attributeName = attributeName;
		this.xPathExpression = xPathExpression;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (srcElement != null) {
			XPath xPath = XPathFactory.newInstance().newXPath();
			try {
				Object resultObject = xPath.evaluate(xPathExpression, srcElement, XPathConstants.STRING);
				if (resultObject instanceof String && curElement != null) {
					curElement.setAttribute(attributeName, (String)resultObject);
				}
			} catch(XPathExpressionException xee) {
				//could not evaluate - return curElement
			}
		}
		return curElement;
	}

}
