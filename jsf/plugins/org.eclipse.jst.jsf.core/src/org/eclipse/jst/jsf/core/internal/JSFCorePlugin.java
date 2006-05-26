/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *    Ian Trimble - JSFLibraryRegistry work
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.internal;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryFactory;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.adapter.MaintainDefaultImplementationAdapter;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryPackageImpl;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.util.JSFLibraryRegistryResourceFactoryImpl;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.util.JSFLibraryRegistryResourceImpl;
import org.eclipse.jst.jsf.core.internal.provisional.jsflibraryregistry.PluginProvidedJSFLibraryCreationHelper;
import org.eclipse.wst.common.frameworks.internal.WTPPlugin;
import org.osgi.framework.BundleContext;

/**
 * JSF Core plugin.
 * 
 * @author Gerry Kessler - Oracle, Ian Trimble - Oracle
 */
public class JSFCorePlugin extends WTPPlugin {
	public static final String PLUGIN_ID = "org.eclipse.jst.jsf.core";//org.eclipse.jst.jsf.core.internal.JSFCorePlugin"; //$NON-NLS-1$

	public static final String FACET_ID = "jst.jsf"; //$NON-NLS-1$
	
	// The shared instance.
	private static JSFCorePlugin plugin;

	// The workspace-relative part of the URL of the JSF Library Registry
	// persistence store.
	private static final String JSF_LIBRARY_REGISTRY_URL = ".metadata/.plugins/org.eclipse.jst.jsf.core/JSFLibraryRegistry.xml"; //$NON-NLS-1$

	// The NS URI of the JSF Library Registry's Ecore package. (Must match
	// setting on package in Ecore model.)
	private static final String JSF_LIBRARY_REGISTRY_NSURI = "http://www.eclipse.org/webtools/jsf/schema/jsflibraryregistry.xsd"; //$NON-NLS-1$

	public static final String LIB_EXT_PT = "jsfLibraries"; //$NON-NLS-1$

	// The JSF Library Registry EMF resource instance.
	private JSFLibraryRegistryResourceImpl jsfLibraryRegistryResource = null;

	// The JSF Library Registry instance.
	private JSFLibraryRegistry jsfLibraryRegistry = null;

	/**
	 * The constructor.
	 */
	public JSFCorePlugin() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		loadJSFLibraryRegistry();
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		saveJSFLibraryRegistry();
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static JSFCorePlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the JSFLibraryRegistry EMF object.
	 * 
	 * @return the JSFLibraryRegistry EMF object.
	 */
	public JSFLibraryRegistry getJSFLibraryRegistry() {
		return jsfLibraryRegistry;
	}

	/**
	 * Loads the JSFLibraryRegistry EMF object from plugin-specfic workspace
	 * settings location. (Called from start(BundleContext).)
	 */
	public void loadJSFLibraryRegistry() {
		try {
			URL jsfLibRegURL = new URL(Platform.getInstanceLocation().getURL(), JSF_LIBRARY_REGISTRY_URL);
			URI jsfLibRegURI = URI.createURI(jsfLibRegURL.toString());
			EPackage.Registry.INSTANCE.put(JSF_LIBRARY_REGISTRY_NSURI, JSFLibraryRegistryPackageImpl.init());
			JSFLibraryRegistryResourceFactoryImpl resourceFactory = new JSFLibraryRegistryResourceFactoryImpl();
			jsfLibraryRegistryResource = (JSFLibraryRegistryResourceImpl)resourceFactory.createResource(jsfLibRegURI);
			try {
				Map options = new HashMap();
				//disable notifications during load to avoid changing stored default implementation
				options.put(XMLResource.OPTION_DISABLE_NOTIFY, Boolean.TRUE);
				jsfLibraryRegistryResource.load(options);
				jsfLibraryRegistry = (JSFLibraryRegistry)jsfLibraryRegistryResource.getContents().get(0);
				loadJSFLibraryExtensions();
			} catch(IOException ioe) {
				log(IStatus.INFO, Messages.JSFLibraryRegistry_NoLoadCreatingNew, ioe);
				jsfLibraryRegistry = JSFLibraryRegistryFactory.eINSTANCE.createJSFLibraryRegistry();
				jsfLibraryRegistryResource.getContents().add(jsfLibraryRegistry);
				loadJSFLibraryExtensions();
			}
			//add adapter to maintain default implementation
			if (jsfLibraryRegistry != null) {
				jsfLibraryRegistry.eAdapters().add(MaintainDefaultImplementationAdapter.getInstance());
			}
		} catch(MalformedURLException mue) {
			log(IStatus.ERROR, Messages.JSFLibraryRegistry_ErrorCreatingURL, mue);
		}
	}

	/**
	 * Creates library registry items from extension points.
	 */
	private void loadJSFLibraryExtensions() {
		try {
			IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(JSFCorePlugin.PLUGIN_ID, JSFCorePlugin.LIB_EXT_PT);
			IExtension[] extensions = point.getExtensions();
			for (int i=0;i < extensions.length;i++){
				IExtension ext = extensions[i];
				for (int j=0;j < ext.getConfigurationElements().length;j++){
					PluginProvidedJSFLibraryCreationHelper newLibCreator = new PluginProvidedJSFLibraryCreationHelper(ext.getConfigurationElements()[j]);						
					JSFLibrary newLib = newLibCreator.create();
					if (newLib != null)
						jsfLibraryRegistry.addJSFLibrary(newLib);
				}
			}
		} catch (InvalidRegistryObjectException e) {
			log(IStatus.ERROR, Messages.JSFLibraryRegistry_ErrorLoadingFromExtPt, e);
		}
	}

	/**
	 * Saves the JSFLibraryRegistry EMF object from plugin-specfic workspace
	 * settings location. (Called from stop(BundleContext).)
	 */
	public boolean saveJSFLibraryRegistry() {
		boolean saved = false;
		if (jsfLibraryRegistryResource != null) {
			try {
				jsfLibraryRegistryResource.save(Collections.EMPTY_MAP);
				saved = true;
			} catch(IOException ioe) {
				log(IStatus.ERROR, Messages.JSFLibraryRegistry_ErrorSaving, ioe);
			}
		} else {
			log(IStatus.ERROR, Messages.JSFLibraryRegistry_ErrorSaving);
		}
		return saved;
	}

	public static void log(final Exception e, final String msg) {
		final ILog log = getDefault().getLog();

		log.log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, msg, e));
	}

	/**
	 * Logs using the default ILog implementation provided by getLog().
	 * 
	 * @param severity Severity (IStatus constant) of log entry
	 * @param message Human-readable message describing log entry
	 * @param ex Throwable instance (can be null)
	 */
	public static void log(int severity, String message, Throwable ex) {
		getDefault().getLog().log(new Status(severity, PLUGIN_ID, IStatus.OK, message, ex));
	}

	/**
	 * Logs using the default ILog implementation provided by getLog().
	 * 
	 * @param severity Severity (IStatus constant) of log entry
	 * @param message Human-readable message describing log entry
	 */
	public static void log(int severity, String message) {
		log(severity, message, null);
	}

	public String getPluginID() {
		return PLUGIN_ID;
	}
}
