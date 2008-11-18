/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
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

import java.util.HashMap;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/**
 * Abstract registry of <code>AbstractMetaDataEnabledType<code>
 * mapped by type id.
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public abstract class AbstractMetaDataEnabledTypeRegistry {
	private final HashMap typeMap;  //map of types keyed by id
	private final String ext_pt_id; //type ext-pt id
	
	/**
	 * @param extPtId
	 */
	public AbstractMetaDataEnabledTypeRegistry(String extPtId){
		typeMap = new HashMap();		
		ext_pt_id = extPtId;
		
		readRegistry();
	}
	
	/**
	 * @param bundleID
	 * @param id
	 * @param klass
	 * @param runtimeType
	 */
	protected void registerType(String bundleID, String id, String klass, String runtimeType){
		AbstractMetaDataEnabledType atype = new AbstractMetaDataEnabledType(bundleID, id, klass);
		if (!typeMap.containsKey(atype.getTypeID())){
			typeMap.put(atype.getTypeID(), atype);
		}
		else {
			//is there any point in putting this more in the user's face?  this is really an internal error.
			JSFCorePlugin.log(IStatus.ERROR, "Duplicate RuntimeTypeId being registered from " + ext_pt_id + ": " + atype.getTypeID()); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	
	/**
	 * Return the metadata enabled type for the given id
	 * @param id
	 * @return type
	 */
	public AbstractMetaDataEnabledType getType(String id){
		if (typeMap.containsKey(id))
        {
			return (AbstractMetaDataEnabledType)typeMap.get(id);
        }
        return null;
	}

	/**
	 * Reads the extensions for a particular type id
	 */
	protected void readRegistry() {
		try {
			IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(JSFCorePlugin.PLUGIN_ID, ext_pt_id);
			IExtension[] extensions = point.getExtensions();
			for (int i=0;i < extensions.length;i++){
				IExtension ext = extensions[i];
				for (int j=0;j < ext.getConfigurationElements().length;j++){
					final String bundleId = ext.getConfigurationElements()[j].getContributor().getName();
					final String id = ext.getConfigurationElements()[j].getAttribute("id"); //$NON-NLS-1$
					String klass = ext.getConfigurationElements()[j].getAttribute("class"); //$NON-NLS-1$
					final String runtimeType = ext.getConfigurationElements()[j].getAttribute("runtime-type"); //$NON-NLS-1$
					if (klass == null || klass.trim().equals("")){ //$NON-NLS-1$
						klass = getDefaultClassName();
					}
					registerType(bundleId, id, klass, runtimeType);
				}
			}
		} catch (InvalidRegistryObjectException e) {
			JSFCorePlugin.log(e, "Unable to read " + JSFCorePlugin.PLUGIN_ID + ext_pt_id + " registry"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	
	/**
	 * @return default classname to use for the type
	 */
	protected abstract String getDefaultClassName();


}
