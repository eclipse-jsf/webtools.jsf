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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
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
		//implicit runtime-provided configuration
		IJSFAppConfigLocater implicitRuntimeConfigLocater = new ImplicitRuntimeJSFAppConfigLocater();
		implicitRuntimeConfigLocater.setJSFAppConfigManager(this);
		configLocaters.add(implicitRuntimeConfigLocater);
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
		List facesConfigModels = new ArrayList();
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

	/**
	 * Gets list of all ManagedBeanType instances from all known faces-config
	 * models; list may be empty.
	 * 
	 * @return List of all ManagedBeanType instances from all known
	 * faces-config models (list may be empty).
	 */
	public List getManagedBeans() {
		List allManagedBeans = new ArrayList();
		List facesConfigs = getFacesConfigModels();
		Iterator itFacesConfigs = facesConfigs.iterator();
		while (itFacesConfigs.hasNext()) {
			FacesConfigType facesConfig = (FacesConfigType)itFacesConfigs.next();
			EList managedBeans = facesConfig.getManagedBean();
			allManagedBeans.addAll(managedBeans);
		}
		return allManagedBeans;
	}

	/**
	 * Gets list of all ValidatorType instances from all known faces-config
	 * models; list may be empty.
	 * 
	 * @return List of all ValidatorType instances from all known faces-config
	 * models (list may be empty).
	 */
	public List getValidators() {
		List allValidators = new ArrayList();
		List facesConfigs = getFacesConfigModels();
		Iterator itFacesConfigs = facesConfigs.iterator();
		while (itFacesConfigs.hasNext()) {
			FacesConfigType facesConfig = (FacesConfigType)itFacesConfigs.next();
			EList validators = facesConfig.getValidator();
			allValidators.addAll(validators);
		}
		return allValidators;
	}

	/**
	 * Gets list of all ConverterType instances from all known faces-config
	 * models; list may be empty.
	 * 
	 * @return List of all ConverterType instances from all known faces-config
	 * models (list may be empty).
	 */
	public List getConverters() {
		List allConverters = new ArrayList();
		List facesConfigs = getFacesConfigModels();
		Iterator itFacesConfigs = facesConfigs.iterator();
		while (itFacesConfigs.hasNext()) {
			FacesConfigType facesConfig = (FacesConfigType)itFacesConfigs.next();
			EList converters = facesConfig.getConverter();
			allConverters.addAll(converters);
		}
		return allConverters;
	}

	/**
	 * Gets list of all NavigationRuleType instances from all known
	 * faces-config models; list may be empty.
	 * 
	 * @return List of all NavigationRuleType instances from all known
	 * faces-config models (list may be empty).
	 */
	public List getNavigationRules() {
		List allNavigationRules = new ArrayList();
		List facesConfigs = getFacesConfigModels();
		Iterator itFacesConfigs = facesConfigs.iterator();
		while (itFacesConfigs.hasNext()) {
			FacesConfigType facesConfig = (FacesConfigType)itFacesConfigs.next();
			EList navigationRules = facesConfig.getNavigationRule();
			allNavigationRules.addAll(navigationRules);
		}
		return allNavigationRules;
	}

	/**
	 * Gets list of all NavigationRuleType instances from all known
	 * faces-config models where the navigation-rule's from-view-id value
	 * matches the web content folder-relative value of the passed IFile
	 * instance; list may be empty. Matching is performed in the same manner
	 * as for a JSF implementation's default NavigationHandler.
	 * 
	 * @param pageFile IFile instance to match against the from-view-id value
	 * of all NavigationRuleType instances. File is assumed to be relative to
	 * the web content folder, but may be expressed in a more complete form;
	 * its path will be calculated relative to the web content folder.
	 * @return List of all NavigationRuleType instances from all known
	 * faces-config models where the navigation-rule's from-view-id value
	 * matches the web content folder-relative value of the passed IFile
	 * instance (list may be empty).
	 */
	public List getNavigationRulesForPage(IFile pageFile) {
		List navigationRulesForPage = new ArrayList();
		IPath pageFilePath = JSFAppConfigUtils.getWebContentFolderRelativePath(pageFile);
		String pageFileString = pageFilePath.toString();
		if (!pageFileString.startsWith("/")) {
			pageFileString = "/" + pageFileString;
		}
		List navigationRules = getNavigationRules();
		Iterator itNavigationRules = navigationRules.iterator();
		while (itNavigationRules.hasNext()) {
			NavigationRuleType navigationRule = (NavigationRuleType)itNavigationRules.next();
			FromViewIdType fromViewIdType = navigationRule.getFromViewId();
			if (fromViewIdType != null) {
				String fromViewId = fromViewIdType.getTextContent();
				if (fromViewId != null && fromViewId.length() > 0) {
					if (!fromViewId.equals("*")) { //$NON-NLS-1$
						if (fromViewId.equals(pageFileString)) {
							//exact match
							navigationRulesForPage.add(navigationRule);
						} else if (fromViewId.endsWith("*")) { //$NON-NLS-1$
							String prefixFromViewId = fromViewId.substring(0, fromViewId.length() - 1);
							if (pageFileString.startsWith(prefixFromViewId)) {
								//prefix match
								navigationRulesForPage.add(navigationRule);
							}
						}
					} else {
						//from-view-id == "*" - matches all pages
						navigationRulesForPage.add(navigationRule);
					}
				}
			} else {
				//no from-view-id element - matches all pages
				navigationRulesForPage.add(navigationRule);
			}
		}
		return navigationRulesForPage;
	}

}
