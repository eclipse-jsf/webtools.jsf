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
package org.eclipse.jst.pagedesigner.tools;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.w3c.dom.Node;

/**
 * This helper class expose an rectangle in design view, currentlly it is used
 * to help expose caret.
 * 
 * @author mengbo
 */
public class ExposeHelper {
	private static final int DEFAULT_OFFSET = 100;

	private static final int SCROLL_OFFSET = 8;

	IHTMLGraphicalViewer _viewer;

	/**
	 * @param viewer 
	 */
	public ExposeHelper(IHTMLGraphicalViewer viewer) {
		_viewer = viewer;
	}

	/**
	 * Expose rectangle. (non-Javadoc)
	 * @param rect 
	 * 
	 * @see org.eclipse.gef.ExposeHelper#exposeDescendant(org.eclipse.gef.EditPart)
	 */
	public void exposeArea(Rectangle rect) {
		if (_viewer == null) {
			return;
		}
		FigureCanvas canvas = (FigureCanvas) _viewer.getControl();
		Viewport port = _viewer.getViewport();

		if (port == null) {
			return;
		}
		Rectangle exposeRegion = rect.getCopy();
		Rectangle portBounds = port.getBounds().getCopy();
		Point viewLocation = port.getViewLocation();
		Dimension diff = calculateDiff(portBounds, exposeRegion);
		if (diff != null) {
			viewLocation.x -= diff.width;
			viewLocation.y -= diff.height;
			canvas.scrollSmoothTo(viewLocation.x, viewLocation.y);
		}
	}

	private void exposeVertical(int offset) {
		if (_viewer == null) {
			return;
		}
		FigureCanvas canvas = (FigureCanvas) _viewer.getControl();
		Viewport port = _viewer.getViewport();

		if (port == null) {
			return;
		}
		Point viewLocation = port.getViewLocation();
		viewLocation.y += offset;
		canvas.scrollSmoothTo(viewLocation.x, viewLocation.y);
	}

	private void exposeHorizontal(int offset) {
		if (_viewer == null) {
			return;
		}
		FigureCanvas canvas = (FigureCanvas) _viewer.getControl();
		Viewport port = _viewer.getViewport();

		if (port == null) {
			return;
		}
		Point viewLocation = port.getViewLocation();
		viewLocation.x += offset;
		canvas.scrollSmoothTo(viewLocation.x, viewLocation.y);
	}

	private int calculateX(Rectangle portBounds, Rectangle caretRect) {
		int result = 0;
		if (portBounds.x > caretRect.getRight().x) {
			result = portBounds.getLeft().getDifference(caretRect.getRight()).width;
			if (portBounds.width >= caretRect.width) {
				result = portBounds.getLeft()
						.getDifference(caretRect.getLeft()).width;
			}
		} else if (portBounds.getRight().x < caretRect.getLeft().x) {
			result = portBounds.getRight().getDifference(caretRect.getLeft()).width;
			if (portBounds.width >= caretRect.width) {
				result = portBounds.getRight().getDifference(
						caretRect.getRight()).width;
			}
		}
		return result;
	}

	private int calculateY(Rectangle portBounds, Rectangle caretRect) {
		int result = 0;
		if (portBounds.y > caretRect.getBottom().y) {
			result = portBounds.getTop().getDifference(caretRect.getBottom()).height;
			if (portBounds.height >= caretRect.height) {
				result = portBounds.getTop().getDifference(caretRect.getTop()).height;
			}
		} else if (portBounds.getBottom().y < caretRect.getTop().y) {
			result = portBounds.getBottom().getDifference(caretRect.getTop()).height;
			if (portBounds.height >= caretRect.height) {
				result = portBounds.getBottom().getDifference(
						caretRect.getBottom()).height;
			}
		} else if (portBounds.getBottom().y < caretRect.getBottom().y) {
			if (portBounds.height >= caretRect.height) {
				result = portBounds.getBottom().getDifference(
						caretRect.getBottom()).height;
			}
		} else if (portBounds.getTop().y > caretRect.getTop().y) {
			if (portBounds.height >= caretRect.height) {
				result = portBounds.getTop().getDifference(caretRect.getTop()).height;
			}
		}
		return result;
	}

	/**
	 * Calculate caretPoint's offset to posrBounds at both x coordinate and y
	 * coordinate.
	 * 
	 * @param portBounds
	 * @param exposeRegion
	 * @param canvas
	 * @param caretPoint
	 */
	private Dimension calculateDiff(Rectangle portBounds, Rectangle caretRect) {
		Dimension diff = new Dimension(0, 0);
		diff.width = calculateX(portBounds, caretRect);
		diff.height = calculateY(portBounds, caretRect);
		return diff;
	}

	// /**
	// * Calculate caretPoint's offset to posrBounds at both x coordinate and y
	// coordinate.
	// *
	// * @param portBounds
	// * @param exposeRegion
	// * @param canvas
	// * @param caretPoint
	// */
	// private Dimension calculateDiff(Rectangle portBounds, Point caretPoint)
	// {
	// int position = portBounds.getPosition(caretPoint);
	// Dimension diff = null;
	// Point containerPos = null;
	// switch (position)
	// {
	// case PositionConstants.EAST:
	// containerPos = new Point(portBounds.getRight().x, caretPoint.y);
	// diff = containerPos.getDifference(caretPoint);
	// break;
	// case PositionConstants.NORTH_EAST:
	// diff = portBounds.getTopRight().getDifference(caretPoint);
	// break;
	// case PositionConstants.WEST:
	// containerPos = new Point(portBounds.getLeft().x, caretPoint.y);
	// diff = containerPos.getDifference(caretPoint);
	// break;
	// case PositionConstants.NORTH_WEST:
	// diff = portBounds.getTopLeft().getDifference(caretPoint);
	// break;
	// case PositionConstants.SOUTH_WEST:
	// diff = portBounds.getBottomLeft().getDifference(caretPoint);
	// break;
	// case PositionConstants.SOUTH_EAST:
	// diff = portBounds.getBottomRight().getDifference(caretPoint);
	// break;
	// case PositionConstants.NORTH:
	// containerPos = new Point(caretPoint.x, portBounds.getTop().y);
	// diff = containerPos.getDifference(caretPoint);
	// break;
	// case PositionConstants.SOUTH:
	// containerPos = new Point(caretPoint.x, portBounds.getBottom().y);
	// diff = containerPos.getDifference(caretPoint);
	// break;
	// }
	// return diff;
	// }
	//
	private static void expose(EditPart part, ScrollingGraphicalViewer viewer) {
		if (part != null && part.getModel() instanceof Node) {
			viewer.reveal(part);
		}
	}

	private static void expose(Node element, ScrollingGraphicalViewer viewer) {
		if (element instanceof INodeNotifier) {
			EditPart editPart = (EditPart) ((INodeNotifier) element)
					.getAdapterFor(EditPart.class);
			expose(editPart, viewer);
		}
	}

	/**
	 * @param selection
	 * @param viewer
	 */
	public static void expose(ISelection selection,
			ScrollingGraphicalViewer viewer) {
		if (selection instanceof IStructuredSelection) {
			Object element = ((IStructuredSelection) selection)
					.getFirstElement();
			if (element instanceof Node) {
				expose((Node) element, viewer);
			} else if (element instanceof EditPart) {
				expose((EditPart) element, viewer);
			}
		}
	}

	/**
	 * @param p
	 */
	public void adjustVertical(Point p) {
		int offset = 0;
		if ((offset = getVerticalBoundsOffset(p, false)) < SCROLL_OFFSET) {
			exposeVertical(SCROLL_OFFSET - offset);
		} else if ((offset = getVerticalBoundsOffset(p, true)) < SCROLL_OFFSET) {
			exposeVertical(offset - SCROLL_OFFSET);
		}
		if ((offset = getHorizontalBoundsOffset(p, true)) < SCROLL_OFFSET) {
			exposeHorizontal(SCROLL_OFFSET - offset);
		} else if ((offset = getHorizontalBoundsOffset(p, false)) < SCROLL_OFFSET) {
			exposeHorizontal(offset - SCROLL_OFFSET);
		}
	}

	/**
	 * @return the location of the view port
	 */
	public Point getViewpostLocation() {
		if (_viewer != null) {
			Viewport port = _viewer.getViewport();

			if (port != null) {
				return port.getViewLocation();
			}
		}
		return null;
	}

	/**
	 * @param figure
	 * @param p
	 * @return the translated point
	 */
	public Point translateToViewport(IFigure figure, Point p) {
		Point vp = getViewpostLocation();
		return new Point(p.x - vp.x, p.y - vp.y);
	}

	private int getHorizontalBoundsOffset(Point p, boolean forward) {
		if (_viewer == null) {
			return DEFAULT_OFFSET;
		}
		Viewport port = _viewer.getViewport();

		if (port == null) {
			return DEFAULT_OFFSET;
		}
		if (forward) {
			Rectangle portBounds = port.getBounds().getCopy();
			return portBounds.getRight().x - p.x;
		}
        return p.x;
	}

	private int getVerticalBoundsOffset(Point p, boolean up) {
		if (_viewer == null) {
			return DEFAULT_OFFSET;
		}
		Viewport port = _viewer.getViewport();

		if (port == null) {
			return DEFAULT_OFFSET;
		}
		if (!up) {
			Rectangle portBounds = port.getBounds().getCopy();
			return portBounds.getBottom().y - p.y;
		}
        return p.y;
	}

	/**
	 * @param rect
	 * @param policy
	 */
	void exposeBorder(Rectangle rect, AbstractEditPolicy policy) {
		Point p = rect.getTopLeft();
		p = translateToViewport(((GraphicalEditPart) policy.getHost())
				.getFigure(), p);
		adjustVertical(p);
		p = rect.getBottomRight();
		p = translateToViewport(((GraphicalEditPart) policy.getHost())
				.getFigure(), p);
		adjustVertical(p);
	}
}
