/**
 * Copyright (c) 2008, 2009 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.jst.pagedesigner.dtmanager.converter.ITransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.TransformOperationFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * ITransformOperation implementation specifically for the "outputFormatted" JSF
 * Element.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class OutputFormattedOperation extends AbstractTrinidadTransformOperation {

	/*
	 * NOTICE (especially if looking for missing whitespace):
	 * Because this operation can potentially return a mix of child Element
	 * and Text nodes inside a span Element, it can suffer from bug #221629
	 * (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=221629).
	 * This operation is not the cause of the above-mentioned bug.
	 */

	private static final String STYLECLASS_INSTRUCTION = "AFInstructionText"; //$NON-NLS-1$
	private static final String STYLECLASS_PAGESTAMP = "OraPageStampText"; //$NON-NLS-1$
	private static final String STYLECLASS_INCONTEXTBRANDING = "p_InContextBrandingText"; //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	public Element transform(Element srcElement, Element curElement) {
		//create outer span element and set class attribute
		Element spanElement = createElement("span"); //$NON-NLS-1$
		ITransformOperation operation =
			TransformOperationFactory.getInstance().getTransformOperation(
					TransformOperationFactory.OP_CopyAttributeWithRenameOperation,
					new String[]{"inlineStyle", "style"}); //$NON-NLS-1$  //$NON-NLS-2$
		operation.transform(srcElement, spanElement);

		String styleClass = srcElement.getAttribute("styleClass"); //$NON-NLS-1$
		if (styleClass == null || styleClass.length() < 1) {
			String styleUsage = srcElement.getAttribute("styleUsage"); //$NON-NLS-1$
			if (styleUsage != null && styleUsage.length() > 8) {
				if (styleUsage.equals("instruction")) { //$NON-NLS-1$
					styleClass = STYLECLASS_INSTRUCTION;
				} else if (styleUsage.equals("pageStamp")) { //$NON-NLS-1$
					styleClass = STYLECLASS_PAGESTAMP;
				} else if (styleUsage.equals("inContextBranding")) { //$NON-NLS-1$
					styleClass = STYLECLASS_INCONTEXTBRANDING;
				}
			}
		}
		if (styleClass != null && styleClass.length() > 0) {
			appendAttribute(spanElement, "class", styleClass); //$NON-NLS-1$
		}

		//deal with value
		String value = srcElement.getAttribute("value"); //$NON-NLS-1$
		if (value != null && value.length() > 0) {
			StringBuffer wrappedValue = new StringBuffer();
			wrappedValue.append("<?xml version=\"1.0\"?><value>"); //$NON-NLS-1$
			wrappedValue.append(value);
			wrappedValue.append("</value>"); //$NON-NLS-1$
			InputStream inputStream = new ByteArrayInputStream(wrappedValue.toString().getBytes());
			Element valueElement = getValueDocumentElement(inputStream);
			if (valueElement != null) {
				if (!appendValueNodes(spanElement, valueElement)) {
					//remove any children added before appendValueNodes failed
					NodeList childNodes = spanElement.getChildNodes();
					for (int i = 0; i < childNodes.getLength(); i++) {
						spanElement.removeChild(childNodes.item(i));
					}
					//set as simple text
					appendChildText(value, spanElement);
				}
			} else {
				//set as simple text
				appendChildText(value, spanElement);
			}
		}

		return spanElement;
	}

	private Element getValueDocumentElement(InputStream inputStream) {
		Element element = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//TODO: entity expansion should be set to false for ".jsp", true for ".jspx"
		factory.setExpandEntityReferences(true);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputStream);
			element = document.getDocumentElement();
		} catch(Exception e) {
			//fail on any exception - text with markup will be rendered instead
		}
		return element;
	}

	private boolean appendValueNodes(Node parentNode, Node currentNode) {
		boolean success = true;
		try {
			NodeList childNodes = currentNode.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node childNode = childNodes.item(i);
				if (childNode instanceof Element) {
					//TODO: elements should be filtered to only create elements specified for this tag
					Element newElement = (Element)parentNode.appendChild(
						parentNode.getOwnerDocument().createElement(
								childNode.getNodeName()));
					NamedNodeMap attrMap = childNode.getAttributes();
					if (attrMap != null) {
						for (int j = 0; j < attrMap.getLength(); j++) {
							Attr attr = (Attr)attrMap.item(j);
							//TODO: attributes should be filtered to only create attributes specified for this tag
							newElement.setAttribute(
									attr.getName(), attr.getValue());
						}
					}
					success &= appendValueNodes(newElement, childNode);
				} else if (childNode instanceof Text) {
					parentNode.appendChild(
							parentNode.getOwnerDocument().createTextNode(
									childNode.getTextContent()));
				}
			}
		} catch(Exception e) {
			//fail on any exception - text with markup will be rendered instead
			success = false;
		}
		return success;
	}

}
