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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jst.pagedesigner.css2.layout.table.CSSTableLayout2;
import org.eclipse.jst.pagedesigner.editpolicies.ITableEditAdapter;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableEditHelper {
	/**
	 * This method will return null if the editpart is not a table.
	 * 
	 * @param tablePart
	 * @return the adapter
	 */
	public static ITableEditAdapter getTableEditAdapter(
			GraphicalEditPart tablePart) {
		IFigure figure = tablePart.getFigure();
		LayoutManager layout = figure.getLayoutManager();
		if (layout instanceof CSSTableLayout2) {
			return new TableEditAdapter((CSSTableLayout2) layout);
		}
		return null;
	}
}
