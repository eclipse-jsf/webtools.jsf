/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.preference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.LayerConstants;

/**
 * This is a printable layer that draws page outline tiles enclosing all of the
 * printable child figures. The page outlines represent complete pages as they
 * would appear if they were printed on dead trees, thus the pageSize set on
 * this layer should be the currently selected printer's page resolution.
 * 
 * The reason this needs to be added to the printable layers is so that it will
 * show up in the content outline viewer.
 */
public class PrintedPageLayer extends FreeformLayer {
	// our layer name
	public static final String PRINTED_PAGE_LAYER = "Printed Page";

	// the size of the page tiles (this should be the printer's resolution)
	protected Dimension pageSize = new Dimension(850, 1100);

	// the WindowFigure that owns this layer
	private final WindowFigure host;

	// flag to lock out multiple calls to setFreeformBounds() when adding
	// and removing PrintedPageFigures.
	private boolean busy;

	// A simple page outline figure. We can get fancy later with turned-up
	// dogears, page numbers, titles and whatnot...
	class PrintedPageFigure extends RectangleFigure {
		PrintedPageFigure() {
			super();
			setFill(false);
			setOutline(true);
			setLineWidth(1);
		}
	}

	public PrintedPageLayer(WindowFigure figure) {
		super();
		this.host = figure;
	}

	protected PrintedPageFigure createPage(int x, int y) {
		PrintedPageFigure page = new PrintedPageFigure();
		page.setBounds(new Rectangle(x, y, pageSize.width, pageSize.height));
		return page;
	}

	/**
	 * Sets the page width and height. This should be the currently selected
	 * printer's page size.
	 * 
	 * @param d
	 */
	public void setPageSize(Dimension d) {
		pageSize = d.getCopy();
	}

	/**
	 * @see org.eclipse.draw2d.FreeformFigure#setFreeformBounds(Rectangle)
	 */
	public void setFreeformBounds(Rectangle bounds) {
		if (!busy && getParent() instanceof FreeformLayeredPane
				&& pageSize.width > 0 && pageSize.height > 0) {
			busy = true;

			// build the new printing layer extent by looking at only the
			// contents of the Primary, Connection and Feedback layers
			Rectangle b;
			FreeformLayer layer;
			layer = (FreeformLayer) host.getLayer(LayerConstants.PRIMARY_LAYER);
			b = layer.getFreeformExtent().getCopy();
			layer = (FreeformLayer) host
					.getLayer(LayerConstants.CONNECTION_LAYER);
			b = b.union(layer.getFreeformExtent());
			layer = (FreeformLayer) host
					.getLayer(LayerConstants.FEEDBACK_LAYER);
			b = b.union(layer.getFreeformExtent());

			// calculate x and y for the page tiles
			int x = 0, y = 0;
			if (b.x < 0) {
				while (x > b.x)
					x -= pageSize.width;
			} else {
				while (x + pageSize.width < b.x)
					x += pageSize.width;
			}
			if (b.y < 0) {
				while (y > b.y)
					y -= pageSize.height;
			} else {
				while (y + pageSize.height < b.y)
					y += pageSize.height;
			}

			// get list of pagefigures that intersect the new extent
			// and a list of those that don't
			List keepers = new ArrayList();
			List trash = new ArrayList();
			Iterator iter = getChildren().iterator();
			while (iter.hasNext()) {
				Figure child = (Figure) iter.next();
				if (child.getBounds().intersects(b))
					keepers.add(child);
				else
					trash.add(child);
			}

			// calculate width and height
			int w = 0, h = 0;
			while (x + w < b.x + b.width)
				w += pageSize.width;
			while (y + h < b.y + b.height)
				h += pageSize.height;

			// determine the required pagefigures
			for (int xi = x; xi < x + w; xi += pageSize.width) {
				for (int yi = y; yi < y + h; yi += pageSize.height) {
					boolean found = false;
					iter = keepers.iterator();
					while (iter.hasNext()) {
						Figure child = (Figure) iter.next();
						Point p = child.getBounds().getLocation();
						if (p.x == xi && p.y == yi) {
							found = true;
							break;
						}
					}
					if (!found) {
						// add the new page
						add(createPage(xi, yi));
					}
				}
			}

			// remove all the old pages
			iter = trash.iterator();
			while (iter.hasNext())
				remove((Figure) iter.next());

			busy = false;
		}
		super.setFreeformBounds(bounds);
	}
}