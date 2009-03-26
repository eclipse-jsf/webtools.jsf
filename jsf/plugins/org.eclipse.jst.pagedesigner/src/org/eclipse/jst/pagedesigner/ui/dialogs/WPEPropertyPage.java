/*******************************************************************************
 * Copyright (c) 2009 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.ui.dialogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dtresourceprovider.DTResourceProviderFactory;
import org.eclipse.jst.pagedesigner.dtresourceprovider.DTSkinManager;
import org.eclipse.jst.pagedesigner.dtresourceprovider.IDTResourceProvider;
import org.eclipse.jst.pagedesigner.dtresourceprovider.IDTSkin;
import org.eclipse.jst.pagedesigner.utils.EditorUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * Property page for the Web Page Editor.
 * 
 * @author Ian Trimble - Oracle
 */
public class WPEPropertyPage extends PropertyPage {

	private IProject project;
	private TableViewer taglibTable;
	private TableViewer skinsTable;
	private Button currentButton;
	private Map<String, IDTSkin> localCurrentSkinMap = new HashMap<String, IDTSkin>();

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#getDescription()
	 */
	@Override
	public String getDescription() {
		return DialogsMessages.getString("WPEPropertyPage.Description"); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite top = new Composite(parent, SWT.NONE);
		GridLayout topLayout = new GridLayout(2, false);
		topLayout.marginWidth = 0;
		top.setLayout(topLayout);
		top.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		//taglibs label
		Label taglibLabel = new Label(top, SWT.NONE);
		taglibLabel.setText(DialogsMessages.getString("WPEPropertyPage.TagLibs.Label")); //$NON-NLS-1$
		taglibLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		//empty cell
		new Label(top, SWT.NONE).setVisible(false);

		//taglibs list
		taglibTable = new TableViewer(top, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		taglibTable.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		taglibTable.getTable().addSelectionListener(new TaglibSelectionListener());
		taglibTable.setContentProvider(new TaglibContentProvider());
		taglibTable.setLabelProvider(new TaglibLabelProvider());
		taglibTable.setInput(project);

		//empty cell
		new Label(top, SWT.NONE).setVisible(false);

		//skins label
		Label skinsLabel = new Label(top, SWT.NONE);
		skinsLabel.setText(DialogsMessages.getString("WPEPropertyPage.Skins.Label")); //$NON-NLS-1$
		skinsLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		//empty cell
		new Label(top, SWT.NONE).setVisible(false);

		//skins list
		skinsTable = new TableViewer(top, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		skinsTable.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		skinsTable.getTable().addSelectionListener(new SkinSelectionListener());
		skinsTable.setContentProvider(new SkinContentProvider());
		skinsTable.setLabelProvider(new SkinLabelProvider());

		//buttons
		Composite buttons = new Composite(top, SWT.NONE);
		GridLayout buttonsLayout = new GridLayout(1, true);
		buttonsLayout.marginWidth = 0;
		buttons.setLayout(buttonsLayout);
		buttons.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		currentButton = new Button(buttons, SWT.NONE);
		currentButton.setText(DialogsMessages.getString("WPEPropertyPage.Current.Button")); //$NON-NLS-1$
		currentButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				String nsURI = getCurrentNSURI();
				IStructuredSelection selection = (IStructuredSelection)skinsTable.getSelection();
				IDTSkin dtSkin = (IDTSkin)selection.getFirstElement();
				if (dtSkin != null) {
					localCurrentSkinMap.put(nsURI, dtSkin);
					skinsTable.refresh();
					updateButtons();
				}
			}
		});
		updateButtons();

		return top;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.PropertyPage#setElement(org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	public void setElement(IAdaptable element) {
		Object objElement = element.getAdapter(IProject.class);
		if (objElement != null) {
			project = (IProject)objElement;
			if (taglibTable != null) {
				taglibTable.setInput(objElement);
			}
		}
		super.setElement(element);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		List<TaglibData> taglibDataList = getTaglibDataList();
		for (TaglibData taglibData: taglibDataList) {
			String nsURI = taglibData.getNSURI();
			IDTSkin defaultDTSkin = DTSkinManager.getInstance(project).getDefaultSkin(nsURI);
			localCurrentSkinMap.put(nsURI, defaultDTSkin);
		}
		skinsTable.refresh();
		updateButtons();
		super.performDefaults();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		Set keys = localCurrentSkinMap.keySet();
		for (Object key: keys) {
			String nsURI = (String)key;
			IDTSkin dtSkin = localCurrentSkinMap.get(nsURI);
			DTSkinManager.getInstance(project).setCurrentSkin(nsURI, dtSkin);
		}
		EditorUtil.refreshAllWPEDesignViewers();
		return super.performOk();
	}

	private IDTSkin getLocalCurrentSkin() {
		IDTSkin localCurrentSkin = null;
		String nsURI = getCurrentNSURI();
		localCurrentSkin = localCurrentSkinMap.get(nsURI);
		if (localCurrentSkin == null) {
			localCurrentSkin = DTSkinManager.getInstance(project).getCurrentSkin(nsURI);
		}
		return localCurrentSkin;
	}

	private String getCurrentNSURI() {
		String nsURI = null;
		if (skinsTable != null) {
			Object objInput = skinsTable.getInput();
			if (objInput instanceof TaglibData) {
				nsURI = ((TaglibData)objInput).getNSURI();
			}
		}
		return nsURI;
	}

	private List<TaglibData> getTaglibDataList() {
		List<TaglibData> taglibDataList = new ArrayList<TaglibData>();
		List<IDTResourceProvider> resourceProviders =
			DTResourceProviderFactory.getInstance().getActiveDTResourceProviders(project);
		for (IDTResourceProvider resourceProvider: resourceProviders) {
			TaglibData taglibData = new TaglibData(resourceProvider.getId());
			if (!taglibDataList.contains(taglibData)) {
				taglibDataList.add(taglibData);
			}
		}
		return taglibDataList;
	}

	private void updateButtons() {
		boolean currentButtonEnabled = false;
		if (skinsTable != null) {
			IStructuredSelection selection = (IStructuredSelection)skinsTable.getSelection();
			if (selection != null) {
				Object selectedObject = selection.getFirstElement();
				if (selectedObject instanceof IDTSkin) {
					IDTSkin localCurrentSkin = getLocalCurrentSkin();
					if ((IDTSkin)selectedObject != localCurrentSkin) {
						currentButtonEnabled = true;
					}
				}
			}
		}
		currentButton.setEnabled(currentButtonEnabled);
	}



	/**
	 * Content provider for taglib table viewer.
	 */
	private class TaglibContentProvider implements IStructuredContentProvider {
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object inputElement) {
			return getTaglibDataList().toArray();
		}
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			viewer.refresh();
		}
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
			//nothing to dispose
		}
	}



	/**
	 * Label provider for taglib table viewer.
	 */
	private class TaglibLabelProvider extends LabelProvider {
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
		 */
		public Image getImage(Object element) {
			Image image = null;
			if (element instanceof TaglibData) {
				image = PDPlugin.getDefault().getImage("library_obj.gif"); //$NON-NLS-1$
			}
			return image;
		}
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
		 */
		public String getText(Object element) {
			String text = null;
			if (element instanceof TaglibData) {
				TaglibData taglibData = ((TaglibData)element);
				if (taglibData.getName() != null) {
					text = taglibData.getName();
				} else {
					text = taglibData.getNSURI();
				}
			}
			return text;
		}
	}



	private class TaglibSelectionListener implements SelectionListener {
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
		 */
		public void widgetSelected(SelectionEvent event) {
			Object obj = event.item.getData();
			if (obj instanceof TaglibData) {
				skinsTable.setInput(obj);
			}
		}
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
		 */
		public void widgetDefaultSelected(SelectionEvent event) {
			widgetSelected(event);
		}
	}



	/**
	 * Used to hold and pass taglib-related data.
	 */
	private class TaglibData {
		private String nsURI;
		private String name;
		public TaglibData(String nsURI) {
			this.nsURI = nsURI;
			ITaglibDomainMetaDataModelContext modelContext = TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(project, nsURI);
			Model model = TaglibDomainMetaDataQueryHelper.getModel(modelContext);
			Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(model, "display-label"); //$NON-NLS-1$
			this.name = TraitValueHelper.getValueAsString(trait);
		}
		public String getNSURI() {
			return nsURI;
		}
		public String getName() {
			return name;
		}
		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		public int hashCode() {
			int nsURIHashCode = 0;
			if (nsURI != null) {
				nsURIHashCode = nsURI.hashCode();
			}
			int nameHashCode = 0;
			if (name != null) {
				nameHashCode = name.hashCode();
			}
			return nameHashCode | nsURIHashCode ;
		}
	}



	/**
	 * Content provider for skin table viewer.
	 */
	private class SkinContentProvider implements IStructuredContentProvider {
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object inputElement) {
			List<IDTSkin> skinList;
			if (inputElement instanceof TaglibData) {
				skinList = DTSkinManager.getInstance(project).getSkins(((TaglibData)inputElement).getNSURI());
			} else {
				skinList = Collections.EMPTY_LIST;
			}
			return skinList.toArray();
		}
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			viewer.refresh();
			updateButtons();
		}
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
			//nothing to dispose
		}
	}



	/**
	 * Label provider for skin table viewer.
	 */
	private class SkinLabelProvider extends LabelProvider {
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
		 */
		public Image getImage(Object element) {
			Image image = null;
			if (element instanceof IDTSkin) {
				image = PDPlugin.getDefault().getImage("skin.png"); //$NON-NLS-1$
			}
			return image;
		}
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
		 */
		public String getText(Object element) {
			String text = null;
			if (element instanceof IDTSkin) {
				IDTSkin dtSkin = (IDTSkin)element;
				text = dtSkin.getName();
				if (dtSkin == getLocalCurrentSkin()) {
					text += " "  + DialogsMessages.getString("WPEPropertyPage.Skins.Item.Current"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			return text;
		}
	}



	private class SkinSelectionListener implements SelectionListener {
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
		 */
		public void widgetSelected(SelectionEvent event) {
			updateButtons();
		}
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
		 */
		public void widgetDefaultSelected(SelectionEvent event) {
			widgetSelected(event);
		}
	}

}
