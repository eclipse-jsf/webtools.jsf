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
public class TableRowColumnRequest extends Request {
	int _index;

	boolean _row;

	/**
	 * @param type
	 * @param row 
	 * @param index 
	 */
	public TableRowColumnRequest(Object type, boolean row, int index) {
		super(type);
		this._row = row;
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

}
