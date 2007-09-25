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
package org.eclipse.jst.pagedesigner.viewer;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMText;

/**
 * This class used to identify a position in a text node. It's very similiar to
 * EditPartPosition. Used instead of EditPartPosition when in cases we are in
 * the middle of handling something without available EditPart created.
 * 
 * offset has the same meaning as in EditPartPosition.
 * 
 * @author mengbo
 */
public class TextPosition {
	private IDOMText _containerNode;

	private int _offset;

	/**
	 * @param part
	 * @param offset
	 */
	public TextPosition(IDOMText part, int offset) {
		_containerNode = part;
		_offset = offset;
	}

	/**
	 * if _containerPart is null, means it is invalid
	 * 
	 * @return the text node
	 */
	public IDOMText getTextNode() {
		return _containerNode;
	}

	/**
	 * if offset < 0, means it is invalid.
	 * 
	 * @return the offset
	 */
	public int getOffset() {
		return _offset;
	}

	/**
	 * @return if this position is considered valid
	 */
	public boolean isValid() {
		return _containerNode != null && _offset >= 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof TextPosition) {
			TextPosition p = (TextPosition) obj;
			return p.getTextNode() == this._containerNode
					&& p.getOffset() == this._offset;
		}
		return false;
	}

    @Override
    public int hashCode() 
    {
        // match hash code to equals criteria
        return System.identityHashCode(getTextNode()) 
                ^ System.identityHashCode(Integer.valueOf(getOffset()));
    }
	
	
}
