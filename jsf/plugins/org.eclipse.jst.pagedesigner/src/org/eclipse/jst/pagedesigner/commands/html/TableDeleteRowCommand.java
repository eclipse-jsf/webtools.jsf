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
public class TableDeleteRowCommand extends DesignerCommand {
	Element _tableEle;

	int _rowIndex;

	/**
	 * @param viewer
	 * @param table
	 * @param index
	 */
	public TableDeleteRowCommand(IHTMLGraphicalViewer viewer, Element table,
			int index) {
		super(CommandResources
				.getString("TableDeleteRowCommand.Label.DeleteRow"), viewer); //$NON-NLS-1$
		this._tableEle = table;
		this._rowIndex = index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		if (this._rowIndex < 0) {
			return false;
		}
		List list = new ArrayList();
		TableUtil.getTrElements(this._tableEle, list);
		// int index = TableUtil.countRowIndexInDOMTree(this._tableEle,
		// this._rowIndex);
		int index = this._rowIndex;
		Element tr = (Element) list.get(index);
		boolean hasRowSpan = TableUtil.hasRowSpanElement(tr);
		boolean isAffectedByRowSpan = TableUtil.isAffectedByRowSpan(list, tr,
				index);
		if (hasRowSpan || isAffectedByRowSpan) {
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
		List list = new ArrayList();
		TableUtil.getTrElements(this._tableEle, list);
		// int index = TableUtil.countRowIndexInDOMTree(this._tableEle,
		// this._rowIndex);
		int index = this._rowIndex;
		Element tr = (Element) list.get(index);
		tr.getParentNode().removeChild(tr);
		formatNode(this._tableEle);
	}

}
