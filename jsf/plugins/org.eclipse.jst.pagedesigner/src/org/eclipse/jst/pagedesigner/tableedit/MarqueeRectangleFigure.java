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
package org.eclipse.jst.pagedesigner.tableedit;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.widgets.Display;

/**
 * This class is copied from MarqueeSelectionTool, since it is private there.
 * 
 * @author mengbo
 * @version 1.5
 */
class MarqueeRectangleFigure extends Figure {

	private int offset = 0;

	private boolean schedulePaint = true;

	private static final int DELAY = 110; // animation delay in millisecond

	/**
	 * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	protected void paintFigure(Graphics graphics) {
		Rectangle bounds1 = getBounds().getCopy();
		graphics.translate(getLocation());

		graphics.setXORMode(true);
		graphics.setForegroundColor(ColorConstants.white);
		graphics.setBackgroundColor(ColorConstants.black);

		graphics.setLineStyle(Graphics.LINE_DOT);

		int[] points = new int[6];

		points[0] = 0 + offset;
		points[1] = 0;
		points[2] = bounds1.width - 1;
		points[3] = 0;
		points[4] = bounds1.width - 1;
		points[5] = bounds1.height - 1;

		graphics.drawPolyline(points);

		points[0] = 0;
		points[1] = 0 + offset;
		points[2] = 0;
		points[3] = bounds1.height - 1;
		points[4] = bounds1.width - 1;
		points[5] = bounds1.height - 1;

		graphics.drawPolyline(points);

		graphics.translate(getLocation().getNegated());

		if (schedulePaint) {
			Display.getCurrent().timerExec(DELAY, new Runnable() {
				public void run() {
					offset++;
					if (offset > 5)
						offset = 0;

					schedulePaint = true;
					repaint();
				}
			});
		}

		schedulePaint = false;
	}

}
