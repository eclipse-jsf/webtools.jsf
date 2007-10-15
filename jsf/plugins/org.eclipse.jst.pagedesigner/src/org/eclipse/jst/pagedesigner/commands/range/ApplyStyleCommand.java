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
package org.eclipse.jst.pagedesigner.commands.range;

import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.commands.CommandResources;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.DOMRefPosition;
import org.eclipse.jst.pagedesigner.dom.DOMUtil;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * @author mengbo
 */
public class ApplyStyleCommand extends RangeModeCommand {
	private String _tag;

	private String _cssProperty;

	private String _cssPropertyValue;

	private final Element _applyingNode;

	/**
	 * @param viewer
	 * @param tag 
	 * @param property 
	 * @param value 
	 */
	public ApplyStyleCommand(IHTMLGraphicalViewer viewer, String tag,
			String property, String value) {
		super(
				CommandResources
						.getString("ApplyStyleCommand.Label.ApplyStyle"), viewer); //$NON-NLS-1$
		this._tag = tag;
		this._cssProperty = property;
		this._cssPropertyValue = value;
		this._applyingNode = null;
	}

	/**
	 * @param viewer
	 * @param node
	 * @param property
	 * @param value
	 */
	public ApplyStyleCommand(IHTMLGraphicalViewer viewer, Element node,
			String property, String value) {
		super(
				CommandResources
						.getString("ApplyStyleCommand.Label.ApplyStyle"), viewer); //$NON-NLS-1$
		this._applyingNode = node;
	}

	/**
	 * @return the applying node (may be null)
	 */
	protected final Element getApplyingNode() {
        return _applyingNode;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected DOMRange doRangeExecute(DOMRange range) {
		if (range == null || range.isEmpty()) {
			return null;
		}

		boolean ordered = range.isOrdered();
		IDOMPosition start = ordered ? range.getStartPosition() : range
				.getEndPosition();
		IDOMPosition end = ordered ? range.getEndPosition() : range
				.getStartPosition();

		Node startContainer = start.getContainerNode();
		Node endContainer = end.getContainerNode();

		Node common = DOMUtil.findCommonAncester(start.getContainerNode(), end
				.getContainerNode());
		if (common == null) {
			// should not happen.
			return null;
		}

		if (common instanceof Text) {
			// under the same Text scope
			range = doTextNodeStyleApply((Text) common, start.getOffset(), end
					.getOffset());

			return range;
		}
        
        if (startContainer instanceof Text) {
        	// if the start offset is 0,then skip split the Text
        	if (start.getOffset() > 0) {
        		startContainer = ((Text) startContainer).splitText(start
        				.getOffset());
        		start = new DOMRefPosition(startContainer, false);
        	}
        } else {
        	startContainer = start.getNextSiblingNode();
        }
        if (endContainer instanceof Text) {
        	if (end.getOffset() > 0) {
        		endContainer = ((Text) endContainer).splitText(end
        				.getOffset());
        		endContainer = endContainer.getPreviousSibling();
        	} else {
        		endContainer = endContainer.getPreviousSibling();
        	}
        } else {
        	endContainer = end.getPreviousSiblingNode();
        }

        for (Node node = startContainer; node != endContainer; node = EditModelQuery
        		.getInstance().getNextLeafNeighbor(node)) {
        	if (EditModelQuery.hasAncestor(node, getTag(), true)) {
        		continue;
        	}
        	Element newnode = createStyleElement();
        	node.getParentNode().insertBefore(newnode, node);
        	newnode.appendChild(node);
        }
        if (!EditModelQuery.hasAncestor(endContainer, getTag(), true)) {
        	Element newnode = createStyleElement();
        	endContainer.getParentNode()
        			.insertBefore(newnode, endContainer);
        	newnode.appendChild(endContainer);
        }

		// merge the style tags

		for (Node node = startContainer; node != endContainer; node = EditModelQuery
				.getInstance().getNextLeafNeighbor(node)) {
			Node stylenode = node;
			while (stylenode != null
					&& !stylenode.getNodeName().equalsIgnoreCase(getTag())) {
				stylenode = stylenode.getParentNode();
			}
			if (stylenode == null) {
				continue;
			}
			if (stylenode.getNextSibling() != null
					&& stylenode.getNextSibling().getNodeName()
							.equalsIgnoreCase(getTag())) {
				Node sibling = stylenode.getNextSibling();
				while (sibling.getFirstChild() != null) {
					stylenode.appendChild(sibling.getFirstChild());
				}
				stylenode.getParentNode().removeChild(sibling);
				node = startContainer;
			}
		}

		return new DOMRange(start, end);

		/*
		 * boolean ordered = range.isOrdered(); IDOMPosition start = ordered ?
		 * range.getStartPosition() : range.getEndPosition(); IDOMPosition end =
		 * ordered ? range.getEndPosition() : range.getStartPosition();
		 * 
		 * Node common = DOMUtil.findCommonAncester(start.getContainerNode(),
		 * end.getContainerNode()); if (common == null) { // should not happen.
		 * return null; }
		 * 
		 * DOMRange result = null; if (common instanceof Text) { result =
		 * doTextNodeStyleApply((Text) common, start.getOffset(),
		 * end.getOffset()); } else { IDOMPosition startPosition = start;
		 * IDOMPosition endPosition = end; Node ancester = common; DOMRange[]
		 * leftRange = new DOMRange[1]; DOMRange[] rightRange = new DOMRange[1];
		 * 
		 * startPosition = partialApply(startPosition, ancester, true,
		 * leftRange); endPosition = partialApply(endPosition, ancester, false,
		 * rightRange); DOMRange middle = middleApply(ancester, startPosition,
		 * endPosition);
		 * 
		 * IDOMPosition startref = null; if (leftRange[0] != null &&
		 * leftRange[0].getStartPosition() != null) { startref =
		 * leftRange[0].getStartPosition(); } else if (middle != null &&
		 * middle.getStartPosition() != null) { startref =
		 * middle.getStartPosition(); } else if (rightRange[0] != null &&
		 * rightRange[0].getStartPosition() != null) { startref =
		 * rightRange[0].getStartPosition(); }
		 * 
		 * IDOMPosition endref = null; if (rightRange[0] != null &&
		 * rightRange[0].getEndPosition() != null) { endref =
		 * rightRange[0].getEndPosition(); } else if (middle != null &&
		 * middle.getEndPosition() != null) { endref = middle.getEndPosition(); }
		 * else if (leftRange[0] != null && leftRange[0].getEndPosition() !=
		 * null) { endref = leftRange[0].getEndPosition(); }
		 * 
		 * if (startref == null) { result = null; } else { startref = new
		 * DOMPosition(EditModelQuery.getInstance().getNextLeafNeighbor(startref.getContainerNode()),
		 * 0); System.out.println(startref.toString()); endref = new
		 * DOMPosition(endref.getContainerNode(), 0); result = new
		 * DOMRange(startref, endref); } }
		 * 
		 * if (result == null) { return null; }
		 * 
		 * if (ordered) { return result; } else { return new
		 * DOMRange(result.getEndPosition(), result.getStartPosition()); }
		 */
	}

    // TODO: unused code.  Dead?
//	private DOMRange middleApply(Node ancester, IDOMPosition startPosition,
//			IDOMPosition endPosition) {
//		startPosition = skip(startPosition, true);
//		if (startPosition.getNextSiblingNode() == null
//				|| startPosition.getOffset() >= endPosition.getOffset()) {
//			return null;
//		} else {
//			List needMove = new ArrayList();
//			Node startNext = startPosition.getNextSiblingNode();
//			Node endNext = endPosition.getNextSiblingNode();
//			while (startNext != null && startNext != endNext) {
//				needMove.add(startNext);
//				startNext = startNext.getNextSibling();
//			}
//			Element newEle = createStyleElement();
//			ancester.insertBefore(newEle, startPosition.getNextSiblingNode());
//			for (int i = 0, n = needMove.size(); i < n; i++) {
//				newEle.appendChild((Node) needMove.get(i));
//			}
//			return new DOMRange(new DOMRefPosition(newEle, false),
//					new DOMRefPosition(newEle, true));
//		}
//	}

    // TODO: unused code.  Dead?
//	private IDOMPosition partialApply(IDOMPosition position, Node ancester,
//			boolean forward, DOMRange[] result) {
//		IDOMPosition startRef = null, endRef = null;
//
//		while (position != null && position.getContainerNode() != ancester) {
//			Node container = position.getContainerNode();
//			if (container instanceof Text) {
//				// splitText will move the position up one level
//				position = splitText(position);
//			} else {
//				// skip those nodes that can't have the style applied.
//				position = skip(position, forward);
//				Node sibling = position.getSibling(forward);
//				if (sibling != null) {
//					List needMove = new ArrayList();
//					while (sibling != null) {
//						needMove.add(sibling);
//						sibling = forward ? sibling.getNextSibling() : sibling
//								.getPreviousSibling();
//					}
//
//					// ok, there is nodes that need the style
//					Element newEle = createStyleElement();
//					container.insertBefore(newEle, position
//							.getNextSiblingNode());
//					for (int i = 0, size = needMove.size(); i < size; i++) {
//						newEle.appendChild((Node) needMove.get(i));
//					}
//					if (startRef == null) {
//						startRef = new DOMRefPosition(newEle, !forward);
//					}
//					endRef = new DOMRefPosition(newEle, forward);
//				}
//				// move the position up one level
//				position = new DOMRefPosition(container, forward);
//			}
//		}
//		if (startRef == null) {
//			result[0] = null;
//		} else {
//			result[0] = forward ? new DOMRange(startRef, endRef)
//					: new DOMRange(endRef, startRef);
//		}
//		return position;
//	}

	/**
	 * @param position
	 * @return
	 */
    // TODO: dead?
//	private IDOMPosition splitText(IDOMPosition position) {
//		Text text = (Text) position.getContainerNode();
//		int offset = position.getOffset();
//		if (offset <= 0) {
//			return new DOMRefPosition(text, false);
//		} else if (offset >= text.getData().length()) {
//			return new DOMRefPosition(text, true);
//		} else {
//			text.splitText(offset);
//			return new DOMRefPosition(text, true);
//		}
//	}

	/**
	 * @param start
	 * @param end
	 * @param common
	 */
	private DOMRange doTextNodeStyleApply(Text textNode, int startOffset,
			int endOffset) {
		String data = textNode.getData();
		String before = data.substring(0, startOffset);
		String middle = data.substring(startOffset, endOffset);
		String tail = data.substring(endOffset);

		Text middleText = getModel().getDocument().createTextNode(middle);

		// case 1: normal one
		if (!isEmptyString(before) && !isEmptyString(tail)) {
			Node parent = textNode.getParentNode();
			parent.insertBefore(
					getModel().getDocument().createTextNode(before), textNode);
			Element bnode = createStyleElement();
			bnode.appendChild(middleText);
			parent.insertBefore(bnode, textNode);
			textNode.setNodeValue(tail);
		}

		if (isEmptyString(before) && !isEmptyString(tail)) {
			Node sibling = textNode.getPreviousSibling();
			if (sibling != null
					&& sibling.getNodeName().equalsIgnoreCase(getTag())) {
				sibling.appendChild(middleText);
			} else {
				Node parent = textNode.getParentNode();
				parent.insertBefore(getModel().getDocument().createTextNode(
						before), textNode);
				Element bnode = createStyleElement();
				bnode.appendChild(middleText);
				parent.insertBefore(bnode, textNode);
			}
			textNode.setNodeValue(tail);
		}

		if (!isEmptyString(before) && isEmptyString(tail)) {
			Node sibling = textNode.getNextSibling();
			textNode.setNodeValue(before);
			if (sibling != null
					&& sibling.getNodeName().equalsIgnoreCase(getTag())) {
				sibling.insertBefore(middleText, sibling.getFirstChild());
			} else {
				Element bnode = createStyleElement();
				bnode.appendChild(middleText);
				textNode.getParentNode().insertBefore(bnode, sibling);
			}
		}

		if (isEmptyString(before) && isEmptyString(tail)) {

			Node previousSibling = textNode.getPreviousSibling();
			Node nextSibling = textNode.getNextSibling();
			//
			if (getTag().equalsIgnoreCase(IHTMLConstants.TAG_P)) {
				Element bnode = createStyleElement();
				bnode.appendChild(middleText);
				textNode.getParentNode().insertBefore(bnode, textNode);
				textNode.getParentNode().removeChild(textNode);
			}
			//
			else {
				if (previousSibling != null
						&& previousSibling.getNodeName().equalsIgnoreCase(
								getTag()) && nextSibling != null
						&& nextSibling.getNodeName().equalsIgnoreCase(getTag())) {
					previousSibling.appendChild(middleText);
					while (nextSibling.getFirstChild() != null) {
						previousSibling
								.appendChild(nextSibling.getFirstChild());
					}
					nextSibling.getParentNode().removeChild(nextSibling);
				} else if (previousSibling != null
						&& previousSibling.getNodeName().equalsIgnoreCase(
								getTag())) {
					previousSibling.appendChild(middleText);
				} else if (nextSibling != null
						&& nextSibling.getNodeName().equalsIgnoreCase(getTag())) {
					nextSibling.insertBefore(middleText, nextSibling
							.getFirstChild());
				} else {
					Element bnode = createStyleElement();
					bnode.appendChild(middleText);
					textNode.getParentNode().insertBefore(bnode, textNode);
				}
				textNode.getParentNode().removeChild(textNode);
			}
		}

		return new DOMRange(new DOMRefPosition(middleText, false),
				new DOMRefPosition(middleText, true));
	}

	private boolean isEmptyString(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @return a style element (cached on create)
	 */
	protected Element createStyleElement() {
		if (_applyingNode != null) {
			return _applyingNode;
		}
        Element element = getModel().getDocument().createElement(getTag());
        if (_cssProperty != null && _cssPropertyValue != null) {
        	element.setAttribute(_cssProperty, _cssPropertyValue);
        }
        return element;
	}

	/**
	 * @param position
	 * @param b
	 * @return
	 */
    // TODO: dead?
//	private IDOMPosition skip(IDOMPosition position, boolean forward) {
//		Node node = position.getSibling(forward);
//
//		if (node == null) {
//			return position;
//		}
//		boolean canSkip = false;
//		if (node instanceof Text) {
//			canSkip = ((IDOMText) node).isElementContentWhitespace();
//		} else if (node instanceof Element) {
//			if (getTag().equalsIgnoreCase(((Element) node).getTagName())) {
//				canSkip = true;
//			} else {
//				canSkip = false;
//			}
//		} else {
//			canSkip = true;
//		}
//		if (canSkip) {
//			return new DOMRefPosition(node, forward);
//		} else {
//			return position;
//		}
//	}

	/**
	 * @return Returns the _cssProperty.
	 */
	public final String getCssProperty() {
		return _cssProperty;
	}

	/**
	 * @param property
	 *            The _cssProperty to set.
	 */
	public final void setCssProperty(String property) {
		_cssProperty = property;
	}

	/**
	 * @return Returns the _cssPropertyValue.
	 */
	public final String getCssPropertyValue() {
		return _cssPropertyValue;
	}

	/**
	 * @param propertyValue
	 *            The _cssPropertyValue to set.
	 */
	public final void setCssPropertyValue(String propertyValue) {
		_cssPropertyValue = propertyValue;
	}

	/**
	 * @return Returns the _tag.
	 */
	public final String getTag() {
		if (_tag != null) {
			return _tag;
		}
        return _applyingNode.getNodeName();
	}

	/**
	 * @param _tag
	 *            The _tag to set.
	 */
	public final void setTag(String _tag) {
		this._tag = _tag;
	}
}
