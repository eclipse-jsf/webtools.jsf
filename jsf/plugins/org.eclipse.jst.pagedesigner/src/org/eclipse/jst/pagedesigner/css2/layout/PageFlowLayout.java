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

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.jst.pagedesigner.css2.font.CSSFont;
import org.eclipse.jst.pagedesigner.css2.font.CSSFontManager;
import org.eclipse.swt.graphics.Font;

/**
 * A block layout which requires no FlowContext to perform its layout. This
 * class is used by {@link FlowPage}.
 * <p>
 * WARNING: This class is not intended to be subclassed by clients.
 */
public class PageFlowLayout extends BlockFlowLayout {

	/**
	 * Creates a new PageFlowLayout with the given FlowPage
	 * 
	 * @param page
	 *            the FlowPage
	 */
	public PageFlowLayout(FlowPage page) {
		super(page);
	}

	/**
	 * @see BlockFlowLayout#endBlock()
	 */
	protected void endBlock() {
        // do nothing
	}

	/**
	 * 
	 */
	public void postValidate() {
       // TODO: This method is not being called.
	}

	protected void setupLine(LineBox line, int topMargin) {
		super.setupLine(line, topMargin);

		CSSFontManager fontManager = CSSFontManager.getInstance();
		Font font = fontManager.getSwtFont((CSSFont) fontManager
				.createDefaultFont());
		line.setFontMetrics(FigureUtilities.getFontMetrics(font));
	}

	/**
	 * Setup blockBox to the initial bounds of the Page
	 */
	protected void setupBlock() {
		// Remove all current Fragments
		_blockBox.clear();

		// Setup the one fragment for this Block with the correct X and
		// available width
		_blockBox.setRecommendedWidth(((FlowPage) getFlowFigure())
				.getRecommendedWidth());
		_blockBox._x = 0;
	}

}
