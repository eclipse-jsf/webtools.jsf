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
	/**
	 * visible keyword
	 */
	public static final String VISIBLE = "visible"; //$NON-NLS-1$

	/**
	 * hidden keyword
	 */
	public static final String HIDDEN = "hidden"; //$NON-NLS-1$

	/**
	 * collapse keyword
	 */
	public static final String COLLAPSE = "collapse"; //$NON-NLS-1$

	private static final String[] KEYWORDS = new String[] { VISIBLE, HIDDEN,
			COLLAPSE };

	/**
	 * Default constructor
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
