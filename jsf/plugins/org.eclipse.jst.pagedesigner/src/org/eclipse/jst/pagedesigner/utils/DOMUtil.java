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
package org.eclipse.jst.pagedesigner.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.wst.xml.core.internal.document.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * @author mengbo
 */
public class DOMUtil {
	public static List getChildElementsByTagIgnoreCase(Element parent,
			String tag) {
		List ret = new ArrayList();
		NodeList nodeList = parent.getChildNodes();
		for (int i = 0, size = nodeList.getLength(); i < size; i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				String t = node.getNodeName();
				if (tag.equalsIgnoreCase(t)) {
					ret.add(node);
				}
			}
		}
		return ret;
	}

	/**
	 * @param ele
	 * @return
	 */
	public static String getTextElementValue(Element ele) {
		StringBuffer buffer = new StringBuffer();
		Node node = ele.getFirstChild();
		while (node != null) {
			if (node.getNodeType() == Node.TEXT_NODE) {
				buffer.append(node.getNodeValue());
			} else if (node.getNodeType() == Node.CDATA_SECTION_NODE) {
				buffer.append(node.getNodeValue());
			}
			node = node.getNextSibling();
		}
		return buffer.toString();
	}

	/**
	 * @param element
	 * @param string
	 * @return
	 */
	public static String getAttributeIgnoreCase(Element element, String string) {
		NamedNodeMap map = element.getAttributes();
		for (int i = 0; i < map.getLength(); i++) {
			Node attr = map.item(i);
			if (string.equalsIgnoreCase(attr.getNodeName())) {
				return attr.getNodeValue();
			}
		}
		return null;
	}

	/**
	 * @param tr
	 * @param strings
	 * @return
	 */
	public static List getChildrenByTagsIgnoreCase(Element parent, String[] tags) {
		List result = new ArrayList();
		NodeList nodeList = parent.getChildNodes();
		for (int i = 0, size = nodeList.getLength(); i < size; i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				String t = node.getNodeName();
				for (int k = 0; k < tags.length; k++) {
					if (tags[k].equalsIgnoreCase(t)) {
						result.add(node);
						break;
					}
				}
			}
		}
		return result;
	}

	public static void removeAllChildren(Element ele) {
		((ElementImpl) ele).removeChildNodes();
	}

	public static void setTextElementValue(Element ele, String value) {
		removeAllChildren(ele);
		Text txt = ele.getOwnerDocument().createTextNode(value);
		ele.appendChild(txt);
	}

	/**
	 * @param htmlElement
	 * @param string
	 * @param i
	 * @return
	 */
	public static int getIntAttributeIgnoreCase(Element ele, String attr,
			int defaultvalue) {
		if (ele == null) {
			return defaultvalue;
		}
		String attrvalue = getAttributeIgnoreCase(ele, attr);
		if (attrvalue == null) {
			return defaultvalue;
		}
        try {
        	return Integer.parseInt(attrvalue);
        } catch (NumberFormatException  ex) {
        	return defaultvalue;
        }
	}

	/**
	 * get all child elements
	 * 
	 * @param ele
	 * @return
	 */
	public static List getElementChildren(Element ele) {
		List ret = new ArrayList();
		NodeList nodeList = ele.getChildNodes();
		for (int i = 0, size = nodeList.getLength(); i < size; i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				ret.add(node);
			}
		}
		return ret;
	}

	/**
	 * judge whether element has an attribute named attrName
	 * 
	 * @param ele
	 * @param attrName
	 * @return
	 */
	public static boolean hasAttribute(Element ele, String attrName) {
		NamedNodeMap map = ele.getAttributes();
		for (int i = 0; i < map.getLength(); i++) {
			Node attr = map.item(i);
			if (attr.getNodeName().equalsIgnoreCase(attrName)) {
				return true;
			}
		}
		return false;
	}

	public static void nodeToString(Node node, StringBuffer sb) {
		int type = node.getNodeType();
		switch (type) {
		case Node.DOCUMENT_NODE:
			sb.append("<?xml version=\"1.0\" ?>");
			nodeToString(((Document) node).getDocumentElement(), sb);
			break;

		case Node.ELEMENT_NODE:
			sb.append("<");
			sb.append(node.getNodeName());
			NamedNodeMap attrs = node.getAttributes();
			for (int i = 0; i < attrs.getLength(); i++) {
				Node attr = attrs.item(i);
				sb.append(" " + attr.getNodeName() + "=\""
						+ attr.getNodeValue() + "\"");
			}

			NodeList children = node.getChildNodes();
			if (children != null) {
				int len = children.getLength();
				if (len != 0) {
					sb.append(">");
				}
				for (int i = 0; i < len; i++) {
					nodeToString(children.item(i), sb);
				}
			}
			break;

		case Node.ENTITY_REFERENCE_NODE:
			sb.append("&");
			sb.append(node.getNodeName());
			sb.append(";");
			break;

		case Node.CDATA_SECTION_NODE:
			sb.append("<![CDATA[");
			sb.append(node.getNodeValue());
			sb.append("]]>");
			break;

		case Node.TEXT_NODE:
			sb.append(node.getNodeValue());
			break;

		case Node.PROCESSING_INSTRUCTION_NODE:
			sb.append("<?");
			sb.append(node.getNodeName());
			String data = node.getNodeValue();
			{
				sb.append(" ");
				sb.append(data);
			}
			sb.append("?>");
			break;
		}

		if (type == Node.ELEMENT_NODE) {
			if (node.getFirstChild() != null) {
				sb.append("</");
				sb.append(node.getNodeName());
				sb.append(">");
			} else {
				sb.append("/>");
			}

		}
	}
}
