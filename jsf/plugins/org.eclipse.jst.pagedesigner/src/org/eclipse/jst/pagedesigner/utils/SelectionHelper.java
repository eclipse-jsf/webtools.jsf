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

import org.eclipse.gef.EditPart;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.pagedesigner.dom.DOMPosition;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.DOMRefPosition;
import org.eclipse.jst.pagedesigner.dom.DOMRefPosition2;
import org.eclipse.jst.pagedesigner.dom.DOMUtil;
import org.eclipse.jst.pagedesigner.dom.EditValidateUtil;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.IndexedRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.eclipse.wst.xml.core.internal.regions.DOMRegionContext;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * @author mengbo
 * @version 1.5
 */
public class SelectionHelper {
	/**
	 * convert the text selection to a Node. Will use the start offset of the
	 * text selection.
	 * 
	 * @param model
	 * @param textSel
	 * @return the node for the text selectin in model or null
	 */
	public static Node toNode(IStructuredModel model, ITextSelection textSel) {
		// FIXME: currently always normalize to a single node. should also
		// consider change into DesignRange
		// on text selection, find the appropriate Node
		Object inode = model.getIndexedRegion(textSel.getOffset());
		if (inode instanceof Node) {
			return (Node) inode;
		}
        return null;
	}

	/**
	 * convert a structured selection of NodeEditPart or Node into the first
	 * node.
	 * 
	 * @param selection
	 * @return the node for the selection or null
	 */
	public static Node toNode(IStructuredSelection selection) {
		if (selection.isEmpty()) {
			return null;
		}
		Object first = selection.getFirstElement();
		if (first instanceof Node) {
			return (Node) first;
		} else if (first instanceof NodeEditPart) {
			return ((NodeEditPart) first).getIDOMNode();
		} else {
			return null;
		}
	}

	/**
	 * convert a DesignRange into a single node.
	 * 
	 * @param range
	 * @return the node for the design range or null
	 */
	public static Node toNode(DesignRange range) {
		if (range.isValid()) {
			Node node1 = range.getStartPosition().getContainerNode();
			Node node2 = range.getEndPosition().getContainerNode();
			return DOMUtil.findCommonAncester(node1, node2);
		}
        return null;
	}

	/**
	 * @param model
	 * @param region
	 *            if null, then will calculate it using offset.
	 * @param offset
	 *            offset in source.
	 * @return a dom position for the region and offset
	 */
	public static IDOMPosition toDOMPosition(IDOMModel model,
			IndexedRegion region, int offset) {
		if (region == null) {
			region = model.getIndexedRegion(offset);
		}
		if (region == null && offset > 0) {
			// in case this is at end of file.
			offset = offset - 1;
			region = model.getIndexedRegion(offset);
			if (region != null) {
				if (region.getEndOffset() >= offset + 1) {
					offset += 1; // restore offset.
				}
			}
		}
		if (region == null) {
			return new DOMPosition(model.getDocument(), 0);
		}
		IDOMNode node = (IDOMNode) region;
		int start = node.getStartOffset();
		if (offset <= start) {
			return new DOMRefPosition(node, false);
		}
		int end = node.getEndOffset();
		if (offset >= end) {
			return new DOMRefPosition(node, true);
		}
		if (node instanceof CharacterData) {
			String data = ((CharacterData) node).getData();
			String source = node.getSource();
			if (data.equals(source)) {
				return new DOMPosition(node, offset - start);
			}
			IStructuredDocumentRegion r = node
					.getFirstStructuredDocumentRegion();
			int countedData = 0;
			// TODO: dead? int offsetInNode = offset - start;
			while (r != null) {
				if (DOMRegionContext.XML_CHAR_REFERENCE.equals(r.getType())
						|| DOMRegionContext.XML_ENTITY_REFERENCE.equals(r
								.getType())) {
					countedData += 1; // FIXME: what if the entity reference's
					// corresponding data is more than 1
					// char?
					// where can we get that information?
					if (r.getEnd() >= offset) {
						return new DOMPosition(node, countedData);
					}
				} else {
					if (r.getEnd() >= offset) {
						return new DOMPosition(node, countedData + offset
								- r.getStart());
					}
					countedData += r.getLength();
				}
				r = r.getNext();
			}
			return new DOMRefPosition(node, true);
		} else if (node instanceof Element) {
			CMElementDeclaration cm = CMUtil
					.getElementDeclaration((Element) node);
			if (cm != null && cm.getContentType() == CMElementDeclaration.EMPTY) {
				// this node can't have children.
				return new DOMRefPosition(node, true);
			}
			IStructuredDocumentRegion startRegion = node
					.getStartStructuredDocumentRegion();
			if (startRegion == null) {
				return new DOMRefPosition(node, true);
			}
            int startRegionEnd = node.getStartStructuredDocumentRegion()
            		.getEnd();
            if (offset <= startRegionEnd) {
            	// it is in the start tag region. So put position at first
            	// child position.
            	return new DOMRefPosition2(node, false);
            }
            return new DOMRefPosition2(node, true);
		} else {
			return new DOMRefPosition(node, true);
		}
		// XXX: the implementation in EditModelQuery seemed to be very complex.
		// Need revisit that
		// and refactor the implementation to this class later. (lium)
	}

	/**
	 * Give a text selection with offset and length, convert it into a Designer
	 * selection (IStrucuturedSelection of editpart or DesignerRange). If the
	 * text selection include just a single element node, we'll create a
	 * IStructuredSelection, otherwise we'll create a DesignerRange.
	 * 
	 * @param graphicViewer
	 * @param offset
	 * @param length
	 * @return a selection
	 */
	public static ISelection convertToDesignerSelection(
			IHTMLGraphicalViewer graphicViewer, int offset, int length) {
		IDOMModel model = graphicViewer.getModel();
		IndexedRegion region1 = model.getIndexedRegion(offset);
		IndexedRegion region2 = model.getIndexedRegion(offset + length);
		IDOMNode node1 = (IDOMNode) region1;

		if (node1 == null) {
			IDOMPosition endOfDoc = new DOMRefPosition2(model.getDocument(),
					true);
			DesignPosition p = DOMPositionHelper.toDesignPosition(endOfDoc);
			return new DesignRange(p, p);
		}

		if ((region1 == region2 || node1.getEndOffset() == offset + length)
				&& !(node1 instanceof Text)) {
			// ok, we selected a single node.
			EditPart part = (EditPart) node1.getAdapterFor(EditPart.class);
			if (part != null) {
				return new StructuredSelection(part);
			}
		}

		// when we reach here, we'll create a DesignerRange
		IDOMPosition position1 = toDOMPosition(model, region1, offset);
		IDOMPosition position2 = (length == 0 ? position1 : toDOMPosition(
				model, region2, offset + length));

		if (position1 == null || position2 == null) {
			return new DesignRange(null, null);
		}
		DesignPosition p1 = DOMPositionHelper.toDesignPosition(position1);
		DesignPosition p2 = (length == 0 ? p1 : DOMPositionHelper
				.toDesignPosition(position2));
		if (p1 == null || p2 == null) {
			return new DesignRange(null, null);
		}

		return new DesignRange(p1, p2);

	}

	/**
	 * convert a IDOMPosition into index in the source.
	 * 
	 * @param p
	 * @return
	 */
	private static int getIndexedRegionLocation(IDOMPosition p) {
		if (!EditValidateUtil.validPosition(p)) {
			return 0;
		}

		IDOMNode parent = (IDOMNode) p.getContainerNode();
		if (p.isText()) {
			String text = ((CharacterData) parent).getData();
			String source = parent.getSource();
			if (text.length() == source.length()) {
				// no entity reference.
				return parent.getStartOffset() + p.getOffset();
			}
			// CR404708. Need to handle entity reference in the text.
			int offset = p.getOffset();
			int counted = 0;
			IStructuredDocumentRegion r = parent
					.getFirstStructuredDocumentRegion();
			while (r != null && counted < offset) {
				if (DOMRegionContext.XML_CHAR_REFERENCE.equals(r.getType())
						|| DOMRegionContext.XML_ENTITY_REFERENCE.equals(r
								.getType())) {
					counted++;
					if (counted >= offset) {
						return r.getEndOffset();
					}
				} else {
					int length = r.getLength();
					if (counted + length >= offset) {
						return r.getStartOffset() + offset - counted;
					}
					counted += length;
				}
				r = r.getNext();
			}
			return parent.getStartOffset() + p.getOffset();
		}
        IDOMNode previous = (IDOMNode) p.getPreviousSiblingNode();
        if (previous != null) {
        	return previous.getEndOffset();
        }
        IDOMNode next = (IDOMNode) p.getNextSiblingNode();
        if (next != null) {
        	return next.getStartOffset();
        }
        IStructuredDocumentRegion r = parent
        		.getStartStructuredDocumentRegion();
        if (r != null) {
        	return r.getEnd();
        }
        // r == null normally means the parent is the document node.
        return parent.getEndOffset();
	}

	/**
	 * convert design selection of structured selection of NodeEditPart into
	 * structured selection of Node
	 * 
	 * @param sel
	 * @return a structured selectino
	 */
	public static IStructuredSelection convertFromDesignSelection(
			IStructuredSelection sel) {
		List list = sel.toList();
		if (list != null) {
			List result = new ArrayList(list.size());
			for (int i = 0, size = list.size(); i < size; i++) {
				NodeEditPart part = (NodeEditPart) list.get(i);
				result.add(part.getIDOMNode());
			}
			return new StructuredSelection(result);
		}
        return new StructuredSelection();
	}

	/**
	 * 
	 * @param range
	 *            selection from designer, could be IStructuredSelection of
	 *            NodeEditPart, or DesignRange.
	 * @return a text selection
	 */
	public static ITextSelection convertFromDesignSelection(DesignRange range) {
		if (range.isValid()) {
			IDOMPosition start = DOMPositionHelper.toDOMPosition(range
					.getStartPosition());
			IDOMPosition end = DOMPositionHelper.toDOMPosition(range
					.getEndPosition());
			// We should not encounter invalid position.
			if (EditValidateUtil.validPosition(start)
					&& EditValidateUtil.validPosition(end)) {
				int offset = getIndexedRegionLocation(start);
				int endoffset = getIndexedRegionLocation(end);
				if (offset > endoffset) {
					int temp = offset;
					offset = endoffset;
					endoffset = temp;
				}
				return new TextSelection(offset, endoffset - offset);
			} 
		}
        return new TextSelection(0, 0);
	}

	/**
	 * @param selection
	 * @return a text selection for the selection or TextSelection(0,0)
	 * if nothing can be determined
	 */
	public static ITextSelection convertFromDesignSelectionToTextSelection(
			ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection nodes = convertFromDesignSelection((IStructuredSelection) selection);
			IDOMNode node = (IDOMNode) nodes.getFirstElement();
			if (node != null && node.getNodeType() != Node.DOCUMENT_NODE) {
				return new TextSelection(node.getStartOffset(), node
						.getEndOffset()
						- node.getStartOffset());
			}
		} else if (selection instanceof DesignRange) {
			return convertFromDesignSelection((DesignRange) selection);
		}
        return new TextSelection(0, 0);
	}
}
