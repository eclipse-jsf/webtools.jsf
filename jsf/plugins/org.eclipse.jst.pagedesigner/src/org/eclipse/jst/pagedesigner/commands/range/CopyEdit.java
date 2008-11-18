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

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.EditHelper;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * @author mengbo
 */
public class CopyEdit extends DesignEdit {
	private static Logger _log = PDPlugin.getLogger(CopyEdit.class);

	private Stack result = new Stack();

	/**
	 * @param range
	 * @param viewer
	 */
	public CopyEdit(DOMRange range, GraphicalViewer viewer) {
		super(range, viewer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.DesignEdit#operate()
	 */
	protected boolean operate() {
		WorkNode root = getRootWorkNode();
		Node rootNode = root.getNode();
		result = getProcessedResult();
		collectOtherStyles(rootNode, result);
		setClipboard(result);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.DesignEdit#processContainer(org.eclipse.jst.pagedesigner.commands.range.WorkNode)
	 */
	protected Node processContainer(WorkNode node) {
		return node.getNode().cloneNode(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.AbstractCopyEdit#processNode(org.w3c.dom.Node,
	 *      int[], java.util.Stack)
	 */
	protected Node processNode(WorkNode node) {
		int pos[] = node.getPosOffsets();
		// the text could be tranparent, or 0 length.
		Assert.isTrue(pos[0] <= pos[1]);
		if (pos[0] == pos[1]) {
			return null;
		}
		return node.getNode().cloneNode(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.AbstractCopyEdit#processText(org.w3c.dom.Text,
	 *      int[], java.util.Stack)
	 */
	protected Text processText(WorkNode node) {
		Text currentNode = (Text) node.getNode();
		int left = EditModelQuery.getNodeStartIndex(currentNode);
		int right = EditModelQuery.getNodeEndIndex(currentNode);
		int location1 = EditHelper.getInstance().getLocation(currentNode,
				node.getPosOffsets()[0], true);
		int location2 = EditHelper.getInstance().getLocation(currentNode,
				node.getPosOffsets()[1], true);
		int start = 0;
		int end = right - left;
		// left index
		if (location1 > EditHelper.IN_MIDDLE
				|| location2 < EditHelper.IN_MIDDLE) {
			return null;
		}
		if (location1 <= EditHelper.IN_MIDDLE) {
			start = node.getQualifiedOffsets()[0];
		}
		if (location2 >= EditHelper.IN_MIDDLE) {
			end = node.getQualifiedOffsets()[1];
		}
		if (start == end) {
			return null;
		}
        
        try {
        	String text = currentNode.getData().substring(start, end);
        	return EditModelQuery.getDocumentNode(currentNode)
        			.createTextNode(text);
        } catch (DOMException e) {
            // TODO: changed this from catching Exception
            // DOMException is the only exception that 
            // anything in the try is threatening to throw
            // and even that is Runtime
        	_log.error("Exception", e); //$NON-NLS-1$
        	return null;
        }
	}
}
