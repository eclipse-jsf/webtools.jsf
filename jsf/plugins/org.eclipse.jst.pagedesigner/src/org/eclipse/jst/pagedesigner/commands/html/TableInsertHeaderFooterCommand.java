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

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.commands.CommandResources;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.dom.html.TableUtil;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableInsertHeaderFooterCommand extends DesignerCommand {
	private Element _table;

	private Element _headerOrFooter;

	private boolean _isHeader;

	private TableUtil _tableUtil;

	/**
	 * @param viewer
	 * @param table
	 * @param isHeader
	 */
	public TableInsertHeaderFooterCommand(IHTMLGraphicalViewer viewer,
			Element table, boolean isHeader) {
		super(
				isHeader ? CommandResources
						.getString("TableInsertHeaderFooterCommand.Label.InsertHeader") : CommandResources.getString("TableInsertHeaderFooterCommand.Label.InsertFooter"), viewer); //$NON-NLS-1$ //$NON-NLS-2$
		this._table = table;
		this._isHeader = isHeader;
		this._tableUtil = new TableUtil(this._table);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		String sectionName = this._isHeader ? IHTMLConstants.TAG_THEAD
				: IHTMLConstants.TAG_TFOOT;
		int rows = TableUtil.countSectionRows(this._table, sectionName);
		if (rows > 0) {
			return false;
		}
		return super.canExecute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected void doExecute() {
		String sectionName = this._isHeader ? IHTMLConstants.TAG_THEAD
				: IHTMLConstants.TAG_TFOOT;
		this._headerOrFooter = this._table.getOwnerDocument().createElement(
				sectionName);
		Element tr = createDefaultElement();
		this._headerOrFooter.appendChild(tr);
		if (this._isHeader) {
			Node child = this._table.getFirstChild();
			this._table.insertBefore(this._headerOrFooter, child);
		} else {
			int headRows = TableUtil.countSectionRows(this._table,
					IHTMLConstants.TAG_THEAD);
			Node refNode = null;
			if (headRows > 0) {
				List list = DOMUtil.getChildElementsByTagIgnoreCase(
						this._table, IHTMLConstants.TAG_THEAD);
				Node header = (Node) list.get(0);
				refNode = header.getNextSibling();
			} else {
				refNode = this._table.getFirstChild();
			}
			this._table.insertBefore(this._headerOrFooter, refNode);
		}
		formatNode(this._headerOrFooter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
	 */
	protected ISelection getAfterCommandDesignerSelection() {
		return this.toDesignSelection(this._headerOrFooter);
	}

	private Element createDefaultElement() {
		String key = this._isHeader ? "TableInsertHeaderFooterCommand.ColumnHeader" //$NON-NLS-1$
				: "TableInsertHeaderFooterCommand.ColumnFooter"; //$NON-NLS-1$
		String name = PDPlugin.getResourceString(key);
		Document doc = this._table.getOwnerDocument();
		Element ele = doc.createElement(IHTMLConstants.TAG_TR);
		int columnCount = _tableUtil.getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			Element td = null;
			if (this._isHeader) {
				td = doc.createElement(IHTMLConstants.TAG_TH);
			} else {
				td = doc.createElement(IHTMLConstants.TAG_TD);
			}
			Node node = doc.createTextNode(name);
			td.appendChild(node);
			ele.appendChild(td);
		}
		return ele;
	}
}
