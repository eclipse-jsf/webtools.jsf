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
package org.eclipse.jst.pagedesigner.actions.range;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.commands.range.ApplyStyleCommand;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.dom.DOMRangeHelper;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class ChangeStyleAction extends DesignerToolBarAction {
	private final String _expectedTag;

	/**
	 * @param text
	 * @param name 
	 * @param image
	 * @param style 
	 */
	public ChangeStyleAction(String text, String name, ImageDescriptor image,
			int style) {
		super(text, style);
		_expectedTag = name;
		this.setImageDescriptor(image);
	}

	/**
	 * @param text
	 * @param name
	 * @param enabled
	 * @param disabled
	 * @param style
	 */
	public ChangeStyleAction(String text, String name, ImageDescriptor enabled,
			ImageDescriptor disabled, int style) {
		super(text, style);
		_expectedTag = name;
		setImageDescriptor(enabled);
		setDisabledImageDescriptor(disabled);
	}


	protected boolean isApplied(DOMRange range) {
		if (range == null) {
			return false;
		}

		boolean ordered = range.isOrdered();
		IDOMPosition start = ordered ? range.getStartPosition() : range
				.getEndPosition();
		IDOMPosition end = ordered ? range.getEndPosition() : range
				.getStartPosition();
		Node startnode = start.getContainerNode();
		Node endnode = end.getContainerNode();
		if (!EditModelQuery.hasAncestor(startnode, _expectedTag, true)) {
			return false;
		}
		for (Node node = startnode; node != endnode; node = EditModelQuery
				.getInstance().getNextLeafNeighbor(node)) {
			if (!EditModelQuery.hasAncestor(node, _expectedTag, true)) {
				return false;
			}
		}
		if (!EditModelQuery.hasAncestor(endnode, _expectedTag, true)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the expected tag
	 */
	protected String getExpectedTag() {
		return _expectedTag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.DesignerToolBarAction#getCommand()
	 */
	protected Command getCommand() {
		DesignRange range = getViewer().getRangeSelection();
		DOMRange dRange = DOMRangeHelper.toDOMRange(range);
		Command command;
		if (isApplied(dRange)) {
			// command = new UnapplyStyleCommand(getViewer(), _expectedTag,
			// null, null);
			// since the un-applystyle is not implemented yet,we do nothing
			// here.
			command = null;
			this.setChecked(true);
		} else {
			command = new ApplyStyleCommand(getViewer(), _expectedTag, null,
					null);
		}
		return command;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.texteditor.IUpdate#update()
	 */
	public void update() {
		if (canRun(getViewer())) {
			setEnabled(true);
		} else {
			setEnabled(false);
		}
		updateStatus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.actions.range.DesignerToolBarAction#updateStatus()
	 */
	public void updateStatus() {
		IHTMLGraphicalViewer viewer = getViewer();
		if (viewer != null && viewer.isInRangeMode()
				&& viewer.getModel().getDocument().hasChildNodes()) {
			DesignRange range = getViewer().getRangeSelection();
			if (range != null && range.isValid()) {
				DOMRange domRange = null;
				domRange = new DOMRange(DOMPositionHelper.toDOMPosition(range
						.getStartPosition()), DOMPositionHelper
						.toDOMPosition(range.getEndPosition()));
				if (isApplied(domRange)) {
					this.setChecked(true);
				} else {
					this.setChecked(false);
				}
				return;
			}
		}
		this.setChecked(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.actions.range.DesignerToolBarAction#canRun(org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer)
	 */
	protected boolean canRun(IHTMLGraphicalViewer viewer) {
		if (viewer != null && viewer.isInRangeMode()
				&& viewer.getModel().getDocument().hasChildNodes()) {
			DesignRange range = viewer.getRangeSelection();
			if (range != null && range.isValid()) {
				DesignPosition startPos = range.getStartPosition();
				DesignPosition endPos = range.getEndPosition();
				if (startPos != endPos) {
					return true;
				}
			}
		}
		return false;
	}
}
