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
import org.eclipse.jst.pagedesigner.css2.layout.CSSWidgetLayout;
import org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider;
import org.w3c.dom.Element;

/**
 * A widget figure handler
 *
 */
abstract class WidgetFigureHandler extends AbstractFigureHandler {

	/**
	 * default constructor
	 */
	public WidgetFigureHandler() {
		super();
	}

	public void updateFigure(Element node, CSSFigure oldFigure) {
		setCurrentFigure(oldFigure);
		ICSSWidgetProvider provider = initializeWidgetProvider(node);
		oldFigure.setCSSStyle(provider.getCSSStyle());
		oldFigure
				.setFixedLayoutManager(new CSSWidgetLayout(oldFigure, provider));
	}

	/**
	 * @param ele
	 * @return the widget provider
	 */
	protected abstract ICSSWidgetProvider initializeWidgetProvider(Element ele);

	public boolean isWidget() {
		return true;
	}

}
