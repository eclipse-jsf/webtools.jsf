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
import java.util.List;
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
	 * Collection of {@link IJSFAppConfigLocater} instances.
	 */
	protected ArrayList configLocaters = null;

	/**
	 * Collection of {@link IJSFAppConfigProvidersChangeListener} instances.
	 */
	protected ArrayList configProvidersChangeListeners = null;

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
	 * 	<li>creating configProvidersChangeListeners collection</li>
	 * 	<li>creating and populating configLocaters collection</li>
	 * 	<li>invoking the startLocating() method on all configLocaters</li>
	 * 	<li>setting instance as a session property of the IProject instance</li>
	 * </ul>
	 */
	protected void initialize() {
		//create collections
		configProvidersChangeListeners = new ArrayList();
		configLocaters = new ArrayList();
		//populate initial set of locaters
		populateConfigLocaters();
		//instruct locaters to start locating
		startConfigLocaters();
		//set as session property of project
		setAsSessionProperty();
	}

	/**
	 * Populates configLocaters Set with "built-in" set of
	 * {@link IJSFAppConfigLocater} implementations.
	 */
	protected void populateConfigLocaters() {
		//default ("/WEB-INF/faces-config.xml") locater
		IJSFAppConfigLocater defaultConfigLocater = new DefaultJSFAppConfigLocater();
		defaultConfigLocater.setJSFAppConfigManager(this);
		configLocaters.add(defaultConfigLocater);
		//web.xml context-parameter specified locater
		IJSFAppConfigLocater contextParamConfigLocater = new ContextParamSpecifiedJSFAppConfigLocater();
		contextParamConfigLocater.setJSFAppConfigManager(this);
		configLocaters.add(contextParamConfigLocater);
		//runtime classpath locater
		IJSFAppConfigLocater classpathConfigLocater = new RuntimeClasspathJSFAppConfigLocater();
		classpathConfigLocater.setJSFAppConfigManager(this);
		configLocaters.add(classpathConfigLocater);
	}

	/**
	 * Instructs set of {@link IJSFAppConfigLocater} instances to start
	 * locating JSF application configuration resources.
	 */
	protected void startConfigLocaters() {
		Iterator itConfigLocaters = configLocaters.iterator();
		while (itConfigLocaters.hasNext()) {
			IJSFAppConfigLocater configLocater = (IJSFAppConfigLocater)itConfigLocaters.next();
			configLocater.startLocating();
		}
	}

	/**
	 * Instructs set of {@link IJSFAppConfigLocater} instances to stop
	 * locating JSF application configuration resources.
	 */
	protected void stopConfigLocaters() {
		Iterator itConfigLocaters = configLocaters.iterator();
		while (itConfigLocaters.hasNext()) {
			IJSFAppConfigLocater configLocater = (IJSFAppConfigLocater)itConfigLocaters.next();
			configLocater.stopLocating();
		}
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
	public void notifyJSFAppConfigProvidersChangeListeners(IJSFAppConfigProvider configProvider, int eventType) {
		JSFAppConfigProvidersChangeEvent event = new JSFAppConfigProvidersChangeEvent(configProvider, eventType);
		Iterator itListeners = configProvidersChangeListeners.iterator();
		while (itListeners.hasNext()) {
			IJSFAppConfigProvidersChangeListener listener =
				(IJSFAppConfigProvidersChangeListener)itListeners.next();
			listener.changedJSFAppConfigProviders(event);
		}
	}

	/**
	 * Gets all {@link IJSFAppConfigProvider} instances from all
	 * {@link IJSFAppConfigLocater} instances.
	 * 
	 * @return Set of all {@link IJSFAppConfigProvider} instances.
	 */
	public Set getJSFAppConfigProviders() {
		Set allConfigProviders = new LinkedHashSet();
		Iterator itConfigLocaters = configLocaters.iterator();
		while (itConfigLocaters.hasNext()) {
			IJSFAppConfigLocater configLocater = (IJSFAppConfigLocater)itConfigLocaters.next();
			allConfigProviders.addAll(configLocater.getJSFAppConfigProviders());
		}
		return allConfigProviders;
	}

	/**
	 * Gets all {@link FacesConfigType} instances from all
	 * {@link IJSFAppConfigProvider} instances.
	 * 
	 * @return List of all {@link FacesConfigType} instances.
	 */
	public List getFacesConfigModels() {
		ArrayList facesConfigModels = new ArrayList();
		Iterator itConfigProviders = getJSFAppConfigProviders().iterator();
		while (itConfigProviders.hasNext()) {
			IJSFAppConfigProvider configProvider = (IJSFAppConfigProvider)itConfigProviders.next();
			FacesConfigType facesConfig = configProvider.getFacesConfigModel();
			if (facesConfig != null) {
				facesConfigModels.add(facesConfig);
			}
		}
		return facesConfigModels;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() {
		//remove session property from project
		unsetAsSessionProperty();
		//instruct locaters to stop locating
		stopConfigLocaters();
		//clear collections
		configLocaters.clear();
		configProvidersChangeListeners.clear();
	}

}
