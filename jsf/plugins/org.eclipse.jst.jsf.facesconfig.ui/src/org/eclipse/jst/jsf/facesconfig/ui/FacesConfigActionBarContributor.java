/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui;

import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowActionBarContributor;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowEditor;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IActionBars2;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.part.EditorActionBarContributor;
import org.eclipse.wst.sse.ui.StructuredTextEditor;

/**
 * @author hmeng
 */

public class FacesConfigActionBarContributor extends ActionBarContributor {
	protected SourceActionBarContributor sourceActionContributor = null;

	protected PageflowActionBarContributor pageflowActionContributor = null;

	protected EditingDomainActionBarContributor formbasedPageActionContributor = null;

	private EditorActionBarContributor activeContributor;

	public FacesConfigActionBarContributor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void contributeToCoolBar(ICoolBarManager coolBarManager) {
		if (activeContributor != null)
			activeContributor.contributeToCoolBar(coolBarManager);
	}

	public void contributeToMenu(IMenuManager menuManager) {
		if (activeContributor != null) {
			activeContributor.contributeToMenu(menuManager);
		}
	}

	public void contributeToStatusLine(IStatusLineManager statusLineManager) {
		if (activeContributor != null) {
			activeContributor.contributeToStatusLine(statusLineManager);
		}
	}

	public void contributeToToolBar(IToolBarManager toolBarManager) {
		if (activeContributor != null) {
			activeContributor.contributeToToolBar(toolBarManager);
		}
	}

	public void dispose() {
		getFormbasedPageActionContributor().dispose();
		getPageflowActionContributor().dispose();
		getSourceActionContributor().dispose();
	}

	public IActionBars getActionBars() {
		if (activeContributor != null) {
			return activeContributor.getActionBars();
		} else {
			return super.getActionBars();
		}
	}

	public IWorkbenchPage getPage() {
		// TODO Auto-generated method stub
		return super.getPage();
	}

	public void init(IActionBars bars, IWorkbenchPage page) {
		getPageflowActionContributor().init(bars, page);
		getSourceActionContributor().init(bars, page);
		getFormbasedPageActionContributor().init(bars, page);
		super.init(bars, page);
	}

	public void init(IActionBars bars) {
		// getPageflowActionContributor().init(bars);
		// getSourceActionContributor().init(bars);
		// getFormbasedPageActionContributor().init(bars);
		super.init(bars);
	}

	public void setActiveEditor(IEditorPart targetEditor) {
		if (activeContributor != null)
			activeContributor.setActiveEditor(targetEditor);
		else
			super.setActiveEditor(targetEditor);
	}

	public void pageChanged(IEditorPart targetEditor) {
		IEditorPart activeNestedEditor = null;
		if (targetEditor instanceof FormEditor) {
			activeNestedEditor = ((FormEditor) targetEditor).getActiveEditor();
		}
		if (activeNestedEditor == null) {
			return;
		}
		if (activeNestedEditor instanceof PageflowEditor) {
			activeContributor = getPageflowActionContributor();
		} else if (activeNestedEditor instanceof StructuredTextEditor) {
			activeContributor = getSourceActionContributor();
			getSourceActionContributor().active(true);
			getSourceActionContributor().setActiveEditor(activeNestedEditor);
		} else {
			activeContributor = getFormbasedPageActionContributor();
		}
		if (activeNestedEditor != getSourceActionContributor()) {
			getSourceActionContributor().active(false);
			ActionRegistry registry = (ActionRegistry) activeNestedEditor
					.getAdapter(ActionRegistry.class);

			if (registry != null) {
				activeContributor.setActiveEditor(activeNestedEditor);
			} else {
				getActionBars().updateActionBars();
			}
		}
		IActionBars bars = targetEditor.getEditorSite().getActionBars();
		// bars.getToolBarManager().getItems()
		// bars.getMenuManager().removeAll();
		// bars.getToolBarManager().removeAll();
		// bars.getStatusLineManager().removeAll();
		if (bars instanceof IActionBars2) {
			// ((IActionBars2) bars).getCoolBarManager().removeAll();
			// activeContributor.contributeToCoolBar(((IActionBars2) bars)
			// .getCoolBarManager());
			// ((IActionBars2) bars).getCoolBarManager().update(false);
		}
		// activeContributor.contributeToMenu(bars.getMenuManager());
		// activeContributor.contributeToStatusLine(bars.getStatusLineManager());
		// activeContributor.contributeToToolBar(bars.getToolBarManager());
		// bars.updateActionBars();
		// // bars.getMenuManager().updateAll(false);
		// // bars.getStatusLineManager().update(false);
		// bars.getToolBarManager().update(true);
	}

	protected void buildActions() {
		// getPageflowActionContributor().buildActions();
	}

	public SourceActionBarContributor getSourceActionContributor() {
		if (sourceActionContributor == null) {
			sourceActionContributor = new SourceActionBarContributor();
		}
		return sourceActionContributor;
	}

	public EditingDomainActionBarContributor getFormbasedPageActionContributor() {
		if (formbasedPageActionContributor == null) {
			formbasedPageActionContributor = new EditingDomainActionBarContributor();
		}
		return formbasedPageActionContributor;
	}

	protected void declareGlobalActionKeys() {
		// TODO Auto-generated method stub

	}

	public PageflowActionBarContributor getPageflowActionContributor() {
		if (pageflowActionContributor == null) {
			pageflowActionContributor = new PageflowActionBarContributor();
		}
		return pageflowActionContributor;

	}

}
