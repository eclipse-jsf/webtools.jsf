/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.SubActionBars;
import org.eclipse.ui.part.IPageSite;

/**
 * 
 * This implementation of <code>IPageSite</code> provides a site for a sub
 * page within a <code>Page</code>. Most methods are forwarded to the parent
 * page's site.
 * 
 * @author Xiao-guang Zhang
 */
public class SubPageSite implements IPageSite {

	/**
	 * The "parent" Page site
	 */
	private IPageSite parentSite;

	/**
	 * A selection provider set by the page. Value is <code>null</code> until
	 * set.
	 */
	private ISelectionProvider selectionProvider;

	/**
	 * The action bars for this site
	 */
	private SubActionBars subActionBars;

	/**
	 * The list of menu extender for each registered menu.
	 */
//	private ArrayList menuExtenders;

	/**
	 * Creates a new sub page site of the given parent page site.
	 * 
	 * @param parentSite
	 *            the parent view site
	 */
	public SubPageSite(IPageSite parentSite) {
		Assert.isNotNull(parentSite);
		this.parentSite = parentSite;
		subActionBars = new SubActionBars(this.parentSite.getActionBars());
	}

	/**
	 * Disposes of the menu extender contributions.
	 */
	protected void dispose() {
		// if (menuExtenders != null) {
		// for (int i = 0; i < menuExtenders.size(); i++) {
		// ((PopupMenuExtender) menuExtenders.get(i)).dispose();
		// }
		// menuExtenders = null;
		//		}
		subActionBars.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.IPageSite#registerContextMenu(java.lang.String,
	 *      org.eclipse.jface.action.MenuManager,
	 *      org.eclipse.jface.viewers.ISelectionProvider)
	 */
	public void registerContextMenu(String menuId, MenuManager menuManager,
			ISelectionProvider selectionProvider1) {

		parentSite.registerContextMenu(menuId, menuManager, selectionProvider1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.IPageSite#getActionBars()
	 */
	public IActionBars getActionBars() {
		return subActionBars;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchSite#getPage()
	 */
	public IWorkbenchPage getPage() {
		return parentSite.getPage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchSite#getSelectionProvider()
	 */
	public ISelectionProvider getSelectionProvider() {
		return selectionProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchSite#getShell()
	 */
	public Shell getShell() {
		return parentSite.getShell();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchSite#getWorkbenchWindow()
	 */
	public IWorkbenchWindow getWorkbenchWindow() {
		return parentSite.getWorkbenchWindow();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchSite#setSelectionProvider(org.eclipse.jface.viewers.ISelectionProvider)
	 */
	public void setSelectionProvider(ISelectionProvider provider) {
		selectionProvider = provider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		return parentSite.getAdapter(adapter);
	}

	public Object getService(Class api) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasService(Class api) {
		// TODO Auto-generated method stub
		return false;
	}

}
