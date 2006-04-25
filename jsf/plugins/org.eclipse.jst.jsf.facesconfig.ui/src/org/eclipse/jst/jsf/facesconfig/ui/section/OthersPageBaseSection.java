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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jst.jsf.facesconfig.ui.NewEditorResourcesNLS;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.OthersPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wtp.jsf.facesconfig.emf.FacesConfigType;

/**
 * @author Zhi-peng Zhang
 * @version
 */
/**
 * The base class for the Sections in Others Page.
 */
public abstract class OthersPageBaseSection extends AbstractFacesConfigSection
		implements ISelectionProvider, ISelectionChangedListener {

	protected TableViewer tableViewer;

	protected Button removeButton;

	private List selectionChangedListeners = new ArrayList();

	/**
	 * 
	 * @param componentClass
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 * @param helpContextId
	 * @param helpTooltip
	 */
	public OthersPageBaseSection(Composite parent, IManagedForm managedForm,
			IFacesConfigPage page, FormToolkit toolkit, String helpContextId,
			String helpTooltip) {
		super(parent, managedForm, page, toolkit, helpContextId, helpTooltip);
	}

	/**
	 * 
	 * @param componentClass
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 */
	public OthersPageBaseSection(Composite parent, IManagedForm managedForm,
			IFacesConfigPage page, FormToolkit toolkit) {
		this(parent, managedForm, page, toolkit, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.section.AbstractFacesConfigSection#createContents(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	protected void createContents(Composite container, FormToolkit toolkit) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = layout.marginHeight = 5;
		container.setLayout(layout);
		createViewer(container, toolkit);
		createOperationSection(container, toolkit);
	}

	/**
	 * create TableViewer for this section. sub-class may override it to return
	 * a new type tableViewer. for example CheckboxTableViewer.
	 * 
	 * @param parent
	 * @return
	 */
	protected TableViewer createTableViewer(Composite parent) {
		return new TableViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.BORDER);
	}

	/**
	 * Config the viewer, such as set a filter and so on. Sub classes should
	 * override this method to add filter.
	 * 
	 * @param tableViewer
	 */
	protected void configTableViewer(TableViewer tableViewer) {
	}

	/**
	 * 
	 * @param parent
	 * @param toolkit
	 */
	protected void createViewer(Composite parent, FormToolkit toolkit) {
		Composite tableContainer = toolkit.createComposite(parent);
		toolkit.paintBordersFor(tableContainer);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 200;
		tableContainer.setLayoutData(gd);
		tableContainer.setLayout(new GridLayout());

		tableViewer = createTableViewer(tableContainer);
		tableViewer.getControl()
				.setLayoutData(new GridData(GridData.FILL_BOTH));
		tableViewer.setContentProvider(new AdapterFactoryContentProvider(
				getAdapterFactory()));
		tableViewer.setLabelProvider(new AdapterFactoryLabelProvider(
				getAdapterFactory()));
		configTableViewer(tableViewer);

		tableViewer.addSelectionChangedListener(this);
	}

	/**
	 * 
	 * @param parent
	 * @param toolkit
	 */
	protected void createOperationSection(Composite parent, FormToolkit toolkit) {
		Composite operationContainer = toolkit.createComposite(parent);
		operationContainer.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		operationContainer.setLayout(new GridLayout());

		Button addButton = toolkit.createButton(operationContainer,
				NewEditorResourcesNLS.UI_Button_Add, SWT.PUSH);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.grabExcessHorizontalSpace = false;
		addButton.setLayoutData(gd);

		addButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				addButtonSelected(e);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				addButtonSelected(e);
			}
		});

		removeButton = toolkit.createButton(operationContainer,
				NewEditorResourcesNLS.UI_Button_Remove, SWT.PUSH);

		removeButton.setEnabled(true);
		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.grabExcessHorizontalSpace = false;
		removeButton.setLayoutData(gd);
		removeButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				removeButtonSelected(e);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				removeButtonSelected(e);
			}
		});
	}

	protected boolean isContainerObj() {
		return getInput() instanceof FacesConfigType;
	}

	abstract void addButtonSelected(SelectionEvent e);

	protected void removeButtonSelected(SelectionEvent e) {
		IStructuredSelection ssel = StructuredSelection.EMPTY;
		ISelection selection = getSelection();
		if (selection instanceof IStructuredSelection) {
			ssel = (IStructuredSelection) selection;
		}

		if (!ssel.isEmpty()) {
			List commands = new ArrayList(ssel.size());
			for (Iterator iter = ssel.iterator(); iter.hasNext();) {
				EObject element = (EObject) iter.next();
				if (element.eContainer().eContents().size() == 1) {
					// if the parent only have this one child, then remove it
					// together.
					element = element.eContainer();
				}
				Command command = RemoveCommand.create(getEditingDomain(),
						element);
				commands.add(command);

			}

			CompoundCommand command = new CompoundCommand(commands);
			if (command.canExecute()) {
				getEditingDomain().getCommandStack().execute(command);
			}
		}
	}

	public ISelection getSelection() {
		return tableViewer.getSelection();
	}

	protected void updateButtons() {
		if (!removeButton.isDisposed()) {
			IStructuredSelection ssel = (IStructuredSelection) getSelection();
			removeButton.setEnabled(!ssel.isEmpty());
		}
	}

	public void refresh() {
		super.refresh();
		tableViewer.refresh();
		updateButtons();
	}

	public void setSelection(ISelection selection) {
		tableViewer.setSelection(selection);
	}

	/**
	 * @return Returns the tableViewer.
	 */
	public TableViewer getTableViewer() {
		return tableViewer;
	}

	public void refreshAll() {
		setViewerInput(getInput());
		updateButtons();
	}

	/**
	 * set the structuredViewer's input
	 * 
	 * @param input
	 */
	abstract protected void setViewerInput(Object input);

	public void expansionStateChanged(boolean expanded) {
		if (expanded) {
			if (tableViewer.getInput() == null) {
				refreshAll();
			}
			tableViewer.setSelection(tableViewer.getSelection());
		} else {
			tableViewer.setSelection(null);
		}
		super.expansionStateChanged(expanded);
	}

	protected void expansionStateChanging(boolean expanding) {
		if (!expanding) {
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			this.getSection().setLayoutData(gd);
		} else {
			((OthersPage) getPage()).closeOtherSections(this);

			GridData gd = new GridData(GridData.FILL_BOTH);
			this.getSection().setLayoutData(gd);
		}

		super.expansionStateChanging(expanding);
	}

	public void clearAll() {

	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		// TODO Auto-generated method stub

		selectionChangedListeners.add(listener);
	}

	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		// TODO Auto-generated method stub

		selectionChangedListeners.remove(listener);
	}

	public void selectionChanged(SelectionChangedEvent event) {
		for (Iterator listeners = selectionChangedListeners.iterator(); listeners
				.hasNext();) {
			ISelectionChangedListener listener = (ISelectionChangedListener) listeners
					.next();
			listener.selectionChanged(new SelectionChangedEvent(this,
					getSelection()));
		}
		updateButtons();
	}
}
