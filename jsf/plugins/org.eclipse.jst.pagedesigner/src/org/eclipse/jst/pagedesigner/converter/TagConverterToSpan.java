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
 * This converter can be used simply convert the tag to span, and copy all the
 * children
 * 
 * NOTE: it will not copy attributes!
 * 
 * @author mengbo
 * @version 1.5
 */
public class TagConverterToSpan extends AbstractTagConverter {

	/**
	 * @param host
	 */
	public TagConverterToSpan(Element host) {
		super(host);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
	 */
	protected Element doConvertRefresh() {
		// Register a named facet on the UIComponent associated with the
		// closest parent UIComponent custom action.
		// we'll render facet as a inline flow figure. so treat it as simple
		// <span> here.
		Element result = createElement("span"); //$NON-NLS-1$
		copyChildren(getHostElement(), result);
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
