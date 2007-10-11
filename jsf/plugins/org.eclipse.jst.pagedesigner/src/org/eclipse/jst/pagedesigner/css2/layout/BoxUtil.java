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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.swt.graphics.Color;

/**
 * @author mengbo
 */
public class BoxUtil {
	/**
	 * @param box
	 * @param style
	 */
	public static void setupBorderPaddingMargin(FlowBox box, ICSSStyle style) {
		box.setMarginInsets(new Insets(style.getMarginInsets()));
		box.setBorderInsets(new Insets(style.getBorderInsets()));
		box.setPaddingInsets(new Insets(style.getPaddingInsets()));

		if (box.getBorderPaddingHeight() > box.getHeight()) {
			box.setHeight(box.getBorderPaddingHeight());
		}
		if (box.getBorderPaddingWidth() > box.getWidth()) {
			box.setWidth(box.getBorderPaddingWidth());
		}
	}

	/**
	 * Debug code.
	 * 
	 * @param g
	 * @param box
	 */
	public static void drawBox(Graphics g, FlowBox box) {
		Color color = null;
		if (box instanceof BlockBox) {
			// color = ColorConstants.red;
		} else if (box instanceof LineBox) {
			color = ColorConstants.blue;
		} else if (box instanceof TextFragmentBox) {
			color = ColorConstants.green;
		} else {
			color = ColorConstants.darkGreen;
		}
		if (color != null) {
			g.setForegroundColor(color);
			g.setLineStyle(Graphics.LINE_DASH);
			g.setLineWidth(1);
			g.drawRectangle(box._x, box._y, box.getWidth(), box.getHeight());
		}
	}

}
