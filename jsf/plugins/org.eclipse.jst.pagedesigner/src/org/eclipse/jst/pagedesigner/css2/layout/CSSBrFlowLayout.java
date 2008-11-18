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

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.swt.graphics.Image;

/**
 * @author mengbo
 */
public class CSSBrFlowLayout extends CSSInlineFlowLayout implements ICSSPainter {

	private static final String LINE_BREAK_IMAGE_FILE = "LineBreak.gif"; //$NON-NLS-1$

	/**
	 * @param flow
	 */
	public CSSBrFlowLayout(CSSFigure flow) {
		super(flow);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.layout.CSSInlineFlowLayout#flush()
	 */
	protected void flush() {
		FlowBox forcedBox = new FlowBox();
		forcedBox.setWidth(16);
		forcedBox.setHeight(getCSSStyle().getCSSFont().getXHeight());
		addToCurrentLine(forcedBox);
		endLine();

		FlowBox flowbox = new FlowBox();
		flowbox.setHeight(getCSSStyle().getCSSFont().getFontSize());
		getCurrentLine().add(flowbox);

		super.flush();
	}

	public void paintFigure(Graphics g) {
		List fragments = getFragmentsForRead();
		if (!fragments.isEmpty()) {
			FlowBox box = (FlowBox) fragments.get(0);
			g.drawImage(getSharedHTMLImage(), new Point(box._x, box._y));
		}
	}

	private static Image getSharedHTMLImage() {
		
		return PDPlugin.getDefault().getImage(LINE_BREAK_IMAGE_FILE);
	}
}
