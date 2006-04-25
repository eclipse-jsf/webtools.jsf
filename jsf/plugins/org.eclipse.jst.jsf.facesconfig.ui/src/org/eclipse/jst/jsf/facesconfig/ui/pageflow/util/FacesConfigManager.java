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
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.util.ListenerList;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.jsf.facesconfig.common.IFileFolderConstants;
import org.eclipse.jst.jsf.facesconfig.ui.util.WebrootUtil;

/**
 * This class traces changes in the web.xml file, and then traces faces-config
 * files declared in the web.xml file (or the default faces config file).
 * Providing access method for all the faces-config files as a group.
 * 
 * @author Yang Liu
 * @version
 */
// Part of this file is refactored from the old ConfigurationManager.
public class FacesConfigManager implements IFacesConfigManager // , ILifecycle
{
	// private static final Logger _log =
	// ModelsPlugin.getLogger(FacesConfigManager.class);

	// private String WEBINF_FOLDER = IFileFolderConstants.FOLDER_WEBROOT
	// + IFileFolderConstants.PATH_SEPARATOR
	// + IFileFolderConstants.FOLDER_WEBINF;

	private IProject currentProject;

	/** The Adaptor Factory */
	// protected ComposedAdapterFactory _adapterFactory;
	ListenerList listenerList = new ListenerList(1);

	// ------------------------------------------------------------------------
	// _editingDomain need refresh each time re-read the configs.
	/** The Faces Configuration Editing Domain */
	// protected AdapterFactoryEditingDomain _editingDomain;
	// ------------------------------------------------------------------------
	// following are things need to re-read each refresh.
	/** List of all the Faces Configuration Files */
	// private List _configs = null;
	//
	// private FacesConfigType[] _facesConfigs = null;
	private List facesConfigFileList;

	// class ResourceTracker implements IResourceChangeListener,
	// IResourceDeltaVisitor {
	// boolean _configChanged;
	//
	// public void resourceChanged(IResourceChangeEvent event) {
	// switch (event.getType()) {
	// case IResourceChangeEvent.POST_CHANGE:
	// try {
	// _configChanged = false;
	// event.getDelta().accept(this);
	// } catch (CoreException exception) {
	// _log.error("common.error", exception);
	// } finally {
	// if (_configChanged) {
	// refresh();
	// }
	// }
	// break;
	// }
	// }
	//
	// public boolean visit(IResourceDelta delta) {
	// if (_configChanged) {
	// return false; // we only need to set the flag, if it is
	// // already set, then no need to process
	// }
	// IResource res = delta.getResource();
	// if (res instanceof IProject && res != currentProject) {
	// return false;
	// }
	//
	// if (res instanceof IProject && res == currentProject) {
	// if (delta.getKind() == IResourceDelta.REMOVED) {
	// return false;
	// } else if (delta.getKind() == IResourceDelta.CHANGED) {
	// if ((delta.getFlags() & IResourceDelta.OPEN) != 0) {
	// if (currentProject.isOpen()) {
	// _configChanged = true;
	// }
	// return false;
	// }
	// }
	// }
	//
	// if (!(res instanceof IFile)) {
	// return true; // visit the children
	// }
	//
	// if (_configs != null) {
	// // See if the changed resource is a faces configuration file
	// for (int x = 0, size = _configs.size(); x < size; x++) {
	// IFile configFile = currentProject
	// .getFile((String) _configs.get(x));
	// if (configFile.equals(res)) {
	// if (delta.getKind() == IResourceDelta.REMOVED
	// || delta.getKind() == IResourceDelta.ADDED) {
	// _configChanged = true;
	// return false;
	// }
	//
	// int flags = delta.getFlags();
	// if (((flags & IResourceDelta.CONTENT) != 0)
	// || ((flags & IResourceDelta.REPLACED) != 0)) {
	// _configChanged = true;
	// return false;
	// }
	// break;
	// }
	// }
	// }
	//
	// if (res.getName().equalsIgnoreCase(
	// IFileFolderConstants.FILE_WEB_XML)) {
	// _configChanged = true;
	// }
	// return false;
	// }
	// }

	// ResourceTracker _tracker = new ResourceTracker();

	/**
	 * 
	 */
	public FacesConfigManager(IProject project) {
		this.currentProject = project;
	}

	// public EditingDomain getEditingDomain()
	// {
	// return _editingDomain;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.models.IFacesConfigManager#getProject()
	 */
	public IProject getProject() {
		return currentProject;
	}

	// public void configurate()
	// {
	// List factories = new ArrayList();
	// factories.add(new ResourceItemProviderAdapterFactory());
	// factories.add(new FacesConfig10ItemProviderAdapterFactory());
	// factories.add(new ReflectiveItemProviderAdapterFactory());
	// _adapterFactory = new ComposedAdapterFactory(factories);
	//
	// BasicCommandStack commandStack = new BasicCommandStack();
	// _editingDomain = new AdapterFactoryEditingDomain(_adapterFactory,
	// commandStack);
	//
	// // read everything from scratch
	// refresh();
	//
	// ResourcesPlugin.getWorkspace().addResourceChangeListener(_tracker);
	// }

	// public void deconfigurate()
	// {
	// ResourcesPlugin.getWorkspace().removeResourceChangeListener(_tracker);
	// // XXX: do we need to do anything here?
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.models.IFacesConfigManager#getFacesConfigs()
	 */
	// public FacesConfigType[] getFacesConfigs() {
	// if (this._facesConfigs == null) {
	// this._facesConfigs = new FacesConfigType[0];
	// }
	// return this._facesConfigs;
	// }
	public IFile[] getFacesConfigs() {
		if (facesConfigFileList == null || facesConfigFileList.size() == 0) {
			loadConfiguration();
		}
		IFile[] files = new IFile[facesConfigFileList.size()];
		facesConfigFileList.toArray(files);
		return files;
	}

	public void refresh() {
		if (currentProject == null) {
			return;
		}
		// Clear the current resources from the set
		// ResourceSet rset = _editingDomain.getResourceSet();
		// rset.getResources().clear();
		// _configs = null;
		// _facesConfigs = null;

		loadConfiguration();
		// Loop thru all the configuration files
		// for (int x = 0; x < _configs.size(); x++) {
		// URI fileURI = null;
		// Resource resource = null;
		// if (currentProject.getFolder(WEBINF_FOLDER).exists()) {
		// IFile file = currentProject.getFile((String) _configs.get(x));
		// if (file != null && file.exists()) {
		// fileURI = URI.createURI("platform:/resource/"
		// + currentProject.getName() + _configs.get(x)); //$NON-NLS-1$
		// resource = new FacesConfig10ResourceFactoryImpl()
		// .createResource(fileURI);
		// } else {
		// // missing config file. skip this one
		// // FIXME: log it.
		// }
		// }
		//
		// if (resource != null) {
		// try {
		// resource.unload();
		// FacesConfig config = new FacesConfig(resource);
		// config.load();
		// } catch (Exception ex) {
		// _log.error("common.error", ex);
		// // String name = resource.getURI().path();
		// // _log.error("DBView.LoadResourceError", name, ex);
		// // //$NON-NLS-1$ //$NON-NLS-2$
		// // DataBindingPlugin.getAlerts().detailError("DBView.ResourceTitle",
		// // "DBView.LoadResourceError", name, ex); //$NON-NLS-1$
		// // //$NON-NLS-2$
		// // return null;
		// }
		//
		// if (resource.isLoaded()) {
		// rset.getResources().add(resource);
		// } else {
		// // FIXME: log
		// }
		// }
		// }
		//
		// int count = rset.getResources().size();
		// List configs = new ArrayList();
		// for (int i = 0; i < count; i++) {
		// Resource resource = (Resource) rset.getResources().get(i);
		// if (resource.getContents() == null
		// || resource.getContents().size() == 0) {
		// continue;
		// }
		//
		// try {
		// DocumentRoot root = (DocumentRoot) resource.getContents()
		// .get(0);
		// FacesConfigType sts = root.getFacesConfig();
		// if (sts != null) {
		// configs.add(sts);
		// }
		// } catch (Exception ex) {
		// _log.error("common.error", ex);
		// }
		// }
		// this._facesConfigs = new FacesConfigType[configs.size()];
		// configs.toArray(this._facesConfigs);

		this.fireFacesConfigChanged();

	}

	/**
	 * check whether the config file is existed in all registered config files
	 * or not.
	 * 
	 * @param configFileName
	 * @return
	 */
	// private boolean isDuplicateConfigFile(String configFileName) {
	// boolean isDuplicated = false;
	// // See if all the configuration files exist
	// for (int x = 0; x < _configs.size(); x++) {
	// if (configFileName.equalsIgnoreCase((String) _configs.get(x))) {
	// isDuplicated = true;
	// }
	// }
	// return isDuplicated;
	// }
	/**
	 * check whether the config file is existed in all registered config files
	 * or not.
	 * 
	 * @param facesConfigFile
	 * @return
	 */
	private boolean isDuplicateConfigFile(IFile facesConfigFile) {

		// See if all the configuration files exist
		if (facesConfigFileList != null
				&& facesConfigFileList.contains(facesConfigFile)) {
			return true;
		}
		return false;
	}

	private void registerConfigFile(IFile facesConfigFile) {
		if (facesConfigFile != null && !isDuplicateConfigFile(facesConfigFile)) {
			facesConfigFileList.add(facesConfigFile);
		}

	}

	/**
	 * Determine what files comprise the Java Server Faces configuration
	 * 
	 * @param project -
	 *            the project to look for configuration files in
	 */
	public void loadConfiguration() {

		String paramValue = null;
		WebArtifactEdit web = WebArtifactEdit
				.getWebArtifactEditForRead(currentProject);
		if (web != null) {
			WebApp webApp = web.getWebApp();

			EList contextParams = webApp.getContextParams();
			for (int i = 0, n = contextParams.size(); i < n; i++) {
				ParamValue aParam = (ParamValue) contextParams.get(i);
				if (WebDotXMLTagConstants.CONFIG_FILES_CONTEXT_PARAM
						.equals(aParam.getName())
						|| WebDotXMLTagConstants.APPLICATION_CONFIG_FILES_CONTEXT_PARAM
								.equals(aParam.getName())) {
					paramValue = aParam.getValue();
					break;
				}
			}
			web.dispose();
		}

		// _configs = new ArrayList();

		facesConfigFileList = new ArrayList();

		IFolder webrootFolder = WebrootUtil
				.getWebContentFolder(this.currentProject);
		IFile defaultFacesConfigFile = webrootFolder
				.getFile(IFileFolderConstants.FOLDER_WEBINF
						+ IFileFolderConstants.PATH_SEPARATOR
						+ IFileFolderConstants.FILE_FACES_CONFIG_XML);
		registerConfigFile(defaultFacesConfigFile);

		if (paramValue != null) {
			// Parse the list to an array of files
			StringTokenizer tokenizer = new StringTokenizer(paramValue, ","); //$NON-NLS-1$
			while (tokenizer.hasMoreTokens()) {
				IFile aConifgFile = webrootFolder.getFile(tokenizer.nextToken()
						.trim());
				registerConfigFile(aConifgFile);
			}
		}
	}

	// ---------------------------------------------------------------------------------------
	// methods for change events.
	protected void fireFacesConfigChanged() {
		Object[] listeners = this.listenerList.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			IFacesConfigChangeListener l = (IFacesConfigChangeListener) listeners[i];
			if (l != null) {
				l.facesConfigChanged();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.models.IFacesConfigManager#addFacesConfigChangeListener(com.sybase.stf.jmt.models.IFacesConfigChangeListener)
	 */
	public void addFacesConfigChangeListener(IFacesConfigChangeListener listener) {
		listenerList.add(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.models.IFacesConfigManager#removeFacesConfigChangeListener(com.sybase.stf.jmt.models.IFacesConfigChangeListener)
	 */
	public void removeFacesConfigChangeListener(
			IFacesConfigChangeListener listener) {
		listenerList.remove(listener);
	}

	// ------------------------------------------------------------------------------------------------------
	/**
	 * Saves all the configuration resources to the local file system
	 */
	// public void save()
	// {
	// ResourceSet rset = _editingDomain.getResourceSet();
	// EList resources = rset.getResources();
	// for (int r = 0; r < resources.size(); r++)
	// {
	// Resource resource = (Resource)resources.get(r);
	// if (resource.isModified())
	// {
	// FacesConfig config = new FacesConfig(resource);
	// try
	// {
	// config.save();
	// }
	// catch (IOException e)
	// {
	// _log.error("FacesConfig.SaveError", e);
	// }
	// }
	// }
	// this.fireFacesConfigChanged();
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.models.IFacesConfigManager#beginModify()
	 */
	// public void beginModify()
	// {
	// // do nothing for now.
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.models.IFacesConfigManager#endModify()
	 */
	// public void endModify()
	// {
	// save();
	// }
}
