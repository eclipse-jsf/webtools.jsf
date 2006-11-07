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
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.DialogField;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.IDialogFieldApplyListener;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PageflowLinkEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowPackageImpl;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.PageflowValidation;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.wst.common.ui.properties.internal.provisional.TabbedPropertySheetPage;
import org.eclipse.wst.common.ui.properties.internal.provisional.TabbedPropertySheetWidgetFactory;

/**
 * Edit section for Pagflow Link element (EditPart)
 * 
 * @author jchoi
 */
public class LinkSection extends AbstractEditPartSection {
	/** edit group */
	private LinkGroup group;

	/** the emf model for Pageflow link */
	private PageflowLink pfLink;

	/**
	 * 
	 */
	public LinkSection() {
		super();
		group = new LinkGroup();
		group.setDefaultChangeListener(changeListener);
		group.initialize();

		group.getFromOutcomeField().setDialogFieldApplyListener(
				new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						LinkSection.this.setValue(PageflowPackageImpl.eINSTANCE
								.getPFLink_Outcome().getName(), group
								.getFromOutcomeField().getText());
					}
				});

		group.getRedirectField().setDialogFieldApplyListener(
				new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						LinkSection.this.setValue(PageflowPackageImpl.eINSTANCE
								.getPFLink_Redirect().getName(), group
								.getRedirectValue());
					}
				});
		group.getFromActionField().setDialogFieldApplyListener(
				new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						LinkSection.this.setValue(PageflowPackageImpl.eINSTANCE
								.getPFLink_Fromaction().getName(), group
								.getFromActionField().getText());
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

		if (getInput() != null && getInput() instanceof PageflowLinkEditPart) {
			Object model = ((PageflowLinkEditPart) getInput()).getModel();
			if (model instanceof PageflowLink) {
				pfLink = (PageflowLink) model;
				if (PageflowValidation.getInstance().isValidLinkForProperty(
						pfLink)) {
					refreshData();
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.properties.sections.AbstractEditPartSection#refreshData()
	 */
	public void refreshData() {
		super.refresh();
		group.setPropertyProvider(pfLink);
	}

    public void validate() {
        // do nothing
    }

}
