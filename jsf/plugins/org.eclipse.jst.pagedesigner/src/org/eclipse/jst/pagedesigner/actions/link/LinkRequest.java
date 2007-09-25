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
package org.eclipse.jst.pagedesigner.actions.link;

import org.eclipse.gef.Request;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;

/**
 * @author mengbo
 * @version 1.5
 */
public class LinkRequest extends Request {
	private final String _identifier;

	private final DesignRange _range;

	/**
	 * @param identifier
	 * @param range
	 */
	public LinkRequest(String identifier, DesignRange range) {
		this._identifier = identifier;
		this._range = range;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return this._identifier;
	}

	/**
	 * @return the design range
	 */
	public DesignRange getDesignRange() {
		return this._range;
	}
}
