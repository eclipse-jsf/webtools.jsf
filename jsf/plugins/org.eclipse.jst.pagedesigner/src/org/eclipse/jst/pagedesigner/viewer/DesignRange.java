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
public class DesignRange implements ISelection {
	public DesignPosition _start;

	public DesignPosition _end;

	public DesignRange(DesignPosition start, DesignPosition end) {
		_start = start;
		_end = end;
	}

	public DesignPosition getStartPosition() {
		return _start;
	}

	public DesignPosition getEndPosition() {
		return _end;
	}

	// public boolean isCollapsed()
	// {
	// }

	// public boolean fullyContains(EditPart part)
	// {
	//        
	// }

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

	public StringBuffer debugDump(StringBuffer buffer) {
		if (_start != null) {
			buffer.append("Start: ").append(_start);
		} else {
			buffer.append("Start: null");
		}
		if (_end != null) {
			buffer.append("End: ").append(_end);
		} else {
			buffer.append("End: null");
		}
		return buffer;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		return debugDump(buffer).toString();
	}
}
