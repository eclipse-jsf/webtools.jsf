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

import org.eclipse.jst.pagedesigner.editors.HTMLEditor;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.wst.xml.ui.internal.actions.ActionContributorXML;

/**
 * SourcePageActionContributor
 * 
 * This class is for multi page editor's source page contributor.
 * 
 * Use XMLEditorActionContributor for single page editor.
 */
public class SourcePageActionContributor extends ActionContributorXML {

	private IActionBars fBars;

	/**
	 * This method calls:
	 * <ul>
	 * <li><code>contributeToMenu</code> with <code>bars</code>' menu
	 * manager</li>
	 * <li><code>contributeToToolBar</code> with <code>bars</code>' tool
	 * bar manager</li>
	 * <li><code>contributeToStatusLine</code> with <code>bars</code>'
	 * status line manager</li>
	 * </ul>
	 * The given action bars are also remembered and made accessible via
	 * <code>getActionBars</code>.
	 * 
	 * @param bars
	 *            the action bars
	 * 
	 */
	public void init(IActionBars bars) {
		fBars = bars;
		contributeToMenu(bars.getMenuManager());
		contributeToToolBar(bars.getToolBarManager());
		contributeToStatusLine(bars.getStatusLineManager());
	}

	/**
	 * Returns this contributor's action bars.
	 * 
	 * @return the action bars
	 */
	public IActionBars getActionBars() {
		return fBars;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.ui.internal.ISourceViewerActionBarContributor#setViewerSpecificContributionsEnabled(boolean)
	 */
	public void setViewerSpecificContributionsEnabled(boolean enabled) {
		super.setViewerSpecificContributionsEnabled(enabled);
		IEditorPart editor = getActiveEditorPart();
		ITextEditor targetEditor = null;
		if (editor instanceof ITextEditor) {
			targetEditor = (ITextEditor) editor;
		} else if (editor instanceof HTMLEditor) {
			targetEditor = ((HTMLEditor) editor).getTextEditor();
		}
		if (targetEditor != null) {
			if (enabled) {
				getActionBars()
						.setGlobalActionHandler(
								ITextEditorActionConstants.UNDO,
								targetEditor
										.getAction(ITextEditorActionConstants.UNDO));
				getActionBars()
						.setGlobalActionHandler(
								ITextEditorActionConstants.REDO,
								targetEditor
										.getAction(ITextEditorActionConstants.REDO));
			} else {
				getActionBars().setGlobalActionHandler(
						ITextEditorActionConstants.UNDO, null);
				getActionBars().setGlobalActionHandler(
						ITextEditorActionConstants.REDO, null);
			}

		}
	}
}
