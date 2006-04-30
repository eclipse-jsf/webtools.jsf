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

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

/**
 * @author Bob
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface IBaseFigure extends IFigure {
	public abstract void setText(String text);

	public abstract String getText();

	/*
	 * CR374981: Long activity labels do not get wrapped or truncated This
	 * method was added to the IBaseFigure interface to support direct edit of
	 * figure labels on the canvas.
	 */
	public abstract Rectangle getTextBounds();

	public abstract void setIcon(Image image);

	public abstract Image getIcon();

	public abstract void setToolTipText(String text);

	public abstract String getToolTipText();

	public abstract void setHighlight(boolean flag);

	public abstract void addDecorator(BaseFigureDecorator decorator);

	public abstract void removeDecorator();

	public abstract void removeDecorator(int position);

	public abstract List getDecorators();
}