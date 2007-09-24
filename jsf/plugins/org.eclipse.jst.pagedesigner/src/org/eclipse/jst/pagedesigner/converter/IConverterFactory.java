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
 * @author mengbo
 * @version 1.5
 */
public interface IConverterFactory {
	/**
	 * indicates designer mode 
	 */
	public static final int MODE_DESIGNER = 0;

	/**
	 * indicates preview mode
	 */
	public static final int MODE_PREVIEW = 1;

	/**
	 * 
	 * @param element
	 * @param mode 
	 * @return null if this factory don't support this element
	 */
	public ITagConverter createConverter(Element element, int mode);

	/**
	 * get the URI namespace that this factory support. "null" means this
	 * factory can be used as default factory.
	 * 
	 * @return null if this factory don't have a specific URI to support.
	 */
	public String getSupportedURI();
}
