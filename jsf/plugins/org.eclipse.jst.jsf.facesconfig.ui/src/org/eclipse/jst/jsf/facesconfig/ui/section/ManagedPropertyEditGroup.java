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

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldGroup;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.NullValueType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyClassType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueType;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.IFacesConfigConstants;
import org.eclipse.jst.jsf.facesconfig.ui.dialog.AddEditManagedPropertyDialog;
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
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author sfshi
 * @version
 */
public class ManagedPropertyEditGroup extends DialogFieldGroup implements
		IDialogFieldGroup, ISelectionProvider, ISelectionChangedListener {

	private TableViewer tableViewer;

	private static final int TABLE_DEFAULT_HEIGHT = 160;

	private Button removeButton;

	private Button editButton;

	private ManagedBeanType managedBean;

	private AbstractFacesConfigSection section;

	private List selectionChangedListeners = new ArrayList();

	/**
	 * @param section 
	 * 
	 */
	public ManagedPropertyEditGroup(AbstractFacesConfigSection section) {
		super();
		this.section = section;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#initialize()
	 */
	public void initialize() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#refreshData()
	 */
	public void refreshData() {
		refresh();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#layoutDialogFields(org.eclipse.ui.forms.widgets.FormToolkit,
	 *      org.eclipse.swt.widgets.Composite)
	 */
	public void layoutDialogFields(FormToolkit toolkit, Composite parent) {
		createGeneralClassPage(parent, toolkit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#validateDialogFields()
	 */
	public IStatus[] validateDialogFields() {
		return null;
	}

	/**
	 * create general class initialization section
	 * 
	 * @param container
	 * @param toolkit
	 */
	private void createGeneralClassPage(Composite container, FormToolkit toolkit) {
		Composite generalSection = toolkit.createComposite(container);
		generalSection.setLayoutData(new GridData(GridData.FILL_BOTH));
		toolkit.paintBordersFor(generalSection);

		GridLayout gl = new GridLayout();
		gl.horizontalSpacing = 0;
		gl.marginWidth = 0;
		gl.numColumns = 2;
		generalSection.setLayout(gl);

		createTableSection(generalSection, toolkit);

		createButtonsSection(generalSection, toolkit);
	}

	/**
	 * create proeprty table section
	 * 
	 * @param container
	 * @param toolkit
	 */
	private void createTableSection(Composite container, FormToolkit toolkit) {
		// create property container
		Composite propertyContainer = toolkit.createComposite(container);
		GridData gd = new GridData(GridData.FILL_BOTH
				| GridData.GRAB_HORIZONTAL);
		propertyContainer.setLayoutData(gd);
		toolkit.paintBordersFor(propertyContainer);

		GridLayout gl = new GridLayout();
		propertyContainer.setLayout(gl);

		// Create property table
		Table table = new Table(propertyContainer, SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION | SWT.BORDER);
		TableLayout layout = new TableLayout();
		table.setLayout(layout);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 1;
		gd.heightHint = TABLE_DEFAULT_HEIGHT;
		table.setLayoutData(gd);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableColumn propertyCol = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(1, true));
		propertyCol
				.setText(EditorMessages.InitializationSection_PropertyTable_Name);
		propertyCol.setResizable(true);

		TableColumn classCol = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(1, true));
		classCol
				.setText(EditorMessages.InitializationSection_PropertyTable_Class);
		classCol.setResizable(true);

		TableColumn valueCol = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(1, true));
		valueCol
				.setText(EditorMessages.InitializationSection_PropertyTable_Value);
		valueCol.setResizable(true);

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
				section.getAdapterFactory()));
		tableViewer.setLabelProvider(new AdapterFactoryLabelProvider(section
				.getAdapterFactory()));

		tableViewer.addFilter(new ViewerFilter() {
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return FacesConfigPackage.eINSTANCE.getManagedPropertyType()
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

		// create list operation buttons
		Button addButton = toolkit.createButton(operationContainer,
				EditorMessages.UI_Button_Add_more, SWT.PUSH);
		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.grabExcessHorizontalSpace = false;
		addButton.setLayoutData(gd);

		editButton = toolkit.createButton(operationContainer,
				EditorMessages.UI_Button_Edit_more, //$NON-NLS-1$
				SWT.PUSH);
		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.grabExcessHorizontalSpace = false;
		editButton.setLayoutData(gd);

		removeButton = toolkit.createButton(operationContainer,
				EditorMessages.UI_Button_Remove, //$NON-NLS-1$
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

	private void addButtonSelected() {
		Shell shell = EditorPlugin.getActiveShell();

		AddEditManagedPropertyDialog dialog = new AddEditManagedPropertyDialog(
				shell, managedBean, section);
		if (dialog.open() == Dialog.OK) {

			ManagedPropertyType managedProperty = createManagedPropertyFromDialog(dialog);
			Command addCommand = AddCommand.create(this.getEditingDomain(),
					getInput(), FacesConfigPackage.eINSTANCE
							.getManagedBeanType_ManagedProperty(),
					managedProperty);
			if (addCommand.canExecute()) {
				this.getEditingDomain().getCommandStack().execute(addCommand);

			}
		}

	}

	private void editButtonSelected() {
		ManagedPropertyType managedProperty = (ManagedPropertyType) ((IStructuredSelection) tableViewer
				.getSelection()).getFirstElement();
		Shell shell = EditorPlugin.getActiveShell();
		AddEditManagedPropertyDialog dialog = new AddEditManagedPropertyDialog(
				shell, managedProperty, section);
		if (dialog.open() == Dialog.OK) {
			List commands = new ArrayList();
			String propertyName = dialog.getPropertyName();
			String propertyClass = dialog.getPropertyClass();
			PropertyNameType propertyNameType = FacesConfigFactory.eINSTANCE
					.createPropertyNameType();
			propertyNameType.setTextContent(propertyName);
			Command cmd1 = SetCommand.create(this.getEditingDomain(),
					managedProperty, FacesConfigPackage.eINSTANCE
							.getManagedPropertyType_PropertyName(),
					propertyNameType);
			commands.add(cmd1);

			PropertyClassType propertyClassType = FacesConfigFactory.eINSTANCE
					.createPropertyClassType();
			propertyClassType.setTextContent(propertyClass);
			Command cmd2 = SetCommand.create(this.getEditingDomain(),
					managedProperty, FacesConfigPackage.eINSTANCE
							.getManagedPropertyType_PropertyClass(),
					propertyClassType);
			commands.add(cmd2);

			// remove value, null-value, map-entries and list-entries element:
			if (managedProperty.getNullValue() != null) {
				Command rmCmd = SetCommand.create(this.getEditingDomain(),
						managedProperty, FacesConfigPackage.eINSTANCE
								.getManagedPropertyType_NullValue(),
						SetCommand.UNSET_VALUE);
				commands.add(rmCmd);
			}
			if (managedProperty.getListEntries() != null) {
				Command rmCmd = SetCommand.create(this.getEditingDomain(),
						managedProperty, FacesConfigPackage.eINSTANCE
								.getManagedPropertyType_ListEntries(),
						SetCommand.UNSET_VALUE);
				commands.add(rmCmd);
			}
			if (managedProperty.getValue() != null) {
				Command rmCmd = SetCommand.create(this.getEditingDomain(),
						managedProperty, FacesConfigPackage.eINSTANCE
								.getManagedPropertyType_Value(),
						SetCommand.UNSET_VALUE);
				commands.add(rmCmd);
			}
			if (managedProperty.getMapEntries() != null) {
				Command rmCmd = SetCommand.create(this.getEditingDomain(),
						managedProperty, FacesConfigPackage.eINSTANCE
								.getManagedPropertyType_MapEntries(),
						SetCommand.UNSET_VALUE);
				commands.add(rmCmd);
			}

			Object valueObject = dialog.getValueObject();
			if (valueObject != null) {
				String valueType = dialog.getValueType();
				if (valueType.equals(IFacesConfigConstants.VALUE)) {

					ValueType value = FacesConfigFactory.eINSTANCE
							.createValueType();
					value.setTextContent((String) valueObject);
					managedProperty.setValue(value);
					Command cmd3 = SetCommand.create(this.getEditingDomain(),
							managedProperty, FacesConfigPackage.eINSTANCE
									.getManagedPropertyType_Value(), value);
					commands.add(cmd3);

				} else if (valueType.equals(IFacesConfigConstants.MAP_ENTRIES)) {

					MapEntriesType mapEntriesType = (MapEntriesType) valueObject;
					Command cmd3 = SetCommand.create(this.getEditingDomain(),
							managedProperty, FacesConfigPackage.eINSTANCE
									.getManagedPropertyType_MapEntries(),
							mapEntriesType);
					commands.add(cmd3);
				} else if (valueType.equals(IFacesConfigConstants.LIST_ENTRIES)) {

					ListEntriesType listEntriesType = (ListEntriesType) valueObject;
					Command cmd3 = SetCommand.create(this.getEditingDomain(),
							managedProperty, FacesConfigPackage.eINSTANCE
									.getManagedPropertyType_ListEntries(),
							listEntriesType);
					commands.add(cmd3);
				} else if (valueType.equals(IFacesConfigConstants.NULL_VALUE)) {

					NullValueType nullValue = (NullValueType) valueObject;
					Command cmd3 = SetCommand.create(this.getEditingDomain(),
							managedProperty, FacesConfigPackage.eINSTANCE
									.getManagedPropertyType_NullValue(),
							nullValue);
					commands.add(cmd3);
				}
			}
			CompoundCommand command = new CompoundCommand(commands);
			if (command.canExecute()) {
				getEditingDomain().getCommandStack().execute(command);
			}
		}
	}

	private ManagedPropertyType createManagedPropertyFromDialog(
			AddEditManagedPropertyDialog dialog) {

		String propertyName = dialog.getPropertyName();
		String propertyClass = dialog.getPropertyClass();

		ManagedPropertyType managedProperty = FacesConfigFactory.eINSTANCE
				.createManagedPropertyType();
		PropertyNameType propertyNameType = FacesConfigFactory.eINSTANCE
				.createPropertyNameType();
		propertyNameType.setTextContent(propertyName);
		PropertyClassType propertyClassType = FacesConfigFactory.eINSTANCE
				.createPropertyClassType();
		propertyClassType.setTextContent(propertyClass);
		managedProperty.setPropertyName(propertyNameType);
		managedProperty.setPropertyClass(propertyClassType);

		Object valueObject = dialog.getValueObject();
		if (valueObject != null) {
			String valueType = dialog.getValueType();
			if (valueType.equals(IFacesConfigConstants.VALUE)) {
				ValueType value = FacesConfigFactory.eINSTANCE
						.createValueType();
				value.setTextContent((String) valueObject);
				managedProperty.setValue(value);
			} else if (valueType.equals(IFacesConfigConstants.MAP_ENTRIES)) {
				MapEntriesType mapEntriesType = (MapEntriesType) dialog
						.getValueObject();
				managedProperty.setMapEntries(mapEntriesType);
			} else if (valueType.equals(IFacesConfigConstants.LIST_ENTRIES)) {
				ListEntriesType listEntriesType = (ListEntriesType) dialog
						.getValueObject();
				managedProperty.setListEntries(listEntriesType);
			} else if (valueType.equals(IFacesConfigConstants.NULL_VALUE)) {
				NullValueType nullValue = (NullValueType) dialog
						.getValueObject();
				managedProperty.setNullValue(nullValue);
			}
		}
		return managedProperty;
	}

	private void removeButtonSelected() {
		ManagedPropertyType managedProperty = (ManagedPropertyType) ((IStructuredSelection) tableViewer
				.getSelection()).getFirstElement();
		Assert.isNotNull(managedProperty);

		Command removeCommand = RemoveCommand.create(this.getEditingDomain(),
				this.getInput(), FacesConfigPackage.eINSTANCE
						.getManagedBeanType_ManagedProperty(), managedProperty);

		if (removeCommand.canExecute()) {
			this.getEditingDomain().getCommandStack().execute(removeCommand);
			refreshAll();
		}
	}

	public void setInput(Object newInput) {
		if (newInput != null && newInput instanceof ManagedBeanType) {
			managedBean = (ManagedBeanType) newInput;
			refreshAll();
		}
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
		tableViewer.refresh();
		updateButtons();
	}

	public Object getInput() {
		return managedBean;
	}

	private EditingDomain getEditingDomain() {
		return section.getEditingDomain();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	public ISelection getSelection() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		for (Iterator listeners = selectionChangedListeners.iterator(); listeners
				.hasNext();) {
			ISelectionChangedListener listener = (ISelectionChangedListener) listeners
					.next();
			listener.selectionChanged(new SelectionChangedEvent(this, event
					.getSelection()));
		}
	}

}
