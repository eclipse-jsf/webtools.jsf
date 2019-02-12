/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.dom.html;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableChildElementPosition {
	private int _rowIndex = -10;

	private int _columnIndex = -10;

	/**
	 * @return Returns the _columnIndex.
	 */
	public int getColumnIndex() {
		return _columnIndex;
	}

	/**
	 * @param index
	 *            The _columnIndex to set.
	 */
	public void setColumnIndex(int index) {
		_columnIndex = index;
	}

	/**
	 * @return Returns the _rowIndex.
	 */
	public int getRowIndex() {
		return _rowIndex;
	}

	/**
	 * @param index
	 *            The _rowIndex to set.
	 */
	public void setRowIndex(int index) {
		_rowIndex = index;
	}
}
