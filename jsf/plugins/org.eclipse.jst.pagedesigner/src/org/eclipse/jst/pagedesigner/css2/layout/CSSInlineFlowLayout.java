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

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo;

/**
 * The layout manager for {@link CSSFigure}figures. This class is based on
 * InlineFlowLayout of draw2d.
 * 
 * @author mengbo
 */
public class CSSInlineFlowLayout extends CSSLayout {
	List _fragments = new ArrayList();

	/**
	 * Creates a new InlineFlowLayout with the given FlowFigure.
	 * 
	 * @param flow
	 *            The FlowFigure
	 */
	public CSSInlineFlowLayout(CSSFigure flow) {
		super(flow);
	}

	/**
	 * Clears out all fragments prior to the call to layoutChildren().
	 */
	public void preLayout() {
		super.preLayout();
		_fragments.clear();
		// force creating of the first line. avoid empty element don't have
		// fragments.
		// createFirstLine();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContainerLayout#layoutChildren()
	 */
	protected void layoutChildren() {
		// For designer, to make it to have some size. otherwise can't
		// be found on screen.
		// List children = getCSSFigure().getChildren();
		// if (children.size() == 0)
		// {
		// FlowBox box = new FlowBox();
		// box._height = getCSSStyle().getCSSFont().getFontSize();
		// box._width = 2;
		// addToCurrentLine(box);
		//
		// }
		super.layoutChildren();
	}

	/**
	 * Adds the given FlowBox to the current line of this InlineFlowLayout.
	 * 
	 * @param block
	 *            the FlowBox to add to the current line
	 */
	public void addToCurrentLine(FlowBox block) {
		getCurrentLine().add(block);
		// XXX: ???: will currentLine be added multiple times to fragments?
		// (yang)
		// _fragments.add(_currentLine);
	}

	private void createFirstLine() {
		_currentLine = new LineBox();
		setupLine(_currentLine, true);
		_fragments.add(_currentLine);
	}

	/**
	 * @see FlowContainerLayout#createNewLine()
	 */
	protected void createNewLine() {
		_currentLine = new LineBox();
		setupLine(_currentLine, false);
		_fragments.add(_currentLine);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContainerLayout#createNewLine(int)
	 */
	protected void createNewLine(int topMargin) {
		// inline flow don't support vertical margin.
		createNewLine();
	}

	/**
	 * @see FlowContainerLayout#cleanup()
	 */
	protected void cleanup() {
		_currentLine = null;
	}

	/**
	 * @see FlowContainerLayout#flush()
	 */
	protected void flush() {
		if (_fragments.isEmpty()) {
			createFirstLine();
		} else if (_fragments.size() == 1) {

			ICSSStyle style = getCSSStyle();
			int minWidth = 0, minHeight = 0;
			// try to see whether there is any designer specified min size
			ITagEditInfo info = (ITagEditInfo) style
					.getAdapter(ITagEditInfo.class);
			if (info != null) {
				minWidth = info.getMinWidth();
				minHeight = info.getMinHeight();
			}
			FlowBox box = (FlowBox) _fragments.get(0);
			if (minWidth > box._width) {
				box._width = minWidth;
			}
			if (minHeight > box._height) {
				box._height = minHeight;
			}
		}

		if (_currentLine != null /* && _currentLine.isOccupied() */) {
			_currentLine.getMarginInsets().right = getCSSStyle().getMarginInsets().right;
			getFlowContext().addToCurrentLine(_currentLine);
		}

	}

	/**
	 * @see FlowContext#endLine()
	 */
	public void endLine() {
		if (_currentLine == null) {
			getFlowContext().endLine();
			return;
		}
		// If nothing was ever placed in the line, ignore it. and if the line is
		// the first line, just remove it.
		if (_currentLine.isOccupied()) {
			getFlowContext().addToCurrentLine(_currentLine);
		} else if (_fragments.size() == 1) {
			_fragments.remove(0);
		}
		getFlowContext().endLine();
		_currentLine = null;
	}

	/**
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#getCurrentY()
	 */
	public int getCurrentY() {
		return getCurrentLine()._y;
	}

	/**
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContainerLayout#isCurrentLineOccupied()
	 */
	public boolean isCurrentLineOccupied() {
		if (_currentLine == null) {
			return getFlowContext().isCurrentLineOccupied();
		} else if (_currentLine.getFragments().isEmpty()) {
			return getFlowContext().isCurrentLineOccupied();
		} else {
			return true;
		}
	}

	/**
	 * Initializes the given LineBox. Called by createNewLine().
	 * 
	 * @param line
	 *            The LineBox to initialize.
	 * @param firstline 
	 */
	protected void setupLine(LineBox line, boolean firstline) {
		LineBox parent = getFlowContext().getCurrentLine();
		line._x = 0;
		line._y = getFlowContext().getCurrentY();

		line.setRecommendedWidth(parent.getAvailableWidth());

		setLineVerticalAlign(line);
		setFontinfoForLine(line);

		if (firstline && getCSSStyle() != null) {
			ICSSStyle style = getCSSStyle();
			int minWidth = 0, minHeight = 0;
			// try to see whether there is any designer specified min size
			ITagEditInfo info = (ITagEditInfo) style
					.getAdapter(ITagEditInfo.class);
			if (info != null) {
				minWidth = info.getMinWidth();
				minHeight = info.getMinHeight();
			}

			// // CSS also has the min-width/min-height property. We should also
			// get that,
			// // and using the max of the "min-width" css property and the
			// designer specified min size.
			// int height =
			// getLengthValue(style,ICSSPropertyID.ATTR_MIN_HEIGHT);
			// if(height > minHeight)
			// {
			// minHeight = height;
			// }
			// int width = getLengthValue(style,ICSSPropertyID.ATTR_MIN_WIDTH);
			// if(width > minWidth)
			// {
			// minWidth = width;
			// }
			if (minWidth > 0) {
				line.setWidth(minWidth);
			}
			int fontHeight = this.getCSSStyle().getCSSFont().getXHeight();
			if (minHeight > 0 && minHeight > fontHeight) {
				line.setHeight(minHeight);
			} else {
				line.setHeight(fontHeight);
			}
		}
	}

	private void setLineVerticalAlign(LineBox box) {
		ICSSStyle style = getCSSStyle();
		if (style != null) {
			box.setVerticalAlignData(style
					.getStyleProperty(ICSSPropertyID.ATTR_VERTICAL_ALIGN));
		}
	}

	private void setFontinfoForLine(LineBox line) {

		ICSSStyle style = getCSSStyle();
		if (style != null) {
			line.setFontMetrics(FigureUtilities.getFontMetrics(style
					.getCSSFont().getSwtFont()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowFigureLayout#dispose()
	 */
	public void dispose() {
		// 

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.ICSSLayout#getFragmentsForRead()
	 */
	public List getFragmentsForRead() {
		return _fragments;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.ICSSLayout#postValidate()
	 */
	public void postValidate() {
		List list = _fragments;

		FlowBox box;
		int left = Integer.MAX_VALUE, top = left;
		int right = Integer.MIN_VALUE, bottom = right;
		for (int i = 0; i < list.size(); i++) {
			box = (FlowBox) list.get(i);
			// if (box instanceof LineBox && !((LineBox) box).isOccupied())
			// {
			// continue; // skip unoccupied line
			// }
			left = Math.min(left, box._x);
			right = Math.max(right, box._x + box._width);
			top = Math.min(top, box._y);
			bottom = Math.max(bottom, box._y + box._height);
		}
		getCSSFigure().setBounds(
				new Rectangle(left, top, right - left, bottom - top));
		list = getCSSFigure().getChildren();
		for (int i = 0; i < list.size(); i++) {
			((FlowFigure) list.get(i)).postValidate();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#getContainerWidth()
	 */
	public int getContainerWidth() {
		// FIXME: don't really understand what means for inline
		return this.getFlowContext().getContainerWidth();
	}
}
