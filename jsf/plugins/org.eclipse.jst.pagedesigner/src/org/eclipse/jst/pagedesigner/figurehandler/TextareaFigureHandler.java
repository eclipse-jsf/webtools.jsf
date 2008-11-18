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

import org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider;
import org.eclipse.jst.pagedesigner.css2.widget.TextAreaWidgetProvider;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
/*package*/ class TextareaFigureHandler extends WidgetFigureHandler {
	protected ICSSWidgetProvider initializeWidgetProvider(Element node) {
		TextAreaWidgetProvider provider = new TextAreaWidgetProvider(
				getCSSStyle(node));
		String s = DOMUtil.getAttributeIgnoreCase(node, "cols"); //$NON-NLS-1$
		if (s != null) {
			try {
				provider.setColumns(Integer.parseInt(s));
			} catch (Exception ex) {
				// ignore
			}
		}
		s = DOMUtil.getAttributeIgnoreCase(node, "rows"); //$NON-NLS-1$
		if (s != null) {
			try {
				provider.setRows(Integer.parseInt(s));
			} catch (Exception ex) {
				// ignore
			}
		}
		s = DOMUtil.getTextElementValue(node);
		if (s != null) {
			provider.setValue(s);
		}
		return provider;
	}
}
