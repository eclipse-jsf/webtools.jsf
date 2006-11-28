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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogFieldApplyListener;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.RadiosDialogField;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.IFacesConfigConstants;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.PageBook;

/**
 * This section handles managed bean's initialization, including general class
 * type, map and list type managed bean.
 * 
 * @author Xiao-guang Zhang, sfshi
 */
public class InitializationSection extends AbstractFacesConfigSection {
	private static final String MANAGEDBEAN_GENERAL_CLASS = EditorMessages.InitializationSection_ClassType_General;

	private static final String MANAGEDBEAN_MAP = EditorMessages.InitializationSection_ClassType_Map;

	private static final String MANAGEDBEAN_LIST = EditorMessages.InitializationSection_ClassType_List;

	/** property initiliazation container composite */
	private Composite propertySection;

	/** property initiliazation DialogFieldGroup */
	private ManagedPropertyEditGroup managedPropertyGroup;

	/** map entries initiliazation container composite */
	private Composite mapSection;

	/** map entries initiliazation DialogFieldGroup */
	private MapEntriesEditGroup mapEntryGroup;

	/** list entries initiliazation container composite */
	private Composite listSection;

	/** list entries initiliazation DialogFieldGroup */
	private ListEntriesEditGroup listEntryGroup;

	/** the PageBook control for three kinds of managed bean */
	private PageBook pageBook;

	/** RadiosDialogField for three kinds of managed bean */
	private RadiosDialogField beanTypeField;

	/** current pages selection */
	private int currentPageIndex = 0;

	/**
	 * @param managedForm
	 * @param toolkit
	 * @param editor
	 */
	public InitializationSection(Composite parent, IManagedForm managedForm,
			IFacesConfigPage page, FormToolkit toolkit) {
		super(parent, managedForm, page, toolkit, null, null); //$NON-NLS-1$
		getSection().setText(EditorMessages.InitializationSection_Name); //$NON-NLS-1$
		getSection().setDescription(
				EditorMessages.InitializationSection_Description); //$NON-NLS-1$

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.managedbean.sections.BaseSectionPart#createClient(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	protected void createContents(Composite container, FormToolkit toolkit) {
		int numberOfColumns = 3;
		GridLayout gl = new GridLayout(numberOfColumns, false);
		gl.horizontalSpacing = 0;
		gl.marginWidth = 0;
		container.setLayout(gl);
		toolkit.paintBordersFor(container);

		createBeanTypeEntry(container, toolkit, numberOfColumns);
		createInitializationEntry(container, toolkit, numberOfColumns);
	}

	/**
	 * create the bean type selection section
	 * 
	 * @param container
	 * @param toolkit
	 */
	private void createBeanTypeEntry(Composite container, FormToolkit toolkit,
			int numberOfColumns) {
		beanTypeField = new RadiosDialogField();
		String[] items = { MANAGEDBEAN_GENERAL_CLASS, MANAGEDBEAN_MAP,
				MANAGEDBEAN_LIST };
		beanTypeField
				.setLabelText(EditorMessages.InitializationSection_ClassType);
		beanTypeField.setItems(items);
		beanTypeField.doFillIntoGrid(toolkit, container, numberOfColumns);
		beanTypeField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						RadiosDialogField beanTypeField1 = (RadiosDialogField) field;

						boolean bChangedSuccess = false;
						if (beanTypeField1.getSelectedIndex() == 0) {
							bChangedSuccess = switchPage(
									IFacesConfigConstants.MANAGED_PROPERTY,
									false);
						} else if (beanTypeField1.getSelectedIndex() == 1) {
							bChangedSuccess = switchPage(
									IFacesConfigConstants.MAP_ENTRIES, false);
						} else if (beanTypeField1.getSelectedIndex() == 2) {
							bChangedSuccess = switchPage(
									IFacesConfigConstants.LIST_ENTRIES, false);
						}

						if (bChangedSuccess) {
							currentPageIndex = beanTypeField1.getSelectedIndex();
						} else {
							beanTypeField1.setSelectedIndexWithoutUpdate(-1);
							beanTypeField1
									.setSelectedIndexWithoutUpdate(currentPageIndex);
						}
					}
				});
	}

	/**
	 * switch to general property, map-entries, or list-entries group If
	 * bForceClear equals to true, the others page will be cleared without user
	 * confirmation. if bForceClear equals to false, the confirmation dialog
	 * will pop up and let user determine whether this page is cleared or not.
	 * 
	 * @param pageID -
	 *            IFacesConfigConstants.MANAGED_PROPERTY,
	 *            MAP_ENTRIES,LIST_ENTRIES
	 * @param bForceClearOthers -
	 *            Force to clear other pages.
	 */
	private boolean switchPage(String pageID, boolean bForceClearOthers) {
		if (pageID == IFacesConfigConstants.MANAGED_PROPERTY) {
			if (clearPage(IFacesConfigConstants.MAP_ENTRIES, bForceClearOthers)
					&& clearPage(IFacesConfigConstants.LIST_ENTRIES,
							bForceClearOthers)) {
				managedPropertyGroup.refreshAll();
				pageBook.showPage(propertySection);
				// FIXME: there should be some other methods to resize the whole
				// section.
				InitializationSection.this.getSection().setExpanded(false);
				InitializationSection.this.getSection().setExpanded(true);
				return true;
			}
		} else if (pageID == IFacesConfigConstants.MAP_ENTRIES) {
			if (clearPage(IFacesConfigConstants.MANAGED_PROPERTY,
					bForceClearOthers)
					&& clearPage(IFacesConfigConstants.LIST_ENTRIES,
							bForceClearOthers)) {
				mapEntryGroup.refreshAll();
				pageBook.showPage(mapSection);
				// FIXME: there should be some other methods to resize the whole
				// section.
				InitializationSection.this.getSection().setExpanded(false);
				InitializationSection.this.getSection().setExpanded(true);
				return true;
			}
		} else if (pageID == IFacesConfigConstants.LIST_ENTRIES) {
			if (clearPage(IFacesConfigConstants.MANAGED_PROPERTY,
					bForceClearOthers)
					&& clearPage(IFacesConfigConstants.MAP_ENTRIES,
							bForceClearOthers)) {
				listEntryGroup.refreshAll();
				pageBook.showPage(listSection);
				// FIXME: there should be some other methods to resize the whole
				// section.
				InitializationSection.this.getSection().setExpanded(false);
				InitializationSection.this.getSection().setExpanded(true);
				return true;
			}
		}
		return false;
	}

	/**
	 * clear one specified page according to pageID if bForceClear equals to
	 * false, the confirmation dialog will pop up and let user determine whether
	 * this page is cleared or not.
	 * 
	 * @param pageID -
	 *            IFacesConfigConstants.MANAGED_PROPERTY,
	 *            MAP_ENTRIES,LIST_ENTRIES
	 * @param bForceClear -
	 *            force to clear current page.
	 * @return
	 */
	private boolean clearPage(String pageID, boolean bForceClear) {
		ManagedBeanType managedBean = (ManagedBeanType) getInput();

		if (pageID == IFacesConfigConstants.MANAGED_PROPERTY) {
			if (managedBean.getManagedProperty().size() == 0) {
				return true;
			}

			if (bForceClear
					|| EditorPlugin
							.getAlerts()
							.confirm(
									"ManagedBeanInitializationSection.Remove.Title",
									"ManagedBeanInitializationSection.RemoveManagedProperty")) {
				Command cmd = SetCommand.create(this.getEditingDomain(),
						managedBean, FacesConfigPackage.eINSTANCE
								.getManagedBeanType_ManagedProperty(),
						SetCommand.UNSET_VALUE);
				if (cmd.canExecute()) {
					getEditingDomain().getCommandStack().execute(cmd);
					return true;
				}

				return false;
			}
		} else if (pageID == IFacesConfigConstants.MAP_ENTRIES) {
			if (managedBean.getMapEntries() == null) {
				return true;
			}

			if (bForceClear
					|| EditorPlugin
							.getAlerts()
							.confirm(
									"ManagedBeanInitializationSection.Remove.Title",
									"ManagedBeanInitializationSection.RemoveMapEntries")) {
				Command cmd = SetCommand.create(this.getEditingDomain(),
						managedBean, FacesConfigPackage.eINSTANCE
								.getManagedBeanType_MapEntries(),
						SetCommand.UNSET_VALUE);
				if (cmd.canExecute()) {
					getEditingDomain().getCommandStack().execute(cmd);
					return true;
				}
			}
		} else if (pageID == IFacesConfigConstants.LIST_ENTRIES) {
			if (managedBean.getListEntries() == null) {
				return true;
			}

			if (bForceClear
					|| EditorPlugin
							.getAlerts()
							.confirm(
									"ManagedBeanInitializationSection.Remove.Title",
									"ManagedBeanInitializationSection.RemoveListEntries")) {
				Command cmd = SetCommand.create(this.getEditingDomain(),
						managedBean, FacesConfigPackage.eINSTANCE
								.getManagedBeanType_ListEntries(),
						SetCommand.UNSET_VALUE);
				if (cmd.canExecute()) {
					getEditingDomain().getCommandStack().execute(cmd);
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * create property initialization section
	 * 
	 * @param container
	 * @param toolkit
	 */
	private void createInitializationEntry(Composite container,
			FormToolkit toolkit, int numberOfColumns) {
		pageBook = new PageBook(container, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = numberOfColumns;
		pageBook.setLayoutData(gd);
		toolkit.paintBordersFor(pageBook);

		managedPropertyGroup = new ManagedPropertyEditGroup(this);
		managedPropertyGroup.initialize();
		managedPropertyGroup.addSelectionChangedListener(this);
		
		mapEntryGroup = new MapEntriesEditGroup(this);
		mapEntryGroup.initialize();
		mapEntryGroup.addSelectionChangedListener(this);
		
		listEntryGroup = new ListEntriesEditGroup(this);
		listEntryGroup.initialize();
		listEntryGroup.addSelectionChangedListener(this);
		
		propertySection = toolkit.createComposite(pageBook);
		GridLayout gl = new GridLayout();
		gl.horizontalSpacing = 0;
		gl.marginWidth = 0;
		propertySection.setLayout(gl);
		gd = new GridData(GridData.FILL_BOTH);
		propertySection.setLayoutData(gd);
		managedPropertyGroup.layoutDialogFields(toolkit, propertySection);
		
		mapSection = toolkit.createComposite(pageBook);
		mapEntryGroup.layoutDialogFields(toolkit, mapSection);
		
		listSection = toolkit.createComposite(pageBook);
		listEntryGroup.layoutDialogFields(toolkit, listSection);
		
		pageBook.showPage(propertySection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.managedbean.sections.ISSESection#setInput(java.lang.Object)
	 */
	public void setInput(Object newInput) {
		super.setInput(newInput);
		refreshAll();
	}

	public void refreshAll() {
		if (getInput() instanceof ManagedBeanType) {
			ManagedBeanType managedBean = (ManagedBeanType) getInput();
			managedPropertyGroup.setInput(managedBean);
			mapEntryGroup.setInput(managedBean);
			listEntryGroup.setInput(managedBean);
			if (managedBean.getManagedProperty().size() > 0) {
				beanTypeField.setSelectedIndexWithoutUpdate(-1);
				beanTypeField.setSelectedIndex(0);
			} else if (managedBean.getMapEntries() != null) {
				beanTypeField.setSelectedIndexWithoutUpdate(-1);
				beanTypeField.setSelectedIndex(1);
			} else if (managedBean.getListEntries() != null) {
				beanTypeField.setSelectedIndexWithoutUpdate(-1);
				beanTypeField.setSelectedIndex(2);
			} else {
				beanTypeField.setSelectedIndexWithoutUpdate(-1);
				beanTypeField.setSelectedIndex(0);
			}
		}
	}
}
