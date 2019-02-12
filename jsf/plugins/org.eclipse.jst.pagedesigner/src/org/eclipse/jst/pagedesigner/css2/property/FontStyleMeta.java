/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.css2.property;

/**
 * @author mengbo
 */
public class FontStyleMeta extends CSSPropertyMeta {
	static final String[] KEYWORDS = new String[] { ICSSPropertyID.VAL_NORMAL,
			ICSSPropertyID.VAL_ITALIC, ICSSPropertyID.VAL_OBLIQUE };

	/**
	 * Default constructor
	 */
	public FontStyleMeta() {
		super(true, ICSSPropertyID.VAL_NORMAL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#getKeywordValues()
	 */
	protected String[] getKeywordValues() {
		return KEYWORDS;
	}

}
