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
					.getString("DesignActionBarFactory.Underline.Text"), name,
					PDPlugin.getDefault().getImageDescriptor(
							"PD_Toolbar_underline.gif"), PDPlugin.getDefault()
							.getImageDescriptor(
									"PD_Toolbar_underline_disabled.gif"),
					IAction.AS_CHECK_BOX);
			action.setToolTipText(ActionsMessages
					.getString("DesignActionBarFactory.Underline"));
		} else if (name.equals(IHTMLConstants.TAG_I)) {
			action = new ChangeStyleAction(ActionsMessages
					.getString("DesignActionBarFactory.Italic.Text"), name,
					PDPlugin.getDefault().getImageDescriptor(
							"PD_Toolbar_italic.gif"), PDPlugin.getDefault()
							.getImageDescriptor(
									"PD_Toolbar_italic_disabled.gif"),
					IAction.AS_CHECK_BOX);
			action.setToolTipText(ActionsMessages
					.getString("DesignActionBarFactory.Italic"));
		} else if (name.equals(IHTMLConstants.TAG_B)) {
			action = new ChangeStyleAction(ActionsMessages
					.getString("DesignActionBarFactory.Bold.Text"), name,
					PDPlugin.getDefault().getImageDescriptor(
							"PD_Toolbar_bold.gif"),
					PDPlugin.getDefault().getImageDescriptor(
							"PD_Toolbar_bold_disabled.gif"),
					IAction.AS_CHECK_BOX);
			action.setToolTipText(ActionsMessages
					.getString("DesignActionBarFactory.Bold"));
		} else if (name.equals(IHTMLConstants.TAG_SMALL)) {
			action = new ChangeStyleAction(ActionsMessages
					.getString("DesignActionBarFactory.Small.Text"), name,
					PDPlugin.getDefault().getImageDescriptor(
							"PD_Toolbar_smallfont.gif"), PDPlugin.getDefault()
							.getImageDescriptor(
									"PD_Toolbar_smallfont_disabled.gif"),
					IAction.AS_CHECK_BOX);
			action.setToolTipText(ActionsMessages
					.getString("DesignActionBarFactory.Small"));
		} else if (name.equals(IHTMLConstants.TAG_BIG)) {
			action = new ChangeStyleAction(ActionsMessages
					.getString("DesignActionBarFactory.Big.Text"), name,
					PDPlugin.getDefault().getImageDescriptor(
							"PD_Toolbar_largefont.gif"), PDPlugin.getDefault()
							.getImageDescriptor(
									"PD_Toolbar_largefont_disabled.gif"),
					IAction.AS_CHECK_BOX);
			action.setToolTipText(ActionsMessages
					.getString("DesignActionBarFactory.Big"));
		} else if (name
				.equalsIgnoreCase(DesignPageActionContributor.PARAGRAPH_ACTION_ID)) {
			action = new HTagsInsertGroupAction(PDPlugin.getDefault()
					.getImageDescriptor("PD_Toolbar_paragraph.gif"),
					IAction.AS_DROP_DOWN_MENU);
			action.setToolTipText(ActionsMessages
					.getString("DesignActionBarFactory.Paragraph"));
		}

		return action;
	}
}
