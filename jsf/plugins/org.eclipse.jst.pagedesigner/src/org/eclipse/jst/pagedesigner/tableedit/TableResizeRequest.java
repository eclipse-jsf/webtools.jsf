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
	public static final String TABLE_RESIZE_REQ = "Table Resize";

	private boolean _row;

	private int _index;

	private int _delta;

	/**
	 * 
	 */
	public TableResizeRequest(boolean isrow, int index) {
		super(TABLE_RESIZE_REQ);
		this._row = isrow;
		this._index = index;
	}

	public int getIndex() {
		return _index;
	}

	public void setIndex(int index) {
		this._index = index;
	}

	public boolean isRow() {
		return _row;
	}

	public void setRow(boolean row) {
		this._row = row;
	}

	public int getDelta() {
		return _delta;
	}

	public void setDelta(int delta) {
		this._delta = delta;
	}
}
