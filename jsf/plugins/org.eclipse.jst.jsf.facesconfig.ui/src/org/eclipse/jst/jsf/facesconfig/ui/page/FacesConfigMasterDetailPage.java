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
package org.eclipse.jst.jsf.facesconfig.ui.page;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.FacesConfigEditor;
import org.eclipse.jst.jsf.facesconfig.ui.page.detail.FacesConfigDetailsPage;
import org.eclipse.jst.jsf.facesconfig.ui.section.FacesConfigMasterSection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IDetailsPageProvider;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.views.properties.IPropertySheetPage;

/**
 * In some cases the page is a master-detail style, the content of the right
 * side will change according user's selection on the left side. This is an
 * abstract implementation of IFacesConfigPage, with a FacesConfigMasterSection
 * array and a DetailsPart.
 * 
 * @author sfshi
 * 
 */
public abstract class FacesConfigMasterDetailPage extends FormPage implements
		IFacesConfigPage, IDetailsPageProvider, ISelectionChangedListener,
		ISelectionProvider, IEditingDomainProvider {

	private static final String BANNER_IMAGE_FILE = "form_banner.gif"; //$NON-NLS-1$

	private final static Image BANNER_IMAGE = EditorPlugin.getDefault()
			.getImage(BANNER_IMAGE_FILE);

	private FacesConfigMasterSection[] facesConfigMasterSections;

	private DetailsPart detailsPart;

	private Object input;

	private List selectionChangedListeners = new ArrayList();

	private IPropertySheetPage propertySheetPage;
	
	private ISelection currentSelection = null;

	/**
	 * 
	 * @param editor
	 *            the editor part that this page belongs to;
	 * @param id
	 *            page id;
	 * @param title
	 *            page title;
	 */
	public FacesConfigMasterDetailPage(FacesConfigEditor editor, String id,
			String title) {
		super(editor, id, title);
	}

	public void dispose() {
		for(int i=0; i < facesConfigMasterSections.length;i++) {
			FacesConfigMasterSection master = facesConfigMasterSections[i];
			if (master != null)
				master.dispose();
		}
		
		selectionChangedListeners.clear();
		
		super.dispose();
	}
	public EditingDomain getEditingDomain() {
		return ((FacesConfigEditor) getEditor()).getEditingDomain();
	}

	/**
	 * 
	 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	protected void createFormContent(IManagedForm managedForm) {

		ScrolledForm form = managedForm.getForm();
		form.setText(this.getTitle());
		form.setBackgroundImage(BANNER_IMAGE); 
		fillBody(managedForm, managedForm.getToolkit());
		managedForm.refresh();
	}

	/**
	 * set input for this page when the it got activated at the first time; if
	 * it's not the first time, then refresh the page.
	 * 
	 * @see org.eclipse.ui.forms.editor.FormPage#setActive(boolean)
	 */
	public void setActive(boolean active) {
		super.setActive(active);
		if (active) {
			if (getInput() != ((FacesConfigEditor) getEditor())
					.getFacesConfig()) {
				setInput(((FacesConfigEditor) getEditor()).getFacesConfig());
			} else {
				this.refresh();
			}
		}
	}

	public boolean isEditor() {
		return true;
	}

	/**
	 * 
	 * @param managedForm
	 * @param toolkit
	 */
	private void fillBody(IManagedForm managedForm, FormToolkit toolkit) {
		Composite body = managedForm.getForm().getBody();

		GridLayout gridLayout = new GridLayout();
		gridLayout.horizontalSpacing = 0;
		gridLayout.verticalSpacing = 0;
		gridLayout.numColumns = 2;
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		body.setLayout(gridLayout);

		SashForm sashForm = new SashForm(body, SWT.NULL);
		managedForm.getToolkit().adapt(sashForm, false, false);
		sashForm.setMenu(body.getMenu());
		sashForm.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite leftContainer = toolkit.createComposite(sashForm);
		GridLayout gl = new GridLayout();
		gl.marginHeight = 0;
		leftContainer.setLayout(gl);

		// create master sections here
		facesConfigMasterSections = createMasterSections(leftContainer,
				managedForm, toolkit, this);

		if (facesConfigMasterSections != null) {
			for (int i = 0, n = facesConfigMasterSections.length; i < n; i++) {
				FacesConfigMasterSection aSection = facesConfigMasterSections[i];
				aSection.initialize();
				aSection.addSelectionChangedListener(this);

			}
		}

		detailsPart = new DetailsPart(managedForm, sashForm, SWT.NULL);
		managedForm.addPart(detailsPart);

		detailsPart.setPageLimit(4);
		detailsPart.setPageProvider(this);

		detailsPart.initialize(managedForm);

		sashForm.setWeights(new int[] { 40, 60 });

		getSite().setSelectionProvider(this);
	}

	/**
	 * Create the detail sections, and set layout data and initial expand state
	 * for them.
	 * 
	 * @param composite
	 * @param managedForm
	 * @param toolkit
	 * @param page
	 * @return the detail sections
	 */
	abstract protected FacesConfigMasterSection[] createMasterSections(
			Composite composite, IManagedForm managedForm, FormToolkit toolkit,
			FacesConfigMasterDetailPage page);

	/**
	 * set input object; also set into all the master sections.
	 */
	public void setInput(Object input) {
		if (facesConfigMasterSections != null) {
			for (int i = 0, n = facesConfigMasterSections.length; i < n; i++) {
				facesConfigMasterSections[i].setInput(input);
			}
		}
		this.input = input;
	}

	/**
	 * get the input object.
	 */
	public Object getInput() {
		return input;
	}

	/**
	 * Defaultly use the EClass object of the selected object as page key. Sub
	 * classes can override this method if there has other ways.
	 * 
	 * @param object
	 */
	public Object getPageKey(Object object) {
		if (object instanceof EObject) {
			EObject eObject = (EObject) object;
			return eObject.eClass();
		}
		return null;
	}

	/**
	 * Subclasses should create and return an IDetailsPage instance according
	 * what the key is. Defaultly the EClass object of the selected object in
	 * the structured viewer will be taken as the page key.
	 * 
	 * @see #getPageKey
	 * 
	 */
	abstract public IDetailsPage getPage(Object key);

	/**
	 * transfer the selection changed event to detail part.
	 */
	public void selectionChanged(SelectionChangedEvent event) {

		currentSelection = event.getSelection();
		if (event.getSource() instanceof FacesConfigMasterSection) {
			FacesConfigMasterSection source = (FacesConfigMasterSection) event
					.getSource();

			detailsPart.selectionChanged(source, event.getSelection());

			if (source.getSection().isExpanded()) {
				for (int i = 0, n = selectionChangedListeners.size(); i < n; i++) {
					((ISelectionChangedListener) selectionChangedListeners
							.get(i)).selectionChanged(event);
				}
			}
		} else if (event.getSource() instanceof FacesConfigDetailsPage) {
			for (int i = 0, n = selectionChangedListeners.size(); i < n; i++) {
				((ISelectionChangedListener) selectionChangedListeners.get(i))
						.selectionChanged(event);
			}
		}
	}

	/**
	 * set other sections' expand state to false, except this section;
	 * 
	 * @param section
	 *            the section that be expanded.
	 */
	public void closeOtherSections(FacesConfigMasterSection section) {

		for (int i = 0, n = facesConfigMasterSections.length; i < n; i++) {
			FacesConfigMasterSection aSection = facesConfigMasterSections[i];
			if (aSection != section && aSection.getSection().isExpanded()) {
				aSection.getSection().setExpanded(false);
				GridData gd = new GridData(GridData.FILL_HORIZONTAL);
				aSection.getSection().setLayoutData(gd);
			}
		}
	}

	/**
	 * refresh this page, the expanded section and the detail page will got
	 * refreshed.
	 */
	public void refresh() {

		// only refresh the expanded section;
		for (int i = 0, n = facesConfigMasterSections.length; i < n; i++) {
			if (facesConfigMasterSections[i].getSection().isExpanded()) {
				facesConfigMasterSections[i].refresh();
			}
		}

		// refresh the detail page, it will call detail sections
		// to got refreshed.
		if (detailsPart.getCurrentPage() != null) {
			detailsPart.getCurrentPage().refresh();
		}
	}

	/**
	 * get the section that is expanded.
	 * 
	 * @return the master section
	 */
	protected FacesConfigMasterSection getActiveSection() {
		for (int i = 0, n = facesConfigMasterSections.length; i < n; i++) {

			if (facesConfigMasterSections[i].getSection().isExpanded()) {
				return facesConfigMasterSections[i];
			}
		}
		return null;

	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);
	}

	public ISelection getSelection() {
		if(currentSelection != null)
			return currentSelection;
		return StructuredSelection.EMPTY;

	}

	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	public void setSelection(ISelection selection) {
	    // no selection setting
	}

	public Object getAdapter(Class adapter) {

		if (adapter == IPropertySheetPage.class) {
			return getPropertySheetPage();
		}
		return super.getAdapter(adapter);
	}

	private IPropertySheetPage getPropertySheetPage() {

		AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain) getEditor()
				.getAdapter(EditingDomain.class);
		AdapterFactory adapterFactory = (AdapterFactory) getEditor()
				.getAdapter(AdapterFactory.class);
		if (propertySheetPage == null) {
			propertySheetPage = new ExtendedPropertySheetPage(editingDomain) {
				public void setSelectionToViewer(List selection) {
					// FacesConfigEditor.this.setSelectionToViewer(selection);
					// FacesConfigEditor.this.setFocus();
				}

				public void setActionBars(IActionBars actionBars) {
					super.setActionBars(actionBars);
					// getActionBarContributor().shareGlobalActions(this,
					// actionBars);
				}
			};
			((ExtendedPropertySheetPage) propertySheetPage)
					.setPropertySourceProvider(new AdapterFactoryContentProvider(
							adapterFactory));
			// this.addSelectionChangedListener((ExtendedPropertySheetPage)
			// propertySheetPage);
		}

		return propertySheetPage;

	}

	/**
	 * @return the facesConfigMasterSections
	 */
	public FacesConfigMasterSection[] getFacesConfigMasterSections() {
		return facesConfigMasterSections;
	}
}
