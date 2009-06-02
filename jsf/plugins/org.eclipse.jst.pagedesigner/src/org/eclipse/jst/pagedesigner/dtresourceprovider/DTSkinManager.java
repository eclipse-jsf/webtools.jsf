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
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

/**
 * Manages IDTSkin instances and provides skin-related services.
 * 
 * @author Ian Trimble - Oracle
 */
public class DTSkinManager {

	private static Map<IProject, DTSkinManager> dtSkinManagerMap =
		new HashMap<IProject, DTSkinManager>();

	private Map<String, IDTSkin> currentDTSkinMap;

	private IProject project;

	/**
	 * Constructs an instance (no public instantiation).
	 * 
	 * @param project IProject instance for which this instance applies.
	 */
	private DTSkinManager(IProject project) {
		this.project = project;
		currentDTSkinMap = new HashMap<String, IDTSkin>();
	}

	/**
	 * Gets a DTSkinManager instance for the specified project instance.
	 * 
	 * @param aProject IProject instance for which a DTSkinManager is being
	 * requested.
	 * @return A DTSkinManager instance for the specified project instance.
	 */
	public static synchronized DTSkinManager getInstance(IProject aProject) {
		DTSkinManager dtSkinManager = dtSkinManagerMap.get(aProject);
		if (dtSkinManager == null) {
			dtSkinManager = new DTSkinManager(aProject);
			dtSkinManagerMap.put(aProject, dtSkinManager);
		}
		return dtSkinManager;
	}

	/**
	 * Gets a DTSkinManager instance for the IProject instance associated with
	 * the specified IDOMNode instance.
	 * 
	 * @param domNode IDOMNode instance for which a DTSkinManager is being
	 * requested.
	 * @return A DTSkinManager instance for the IProject instance associated
	 * with the specified IDOMNode instance.
	 */
	public static DTSkinManager getInstance(IDOMNode domNode) {
		IDOMModel domModel = domNode.getModel();
		IProject aProject = null;
		if (domModel != null) {
			aProject = StructuredModelUtil.getProjectFor(domModel);
		}
		return getInstance(aProject);
	}

	/**
	 * Returns a List of available IDTSkin instances.
	 * 
	 * @param id ID for which to get skins.
	 * @return A List of available IDTSkin instances. List may be empty, but
	 * will not be null.
	 */
	public List<IDTSkin> getSkins(String id) {
		List<IDTSkin> dtSkins = new ArrayList<IDTSkin>();
		List<IDTResourceProvider> dtResourceProviders =
			DTResourceProviderFactory.getInstance().getActiveDTResourceProviders(project);
		for (IDTResourceProvider dtResourceProvider: dtResourceProviders) {
			if (dtResourceProvider.getId().equals(id)) {
				dtSkins.addAll(dtResourceProvider.getSkins());
			}
		}
		return dtSkins;
	}

	/**
	 * Returns the current IDTSkin instance for the specified ID.
	 * 
	 * @param id The ID (typically nsURI) for which the current IDTSkin instance
	 * is being requested.
	 * @return The current IDTSkin instance for the specified ID. May return
	 * null if there is no current IDTSkin instance for the specified ID.
	 */
	public IDTSkin getCurrentSkin(String id) {
		IDTSkin dtSkin = currentDTSkinMap.get(id);
		if (dtSkin == null) {
			dtSkin = getDefaultSkin(id);
			setCurrentSkin(id, dtSkin);
		}
		return dtSkin;
	}

	/**
	 * Sets the current IDTSkin instance for the specified ID.
	 * 
	 * @param id The ID (typically nsURI) for which the current IDTSkin instance
	 * is being set.
	 * @param dtSkin The current IDTSkin instance to be set for the specified ID.
	 */
	public void setCurrentSkin(String id, IDTSkin dtSkin) {
		if (id != null) {
			IDTSkin existingDTSkin = currentDTSkinMap.get(id);
			if (existingDTSkin != null) {
				existingDTSkin.releaseResources();
			}
			if (dtSkin != null) {
				currentDTSkinMap.put(id, dtSkin);
			} else {
				currentDTSkinMap.remove(id);
			}
		}
	}

	/**
	 * Returns a List of all current DTSkin instances (one per id).
	 * 
	 * @return A List of all current DTSkin instances (one per id).
	 */
	public List<IDTSkin> getCurrentSkins() {
		List<IDTSkin> currentDTSkins = new ArrayList<IDTSkin>(); 
		List <IDTResourceProvider> dtResourceProviders =
			DTResourceProviderFactory.getInstance().getActiveDTResourceProviders(project);
		for (IDTResourceProvider dtResourceProvider: dtResourceProviders) {
			IDTSkin dtSkin = getCurrentSkin(dtResourceProvider.getId());
			if (dtSkin != null && !currentDTSkins.contains(dtSkin)) {
				currentDTSkins.add(dtSkin);
			}
		}
		return Collections.unmodifiableList(currentDTSkins);
	}

	/**
	 * Returns the default IDTSkin instance for the specified ID.
	 * 
	 * @param id The ID (typically nsURI) for which the default IDTSkin instance
	 * is being requested.
	 * @return The default IDTSkin instance for the specified ID. May return
	 * null if there is no default IDTSkin instance for the specified ID.
	 */
	public IDTSkin getDefaultSkin(String id) {
		IDTSkin firstDTSkin = null;
		IDTSkin defaultDTSkin = null;
		for (IDTSkin dtSkin: getSkins(id)) {
			if (firstDTSkin == null) {
				firstDTSkin = dtSkin;
			}
			if (dtSkin.isDefault()) {
				defaultDTSkin = dtSkin;
				break;
			}
		}
		if (defaultDTSkin == null) {
			defaultDTSkin = firstDTSkin;
		}
		return defaultDTSkin;
	}

}
