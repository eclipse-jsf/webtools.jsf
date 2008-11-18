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
package org.eclipse.jst.pagedesigner.actions.link;

import org.eclipse.gef.EditPart;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.parts.TextEditPart;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * @author mengbo
 * @version 1.5
 */
public class HtmlLinkCreator extends AbstractLinkCreator {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.actions.link.ILinkCreator#makeLinkElement(org.eclipse.gef.EditPart,
	 *      org.eclipse.jst.pagedesigner.viewer.DesignRange)
	 */
	public Element makeLinkElement(EditPart part, DesignRange range) {
		if (part instanceof TextEditPart) {
			Text middleNode = LinkUtil.splitDomText(part, range);
			EditPart parent = part.getParent();
			Node parentNode = (Node) parent.getModel();
			Document doc = (parentNode instanceof Document) ? (Document) parentNode
					: (parentNode.getOwnerDocument());

			Element htmlLink = doc.createElement(IHTMLConstants.TAG_A);
			htmlLink.setAttribute(ICSSPropertyID.ATTR_HREF, ""); //$NON-NLS-1$
			Text text = doc.createTextNode(middleNode.getNodeValue());
			htmlLink.appendChild(text);
			parentNode.replaceChild(htmlLink, middleNode);
			return htmlLink;
		}

		return null;
	}

	public String getSourcePreview(EditPart part, DesignRange range) {
		if (part instanceof TextEditPart) {
			TextEditPart textPart = (TextEditPart) part;
			int[] offsets = textPart.getSelectedRange();
			String displayData = textPart.getTextData();

			String linkExp = displayData.substring(offsets[0], offsets[1]);
			StringBuffer sb = new StringBuffer();
			sb.append("<a href=\"\">"); //$NON-NLS-1$
			sb.append(linkExp);
			sb.append("</a>"); //$NON-NLS-1$
			return sb.toString();
		}
		return null;
	}
}
