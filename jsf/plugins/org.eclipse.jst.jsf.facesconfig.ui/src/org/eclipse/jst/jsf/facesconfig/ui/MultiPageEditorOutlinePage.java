/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.jsf.facesconfig.common.logging.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.SubActionBars;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.IPageBookViewPage;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.MessagePage;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * @author Xiao-guang Zhang
 * 
 * The outline page class for mulitPage Editorpart.
 */
public class MultiPageEditorOutlinePage extends Page implements
		IContentOutlinePage, SelectionListener {
	/** log instance */
	private static final Logger log = EditorPlugin
			.getLogger(MultiPageEditorOutlinePage.class);

	/**
	 * Selection change listeners.
	 */
	private ListenerList selectionChangedListeners = new ListenerList(ListenerList.IDENTITY);

	/** the pagebook */
	private PageBook pageBook = null;

	/**
	 * Selection change listener to listen for page selection changes
	 */
	private ISelectionChangedListener selectionChangedListener = new ISelectionChangedListener() {
		public void selectionChanged(SelectionChangedEvent event) {
			pageSelectionChanged(event);
		}
	};

	/**
	 * A data structure used to store the information about a single page within
	 * a MultiPageEditorOutlinePage
	 */
	protected static class PageRec {

		/**
		 * The part including editorpart, or Control
		 */
		public IWorkbenchPart part;

		/**
		 * The page.
		 */
		public IPage page;

		/**
		 * The page's action bars
		 */
		public SubActionBars subActionBars;

		/**
		 * Creates a new page record initialized to the given part and page.
		 * 
		 * @param part
		 * @param page
		 */
		public PageRec(IWorkbenchPart part, IPage page) {
			this.part = part;
			this.page = page;
		}

		/**
		 * Disposes of this page record by <code>null</code>ing its fields.
		 */
		public void dispose() {
			part = null;
			page = null;
		}
	}

	/**
	 * The page record for the default page.
	 */
	private PageRec defaultPageRec;

	/**
	 * Map from parts to part records (key type: <code>IWorkbenchPart</code>;
	 * value type: <code>PartRec</code>).
	 */
	private Map mapPartToRec = new HashMap();

	/**
	 * Map from pages to view sites Note that view sites were not added to page
	 * recs to avoid breaking binary compatibility with previous builds
	 */
	private Map mapPageToSite = new HashMap();

	/**
	 * The page rec which provided the current page or <code>null</code>
	 */
	private PageRec activeRec;

	/**
	 * the container composite control of MutliPageEditorPart
	 */
	private CTabFolder tabFolder;

	/**
	 * Creates a new MultiPageEditorOutlinePage instance.
	 * 
	 * 
	 */
	public MultiPageEditorOutlinePage() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.Page#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		// pagebook
		pageBook = new PageBook(parent, SWT.NONE);

		// Create the default page rec.
		IPage defaultPage = createDefaultPage(pageBook);
		defaultPageRec = new PageRec(null, defaultPage);
		preparePage(defaultPageRec);

		// Show the default page
		showPageRec(defaultPageRec);

		// get the tab control and add the page selection listener.
		if (getContainerForMultiPageEditorPart() != null) {
			getContainerForMultiPageEditorPart().addSelectionListener(this);
		}

		// show the activate part page.
		showBootstrapPart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Page#dispose()
	 */
	public void dispose() {
		// Deref all of the pages.
		activeRec = null;
		if (defaultPageRec != null) {
			// check for null since the default page may not have
			// been created (ex. perspective never visible)
			defaultPageRec.page.dispose();
			defaultPageRec = null;
		}
		Map clone = (Map) ((HashMap) mapPartToRec).clone();
		Iterator iterator = clone.values().iterator();
		while (iterator.hasNext()) {
			PageRec rec = (PageRec) iterator.next();
			removePage(rec);
		}

		// important: always call super implementation of dispose
		super.dispose();
	}

	/**
	 * Creates and returns the default page for this view.
	 * 
	 * @param book -
	 *            the pagebook control
	 * @return - the default page
	 */
	protected IPage createDefaultPage(PageBook book) {
		// Message to show on the default page
		String defaultText = EditorMessages.MultiPageEditorOutlinePage_noOutline;

		MessagePage page = new MessagePage();
		initPage(page);
		page.createControl(book);
		page.setMessage(defaultText);
		return page;
	}

	/**
	 * Prepares the page in the given page rec for use in this view.
	 * 
	 * @param rec -
	 *            the page rec
	 */
	private void preparePage(PageRec rec) {
		IPageSite site = null;

		if (!doesPageExist(rec.page)) {
			if (rec.page instanceof IPageBookViewPage) {
				site = ((IPageBookViewPage) rec.page).getSite();
			}
			if (site == null) {
				// We will create a site for our use
				site = new SubPageSite(getSite());
			}
			mapPageToSite.put(rec.page, site);

			rec.subActionBars = (SubActionBars) site.getActionBars();
			// rec.subActionBars.addPropertyChangeListener(actionBarPropListener);
			// for backward compability with IPage
			rec.page.setActionBars(rec.subActionBars);

		} else {
			site = (IPageSite) mapPageToSite.get(rec.page);
			rec.subActionBars = (SubActionBars) site.getActionBars();
		}
	}

	/**
	 * Returns the currently visible page for this view or <code>null</code>
	 * if no page is currently visible.
	 * 
	 * @return the currently visible page
	 */
	public IPage getCurrentPage() {
		if (activeRec == null)
			return null;
		return activeRec.page;
	}

	/**
	 * Returns the view site for the given page of this view.
	 * 
	 * @param page
	 *            the page
	 * @return the corresponding site, or <code>null</code> if not found
	 */
	protected SubPageSite getPageSite(IPage page) {
		return (SubPageSite) mapPageToSite.get(page);
	}

	/**
	 * Shows page contained in the given page record in this view. The page
	 * record must be one from this pagebook view.
	 * <p>
	 * The <code>PageBookView</code> implementation of this method asks the
	 * pagebook control to show the given page's control, and records that the
	 * given page is now current. Subclasses may extend.
	 * </p>
	 * 
	 * @param pageRec
	 *            the page record containing the page to show
	 */
	protected void showPageRec(PageRec pageRec) {
		IPageSite pageSite = getPageSite(pageRec.page);
		ISelectionProvider provider = pageSite.getSelectionProvider();
		if (provider == null && (pageRec.page instanceof IContentOutlinePage)) {
			// This means that the page did not set a provider during its
			// initialization
			// so for backward compatibility we will set the page itself as the
			// provider.
			pageSite.setSelectionProvider((IContentOutlinePage) pageRec.page);
		}

		// If already showing do nothing
		if (activeRec == pageRec) {
			return;
		}
		// If the page is the same, just set activeRec to pageRec
		if (activeRec != null && pageRec != null
				&& activeRec.page == pageRec.page) {
			activeRec = pageRec;
			return;
		}

		// Hide old page.
		if (activeRec != null) {
			activeRec.subActionBars.deactivate();
			// remove our selection listener
			provider = ((SubPageSite) mapPageToSite.get(activeRec.page))
					.getSelectionProvider();
			if (provider != null) {
				provider
						.removeSelectionChangedListener(selectionChangedListener);
			}
		}
		// Show new page.
		activeRec = pageRec;
		Control pageControl = activeRec.page.getControl();
		if (pageControl != null && !pageControl.isDisposed()) {
			// Verify that the page control is not disposed
			// If we are closing, it may have already been disposed
			pageBook.showPage(pageControl);
			activeRec.subActionBars.activate();
			refreshGlobalActionHandlers();
			// add our selection listener
			provider = ((SubPageSite) mapPageToSite.get(activeRec.page))
					.getSelectionProvider();
			if (provider != null) {
				provider.addSelectionChangedListener(selectionChangedListener);
			}
			// Update action bars.
			getSite().getActionBars().updateActionBars();
		}
	}

	/**
	 * Refreshes the global actions for the active page.
	 */
	private void refreshGlobalActionHandlers() {
		// Clear old actions.
		IActionBars bars = getSite().getActionBars();
		bars.clearGlobalActionHandlers();

		// Set new actions.
		Map newActionHandlers = activeRec.subActionBars
				.getGlobalActionHandlers();
		if (newActionHandlers != null) {
			Set keys = newActionHandlers.entrySet();
			Iterator iter = keys.iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				bars.setGlobalActionHandler((String) entry.getKey(),
						(IAction) entry.getValue());
			}
		}
	}

	/**
	 * Creates a page for a given part. Adds it to the pagebook but does not
	 * show it.
	 * 
	 * @param part
	 *            The part we are making a page for.
	 * @return IWorkbenchPart
	 */
	private PageRec createPage(IWorkbenchPart part) {
		PageRec rec = doCreatePage(part);
		if (rec != null) {
			mapPartToRec.put(part, rec);
			preparePage(rec);
		}
		return rec;
	}

	/*
	 * (non-Javadoc) Method declared on PageBookView.
	 */
	protected PageRec doCreatePage(IWorkbenchPart part) {
		// Try to get an outline page.
		Object obj = part.getAdapter(IContentOutlinePage.class);
		if (obj instanceof IContentOutlinePage) {
			IContentOutlinePage page = (IContentOutlinePage) obj;
			if (page instanceof IPageBookViewPage) {
				initPage((IPageBookViewPage) page);
			}
			page.createControl(getPageBook());
			return new PageRec(part, page);
		}
		// There is no content outline
		return null;
	}

	/**
	 * Returns the pagebook control for this view.
	 * 
	 * @return the pagebook control, or <code>null</code> if not initialized
	 */
	protected PageBook getPageBook() {
		return pageBook;
	}

	/**
	 * Returns the page record for the given part.
	 * 
	 * @param part
	 *            the part
	 * @return the corresponding page record, or <code>null</code> if not
	 *         found
	 */
	protected PageRec getPageRec(Object part) {
		return (PageRec) mapPartToRec.get(part);
	}

	/**
	 * Initializes the given page with a page site.
	 * <p>
	 * Subclasses should call this method after the page is created but before
	 * creating its controls.
	 * </p>
	 * <p>
	 * Subclasses may override
	 * </p>
	 * 
	 * @param page
	 *            The page to initialize
	 */
	protected void initPage(IPageBookViewPage page) {
		try {
			page.init(new SubPageSite(getSite()));
		} catch (PartInitException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * Shows a page for the active workbench part.
	 */
	private void showBootstrapPart() {
		IWorkbenchPart part = getBootstrapPart();
		if (part != null) {
			partActivated(part);
		}
	}

	/**
	 * Returns the active, important workbench part for this view.
	 * 
	 * @return the active important part, or <code>null</code> if none
	 */
	private IWorkbenchPart getBootstrapPart() {
		IWorkbenchPage page = getSite().getPage();
		if (page != null
				&& page.getActiveEditor() instanceof MultiPageEditorPart) {
			// get active editor of mutli-page editor.
			return (IWorkbenchPart) page.getActiveEditor().getAdapter(
					IEditorPart.class);
		}
        return null;
	}

	/**
	 * This method shows the page when the given part is activated. Subclasses
	 * may extend.
	 */
	private void partActivated(IWorkbenchPart part) {
		// Is this an important part? If not just return.
		if (!isImportant(part)) {
			return;
		}

		// Create a page for the part.
		PageRec rec = getPageRec(part);
		if (rec == null) {
			rec = createPage(part);
		}

		// Show the page.
		if (rec != null) {
			showPageRec(rec);
		} else {
			showPageRec(defaultPageRec);
		}
	}

	/**
	 * Returns true if the page has already been created.
	 * 
	 * @param page
	 *            the page to test
	 * @return true if this page has already been created.
	 */
	private boolean doesPageExist(IPage page) {
		return mapPageToSite.containsKey(page);
	}

	/**
	 * Returns whether the given part should be added to this view.
	 * 
	 * @param part
	 *            the input part
	 * @return <code>true</code> if the part is relevant, and
	 *         <code>false</code> otherwise
	 */
	protected boolean isImportant(IWorkbenchPart part) {
		// We only care about editors
		return (part instanceof IEditorPart);
	}

	/**
	 * get the composite control (Container) of source MultiPageEditorPart
	 * 
	 * @return - the composite control (Container)
	 */
	private CTabFolder getContainerForMultiPageEditorPart() {
		if (null == tabFolder) {
			tabFolder = ((CTabFolder) (getSite().getPage().getActiveEditor()
					.getAdapter(CTabFolder.class)));
		}
		return tabFolder;
	}

	/**
	 * Removes a page record. If it is the last reference to the page dispose of
	 * it - otherwise just decrement the reference count.
	 * 
	 * @param rec
	 */
	private void removePage(PageRec rec) {
		mapPartToRec.remove(rec.part);
		IPageSite site = (IPageSite) mapPageToSite.remove(rec.page);

		if (rec.subActionBars != null) {
			rec.subActionBars.dispose();
		}

		Control control = rec.page.getControl();
		if (control != null && !control.isDisposed()) {
			// Dispose the page's control so pages don't have to do this in
			// their
			// dispose method.
			// The page's control is a child of this view's control so if this
			// view
			// is closed, the page's control will already be disposed.
			control.dispose();
		}

		if (site instanceof SubPageSite) {
			((SubPageSite) site).dispose();
		}

		// free the page
		doDestroyPage(rec.part, rec);
	}

	/**
	 * Destroys a page in the pagebook for a particular part.
	 * 
	 * @param part
	 *            the input part
	 * @param pageRecord
	 *            a page record for the part
	 */
	protected void doDestroyPage(IWorkbenchPart part, PageRec rec) {
		IContentOutlinePage page = (IContentOutlinePage) rec.page;
		page.dispose();
		rec.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.Page#getControl()
	 */
	public Control getControl() {
		return pageBook;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.Page#setFocus()
	 */
	public void setFocus() {
		if (getControl() != null) {
			getControl().setFocus();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	public ISelection getSelection() {
		// get the selection provider from the current page
		IPage currentPage = getCurrentPage();
		// during workbench startup we may be in a state when
		// there is no current page
		if (currentPage == null) {
			return StructuredSelection.EMPTY;
		}
		IPageSite site = getPageSite(currentPage);
		if (site == null) {
			return StructuredSelection.EMPTY;
		}
		ISelectionProvider selProvider = site.getSelectionProvider();
		if (selProvider != null) {
			return selProvider.getSelection();
		}
		return StructuredSelection.EMPTY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {
		// get the selection provider from the current page
		IPage currentPage = getCurrentPage();
		// during workbench startup we may be in a state when
		// there is no current page
		if (currentPage == null) {
			return;
		}
		IPageSite site = getPageSite(currentPage);
		if (site == null) {
			return;
		}
		ISelectionProvider selProvider = site.getSelectionProvider();
		// and set its selection
		if (selProvider != null) {
			selProvider.setSelection(selection);
		}
	}

	/**
	 * The selection has changed. Process the event.
	 * 
	 * @param event
	 */
	public void pageSelectionChanged(final SelectionChangedEvent event) {
		// pass on the notification to listeners
		Object[] listeners = selectionChangedListeners.getListeners();
		for (int i = 0; i < listeners.length; ++i) {
			final ISelectionChangedListener l = (ISelectionChangedListener) listeners[i];
			SafeRunner.run(new SafeRunnable() {
				public void run() {
					l.selectionChanged(event);
				}
			});
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SelectionListener#widgetSelected(SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent e) {
		EditorPart part = (EditorPart) ((CTabItem) e.item).getData();

		if (part != null) {
			partActivated(part);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SelectionListener#widgetDefaultSelected(SelectionEvent)
	 */
	public void widgetDefaultSelected(SelectionEvent e) {
        // do nothing: no handling of default selected event
	}
}
