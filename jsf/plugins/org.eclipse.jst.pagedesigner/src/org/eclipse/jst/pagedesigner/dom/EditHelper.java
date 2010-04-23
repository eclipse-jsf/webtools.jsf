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
import java.util.Stack;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.EditPart;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.commands.range.WorkNode;
import org.eclipse.jst.pagedesigner.parts.TextEditPart;
import org.eclipse.jst.pagedesigner.utils.HTMLUtil;
import org.eclipse.jst.pagedesigner.validation.caret.ActionData;
import org.eclipse.jst.pagedesigner.validation.caret.IMovementMediator;
import org.eclipse.jst.pagedesigner.validation.caret.InlineEditingNavigationMediator;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRefPosition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * @author mengbo
 */
public class EditHelper {
//	public final static boolean INNER_DEBUG = false;

	private final static int OUT_OF_LEFT = 1;

	private final static int LEFT_NAME = 2;

	/**
	 * indicates a position in the middle
	 */
	public final static int IN_MIDDLE = 3;

	private final static int RIGHT_NAME = 4;

	private final static int OUT_OF_RIGHT = 5;

	private static final EditHelper _instance = new EditHelper();

	//private static Logger _log = PDPlugin.getLogger(EditHelper.class);

	private EditHelper() {
        //  no external instantiation
	}

	/**
	 * Move operation position in front of next non-blank and non-transparent
	 * char. The caller should ensure position's container node is not
	 * transparent text node.
	 * 
	 * @param position
	 * @param forward
	 * @param forEmpty
	 * @return the offset
	 */
	public int getTextNextOffset(IDOMPosition position, boolean forward,
			boolean forEmpty) {
		EditValidateUtil.validPosition(position);
		Assert.isTrue(!EditModelQuery.isTransparentText(position
				.getContainerNode()));
		Text text = (Text) position.getContainerNode();
		int offset = position.getOffset();
		String data = text.getNodeValue();
		if (forward) {
			while (offset < data.length()
					&& HTMLUtil.isHTMLWhitespace(data.charAt(offset))) {
				offset++;
			}
		} else {
			while (offset > 0
					&& HTMLUtil.isHTMLWhitespace(data.charAt(offset - 1))) {
				offset--;
			}
		}
		return offset;

	}

	/**
	 * @return the singleton instance
	 */
	public static EditHelper getInstance() {
		return _instance;
	}

	/**
	 * This caret from current operation position to next position, this method
	 * will convert DesignPosition in to DOMPosition, then call dom function to
	 * move dom position. Here we might insert some complex rules to see whether
	 * move is valid.
	 * 
	 * @param action
	 * @param currentPosition
	 * @param forward
	 * @return the dom position
	 */
	public static DesignPosition moveToNextEditPosition(int action,
			DesignPosition currentPosition, boolean forward) {
		IDOMPosition position;
		position = DOMPositionHelper.toDOMPosition(currentPosition);
		position = moveToNextEditPosition(position, forward,
				new InlineEditingNavigationMediator(
						new ActionData(action, null)));
		if (position == null) {
			return currentPosition;
		}

		EditValidateUtil.validPosition(position);
		return DOMPositionHelper.toDesignPosition(position);
	}

	/**
	 * Move operation position to next edit position. We may need rule to valid
	 * it based on operation ID and direction. We need to pack transparent
	 * string.
	 * 
	 * @param currentPosition
	 * @param forward
	 * @param validator
	 * @return the dom position
	 */
	public static IDOMPosition moveToNextEditPosition(
			IDOMPosition currentPosition, boolean forward,
			IMovementMediator validator) {
		IDOMPosition result = null;
		CaretMoveIterator moveIterator = new CaretMoveIterator(null, validator,
				currentPosition, forward);
		if ((result = moveIterator.moveToNextEditPosition(currentPosition,
				forward, validator)) == null) {
			result = currentPosition;
		}
		return result;
	}

	/**
	 * Delete a node, in case it is 'body' or 'html', it won't perform delete.
	 * 
	 * @param node
	 * @return the node
	 */
	public static Node deleteNode(Node node) {
		if (node == null || node.getNodeName() == null) {
			return null;
		}
		String name = node.getLocalName();

		if (name != null
				&& (name.equalsIgnoreCase(IHTMLConstants.TAG_BODY)
						|| name.equalsIgnoreCase(IHTMLConstants.TAG_HEAD)
						|| name.equalsIgnoreCase(IHTMLConstants.TAG_HTML))) {
			return null;
		}
		Node parent = node.getParentNode();
		name = parent.getNodeName();
		if (parent != null
				&& name != null
				&& parent.getNodeName().equalsIgnoreCase(
						IHTMLConstants.TAG_HEAD)) {
			return null;
		}
		return parent.removeChild(node);
	}

	/**
	 * Order the IDOMPositions in a range in ascending order.
	 * 
	 * @param range
	 * @return the dom range
	 */
	public static DOMRange normal(DOMRange range) {
		EditValidateUtil.validRange(range);
		IDOMPosition p1 = range.getStartPosition();
		IDOMPosition p2 = range.getEndPosition();
		if (EditModelQuery.getIndexedRegionLocation(p1) > EditModelQuery
				.getIndexedRegionLocation(p2)) {
			return new DOMRange(p2, p1);
		}
        return range;
	}

	/**
	 * Move position in to node from its outside, the node should be breakble.
	 * 
	 * @param node
	 * @param validator 
	 * @param forward
	 * @return the dom position
	 */
	public static IDOMPosition moveInto(Node node, IMovementMediator validator,
			boolean forward) {
		CaretMoveIterator moveIterator = new CaretMoveIterator(null, validator,
				new DOMRefPosition(node, !forward), forward);
		return moveIterator.moveIn(node);
	}

	/**
	 * Convert a DomRefPosition into DOMPosition.
	 * 
	 * @param position
	 * @return the dom position
	 */
	public static IDOMPosition ensureDOMPosition(IDOMPosition position) {
		if (position instanceof DOMRefPosition) {
			return new DOMPosition(position.getContainerNode(), position
					.getOffset());
		}
		return position;
	}

	/**
	 * @param currentNode
	 * @param pos1
	 * @param pos2
	 * @param top
	 * @param workNode
	 */
	public void processText(Text currentNode, final int pos1, final int pos2,
			Node top, Stack workNode) {
		// the text could be tranparent, or 0 length.
		Assert.isTrue(pos1 <= pos2);
		if (pos1 == pos2) {
			return;
		}
		// int left = EditModelQuery.getNodeStartIndex(currentNode);
		// int right = EditModelQuery.getNodeEndIndex(currentNode);
		int location1 = getLocation(currentNode, pos1, false);
		int location2 = getLocation(currentNode, pos2, false);
		if (location1 <= IN_MIDDLE && location2 >= IN_MIDDLE) {
			workNode.push(new WorkNode(currentNode, pos1, pos2));
		}
	}

	/**
	 * @param currentNode
	 * @param pos1
	 * @param pos2
	 * @param top
	 * @param result
	 */
	public void collectNodes(Node currentNode, final int pos1, final int pos2,
			Node top, Stack result) {
		Assert.isTrue(pos1 <= pos2);
		if (pos1 == pos2) {
			return;
		}
		if (EditModelQuery.isText(currentNode)) {
			processText((Text) currentNode, pos1, pos2, top, result);
		} else {
			int location1 = getLocation(currentNode, pos1, false);
			int location2 = getLocation(currentNode, pos2, false);
			if (location1 < IN_MIDDLE && location2 > IN_MIDDLE) {
				// TODO: push the node into result.--
				result.push(new WorkNode(currentNode, pos1, pos2));
			} else if (location1 <= IN_MIDDLE && location2 >= IN_MIDDLE) {
				if (currentNode.hasChildNodes()) {
					Node child = currentNode.getFirstChild();
					Stack myResult = new Stack();
					while (child != null) {
						collectNodes(child, pos1, pos2, top, myResult);
						child = child.getNextSibling();
					}
					if (location1 < IN_MIDDLE && location2 >= IN_MIDDLE
							|| location1 <= IN_MIDDLE && location2 > IN_MIDDLE) {
						WorkNode workNode = new WorkNode(currentNode, pos1,
								pos2);
						while (myResult.size() > 0) {
							WorkNode w = (WorkNode) myResult.remove(0);
							if (w.getNode().getParentNode() == workNode
									.getNode()) {
								w.setParent(workNode);
							}
							result.push(w);
						}
						// TODO: push parent into result.--
						result.push(workNode);
					}
				} else {
					if (!(location1 == IN_MIDDLE && location2 == IN_MIDDLE)) {
						// TODO: push this node into result.
						result.push(new WorkNode(currentNode, pos1, pos2));
					}
				}
			}
		}
	}

	/**
	 * @param currentNode
	 * @param pos
	 * @param isOffset
	 * @return the location
	 */
	public int getLocation(Node currentNode, int pos, boolean isOffset) {
		if (EditModelQuery.getInstance().isSingleRegionNode(currentNode)) {
			// if (EditModelQuery.isText(currentNode))
			{

				int left = EditModelQuery.getNodeStartIndex(currentNode);
				int right = EditModelQuery.getNodeEndIndex(currentNode);
				if (isOffset) {
					pos += left;
				}
				if (pos <= left) {
					return OUT_OF_LEFT;
				} else if (left < pos && pos < right) {
					return IN_MIDDLE;
				} else {
					return OUT_OF_RIGHT;
				}
			}
		}
        int left = EditModelQuery.getNodeStartIndex(currentNode);
        int left1 = EditModelQuery.getNodeStartNameEndIndex(currentNode);
        int right = EditModelQuery.getNodeEndNameStartIndex(currentNode);
        int right1 = EditModelQuery.getNodeEndIndex(currentNode);
        if (isOffset) {
        	pos += left;
        }
        if (pos <= left) {
        	return OUT_OF_LEFT;
        } else if (left < pos && pos < left1) {
        	return LEFT_NAME;
        } else if (left1 <= pos && pos <= right) {
        	return IN_MIDDLE;
        } else if (right < pos && pos < right1) {
        	return RIGHT_NAME;
        } else {
        	return OUT_OF_RIGHT;
        }

	}

    // TODO: dead?
//	private Node cutCurrentNode(int pos[], Node currentNode,
//			IDOMPosition position) {
//		// at right edge
//		int curpos = EditModelQuery.getIndexedRegionLocation(position);
//		if (pos[0] <= curpos) {
//			pos[1] = EditModelQuery.getNodeStartIndex(currentNode);
//			currentNode = deleteNode(currentNode);
//			if (INNER_DEBUG) {
//				_log.info("cut:" + currentNode);
//			}
//			return currentNode;
//		} else if (pos[1] >= curpos) {
//			pos[0] = EditModelQuery.getNodeEndIndex(currentNode);
//			currentNode = deleteNode(currentNode);
//			if (INNER_DEBUG) {
//				_log.info("cut:" + currentNode);
//			}
//			return currentNode;
//		}
//		return null;
//	}

    //TODO: dead?
//	private int getPos(DOMRange range, boolean forStart) {
//		if (forStart) {
//			return EditModelQuery.getIndexedRegionLocation(range
//					.getStartPosition());
//		} else {
//			return EditModelQuery.getIndexedRegionLocation(range
//					.getEndPosition());
//		}
//	}

	/**
	 * @param position
	 * @param forward
	 * @return the edit part for position
	 */
	public EditPart getEditPart(DesignPosition position, boolean forward) {
		if (position instanceof DesignRefPosition) {
			return ((DesignRefPosition) position).getRefPart();
		}
		EditPart container = position.getContainerPart();
		if (container instanceof TextEditPart) {
			return container;
		}
		if (container != null) {
			List children = container.getChildren();
			for (int i = 0, n = children.size(); i < n; i++) {
				if (i == position.getOffset()) {
					int index = (forward) ? i - 1 : i + 1;
					if (index < 0) {
						index = 0;
					}
					if (index >= children.size()) {
						index = children.size() - 1;
					}

					return (EditPart) children.get(index);
				}
			}
		}
		return null;
	}

	/**
	 * @param position
	 * @return the resulting dom position
	 */
	public static IDOMPosition splitNode(IDOMPosition position) {
		if (EditValidateUtil.validPosition(position)) {
			Node container = null;
			// Avoid to split tag at its edge
			if (position.getOffset() > 0) {
				if (position.isText()) {
					container = position.getContainerNode();
					if (position.getOffset() < ((Text) container).getLength()) {
						position = DOMPositionHelper.splitText(position);
					} else {
						// position = new
						// DOMRefPosition(position.getContainerNode(), true);
					}
				} else {
					if (position.getNextSiblingNode() != null) {
						container = position.getContainerNode();
						Node parent = container.getParentNode();

						Document document = EditModelQuery
								.getDocumentNode(container);
						Node newContainer = document.createElement(container
								.getNodeName());
						Node node = position.getPreviousSiblingNode();
						Node refNode = null;
						while (node != null) {
							Node prev = node.getPreviousSibling();
							node = node.getParentNode().removeChild(node);

							newContainer.insertBefore(node, refNode);
							refNode = node;
							node = prev;
						}
						parent.insertBefore(newContainer, container);
						// set the newContainer node align attribute to the
						// original align attribue
						// copy nodes under container node to container node's
						// parent node
						if (container.getNodeName().equalsIgnoreCase(
								IHTMLConstants.TAG_P)) {
							Element pNode = (Element) container;
							String align = pNode
									.getAttribute(IHTMLConstants.ATTR_ALIGN);
							if (align != null && !"".equalsIgnoreCase(align)) { //$NON-NLS-1$
								((Element) newContainer).setAttribute(
										IHTMLConstants.ATTR_ALIGN, align);
							}
							NodeList nodeList = pNode.getChildNodes();
							for (int i = 0, size = nodeList.getLength(); i < size; i++) {
								Node tempNode = nodeList.item(i);
								parent.insertBefore(tempNode, container);
							}
						}
						return new DOMRefPosition(newContainer, true);
					}
//                    position = new
//                    DOMRefPosition(position.getContainerNode(), true);
				}
			} else {
				// container = position.getContainerNode();
				// position = new DOMRefPosition(container, false);
			}
		}
		return position;
	}

	/**
	 * @param position 
	 * @return the position of this 'position' in relative to it's container.
	 */
	public static int getLocation(IDOMPosition position) {
		if (position.getOffset() == 0) {
			return -1;
		}
        if (position.isText()) {
        	if (position.getOffset() == ((Text) position.getContainerNode())
        			.getLength()) {
        		return 1;
        	}
            return 0;
        }
        if (position.getOffset() == position.getContainerNode()
        		.getChildNodes().getLength()) {
        	return 1;
        }
        return 0;
	}
}
