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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RangeModel;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.tools.ExposeHelper;
import org.eclipse.swt.widgets.Caret;

/**
 * This class is responsible for update the caret location. At least the
 * following changes may result in caret location change. <ll>
 * <li>The selection mode change. For example, from/to text mode to/from object
 * mode, we need to hide/display the caret
 * <li>The caret location change in the model.
 * <li>the figures moved. This may result in model change in somewhere else, or
 * user resized the page designer.
 * <li>The figure that containing the caret get recreated. </ll>
 * 
 * @author mengbo
 */
/*package*/ class CaretUpdater implements IHTMLGraphicalViewerListener,
		FigureListener {
//	private static final Logger _log = PDPlugin.getLogger(CaretUpdater.class);

	private IHTMLGraphicalViewer _viewer;

	private boolean _viewerBatchChanging = false;

	/**
	 * the width value of the caret in pixels
	 */
	public static final int CARET_WIDTH = 2;

	/**
	 * the figure the caret associate to, we need to track this figure's
	 * resizing, location change, etc.
	 */
	private IFigure _trackFigure;

	/**
	 * @param viewer
	 */
	public CaretUpdater(IHTMLGraphicalViewer viewer) {
		_viewer = viewer;
		setup();
	}

	/**
	 * set up the
	 */
	public void setup() {
		_viewer.addHTMLViewerListener(this);
	}

	/**
	 * this method is called after the view is fully initialized.
	 */
	public void connectViewer() {
		Viewport viewport = _viewer.getViewport();
		if (viewport != null) {
			viewport.getHorizontalRangeModel().addPropertyChangeListener(
					new PropertyChangeListener() {
						public void propertyChange(
								PropertyChangeEvent propertychangeevent) {
							if ((propertychangeevent.getSource() instanceof RangeModel)
									&& (propertychangeevent.getPropertyName()
											.equals(ICSSPropertyID.ATTR_VALUE) || propertychangeevent
											.getPropertyName().equals("extent"))) //$NON-NLS-1$
								updateCaret();
						}

					});
			viewport.getVerticalRangeModel().addPropertyChangeListener(
					new PropertyChangeListener() {

						public void propertyChange(
								PropertyChangeEvent propertychangeevent) {
							if ((propertychangeevent.getSource() instanceof RangeModel)
									&& (propertychangeevent.getPropertyName()
											.equals(ICSSPropertyID.ATTR_VALUE) || propertychangeevent
											.getPropertyName().equals("extent"))) //$NON-NLS-1$
								updateCaret();
						}

					});
		}
	}

	/**
	 * dispose the instance
	 */
	public void dispose() {
		_viewer.removeHTMLViewerListener(this);
	}

	/**
	 * Update the selection
	 */
	public void updateSelection() {
		setCaretVisible(false);
		updateRangeSelection();
		updateCaret();
		reveal();
	}

	private void setCaretVisible(boolean visible) {
		Caret caret = _viewer.getCaret();
		if (caret == null)
			return;
		if (caret.isDisposed()) {
			return;
		}
		caret.setVisible(visible);
	}

	/**
	 * 
	 */
	private void updateRangeSelection() {
		// FIXME: optimization needed here. Normally should not repaint the
		// whole page.
		((GraphicalEditPart) _viewer.getRootEditPart()).getFigure().repaint();
		((GraphicalEditPart) _viewer.getRootEditPart()).getFigure()
				.getUpdateManager().performUpdate();
	}

	/**
	 * update the caret
	 */
	public void updateCaret() {
		if (_trackFigure != null) {
			_trackFigure.removeFigureListener(this);
			_trackFigure = null;
		}
		Caret caret = _viewer.getCaret();
		if (caret == null) {
			return;
		}
		if (caret.isDisposed()) {
			return;
		}

		Rectangle rect = null;

		// try get the caret bounds.
		if (_viewer.isInRangeMode()) {
			DesignRange range = _viewer.getRangeSelection();
			if (range != null) {
				DesignPosition endPosition = range.getEndPosition();
				if (endPosition != null && endPosition.isValid()) {
					rect = EditPartPositionHelper
							.convertToAbsoluteCaretRect(endPosition);
					_trackFigure = ((GraphicalEditPart) endPosition
							.getContainerPart()).getFigure();
					_trackFigure.addFigureListener(this);
				}
			}
		}

		// set visible effect
		if (rect == null) {
			caret.setVisible(false);
		} else {
			caret.setVisible(false); // make sure it get removed from the
			// screen.
			// the caret width doesn't need to be calculated, the x pos should
			// be adjusted more acurately.
			caret.setBounds(rect.x, rect.y, CARET_WIDTH, rect.height);
			caret.setVisible(true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		if (_viewerBatchChanging) {
			return;
		}
		updateSelection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewerListener#selectionAboutToChange()
	 */
	public void selectionAboutToChange() {
		_viewerBatchChanging = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewerListener#selectionChangeFinished()
	 */
	public void selectionChangeFinished() {
		_viewerBatchChanging = false;
		updateSelection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.FigureListener#figureMoved(org.eclipse.draw2d.IFigure)
	 */
	public void figureMoved(IFigure source) {
		updateCaret();
	}

	private void reveal() {
		Caret caret = _viewer.getCaret();
		if (caret != null && !caret.isDisposed() && _viewer.isInRangeMode()) {
			org.eclipse.swt.graphics.Rectangle rect = caret.getBounds();
			ExposeHelper helper = new ExposeHelper(_viewer);
			helper.exposeArea(new Rectangle(rect.x, rect.y, rect.width,
					rect.height));
		}
	}
}
