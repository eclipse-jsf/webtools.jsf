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
package org.eclipse.jst.pagedesigner.css2.property;

/**
 * @author mengbo
 * @version 1.5
 */
public class PositionMeta extends CSSPropertyMeta {
	public static final String STATIC = "static";

	public static final String ABSOLUTE = "absolute";

	public static final String RELATIVE = "relative";

	public static final String FIXED = "fixed";

	public static final String[] _keywords = new String[] { STATIC, ABSOLUTE,
			RELATIVE, FIXED };

	/**
	 * @param inherit
	 * @param initvalue
	 */
	public PositionMeta() {
		super(false, STATIC);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#getKeywordValues()
	 */
	protected String[] getKeywordValues() {
		return _keywords;
	}

}
