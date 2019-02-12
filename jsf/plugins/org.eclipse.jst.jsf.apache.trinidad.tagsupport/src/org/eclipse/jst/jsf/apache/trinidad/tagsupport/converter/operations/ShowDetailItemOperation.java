/**
 * Copyright (c) 2008 Oracle Corporation and others.
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

import org.eclipse.jst.jsf.apache.trinidad.tagsupport.ITrinidadConstants;
import org.eclipse.jst.jsf.apache.trinidad.tagsupport.Messages;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
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
public class ShowDetailItemOperation extends AbstractTrinidadTransformOperation {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	public Element transform(Element srcElement, Element curElement) {

		/*
		 * We need to represent showDetailItem as a container on the design
		 * canvas, so we need to return a top-level element to contain converted
		 * child elements. However, at runtime, it is the parent that renders
		 * the container element for showDetailItem's children. Therefore, we
		 * need to determine the parent element in order to know what top-level
		 * element it will render at runtime. There's no obvious way to do this
		 * using generic operations alone; hence, we need this specialization.
		 */

		Element topElement = null;

		/*
		 * create top-level element type, which may differ depending on parent
		 * element
		 */
		Element parentSrcElement = getParentElement(srcElement);
		if (ITrinidadConstants.TAG_IDENTIFIER_PANELTABBED.isSameTagType(
				TagIdentifierFactory.createDocumentTagWrapper(parentSrcElement))) {
			topElement = createElement("div"); //$NON-NLS-1$
		} else {
			//default to div element
			topElement = createElement("div"); //$NON-NLS-1$
		}

		if (getCopyChildrenNodes(srcElement).size() > 0) {
			//copy all children
			tagConverterContext.copyChildren(srcElement, topElement);
		} else {
			appendAttribute(
					topElement,
					"style", //$NON-NLS-1$
					ITrinidadConstants.STYLE_EMPTYELEMENT);
			appendChildText(
					Messages.ShowDetailItemOperation_EmptyShowDetailItemTag,
					topElement);
		}
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

}
