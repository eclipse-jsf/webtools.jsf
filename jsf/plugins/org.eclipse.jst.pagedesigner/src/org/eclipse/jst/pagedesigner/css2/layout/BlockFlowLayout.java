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

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Insets;

/**
 * The layout for {@link BlockFlow}figures.
 * <P>
 * WARNING: This class is not intended to be subclassed by clients.
 * 
 * @author mengbo
 * @since 2.1
 */
public class BlockFlowLayout extends FlowContainerLayout {
	private LineBox _previousLine = null;

	BlockBox _blockBox;

	/**
	 * Creates a new BlockFlowLayout with the given BlockFlow.
	 * 
	 * @param blockFlow
	 *            the BlockFlow
	 */
	public BlockFlowLayout(BlockFlow blockFlow) {
		super(blockFlow);
	}

	/**
	 * @see FlowContainerLayout#cleanup()
	 */
	protected void cleanup() {
		_currentLine = _previousLine = null;
	}

	/**
	 * @see FlowContainerLayout#createNewLine()
	 */
	protected void createNewLine() {
		_currentLine = new LineBox();
		setupLine(_currentLine, Integer.MIN_VALUE);
	}

	protected void createNewLine(int topmargin) {
		_currentLine = new LineBox();
		setupLine(_currentLine, topmargin);
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
		line._x = 0;

		// FIXME: here should check the floating boxes, and minus the width of
		// them from
		// current line.
		line.setRecommendedWidth(_blockBox.getRecommendedContentWidth());
		if (_previousLine == null) {
			line._y = 0;
			if (topMargin != Integer.MIN_VALUE) {
				line._y += topMargin;
			}
		} else {
			if (topMargin == Integer.MIN_VALUE) {
				line._y = _previousLine._y + _previousLine.getHeight()
						+ getLinePadding() + _previousLine.getMarginInsets().bottom; // XXX:
				// should
				// add
				// previous
				// margin
				// bottom?
			} else {
				line._y = _previousLine._y
						+ _previousLine.getHeight()
						+ Math.max(topMargin,
								_previousLine.getMarginInsets().bottom);
			}
		}
		// line.validate();
	}

	/**
	 * Called by flush(), adds the BlockBox associated with this BlockFlowLayout
	 * to the current line and then ends the line.
	 */
	protected void endBlock() {
		getFlowContext().addToCurrentLine(_blockBox);

		// FIXME: here should tell the context the bottom margin.
		getFlowContext().endLine();
	}

	/**
	 * @see FlowContext#endLine()
	 */
	public void endLine() {
		// this is called from child layouts.
		// If there is no current line, state is equivalent to new line
		if (_currentLine == null) {
			return;
		}
		if (_currentLine.isOccupied()) {
			layoutLine(); // finalize the current line layout
		} else {
			_currentLine = null;
			return;
		}
		LineBox box = _currentLine;
		_previousLine = box;
		_currentLine = null;// _previousLine; //XXX: ???? why (yang)

		// setupLine(getCurrentLine());
	}

	/**
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#getCurrentY()
	 */
	public int getCurrentY() {
		return getCurrentLine()._y; // FIXME: margin of previous block?
	}

	/**
	 * Returns the BlockFlow associated with this BlockFlowLayout
	 * 
	 * @return the BlockFlow
	 */
	protected final BlockFlow getBlockFlow() {
		return (BlockFlow) getFlowFigure();
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
		switch (getBlockFlow().getHorizontalAligment()) {
		case PositionConstants.RIGHT:
			_currentLine._x = _blockBox.getContentWidth()
					- getBorderPaddingInsets().right - _currentLine.getWidth();
			break;
		case PositionConstants.CENTER:
			_currentLine._x = (_blockBox.getContentWidth()
					+ getBorderPaddingInsets().left
					- getBorderPaddingInsets().right - _currentLine.getWidth()) / 2;
			break;
		}
		// FIXME: should check vertical alignment here?
		_currentLine.commit();
		_blockBox.add(_currentLine);
	}

	/**
	 * @see FlowContainerLayout#flush()
	 */
	protected void flush() {
		if (_currentLine != null)
			layoutLine();
		endBlock();
	}

	/**
	 * @see FlowContainerLayout#preLayout()
	 */
	protected void preLayout() {
		_blockBox = getBlockFlow().getBlockBox();
		setupBlock();
		// Probably could setup current and previous line here, or just previous
	}

	/**
	 * sets up the single block that contains all of the lines.
	 */
	protected void setupBlock() {
		// Ask for a new line, in case we are in the middle of a line

		// FIXME: the endLine() should tell context the top margin of this
		// block.
		getFlowContext().endLine();

		LineBox line = getFlowContext().getCurrentLine();
		// int recommended = line.getAvailableWidth();
		// if (recommended != previousRecommendedWidth)
		// Remove all current Fragments
		_blockBox.clear();

		// Setup the one fragment for this Block with the correct X and
		// available width

		// FIXME: here should check whether the CSS already set recommended
		// width for this
		// block.
		_blockBox.setRecommendedWidth(line.getAvailableWidth());

		_blockBox._y = getFlowContext().getCurrentY();

		// FIXME: blockBox.x should be context.getBorderPaddingInsets().left
		// or just line.x ?
		_blockBox._x = 0;
	}

	Insets getBorderPaddingInsets() {
		// FIXME:
		return new Insets();
	}

	int getLinePadding() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowFigureLayout#dispose()
	 */
	public void dispose() {
        // TODO: anything to dispose?
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#getContainerWidth()
	 */
	public int getContainerWidth() {
		int width = Math.max(0, Math.max(_blockBox.getWidth(), _blockBox
				.getRecommendedWidth()));
		return width;
	}
}
