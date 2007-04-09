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

import org.eclipse.jst.pagedesigner.dom.DOMUtil;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class DumDeepTagConverter extends AbstractTagConverter {

	/**
	 * @param host
	 */
	public DumDeepTagConverter(Element host) {
		super(host);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
	 */
	protected Element doConvertRefresh() {
		return (Element) DOMUtil.cloneNodeDeepIgnoreError(getDestDocument(),
				getHostElement());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#isMultiLevel()
	 */
	public boolean isMultiLevel() {
		return true;
	}

    // TODO: dead?
//	private boolean internalIsWidget(Element result) {
//		String tagname = result.getTagName();
//		if (IHTMLConstants.TAG_INPUT.equalsIgnoreCase(tagname)
//				|| IHTMLConstants.TAG_SELECT.equalsIgnoreCase(tagname)
//				|| IHTMLConstants.TAG_TEXTAREA.equalsIgnoreCase(tagname)
//				|| IHTMLConstants.TAG_IMG.equalsIgnoreCase(tagname)
//				|| IHTMLConstants.TAG_HEAD.equalsIgnoreCase(tagname)
//				|| IHTMLConstants.TAG_SCRIPT.equalsIgnoreCase(tagname)
//				|| IHTMLConstants.TAG_LINK.equalsIgnoreCase(tagname)) {
//			return true;
//		} else {
//
//			return false;
//		}
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.visualtag.ITagConverter#isWidget()
	 */
	public boolean isWidget() {
		return true;
	}

}
