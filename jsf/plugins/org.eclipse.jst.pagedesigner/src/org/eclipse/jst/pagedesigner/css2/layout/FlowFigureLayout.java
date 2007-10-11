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
package org.eclipse.jst.pagedesigner.css2.layout;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * Layout for flow figures
 *
 */
public abstract class FlowFigureLayout extends AbstractLayout {

	/**
	 * <code>true</code> if the context has changed, and a layout is needed.
	 */
	protected boolean _invalid = true;

	/**
	 * The flow context in which this LayoutManager exists.
	 */
	private FlowContext _context;

	/**
	 * The figure passed by layout(Figure) is held for convenience.
	 */
	private final FlowFigure _flowFigure;

	/**
	 * Constructs a new FlowFigureLayout with the given FlowFigure.
	 * 
	 * @param flowfigure
	 *            the FlowFigure
	 */
	protected FlowFigureLayout(FlowFigure flowfigure) {
		this._flowFigure = flowfigure;
	}

	/**
	 * TextFlowLayouts do not calculate a preferred size because it is too
	 * expensive. {@link FlowPage}will actually layout itself in order to
	 * calculate preferredSize.
	 * 
	 * @see AbstractLayout#calculatePreferredSize(IFigure)
	 */
	public Dimension calculatePreferredSize(IFigure f, int w, int h) {
		return null;
	}

	/**
	 * @return the FlowFigure
	 */
	protected FlowFigure getFlowFigure() {
		return _flowFigure;
	}

	/**
	 * Marks this layout as invalid.
	 * 
	 * @see org.eclipse.draw2d.LayoutManager#invalidate()
	 */
	public void invalidate() {
		_invalid = true;
		super.invalidate();
	}

	/**
	 * @see org.eclipse.draw2d.LayoutManager#layout(IFigure)
	 */
	public final void layout(IFigure figure) {
		layout();
		_invalid = false;
	}

	/**
	 * Called during {@link #layout(IFigure)}. The {@link  #_invalid}flag is
	 * reset after this method is called.
	 */
	protected abstract void layout();

	/**
	 * Sets the context for this layout manager.
	 * 
	 * @param flowContext
	 *            the context of this layout
	 */
	public void setOriginalFlowContext(FlowContext flowContext) {
		_context = flowContext;
	}

	/**
	 * @return the original flow context
	 */
	protected final FlowContext getOriginalFlowContext() {
		return _context;
	}

	/**
	 * get flow context.
	 * 
	 * @return the flow context
	 */
	public FlowContext getFlowContext() {
		return _context;
	}

	public String toString() {
		// for debug purpose.
		return _flowFigure.toString();
	}

	/**
	 * Called to dispose the layout
	 */
	abstract public void dispose();
}
