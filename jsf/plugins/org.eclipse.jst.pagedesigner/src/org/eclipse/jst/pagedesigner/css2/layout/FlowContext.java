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

/**
 * Copied from draw2d. Enhanced to meet page designer's needs. The context that
 * a {@link FlowFigureLayout}uses to perform its layout.
 * <P>
 * WARNING: This interface is not intended to be implemented by clients. It
 * exists to define the API between the layout and its context.
 */
public interface FlowContext {

	/**
	 * Adds the given box into the current line.
	 * 
	 * @param box
	 *            the FlowBox to add
	 */
	void addToCurrentLine(FlowBox box);

	/**
	 * The current line should be committed if it is occupied, and then set to
	 * <code>null</code>. Otherwise, do nothing.
	 */
	void endLine();

	/**
	 * Obtains the current line, creating a new line if there is no current
	 * line. if create a new line, the new line's x will be set correctly
	 * without considering the new element's left margin. Also, if create new
	 * line, it will treat as the new line's top margin is 0.
	 * 
	 * @return the current line
	 */
	LineBox getCurrentLine();

	/**
	 * if create a new line, the new line's x will be set correctly without
	 * considering the new element's left margin.
	 * 
	 * @param topMargin
	 * @return the current line
	 */
	LineBox getCurrentLine(int topMargin);

	/**
	 * Returns the current Y value.
	 * 
	 * @return the current Y value
	 */
	int getCurrentY();

	/**
	 * @return <code>true</code> if the current line contains any fragments
	 */
	boolean isCurrentLineOccupied();

	/**
	 * @return the last margin right coord
	 */
	int getLastMarginRight();

	/**
	 * when layout table, we need to calculate max width of a cell. This is done
	 * by don't break line (other than explicit required). Currently, the
	 * solution is to make the recommended width to be very big, and when create
	 * block element we don't set the block element's size to be recommended
	 * width. Please see CSSBlockFlowLayout
	 * 
	 * @return true if is calculating max width
	 */
	boolean isCalculatingMaxWidth();

	/**
	 * when calculating percentage width, we need the container width
	 * 
	 * @return container width
	 */
	int getContainerWidth();
}
