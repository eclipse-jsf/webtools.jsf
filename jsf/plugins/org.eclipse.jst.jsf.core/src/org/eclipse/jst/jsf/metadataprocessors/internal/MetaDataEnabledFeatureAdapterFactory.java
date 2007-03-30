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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.IMetaDataEnabledFeature;
import org.osgi.framework.Bundle;

/**
 * Factory producing <code>IMetaDataEnabledFeature</code>s from 
 * <code>IType</code> objects that implement a specified feature
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public final class MetaDataEnabledFeatureAdapterFactory {
	private static MetaDataEnabledFeatureAdapterFactory INSTANCE;
	
	/**
	 * @return singleton instance
	 */
	public static MetaDataEnabledFeatureAdapterFactory getInstance(){
		if (INSTANCE == null){
			INSTANCE = new MetaDataEnabledFeatureAdapterFactory();	
		}
		return INSTANCE;
	}
	
	private MetaDataEnabledFeatureAdapterFactory(){
		super();
	}
	
	/**
	 * Given the feature extension, create and return the {@link IMetaDataEnabledFeature} for a given processing feature
	 * @param feature
	 * @param processingFeature class
	 * @return IMetaDataEnabledFeature
	 */
	public IMetaDataEnabledFeature getFeatureAdapter(IMetaDataEnabledFeatureExtension feature, Class processingFeature){ 
		if (feature != null){
			return createFeature(feature, processingFeature);			
		}
		return null;
	}

	private IMetaDataEnabledFeature createFeature(IMetaDataEnabledFeatureExtension featureExt, Class processingFeature){
		String className = featureExt.getClassName();
		try {
			Bundle bundle =Platform.getBundle(featureExt.getBundleID());
			if (bundle == null){
				JSFCorePlugin.log(IStatus.ERROR, featureExt.getBundleID() + " could not be created to load " + className);
				return null;
			}
			Class klass = bundle.loadClass(className);
			if (klass != null){
				if (!IMetaDataEnabledFeature.class.isAssignableFrom(klass)){
					JSFCorePlugin.log(IStatus.INFO, className + " is not a IMetaDataEnabledFeature. " + featureExt.getBundleID() +" : " + featureExt.getTypeID());
				} 
				else if (processingFeature.isAssignableFrom(klass)){
					IMetaDataEnabledFeature obj = (IMetaDataEnabledFeature)klass.newInstance();
//					obj.setBundleID(featureExt.getBundleID());
					return obj;
				}
			}
		} catch (ClassNotFoundException e) {
			JSFCorePlugin.log(IStatus.ERROR, className + " was not found in " + featureExt.getBundleID() +" for " + featureExt.getTypeID());
		} catch (InstantiationException e) {
			JSFCorePlugin.log(IStatus.ERROR, "InstantiationException: " + className + " in " + featureExt.getBundleID() +" for " + featureExt.getTypeID());
		} catch (IllegalAccessException e) {
			JSFCorePlugin.log(IStatus.ERROR,  "IllegalAccessException: " + className + " in " + featureExt.getBundleID() +" for " + featureExt.getTypeID());
		}
		return null;
	}

}
