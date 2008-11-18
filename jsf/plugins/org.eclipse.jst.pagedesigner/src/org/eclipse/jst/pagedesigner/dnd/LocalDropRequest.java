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
package org.eclipse.jst.pagedesigner.dnd;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.DropRequest;

/**
 * @author mengbo
 */
public class LocalDropRequest extends Request implements DropRequest {
	// XXX: need move this constant to somewhere else.
	/**
	 * the request name for local drops
	 */
	public static final String REQ_LOCAL_DROP = "Local Drop"; //$NON-NLS-1$

	private Point _location;

	private Object _localObject;

	/**
	 * 
	 */
	public LocalDropRequest() {
		super(REQ_LOCAL_DROP);
	}

	/**
	 * Returns the location of the object to be created.
	 * 
	 * @return the location
	 */
	public Point getLocation() {
		return _location;
	}

	/**
	 * Sets the location where the new object will be placed.
	 * 
	 * @param location
	 *            the location
	 */
	public void setLocation(Point location) {
		this._location = location;
	}

	/**
	 * @return the  local object
	 */
	public Object getLocalObject() {
		return _localObject;
	}

	/**
	 * @param local
	 */
	public void setLocalObject(Object local) {
		this._localObject = local;
	}
}
