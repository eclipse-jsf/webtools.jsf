/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
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
import org.eclipse.jst.pagedesigner.css2.widget.ImageWidgetProvider;
import org.eclipse.jst.pagedesigner.utils.ImageResolver;
import org.eclipse.swt.graphics.Image;
import org.w3c.dom.Element;

/**
 * @author mengbo
 */
/*package*/ class ImgFigureHandler extends WidgetFigureHandler {
	/**
	 * the image provided for a widget
	 */
	protected Image _image;

	/**
	 * child class can override this method.
	 * 
	 * @param node
	 */
	protected void initializeImage(Element node) {
		if (_image != null) {
			_image.dispose();
		}
		_image = ImageResolver.initializeImage(node, "src"); //$NON-NLS-1$
	}

	public void dispose() {
		if (_image != null) {
			_image.dispose();
			_image = null;
		}
	}

	protected ICSSWidgetProvider initializeWidgetProvider(Element ele) {
		initializeImage(ele);
		ImageWidgetProvider provider = new ImageWidgetProvider(_image,
				getCSSStyle(ele));
		return provider;
	}
}
