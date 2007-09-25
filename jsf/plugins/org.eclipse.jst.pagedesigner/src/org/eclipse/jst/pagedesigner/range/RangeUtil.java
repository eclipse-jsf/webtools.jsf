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
package org.eclipse.jst.pagedesigner.range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jst.pagedesigner.parts.DocumentEditPart;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;

/**
 * @author mengbo
 */
public class RangeUtil {
	/**
	 * append the child after the reference node as next sibling.
	 * 
	 * @param child
	 *            can't be null
	 * @param reference
	 *            can't be null
	 * @return ??
	 */
    //TODO: dead
//	private static Node appendAfter(Node child, Node reference) {
//		Node next = reference.getNextSibling();
//		if (next == null)
//        {
//			return reference.getParentNode().appendChild(child);
//        }
//        return reference.getParentNode().insertBefore(child, next);
//	}

	/**
	 * @param child
	 * @param reference
	 * @return ??
	 */
    // TODO: dead
//	private static Node insertBefore(Node child, Node reference) {
//		return reference.getParentNode().insertBefore(child, reference);
//	}

	/**
	 * Insert a node into the specified position. The node can be an element or
	 * DocumentFragment.
	 * 
	 * @param node
	 * @param position
	 */
	// TODO: dead
//	private static Node insertElement(DesignPosition position, Element node) {
//		EditPart containerEditPart = position.getContainerPart();
//		int offset = position.getOffset();
//
//		if (containerEditPart instanceof TextEditPart) {
//			TextEditPart textPart = (TextEditPart) containerEditPart;
//			String textData = textPart.getTextData();
//			Node textNode = (Node) textPart.getModel();
//			if (offset == 0)
//				return insertBefore(node, textNode);
//			else if (offset == textData.length())
//				return appendAfter(node, textNode);
//			else {
//				// inserting the element in the middle of text.
//				String before = textData.substring(0, offset);
//				String after = textData.substring(offset);
//
//				// XXX: don't know whether setNodeValue() will do all those
//				// escape or not.
//				textNode.setNodeValue(after);
//				Node newnode = insertBefore(node, textNode);
//
//				// XXX: don't know whether createTextNode() will do all those
//				// escape or not
//				Text t = textNode.getOwnerDocument().createTextNode(before);
//
//				insertBefore(t, newnode);
//				return newnode;
//			}
//		}
//        return insertIntoEditPart(containerEditPart, node, offset);
//	}

	/**
	 * @param containerEditPart
	 * @param node
	 * @param offset
	 * @return
	 */
	// TODO: dead
//	private static Node insertIntoEditPart(EditPart containerEditPart,
//			Node node, int offset) {
//		Node parent = (Node) containerEditPart.getModel();
//		List childParts = containerEditPart.getChildren();
//		if (offset >= childParts.size()) {
//			// to the end of parent
//			return parent.appendChild(node);
//		}
//        Node child = (Node) ((EditPart) childParts.get(offset)).getModel();
//        return insertBefore(node, child);
//	}

	// TODO: dead
//	private static TextPosition insertText(DesignPosition position, String data) {
//		// TODO: never read EditPart containerEditPart = position.getContainerPart();
//
//		position = moveIntoText(position);
//		int offset = position.getOffset();
//
//		if (position.getContainerPart() instanceof TextEditPart) {
//			// it is guaranteeed that now the containing edit part is text node.
//			TextEditPart textPart = (TextEditPart) position.getContainerPart();
//			String textData = textPart.getTextData();
//			String before = textData.substring(0, offset);
//			String after = textData.substring(offset);
//			if (data.startsWith(" ") && before.endsWith(" ")) {
//				before = before.substring(0, before.length() - 1) + "&nbsp;";
//			}
//			if (after.startsWith(" ") && data.endsWith(" ")) {
//				data = data.substring(0, data.length() - 1) + (char) 160;
//			}
//			String nextData = before + data + after;
//			IDOMText text = (IDOMText) textPart.getModel();
//			text.setData(nextData);
//			return new TextPosition(text, offset + data.length());
//		}
//        // can't merge into a neighboring text node. So create a text node
//        // of it's own
//        EditPart part = position.getContainerPart();
//        Node parent = (Node) part.getModel();
//        Text text = parent.getOwnerDocument().createTextNode(data);
//        insertIntoEditPart(part, text, offset);
//        return new TextPosition((IDOMText) text, offset);
//	}

	/**
	 * Try to make the position move into a text node.
	 * 
	 * @param position
	 * @return
	 */
    // TODO: dead
//	private static DesignPosition moveIntoText(DesignPosition position) {
//		EditPart container = position.getContainerPart();
//		if (container instanceof TextEditPart)
//			return position;
//		if (position.getOffset() > 0) {
//			EditPart pre = (EditPart) container.getChildren().get(
//					position.getOffset() - 1);
//			if (pre instanceof TextEditPart) {
//				return new DesignPosition(pre, ((TextEditPart) pre)
//						.getTextData().length());
//			}
//		}
//		if (position.getOffset() < container.getChildren().size()) {
//			EditPart next = (EditPart) container.getChildren().get(
//					position.getOffset());
//			if (next instanceof TextEditPart) {
//				return new DesignPosition(next, 0);
//			}
//		}
//		return position;
//	}

	/**
	 * try to move the position up to not inside a text. if the position is at 0
	 * index or last index of a text node, then try to move it up.
	 * 
	 * @param position
	 * @return
	 */
    // TODO: dead
//	private static DesignPosition moveOutFromText(DesignPosition position) {
//		EditPart container = position.getContainerPart();
//		if (container instanceof TextEditPart) {
//			int offset = position.getOffset();
//			String text = ((TextEditPart) container).getTextData();
//			if (offset == 0) {
//				return new DesignPosition(container.getParent(), container
//						.getParent().getChildren().indexOf(container));
//			} else if (offset == text.length()) {
//				return new DesignPosition(container.getParent(), container
//						.getParent().getChildren().indexOf(container) + 1);
//			}
//		}
//		return position;
//	}

//	private static void insertDocumentFragment(DesignPosition position,
//			DocumentFragment fragment) {
//		// FIXME: NOT DONE.
//	}

	/**
	 * Test whether the range intersect with the part.
	 * 
	 * @param range
	 * @param part
	 * @return true if thereis an intersection
	 */
	public static boolean intersect(DesignRange range, EditPart part) {
		if (range == null || !range.isValid())
			return false;
		range = normalize(range);
		if (part instanceof DocumentEditPart)
			return true;
		EditPart parent = part.getParent();
		int index = parent.getChildren().indexOf(part);
		DesignPosition left = new DesignPosition(parent, index);
		DesignPosition right = new DesignPosition(parent, index + 1);
		int compare = compareDesignPosition(left, range.getEndPosition());
		if (compare == 1 || compare == 0 || compare == Integer.MIN_VALUE)
			return false;

		compare = compareDesignPosition(right, range.getStartPosition());
		if (compare == -1 || compare == 0 || compare == Integer.MIN_VALUE)
			return false;

		return true;
	}

	/**
	 * make sure the start position is before end position. If the original
	 * range is already normalized, then the original range will be returned
	 * without constructing a new one.
	 * 
	 * @param range
	 * @return the normalized range
	 */
	public static DesignRange normalize(DesignRange range) {
		if (range == null || !range.isValid()) {
			return range;
		}
		int result = compareDesignPosition(range.getStartPosition(), range
				.getEndPosition());
		if (result == 1)
        {
			return new DesignRange(range.getEndPosition(), range
					.getStartPosition());
        }
        return range;
	}

	/**
	 * 
	 * @param p1
	 * @param p2
	 * @return 0 means equal. 1 Means p1 is after p2. -1 means p1 is before p2.
	 *         Integer.MIN_VALUE means some error and can't compare.
	 */
	private static int compareDesignPosition(DesignPosition p1, DesignPosition p2) {
		if (!p1.isValid() || !p2.isValid())
			return Integer.MIN_VALUE;
		if (p1.equals(p2))
			return 0;
		int offset1 = p1.getOffset();
		int offset2 = p2.getOffset();
		List a1 = getAncesters(p1.getContainerPart());
		List a2 = getAncesters(p2.getContainerPart());
		if (a1 == null || a2 == null)
			return Integer.MIN_VALUE;
		if (a1.get(0) != a2.get(0))
			return Integer.MIN_VALUE; // not same DocumentEditPart
		for (int i = 1;; i++) {
			EditPart p1a = (EditPart) a1.get(i);
			EditPart p2a = (EditPart) a2.get(i);
			if (p1a == p2a) {
				if (p1a != null)
                {
					continue; // same ancester
                }
                // both are null. just compare the offset.
                return offset1 < offset2 ? -1
                		: (offset1 == offset2 ? 0 : 1);
			}
			// p1a != p2a. now we can just compare p1a and p2a to decide the
			// order.
			if (p1a != null)
				offset1 = p1a.getParent().getChildren().indexOf(p1a);
			if (p2a != null)
				offset2 = p2a.getParent().getChildren().indexOf(p2a);
			if ((p1a == null && p2a == null) || (p1a != null && p2a != null)) {
				return offset1 < offset2 ? -1 : (offset1 == offset2 ? 0 : 1);
			} else if (p1a == null) {
				return offset1 <= offset2 ? -1 : 1;
			} else {
				return offset1 >= offset2 ? 1 : -1;
			}
		}
	}

	/**
	 * Get a list of ancester nodes starting from the DocumentEditPart till the
	 * node.
	 * 
	 * @param part
	 * @return
	 */
	private static List getAncesters(EditPart part) {
		List list = new ArrayList();
		while (part != null) {
			list.add(part);
			if (part instanceof DocumentEditPart)
            {
				break;
            }
			part = part.getParent();
		}
		if (part == null) {
			// if part ==null, means we didn't find a DocumentEditPart,
			// something must be wrong.
			return null;
		}
		// reverse to make it starting from the docuemnteditpart node.
		Collections.reverse(list);
		list.add(null); // add an null terminator.
		return list;
	}

	/**
	 * find the smallest common ancester of two edit part.
	 * 
	 * @param part1
	 * @param part2
	 * @return
	 */
	private static EditPart findCommonAncester(EditPart part1, EditPart part2) {
		if (part1 == part2) {
			return part1;
		}
		List list1 = getAncesters(part1);
		if (list1 == null)
			return null;
		List list2 = getAncesters(part2);
		if (list2 == null)
			return null;
		if (list1.get(0) != list2.get(0))
			return null;
		EditPart common = (EditPart) list1.get(0);
		for (int i = 1;; i++) {
			EditPart p1 = (EditPart) list1.get(i);
			EditPart p2 = (EditPart) list2.get(i);
			if (p1 == null || p2 == null)
				return common;
			if (p1 != p2)
				return common;
			common = p1;
		}

	}

	/**
	 * @param range
	 * @return the common ancestor
	 */
	public static EditPart findCommonAncestor(DesignRange range) {
		if (!range.isValid()) {
			return null;
		}
		DesignPosition startPosition = range.getStartPosition();
		DesignPosition endPosition = range.getEndPosition();
		return findCommonAncester(startPosition.getContainerPart(), endPosition
				.getContainerPart());
	}
}
