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
public class TableRowColumnDeleteRequest extends TableRowColumnRequest {
	private static final String TABLE_ROWCOLUMN_DELETE = "Table RowColumn Delete"; //$NON-NLS-1$

	/**
	 * @param row
	 * @param index
	 */
	public TableRowColumnDeleteRequest(boolean row, int index) {
		super(TABLE_ROWCOLUMN_DELETE, row, index);
	}
}
