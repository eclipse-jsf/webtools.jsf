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

import org.eclipse.draw2d.geometry.Rectangle;

/**
 * A CompositeBox suitable for containing multiple LineBox fragments. Based on
 * BlockBox of draw2d.
 * 
 * @author mengbo
 */
public class BlockBox extends CompositeBox {
	// internalContent dimension is for the closure of the FlowBox(es) added
	// into the BlockBox.
	private int _internalContentWidth = -1;

	private int _internalContentHeight = -1;

	Rectangle toRectangle() {
		return new Rectangle(_x, _y, Math.max(_width, _recommendedWidth),
				_height);
	}

	/**
	 * Sets the height.
	 * 
	 * @param h
	 *            The height
	 */
	public void setHeight(int h) {
		_height = h;
	}

	/**
	 * Unions the dimensions of this with the dimensions of the passed FlowBox.
	 * For BlockBox, each time unionInfo is called, the passed in object
	 * represents a line.
	 * 
	 * @param box
	 *            The FlowBox to union this with
	 */
	protected void unionInfo(FlowBox box) {
		_width = Math.max(_width, box._width + this.getBorderPaddingWidth());
		_height = Math.max(_height, box._y + box._height
				+ this.getBorderPaddingHeight());

		_internalContentWidth = Math.max(_internalContentWidth, box._width);
		_internalContentHeight = Math.max(_internalContentHeight, box._y
				+ box._height);
	}

	int getInternalContentWidth() {
		return _internalContentWidth;
	}

	int getInternalContentHeight() {
		return _internalContentHeight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowBox#getAscent()
	 */
	public int getAscent() {
		// XXX: some hard coded things here. If the blockbox is only for a
		// single widget, and if that widget support ascent, then we'll
		// delegate to that widget for ascent support.
		// if (_fragments.size()==1)
		// {
		// FlowBox box = (FlowBox) _fragments.get(0);
		// if (box instanceof LineBox)
		// {
		// List linecomponents = ((LineBox) box).getFragments();
		// if (linecomponents != null && linecomponents.size() == 1)
		// {
		// FlowBox box2 = (FlowBox) linecomponents.get(0);
		// if (box2 instanceof WidgetBox)
		// {
		// WidgetBox widgetBox = (WidgetBox) box2;
		// if (widgetBox.supportAscent())
		// {
		// return widgetBox.getAscent() + this.getBorderPaddingInsets().top;
		// }
		// }
		// }
		// }
		// }
		return super.getAscent();
	}
}
