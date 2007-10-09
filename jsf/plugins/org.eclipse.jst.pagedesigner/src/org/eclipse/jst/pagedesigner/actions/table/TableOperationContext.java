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
package org.eclipse.jst.pagedesigner.actions.table;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jst.pagedesigner.css2.layout.CSSFigure;
import org.eclipse.jst.pagedesigner.css2.layout.table.CSSTableCellLayout;
import org.eclipse.jst.pagedesigner.css2.layout.table.CSSTableLayout2;
import org.eclipse.jst.pagedesigner.css2.layout.table.TableCellInfo;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;

/**
 * 
 * @author mengbo
 * @version 1.5
 */
/*package*/ class TableOperationContext {
	private ElementEditPart _tablePart;

	private int _rowIndex;

	private int _columnIndex;

	/**
	 * @param part
	 * @return the operation context for part
	 */
	public static TableOperationContext getTableOperationContext(EditPart part) {
		EditPart originalPart = part;
		ElementEditPart tablePart = null;
		CSSTableLayout2 tableLayout = null;

		while (part.getParent() instanceof ElementEditPart) {
			part = part.getParent();
			IFigure figure = ((GraphicalEditPart) part).getFigure();

			if (figure.getLayoutManager() instanceof CSSTableLayout2) {
				tableLayout = (CSSTableLayout2) figure.getLayoutManager();
				tablePart = ((ElementEditPart) part);
				break;
			}
		}
		if (tablePart == null) {
			return null;
		}

		part = originalPart;

		IFigure figure = ((GraphicalEditPart) originalPart).getFigure();
		while (figure instanceof CSSFigure) {
			if (figure.getLayoutManager() instanceof CSSTableCellLayout) {
				CSSTableCellLayout cellLayout = (CSSTableCellLayout) figure
						.getLayoutManager();
				if (cellLayout.getTableLayout() == tableLayout) {
					// ok, we found.
					TableCellInfo cellInfo = cellLayout.getTableCellInfo();
					if (cellInfo == null) {
						return null;
					}
                    TableOperationContext context = new TableOperationContext();
                    context._tablePart = tablePart;
                    context._rowIndex = cellInfo.getRowIndex();
                    context._columnIndex = cellInfo.getColumnIndex();
                    return context;
				}
                return null;
			}
			figure = figure.getParent();
		}
		return null;
	}

    ElementEditPart getTablePart() {
        return _tablePart;
    }

    int getRowIndex() {
        return _rowIndex;
    }

    int getColumnIndex() {
        return _columnIndex;
    }
	
	
}
