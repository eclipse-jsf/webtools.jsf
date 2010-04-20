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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation;
import org.w3c.dom.Element;

/**
 * ITransformOperation implementation that creates a new attribute on the
 * current Element by getting a value from the specified XPath expression.
 * 
 * @author Ian Trimble - Oracle
 */
public class CreateAttributeFromXPathOperation extends AbstractTransformOperation {

	private static final String DEFAULT_PREFIX = "prefix"; //$NON-NLS-1$

	private String attributeName;
	private String xPathExpression;
	private String nsURI;
	private String prefixedTokens;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		
		if (getParameters().length < 2) {
			getLog().error("Warning.TransformOperationFactory.TooFewParameters", getTransformOperationID()); //$NON-NLS-1$
			return null;
		}
		attributeName = getParameters()[0];
		xPathExpression = getParameters()[1];				
		Assert.isNotNull(attributeName);
		Assert.isNotNull(xPathExpression);
		if (getParameters().length > 3) {
			nsURI = getParameters()[2];
			Assert.isNotNull(nsURI);
			prefixedTokens = getParameters()[3];
			Assert.isNotNull(prefixedTokens);
		}
	
		if (srcElement != null) {
			XPath xPath = XPathFactory.newInstance().newXPath();
			try {
				Object resultObject = xPath.evaluate(xPathExpression, srcElement, XPathConstants.STRING);
				if (resultObject instanceof String && curElement != null) {
					if (((String)resultObject).equals("0")) { //$NON-NLS-1$
						if (nsURI != null && prefixedTokens != null) {
							xPath.setNamespaceContext(new NSContext(nsURI, DEFAULT_PREFIX));
							resultObject = xPath.evaluate(getPrefixedExpression(DEFAULT_PREFIX), srcElement, XPathConstants.STRING);
						}
					}
					curElement.setAttribute(attributeName, (String)resultObject);
				}
			} catch(XPathExpressionException xee) {
				//could not evaluate - return curElement
			}
		}
		return curElement;
	}

	private String getPrefixedExpression(String prefix) {
		String prefixedExpression = xPathExpression;
		final List<String> tokens = new ArrayList<String>();
		final StringTokenizer tokenizer = new StringTokenizer(prefixedTokens, ","); //$NON-NLS-1$
		while (tokenizer.hasMoreTokens()) {
			tokens.add(tokenizer.nextToken());
		}
		for (String token: tokens) {
			prefixedExpression = prefixedExpression.replace(token, prefix + ":" + token); //$NON-NLS-1$
		}
		return prefixedExpression;
	}



	class NSContext implements NamespaceContext {

		private String _namespaceURI;
		private String _prefix;

		public NSContext(String namespaceURI, String prefix) {
			_namespaceURI = namespaceURI;
			_prefix = prefix;
		}

		public String getNamespaceURI(String prefix) {
			return _namespaceURI;
		}

		public String getPrefix(String namespaceURI) {
			return _prefix;
		}

		public Iterator getPrefixes(String namespaceURI) {
			return Arrays.asList(new String[]{_prefix}).iterator();
		}

	}

}
