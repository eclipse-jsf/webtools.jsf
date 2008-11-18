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

import org.w3c.dom.Element;

/**
 * This converter can be used simply convert the tag to inline div, and copy all
 * the children
 * 
 * NOTE: It behave almost same as "span". In fact, we may remove this later, if
 * our css engine support "minWidth"/"minHeight" for inline element.
 * 
 * NOTE: it will not copy attributes!
 * 
 * @author mengbo
 * @version 1.5
 * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#getMinHeight()
 * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#getMinWidth()
 */
public class TagConverterToInlineBlock extends AbstractTagConverter {
	private int displayMode;

	/**
	 * @param host
	 * @param mode
	 */
	public TagConverterToInlineBlock(Element host, int mode) {
		super(host);
		this.displayMode = mode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
	 */
	protected Element doConvertRefresh() {
		Element result = null;
		if (displayMode == IConverterFactory.MODE_PREVIEW) {
			result = createElement("span"); //$NON-NLS-1$
			copyChildren(getHostElement(), result);
		} else {
			result = createElement("div"); //$NON-NLS-1$
			result
					.setAttribute(
							"style", //$NON-NLS-1$
							"display:inline-block; border-width:0; margin:0; min-width:1.2em;min-height:1.2em;"); //$NON-NLS-1$
			copyChildren(getHostElement(), result);
		}
		return result;
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
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isWidget()
	 */
	public boolean isWidget() {
		return false;
	}
}
