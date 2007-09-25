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
package org.eclipse.jst.pagedesigner.editors.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.commands.range.ApplyStyleCommand;
import org.eclipse.jst.pagedesigner.range.RangeUtil;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.HTMLGraphicalViewerListenenerAdapter;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewerListener;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * @author mengbo
 */
public abstract class ChangeStyleAction extends Action implements IUpdate {
	private IHTMLGraphicalViewer _viewer;

	private String _expectedTag;

	private IHTMLGraphicalViewerListener _listener = new HTMLGraphicalViewerListenenerAdapter() 
	{
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewerListener#selectionChangeFinished()
		 */
		public void selectionChangeFinished() {
			update();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
		 */
		public void selectionChanged(SelectionChangedEvent event) {
			update();
		}
	};

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
	 * @param viewer
	 */
	public void setViewer(IHTMLGraphicalViewer viewer) {
		if (viewer == _viewer) {
			return;
		}
		if (_viewer != null) {
			_viewer.removeHTMLViewerListener(_listener);
		}
		_viewer = viewer;
		if (_viewer != null) {
			_viewer.addHTMLViewerListener(_listener);
		}
		update();
	}

	/**
	 * 
	 */
	public void update() 
	{
	    boolean update = checkForUpdateAndMaybeDisableState();
	    
	    if (update)
	    {
	        updateState();
	    }
	}

   /**
    * Update the state 
    */
	protected void updateState()
    {
       DesignRange range = _viewer.getRangeSelection();
       updateStatus(RangeUtil.normalize(range));
    }

	/**
	 * Update the checked/enabled state
	 * @return true if we should update status
	 */
	protected final boolean checkForUpdateAndMaybeDisableState()
	{
        if (_viewer == null) {
            this.setChecked(false);
            this.setEnabled(false);
            return false;
        }
        if (!_viewer.isInRangeMode()) {
            // XXX: later we may support in range mode.
            this.setChecked(false);
            this.setEnabled(false);
            return false;
        }
        DesignRange range = _viewer.getRangeSelection();
        if (range == null || !range.isValid()) {
            this.setChecked(false);
            this.setEnabled(false);
            return false;
        }
        return true;
	}
	

	/**
	 * @return the viewer's current design range
	 */
	protected final DesignRange getDesignRange()
	{
	    return _viewer.getRangeSelection();
	}
	/**
	 * @param range
	 */
	private void updateStatus(DesignRange range) {
		if (range.isEmpty()) {
			this.setEnabled(false);
			this.setChecked(false); // FIXME: not handling checked status yet.
		} else {
			this.setEnabled(true);
			this.setChecked(false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		if (_viewer == null || !_viewer.isInRangeMode()) {
			return;
		}
		DesignRange range = _viewer.getRangeSelection();
		if (range == null || !range.isValid()) {
			return;
		}
		if (range.isEmpty())
			return; // nothing to do to empty range.

		// if currently checked, means unapply the style. If current not
		// checked, means apply the style
		boolean apply = !this.isChecked();
		if (apply) {
			applyStyle();
		} else {
			// not supported yet.
		}
	}

	/**
	 * 
	 */
	private void applyStyle() {
		DesignerCommand command = new ApplyStyleCommand(_viewer,
				getExpectedTag(), getExpectedCSSProperty(),
				getExpectedCSSPropertyValue());
		command.execute();
	}

	/**
	 * @return  the expected property value
	 */
	protected abstract String getExpectedCSSPropertyValue();

	/**
	 * @return  the expected property
	 */
	protected abstract String getExpectedCSSProperty();

	/**
	 * @return the expected tag
	 */
	protected String getExpectedTag() {
		return _expectedTag;
	}
}
