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
	 * @param mode 
	 */
	public DefaultUnknownTagConverter(Element host, int  mode) {
		super(host);
        setMode(mode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
	 */
	protected Element doConvertRefresh() {
		Element hostEle = this.getHostElement();
		Element divEle = createElement("div"); //$NON-NLS-1$
		String style = DOMUtil.getAttributeIgnoreCase(hostEle, "style"); //$NON-NLS-1$
		if (style == null) {
			style = ""; //$NON-NLS-1$
		}
		if (style.length() > 0 && !style.endsWith(";")) { //$NON-NLS-1$
			style += ";"; //$NON-NLS-1$
		}
		style += "border: none; padding: 0; margin: 0"; //$NON-NLS-1$
		divEle.setAttribute("style", style); //$NON-NLS-1$
		Element div2 = createElement("span"); //$NON-NLS-1$
        String border = isPreviewMode() ? "border-style: solid;border-width: 1px" : "border:none"; //$NON-NLS-1$ //$NON-NLS-2$
		div2.setAttribute("style", "background-color: white;"+border+";color:gray"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		Text txt = createText(hostEle.getTagName());
		div2.appendChild(txt);

		divEle.appendChild(div2);

		Element div3 = createElement("div"); //$NON-NLS-1$
		div3.setAttribute("style", "margin: 0; padding: 0"); //$NON-NLS-1$ //$NON-NLS-2$
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
