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
package org.eclipse.jst.pagedesigner.css2.marker;

import org.eclipse.jst.pagedesigner.css2.list.CounterHelper;

/**
 * @author mengbo
 */
public final class CounterUtil {
	/**
	 * @param count
	 * @param type
	 * @return the converter count
	 */
	public static String convertCount(int count, int type) {
		// XXX: currently we only support style that IE supported.for type that
		// does not
		// support we return an empty String.
		StringBuffer buffer = new StringBuffer();
		switch (type) {
		case CounterHelper.LIST_T_DECIMAL:
			buffer.append(count);
			buffer.append('.');
			break;
		case CounterHelper.LIST_T_UPPER_ALPHA:
		case CounterHelper.LIST_T_LOWER_ALPHA:
			char charA = 'a';
			if (type == CounterHelper.LIST_T_UPPER_ALPHA) {
				charA = 'A';
			}
			int index = count;
			while (index > 0 && index > 26) {
				buffer.append((char) (charA + (index / 26) - 1));
				index = index % 26;
			}
			buffer.append((char) (charA + (index - 1)));
			buffer.append("."); //$NON-NLS-1$
			break;
		case CounterHelper.LIST_T_UPPER_ROMAN:
		case CounterHelper.LIST_T_LOWER_ROMAN:
			String string = new RomanCounter().getString(count);
			if (type == CounterHelper.LIST_T_LOWER_ROMAN) {
				buffer.append(string.toLowerCase());
			} else {
				buffer.append(string);
			}
			buffer.append("."); //$NON-NLS-1$
			break;
		case CounterHelper.LIST_T_ARMENIAN:
			break;
		case CounterHelper.LIST_T_DECIMAL_LEADING_ZERO:
			break;
		case CounterHelper.LIST_T_LOWER_GREEK:
			break;
		case CounterHelper.LIST_T_GEORGIAN:
			break;
		default:
			break;
		}
		return buffer.toString();
	}
	
	private CounterUtil()
	{
	    // no instantiation
	}
}
