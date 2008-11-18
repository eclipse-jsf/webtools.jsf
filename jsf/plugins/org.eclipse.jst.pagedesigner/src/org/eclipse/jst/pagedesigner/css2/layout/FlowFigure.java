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

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.PDPlugin;

/**
 * The base implementation for text flow figures. A flow figure is used to
 * render a document in which elements are laid out horizontally within a "line"
 * until that line is filled. Layout continues on the next line.
 * <p>
 * WARNING: This class is not intended to be subclassed by clients. Future
 * versions may contain additional abstract methods.
 * 
 * @author mengbo
 * @since 2.1
 */
public abstract class FlowFigure extends Figure {

	// static final boolean SHOW_BASELINE = true;

	/**
	 * Constructs a new FlowFigure.
	 */
	public FlowFigure() {
		// setLayoutManager(createDefaultFlowLayout());
	}

	/**
	 * If the child is a <code>FlowFigure</code>, its FlowContext is passed
	 * to it.
	 * 
	 * @see org.eclipse.draw2d.IFigure#add(IFigure, Object, int)
	 */
	public void add(IFigure child, Object constraint, int index) {
		super.add(child, constraint, index);
		if (child instanceof FlowFigure) {
			FlowFigure ff = (FlowFigure) child;
			if (getLayoutManager() instanceof FlowContext) {
				ff.setOriginalFlowContext((FlowContext) getLayoutManager());
			} else {
				PDPlugin.getLogger(this.getClass()).error("layout is not FlowContext", new Throwable("This exception is artificial so  we can get a stack trace")); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
	}

	/**
	 * Creates the default layout manager
	 * 
	 * @return The default layout
	 */
	// protected abstract FlowFigureLayout createDefaultFlowLayout();
	/**
	 * @see Figure#paintFigure(Graphics)
	 */
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		// g.drawRectangle(getBounds().getResized(-1,-1));
	}

	/**
	 * Called after validate has occurred. This is used to update the bounds of
	 * the FlowFigure to encompass its new flow boxed created during validate.
	 */
	public abstract void postValidate();

	/**
	 * FlowFigures override setBounds() to prevent translation of children.
	 * "bounds" is a derived property for FlowFigures, calculated from the
	 * fragments that make up the FlowFigure.
	 * 
	 * @see Figure#setBounds(Rectangle)
	 */
	public void setBounds(Rectangle r) {
		if (getBounds().equals(r))
			return;
		erase();
		bounds.x = r.x;
		bounds.y = r.y;
		bounds.width = r.width;
		bounds.height = r.height;
		fireFigureMoved();
		repaint();
	}

	/**
	 * Sets the flow context.
	 * 
	 * @param flowContext
	 *            the flow context for this flow figure
	 */
	public void setOriginalFlowContext(FlowContext flowContext) {
		((FlowFigureLayout) getLayoutManager())
				.setOriginalFlowContext(flowContext);
	}

	public String toString() {
		if (_displayString == null)
        {
			return super.toString();
        }
        return _displayString + " " + getClass().getName(); //$NON-NLS-1$
	}

	private String _displayString; // for debug

	/**
	 * @return the flow context
	 */
	public FlowContext getFlowContext() {
		return ((FlowFigureLayout) getLayoutManager()).getFlowContext();
	}

	// ----------------------------------------------------------------------
	// as absolute positioning and relative positioning may have children
	// out-side
	// of parent bounds, so we want to disable clipping when drawing figures
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Figure#paintChildren(org.eclipse.draw2d.Graphics)
	 */
	protected void paintChildren(Graphics graphics) {
		IFigure child;

		Rectangle clip = Rectangle.SINGLETON;
		List children = this.getChildren();
		for (int i = 0; i < children.size(); i++) {
			child = (IFigure) children.get(i);
			if (child.isVisible() && child.intersects(graphics.getClip(clip))) {
				// graphics.clipRect(child.getBounds());
				child.paint(graphics);
				graphics.restoreState();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Figure#paintClientArea(org.eclipse.draw2d.Graphics)
	 */
	protected void paintClientArea(Graphics graphics) {
		if (this.getChildren().isEmpty())
			return;

		// boolean optimizeClip = getBorder() == null || getBorder().isOpaque();

		if (useLocalCoordinates()) {
			graphics.translate(getBounds().x + getInsets().left, getBounds().y
					+ getInsets().top);
			// if (!optimizeClip)
			// graphics.clipRect(getClientArea(PRIVATE_RECT));
			graphics.pushState();
			paintChildren(graphics);
			graphics.popState();
			graphics.restoreState();
		} else {
			// if (optimizeClip)
			paintChildren(graphics);
			// else {
			// graphics.clipRect(getClientArea(PRIVATE_RECT));
			// graphics.pushState();
			// paintChildren(graphics);
			// graphics.popState();
			// graphics.restoreState();
			// }
		}

	}
}
