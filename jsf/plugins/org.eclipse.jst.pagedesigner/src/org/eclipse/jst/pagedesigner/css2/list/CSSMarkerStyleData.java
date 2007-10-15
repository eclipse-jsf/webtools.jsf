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
package org.eclipse.jst.pagedesigner.css2.list;

import java.util.Vector;

import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;

/**
 * @author mengbo
 */
public class CSSMarkerStyleData {
	private Vector _content;

	private String _textContent;

	private int _Offset;

	private boolean _isOutside;

	/**
	 * @return Returns the isBefore.
	 */
	public boolean isOutside() {
		return _isOutside;
	}

	/**
	 * @param position
	 *            The isBefore to set.
	 */
	public void setPosition(String position) {
		if (position == ICSSPropertyID.VAL_INSIDE) {
			_isOutside = false;
		} else if (position == ICSSPropertyID.VAL_OUTSIDE) {
			_isOutside = true;
		}
	}

	/**
	 * @return Returns the _Offset.
	 */
	public int getOffset() {
		return _Offset;
	}

	/**
	 * @param offset
	 *            The _Offset to set.
	 */
	public void setOffset(int offset) {
		_Offset = offset;
	}

	/**
	 * @return Returns the _textValue.
	 */
	public String getTextContent() {
		return _textContent;
	}

	/**
	 * @param value
	 *            The _textValue to set.
	 */
	public void setTextContent(String value) {
		_textContent = value;
	}

	/**
	 * @return the next element.  removes it from the content vector.  Returns
	 * null if the content is empty
	 */
	public Object getNextElement() {
		if (_content == null || _content.size() == 0) {
			return null;
		}
		return _content.remove(0);
	}

	/**
	 * @param content
	 */
	public void setContent(Vector content) {
		_content = content;
	}
}
