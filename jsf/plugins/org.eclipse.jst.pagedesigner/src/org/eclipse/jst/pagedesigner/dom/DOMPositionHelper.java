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
package org.eclipse.jst.pagedesigner.dom;

import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.EditPart;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.parts.TextEditPart;
import org.eclipse.jst.pagedesigner.utils.HTMLUtil;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRefPosition;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * @author mengbo
 */
public class DOMPositionHelper {
	/**
	 * @param position
	 * @return the design position
	 */
	public static DesignPosition toDesignRefPosition(DOMRefPosition position) {
		Node node = position.getReferenceNode();
		do {
			IDOMNode container = (IDOMNode) node.getParentNode();
			EditPart part = (EditPart) container.getAdapterFor(EditPart.class);
			if (part != null) {
				// XXX: what if the node has not corresponding part?
				EditPart child = DOMPositionHelper.findEditPart(part, node);
				if (child != null) {
					return new DesignRefPosition(child, position.isForward());
				}
                return DesignPosition.INVALID;
			}
            node = node.getParentNode();
		} while (true);
	}

	/**
	 * 
	 * @param position
	 *            if it is null, then will return null
	 * @return null if position is null or invalid.
	 */
	public static DesignPosition toDesignPosition(IDOMPosition position) {
		if (position == null) {
			return null;
		}
		if (position instanceof DOMRefPosition) {
			return toDesignRefPosition((DOMRefPosition) position);
		}
		do {
			IDOMNode container = (IDOMNode) position.getContainerNode();
			EditPart part = (EditPart) container.getAdapterFor(EditPart.class);
			if (part != null) {
				if (container instanceof Text) {
					String textData = ((Text) container).getData();
					String displayData = ((TextEditPart) part).getTextData();
					return new DesignPosition(part,
							textDataOffsetToDisplayOffset(textData,
									displayData, position.getOffset()));
				}
                Node pre = position.getPreviousSiblingNode();
                while (pre != null) {
                	int index = findChildEditPartIndex(part, pre);
                	if (index != -1) {
                		return new DesignPosition(part, index + 1);
                	}
                	pre = pre.getPreviousSibling();
                }
                return new DesignPosition(part, 0);
			}
            position = new DOMRefPosition(position.getContainerNode(),
            		false);
		} while (true);
	}

	/**
	 * Here is the position is not currect, currently it will returns invalid
	 * pos.
	 * 
	 * @param position
	 * @return the design position
	 */
	public static DesignPosition toDesignPosition1(IDOMPosition position) {
		if (position instanceof DOMRefPosition) {
			return toDesignRefPosition((DOMRefPosition) position);
		}
		do {
			IDOMNode container = (IDOMNode) position.getContainerNode();
			EditPart part = (EditPart) container.getAdapterFor(EditPart.class);
			if (part != null) {
				if (container instanceof Text) {
					String textData = ((Text) container).getData();
					String displayData = ((TextEditPart) part).getTextData();
					return new DesignPosition(part,
							textDataOffsetToDisplayOffset(textData,
									displayData, position.getOffset()));
				}
                Node pre = position.getPreviousSiblingNode();
                while (pre != null) {
                	int index = findChildEditPartIndex(part, pre);
                	if (index != -1) {
                		return new DesignPosition(part, index + 1);
                	}
                	pre = pre.getPreviousSibling();
                }
                return new DesignPosition(part, 0);
			}
            return DesignPosition.INVALID;
		} while (true);
	}

	static int findChildEditPartIndex(EditPart parent, Node node) {
		List children = parent.getChildren();
		for (int i = 0; i < children.size(); i++) {
			if (((EditPart) children.get(i)).getModel() == node) {
				return i;
			}
		}
		return -1;
	}

	static EditPart findEditPart(EditPart parent, Node node) {
		List children = parent.getChildren();
		EditPart part;
		for (int i = 0; i < children.size(); i++) {
			if ((part = (EditPart) children.get(i)).getModel() == node) {
				return part;
			}
		}
		return null;
	}

	/**
	 * convert a DesignPosition into DOMPosition.
	 * 
	 * @param position
	 * @return the dom position
	 */
	public static IDOMPosition toDOMRefPosition(DesignRefPosition position) {
		// ok, it is not text.
		EditPart sibling = position.getRefPart();
		if (sibling != null) {
			return new DOMRefPosition((Node) sibling.getModel(), position
					.caretIsAtRight());
		}
		// should never happens
		Assert.isTrue(false);
		return null;
	}

	/**
	 * convert a DesignPosition into DOMPosition.
	 * 
	 * @param position
	 * @return the dom position
	 */
	public static IDOMPosition toDOMPosition(DesignPosition position) {
		if (!EditValidateUtil.validPosition(position)) {
			return null;
		} else if (position instanceof DesignRefPosition) {
			return toDOMRefPosition((DesignRefPosition) position);
		}
		EditPart part = position.getContainerPart();
		if (part instanceof TextEditPart) {
			Text text = (Text) ((TextEditPart) part).getIDOMNode();
			int offset = position.getOffset();
			if (offset == 0) {
				return new DOMPosition(text, 0);
			}
            String displayData = ((TextEditPart) part).getTextData();
            String nodeData = text.getData();
            if (offset >= displayData.length()) {
            	// point to end of the text node.
            	return new DOMPosition(text, nodeData.length());
            }
            // we need to calculate it out.
            int index = displayOffsetToTextDataOffset(displayData,
            		nodeData, offset);
            return new DOMPosition(text, index);
		}
        // ok, it is not text.
        EditPart sibling = position.getSiblingEditPart(true);
        if (sibling instanceof NodeEditPart) {
        	return new DOMRefPosition(((NodeEditPart) sibling).getDOMNode(), false);
        }

        sibling = position.getSiblingEditPart(false);
        if (sibling instanceof NodeEditPart) {
        	return new DOMRefPosition(((NodeEditPart) sibling).getDOMNode(), true);
        }

        // no previous sibling, no next sibling, the parent node must be
        // empty
        return new DOMPosition(((NodeEditPart) part).getDOMNode(), 0);
	}

	/**
	 * if "position" is inside a text node, then split the text node and return
	 * a new IDOMPosition semantically equal to the position in the two
	 * splitted text. If the "position" is not a text position, then no action
	 * will be taken and will return the original position.
	 * 
	 * @param position
	 * @return IDOMPosition
	 */
	public static IDOMPosition splitText(IDOMPosition position) {
		Node container = position.getContainerNode();
		if (container instanceof Text) {
			int offset = position.getOffset();
			if (offset <= 0) {
				// at beginning of text node. no need to split
				return new DOMRefPosition(container, false);
			}
			String textData = ((Text) container).getData();
			if (offset >= textData.length()) {
				// at end of text node. no need to split
				return new DOMRefPosition(container, true);
			}
			// ok, we need split
			((Text) container).splitText(offset);
			return new DOMRefPosition(container, true);
		}
        return position;
	}

	/**
	 * Remove all the content in the range. And return the new position.
	 * 
	 * @param range
	 * @return the dom position
	 */
	public static IDOMPosition removeRange(DOMRange range) {
		boolean ordered = range.isOrdered();
//		IDOMPosition start = ordered ? range.getStartPosition() : range
//				.getEndPosition();
		IDOMPosition end = ordered ? range.getEndPosition() : range
				.getStartPosition();

		// FIXME: Not DONE:
		return end;
	}

	/**
	 * try to merge the position in adjacent text node (if it is not already in)
	 * 
	 * @param position
	 * @return the dom position
	 */
	public static IDOMPosition mergeIntoText(IDOMPosition position) {
		if (position.getContainerNode() instanceof Text)
			return position;
		Node pre = position.getPreviousSiblingNode();
		if (pre instanceof Text) {
			return new DOMPosition(pre, ((Text) pre).getData().length());
		}
		Node after = position.getNextSiblingNode();
		if (after instanceof Text) {
			return new DOMPosition(after, 0);
		}
		return position;
	}

	/**
	 * @param displayData
	 * @param nodeData
	 * @param offset
	 * @return the offset
	 */
	// FIXME: this method is still buggy
	public static int displayOffsetToTextDataOffset(String displayData,
			String nodeData, int offset) {
		char[] display = displayData.toCharArray();
		if (offset >= display.length) {
			// offset is already at end
			return nodeData.length();
		}
		char[] node = nodeData.toCharArray();
		int nodeDataLength = node.length;
		int displayIndex = 0;
		int nodeIndex = 0;

		while (displayIndex < offset && nodeIndex < nodeDataLength) {
			if (display[displayIndex] == node[nodeIndex]) {
				displayIndex++;
				nodeIndex++;
				continue;
			}
			if (HTMLUtil.isHTMLWhitespace(node[nodeIndex])) {
				if (HTMLUtil.isHTMLWhitespace(display[displayIndex])) {
					displayIndex++;
					nodeIndex++;
				} else {
					nodeIndex++;
				}
				continue;
			}
            // should not happen!
            displayIndex++;
            nodeIndex++;
		}

		if (nodeIndex >= nodeDataLength)
			return nodeDataLength;
		// else means displayIndex == offset
		// since we already checked that offset < displayLength, so we can get
		// the next char
		if (display[offset] != ' ') {
			// we may need to skip whitespaces after nodeIndex
			while (nodeIndex < nodeDataLength
					&& HTMLUtil.isHTMLWhitespace(node[nodeIndex])) {
				nodeIndex++;
			}
		}
		return nodeIndex;
	}

	/**
	 * @param nodeData 
	 * @param displayData
	 * @param offset
	 * @return the offset
	 */
	// FIXME: this method is still buggy
	public static int textDataOffsetToDisplayOffset(String nodeData,
			String displayData, int offset) {
		if (offset >= nodeData.length()) {
			return displayData.length();
		}
		char[] node = nodeData.toCharArray();
		char[] display = displayData.toCharArray();

		int displayIndex = 0;
		int nodeIndex = 0;
		int displayDataLength = display.length;

		while (nodeIndex < offset && displayIndex < displayDataLength) {
			if (display[displayIndex] == node[nodeIndex]) {
				displayIndex++;
				nodeIndex++;
				continue;
			}
			if (HTMLUtil.isHTMLWhitespace(node[nodeIndex])) {
				if (HTMLUtil.isHTMLWhitespace(display[displayIndex])) {
					displayIndex++;
					nodeIndex++;
				} else {
					nodeIndex++;
				}
				continue;
			}
            // should not happen!
            displayIndex++;
            nodeIndex++;
		}
		return displayIndex;
	}

	/**
	 * Convert a IDOMPosition to IDOMRefPosition. If can't convert to
	 * IDOMRefPosition, will return the original one.
	 * 
	 * @param position
	 * @return IDOMPosition
	 */
	public static IDOMPosition toDOMRefPosition(IDOMPosition position) {
		if (position.isText()) {
			return position; // can't convert Text node.
		}
		if (position instanceof IDOMRefPosition) {
			return position;
		}
		if (position.getNextSiblingNode() != null) {
			return new DOMRefPosition(position.getNextSiblingNode(), false);
		}
		if (position.getPreviousSiblingNode() != null) {
			return new DOMRefPosition(position.getPreviousSiblingNode(), true);
		}
        return new DOMRefPosition2(position.getContainerNode(), true);
	}
}
