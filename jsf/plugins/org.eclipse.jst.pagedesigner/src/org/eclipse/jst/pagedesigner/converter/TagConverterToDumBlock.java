/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.converter;

import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class TagConverterToDumBlock extends AbstractTagConverter {

	/**
	 * @param host
	 */
	public TagConverterToDumBlock(Element host) {
		super(host);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
	 */
	protected Element doConvertRefresh() {
		Element result = createElement(IHTMLConstants.TAG_DIV);
		copyChildren(getHostElement(), result);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isMultiLevel()
	 */
	public boolean isMultiLevel() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#isWidget()
	 */
	public boolean isWidget() {
		return false;
	}

}
