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
package org.eclipse.jst.jsf.facesconfig.ui.page.detail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.jsf.facesconfig.ui.page.FacesConfigMasterDetailPage;
import org.eclipse.jst.jsf.facesconfig.ui.section.IFacesConfigSection;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.AbstractFormPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * The detail part of a page. It contains several IFacesConfigSection instances.
 * 
 * @author sfshi
 * 
 */
public abstract class FacesConfigDetailsPage extends AbstractFormPart implements
		IDetailsPage, ISelectionProvider, ISelectionChangedListener {

	private FacesConfigMasterDetailPage page;

	private IFacesConfigSection[] detailSections;

	private List selectionChangedListeners = new ArrayList();

	public FacesConfigDetailsPage(FacesConfigMasterDetailPage page) {
		super();
		this.page = page;
	}

	public void createContents(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		parent.setLayout(layout);

		parent.setLayoutData(new GridData(GridData.FILL_BOTH));

		FormToolkit toolkit = getManagedForm().getToolkit();
		Composite detailsContainer = toolkit.createComposite(parent);
		toolkit.paintBordersFor(detailsContainer);
		detailsContainer.setLayoutData(new GridData(GridData.FILL_BOTH));

		GridLayout gl = new GridLayout();
		gl.verticalSpacing = 0;
		gl.marginHeight = 0;
		detailsContainer.setLayout(gl);

		detailSections = createDetailSections(detailsContainer,
				getManagedForm(), toolkit, page);
		if (detailSections != null) {
			for (int i = 0, n = detailSections.length; i < n; i++) {
				detailSections[i].initialize();
				if (detailSections[i] instanceof ISelectionProvider) {
					((ISelectionProvider) detailSections[i])
							.addSelectionChangedListener(this);
				}
			}
		}
	}

	/**
	 * create the sub detail sections and set layout data for them.
	 * 
	 * @param composite
	 * @param managedForm
	 * @param toolkit
	 * @param page
	 * @return
	 */
	abstract protected IFacesConfigSection[] createDetailSections(
			Composite composite, IManagedForm managedForm, FormToolkit toolkit,
			FacesConfigMasterDetailPage page_);

	/**
	 * 
	 */
	public void selectionChanged(IFormPart part, ISelection selection) {
		Object selectedItem = ((StructuredSelection) selection)
				.getFirstElement();
		if (detailSections != null) {
			for (int i = 0, n = detailSections.length; i < n; i++) {
				IFacesConfigSection aSection = detailSections[i];
				aSection.setInput(selectedItem);
			}
		}

	}

	public FacesConfigMasterDetailPage getPage() {
		return page;
	}

	public void refresh() {
		super.refresh();
		if (detailSections != null) {
			for (int i = 0, n = detailSections.length; i < n; i++) {
				detailSections[i].refresh();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		for (Iterator listeners = selectionChangedListeners.iterator(); listeners
				.hasNext();) {
			ISelectionChangedListener listener = (ISelectionChangedListener) listeners
					.next();
			listener.selectionChanged(new SelectionChangedEvent(this, event
					.getSelection()));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		// TODO Auto-generated method stub
		selectionChangedListeners.add(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	public ISelection getSelection() {
		// TODO Auto-generated method stub
		return StructuredSelection.EMPTY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		// TODO Auto-generated method stub
		selectionChangedListeners.remove(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {
		// TODO Auto-generated method stub

	}

}
