/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.css2.list;

/**
 * @author mengbo
 */
/*package*/ final class ListStyleUtil {
	/**
	 * @param type
	 * @param index
	 * @return the type as a string
	 */
	public static String convertTypeToString(int type, int index) {
		if (type == CSSHtmlListStyleData.LIST_T_LOWER_ROMAN
				|| type == CSSHtmlListStyleData.LIST_T_UPPER_ROMAN) {
			// TODO: to implement roman.
			// return decimalToRoman(index);
		} else if (type == CSSHtmlListStyleData.LIST_T_DECIMAL
				|| type == CSSHtmlListStyleData.LIST_T_DECIMAL_LEADING_ZERO) {
			return Integer.toString(index);
		}
		return null;
	}

	private ListStyleUtil()
	{
	    // no instantiation
	}
}
