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

import java.util.ArrayList;

import org.eclipse.draw2d.geometry.Rectangle;

/**
 * A List<Rectangle> implementation with a coordinate-based
 * convenience method for adding rectangles.
 *
 */
class RectangleList extends ArrayList<Rectangle>
{
	private static final long serialVersionUID = -4088355285820327890L;

	/**
	 * Default constructor
	 */
	public RectangleList() {
		super();
	}

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void addRectangle(int x1, int y1, int x2, int y2) {
		add(new Rectangle(x1, y1, x2 - x1, y2 - y1));
	}
}