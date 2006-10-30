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
package org.eclipse.jst.pagedesigner.css2.layout;

import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * This class represents the CSS box model. See chapter 8 of CSS2 spec.
 * 
 * @see http://www.w3.org/TR/REC-CSS2/box.html
 */
public class FlowBox {
	private Object _verticalAlignData = null;

	/**
	 * The x location
	 */
	public int _x;

	/**
	 * The y location
	 */
	public int _y;

	int _width;

	int _height;

	public Insets _marginInsets = new Insets();

	public Insets _borderInsets = new Insets();

	public Insets _paddingInsets = new Insets();

	/**
	 * This method must be called on a block that is completely positioned and
	 * committed.
	 * 
	 * @param x
	 *            X
	 * @param y
	 *            Y
	 * @return <code>true</code> if the FlowBox contains the point
	 */
	public boolean containsPoint(int x, int y) {
		return x >= this._x && y >= this._y && x < this._x + this._width
				&& y < this._y + this._height;
	}

	/**
	 * By default, a FlowBox is all ascent, and no descent, so the height is
	 * returned.
	 * 
	 * @return the <i>ascent </i> in pixels above the baseline
	 */
	public int getAscent() {
		return getHeight();
	}

	/**
	 * By default, a simple FlowBox is all ascent, and no descent. Zero is
	 * returned.
	 * 
	 * @return the <i>descent </i> in pixels below the baseline
	 */
	public final int getDescent() {
		return getHeight() - getAscent();
	}

	/**
	 * Returns the height
	 * 
	 * @return height
	 */
	public int getHeight() {
		return _height;
	}

	/**
	 * Returns the width
	 * 
	 * @return width
	 */
	public int getWidth() {
		return _width;
	}

	public void setWidth(int w) {
		_width = w;
	}

	public void setHeight(int h) {
		_height = h;
	}

	/**
	 * Used to set the baseline of this FlowBox to the specified value.
	 * 
	 * @param value
	 *            the new baseline
	 */
	public void makeBaseline(int value) {
		_y = (value - getAscent());
	}

	public int getBorderPaddingWidth() {
		return _borderInsets.getWidth() + _paddingInsets.getWidth();
	}

	/**
	 * @return
	 */
	public int getBorderPaddingHeight() {
		return _borderInsets.getHeight() + _paddingInsets.getHeight();
	}

	/**
	 * @return
	 */
	public Insets getBorderPaddingInsets() {
		Insets temp = new Insets(_borderInsets);
		return temp.add(_paddingInsets);
	}

	public void setXYWidthHeight(Rectangle rect) {
		this._x = rect.x;
		this._y = rect.y;
		this.setWidth(rect.width);
		this.setHeight(rect.height);
	}

	/**
	 * @return Returns the _verticalAlignData.
	 */
	public Object getVerticalAlignData() {
		return _verticalAlignData;
	}

	/**
	 * @param alignData
	 *            The _verticalAlignData to set.
	 */
	public void setVerticalAlignData(Object alignData) {
		_verticalAlignData = alignData;
	}

	public Rectangle getRectangle() {
		return new Rectangle(this._x, this._y, this.getWidth(), this
				.getHeight());
	}
}
