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

import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.value.Length;

/**
 * When doing absolute positioning, we need to create a block. But that block
 * don't have a corresponding figure. So we need a block without corresponding
 * figure.
 * 
 * @author mengbo
 * @version 1.5
 */
public class BlockFlowContext implements FlowContext {
	private LineBox _currentLine;

	private LineBox _previousLine = null;

	BlockBox _blockBox;

	private final FlowContext _originalContext;

	private final ICSSStyle _style;

	/**
	 * @param originalContext 
	 * @param style 
	 */
	public BlockFlowContext(FlowContext originalContext, ICSSStyle style) {
		this._originalContext = originalContext;
		this._style = style;
		setup();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#getContainerWidth()
	 */
	public int getContainerWidth() {

		return _originalContext.getContainerWidth();
	}

	/**
	 * Initialize the object
	 */
	private void setup() {
		_blockBox = new BlockBox();
		_blockBox.setRecommendedWidth(getRecommendedWidth());
		_currentLine = this.getCurrentLine();
		_previousLine = null;
	}

	private int getRecommendedWidth() {
		int containerWidth = getContainerWidth();
		Object leftObj = _style.getStyleProperty(ICSSPropertyID.ATTR_LEFT);
		if (leftObj != null && leftObj instanceof Length) {
			Length left = (Length) leftObj;
			int intLeft = left.getValue();
			if (left.isPercentage()) {
				intLeft = containerWidth * intLeft / 100;
			}
			if (intLeft < containerWidth) {
				return containerWidth - intLeft;
			}
		}
		return containerWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#addToCurrentLine(org.eclipse.jst.pagedesigner.css2.layout.FlowBox)
	 */
	public void addToCurrentLine(FlowBox block) {
		getCurrentLine().add(block);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#endLine()
	 */
	public void endLine() {
		// this is called from child layouts.
		// If there is no current line, state is equivalent to new line
		if (_currentLine == null)
			return;
		if (_currentLine.isOccupied())
			layoutLine(); // finalize the current line layout
		else
			return;

		LineBox box = _currentLine;
		// _currentLine = _previousLine; //XXX: ???? why (yang)
		_previousLine = box;

		_currentLine = null;
		// setupLine(getCurrentLine());

	}

	/**
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#getCurrentLine()
	 */
	public LineBox getCurrentLine() {
		if (_currentLine == null)
			createNewLine();
		return _currentLine;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#getCurrentLine(int)
	 */
	public LineBox getCurrentLine(int topMargin) {
		if (_currentLine == null)
			createNewLine(topMargin);
		return _currentLine;
	}

	/**
	 * @param topMargin
	 */
	private void createNewLine(int topMargin) {
		createNewLine();
	}

	private void createNewLine() {
		_currentLine = new LineBox();
		setupLine(_currentLine, Integer.MIN_VALUE);
	}

	/**
	 * Override to setup the line's x, remaining, and available width.
	 * 
	 * @param line
	 *            the LineBox to set up
	 * @param topMargin 
	 */
	protected void setupLine(LineBox line, int topMargin) {
		line.clear();

		// the caller of getCurrentLine() may add leftMargin and leftPadding and
		// leftBorder to line.x
		line._x = _blockBox.getBorderInsets().left + _blockBox.getPaddingInsets().left;

		// FIXME: here should check the floating boxes, and minus the width of
		// them from
		// current line.
		// XXX: the RecommendedContentWidth is related with the RecommendedWidth
		// of container that
		// usually larger than it needed.here we do not set the RecommendedWidth
		// for the sake of
		// layouting right absolute position.
		// /shortcoming:the box will break into multi-line after every white
		// space.
		// line.setRecommendedWidth(_blockBox.getRecommendedContentWidth());
		if (_previousLine == null) {
			line._y = _blockBox.getBorderInsets().top
					+ _blockBox.getPaddingInsets().top;
			if (topMargin != Integer.MIN_VALUE)
				line._y += topMargin;
		} else {
			if (topMargin == Integer.MIN_VALUE)
				line._y = _previousLine._y + _previousLine.getHeight()
						+ getLinePadding() + _previousLine.getMarginInsets().bottom; // XXX:
			// should
			// add
			// previous
			// margin
			// bottom?
			else
				line._y = _previousLine._y
						+ _previousLine.getHeight()
						+ Math.max(topMargin,
								_previousLine.getMarginInsets().bottom);
		}
		// line.validate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#getCurrentY()
	 */
	public int getCurrentY() {
		return getCurrentLine()._y; // FIXME: margin of previous block?

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#isCurrentLineOccupied()
	 */
	public boolean isCurrentLineOccupied() {
		return _currentLine != null && _currentLine.isOccupied();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#getLastMarginRight()
	 */
	public int getLastMarginRight() {
		if (_currentLine == null || !_currentLine.isOccupied()) {
			return 0;
		}
		FlowBox box = (FlowBox) _currentLine.getFragments().get(
				_currentLine.getFragments().size() - 1);
		if (box != null) {
			return box.getMarginInsets().right;
		}
        return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#isCalculatingMaxWidth()
	 */
	public boolean isCalculatingMaxWidth() {
		return false;
	}

	/**
	 * Adjust all fragments in the current line to have the same baseline. Do
	 * any additional adjustments, such as horizontal alignment.
	 */
	protected void layoutLine() {
		// currentLine.x = 0; //XXX: comment out, don't understand why set to 0,
		// because it has already
		// been set when setupLine(). And if do need, should
		// set to getBorderPaddingInsets().left
		// if (!isInlineBlock() && shouldExpand())
		// {
		// // FIXME: currently we are using getRecommendedContentWidth,
		// // what happen if after adding the new line, the new width is bigger
		// than
		// // recommendedContentWidth? should we use getWidth() instead of
		// // recommendedcontentWidth?
		//
		// Object textalign =
		// (getCSSStyle().getStyleProperty(ICSSPropertyID.ATTR_TEXTALIGN));
		// if (textalign == ICSSPropertyID.VAL_RIGHT)
		// {
		// _currentLine._x = _blockBox.getRecommendedContentWidth() +
		// _blockBox.getBorderPaddingInsets().left - _currentLine.getWidth();
		// }
		// else if (textalign == ICSSPropertyID.VAL_CENTER)
		// {
		//
		// _currentLine._x = _blockBox.getBorderPaddingInsets().left +
		// (_blockBox.getRecommendedContentWidth() - _currentLine.getWidth()) /
		// 2;
		// }
		// if (_currentLine._x < 0)
		// _currentLine._x = 0;
		// }

		// FIXME: should check vertical alignment here?
		_currentLine.commit();
		_blockBox.add(_currentLine);
	}

	void endBlock() {
		endLine();
	}

	int getLinePadding() {
		return 0;
	}
}
