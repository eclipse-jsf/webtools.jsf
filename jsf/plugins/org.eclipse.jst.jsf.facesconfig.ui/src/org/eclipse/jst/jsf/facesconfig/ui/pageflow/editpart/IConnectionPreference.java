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

/**
 * This interface can be used to set Connection's preference
 * 
 * @author Xiaoguang Zhang
 * 
 */
public interface IConnectionPreference extends IFigurePreference {

	/**
	 * set the connection line width
	 * 
	 * @param w
	 */
	void setLineWidth(int w);

	/**
	 * set the label visible or not
	 * 
	 * @param b
	 */
	void setLabelVisible(boolean b);

	/**
	 * set the label's foreground color
	 * 
	 * @param c
	 */
	void setLabelForegroundColor(Color c);

	/**
	 * set the label's background color
	 * 
	 * @param c
	 */
	void setLabelBackgroundColor(Color c);

	/**
	 * set the connection router's style, it can be LINE_ROUTING_MANUAL,
	 * LINE_ROUTING_MANHATTAN
	 * 
	 * @param style -
	 *            The new connection router's style
	 */
	void setConnectionRouterStyle(int style);

}
