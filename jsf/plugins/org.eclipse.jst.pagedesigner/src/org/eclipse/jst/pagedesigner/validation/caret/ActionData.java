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
 * This method represents a user action, the 'data' could be any related
 * data that will be referenced later in the action processing. For example.
 * when user does a DnD, the localData will be referenced as _data.
 * 
 * @author mengbo
 */
public class ActionData {
	/**
	 * Action type for palette drag and drop
	 */
	public static final int PALETTE_DND = 1;

	/**
	 * Action type for databinding drag and drop
	 */
	public static final int DATABINDING_DND = 2;

	/**
	 * Action type for other drag and  drop
	 */
	public static final int OTHER_DND = 3;

	/**
	 * Action type for key board navigation
	 */
	public static final int KEYBOARD_NAVAGATION = 4;

	/**
	 * Action type for inline editing
	 */
	public static final int INLINE_EDIT = 5;

	/**
	 * Action type for component move
	 */
	public static final int COMPONENT_MOVE = 6;

	/**
	 * Unknown action type (?)
	 */
	public static final int UNKNOWN = 0;

	private final int _actionType;

	private final Object _data;

	/**
	 * @param action 
	 * @param data 
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
