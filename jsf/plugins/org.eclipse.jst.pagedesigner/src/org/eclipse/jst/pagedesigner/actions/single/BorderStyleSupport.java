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
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public class BorderStyleSupport {
	public static final String VAL_HIDDEN = PDPlugin
			.getResourceString("BorderStyleSupport.CommandLabel.Hidden"); //$NON-NLS-1$

	public static final String VAL_DOTTED = PDPlugin
			.getResourceString("BorderStyleSupport.CommandLabel.Dotted"); //$NON-NLS-1$

	public static final String VAL_DASHED = PDPlugin
			.getResourceString("BorderStyleSupport.CommandLabel.Dashed"); //$NON-NLS-1$

	public static final String VAL_SOLID = PDPlugin
			.getResourceString("BorderStyleSupport.CommandLabel.Solid"); //$NON-NLS-1$

	public static final String VAL_DOUBLE = PDPlugin
			.getResourceString("BorderStyleSupport.CommandLabel.Double"); //$NON-NLS-1$

	public static final String VAL_GROOVE = PDPlugin
			.getResourceString("BorderStyleSupport.CommandLabel.Groove"); //$NON-NLS-1$

	public static final String VAL_RIDGE = PDPlugin
			.getResourceString("BorderStyleSupport.CommandLabel.Ridge"); //$NON-NLS-1$

	public static final String VAL_INSET = PDPlugin
			.getResourceString("BorderStyleSupport.CommandLabel.Inset"); //$NON-NLS-1$

	public static final String VAL_OUTSET = PDPlugin
			.getResourceString("BorderStyleSupport.CommandLabel.Outset"); //$NON-NLS-1$

	public static final String[] BORDERSTYLES = new String[] { VAL_HIDDEN,
			VAL_DOTTED, VAL_DASHED, VAL_SOLID, VAL_DOUBLE, VAL_GROOVE,
			VAL_RIDGE, VAL_INSET, VAL_OUTSET };

	public static final String getCurrentBorderStyle(IDOMElement ele) {
		return VAL_HIDDEN;
	}

	public static void createParagraphActions(IMenuManager man,
			IDOMElement ele, String currentMode) {
		for (int i = 0; i < BORDERSTYLES.length; i++) {
			BorderStyleAction action = new BorderStyleAction(BORDERSTYLES[i],
					ele, BORDERSTYLES[i]);
			if (BORDERSTYLES[i].equalsIgnoreCase(currentMode)) {
				action.setChecked(true);
			}
			man.add(action);
		}
	}
}
