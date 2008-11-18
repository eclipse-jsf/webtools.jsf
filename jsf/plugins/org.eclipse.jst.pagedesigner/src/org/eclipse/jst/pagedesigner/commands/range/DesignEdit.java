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

import java.util.Stack;
import java.util.Vector;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.css2.CSSUtil;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.EditHelper;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.EditValidateUtil;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * @author mengbo
 */
public abstract class DesignEdit {

    private Stack _selections;

	private DOMRange _range;

	private GraphicalViewer _viewer;

	private IDOMPosition _operationPosition;

	private final Document _document;

	private Stack _processedResult;

	/**
	 * @param range
	 * @param viewer
	 */
	public DesignEdit(DOMRange range, GraphicalViewer viewer) {
		setRange(range);
		_viewer = viewer;
		_operationPosition = getRange().getStartPosition();
		_document = ((IDOMNode) _operationPosition.getContainerNode())
				.getModel().getDocument();
	}

	
	/**
	 * @return the target document
	 */
	protected final Document getDocument() {
        return _document;
    }

    /**
     * @return the result
     */
    protected abstract boolean operate();

	/**
	 * @param node
	 * @return the text
	 */
	protected abstract Text processText(WorkNode node);

	/**
	 * @param node
	 * @return the node 
	 */
	protected abstract Node processNode(WorkNode node);

	/**
	 * @param node
	 * @return the node
	 */
	protected abstract Node processContainer(WorkNode node);

	/**
	 * @return the dom range
	 */ 
	public DOMRange getRange() {
		return _range;
	}

	void setRange(DOMRange range) {
		range = EditHelper.normal(range);
		IDOMPosition start = EditHelper.ensureDOMPosition(range
				.getStartPosition());
		IDOMPosition end = EditHelper.ensureDOMPosition(range.getEndPosition());
		_range = new DOMRange(start, end);
		EditValidateUtil.validRange(range);
	}

	/**
	 * @return the clipboard
	 */
	protected Clipboard getClipboard() {
		return new Clipboard(_viewer.getControl().getDisplay());
	}

	/**
	 * @return the position
	 */
	public IDOMPosition getOperationPosition() {
		// try
		// {
		// Assert.isTrue(_operationPosition != null &&
		// _operationPosition.getContainerNode() != null &&
		// _operationPosition.getOffset() > -1);
		// if (_operationPosition.isText())
		// {
		// int length = ((Text)
		// _operationPosition.getContainerNode()).getLength();
		// Assert.isTrue(_operationPosition.getOffset() >= 0 &&
		// _operationPosition.getOffset() <= length);
		// }
		// }
		// catch (Exception e)
		// {
		// // "Error", "Error in operation location move"
		// PDPlugin.getAlerts().confirm("Alert.DesignEdit.opLocationValidTitle",
		// "Alert.DesignEdit.opLocationValidMessage"); //$NON-NLS-1$
		// //$NON-NLS-2$
		// }

		return _operationPosition;
	}

	/**
	 * @param position
	 */
	protected void setOperationPosition(IDOMPosition position) {
		if (!EditValidateUtil.validPosition(position)) {
			return;
		}
		position = EditHelper.ensureDOMPosition(position);
		_operationPosition = position;
	}

	/**
	 * @return the result of performing the edit
	 */
	public boolean perform() {
		boolean result = false;

		result = operate();
		return result;
	}

	/**
	 * @return Returns the _viewer.
	 */
	public GraphicalViewer getViewer() {
		return _viewer;
	}

	private Stack collectNodes() {
		Node node;
		Stack result = new Stack();
		IDOMPosition start = getRange().getStartPosition(), end = getRange()
				.getEndPosition();
		int pos[] = new int[] { EditModelQuery.getIndexedRegionLocation(start),
				EditModelQuery.getIndexedRegionLocation(end), };
		if (!EditModelQuery.isSame(start, end)) {
			Node ancestor = EditModelQuery.getInstance().getCommonAncestor(
					start, end);
			WorkNode rootWorkNode = new WorkNode(ancestor, pos[0], pos[1]);
			rootWorkNode.setRoot(true);
			result.push(rootWorkNode);
			try {
				// Loop all the children of the ancestor, and and the result
				// will be collected
				if (EditModelQuery.isText(ancestor)) {
					Stack temp = new Stack();
					EditHelper.getInstance().collectNodes(ancestor, pos[0],
							pos[1], ancestor, temp);
					WorkNode wNode = (WorkNode) temp.remove(0);
					wNode.setParent(rootWorkNode);
					result.push(wNode);
				} else {
					node = ancestor.getFirstChild();
					Stack temp = new Stack();
					while (node != null) {
						EditHelper.getInstance().collectNodes(node, pos[0],
								pos[1], ancestor, temp);
						while (temp.size() > 0) {
							WorkNode wNode = (WorkNode) temp.remove(0);
							if (wNode.getNode().getParentNode() == ancestor) {
								wNode.setParent(rootWorkNode);
							}
							result.push(wNode);
						}
						node = node.getNextSibling();
					}
				}
			} catch (Exception e) {
				result.clear();
			}
		}
		return result;
	}

	/**
	 * @return Returns the result.
	 */
	public Stack getSelections() {
		if (_selections == null) {
			_selections = collectNodes();
		}
		return _selections;
	}

	/**
	 * @return the result stack
	 */
	public Stack getProcessedResult() {
		if (_processedResult == null) {
			_processedResult = new Stack();
			WorkNode rootNode = getRootWorkNode();
			if (rootNode != null) {
				processNodes(rootNode, _processedResult);
			}
		}
		return _processedResult;
	}

	/**
	 * @return the root work node
	 */
	protected final WorkNode getRootWorkNode() {
		WorkNode result = null;
		if (getSelections().size() > 0) {
			WorkNode node = (WorkNode) getSelections().get(0);
			while (node.getParent() != null) {
				node = node.getParent();
			}
			result = node;
			Assert.isTrue(node.isRoot());
		}
		return result;
	}

	/**
	 * @param node
	 * @param result
	 * @return true if node
	 */
	private final boolean processText(WorkNode node, Stack result) {
		boolean done = false;
		if (EditModelQuery.isText(node.getNode())) {
			Node text = processText(node);
			if (text != null) {
				result.add(text);
			}
			getSelections().remove(node);
			done = true;
		}
		return done;
	}

	/**
	 * @param node
	 * @param result
	 */
	private final void processContainer(WorkNode node, Stack result) {
		processContainer(node);
		getSelections().remove(node);
	}

	/**
	 * @param node
	 * @param result
	 * @return true if done
	 */
	private final boolean processChildren(WorkNode node, Stack result) {
		boolean done = false;
		if (getFirstSelectedChild(node) != null) {
			Stack myResult = new Stack();
			{
				WorkNode child = null;
				while ((child = getFirstSelectedChild(node)) != null) {
					{
						processNodes(child, myResult);
					}
				}
				Node newParent = processContainer(node);
				newParent = toBeParent(newParent, myResult);
				result.push(newParent);
			}
			getSelections().remove(node);
			done = true;
		}
		return done;
	}

	/**
	 * @param node
	 * @param result
	 * @return true if done
	 */
	private final boolean processChildren1(WorkNode node, Stack result) {
		boolean done = false;
		if (node.getNode().hasChildNodes()) {
			Stack myResult = new Stack();
			{
				Node childNode = node.getNode().getFirstChild();
				Node next = null;
				while (childNode != null) {
					next = childNode.getNextSibling();
					int x1 = EditModelQuery.getNodeStartIndex(childNode) - 1;
					int x2 = EditModelQuery.getNodeEndIndex(childNode) + 1;
					processNodes(new WorkNode(childNode, x1, x2), myResult);
					childNode = next;
				}
				Node newParent = processContainer(node);
				newParent = toBeParent(newParent, myResult);
				result.push(newParent);
			}
			getSelections().remove(node);
			done = true;
		}
		return done;
	}

	/**
	 * Process the nodes that are selected, the result is a collection of nodes
	 * that either are clones or the nodes cuted.
	 * 
	 * @param node
	 * @param result
	 */
	protected final void processNodes(WorkNode node, Stack result) {
		WorkNode child = null;
		if (node.isRoot()) {
			while ((child = getFirstSelectedChild(node)) != null) {
				processNodes(child, result);
			}
		} else {
			if (node.isWholeSelected()
					|| //
					(!EditModelQuery.isText(node.getNode()) && EditModelQuery
							.getInstance().isSingleRegionNode(node.getNode()))
					|| //
					EditModelQuery.isWidget(node.getNode())) {
				Node temp = processNode(node);
				if (temp != null) {
					result.push(temp);
					getSelections().remove(node);
				} else {
					if (!processText(node, result)) {
						if (!processChildren1(node, result)) {
							processContainer(node, result);
						}
					}
				}
			} else {
				if (!processText(node, result)) {
					if (!processChildren(node, result)) {
						processContainer(node, result);
					}
				}
			}
		}
	}

	/**
	 * @param result
	 */
	protected void setClipboard(Stack result) {
		Node[] nodes = (Node[]) result.toArray(new Node[result.size()]);
		StringBuffer sb = new StringBuffer();
		for (int i = 0, size = nodes.length; i < size; i++) {
			DOMUtil.nodeToString(nodes[i], sb);
		}
		getClipboard().setContents(
				new Object[] { result, sb.toString() },
				new Transfer[] { TemplateTransfer.getInstance(),
						TextTransfer.getInstance() });
	}

	private Node toBeParent(Node parent, Stack children) {
		while (children.size() > 0) {
			parent.appendChild((Node) children.remove(0));
		}
		return parent;
	}

	private WorkNode getFirstSelectedChild(WorkNode node) {
		for (int i = 0, n = getSelections().size(); i < n; i++) {
			WorkNode wNode = (WorkNode) getSelections().get(i);
			if (wNode.getParent() == node) {
				return wNode;
			}
		}
		return null;
	}

	/**
	 * @param rootNode
	 * @param result
	 * @return the node
	 */
	Node collectStyleNodes(Node rootNode, Vector result) {
		Element element = null;
		if (rootNode instanceof Element) {
			element = (Element) rootNode;
		} else if (rootNode.getParentNode() != null) {
			element = (Element) rootNode.getParentNode();
		}
		ICSSStyle style = CSSUtil.getCSSStyle(element);

		Node node = EditModelQuery.getDocumentNode(rootNode).createElement(
				"span"); //$NON-NLS-1$
		for (int i = 0, n = result.size(); i < n; i++) {
			node.appendChild((Node) result.elementAt(i));
		}
		((Element) node).setAttribute(IHTMLConstants.ATTR_STYLE, CSSUtil
				.resolveCSSStyle(style));
		result.removeAllElements();
		result.add(node);
		return node;
	}

	/**
	 * @param rootNode
	 * @param result 
	 * @return the node
	 */
	protected final Node collectOtherStyles(Node rootNode, Vector result) {
		Node cur = rootNode, prev = null, appendPoint = null;
		if (EditValidateUtil.validNode(rootNode)) {
			while (!EditModelQuery.isDocument(cur)) {
				if (!EditValidateUtil.validNode(cur)) {
					return null;
				}
				String name = cur.getNodeName() != null ? cur.getNodeName()
						.toLowerCase() : ""; //$NON-NLS-1$
				if (EditModelQuery.HTML_STYLE_NODES.contains(name)) {
					if (prev != null) {
						Node newone = cur.cloneNode(false);
						newone.appendChild(prev);
						prev = newone;
					} else {
						prev = cur.cloneNode(false);
						appendPoint = prev;
					}
				}
				cur = cur.getParentNode();
			}
			if (appendPoint != null) {
				for (int i = 0, n = result.size(); i < n; i++) {
					appendPoint.appendChild((Node) result.elementAt(i));
				}
				result.removeAllElements();
				result.add(prev);
			}
		}
		return prev;
	}
}
