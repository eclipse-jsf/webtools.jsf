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
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta;
import org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo;
import org.eclipse.jst.pagedesigner.css2.value.Length;
import org.eclipse.jst.pagedesigner.css2.widget.BorderUtil;
import org.eclipse.swt.graphics.FontMetrics;

/**
 * The block layout for {@link CSSFigure}figures. Basic code structure is from
 * BlockFlowLayout.
 * 
 * @author mengbo
 */
public class CSSBlockFlowLayout extends CSSLayout implements ICSSPainter2 {
	private LineBox _previousLine = null;

	/**
	 * the block box for the layout object
	 */
	protected BlockBox _blockBox = null;

	/**
	 * The font metrics for this layout object
	 */
	protected FontMetrics _fontMetrices;

	int _userSpecifiedWidth;

	int _userSpecifiedHeight;

	/*
	 * whether we need HScroll and VScroll when overflow is set to "scroll".
	 * will be updated in "endBlock" and used in "paintFigurePostClientArea"
	 */
	boolean _needHScroll = false;

	boolean _needVScroll = false;

	/**
     * Creates a new CSSBlockFlowLayout with the given BlockFlow.
	 * @param cssfigure
	 */
	public CSSBlockFlowLayout(CSSFigure cssfigure) {
		super(cssfigure);
	}

	/**
	 * @return true if this layout box has more than one line
	 */
	protected boolean hasMoreThanOneLine() {
		return _previousLine != null;
	}

	/**
	 * @return true if this layout block is inline
	 */
	public boolean isInlineBlock() {
		String obj = getCSSStyle().getDisplay();
		return ICSSPropertyID.VAL_INLINE_BLOCK.equals(obj)
				|| ICSSPropertyID.VAL_INLINE_TABLE.equals(obj);
	}

	/**
	 * @return true if should expand the width to all available width. 
	 */
	public boolean shouldExpand() {
		ICSSStyle style = getCSSStyle();
		if (style == null) {
			return false;
		}
        return "block".equalsIgnoreCase(style.getDisplay()) //$NON-NLS-1$
        		|| "list-item".equalsIgnoreCase(style.getDisplay()); //$NON-NLS-1$
	}

	// ---------------------------------------------------------------------------------------------------
	// preLayout stage. Major job is get the top-left corner information of the
	// new block.

	/**
	 * sets up the single block that contains all of the lines.
	 */
	protected void setupBlock() {
		// int recommended = line.getAvailableWidth();
		// if (recommended != previousRecommendedWidth)
		// Remove all current Fragments
		_blockBox.clear();
		// Ask for a new line, in case we are in the middle of a line

		if (!isInlineBlock()) {
			LineBox lineBox = getFlowContext().getCurrentLine();
			if (lineBox != null && !lineBox.isEmptyStringLine()) {
				getFlowContext().endLine();
			}
		}

		ICSSStyle style = getCSSStyle();

		// endLine will result in context create a new line, so we are in the
		// new line now.
		// passing in the top margin, and context will consider that when create
		// the new line.
		int marginTop = style.getMarginInsets().top;
		LineBox line = getFlowContext().getCurrentLine(marginTop);

		// Setup the one fragment for this Block with the correct X and
		// available width

		// FIXME: according to spec, when using percentage width/height, should
		// percentage to
		// the "containing block". But we don't have very good "containing
		// block" resolution
		// implementation yet.

		// calculate the min size
		// int minWidth = 0;
		// int minHeight = 0;
		// if (style != null)
		// {
		// // try to see whether there is any designer specified min size
		// ITagEditInfo info = (ITagEditInfo)
		// style.getAdapter(ITagEditInfo.class);
		// if (info != null)
		// {
		// minWidth = info.getMinWidth();
		// minHeight = info.getMinHeight();
		// }
		//
		// // CSS also has the min-width/min-height property. We should also get
		// that,
		// // and using the max of the "min-width" css property and the designer
		// specified min size.
		// int height = getLengthValue(style,ICSSPropertyID.ATTR_MIN_HEIGHT);
		// if(height > minHeight)
		// {
		// minHeight = height;
		// }
		// int width = getLengthValue(style,ICSSPropertyID.ATTR_MIN_WIDTH);
		// if(width > minWidth)
		// {
		// minWidth = width;
		// }
		// }

		// keep track of user specified size, this will be used when handling
		// the "overflow" CSS property.
		_userSpecifiedWidth = 0;
		_userSpecifiedHeight = 0;

		{
			int width = getLengthValue(style, ICSSPropertyID.ATTR_WIDTH);

			int availableWidth = line.getAvailableWidth()
					- style.getMarginInsets().getWidth();
			if (width <= 0) {
				// no width setting
				if (isCalculatingMaxWidth()) {
					_blockBox.setRecommendedWidth(Integer.MAX_VALUE);
					// _blockBox.setWidth( (minWidth>0?minWidth:0));
				} else {
					_blockBox.setRecommendedWidth(availableWidth);
					if (shouldExpand()) {
						_blockBox.setWidth(availableWidth);
					} else {
						// _blockBox.setWidth( (minWidth>0?minWidth:0));
					}
				}
			} else {
				int w = width;
				if (!style.isSizeIncludeBorderPadding()) {
					w += style.getBorderInsets().getWidth()
							+ style.getPaddingInsets().getWidth();
				}
				// XXX: should we use minWidth or follow user's choice?
				// if (w < minWidth)
				// {
				// w = minWidth;
				// }
				_userSpecifiedWidth = w;
				_blockBox.setWidth(w);
				_blockBox.setRecommendedWidth(w);
			}
		}

		{
			int height = getLengthValue(style, ICSSPropertyID.ATTR_HEIGHT);
			// Object height =
			// style.getStyleProperty(ICSSPropertyID.ATTR_HEIGHT);
			// Length heightLength = (height instanceof Length) ? (Length)
			// height : null;

			if (height <= 0) {
				// if (minHeight > 0)
				// {
				// // _blockBox.setHeight(minHeight);
				// _blockBox.setRecommendedHeight(minHeight);
				// }
				// else
				{
					_blockBox.setHeight(0);
					_blockBox.setRecommendedHeight(0);
				}
			} else {
				int h = height;
				if (handlingBorderForBlock()
						&& !style.isSizeIncludeBorderPadding()) {
					h += style.getBorderInsets().getHeight()
							+ style.getPaddingInsets().getHeight();
				}
				// XXX: should we follow minHeight or user's choice?
				// if (minHeight > h)
				// {
				// h = minHeight;
				// }
				_userSpecifiedHeight = h;
				_blockBox.setHeight(h);
				_blockBox.setRecommendedHeight(h);
			}
		}
		_blockBox.setMarginInsets(new Insets(style.getMarginInsets()));
		if (handlingBorderForBlock()) {
			BoxUtil.setupBorderPaddingMargin(_blockBox, getCSSStyle());
		}

		// as in designer, we don't want to the element to have zero size, so
		// set a minimun size here.
		// _blockBox.setWidth(Math.max(20, _blockBox.getWidth()));
		// int minHeight = getCSSStyle().getCSSFont().getFontSize() +
		// _blockBox.getBorderPaddingHeight();
		// _blockBox.setHeight(Math.max(minHeight, _blockBox.getHeight()));

		_blockBox._y = line._y;
		_blockBox._x = line._x;

		setBlockVerticalAlign(_blockBox);
	}

	/**
	 * @param style
	 * @param property
	 * @return the length value
	 */
	protected int getLengthValue(ICSSStyle style, String property) {
		int lengthValue = 0;
		if (style != null) {
			Object object = style.getStyleProperty(property);
			Length lengthObj = (object instanceof Length) ? (Length) object
					: null;

			if (lengthObj != null) {
				lengthValue = lengthObj.getValue();
				if (lengthObj.isPercentage()) {
					if (ICSSPropertyID.ATTR_WIDTH.equalsIgnoreCase(property)
							|| ICSSPropertyID.ATTR_MIN_WIDTH
									.equalsIgnoreCase(property)) {
						lengthValue = this.getFlowContext().getCurrentLine().getRecommendedContentWidth()
								* lengthValue / 100;
					} else if (ICSSPropertyID.ATTR_HEIGHT
							.equalsIgnoreCase(property)
							|| ICSSPropertyID.ATTR_MIN_HEIGHT
									.equalsIgnoreCase(property)) {
						// XXX: we should omit it because we don't support
						// percentage height now.
						lengthValue = 0;
					}
				}
			}
		}
		return lengthValue;
	}

	private void setBlockVerticalAlign(BlockBox box) {
		ICSSStyle style = getCSSStyle();
		if (style != null) {
			box.setVerticalAlignData(style
					.getStyleProperty(ICSSPropertyID.ATTR_VERTICAL_ALIGN));
		}
	}

	/**
	 * @see FlowContainerLayout#preLayout()
	 */
	protected void preLayout() {
		super.preLayout();
		_blockBox = new BlockBox();
		setupBlock();
		// Probably could setup current and previous line here, or just previous
	}

	// -------------------------------------------------------------------------------------------------------
	/**
	 * layout the lines in this layout
	 */
	protected void layoutLines() {
		List lines = _blockBox.getFragments();
		if (lines != null) {
			for (int i = 0; i < lines.size(); i++) {
				if (lines.get(i) instanceof LineBox) {
					layoutLine((LineBox) lines.get(i));
				}
			}
		}
	}

	/**
	 * Called by flush(), adds the BlockBox associated with this BlockFlowLayout
	 * to the current line and then ends the line.
	 */
	protected void endBlock() {
		layoutLines();
		ICSSStyle style = getCSSStyle();
		if (style != null) {
			int minWidth = 0;
			int minHeight = 0;
			// try to see whether there is any designer specified min size
			ITagEditInfo info = (ITagEditInfo) style
					.getAdapter(ITagEditInfo.class);
			if (info != null) {
				minWidth = info.getMinWidth();
				minHeight = info.getMinHeight();
			}

			// CSS also has the min-width/min-height property. We should also
			// get that,
			// and using the max of the "min-width" css property and the
			// designer specified min size.
			int height = getLengthValue(style, ICSSPropertyID.ATTR_MIN_HEIGHT);
			if (height > minHeight) {
				minHeight = height;
			}
			int width = getLengthValue(style, ICSSPropertyID.ATTR_MIN_WIDTH);
			if (width > minWidth) {
				minWidth = width;
			}
			if (minHeight > _blockBox.getHeight()) {
				_blockBox.setHeight(minHeight);
			}
			if (minWidth > _blockBox.getWidth()) {
				_blockBox.setWidth(minWidth);
			}
		}

		// reset scroll information.
		this._needHScroll = this._needVScroll = false;

		// ok, now we need to adjust the _blockBox's size according to the
		// "overflow" setting.
		// depends on different "overflow" style of this block, different sizing
		// policy may apply.
		// ICSSStyle style = this.getCSSStyle();
		if (style != null) {
			Object overflow = style
					.getStyleProperty(ICSSPropertyID.ATTR_OVERFLOW);
			if (ICSSPropertyID.VAL_HIDDEN.equals(overflow)) {
				if (_userSpecifiedWidth > 0) {
					_blockBox.setWidth(_userSpecifiedWidth);
				}
				if (_userSpecifiedHeight > 0) {
					_blockBox.setHeight(_userSpecifiedHeight);
				}
			} else if (ICSSPropertyID.VAL_SCROLL.equals(overflow)
					|| ICSSPropertyID.VAL_AUTO.equals(overflow)) {
				// adjust _needHScroll and _needVScroll
				if (_userSpecifiedWidth > 0
						&& _userSpecifiedWidth < _blockBox.getWidth()) {
					_needHScroll = true;
				}
				if (_userSpecifiedHeight > 0
						&& _userSpecifiedHeight < _blockBox.getHeight()) {
					_needVScroll = true;
				}
				if (_needHScroll && !_needVScroll) {
					if (_userSpecifiedHeight > 0
							&& _blockBox.getInternalContentHeight() >= 0
							&& _userSpecifiedHeight < _blockBox
									.getInternalContentHeight()
									+ _blockBox.getPaddingInsets().getHeight()
									+ BorderUtil.SCROLL_WIDTH) {
						_needVScroll = true;
					}
				}
				if (!_needHScroll && _needVScroll) {
					if (_userSpecifiedWidth > 0
							&& _blockBox.getInternalContentWidth() >= 0
							&& _userSpecifiedWidth < _blockBox
									.getInternalContentWidth()
									+ _blockBox.getPaddingInsets().getWidth()
									+ BorderUtil.SCROLL_WIDTH) {
						_needHScroll = true;
					}
				}

				if (_userSpecifiedWidth > 0) {
					_blockBox.setWidth(_userSpecifiedWidth);
				}
				if (_userSpecifiedHeight > 0) {
					_blockBox.setHeight(_userSpecifiedHeight);
				}
			}
		}

		if (getFlowContext().isCurrentLineOccupied()
				&& getFlowContext().getCurrentLine().getAvailableWidth() < _blockBox._width
						+ _blockBox.getMarginInsets().getWidth()) {
			getFlowContext().endLine();
		}
		if (!isInlineBlock()) {
			LineBox line = getFlowContext().getCurrentLine();
			line.setHorizonalData(getCSSStyle().getStyleProperty(
					ICSSPropertyID.ATTR_HORIZONTAL_ALIGN));
			line.setHtmlInitData(getCSSStyle().getHTMLelementInitValue(
					ICSSPropertyID.ATTR_HORIZONTAL_ALIGN));
			line.add(_blockBox);
			// getFlowContext().addToCurrentLine(_blockBox);
		} else {
			getFlowContext().addToCurrentLine(_blockBox);
		}
		getFlowContext().getCurrentLine().getMarginInsets().bottom = getCSSStyle()
				.getMarginInsets().bottom;

		if (!isInlineBlock()) {
			getFlowContext().endLine();
		}
	}

	/**
	 * @param line
	 */
	protected void layoutLine(LineBox line) {
		// currentLine.x = 0; //XXX: comment out, don't understand why set to 0,
		// because it has already
		// been set when setupLine(). And if do need, should
		// set to getBorderPaddingInsets().left
		// if (!isInlineBlock() && shouldExpand())
		// {
		// FIXME: currently we are using getRecommendedContentWidth,
		// what happen if after adding the new line, the new width is bigger
		// than
		// recommendedContentWidth? should we use getWidth() instead of
		// recommendedcontentWidth?
		Object textalign = line.getHorizonalData();
		if (textalign == null
				|| ICSSPropertyMeta.NOT_SPECIFIED.equals(textalign)) {
			textalign = (getCSSStyle()
					.getStyleProperty(ICSSPropertyID.ATTR_TEXTALIGN));
		}
		if (textalign == null
				|| ICSSPropertyMeta.NOT_SPECIFIED.equals(textalign)) {
			textalign = line.getHtmlInitData();
		}
		if (ICSSPropertyID.VAL_RIGHT.equals(textalign)) {
			line._x = _blockBox.getContentWidth() - line.getWidth();
		} else if (ICSSPropertyID.VAL_CENTER.equals(textalign)) {
			line._x = (_blockBox.getContentWidth() - line.getWidth()) / 2;
		}

		if (line._x < 0) {
			line._x = 0;
		}
		line.commit();
	}

	/**
	 * Adjust all fragments in the current line to have the same baseline. Do
	 * any additional adjustments, such as horizontal alignment.
	 */
	protected void addCurrentLine() {
		// The follow code is commented out, and moved into layoutLine(line)
		// called by endBlock().
		// since only when endBlock is called we really know how big is this
		// block box, and then can
		// do horizontal alignment.
		// // currentLine.x = 0; //XXX: comment out, don't understand why set to
		// 0, because it has already
		// // been set when setupLine(). And if do need, should
		// // set to getBorderPaddingInsets().left
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
		// _currentLine._x = _blockBox.getContentWidth() +
		// _blockBox.getBorderPaddingInsets().left - _currentLine.getWidth();
		// }
		// else if (textalign == ICSSPropertyID.VAL_CENTER)
		// {
		//
		// _currentLine._x = _blockBox.getBorderPaddingInsets().left +
		// (_blockBox.getContentWidth() - _currentLine.getWidth()) / 2;
		// }
		// if (_currentLine._x < 0)
		// _currentLine._x = 0;
		// }
		//
		// // FIXME: should check vertical alignment here?
		// _currentLine.commit();

		// layoutLine(_currentLine);
		_blockBox.add(_currentLine);
	}

	/**
	 * @see FlowContainerLayout#flush()
	 */
	protected void flush() {
		if (_currentLine != null && _currentLine.isOccupied()) {
			addCurrentLine();
		}
		endBlock();
	}

	/**
	 * @see FlowContainerLayout#cleanup()
	 */
	protected void cleanup() {
		_currentLine = _previousLine = null;
		_fontMetrices = null;
	}

	// ----------------------------------------------------------------------------------

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
		setFontinfoForLine(line);
		// line.validate();
	}

	private void setFontinfoForLine(LineBox line) {

		ICSSStyle style = getCSSStyle();
		if (style != null) {
			if (_fontMetrices == null) {
				// as getSwtFont is resource consuming, so we cache the
				// _fontMetrics.
				_fontMetrices = FigureUtilities.getFontMetrics(style
						.getCSSFont().getSwtFont());
			}
			line.setFontMetrics(_fontMetrices);
		}
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
	 * @see FlowContext#endLine()
	 */
	public void endLine() {
		// this is called from child layouts.
		// If there is no current line, state is equivalent to new line
		if (_currentLine == null) {
			return;
		}
		if (_currentLine.isOccupied()) {
			addCurrentLine(); // finalize the current line layout
		} else {
			_currentLine = null;
			return;
		}

		LineBox box = _currentLine;
		// _currentLine = _previousLine; //XXX: ???? why (yang)
		_previousLine = box;

		_currentLine = null;
		// setupLine(getCurrentLine());
	}

	/**
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#getCurrentY()
	 */
	public int getCurrentY() {
		return getCurrentLine()._y; // FIXME: margin of previous block?
	}

	int getLinePadding() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSLayout#useLocalCoordinates()
	 */
	public boolean useLocalCoordinates() {
		return true;
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
		List r = new ArrayList(1);
		r.add(_blockBox);
		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.ICSSLayout#postValidate()
	 */
	public void postValidate() {

		Rectangle r = new Rectangle(_blockBox._x, _blockBox._y, _blockBox
				.getWidth(), _blockBox.getHeight());
		getCSSFigure().setBounds(r);
		List list = getCSSFigure().getChildren();
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
		int width = Math.max(0, Math.max(_blockBox.getWidth(), _blockBox
				.getRecommendedWidth()));
		return width;
	}

	/**
	 * when the "overflow" is "scroll", we need to paint the scrollbar
	 */
	public void paintFigurePostClientArea(Graphics g) {
		ICSSStyle style = this.getCSSStyle();
		if (style != null) {
			Object overflow = style
					.getStyleProperty(ICSSPropertyID.ATTR_OVERFLOW);
			if (ICSSPropertyID.VAL_SCROLL.equals(overflow)
					|| ICSSPropertyID.VAL_AUTO.equals(overflow)) {
				if (this._needHScroll || this._needVScroll) {
					// as this is using localCoordinate, so translate to
					// relative to left/up corder of whole
					// blockbox.
					g.translate(-_blockBox.getBorderPaddingInsets().left,
							-_blockBox.getBorderPaddingInsets().top);

					Rectangle rect = new Rectangle(0, 0, _blockBox.getWidth(),
							_blockBox.getHeight());
					rect.crop(_blockBox.getBorderInsets());

					if (this._needHScroll && this._needVScroll) {
						BorderUtil.drawScrollBar(g, BorderUtil.SCROLL_WIDTH,
								rect, BorderUtil.BOTH);
					} else if (this._needHScroll) {
						BorderUtil.drawScrollBar(g, BorderUtil.SCROLL_WIDTH,
								rect, BorderUtil.HORIZONTAL_BAR);
					} else if (this._needVScroll) {
						BorderUtil.drawScrollBar(g, BorderUtil.SCROLL_WIDTH,
								rect, BorderUtil.VERTICAL_BAR);
					}
				}
			}
		}
	}
}
