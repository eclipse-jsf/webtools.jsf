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
package org.eclipse.jst.pagedesigner.dnd.internal;

import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jst.jsf.common.ui.internal.dialogs.CommonWizardDialog;
import org.eclipse.jst.pagedesigner.dnd.ILocalDropHandler;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.Node;

/**
 * This is the command that performs the drop operation. There is no need for
 * this command to go into command stack, it simply open wizards, and let
 * wizards to handle the remaining things.
 * 
 * @author mengbo
 */
public class LocalDropCommand extends Command {
	/**
	 * the map from feedback to ILocalDropHandler
	 */
	private Map _feedbackToHandlers;

	private IHTMLGraphicalViewer _viewer;

	private Object _localObject;

	private Node _widget;

	private IDOMPosition _position;

	/**
	 * constructor
	 * @param viewer 
	 * @param localObject 
	 * @param feedbackHandlers 
	 */
	public LocalDropCommand(IHTMLGraphicalViewer viewer, Object localObject,
			Map feedbackHandlers) {
		_viewer = viewer;
		_localObject = localObject;
		_feedbackToHandlers = feedbackHandlers;
	}

	/**
	 * @param widget
	 */
	public void setWidget(Node widget) {
		_widget = widget;
	}

	/**
	 * @param position
	 */
	public void setDOMPosition(IDOMPosition position) {
		_position = position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		if (_feedbackToHandlers.size() == 1) {
			// only have one choice.
			ILocalDropHandler handler = (ILocalDropHandler) _feedbackToHandlers
					.values().toArray()[0];
			if (handler.useWizard(_localObject, _viewer)) {
				IWizard wizard;
				if (_widget != null) {
					wizard = handler.getWizard(_localObject, _widget, _viewer);
				} else {
					wizard = handler
							.getWizard(_localObject, _position, _viewer);
				}
				CommonWizardDialog wizardDialog = new CommonWizardDialog(
						getShell(), wizard);
				wizardDialog.setTitle(Messages
						.getString("LocalDropCommand.DropHandler")); //$NON-NLS-1$
				wizardDialog.create();
				wizardDialog.open();
			} else {
				if (_widget != null) {
					handler.doUpdateWidget(_localObject, _widget, _viewer);
				} else {
					handler.doInsertElements(_localObject, _position, _viewer);
				}
			}
		} else {
			CommonWizardDialog wizardDialog = new CommonWizardDialog(
					getShell(), getWizard());
			wizardDialog.setTitle(Messages
					.getString("LocalDropCommand.DropHandler")); //$NON-NLS-1$
			wizardDialog.setBlockOnOpen(false);
			wizardDialog.create();
			wizardDialog.open();
		}
	}

	/**
	 * @return
	 */
	private IWizard getWizard() {
		if (_widget != null) {
			return new DropSelectionWizard(_viewer, _localObject,
					_feedbackToHandlers, _widget);
		}
        return new DropSelectionWizard(_viewer, _localObject,
        		_feedbackToHandlers, _position);
	}

	/**
	 * @return
	 */
	private Shell getShell() {
		return _viewer.getControl().getShell();
	}
}
