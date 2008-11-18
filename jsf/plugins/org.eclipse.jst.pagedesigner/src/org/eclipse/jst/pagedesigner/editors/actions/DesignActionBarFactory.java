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

import org.eclipse.jface.action.IAction;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.actions.range.ChangeStyleAction;
import org.eclipse.jst.pagedesigner.actions.range.DesignerToolBarAction;
import org.eclipse.jst.pagedesigner.actions.range.HTagsInsertGroupAction;

/**
 * @author mengbo
 */
public class DesignActionBarFactory {
	
	private static final String TOOLBAR_PARAGRAPH_IMAGE_FILE = "PD_Toolbar_paragraph.gif"; //$NON-NLS-1$
	private static final String TOOLBAR_LARGEFONT_DISABLED_IMAGE_FILE = "PD_Toolbar_largefont_disabled.gif"; //$NON-NLS-1$
	private static final String TOOLBAR_LARGEFONT_IMAGE_FILE = "PD_Toolbar_largefont.gif"; //$NON-NLS-1$
	private static final String TOOLBAR_SMALLFONT_DISABLED_IMAGE_FILE = "PD_Toolbar_smallfont_disabled.gif"; //$NON-NLS-1$
	private static final String TOOLBAR_SMALLFONT_IMAGE_FILE = "PD_Toolbar_smallfont.gif"; //$NON-NLS-1$
	private static final String TOOLBAR_BOLD_DISABLED_IMAGE_FILE = "PD_Toolbar_bold_disabled.gif"; //$NON-NLS-1$
	private static final String TOOLBAR_BOLD_IMAGE_FILE = "PD_Toolbar_bold.gif"; //$NON-NLS-1$
	private static final String TOOLBAR_ITALIC_DISABLED_IMAGE_FILE = "PD_Toolbar_italic_disabled.gif"; //$NON-NLS-1$
	private static final String TOOLBAR_ITALIC_IMAGE_FILE = "PD_Toolbar_italic.gif"; //$NON-NLS-1$
	private static final String TOOLBAR_UNDERLINE_DISABLED_IMAGE_FILE = "PD_Toolbar_underline_disabled.gif"; //$NON-NLS-1$
	private static final String TOOLBAR_UNDERLINE_IMAGE_FILE = "PD_Toolbar_underline.gif"; //$NON-NLS-1$
	
	private static DesignActionBarFactory _instance;

	private DesignActionBarFactory() {
        // no external instantiation
	}

	/**
	 * @return get the factory singleton
	 */
	public static DesignActionBarFactory getInstance() {
		if (_instance == null) {
			_instance = new DesignActionBarFactory();
		}
		return _instance;
	}

	/**
	 * @param name
	 * @return the tool bar action
	 */
	public DesignerToolBarAction getStyleAction(String name) {
		DesignerToolBarAction action = null;

		if (name.equals(IHTMLConstants.TAG_U)) {
			action = new ChangeStyleAction(ActionsMessages
					.getString("DesignActionBarFactory.Underline.Text"), name, //$NON-NLS-1$
					PDPlugin.getDefault().getImageDescriptor(
							TOOLBAR_UNDERLINE_IMAGE_FILE), PDPlugin.getDefault()
							.getImageDescriptor(
									TOOLBAR_UNDERLINE_DISABLED_IMAGE_FILE),
					IAction.AS_CHECK_BOX);
			action.setToolTipText(ActionsMessages
					.getString("DesignActionBarFactory.Underline")); //$NON-NLS-1$
		} else if (name.equals(IHTMLConstants.TAG_I)) {
			action = new ChangeStyleAction(ActionsMessages
					.getString("DesignActionBarFactory.Italic.Text"), name, //$NON-NLS-1$
					PDPlugin.getDefault().getImageDescriptor(
							TOOLBAR_ITALIC_IMAGE_FILE), PDPlugin.getDefault()
							.getImageDescriptor(
									TOOLBAR_ITALIC_DISABLED_IMAGE_FILE),
					IAction.AS_CHECK_BOX);
			action.setToolTipText(ActionsMessages
					.getString("DesignActionBarFactory.Italic")); //$NON-NLS-1$
		} else if (name.equals(IHTMLConstants.TAG_B)) {
			action = new ChangeStyleAction(ActionsMessages
					.getString("DesignActionBarFactory.Bold.Text"), name, //$NON-NLS-1$
					PDPlugin.getDefault().getImageDescriptor(
							TOOLBAR_BOLD_IMAGE_FILE),
					PDPlugin.getDefault().getImageDescriptor(
							TOOLBAR_BOLD_DISABLED_IMAGE_FILE),
					IAction.AS_CHECK_BOX);
			action.setToolTipText(ActionsMessages
					.getString("DesignActionBarFactory.Bold")); //$NON-NLS-1$
		} else if (name.equals(IHTMLConstants.TAG_SMALL)) {
			action = new ChangeStyleAction(ActionsMessages
					.getString("DesignActionBarFactory.Small.Text"), name, //$NON-NLS-1$
					PDPlugin.getDefault().getImageDescriptor(
							TOOLBAR_SMALLFONT_IMAGE_FILE), PDPlugin.getDefault()
							.getImageDescriptor(
									TOOLBAR_SMALLFONT_DISABLED_IMAGE_FILE),
					IAction.AS_CHECK_BOX);
			action.setToolTipText(ActionsMessages
					.getString("DesignActionBarFactory.Small")); //$NON-NLS-1$
		} else if (name.equals(IHTMLConstants.TAG_BIG)) {
			action = new ChangeStyleAction(ActionsMessages
					.getString("DesignActionBarFactory.Big.Text"), name, //$NON-NLS-1$
					PDPlugin.getDefault().getImageDescriptor(
							TOOLBAR_LARGEFONT_IMAGE_FILE), PDPlugin.getDefault()
							.getImageDescriptor(
									TOOLBAR_LARGEFONT_DISABLED_IMAGE_FILE),
					IAction.AS_CHECK_BOX);
			action.setToolTipText(ActionsMessages
					.getString("DesignActionBarFactory.Big")); //$NON-NLS-1$
		} else if (name
				.equalsIgnoreCase(DesignPageActionContributor.PARAGRAPH_ACTION_ID)) {
			action = new HTagsInsertGroupAction(PDPlugin.getDefault()
					.getImageDescriptor(TOOLBAR_PARAGRAPH_IMAGE_FILE),
					IAction.AS_DROP_DOWN_MENU);
			action.setToolTipText(ActionsMessages
					.getString("DesignActionBarFactory.Paragraph")); //$NON-NLS-1$
		}

		return action;
	}
}
