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

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * ITransformOperation implementation that creates a new child Text node by
 * getting a value from the specified XPath expression.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class AppendChildTextFromXPathOperation extends AbstractTransformOperation {

	private String xPathExpression;

	/**
	 * Constructs an instance with the specified XPath expression.
	 * 
	 * @param xPathExpression XPath expression to be evaluated against the
	 * source Element instance.
	 */
	public AppendChildTextFromXPathOperation(String xPathExpression) {
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
				if (tagConverterContext != null && resultObject instanceof String && curElement != null) {
					Text childText = tagConverterContext.createText((String)resultObject);
					curElement.appendChild(childText);
				}
			} catch(XPathExpressionException xee) {
				//could not evaluate - return curElement
			}
		}
		return curElement;
	}

}
