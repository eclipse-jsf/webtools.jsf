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
package org.eclipse.jst.pagedesigner.editpolicies;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jst.pagedesigner.css2.layout.CSSFigure;
import org.eclipse.jst.pagedesigner.css2.layout.table.CSSTableLayout2;

/**
 * @author mengbo
 */
public class ColumnHelper {
	public static Rectangle getColumnBounds(GraphicalEditPart editPart,
			IFigure target) {
		Insets insets = target.getInsets();
		Rectangle bounds = editPart.getFigure().getBounds().getCopy();

		if (editPart.getParent() != null) {
			IFigure figure = ((GraphicalEditPart) editPart.getParent())
					.getFigure();
			if (figure instanceof CSSFigure) {
				CSSFigure cssFigure = (CSSFigure) figure;
				LayoutManager layoutManager = cssFigure.getLayoutManager();
				if (layoutManager instanceof CSSTableLayout2) {
					CSSTableLayout2 tableLayout = (CSSTableLayout2) layoutManager;
					bounds.y = tableLayout.getHSpacing();
					bounds.height = figure.getClientArea().height
							- tableLayout.getHSpacing() * 2;
				}
			}
		}
		bounds = new PrecisionRectangle(bounds.getResized(-1, -1));
		editPart.getFigure().translateToAbsolute(bounds);
		target.translateToRelative(bounds);
		bounds.translate(-insets.left, -insets.top);
		bounds.resize(insets.getWidth() + 1, insets.getHeight() + 1);
		return bounds;
	}
}
