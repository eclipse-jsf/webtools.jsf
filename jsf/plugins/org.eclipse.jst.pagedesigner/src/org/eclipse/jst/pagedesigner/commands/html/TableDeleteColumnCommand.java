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
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.commands.CommandResources;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.dom.html.TableUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableDeleteColumnCommand extends DesignerCommand {
	private Element _tableEle;

	private int _columnIndex;

	private TableUtil _tableUtil;

	/**
	 * @param viewer
	 * @param dataTable
	 * @param index
	 */
	public TableDeleteColumnCommand(IHTMLGraphicalViewer viewer,
			Element dataTable, int index) {
		super(
				CommandResources
						.getString("TableDeleteColumnCommand.Label.DeleteColumn"), viewer); //$NON-NLS-1$
		this._tableEle = dataTable;
		this._columnIndex = index;
		this._tableUtil = new TableUtil(this._tableEle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		if (this._columnIndex < 0) {
			return false;
		}
		boolean hasColSpan = _tableUtil.hasColumnSpanElement(this._columnIndex);
		boolean isAffectedByColSpan = _tableUtil
				.isAffectedByColSpan(this._columnIndex);
		if (hasColSpan || isAffectedByColSpan) {
			return false;
		}

		return super.canExecute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
	 */
	protected ISelection getAfterCommandDesignerSelection() {
		return toDesignSelection(_tableEle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected void doExecute() {
		List trList = new ArrayList();
		TableUtil.getTrElements(this._tableEle, trList);

		List[] lists = _tableUtil.getTrCellLists();

		for (int i = 0, size = trList.size(); i < size; i++) {
			Element tr = (Element) trList.get(i);
			List cells = lists[i];
			if (cells.size() <= this._columnIndex) {
				continue;
			}
			Element cell = (Element) cells.get(this._columnIndex);
			if (!cell.getTagName().equalsIgnoreCase("fake")) //$NON-NLS-1$
			{
				tr.removeChild(cell);
			}
		}
	}

}
