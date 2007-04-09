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
public class CSSTRLayout extends CSSBlockFlowLayout {
	/**
	 * @param cssfigure
	 */
	public CSSTRLayout(CSSFigure cssfigure) {
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
			} else if (parentLayout instanceof CSSTRGroupLayout) {
				return ((CSSTRGroupLayout) parentLayout)
						.getTableLayoutContext();
			}
		}

		return null;
	}

	/**
	 * @return the group layout or null
	 */
	public CSSTRGroupLayout getTRGroupLayout() {
		IFigure parent = getCSSFigure().getParent();
		if (parent != null) {
			LayoutManager parentLayout = parent.getLayoutManager();
			if (parentLayout instanceof CSSTRGroupLayout) {
				return ((CSSTRGroupLayout) parentLayout);
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
			// we are not in table? treat as block.
			super.postValidate();
		} else {
			Rectangle r = getTRRect(tableLayout, getTRGroupLayout());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSBlockFlowLayout#endBlock()
	 */
	protected void endBlock() {
		if (this.getTableLayoutContext() == null) {
			super.endBlock();
		} else {
			layoutLines();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSBlockFlowLayout#useLocalCoordinates()
	 */
	public boolean useLocalCoordinates() {
		return this.getTableLayoutContext() == null;
	}

	private Rectangle getTRRect(CSSTableLayout2 tableLayout,
			CSSTRGroupLayout groupLayout) {
		TableRowInfo rowinfo = tableLayout.getRowInfo(this.getCSSFigure());
		int rowIndex = rowinfo.getRowIndex();
		int y = (rowIndex + 1) * tableLayout.getVSpacing();
		for (int k = 0; k < rowIndex; k++) {
			y += tableLayout.getRowHeights()[k];
		}
		if (tableLayout.getCaptionInfo() != null
				&& "top".equalsIgnoreCase(tableLayout.getCaptionInfo().getAlign())) //$NON-NLS-1$
		{
			y += tableLayout.getCaptionSize().height;
		}

		int height = tableLayout.getRowHeights()[rowIndex];
		//ICSSFigure figure = rowinfo.getFigure();
		return new Rectangle(tableLayout.getRowX(), y, tableLayout
				.getRowWidth(), height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSLayout#handlingBorderForBlock()
	 */
	public boolean handlingBorderForBlock() {
		return false;
	}
}
