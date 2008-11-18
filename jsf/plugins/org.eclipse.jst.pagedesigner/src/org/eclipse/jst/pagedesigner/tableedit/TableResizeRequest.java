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

import org.eclipse.gef.Request;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableResizeRequest extends Request {
	private static final String TABLE_RESIZE_REQ = "Table Resize"; //$NON-NLS-1$

	private boolean _row;

	private int _index;

	private int _delta;

	/**
	 * @param isrow 
	 * @param index 
	 */
	public TableResizeRequest(boolean isrow, int index) {
		super(TABLE_RESIZE_REQ);
		this._row = isrow;
		this._index = index;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return _index;
	}

	/**
	 * @param index
	 */
	public void setIndex(int index) {
		this._index = index;
	}

	/**
	 * @return true if is row
	 */
	public boolean isRow() {
		return _row;
	}

	/**
	 * @param row
	 */
	public void setRow(boolean row) {
		this._row = row;
	}

	/**
	 * @return the delta
	 */
	public int getDelta() {
		return _delta;
	}

	/**
	 * @param delta
	 */
	public void setDelta(int delta) {
		this._delta = delta;
	}
}
