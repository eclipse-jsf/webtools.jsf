/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.css2.layout.table;

import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.layout.ICSSFigure;

/**
 * @author mengbo
 * @version 1.5
 */
/*package*/ class TableItemInfo 
{
	private ICSSFigure _figure;

	/**
	 * @param figure 
	 */
	public TableItemInfo(ICSSFigure figure) {
		_figure = figure;
	}

	/**
	 * @return the figure
	 */
	public ICSSFigure getFigure() {
		return _figure;
	}

	/**
	 * @return the style
	 */
	public ICSSStyle getStyle() {
		return _figure.getCSSStyle();
	}
}
