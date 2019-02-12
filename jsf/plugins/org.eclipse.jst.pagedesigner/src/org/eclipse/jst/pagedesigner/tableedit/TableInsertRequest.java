/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.tableedit;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableInsertRequest extends TableRowColumnRequest {
	/**
	 * 
	 */
	private static final String TABLE_INSERT_REQUEST = "Table Insert"; //$NON-NLS-1$

	boolean _before;

	/**
	 * @param row 
	 * @param index 
	 * @param before 
	 * 
	 */
	public TableInsertRequest(boolean row, int index, boolean before) {
		super(TABLE_INSERT_REQUEST, row, index);
		this._before = before;
	}

	/**
	 * @return true if is before
	 */
	public boolean isBefore() {
		return _before;
	}

	/**
	 * @param before
	 */
	public void setBefore(boolean before) {
		this._before = before;
	}

}
