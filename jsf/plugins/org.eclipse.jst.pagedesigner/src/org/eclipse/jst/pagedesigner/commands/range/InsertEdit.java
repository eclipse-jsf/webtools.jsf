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

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.dom.DOMPosition;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.DOMRefPosition;
import org.eclipse.jst.pagedesigner.dom.DOMUtil;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.jsp.core.IJSPCoreConstants;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * InsertEdit will perform action at a single location, the data souce could be
 * clipboard or keyboard.
 * 
 * @author mengbo
 */
public class InsertEdit extends DeleteEdit {
	private IInputSourceProvider _data;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.DesignEdit#operate()
	 */
	public boolean operate() {
		DOMRange range = getRange();
		if (!EditModelQuery.isSame(range.getStartPosition(), range
				.getEndPosition())) {
			deleteRange();
			range = new DOMRange(getOperationPosition(), getOperationPosition());
			setRange(range);
		}
		if (insertNodes()) {
			return true;
		} else if (insertString()) {
			return true;
		} else {
			return insertChar();
		}
	}

	/**
	 * @param range
	 * @param viewer
	 * @param data 
	 */
	public InsertEdit(DOMRange range, GraphicalViewer viewer,
			IInputSourceProvider data) {
		super(range, viewer);
		_data = data;
	}

	/**
	 * @param position
	 * @param viewer
	 * @param data
	 */
	public InsertEdit(IDOMPosition position, GraphicalViewer viewer,
			IInputSourceProvider data) {
		super(new DOMRange(position, position), viewer);
		setOperationPosition(position);
		_data = data;
	}

	/**
	 * @return ??
	 */
	public boolean insertChar() {
		if (_data.getStringData() == null) {
			return false;
		}
		IDOMPosition position = getOperationPosition();
		if (position.isText()) {
			Text text = EditModelQuery.getInstance().getText(position);
			text.insertData(getOperationPosition().getOffset(), _data
					.getCharacterData().toString());
			setOperationPosition(new DOMPosition(text, position.getOffset() + 1));
		} else {
			Node refNode = position.getNextSiblingNode();
			Text text = getDocument().createTextNode(_data.getCharacterData()
					.toString());
			position.getContainerNode().insertBefore(text, refNode);
			setOperationPosition(new DOMPosition(text, text.getLength()));
		}
		return true;
	}

	/**
	 * @return ????
	 */
	public boolean insertString() {
		String content = _data.getStringData();
		if (content != null) {
			IDOMPosition position = getOperationPosition();
			if (position.isText()) {
				Text text = EditModelQuery.getInstance().getText(position);
				text.insertData(getOperationPosition().getOffset(), content);
				setOperationPosition(new DOMPosition(text, position.getOffset()
						+ content.length()));
			} else {
				Node refNode = position.getNextSiblingNode();
				Text text = getDocument().createTextNode(content);
				position.getContainerNode().insertBefore(text, refNode);
				setOperationPosition(new DOMPosition(text, text.getLength()));
			}
			return true;
		}
		return false;
	}

	private boolean insertNodes() {
		Node[] nodes = _data.getNodes();
		if (nodes == null) {
			return false;
		}
		IDOMPosition position = getOperationPosition();
		if (position == null) {
			return false;
		}
		Node refNode = null;
		if (position.isText()) {
			position = DOMPositionHelper.splitText(position);
		}
		refNode = position.getNextSiblingNode();
		Node parent = position.getContainerNode();
		Node node = null;
		for (int i = 0; i < nodes.length; i++) {
			node = DOMUtil.cloneNodeDeep(getDocument(), nodes[i]);
			String prefix = node.getPrefix();
			String name = node.getLocalName();
			if (name != null
					&& ITLDConstants.URI_JSP.equals(prefix)
					&& (node.getLocalName().startsWith(
							IJSPCoreConstants.TAG_LEADING_DIRECTIVE)
							|| IJSPCoreConstants.TAG_DECLARATION.equals(name)
							|| IJSPCoreConstants.TAG_EXPRESSION.equals(name) || IJSPCoreConstants.TAG_SCRIPTLET
							.equals(name))) {
				// it is a jsp tag
				((IDOMElement) node).setJSPTag(true);
			}
			node = parent.insertBefore(node, refNode);
		}

		if (node != null) {
			setOperationPosition(new DOMRefPosition(node, true));
		} else if (refNode != null) {
			setOperationPosition(new DOMRefPosition(refNode, false));
		} else {
			setOperationPosition(new DOMRefPosition(parent.getLastChild(), true));
		}
		return true;
	}

    // TODO: dead?
//	private boolean splitNode() {
//		if ((getViewer()).getSelection() instanceof DesignRange
//				&& _data.getCharacterData() != null
//				&& _data.getCharacterData().charValue() == '\r') {
//			DesignRange range = (DesignRange) (getViewer()).getSelection();
//			Node node = range.getStartPosition().getContainerNode();
//			if (EditModelQuery.isText(node)) {
//				node = node.getParentNode();
//			}
//			if (EditModelQuery.isListItem(node)) {
//				IDOMPosition position = DOMPositionHelper.toDOMPosition(range
//						.getStartPosition());
//				// split text and it's parent.
//				position = EditHelper.splitNode(position);
//				position = EditHelper.splitNode(position);
//				position = EditHelper.moveInto(position.getNextSiblingNode(),
//						new InlineEditingNavigationMediator(new ActionData(
//								ActionData.INLINE_EDIT, null)), true);
//				setOperationPosition(position);
//				return true;
//			}
//		}
//		return false;
//	}
}
