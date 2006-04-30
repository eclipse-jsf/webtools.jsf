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
package org.eclipse.jst.jsf.facesconfig.ui.section;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.ui.NewEditorResourcesNLS;
import org.eclipse.jst.jsf.facesconfig.ui.page.ComponentsPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
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
public class OverviewComponentsSection extends AbstractOverviewSection {

	/**
	 * 
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 */
	public OverviewComponentsSection(Composite parent,
			IManagedForm managedForm, IFacesConfigPage page, FormToolkit toolkit) {
		super(parent, managedForm, page, toolkit, ComponentsPage.PAGE_ID,
				NewEditorResourcesNLS.OverviewPage_ComponentsSection_name,
				NewEditorResourcesNLS.OverviewPage_ComponentsSection_description,
				null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.section.AbstractOverviewSection#configTableViewer(org.eclipse.jface.viewers.TableViewer)
	 */
	protected void configTableViewer(TableViewer tableViewer) {
		tableViewer.addFilter(new ViewerFilter() {
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return FacesConfigPackage.eINSTANCE.getComponentType()
						.isInstance(element)
						|| FacesConfigPackage.eINSTANCE.getConverterType()
								.isInstance(element)
						|| FacesConfigPackage.eINSTANCE.getRenderKitType()
								.isInstance(element)
						|| FacesConfigPackage.eINSTANCE.getValidatorType()
								.isInstance(element);
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.section.AbstractOverviewSection#createTable(org.eclipse.swt.widgets.Composite)
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

		TableColumn typecol = new TableColumn(table, SWT.LEFT);
		tablelayout.addColumnData(new ColumnWeightData(1, true));
		typecol
				.setText(NewEditorResourcesNLS.OverviewPage_ComponentsSection_table_typecol);
		typecol.setResizable(true);

		TableColumn namecol = new TableColumn(table, SWT.LEFT);
		tablelayout.addColumnData(new ColumnWeightData(1, true));
		namecol
				.setText(NewEditorResourcesNLS.OverviewPage_ComponentsSection_table_namecol);
		namecol.setResizable(true);

		return table;
	}
}
