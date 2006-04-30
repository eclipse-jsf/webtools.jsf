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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.Rectangle;

public class RectangleList extends Figure {
	protected List rectangles = new ArrayList();

	public RectangleList() {
	}

	public void addRectangle(int x1, int y1, int x2, int y2) {
		rectangles.add(new Rectangle(x1, y1, x2 - x1, y2 - y1));
	}

	public List getRectangles() {
		return rectangles;
	}
}