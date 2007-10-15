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
package org.eclipse.jst.pagedesigner.css2.provider;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;

/**
 * @author mengbo
 */
public interface ICSSWidgetProvider {
	/**
	 * can be null
	 * 
	 * @return the style
	 */
	public ICSSStyle getCSSStyle();

	/**
	 * @return true if is handling border
	 */
	public boolean isHandlingBorder();

	/**
	 * whether the parameter and return value includes border depends on the
	 * isHandlingBorder
	 * 
	 * @param width
	 *            -1 means no suggested value
	 * @param height
	 *            -1 means no suggested value
	 * @return the dimension info
	 */
	public DimensionInfo getPreferredDimension(int width, int height);

	/**
	 * if isHandlingBorder return false, then "rect" will be the rect without
	 * border, and this method should not paint border.
	 * 
	 * If isHandlingBorder returns true, then "rect" include border, and this
	 * method should also paint its own border.
	 * @param g 
	 * @param rect
	 */
	public void paintFigure(Graphics g, Rectangle rect);

	/**
	 * @return true if is inline
	 */
	public boolean isInline();
}
