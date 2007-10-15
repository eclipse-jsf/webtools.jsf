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

import org.eclipse.core.runtime.Assert;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRefPosition;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * This class is used for debug purpose, and can be used to test some invalid
 * status in the functions. Generally all the methods here will throw exception
 * when invalid status happens.
 * 
 * @author mengbo
 */
public final class EditValidateUtil {
	private static final Logger _logger = PDPlugin
			.getLogger(EditValidateUtil.class);

	private static final boolean ALERT = false;

	private static final boolean REPORT = false;

	/**
	 * A valid position means container node, offset are all valid.
	 * 
	 * @param position
	 * @return true if the position is valid
	 */
	public static boolean validPosition(IDOMPosition position) {
		try {
			boolean result = true;
			Assert.isTrue(position != null
					&& position.getContainerNode() != null
					&& position.getOffset() >= 0);
			Node container = position.getContainerNode();
			result &= validNode(container);
			int offset = position.getOffset();
			if (position.isText()) {
				int length = ((Text) container).getLength();
				Assert.isTrue(offset <= length);
			} else {
				if (position instanceof DOMPosition && offset > 0) {
					Assert.isTrue(container.hasChildNodes()
							&& container.getChildNodes().getLength() >= offset);
				}
			}
			return result;
		} catch (Exception e) {
			// "error", "Error in validPosition"
			if (ALERT) {
				PDPlugin
						.getAlerts()
						.confirm(
								"Alert.EditValidateUtil.Title", "Alert.EditValidateUtil.Position", e); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (REPORT) {
				// "Invalid position:"
				_logger.error("Error.EditValidateUtil.Position", e); //$NON-NLS-1$
			}
			return false;
		}
	}

	/**
	 * @param position
	 * @return true if position is valid
	 */
	public static boolean validPosition(DesignPosition position) {
		try {
			boolean result = true;
			Assert.isTrue(position != null
					&& position.getContainerPart() != null
					&& position.getContainerNode() != null
					&& position.getOffset() >= 0);
			Node container = position.getContainerNode();
			result &= validNode(container);
			int offset = position.getOffset();
			if (EditModelQuery.isText(container)) {
				int length = ((Text) container).getLength();
				Assert.isTrue(offset <= length);
			} else {
				if (position instanceof DesignRefPosition && offset > 0) {
					Assert.isTrue(container.hasChildNodes()
							&& container.getChildNodes().getLength() >= offset);
				}
			}
			return result;
		} catch (Exception e) {
			// "error", "Error in validPosition"
			if (ALERT) {
				PDPlugin
						.getAlerts()
						.confirm(
								"Alert.EditValidateUtil.Title", "Alert.EditValidateUtil.Position", e); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (REPORT) {
				// "Invalid position:"
				_logger.error("Error.EditValidateUtil.Position", e); //$NON-NLS-1$
			}
			return false;
		}
	}

	/**
	 * Valid node is TextNode and it's valid node.
	 * 
	 * @param node
	 * @return true if node is valid
	 */
	public static boolean validText(Node node) {
		try {
			boolean result = true;
			Assert.isTrue(node.getNodeType() == Node.TEXT_NODE);
			Assert.isTrue(((Text) node).getData() != null);
			result &= validNode(node);
			return result;
		} catch (Exception e) {
			// "Error", "Error in validText"
			if (ALERT) {
				PDPlugin
						.getAlerts()
						.confirm(
								"Alert.EditValidateUtil.Title", "Alert.EditValidateUtil.Text", e); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (REPORT) {
				// "Invalid Text:"
				_logger.error("Error.EditValidateUtil.Text", e); //$NON-NLS-1$
			}
			return false;
		}
	}

	/**
	 * A valid node is resided in the model tree
	 * 
	 * @param node
	 * @return true if node is valid
	 */
	public static boolean validNode(Node node) {
		try {
			Assert.isTrue(node instanceof IDOMNode);
			Assert.isTrue((node.getNodeType() == Node.DOCUMENT_NODE)
					|| (node.getParentNode() != null));
			// What's this?
			return true;
		} catch (Exception e) {
			// "Error", "Error in validNode"
			if (ALERT) {
				PDPlugin
						.getAlerts()
						.confirm(
								"Alert.EditValidateUtil.Title", "Alert.EditValidateUtil.Node", e); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (REPORT) {
				// "Invalid node:"
				_logger.error("Error.EditValidateUtil.Node", e); //$NON-NLS-1$
			}
			return false;
		}
	}

	/**
	 * A valid DOMRange contains valid IDOMPosition, and it should not be within
	 * node like 'HEAD'. The later one might not be suitble to valid in this
	 * util, it should be checked by some edit valid helper.
	 * 
	 * @param range
	 * @return true if range is valid
	 */
	public static boolean validRange(DOMRange range) {
		try {
			// TODO: never read EditModelQuery modelQuery = EditModelQuery.getInstance();
			boolean result = true;
			IDOMPosition start = range.getStartPosition();
			IDOMPosition end = range.getEndPosition();
			result &= validPosition(start);
			result &= validPosition(end);
            // TODO: never read
//			Node startContainer = start.getContainerNode();
//			Node endContainer = end.getContainerNode();
			// Assert.isTrue(isValidForEditing(modelQuery.getCommonAncestor(startContainer,
			// endContainer)));
			return result;
		} catch (Exception e) {
			// "Error", "Error in validRange"
			if (ALERT) {
				PDPlugin
						.getAlerts()
						.confirm(
								"Alert.EditValidateUtil.Title", "Alert.EditValidateUtil.Range", e); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (REPORT) {
				// "Invalid Range:"
				_logger.error("Error.EditValidateUtil.Range", e); //$NON-NLS-1$
			}
			return false;
		}
	}

	/**
	 * @param text
	 * @param index
	 * @return true if the index is valid
	 */
	public static boolean validStringIndex(Node text, int index) {
		try {
			Assert.isTrue(index >= 0 && ((Text) text).getLength() >= index);
			return validText(text);
		} catch (Exception e) {
			// "Error", "Error in validStringIndex"
			if (ALERT) {
				PDPlugin
						.getAlerts()
						.confirm(
								"Alert.EditValidateUtil.Title", "Alert.EditValidateUtil.StringIndex", e); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (REPORT) {
				// "Invalid Index in String:"
				_logger.error("Error.EditValidateUtil.StringIndex", e); //$NON-NLS-1$
			}
			return false;
		}
	}

	/**
	 * @param text
	 * @param index
	 * @param offset
	 * @return true if the index offset is valid
	 */
	public static boolean validStringIndexOffset(Node text, int index,
			int offset) {
		try {
			Assert.isTrue(index >= 0 && (index + offset) >= 0
					&& ((Text) text).getLength() >= (index + offset)
					&& ((Text) text).getLength() >= index);
			return validText(text);
		} catch (Exception e) {
			// "error", "Error in validStringIndex"
			if (ALERT) {
				PDPlugin
						.getAlerts()
						.confirm(
								"Alert.EditValidateUtil.Title", "Alert.EditValidateUtil.IndexOffset", e); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (REPORT) {
				// "Invalid index or offset in String:"
				_logger.error("Error.EditValidateUtil.IndexOffset", e); //$NON-NLS-1$
			}
			return false;
		}
	}

//	private static void dumpPosition(String message, IDOMPosition position,
//			boolean forward) {
		// for future internal debug
		// message(message);
		// _logger.debug("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
		// if (position != null)
		// {
		// Node container = position.getContainerNode();
		// Node node = null;
		// String text = null;
		// if (getContainerLength(container) > 0)
		// {
		// if (!position.isText())
		// {
		// int index = forward ? position.getOffset() : position.getOffset() -
		// 1;
		// node = container.getChildNodes().item(index);
		// }
		// else
		// {
		// int index = forward ? position.getOffset() : position.getOffset() -
		// 1;
		// if (index >= 0 && index < getContainerLength(container))
		// {
		// text = ((Text) container).substringData(index, 1);
		// }
		// else
		// {
		// text = null;
		// }
		// }
		// }
		// _logger.debug("DOMPosition type?:" + (position instanceof
		// DOMPosition) + " container name: " +
		// position.getContainerNode().getLocalName() + " offset:" +
		// position.getOffset()
		// + " length:" + getContainerLength(position.getContainerNode()));
		// if (node != null)
		// {
		// _logger.debug("node to operate:" + node.getLocalName());
		// }
		// if (text != null)
		// {
		// _logger.debug("text to operate:\"" + text + "\" length:" +
		// text.length());
		// }
		// }
		// _logger.debug("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//	}
	
	/**
	 * @param pos
	 * @param forward
	 * @return true if the position if vallid for editing relative to forward (true) or backward  (false)
	 */
	public static boolean isValidForEditing(IDOMPosition pos, boolean forward) {
		if (pos == null) {
			return false;
		}
		if (pos.isText()) {
			return true;
		}
        Node container = pos.getContainerNode();
        // only head can't be edited
        if (EditModelQuery.isChild(IHTMLConstants.TAG_HEAD, container,
        		true)) {
        	return false;
        }
        Node sibling = EditModelQuery.getInstance().getSibling(pos,
        		forward);
        if (sibling != null) {
        	if (EditModelQuery.isText(sibling)) {
        		return true;
        	}
        	Assert.isTrue(sibling.getLocalName() != null);
        	if (EditModelQuery.UNREMOVEBLE_TAGS.contains(sibling
        			.getLocalName().toLowerCase())) {
        		return false;
        	}
        }
        return true;
	}

	/**
	 * @param node
	 * @return true if node is valid for editing
	 */
	public static boolean isValidForEditing(Node node) {
		if (EditModelQuery.isChild(IHTMLConstants.TAG_HEAD, node, true)) {
			return false;
		}
		return true;
	}

	private EditValidateUtil()
	{
	    // do nothing
	}
	// Reserved for inner use.
	//
	// private static void errorNotice()
	// {
	// try
	// {
	// // FileInputStream fileau = new
	// FileInputStream("C:\\WINNT\\Media\\ringout.wav");
	// // AudioStream as = new AudioStream(fileau);
	// // AudioPlayer.player.start(as);
	// }
	// catch (Exception e)
	// {
	// System.out.println("error in file open");
	// }
	//
	// }
}
