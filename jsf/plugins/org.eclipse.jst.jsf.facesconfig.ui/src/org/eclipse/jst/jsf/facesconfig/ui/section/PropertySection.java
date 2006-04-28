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

import org.eclipse.core.resources.IProject;
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
import org.eclipse.jst.jsf.facesconfig.emf.DefaultValueType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyClassType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.NewEditorResourcesNLS;
import org.eclipse.jst.jsf.facesconfig.ui.dialog.AddEditPropertyDialog;
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

public class PropertySection extends AbstractFacesConfigSection {

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
	public PropertySection(Composite parent, IManagedForm managedForm,
			IFacesConfigPage page, FormToolkit toolkit) {
		super(parent, managedForm, page, toolkit, null, null);
		this.getSection().setText(NewEditorResourcesNLS.PropertySection_Name);
		this.getSection().setDescription(
				NewEditorResourcesNLS.PropertySection_Description);

	}

	/**
	 * @param container
	 * @param toolkit
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

		Composite propertySection = toolkit.createComposite(container);
		GridLayout gl2 = new GridLayout();
		gl2.horizontalSpacing = 0;
		gl2.verticalSpacing = 0;
		gl2.marginWidth = 0;
		gl2.marginHeight = 0;
		gl2.numColumns = 2;
		propertySection.setLayout(gl2);
		GridData gd = new GridData(GridData.FILL_BOTH
				| GridData.VERTICAL_ALIGN_BEGINNING);
		propertySection.setLayoutData(gd);

		createTableSection(propertySection, toolkit);
		createButtonsSection(propertySection, toolkit);

	}

	/**
	 * create table section
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

		TableColumn nameCol = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(1, true));
		nameCol.setText(NewEditorResourcesNLS.PropertySection_Table_NameColumn);//$NON-NLS-1$

		TableColumn classCol = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(1, true));
		classCol
				.setText(NewEditorResourcesNLS.PropertySection_Table_ClassColumn);

		TableColumn valueCol = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(1, true));
		valueCol
				.setText(NewEditorResourcesNLS.PropertySection_Table_DefaultValueColumn);

		TableColumn suggestedValueCol = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(1, true));
		suggestedValueCol
				.setText(NewEditorResourcesNLS.PropertySection_Table_SuggestedValueColumn);

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
				return FacesConfigPackage.eINSTANCE.getPropertyType()
						.isInstance(element);
			}
		});
	}

	/**
	 * Update the buttons' enable and disable status
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
	 * create property's editing buttons
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

	/**
	 * refresh the table viewer and update buttons.
	 */
	public void refresh() {
		super.refresh();
		tableViewer.refresh();
		updateButtons();
	}

	/**
	 * 
	 */
	public void refreshAll() {
		tableViewer.setInput(getInput());
		updateButtons();

	}

	public void clearAll() {
		// TODO Auto-generated method stub

	}

	/**
	 * add a property.
	 * 
	 */
	private void addButtonSelected() {

		Shell shell = EditorPlugin.getActiveShell();

		AddEditPropertyDialog dialog = new AddEditPropertyDialog(shell, true);
		dialog.setProject((IProject) getPage().getEditor().getAdapter(
				IProject.class));
		if (dialog.open() == Dialog.OK) {

			PropertyType newProperty = FacesConfigFactory.eINSTANCE
					.createPropertyType();

			if (dialog.getPropertyName().length() > 0) {
				PropertyNameType propertyNameType = FacesConfigFactory.eINSTANCE
						.createPropertyNameType();
				propertyNameType.setTextContent(dialog.getPropertyName());
				newProperty.setPropertyName(propertyNameType);
			}

			if (dialog.getPropertyClass().length() > 0) {
				PropertyClassType propertyClassType = FacesConfigFactory.eINSTANCE
						.createPropertyClassType();
				propertyClassType.setTextContent(dialog.getPropertyClass());
				newProperty.setPropertyClass(propertyClassType);
			}

			if (dialog.getDefaultValue().length() > 0) {
				DefaultValueType defaultValueType = FacesConfigFactory.eINSTANCE
						.createDefaultValueType();
				defaultValueType.setTextContent(dialog.getDefaultValue());
				newProperty.setDefaultValue(defaultValueType);
			}

			if (dialog.getSuggestedValue().length() > 0) {
				SuggestedValueType suggestedValueType = FacesConfigFactory.eINSTANCE
						.createSuggestedValueType();
				suggestedValueType.setTextContent(dialog.getSuggestedValue());
				newProperty.setSuggestedValue(suggestedValueType);
			}

			Command addCommand = AddCommand.create(this.getEditingDomain(),
					getInput(), null, newProperty);
			if (addCommand.canExecute()) {
				this.getEditingDomain().getCommandStack().execute(addCommand);

			}
		}

	}

	/**
	 * edit a property.
	 * 
	 */
	private void editButtonSelected() {

		PropertyType property = (PropertyType) ((IStructuredSelection) tableViewer
				.getSelection()).getFirstElement();

		Assert.isNotNull(property);

		Shell shell = EditorPlugin.getActiveShell();

		AddEditPropertyDialog dialog = new AddEditPropertyDialog(shell, false);
		dialog.setProject((IProject) getPage().getEditor().getAdapter(
				IProject.class));
		if (property.getPropertyName() != null) {
			dialog.setPropertyName(property.getPropertyName().getTextContent());
		}

		if (property.getPropertyClass() != null) {
			dialog.setPropertyClass(property.getPropertyClass()
					.getTextContent());
		}

		if (property.getDefaultValue() != null) {
			dialog.setDefaultValue(property.getDefaultValue().getTextContent());
		}

		if (property.getSuggestedValue() != null) {
			dialog.setSuggestedValue(property.getSuggestedValue()
					.getTextContent());
		}

		if (dialog.open() == Dialog.OK) {
			List commands = new ArrayList(4);

			if (property.getPropertyName() != null) {
				Command cmd1 = SetCommand.create(this.getEditingDomain(),
						property.getPropertyName(),
						FacesConfigPackage.eINSTANCE
								.getPropertyNameType_TextContent(), dialog
								.getPropertyName());
				commands.add(cmd1);
			} else if (dialog.getPropertyName().length() > 0) {
				PropertyNameType propertyNameType = FacesConfigFactory.eINSTANCE
						.createPropertyNameType();
				propertyNameType.setTextContent(dialog.getPropertyName());

				Command cmd2 = AddCommand.create(this.getEditingDomain(),
						property, FacesConfigPackage.eINSTANCE
								.getPropertyType_PropertyName(),
						propertyNameType);
				commands.add(cmd2);
			}

			if (property.getPropertyClass() != null) {
				Command cmd3 = SetCommand.create(this.getEditingDomain(),
						property.getPropertyClass(),
						FacesConfigPackage.eINSTANCE
								.getPropertyClassType_TextContent(), dialog
								.getPropertyClass());
				commands.add(cmd3);
			} else if (dialog.getPropertyClass().length() > 0) {
				PropertyClassType propertyClassType = FacesConfigFactory.eINSTANCE
						.createPropertyClassType();
				propertyClassType.setTextContent(dialog.getPropertyClass());

				Command cmd4 = AddCommand.create(this.getEditingDomain(),
						property, FacesConfigPackage.eINSTANCE
								.getPropertyType_PropertyClass(),
						propertyClassType);
				commands.add(cmd4);
			}

			if (property.getDefaultValue() != null) {
				Command cmd5 = SetCommand.create(this.getEditingDomain(),
						property.getDefaultValue(),
						FacesConfigPackage.eINSTANCE
								.getDefaultValueType_TextContent(), dialog
								.getDefaultValue());
				commands.add(cmd5);

			} else if (dialog.getDefaultValue().length() > 0) {
				DefaultValueType defaultValueType = FacesConfigFactory.eINSTANCE
						.createDefaultValueType();
				defaultValueType.setTextContent(dialog.getDefaultValue());
				Command cmd6 = AddCommand.create(this.getEditingDomain(),
						property, FacesConfigPackage.eINSTANCE
								.getPropertyType_DefaultValue(),
						defaultValueType);
				commands.add(cmd6);
			}

			if (property.getSuggestedValue() != null) {
				Command cmd7 = SetCommand.create(this.getEditingDomain(),
						property.getSuggestedValue(),
						FacesConfigPackage.eINSTANCE
								.getSuggestedValueType_TextContent(), dialog
								.getSuggestedValue());
				commands.add(cmd7);
			} else if (dialog.getSuggestedValue().length() > 0) {
				SuggestedValueType suggestedValueType = FacesConfigFactory.eINSTANCE
						.createSuggestedValueType();
				suggestedValueType.setTextContent(dialog.getSuggestedValue());
				Command cmd8 = AddCommand.create(this.getEditingDomain(),
						property, FacesConfigPackage.eINSTANCE
								.getPropertyType_SuggestedValue(),
						suggestedValueType);
				commands.add(cmd8);
			}

			CompoundCommand command = new CompoundCommand(commands);
			if (command.canExecute()) {
				this.getEditingDomain().getCommandStack().execute(command);
				tableViewer.refresh(property);

			}
		}

	}

	/**
	 * remove a property.
	 * 
	 */
	private void removeButtonSelected() {
		PropertyType property = (PropertyType) ((IStructuredSelection) tableViewer
				.getSelection()).getFirstElement();

		Assert.isNotNull(property);

		Command removeCommand = RemoveCommand.create(this.getEditingDomain(),
				getInput(), null, property);

		if (removeCommand.canExecute()) {
			this.getEditingDomain().getCommandStack().execute(removeCommand);
			refreshAll();
		}

	}

}
