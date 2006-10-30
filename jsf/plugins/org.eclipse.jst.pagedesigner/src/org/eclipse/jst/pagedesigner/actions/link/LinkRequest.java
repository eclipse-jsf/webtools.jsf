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
	private String _identifier = "";

	private DesignRange _range = null;

	public LinkRequest(String identifier, DesignRange range) {
		this._identifier = identifier;
		this._range = range;
	}

	public String getIdentifier() {
		return this._identifier;
	}

	public DesignRange getDesignRange() {
		return this._range;
	}
}
