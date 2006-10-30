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

import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * This tag converter is for those unsupported jsp tags.
 * 
 * @author mengbo
 * @version 1.5
 */
public class DefaultUnknownTagConverter extends AbstractTagConverter {

	/**
	 * @param host
	 */
	public DefaultUnknownTagConverter(Element host) {
		super(host);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
	 */
	protected Element doConvertRefresh() {
		Element hostEle = this.getHostElement();
		Element divEle = createElement("div");
		String style = DOMUtil.getAttributeIgnoreCase(hostEle, "style");
		if (style == null) {
			style = "";
		}
		if (style.length() > 0 && !style.endsWith(";")) {
			style += ";";
		}
		style += "border: none; padding: 0; margin: 0";
		divEle.setAttribute("style", style);
		Element div2 = createElement("span");
		div2.setAttribute("style", "background-color: cyan; border: none;");
		Text txt = createText(hostEle.getTagName());
		div2.appendChild(txt);

		divEle.appendChild(div2);

		Element div3 = createElement("div");
		div3.setAttribute("style", "margin: 0; padding: 0");
		divEle.appendChild(div3);

		copyChildren(getHostElement(), div3);
		return divEle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isMultiLevel()
	 */
	public boolean isMultiLevel() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#isWidget()
	 */
	public boolean isWidget() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#needBorderDecorator()
	 */
	public boolean needBorderDecorator() {
		return true;
	}
}
