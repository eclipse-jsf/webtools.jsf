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

import java.util.ArrayList;
import java.util.List;

/**
 * A FlowBox that can contain other BlockInfos. The contained BlockInfos are
 * called <i>fragments </i>.
 * 
 * @author mengbo
 * @since 2.1
 */
public abstract class CompositeBox extends FlowBox {

	/**
	 * The contained fragments.
	 */
	protected List _fragments = new ArrayList();

	int _recommendedWidth;

	int _recommendedHeight;

	/**
	 * Adds the specified FlowBox. Updates the width, height, and ascent
	 * properties.
	 * 
	 * @param block
	 *            the FlowBox being added
	 */
	public void add(FlowBox block) {
		// The order is critical.see the first "if" block in the unionInfo()
		// method.
		unionInfo(block);
		_fragments.add(block);
	}

	/**
	 * Removes all owned fragments and invalidates this CompositeBox.
	 */
	public void clear() {
		_fragments.clear();
		resetInfo();
	}

	/**
	 * Overridden to ensure that the CompositeBox is valid.
	 * 
	 * @see FlowBox#getBounds()
	 */
	// public Rectangle getBounds() {
	// validate();
	// return this;
	// }
	/**
	 * @return the List of fragments
	 */
	public List getFragments() {
		return _fragments;
	}

	/**
	 * Returns the recommended width for this CompositeBox.
	 * 
	 * @return the recommended width
	 */
	public int getRecommendedWidth() {
		return _recommendedWidth;
	}

	/**
	 * resets fields before unioning the data from the fragments.
	 */
	protected void resetInfo() {
		_width = _height = 0;
	}

	/**
	 * Sets the recommended width for this CompositeBox.
	 * 
	 * @param w
	 *            the width
	 */
	public void setRecommendedWidth(int w) {
		_recommendedWidth = w;
	}

	/**
	 * @param h
	 */
	public void setRecommendedHeight(int h) {
		_recommendedHeight = h;
	}

	/**
	 * unions the fragment's width, height, and ascent into this composite.
	 * 
	 * @param box
	 *            the fragment
	 */
	protected void unionInfo(FlowBox box) {
		int right = Math.max(_x + _width, box._x + box._width);
		int bottom = Math.max(_y + _height, box._y + box._height);
		_x = Math.min(_x, box._x);
		_y = Math.min(_y, box._y);
		_width = right - _x;
		_height = bottom - _y;
	}

	/**
	 * @return the content width
	 */
	public int getContentWidth() {
		return getWidth() - getBorderPaddingWidth();
	}

	/**
	 * @return the content height
	 */
	public int getContentHeight() {
		return getHeight() - getBorderPaddingHeight();
	}

	/**
	 * @return the recommended content width
	 */
	public int getRecommendedContentWidth() {
		return Math.max(0, getRecommendedWidth() - getBorderPaddingWidth());
	}
	//
	// public int getRecommendedContentHeight()
	// {
	// return Math.max(0, getRecommendedHeight() - getBorderPaddingHeight());
	// }
}
