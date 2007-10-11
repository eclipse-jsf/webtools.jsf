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
 * see http://www.w3.org/TR/REC-CSS2/box.html
 * 
 */
public class FlowBox {
	private Object _verticalAlignData = null;

	/**
	 * The x location
	 */
	protected int _x;

	/**
	 * The y location
	 */
	protected int _y;

	int _width;

	int _height;

	private Insets _marginInsets = new Insets();

	private Insets _borderInsets = new Insets();

	private Insets _paddingInsets = new Insets();

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

	/**
	 * @param w
	 */
	public void setWidth(int w) {
		_width = w;
	}

	/**
	 * @param h
	 */
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

	/**
	 * @return the border padding width
	 */
	public int getBorderPaddingWidth() {
		return _borderInsets.getWidth() + _paddingInsets.getWidth();
	}

	/**
	 * @return the border padding height
	 */
	public int getBorderPaddingHeight() {
		return _borderInsets.getHeight() + _paddingInsets.getHeight();
	}

	/**
	 * @return the border padding insets
	 */
	public Insets getBorderPaddingInsets() {
		Insets temp = new Insets(_borderInsets);
		return temp.add(_paddingInsets);
	}

	/**
	 * @param rect
	 */
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

	/**
	 * @return a copy of the rectangle
	 * TODO: use getCopy() ?
	 */
	public Rectangle getRectangle() {
		return new Rectangle(this._x, this._y, this.getWidth(), this
				.getHeight());
	}

    /**
     * @return the x coordinate
     */
    public final int getX() {
        return _x;
    }

    /**
     * @return the y coordinate
     */
    public final int getY() {
        return _y;
    }

    /**
     * @param y
     */
    protected void setY(int y)
    {
        _y = y;
    }
    
    /**
     * @return the margin insets
     */
    public final Insets getMarginInsets() {
        return _marginInsets;
    }

    final void setMarginInsets(Insets marginInsets) {
        _marginInsets = marginInsets;
    }

    final Insets getBorderInsets() {
        return _borderInsets;
    }

    final void setBorderInsets(Insets borderInsets)
    {
        _borderInsets = borderInsets;
    }
    
    final Insets getPaddingInsets() {
        return _paddingInsets;
    }
	
	final void setPaddingInsets(Insets paddingInsets)
	{
	    _paddingInsets = paddingInsets;
	}
}
