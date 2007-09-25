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
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.DOMRangeHelper;
import org.eclipse.jst.pagedesigner.dom.DOMRefPosition;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.parts.TextEditPart;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * @author mengbo
 * @version 1.5
 */
public class LinkUtil {
	/**
	 * @param part
	 * @param range
	 * @return the select text if part is a text node or null.
	 */
	public static String getSelectedText(EditPart part, DesignRange range) {
		if (part instanceof TextEditPart) {
			TextEditPart textPart = (TextEditPart) part;
			int[] offsets = textPart.getSelectedRange();
			String displayData = textPart.getTextData();

			String linkExp = displayData.substring(offsets[0], offsets[1]);
			return linkExp;
		}
		return null;
	}

	/**
	 * @param part
	 * @param range
	 * @return the text from part split if it is a text node or null
	 */
	public static Text splitDomText(EditPart part, DesignRange range) {
		if (part instanceof TextEditPart) {
			Text textNode = (Text) part.getModel();

			DOMRange domRange = DOMRangeHelper.toDOMRange(range);
			IDOMPosition start = domRange.getStartPosition();
			IDOMPosition end = domRange.getEndPosition();
			int domTempStartOffset = computeOffset(start, textNode);
			int domTempEndOffset = computeOffset(end, textNode);

			int domStartOffset = Math.min(domTempStartOffset, domTempEndOffset);
			int domEndOffset = Math.max(domTempStartOffset, domTempEndOffset);

			Text lastNode = textNode;
			if (domStartOffset > 0) {
				lastNode = textNode.splitText(domStartOffset);
			}
			lastNode = lastNode.splitText(domEndOffset - domStartOffset);
			Text middleNode = (Text) lastNode.getPreviousSibling();
			return middleNode;
		}
		return null;
	}

	private static int computeOffset(IDOMPosition pos, Text textNode) {
		int domOffset = 0;
		if (pos instanceof DOMRefPosition) {
			DOMRefPosition rep = (DOMRefPosition) pos;
			boolean forward = rep.isForward();
			Node refNode = rep.getReferenceNode();

			if ((refNode != textNode) && forward || (refNode == textNode)
					&& !forward) {
				domOffset = 0;
			} else {
				domOffset = textNode.getLength();
			}
		} else {
			domOffset = pos.getOffset();
		}
		return domOffset;
	}
}
