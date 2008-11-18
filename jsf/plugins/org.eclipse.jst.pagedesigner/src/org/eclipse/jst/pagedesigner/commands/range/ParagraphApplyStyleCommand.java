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

import java.util.Arrays;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.EditPart;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.dom.DOMPosition;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.DOMRefPosition;
import org.eclipse.jst.pagedesigner.dom.DOMUtil;
import org.eclipse.jst.pagedesigner.dom.EditHelper;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.dom.IDOMRefPosition;
import org.eclipse.jst.pagedesigner.parts.TextEditPart;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * @author mengbo
 */
public class ParagraphApplyStyleCommand extends ApplyStyleCommand {

    /**
     * the list of possible html heading tags
     */
    private static final String[] HH = { "h1", "h2", "h3", "h4", "h5", "h6" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

    /**
	 * @param viewer
	 * @param tag
	 * @param property
	 * @param value
	 */
	public ParagraphApplyStyleCommand(IHTMLGraphicalViewer viewer, String tag,
			String property, String value) {
		super(viewer, tag, property, value);
	}

	/**
	 * @param viewer
	 * @param node
	 * @param property
	 * @param value
	 */
	public ParagraphApplyStyleCommand(IHTMLGraphicalViewer viewer,
			Element node, String property, String value) {
		super(viewer, node, property, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.RangeModeCommand#doRangeExecute(org.eclipse.jst.pagedesigner.dom.DOMRange)
	 */
	protected DOMRange doRangeExecute(DOMRange range) {
		if (range != null) {
			boolean ordered = range.isOrdered();
			IDOMPosition start = ordered ? range.getStartPosition() : range
					.getEndPosition();
			IDOMPosition end = ordered ? range.getEndPosition() : range
					.getStartPosition();
			Node common = null;
			Node container = null;
			if (EditModelQuery.isSame(range)) {
				container = start.getContainerNode();
				ParagraphFinder finder = new ParagraphFinder(start);
				Paragraph p = finder.getParagraph(start);
				start = p.getStart();
				end = p.getEnd();
				common = p.getLowestContainer();
			} else {
				common = EditModelQuery.getInstance().getCommonAncestor(start,
						end);
			}
			DOMRange rt;
			// This code is for h1-h6 only, it may need to be replaced.
			if ((rt = replaceExistingH(start, end)) != null) {
				return rt;
			}
			// replace existing p
			if (getTag().equalsIgnoreCase(IHTMLConstants.TAG_P)) {
				rt = replaceExistingP(start, end);
				if (rt != null) {
					return rt;
				}
			}
			if (start.getContainerNode() == end.getContainerNode()) {
				int offset1 = start.getOffset();
				int offset2 = end.getOffset();
				IDOMPosition old = start;
				start = split(start);
				// parent is splited
				if (start != old) {
					container = start.getNextSiblingNode();
					offset2 -= offset1;
					end = new DOMPosition(container, offset2);
				}
				end = split(end);
			} else {
				start = split(common, start);
				end = split(common, end);
			}
			range = InsertStyleTag(new DOMRange(start, end));
		}
		return range;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		return true;
	}

	/*
	 * Try to split the node so that we can avoid wrap its children directly.
	 * Begining from 'position' the split can reach as high as the level of
	 * 'common'.
	 */
	private IDOMPosition split(Node common, IDOMPosition position) {
		Assert.isTrue(EditModelQuery.isChild(common, position
				.getContainerNode()));
		Node container = position.getContainerNode();
		String[] styleNodes = new String[EditModelQuery.HTML_STYLE_NODES.size()];
		EditModelQuery.HTML_STYLE_NODES.toArray(styleNodes);
		while (EditModelQuery.isText(container) || (container != common && //
				EditModelQuery.containItem(styleNodes, container, true))) {
			IDOMPosition old = position;
			position = EditHelper.splitNode(position);
			if (old == position) {
				int pos = EditHelper.getLocation(position);
				switch (pos) {
				case -1:
					position = new DOMRefPosition(position.getContainerNode(),
							false);
					break;
				case 1:
					position = new DOMRefPosition(position.getContainerNode(),
							true);
				}
			}
			Node containerBackup = container;
			container = container.getParentNode();
			if (containerBackup.getNodeName().equalsIgnoreCase(
					IHTMLConstants.TAG_P)) {
				container.removeChild(containerBackup);
			}
		}
		return position;
	}

	/*
	 * Split the position's container node only.
	 */
	private IDOMPosition split(IDOMPosition position) {
		Node container = position.getContainerNode();
		String[] styleNodes = new String[EditModelQuery.HTML_STYLE_NODES.size()];
		EditModelQuery.HTML_STYLE_NODES.toArray(styleNodes);
		if (EditModelQuery.isText(container)
				|| EditModelQuery.containItem(styleNodes, container, true)) {
			return EditHelper.splitNode(position);
		}
		return position;
	}

	private DOMRange replaceExistingH(IDOMPosition start, IDOMPosition end) {
		Node common = EditModelQuery.getInstance()
				.getCommonAncestor(start, end);
		// Here we insert some code to avoid creating tags duplicated. but these
		// are not the entire cases.
		if (Arrays.asList(HH).contains(
				getAName(getTag()).toLowerCase())
				&& Arrays.asList(HH).contains(
						getAName(common.getNodeName()).toLowerCase())) {
			// uncheck action menu
			if (getAName(getTag()).toLowerCase().equalsIgnoreCase(
					getAName(common.getNodeName()).toLowerCase())) {
				NodeList nodes = common.getChildNodes();

				for (int i = 0, size = nodes.getLength(); i < size; i++) {
					common.getParentNode().insertBefore(nodes.item(i), common);
				}
				common.getParentNode().removeChild(common);
				return new DOMRange(start, end);
			}
			start = DOMPositionHelper.toDOMRefPosition(start);
			end = DOMPositionHelper.toDOMRefPosition(end);
			Node newHNode = EditModelQuery.getDocumentNode(common)
					.createElement(getTag());
			EditModelQuery.copyChildren(common, newHNode);
			common.getParentNode().replaceChild(newHNode, common);
			return new DOMRange(start, end);
		}
		return null;
	}

	private DOMRange replaceExistingP(IDOMPosition start, IDOMPosition end) {
		// find the selected startNode,endNode and start node's parent node
		Node startNode = start instanceof IDOMRefPosition ? start
				.getNextSiblingNode() : start.getContainerNode();
		Node endNode = end instanceof IDOMRefPosition ? end
				.getPreviousSiblingNode() : end.getContainerNode();
		Node parentNode = startNode.getParentNode();

		if (!(start.isText()) && start instanceof DOMPosition) {
			startNode = startNode.getChildNodes().item(start.getOffset());
			parentNode = start.getContainerNode();
		}
		if (!(end.isText()) && end instanceof DOMPosition) {
			// because the offset is based on the position between nodes,so we
			// need to reduce one from the offset
			// in order to get the correct end node.
			endNode = endNode.getChildNodes().item(end.getOffset() - 1);
		}

		// compute selected character number in the text or selected element
		// number under a node
		int len = 0;
//		if (start instanceof DOMPosition && end instanceof DOMPosition
//				|| start instanceof IDOMPosition && end instanceof IDOMPosition) {
        // TODO: as written, this will be the only statement run, since
        // both start and end are instanceof IDOMPosition by defn.
			len = end.getOffset() - start.getOffset();
//		} else {
//			IDOMRefPosition startRef = null;
//			IDOMRefPosition endRef = null;
//			if (!(start.isText()) && start instanceof DOMPosition) {
//				startRef = new DOMRefPosition(startNode, false);
//			} else if (!(end.isText()) && end instanceof DOMPosition) {
//				endRef = new DOMRefPosition(endNode, true);
//			}
//			len = (endRef != null ? endRef.getOffset() : end.getOffset())
//					- (startRef != null ? startRef.getOffset() : start
//							.getOffset());
//		}

		// if a full Text node is selected,and the Text node is the only child
		// of its parent
		if ((startNode == endNode) && (startNode instanceof Text)) {
			TextEditPart part = (TextEditPart) ((INodeNotifier) startNode)
					.getAdapterFor(EditPart.class);
			boolean condition = false;
			if (start instanceof IDOMRefPosition
					|| (start instanceof DOMPosition && !start.isText())) {
				condition = parentNode.getNodeName().equalsIgnoreCase(
						IHTMLConstants.TAG_P)
						&& parentNode.getChildNodes().getLength() == 1;
			} else {
				condition = parentNode.getNodeName().equalsIgnoreCase(
						IHTMLConstants.TAG_P)
						&& parentNode.getChildNodes().getLength() == 1
						&& part.getTextData().length() == len;
			}
			if (condition) {
				// if uncheck the align action
				if (this.getApplyingNode()
						.getAttribute(IHTMLConstants.ATTR_ALIGN)
						.equals(
								((Element) parentNode)
										.getAttribute(IHTMLConstants.ATTR_ALIGN))) {
					((Element) parentNode)
							.removeAttribute(IHTMLConstants.ATTR_ALIGN);
					IDOMPosition startPos = new DOMPosition(parentNode, 0);
					IDOMPosition endPos = new DOMRefPosition(endNode, true);
					return new DOMRange(startPos, endPos);
				}
				// else replace the align attribute
				/**
				 * this._applyingNode.appendChild(startNode);
				 * parentNode.getParentNode().replaceChild(this._applyingNode,
				 * parentNode);
				 */
				String align = this.getApplyingNode()
						.getAttribute(IHTMLConstants.ATTR_ALIGN);
				((Element) parentNode).setAttribute(IHTMLConstants.ATTR_ALIGN,
						align);

				IDOMPosition startPos = new DOMPosition(parentNode, 0);
				IDOMPosition endPos = new DOMRefPosition(endNode, true);
				return new DOMRange(startPos, endPos);
			}
		} else {
			if (parentNode != null
					&& parentNode.getNodeName().equalsIgnoreCase(
							IHTMLConstants.TAG_P)
					&& parentNode.getChildNodes().getLength() == len) {
				if (this.getApplyingNode()
						.getAttribute(IHTMLConstants.ATTR_ALIGN)
						.equals(
								((Element) parentNode)
										.getAttribute(IHTMLConstants.ATTR_ALIGN))) {
					((Element) parentNode)
							.removeAttribute(IHTMLConstants.ATTR_ALIGN);
					IDOMPosition startPos = new DOMPosition(parentNode, 0);
					IDOMPosition endPos = new DOMRefPosition(endNode, true);
					return new DOMRange(startPos, endPos);
				}

				/**
				 * Node sibling = startNode.getNextSibling();
				 * this._applyingNode.appendChild(startNode); Node
				 * endNodeSibling = endNode.getNextSibling(); while (sibling !=
				 * null && startNode != endNode && sibling != endNodeSibling) {
				 * Node tempNode = sibling.getNextSibling();
				 * this._applyingNode.appendChild(sibling); sibling = tempNode; }
				 * parentNode.getParentNode().replaceChild(this._applyingNode,
				 * parentNode);
				 */
				String align = this.getApplyingNode()
						.getAttribute(IHTMLConstants.ATTR_ALIGN);
				((Element) parentNode).setAttribute(IHTMLConstants.ATTR_ALIGN,
						align);

				IDOMPosition startPos = new DOMPosition(parentNode, 0);
				IDOMPosition endPos = new DOMRefPosition(endNode, true);
				return new DOMRange(startPos, endPos);
			}
		}
		return null;
	}

	private DOMRange InsertStyleTag(DOMRange range) {
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

        // now the startContainer and the endContainer should share the same
        // parent
        Element newNode = createStyleElement();
        startContainer.getParentNode()
        		.insertBefore(newNode, startContainer);

        Node sibling = startContainer.getNextSibling();
        newNode.appendChild(startContainer);
        Node endNodeSibling = endContainer.getNextSibling();
        while (sibling != null && startContainer != endContainer
        		&& sibling != endNodeSibling) {
        	Node tempNode = sibling.getNextSibling();
        	newNode.appendChild(sibling);
        	sibling = tempNode;
        }

        IDOMPosition startPos = new DOMPosition(newNode, 0);
        IDOMPosition endPos = new DOMRefPosition(endContainer, true);
        return new DOMRange(startPos, endPos);
	}

	private static String getAName(String name) {
		return name == null ? "" : name; //$NON-NLS-1$
	}
}
