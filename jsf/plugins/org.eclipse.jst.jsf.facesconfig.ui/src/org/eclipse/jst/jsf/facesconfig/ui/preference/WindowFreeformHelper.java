/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.preference;

import java.util.List;

import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.FreeformListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Stolen from org.eclipse.draw2d.FreeformHelper. See also FreeformLayeredPane.
 * 
 * @author bbrodt
 */
/*package*/ class WindowFreeformHelper implements FreeformListener {
	FreeformLayeredPane x;

	class ChildTracker implements FigureListener {
		public void figureMoved(IFigure source) {
			invalidate();
		}
	}

	private FreeformFigure host;

	private Rectangle freeformExtent;

	private FigureListener figureListener = new ChildTracker();

	/**
	 * @param host
	 */
	public WindowFreeformHelper(FreeformFigure host) {
		this.host = host;
	}

	/**
	 * @return the freeform extent
	 */
	public Rectangle getFreeformExtent() {
		if (freeformExtent != null)
			return freeformExtent;
		Rectangle r;
		List children = host.getChildren();
		for (int i = 0; i < children.size(); i++) {
			IFigure child = (IFigure) children.get(i);
			if (child instanceof FreeformFigure)
				r = ((FreeformFigure) child).getFreeformExtent();
			else
				r = child.getBounds();
			if (freeformExtent == null)
				freeformExtent = r.getCopy();
			else
				freeformExtent.union(r);
		}
		Insets insets = host.getInsets();
		if (freeformExtent == null)
			freeformExtent = new Rectangle(0, 0, insets.getWidth(), insets
					.getHeight());
		else {
			host.translateToParent(freeformExtent);
			freeformExtent.expand(insets);
		}
		// System.out.println("New extent calculated for " + host + " = " +
		// freeformExtent);
		return freeformExtent;
	}

	/**
	 * @param child
	 */
	public void hookChild(IFigure child) {
		invalidate();
		if (child instanceof FreeformFigure)
			((FreeformFigure) child).addFreeformListener(this);
		else
			child.addFigureListener(figureListener);
	}

	void invalidate() {
		freeformExtent = null;
		host.fireExtentChanged();
		if (host.getParent() != null)
			host.getParent().revalidate();
		else
			host.revalidate();
	}

	public void notifyFreeformExtentChanged() {
		// A childs freeform extent has changed, therefore this extent must be
		// recalculated
		invalidate();
	}

	/**
	 * @param bounds
	 */
	public void setFreeformBounds(Rectangle bounds) {
		host.setBounds(bounds);
		bounds = bounds.getCopy();
		host.translateFromParent(bounds);
		List children = host.getChildren();
		for (int i = 0; i < children.size(); i++) {
			IFigure child = (IFigure) children.get(i);
			if (child instanceof FreeformFigure)
				((FreeformFigure) child).setFreeformBounds(bounds);
		}
		// CR420954: Compensation Handler canvas goes blank after Variables
		// Sections is expanded
		host.getLayoutManager().layout(host);
	}

	/**
	 * @param child
	 */
	public void unhookChild(IFigure child) {
		invalidate();
		if (child instanceof FreeformFigure)
			((FreeformFigure) child).removeFreeformListener(this);
		else
			child.removeFigureListener(figureListener);
	}

}