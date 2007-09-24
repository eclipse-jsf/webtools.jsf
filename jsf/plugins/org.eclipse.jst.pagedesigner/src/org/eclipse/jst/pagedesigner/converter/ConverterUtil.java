/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.converter;

import java.util.Set;

import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMText;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author mengbo
 * @version 1.5
 */
public class ConverterUtil {
	/**
	 * 
	 * @param source
	 * @param dest
	 * @param ignore
	 */
	public static void copyAllAttributes(Element source, Element dest,
			Set ignore) {
		NamedNodeMap attrs = source.getAttributes();
		for (int i = 0, size = attrs.getLength(); i < size; i++) {
			Attr attr = (Attr) attrs.item(i);
			if (ignore == null || !ignore.contains(attr.getName())) {
				dest.setAttribute(attr.getName(), attr.getValue());
			}
		}
	}

	/**
	 * copy a single attribute (if exist)
	 * 
	 * @param source
	 * @param srcattr
	 * @param dest
	 * @param destattr
	 */
	public static void copyAttribute(Element source, String srcattr,
			Element dest, String destattr) {
		Attr attr = source.getAttributeNode(srcattr);
		if (attr != null) {
			dest.setAttribute(destattr, attr.getValue());
		}
	}

	/**
	 * @param hostElement
	 * @return true if hostElement represents an empty container
	 */
	public static boolean isEmptyContainer(Element hostElement) {
		NodeList nl = hostElement.getChildNodes();
		if (nl == null || nl.getLength() == 0) {
			return true;
		}

		for (int i = 0, n = nl.getLength(); i < n; i++) {
			Node node = nl.item(i);
			if (!(node instanceof IDOMText)) {
				return false;
			}
			if (!((IDOMText) node).isElementContentWhitespace()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param document
	 * @param text
	 * @return the descripton element in the document containing text
	 */
	public static Element createDescriptionElement(IDOMDocument document,
			String text) {
		if (document == null) {
			return null;
		}
		Element span = document.createElement(IHTMLConstants.TAG_SPAN); //$NON-NLS-1$
		span.setAttribute(
				"style", "color:gray;font-style:italic;font-size:normal;"); //$NON-NLS-1$ //$NON-NLS-2$
		if (text == null) {
			span.appendChild(document.createTextNode(PDPlugin
					.getResourceString("ConverterUtil.Description"))); //$NON-NLS-1$
		} else {
			span.appendChild(document.createTextNode(text));
		}
		return span;
	}
}
