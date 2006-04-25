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
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jst.jsf.facesconfig.ui.dialog.DialogUtil;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.OthersPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wtp.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.wtp.jsf.facesconfig.emf.DefaultLocaleType;
import org.eclipse.wtp.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.wtp.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.wtp.jsf.facesconfig.emf.LocaleConfigType;
import org.eclipse.wtp.jsf.facesconfig.emf.SupportedLocaleType;

/**
 * @author Zhi-peng Zhang, sfshi
 * @version
 */
public class LocaleConfigSection extends ApplicationSection implements
		ICheckStateListener {
	/**
	 * 
	 * @param componentClass
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 * @param helpContextId
	 * @param helpTooltip
	 */
	public LocaleConfigSection(EClass componentClass, Composite parent,
			IManagedForm managedForm, IFacesConfigPage page,
			FormToolkit toolkit, String helpContextId, String helpTooltip) {
		super(componentClass, parent, managedForm, page, toolkit,
				helpContextId, helpTooltip);
	}

	/**
	 * 
	 * @param componentClass
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 */
	public LocaleConfigSection(EClass componentClass, Composite parent,
			IManagedForm managedForm, IFacesConfigPage page, FormToolkit toolkit) {
		this(componentClass, parent, managedForm, page, toolkit, null, null);
	}

	/**
	 * create a CheckboxTableViewer for this section.
	 */
	protected TableViewer createTableViewer(Composite parent) {
		CheckboxTableViewer tableViewer = CheckboxTableViewer.newCheckList(
				parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		tableViewer.addCheckStateListener(this);
		tableViewer.setSorter(new ViewerSorter());
		return tableViewer;
	}

	/**
	 * Add a filter for this table viewer, only <supported-locale> and
	 * <default-locale> elements would be listed.
	 */
	protected void configTableViewer(TableViewer tableViewer) {
		tableViewer.addFilter(new ViewerFilter() {
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return FacesConfigPackage.eINSTANCE.getSupportedLocaleType()
						.isInstance(element)
						|| FacesConfigPackage.eINSTANCE.getDefaultLocaleType()
								.isInstance(element);
			}
		});
	}

	/**
	 * Pop up a dialog for user to select a locale string, then create a
	 * <supported-locale> element with the string as value.
	 * 
	 * @return
	 */
	public SupportedLocaleType createSupportedLocaleObject() {
		ArrayList locales = new ArrayList();

		/**
		 * Compute the locales that already declared in this <locale-config>
		 * element. If user select a locale that already declared, then a
		 * warning message will be shown in the dialog.
		 */

		if (getInput() instanceof ApplicationType
				&& ((ApplicationType) getInput()).getLocaleConfig().size() > 0) {
			LocaleConfigType localeConfigType = (LocaleConfigType) ((ApplicationType) getInput())
					.getLocaleConfig().get(0);
			if (localeConfigType.getDefaultLocale() != null) {
				String locale = localeConfigType.getDefaultLocale()
						.getTextContent();
				if (locale != null) {
					locales.add(locale);
				}
			}
			List list = localeConfigType.getSupportedLocale();
			for (int i = 0, n = list.size(); i < n; i++) {
				String locale = ((SupportedLocaleType) list.get(i))
						.getTextContent();
				if (locale != null) {
					locales.add(locale);
				}
			}
		}
		String result = DialogUtil.openLocaleDialog(getSection().getShell(),
				locales);
		if (result != null) {
			SupportedLocaleType component = FacesConfigFactory.eINSTANCE
					.createSupportedLocaleType();
			component.setTextContent(result);
			return component;
		}
		return null;
	}

	/**
	 * When user click "Add" button on this section, pop up a dialog to create a
	 * <supported-locale> element, then add it onto <locale-config> element, if
	 * the <locale-config> or <application> doesn't exist, create them together.
	 */
	void addButtonSelected(SelectionEvent e) {
		SupportedLocaleType obj = createSupportedLocaleObject();
		if (obj != null) {

			boolean needRefreshAll = false;
			Command command = null;

			if (getInput() instanceof ApplicationType) {
				/** the <application> element exists. */
				ApplicationType application = (ApplicationType) getInput();
				if (application.getLocaleConfig().size() > 0) {
					/** the <locale-config> element exists. */
					LocaleConfigType localeConfig = (LocaleConfigType) application
							.getLocaleConfig().get(0);
					command = AddCommand.create(this.getEditingDomain(),
							localeConfig, null, obj);
				} else {
					/** the <locale-config> element doesn't exist, create it. */
					LocaleConfigType localeConfig = FacesConfigFactory.eINSTANCE
							.createLocaleConfigType();
					localeConfig.getSupportedLocale().add(obj);
					command = AddCommand.create(getEditingDomain(),
							application, null, localeConfig);
				}
			} else {
				/**
				 * the <application> element doesn't exist, then create it,
				 * after execute, reset the input for all the application
				 * sections.
				 */
				needRefreshAll = true;
				ApplicationType application = FacesConfigFactory.eINSTANCE
						.createApplicationType();
				LocaleConfigType localeConfig = FacesConfigFactory.eINSTANCE
						.createLocaleConfigType();
				localeConfig.getSupportedLocale().add(obj);
				application.getLocaleConfig().add(localeConfig);
				command = AddCommand.create(getEditingDomain(), this.getPage()
						.getInput(), null, application);
			}

			if (command.canExecute()) {
				getEditingDomain().getCommandStack().execute(command);
				if (needRefreshAll)
					/** reset input for all the application sections. */
					((OthersPage) this.getPage()).resetApplicationInput();
			}
		}

	}

	/**
	 * when user click on the CheckboxTableViewer, perform some actions to
	 * set/unset <default-locale> and <supported-locale> elements.
	 * 
	 * If an item is selected, that means it's a <default-locale>, otherwise
	 * it's a <supported-locale>.
	 * 
	 * @see org.eclipse.jface.viewers.ICheckStateListener#checkStateChanged(org.eclipse.jface.viewers.CheckStateChangedEvent)
	 */
	public void checkStateChanged(CheckStateChangedEvent event) {
		boolean checked = event.getChecked();

		if (event.getElement() == null) {
			return;
		}

		EObject node = (EObject) event.getElement();
		LocaleConfigType localeConfigType = (LocaleConfigType) ((ApplicationType) getInput())
				.getLocaleConfig().get(0);
		CompoundCommand compoundCommand = new CompoundCommand();

		if (checked) {
			/** user checked one item, going to set it as <default-locale>. */
			if (localeConfigType.getDefaultLocale() != null) {
				/**
				 * Change the <default-locale> value to <supported-locale>.
				 */
				Command command = RemoveCommand.create(getEditingDomain(),
						localeConfigType, null, localeConfigType
								.getDefaultLocale());

				compoundCommand.append(command);

				SupportedLocaleType supportedLocale = FacesConfigFactory.eINSTANCE
						.createSupportedLocaleType();
				supportedLocale.setTextContent(localeConfigType
						.getDefaultLocale().getTextContent());
				command = AddCommand.create(getEditingDomain(),
						localeConfigType, FacesConfigPackage.eINSTANCE
								.getLocaleConfigType_SupportedLocale(),
						supportedLocale);
				compoundCommand.append(command);
			}

			/**
			 * Change the selected item from <supported-locale> to
			 * <default-locale>
			 */
			SupportedLocaleType supportedLocale = (SupportedLocaleType) node;
			Command command = RemoveCommand.create(getEditingDomain(),
					localeConfigType, FacesConfigPackage.eINSTANCE
							.getLocaleConfigType_SupportedLocale(),
					supportedLocale);
			compoundCommand.append(command);

			DefaultLocaleType defaultLocale = FacesConfigFactory.eINSTANCE
					.createDefaultLocaleType();
			defaultLocale.setTextContent(supportedLocale.getTextContent());
			command = SetCommand
					.create(getEditingDomain(), localeConfigType,
							FacesConfigPackage.eINSTANCE
									.getLocaleConfigType_DefaultLocale(),
							defaultLocale);
			compoundCommand.append(command);

		} else {
			/**
			 * User unchecked one item, going to change it from <default-locale>
			 * to <supported-locale>.
			 */
			DefaultLocaleType defaultLocale = (DefaultLocaleType) node;
			Command command = RemoveCommand.create(getEditingDomain(),
					localeConfigType, null, defaultLocale);
			compoundCommand.append(command);

			SupportedLocaleType supportedLocale = FacesConfigFactory.eINSTANCE
					.createSupportedLocaleType();
			supportedLocale.setTextContent(defaultLocale.getTextContent());
			command = AddCommand.create(getEditingDomain(), localeConfigType,
					null, supportedLocale);
			compoundCommand.append(command);
		}

		if (compoundCommand.canExecute()) {
			getEditingDomain().getCommandStack().execute(compoundCommand);
			this.refresh();
			setTableViewerCheckedState();
		}
	}

	/**
	 * Set the checked state for items in the checkbox table viewer. This method
	 * could be used for refresh the checked state of the table viewer.
	 */
	private void setTableViewerCheckedState() {
		if (getInput() instanceof ApplicationType) {
			ApplicationType application = (ApplicationType) getInput();
			if (application.getLocaleConfig().size() > 0) {

				LocaleConfigType localeConfigType = (LocaleConfigType) application
						.getLocaleConfig().get(0);
				if (localeConfigType.getDefaultLocale() != null) {
					((CheckboxTableViewer) getTableViewer()).setChecked(
							localeConfigType.getDefaultLocale(), true);
				}
			}
		}
	}

	/**
	 * set the structuredViewer's input. Set the first LocaleConfig as input.
	 * 
	 * @param input
	 */
	protected void setViewerInput(Object input) {
		if (input instanceof ApplicationType) {
			ApplicationType application = (ApplicationType) input;
			if (EcoreUtil.getExistingAdapter(application,
					LocaleConfigSection.class) == null) {
				application.eAdapters().add(new LocaleConfigAdapter());
			}
			if (application.getLocaleConfig().size() > 0) {
				tableViewer.setInput(application.getLocaleConfig().get(0));
			} else
				tableViewer.setInput(null);
		} else
			tableViewer.setInput(null);

	}

	public void refreshAll() {
		super.refreshAll();
		setTableViewerCheckedState();
	}

	/**
	 * A listener that is be adapted on the input <application> element, to
	 * listen the event that the first <locale-config> being created and
	 * removed, then reset the input of the tabel viewer.
	 * 
	 * @author sfshi
	 * 
	 */
	class LocaleConfigAdapter extends AdapterImpl {
		public boolean isAdapterForType(Object type) {
			if (type == LocaleConfigSection.class)
				return true;
			return false;
		}

		public void notifyChanged(Notification msg) {
			if (msg.getEventType() == Notification.ADD
					|| msg.getEventType() == Notification.REMOVE) {
				if (msg.getFeature() == FacesConfigPackage.eINSTANCE
						.getApplicationType_LocaleConfig()) {
					/**
					 * a <locale-config> was created or removed, notify this
					 * section to reset it's input.
					 */
					setInput(getInput());

				}
			}
		}

	}
}
