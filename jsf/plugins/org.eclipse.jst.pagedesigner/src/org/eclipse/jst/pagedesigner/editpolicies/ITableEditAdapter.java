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
	public int getColumnCount();

	public int getRowCount();

	public void insertColumn(int atPosition);

	public void insertRow(int rowPosition);

	/**
	 * @param columnIndex
	 * @return
	 */
	public int getColumnResizeStart(int columnIndex);

	/**
	 * @return
	 */
	public int getColumnResizeWidth();

	/**
	 * @param columnIndex
	 * @return
	 */
	public int getColumnStart(int columnIndex);

	/**
	 * @param columnIndex
	 * @return
	 */
	public int getColumnWidth(int columnIndex);

	/**
	 * @param rowIndex
	 * @return
	 */
	public int getRowStart(int rowIndex);

	/**
	 * @param rowIndex
	 * @return
	 */
	public int getRowHeight(int rowIndex);

	/**
	 * @param rowIndex
	 * @return
	 */
	public int getRowResizeStart(int rowIndex);

	/**
	 * @return
	 */
	public int getRowResizeWidth();
}
