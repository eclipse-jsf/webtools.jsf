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

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.property.PositionMeta;
import org.eclipse.jst.pagedesigner.css2.property.VerticalAlignMeta;
import org.eclipse.jst.pagedesigner.css2.value.Length;
import org.eclipse.jst.pagedesigner.ui.preferences.PDPreferences;

/**
 * CSSLayout is the base layout manager for different CSS layouts, such as block
 * layout, inline layout (possible in the future table layout, etc)
 * 
 * @author mengbo
 */
public abstract class CSSLayout extends FlowFigureLayout implements FlowContext {
	private BlockFlowContext _absoluteContext;

	// when doing absolute layout, and if top/left are both "auto", it will be
	// relating to the normaly flow position. The following two fields try to
	// catch normal flow layout position.
	// int _xForAbsolute;
	// int _yForAbsolute;
	private FlowBox _boxForAbsolute;

	/**
	 * the current line
	 */
	protected LineBox _currentLine;

	private boolean _calculatingMaxWidth = false;

	/**
	 * @param flowFigure 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowFigureLayout#FlowFigureLayout(FlowFigure)
	 */
	protected CSSLayout(CSSFigure flowFigure) {
		super(flowFigure);
	}

	/**
	 * a shortcut method to get the style associated with the figure.
	 * 
	 * @return the css style
	 */
	public ICSSStyle getCSSStyle() {
		return getCSSFigure().getCSSStyle();
	}

	/**
	 * @return the absolute context
	 */
	protected final BlockFlowContext getAbsoluteContext() {
        return _absoluteContext;
    }

    /**
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#addToCurrentLine(FlowBox)
	 */
	public void addToCurrentLine(FlowBox block) {
		getCurrentLine().add(block);
	}

	/**
	 * Used by getCurrentLine().
	 */
	protected abstract void createNewLine();

	/**
	 * Used by getCurrentLine(int topmargin)
	 * 
	 * @param topMargin
	 */
	protected void createNewLine(int topMargin) {
		createNewLine();
	}

	/**
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#getCurrentLine()
	 */
	public LineBox getCurrentLine() {
		if (_currentLine == null) {
			createNewLine();
		}
		return _currentLine;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#getCurrentLine(int)
	 */
	public LineBox getCurrentLine(int topMargin) {
		if (_currentLine == null) {
			createNewLine(topMargin);
		}
		// if the current line only contains an empty string, reset the current
		// line using the given margin.
		else if (_currentLine.isEmptyStringLine()) {
			List list = _currentLine.getFragments();
			createNewLine(topMargin);
			_currentLine._fragments.addAll(list);
		}
		return _currentLine;
	}

	/**
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#isCurrentLineOccupied
	 */
	public boolean isCurrentLineOccupied() {
		return _currentLine != null && _currentLine.isOccupied();
	}

	/**
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowFigureLayout#layout()
	 */
	protected void layout() {
		preLayout();
		layoutChildren();
		flush();
		cleanup();
	}

	/**
	 * @return true if is absolute position
	 */
	protected final boolean isAbsolutePosition() {
		ICSSStyle style = getCSSStyle();

		// FIXME: Some layout don't support absolute, need check here
		if (style != null) {
			Object obj = style.getStyleProperty(ICSSPropertyID.ATTR_POSITION);
			if (PositionMeta.ABSOLUTE.equals(obj)
					|| PositionMeta.FIXED.equals(obj)) 
			{
			    PDPreferences prefs = new PDPreferences();
			    return prefs.isCssAbsolutePositioningEnabled();
			}
		}
		return false;
	}

	/**
	 * Child class could override this method.
	 * 
	 * @return true if supports absolute position  
	 */
	protected boolean supportAbsolutePosition() {
		if (findContainingPositionedFigure() == null) {
			return false;
		}
		return true;
	}

	/**
	 * Perform a prelayout
	 */
	protected void preLayout() {
		ICSSStyle style = this.getCSSStyle();
		if (style != null) {
			style.processCounters();
		}

		if (isAbsolutePosition()) {
			FlowContext parentFigureContext = getParentFigureContext();
			_absoluteContext = new BlockFlowContext(parentFigureContext, style);
			_boxForAbsolute = new FlowBox();// size is 0. Just as a flag, so
			// later we
			// could figure out where will this figure be
			// be put in case of not absolute
			_boxForAbsolute.setVerticalAlignData(VerticalAlignMeta.TOP);
			parentFigureContext.addToCurrentLine(_boxForAbsolute);
		} else {
			_absoluteContext = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowFigureLayout#getFlowContext()
	 */
	public FlowContext getFlowContext() {
		if (_absoluteContext != null) {
			return _absoluteContext;
		}
        return getOriginalFlowContext();
	}

	/**
	 * @return the flow context
	 */
	private FlowContext getParentFigureContext() {
		return super.getFlowContext();
	}

	final void postValidateForAbsolute() {
		if (_absoluteContext != null) {
			ICSSStyle style = this.getCSSStyle();

			_absoluteContext.endBlock();

			int xOffset;
			int yOffset;

			ICSSFigure containingPositionedFigure = findContainingPositionedFigure();
			IFigure parentFigure = this.getCSSFigure().getParent();

			xOffset = calculatePositionRelativeToParent(style,
					containingPositionedFigure, parentFigure, true);
			yOffset = calculatePositionRelativeToParent(style,
					containingPositionedFigure, parentFigure, false);
			move(_absoluteContext._blockBox, xOffset, yOffset);
		}
	}

	/**
	 * @param style
	 * @param containingPositionedFigure
	 * @param parentFigure
	 * @return
	 */
	private int calculatePositionRelativeToParent(ICSSStyle style,
			ICSSFigure containingPositionedFigure, IFigure parentFigure,
			boolean horizontal) {
		int xOffset;
		Object left = horizontal ? style
				.getStyleProperty(ICSSPropertyID.ATTR_LEFT) : style
				.getStyleProperty(ICSSPropertyID.ATTR_TOP);
		Object right = horizontal ? style
				.getStyleProperty(ICSSPropertyID.ATTR_RIGHT) : style
				.getStyleProperty(ICSSPropertyID.ATTR_BOTTOM);

		if (!(left instanceof Length) && !(right instanceof Length)) {
			// _boxForAbsolute partipated the layout of the parent figure, and
			// is already relative to parent.
			return horizontal ? _boxForAbsolute._x : _boxForAbsolute._y;
		}

		// ok, user specified left or right. let's calculate the left
		int leftValue;
		if (left instanceof Length) {
			Length leftLength = (Length) left;
			leftValue = leftLength.getValue();
			if (leftLength.isPercentage()) {
				leftValue = (horizontal ? containingPositionedFigure
						.getBounds().width : containingPositionedFigure
						.getBounds().height)
						* leftValue / 100;
			}
		} else {
			Length rightLength = (Length) right;
			int lengthValue = rightLength.getValue();
			if (rightLength.isPercentage()) {
				lengthValue = (horizontal ? containingPositionedFigure
						.getBounds().width : containingPositionedFigure
						.getBounds().height)
						* lengthValue / 100;
			}

			if (horizontal) {
				leftValue = containingPositionedFigure.getBounds().width
						- _absoluteContext._blockBox.getWidth() - lengthValue;
			} else {
				leftValue = containingPositionedFigure.getBounds().height
						- _absoluteContext._blockBox.getHeight() - lengthValue;
			}

		}

		// xOffset is relative to the first box of the containing figure
		List fragments = containingPositionedFigure
				.getFragmentsForRead();
		if (fragments.size() > 0) {
			FlowBox box = (FlowBox) fragments.get(0);
			// box._x is the x location relative to containingPositionedFigure's
			// parent.
			// so now xOffset is relative to containingPositionedFigure's
			// parent.
			xOffset = (horizontal ? box._x : box._y) + leftValue;
		} else {
			xOffset = leftValue; // should not happen.
		}
		Point p;
		if (horizontal) {
			p = new Point(xOffset, 0);
		} else {
			p = new Point(0, xOffset);
		}
		containingPositionedFigure.translateFromParent(p);
		containingPositionedFigure.translateToAbsolute(p);
		parentFigure.translateToRelative(p);
		return horizontal ? p.x : p.y;
	}

	/**
	 * @return
	 */
	private ICSSFigure findContainingPositionedFigure() {
		IFigure figure = this.getCSSFigure().getParent();
		while (figure instanceof ICSSFigure) {
			return (ICSSFigure) figure;
			// ICSSStyle style = ((ICSSFigure) figure).getCSSStyle();
			// if (DisplayToLayout.isPositioned(style))
			// {
			// return (ICSSFigure) figure;
			// }
			// figure = figure.getParent();
		}
		return null;

	}

	/**
	 * @param resultBox
	 * @param x
	 * @param y
	 */
	private void move(CompositeBox compBox, int x, int y) {
		compBox._x += x;
		compBox._y += y;
		List list = compBox.getFragments();
		for (int i = 0; i < list.size(); i++) {
			FlowBox box = (FlowBox) list.get(i);

			if (box instanceof CompositeBox && !(box instanceof BlockBox)) {
				move((CompositeBox) box, x, y);
			} else {
				box._x += x;
				box._y += y;
			}
		}
	}

	/**
	 * Layout all children.
	 */
	protected void layoutChildren() {
		List children = getFlowFigure().getChildren();
		for (int i = 0; i < children.size(); i++) {
			Figure f = (Figure) children.get(i);
			f.invalidate();
			f.validate();
		}
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

	/**
	 * @param c
	 */
	public void setCalculatingMaxWidth(boolean c) {
		_calculatingMaxWidth = c;
	}

	/**
	 * @return the calculated maximum width
	 */
	public boolean getCalcuatingMaxWidth() {
		return _calculatingMaxWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowContext#isCalculatingMaxWidth()
	 */
	public boolean isCalculatingMaxWidth() {
		if (_calculatingMaxWidth) {
			return true;
		} else if (this.getFlowContext() == null) {
			return false;
		} else {
			return this.getFlowContext().isCalculatingMaxWidth();
		}
	}

	/**
	 * Called after {@link #layoutChildren()}when all children have been laid
	 * out. This method exists to flush the last line.
	 */
	protected abstract void flush();

	/**
	 * Flush anything pending and free all temporary data used during layout.
	 */
	protected abstract void cleanup();

	// ------------------------------------------------------------------------------------

	/**
	 * @return the css figure
	 */
	protected final CSSFigure getCSSFigure() {
		return (CSSFigure) getFlowFigure();
	}

	/**
	 * 
	 * @return the fragments for read
	 */
	public abstract List getFragmentsForRead();

	/**
	 * postValidate the child figures of this CSSFigure. Normally layout fall
	 * into the first category need implement this method.
	 */
	public abstract void postValidate();

	/**
	 * setBounds is called on the CSSFigure. Normally layout fall into the
	 * second category need implement this method.
	 * 
	 * @param rect
	 * @param invalidate
	 */
	public void setBoundsCalled(Rectangle rect, boolean invalidate) {
        // TODO: dead?
	}

	/**
	 * Child class can override this. Normally block figure will return true.
	 * 
	 * @return true if should use local coordinates
	 */
	protected boolean useLocalCoordinates() {
		return false;
	}

	/**
	 * If CSSLayout will call paint rountine to draw Border for its box, this
	 * method will return true, else return false, for example,the input file
	 * will return false.
	 * 
	 * @return true if handling border block
	 */
	protected boolean handlingBorderForBlock() {
		return true;
	}

	/**
	 * This method is called when the corresponding figure is revalidated.
	 * 
	 */
	protected void figureRevalidate() {
		// child class can override.
	}
}
