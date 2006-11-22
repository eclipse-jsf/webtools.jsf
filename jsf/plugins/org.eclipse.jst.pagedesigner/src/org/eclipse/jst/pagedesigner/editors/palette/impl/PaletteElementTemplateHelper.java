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
package org.eclipse.jst.pagedesigner.editors.palette.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemDescriptor;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMText;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * @author mengbo
 */
public class PaletteElementTemplateHelper {
	private static final String TEMPLATE_ITEM_NAME = "template";

	private static final String PREFIX_ATTRIBUTE = "uri";

//	private static Logger _log = PDPlugin
//			.getLogger(PaletteElementTemplateHelper.class);

	public static NodeList readTemplate(Element item) {
		if (item != null) {
			Node node = EditModelQuery.getChildDeferredNode(item,
					new String[] { TEMPLATE_ITEM_NAME }, 1, true);
			return node != null ? node.cloneNode(true).getChildNodes() : null;
		}
		return null;
	}

	/**
	 * This method is used to process template element which is read from .xmi
	 * file and generate default element.
	 * 
	 * @param model
	 * @param element
	 * @param itemDes
	 */
	public static void applyTemplate(IDOMModel model, Element element,
			IPaletteItemDescriptor itemDes) {
		if (element == null || element.getLocalName() == null) {
			return;
		}
		Node[] nl = itemDes.getTemplateSubNodes(model);
		if (nl != null) {
			for (int i = 0, n = nl.length; i < n; i++) {
				element.appendChild(nl[i]);
			}
		}
	}

	/**
	 * Use the actrual prefixs of jsf html and jsf core to set the prefix of
	 * each node declared in template.
	 * 
	 * @param prefixH
	 * @param prefixC
	 * @param nl
	 * @param document
	 * @return
	 */
	public static Node[] applyPrefixes(String prefixH, String prefixC,
			NodeList nl, Document document) {
		List result = new ArrayList();
		for (int i = 0, n = nl.getLength(); i < n; i++) {
			Node node = cloneNodeDeep(document, nl.item(i), prefixH, prefixC);
			if (nl.item(i) instanceof Element) {
				result.add(node);
			}
		}
		return (Node[]) result.toArray(new Node[result.size()]);
	}

	/**
	 * TODO: Later we may add some logic to reference the tld file through tag
	 * name to resolve the prefixs directly.
	 * 
	 * @param model
	 * @param prefixH
	 * @param prefixC
	 * @param node
	 * @return
	 */
	private static void internalApplyPrefixes(String prefixH, String prefixC,
			Element refNode, Element node) {
		if (node != null && refNode != null) {
			String prefix = refNode.getAttribute(PREFIX_ATTRIBUTE); //$NON-NLS-1$
			if (prefix != null) {
				if (prefix.equalsIgnoreCase(IJMTConstants.URI_JSF_HTML)) //$NON-NLS-1$
				{
					node.setPrefix(prefixH);
				} else if (prefix.equalsIgnoreCase(IJMTConstants.URI_JSF_CORE)) //$NON-NLS-1$
				{
					node.setPrefix(prefixC);
				}
			}
		}
	}

	public static Node cloneNodeDeep(Document destDoc, Node sourceNode,
			String prefixH, String prefixC) {
		switch (sourceNode.getNodeType()) {
		case Node.ELEMENT_NODE:
			Element sourceEle = (Element) sourceNode;
			Element resultEle = destDoc.createElement(sourceEle.getTagName());
			internalApplyPrefixes(prefixH, prefixC, sourceEle, resultEle);
			NamedNodeMap attrs = sourceEle.getAttributes();
			for (int i = 0, size = attrs.getLength(); i < size; i++) {
				Attr a = (Attr) attrs.item(i);
				if (!PREFIX_ATTRIBUTE.equalsIgnoreCase(a.getNodeName())) {
					resultEle.setAttribute(a.getName(), a.getValue());
				}
			}
			NodeList children = sourceEle.getChildNodes();
			for (int i = 0, size = children.getLength(); i < size; i++) {
				Node n = children.item(i);
				Node d = cloneNodeDeep(destDoc, n, prefixH, prefixC);
				if (d != null) {
					resultEle.appendChild(d);
				}
			}
			return resultEle;
		case Node.TEXT_NODE:
			Text txt = destDoc.createTextNode(sourceNode.getNodeValue());
			if (txt instanceof IDOMText && sourceNode instanceof IDOMText) {
				try {
					((IDOMText) txt).setSource(((IDOMText) sourceNode)
							.getSource());
				} catch (Exception ex) {
					// ignore
				}
			}
			return txt;
		case Node.CDATA_SECTION_NODE:
			return destDoc.createCDATASection(sourceNode.getNodeValue());
		default:
			return null; // not support.
		}
	}
}
