/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Color;

/**
 * LayerPane has muliple layers, this interface will be used to set different
 * layer's style.
 * 
 * @author Xiao-guang Zhang
 * 
 */
public interface ILayerPanePreference extends IFigurePreference {
	public static int LINE_ROUTING_MANUAL = 0;

	public static int LINE_ROUTING_MANHATTAN = 1;

	/**
	 * sets the grid to be shown or not
	 * 
	 * @param bVisible
	 */
	void setGridVisible(boolean bVisible);

	/**
	 * sets the grid's dimension
	 * 
	 * @param d
	 *            The new grid spacing
	 */
	void setGridSpacing(Dimension d);

	/**
	 * set the grid's foregoundColor
	 * 
	 * @param c -
	 *            The new foreground color
	 */
	void setGridForegroundColor(Color c);

	/**
	 * set the connection router's style, it can be LINE_ROUTING_MANUAL,
	 * LINE_ROUTING_MANHATTAN
	 * 
	 * @param style -
	 *            The new connection router's style
	 */
	void setConnectionRouterStyle(int style);

}
