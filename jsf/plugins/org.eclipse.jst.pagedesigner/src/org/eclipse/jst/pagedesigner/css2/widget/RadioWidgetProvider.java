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
package org.eclipse.jst.pagedesigner.css2.widget;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.provider.DimensionInfo;

/**
 * @author mengbo
 * @version 1.5
 */
public class RadioWidgetProvider extends AbstractWidgetProvider {
	private boolean isChecked;

	/**
	 * @param style
	 */
	public RadioWidgetProvider(ICSSStyle style) {
		super(style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider#getPreferredDimension(int,
	 *      int)
	 */
	public DimensionInfo getPreferredDimension(int width, int height) {
		if (width <= 0) {
			width = getDefaultWidth();
		}
		if (height <= 0) {
			height = getDefaultWidth();
		}
		return new DimensionInfo(width, height, height * 4 / 5);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider#paintFigure(org.eclipse.draw2d.Graphics,
	 *      org.eclipse.draw2d.geometry.Rectangle)
	 */
	public void paintFigure(Graphics g, Rectangle rect) {
		int centerX = rect.x + rect.width / 2;
		int centerY = rect.y + rect.height / 2;

		// we always draw it as a circle.
		int width = Math.min(rect.width, rect.height);
		int cycleWidth = width * 3 / 5;

		// FIXME: when the size of the radio is big, the line width may need
		// calculate to be bigger.
		int lineWidth = 2;
		g.setLineWidth(lineWidth);

		g.setForegroundColor(ColorConstants.buttonDarker);
		g.drawArc(centerX - cycleWidth / 2, centerY - cycleWidth + 1,
				cycleWidth, cycleWidth, 45, 180);

		g.setForegroundColor(ColorConstants.button);
		g.drawArc(centerX - cycleWidth / 2, centerY - cycleWidth + 1,
				cycleWidth, cycleWidth, 225, 180);

		// since the two cycle may not overlap very well, some space between
		// them will be displayed.
		// so we make the bigger cycle to be a little thicker.
		cycleWidth += (2 * lineWidth - 2);
		lineWidth += 1;

		if (cycleWidth < width) {
			g.setForegroundColor(ColorConstants.buttonDarkest);
			g.drawArc(centerX - cycleWidth / 2, centerY - cycleWidth + 1,
					cycleWidth, cycleWidth, 45, 180);
		}

		if (isChecked()) {
			g.setBackgroundColor(ColorConstants.black);
			g.fillArc(centerX - 1, centerY - cycleWidth / 2 - 1, 4, 4, 0, 360);
		}
	}

	/**
	 * @return the default width
	 */
	public final int getDefaultWidth() {
		// FIXME: don't know the how to define the default width yet.
		return 15;
	}

	/**
	 * @return Returns the checked.
	 */
	public boolean isChecked() {
		return isChecked;
	}

	/**
	 * @param checked
	 */
	public void setChecked(boolean checked) {
		this.isChecked = checked;
	}

}
