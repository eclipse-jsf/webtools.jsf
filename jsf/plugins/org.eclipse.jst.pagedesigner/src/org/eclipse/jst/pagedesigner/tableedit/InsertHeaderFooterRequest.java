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
package org.eclipse.jst.pagedesigner.tableedit;

import org.eclipse.gef.Request;

/**
 * @author mengbo
 * @version 1.5
 */
public class InsertHeaderFooterRequest extends Request {
	private boolean _isHeader;

	/**
	 * @param type
	 * @param isHeader
	 */
	public InsertHeaderFooterRequest(String type, boolean isHeader) {
		super(type);
		this._isHeader = isHeader;
	}

	/**
	 * @return Returns the _isHeader.
	 */
	public boolean isHeader() {
		return _isHeader;
	}

	/**
	 * @param header
	 *            The _isHeader to set.
	 */
	public void setHeader(boolean header) {
		_isHeader = header;
	}
}
