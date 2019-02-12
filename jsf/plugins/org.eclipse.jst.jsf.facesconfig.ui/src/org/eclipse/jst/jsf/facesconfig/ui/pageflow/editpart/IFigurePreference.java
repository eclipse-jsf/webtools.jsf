/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
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
