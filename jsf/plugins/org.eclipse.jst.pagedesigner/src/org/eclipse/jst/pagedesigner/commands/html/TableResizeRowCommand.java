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
package org.eclipse.jst.pagedesigner.commands.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.commands.CommandResources;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.dom.DOMStyleUtil;
import org.eclipse.jst.pagedesigner.dom.html.TableUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableResizeRowCommand extends DesignerCommand {
	private Element _table;

	private int _rowIndex;

	private int _delta;

	/**
	 * @param viewer
	 * @param table
	 * @param rowIndex
	 * @param delta
	 */
	public TableResizeRowCommand(IHTMLGraphicalViewer viewer, Element table,
			int rowIndex, int delta) {
		super(CommandResources
				.getString("TableResizeRowCommand.Label.ResizeColumn"), viewer); //$NON-NLS-1$
		this._table = table;
		this._rowIndex = rowIndex;
		this._delta = delta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
	 */
	protected ISelection getAfterCommandDesignerSelection() {
		return toDesignSelection(this._table);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected void doExecute() {
		int index = this._rowIndex - 1;
		if (index < 0) {
			index = 0;
		}
		// int domIndex = TableUtil.countRowIndexInDOMTree(this._table, index);
		int domIndex = index;

		List list = new ArrayList();
		TableUtil.getTrElements(this._table, list);
		Element tr = (Element) list.get(domIndex);

		IFigure cellFigure = getFigureInfo(tr);
		int oldRowHeight = cellFigure.getBounds().height;
		int cellPadding = cellFigure.getInsets().getHeight();
		int newHeight = oldRowHeight + this._delta - cellPadding;
		if (this._rowIndex - 1 < 0) {
			newHeight = oldRowHeight - this._delta - cellPadding;
		}
		Map map = new HashMap();
		if (newHeight > 0) {
			map.put(ICSSPropertyID.ATTR_HEIGHT, newHeight + "px"); //$NON-NLS-1$
			DOMStyleUtil.insertStyle(tr, map);
		}
	}
}
