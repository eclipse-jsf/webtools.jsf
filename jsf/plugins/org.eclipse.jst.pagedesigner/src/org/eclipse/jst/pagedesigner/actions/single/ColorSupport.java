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
package org.eclipse.jst.pagedesigner.actions.single;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dom.DOMStyleUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
/*package*/ class ColorSupport {
	static final String[] COLOR_VALUES = new String[] {
			"Aqua", //$NON-NLS-1$
			"Black", //$NON-NLS-1$
			"Blue", //$NON-NLS-1$
			"Fuchsia", //$NON-NLS-1$
			"Gray", //$NON-NLS-1$
			"Green", //$NON-NLS-1$
			"Lime", //$NON-NLS-1$
			"Maroon", //$NON-NLS-1$
			"Navy", //$NON-NLS-1$
			"Olive", //$NON-NLS-1$
			"Orange", //$NON-NLS-1$
			"Purple", //$NON-NLS-1$
			"Red", //$NON-NLS-1$
			"Silver", //$NON-NLS-1$
			"Teal", //$NON-NLS-1$
			"White", //$NON-NLS-1$
			"Yellow" //$NON-NLS-1$
	};
	static final String[] COLOR_MENU_LABELS = new String[] {
		PDPlugin.getResourceString("ColorSupport.CommandLabel.Aqua"), //$NON-NLS-1$
		PDPlugin.getResourceString("ColorSupport.CommandLabel.Black"), //$NON-NLS-1$
		PDPlugin.getResourceString("ColorSupport.CommandLabel.Blue"), //$NON-NLS-1$
		PDPlugin.getResourceString("ColorSupport.CommandLabel.Fuchsia"), //$NON-NLS-1$
		PDPlugin.getResourceString("ColorSupport.CommandLabel.Gray"), //$NON-NLS-1$
		PDPlugin.getResourceString("ColorSupport.CommandLabel.Green"), //$NON-NLS-1$
		PDPlugin.getResourceString("ColorSupport.CommandLabel.Lime"), //$NON-NLS-1$
		PDPlugin.getResourceString("ColorSupport.CommandLabel.Maroon"), //$NON-NLS-1$
		PDPlugin.getResourceString("ColorSupport.CommandLabel.Navy"), //$NON-NLS-1$
		PDPlugin.getResourceString("ColorSupport.CommandLabel.Olive"), //$NON-NLS-1$
		PDPlugin.getResourceString("ColorSupport.CommandLabel.Orange"), //$NON-NLS-1$
		PDPlugin.getResourceString("ColorSupport.CommandLabel.Purple"), //$NON-NLS-1$
		PDPlugin.getResourceString("ColorSupport.CommandLabel.Red"), //$NON-NLS-1$
		PDPlugin.getResourceString("ColorSupport.CommandLabel.Silver"), //$NON-NLS-1$
		PDPlugin.getResourceString("ColorSupport.CommandLabel.Teal"), //$NON-NLS-1$
		PDPlugin.getResourceString("ColorSupport.CommandLabel.White"), //$NON-NLS-1$
		PDPlugin.getResourceString("ColorSupport.CommandLabel.Yellow") //$NON-NLS-1$
};

	/**
	 * 
	 * @param menu
	 * @param ele
	 * @param cssProperty
	 *            will be "color" or "background-color"
	 */
	static void createColorActions(IMenuManager menu, IDOMElement ele,
			String cssProperty) {
		boolean needAdditional = true;
		String currentValue = DOMStyleUtil.getInlineStyleProperty(ele,
				cssProperty);
		ChangeStylePropertyAction defaultAction = new ChangeStylePropertyAction(
				PDPlugin.getResourceString("ColorSupport.CommandLabel.Default"), ele, cssProperty, null); //$NON-NLS-1$
		if (currentValue == null || currentValue.length() == 0) {
			defaultAction.setChecked(true);
			needAdditional = false;
		}
		menu.add(defaultAction);
		menu.add(new Separator());
		for (int i = 0; i < COLOR_VALUES.length; i++) {
			ChangeStylePropertyAction action = new ChangeStylePropertyAction(
					COLOR_MENU_LABELS[i], ele, cssProperty, COLOR_VALUES[i]);
			if (COLOR_VALUES[i].equalsIgnoreCase(currentValue)) {
				action.setChecked(true);
				needAdditional = false;
			}
			menu.add(action);
		}

		if (needAdditional) {
			ChangeStylePropertyAction action = new ChangeStylePropertyAction(
					currentValue, ele, cssProperty, currentValue);
			action.setChecked(true);
			menu.add(action);
		}
	}
}
