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

import org.eclipse.draw2d.geometry.Dimension;

/**
 * This is a struct, include a dimension and a ascent info.
 * 
 * @author mengbo
 * @version 1.5
 */
public class DimensionInfo {
	private final Dimension _dimension;

	private final int _ascent;

	/**
	 * 
	 * @param d
	 * @param ascent
	 *            -1 means ascent is same as dimension height
	 */
	public DimensionInfo(Dimension d, int ascent) {
		this._dimension = d;
		this._ascent = ascent;
	}

	/**
	 * @param width
	 * @param height
	 * @param i
	 */
	public DimensionInfo(int width, int height, int i) {
		this(new Dimension(width, height), i);
	}

	/**
	 * @return the dimension
	 */
	public Dimension getDimension() {
		return _dimension;
	}

	/**
	 * @return the ascent
	 */
	public int getAscent() {
		return _ascent;
	}

}
