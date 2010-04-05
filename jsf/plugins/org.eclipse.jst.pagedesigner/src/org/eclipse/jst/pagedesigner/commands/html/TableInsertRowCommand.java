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
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.commands.CommandResources;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.dom.html.TableUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableInsertRowCommand extends DesignerCommand {
	private Element _tableEle;

	private int _rowIndex;

	private boolean _isBefore;

	private TableUtil _tableUtil;

	/**
	 * @param viewer
	 * @param table
	 * @param index
	 * @param isBefore 
	 */
	public TableInsertRowCommand(IHTMLGraphicalViewer viewer, Element table,
			int index, boolean isBefore) {
		super(CommandResources
				.getString("TableInsertRowCommand.Label.InsertRow"), viewer); //$NON-NLS-1$
		this._tableEle = table;
		this._rowIndex = index;
		this._isBefore = isBefore;
		this._tableUtil = new TableUtil(this._tableEle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		if (this._rowIndex < 0 && this._rowIndex != -10) {
			return false;
		}
		List list = new ArrayList();
		TableUtil.getTrElements(this._tableEle, list);
		// if the row neither at the beginning nor the end then count
		if (_rowIndex > 0 && _rowIndex < list.size()) {
			// int index = TableUtil.countRowIndexInDOMTree(this._tableEle,
			// this._rowIndex);
			int index = this._rowIndex;
			Element tr = (Element) list.get(index);
			boolean isAffectedByRowSpan = TableUtil.isAffectedByRowSpan(list,
					tr, index);
			if (isAffectedByRowSpan) {
				return false;
			}

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
		Element insertElement = createDefaultElement();

		if (this._rowIndex < list.size()) {
			if (this._rowIndex != -10) {
				// int index = TableUtil.countRowIndexInDOMTree(this._tableEle,
				// this._rowIndex);
				int index = this._rowIndex;
				Element tr = (Element) list.get(index);
				Element nextTr = tr;
	
				// int headRows = TableUtil.countSectionRows(this._tableEle,
				// IHTMLConstants.TAG_THEAD);
				// int footRows = TableUtil.countSectionRows(this._tableEle,
				// IHTMLConstants.TAG_TFOOT);
				if (!_isBefore) {
					int parentIndex = index - 1;
					/**
					 * doesn't need any more,since the row index is from model now
					 * int bodyRows = list.size() - headRows - footRows; boolean
					 * hasBodyRow = false; boolean hasFootRow = false; if (bodyRows >
					 * 0) { hasBodyRow = true; } if (footRows > 0) { hasFootRow =
					 * true; } //last row in THEAD excute insert row after command
					 * if ((this._rowIndex == headRows) && hasBodyRow && hasFootRow) {
					 * parentIndex = index - footRows - 1; } //last row in TBODY
					 * excute insert row after command if ((this._rowIndex ==
					 * (list.size() - footRows)) && hasBodyRow && hasFootRow) {
					 * parentIndex = list.size() - 1; }
					 */
	
					tr = (Element) list.get(parentIndex);
				}
	
				if (tr.getParentNode() == nextTr.getParentNode()) {
					tr.getParentNode().insertBefore(insertElement, nextTr);
				} else {
					tr.getParentNode().appendChild(insertElement);
				}
			} else {
				//empty table - append first row
				_tableEle.appendChild(insertElement);
			}
		} else {
			// int index = TableUtil.countRowIndexInDOMTree(this._tableEle,
			// this._rowIndex - 1);
			int index = this._rowIndex - 1;
			Element tr = (Element) list.get(index);
			tr.getParentNode().insertBefore(insertElement, null);
		}
		formatNode(this._tableEle);

	}

	private Element createDefaultElement() {
		Document doc = this._tableEle.getOwnerDocument();
		Element ele = doc.createElement(IHTMLConstants.TAG_TR);
		int columnCount = _tableUtil.getColumnCount();
		if (columnCount < 1) {
			columnCount = 1;
		}
		for (int i = 0; i < columnCount; i++) {
			Element td = doc.createElement(IHTMLConstants.TAG_TD);
			Node node = doc.createTextNode(
					CommandResources.getString("TableInsertColumnCommand.Text.Default")); //$NON-NLS-1$
			td.appendChild(node);
			ele.appendChild(td);
		}
		return ele;
	}
}
