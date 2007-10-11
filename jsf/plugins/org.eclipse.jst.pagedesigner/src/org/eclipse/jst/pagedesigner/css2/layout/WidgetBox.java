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
package org.eclipse.jst.pagedesigner.css2.layout;

/**
 * Simple box support ascent.
 * 
 * @author mengbo
 * @version 1.5
 */
public class WidgetBox extends FlowBox {
	private int _ascent = -1;

	public int getAscent() {
		if (_ascent < 0) {
			return super.getAscent();
		}
        return _ascent;
	}

	/*package*/ void setAscent(int ascent) {
		_ascent = ascent;
	}

	/**
	 * @return true if supports ascent
	 */
	public boolean supportAscent() {
		return _ascent > 0;
	}
}
