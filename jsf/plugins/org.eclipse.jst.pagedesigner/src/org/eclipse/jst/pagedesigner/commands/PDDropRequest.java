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
package org.eclipse.jst.pagedesigner.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.swt.dnd.DropTargetEvent;

/**
 * @author mengbo
 */
public class PDDropRequest extends Request implements DropRequest {
	private Point _location;

	private DropTargetEvent _currentEvent;

	/**
	 * 
	 */
	public PDDropRequest() {
		setType(PDRequestConstants.REQ_DROP);
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
	 * @param ev
	 */
	public void setCurrentEvent(DropTargetEvent ev) {
		_currentEvent = ev;
	}

	/**
	 * @return the current drop target event
	 */
	public DropTargetEvent getCurrentEvent() {
		return _currentEvent;
	}
}
