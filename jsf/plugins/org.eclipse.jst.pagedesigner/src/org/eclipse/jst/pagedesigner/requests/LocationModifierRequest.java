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
package org.eclipse.jst.pagedesigner.requests;

import org.eclipse.gef.requests.LocationRequest;

/**
 * This is a LocationRequest plus keyboard modified support.
 * 
 * @author mengbo
 * @version 1.5
 */
public class LocationModifierRequest extends LocationRequest {

	// TODO: dead? private int _statemask;

	private boolean _controlKeyDown;

	/**
	 * 
	 */
	public LocationModifierRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param type
	 */
	public LocationModifierRequest(Object type) {
		super(type);
	}

	public boolean isControlKeyPressed() {
		return _controlKeyDown;
	}

	public void setControlKeyPressed(boolean b) {
		this._controlKeyDown = b;
	}
	// /**
	// * Returns <code>true</code> if the ALT key is currently pressed.
	// * @return whether the ALT key is pressed
	// */
	// public boolean isAltKeyPressed() {
	// return ((_statemask & MouseEvent.ALT) != 0);
	// }
	//
	// /**
	// * Returns <code>true</code> if any mouse button is currently pressed.
	// * @return whether any mouse button is pressed
	// */
	// public boolean isAnyMouseButtonPressed() {
	// return ((_statemask & MouseEvent.ANY_BUTTON) != 0);
	// }
	//
	// /**
	// * Returns <code>true</code> if the CTRL key is currently pressed.
	// * @return whether the CTRL key is pressed
	// */
	// public boolean isControlKeyPressed() {
	// return ((_statemask & MouseEvent.CONTROL) != 0);
	// }
	//
	// /**
	// * Returns <code>true</code> if the left mouse button is pressed.
	// * @return whether the left mouse button is pressed
	// */
	// public boolean isLeftMouseButtonPressed() {
	// return ((_statemask & MouseEvent.BUTTON1) != 0);
	// }
	//
	// /**
	// * Returns <code>true</code> if the right mouse button is pressed.
	// * @return whether the right mouse button is pressed
	// */
	// public boolean isRightMouseButtonPressed() {
	// return ((_statemask & MouseEvent.BUTTON3) != 0);
	// }
	//
	// /**
	// * Returns <code>true</code> if the SHIFT key is currently pressed.
	// * @return whether the SHIFT key is pressed
	// */
	// public boolean isShiftKeyPressed() {
	// return ((_statemask & MouseEvent.SHIFT) != 0);
	// }
	//
	// /**
	// * Sets the statemask for this request.
	// * @param mask the statemask
	// */
	// public void setModifiers(int mask) {
	// this._statemask = mask;
	// }

}
