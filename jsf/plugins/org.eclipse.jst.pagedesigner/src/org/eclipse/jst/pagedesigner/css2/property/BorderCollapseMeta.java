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
 */
public class BorderCollapseMeta extends CSSPropertyMeta {
	static String[] keywords = new String[] { ICSSPropertyID.VAL_COLLAPSE,
			ICSSPropertyID.VAL_SEPARATE };

	/**
	 */
	public BorderCollapseMeta() {
		// XXX: the spec says COLLAPSE is initial value, but seemed that IE
		// is using separate as default
		super(true, ICSSPropertyID.VAL_SEPARATE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#getKeywordValues()
	 */
	protected String[] getKeywordValues() {
		return keywords;
	}
}
