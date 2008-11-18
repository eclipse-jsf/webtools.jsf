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

import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.w3c.dom.css.CSSValue;

/**
 * 
 * @author mengbo
 * @version 1.5
 */
public class OverflowMeta extends CSSPropertyMeta {
	static final String[] KEYWORDS = new String[] { ICSSPropertyID.VAL_VISIBLE,
			ICSSPropertyID.VAL_HIDDEN, ICSSPropertyID.VAL_SCROLL,
			ICSSPropertyID.VAL_AUTO };

	/**
	 * Default constructor
	 */
	public OverflowMeta() {
		super(false, ICSSPropertyID.VAL_VISIBLE);
	}

	protected String[] getKeywordValues() {
		return KEYWORDS;
	}

	public Object calculateCSSValueResult(CSSValue value, String propertyName,
			ICSSStyle style) {
		String display = style.getDisplay();
		// we only support overflow for "block". Which means we don't support it
		// for table/inline-block, etc.
		if (!"block".equalsIgnoreCase(display)) { //$NON-NLS-1$
			return ICSSPropertyID.VAL_VISIBLE;
		}
		return super.calculateCSSValueResult(value, propertyName, style);
	}
}
