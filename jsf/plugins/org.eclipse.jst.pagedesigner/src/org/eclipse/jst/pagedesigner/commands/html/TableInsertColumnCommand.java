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
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableInsertColumnCommand extends DesignerCommand {
	private Element _tableEle;

	private int _columnIndex;

	private TableUtil _tableUtil;

	/**
	 * @param viewer
	 * @param dataTable
	 * @param index
	 */
	public TableInsertColumnCommand(IHTMLGraphicalViewer viewer,
			Element dataTable, int index) {
		super(
				CommandResources
						.getString("TableInsertColumnCommand.Label.InsertColumn"), viewer); //$NON-NLS-1$
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
		List trList = new ArrayList();
		TableUtil.getTrElements(this._tableEle, trList);
		if (trList == null || trList.isEmpty()) {
			return false;
		}
		if (this._columnIndex < 0 && this._columnIndex != -10) {
			return false;
		}
		boolean isAffectedByColSpan = _tableUtil
				.isAffectedByColSpan(this._columnIndex);
		if (isAffectedByColSpan) {
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
		int maxColumn = _tableUtil.getColumnCount();

		boolean isAtLastColumn = false;
		if (this._columnIndex >= maxColumn) {
			isAtLastColumn = true;
		}

		for (int i = 0, size = trList.size(); i < size; i++) {
			Element tr = (Element) trList.get(i);
			boolean hasTH = (DOMUtil.getChildElementsByTagIgnoreCase(tr,
					IHTMLConstants.TAG_TH).size() > 0);

			List cells = lists[i];
			if (isAtLastColumn || (cells.size() <= this._columnIndex)) {
				int index = this._columnIndex + 1;
				for (int k = cells.size(); k < index; k++) {
					tr.appendChild(createDefaultElement(hasTH));
				}
			} else {
				if (this._columnIndex != -10) {
					Element cell = (Element) cells.get(this._columnIndex);
					if (!cell.getTagName().equalsIgnoreCase("fake")) //$NON-NLS-1$
					{
						tr.insertBefore(createDefaultElement(hasTH), cell);
					} else {
						boolean hasRealElement = false;
						for (int k = _columnIndex + 1; k < cells.size(); k++) {
							Element td = (Element) cells.get(k);
							if (!td.getTagName().equalsIgnoreCase("fake")) //$NON-NLS-1$
							{
								hasRealElement = true;
								tr.insertBefore(createDefaultElement(hasTH), td);
								break;
							}
						}
						if (!hasRealElement) {
							tr.appendChild(createDefaultElement(hasTH));
						}
					}
				} else {
					tr.appendChild(createDefaultElement(hasTH));
				}
			}
		}

		formatNode(this._tableEle);
	}

	private Element createDefaultElement(boolean createTH) {
		Document doc = this._tableEle.getOwnerDocument();
		Element td = null;
		if (createTH) {
			td = doc.createElement(IHTMLConstants.TAG_TH);
		} else {
			td = doc.createElement(IHTMLConstants.TAG_TD);
		}

		Node node = doc.createTextNode(
				CommandResources.getString("TableInsertColumnCommand.Text.Default")); //$NON-NLS-1$
		td.appendChild(node);
		return td;
	}
}
