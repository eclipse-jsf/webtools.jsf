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
package org.eclipse.jst.pagedesigner.css2.layout.table;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jst.pagedesigner.css2.layout.CSSBlockFlowLayout;
import org.eclipse.jst.pagedesigner.css2.layout.CSSFigure;
import org.eclipse.jst.pagedesigner.css2.layout.FlowFigure;

/**
 * @author mengbo
 * @version 1.5
 */
public class CSSTRGroupLayout extends CSSBlockFlowLayout {

	/**
	 * @param cssfigure
	 */
	public CSSTRGroupLayout(CSSFigure cssfigure) {
		super(cssfigure);
	}

	/**
	 * @return the parent figure of TRGroup should be table figure. If so, return the
     * corresponding table layout. 
	 */
	public CSSTableLayout2 getTableLayoutContext() {
		IFigure parent = getCSSFigure().getParent();
		if (parent != null) {
			LayoutManager parentLayout = parent.getLayoutManager();
			if (parentLayout instanceof CSSTableLayout2) {
				return (CSSTableLayout2) parentLayout;
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSBlockFlowLayout#postValidate()
	 */
	public void postValidate() {
		CSSTableLayout2 tableLayout = getTableLayoutContext();
		if (tableLayout == null) {
			super.postValidate();
		} else {
			Rectangle r = getTRGroupRect(tableLayout);
			if (r != null) {
				_blockBox.setXYWidthHeight(r);
				getCSSFigure().setBounds(r);
				List list = getCSSFigure().getChildren();
				for (int i = 0; i < list.size(); i++) {
					((FlowFigure) list.get(i)).postValidate();
				}
			} else {
				super.postValidate();
			}
		}
	}

	/**
	 * @return
	 */
	private Rectangle getTRGroupRect(CSSTableLayout2 tableLayout) {
		TableRowGroupInfo groupinfo = tableLayout.getGroupInfo(this
				.getCSSFigure());
		int rowIndex = groupinfo.getRowIndex();
		int rowCount = groupinfo.getRowCount();
		int y = (rowIndex + 1) * tableLayout.getVSpacing();
		for (int k = 0; k < rowIndex; k++) {
			y += tableLayout.getRowHeights()[k];
		}
		if (tableLayout.getCaptionInfo() != null
				&& "top".equalsIgnoreCase(tableLayout.getCaptionInfo().getAlign())) //$NON-NLS-1$
		{
			y += tableLayout.getCaptionSize().height;
		}

		int height = (rowCount - 1) * tableLayout.getVSpacing();
		for (int k = 0; k < rowCount; k++) {
			height += tableLayout.getRowHeights()[rowIndex + k];
		}
		//ICSSFigure figure = groupinfo.getFigure();
		return new Rectangle(tableLayout.getRowX(), y, tableLayout
				.getRowWidth(), height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSBlockFlowLayout#useLocalCoordinates()
	 */
	public boolean useLocalCoordinates() {
		// if is in table, we don't use local coordinates.
		return getTableLayoutContext() == null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSBlockFlowLayout#endBlock()
	 */
	protected void endBlock() {
		if (getTableLayoutContext() == null) {
			super.endBlock();
		} else {
			layoutLines();
		}
	}

	// /* (non-Javadoc)
	// * @see
	// org.eclipse.jst.pagedesigner.css2.layout.FlowContainerLayout#layout()
	// */
	// protected void layout()
	// {
	// CSSTableLayout2 tableLayout = getTableLayoutContext();
	// if (tableLayout == null)
	// {
	// // we are not in table? treat as block.
	// super.layout();
	// }
	// else
	// {
	// // ok, we are in table. we need to layout our children.
	// TableRowGroupInfo groupInfo =
	// tableLayout.getGroupInfo(this.getCSSFigure());
	// int[] rowHeights = tableLayout.getRowHeights();
	// int vspacing = tableLayout.getVSpacing();
	// int rowwidth = getCSSFigure().getBounds().width;// XXX: get from table
	// layout?
	// int grouprowindex = groupInfo.getRowIndex();
	// List rows = groupInfo.getRowList();
	// for (int i=0, size=rows.size(); i<size; i++)
	// {
	// TableRowInfo rowinfo = (TableRowInfo) rows.get(i);
	// ICSSFigure figure = rowinfo.getFigure();
	//                
	// int y = 0;
	// int rowindex = rowinfo.getRowIndex();
	// for (int row=grouprowindex; row<rowindex; row++)
	// {
	// y += rowHeights[row];
	// y += vspacing;
	// }
	// int height = rowHeights[rowindex];
	// Rectangle rect = new Rectangle(0, y, rowwidth, height);
	// figure.setBounds(rect);
	// }
	// }
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSLayout#handlingBorderForBlock()
	 */
	public boolean handlingBorderForBlock() {
		return false;
	}
}
