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
package org.eclipse.jst.pagedesigner.viewer;

import org.eclipse.gef.EditPart;

/**
 * @author mengbo
 */
public class DesignRefPosition extends DesignPosition {
	private boolean _caretIsAtRight;

	private EditPart _refPart;

	/**
	 * @param part
	 * @param caretIsAfter
	 */
	public DesignRefPosition(EditPart part, boolean caretIsAfter) {
		super(part.getParent(), 0);
		int offset = part.getParent().getChildren().indexOf(part);
		_offset = caretIsAfter ? offset + 1 : offset;
		_refPart = part;
		_caretIsAtRight = caretIsAfter;
	}

	/**
	 * @return the reference edit part
	 */
	public EditPart getRefPart() {
		return _refPart;
	}

	/**
	 * @return Returns the _isAfter.
	 */
	public boolean caretIsAtRight() {
		return _caretIsAtRight;
	}
}
