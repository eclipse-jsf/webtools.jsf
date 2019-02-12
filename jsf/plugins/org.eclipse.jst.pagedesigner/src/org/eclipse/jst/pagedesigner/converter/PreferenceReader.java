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
package org.eclipse.jst.pagedesigner.converter;

/**
 * @author mengbo
 * @version 1.5
 */
public class PreferenceReader {
	/**
	 * expression type
	 */
	public final static int FULL_EXPRESSION_TYPE = 0;

    /**
     * expression type
     */
	public final static int LAST_EXPRESSION_TYPE = 1;

    /**
     * expression type
     */
	public final static int REAL_VALUE_TYPE = 2;

	/**
	 * @return the map value type
	 */
	public static int getMapValueType() {
		return LAST_EXPRESSION_TYPE;
	}
}
