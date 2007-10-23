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
package org.eclipse.jst.pagedesigner.ui.common;

import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author mengbo
 */
public abstract class PartActivationHandler extends ShellAdapter implements
		IPartListener {
	private IWorkbenchPart _activePart;

	private boolean _isHandlingActivation = false;

	private IWorkbenchPart _trace;

	/**
	 * @param part
	 */
	public PartActivationHandler(IWorkbenchPart part) {
		_trace = part;
	}

	/**
	 * this method is called when the specified part is activated.
	 */
	public abstract void handleActivation();

	private void internalHandleActivation() {

		if (_isHandlingActivation)
			return;

		if (_activePart == _trace) {
			_isHandlingActivation = true;
			try {
				handleActivation();
			} finally {
				_isHandlingActivation = false;
			}
		}
	}

	/**
	 * @see IPartListener#partActivated(IWorkbenchPart)
	 */
	public void partActivated(IWorkbenchPart part) {
		_activePart = part;
		internalHandleActivation();
	}

	/**
	 * @see IPartListener#partBroughtToTop(IWorkbenchPart)
	 */
	public void partBroughtToTop(IWorkbenchPart part) {
        // do nothing
	}

	/**
	 * @see IPartListener#partClosed(IWorkbenchPart)
	 */
	public void partClosed(IWorkbenchPart part) {
        // do nothing
	}

	/**
	 * @see IPartListener#partDeactivated(IWorkbenchPart)
	 */
	public void partDeactivated(IWorkbenchPart part) {
		_activePart = null;
	}

	/**
	 * @see IPartListener#partOpened(IWorkbenchPart)
	 */
	public void partOpened(IWorkbenchPart part) {
        // do nothing
	}

	/*
	 * @see ShellListener#shellActivated(ShellEvent)
	 */
	public void shellActivated(ShellEvent e) {
		internalHandleActivation();
	}
}
