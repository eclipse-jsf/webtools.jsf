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
	/**
	 * @param parent
	 * @param tag
	 * @return the list of child elements  of parent that are Elements
	 * and that have name 'tag' ignoring case
	 */
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
	 * @return the element value of the TEXT_NODE children of element
	 * concat'd together
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
	 * @return the attribute named string on element ignoring case in the comparison
	 * or null if not found
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
	 * @param parent
	 * @param tags
	 * @return the list of children of parent with name in tags ignoring case
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

	/**
	 * @param ele
	 */
	public static void removeAllChildren(Element ele) {
		((ElementImpl) ele).removeChildNodes();
	}

	/**
	 * @param ele
	 * @param value
	 */
	public static void setTextElementValue(Element ele, String value) {
		removeAllChildren(ele);
		Text txt = ele.getOwnerDocument().createTextNode(value);
		ele.appendChild(txt);
	}

	/**
	 * @param ele 
	 * @param attr 
	 * @param defaultvalue 
	 * @return the integer attribute of ele called attr.  Default value
	 * is returned if the attribute is not found.
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
	 * @return the list of element children of type ELEMENT_NODE
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
	 * @return true if element has attribute called attrName ignoring
	 * case  in the comparison.
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

	/**
	 * @param node
	 * @param sb
	 */
	public static void nodeToString(Node node, StringBuffer sb) {
		int type = node.getNodeType();
		switch (type) {
		case Node.DOCUMENT_NODE:
			sb.append("<?xml version=\"1.0\" ?>"); //$NON-NLS-1$
			nodeToString(((Document) node).getDocumentElement(), sb);
			break;

		case Node.ELEMENT_NODE:
			sb.append("<"); //$NON-NLS-1$
			sb.append(node.getNodeName());
			NamedNodeMap attrs = node.getAttributes();
			for (int i = 0; i < attrs.getLength(); i++) {
				Node attr = attrs.item(i);
				sb.append(" " + attr.getNodeName() + "=\"" //$NON-NLS-1$ //$NON-NLS-2$
						+ attr.getNodeValue() + "\""); //$NON-NLS-1$
			}

			NodeList children = node.getChildNodes();
			if (children != null) {
				int len = children.getLength();
				if (len != 0) {
					sb.append(">"); //$NON-NLS-1$
				}
				for (int i = 0; i < len; i++) {
					nodeToString(children.item(i), sb);
				}
			}
			break;

		case Node.ENTITY_REFERENCE_NODE:
			sb.append("&"); //$NON-NLS-1$
			sb.append(node.getNodeName());
			sb.append(";"); //$NON-NLS-1$
			break;

		case Node.CDATA_SECTION_NODE:
			sb.append("<![CDATA["); //$NON-NLS-1$
			sb.append(node.getNodeValue());
			sb.append("]]>"); //$NON-NLS-1$
			break;

		case Node.TEXT_NODE:
			sb.append(node.getNodeValue());
			break;

		case Node.PROCESSING_INSTRUCTION_NODE:
			sb.append("<?"); //$NON-NLS-1$
			sb.append(node.getNodeName());
			String data = node.getNodeValue();
			{
				sb.append(" "); //$NON-NLS-1$
				sb.append(data);
			}
			sb.append("?>"); //$NON-NLS-1$
			break;
		}

		if (type == Node.ELEMENT_NODE) {
			if (node.getFirstChild() != null) {
				sb.append("</"); //$NON-NLS-1$
				sb.append(node.getNodeName());
				sb.append(">"); //$NON-NLS-1$
			} else {
				sb.append("/>"); //$NON-NLS-1$
			}

		}
	}
}
