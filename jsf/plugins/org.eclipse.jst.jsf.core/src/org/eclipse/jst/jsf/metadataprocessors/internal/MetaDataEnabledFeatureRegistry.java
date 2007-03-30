/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.metadataprocessors.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/**
 * Registry of <code>AbstractMetaDataEnabledType</code>s loaded from 
 * the <code>MetaDataEnabledFeatures</code> extension point
 * 
 * A map of features keyed by type id
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public class MetaDataEnabledFeatureRegistry{
	private static final String EXTPTID = "MetaDataEnabledFeatures";
	private Map featuresMap;
	private List EMPTY_LIST = new ArrayList(0);
	
	private static MetaDataEnabledFeatureRegistry INSTANCE;
	
	/**
	 * @return the singleton instance of the MetaDataEnabledFeatureRegistry
	 */
	public static synchronized MetaDataEnabledFeatureRegistry getInstance(){
		if (INSTANCE == null){
			INSTANCE = new MetaDataEnabledFeatureRegistry();	
		}
		return INSTANCE;
	}
	
	private MetaDataEnabledFeatureRegistry(){
		featuresMap = new HashMap();
		readRegistry();		
	}
	
	/**
	 * Reads the MetaDataEnabledFeatures extensions into a registry
	 */
	protected void readRegistry() {
		try {
			IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(JSFCorePlugin.PLUGIN_ID, EXTPTID);
			IExtension[] extensions = point.getExtensions();
			for (int i=0;i < extensions.length;i++){
				IExtension ext = extensions[i];
				for (int j=0;j < ext.getConfigurationElements().length;j++){
					final String bundleId = ext.getConfigurationElements()[j].getContributor().getName();
					final String id = ext.getConfigurationElements()[j].getAttribute("typeid");
					final String klass = ext.getConfigurationElements()[j].getAttribute("class");
					registerFeature(bundleId, id, klass);
				}
			}
		} catch (InvalidRegistryObjectException e) {
			JSFCorePlugin.log(e, "Unable to read " + JSFCorePlugin.PLUGIN_ID + EXTPTID + " registry");
		}
	}
	
	/**
	 * Create {@link IMetaDataEnabledFeatureExtension}s and add to registry
	 * @param bundleID
	 * @param typeId
	 * @param klass
	 */
	protected void registerFeature(String bundleID, String typeId, String klass){
		IMetaDataEnabledFeatureExtension aFeature = new MetaDataEnabledFeatureExtension(bundleID, typeId, klass);
		if (!featuresMap.containsKey(typeId)){
			List list = new ArrayList();
			list.add(aFeature);
			featuresMap.put(typeId, list);
		}
		else {
			List list = (List)featuresMap.get(typeId);
			list.add(aFeature);
		}
	}

	/**
	 * @param typeId
	 * @return List of <code>AbstractMetaDataEnabledRuntimeTypeExtensions</code>
	 * for a given by type id
	 */
	public List getFeatures(String typeId) {
		if (featuresMap.containsKey(typeId))
        {
			return (List)featuresMap.get(typeId);			
        }
		return EMPTY_LIST;
	}
}
