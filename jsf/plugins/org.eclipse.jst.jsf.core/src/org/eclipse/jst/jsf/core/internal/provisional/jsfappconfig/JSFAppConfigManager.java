/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * JSFAppConfigManager provides an entry point to an entire JSF application
 * configuration, which is defined in one or more application configuration
 * resource files.
 * 
 * @author Ian Trimble - Oracle
 */
public class JSFAppConfigManager {

	/**
	 * Key that is used for the IProject instance's session property that
	 * holds a JSFAppConfigManager instance.
	 */
	public static final QualifiedName KEY_SESSIONPROPERTY =
		new QualifiedName(JSFCorePlugin.PLUGIN_ID, "JSFAppConfigManager"); //$NON-NLS-1$

	/**
	 * IProject instance to which this JSFAppConfigManager instance is keyed.
	 */
	protected IProject project = null;

	/**
	 * Set of {@link IJSFAppConfigProvider} instances.
	 */
	protected LinkedHashSet configProviders = null;

	/**
	 * Collection of {@link IJSFAppConfigProvidersChangeListener} instances.
	 */
	protected ArrayList configProvidersChangeListeners = null;

	/**
	 * Set of {@link IJSFAppConfigLocater} instances.
	 */
	protected LinkedHashSet configLocaters = null;

	/**
	 * Constructor is private to prevent direct instantiation; call
	 * getInstance(IProject).
	 * 
	 * @param project IProject instance to which the new JSFAppConfigManager
	 * instance is keyed.
	 */
	private JSFAppConfigManager(IProject project) {
		this.project = project;
		initialize();
	}

	/**
	 * Gets this instance's IProject instance.
	 * 
	 * @return This instance's IProject instance.
	 */
	public IProject getProject() {
		return project;
	}

	/**
	 * Initializes instance by:
	 * <ul>
	 * 	<li>creating configProviders Set</li>
	 * 	<li>creating configProvidersChangeListeners collection</li>
	 * 	<li>creating and populating configLocaters Set</li>
	 * 	<li>invoking the locateProviders(...) method on all configLocaters</li>
	 * 	<li>setting instance as a session property of the IProject instance</li>
	 * </ul>
	 */
	protected void initialize() {
		//create collections
		configProviders = new LinkedHashSet();
		configProvidersChangeListeners = new ArrayList();
		configLocaters = new LinkedHashSet();
		//populate initial Set of locaters
		populateConfigLocaters();
		//locate config providers (no need to clear; collection just created)
		locateConfigProviders(false);
		//set as session property of project
		setAsSessionProperty();
	}

	/**
	 * Populates configLocaters Set with "built-in" set of
	 * {@link IJSFAppConfigLocater} implementations.
	 */
	protected void populateConfigLocaters() {
		configLocaters.add(new DefaultJSFAppConfigLocater());
		configLocaters.add(new ContextParamSpecifiedJSFAppConfigLocater());
		configLocaters.add(new RuntimeClasspathJSFAppConfigLocater());
	}

	/**
	 * Optionally clears known {@link IJSFAppConfigProvider} instances then
	 * invokes the locate(JSFAppConfigManager) method of all known
	 * {@link IJSFAppConfigLocater} instances.
	 * 
	 * @param clearFirst If true, clears known {@link IJSFAppConfigProvider}
	 * instances before invoking known {@link IJSFAppConfigLocater} instances
	 */
	public void locateConfigProviders(boolean clearFirst) {
		//clear known providers if requested
		if (clearFirst) {
			disposeConfigProviders();
			configProviders.clear();
		}
		//invoke locateProviders method on all known locaters
		Iterator itConfigLocaters = configLocaters.iterator();
		while (itConfigLocaters.hasNext()) {
			IJSFAppConfigLocater configLocater = (IJSFAppConfigLocater)itConfigLocaters.next();
			configLocater.locateProviders(this);
		}
	}

	/**
	 * Calls releaseFacesConfigModel() method on all known
	 * {@link IJSFAppConfigProvider} instances, in preparation for clearing
	 * of known instances.
	 */
	public void disposeConfigProviders() {
		Iterator itConfigProviders = configProviders.iterator();
		while (itConfigProviders.hasNext()) {
			IJSFAppConfigProvider configProvider = (IJSFAppConfigProvider)itConfigProviders.next();
			configProvider.releaseFacesConfigModel();
		}
	}

	/**
	 * Gets a JSFAppConfigManager instance that is keyed to the passed IProject
	 * parameter. May return null if the project is not valid or if a
	 * CoreException is thrown while attempting to get or set the instance as
	 * a session property.
	 * 
	 * @param project IProject instance to which the returned
	 * JSFAppConfigManager instance is keyed
	 * @return JSFAppConfigManager instance, or null
	 */
	public static JSFAppConfigManager getInstance(IProject project) {
		JSFAppConfigManager manager = null;
		if (isValidProject(project)) {
			manager = getFromSessionProperty(project);
			if (manager == null) {
				manager = new JSFAppConfigManager(project);
			}
		}
		return manager;
	}

	/**
	 * Tests if the passed IProject instance is valid in the following ways:
	 * <ul>
	 * <li>project is not null and is accessible</li>
	 * <li>project has the "jst.jsf" facet set on it</li>
	 * </ul>
	 * 
	 * @param project IProject instance to be tested
	 * @return true if the IProject instance is valid, else false
	 */
	protected static boolean isValidProject(IProject project) {
		boolean isValid = false;
		//check for null or inaccessible project
		if (project != null && project.isAccessible()) {
			//check for "jst.jsf" facet on project
			try {
				IFacetedProject facetedProject = ProjectFacetsManager.create(project);
				if (facetedProject != null) {
					Set projectFacets = facetedProject.getProjectFacets();
					Iterator itProjectFacets = projectFacets.iterator();
					while (itProjectFacets.hasNext()) {
						IProjectFacetVersion projectFacetVersion = (IProjectFacetVersion)itProjectFacets.next();
						IProjectFacet projectFacet = projectFacetVersion.getProjectFacet();
						if ("jst.jsf".equals(projectFacet.getId())) { //$NON-NLS-1$
							isValid = true;
							break;
						}
					}
				}
			} catch(CoreException ce) {
				//log error
				JSFCorePlugin.log(IStatus.ERROR, ce.getLocalizedMessage(), ce);
			}
		}
		return isValid;
	}

	/**
	 * Attempts to get a JSFAppConfigManager instance from a session property
	 * of the passed IProject instance. Will return null if the session
	 * property has not yet been set.
	 * 
	 * @param project IProject instance from which to retrieve the
	 * JSFAppConfigManager instance
	 * @return JSFAppConfigManager instance, or null
	 */
	protected static JSFAppConfigManager getFromSessionProperty(IProject project) {
		JSFAppConfigManager manager = null;
		try {
			Object obj = project.getSessionProperty(KEY_SESSIONPROPERTY);
			if (obj != null && obj instanceof JSFAppConfigManager) {
				manager = (JSFAppConfigManager)obj;
			}
		} catch(CoreException ce) {
			//log error
			JSFCorePlugin.log(IStatus.ERROR, ce.getLocalizedMessage(), ce);
		}
		return manager;
	}

	/**
	 * Sets this JSFAppConfigManager instance as a session property of its
	 * IProject instance.
	 */
	protected void setAsSessionProperty() {
		if (project != null && project.isAccessible()) {
			try {
				project.setSessionProperty(KEY_SESSIONPROPERTY, this);
			} catch(CoreException ce) {
				//log error
				JSFCorePlugin.log(IStatus.ERROR, ce.getLocalizedMessage(), ce);
			}
		}
	}

	/**
	 * Unsets this JSFAppConfigManager instance as a session property of its
	 * IProject instance.
	 */
	protected void unsetAsSessionProperty() {
		if (project != null && project.isAccessible()) {
			try {
				project.setSessionProperty(KEY_SESSIONPROPERTY, null);
			} catch(CoreException ce) {
				//log error
				JSFCorePlugin.log(IStatus.ERROR, ce.getLocalizedMessage(), ce);
			}
		}
	}

	/**
	 * Adds an instance of IJSFAppConfigProvider to the configProviders Set.
	 * 
	 * @param configProvider Instance of IJSFAppConfigProvider.
	 * @return true if instance was added (if it did not already exist in the
	 * Set), else false.
	 */
	public boolean addJSFAppConfigProvider(IJSFAppConfigProvider configProvider) {
		boolean added = configProviders.add(configProvider);
		if (added) {
			notifyJSFAppConfigProvidersChangeListeners(
					configProvider, JSFAppConfigProvidersChangeEvent.ADDED);
		}
		return added;
	}

	/**
	 * Removes an instance of IJSFAppConfigProvider from the configProviders
	 * Set.
	 * 
	 * @param configProvider Instance of IJSFAppConfigProvider.
	 * @return true if instance was removed (if it existed in the Set), else
	 * false.
	 */
	public boolean removeJSFAppConfigProvider(IJSFAppConfigProvider configProvider) {
		if (configProvider != null) {
			configProvider.releaseFacesConfigModel();
		}
		boolean removed = configProviders.remove(configProvider);
		if (removed) {
			notifyJSFAppConfigProvidersChangeListeners(
				configProvider, JSFAppConfigProvidersChangeEvent.REMOVED);
		}
		return removed;
	}

	/**
	 * Adds an instance of {@link IJSFAppConfigProvidersChangeListener}.
	 * 
	 * @param listener An instance of {@link IJSFAppConfigProvidersChangeListener}
	 * @return true if added, else false
	 */
	public boolean addJSFAppConfigProvidersChangeListener(IJSFAppConfigProvidersChangeListener listener) {
		return configProvidersChangeListeners.add(listener);
	}

	/**
	 * Removes an instance of {@link IJSFAppConfigProvidersChangeListener}.
	 * 
	 * @param listener an instance of {@link IJSFAppConfigProvidersChangeListener}
	 * @return true if removed, else false
	 */
	public boolean removeJSFAppConfigProvidersChangeListener(IJSFAppConfigProvidersChangeListener listener) {
		return configProvidersChangeListeners.remove(listener);
	}

	/**
	 * Notifies all {@link IJSFAppConfigProvidersChangeListener} instances of
	 * a change in the Set of {@link IJSFAppConfigProvider} instances.
	 * 
	 * @param configProvider {@link IJSFAppConfigProvider} instance that has
	 * changed
	 * @param eventType Event type
	 */
	protected void notifyJSFAppConfigProvidersChangeListeners(IJSFAppConfigProvider configProvider, int eventType) {
		JSFAppConfigProvidersChangeEvent event = new JSFAppConfigProvidersChangeEvent(configProvider, eventType);
		Iterator itListeners = configProvidersChangeListeners.iterator();
		while (itListeners.hasNext()) {
			IJSFAppConfigProvidersChangeListener listener =
				(IJSFAppConfigProvidersChangeListener)itListeners.next();
			listener.changedJSFAppConfigProviders(event);
		}
	}

	/**
	 * Gets array of all FacesConfigType instances for read access.
	 * 
	 * @return Array of all FacesConfigType instances for read access.
	 */
	public FacesConfigType[] getFacesConfigModelsForRead() {
		ArrayList facesConfigList = new ArrayList();
		Iterator itConfigProviders = configProviders.iterator();
		while (itConfigProviders.hasNext()) {
			IJSFAppConfigProvider configProvider = (IJSFAppConfigProvider)itConfigProviders.next();
			try {
				FacesConfigType facesConfig = configProvider.getFacesConfigModel(false);
				if (facesConfig != null) {
					facesConfigList.add(facesConfig);
				}
			} catch (InvalidWriteAccessModeException iwame) {
				//log error
				JSFCorePlugin.log(IStatus.ERROR, iwame.getLocalizedMessage(), iwame);
			}
		}
		FacesConfigType[] facesConfigs = new FacesConfigType[facesConfigList.size()];
		facesConfigs = (FacesConfigType[])facesConfigList.toArray(facesConfigs);
		return facesConfigs;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() {
		//remove session property from project
		unsetAsSessionProperty();
		//clear collections
		configLocaters.clear();
		configProvidersChangeListeners.clear();
		//release all models from providers
		disposeConfigProviders();
		configProviders.clear();
	}

}
