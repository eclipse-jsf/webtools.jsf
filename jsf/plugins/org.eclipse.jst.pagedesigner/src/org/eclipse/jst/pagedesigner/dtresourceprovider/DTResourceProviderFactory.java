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
package org.eclipse.jst.pagedesigner.dtresourceprovider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsp.core.taglib.ITaglibDescriptor;
import org.eclipse.jst.jsp.core.taglib.ITaglibRecord;
import org.eclipse.jst.jsp.core.taglib.TaglibIndex;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.osgi.framework.Bundle;

/**
 * Factory responsible for producing {@link IDTResourceProvider} instances from
 * <code>org.eclipse.jst.pagedesigner.pageDesignerExtension.dtResourceProvider</code>
 * extensions. 
 * 
 * @author Ian Trimble - Oracle
 */
public class DTResourceProviderFactory {

	private static DTResourceProviderFactory instance;
	private ProviderClassMap providerClassMap;
	private Map<IProject, List<IDTResourceProvider>> providerMap;
	private Logger log = PDPlugin.getLogger(DTResourceProviderFactory.class);

	private DTResourceProviderFactory() {
		//singleton - no external instantiation
		providerClassMap = new ProviderClassMap();
		loadExtensions();
		providerMap = new HashMap<IProject, List<IDTResourceProvider>>();
	}

	/**
	 * Gets the singleton instance.
	 * 
	 * @return The singleton instance.
	 */
	public static synchronized DTResourceProviderFactory getInstance() {
		if (instance == null) {
			instance = new DTResourceProviderFactory();
		}
		return instance;
	}

	/**
	 * Returns a List of IDTResourceProvider instances that are active for the
	 * specified IProject instance.
	 * 
	 * @param project IProject instance for which to get the active
	 * IDTResourceProvider instances.
	 * @return A List of IDTResourceProvider instances that are active for the
	 * specified IProject instance. List may be empty, but will not be null.
	 */
	public List<IDTResourceProvider> getActiveDTResourceProviders(IProject project) {
		List<IDTResourceProvider> activeProviders = new ArrayList<IDTResourceProvider>();
		if (project != null) {
			ITaglibRecord[] taglibRecords = TaglibIndex.getAvailableTaglibRecords(project.getFullPath());
			if (taglibRecords != null && taglibRecords.length > 0) {
				for (ITaglibRecord taglibRecord: taglibRecords) {
					ITaglibDescriptor taglibDescriptor = taglibRecord.getDescriptor();
					if (taglibDescriptor != null) {
						String taglibURI = taglibDescriptor.getURI();
						if (taglibURI != null) {
							activeProviders.addAll(getDTResourceProviders(project, taglibURI));
						}
					}
				}
			}
		}
		return Collections.unmodifiableList(activeProviders);
	}

	/**
	 * Convenience method to determine IProject instance from the specified
	 * IDOMNode instance and then call {@link #getActiveDTResourceProviders(IProject)}.
	 * 
	 * @param domNode IDOMNode instance from which to determine IProject
	 * instance.
	 * @return A List of IDTResourceProvider instances that are active for the
	 * specified IDOMNode instance's IProject instance. List may be empty, but
	 * will not be null.
	 */
	public List<IDTResourceProvider> getActiveDTResourceProviders(IDOMNode domNode) {
		IDOMModel domModel = domNode.getModel();
		IProject project = null;
		if (domModel != null) {
			project = StructuredModelUtil.getProjectFor(domModel);
		}
		return getActiveDTResourceProviders(project);
	}

	/**
	 * Returns a List of IDTResourceProvider instances for the specified
	 * IProject instance and id.
	 * 
	 * @param project IProject instance for which a List of IDTResourceProviders
	 * is being requested.
	 * @param id Identifier for which a List of IDTResourceProvider instances is
	 * being requested.
	 * @return A List of IDTResourceProvider instances for the specified
	 * IProject instance and id. List may be empty, but will not be null.
	 */
	public synchronized List<IDTResourceProvider> getDTResourceProviders(IProject project, String id) {
		List<IDTResourceProvider> providers = new ArrayList<IDTResourceProvider>();
		List<IDTResourceProvider> projectProviders = providerMap.get(project);
		if (projectProviders == null) {
			projectProviders = providerClassMap.createInstances();
			providerMap.put(project, projectProviders);
		}
		for (IDTResourceProvider curProvider: projectProviders) {
			if (curProvider.getId().equals(id)) {
				providers.add(curProvider);
			}
		}
		return providers;
	}

	private void loadExtensions() {
		try {
			final IExtensionPoint extPoint = Platform.getExtensionRegistry().getExtensionPoint(PDPlugin.getPluginId(), IJMTConstants.EXTENSION_POINT_PAGEDESIGNER);
			final IExtension[] extensions = extPoint.getExtensions();
			for (int i = 0; i < extensions.length; i++) {
				final IExtension extension = extensions[i];
				final IConfigurationElement[] confElements = extension.getConfigurationElements();
				for (int j = 0; j < confElements.length; j++) {
					final IConfigurationElement confElement = confElements[j];
					if (confElement.getName().equals(IJMTConstants.DT_RESOURCE_PROVIDER)) {
						final String id = confElement.getAttribute("id"); //$NON-NLS-1$
						final String providerClassName = confElement.getAttribute("class"); //$NON-NLS-1$
						final Bundle bundle = Platform.getBundle(confElement.getContributor().getName());
						if (bundle != null) {
							try {
								providerClassMap.add(id, bundle.loadClass(providerClassName));
							} catch(ClassNotFoundException cnfe) {
								log.error("Warning.DTResourceProviderFactory.ClassNotFound", providerClassName, id, cnfe); //$NON-NLS-1$
							}
						}
					}
				}
			}
		} catch(InvalidRegistryObjectException iroe) {
			log.error("Warning.DTResourceProviderFactory.RegistryError", PDPlugin.getPluginId() + IJMTConstants.EXTENSION_POINT_PAGEDESIGNER + "." + IJMTConstants.DT_RESOURCE_PROVIDER, iroe); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

}
