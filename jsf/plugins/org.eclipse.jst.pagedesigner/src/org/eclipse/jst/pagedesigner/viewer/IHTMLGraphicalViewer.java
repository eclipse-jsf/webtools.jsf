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
package org.eclipse.jst.pagedesigner.viewer;

import org.eclipse.draw2d.Viewport;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Caret;
import org.eclipse.ui.IEditorPart;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;

/**
 * @author mengbo
 */
public interface IHTMLGraphicalViewer extends GraphicalViewer 
{
    /**
     * Factory for IHTMLGraphicalViewers
     *
     */
    static class Factory
    {
        /**
         * @param part 
         * @return a new graphical viewer for part
         */
        public static IHTMLGraphicalViewer createGraphicalViewer(IEditorPart part)
        {
            return new HTMLGraphicalViewer(part);
        }
    }

	/**
	 * @return the dom model
	 */
	public IDOMModel getModel();

	/**
	 * ensure we are in range selection mode
	 */
	public void ensureRangeSelectionMode();

	/**
	 * 
	 */
	public void ensureObjectSelectionMode();

	/**
	 * @return true if in range mode
	 */
	public boolean isInRangeMode();

	/**
	 * @return the current selection range
	 */
	public DesignRange getRangeSelection();

	/**
	 * @param position
	 * @param position2
	 */
	public void setRange(DesignPosition position, DesignPosition position2);

	/**
	 * @param position
	 */
	public void setRangeEndPosition(DesignPosition position);

	/**
	 * @return the caret
	 */
	public Caret getCaret();

	/**
	 * indicate a batch of operations is began, and may result in selection
	 * change. This viewer will only fire a single selection changed event when
	 * this batch of operations finish.
	 */
	public void startSelectionChange();

	/**
	 * batch operation that change the selection finished.
	 * 
	 */
	public void selectionChanged();

	/**
	 * @return the view port
	 */
	public Viewport getViewport();
	
	/**
	 * @return the status line manager
	 */
	public IStatusLineManager getStatusLineManager();
	
	/**
	 * @param newSelection
	 */
	public void updateRangeSelection(ISelection newSelection);
	
	/**
	 * Update the horizontal position
	 */
	public void updateHorizontalPos();

	/**
	 * Clear the selection to null. When the model is modified, the selection is
     * invalid, so we need to clear the selection. We change the selection
     * directly, it won't need to fire selectionchange event to other part.
     */
	public void clearSelectionRange();

	/**
     * Adds listener both as a selection changed listener and as an
     * {@link IHTMLGraphicalViewerListener}.  Callers of this method
     * need not call addSelectionChangedListener.
     * @param listener
     */
    public void addHTMLViewerListener(IHTMLGraphicalViewerListener listener);
    
    /**
     * Removes listener both as a selection changed listener and as an
     * {@link IHTMLGraphicalViewerListener}.  Callers of this method
     * need not call removeSelectionChangedListener.
     * @param listener
     */
    public void removeHTMLViewerListener(IHTMLGraphicalViewerListener listener);
}
