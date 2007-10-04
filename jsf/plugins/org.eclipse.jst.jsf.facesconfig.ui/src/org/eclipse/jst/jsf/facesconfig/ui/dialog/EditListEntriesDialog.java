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


package org.eclipse.jst.jsf.facesconfig.ui.dialog;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ClassButtonDialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldBase;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.LayoutUtil;
import org.eclipse.jst.jsf.common.ui.internal.guiutils.SWTUtils;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.NullValueType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueType;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.section.AbstractFacesConfigSection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * 
 * @author sfshi
 * 
 */
public class EditListEntriesDialog extends Dialog {

	private static final int MIN_DIALOG_WIDTH = 300;

	private static final int TABLE_DEFAULT_HEIGHT = 160;

	private ClassButtonDialogField valueClassField;

	private IProject project;

	private TableViewer tableViewer;

	private Button removeButton;

	private Button editButton;

	private ListEntriesType listEntries;

	private AbstractFacesConfigSection section;

	/**
	 * 
	 * @param parentShell
	 * @param listEntries
	 *            the list-entries element that working on.
	 * @param section 
	 */
	public EditListEntriesDialog(Shell parentShell,
			ListEntriesType listEntries, AbstractFacesConfigSection section) {
		super(parentShell);
		this.listEntries = listEntries;
		this.section = section;
	}

	/*
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Editing list-entries");
	}

	/*
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.FILL);
		GridData data = new GridData(GridData.FILL_BOTH);
		container.setLayoutData(data);

		GridLayout gl = new GridLayout();
		gl.verticalSpacing = 0;
		// gl.marginHeight = 0;
		container.setLayout(gl);
		createKeyValueSection(container);
		createTableSection(container);

		initFields();
		return container;
	}

	private void initFields() {

		if (listEntries.getValueClass() != null)
			valueClassField.setText(listEntries.getValueClass()
					.getTextContent());

		tableViewer.setInput(listEntries);
	}

	private void createKeyValueSection(Composite parent) {
		valueClassField = new ClassButtonDialogField(getProject());

		valueClassField
				.setLabelText(EditorMessages.InitializationSection_MapType_ValueClass);//$NON-NLS-1$
		Composite typeSelectionSection = SWTUtils.createComposite(parent,
				SWT.NONE);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		typeSelectionSection.setLayoutData(gd);

		int numberOfColumns = 4;
		GridLayout gl = new GridLayout(numberOfColumns, false);
		typeSelectionSection.setLayout(gl);

		valueClassField.doFillIntoGrid(null, typeSelectionSection,
				numberOfColumns);
		LayoutUtil.setHorizontalGrabbing(valueClassField.getTextControl(null,
				parent));
	}

	/**
	 * 
	 * @param parent
	 */
	private void createTableSection(Composite parent) {
		Composite mapValueSection = SWTUtils.createComposite(parent, SWT.NONE);

		GridData gd = new GridData(GridData.FILL_BOTH);
		mapValueSection.setLayoutData(gd);

		int numberOfColumns = 3;
		GridLayout gl = new GridLayout(numberOfColumns, false);
		mapValueSection.setLayout(gl);

		DialogField valuesTitle = new DialogFieldBase();
		valuesTitle
				.setLabelText(EditorMessages.InitializationSection_MapTable_Title);//$NON-NLS-1$    

		valuesTitle.doFillIntoGrid(null, mapValueSection, numberOfColumns);

		Table mapTable = new Table(mapValueSection, SWT.FULL_SELECTION
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

		mapTable.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				updateButtonsStatus();

			}

		});
		mapTable.addMouseListener(new MouseAdapter() {
			public void mouseDoubleClick(MouseEvent e) {
				if (((IStructuredSelection) tableViewer.getSelection()).size() > 0)
					editButtonSelected(null);
			}
		});
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 1;
		gd.heightHint = TABLE_DEFAULT_HEIGHT;
		mapTable.setLayoutData(gd);
		mapTable.setHeaderVisible(true);
		mapTable.setLinesVisible(true);
		TableLayout layout = new TableLayout();
		layout.addColumnData(new ColumnWeightData(1, true));
		mapTable.setLayout(layout);

		TableColumn keyCol = new TableColumn(mapTable, SWT.NONE);
		keyCol
				.setText(EditorMessages.InitializationSection_MapTable_Value);//$NON-NLS-1$
		layout.addColumnData(new ColumnWeightData(1, true));
		keyCol.setResizable(true);

		tableViewer = new TableViewer(mapTable);
		tableViewer.setContentProvider(new AdapterFactoryContentProvider(
				getAdapterFactory()));
		tableViewer.setLabelProvider(new AdapterFactoryLabelProvider(
				getAdapterFactory()));

		tableViewer.addFilter(new ViewerFilter() {
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return FacesConfigPackage.eINSTANCE.getValueType().isInstance(
						element)
						|| FacesConfigPackage.eINSTANCE.getNullValueType()
								.isInstance(element);
			}
		});

		Composite operationContainer = null;

		operationContainer = SWTUtils
				.createComposite(mapValueSection, SWT.NONE);

		gd = new GridData(GridData.FILL_VERTICAL);
		operationContainer.setLayoutData(gd);
		gl = new GridLayout();
		operationContainer.setLayout(gl);

		Button addButton = SWTUtils.createPushButton(operationContainer,
				EditorMessages.UI_Button_Add_more); //$NON-NLS-1$

		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.grabExcessHorizontalSpace = false;
		addButton.setLayoutData(gd);
		addButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				addButtonSelected(e);
			}
		});

		editButton = SWTUtils.createPushButton(operationContainer,
				EditorMessages.UI_Button_Edit_more);

		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.grabExcessHorizontalSpace = false;
		editButton.setLayoutData(gd);
		editButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				editButtonSelected(e);
			}

		});
		removeButton = SWTUtils.createPushButton(operationContainer,
				EditorMessages.UI_Button_Remove); //$NON-NLS-1$

		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.grabExcessHorizontalSpace = false;
		removeButton.setLayoutData(gd);
		removeButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				removeButtonSelected();
			}

		});
		editButton.setEnabled(false);
		removeButton.setEnabled(false);
	}

	private AdapterFactory getAdapterFactory() {
		return section.getAdapterFactory();
	}

	private void addButtonSelected(SelectionEvent e) {

		EditValueDialog dialog = new EditValueDialog(EditorPlugin
				.getActiveShell(), true, false, null);
		if (dialog.open() == Dialog.OK) {
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
		}

	}

	private void editButtonSelected(SelectionEvent e) {
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

		EditValueDialog dialog = new EditValueDialog(EditorPlugin
				.getActiveShell(), true, isNullValue, valueText);
		if (dialog.open() == Dialog.OK) {
			if (isNullValue && !dialog.isNullValue()) {
				listEntries.getNullValue().remove(select);
				ValueType value = FacesConfigFactory.eINSTANCE
						.createValueType();
				value.setTextContent((String) dialog.getResultData());
				listEntries.getValue().add(value);
			} else if (!isNullValue && dialog.isNullValue()) {
				listEntries.getValue().remove(select);
				NullValueType nullValue = FacesConfigFactory.eINSTANCE
						.createNullValueType();
				listEntries.getNullValue().add(nullValue);
			} else if (!isNullValue && !dialog.isNullValue()) {
				((ValueType) select).setTextContent((String) dialog
						.getResultData());
			}
		}
	}

	private void removeButtonSelected() {
		Object select = ((IStructuredSelection) tableViewer.getSelection())
				.getFirstElement();
		if (select instanceof NullValueType)
			listEntries.getNullValue().remove(select);
		else
			listEntries.getValue().remove(select);
		tableViewer.refresh();
		updateButtonsStatus();
	}

	/**
	 * update the status buttons
	 */
	public void updateButtonsStatus() {
		if (((IStructuredSelection) tableViewer.getSelection()).size() > 0) {
			editButton.setEnabled(true);
			removeButton.setEnabled(true);
		} else {
			editButton.setEnabled(false);
			removeButton.setEnabled(false);
		}
	}

	/**
	 * 
	 */
	protected void okPressed() {

		String valueClass = valueClassField.getText();
		if (listEntries.getValueClass() != null) {
			listEntries.getValueClass().setTextContent(valueClass);
		} else {
			ValueClassType valueClassType = FacesConfigFactory.eINSTANCE
					.createValueClassType();
			valueClassType.setTextContent(valueClass);
			listEntries.setValueClass(valueClassType);
		}
		super.okPressed();
	}

	/*
	 * @see org.eclipse.jface.window.Window#getInitialSize()
	 */
	protected Point getInitialSize() {
		Point shellSize = super.getInitialSize();
		return new Point(Math.max(
				convertHorizontalDLUsToPixels(MIN_DIALOG_WIDTH), shellSize.x),
				shellSize.y);
	}

	/**
	 * @return the current project
	 */
	public IProject getProject() {
		if (project == null) {
			project = (IProject) section.getPage().getEditor().getAdapter(
					IProject.class);
		}
		return project;
	}

	/**
	 * @param project
	 */
	public void setProject(IProject project) {
		this.project = project;
	}

	/**
	 * @return the list entries
	 */
	public ListEntriesType getListEntries() {
		return listEntries;
	}

	/**
	 * @param listEntries
	 */
	public void setListEntries(ListEntriesType listEntries) {
		this.listEntries = listEntries;
	}

}
