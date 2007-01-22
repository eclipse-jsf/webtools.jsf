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

import java.util.Iterator;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * The root of a Flow hierarchy. A flow page can be treated as a normal figure,
 * but contains FlowFigures.
 * <P>
 * A FlowPage will not have a defined width unless it is inside a figure whose
 * layout provides width hints when calling
 * {@link org.eclipse.draw2d.IFigure#getPreferredSize(int, int)}.
 * <P>
 * WARNING: This class is not intended to be subclassed by clients.
 */
public class FlowPage extends BlockFlow {

	private Dimension _pageSize = new Dimension();

	private int _recommendedWidth;

	private int _pageSizeCacheKeys[] = new int[4];

	private Dimension _pageSizeCacheValues[] = new Dimension[4];

	/**
	 * @see org.eclipse.jst.pagedesigner.css2.layout.BlockFlow#createDefaultFlowLayout()
	 */
	protected FlowFigureLayout createDefaultFlowLayout() {
		return new PageFlowLayout(this);
	}

	/**
	 * @see org.eclipse.draw2d.Figure#getMinimumSize()
	 */
	public Dimension getMinimumSize(int w, int h) {
		return getPreferredSize(w, h);
	}

	/**
	 * @see org.eclipse.draw2d.Figure#invalidate()
	 */
	public void invalidate() {
		_pageSizeCacheValues = new Dimension[4];
		super.invalidate();
	}

	/**
	 * @see org.eclipse.draw2d.Figure#getPreferredSize(int, int)
	 */
	public Dimension getPreferredSize(int width, int h) {
		if (width >= 0)
			width = Math.max(0, width - getInsets().getWidth());

		for (int i = 0; i < 4; i++) {
			if (_pageSizeCacheKeys[i] == width
					&& _pageSizeCacheValues[i] != null)
				return _pageSizeCacheValues[i];
		}

		_pageSizeCacheKeys[3] = _pageSizeCacheKeys[2];
		_pageSizeCacheKeys[2] = _pageSizeCacheKeys[1];
		_pageSizeCacheKeys[1] = _pageSizeCacheKeys[0];
		_pageSizeCacheKeys[0] = width;

		_pageSizeCacheValues[3] = _pageSizeCacheValues[2];
		_pageSizeCacheValues[2] = _pageSizeCacheValues[1];
		_pageSizeCacheValues[1] = _pageSizeCacheValues[0];

		// Flowpage must temporarily layout to determine its preferred size
		int oldWidth = getRecommendedWidth();
		setRecommendedWidth(width);
		validate();
		_pageSizeCacheValues[0] = _pageSize.getExpanded(getInsets().getWidth(),
				getInsets().getHeight());

		if (width != oldWidth) {
			setRecommendedWidth(oldWidth);
			getUpdateManager().addInvalidFigure(this);
		}
		return _pageSizeCacheValues[0];
	}

	int getRecommendedWidth() {
		return _recommendedWidth;
	}

	/**
	 * @see BlockFlow#postValidate()
	 */
	public void postValidate() {
		Rectangle r = getBlockBox().toRectangle();
		_pageSize.width = r.width;
		_pageSize.height = r.height;
		for (final Iterator it = getChildren().iterator(); it.hasNext();)
        {
            ((FlowFigure)it.next()).postValidate();            
        }
	}

	/**
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowFigure#setBounds(Rectangle)
	 */
	public void setBounds(Rectangle r) {
		if (getBounds().equals(r))
			return;
		boolean invalidate = getBounds().width != r.width
				|| getBounds().height != r.height;
		super.setBounds(r);
		int newWidth = r.width - getInsets().getWidth();
		if (invalidate || getRecommendedWidth() != newWidth) {
			setRecommendedWidth(newWidth);
			getUpdateManager().addInvalidFigure(this);
		}
	}

	private void setRecommendedWidth(int width) {
		if (_recommendedWidth == width)
			return;
		_recommendedWidth = width;
		super.invalidate();
	}

	/**
	 * @see org.eclipse.draw2d.Figure#validate()
	 */
	public void validate() {
		if (isValid())
			return;
		super.validate();
		postValidate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Figure#setValid(boolean)
	 */
	public void setValid(boolean value) {
		super.setValid(value);
	}

	public Insets getInsets() {
		return new Insets(8);
	}

}
