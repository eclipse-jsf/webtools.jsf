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
package org.eclipse.jst.pagedesigner.css2.layout.table;

import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.utils.IntFlexArray;

/**
 * @author mengbo
 * @version 1.5
 */
/*package*/ class TableInfoContext {
	static Logger _log = PDPlugin.getLogger(TableInfoContext.class);

	int _currentCol = 0;

	int _currentRow = 0;

	IntFlexArray _array = new IntFlexArray();

	int _colCount = 0;

	int _rowCount = 0;

	/**
	 * 
	 */
	public TableInfoContext() {
        // do nothing
	}

	/**
	 * @return the flex array
	 */
	public IntFlexArray getIntFlexArray() {
		return _array;
	}

	/**
	 * @return the current column
	 */
	public int getCurrentCol() {
		return _currentCol;
	}

	/**
	 * @param currentcol
	 */
	public void setCurrentCol(int currentcol) {
		_currentCol = currentcol;
	}

	/**
	 * @return the current row
	 */
	public int getCurrentRow() {
		return _currentRow;
	}

	/**
	 * @return the column count
	 */
	public int getColumnCount() {
		return _colCount;
	}

	/**
	 * 
	 */
	public void finishRow() {
		if (_currentCol > _colCount) {
			_colCount = _currentCol;
		}
		_currentCol = 0;
		_currentRow++;
		for (int i = 0; i < _colCount; i++) {
			if (_array.getAt(i) > 0) {
				_array.setAt(i, _array.getAt(i) - 1);
			}
		}
	}

	/**
	 * 
	 */
	public void finishTable() {
		// do some checking here.
		int additionalRow = 0;
		for (int i = 0; i < _colCount; i++) {
			if (_array.getAt(i) > additionalRow) {
				additionalRow = _array.getAt(i);
			}
		}
		_rowCount = _currentRow + additionalRow;
	}

	/**
	 * 
	 */
	public void finishRowGroup() {
        // TODO: does nothing; only called in one places
	}

	/**
	 * @return the row count
	 */
	public int getRowCount() {
		return _rowCount;
	}
}
