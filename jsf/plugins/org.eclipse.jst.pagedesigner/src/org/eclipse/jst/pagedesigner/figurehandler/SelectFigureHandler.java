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

import java.util.List;

import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider;
import org.eclipse.jst.pagedesigner.css2.widget.ComboWidgetProvider;
import org.eclipse.jst.pagedesigner.css2.widget.ListWidgetProvider;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.w3c.dom.Element;

/**
 * @author mengbo
 */
/*package*/ class SelectFigureHandler extends WidgetFigureHandler {

	/**
	 * @param node
	 * @return the labels
	 */
	public String[] getOptionLabels(Element node) {
		List options = DOMUtil.getChildElementsByTagIgnoreCase(node,
				IHTMLConstants.TAG_OPTION);
		String[] ret = new String[options.size()];
		for (int i = 0; i < ret.length; i++) {
			Element option = (Element) options.get(i);
			ret[i] = DOMUtil.getTextElementValue(option);
		}
		return ret;
	}

	/**
	 * @param node
	 * @return the label
	 */
	public String getSelectedLabels(Element node) {
		List options = DOMUtil.getChildElementsByTagIgnoreCase(node,
				IHTMLConstants.TAG_OPTION);
		String result = null;
		for (int i = 0, n = options.size(); i < n; i++) {
			Element option = (Element) options.get(i);
			if (option.hasAttribute(IHTMLConstants.ATTR_SELECTED)) {
				result = DOMUtil.getTextElementValue(option);
			}
		}
		return result;
	}

	/**
	 * @param node
	 * @return true if is multiple
	 */
	private boolean isMultiple(Element node) {
		return DOMUtil
				.getAttributeIgnoreCase(node, ICSSPropertyID.VAL_MULTIPLE) != null;
	}

	/**
	 * @return the provider
	 */
	protected final ICSSWidgetProvider initializeWidgetProvider(Element node) {
		String[] labels = getOptionLabels(node);
		String rows = DOMUtil.getAttributeIgnoreCase(node,
				IHTMLConstants.ATTR_SIZE);
		int rowsInt = 0;
		try {
			if (rows != null) {
				rowsInt = Integer.parseInt(rows);
			}
		} catch (Exception ex) {
			// ignore
		}
		if (isMultiple(node) || rowsInt > 1) {
			ListWidgetProvider provider = new ListWidgetProvider(
					getCSSStyle(node));
			provider.setOptions(labels);
			provider.setRows(rowsInt);
			return provider;
		}
        ComboWidgetProvider provider = new ComboWidgetProvider(
        		getCSSStyle(node));
        provider.setOptions(labels);
        provider.setSelectedLabel(getSelectedLabels(node));
        return provider;
	}
}
