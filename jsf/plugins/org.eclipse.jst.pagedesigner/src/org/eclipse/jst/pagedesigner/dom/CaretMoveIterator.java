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

import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.utils.HTMLUtil;
import org.eclipse.jst.pagedesigner.validation.caret.IMovementMediator;
import org.eclipse.jst.pagedesigner.validation.caret.Target;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.traversal.NodeIterator;

/**
 * @author mengbo
 */
public class CaretMoveIterator {
	private final static Logger _log = PDPlugin
			.getLogger(CaretMoveIterator.class);

	private final boolean INNER_DEBUG = false;

	private NodeIterator _nodeIterator;

	private IMovementMediator _validator;

	private IDOMPosition _currentPosition;

	private boolean _forward;

	/**
	 * @param nodeIterator 
	 * @param validator 
	 * @param position 
	 * @param forward 
	 */
	public CaretMoveIterator(NodeIterator nodeIterator,
			IMovementMediator validator, IDOMPosition position, boolean forward) {
		super();
		_nodeIterator = nodeIterator;
		_validator = validator;
		_currentPosition = position;
		_forward = forward;
	}

	/**
	 * @return the node iterator
	 */
	public NodeIterator getNodeIterator() {
		return _nodeIterator;
	}

	/**
	 * @return Returns the _currentPosition.
	 */
	public IDOMPosition getCurrentPosition() {
		return _currentPosition;
	}

	/**
	 * @param position
	 *            The _currentPosition to set.
	 */
	public void setCurrentPosition(IDOMPosition position) {
		_currentPosition = position;
	}

	// assume the currentPosition is invalid
	private IDOMPosition moveOut(Node container) {
		IDOMPosition result = new DOMRefPosition(container, _forward);
		String name = container.getNodeName();
		if (name != null
				&& EditModelQuery.HTML_STYLE_NODES.contains(name.toLowerCase())) {
			result = moveToNextPosition(result, _validator);
		}
		return result;
	}

	/**
	 * @param node
	 * @return the dom position
	 */
	public IDOMPosition moveIn(Node node) {
		IDOMPosition result = null;
		if (INNER_DEBUG) {
			_log.info("- Move into: " + node.getLocalName()); //$NON-NLS-1$
		}
		if (_validator.isEditable(new Target(node))) {
			int index;
			// Transparent text is not editable, so this is not transparent.
			if (EditModelQuery.isText(node)) {
				index = (_forward) ? 0 : ((Text) node).getData().length();
				result = new DOMPosition(node, index);
				// move ahead one pos.
				IDOMPosition pos = getNextTextPosition(result);
				if (pos != null) {
					result = pos;
				}
			} else {
				if (node.hasChildNodes()) {
					index = _forward ? 0 : node.getChildNodes().getLength();
					result = new DOMPosition(node, index); // DOMRefPosition(next,
					// !_forward);
				} else {
					result = new DOMPosition(node, 0);
				}
			}
		} else {
			if (node.hasChildNodes()) {
				Node child = _forward ? node.getFirstChild() : node
						.getLastChild();
				result = new DOMRefPosition(child, _forward);
				while (child != null) {
					if (_validator.allowsMoveIn(new Target(child))) {
						result = moveIn(child);
						break;
					}
					child = _forward ? child.getNextSibling() : child
							.getPreviousSibling();
				}
			} else {
				// Should be impposible to reach here.
				result = new DOMPosition(node, 0);
			}
		}
		return result;
	}

	private IDOMPosition getNextTextPosition(IDOMPosition position) {
		IDOMPosition result = null;
		String value = position.getContainerNode().getNodeValue();
		int i = position.getOffset();
		if (_forward) {
			if (i < value.length()) {
				if (HTMLUtil.isHTMLWhitespace(value.charAt(i))) {
					while (i < value.length()
							&& HTMLUtil.isHTMLWhitespace(value.charAt(i))) {
						i++;
					}
					result = new DOMPosition(position.getContainerNode(), i);
				} else if (i < value.length()) {
					result = new DOMPosition(position.getContainerNode(), i + 1);
				}
			}
		} else {
			if (i > 0) {
				if (HTMLUtil.isHTMLWhitespace(value.charAt(i - 1))) {
					while (i > 0
							&& HTMLUtil.isHTMLWhitespace(value.charAt(i - 1))) {
						i--;
					}
					result = new DOMPosition(position.getContainerNode(), i);
				} else if (i > 0) {
					result = new DOMPosition(position.getContainerNode(), i - 1);
				}
			}
		}
		return result;
	}

	/**
	 * Assume the original position are valid.
	 * 
	 * @param position
	 * @param validator
	 * @param _forward
	 * @param referenceImediatly
	 * @return
	 */
	private IDOMPosition moveToNextPosition(IDOMPosition position,
			IMovementMediator validator) {
		IDOMPosition currentPosition = null;
		if (validator.isValidPosition(position) && position.isText()) {
			currentPosition = getNextTextPosition(position);
		}
		if (currentPosition == null) {
			Node nextNode = EditModelQuery.getInstance().getSibling(position,
					_forward);
			while (EditModelQuery.isText(nextNode)
					&& ((Text) nextNode).getData().length() == 0) {
				nextNode = EditModelQuery.getInstance().getSibling(nextNode,
						_forward);
			}
			if (nextNode != null) {
				// move in?
				if (validator.allowsMoveIn(new Target(nextNode))) {
					currentPosition = moveIn(nextNode);
					// Stop when it is in table. For others we continue search
					// for text.
					if (!canStopHere(nextNode) && //
							EditModelQuery.getInstance().getSibling(
									currentPosition, _forward) != null) {
						currentPosition = moveToNextPosition(currentPosition,
								validator);
					}
				}
				// not allowed to move in. e.g. it's empty string.
				else {
					currentPosition = new DOMRefPosition(nextNode, _forward);// skip(position);
				}
			} else {
				if (validator.allowsMoveOut(new Target(
						getNaviContainer(position)))) {
					currentPosition = moveOut(getNaviContainer(position));
				}
			}
		}
		currentPosition = EditHelper.ensureDOMPosition(currentPosition);
		if (currentPosition != null
				&& !validator.isValidPosition(currentPosition)) {
			currentPosition = moveToNextPosition(currentPosition, validator);
		}
		return currentPosition;
	}

	/**
	 * When the tag starts from new line, or in table, then caret can be put at
	 * 0 offset.
	 * 
	 * @param node
	 * @return
	 */
	private boolean canStopHere(Node node) {
		boolean result = false;
		if (EditModelQuery.isText(node)) {
			result = true;
		} else if (node != null && node.getNodeName() != null) {
			result |= node.getNodeName().equals(IHTMLConstants.TAG_TD);
			result |= EditModelQuery.isBlockNode(node);
		}
		return result;
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
	public IDOMPosition moveToNextEditPosition(IDOMPosition currentPosition,
			boolean forward, IMovementMediator validator) {
		IDOMPosition result = null;
		if ((currentPosition = moveToNextPosition(currentPosition, validator)) != null) {
			result = currentPosition;
		} else {
			result = _currentPosition;
		}
		return result;
	}

	private Node getNaviContainer(IDOMPosition position) {
		if (position.isText()) {
			return position.getContainerNode().getParentNode();
		}
        return position.getContainerNode();
	}
}
