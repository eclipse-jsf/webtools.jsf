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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * ITransformOperation implementation specifically for the "panelGroupLayout"
 * JSF Element.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class PanelGroupLayoutOperation extends AbstractTrinidadTransformOperation {

	private static final int LAYOUT_DEFAULT = 0;
	private static final int LAYOUT_HORIZONTAL = 1;
	private static final int LAYOUT_VERTICAL = 2;

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	public Element transform(Element srcElement, Element curElement) {
		Element outerElement;

		//create outer element and set element-specific attributes
		final int layout = getLayout(srcElement);
		Element containerElement;
		switch (layout) {
		case LAYOUT_HORIZONTAL:
			outerElement = createElement("table"); //$NON-NLS-1$
			outerElement.setAttribute("cellpadding", "0"); //$NON-NLS-1$ //$NON-NLS-2$
			outerElement.setAttribute("cellspacing", "0"); //$NON-NLS-1$ //$NON-NLS-2$
			outerElement.setAttribute("border", "0"); //$NON-NLS-1$ //$NON-NLS-2$
			containerElement = appendChildElement("tr", outerElement); //$NON-NLS-1$
			break;
		case LAYOUT_VERTICAL:
			outerElement = createElement("div"); //$NON-NLS-1$
			containerElement = outerElement;
			break;
		default:
			outerElement = createElement("span"); //$NON-NLS-1$
			containerElement = outerElement;
			break;
		}

		//set common attributes on outer element
		final String styleClass = srcElement.getAttribute("styleClass"); //$NON-NLS-1$
		if (styleClass != null && styleClass.length() > 0) {
			outerElement.setAttribute("class", styleClass); //$NON-NLS-1$
		}
		final String inlineStyle = srcElement.getAttribute("inlineStyle"); //$NON-NLS-1$
		if (inlineStyle != null && inlineStyle.length() > 0) {
			outerElement.setAttribute("style", inlineStyle); //$NON-NLS-1$
		}

		//get child nodes, skipping "facet" elements and empty Text nodes
		List<Node> childNodes = new ArrayList<Node>();
		Iterator<Node> itCopyChildrenNodes = getCopyChildrenNodes(srcElement).iterator();
		while (itCopyChildrenNodes.hasNext()) {
			Node copyChildrenNode = itCopyChildrenNodes.next();
			if (!(copyChildrenNode instanceof Element && TagIdentifierFactory.createDocumentTagWrapper((Element)copyChildrenNode).isSameTagType(IJSFConstants.TAG_IDENTIFIER_FACET))) {
				if (!(copyChildrenNode instanceof Text && copyChildrenNode.getNodeValue().trim().length() < 1)) {
					childNodes.add(copyChildrenNode);
				}
			}
		}

		//get "separator" facet's first child Element (only one used at runtime)
		Element separatorElement = null;
		final Element facetElement = getChildFacetByName(srcElement, "separator"); //$NON-NLS-1$
		if (facetElement != null) {
			NodeList facetChildNodes = facetElement.getChildNodes();
			for (int i = 0; i < facetChildNodes.getLength(); i++) {
				Node curChildNode = facetChildNodes.item(i);
				if (curChildNode instanceof Element) {
					separatorElement = (Element)curChildNode;
					break;
				}
			}
		}

		//iterate over child nodes, interspersing separator(s) as required
		int curChildNodeIndex = 0;
		int curIndex = 0;
		Iterator<Node> itChildNodes = childNodes.iterator();
		switch (layout) {
		case LAYOUT_HORIZONTAL:
			while (itChildNodes.hasNext()) {
				final Element childTDElement = appendChildElement("td", containerElement); //$NON-NLS-1$
				tagConverterContext.addChild(itChildNodes.next(), new ConvertPosition(childTDElement, 0));
				if (++curChildNodeIndex < childNodes.size()) {
					if (separatorElement != null) {
						final Element sepTDElement = appendChildElement("td", containerElement); //$NON-NLS-1$
						tagConverterContext.addChild(separatorElement, new ConvertPosition(sepTDElement, 0));
					}
				}
			}
			break;
		case LAYOUT_VERTICAL:
			while (itChildNodes.hasNext()) {
				tagConverterContext.addChild(itChildNodes.next(), new ConvertPosition(containerElement, curIndex++));
				if (++curChildNodeIndex < childNodes.size()) {
					final Element divElement = appendChildElement("div", containerElement); //$NON-NLS-1$
					curIndex++;
					if (separatorElement != null) {
						tagConverterContext.addChild(separatorElement, new ConvertPosition(divElement, 0));
					}
				}
			}
			break;
		default:
			while (itChildNodes.hasNext()) {
				tagConverterContext.addChild(itChildNodes.next(), new ConvertPosition(containerElement, curIndex++));
				if (++curChildNodeIndex < childNodes.size()) {
					if (separatorElement != null) {
						tagConverterContext.addChild(separatorElement, new ConvertPosition(containerElement, curIndex++));
					}
				}
			}
			break;
		}

		return outerElement;
	}

	private int getLayout(Element srcElement) {
		int layout = LAYOUT_DEFAULT;
		if (srcElement != null) {
			String layoutValue = srcElement.getAttribute("layout"); //$NON-NLS-1$
			if (layoutValue != null) {
				if (layoutValue.equals("horizontal")) { //$NON-NLS-1$
					layout = LAYOUT_HORIZONTAL;
				} else if (layoutValue.equals("vertical")) { //$NON-NLS-1$
					layout = LAYOUT_VERTICAL;
				}
			}
		}
		return layout;
	}

}
