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

import org.eclipse.draw2d.PositionConstants;

/**
 * A <code>FlowFigure</code> represented by a single {@link BlockBox}fragment
 * containing one or more lines. A BlockFlow is a creator of LineBoxes, which
 * its children require during layout. A BlockFlow can be thought of as a
 * paragraph.
 * <P>
 * BlockFlows should be nested inside other BlockFlows, but it is also valid to
 * place them in InlineFlows. {@link FlowPage}can be used as a "root" block and
 * can be added to normal draw2d Figures.
 * <P>
 * Only {@link FlowFigure}s can be added to a BlockFlow.
 */
/*package*/ class BlockFlow extends FlowFigure {

	final BlockBox _blockBox;

	private int _aligment;

	/**
	 * Constructs a new BlockFlow.
	 */
	public BlockFlow() {
		setLayoutManager(createDefaultFlowLayout());
		_blockBox = createBlockBox();
	}

	BlockBox createBlockBox() {
		return new BlockBox();
	}

	/**
	 * @return the default flow layout
	 * 
	 */
	protected FlowFigureLayout createDefaultFlowLayout() {
		return new BlockFlowLayout(this);
	}

	/**
	 * Returns the BlockBox associated with this.
	 * 
	 * @return This BlockFlow's BlockBox
	 */
	protected BlockBox getBlockBox() {
		return _blockBox;
	}

	/**
	 * Returns the horizontal aligment.
	 * 
	 * @return the hotizontal aligment
	 */
	public int getHorizontalAligment() {
		return _aligment & PositionConstants.LEFT_CENTER_RIGHT;
	}

	/**
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowFigure#postValidate()
	 */
	public void postValidate() {
		setBounds(getBlockBox().toRectangle().expand(getInsets()));
		List v = getChildren();
		for (int i = 0, n = v.size(); i < n; i++) {
			((FlowFigure) v.get(i)).postValidate();
		}
	}

	/**
	 * Sets the horitontal aligment of the block. Valid values are:
	 * <UL>
	 * <LI>{@link org.eclipse.draw2d.PositionConstants#LEFT}
	 * <LI>{@link org.eclipse.draw2d.PositionConstants#RIGHT}
	 * <LI>{@link org.eclipse.draw2d.PositionConstants#CENTER}
	 * 
	 * @param value
	 *            the aligment
	 */
	public void setHorizontalAligment(int value) {
		if (!(value == PositionConstants.LEFT
				|| value == PositionConstants.RIGHT || value == PositionConstants.CENTER)) {
			throw new IllegalArgumentException(
					"Horizontal Aligment must be one of: LEFT, CENTER, RIGHT"); //$NON-NLS-1$
		}
		this._aligment &= ~PositionConstants.LEFT_CENTER_RIGHT;
		this._aligment |= value;
		revalidate();
	}

	/**
	 * @see org.eclipse.draw2d.Figure#useLocalCoordinates()
	 */
	protected boolean useLocalCoordinates() {
		return true;
	}

}
