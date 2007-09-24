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
package org.eclipse.jst.pagedesigner.converter;

import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class DumTagConverter extends AbstractTagConverter {
	/**
	 * @param host 
	 * @param needBorder 
	 */
	public DumTagConverter(Element host, boolean needBorder) {
		this(host);
		this.setNeedBorderDecorator(needBorder);
	}

	/**
	 * @param host
	 */
	public DumTagConverter(Element host) {
		super(host);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
	 */
	protected Element doConvertRefresh() {
		Element result = createElement(getHostElement().getTagName());
		ConverterUtil.copyAllAttributes(getHostElement(), result, null);
		if (!internalIsWidget(result)) {
			copyChildren(getHostElement(), result);
		} else {
			dumCopyChildren(getHostElement(), result);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#isMultiLevel()
	 */
	public boolean isMultiLevel() {
		if (isWidget()) {
			return true;
		}
		return false;
	}

	/**
	 * @param result
	 * @return
	 */
	private boolean internalIsWidget(Element result) {
		String tagname = result.getTagName();
		if (IHTMLConstants.TAG_INPUT.equalsIgnoreCase(tagname)
				|| IHTMLConstants.TAG_SELECT.equalsIgnoreCase(tagname)
				|| IHTMLConstants.TAG_TEXTAREA.equalsIgnoreCase(tagname)
				|| IHTMLConstants.TAG_IMG.equalsIgnoreCase(tagname)
				|| IHTMLConstants.TAG_OBJECT.equalsIgnoreCase(tagname)
				|| IHTMLConstants.TAG_HEAD.equalsIgnoreCase(tagname)
				|| IHTMLConstants.TAG_SCRIPT.equalsIgnoreCase(tagname)
				|| IHTMLConstants.TAG_LINK.equalsIgnoreCase(tagname)
				|| IHTMLConstants.TAG_BR.equalsIgnoreCase(tagname)
				|| IHTMLConstants.TAG_STYLE.equalsIgnoreCase(tagname)
				|| IHTMLConstants.TAG_HR.equalsIgnoreCase(tagname)) {
			return true;
		}
        return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#isWidget()
	 */
	public boolean isWidget() {
		return internalIsWidget(getResultElement());
	}
}
