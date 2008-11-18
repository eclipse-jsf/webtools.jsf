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

import org.eclipse.jface.viewers.ISelection;

/**
 * @author mengbo
 */
public class DesignRange implements ISelection 
{
    private final DesignPosition _start;
    private final DesignPosition _end;

	/**
	 * @param start
	 * @param end
	 */
	public DesignRange(DesignPosition start, DesignPosition end) {
		_start = start;
		_end = end;
	}

	/**
	 * @return the start position in the range
	 */
	public DesignPosition getStartPosition() {
		return _start;
	}

	/**
	 * @return the end position in the range
	 */
	public DesignPosition getEndPosition() {
		return _end;
	}

	/**
	 * @return true if the range is valid
	 */
	public boolean isValid() {
		return _start != null && _start.isValid() && _end != null
				&& _end.isValid();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelection#isEmpty()
	 */
	public boolean isEmpty() {
		// FIXME: temp implementation, need revisit.
		return !isValid() || _start.equals(_end);
	}

	/**
	 * @param buffer
	 * @return a buffer with the debug dum
	 */
	public StringBuffer debugDump(StringBuffer buffer) {
		if (_start != null) {
			buffer.append("Start: ").append(_start); //$NON-NLS-1$
		} else {
			buffer.append("Start: null"); //$NON-NLS-1$
		}
		if (_end != null) {
			buffer.append("End: ").append(_end); //$NON-NLS-1$
		} else {
			buffer.append("End: null"); //$NON-NLS-1$
		}
		return buffer;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		return debugDump(buffer).toString();
	}
}
