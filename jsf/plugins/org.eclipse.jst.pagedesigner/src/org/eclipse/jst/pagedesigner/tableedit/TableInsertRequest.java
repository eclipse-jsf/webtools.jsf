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
