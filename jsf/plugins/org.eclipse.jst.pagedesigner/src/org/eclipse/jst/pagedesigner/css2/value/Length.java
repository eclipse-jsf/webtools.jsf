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
package org.eclipse.jst.pagedesigner.css2.value;

/**
 * @author mengbo
 */
public class Length {
	/**
	 * Constant for 0 length
	 */
	public static final Length LENGTH_0 = new Length(0, false);

	/**
	 * Constant for 1 length
	 */
	public static final Length LENGTH_1 = new Length(1, false);

	/**
	 * Constant for 2 length
	 */
	public static final Length LENGTH_2 = new Length(2, false);

	/**
	 * Constant for 3 length
	 */
	public static final Length LENGTH_3 = new Length(3, false);

	/**
	 * Constant for 8 length
	 */
	public static final Length LENGTH_8 = new Length(8, false);

	private final boolean _percentage;

	private final int _value;

	/**
	 * @param value
	 * @param percentage
	 */
	public Length(int value, boolean percentage) {
		_value = value;
		_percentage = percentage;
	}

	/**
	 * @return true if value is a percentage
	 */
	public boolean isPercentage() {
		return _percentage;
	}

	/**
	 * @return the length value
	 */
	public int getValue() {
		return _value;
	}
}
