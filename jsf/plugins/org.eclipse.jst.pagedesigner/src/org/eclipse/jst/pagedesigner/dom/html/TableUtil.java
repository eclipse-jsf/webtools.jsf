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
package org.eclipse.jst.pagedesigner.dom.html;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableUtil {
	private Element _fakeCell = null;

	private Element _table = null;

	private List[] _trCellLists = null;

	/**
	 * judge whether there is rowspan>1 cell in the tr
	 * 
	 * @param tr
	 *            TR element in a table
	 * @return true if there is rowspan>1 cell in the tr
	 */
	public static boolean hasRowSpanElement(Element tr) {
		List list = DOMUtil.getElementChildren(tr);
		Iterator itr = list.iterator();
		while (itr.hasNext()) {
			Element ele = (Element) itr.next();
			int value = DOMUtil.getIntAttributeIgnoreCase(ele,
					IHTMLConstants.ATTR_ROWSPAN, 1);
			if (value > 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * judge whether a tr is affected by row span cell in the previous trs.
	 * 
	 * @param trList
	 *            list holds all tr elements in a table
	 * @param tr
	 * @param index
	 *            tr index in the DOM tree
	 * @return true if tr is affected by row span cell in the previous trs
	 */
	public static boolean isAffectedByRowSpan(List trList, Element tr, int index) {
		Node parent = tr.getParentNode();
		int dex = index;
		while (--dex >= 0) {
			Element preTr = (Element) trList.get(dex);
			if (preTr.getParentNode() != parent) {
				break;
			}
            int maxRowSpan = countMaxRowSpan(preTr);
            if (maxRowSpan > (index - dex)) {
            	return true;
            }
		}
		return false;
	}

	/**
	 * count all tr in the table
	 * 
	 * @param element
	 *            table
	 * @param list
	 *            list to hold tr elements
	 */
	public static void getTrElements(Element element, List list) {
		NodeList nodeList = element.getChildNodes();
		for (int i = 0, size = nodeList.getLength(); i < size; i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element ele = (Element) node;
				if (ele.getNodeName().equalsIgnoreCase(IHTMLConstants.TAG_TR)) {
					list.add(ele);
				} else if (ele.getNodeName().equalsIgnoreCase(
						IHTMLConstants.TAG_THEAD)
						|| ele.getNodeName().equalsIgnoreCase(
								IHTMLConstants.TAG_TBODY)
						|| ele.getNodeName().equalsIgnoreCase(
								IHTMLConstants.TAG_TFOOT)) {
					getTrElements(ele, list);
				}
			}

		}
	}

	/**
	 * count row index in the code DOM tree according to the display index
	 * 
	 * @param table
	 * @param displayIndex
	 * @return the row index
	 */
	public static int countRowIndexInDOMTree(Element table, int displayIndex) {

		int footRows = countSectionRows(table, IHTMLConstants.TAG_TFOOT);
		if (footRows == 0) {
			return displayIndex;
		}
		int headRows = countSectionRows(table, IHTMLConstants.TAG_THEAD);
		List list = new ArrayList();
		getTrElements(table, list);
		int bodyRows = list.size() - headRows - footRows;
		// if display index is in TFOOT area
		if (displayIndex >= (list.size() - footRows)) {
			int index = displayIndex - bodyRows;
			return index;
		}
		// if display index is in TBODY area
		else if (displayIndex >= headRows) {
			int index = displayIndex + footRows;
			return index;
		}
		// if display index is in THEAD area
		else {
			return displayIndex;
		}
	}

	/**
	 * get row count in the specified section
	 * 
	 * @param table
	 * @param sectionName
	 *            child element name of table, like THEAD or TFOOT
	 * @return the row count in the section
	 */
	public static int countSectionRows(Element table, String sectionName) {
		NodeList nodeList = table.getChildNodes();
		for (int i = 0, size = nodeList.getLength(); i < size; i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element ele = (Element) node;
				if (node.getNodeName().equalsIgnoreCase(sectionName)) {
					List list = DOMUtil.getChildElementsByTagIgnoreCase(ele,
							IHTMLConstants.TAG_TR);
					if (list != null) {
						return list.size();
					}
				}
			}

		}
		return 0;
	}

	/**
	 * constructor
	 * 
	 * @param table
	 */
	public TableUtil(Element table) {
		this._table = table;
		this._trCellLists = fillTrCells();
	}

	/**
	 * get tr cells list
	 * 
	 * @return the list of tr cells
	 */
	public List[] getTrCellLists() {
		return this._trCellLists;
	}

	/**
	 * calculate row and column index for tr or td/th
	 * 
	 * @param node
	 *            tr or td/th
	 * @return the position
	 */
	public TableChildElementPosition getPosition(Node node) {
		TableChildElementPosition position = new TableChildElementPosition();
		if (node == null) {
			return position;
		}
		String tagName = node.getLocalName();
		if (IHTMLConstants.TAG_TR.equalsIgnoreCase(tagName)) {
			List list = new ArrayList();
			getTrElements(this._table, list);
			for (int i = 0, size = list.size(); i < size; i++) {
				Element tr = (Element) list.get(i);
				if (tr == node) {
					position.setRowIndex(i);
					break;
				}
			}
		} else if (IHTMLConstants.TAG_TD.equalsIgnoreCase(tagName)
				|| IHTMLConstants.TAG_TH.equalsIgnoreCase(tagName)) {
			Element tr = (Element) node.getParentNode();
			TableChildElementPosition pos = getPosition(tr);
			position.setRowIndex(pos.getRowIndex());
			List[] lists = getTrCellLists();
			List list = lists[pos.getRowIndex()];
			for (int i = 0, size = list.size(); i < size; i++) {
				Element td = (Element) list.get(i);
				if (td == node) {
					position.setColumnIndex(i);
					break;
				}
			}
		} else {
			boolean hasTDParent = false;
			Node childBackup = node;
			while (!IHTMLConstants.TAG_TABLE.equalsIgnoreCase(childBackup
					.getParentNode().getLocalName())) {
				childBackup = childBackup.getParentNode();
				String localName = childBackup.getLocalName();
				if (IHTMLConstants.TAG_TD.equalsIgnoreCase(localName)
						|| IHTMLConstants.TAG_TH.equalsIgnoreCase(localName)) {
					hasTDParent = true;
					break;
				}
			}
			if (hasTDParent) {
				position = getPosition(childBackup);
			}
		}
		return position;
	}

	/**
	 * get column count
	 * 
	 * @return the column count
	 */
	public int getColumnCount() {
		List[] lists = this._trCellLists;
		if (lists != null) {
			int max = 0;
			for (int i = 0, size = lists.length; i < size; i++) {
				List list = lists[i];
				if (list.size() > max) {
					max = list.size();
				}
			}
			return max;
		}
		return 0;
	}

	/**
	 * judge whether there is columnspan>1 cell in the column
	 * 
	 * @param columnIndex
	 *            column index in a table
	 * @return true if there is columnspan>1 cell in the column
	 */
	public boolean hasColumnSpanElement(int columnIndex) {
		List cells = getColumnCells(columnIndex);
		if (cells != null) {
			Iterator itr = cells.iterator();
			while (itr.hasNext()) {
				Element cell = (Element) itr.next();
				int value = DOMUtil.getIntAttributeIgnoreCase(cell,
						IHTMLConstants.ATTR_COLSPAN, 1);
				if (value > 1) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * judge whether the column has cell affected by column span cell in
	 * privious columns
	 * 
	 * @param columnIndex
	 * @return true if the column has cell affected by column span cell in
     * privious columns 
	 */
	public boolean isAffectedByColSpan(int columnIndex) {
		int index = columnIndex;
		while (--index >= 0) {
			List cells = getColumnCells(index);
			int max = countMaxColSpan(cells);
			if (max > (columnIndex - index)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * get cells in the specified column of the table
	 * 
	 * @param columnIndex
	 * @return the column cells
	 */
	public List getColumnCells(int columnIndex) {
		List list = new ArrayList();

		List[] lists = this._trCellLists;
		for (int i = 0; i < lists.length; i++) {
			List tempList = lists[i];
			if (tempList.size() <= columnIndex) {
				continue;
			}
			list.add(tempList.get(columnIndex));
		}
		return list;
	}

	/**
	 * count max row span in the tr
	 * 
	 * @param tr
	 * @return
	 */
	private static int countMaxRowSpan(Element tr) {
		List list = DOMUtil.getElementChildren(tr);
		int max = countMaxValue(list, IHTMLConstants.ATTR_ROWSPAN);
		return max;
	}

	/**
	 * count max attr value
	 * 
	 * @param list
	 * @param attr
	 * @return
	 */
	private static int countMaxValue(List list, String attr) {
		int max = 1;
		Iterator itr = list.iterator();
		while (itr.hasNext()) {
			Element ele = (Element) itr.next();
			int value = DOMUtil.getIntAttributeIgnoreCase(ele, attr, 1);
			if (value > max) {
				max = value;
			}
		}
		return max;
	}

	/**
	 * count max col span in a column
	 * 
	 * @param list
	 * @return
	 */
	private int countMaxColSpan(List list) {
		int max = countMaxValue(list, IHTMLConstants.ATTR_COLSPAN);
		return max;
	}

	/**
	 * get fake element to fill tr cell
	 * 
	 * @return
	 */
	private Element getFakeElement() {
		if (_fakeCell != null) {
			return _fakeCell;
		}
		_fakeCell = this._table.getOwnerDocument().createElement("fake"); //$NON-NLS-1$
		return _fakeCell;
	}

	/**
	 * initial every tr cells according to th and td under each tr element
	 * 
	 * @param trList
	 * @return
	 */
	private List[] initialTrCells(List trList) {
		int size = trList.size();
		List[] lists = new ArrayList[size];

		if (trList != null) {
			for (int i = 0, n = trList.size(); i < n; i++) {
				lists[i] = new ArrayList();
				Element tr = (Element) trList.get(i);
				List domCells = DOMUtil.getElementChildren(tr);
				Iterator itr = domCells.iterator();
				while (itr.hasNext()) {
					Element cell = (Element) itr.next();
					lists[i].add(cell);
					int colSpan = DOMUtil.getIntAttributeIgnoreCase(cell,
							IHTMLConstants.ATTR_COLSPAN, 1);
					while (--colSpan > 0) {
						Element fakeElement = getFakeElement();
						lists[i].add(fakeElement);
					}
				}
			}

		}

		return lists;

	}

	/**
	 * after initial tr cells,fill tr with fake cells if necessary.
	 * 
	 * @return
	 */
	private List[] fillTrCells() {
		List list = new ArrayList();
		getTrElements(this._table, list);

		List[] lists = initialTrCells(list);
		int size = lists.length;

		int headRows = countSectionRows(this._table, IHTMLConstants.TAG_THEAD);
		if (headRows > 0) {
			List[] headList = new ArrayList[headRows];
			for (int i = 0; i < headRows; i++) {
				headList[i] = lists[i];
			}
			fillSectionTrCells(headList);
		}

		int footRows = countSectionRows(this._table, IHTMLConstants.TAG_TFOOT);
		if (footRows > 0) {
			List[] footList = new ArrayList[footRows];
			for (int i = 0; i < footRows; i++) {
				footList[i] = lists[headRows + i];
			}
			fillSectionTrCells(footList);
		}

		int bodyRows = size - headRows - footRows;
		if (bodyRows > 0) {
			int bodyCount = 1;
			int k = 0;
			List bodys = new ArrayList();
			Element tr = (Element) list.get(headRows + footRows);
			Node node = tr.getParentNode();
			for (int i = 1; i < bodyRows; i++) {
				Element tempTr = (Element) list.get(headRows + footRows + i);
				if (tempTr.getParentNode() != node) {
					node = tempTr.getParentNode();
					bodys.add(new Integer(i - k));
					k = i;
					bodyCount++;
				}
			}
			bodys.add(new Integer(bodyRows - k));

			for (int j = 0; j < bodyCount; j++) {
				int num = ((Integer) bodys.get(j)).intValue();
				List[] bodyList = new ArrayList[num];
				int m = headRows + footRows;

				for (int i = 0; i < num; i++) {
					bodyList[i] = lists[m + i];
				}
				fillSectionTrCells(bodyList);
				m = m + num;
			}

		}

		return lists;
	}

	/**
	 * fill tr cells under each table section,like THEAD,TFOOT,TBODY.
	 * 
	 * @param lists
	 */
	private void fillSectionTrCells(List[] lists) {
		Element cell = null;

		if (lists != null) {
			for (int i = 1, size = lists.length; i < size; i++) {
				List insertPoints = new ArrayList();

				for (int j = 0; j < i; j++) {
					List list = lists[j];
					for (int column = 0; column < list.size(); column++) {
						cell = (Element) list.get(column);
						if (cell.getTagName().equalsIgnoreCase("fake")) { //$NON-NLS-1$
							continue;
						}
						int rowSpan = DOMUtil.getIntAttributeIgnoreCase(cell,
								IHTMLConstants.ATTR_ROWSPAN, 1);
						if (rowSpan > (i - j)) {
							int colSpan = DOMUtil.getIntAttributeIgnoreCase(
									cell, IHTMLConstants.ATTR_COLSPAN, 1);
							insertPoints.add(new ColStructure(column, colSpan));
						}
					}
				}
				// there are fake column cell need to be inserted into this tr
				if (insertPoints.size() > 0) {
					Collections.sort(insertPoints);
					List trCells = lists[i];

					Iterator itr = insertPoints.iterator();
					while (itr.hasNext()) {
						ColStructure cls = (ColStructure) itr.next();
						int loop = cls.getColSpan();
						int column = cls.getColumn();
						while (loop-- != 0) {
							trCells.add(column++, getFakeElement());
						}

					}
				}
			}
		}
	}
}
