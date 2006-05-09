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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

/**
 * Manages the files that comprise the Java Server Faces Configuration
 * 
 * @author Jane Cantz, Xiao-guang Zhang
 * @version 1.0
 */
public class ConfigurationManager {

	/** The current project reference */
	private IProject currentProject = null;

	/** Create the logger for this class */
	// private static final Logger log = EditorPlugin
	// .getLogger(ConfigurationManager.class);
	/** List of all the Faces Configuration Types */
	private List configs = null;

	/** An array of the managed beans List from the configuration set */
	private List[] managedBeans = null;

	// private IFacesConfigManager facesConfigManagerAdapter;

	/**
	 * Manages the Faces Configuration set, which could be a single
	 * <i>faces-config.xml</i> file or a set of config files defined in
	 * <i>web.xml</i>.
	 * 
	 * @param project -
	 *            the current project
	 */
	public ConfigurationManager(IProject project) {
		super();
		// log.setResourceBundle(EditorPlugin.getDefault().getResourceBundle());
		currentProject = project;
	}

	/**
	 * get the current project
	 * 
	 * @return
	 */
	public IProject getProject() {
		return currentProject;
	}

	/**
	 * @return List[] - a derived list of managed beans from the configuration
	 *         set. The list is derived from reading the configuration files and
	 *         extracting the bean references. Every element of the retruned
	 *         list is a list, which contains
	 *         <code>org.eclipse.jst.jsf.facesconfig.ui.model.xml.ManagedBean</code>.
	 */
//	public List[] deriveManagedBeans() {
//		if (configs == null || configs.size() == 0) {
//			return null;
//		}
//		managedBeans = new ArrayList[configs.size()];
//
//		// Loop thru all the configuration types
//		for (int x = 0; x < configs.size(); x++) {
//			IFile aConfigFile = (IFile) configs.get(x);
//
//			// List beans = ((FacesConfigType) configs.get(x)).getManagedBean();
//
//			managedBeans[x] = getManagedBeanListFromFile(aConfigFile);
//		}
//		return managedBeans;
//	}

	// private List getManagedBeanListFromFile(IFile facesConfigFile) {
	//
	// if (facesConfigFile == null || !facesConfigFile.exists()) {
	// return null;
	// }
	// List managedBeanList = new ArrayList(0);
	// IStructuredModel sModel = StructuredModelManager.getModelManager()
	// .getExistingModelForRead(facesConfigFile);
	// if (sModel != null) {
	// IDOMDocument document = ((IDOMModel) sModel).getDocument();
	// FacesConfigDocumentWrap facesConfigWrap = new FacesConfigDocumentWrap(
	// document);
	// if (facesConfigWrap.getFacesConfig() != null) {
	// managedBeanList = facesConfigWrap.getFacesConfig()
	// .getManagedBeanList();
	// }
	// sModel.releaseFromRead();
	// }
	// return managedBeanList;
	// }

	/**
	 * @return String[] - An array of the configuration files
	 */
	// public Object[] getConfigs() {
	// return configs.toArray();
	// }
	/**
	 * @return List[] - a derived list of managed beans from the configuration
	 *         set. The list is derived from reading the configuration files and
	 *         extracting the bean references. Every element of the retruned
	 *         list is a list, which contains
	 *         <code>org.eclipse.jst.jsf.facesconfig.ui.model.xml.ManagedBean</code>.
//	 */
//	public List[] getManagedBeans() {
//		deriveManagedBeans();
//		return managedBeans;
//	}

	/**
	 * Determine what files comprise the Java Server Faces configuration
	 * 
	 * @param project -
	 *            the project to look for configuration files in
	 */
	public void loadConfiguration(IProject project) {
		currentProject = project;

		if (currentProject == null) {
			return;
		}

		IFacesConfigManager facesConfigManager = getFacesConfigurationManager(project);

		if (facesConfigManager == null) {
			return;
		}

		IFile[] facesConfigFiles = facesConfigManager.getFacesConfigs();

		if (facesConfigFiles != null && facesConfigFiles.length > 0) {
			configs = new ArrayList();
			for (int i = 0; i < facesConfigFiles.length; i++) {
				configs.add(facesConfigFiles[i]);
			}
		}
	}

	private IFacesConfigManager getFacesConfigurationManager(IProject project) {
		// facesConfigManagerAdapter = (IFacesConfigManager) Platform
		// .getAdapterManager().getAdapter(project,
		// IFacesConfigManager.class);
		//
		// return facesConfigManagerAdapter;
		FacesConfigManager configManager = new FacesConfigManager(project);
		configManager.loadConfiguration();
		return configManager;
	}

	/**
	 * Loads a single configuration file
	 * 
	 * @param config -
	 *            the faces configuration file to load. Just the file name only.
	 */
	// public void loadConfiguration(String config) {
	// if (currentProject == null) {
	// return;
	// }
	//
	// IFacesConfigManager facesConfigManager =
	// getFacesConfigurationManager(currentProject);
	//
	// if (facesConfigManager == null) {
	// return;
	// }
	//
	// FacesConfigType[] facesConfigs = facesConfigManager.getFacesConfigs();
	//
	// if (facesConfigs != null) {
	// IFile file = currentProject
	// .getFile(IFileFolderConstants.PATH_SEPARATOR
	// + WEBINF_FOLDER
	// + IFileFolderConstants.PATH_SEPARATOR + config);
	//
	// for (int i = 0; i < facesConfigs.length; i++) {
	// if (URIHelper.getPlatformURI(file).equalsIgnoreCase(
	// facesConfigs[i].eResource().getURI().toString())) {
	// configs = new ArrayList();
	// configs.add(facesConfigs[i]);
	// break;
	// }
	// }
	// }
	// }
	/**
	 * Saves all the configuration resources to the local file system
	 */
	// public void save() {
	// IFacesConfigManager facesConfigManager =
	// getFacesConfigurationManager(currentProject);
	//
	// if (facesConfigManager != null) {
	// facesConfigManager.endModify();
	// }
	// }
	/**
	 * Update the managed bean type's scope value
	 * 
	 * @param mbt -
	 *            the managed bean type
	 * @param scopeValue -
	 *            the scope value to set
	 */
	// public void updateBeanScope(ManagedBeanTypeImpl mbt, String scopeValue) {
	// ManagedBeanScopeType mbst = mbt.getManagedBeanScope();
	// mbst.setValue(scopeValue);
	// Resource resource = mbt.eResource();
	// resource.setModified(true);
	// }
}
