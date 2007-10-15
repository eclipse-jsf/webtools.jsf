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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.list.CounterHelper;
import org.eclipse.jst.pagedesigner.css2.list.IncrementObject;
import org.eclipse.wst.css.core.internal.provisional.document.ICSSNode;
import org.eclipse.wst.css.core.internal.provisional.document.ICSSPrimitiveValue;
import org.w3c.dom.css.CSSValue;

/**
 * For CounterIncrementMeta, the valid value could be the following: 1. NULL 2.
 * NOT_SPECIFIED 3. a List of IncrementObject.
 * 
 * @author mengbo
 */
public class CounterIncrementMeta extends CSSPropertyMeta {
	/**
	 * 
	 */
	public CounterIncrementMeta() {
		super(false, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.CSSPropertyMeta#getKeywordValues()
	 */
	protected String[] getKeywordValues() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyMeta#calculateCSSValueResult(org.w3c.dom.css.CSSValue,
	 *      java.lang.String,
	 *      org.eclipse.jst.pagedesigner.css2.style.AbstractStyle)
	 */
	public Object calculateCSSValueResult(CSSValue value, String propertyName,
			ICSSStyle style) {
		return getCounter(value);
	}

	/**
	 * @param value
	 * @return the counter
	 */
	public List getCounter(CSSValue value) {
		if (value == null) {
			return null;
		}
		List result = new ArrayList();
		IncrementObject incrementObject = null;
		String identifier = null;
		Integer increment = null;
		ICSSNode cssValue = (ICSSNode) value;
		while (cssValue != null) {
			// find next idetifier.
			while (cssValue != null && !CounterHelper.isIdentifier(cssValue)) {
				cssValue = cssValue.getNextSibling();
			}
			if (cssValue == null) {
				return null;
			}
            // identifier:
            identifier = ((ICSSPrimitiveValue) value).getStringValue();
            cssValue = cssValue.getNextSibling();
            // value:
            if (CounterHelper.isNumber(cssValue)) {
            	increment = new Integer((int) ((ICSSPrimitiveValue) value)
            			.getFloatValue(ICSSPrimitiveValue.CSS_INTEGER));
            }

			if (identifier != null) {
				incrementObject = new IncrementObject(identifier, increment);
				result.add(incrementObject);
			}
		}
		return result;
	}
}
