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

	private static final String DEFAULT_PREFIX = "prefix"; //$NON-NLS-1$

	private String xPathExpression;
	private String nsURI;
	private String prefixedTokens;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (getParameters().length < 1) {
			getLog().error("Warning.TransformOperationFactory.TooFewParameters", getTransformOperationID()); //$NON-NLS-1$
			return null;
		}
		xPathExpression = getParameters()[0];		
		Assert.isNotNull(xPathExpression);
		if (getParameters().length > 2) {
			nsURI = getParameters()[1];
			Assert.isNotNull(nsURI);
			prefixedTokens = getParameters()[2];
			Assert.isNotNull(prefixedTokens);
		}

		Element retElement = curElement;
		if (srcElement != null) {
			XPath xPath = XPathFactory.newInstance().newXPath();
			try {
				Object resultObject = xPath.evaluate(xPathExpression, srcElement, XPathConstants.NODESET);
				if (resultObject instanceof NodeList) {
					NodeList nodes = (NodeList)resultObject;
					if (nodes.getLength() < 1) {
						if (nsURI != null && prefixedTokens != null) {
							xPath.setNamespaceContext(new NSContext(nsURI, DEFAULT_PREFIX));
							resultObject = xPath.evaluate(getPrefixedExpression(DEFAULT_PREFIX), srcElement, XPathConstants.NODESET);
							if (resultObject instanceof NodeList) {
								nodes = (NodeList)resultObject;
							}
						}
					}
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
