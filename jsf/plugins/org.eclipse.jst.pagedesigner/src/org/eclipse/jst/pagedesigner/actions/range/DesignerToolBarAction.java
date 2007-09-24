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
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * @author mengbo
 */
public abstract class DesignerToolBarAction extends Action implements IUpdate,
		ISelectionChangedListener {
	private IHTMLGraphicalViewer _viewer;

	/**
	 * @param text
	 * @param style
	 */
	public DesignerToolBarAction(String text, int style) {
		super(text, style);
	}

	/**
	 * @param text
	 * @param image
	 */
	public DesignerToolBarAction(String text, ImageDescriptor image) {
		super(text, image);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		update();
	}

	/**
	 * 
	 */
	public void update() {
		if (canRun(this._viewer)) {
			setEnabled(true);
			updateStatus();
		} else {
			setEnabled(false);
		}
	}

	/**
	 * Update the status
	 */
	protected void updateStatus() {
		DesignRange range = _viewer.getRangeSelection();
		DOMRange domRange = null;
		if (range != null) {
			domRange = new DOMRange(DOMPositionHelper.toDOMPosition(range
					.getStartPosition()), DOMPositionHelper.toDOMPosition(range
					.getEndPosition()));
			if (isApplied(domRange)) {
				this.setChecked(true);
			} else {
				this.setChecked(false);
			}
		}
	}

	/**
	 * @param range
	 * @return ??
	 */
	protected abstract boolean isApplied(DOMRange range);

	/**
	 * @param viewer
	 * @return true if this action can run
	 */
	protected boolean canRun(IHTMLGraphicalViewer viewer) {
		if (viewer != null && viewer.isInRangeMode()
				&& viewer.getModel().getDocument().hasChildNodes()) {
			DesignRange range = viewer.getRangeSelection();
			if (range != null && range.isValid()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param viewer
	 */
	public void setViewer(IHTMLGraphicalViewer viewer) {
		if (viewer == _viewer) {
			return;
		}
        
        if (_viewer != null) {
        	_viewer.removeSelectionChangedListener(this);
        }
        _viewer = viewer;
        if (_viewer != null) {
        	_viewer.addSelectionChangedListener(this);
        }
        update();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		// Assert.isTrue(_viewer != null && _viewer.isInRangeMode());
		DesignRange range = _viewer.getRangeSelection();
		if (range == null || !range.isValid()) {
			return;
		}
		Command command = getCommand();
		if (command != null) {
			command.execute();
		}
	}

	/**
	 * @return the command for this action or null
	 */
	protected abstract Command getCommand();

	/**
	 * @return Returns the _viewer.
	 */
	public IHTMLGraphicalViewer getViewer() {
		return _viewer;
	}

}
