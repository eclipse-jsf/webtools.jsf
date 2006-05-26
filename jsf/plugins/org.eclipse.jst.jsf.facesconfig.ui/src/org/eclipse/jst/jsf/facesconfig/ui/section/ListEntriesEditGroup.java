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

import org.eclipse.core.resources.IProject;
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
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.ClassButtonDialogField;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.DialogField;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.DialogFieldBase;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.DialogFieldGroup;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.IDialogFieldApplyListener;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.LayoutUtil;
import org.eclipse.jst.jsf.facesconfig.common.guiutils.SWTUtils;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.NullValueType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueType;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.NewEditorResourcesNLS;
import org.eclipse.jst.jsf.facesconfig.ui.dialog.EditValueDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * ListEntries editing DialogFieldGroup
 * 
 * @author Xiao-guang Zhang, sfshi
 * @version
 */
public class ListEntriesEditGroup extends DialogFieldGroup implements
		IDialogFieldGroup, ISelectionProvider, ISelectionChangedListener {

	private ClassButtonDialogField valueClassField;

	private static final int TABLE_DEFAULT_HEIGHT = 160;

	private IProject currentProject;

	private TableViewer tableViewer;

	private Button removeButton;

	private Button editButton;

	private ManagedBeanType managedBean;

	private AbstractFacesConfigSection section;

	private List selectionChangedListeners = new ArrayList();

	/**
	 * @param propertyRecorder
	 */
	public ListEntriesEditGroup(AbstractFacesConfigSection section) {
		super();
		this.section = section;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#initialize()
	 */
	public void initialize() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#refreshData()
	 */
	public void refreshData() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogfield.DialogFieldGroup#layoutDialogFields(org.eclipse.ui.forms.widgets.FormToolkit,
	 *      org.eclipse.swt.widgets.Composite)
	 */
	public void layoutDialogFields(FormToolkit toolkit, Composite parent) {
		GridData data = new GridData(GridData.FILL_BOTH);
		parent.setLayoutData(data);

		GridLayout gl = new GridLayout();
		gl.verticalSpacing = 0;
		gl.marginHeight = 0;
		parent.setLayout(gl);

		layoutListTypeSelectionSection(toolkit, parent);

		createAndLayoutListValueSection(toolkit, parent);
	}

	/**
	 * 
	 */
	private void layoutListTypeSelectionSection(FormToolkit toolkit,
			Composite container) {
		Composite typeSelectionSection = null;
		if (toolkit != null) {
			typeSelectionSection = toolkit.createComposite(container);
			toolkit.paintBordersFor(typeSelectionSection);
		} else {
			typeSelectionSection = SWTUtils
					.createComposite(container, SWT.NONE);
		}

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		typeSelectionSection.setLayoutData(gd);

		int numberOfColumns = 4;
		GridLayout gl = new GridLayout(numberOfColumns, false);
		typeSelectionSection.setLayout(gl);
		valueClassField = new ClassButtonDialogField(getProject());

		valueClassField
				.setLabelText(NewEditorResourcesNLS.InitializationSection_MapType_ValueClass);//$NON-NLS-1$
		valueClassField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {

					public void dialogFieldApplied(DialogField field) {
						ManagedBeanType managedBean = (ManagedBeanType) getInput();
						String className = ((ClassButtonDialogField) field)
								.getText();
						ValueClassType valueClass = FacesConfigFactory.eINSTANCE
								.createValueClassType();
						valueClass.setTextContent(className);
						EditingDomain editingDomain = section
								.getEditingDomain();
						Command cmd;
						if (managedBean.getListEntries() == null) {
							ListEntriesType listEntries = FacesConfigFactory.eINSTANCE
									.createListEntriesType();
							listEntries.setValueClass(valueClass);
							cmd = SetCommand.create(editingDomain, managedBean,
									FacesConfigPackage.eINSTANCE
											.getManagedBeanType_ListEntries(),
									listEntries);
						} else {
							ListEntriesType listEntries = managedBean
									.getListEntries();
							cmd = SetCommand.create(editingDomain, listEntries,
									FacesConfigPackage.eINSTANCE
											.getListEntriesType_ValueClass(),
									valueClass);
						}

						if (cmd.canExecute()) {
							editingDomain.getCommandStack().execute(cmd);
						}

					}
				});
		valueClassField.doFillIntoGrid(toolkit, typeSelectionSection,
				numberOfColumns);
		LayoutUtil.setHorizontalGrabbing(valueClassField.getTextControl(
				toolkit, container));
	}

	/**
	 * @param toolkit
	 * @param parent
	 */
	private void createAndLayoutListValueSection(FormToolkit toolkit,
			Composite parent) {
		Composite listValueSection = null;
		if (toolkit != null) {
			listValueSection = toolkit.createComposite(parent);
			toolkit.paintBordersFor(listValueSection);
		} else {
			listValueSection = SWTUtils.createComposite(parent, SWT.NONE);
		}

		GridData gd = new GridData(GridData.FILL_BOTH);
		listValueSection.setLayoutData(gd);

		int numberOfColumns = 3;
		GridLayout gl = new GridLayout(numberOfColumns, false);
		listValueSection.setLayout(gl);
		DialogFieldBase valuesTitle = new DialogFieldBase();
		valuesTitle
				.setLabelText(NewEditorResourcesNLS.InitializationSection_MapTable_Title);//$NON-NLS-1$    
		valuesTitle.doFillIntoGrid(toolkit, listValueSection, numberOfColumns);

		// list's value table
		Table listTable;
		if (toolkit == null) {
			listTable = new Table(listValueSection, SWT.H_SCROLL | SWT.V_SCROLL
					| SWT.FULL_SELECTION | SWT.BORDER);
		} else {
			listTable = new Table(listValueSection, SWT.H_SCROLL | SWT.V_SCROLL
					| SWT.FULL_SELECTION | SWT.BORDER);
		}

		listTable.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				updateButtons();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				updateButtons();
			}

		});
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 1;
		gd.heightHint = TABLE_DEFAULT_HEIGHT;
		listTable.setLayoutData(gd);
		listTable.setHeaderVisible(true);
		listTable.setLinesVisible(true);
		TableLayout layout = new TableLayout();
		layout.addColumnData(new ColumnWeightData(1, true));
		listTable.setLayout(layout);
		TableColumn valueCol = new TableColumn(listTable, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(1, true));
		valueCol
				.setText(NewEditorResourcesNLS.InitializationSection_MapTable_Value);//$NON-NLS-1$
		valueCol.setResizable(true);

		listTable.addMouseListener(new MouseAdapter() {
			public void mouseDoubleClick(MouseEvent e) {
				if (((IStructuredSelection) tableViewer.getSelection()).size() > 0)
					editButtonSelected();
			}
		});
		tableViewer = new TableViewer(listTable);
		tableViewer.setContentProvider(new AdapterFactoryContentProvider(
				section.getAdapterFactory()));
		tableViewer.setLabelProvider(new AdapterFactoryLabelProvider(section
				.getAdapterFactory()));

		tableViewer.addFilter(new ViewerFilter() {
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return FacesConfigPackage.eINSTANCE.getValueType().isInstance(
						element)
						|| FacesConfigPackage.eINSTANCE.getNullValueType()
								.isInstance(element);
			}
		});
		tableViewer.addSelectionChangedListener(this);

		Composite operationContainer = null;
		if (toolkit != null) {
			operationContainer = toolkit.createComposite(listValueSection);
		} else {
			operationContainer = SWTUtils.createComposite(listValueSection,
					SWT.NONE);
		}
		gd = new GridData(GridData.FILL_VERTICAL);
		operationContainer.setLayoutData(gd);
		gl = new GridLayout();
		operationContainer.setLayout(gl);

		Button addButton = null;
		if (toolkit != null) {
			addButton = toolkit.createButton(operationContainer,
					NewEditorResourcesNLS.UI_Button_Add_more, //$NON-NLS-1$
					SWT.PUSH);
		} else {
			addButton = SWTUtils.createPushButton(operationContainer,
					NewEditorResourcesNLS.UI_Button_Add_more); //$NON-NLS-1$
		}
		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.grabExcessHorizontalSpace = false;
		addButton.setLayoutData(gd);

		editButton = null;
		if (toolkit != null) {
			editButton = toolkit.createButton(operationContainer,
					NewEditorResourcesNLS.UI_Button_Edit_more, SWT.PUSH);
		} else {
			editButton = SWTUtils.createPushButton(operationContainer,
					NewEditorResourcesNLS.UI_Button_Edit_more);
		}

		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.grabExcessHorizontalSpace = false;
		editButton.setLayoutData(gd);

		removeButton = null;
		if (toolkit != null) {
			removeButton = toolkit.createButton(operationContainer,
					NewEditorResourcesNLS.UI_Button_Remove, //$NON-NLS-1$
					SWT.PUSH);
		} else {
			removeButton = SWTUtils.createPushButton(operationContainer,
					NewEditorResourcesNLS.UI_Button_Remove); //$NON-NLS-1$
		}

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

	protected void addButtonSelected() {
		EditValueDialog dialog = new EditValueDialog(EditorPlugin
				.getActiveShell(), true, false, null);
		if (dialog.open() == Dialog.OK) {

			EditingDomain editingDomain = section.getEditingDomain();
			if (this.managedBean.getListEntries() == null) {
				ListEntriesType listEntries = FacesConfigFactory.eINSTANCE
						.createListEntriesType();
				if (dialog.isNullValue()) {
					NullValueType nullValue = FacesConfigFactory.eINSTANCE
							.createNullValueType();
					listEntries.getNullValue().add(nullValue);
				} else {
					ValueType value = FacesConfigFactory.eINSTANCE
							.createValueType();
					value.setTextContent((String) dialog.getResultData());
					listEntries.getValue().add(value);
				}

				Command cmd1 = SetCommand.create(editingDomain, managedBean,
						FacesConfigPackage.eINSTANCE
								.getManagedBeanType_ListEntries(), listEntries);
				if (cmd1.canExecute()) {
					editingDomain.getCommandStack().execute(cmd1);
					refreshAll();
				}
			} else {
				ListEntriesType listEntries = managedBean.getListEntries();
				Command cmd;
				if (dialog.isNullValue()) {
					NullValueType nullValue = FacesConfigFactory.eINSTANCE
							.createNullValueType();
					cmd = AddCommand.create(editingDomain, listEntries,
							FacesConfigPackage.eINSTANCE
									.getListEntriesType_NullValue(), nullValue);
				} else {
					ValueType value = FacesConfigFactory.eINSTANCE
							.createValueType();
					value.setTextContent((String) dialog.getResultData());
					cmd = AddCommand.create(editingDomain, listEntries,
							FacesConfigPackage.eINSTANCE
									.getListEntriesType_Value(), value);
				}
				if (cmd.canExecute()) {
					editingDomain.getCommandStack().execute(cmd);
				}
			}

		}
	}

	protected void editButtonSelected() {
		Object select = ((IStructuredSelection) tableViewer.getSelection())
				.getFirstElement();
		boolean isNullValue;
		String valueText = null;
		if (select instanceof NullValueType)
			isNullValue = true;
		else {
			isNullValue = false;
			valueText = ((ValueType) select).getTextContent();
		}
		ListEntriesType listEntries = managedBean.getListEntries();
		EditValueDialog dialog = new EditValueDialog(EditorPlugin
				.getActiveShell(), true, isNullValue, valueText);
		if (dialog.open() == Dialog.OK) {
			EditingDomain editingDomain = section.getEditingDomain();
			Command cmd = null;
			if (isNullValue && !dialog.isNullValue()) {
				List commands = new ArrayList();
				Command cmd1 = RemoveCommand.create(editingDomain, listEntries,
						FacesConfigPackage.eINSTANCE
								.getListEntriesType_NullValue(), select);
				commands.add(cmd1);

				// listEntries.getNullValue().remove(select);
				ValueType value = FacesConfigFactory.eINSTANCE
						.createValueType();
				value.setTextContent((String) dialog.getResultData());
				Command cmd2 = AddCommand
						.create(editingDomain, listEntries,
								FacesConfigPackage.eINSTANCE
										.getListEntriesType_Value(), value);
				commands.add(cmd2);
				cmd = new CompoundCommand(commands);
			} else if (!isNullValue && dialog.isNullValue()) {
				List commands = new ArrayList();
				Command cmd1 = RemoveCommand
						.create(editingDomain, listEntries,
								FacesConfigPackage.eINSTANCE
										.getListEntriesType_Value(), select);
				commands.add(cmd1);
				NullValueType nullValue = FacesConfigFactory.eINSTANCE
						.createNullValueType();
				Command cmd2 = AddCommand.create(editingDomain, listEntries,
						FacesConfigPackage.eINSTANCE
								.getListEntriesType_NullValue(), nullValue);
				commands.add(cmd2);
				cmd = new CompoundCommand(commands);
			} else if (!isNullValue && !dialog.isNullValue()) {
				cmd = SetCommand
						.create(editingDomain, select,
								FacesConfigPackage.eINSTANCE
										.getValueType_TextContent(), dialog
										.getResultData());
			}

			if (cmd != null && cmd.canExecute()) {
				editingDomain.getCommandStack().execute(cmd);
			}
		}
	}

	protected void removeButtonSelected() {
		Object select = ((IStructuredSelection) tableViewer.getSelection())
				.getFirstElement();
		ListEntriesType listEntries = managedBean.getListEntries();
		EditingDomain editingDomain = section.getEditingDomain();
		Command cmd;
		if (select instanceof NullValueType) {
			cmd = RemoveCommand
					.create(editingDomain, listEntries,
							FacesConfigPackage.eINSTANCE
									.getListEntriesType_NullValue(), select);
		} else {
			cmd = RemoveCommand.create(editingDomain, listEntries,
					FacesConfigPackage.eINSTANCE.getListEntriesType_Value(),
					select);
		}
		if (cmd.canExecute()) {
			editingDomain.getCommandStack().execute(cmd);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.managedbean.sections.wizards.IDialogFieldGroup#setInput(java.lang.Object)
	 */
	public void setInput(Object newInput) {
		if (newInput != null && newInput instanceof ManagedBeanType) {
			managedBean = (ManagedBeanType) newInput;
			if (managedBean.getListEntries() != null)
				refreshAll();
		}

	}

	/**
	 * 
	 */
	public void refreshAll() {
		if (managedBean.getListEntries().getValueClass() != null) {
			this.valueClassField.setTextWithoutUpdate(managedBean.getListEntries()
					.getValueClass().getTextContent());
		}
		tableViewer.setInput(((ManagedBeanType) getInput()).getListEntries());
		updateButtons();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.managedbean.sections.wizards.IDialogFieldGroup#getInput()
	 */
	public Object getInput() {
		return managedBean;
	}

	private IProject getProject() {
		if (currentProject == null) {
			currentProject = (IProject) section.getPage().getEditor()
					.getAdapter(IProject.class);
		}
		return currentProject;
	}

	public void setProject(IProject project) {
		currentProject = project;
	}

	public void updateButtons() {
		if (((IStructuredSelection) tableViewer.getSelection()).size() > 0) {
			editButton.setEnabled(true);
			removeButton.setEnabled(true);
		} else {
			editButton.setEnabled(false);
			removeButton.setEnabled(false);
		}
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
	 * 
	 */
	public void clearAll() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		// TODO Auto-generated method stub
		selectionChangedListeners.add(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	public ISelection getSelection() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		// TODO Auto-generated method stub
		selectionChangedListeners.remove(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		// TODO Auto-generated method stub
		for (Iterator listeners = selectionChangedListeners.iterator(); listeners
				.hasNext();) {
			ISelectionChangedListener listener = (ISelectionChangedListener) listeners
					.next();
			listener.selectionChanged(new SelectionChangedEvent(this, event
					.getSelection()));
		}
	}

}
