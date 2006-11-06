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

package org.eclipse.jst.jsf.facesconfig.ui.section;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jst.jsf.facesconfig.common.actions.OpenPageAction;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author sfshi
 * 
 */
public abstract class AbstractOverviewSection extends
		AbstractFacesConfigSection {

	protected TableViewer tableViewer;

	private String targetPageID;

	/**
	 * 
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 * @param targetPageID
	 * @param title
	 * @param description
	 * @param helpContextId
	 * @param helpTooltip
	 */
	public AbstractOverviewSection(Composite parent, IManagedForm managedForm,
			IFacesConfigPage page, FormToolkit toolkit, String targetPageID,
			String title, String description, String helpContextId,
			String helpTooltip) {
		super(parent, managedForm, page, toolkit, helpContextId, helpTooltip);
		this.targetPageID = targetPageID;
		getSection().setText(title); //$NON-NLS-1$
		getSection().setDescription(description); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.section.AbstractFacesConfigSection#createContents(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	protected void createContents(Composite container, FormToolkit toolkit) {

		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginHeight = 2;
		layout.marginWidth = 2;
		layout.verticalSpacing = 5;
		layout.horizontalSpacing = 6;
		container.setLayout(layout);
		toolkit.paintBordersFor(container);
		Table table = createTable(container);
		if (targetPageID != null) {
			table.addMouseListener(new MouseAdapter() {
				public void mouseDoubleClick(MouseEvent e) {
					if (((IStructuredSelection) tableViewer.getSelection())
							.size() == 1) {
						OpenPageAction action = new OpenPageAction();
						action.setPageName(targetPageID);
						action.setSelection(tableViewer.getSelection());
						action.run();
					}
				}
			});
		}
		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new AdapterFactoryContentProvider(
				getAdapterFactory()));
		ComposedAdapterFactory factory = (ComposedAdapterFactory)getAdapterFactory();
		tableViewer.setLabelProvider(new AdapterFactoryLabelProvider(factory));
		configTableViewer(tableViewer);
	}

	/**
	 * 
	 * @return
	 */
	abstract protected Table createTable(Composite container);

	/**
	 * 
	 * @param tableViewer1
	 */
	abstract protected void configTableViewer(TableViewer tableViewer1);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.section.IFacesConfigSection#clearAll()
	 */
	public void clearAll() {
		tableViewer.setInput(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.section.IFacesConfigSection#refreshAll()
	 */
	public void refreshAll() {
		tableViewer.setInput(getInput());

	}

}
