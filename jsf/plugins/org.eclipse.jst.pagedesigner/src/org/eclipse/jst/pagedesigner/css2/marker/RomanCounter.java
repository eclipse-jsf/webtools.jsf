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
/*package*/ class RomanCounter extends EnumerableCounter {
	RomanCounter() 
	{
	    // do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.list.EnumerableCounter#getString(int,
	 *      boolean)
	 */
	public String getNextString(int index, int type) {
		switch (type) {
		case CSSHtmlListStyleData.LIST_T_LOWER_ROMAN:
			return getString(index).toLowerCase();
		case CSSHtmlListStyleData.LIST_T_UPPER_ROMAN:
			return getString(index);
		default:
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.list.NumberCounter#getString(int)
	 */
	/*package*/ String getString(int index) {
		// FIXME: Need to enhance more.
		StringBuffer roman = new StringBuffer();
		int a, b, c, d;
		a = (index / 1000) * 1000;
		b = ((index / 100) % 10) * 100;
		c = ((index / 10) % 10) * 10;
		d = ((index / 1) % 10) * 1;

		if (a == 1000)
			roman.append("M"); //$NON-NLS-1$
		else if (a == 2000)
			roman.append("MM"); //$NON-NLS-1$
		else if (a == 3000)
			roman.append("MMM"); //$NON-NLS-1$

		if (b == 100)
			roman.append("C"); //$NON-NLS-1$
		else if (b == 200)
			roman.append("CC"); //$NON-NLS-1$
		else if (b == 300)
			roman.append("CCC"); //$NON-NLS-1$
		else if (b == 400)
			roman.append("CD"); //$NON-NLS-1$
		else if (b == 500)
			roman.append("D"); //$NON-NLS-1$
		else if (b == 600)
			roman.append("DC"); //$NON-NLS-1$
		else if (b == 700)
			roman.append("DCC"); //$NON-NLS-1$
		else if (b == 800)
			roman.append("DCCC"); //$NON-NLS-1$
		else if (b == 900)
			roman.append("CM"); //$NON-NLS-1$

		if (c == 10)
			roman.append("X"); //$NON-NLS-1$
		else if (c == 20)
			roman.append("XX"); //$NON-NLS-1$
		else if (c == 30)
			roman.append("XXX"); //$NON-NLS-1$
		else if (c == 40)
			roman.append("XL"); //$NON-NLS-1$
		else if (c == 50)
			roman.append("L"); //$NON-NLS-1$
		else if (c == 60)
			roman.append("LX"); //$NON-NLS-1$
		else if (c == 70)
			roman.append("LXX"); //$NON-NLS-1$
		else if (c == 80)
			roman.append("LXXX"); //$NON-NLS-1$
		else if (c == 90)
			roman.append("XC"); //$NON-NLS-1$

		if (d == 1)
			roman.append("I"); //$NON-NLS-1$
		else if (d == 2)
			roman.append("II"); //$NON-NLS-1$
		else if (d == 3)
			roman.append("III"); //$NON-NLS-1$
		else if (d == 4)
			roman.append("IV"); //$NON-NLS-1$
		else if (d == 5)
			roman.append("V"); //$NON-NLS-1$
		else if (d == 6)
			roman.append("VI"); //$NON-NLS-1$
		else if (d == 7)
			roman.append("VII"); //$NON-NLS-1$
		else if (d == 8)
			roman.append("VIII"); //$NON-NLS-1$
		else if (d == 9)
			roman.append("IX"); //$NON-NLS-1$

		return roman.toString();
	}
}
