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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.NewEditorResourcesNLS;
import org.eclipse.jst.jsf.facesconfig.ui.dialog.AddEditFacetDialog;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wtp.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.wtp.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.wtp.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.wtp.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.wtp.jsf.facesconfig.emf.FacetNameType;
import org.eclipse.wtp.jsf.facesconfig.emf.FacetType;

public class FacetSection extends AbstractFacesConfigSection {

	private static final int TABLE_DEFAULT_HEIGHT = 160;

	private TableViewer tableViewer;

	private Button removeButton;

	private Button editButton;

	/**
	 * 
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 */
	public FacetSection(Composite parent, IManagedForm managedForm,
			IFacesConfigPage page, FormToolkit toolkit) {
		super(parent, managedForm, page, toolkit, null, null);
		this.getSection().setText(NewEditorResourcesNLS.FacetSection_Name);
		this.getSection().setDescription(
				NewEditorResourcesNLS.FacetSection_Description);

	}

	/**
	 * 
	 */
	protected void createContents(Composite container, FormToolkit toolkit) {
		GridLayout gl = new GridLayout();
		gl.horizontalSpacing = 0;
		gl.marginWidth = 0;
		gl.numColumns = 2;
		container.setLayout(gl);
		GridData td = new GridData(GridData.FILL_BOTH);
		container.setLayoutData(td);

		toolkit.paintBordersFor(container);

		Composite facetSection = toolkit.createComposite(container);
		GridLayout gl2 = new GridLayout();
		gl2.horizontalSpacing = 0;
		gl2.marginWidth = 0;
		gl2.numColumns = 2;
		facetSection.setLayout(gl2);
		GridData gd = new GridData(GridData.FILL_BOTH
				| GridData.VERTICAL_ALIGN_BEGINNING);
		facetSection.setLayoutData(gd);

		createTableSection(facetSection, toolkit);
		createButtonsSection(facetSection, toolkit);

	}

	/**
	 * create facet table section
	 * 
	 * @param container
	 * @param toolkit
	 */
	private void createTableSection(Composite container, FormToolkit toolkit) {
		Composite tableContainer = toolkit.createComposite(container);
		GridData gd = new GridData(GridData.FILL_BOTH
				| GridData.GRAB_HORIZONTAL);
		tableContainer.setLayoutData(gd);
		toolkit.paintBordersFor(tableContainer);

		GridLayout gl = new GridLayout();
		tableContainer.setLayout(gl);

		Table table = new Table(tableContainer, SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION | SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 1;
		gd.heightHint = TABLE_DEFAULT_HEIGHT;
		table.setLayoutData(gd);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableLayout layout = new TableLayout();
		table.setLayout(layout);

		TableColumn facetNameCol = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(1, true));
		facetNameCol
				.setText(NewEditorResourcesNLS.FacetSection_Table_FacetNameColumn);
		TableColumn displayNameCol = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(1, true));
		displayNameCol
				.setText(NewEditorResourcesNLS.FacetSection_Table_DisplayNameColumn);//$NON-NLS-1$

		TableColumn descriptionCol = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(1, true));
		descriptionCol
				.setText(NewEditorResourcesNLS.FacetSection_Table_DescriptionColumn);

		table.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				updateButtons();
			}
		});

		table.addMouseListener(new MouseAdapter() {
			public void mouseDoubleClick(MouseEvent e) {
				if (((IStructuredSelection) tableViewer.getSelection()).size() > 0)
					editButtonSelected();
			}
		});

		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new AdapterFactoryContentProvider(
				getAdapterFactory()));
		tableViewer.setLabelProvider(new AdapterFactoryLabelProvider(
				getAdapterFactory()));

		tableViewer.addFilter(new ViewerFilter() {
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return FacesConfigPackage.eINSTANCE.getFacetType().isInstance(
						element);
			}
		});
	}

	/**
	 * create buttons
	 * 
	 * @param container
	 * @param toolkit
	 */
	private void createButtonsSection(Composite container, FormToolkit toolkit) {
		// create operation buttons's container
		Composite operationContainer = toolkit.createComposite(container);
		GridData gd = new GridData(GridData.FILL_VERTICAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		operationContainer.setLayoutData(gd);
		GridLayout gl = new GridLayout();
		operationContainer.setLayout(gl);

		Button addButton = toolkit.createButton(operationContainer,
				NewEditorResourcesNLS.UI_Button_Add_more, //$NON-NLS-1$
				SWT.PUSH);
		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.grabExcessHorizontalSpace = false;
		addButton.setLayoutData(gd);

		editButton = toolkit.createButton(operationContainer,
				NewEditorResourcesNLS.UI_Button_Edit_more, //$NON-NLS-1$
				SWT.PUSH);
		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.grabExcessHorizontalSpace = false;
		editButton.setLayoutData(gd);

		removeButton = toolkit.createButton(operationContainer,
				NewEditorResourcesNLS.UI_Button_Remove, //$NON-NLS-1$
				SWT.PUSH);
		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.grabExcessHorizontalSpace = false;
		removeButton.setLayoutData(gd);

		addButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				addButtonSelected();
			}
		});

		editButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				editButtonSelected();
			}
		});
		removeButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				removeButtonSelected();
			}
		});
	}

	public void clearAll() {
		// TODO Auto-generated method stub

	}

	/**
	 * refresh the table viewer and update buttons.
	 */
	public void refresh() {
		super.refresh();
		tableViewer.refresh();
		updateButtons();
	}

	public void refreshAll() {
		tableViewer.setInput(getInput());
		updateButtons();
	}

	/**
	 * Update the buttons' enable status.
	 */
	private void updateButtons() {
		if (((IStructuredSelection) tableViewer.getSelection()).size() > 0) {
			editButton.setEnabled(true);
			removeButton.setEnabled(true);
		} else {
			editButton.setEnabled(false);
			removeButton.setEnabled(false);
		}
	}

	/**
	 * add a facet.
	 * 
	 */
	private void addButtonSelected() {

		Shell shell = EditorPlugin.getActiveShell();

		AddEditFacetDialog dialog = new AddEditFacetDialog(shell, true);
		if (dialog.open() == Dialog.OK) {

			String newFacetName = dialog.getFacetName();
			String newDisplayName = dialog.getDisplayName();
			String newDescription = dialog.getDescription();

			FacetType newFacet = FacesConfigFactory.eINSTANCE.createFacetType();
			if (newFacetName.length() > 0) {
				FacetNameType facetNameType = FacesConfigFactory.eINSTANCE
						.createFacetNameType();
				facetNameType.setTextContent(newFacetName);
				newFacet.setFacetName(facetNameType);
			}

			if (newDisplayName.length() > 0) {
				DisplayNameType displayNameType = FacesConfigFactory.eINSTANCE
						.createDisplayNameType();
				displayNameType.setTextContent(newDisplayName);
				newFacet.getDisplayName().add(displayNameType);
			}

			if (newDescription.length() > 0) {
				DescriptionType descriptionType = FacesConfigFactory.eINSTANCE
						.createDescriptionType();
				descriptionType.setTextContent(newDescription);
				newFacet.getDescription().add(descriptionType);
			}

			Command addCommand = AddCommand.create(this.getEditingDomain(),
					getInput(), FacesConfigPackage.eINSTANCE
							.getComponentType_Facet(), newFacet);
			if (addCommand.canExecute()) {
				this.getEditingDomain().getCommandStack().execute(addCommand);

			}
		}

	}

	/**
	 * edit a facet.
	 * 
	 */
	private void editButtonSelected() {
		FacetType facet = (FacetType) ((IStructuredSelection) tableViewer
				.getSelection()).getFirstElement();

		Assert.isNotNull(facet);

		Shell shell = EditorPlugin.getActiveShell();

		AddEditFacetDialog dialog = new AddEditFacetDialog(shell, false);
		if (facet.getFacetName() != null)
			dialog.setFacetName(facet.getFacetName().getTextContent());

		if (facet.getDisplayName().size() > 0) {
			dialog.setDisplayName(((DisplayNameType) facet.getDisplayName()
					.get(0)).getTextContent());
		}

		if (facet.getDescription().size() > 0) {
			dialog.setDescription(((DescriptionType) facet.getDescription()
					.get(0)).getTextContent());
		}

		if (dialog.open() == Dialog.OK) {
			List commands = new ArrayList(3);

			String newFacetName = dialog.getFacetName();
			String newDisplayName = dialog.getDisplayName();
			String newDescription = dialog.getDescription();
			if (facet.getFacetName() != null) {
				Command cmd1 = SetCommand.create(getEditingDomain(), facet
						.getFacetName(), FacesConfigPackage.eINSTANCE
						.getFacetNameType_TextContent(), newFacetName);
				commands.add(cmd1);
			} else if (newFacetName.length() > 0) {
				FacetNameType newFacetNameType = FacesConfigFactory.eINSTANCE
						.createFacetNameType();
				newFacetNameType.setTextContent(newFacetName);
				Command cmd2 = AddCommand.create(getEditingDomain(), facet,
						FacesConfigPackage.eINSTANCE.getFacetType_FacetName(),
						newFacetNameType);
				commands.add(cmd2);
			}

			if (facet.getDisplayName().size() > 0) {
				DisplayNameType displayNameType = (DisplayNameType) facet
						.getDisplayName().get(0);
				Command cmd3 = SetCommand.create(this.getEditingDomain(),
						displayNameType, FacesConfigPackage.eINSTANCE
								.getDisplayNameType_TextContent(),
						newDisplayName);
				commands.add(cmd3);
			} else if (newDisplayName.length() > 0) {
				DisplayNameType newDisplayNameType = FacesConfigFactory.eINSTANCE
						.createDisplayNameType();
				newDisplayNameType.setTextContent(newDisplayName);
				Command cmd4 = AddCommand
						.create(this.getEditingDomain(), facet,
								FacesConfigPackage.eINSTANCE
										.getFacetType_DisplayName(),
								newDisplayNameType);
				commands.add(cmd4);
			}

			if (facet.getDescription().size() > 0) {
				DescriptionType descriptionType = (DescriptionType) facet
						.getDescription().get(0);
				Command cmd5 = SetCommand.create(this.getEditingDomain(),
						descriptionType, FacesConfigPackage.eINSTANCE
								.getDescriptionType_TextContent(),
						newDescription);
				commands.add(cmd5);
			} else if (newDescription.length() > 0) {
				DescriptionType newDescriptionType = FacesConfigFactory.eINSTANCE
						.createDescriptionType();
				newDescriptionType.setTextContent(newDescription);
				Command cmd6 = AddCommand
						.create(this.getEditingDomain(), facet,
								FacesConfigPackage.eINSTANCE
										.getFacetType_Description(),
								newDescriptionType);
				commands.add(cmd6);
			}

			CompoundCommand command = new CompoundCommand(commands);
			if (command.canExecute()) {
				getEditingDomain().getCommandStack().execute(command);
				tableViewer.refresh(facet);
			}
		}

	}

	/**
	 * remove a facet.
	 * 
	 */
	private void removeButtonSelected() {
		FacetType facet = (FacetType) ((IStructuredSelection) tableViewer
				.getSelection()).getFirstElement();

		Assert.isNotNull(facet);
		Command removeCommand = RemoveCommand.create(this.getEditingDomain(),
				getInput(), FacesConfigPackage.eINSTANCE
						.getComponentType_Facet(), facet);

		if (removeCommand.canExecute()) {
			this.getEditingDomain().getCommandStack().execute(removeCommand);
			refreshAll();
		}

	}

}
