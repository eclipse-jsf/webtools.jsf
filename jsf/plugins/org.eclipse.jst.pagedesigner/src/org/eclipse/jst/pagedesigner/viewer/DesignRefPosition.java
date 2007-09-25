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
