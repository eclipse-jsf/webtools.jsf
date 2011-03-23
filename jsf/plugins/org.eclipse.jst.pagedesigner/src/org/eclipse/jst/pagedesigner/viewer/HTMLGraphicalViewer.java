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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.Viewport;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.pagedesigner.commands.nav.CaretPositionTracker;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.DOMRefPosition;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.parts.DocumentEditPart;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.tools.ExposeHelper;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Caret;
import org.eclipse.ui.IEditorPart;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Node;

/**
 * For the GraphicalViewer selection management, we have two different selection
 * mode: Range mode and object mode.
 * 
 * Range mode is to support inline text editing, it selects a range. Object mode
 * selects a list of edit parts.
 * 
 * We let the super class of HTMLGraphicalViewer to handle object selection, and
 * add range selection support in this class. Need to override certain selection
 * related methods of super class to handle selection mode switching.
 * 
 * @author mengbo
 */
public class HTMLGraphicalViewer extends ScrollingGraphicalViewer implements
		IHTMLGraphicalViewer, CaretPositionTracker {
	private IEditorPart _parentPart;
	private Caret _caret;
	// initially nothing selected, treat as object selectin mode.
	private boolean _rangeMode = false;
	private DesignRange _selectionRange = null;
	private int _inBatch = 0;
	private final CaretUpdater _caretUpdater;
	private int _xOffset;
	private final List<IHTMLGraphicalViewerListener>  _htmlViewerListeners;
	// private ListenerList _postSelectionChangedListeners = new
	// ListenerList(1);

	/**
	 * @param parent 
	 * 
	 */
	public HTMLGraphicalViewer(IEditorPart parent) {
		_parentPart = parent;
		// CaretUpdater is not fully initialized yet, since this time the
		// viewport is not
		// initialized yet, and we need add listener to range model change.
        _htmlViewerListeners = new ArrayList<IHTMLGraphicalViewerListener>();
		_caretUpdater = new CaretUpdater(this);
	}

	/**
	 * Adds listener both as a selection changed listener and as an
	 * {@link IHTMLGraphicalViewerListener}.  Callers of this method
	 * need not call addSelectionChangedListener.
	 * @param listener
	 */
	public void addHTMLViewerListener(IHTMLGraphicalViewerListener listener)
	{
	    addSelectionChangedListener(listener);
	    
	    if (!_htmlViewerListeners.contains(listener))
	    {
	        _htmlViewerListeners.add(listener);
	    }
	}
	
	/**
	 * Removes listener both as a selection changed listener and as an
     * {@link IHTMLGraphicalViewerListener}.  Callers of this method
     * need not call removeSelectionChangedListener.
	 * @param listener
	 */
	public void removeHTMLViewerListener(IHTMLGraphicalViewerListener listener)
	{
	    removeSelectionChangedListener(listener);
	    _htmlViewerListeners.remove(listener);
	}

	public Viewport getViewport() {
		FigureCanvas canvas = this.getFigureCanvas();
		if (canvas != null) {
			return canvas.getViewport();
		}
        return null;
	}

	public IDOMModel getModel() {
		// XXX: temp implementation.
		EditPart part = this.getContents();
		if (part != null) {
			return ((IDOMNode) part.getModel()).getModel();
		}
        return null;
	}

	/**
	 * @return the status line manager
	 */
	public IStatusLineManager getStatusLineManager() {
		if (_parentPart == null) {
			return null;
		}
        return _parentPart.getEditorSite().getActionBars()
        		.getStatusLineManager();
	}

	public Caret getCaret() {
		if (_caret == null) {
			Canvas parentCanvas = (Canvas) getControl();
			if (parentCanvas == null || parentCanvas.isDisposed()) {
				return null;
			}

			_caret = new Caret(parentCanvas, 0);
			_caretUpdater.connectViewer();
		}
		return _caret;

	}

	/**
	 * this method normally should only be called when in object selection mode.
	 * 
	 * @return the edit part that has primary selection or null if none
	 */
	public EditPart getPrimarySelectedNode() {
		List list = this.getSelectedEditParts();
		if (list.isEmpty()) {
			return null;
		}
		for (int i = 0, n = list.size(); i < n; i++) {
			EditPart part = (EditPart) list.get(i);
			if (part.getSelected() == EditPart.SELECTED_PRIMARY) {
				return part;
			}
		}
		return (EditPart) list.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.parts.IHTMLGraphicalViewer#ensureRangeSelectionMode()
	 */
	public void ensureRangeSelectionMode() {
		if (!_rangeMode) {
			EditPart primary = getPrimarySelectedNode();
			this.deselectAll();
			DesignPosition begin = primary == null ? DesignPosition.INVALID
					: DesignPosition.createPositionBeforePart(primary);
			DesignPosition after = primary == null ? DesignPosition.INVALID
					: DesignPosition.createPositionAfterPart(primary);
			internalSetRange(begin, after);
			fireSelectionChanged();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.parts.IHTMLGraphicalViewer#ensureObjectSelectionMode()
	 */
	public void ensureObjectSelectionMode() {
		if (_rangeMode) {
			// switch to object selection mode with no selection.
			internalToObjectMode();
			fireSelectionChanged();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.parts.IHTMLGraphicalViewer#isInRangeMode()
	 */
	public boolean isInRangeMode() {
		return _rangeMode;
	}

	public ISelection getSelection() {
		if (isInRangeMode()) {
			return getRangeSelection();
		}
        return super.getSelection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.parts.IHTMLGraphicalViewer#startSelectionChange()
	 */
	public void startSelectionChange() {
		if (_inBatch == 0) {
			fireSelectionAboutToChange();
		}
		_inBatch++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.parts.IHTMLGraphicalViewer#selectionChanged()
	 */
	public void selectionChanged() {
		if (--_inBatch == 0) {
			fireSelectionChanged();
			fireSelectionChangeFinished();
		}
	}

	/**
	 * 
	 */
	private void fireSelectionAboutToChange() {
		IHTMLGraphicalViewerListener listeners[] = 
		    _htmlViewerListeners.toArray(new IHTMLGraphicalViewerListener[0]);

		for (int i = 0; i < listeners.length; i++) 
		{
			listeners[i].selectionAboutToChange();
		}
	}

	/**
	 * 
	 */
	private void fireSelectionChangeFinished()
	{
        IHTMLGraphicalViewerListener listeners[] = 
            _htmlViewerListeners.toArray(new IHTMLGraphicalViewerListener[0]);
        for (int i = 0; i < listeners.length; i++) 
        {
            listeners[i].selectionChangeFinished();
        }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.AbstractEditPartViewer#fireSelectionChanged()
	 */
	protected void fireSelectionChanged() {
		if (_inBatch == 0)// && this.getControl().isFocusControl())
		{
			super.fireSelectionChanged();
			// firePostSelectionChanged(new SelectionChangedEvent(this,
			// getSelection()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.AbstractEditPartViewer#setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(ISelection newSelection) {
		if (newSelection instanceof IStructuredSelection) {
			internalToObjectMode();
			ExposeHelper.expose(newSelection, this);
			updateRangeSelection(newSelection);
			super.setSelection(newSelection);
		} else if (newSelection instanceof DesignRange) {
			DesignRange range = (DesignRange) newSelection;
			internalSetRange(range.getStartPosition(), range.getEndPosition());
			fireSelectionChanged();
		}
		// else we don't support, ignore
	}


	/**
	 * @param newSelection
	 */
	public void updateRangeSelection(ISelection newSelection) {
		if (newSelection instanceof IStructuredSelection && //
				!(((IStructuredSelection) newSelection).getFirstElement() instanceof DocumentEditPart)) {
			Object element = ((IStructuredSelection) newSelection)
					.getFirstElement();
			if (element instanceof ElementEditPart) {
				updateRangeSelection(new DesignRefPosition((EditPart) element,
						false), new DesignRefPosition((EditPart) element, true));
			} else if (element instanceof Node) {
				IDOMPosition start = new DOMRefPosition((Node) element, false);
				IDOMPosition end = new DOMRefPosition((Node) element, true);
				updateRangeSelection(DOMPositionHelper.toDesignPosition(start),
						DOMPositionHelper.toDesignPosition(end));
			}
		}
	}

	/**
	 * This method is used to synchronize range mode selection when node
	 * selection is changed.
	 * 
	 * @param position
	 * @param position2
	 */
	private void updateRangeSelection(DesignPosition position,
			DesignPosition position2) {
		// if only one position is invalid, we will make a collapsed range using
		// the valid position
		if (position == null) {
			position = DesignPosition.INVALID;
		}
		if (position2 == null || !position2.isValid()) {
			position2 = position;
		}
		if (!position.isValid()) {
			position = position2;
		}

		_selectionRange = new DesignRange(position, position2);
	}

	// -------------------------------------------------------------------------------------------------
	// override super class methods for selection handling.
	// operations that handles object selection
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.AbstractEditPartViewer#appendSelection(org.eclipse.gef.EditPart)
	 */
	public void appendSelection(EditPart editpart) {
		internalToObjectMode();
		super.appendSelection(editpart); // super will fireSelectionChanged.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.AbstractEditPartViewer#deselectAll()
	 */
	public void deselectAll() {
		internalToObjectMode();
		super.deselectAll(); // super.deselectAll() will fireSelectionChanged
	}

	/**
	 * Clear the selection to null. When the model is modified, the selection is
	 * invalid, so we need to clear the selection. We change the selection
	 * directly, it won't need to fire selectionchange event to other part.
	 * 
	 */
	public void clearSelectionRange() {
		internalToObjectMode();
		_selectionRange = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ui.parts.AbstractEditPartViewer#deselect(org.eclipse.gef.EditPart)
	 */
	public void deselect(EditPart editpart) {
		if (!_rangeMode) {
			super.deselect(editpart); // super will fireSelectionChanged.
		}
		// else just ignore.
	}

	// ---------------------------------------------------------------------------------------------
	// range selection handling methods.
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.parts.IHTMLGraphicalViewer#getRangeSelection()
	 */
	public DesignRange getRangeSelection() {
		return _selectionRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.parts.IHTMLGraphicalViewer#setRange(org.eclipse.jst.pagedesigner.selection.EditPartPosition,
	 *      org.eclipse.jst.pagedesigner.selection.EditPartPosition)
	 */
	public void setRange(DesignPosition position, DesignPosition position2) {
		internalSetRange(position, position2);
		fireSelectionChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.parts.IHTMLGraphicalViewer#setRangeEndPosition(org.eclipse.jst.pagedesigner.selection.EditPartPosition)
	 */
	public void setRangeEndPosition(DesignPosition position) {
		DesignRange range = getRangeSelection();
		DesignPosition begin = null;
		if (range != null) {
			begin = range.getStartPosition();
		}
		internalSetRange(begin, position);
		fireSelectionChanged();
	}

	// --------------------------------------------------------------------------------------
	/**
	 * internall switch to object mode, no selection change event is fired. the
	 * caller must call some other methods that will result in selection change
	 * event after calling this method.
	 */
	private void internalToObjectMode() {
		_rangeMode = false;
	}

	/**
	 * this method will not fire selection changed event. caller should do that.
	 * 
	 * @param position
	 * @param position2
	 */
	private void internalSetRange(DesignPosition position,
			DesignPosition position2) {
		if (!_rangeMode) {
			// XXX: deselectAll() will result in fireSelectionChange, so here is
			// one unnecessary
			// event fire. But should be ok.
			deselectAll();
			_rangeMode = true;
		}
		// if only one position is invalid, we will make a collapsed range using
		// the valid position
		if (position == null) {
			position = DesignPosition.INVALID;
		}
		if (position2 == null || !position2.isValid()) {
			position2 = position;
		}
		if (!position.isValid()) {
			position = position2;
		}

		_selectionRange = new DesignRange(position, position2);
	}

	/**
	 * debug method, dump some debug information to the console
	 */
	public void dumpStatus() {
		if (isInRangeMode()) {
			// System.out.println("Range start: " +
			// this.getRangeSelection().getStartPosition());
			// System.out.println("Range end: " +
			// this.getRangeSelection().getEndPosition());
		} else {
			// System.out.println("Object selection mode");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.nav.CaretPositionTracker#getXoffset()
	 */
	public int getXoffset() {
		return _xOffset;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.nav.CaretPositionTracker#setXoffset(int)
	 */
	public void setXoffset(int xoffset) {
		this._xOffset = xoffset;
	}

	/**
	 * 
	 */
	public void updateHorizontalPos() {
		Caret caret = getCaret();
		if (caret != null && !caret.isDisposed() && isInRangeMode()) {
			org.eclipse.swt.graphics.Rectangle rect = caret.getBounds();
			setXoffset(rect.x);
		}
	}

	/**
	 * Gets parent editor part.
	 * @return Parent editor part.
	 */
	public IEditorPart getParent() {
		return _parentPart;
	}

	// public void addPostSelectionChangedListener(ISelectionChangedListener
	// listener)
	// {
	// _postSelectionChangedListeners.add(listener);
	//
	// }
	//
	// public void removePostSelectionChangedListener(ISelectionChangedListener
	// listener)
	// {
	// _postSelectionChangedListeners.remove(listener);
	// }

	/**
	 * Notifies any post selection listeners that a post selection event has
	 * been received. Only listeners registered at the time this method is
	 * called are notified.
	 * 
	 * @param event
	 *            a selection changed event
	 * 
	 * @see #addPostSelectionChangedListener(ISelectionChangedListener)
	 */
	// public void firePostSelectionChanged(final SelectionChangedEvent event)
	// {
	// Object[] listeners = _postSelectionChangedListeners.getListeners();
	// for (int i = 0; i < listeners.length; ++i)
	// {
	// final ISelectionChangedListener l = (ISelectionChangedListener)
	// listeners[i];
	// SafeRunnable.run(new SafeRunnable()
	// {
	// public void run()
	// {
	// l.selectionChanged(event);
	// }
	// });
	// }
	// }
}
