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

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.ClassButtonDialogField;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.ComboDialogField;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.DialogField;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.IDialogFieldApplyListener;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.IDialogFieldChangeListener;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.LayoutUtil;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.StringDialogField;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.ui.IFacesConfigConstants;
import org.eclipse.jst.jsf.facesconfig.ui.NewEditorResourcesNLS;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * 
 * @author sfshi
 * 
 */
public class ManagedBeanGeneralSection extends AbstractFacesConfigSection {

	private StringDialogField mbNameField;

	private ClassButtonDialogField mbClassField;

	private ComboDialogField mbScopeField;

	private ManagedBeanGeneralSectionAdapter managedBeanGeneralSectionAdapter;

	/**
	 * 
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 */
	public ManagedBeanGeneralSection(Composite parent,
			IManagedForm managedForm, IFacesConfigPage page, FormToolkit toolkit) {
		super(parent, managedForm, page, toolkit, null, null);
		getSection().setText(
				NewEditorResourcesNLS.ManagedBeanGeneralSection_Name);
		getSection().setDescription(
				NewEditorResourcesNLS.ManagedBeanGeneralSection_Desc);
	}

	protected void createContents(Composite container, FormToolkit toolkit) {
		int numberOfColumns = 4;
		GridLayout layout = new GridLayout(numberOfColumns, false);
		container.setLayout(layout);

		toolkit.paintBordersFor(container);

		createMBNameEntry(container, toolkit, numberOfColumns);

		createMBClassEntry(container, toolkit, numberOfColumns);

		createMBScopeEntry(container, toolkit, numberOfColumns);

	}

	/**
	 * create managed bean's name editing field
	 * 
	 * @param container
	 * @param toolkit
	 */
	private void createMBNameEntry(Composite container, FormToolkit toolkit,
			int numberOfColumns) {
		mbNameField = new StringDialogField();
		mbNameField
				.setLabelText(NewEditorResourcesNLS.ManagedBeanGeneralSection_ManagedBeanName);//$NON-NLS-1$
		mbNameField.doFillIntoGrid(toolkit, container, numberOfColumns);
		LayoutUtil.setHorizontalGrabbing(mbNameField.getTextControl(toolkit,
				container));
		mbNameField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						String mbNameValue = ((StringDialogField) field)
								.getText();
						Command cmd = null;
						ManagedBeanType managedBean = (ManagedBeanType) getInput();
						// if (managedBean.getManagedBeanName() != null) {
						// cmd = SetCommand
						// .create(
						// getEditingDomain(),
						// managedBean.getManagedBeanName(),
						// FacesConfigPackage.eINSTANCE
						// .getManagedBeanNameType_TextContent(),
						// mbNameValue);
						// } else {
						ManagedBeanNameType newManagedBeanName = FacesConfigFactory.eINSTANCE
								.createManagedBeanNameType();
						newManagedBeanName.setTextContent(mbNameValue);
						cmd = SetCommand.create(getEditingDomain(),
								managedBean, FacesConfigPackage.eINSTANCE
										.getManagedBeanType_ManagedBeanName(),
								newManagedBeanName);
						// }
						if (cmd.canExecute()) {
							getEditingDomain().getCommandStack().execute(cmd);
						}
					}
				});
	}

	/**
	 * create managed bean's class field.
	 * 
	 * @param container
	 * @param toolkit
	 */
	private void createMBClassEntry(final Composite container,
			final FormToolkit toolkit, int numberOfColumns) {
		mbClassField = new ClassButtonDialogField(null);
		mbClassField
				.setLabelText(NewEditorResourcesNLS.ManagedBeanGeneralSection_ManagedBeanClass);//$NON-NLS-1$
		mbClassField.setProject((IProject) getPage().getEditor().getAdapter(
				IProject.class));
		mbClassField.doFillIntoGrid(toolkit, container, numberOfColumns);
		LayoutUtil.setHorizontalGrabbing(mbClassField.getTextControl(toolkit,
				container));

		mbClassField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						String newValue = ((StringDialogField) field).getText();
						Command cmd = null;
						ManagedBeanType managedBean = (ManagedBeanType) getInput();
						// if (managedBean.getManagedBeanClass() != null) {
						// cmd = SetCommand
						// .create(
						// getEditingDomain(),
						// managedBean.getManagedBeanClass(),
						// FacesConfigPackage.eINSTANCE
						// .getManagedBeanClassType_TextContent(),
						// newValue);
						// } else {
						ManagedBeanClassType newManagedBeanClass = FacesConfigFactory.eINSTANCE
								.createManagedBeanClassType();
						newManagedBeanClass.setTextContent(newValue);
						cmd = SetCommand.create(getEditingDomain(),
								managedBean, FacesConfigPackage.eINSTANCE
										.getManagedBeanType_ManagedBeanClass(),
								newManagedBeanClass);
						// }
						if (cmd.canExecute()) {
							getEditingDomain().getCommandStack().execute(cmd);
						}
					}
				});
	}

	/**
	 * create managed bean's scope field
	 * 
	 * @param container
	 * @param toolkit
	 */
	private void createMBScopeEntry(Composite container, FormToolkit toolkit,
			int numberOfColumns) {
		mbScopeField = new ComboDialogField(SWT.DROP_DOWN | SWT.READ_ONLY | SWT.BORDER);
		mbScopeField
				.setLabelText(NewEditorResourcesNLS.ManagedBeanGeneralSection_ManagedBeanScope);//$NON-NLS-1$
		mbScopeField.doFillIntoGrid(toolkit, container, numberOfColumns);
		LayoutUtil.setHorizontalGrabbing(mbScopeField.getComboControl(toolkit,
				container));

		mbScopeField.setItems(ManagedBeanScopeTreeItem.scopeItems);

		mbScopeField
				.setDialogFieldChangeListener(new IDialogFieldChangeListener() {
					public void dialogFieldChanged(DialogField field) {
						String newValue = ((ComboDialogField) field).getText();
						Command cmd = null;
						ManagedBeanType managedBean = (ManagedBeanType) getInput();
						// if (managedBean.getManagedBeanScope() != null) {
						// cmd = SetCommand
						// .create(
						// getEditingDomain(),
						// managedBean.getManagedBeanScope(),
						// FacesConfigPackage.eINSTANCE
						// .getManagedBeanScopeType_TextContent(),
						// newValue);
						// } else {
						ManagedBeanScopeType newManagedBeanScope = FacesConfigFactory.eINSTANCE
								.createManagedBeanScopeType();
						newManagedBeanScope.setTextContent(newValue);
						cmd = SetCommand.create(getEditingDomain(),
								managedBean, FacesConfigPackage.eINSTANCE
										.getManagedBeanType_ManagedBeanScope(),
								newManagedBeanScope);
						// }
						if (cmd.canExecute()) {
							getEditingDomain().getCommandStack().execute(cmd);
						}
					}
				});
	}

	public void clearAll() {
		// TODO Auto-generated method stub

	}

	public void refreshAll() {
		refresh();
	}

	/**
	 * 
	 */
	public void refresh() {
		super.refresh();
		Object input = this.getInput();
		if (input instanceof ManagedBeanType) {
			ManagedBeanType component = (ManagedBeanType) input;

			if (component.getManagedBeanName() != null) {
				mbNameField.setTextWithoutUpdate(component.getManagedBeanName()
						.getTextContent());
			} else {
				mbNameField.setTextWithoutUpdate("");
			}

			if (component.getManagedBeanClass() != null) {
				mbClassField.setTextWithoutUpdate(component
						.getManagedBeanClass().getTextContent());
			} else {
				mbClassField.setTextWithoutUpdate("");
			}

			if (component.getManagedBeanScope() != null) {
				mbScopeField.setTextWithoutUpdate(component
						.getManagedBeanScope().getTextContent());
			} else {
				// defaultly set it's scope to "session";
				mbScopeField
						.setTextWithoutUpdate(IFacesConfigConstants.MANAGED_BEAN_SCOPE_SESSION);
			}
		}
	}

	protected void addAdaptersOntoInput(Object newInput) {
		super.addAdaptersOntoInput(newInput);
		ManagedBeanType managedBean = (ManagedBeanType) newInput;
		if (EcoreUtil.getExistingAdapter(managedBean,
				ManagedBeanGeneralSection.class) == null) {
			managedBean.eAdapters().add(getManagedBeanGeneralSectionAdatper());
		}

	}

	protected void removeAdaptersFromInput(Object oldInput) {
		super.removeAdaptersFromInput(oldInput);
		ManagedBeanType managedBean = (ManagedBeanType) oldInput;
		if (EcoreUtil.getExistingAdapter(managedBean,
				ManagedBeanGeneralSection.class) != null) {
			managedBean.eAdapters().remove(
					getManagedBeanGeneralSectionAdatper());
		}
	}

	protected ManagedBeanGeneralSectionAdapter getManagedBeanGeneralSectionAdatper() {
		if (managedBeanGeneralSectionAdapter == null) {
			managedBeanGeneralSectionAdapter = new ManagedBeanGeneralSectionAdapter();
		}
		return managedBeanGeneralSectionAdapter;
	}

	class ManagedBeanGeneralSectionAdapter extends AdapterImpl {

		public boolean isAdapterForType(Object type) {

			if (type == ManagedBeanGeneralSection.class)
				return true;
			return false;
		}

		public void notifyChanged(Notification msg) {
			if (msg.getEventType() == Notification.ADD
					|| msg.getEventType() == Notification.REMOVE
					|| msg.getEventType() == Notification.SET) {
				if (msg.getFeature() == FacesConfigPackage.eINSTANCE
						.getManagedBeanType_ManagedBeanName()
						|| msg.getFeature() == FacesConfigPackage.eINSTANCE
								.getManagedBeanType_ManagedBeanClass()
						|| msg.getFeature() == FacesConfigPackage.eINSTANCE
								.getManagedBeanType_ManagedBeanScope()) {
					refresh();
				}
			}
		}

	}

}
