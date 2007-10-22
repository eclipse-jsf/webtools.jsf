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
package org.eclipse.jst.pagedesigner.editpolicies;

/**
 * @author mengbo
 * @version 1.5
 */
public interface ITableEditAdapter {
	/**
	 * @return the column count
	 */
	public int getColumnCount();

	/**
	 * @return the row count
	 */
	public int getRowCount();

	/**
	 * @param atPosition
	 */
	public void insertColumn(int atPosition);

	/**
	 * @param rowPosition
	 */
	public void insertRow(int rowPosition);

	/**
	 * @param columnIndex
	 * @return the starting point for resize
	 */
	public int getColumnResizeStart(int columnIndex);

	/**
	 * @return the resize width
	 */
	public int getColumnResizeWidth();

	/**
	 * @param columnIndex
	 * @return column start
	 */
	public int getColumnStart(int columnIndex);

	/**
	 * @param columnIndex
	 * @return the column width
	 */
	public int getColumnWidth(int columnIndex);

	/**
	 * @param rowIndex
	 * @return the row start
	 */
	public int getRowStart(int rowIndex);

	/**
	 * @param rowIndex
	 * @return the row height
	 */
	public int getRowHeight(int rowIndex);

	/**
	 * @param rowIndex
	 * @return starting point of the row resize
	 */
	public int getRowResizeStart(int rowIndex);

	/**
	 * @return the row resize width
	 */
	public int getRowResizeWidth();
}
