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
package org.eclipse.jst.pagedesigner.dom.html;

/**
 * @author mengbo
 * @version 1.5
 */
public class ColStructure implements Comparable {
	private int _column;

	private int _colSpan;

	public ColStructure(int column, int colSpan) {
		this._column = column;
		this._colSpan = colSpan;
	}

	public int getColSpan() {
		return _colSpan;
	}

	public void setColSpan(int colSpan) {
		this._colSpan = colSpan;
	}

	public int getColumn() {
		return _column;
	}

	public void setColumn(int column) {
		this._column = column;
	}

	public int compareTo(Object o) {
		ColStructure cs = (ColStructure) o;
		if (this._column > cs.getColumn()) {
			return 1;
		} else if (this._column == cs.getColumn()) {
			return 0;
		} else {
			return -1;
		}
	}
}
