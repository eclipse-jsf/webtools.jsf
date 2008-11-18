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
package org.eclipse.jst.pagedesigner.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.editors.HTMLEditor;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Element;

/**
 * @author mengbo
 */
public class DesignResizeComponentCommand extends Command {
	private EditPart _part;

	private Object _constraint;

	private SourceViewer _viewer;

	/**
	 * @param child
	 * @param constraint
	 */
	public DesignResizeComponentCommand(EditPart child, Object constraint) {
		this._part = child;
		this._constraint = constraint;
		EditPart part = child;
		if (part instanceof ScalableRootEditPart) {
            // TODO: eh?
            // do nothing I guess...
		} else {
			while (part != null
					&& !(part.getParent() instanceof ScalableRootEditPart)) {
				part = part.getParent();
			}
		}
		if (part != null) {
			EditPartViewer viewer = ((ScalableRootEditPart) part.getParent())
					.getViewer();
			HTMLEditor editor = ((HTMLEditor) ((DefaultEditDomain) ((IHTMLGraphicalViewer) viewer)
					.getEditDomain()).getEditorPart());
			_viewer = editor.getTextEditor().getTextViewer();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		Element element = ((Element) _part.getModel());
		String width = element.getAttribute(ICSSPropertyID.ATTR_WIDTH);
		// String height = element.getAttribute(ICSSPropertyID.ATTR_HEIGHT);
		String originalStyle = element.getAttribute(ICSSPropertyID.ATTR_STYLE);
		StringBuffer style;
		if (originalStyle != null) {
			originalStyle = this.removeOthers(originalStyle,
					ICSSPropertyID.ATTR_WIDTH);
			originalStyle = this.removeOthers(originalStyle,
					ICSSPropertyID.ATTR_HEIGHT);
			style = new StringBuffer(originalStyle);
		} else {
			style = new StringBuffer(50);
		}
		if (null == width) {
			style.append(";").append(ICSSPropertyID.ATTR_WIDTH).append(":") //$NON-NLS-1$ //$NON-NLS-2$
					.append(((Rectangle) _constraint).width).append(";"); //$NON-NLS-1$
			style.append(ICSSPropertyID.ATTR_HEIGHT).append(":").append( //$NON-NLS-1$
					((Rectangle) _constraint).height).append(""); //$NON-NLS-1$
		}
		element.setAttribute(ICSSPropertyID.ATTR_STYLE, style.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		_viewer.doOperation(ITextOperationTarget.REDO);
	}

	/**
	 * @param style
	 * @param item
	 * @return ?
	 */
	public String removeOthers(String style, String item) {
		String result = null;
		int pos = style.indexOf(item);
		if (pos < 0) {
			return style;
		}
		int pos1 = pos;
		if (pos > 0) {
			if (style.charAt(pos - 1) == ';') {
				pos--;
			}
		}
		char ch = style.charAt(pos1);
		while (!(ch == ';' || ch == '"' || ch == '\'')) {
			pos1++;
			if (pos1 >= style.length()) {
				break;
			}
			ch = style.charAt(pos1);
		}
		if (pos1 < style.length()) {
			result = style.substring(0, pos)
					+ style.substring(pos1 + 1, style.length());
		} else {
			result = style.substring(0, pos);
		}
		if (result.indexOf(item) >= 0) {
			return removeOthers(result, item);
		}
        return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		_viewer.doOperation(ITextOperationTarget.UNDO);
	}
}
