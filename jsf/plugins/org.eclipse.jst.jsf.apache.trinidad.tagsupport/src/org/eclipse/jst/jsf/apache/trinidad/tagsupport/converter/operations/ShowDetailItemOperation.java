/**
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations;

import org.eclipse.jst.jsf.apache.trinidad.tagsupport.ITrinidadConstants;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * ITransformOperation implementation specifically for the "showDetailItem" JSF
 * Element.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class ShowDetailItemOperation extends AbstractTransformOperation {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	public Element transform(Element srcElement, Element curElement) {

		Element topElement = null;

		/*
		 * create top-level element type, which may differ depending on parent
		 * element
		 */
		Element parentSrcElement = getParentElement(srcElement);
		if (representsTag(
				parentSrcElement,
				ITrinidadConstants.TLD_CORE_URI,
				ITrinidadConstants.TAG_PANELTABBED)) {
			topElement = createElement("div"); //$NON-NLS-1$
		} else {
			//default to div element
			topElement = createElement("div"); //$NON-NLS-1$
		}

		//copy all children
		tagConverterContext.copyChildren(srcElement, topElement);
		return topElement;
	}

	private Element getParentElement(Node node) {
		Element parentElement = null;
		Node curNode = node;
		while (curNode != null) {
			curNode = curNode.getParentNode();
			if (curNode instanceof Element) {
				parentElement = (Element)curNode;
				break;
			}
		}
		return parentElement;
	}

	private static boolean representsTag(
			Element element, String uri, String tagName) {
		boolean ret = false;
		if (element != null) {
			ret =
				element.getNamespaceURI().equalsIgnoreCase(uri) &&
				element.getLocalName().equalsIgnoreCase(tagName);
		}
		return ret;
	}

}
