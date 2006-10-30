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
public class VisibilityMeta extends CSSPropertyMeta {
	public static final String VISIBLE = "visible";

	public static final String HIDDEN = "hidden";

	public static final String COLLAPSE = "collapse";

	public static final String[] KEYWORDS = new String[] { VISIBLE, HIDDEN,
			COLLAPSE };

	/**
	 * @param inherit
	 * @param initvalue
	 */
	public VisibilityMeta() {
		super(true, VISIBLE);
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
