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

import org.eclipse.jst.pagedesigner.css2.list.CSSHtmlListStyleData;

/**
 * @author mengbo
 */
/*package*/ class DecimalCounter extends EnumerableCounter 
{
	
    DecimalCounter() 
    {
	    // do nothing; only nee
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.list.EnumerableCounter#getString(int)
	 */
	public String getNextString(int index, int type) {
		//String result;
		switch (type) {
		case CSSHtmlListStyleData.LIST_T_DECIMAL:
			return Integer.toString(index);

		case CSSHtmlListStyleData.LIST_T_DECIMAL_LEADING_ZERO:
			StringBuffer sb = new StringBuffer();
			int count = 1;
			for (int i = sb.length(); i < count; i++) {
				sb.append('0');
			}
			sb.append(Integer.toString(index));
			return sb.toString();
		default:
			return null;
		}
	}
}
