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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.provider.DimensionInfo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

/**
 * @author mengbo
 * @version 1.5
 */
public class CheckboxWidgetProvider extends AbstractWidgetProvider {
	private boolean isChecked;

	/**
	 * @param style
	 */
	public CheckboxWidgetProvider(ICSSStyle style) {
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
		return new DimensionInfo(new Dimension(width, height), height * 4 / 5);
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

		// we always draw it as a square.
		int width = Math.min(rect.width, rect.height);
		int innerWidth = width * 4 / 5;

		int left = centerX - innerWidth / 2;
		int top = centerY - innerWidth / 2;
		BorderUtil.drawBorder(g, left, top, innerWidth, innerWidth, 2, true);
		if (isChecked()) {
			g.pushState();
			Display display = Display.getCurrent();
			g.setForegroundColor(display.getSystemColor(SWT.COLOR_BLACK));
			g.drawLine(left + 3, top + 2, left + innerWidth - 3, top
					+ innerWidth - 4);
			g.drawLine(left + 2, top + 2, left + innerWidth - 3, top
					+ innerWidth - 3);

			g.drawLine(left + innerWidth - 4, top + 2, left + 2, top
					+ innerWidth - 4);
			g.drawLine(left + innerWidth - 3, top + 2, left + 2, top
					+ innerWidth - 3);
			g.popState();
		}
	}

	/**
	 * @return the default width
	 */
	private int getDefaultWidth() {
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
