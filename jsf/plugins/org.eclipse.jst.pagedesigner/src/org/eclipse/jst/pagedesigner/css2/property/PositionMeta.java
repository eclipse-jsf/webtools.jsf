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
	/**
	 * static keyword
	 */
	public static final String STATIC = "static"; //$NON-NLS-1$

	/**
	 * absolute keyword
	 */
	public static final String ABSOLUTE = "absolute"; //$NON-NLS-1$

	private static final String RELATIVE = "relative"; //$NON-NLS-1$

	/**
	 * fixed keyword
	 */
	public static final String FIXED = "fixed"; //$NON-NLS-1$

	private static final String[] _keywords = new String[] { STATIC, ABSOLUTE,
			RELATIVE, FIXED };

	/**
	 * Default constructor
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
