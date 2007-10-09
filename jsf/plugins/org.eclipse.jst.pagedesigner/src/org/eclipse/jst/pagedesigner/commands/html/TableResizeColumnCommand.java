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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.commands.CommandResources;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.dom.DOMStyleUtil;
import org.eclipse.jst.pagedesigner.dom.html.TableUtil;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableResizeColumnCommand extends DesignerCommand {
	private Element _table;

	private TableUtil _tableUtil;

	private int _columnIndex;

	private int _delta;

	/**
	 * @param viewer
	 * @param table
	 * @param columnIndex
	 * @param delta
	 */
	public TableResizeColumnCommand(IHTMLGraphicalViewer viewer, Element table,
			int columnIndex, int delta) {
		super(
				CommandResources
						.getString("TableResizeColumnCommand.Label.ResizeColumn"), viewer); //$NON-NLS-1$
		this._table = table;
		this._columnIndex = columnIndex;
		this._delta = delta;
		_tableUtil = new TableUtil(this._table);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
	 */
	protected ISelection getAfterCommandDesignerSelection() {
		return toDesignSelection(_table);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected void doExecute() {
		int index = this._columnIndex - 1;
		if (index < 0) {
			index = 0;
		}
		List list = _tableUtil.getColumnCells(index);
		Iterator itr = list.iterator();
		Element cell = null;
		while (itr.hasNext()) {
			Element ele = (Element) itr.next();
			if (ele.getTagName().equalsIgnoreCase("fake") || DOMUtil.getIntAttributeIgnoreCase(ele, IHTMLConstants.ATTR_COLSPAN, 1) > 1) //$NON-NLS-1$
			{
				continue;
			}
			cell = ele;
			break;
		}

		IFigure cellFigure = getFigureInfo(cell);
		int oldColumnWidth = cellFigure.getBounds().width;
		int cellPadding = cellFigure.getInsets().getWidth();
		int newWidth = oldColumnWidth + this._delta - cellPadding;
		if (this._columnIndex - 1 < 0) {
			newWidth = oldColumnWidth - this._delta - cellPadding;
		}
		Map map = new HashMap();
		if (newWidth > 0) {
			map.put(ICSSPropertyID.ATTR_WIDTH, newWidth + "px"); //$NON-NLS-1$
			DOMStyleUtil.insertStyle(cell, map);
		}
	}
}
