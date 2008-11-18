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
public class EmptyCellsMeta extends CSSPropertyMeta {
	private static final String SHOW = "show"; //$NON-NLS-1$

	static final String HIDE = "hide"; //$NON-NLS-1$

	private static final String[] _keywords = new String[] { SHOW, HIDE };

	/**
	 */
	public EmptyCellsMeta() {
		// FIXME: on CSS spec, initial value should be "show".
		// but seemed IE's default is hide.
		super(true, HIDE);
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
