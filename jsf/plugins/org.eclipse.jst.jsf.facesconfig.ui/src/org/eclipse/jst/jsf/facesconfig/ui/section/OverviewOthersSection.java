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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleType;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.OthersPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author sfshi
 * 
 */
public class OverviewOthersSection extends AbstractOverviewSection {

	private static final int COLUMN_WITH = 100;

	private OverviewOthersSectionAdapter overviewOthersSectionAdapter;

	/**
	 * 
	 * @param parent
	 * @param managedForm
	 * @param page
	 * @param toolkit
	 */
	public OverviewOthersSection(Composite parent, IManagedForm managedForm,
			IFacesConfigPage page, FormToolkit toolkit) {
		super(parent, managedForm, page, toolkit, OthersPage.PAGE_ID,
				EditorMessages.OverviewPage_OthersSection_name,
				EditorMessages.OverviewPage_OthersSection_description,
				null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.section.AbstractOverviewSection#configTableViewer(org.eclipse.jface.viewers.TableViewer)
	 */
	protected void configTableViewer(TableViewer tableViewer1) {
		tableViewer1.setContentProvider(new IStructuredContentProvider() {

			public Object[] getElements(Object inputElement) {
				List othersNodesList = (List) inputElement;
				return othersNodesList.toArray();
			}

			public void dispose() {
                // do nothing
			}

			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
			    // do nothing
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.section.AbstractOverviewSection#createTable(org.eclipse.swt.widgets.Composite)
	 */
	protected Table createTable(Composite container) {
		Table table = new Table(container, SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION | SWT.BORDER);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 1;
		gd.heightHint = 100;
		table.setLayoutData(gd);

		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		TableLayout tablelayout = new TableLayout();
		table.setLayout(tablelayout);

		TableColumn valuecol = new TableColumn(table, SWT.NONE);
		tablelayout.addColumnData(new ColumnWeightData(1, COLUMN_WITH, true));
		valuecol
				.setText(EditorMessages.OverviewPage_OthersSection_table_valuecol);
		valuecol.setResizable(true);

		TableColumn typecol = new TableColumn(table, SWT.NONE);
		tablelayout.addColumnData(new ColumnWeightData(1, COLUMN_WITH, true));
		typecol
				.setText(EditorMessages.OverviewPage_OthersSection_table_typecol);
		typecol.setResizable(true);

		return table;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.section.AbstractOverviewSection#refreshAll()
	 */
	public void refreshAll() {

		List othersNodesList = new ArrayList();
		if (getInput() instanceof FacesConfigType) {
			Object[] applicationNodes = null;
			Object[] factoryNodes = null;
			Object[] lifecycleNodes = null;
			FacesConfigType facesConfig = (FacesConfigType) getInput();
			IStructuredContentProvider contentProvider = new AdapterFactoryContentProvider(
					getAdapterFactory());
			if (facesConfig.getApplication().size() > 0) {

				ApplicationType application = (ApplicationType) facesConfig
						.getApplication().get(0);
				applicationNodes = contentProvider.getElements(application);

			}

			if (facesConfig.getFactory().size() > 0) {
				FactoryType factory = (FactoryType) facesConfig.getFactory()
						.get(0);
				factoryNodes = contentProvider.getElements(factory);
			}

			if (facesConfig.getLifecycle().size() > 0) {
				LifecycleType lifecycle = (LifecycleType) facesConfig
						.getLifecycle().get(0);
				lifecycleNodes = contentProvider.getElements(lifecycle);
			}

			if (applicationNodes != null) {
				for (int i = 0, n = applicationNodes.length; i < n; i++) {
					othersNodesList.add(applicationNodes[i]);
				}
			}

			if (factoryNodes != null) {
				for (int i = 0, n = factoryNodes.length; i < n; i++) {
					othersNodesList.add(factoryNodes[i]);
				}
			}

			if (lifecycleNodes != null) {
				for (int i = 0, n = lifecycleNodes.length; i < n; i++) {
					othersNodesList.add(lifecycleNodes[i]);
				}
			}
		}

		tableViewer.setInput(othersNodesList);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.section.AbstractFacesConfigSection#addAdaptersOntoInput(java.lang.Object)
	 */
	protected void addAdaptersOntoInput(Object newInput) {
		FacesConfigType facesConfig = (FacesConfigType) newInput;
		addOverviewOthersSectionAdapter(facesConfig);
		IStructuredContentProvider contentProvider = new AdapterFactoryContentProvider(
				getAdapterFactory());
		if (facesConfig.getApplication().size() > 0) {
			ApplicationType application = (ApplicationType) facesConfig
					.getApplication().get(0);
			addOverviewOthersSectionAdapter(application);

			Object[] applicationNodes = contentProvider
					.getElements(application);
			for (int i = 0, n = applicationNodes.length; i < n; i++) {
				addOverviewOthersSectionAdapter((EObject) applicationNodes[i]);
			}

		}

		if (facesConfig.getFactory().size() > 0) {
			FactoryType factory = (FactoryType) facesConfig.getFactory().get(0);
			addOverviewOthersSectionAdapter(factory);

			Object[] factoryNodes = contentProvider.getElements(factory);
			for (int i = 0, n = factoryNodes.length; i < n; i++) {
				addOverviewOthersSectionAdapter((EObject) factoryNodes[i]);
			}

		}

		if (facesConfig.getLifecycle().size() > 0) {
			LifecycleType lifecycle = (LifecycleType) facesConfig
					.getLifecycle().get(0);
			addOverviewOthersSectionAdapter(lifecycle);

			Object[] lifecycleNodes = contentProvider.getElements(lifecycle);
			for (int i = 0, n = lifecycleNodes.length; i < n; i++) {
				addOverviewOthersSectionAdapter((EObject) lifecycleNodes[i]);
			}

		}

	}

	private void addOverviewOthersSectionAdapter(EObject object) {
		if (EcoreUtil.getExistingAdapter(object, OverviewOthersSection.class) == null) {
			object.eAdapters().add(getOverviewOthersSectionAdapter());
		}

	}

	private void removeOverviewOthersSectionAdapter(EObject object) {
		if (EcoreUtil.getExistingAdapter(object, OverviewOthersSection.class) != null) {
			object.eAdapters().remove(getOverviewOthersSectionAdapter());
		}
	}

	private OverviewOthersSectionAdapter getOverviewOthersSectionAdapter() {
		if (overviewOthersSectionAdapter == null) {
			overviewOthersSectionAdapter = new OverviewOthersSectionAdapter();
		}

		return overviewOthersSectionAdapter;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.section.AbstractFacesConfigSection#removeAdaptersFromInput(java.lang.Object)
	 */
	protected void removeAdaptersFromInput(Object oldInput) {

		FacesConfigType facesConfig = (FacesConfigType) oldInput;
		removeOverviewOthersSectionAdapter(facesConfig);
		IStructuredContentProvider contentProvider = new AdapterFactoryContentProvider(
				getAdapterFactory());
		if (facesConfig.getApplication().size() > 0) {
			ApplicationType application = (ApplicationType) facesConfig
					.getApplication().get(0);
			removeOverviewOthersSectionAdapter(application);

			Object[] applicationNodes = contentProvider
					.getElements(application);
			for (int i = 0, n = applicationNodes.length; i < n; i++) {
				removeOverviewOthersSectionAdapter((EObject) applicationNodes[i]);
			}

		}

		if (facesConfig.getFactory().size() > 0) {
			FactoryType factory = (FactoryType) facesConfig.getFactory().get(0);
			removeOverviewOthersSectionAdapter(factory);

			Object[] factoryNodes = contentProvider.getElements(factory);
			for (int i = 0, n = factoryNodes.length; i < n; i++) {
				removeOverviewOthersSectionAdapter((EObject) factoryNodes[i]);
			}

		}

		if (facesConfig.getLifecycle().size() > 0) {
			LifecycleType lifecycle = (LifecycleType) facesConfig
					.getLifecycle().get(0);
			removeOverviewOthersSectionAdapter(lifecycle);

			Object[] lifecycleNodes = contentProvider.getElements(lifecycle);
			for (int i = 0, n = lifecycleNodes.length; i < n; i++) {
				removeOverviewOthersSectionAdapter((EObject) lifecycleNodes[i]);
			}

		}

	}

	class OverviewOthersSectionAdapter extends AdapterImpl {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#isAdapterForType(java.lang.Object)
		 */
		public boolean isAdapterForType(Object type) {
			if (type == OverviewOthersSection.class)
				return true;
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		public void notifyChanged(Notification msg) {
			if (msg.getFeature() == FacesConfigPackage.eINSTANCE
					.getFacesConfigType_Application()
					|| msg.getFeature() == FacesConfigPackage.eINSTANCE
							.getFacesConfigType_Factory()
					|| msg.getFeature() == FacesConfigPackage.eINSTANCE
							.getFacesConfigType_Lifecycle()
					|| msg.getNotifier() instanceof ApplicationType
					|| msg.getNotifier() instanceof FactoryType
					|| msg.getNotifier() instanceof LifecycleType) {

				if (msg.getEventType() == Notification.ADD) {
					EObject newObject = (EObject) msg.getNewValue();
					if (newObject != null) {
						addOverviewOthersSectionAdapter(newObject);
					}
				}

				if (msg.getEventType() == Notification.ADD
						|| msg.getEventType() == Notification.REMOVE
						|| msg.getEventType() == Notification.SET) {
					refreshAll();
				}
			}

		}
	}
}
