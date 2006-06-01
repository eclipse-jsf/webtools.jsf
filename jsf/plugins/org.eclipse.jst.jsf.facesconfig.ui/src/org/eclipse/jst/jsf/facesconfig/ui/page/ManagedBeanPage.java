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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType;
import org.eclipse.jst.jsf.facesconfig.ui.FacesConfigEditor;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.jst.jsf.facesconfig.ui.page.detail.ManagedBeanDetailsPage;
import org.eclipse.jst.jsf.facesconfig.ui.section.FacesConfigMasterSection;
import org.eclipse.jst.jsf.facesconfig.ui.section.ManagedBeanMasterSection;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * 
 * @author sfshi
 * 
 */
public class ManagedBeanPage extends FacesConfigMasterDetailPage {

	public static final String PAGE_ID = "org.eclipse.jst.jsf.facesconfig.ui.page.ManagedBeanPage";

	private IContentOutlinePage contentOutlinePage;

	public ManagedBeanPage(FacesConfigEditor editor) {
		super(editor, "ManagedBeanPage",
				EditorMessages.FacesConfigEditor_ManagedBeans_TabName);
	}

	protected FacesConfigMasterSection[] createMasterSections(
			Composite composite, IManagedForm managedForm, FormToolkit toolkit,
			FacesConfigMasterDetailPage page) {
		FacesConfigMasterSection managedBeanMaster = new ManagedBeanMasterSection(
				composite, managedForm, toolkit, page);
		managedBeanMaster.getSection().setExpanded(true);
		GridData gd = new GridData(GridData.FILL_BOTH);
		managedBeanMaster.getSection().setLayoutData(gd);
		return new FacesConfigMasterSection[] { managedBeanMaster };
	}

	public IDetailsPage getPage(Object key) {
		IDetailsPage detailsPage = null;
		if (key instanceof EClass) {
			EClass eClass = (EClass) key;
			if (eClass.getClassifierID() == FacesConfigPackage.MANAGED_BEAN_TYPE) {
				detailsPage = new ManagedBeanDetailsPage(this);
			}
			
			if (detailsPage instanceof ISelectionProvider) {
				((ISelectionProvider) detailsPage)
						.addSelectionChangedListener(this);
			}
		}
		return detailsPage;
	}

	public Object getAdapter(Class adapter) {

		if (adapter == IContentOutlinePage.class) {
			return getOutlinePage();
		}

		return super.getAdapter(adapter);
	}

	private IContentOutlinePage getOutlinePage() {

		if (contentOutlinePage == null) {
			// The content outline is just a tree.
			//
			class ManagedBeanContentOutlinePage extends ContentOutlinePage {
				public void createControl(Composite parent) {
					super.createControl(parent);
					TreeViewer contentOutlineViewer = getTreeViewer();
					contentOutlineViewer.addSelectionChangedListener(this);

					AdapterFactory adapterFactory = (AdapterFactory) getEditor()
							.getAdapter(AdapterFactory.class);
					// Set up the tree viewer.
					contentOutlineViewer
							.setContentProvider(new AdapterFactoryContentProvider(
									adapterFactory));
					contentOutlineViewer
							.setLabelProvider(new AdapterFactoryLabelProvider(
									adapterFactory));
					contentOutlineViewer.addFilter(new ViewerFilter() {

						public boolean select(Viewer viewer,
								Object parentElement, Object element) {
							return FacesConfigPackage.eINSTANCE
									.getManagedBeanType().isInstance(element)
									|| FacesConfigPackage.eINSTANCE
											.getManagedPropertyType()
											.isInstance(element);
						}
					});

					contentOutlineViewer.setSorter(new ViewerSorter());
					contentOutlineViewer.setInput(getInput());
				}

			}

			contentOutlinePage = new ManagedBeanContentOutlinePage();

			// Listen to selection so that we can handle it is a special
			// way.
			//
			contentOutlinePage
					.addSelectionChangedListener(new ISelectionChangedListener() {
						// This ensures that we handle selections correctly.
						public void selectionChanged(SelectionChangedEvent event) {
							setSelection(event.getSelection());
						}
					});

			// this.addSelectionChangedListener((ContentOutlinePage)
			// contentOutlinePage);
		}

		return contentOutlinePage;

	}

	/**
	 * handle the selection changed event from outline page.
	 * 
	 * @param selection
	 */
	public void setSelection(ISelection selection) {

		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			ManagedBeanType bean = null;
			if (ss.getFirstElement() instanceof ManagedBeanType) {
				bean = (ManagedBeanType) ss.getFirstElement();
			} else if (ss.getFirstElement() instanceof ManagedPropertyType) {
				bean = (ManagedBeanType) ((ManagedPropertyType) ss
						.getFirstElement()).eContainer();
			}

			if (bean != null) {
				if (!getManagedBeanMasterSection().getSection().isExpanded()) {
					GridData gd = new GridData(GridData.FILL_BOTH);
					getManagedBeanMasterSection().getSection()
							.setLayoutData(gd);
					getManagedBeanMasterSection().getSection()
							.setExpanded(true);
				}
				IStructuredSelection newselection = new StructuredSelection(
						bean);
				getManagedBeanMasterSection().getStructuredViewer()
						.setSelection(newselection);
			}
		}

	}

	private ManagedBeanMasterSection getManagedBeanMasterSection() {
		return (ManagedBeanMasterSection) facesConfigMasterSections[0];
	}
}
