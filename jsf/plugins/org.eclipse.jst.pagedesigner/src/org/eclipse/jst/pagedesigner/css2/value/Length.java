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
	public static final Length LENGTH_0 = new Length(0, false);

	public static final Length LENGTH_1 = new Length(1, false);

	public static final Length LENGTH_2 = new Length(2, false);

	public static final Length LENGTH_3 = new Length(3, false);

	public static final Length LENGTH_8 = new Length(8, false);

	boolean _percentage;

	int _value;

	public Length(int value, boolean percentage) {
		_value = value;
		_percentage = percentage;
	}

	public boolean isPercentage() {
		return _percentage;
	}

	public int getValue() {
		return _value;
	}
}
