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

/**
 * A layout for FlowFigures with children.
 * <P>
 * WARNING: This class is not intended to be subclassed by clients.
 * 
 * @author mengbo
 * @since 2.1
 */
public abstract class FlowContainerLayout extends FlowFigureLayout implements
		FlowContext {
//	private static Logger _log = PDPlugin.getLogger(FlowContainerLayout.class);

	/**
	 * the current line
	 */
	protected LineBox _currentLine;

	private boolean _calculatingMaxWidth;

	/**
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowFigureLayout#FlowFigureLayout(FlowFigure)
	 */
	protected FlowContainerLayout(FlowFigure flowFigure) {
		super(flowFigure);
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
	 * Called before layoutChildren() to setup any necessary state.
	 */
	protected abstract void preLayout();

	/**
	 * Called after {@link #layoutChildren()}when all children have been laid
	 * out. This method exists to flush the last line.
	 */
	protected abstract void flush();

	/**
	 * Flush anything pending and free all temporary data used during layout.
	 */
	protected abstract void cleanup();
}
