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
package org.eclipse.jst.pagedesigner.actions.range;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;

/**
 * @author mengbo
 * @version 1.5
 */
public class RangeStyleSupport {
	private static final String[] ActionLabel = new String[] {
			PDPlugin.getResourceString("RangeStyleSupport.ActionLabel.Bold"),//$NON-NLS-1$
			PDPlugin.getResourceString("RangeStyleSupport.ActionLabel.Italic"),//$NON-NLS-1$
			PDPlugin
					.getResourceString("RangeStyleSupport.ActionLabel.Underline"), }; //$NON-NLS-1$

	private static final String[] HtmlTag = new String[] { "STRONG", "I", "U", }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	private static final String[] CSSProperty = new String[] {
			"font-weight", "font-style", "text-decoration", }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	private static final String[] CSSValue = new String[] { "bolder", "italic", //$NON-NLS-1$ //$NON-NLS-2$
			"underline", }; //$NON-NLS-1$

	/**
	 * @param menu
	 * @param range
	 */
	public static void createRangeStyleActions(IMenuManager menu,
			DesignRange range) {
		for (int i = 0; i < ActionLabel.length; i++) {
			RangeStyleAction action = new RangeStyleAction(ActionLabel[i],
					range, HtmlTag[i], CSSProperty[i], CSSValue[i]);

			menu.add(action);
		}
	}
}
