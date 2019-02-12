/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeClassType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeNameType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultValueType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.jst.jsf.facesconfig.ui.dialog.AddEditAttributeDialog;
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

/**
 * Attribute section
 *
 */
public class AttributeSection extends AbstractFacesConfigSection  {

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
	public AttributeSection(Composite parent, IManagedForm managedForm,
			IFacesConfigPage page, FormToolkit toolkit) {
		super(parent, managedForm, page, toolkit, null, null);
		this.getSection().setText(EditorMessages.AttributeSection_Name);
		this.getSection().setDescription(
				EditorMessages.AttributeSection_Description);

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

		Composite attributeSection = toolkit.createComposite(container);
		GridLayout gl2 = new GridLayout();
		gl2.horizontalSpacing = 0;
		gl2.verticalSpacing = 0;
		gl2.marginWidth = 0;
		gl2.marginHeight = 0;
		gl2.numColumns = 2;
		attributeSection.setLayout(gl2);
		GridData gd = new GridData(GridData.FILL_BOTH
				| GridData.VERTICAL_ALIGN_BEGINNING);
		attributeSection.setLayoutData(gd);

		createTableSection(attributeSection, toolkit);
		createButtonsSection(attributeSection, toolkit);
		
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
		nameCol
				.setText(EditorMessages.AttributeSection_Table_NameColumn);

		TableColumn classCol = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(1, true));
		classCol
				.setText(EditorMessages.AttributeSection_Table_ClassColumn);

		TableColumn valueCol = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(1, true));
		valueCol
				.setText(EditorMessages.AttributeSection_Table_DefaultValueColumn);

		TableColumn suggestedValueCol = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(1, true));
		suggestedValueCol
				.setText(EditorMessages.AttributeSection_Table_SuggestedValueColumn);

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
				return FacesConfigPackage.eINSTANCE.getAttributeType()
						.isInstance(element);
			}
		});
		
		tableViewer.addSelectionChangedListener(this);
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
				EditorMessages.UI_Button_Add_more,
				SWT.PUSH);
		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.grabExcessHorizontalSpace = false;
		addButton.setLayoutData(gd);

		editButton = toolkit.createButton(operationContainer,
				EditorMessages.UI_Button_Edit_more,
				SWT.PUSH);
		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.grabExcessHorizontalSpace = false;
		editButton.setLayoutData(gd);

		removeButton = toolkit.createButton(operationContainer,
				EditorMessages.UI_Button_Remove,
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
	 * 
	 */
	public void refreshAll() {
		tableViewer.setInput(getInput());
		updateButtons();

	}

	/**
	 * 
	 */
	public void refresh() {
		super.refresh();
		tableViewer.refresh();
		updateButtons();
	}

	/**
	 * add an attribute.
	 * 
	 */
	private void addButtonSelected() {
		Shell shell = EditorPlugin.getActiveShell();

		AddEditAttributeDialog dialog = new AddEditAttributeDialog(shell, true);
		dialog.setProject((IProject) getPage().getEditor().getAdapter(
				IProject.class));
		if (dialog.open() == Dialog.OK) {

			// AttributeType newAttribute = dialog.getResult();
			AttributeType newAttribute = FacesConfigFactory.eINSTANCE
					.createAttributeType();

			if (dialog.getAttributeName().length() > 0) {
				AttributeNameType attributeNameType = FacesConfigFactory.eINSTANCE
						.createAttributeNameType();
				attributeNameType.setTextContent(dialog.getAttributeName());
				newAttribute.setAttributeName(attributeNameType);
			}

			if (dialog.getAttributeClass().length() > 0) {
				AttributeClassType attributeClassType = FacesConfigFactory.eINSTANCE
						.createAttributeClassType();
				attributeClassType.setTextContent(dialog.getAttributeClass());
				newAttribute.setAttributeClass(attributeClassType);
			}

			if (dialog.getDefaultValue().length() > 0) {
				DefaultValueType defaultValueType = FacesConfigFactory.eINSTANCE
						.createDefaultValueType();
				defaultValueType.setTextContent(dialog.getDefaultValue());
				newAttribute.setDefaultValue(defaultValueType);
			}

			if (dialog.getSuggestedValue().length() > 0) {
				SuggestedValueType suggestedValueType = FacesConfigFactory.eINSTANCE
						.createSuggestedValueType();
				suggestedValueType.setTextContent(dialog.getSuggestedValue());
				newAttribute.setSuggestedValue(suggestedValueType);
			}

			Command addCommand = AddCommand.create(this.getEditingDomain(),
					getInput(), null, newAttribute); // getEReference(),
														// newAttribute);
			if (addCommand.canExecute()) {
				this.getEditingDomain().getCommandStack().execute(addCommand);

			}
		}

	}

	/**
	 * edit an attribute.
	 * 
	 */
	private void editButtonSelected() {

		AttributeType attribute = (AttributeType) ((IStructuredSelection) tableViewer
				.getSelection()).getFirstElement();

		Assert.isNotNull(attribute);

		Shell shell = EditorPlugin.getActiveShell();

		AddEditAttributeDialog dialog = new AddEditAttributeDialog(shell, false);
		dialog.setProject((IProject) getPage().getEditor().getAdapter(
				IProject.class));
		if (attribute.getAttributeName() != null) {
			dialog.setAttributeName(attribute.getAttributeName()
					.getTextContent());
		}

		if (attribute.getAttributeClass() != null) {
			dialog.setAttributeClass(attribute.getAttributeClass()
					.getTextContent());
		}

		if (attribute.getDefaultValue() != null) {
			dialog
					.setDefaultValue(attribute.getDefaultValue()
							.getTextContent());
		}

		if (attribute.getSuggestedValue() != null) {
			dialog.setSuggestedValue(attribute.getSuggestedValue()
					.getTextContent());
		}

		if (dialog.open() == Dialog.OK) {
			List commands = new ArrayList(4);

			if (attribute.getAttributeName() != null) {
				Command cmd1 = SetCommand.create(this.getEditingDomain(),
						attribute.getAttributeName(),
						FacesConfigPackage.eINSTANCE
								.getAttributeNameType_TextContent(), dialog
								.getAttributeName());
				commands.add(cmd1);
			} else if (dialog.getAttributeName().length() > 0) {
				AttributeNameType attributeNameType = FacesConfigFactory.eINSTANCE
						.createAttributeNameType();
				attributeNameType.setTextContent(dialog.getAttributeName());

				Command cmd2 = AddCommand.create(this.getEditingDomain(),
						attribute, FacesConfigPackage.eINSTANCE
								.getAttributeType_AttributeName(),
						attributeNameType);
				commands.add(cmd2);
			}

			if (attribute.getAttributeClass() != null) {
				Command cmd3 = SetCommand.create(this.getEditingDomain(),
						attribute.getAttributeClass(),
						FacesConfigPackage.eINSTANCE
								.getAttributeClassType_TextContent(), dialog
								.getAttributeClass());
				commands.add(cmd3);
			} else if (dialog.getAttributeClass().length() > 0) {
				AttributeClassType attributeClassType = FacesConfigFactory.eINSTANCE
						.createAttributeClassType();
				attributeClassType.setTextContent(dialog.getAttributeClass());

				Command cmd4 = AddCommand.create(this.getEditingDomain(),
						attribute, FacesConfigPackage.eINSTANCE
								.getAttributeType_AttributeClass(),
						attributeClassType);
				commands.add(cmd4);
			}

			if (attribute.getDefaultValue() != null) {
				Command cmd5 = SetCommand.create(this.getEditingDomain(),
						attribute.getDefaultValue(),
						FacesConfigPackage.eINSTANCE
								.getDefaultValueType_TextContent(), dialog
								.getDefaultValue());
				commands.add(cmd5);

			} else if (dialog.getDefaultValue().length() > 0) {
				DefaultValueType defaultValueType = FacesConfigFactory.eINSTANCE
						.createDefaultValueType();
				defaultValueType.setTextContent(dialog.getDefaultValue());
				Command cmd6 = AddCommand.create(this.getEditingDomain(),
						attribute, FacesConfigPackage.eINSTANCE
								.getAttributeType_DefaultValue(),
						defaultValueType);
				commands.add(cmd6);
			}

			if (attribute.getSuggestedValue() != null) {
				Command cmd7 = SetCommand.create(this.getEditingDomain(),
						attribute.getSuggestedValue(),
						FacesConfigPackage.eINSTANCE
								.getSuggestedValueType_TextContent(), dialog
								.getSuggestedValue());
				commands.add(cmd7);
			} else if (dialog.getSuggestedValue().length() > 0) {
				SuggestedValueType suggestedValueType = FacesConfigFactory.eINSTANCE
						.createSuggestedValueType();
				suggestedValueType.setTextContent(dialog.getSuggestedValue());
				Command cmd8 = AddCommand.create(this.getEditingDomain(),
						attribute, FacesConfigPackage.eINSTANCE
								.getAttributeType_SuggestedValue(),
						suggestedValueType);
				commands.add(cmd8);
			}

			CompoundCommand command = new CompoundCommand(commands);
			if (command.canExecute()) {
				this.getEditingDomain().getCommandStack().execute(command);
				tableViewer.refresh(attribute);

			}
		}

	}

	/**
	 * remove an attribute.
	 * 
	 */
	private void removeButtonSelected() {
		AttributeType attribute = (AttributeType) ((IStructuredSelection) tableViewer
				.getSelection()).getFirstElement();

		Assert.isNotNull(attribute);

		Command removeCommand = RemoveCommand.create(this.getEditingDomain(),
				getInput(), null, attribute);// getEReference(), attribute);

		if (removeCommand.canExecute()) {
			this.getEditingDomain().getCommandStack().execute(removeCommand);
			refreshAll();
		}

	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	public ISelection getSelection() {
		return tableViewer.getSelection();
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {

		tableViewer.setSelection(selection);
	}
}
