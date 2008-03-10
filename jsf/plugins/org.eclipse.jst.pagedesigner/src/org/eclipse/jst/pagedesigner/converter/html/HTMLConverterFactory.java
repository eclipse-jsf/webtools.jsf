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
package org.eclipse.jst.pagedesigner.converter.html;

import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.utils.JSFSharedImages;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.converter.AbstractTagConverter;
import org.eclipse.jst.pagedesigner.converter.DumDescriptionTagConverter;
import org.eclipse.jst.pagedesigner.converter.DumTagConverter;
import org.eclipse.jst.pagedesigner.converter.HiddenTagConverter2;
import org.eclipse.jst.pagedesigner.converter.IConverterFactory;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.jst.pagedesigner.utils.HTMLUtil;
import org.eclipse.swt.graphics.Image;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class HTMLConverterFactory implements IConverterFactory {

	/**
	 * the constructor
	 */
	public HTMLConverterFactory() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.IConverterFactory#createConverter(org.w3c.dom.Element)
	 */
	public ITagConverter createConverter(Element element, int mode) {
		String tagName = element.getLocalName();

		if (mode == MODE_PREVIEW) {
			return new DumTagConverter(element);
		}
		if (!HTMLUtil.isVisualHtmlElement(tagName)) {
			return new HiddenTagConverter2(element, getUnknownImage());
		}

		AbstractTagConverter c;
		if (IHTMLConstants.TAG_TABLE.equalsIgnoreCase(tagName)) {
			c = new TableTagConverter(element);
		} else if (IHTMLConstants.TAG_A.equalsIgnoreCase(tagName)) {
			c = new ATagConverter(element);
		} else if (IHTMLConstants.TAG_FORM.equalsIgnoreCase(tagName)) {
			// for those HTML tag that we want to build a border decorator,
			// should
			// go there.
			c = new DumTagConverter(element, true);
		} else if (IHTMLConstants.TAG_HTML.equalsIgnoreCase(tagName)
				|| IHTMLConstants.TAG_BODY.equalsIgnoreCase(tagName)) {
			c = new DumDescriptionTagConverter(element);
			c.setNeedBorderDecorator(true);
		} else {
			c = new DumTagConverter(element);
		}
		c.setMode(mode);
		return c;
	}

	private static Image getUnknownImage() {
		return JSFUICommonPlugin.getDefault().getImage(
				JSFSharedImages.DEFAULT_PALETTE_TAG_IMG);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.IConverterFactory#getSupportedURI()
	 */
	public String getSupportedURI() {
		return ITLDConstants.URI_HTML;
	}
}
