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
package org.eclipse.jst.pagedesigner.editors.actions;

import java.util.HashMap;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.editors.HTMLEditor;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IKeyBindingService;

/**
 * @author mengbo
 * @version 1.5
 */
public class DesignerStyleActionGroup {
	private static HashMap IMAGE_NAMES = new HashMap();

	private static final String VERTICAL = ActionsMessages
			.getString("DesignerStyleActionGroup.CommandLabel.V"); //$NON-NLS-1$

	private static final String HORIZONTAL = ActionsMessages
			.getString("DesignerStyleActionGroup.CommandLabel.H"); //$NON-NLS-1$

	private static final String DESIGN = ActionsMessages
			.getString("DesignerStyleActionGroup.CommandLabel.D"); //$NON-NLS-1$

	private static final String SOURCE = ActionsMessages
			.getString("DesignerStyleActionGroup.CommandLabel.S"); //$NON-NLS-1$

	private static final String VERTICAL_TOOLTIP = ActionsMessages
			.getString("DesignerStyleActionGroup.CommandLabel.V.Tooltip"); //$NON-NLS-1$

	private static final String HORIZONTAL_TOOLTIP = ActionsMessages
			.getString("DesignerStyleActionGroup.CommandLabel.H.Tooltip"); //$NON-NLS-1$

	private static final String DESIGN_TOOLTIP = ActionsMessages
			.getString("DesignerStyleActionGroup.CommandLabel.D.Tooltip"); //$NON-NLS-1$

	private static final String SOURCE_TOOLTIP = ActionsMessages
			.getString("DesignerStyleActionGroup.CommandLabel.S.Tooltip"); //$NON-NLS-1$
	static {
		IMAGE_NAMES.put(VERTICAL, "PD_Toolbar_vsplit.gif"); //$NON-NLS-1$
		IMAGE_NAMES.put(HORIZONTAL, "PD_Toolbar_hsplit.gif"); //$NON-NLS-1$
		IMAGE_NAMES.put(DESIGN, "PD_Toolbar_designer.gif"); //$NON-NLS-1$
		IMAGE_NAMES.put(SOURCE, "PD_Toolbar_source.gif"); //$NON-NLS-1$
	}

	class ChangeDesignerStyleAction extends Action {
		int _mode;

		ChangeDesignerStyleAction(String text, ImageDescriptor image, int mode) {
			super(text, IAction.AS_RADIO_BUTTON);
			this.setImageDescriptor(image);
			_mode = mode;
		}

		/**
		 * @return the mode
		 */
		public int getMode() {
			return _mode;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		public void run() {
			if (_htmlEditor != null) {
				_htmlEditor.setDesignerMode(_mode);
				updateActionBars();
			}
		}
	};

	HTMLEditor _htmlEditor;

	ChangeDesignerStyleAction[] _actions = null;

	{
		ChangeDesignerStyleAction _verAction = new ChangeDesignerStyleAction(
				VERTICAL, PDPlugin.getDefault().getImageDescriptor(
						(String) IMAGE_NAMES.get(VERTICAL)),
				HTMLEditor.MODE_SASH_VERTICAL);
		_verAction.setId("org.eclipse.jst.pagedesigner.vertical");
		_verAction
				.setActionDefinitionId("org.eclipse.jst.pagedesigner.vertical");
		_verAction.setToolTipText(VERTICAL_TOOLTIP);

		ChangeDesignerStyleAction _horAction = new ChangeDesignerStyleAction(
				HORIZONTAL, PDPlugin.getDefault().getImageDescriptor(
						(String) IMAGE_NAMES.get(HORIZONTAL)),
				HTMLEditor.MODE_SASH_HORIZONTAL);
		_horAction.setId("org.eclipse.jst.pagedesigner.horizotal");
		_horAction
				.setActionDefinitionId("org.eclipse.jst.pagedesigner.horizotal");
		_horAction.setToolTipText(HORIZONTAL_TOOLTIP);

		ChangeDesignerStyleAction _designAction = new ChangeDesignerStyleAction(
				DESIGN, PDPlugin.getDefault().getImageDescriptor(
						(String) IMAGE_NAMES.get(DESIGN)),
				HTMLEditor.MODE_DESIGNER);
		_designAction.setId("org.eclipse.jst.pagedesigner.design");
		_designAction
				.setActionDefinitionId("org.eclipse.jst.pagedesigner.design");
		_designAction.setToolTipText(DESIGN_TOOLTIP);

		ChangeDesignerStyleAction _sourceAction = new ChangeDesignerStyleAction(
				SOURCE, PDPlugin.getDefault().getImageDescriptor(
						(String) IMAGE_NAMES.get(SOURCE)),
				HTMLEditor.MODE_SOURCE);
		_sourceAction.setId("org.eclipse.jst.pagedesigner.source");
		_sourceAction
				.setActionDefinitionId("org.eclipse.jst.pagedesigner.source");
		_sourceAction.setToolTipText(SOURCE_TOOLTIP);

		_actions = new ChangeDesignerStyleAction[] { _verAction, _horAction,
				_designAction, _sourceAction };
	}


	private void updateActionBars() {
		if (_htmlEditor == null) {
			for (int i = 0; i < _actions.length; i++) {
				_actions[i].setEnabled(false);
			}
		} else {
			for (int i = 0; i < _actions.length; i++) {
				_actions[i].setEnabled(true);
				_actions[i].setChecked(_actions[i].getMode() == _htmlEditor
						.getDesignerMode());
			}
		}
	}

	/**
	 * @param editor
	 */
	public void setHTMLEditor(HTMLEditor editor) {
		this._htmlEditor = editor;
		if (editor != null) {
			IKeyBindingService keyBindingService = editor.getSite()
					.getKeyBindingService();
			for (int i = 0; i < _actions.length; i++) {
				keyBindingService.registerAction(_actions[i]);
			}
		}
		updateActionBars();
	}


	/**
	 * @param actionBars
	 */
	public void fillActionBars(IActionBars actionBars) {
		IToolBarManager toolbar = actionBars.getToolBarManager();
		for (int i = 0; i < _actions.length; i++) {
			toolbar.add(_actions[i]);
		}
		toolbar.add(new Separator());
	}

	/**
	 * dispose the action
	 */
	public void dispose() {
		if (_htmlEditor != null) {
			IKeyBindingService keyBindingService = _htmlEditor.getSite()
					.getKeyBindingService();
			for (int i = 0; i < _actions.length; i++) {
				keyBindingService.unregisterAction(_actions[i]);
			}
		}

	}
}
