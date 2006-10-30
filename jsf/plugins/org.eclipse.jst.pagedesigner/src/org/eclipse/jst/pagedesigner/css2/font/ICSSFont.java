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
package org.eclipse.jst.pagedesigner.css2.font;

import org.eclipse.swt.graphics.Font;

/**
 * XXX: this class should be renamed to CSSFontDescriptor. Since now it is only
 * used to describe a CSSFont.
 * 
 * @author mengbo
 */
public interface ICSSFont {
	/**
	 * Will get a Font system resource from a pool.
	 * 
	 * The caller should NOT dispose the returned Font object.
	 * 
	 * The caller should not hold reference on the returned Font object, since
	 * system may dispose it at any time.
	 * 
	 * @return
	 */
	public Font getSwtFont();

	public String getFontFamily();

	/**
	 * get font size in pixel
	 * 
	 * @return
	 */
	public int getFontSize();

	/**
	 * get font x height in pixel
	 * 
	 * @return
	 */
	public int getXHeight();

	/**
	 * @return
	 */
	public int getWeight();

	/**
	 * 
	 * @return could be SWT.NONE or SWT.ITALIC
	 */
	public int getFontStyle();

	public String getCSSString();
}
