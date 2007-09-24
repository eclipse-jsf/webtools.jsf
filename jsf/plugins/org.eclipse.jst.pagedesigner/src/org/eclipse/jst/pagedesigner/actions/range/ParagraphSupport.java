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

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * @author mengbo
 * @version 1.5
 */
public class ParagraphSupport {
	static final String[] labels = new String[] {
			PDPlugin.getResourceString("ParagraphSupport.CommandLabel.None"), //$NON-NLS-1$
			PDPlugin
					.getResourceString("ParagraphSupport.CommandLabel.Paragraph"), //$NON-NLS-1$
			PDPlugin
					.getResourceString("ParagraphSupport.CommandLabel.Heading1"), //$NON-NLS-1$
			PDPlugin
					.getResourceString("ParagraphSupport.CommandLabel.Heading2"), //$NON-NLS-1$
			PDPlugin
					.getResourceString("ParagraphSupport.CommandLabel.Heading3"), //$NON-NLS-1$ 
			PDPlugin
					.getResourceString("ParagraphSupport.CommandLabel.Heading4"),//$NON-NLS-1$ 
			PDPlugin
					.getResourceString("ParagraphSupport.CommandLabel.Heading5"), //$NON-NLS-1$ 
			PDPlugin
					.getResourceString("ParagraphSupport.CommandLabel.Heading6"), //$NON-NLS-1$ 
			PDPlugin
					.getResourceString("ParagraphSupport.CommandLabel.Preformated") //$NON-NLS-1$ 
	};

	static final String[] tags = new String[] {
			null,
			PDPlugin.getResourceString("ParagraphSupport.CommandLabel.P"), PDPlugin.getResourceString("ParagraphSupport.CommandLabel.H1"), PDPlugin.getResourceString("ParagraphSupport.CommandLabel.H2"), PDPlugin.getResourceString("ParagraphSupport.CommandLabel.H3"), PDPlugin.getResourceString("ParagraphSupport.CommandLabel.H4"), PDPlugin.getResourceString("ParagraphSupport.CommandLabel.H5"), PDPlugin.getResourceString("ParagraphSupport.CommandLabel.H6"), PDPlugin.getResourceString("ParagraphSupport.CommandLabel.PRE") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
	};

	/**
	 * @param man
	 * @param range
	 * @param viewer
	 */
	public static void createParagraphActions(IMenuManager man,
			DesignRange range, IHTMLGraphicalViewer viewer) {
		ParagraphStyleAction action = new NoneParagraphStyleAction(
				PDPlugin
						.getResourceString("ParagraphSupport.CommandLabel.None"), tags, null, IAction.AS_CHECK_BOX); //$NON-NLS-1$
		action.setViewer(viewer);
		action.update();
		man.add(action);
		for (int i = 1; i < labels.length; i++) {
			action = new ParagraphStyleAction(labels[i], tags[i], null,
					IAction.AS_CHECK_BOX);
			action.setViewer(viewer);
			action.update();
			man.add(action);
		}
	}
}
