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
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowActionBarContributor;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowEditor;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.part.EditorActionBarContributor;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
import org.eclipse.wst.sse.ui.StructuredTextEditor;
import org.eclipse.wst.xml.ui.internal.tabletree.SourcePageActionContributor;

/**
 * The faces-config editor itself is composed by a set of pages. Each page has
 * its own action contributor. This FacesConfigActionBarContributor will work as
 * a proxy to delegate the action contributing to target nested action
 * contributor.
 * 
 * @author hmeng
 */

public class FacesConfigActionBarContributor extends
		MultiPageEditorActionBarContributor {
	protected SourcePageActionContributor sourceActionContributor = null;

	protected PageflowActionBarContributor pageflowActionContributor = null;

	protected EditingDomainActionBarContributor formbasedPageActionContributor = null;

	private IEditorPart activeNestedEditor;

	private IEditorPart targetEditor;

	public FacesConfigActionBarContributor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setActivePage(IEditorPart activeEditor) {
		if (activeEditor != activeNestedEditor) {
			if (getActionContributor(activeNestedEditor) != null) {
				getActionContributor(activeNestedEditor).setActiveEditor(
						activeEditor);
			}
			activeNestedEditor = activeEditor;
			EditorActionBarContributor activeContributor = getActionContributor(activeEditor);
			if (activeContributor != null)
				activeContributor.setActiveEditor(activeEditor);
			else
				super.setActiveEditor(activeEditor);
			updateActionBars();
		}
	}

	public void contributeToCoolBar(ICoolBarManager coolBarManager) {
		EditorActionBarContributor activeContributor = getActionContributor(activeNestedEditor);
		if (activeContributor != null)
			activeContributor.contributeToCoolBar(coolBarManager);
	}

	public void contributeToMenu(IMenuManager menuManager) {
		EditorActionBarContributor activeContributor = getActionContributor(activeNestedEditor);
		if (activeContributor != null) {
			activeContributor.contributeToMenu(menuManager);
		}
	}

	public void contributeToStatusLine(IStatusLineManager statusLineManager) {
		EditorActionBarContributor activeContributor = getActionContributor(activeNestedEditor);
		if (activeContributor != null) {
			activeContributor.contributeToStatusLine(statusLineManager);
		}
	}

	public void contributeToToolBar(IToolBarManager toolBarManager) {
		EditorActionBarContributor activeContributor = getActionContributor(activeNestedEditor);
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
		EditorActionBarContributor activeContributor = getActionContributor(activeNestedEditor);
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

	private IEditorPart getActiveNestedEditor(IEditorPart targetEditor) {
		IEditorPart activeNestedEditor;
		if (targetEditor instanceof FormEditor) {
			activeNestedEditor = ((FormEditor) targetEditor).getActiveEditor();
		} else {
			activeNestedEditor = targetEditor;
		}
		return activeNestedEditor;
	}

	private EditorActionBarContributor getActionContributor(
			IEditorPart activeNestedEditor) {
		EditorActionBarContributor activeContributor = null;
		if (activeNestedEditor instanceof PageflowEditor) {
			activeContributor = getPageflowActionContributor();
		} else if (activeNestedEditor instanceof StructuredTextEditor) {
			activeContributor = getSourceActionContributor();
		} else if (activeNestedEditor != null) {
			activeContributor = getFormbasedPageActionContributor();
		}
		return activeContributor;
	}

	public SourcePageActionContributor getSourceActionContributor() {
		if (sourceActionContributor == null) {
			sourceActionContributor = new SourcePageActionContributor();
		}
		return sourceActionContributor;
	}

	public EditingDomainActionBarContributor getFormbasedPageActionContributor() {
		if (formbasedPageActionContributor == null) {
			formbasedPageActionContributor = new MyEditingDomainActionContributor();
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

	public void updateActionBars() {
		EditorActionBarContributor activeContributor = getActionContributor(activeNestedEditor);
		if (activeContributor instanceof INestedActionContributor)
			((INestedActionContributor) activeContributor).update();
		// getActionBars().getMenuManager().removeAll();
		// activeContributor.contributeToMenu(getActionBars().getMenuManager());
		getActionBars().updateActionBars();
	}
}
