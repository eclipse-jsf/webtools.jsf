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
import org.eclipse.jst.pagedesigner.commands.CommandResources;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.dom.html.TableUtil;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableDeleteHeaderFooterCommand extends DesignerCommand {
	private Element _table;

	private boolean _isHeader;

	/**
	 * @param viewer
	 * @param table
	 * @param isHeader
	 */
	public TableDeleteHeaderFooterCommand(IHTMLGraphicalViewer viewer,
			Element table, boolean isHeader) {
		super(
				isHeader ? CommandResources
						.getString("TableDeleteHeaderFooterCommand.Label.DeleteHeader") : CommandResources.getString("TableDeleteHeaderFooterCommand.Label.DeleteFooter"), viewer); //$NON-NLS-1$ //$NON-NLS-2$
		this._table = table;
		this._isHeader = isHeader;
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
		if (rows == 0) {
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
		List list = DOMUtil.getChildElementsByTagIgnoreCase(this._table,
				sectionName);
		Node delNode = (Node) list.get(0);
		this._table.removeChild(delNode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
	 */
	protected ISelection getAfterCommandDesignerSelection() {
		return this.toDesignSelection(this._table);
	}
}
