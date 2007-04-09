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

import org.eclipse.jst.pagedesigner.commands.CommandResources;
import org.eclipse.jst.pagedesigner.commands.nav.ICaretPositionMover;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * @author mengbo
 */
public class DeleteCommand extends RangeModeCommand implements
		ICaretPositionMover {
	private boolean _forward;

	/**
	 * @param forward
	 * @param viewer
	 */
	public DeleteCommand(boolean forward, IHTMLGraphicalViewer viewer) {
		super(CommandResources.getString("DeleteCommand.Label.Delete"), viewer); //$NON-NLS-1$
		_forward = forward;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected DOMRange doRangeExecute(DOMRange selection) {
		if (selection == null) {
			return null;
		}

		DesignEdit edit = new DeleteEdit(selection, getViewer(), _forward);
		Listener listener = new Listener() {
			public void handleEvent(Event event) {
				event.type = SWT.NONE;
			}
		};
		getViewer().getControl().getDisplay()
				.addFilter(SWT.Selection, listener);
		boolean status = edit.perform();
		getViewer().getControl().getDisplay().removeFilter(SWT.Selection,
				listener);
		if (status) {
			return new DOMRange(edit.getOperationPosition(), edit
					.getOperationPosition());
		}
        return selection;
	}
}
