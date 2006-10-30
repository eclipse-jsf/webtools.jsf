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
package org.eclipse.jst.pagedesigner.figurehandler;

import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.layout.CSSFigure;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class DefaultFigureHandler extends AbstractFigureHandler {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.figurehandler.IFigureHandler#updateFigure(org.w3c.dom.Element,
	 *      org.eclipse.jst.pagedesigner.css2.layout.CSSFigure)
	 */
	public void updateFigure(Element node, CSSFigure oldFigure) {
		setCurrentFigure(oldFigure);
		ICSSStyle style = getCSSStyle(node);
		oldFigure.setCSSStyle(style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.figurehandler.IFigureHandler#isWidget()
	 */
	public boolean isWidget() {
		return false;
	}
}
