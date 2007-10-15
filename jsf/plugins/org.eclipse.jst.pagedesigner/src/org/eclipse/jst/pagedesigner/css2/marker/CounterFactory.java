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
public class CounterFactory {
	private static DecimalCounter _decimalCounterInstance;

	private static RomanCounter _romanCounterInstance;

	private static CounterFactory _instance;

	/**
	 * @return the factory singelton
	 */
	public static CounterFactory getInstance() {
		if (_instance == null) {
			_instance = new CounterFactory();
		}
		return _instance;
	}

	/**
	 * @param type
	 * @return the counter for type
	 */
	public ICounter getCounter(int type) {
		switch (type) {
		case CSSHtmlListStyleData.LIST_T_DECIMAL:
		case CSSHtmlListStyleData.LIST_T_DECIMAL_LEADING_ZERO:
			if (_decimalCounterInstance == null) {
				_decimalCounterInstance = new DecimalCounter();
			}
			return _decimalCounterInstance;
		case CSSHtmlListStyleData.LIST_T_LOWER_ROMAN:
		case CSSHtmlListStyleData.LIST_T_UPPER_ROMAN:
			if (_romanCounterInstance == null) {
				_romanCounterInstance = new RomanCounter();
			}
			return _romanCounterInstance;
		default:
			return null;

		}
	}
	
	private CounterFactory()
	{
	    // singleton, no external instantiation
	}
}
