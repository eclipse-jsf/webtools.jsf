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

import org.eclipse.jst.pagedesigner.dom.DOMPosition;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.DOMRefPosition;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Text;

/**
 * This command can used to handle things like "paste". Or keyboard printable
 * ascii key. Note: ENTER key is not handled here.
 * 
 * @author mengbo
 */
// FIXME: \r \n in the content string is not handled.
public class ContentCommand extends RangeModeCommand {

	private String _content;

	/**
	 * @param viewer
	 * @param content 
	 */
	public ContentCommand(IHTMLGraphicalViewer viewer, String content) {
		super("", viewer); //$NON-NLS-1$
		_content = content;
	}

	/**
	 * @param viewer
	 * @param c
	 */
	public ContentCommand(IHTMLGraphicalViewer viewer, char c) {
		super("", viewer); //$NON-NLS-1$
		_content = String.valueOf(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected DOMRange doRangeExecute(DOMRange range) {
		if (range == null)
			return null;

		IDOMPosition position = DOMPositionHelper.removeRange(range);
		position = doContent(position);
		return new DOMRange(position, position);

	}

	/**
	 * @param position
	 * @return ??
	 */
	protected IDOMPosition doContent(IDOMPosition position) {
		position = DOMPositionHelper.mergeIntoText(position);

		if (position.getContainerNode() instanceof Text) {
			Text text = (Text) position.getContainerNode();
			String data = text.getData();
			int offset = position.getOffset();
			String newData = data.substring(0, offset) + _content
					+ data.substring(offset);
			text.setData(newData);
			return new DOMPosition(text, offset + _content.length());
		}
        // we need to create a text node.
        Text text = getDocument().createTextNode(_content);
        position.getContainerNode().insertBefore(text,
        		position.getNextSiblingNode());
        return new DOMRefPosition(text, true);
	}

	// protected DesignPosition doContent()
	// {
	// // DesignPosition position = removeRange();
	// DesignPosition position = this.getSelectionRange().getEndPosition();
	// if ('\r' == _content || '\n' == _content)
	// {
	// Element br = getModel().getDocument().createElement("BR");
	// Node node = RangeUtil.insertElement(position, br);
	//
	// // we need set the new range to the node.
	// // FIXME: temp code, need to reconsider how to do refresh, when those
	// editpart
	// // are recreated.
	// IDOMNode parent = (IDOMNode) node.getParentNode();
	// EditPart parentPart = (EditPart) parent.getAdapterFor(EditPart.class);
	// List childParts = parentPart.getChildren();
	// for (int i=0; i<childParts.size(); i++)
	// {
	// if (node == ((EditPart)childParts.get(i)).getModel())
	// {
	// return new DesignPosition(parentPart, i+1);
	// }
	// }
	// return new DesignPosition(parentPart, childParts.size());
	// }
	// else
	// {
	// TextPosition textPosition = RangeUtil.insertText(position,
	// String.valueOf(_content));
	// IDOMText text = textPosition.getTextNode();
	// EditPart part = (EditPart) text.getAdapterFor(EditPart.class);
	// return new DesignPosition(part, textPosition.getOffset());
	// }
	// }
	//
}
