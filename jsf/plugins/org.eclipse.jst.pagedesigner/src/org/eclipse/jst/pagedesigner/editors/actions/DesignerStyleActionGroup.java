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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.commands.ActionHandler;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.editors.HTMLEditor;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;

/**
 * @author mengbo
 * @version 1.5
 */
public class DesignerStyleActionGroup extends ActionGroup
{
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

    private class ChangeDesignerStyleAction extends Action {
		int _mode;

		ChangeDesignerStyleAction(String actionId, String text, ImageDescriptor image, int mode) {
			super(text, IAction.AS_RADIO_BUTTON);
			this.setImageDescriptor(image);
			_mode = mode;
			setId(actionId);
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
		    //final IWorkbenchPart editorPart = getActivePart();
			if (_htmlEditor != null) {
			    _htmlEditor.setDesignerMode(_mode);
				updateActionBars();
			}
		}

	};

	private HTMLEditor _htmlEditor;

	private final ChangeDesignerStyleAction[] _actions;

    private final  List<ActionHandler> _actionHandlers;
    
    private final List<IHandlerActivation>    _handlers;

    /**
     * constructor
     */
    public DesignerStyleActionGroup()
	{
		ChangeDesignerStyleAction verAction = new ChangeDesignerStyleAction(
		        "org.eclipse.jst.pagedesigner.vertical", //$NON-NLS-1$
		        VERTICAL, PDPlugin.getDefault().getImageDescriptor(
						(String) IMAGE_NAMES.get(VERTICAL)),
				HTMLEditor.MODE_SASH_VERTICAL);
		verAction
				.setActionDefinitionId("org.eclipse.jst.pagedesigner.vertical"); //$NON-NLS-1$
		verAction.setToolTipText(VERTICAL_TOOLTIP);

		ChangeDesignerStyleAction horAction = new ChangeDesignerStyleAction(
		        "org.eclipse.jst.pagedesigner.horizotal", //$NON-NLS-1$
				HORIZONTAL, PDPlugin.getDefault().getImageDescriptor(
						(String) IMAGE_NAMES.get(HORIZONTAL)),
				HTMLEditor.MODE_SASH_HORIZONTAL);
		horAction
				.setActionDefinitionId("org.eclipse.jst.pagedesigner.horizotal"); //$NON-NLS-1$
		horAction.setToolTipText(HORIZONTAL_TOOLTIP);

		ChangeDesignerStyleAction designAction = new ChangeDesignerStyleAction(
		        "org.eclipse.jst.pagedesigner.design", //$NON-NLS-1$
				DESIGN, PDPlugin.getDefault().getImageDescriptor(
						(String) IMAGE_NAMES.get(DESIGN)),
				HTMLEditor.MODE_DESIGNER);
		designAction
				.setActionDefinitionId("org.eclipse.jst.pagedesigner.design"); //$NON-NLS-1$
		designAction.setToolTipText(DESIGN_TOOLTIP);

		ChangeDesignerStyleAction sourceAction = new ChangeDesignerStyleAction(
		        "org.eclipse.jst.pagedesigner.source", //$NON-NLS-1$
				SOURCE, PDPlugin.getDefault().getImageDescriptor(
						(String) IMAGE_NAMES.get(SOURCE)),
				HTMLEditor.MODE_SOURCE);
		sourceAction
				.setActionDefinitionId("org.eclipse.jst.pagedesigner.source"); //$NON-NLS-1$
		sourceAction.setToolTipText(SOURCE_TOOLTIP);

		_actions = new ChangeDesignerStyleAction[] { verAction, horAction,
				designAction, sourceAction };
        _actionHandlers = new ArrayList<ActionHandler>();
		for (int i = 0; i < _actions.length; i++)
		{
	        _actionHandlers.add(new ActionHandler(_actions[i]));
		}
		_handlers = new ArrayList<IHandlerActivation>();
	}


	public void updateActionBars() {
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
    public void setHTMLEditor(HTMLEditor editor)
    {
        if (_htmlEditor != editor)
        {
            if (_htmlEditor != null)
            {
                deactivateHandlers();
            }

            this._htmlEditor = editor;
            if (_htmlEditor != null)
            {
                activateHandlers();
            }
            updateActionBars();
        }
    }

    private void deactivateHandlers()
    {
        final IHandlerService service = (IHandlerService) _htmlEditor.getSite()
                .getService(IHandlerService.class);
        if (service != null)
        {
            for (final IHandlerActivation activation : _handlers)
            {
                service.deactivateHandler(activation);
            }
        }
        _handlers.clear();
    }

    private void activateHandlers()
    {
        final IHandlerService service = (IHandlerService) _htmlEditor.getSite()
                .getService(IHandlerService.class);
        for (int i = 0; i < _actions.length; i++)
        {
            _handlers.add(service.activateHandler(_actions[i].getId(), _actionHandlers.get(i)));
        }
    }

    /**
     * @param actionBars
     */
    public void fillActionBars(IActionBars actionBars)
    {
        IToolBarManager toolbar = actionBars.getToolBarManager();
        for (int i = 0; i < _actions.length; i++)
        {
            toolbar.add(_actions[i]);
        }
        toolbar.add(new Separator());
    }

    /**
     * dispose the action
     */
    public void dispose()
    {
        for (final ActionHandler actionHandler : _actionHandlers)
        {
            actionHandler.dispose();
        }
    }
}
