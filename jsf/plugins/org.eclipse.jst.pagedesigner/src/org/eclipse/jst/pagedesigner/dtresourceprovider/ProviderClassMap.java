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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;

/**
 * Used to Map a String ID to a List of Classes and provide convenient access.
 * 
 * @author Ian Trimble - Oracle
 */
public class ProviderClassMap {

	private Map<String, List<Class>> idToClassListMap;
	private Logger log = PDPlugin.getLogger(ProviderClassMap.class);

	/**
	 * Creates an instance.
	 */
	public ProviderClassMap() {
		idToClassListMap = new HashMap<String, List<Class>>();
	}

	/**
	 * Gets the List of Classes associated with the specified ID.
	 * 
	 * @param id ID for which to get the List.
	 * @return The List of Classes associated with the specified ID. List may be
	 * empty but will not be null.
	 */
	public List<Class> get(String id) {
		List<Class> classList = idToClassListMap.get(id);
		if (classList == null) {
			classList = new ArrayList<Class>();
			idToClassListMap.put(id, classList);
		}
		return classList;
	}

	/**
	 * Adds the specified Class to the List associated with the specified ID.
	 * 
	 * @param id ID for which to associate the specified Class.
	 * @param clazz Class to add to the List associated with the specified ID.
	 * @return true (as per the general contract of the Collection.add method). 
	 */
	public boolean add(String id, Class clazz) {
		List<Class> classList = get(id);
		return classList.add(clazz);
	}

	/**
	 * Instantiates all classes associated with the specified ID and returns a
	 * List of IDTResourceProvider instances.
	 * 
	 * @param id ID for which to instantiate all Classes.
	 * @return a List of IDTResourceProvider instances associated with the
	 * specified ID. List may be empty but will not be null.
	 */
	public List<IDTResourceProvider> createInstances(String id) {
		List<IDTResourceProvider> providers = new ArrayList<IDTResourceProvider>();
		List<Class> classList = get(id);
		for (Class providerClass: classList) {
			try {
				Object objProvider = providerClass.newInstance();
				if (objProvider instanceof IDTResourceProvider) {
					providers.add((IDTResourceProvider)objProvider);
				} else {
					log.error("Warning.ProviderClassMap.NotIDTResourceProvider", providerClass.getName()); //$NON-NLS-1$
				}
			} catch(IllegalAccessException iae) {
				log.error("Warning.ProviderClassMap.IllegalAccess", providerClass.getName(), id, iae); //$NON-NLS-1$
			} catch(InstantiationException ie) {
				log.error("Warning.ProviderClassMap.Instantiation", providerClass.getName(), id, ie); //$NON-NLS-1$
			}
		}
		return providers;
	}

	/**
	 * Instantiates all registered IDTResourceProviders and returns as a List.
	 * 
	 * @return List of all registered IDTResourceProviders. List may be empty
	 * but will not be null.
	 */
	public List<IDTResourceProvider> createInstances() {
		List<IDTResourceProvider> providers = new ArrayList<IDTResourceProvider>();
		Set<String> idSet = idToClassListMap.keySet();
		for (String curId: idSet) {
			List<IDTResourceProvider> providersForCurId = createInstances(curId);
			providers.addAll(providersForCurId);
		}
		return providers;
	}

}
