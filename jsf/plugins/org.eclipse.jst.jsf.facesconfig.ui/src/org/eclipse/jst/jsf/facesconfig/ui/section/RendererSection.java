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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
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
import org.eclipse.jst.jsf.facesconfig.emf.ComponentFamilyType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererClassType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererTypeType;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.jst.jsf.facesconfig.ui.dialog.AddEditRendererDialog;
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
 * @author Bryan Yang
 * 
 */
public class RendererSection extends AbstractFacesConfigSection {

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
	public RendererSection(Composite parent, IManagedForm managedForm,
			IFacesConfigPage page, FormToolkit toolkit) {
		super(parent, managedForm, page, toolkit, null, null);
		this.getSection().setText(EditorMessages.RendererSection_Name);
		this.getSection().setDescription(
				EditorMessages.RendererSection_Description);

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

		Composite RendererSection = toolkit.createComposite(container);
		GridLayout gl2 = new GridLayout();
		gl2.horizontalSpacing = 0;
		gl2.verticalSpacing = 0;
		gl2.marginWidth = 0;
		gl2.marginHeight = 0;
		gl2.numColumns = 2;
		RendererSection.setLayout(gl2);
		GridData gd = new GridData(GridData.FILL_BOTH
				| GridData.VERTICAL_ALIGN_BEGINNING);
		RendererSection.setLayoutData(gd);

		createTableSection(RendererSection, toolkit);
		createButtonsSection(RendererSection, toolkit);

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
		nameCol.setText(EditorMessages.RendererSection_Table_NameColumn);

		TableColumn valueCol = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(1, true));
		valueCol
				.setText(EditorMessages.RendererSection_Table_ComponentFamilyColumn);

		TableColumn suggestedValueCol = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(1, true));
		suggestedValueCol
				.setText(EditorMessages.RendererSection_Table_TypeColumn);

		TableColumn classCol = new TableColumn(table, SWT.NONE);
		layout.addColumnData(new ColumnWeightData(1, true));
		classCol.setText(EditorMessages.RendererSection_Table_ClassColumn);

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
				return FacesConfigPackage.eINSTANCE.getRendererType()
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
	 * @return
	 */
	private EReference getEReference() {
		EReference reference = null;
		if (getInput() instanceof RenderKitType) {
			reference = FacesConfigPackage.eINSTANCE
					.getRenderKitType_Renderer();
		}
		return reference;
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
	 * add an Renderer.
	 * 
	 */
	private void addButtonSelected() {
		Shell shell = EditorPlugin.getActiveShell();

		AddEditRendererDialog dialog = new AddEditRendererDialog(shell, this
				.getPage(), true);
		dialog.setProject((IProject) getPage().getEditor().getAdapter(
				IProject.class));
		if (dialog.open() == Dialog.OK) {

			// RendererType newRenderer = dialog.getResult();
			RendererType newRenderer = FacesConfigFactory.eINSTANCE
					.createRendererType();

			if (dialog.getRendererName().length() > 0) {
				DisplayNameType diaplayName = FacesConfigFactory.eINSTANCE
						.createDisplayNameType();
				diaplayName.setTextContent(dialog.getRendererName());
				newRenderer.getDisplayName().add(diaplayName);
			}

			if (dialog.getRendererClass().length() > 0) {
				RendererClassType RendererClassType = FacesConfigFactory.eINSTANCE
						.createRendererClassType();
				RendererClassType.setTextContent(dialog.getRendererClass());
				newRenderer.setRendererClass(RendererClassType);
			}

			if (dialog.getComponentFamily().length() > 0) {
				ComponentFamilyType componentFamilyType = FacesConfigFactory.eINSTANCE
						.createComponentFamilyType();
				componentFamilyType.setTextContent(dialog.getComponentFamily());
				newRenderer.setComponentFamily(componentFamilyType);
			}

			if (dialog.getRendererType().length() > 0) {
				RendererTypeType rendererType = FacesConfigFactory.eINSTANCE
						.createRendererTypeType();
				rendererType.setTextContent(dialog.getRendererType());
				newRenderer.setRendererType(rendererType);
			}

			Command addCommand = AddCommand.create(this.getEditingDomain(),
					getInput(), getEReference(), newRenderer);
			if (addCommand.canExecute()) {
				this.getEditingDomain().getCommandStack().execute(addCommand);

			}
		}

	}

	/**
	 * edit an Renderer.
	 * 
	 */
	private void editButtonSelected() {

		RendererType renderer = (RendererType) ((IStructuredSelection) tableViewer
				.getSelection()).getFirstElement();

		Assert.isNotNull(renderer);

		Shell shell = EditorPlugin.getActiveShell();

		AddEditRendererDialog dialog = new AddEditRendererDialog(shell, this
				.getPage(), false);
		dialog.setProject((IProject) getPage().getEditor().getAdapter(
				IProject.class));
		if (renderer.getDisplayName() != null
				&& renderer.getDisplayName().size() > 0) {
			String name = ((DisplayNameType) (renderer.getDisplayName().get(0)))
					.getTextContent();
			if (name == null) {
				name = ""; //$NON-NLS-1$
			}
			dialog.setRendererName(name);
		}

		if (renderer.getRendererClass() != null) {
			dialog.setRendererClass(renderer.getRendererClass()
					.getTextContent());
		}

		if (renderer.getComponentFamily() != null) {
			dialog.setComponentFamily(renderer.getComponentFamily()
					.getTextContent());
		}

		if (renderer.getRendererType() != null) {
			dialog.setRendererType(renderer.getRendererType().getTextContent());
		}

		if (dialog.open() == Dialog.OK) {
			List commands = new ArrayList(4);

			if (renderer.getDisplayName() != null
					&& renderer.getDisplayName().size() > 0) {
				DisplayNameType diaplayName = FacesConfigFactory.eINSTANCE
						.createDisplayNameType();
				diaplayName.setTextContent(dialog.getRendererName());

				Command cmd1 = SetCommand.create(this.getEditingDomain(),
						renderer.getDisplayName().get(0),
						FacesConfigPackage.eINSTANCE
								.getRendererType_DisplayName(), diaplayName);
				commands.add(cmd1);
			} else if (dialog.getRendererName().length() > 0) {
				DisplayNameType displayName = FacesConfigFactory.eINSTANCE
						.createDisplayNameType();
				displayName.setTextContent(dialog.getRendererName());

				EList dl = renderer.getDisplayName();
				dl.add(displayName);
				Command cmd2 = AddCommand.create(this.getEditingDomain(),
						renderer.getDisplayName(), FacesConfigPackage.eINSTANCE
								.getRendererType_DisplayName(), dl);
				commands.add(cmd2);
			}

			if (renderer.getRendererClass() != null) {
				Command cmd3 = SetCommand.create(this.getEditingDomain(),
						renderer.getRendererClass(),
						FacesConfigPackage.eINSTANCE
								.getRendererClassType_TextContent(), dialog
								.getRendererClass());
				commands.add(cmd3);
			} else if (dialog.getRendererClass().length() > 0) {
				RendererClassType RendererClassType = FacesConfigFactory.eINSTANCE
						.createRendererClassType();
				RendererClassType.setTextContent(dialog.getRendererClass());

				Command cmd4 = AddCommand.create(this.getEditingDomain(),
						renderer, FacesConfigPackage.eINSTANCE
								.getRendererType_RendererClass(),
						RendererClassType);
				commands.add(cmd4);
			}

			if (renderer.getComponentFamily() != null) {
				Command cmd5 = SetCommand.create(this.getEditingDomain(),
						renderer.getComponentFamily(),
						FacesConfigPackage.eINSTANCE
								.getDefaultValueType_TextContent(), dialog
								.getComponentFamily());
				commands.add(cmd5);

			} else if (dialog.getComponentFamily().length() > 0) {
				ComponentFamilyType componentFamily = FacesConfigFactory.eINSTANCE
						.createComponentFamilyType();
				componentFamily.setTextContent(dialog.getComponentFamily());
				Command cmd6 = AddCommand.create(this.getEditingDomain(),
						renderer, FacesConfigPackage.eINSTANCE
								.getRendererType_ComponentFamily(),
						componentFamily);
				commands.add(cmd6);
			}

			if (renderer.getRendererType() != null) {
				Command cmd7 = SetCommand.create(this.getEditingDomain(),
						renderer.getRendererType(),
						FacesConfigPackage.eINSTANCE
								.getSuggestedValueType_TextContent(), dialog
								.getRendererType());
				commands.add(cmd7);
			} else if (dialog.getRendererType().length() > 0) {
				RendererTypeType rendererType = FacesConfigFactory.eINSTANCE
						.createRendererTypeType();
				rendererType.setTextContent(dialog.getRendererType());
				Command cmd8 = AddCommand.create(this.getEditingDomain(),
						renderer, FacesConfigPackage.eINSTANCE
								.getRendererType_RendererType(), rendererType);
				commands.add(cmd8);
			}

			CompoundCommand command = new CompoundCommand(commands);
			if (command.canExecute()) {
				this.getEditingDomain().getCommandStack().execute(command);
				tableViewer.refresh(renderer);

			}
		}

	}

	/**
	 * remove an Renderer.
	 * 
	 */
	private void removeButtonSelected() {
		RendererType Renderer = (RendererType) ((IStructuredSelection) tableViewer
				.getSelection()).getFirstElement();

		Assert.isNotNull(Renderer);

		Command removeCommand = RemoveCommand.create(this.getEditingDomain(),
				getInput(), getEReference(), Renderer);

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
