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
 * @author mengbo
 */
public class DisplayMeta extends CSSPropertyMeta {
	static final String[] KEYWORDS = new String[] { ICSSPropertyID.VAL_INLINE,
			ICSSPropertyID.VAL_BLOCK, ICSSPropertyID.VAL_INLINE_BLOCK,
			ICSSPropertyID.VAL_LIST_ITEM, ICSSPropertyID.VAL_RUN_IN,
			ICSSPropertyID.VAL_COMPACT, ICSSPropertyID.VAL_MARKER,
			ICSSPropertyID.VAL_TABLE, ICSSPropertyID.VAL_INLINE_TABLE,
			ICSSPropertyID.VAL_TABLE_ROW_GROUP,
			ICSSPropertyID.VAL_TABLE_HEADER_GROUP,
			ICSSPropertyID.VAL_TABLE_FOOTER_GROUP,
			ICSSPropertyID.VAL_TABLE_ROW,
			ICSSPropertyID.VAL_TABLE_COLUMN_GROUP,
			ICSSPropertyID.VAL_TABLE_COLUMN, ICSSPropertyID.VAL_TABLE_CELL,
			ICSSPropertyID.VAL_TABLE_CAPTION, ICSSPropertyID.VAL_NONE };

	/**
	 */
	public DisplayMeta() {
		super(false, ICSSPropertyID.VAL_INLINE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#getKeywordValues()
	 */
	protected String[] getKeywordValues() {
		return KEYWORDS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta#calculateCSSValueResult(org.w3c.dom.css.CSSValue,
	 *      java.lang.String,
	 *      org.eclipse.jst.pagedesigner.css2.property.AbstractStyle)
	 */
	public Object calculateCSSValueResult(CSSValue value, String propertyName,
			ICSSStyle style) {
		String text = value.getCssText();
		if (text == null)
			return ICSSPropertyID.VAL_INLINE;
		String key = this.checkKeywordValues(text);
		if (key != null)
			return key;
        return ICSSPropertyID.VAL_INLINE;
	}

}
