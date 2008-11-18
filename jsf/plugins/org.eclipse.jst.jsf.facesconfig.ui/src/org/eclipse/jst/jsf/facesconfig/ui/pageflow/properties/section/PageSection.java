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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.section;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogFieldApplyListener;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PageflowNodeEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowPackageImpl;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.PageflowValidation;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * Edit section for Pagflow Page Element (EditPart)
 * 
 * @author jchoi, Xiao-guang Zhang
 */
public class PageSection extends AbstractEditPartSection {
	/** edit group */
	private PageGroup group;

	/** the emf model for Pageflow page */
	private PageflowPage pfPage = null;

	/**
	 * 
	 */
	public PageSection() {
		super();
		group = new PageGroup();
		group.setDefaultChangeListener(changeListener);
		group.initialize();

		group.getFromViewField().setDialogFieldApplyListener(
				new IDialogFieldApplyListener() {

					public void dialogFieldApplied(DialogField field) {
						if (pfPage != null) {
							String jsfNew = group.getFromViewField().getText();
							if (jsfNew != null && jsfNew.length() > 0) {
								if (PageflowValidation.getInstance()
										.isExistedPage(pfPage.getPageflow(),
												jsfNew)) {
									// Pageflow.PageflowEditor.Alert.DNDResourceTitle
									// = Pageflow Creation Error
									// Pageflow.PageflowEditor.Alert.ExistingPage
									// = The web page {0} is already existed in
									// current PageFlow.
									EditorPlugin
											.getAlerts()
											.error(
													"Pageflow.PageflowEditor.Alert.DNDResourceTitle", //$NON-NLS-1$
													"Pageflow.PageflowEditor.Alert.ExistingPage", //$NON-NLS-1$
													jsfNew);
								} else {
									PageSection.this
											.setValue(
													PageflowPackageImpl.eINSTANCE
															.getPFPage_Path()
															.getName(), jsfNew);
								}
							}
						}
					}
				});

		group.getLargeIconField().setDialogFieldApplyListener(
				new IDialogFieldApplyListener() {

					public void dialogFieldApplied(DialogField field) {
						PageSection.this.setValue(PageflowPackageImpl.eINSTANCE
								.getPFPage_Largeicon().getName(), group
								.getLargeIconField().getText());
					}
				});
		group.getSmallIconField().setDialogFieldApplyListener(
				new IDialogFieldApplyListener() {

					public void dialogFieldApplied(DialogField field) {
						PageSection.this.setValue(PageflowPackageImpl.eINSTANCE
								.getPFPage_Smallicon().getName(), group
								.getSmallIconField().getText());
					}
				});

		group.getDescField().setDialogFieldApplyListener(
				new IDialogFieldApplyListener() {

					public void dialogFieldApplied(DialogField field) {
						PageSection.this.setValue(PageflowPackageImpl.eINSTANCE
								.getPageflowElement_Comment().getName(), group
								.getDescField().getText());
					}
				});

		group.getDisplayNameField().setDialogFieldApplyListener(
				new IDialogFieldApplyListener() {

					public void dialogFieldApplied(DialogField field) {
						PageSection.this.setValue(PageflowPackageImpl.eINSTANCE
								.getPageflowElement_Name().getName(), group
								.getDisplayNameField().getText());
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.wst.common.ui.properties.internal.provisional.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		TabbedPropertySheetWidgetFactory factory = aTabbedPropertySheetPage
				.getWidgetFactory();
		Composite top = factory.createFlatFormComposite(parent);

		group.layoutDialogFields(factory, top);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISection#setInput(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		if (getInput() != null && getInput() instanceof PageflowNodeEditPart) {
			Object model = ((PageflowNodeEditPart) getInput()).getModel();
			if (model instanceof PageflowPage) {
				pfPage = (PageflowPage) model;
				refreshData();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.properties.sections.AbstractEditPartSection#refreshData()
	 */
	public void refreshData() {
		super.refreshData();
		group.setPropertyProvider(pfPage);
	}

    public void validate() {
        // do nothing
    }
}
