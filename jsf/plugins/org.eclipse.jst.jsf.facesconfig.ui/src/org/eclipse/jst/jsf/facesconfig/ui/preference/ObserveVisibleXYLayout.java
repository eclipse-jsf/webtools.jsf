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

import java.util.Iterator;
import java.util.ListIterator;

import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author Bob Brodt
 * 
 * This is an XY layout that actually observes visiblity of children figures
 * when doint its layout.
 * 
 * Apparently there is a bug in the GEF XYLayout class because it does not
 * observe visibility.
 * 
 * @see org.eclipse.draw2d.AbstractLayout#isObservingVisibility()
 */
/*package*/ class ObserveVisibleXYLayout extends FreeformLayout {
	protected Dimension calculatePreferredSize(IFigure f, int wHint, int hHint) {
		Rectangle rect = new Rectangle();
		ListIterator children = f.getChildren().listIterator();
		while (children.hasNext()) {
			IFigure child = (IFigure) children.next();
			// bug fix
			if (isObservingVisibility() && !child.isVisible())
				continue;
			Rectangle r = (Rectangle) constraints.get(child);
			if (r == null)
				continue;

			if (r.width == -1 || r.height == -1) {
				Dimension preferredSize_ = child.getPreferredSize(r.width,
						r.height);
				r = r.getCopy();
				if (r.width == -1)
					r.width = preferredSize_.width;
				if (r.height == -1)
					r.height = preferredSize_.height;
			}
			rect.union(r);
		}
		Dimension d = rect.getSize();
		Insets insets = f.getInsets();
		return new Dimension(d.width + insets.getWidth(), d.height
				+ insets.getHeight()).union(getBorderPreferredSize(f));
	}

	public void layout(IFigure parent) {
		Iterator children = parent.getChildren().iterator();
		Point offset = getOrigin(parent);
		IFigure f;
		while (children.hasNext()) {
			f = (IFigure) children.next();
			// bug fix
			if (isObservingVisibility() && !f.isVisible())
				continue;
			Rectangle bounds = (Rectangle) getConstraint(f);
			if (bounds == null)
				continue;

			if (bounds.width == -1 || bounds.height == -1) {
				Dimension preferredSize_ = f.getPreferredSize(bounds.width,
						bounds.height);
				bounds = bounds.getCopy();
				if (bounds.width == -1)
					bounds.width = preferredSize_.width;
				if (bounds.height == -1)
					bounds.height = preferredSize_.height;
			}
			bounds = bounds.getTranslated(offset);
			f.setBounds(bounds);
		}
	}
}