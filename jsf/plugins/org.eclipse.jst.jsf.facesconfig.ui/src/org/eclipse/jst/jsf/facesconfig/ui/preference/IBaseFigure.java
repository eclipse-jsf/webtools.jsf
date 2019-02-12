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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

/**
 * @author Bob
 */
/*package*/ interface IBaseFigure extends IFigure 
{
	/**
	 * @param text
	 */
	public abstract void setText(String text);

	/**
	 * @return the text
	 */
	public abstract String getText();

	/**
	 * CR374981: Long activity labels do not get wrapped or truncated This
	 * method was added to the IBaseFigure interface to support direct edit of
	 * figure labels on the canvas.
	 * @return the text bounds rectangle
	 */
	public abstract Rectangle getTextBounds();

	/**
	 * @param image
	 */
	public abstract void setIcon(Image image);

	/**
	 * @return the icon
	 */
	public abstract Image getIcon();

	/**
	 * @param text
	 */
	public abstract void setToolTipText(String text);

	/**
	 * @return the tool tip text
	 */
	public abstract String getToolTipText();

	/**
	 * @param flag
	 */
	public abstract void setHighlight(boolean flag);

	/**
	 * Add decorator.  May
	 * cause the figure to become invalidated
	 * @param decorator
	 */
	public abstract void addDecorator(BaseFigureDecorator decorator);

	/**
	 * Remove the decorator
	 */
	public abstract void removeDecorator();

	/**
	 * @param position
	 */
	public abstract void removeDecorator(int position);

	/**
	 * @return the list of decoractors
	 */
	public abstract List getDecorators();
}