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

import org.eclipse.jst.pagedesigner.css2.layout.CSSFigure;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.w3c.dom.Element;

/**
 * IFigureHandler is similiar to EditPart in some sence. Each IFigureHandler is
 * adapted to an HTML element node, and provide a figure for it.
 * 
 * @author mengbo
 * @version 1.5
 */
public interface IFigureHandler extends INodeAdapter {
	/**
	 * @param node
	 * @param oldFigure
	 */
	public void updateFigure(Element node, CSSFigure oldFigure);

	/**
	 * 
	 */
	public void dispose();

	/**
	 * @return true if the node is deemed a widget
	 */
	public boolean isWidget();

	/**
	 * @return the figure
	 */
	public CSSFigure getFigure();
}
