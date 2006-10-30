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
package org.eclipse.jst.pagedesigner.validation.caret;

/**
 * This method represents the an user action, the 'data' could be any related
 * data that will be referenced later in the action processing. For example.
 * when user do DnD, the localData will be referenced as _data.
 * 
 * @author mengbo
 */
public class ActionData {
	public static final int PALETTE_DND = 1;

	public static final int DATABINDING_DND = 2;

	public static final int OTHER_DND = 3;

	public static final int KEYBOARD_NAVAGATION = 4;

	public static final int INLINE_EDIT = 5;

	public static final int COMPONENT_MOVE = 6;

	public static final int UNKNOWN = 0;

	private int _actionType;

	private Object _data;

	/**
	 * 
	 */
	public ActionData(int action, Object data) {
		_actionType = action;
		if (data != null) {
			_data = data;
		} else {
			_data = new Object();
		}
	}

	/**
	 * @return Returns the _actionType.
	 */
	public final int getActionType() {
		return _actionType;
	}

	/**
	 * @return Returns the _data.
	 */
	public final Object getData() {
		return _data;
	}
}
