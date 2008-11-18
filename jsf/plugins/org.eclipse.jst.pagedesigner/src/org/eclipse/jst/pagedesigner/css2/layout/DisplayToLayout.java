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
package org.eclipse.jst.pagedesigner.css2.layout;

import org.eclipse.draw2d.LayoutManager;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.layout.table.CSSTRGroupLayout;
import org.eclipse.jst.pagedesigner.css2.layout.table.CSSTRLayout;
import org.eclipse.jst.pagedesigner.css2.layout.table.CSSTableCaptionLayout;
import org.eclipse.jst.pagedesigner.css2.layout.table.CSSTableCellLayout;
import org.eclipse.jst.pagedesigner.css2.layout.table.CSSTableLayout2;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.property.PositionMeta;

/**
 * @author mengbo
 */
public final class DisplayToLayout {
	/**
	 * @param figure
	 * @param display
	 * @param old
	 * @return the layout
	 */
	public static CSSLayout displayToLayout(CSSFigure figure, String display,
			LayoutManager old) {
		if ("block".equalsIgnoreCase(display)) //$NON-NLS-1$
		{
			return new CSSBlockFlowLayout(figure);
		} else if ("inline".equalsIgnoreCase(display)) //$NON-NLS-1$
		{
			return new CSSInlineFlowLayout(figure);
		} else if ("table".equalsIgnoreCase(display) || "inline-table".equalsIgnoreCase(display)) //$NON-NLS-1$ //$NON-NLS-2$ $NON-NLS-2$
		{
			return new CSSTableLayout2(figure);
		} else if ("table-row".equalsIgnoreCase(display)) //$NON-NLS-1$
		{
			return new CSSTRLayout(figure);
		} else if ("table-row-group".equalsIgnoreCase(display) //$NON-NLS-1$
				|| "table-header-group".equalsIgnoreCase(display) //$NON-NLS-1$
				|| "table-footer-group".equalsIgnoreCase(display)) //$NON-NLS-1$
		{
			return new CSSTRGroupLayout(figure);
		} else if ("table-cell".equalsIgnoreCase(display)) //$NON-NLS-1$
		{
			return new CSSTableCellLayout(figure);
		} else if (display.equalsIgnoreCase("table-caption")) //$NON-NLS-1$
		{
			return new CSSTableCaptionLayout(figure);
		} else if ("inline-block".equalsIgnoreCase(display)) //$NON-NLS-1$
		{
			return new CSSBlockFlowLayout(figure) {
				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSBlockFlowLayout#isInlineBlock()
				 */
				public boolean isInlineBlock() {
					return true;
				}
			};
		} else if (ICSSPropertyID.VAL_LIST_ITEM.equalsIgnoreCase(display)) {
			return new CSSListItemLayout(figure);
		}
		return null;
	}

	/**
	 * @param display 
	 * @return true if is inline
	 */
	public static boolean isInline(String display) {
		return "inline".equalsIgnoreCase(display) //$NON-NLS-1$
				|| "inline-block".equalsIgnoreCase(display); //$NON-NLS-1$
	}

	/**
	 * @param style
	 * @return true if is positioned
	 */
	public static boolean isPositioned(ICSSStyle style) {
		Object position = style.getStyleProperty(ICSSPropertyID.ATTR_POSITION);
		if (PositionMeta.STATIC.equalsIgnoreCase((String) position)) {
			return false;
		}
        return true;
	}
	
	private DisplayToLayout()
	{
	    // util class, no instantiation
	}
}
