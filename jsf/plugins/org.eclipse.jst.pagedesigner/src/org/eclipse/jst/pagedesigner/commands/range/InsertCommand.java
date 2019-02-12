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

import org.eclipse.jst.pagedesigner.commands.nav.ICaretPositionMover;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * @author mengbo
 */
public class InsertCommand extends RangeModeCommand implements
		ICaretPositionMover {

	private IInputSourceProvider _data;

	/**
	 * @param label
	 * @param viewer
	 * @param data 
	 */
	public InsertCommand(String label, IHTMLGraphicalViewer viewer,
			IInputSourceProvider data) {
		super(label, viewer);
		_data = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.RangeModeCommand#doRangeExecute(org.eclipse.jst.pagedesigner.dom.DOMRange)
	 */
	protected DOMRange doRangeExecute(DOMRange selection) {
		DesignEdit edit = null;
		edit = new InsertEdit(selection, getViewer(), _data);
		edit.perform();
		selection = new DOMRange(edit.getOperationPosition(), edit
				.getOperationPosition());
		return selection;
	}

}
