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
package org.eclipse.jst.jsf.facesconfig.ui.section;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.ManagedBeanPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author sfshi
 * @version
 */
public class OverviewManagedBeanSection extends AbstractOverviewSection {


	/**
	 * 
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 */
	public OverviewManagedBeanSection(Composite parent,
			IManagedForm managedForm, IFacesConfigPage page, FormToolkit toolkit) {
		super(parent, managedForm, page, toolkit, ManagedBeanPage.PAGE_ID,
				EditorMessages.OverviewPage_ManagedBeanSection_name,
				EditorMessages.OverviewPage_ManagedBeanSection_description,
				null, null);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.section.AbstractOverviewSection#configTableViewer(org.eclipse.jface.viewers.TableViewer)
	 */
	protected void configTableViewer(TableViewer tableViewer1) {
		tableViewer1.addFilter(new ViewerFilter() {
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return FacesConfigPackage.eINSTANCE.getManagedBeanType()
						.isInstance(element);
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.section.AbstractOverviewSection#createTable()
	 */
	protected Table createTable(Composite container) {
		Table table = new Table(container, SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION | SWT.BORDER);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 1;
		gd.heightHint = 100;
		table.setLayoutData(gd);

		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		TableLayout tablelayout = new TableLayout();
		table.setLayout(tablelayout);

		TableColumn namecol = new TableColumn(table, SWT.LEFT);
		namecol
				.setText(EditorMessages.OverviewPage_ManagedBeanSection_table_namecol);
		tablelayout.addColumnData(new ColumnWeightData(1, true));
		namecol.setResizable(true);

		TableColumn scopecol = new TableColumn(table, SWT.LEFT);
		scopecol
				.setText(EditorMessages.OverviewPage_ManagedBeanSection_table_scopecol);
		tablelayout.addColumnData(new ColumnWeightData(1, true));
		scopecol.setResizable(true);

		TableColumn classcol = new TableColumn(table, SWT.LEFT);
		classcol
				.setText(EditorMessages.OverviewPage_ManagedBeanSection_table_classcol);
		tablelayout.addColumnData(new ColumnWeightData(1, true));
		classcol.setResizable(true);

		return table;
	}
}
