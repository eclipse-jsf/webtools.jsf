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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.util;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;

/**
 * 
 * This class includes transform from the faces-config xml model to databinding
 * EMF model, and pageflow EMF model. and the reverse direction.
 * 
 * @author Xiao-guang Zhang
 */
public class FacesConfigModelAdapter {// implements INodeAdapter {

	// private static final String PATH_SEPARATOR = "/";

	// private FacesConfig facesConfig = null;

	/** creation factory of faces-config model */
	// private FacesConfig10Factory facesConfigFactory = null;
	/** the dirty state of source page */
	private boolean isDirtyFacesConfig = false;

	/** the dirty state of pageflow page */
	private boolean isDirtyPageflow = false;

	/** the pageflow model manager */
	private PageflowModelManager pageflowManager;

	/** the faces-config xml SSEModel */
	// private IStructuredModel facesConfigSSEModel;
	/** Create the logger for this class */
	// private final Logger log = EditorPlugin
	// .getLogger(FacesConfigModelAdapter.class);
	/**
	 */

	/**
	 * --------------------------------- new for EMF model.
	 */

	private FacesConfigType facesConfigType;

	public FacesConfigType getFacesConfigType() {
		return facesConfigType;
	}

	public void setFacesConfigEMFModel(FacesConfigType facesConfigType) {
		this.facesConfigType = facesConfigType;
	}

	public FacesConfigModelAdapter() {
		super();
	}

	public void dispose() {
		removeNavigationRuleAdapters();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.core.internal.provisional.INodeAdapter#isAdapterForType(java.lang.Object)
	 */
	// public boolean isAdapterForType(Object type) {
	// return type == FacesConfigModelAdapter.class;
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.core.internal.provisional.INodeAdapter#notifyChanged(org.eclipse.wst.sse.core.internal.provisional.INodeNotifier,
	 *      int, java.lang.Object, java.lang.Object, java.lang.Object, int)
	 */
	// public void notifyChanged(INodeNotifier notifier, int eventType,
	// Object changedFeature, Object oldValue, Object newValue, int pos) {
	//
	// boolean bNeedUpdateAdapters = false;
	// if (eventType == INodeNotifier.REMOVE) {
	// if (changedFeature instanceof IDOMNode) {
	// // if remove the faces-config tag
	// if (((IDOMNode) changedFeature).getNodeName().equalsIgnoreCase(
	// IFacesConfigConstants.FACES_CONFIG)) {
	// bNeedUpdateAdapters = true;
	// }
	//
	// // if remove navigation-rule tag
	// if (((IDOMNode) notifier).getNodeName().equalsIgnoreCase(
	// IFacesConfigConstants.FACES_CONFIG)) {
	// if (((IDOMNode) changedFeature).getNodeName()
	// .equalsIgnoreCase(
	// IFacesConfigConstants.NAVIGATION_RULE)) {
	// bNeedUpdateAdapters = true;
	// }
	// }
	// }
	// } else if (eventType == INodeNotifier.ADD) {
	// if (newValue instanceof IDOMNode) {
	// // if add the faces-config tag
	// if (((IDOMNode) newValue).getNodeName().equalsIgnoreCase(
	// IFacesConfigConstants.FACES_CONFIG)) {
	// bNeedUpdateAdapters = true;
	// }
	//
	// // if add navigation-rule tag
	// if (((IDOMNode) notifier).getNodeName().equalsIgnoreCase(
	// IFacesConfigConstants.FACES_CONFIG)) {
	// if (((IDOMNode) newValue).getNodeName().equalsIgnoreCase(
	// IFacesConfigConstants.NAVIGATION_RULE)) {
	// bNeedUpdateAdapters = true;
	// }
	// }
	// }
	// } else if (eventType == INodeNotifier.CONTENT_CHANGED
	// || eventType == INodeNotifier.STRUCTURE_CHANGED) {
	// if (!((IDOMNode) notifier).getNodeName().equalsIgnoreCase(
	// IFacesConfigConstants.FACES_CONFIG)) {
	// setFacesConfigDirty(true);
	// }
	// }
	//
	// if (bNeedUpdateAdapters) {
	// setFacesConfigDirty(true);
	// addNavigationRuleAdapters();
	// }
	// }
	/**
	 * Add emf notification adapters to all navigation XML nodes, and its parent
	 * Faces-config node and root document node.
	 * 
	 */
	// private void addNavigationRuleAdapters() {
	// System.out
	// .println("Add navigation rule adapters in facesConfigModelAdapter");
	// if (getFacesConfigModel() != null) {
	// FacesConfigDocumentWrap documentWrap = new FacesConfigDocumentWrap(
	// ((IDOMModel) getFacesConfigModel()).getDocument());
	//
	// // add the notification adapter to the document
	// ((IDOMNode) ((IDOMModel) getFacesConfigModel()).getDocument())
	// .addAdapter(this);
	//
	// // The Faces-config may not exist.
	// if (documentWrap.getFacesConfig() == null) {
	// return;
	// }
	//
	// // add the notification adapter to faces-config tag
	// if (documentWrap.getFacesConfig().getNode().getAdapterFor(
	// FacesConfigModelAdapter.class) == null) {
	// documentWrap.getFacesConfig().getNode().addAdapter(this);
	// }
	//
	// List navigationRules = documentWrap.getFacesConfig()
	// .getNavigationList();
	//
	// if (navigationRules != null) {
	// Iterator iter = navigationRules.iterator();
	// while (iter.hasNext()) {
	// NavigationRule navigationRule = (NavigationRule) iter
	// .next();
	// StructuredModelUtil.propagateAddAdapter(navigationRule
	// .getNode(), this, FacesConfigModelAdapter.class);
	// }
	// }
	// }
	// }
	/**
	 * Remove emf notification adapters to all navigation XML nodes, and its
	 * parent Faces-config node and root document node.
	 * 
	 */
	private void removeNavigationRuleAdapters() {
		// System.out.println("Remove navigation rule adapters");
		// if (getFacesConfigModel() != null) {
		// FacesConfigDocumentWrap documentWrap = new FacesConfigDocumentWrap(
		// ((IDOMModel) getFacesConfigModel()).getDocument());
		//
		// // add the notification adapter to the document
		// ((IDOMNode) ((IDOMModel) getFacesConfigModel()).getDocument())
		// .removeAdapter(this);
		//
		// // The Faces-config may not exist.
		// if (documentWrap.getFacesConfig() == null) {
		// return;
		// }
		//
		// // add the notification adapter to faces-config tag
		// documentWrap.getFacesConfig().getNode().removeAdapter(this);
		//
		// List navigationRules = documentWrap.getFacesConfig()
		// .getNavigationList();
		//
		// if (navigationRules != null) {
		// Iterator iter = navigationRules.iterator();
		// while (iter.hasNext()) {
		// NavigationRule navigationRule = (NavigationRule) iter
		// .next();
		// StructuredModelUtil.propagateRemoveAdapter(navigationRule
		// .getNode(), this);
		// }
		// }
		// }
	}

	/**
	 * get the faces-config model
	 * 
	 * @return
	 */
	// public IStructuredModel getFacesConfigModel() {
	// return facesConfigSSEModel;
	// }
	//
	// /**
	// * set the pageflow model manager
	// *
	// * @param model
	// */
	// public void setFacesConfigModel(IStructuredModel model) {
	// facesConfigSSEModel = model;
	// addNavigationRuleAdapters();
	// }
	/**
	 * get the pageflow model manager
	 * 
	 * @return
	 */
	public PageflowModelManager getPageflowManager() {
		return pageflowManager;
	}

	/**
	 * set the pageflow model manager
	 * 
	 * @param pageflowManager
	 */
	public void setPageflowManager(PageflowModelManager pageflowManager) {
		this.pageflowManager = pageflowManager;
	}

	/**
	 * faces-config source editor's dirty status
	 * 
	 * @return
	 */
	public boolean isFacesConfigDirty() {
		return isDirtyFacesConfig;
	}

	/**
	 * pageflow editor is dirty or not.
	 * 
	 * @return
	 */
	public boolean isPageflowDirty() {
		return isDirtyPageflow;
	}

	/**
	 * set the dirty status of faces-config source editor.
	 * 
	 * @param isDirtyFacesConfig
	 */
	public void setFacesConfigDirty(boolean isDirtyFacesConfig) {
		if (this.isDirtyFacesConfig != isDirtyFacesConfig) {
			this.isDirtyFacesConfig = isDirtyFacesConfig;
		}
	}

	/**
	 * set the dirty status of pageflow editor.
	 * 
	 */
	public void setPageflowDirty(boolean dirty) {
		if (this.isDirtyPageflow != dirty) {
			this.isDirtyPageflow = dirty;
		}
	}

	/**
	 * Sychronize the dirty statuses of all editors.
	 * 
	 * @param isSynchronized
	 */
	public void setPageflowSynchronizeState(boolean isSynchronized) {
		if (isSynchronized) {
			isDirtyFacesConfig = false;
			isDirtyPageflow = false;
		}
	}

	/**
	 * get the faces-config file according to current pageflow file. if the
	 * current pageflow file has no face-config file assigned, the default
	 * faces-config file will be got.
	 * 
	 * @return
	 */
	// public IFile getFacesConfigFile() {
	// if (null == fileFacesConfig) {
	// if (null != pageflowManager.getModel().getConfigfile()) {
	// IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	// fileFacesConfig = root.getFile(new Path(pageflowManager
	// .getModel().getConfigfile()));
	// if (fileFacesConfig == null || !fileFacesConfig.exists()) {
	// WorkspaceUtil
	// .ensureExistsInWorkspace(fileFacesConfig, true);
	// initFacesConfigFile(fileFacesConfig);
	// }
	// } else
	// // no specific faces-config file.
	// {
	// IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	// String configFileName = getDefaultFacesConfigFileName(pageflowManager
	// .getPath());
	// fileFacesConfig = root.getFile(new Path(configFileName));
	// WorkspaceUtil.ensureExistsInWorkspace(fileFacesConfig, true);
	// pageflowManager.getModel().setConfigfile(configFileName);
	// }
	// }
	// return fileFacesConfig;
	// }
	/**
	 * initialize faces config file with the document root and faceconfig tag
	 * 
	 * @param fileFacesConfig
	 */
	// private void initFacesConfigFile(IFile fileFacesConfig) {
	// URI fileURI = URI.createURI("platform:/resource"
	// + fileFacesConfig.getFullPath());
	// Resource rsc = new FacesConfig10ResourceFactoryImpl()
	// .createResource(fileURI);
	//
	// FacesConfig10Factory facesFactory = FacesConfig10Factory.eINSTANCE;
	//
	// DocumentRoot droot = facesFactory.createDocumentRoot();
	// rsc.getContents().add(droot);
	//
	// FacesConfigType sts = facesFactory.createFacesConfigType();
	// droot.setFacesConfig(sts);
	//
	// // call the FacesConfig.save() to save the default file
	// FacesConfig config = new FacesConfig(rsc);
	// try {
	// config.save();
	// } catch (IOException e) {
	// log.error(e);
	// EditorPlugin.getAlerts().warning(
	// "Pageflow.FacesConfig.Alert.ResourceTitle",
	// "Pageflow.FacesConfig.Alert.SaveFileError");
	// }
	// }
	/**
	 * get the faces-config manager related with pageflow model.
	 * 
	 * @return
	 */
	// public FacesConfig getFacesConfig() {
	// if (null == facesConfig) {
	// // create the dummy faces-config model.
	// if (null != pageflowManager.getModel()) {
	// IFile fileFacesConfig = getFacesConfigFile();
	//
	// /** Resouce instance for face-config file */
	// Resource resource = null;
	// if (fileFacesConfig.exists()) {
	// URI fileURI = URI.createURI("platform:/resource"
	// + fileFacesConfig.getFullPath());
	//
	// resource = new FacesConfig10ResourceFactoryImpl()
	// .createResource(fileURI);
	// facesConfig = new FacesConfig(resource);
	// }
	// }
	// }
	// return facesConfig;
	// }
	/**
	 * Update the content of the model with the faces-config file.
	 * 
	 */
	public boolean updateFacesConfigFromPageflow(
			final InputStream inputStreamOfFacesConfig) {
		if (getFacesConfigType() == null) {
			return false;
		}

		// try {
		// // update the faces-config Resource.
		// getFacesConfig().load(inputStreamOfFacesConfig);
		// } catch (IOException e) {
		// EditorPlugin
		// .getAlerts()
		// .warning("Pageflow.FacesConfig.Alert.ResourceTitle",
		// "Pageflow.FacesConfig.Alert.ErrorUpdateFacesConfigFromPageflow");
		// e.printStackTrace();
		// return false;
		// }

		// get the faces-config EMF model
		// FacesConfigType facesConfigType =
		// getFacesConfigEMFModel(getFacesConfig()
		// .getResource());
		//
		// facesConfigType.getNavigationRule().clear();
		//
		// FacesConfigDocumentWrap documentWrap = new FacesConfigDocumentWrap(
		// ((IDOMModel) facesConfigSSEModel).getDocument());
		//
		// documentWrap.getFacesConfig().getElement().removeChildNodes(
		// IFacesConfigConstants.NAVIGATION_RULE);

		// PageflowTransform.getInstance().updateFacesConfigModel(
		// pageflowManager.getModel(), facesConfigType, facesConfigSSEModel);

		// PageflowTransform.getInstance().updateFacesConfigModel(
		// pageflowManager.getModel(), getFacesConfigType());

		return true;
	}

	/**
	 * Update the content of the model with the faces-config file.
	 * 
	 * @throws IOException
	 * 
	 */
	public boolean updatePageflowFromFacesConfig() {
		if (pageflowManager == null) {
			return false;
		}
		// try {
		// // update the faces-config Resource.
		// getFacesConfig().load();
		// } catch (IOException e) {
		// EditorPlugin.getAlerts().warning(
		// "Pageflow.FacesConfig.Alert.ResourceTitle",
		// "Pageflow.FacesConfig.Alert.LoadFileError",
		// fileFacesConfig.getFullPath().toString());
		// e.printStackTrace();
		// return;
		// }

		// get the faces-config EMF model
		// FacesConfigType facesConfig = getFacesConfigEMFModel(getFacesConfig()
		// .getResource());

		// if (!PageflowTransform.getInstance().updatePageflowModel(
		// pageflowManager.getModel(), facesConfig)) {
		// return;
		return PageflowTransform.getInstance().updatePageflowModelFromEMF(
				pageflowManager.getModel(), getFacesConfigType());
		// {
		// return;
		// }
		// auto layout pageflow
		// if (layout)
		// PageflowLayoutManager.getInstance().layoutPageflow(
		// pageflowManager.getModel());
	}

	/**
	 * get the content of the model with the faces-config file.
	 * 
	 * @param path
	 */
	public Pageflow getPageflowFromFacesConfig(boolean layout)
			throws IOException {
		Pageflow pageflow = pageflowManager.getModel();

		// update the faces-config Resource based on the streamOfFacesConfig.
		// getFacesConfig().load(streamOfFacesConfig);

		// get the faces-config EMF model
		// FacesConfigType facesConfig = getFacesConfigEMFModel(getFacesConfig()
		// .getResource());

		if (PageflowTransform.getInstance().updatePageflowModelFromEMF(
				pageflow, getFacesConfigType())) {
			// update the pageflow model information
			// PageflowTransform.updatePageflowModelInfo(pageflow,
			// pageflowManager
			// .getModel());
			// auto layout pageflow
			// if (layout) {
			// // PageflowLayoutManager.getInstance().layoutPageflow(pageflow);
			// // // update the layout according to existing Pageflow model
			// // PageflowLayoutManager.getInstance().updatePageflowLayout(
			// // pageflow, pageflowManager.getModel());
			// }
			return pageflow;
		}
		return null;
	}

	/**
	 * get the content of the model with the faces-config file.
	 * 
	 * @param path
	 */
	// public EList getManagedBeansFromFacesConfig(
	// final InputStream streamOfFacesConfig) throws IOException {
	// // update the faces-config Resource based on the streamOfFacesConfig.
	// getFacesConfig().load(streamOfFacesConfig);
	//
	// // get the faces-config EMF model
	// FacesConfigType facesConfig = getFacesConfigEMFModel(getFacesConfig()
	// .getResource());
	//
	// return facesConfig.getManagedBean();
	// }
	/**
	 * get the default faces-config file name according to project name of the
	 * pageflow
	 * 
	 * @param pathPageflow -
	 *            the pageflow's path
	 * @return - the default faces-config file name
	 */
	// private String getDefaultFacesConfigFileName(final IPath pathPageflow) {
	// IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	// IFile filePageflow = root.getFile(pathPageflow);
	//
	// String strFacesConfig = PATH_SEPARATOR
	// + filePageflow.getProject().getName()
	// + IFileFolderConstants.DEFAULT_FACES_CONFIG_FILE_PATH;
	// return strFacesConfig;
	// }
	/**
	 * get the navigation rules in faces-config file. if the face-config file is
	 * empty, it should create a new one.
	 * 
	 * @param resCF -
	 *            EMF Resource for the file
	 * @return - faces-config model root type
	 */
	// private FacesConfigType getFacesConfigEMFModel(Resource resCF) {
	// FacesConfigType configType = null;
	// if (resCF != null) {
	// if (resCF.getContents().isEmpty()) {
	// FacesConfig10Factory factory = getFacesConfigFactory();
	//
	// DocumentRoot droot = factory.createDocumentRoot();
	// resCF.getContents().add(droot);
	//
	// configType = factory.createFacesConfigType();
	// droot.setFacesConfig(configType);
	// } else {
	// DocumentRoot root = (DocumentRoot) resCF.getContents().get(0);
	// configType = root.getFacesConfig();
	// }
	// }
	// return configType;
	// }
	/**
	 * get the model creation factory of faces-config 1.0
	 * 
	 * @return - the model creation factory of faces-config 1.0
	 */
	// private FacesConfig10Factory getFacesConfigFactory() {
	// if (facesConfigFactory == null) {
	// facesConfigFactory = FacesConfig10Factory.eINSTANCE;
	// }
	// return facesConfigFactory;
	// }
}
