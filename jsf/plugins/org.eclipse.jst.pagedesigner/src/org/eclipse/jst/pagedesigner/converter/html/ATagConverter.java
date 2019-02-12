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
package org.eclipse.jst.pagedesigner.converter.html;

import org.eclipse.jst.pagedesigner.converter.ConverterUtil;
import org.eclipse.jst.pagedesigner.converter.DumTagConverter;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * @author mengbo
 * @version 1.5
 */
public class ATagConverter extends DumTagConverter {
	private boolean _emptyContainer = false;

	/**
	 * @param host
	 */
	public ATagConverter(Element host) {
		super(host);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
	 */
	protected Element doConvertRefresh() {
		_emptyContainer = ConverterUtil.isEmptyContainer(getHostElement());
		if (_emptyContainer) {
			Element resultEle = createElement("a"); //$NON-NLS-1$
			ConverterUtil.copyAllAttributes(getHostElement(), resultEle, null);
			Text fakedNode = createText("link"); //$NON-NLS-1$
			resultEle.appendChild(fakedNode);
			return resultEle;
		}
        return super.doConvertRefresh();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#needBorderDecorator()
	 */
	public boolean needBorderDecorator() {
		return _emptyContainer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isMultiLevel()
	 */
	public boolean isMultiLevel() {
		return _emptyContainer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#isWidget()
	 */
	public boolean isWidget() {
		return _emptyContainer;
	}

}
