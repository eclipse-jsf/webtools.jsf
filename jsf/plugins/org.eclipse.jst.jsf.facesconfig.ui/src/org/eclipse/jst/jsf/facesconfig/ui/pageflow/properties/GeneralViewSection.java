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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.wst.common.ui.properties.internal.provisional.ISection;
import org.eclipse.wst.common.ui.properties.internal.provisional.ITabbedPropertyConstants;
import org.eclipse.wst.common.ui.properties.internal.provisional.TabbedPropertySheetPage;
import org.eclipse.wst.sse.ui.StructuredTextEditor;

/**
 * This class defines the general tab for pageflow attributes. This class
 * defines the controls shown in this sectin and their event handler. For
 * MultiPageEditorPart, if it support getAdapter(IEditorPart.class) to get the
 * current active editor, then, this section can get the different
 * PropertySheetPage for different sub EditorPart.
 * 
 * @author Xiao-guang Zhang
 */
public class GeneralViewSection implements ISection, SelectionListener,
		ISelectionChangedListener {
	/** the pagebook */
	private PageBook pageBook = null;

	/**
	 * A data structure used to store the information about a single page within
	 * a MultiPageEditorPart
	 */
	protected static class PageRec {

		/**
		 * The part including editorpart, or Control
		 */
		public IWorkbenchPart part;

		/**
		 * The page.
		 */
		public IPropertySheetPage page;

		/**
		 * Creates a new page record initialized to the given part and page.
		 * 
		 * @param part
		 * @param page
		 */
		public PageRec(IWorkbenchPart part, IPropertySheetPage page) {
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
	 * Map from parts to part records (key type: <code>IWorkbenchPart</code>;
	 * value type: <code>PartRec</code>).
	 */
	private Map mapPartToRec = new HashMap();

	/**
	 * The page record for the default page.
	 */
	private PageRec defaultPageRec;

	/**
	 * The page rec which provided the current page or <code>null</code>
	 */
	private PageRec activeRec;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.createControls(Composite, TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		FillLayout layout = new FillLayout();
		layout.marginWidth = ITabbedPropertyConstants.HSPACE;
		layout.marginHeight = ITabbedPropertyConstants.VSPACE;

		// composite.setLayout(layout);
		parent.setLayout(layout);
		// pagebook
		pageBook = new PageBook(parent, SWT.BORDER);

		// create a default property page.
		createDefaultPage();

		// Show the default page
		if (defaultPageRec != null) {
			showPageRec(defaultPageRec);
		}
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

		// Show new page.
		activeRec = pageRec;
		Control pageControl = activeRec.page.getControl();
		if (pageControl != null && !pageControl.isDisposed()) {
			// Verify that the page control is not disposed
			// If we are closing, it may have already been disposed
			pageBook.showPage(pageControl);
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
		}
		return rec;
	}

	/*
	 * (non-Javadoc) Method declared on PageBookView.
	 */
	protected PageRec doCreatePage(IWorkbenchPart part) {
		// FIXME: Because the PropertySheetPage of StructuredTextEditor has a
		// bug:
		// the editor can't be opened twice if the property view is shown.
		// This bug only exists in WTP 1.0.0 version.
		if (part instanceof StructuredTextEditor) {
			return null;
		}
		// Try to get an property page.
		Object obj = part.getAdapter(IPropertySheetPage.class);
		if (obj instanceof IPropertySheetPage) {
			IPropertySheetPage page = (IPropertySheetPage) obj;

			page.createControl(getPageBook());

			return new PageRec(part, page);
		}
		// There is no content property page
		return null;
	}

	private void createDefaultPage() {
		// Create the default PropertySheetPage rec.
		IPropertySheetPage defaultPage = new PropertySheetPage();
		defaultPage.createControl(getPageBook());

		defaultPageRec = new PageRec(null, defaultPage);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ISection#setInput(IWorkbenchPart, ISelection)
	 */
	public void setInput(IWorkbenchPart part, ISelection selection) {
		if (part instanceof MultiPageEditorPart) {
			IEditorPart subPage = (IEditorPart) part
					.getAdapter(IEditorPart.class);
			if (subPage != null) {
				// get or Create a PropertySheetPage for the part.
				PageRec rec = getPageRec(subPage);
				if (rec == null) {
					rec = createPage(subPage);
				}

				// Show the page.
				if (rec != null) {
					showPageRec(rec);
				} else {
					showPageRec(defaultPageRec);
				}
			}
		}
		activeRec.page.selectionChanged(part, selection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ISection#aboutToBeShown()
	 */
	public void aboutToBeShown() {
		refresh();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ISection#aboutToBeHidden()
	 */
	public void aboutToBeHidden() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ISection#dispose()
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
		Iterator iter = clone.values().iterator();
		while (iter.hasNext()) {
			PageRec rec = (PageRec) iter.next();
			removePage(rec);
		}
	}

	/**
	 * Removes a page record. If it is the last reference to the page dispose of
	 * it - otherwise just decrement the reference count.
	 * 
	 * @param rec
	 */
	private void removePage(PageRec rec) {
		mapPartToRec.remove(rec.part);

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
		IPropertySheetPage page = (IPropertySheetPage) rec.page;
		page.dispose();
		rec.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ISection#getMinimumHeight()
	 */
	public int getMinimumHeight() {
		return SWT.DEFAULT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.xtools.common.ui.properties.ISection#shouldUseExtraSpace()
	 */
	public boolean shouldUseExtraSpace() {
		/**
		 * here should return true, otherwise, the component controls will not
		 * fill the whole panel of the tab.
		 */
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.xtools.common.ui.properties.ISection#refresh()
	 */
	public void refresh() {
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		setInput((IWorkbenchPart) event.getSource(), event.getSelection());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
