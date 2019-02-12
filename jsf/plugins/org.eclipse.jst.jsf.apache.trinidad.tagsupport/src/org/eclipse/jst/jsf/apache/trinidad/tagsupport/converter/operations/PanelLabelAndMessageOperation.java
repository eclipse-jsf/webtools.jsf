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

import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * ITransformOperation implementation specifically for the "panelLabelAndMessage"
 * JSF Element.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class PanelLabelAndMessageOperation extends
		AbstractLabelAndHelpOperation {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations.AbstractLabelAndHelpOperation#appendControl(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	protected void appendControl(Element srcElement, Element parentElement) {
		handleLabelStyle(srcElement, parentElement);
		Element tableElement = appendChildElement("table", parentElement); //$NON-NLS-1$
		appendAttribute(tableElement, "cellpadding", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tableElement, "cellspacing", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tableElement, "border", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		Element trElement = appendChildElement("tr", tableElement); //$NON-NLS-1$
		Element tdElementLeft = appendChildElement("td", trElement); //$NON-NLS-1$
		List<Node> childNodes = getCopyChildrenNodes(srcElement);
		Iterator<Node> itChildNodes = childNodes.iterator();
		int curIndex = 0;
		while (itChildNodes.hasNext()) {
			Node childNode = itChildNodes.next();
			if (!(childNode instanceof Element &&
					IJSFConstants.TAG_IDENTIFIER_FACET.isSameTagType(
							TagIdentifierFactory.createDocumentTagWrapper((Element)childNode)))) {
				tagConverterContext.addChild(
						childNode, new ConvertPosition(tdElementLeft, curIndex++));
			}
		}
		Element tdElementRight = appendChildElement("td", trElement); //$NON-NLS-1$
		Element endFacetElement = getChildFacetByName(srcElement, "end"); //$NON-NLS-1$
		if (endFacetElement != null) {
			tagConverterContext.addChild(
					endFacetElement, new ConvertPosition(tdElementRight, 0));
		}
	}

	private void handleLabelStyle(Element srcElement, Element parentElement) {
		String labelStyle = srcElement.getAttribute("labelStyle"); //$NON-NLS-1$
		if (labelStyle != null && labelStyle.length() > 0) {
			if (parentElement.getPreviousSibling() instanceof Element) {
				Element labelTDElement = (Element)parentElement.getPreviousSibling();
				labelTDElement.removeAttribute("valign"); //$NON-NLS-1$
				String style = labelTDElement.getAttribute("style"); //$NON-NLS-1$
				if (style != null && style.length() > 0) {
					if (!style.endsWith(";")) { //$NON-NLS-1$
						style += ";"; //$NON-NLS-1$
					}
					style += labelStyle;
					appendAttribute(labelTDElement, "style", style); //$NON-NLS-1$
				} else {
					appendAttribute(labelTDElement, "style", labelStyle); //$NON-NLS-1$
				}
			}
		}
	}

}
