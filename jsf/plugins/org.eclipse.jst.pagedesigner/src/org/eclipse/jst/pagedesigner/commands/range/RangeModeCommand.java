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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.EditValidateUtil;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public abstract class RangeModeCommand extends DesignerCommand {
//	private static final Logger _log = PDPlugin
//			.getLogger(RangeModeCommand.class);

	DOMRange _resultRange = null;

	/**
	 * @param label
	 * @param viewer
	 */
	public RangeModeCommand(String label, IHTMLGraphicalViewer viewer) {
		super(label, viewer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#prePreExecute()
	 */
	protected boolean prePreExecute() {
		int position = -1;
		int length = -1;
		ISelection selection = getViewer().getSelection();
		if (selection != null) {
			if (getViewer().isInRangeMode()) {
				DesignRange range = (DesignRange) selection;
				if (range.isValid()) {
					IDOMPosition domPos = DOMPositionHelper.toDOMPosition(range
							.getStartPosition());
					IDOMPosition domEnd = DOMPositionHelper.toDOMPosition(range
							.getEndPosition());
					if (!EditValidateUtil.validPosition(domPos)
							|| !EditValidateUtil.validPosition(domEnd)) {
						return false;
					}
					position = EditModelQuery.getIndexedRegionLocation(domPos);
					int end = EditModelQuery.getIndexedRegionLocation(domEnd);
					if (end < position) {
						length = position - end;
						position = end;
					} else {
						length = end - position;
					}
				}
			} else {
				Object object = ((IStructuredSelection) selection)
						.getFirstElement();
				if (object instanceof ElementEditPart) {
					Node node = ((ElementEditPart) object).getIDOMNode();
					position = EditModelQuery.getNodeStartIndex(node);
					length = EditModelQuery.getNodeLenth(node);
				} else {
					return false;
				}
			}
			if (position >= 0 && length >= 0) {
				getModel().beginRecording(this, getLabel(), position, length);
			} else {
				getModel().beginRecording(this, getLabel());
			}
			getViewer().startSelectionChange();
			getModel().aboutToChangeModel();
			return true;
		}
        return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected final void doExecute() {
		DesignRange range = getViewer().getRangeSelection();
		if (range != null && range.isValid()) {
			DOMRange domrange = (range == null || !range.isValid()) ? null
					: toDOMRange(range);
			_resultRange = doRangeExecute(domrange);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
	 */
	protected final ISelection getAfterCommandDesignerSelection() {
//		try {
			if (_resultRange == null) {
				return null;
			}
			IDOMPosition startPos = _resultRange.getStartPosition();
			DesignPosition start = DOMPositionHelper.toDesignPosition(startPos);
			if (_resultRange.isEmpty()) {
				return new DesignRange(start, start);
			}
            IDOMPosition endPos = _resultRange.getEndPosition();
            return new DesignRange(start, DOMPositionHelper
            		.toDesignPosition(endPos));
            // TODO: don't know what this exception catch is for
//		} catch (Exception e) {
//			// "Selection error"
//			_log.error("Error.RangeModeCommand.SetSelection"); //$NON-NLS-1$
//			return null;
//		}
	}

	/**
	 * @param range
	 * @return
	 */
	private DOMRange toDOMRange(DesignRange range) {
		return new DOMRange(DOMPositionHelper.toDOMPosition(range
				.getStartPosition()), DOMPositionHelper.toDOMPosition(range
				.getEndPosition()));
	}

	/**
	 * In the implementation of this method, should not do anything relating to
	 * EditPart. (maybe even not ICSSStyle, since not style information not
	 * refreshed yet)
	 * @param selection 
	 * 
	 * @return null means no change have been done to the model. In this case,
	 *         system may choose to cancel undo recorrding, etc.
	 */
	protected abstract DOMRange doRangeExecute(DOMRange selection);

	/**
	 * @param parent
	 * @param ref
	 * @param child
	 */
	protected static void appendChild(Node parent, Node ref, Node child) {
		Node next = ref.getNextSibling();
		if (next == null)
			parent.appendChild(child);
		else
			parent.insertBefore(child, next);
	}
}
