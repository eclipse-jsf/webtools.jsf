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
package org.eclipse.jst.pagedesigner.properties;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.editors.HTMLEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.wst.common.ui.properties.internal.provisional.ITabbedPropertySheetPageContributor;
import org.eclipse.wst.common.ui.properties.internal.provisional.TabbedPropertySheetPage;
import org.w3c.dom.Node;

/**
 * @author mengbo
 * @author mengbo
 */
public class DesignerTabbedPropertySheetPage extends TabbedPropertySheetPage {
	// TODO: when we want to extend this page, HTMLEditor would not be the sole
	// type of editor part.
	private HTMLEditor _htmlEditor;

	private NavigationHiearchyAction _hiearchAction = new NavigationHiearchyAction(
			this);

	/**
	 * @param tabbedPropertySheetPageContributor
	 * @param editor 
	 */
	public DesignerTabbedPropertySheetPage(
			ITabbedPropertySheetPageContributor tabbedPropertySheetPageContributor,
			HTMLEditor editor) {
		super(tabbedPropertySheetPageContributor);
		_htmlEditor = editor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part == null) {
			part = _htmlEditor;
		}
		if (part instanceof HTMLEditor || part instanceof ContentOutline) {
			Node node = DesignerPropertyTool.normalizeSelectionToElement(part,
					selection, _htmlEditor);
			if (node != null) {
				try {
					_hiearchAction.refresh(node, node);
					this.getSite().getActionBars().getToolBarManager().update(
							true);
					super.selectionChanged(part, new StructuredSelection(node));
				} catch (Exception e) {
					// Some synchronization would cause this, it does not damage
					// the data.
				}
			}
		}
	}

	/**
	 * This method should be called from internal of the property page. Normally
	 * means user did some action inside the property sheet to change current
	 * selection.
	 * 
	 * @param selectedNode
	 * @param innerNode
	 */
	public void internalChangeSelection(Node selectedNode, Node innerNode) {
		getEditor().setFocus();
		_hiearchAction.refresh(selectedNode, innerNode);
		this.getSite().getActionBars().getToolBarManager().update(true);
		super.selectionChanged(null, new StructuredSelection(selectedNode));
	}

	/**
	 * @return the editor part
	 */
	public EditorPart getEditor() {
		return this._htmlEditor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.Page#init(org.eclipse.ui.part.IPageSite)
	 */
	public void init(IPageSite pageSite) {
		super.init(pageSite);
		setSelectionProvider();
		setSelectionListener();
		IToolBarManager toolbar = pageSite.getActionBars().getToolBarManager();
		toolbar.add(_hiearchAction);
		_hiearchAction.refresh(null, null);
	}

	private void setSelectionListener() {
		this.getSite().getWorkbenchWindow().getSelectionService()
				.addPostSelectionListener(new ISelectionListener() {
					public void selectionChanged(IWorkbenchPart part,
							ISelection selection) {
						DesignerTabbedPropertySheetPage.this.selectionChanged(
								part, selection);
					}
				});
	}

	private void setSelectionProvider() {
		this.getSite().setSelectionProvider(new ISelectionProvider() {
			private ISelection _selection;

			public void addSelectionChangedListener(
					ISelectionChangedListener listener) {
			    // do nothing
			}

			/**
			 * Returns the current selection for this provider.
			 * 
			 * @return the current selection
			 */
			public ISelection getSelection() {
				return _selection;
			}

			/**
			 * Removes the given selection change listener from this selection
			 * provider. Has no affect if an identical listener is not
			 * registered.
			 * 
			 * @param listener
			 *            a selection changed listener
			 */
			public void removeSelectionChangedListener(
					ISelectionChangedListener listener) {
				// do nothing
			}

			/**
			 * Sets the current selection for this selection provider.
			 * 
			 * @param selection
			 *            the new selection
			 */
			public void setSelection(ISelection selection) {
				_selection = selection;
			}
		});

	}

	public void createControl(Composite parent) {
		super.createControl(parent);
		PlatformUI
				.getWorkbench()
				.getHelpSystem()
				.setHelp(
						getControl(),
						PDPlugin
								.getResourceString("DesignerTabbedPropertySheetPage.help.id"));
	}
}
