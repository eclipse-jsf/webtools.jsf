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
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.ClassButtonDialogField;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.DialogField;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.IDialogFieldApplyListener;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.LayoutUtil;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.StringDialogField;
import org.eclipse.jst.jsf.facesconfig.ui.IFacesConfigConstants;
import org.eclipse.jst.jsf.facesconfig.ui.NewEditorResourcesNLS;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.jst.jsf.facesconfig.ui.util.ModelUtil;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wtp.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.wtp.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.wtp.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.wtp.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.wtp.jsf.facesconfig.emf.RenderKitClassType;
import org.eclipse.wtp.jsf.facesconfig.emf.RenderKitIdType;
import org.eclipse.wtp.jsf.facesconfig.emf.RenderKitType;

/**
 * @author Bryan Yang
 * 
 */
public class RenderkitGeneralSection extends AbstractFacesConfigSection {

	private StringDialogField displayNameField;

	private StringDialogField descriptionField;

	private StringDialogField renderkitIdField;

	private ClassButtonDialogField renderkitClassField;

	private RenderKitGeneralAdapter renderKitGeneralAdapter;

	/**
	 * 
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 */
	public RenderkitGeneralSection(Composite parent, IManagedForm managedForm,
			IFacesConfigPage page, FormToolkit toolkit) {
		super(parent, managedForm, page, toolkit, null, null);
		getSection()
				.setText(NewEditorResourcesNLS.RenderKitGeneralSection_Name);
		getSection().setDescription(
				NewEditorResourcesNLS.RenderKitGeneralSection_Description);
	}

	/**
	 * 
	 */
	protected void createContents(Composite container, FormToolkit toolkit) {
		int numberOfColumns = 4;
		GridLayout layout = new GridLayout(numberOfColumns, false);
		container.setLayout(layout);
		GridData td = new GridData(GridData.FILL_HORIZONTAL);
		container.setLayoutData(td);

		toolkit.paintBordersFor(container);
		displayNameField = new StringDialogField();
		displayNameField
				.setLabelText(NewEditorResourcesNLS.RenderKitGeneralSection_Label_DisplayName);
		displayNameField.doFillIntoGrid(toolkit, container, numberOfColumns);

		displayNameField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {

					public void dialogFieldApplied(DialogField field) {
						String newDisplayNameValue = ((StringDialogField) field)
								.getText().trim();
						Command cmd = null;
						RenderKitType Renderkit = (RenderKitType) getInput();
						EditingDomain editingDomain = getEditingDomain();
						if (Renderkit.getDisplayName().size() > 0) {
							DisplayNameType displayName = (DisplayNameType) Renderkit
									.getDisplayName().get(0);
							cmd = SetCommand.create(editingDomain, displayName,
									FacesConfigPackage.eINSTANCE
											.getDisplayNameType_TextContent(),
									newDisplayNameValue);
						} else {
							DisplayNameType displayNameType = FacesConfigFactory.eINSTANCE
									.createDisplayNameType();
							displayNameType.setTextContent(newDisplayNameValue);
							displayNameType.eAdapters().add(
									getRenderKitGeneralAdapter());

							cmd = AddCommand.create(editingDomain, Renderkit,
									FacesConfigPackage.eINSTANCE
											.getRenderKitType_DisplayName(),
									displayNameType);
						}
						if (cmd.canExecute()) {
							editingDomain.getCommandStack().execute(cmd);
						}
					}

				});

		descriptionField = new StringDialogField(2);
		descriptionField
				.setLabelText(NewEditorResourcesNLS.RenderKitGeneralSection_Label_Description);
		descriptionField.doFillIntoGrid(toolkit, container, numberOfColumns);

		descriptionField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						String newDescriptionValue = ((StringDialogField) field)
								.getText().trim();
						newDescriptionValue = ModelUtil
								.escapeEntities(newDescriptionValue);
						Command cmd = null;
						RenderKitType Renderkit = (RenderKitType) getInput();
						EditingDomain editingDomain = getEditingDomain();
						if (Renderkit.getDescription().size() > 0) {
							DescriptionType description = (DescriptionType) Renderkit
									.getDescription().get(0);
							cmd = SetCommand.create(editingDomain, description,
									FacesConfigPackage.eINSTANCE
											.getDescriptionType_TextContent(),
									newDescriptionValue);
						} else {
							DescriptionType description = FacesConfigFactory.eINSTANCE
									.createDescriptionType();
							description.setTextContent(newDescriptionValue);
							description.eAdapters().add(
									getRenderKitGeneralAdapter());

							cmd = AddCommand.create(editingDomain, Renderkit,
									FacesConfigPackage.eINSTANCE
											.getRenderKitType_Description(),
									description);
						}
						if (cmd.canExecute()) {
							editingDomain.getCommandStack().execute(cmd);
						}
					}
				});

		renderkitIdField = new StringDialogField();
		// renderkitIdField.setRequired(true);
		renderkitIdField
				.setLabelText(NewEditorResourcesNLS.RenderKitGeneralSection_Label_RenderKitID);
		renderkitIdField.doFillIntoGrid(toolkit, container, numberOfColumns);
		renderkitIdField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						RenderKitIdType RenderkitId = FacesConfigFactory.eINSTANCE
								.createRenderKitIdType();
						RenderkitId.setTextContent(((StringDialogField) field)
								.getText());

						EditingDomain editingDomain = getEditingDomain();
						if (editingDomain != null) {
							Command command = SetCommand.create(editingDomain,
									getInput(), FacesConfigPackage.eINSTANCE
											.getRenderKitType_RenderKitId(),
									RenderkitId);
							if (command.canExecute()) {
								editingDomain.getCommandStack()
										.execute(command);
							}
						}
					}
				});

		renderkitClassField = new ClassButtonDialogField(null);
		// renderkitClassField.setRequired(true);
		renderkitClassField
				.setLabelText(NewEditorResourcesNLS.RenderKitGeneralSection_Label_RenderKitClass);
		renderkitClassField.doFillIntoGrid(toolkit, container, numberOfColumns);
		renderkitClassField.setProject((IProject) getPage().getEditor()
				.getAdapter(IProject.class));
		LayoutUtil.setHorizontalGrabbing(renderkitClassField.getTextControl(
				toolkit, container));

		renderkitClassField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {

						RenderKitClassType RenderkitClass = FacesConfigFactory.eINSTANCE
								.createRenderKitClassType();
						RenderkitClass
								.setTextContent(((StringDialogField) field)
										.getText());
						EditingDomain editingDomain = getEditingDomain();
						if (editingDomain != null) {
							Command command = SetCommand.create(editingDomain,
									getInput(), FacesConfigPackage.eINSTANCE
											.getRenderKitType_RenderKitClass(),
									RenderkitClass);
							if (command.canExecute()) {
								editingDomain.getCommandStack()
										.execute(command);
							}
						}

					}
				});
		renderkitClassField
				.setSuperClassName(IFacesConfigConstants.RENDER_KIT_SUPER_CLASS);
	}

	/**
	 * 
	 */
	public void refreshAll() {
		refresh();
	}

	/**
	 * 
	 */
	public void refresh() {
		super.refresh();
		Object input = this.getInput();
		if (input instanceof RenderKitType) {
			RenderKitType Renderkit = (RenderKitType) input;

			if (Renderkit.getDisplayName().size() > 0) {
				DisplayNameType displayName = (DisplayNameType) Renderkit
						.getDisplayName().get(0);
				displayNameField.setTextWithoutUpdate(displayName
						.getTextContent());
			} else {
				displayNameField.setTextWithoutUpdate("");
			}

			if (Renderkit.getDescription().size() > 0) {
				DescriptionType description = (DescriptionType) Renderkit
						.getDescription().get(0);
				String descriptionString = description.getTextContent();
				descriptionString = ModelUtil
						.unEscapeEntities(descriptionString);
				descriptionField.setTextWithoutUpdate(descriptionString);
			} else {
				descriptionField.setTextWithoutUpdate("");
			}

			if (Renderkit.getRenderKitId() != null) {
				renderkitIdField.setTextWithoutUpdate(Renderkit
						.getRenderKitId().getTextContent());
			} else {
				renderkitIdField.setTextWithoutUpdate("");
			}

			if (Renderkit.getRenderKitClass() != null) {
				renderkitClassField.setTextWithoutUpdate(Renderkit
						.getRenderKitClass().getTextContent());
			} else {
				renderkitClassField.setTextWithoutUpdate("");
			}
		}
	}

	/**
	 * 
	 */
	public void clearAll() {
		// TODO Auto-generated method stub

	}

	/**
	 * Add RenderKitGeneralAdapter onto <render-kit> and the first
	 * <display-name> and <description> elements.
	 */
	protected void addAdaptersOntoInput(Object newInput) {
		RenderKitType renderkit = (RenderKitType) newInput;
		if (EcoreUtil.getExistingAdapter(renderkit,
				RenderkitGeneralSection.class) == null) {

			renderkit.eAdapters().add(getRenderKitGeneralAdapter());
		}

		if (renderkit.getDisplayName().size() > 0) {
			DisplayNameType displayName = (DisplayNameType) renderkit
					.getDisplayName().get(0);
			if (EcoreUtil.getExistingAdapter(displayName,
					RenderkitGeneralSection.class) == null) {

				displayName.eAdapters().add(getRenderKitGeneralAdapter());
			}
		}

		if (renderkit.getDescription().size() > 0) {
			DescriptionType description = (DescriptionType) renderkit
					.getDescription().get(0);
			if (EcoreUtil.getExistingAdapter(description,
					RenderkitGeneralSection.class) == null) {

				description.eAdapters().add(getRenderKitGeneralAdapter());
			}
		}
	}

	/**
	 * Remove RenderKitGeneralAdapter from <render-kit> and the first
	 * <display-name> and <description> elements.
	 */
	protected void removeAdaptersFromInput(Object oldInput) {
		RenderKitType renderkit = (RenderKitType) oldInput;
		if (EcoreUtil.getExistingAdapter(renderkit,
				RenderkitGeneralSection.class) != null) {
			renderkit.eAdapters().remove(getRenderKitGeneralAdapter());
		}
		if (renderkit.getDisplayName().size() > 0) {
			DisplayNameType displayName = (DisplayNameType) renderkit
					.getDisplayName().get(0);
			if (EcoreUtil.getExistingAdapter(displayName,
					RenderkitGeneralSection.class) != null) {

				displayName.eAdapters().remove(getRenderKitGeneralAdapter());
			}
		}

		if (renderkit.getDescription().size() > 0) {
			DescriptionType description = (DescriptionType) renderkit
					.getDescription().get(0);
			if (EcoreUtil.getExistingAdapter(description,
					RenderkitGeneralSection.class) != null) {

				description.eAdapters().remove(getRenderKitGeneralAdapter());
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private Adapter getRenderKitGeneralAdapter() {

		if (renderKitGeneralAdapter == null) {
			renderKitGeneralAdapter = new RenderKitGeneralAdapter();
		}
		return renderKitGeneralAdapter;
	}

	/**
	 * The adapter that will be added onto <render-kit> element, to listen the
	 * events of the children that are displayed in this section, notify the
	 * section to refresh.
	 * 
	 * @author sfshi
	 * 
	 */
	class RenderKitGeneralAdapter extends AdapterImpl {

		public boolean isAdapterForType(Object type) {
			if (type == RenderkitGeneralSection.class)
				return true;
			return false;
		}

		public void notifyChanged(Notification msg) {

			if (msg.getEventType() == Notification.ADD
					|| msg.getEventType() == Notification.REMOVE
					|| msg.getEventType() == Notification.SET) {
				if (msg.getFeature() == FacesConfigPackage.eINSTANCE
						.getRenderKitType_RenderKitId()
						|| msg.getFeature() == FacesConfigPackage.eINSTANCE
								.getRenderKitType_RenderKitClass()
						|| msg.getFeature() == FacesConfigPackage.eINSTANCE
								.getRenderKitType_Description()
						|| msg.getFeature() == FacesConfigPackage.eINSTANCE
								.getRenderKitType_DisplayName()) {
					refresh();
				} else if (msg.getFeature() == FacesConfigPackage.eINSTANCE
						.getDisplayNameType_TextContent()
						|| msg.getFeature() == FacesConfigPackage.eINSTANCE
								.getDescriptionType_TextContent()) {
					refresh();
				}
			}
		}
	}
}
