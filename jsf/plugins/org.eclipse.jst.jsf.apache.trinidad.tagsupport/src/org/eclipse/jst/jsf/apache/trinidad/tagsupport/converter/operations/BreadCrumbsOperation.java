/**
 * Copyright (c) 2009 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.apache.trinidad.tagsupport.ITrinidadConstants;
import org.eclipse.jst.jsf.apache.trinidad.tagsupport.Messages;
import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * ITransformOperation implementation specifically for the "breadCrumbs" JSF
 * Element.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class BreadCrumbsOperation extends AbstractTrinidadTransformOperation {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	public Element transform(Element srcElement, Element curElement) {
		Element outerSpan = createElement("span"); //$NON-NLS-1$
		String styleClass = calculateStyleClass("af_breadCrumbs", srcElement); //$NON-NLS-1$
		if (styleClass != null) {
			appendAttribute(outerSpan, "class", styleClass); //$NON-NLS-1$
		}
		String style = srcElement.getAttribute("inlineStyle"); //$NON-NLS-1$
		if (style != null) {
			appendAttribute(outerSpan, "style", style); //$NON-NLS-1$
		}
		List<Element> childCmdNavItems = getChildCmdNavItems(srcElement);
		int currentChildIndex = 0;
		int numChildren = childCmdNavItems.size();
		if (numChildren > 0) {
			for (Element childCmdNavItem: childCmdNavItems) {
				Element nobr;
				if (isVertical(srcElement)) {
					Element div = appendChildElement("div", outerSpan); //$NON-NLS-1$
					nobr = appendChildElement("nobr", div); //$NON-NLS-1$
					appendChildText(getIndentationString(currentChildIndex), nobr);
				} else {
					nobr = appendChildElement("nobr", outerSpan); //$NON-NLS-1$
				}
				Element innerSpan = appendChildElement("span", nobr); //$NON-NLS-1$
				tagConverterContext.addChild(
						childCmdNavItem, new ConvertPosition(innerSpan, 0));
				if (currentChildIndex < numChildren - 1) {
					//appendChildText("&nbsp;&nbsp;&gt;&nbsp;&nbsp;", nobr); //$NON-NLS-1$
					appendChildText("\u00A0 \u003E \u00A0", nobr); //$NON-NLS-1$
				}
				currentChildIndex++;
			}
		} else {
			appendAttribute(outerSpan, "style", ITrinidadConstants.STYLE_EMPTYELEMENT); //$NON-NLS-1$
			appendChildText(Messages.BreadCrumbsOperation_EmptyBreadCrumbsTag, outerSpan);
		}
		return outerSpan;
	}

	private List<Element> getChildCmdNavItems(Element srcElement) {
		List<Element> childCmdNavItems = new ArrayList<Element>();
		NodeList childElements = srcElement.getElementsByTagNameNS(ITrinidadConstants.URI_CORE, "commandNavigationItem"); //$NON-NLS-1$$
		if (childElements != null && childElements.getLength() > 0) {
			for (int i = 0, len = childElements.getLength(); i < len; i++) {
				childCmdNavItems.add((Element)childElements.item(i));
			}
		} else {
			Element nodeStampFacet = getChildFacetByName(srcElement, "nodeStamp"); //$NON-NLS-1$
			if (nodeStampFacet != null) {
				childElements = nodeStampFacet.getElementsByTagNameNS(ITrinidadConstants.URI_CORE, "commandNavigationItem"); //$NON-NLS-1$
				if (childElements != null && childElements.getLength() > 0) {
					for (int i = 0, len = childElements.getLength(); i < len; i++) {
						childCmdNavItems.add((Element)childElements.item(i));
					}
				}
			}
		}
		return childCmdNavItems;
	}

	private boolean isVertical(Element srcElement) {
		String orientationAttr = srcElement.getAttribute(ITrinidadConstants.ATTR_ORIENTATION);
		return "vertical".equalsIgnoreCase(orientationAttr); //$NON-NLS-1$
	}

	private String getIndentationString(int indentationLevel) {
		StringBuffer indentation = new StringBuffer(""); //$NON-NLS-1$
		for (int i = 0; i < indentationLevel; i++) {
			//indentation.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"); //$NON-NLS-1$
			indentation.append(" \u00A0 \u00A0 \u00A0 \u00A0 \u00A0"); //$NON-NLS-1$
		}
		return indentation.toString();
	}

}
