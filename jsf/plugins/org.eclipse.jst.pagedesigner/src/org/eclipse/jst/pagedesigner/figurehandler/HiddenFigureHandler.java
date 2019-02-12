/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.figurehandler;

import org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider;
import org.eclipse.jst.pagedesigner.css2.style.DefaultStyle;
import org.eclipse.jst.pagedesigner.css2.widget.ImageWidgetProvider;
import org.eclipse.swt.graphics.Image;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
/*package*/ class HiddenFigureHandler extends WidgetFigureHandler {
	private Image _image;

	/**
	 * @param image
	 */
	public HiddenFigureHandler(Image image) {
		_image = image;
	}

	protected ICSSWidgetProvider initializeWidgetProvider(Element ele) {
		return new ImageWidgetProvider(getImage(), DefaultStyle.getInstance());
	}

	/**
	 * @return
	 */
	private Image getImage() {
		return _image;
	}
}
