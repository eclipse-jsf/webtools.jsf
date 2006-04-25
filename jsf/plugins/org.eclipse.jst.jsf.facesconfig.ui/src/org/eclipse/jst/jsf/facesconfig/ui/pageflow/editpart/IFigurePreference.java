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

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

/**
 * This interface can be implemented to set the figure's preferences
 * 
 * @author Xiao-guang Zhang
 * 
 */
public interface IFigurePreference {
	/**
	 * Sets the foreground color.
	 * 
	 * @param c
	 *            The new foreground color
	 */
	void setForegroundColor(Color c);

	/**
	 * Sets the background color.
	 * 
	 * @param c
	 *            The new background color
	 */
	void setBackgroundColor(Color c);

	/**
	 * sets the new font
	 * 
	 * @param f
	 */
	void setFont(Font f);

}
