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
package org.eclipse.jst.pagedesigner.commands.range;

import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.editors.HTMLEditor;
import org.eclipse.swt.SWT;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class KeyboardData implements IInputSourceProvider {
	private Character _keyCode;

	private int _stateMask;

	private GraphicalViewer _viewer;

	/**
	 * @param code
	 * @param mask
	 * @param viewer
	 */
	public KeyboardData(char code, int mask, GraphicalViewer viewer) {
		if ((mask & SWT.SHIFT) != 0) {
			_keyCode = new Character(Character.toUpperCase(code));
		} else {
			_keyCode = new Character(code);
		}
		_stateMask = mask;
		_viewer = viewer;
	}

	/**
	 * @return Returns the keyCode.
	 */
	public int getKeyCode() {
		return _keyCode.charValue();
	}

	/**
	 * @param keyCode
	 *            The keyCode to set.
	 */
	public void setKeyCode(char keyCode) {
		this._keyCode = new Character(keyCode);
	}

	/**
	 * @return Returns the stateMask.
	 */
	public int getStateMask() {
		return _stateMask;
	}

	/**
	 * @param stateMask
	 *            The stateMask to set.
	 */
	public void setStateMask(int stateMask) {
		this._stateMask = stateMask;
	}

	/**
	 * @param keyCode
	 * @return the node or null
	 */
	public Node getSpecialNode(KeyboardData keyCode) {
		Object name;
		EditDomain domain = _viewer.getEditDomain();
		Document document = null;
		if (domain instanceof DefaultEditDomain) {
			document = ((HTMLEditor) (((DefaultEditDomain) domain)
					.getEditorPart())).getDOMDocument();
		}
		// if ((keyCode.getStateMask() & SWT.SHIFT) != 0)
		{
			if ((name = EditModelQuery.CHAR_NODE_MAP.get(keyCode
					.getCharacterData())) != null) {
				return document.createElement((String) name);
			}
		}
		return null;
	}

	/**
	 * @return Returns the _data.
	 */
	public Node[] getNodes() {
		Node node = getSpecialNode(this);
		if (node != null) {
			return new Node[] { node };
		}
		return null;
	}

	public String getStringData() {
		return _keyCode.toString();
	}

	// public Key
	public Character getCharacterData() {
		return _keyCode;
	}

	/**
	 * @return the char value of the key code
	 */
	public char getChar() {
		return _keyCode.charValue();
	}
}
