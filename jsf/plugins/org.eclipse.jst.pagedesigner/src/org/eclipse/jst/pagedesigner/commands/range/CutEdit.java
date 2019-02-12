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
package org.eclipse.jst.pagedesigner.commands.range;

import java.util.Stack;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jst.pagedesigner.dom.DOMRange;

/**
 * @author mengbo
 */
public class CutEdit extends DeleteEdit {
	/**
	 * @param range
	 * @param viewer 
	 */
	public CutEdit(DOMRange range, GraphicalViewer viewer) {
		super(range, viewer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.DesignEdit#operate()
	 */
	protected boolean operate() {
		Stack result = deleteRange();
		setClipboard(result);
		return true;
	}

	/*
	 * private Text cutText(Text text, int start, int end) {
	 * EditValidateUtil.validStringIndexOffset(text, start, end - start); String
	 * content = text.substringData(start, end - start); if (content == null ||
	 * content.length() == 0) { return null; } text.deleteData(start, end -
	 * start); return _document.createTextNode(content); }
	 */
}
