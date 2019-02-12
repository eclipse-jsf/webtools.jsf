/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.section;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogFieldApplyListener;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PageflowEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowPackageImpl;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * Edit section for Pagflow (EditPart)
 * 
 * @author jchoi, Xiao-guang Zhang
 */
public class EditorSection extends AbstractEditPartSection {
	/** edit group */
	private EditorGroup group;

	/** the emf model for Pageflow */
	private Pageflow pageflow;

	/**
	 * 
	 */
	public EditorSection() {
		super();
		group = new EditorGroup();
		group.setDefaultChangeListener(changeListener);
		group.initialize();

		group.getDisplayNameField().setDialogFieldApplyListener(
				new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						EditorSection.this.setValue(
								PageflowPackageImpl.eINSTANCE
										.getPageflowElement_Name().getName(),
								group.getDisplayNameField().getText());
					}
				});

		group.getDescField().setDialogFieldApplyListener(
				new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						EditorSection.this
								.setValue(
										PageflowPackageImpl.eINSTANCE
												.getPageflowElement_Comment()
												.getName(), group
												.getDescField().getText());
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

		if (getInput() != null && getInput() instanceof PageflowEditPart) {
			Object model = ((PageflowEditPart) getInput()).getModel();
			if (model instanceof Pageflow) {
				pageflow = (Pageflow) model;
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
		group.setPropertyProvider(pageflow);
	}

    public void validate() {
        // do nothing.
    }
}
