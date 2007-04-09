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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.layout.ICSSFigure;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.swt.SWT;

/**
 * @author mengbo
 * @version 1.5
 */
public class TableCaptionInfo extends TableItemInfo {
	String _align;

	/**
	 * @param figure
	 */
	public TableCaptionInfo(ICSSFigure figure) {
		super(figure);
		ICSSStyle style = figure.getCSSStyle();
		if (style != null) {
			_align = style.getStyleProperty(
					ICSSPropertyID.ATTR_HORIZONTAL_ALIGN).toString();
		}
	}

	/**
	 * @param width 
	 * @param height 
	 * @return the preferred dimension of the figure
	 */
	public Dimension getDimension(int width, int height) {
		return getFigure().getPreferredSize(width, height);
	}

	/**
	 * @return getDimension(width, height)
	 */
	public Dimension getDimension() {
		return getDimension(SWT.DEFAULT, SWT.DEFAULT);
	}

	/**
	 * @return Returns the align.
	 */
	public String getAlign() {
		// TODO:We do not support left/right align of caption currently. so we
		// treat them as top.
		if ("bottom".equalsIgnoreCase(_align)) //$NON-NLS-1$
		{
			return _align;
		}
        return "top"; //$NON-NLS-1$
	}
}
