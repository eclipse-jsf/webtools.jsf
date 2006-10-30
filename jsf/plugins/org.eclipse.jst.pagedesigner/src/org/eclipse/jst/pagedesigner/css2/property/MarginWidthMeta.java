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

/**
 * @author mengbo
 */
public class MarginWidthMeta extends LengthMeta {

	/**
	 * @param inherit
	 * @param initvalue
	 */
	public MarginWidthMeta() {
		super(false, ICSSPropertyID.VAL_AUTO);
	}

	/**
	 * @param propertyName
	 * @return
	 */
	public static boolean isMarginWidth(String propertyName) {
		return ICSSPropertyID.ATTR_MARGIN_BOTTOM.equalsIgnoreCase(propertyName)
				|| ICSSPropertyID.ATTR_MARGIN_TOP
						.equalsIgnoreCase(propertyName)
				|| ICSSPropertyID.ATTR_MARGIN_LEFT
						.equalsIgnoreCase(propertyName)
				|| ICSSPropertyID.ATTR_MARGIN_RIGHT
						.equalsIgnoreCase(propertyName);
	}

}
