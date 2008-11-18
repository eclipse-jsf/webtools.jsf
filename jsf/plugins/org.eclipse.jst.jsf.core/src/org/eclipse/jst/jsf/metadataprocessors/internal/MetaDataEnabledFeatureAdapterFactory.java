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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;
import org.eclipse.jst.jsf.metadataprocessors.IType;
import org.eclipse.jst.jsf.metadataprocessors.ITypeDescriptor;
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
	private Map<String, Class> typesCache;
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
		typesCache = new HashMap<String, Class>();
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

	/**
	 * Given the feature extension, create and return the {@link IMetaDataEnabledFeature} for a given processing feature if
	 * that the type that the feature is bound to is a subclass of the the type
	 * @param feature
	 * @param processingFeature class
	 * @param type
	 * @return IMetaDataEnabledFeature.  <br>Will return null if the type that the feature extension is bound to, 
	 * is not a subclass of the supplied type
	 */
	public IMetaDataEnabledFeature getFeatureAdapterForSubclass(IMetaDataEnabledFeatureExtension feature, Class processingFeature, IType type){ 
		if (feature != null ){
			ITypeDescriptor aType = AttributeValueRuntimeTypeFactory.getInstance().getType(feature.getTypeID());
			Class klass = aType.getTypeExtension().getClass();
			if (klass.asSubclass(type.getClass()) != null)
				return createFeature(feature, processingFeature);			
		}
		return null;
	}
	
//	private Class getOrCreateTypeClassFor(IMetaDataEnabledFeatureExtension featureExt){
//		Class klass = null;
//		String className;
//		ITypeDescriptor type = AttributeValueRuntimeTypeFactory.getInstance().getType(featureExt.getTypeID());
//		try {
//			if (! typesCache.containsKey(featureExt.getTypeID())){
//				Bundle bundle =Platform.getBundle(featureExt.getBundleID());
//				if (bundle == null){
//					JSFCorePlugin.log(IStatus.ERROR, featureExt.getBundleID() + " could not be created to load " + className);
//					return null;
//				}
//				klass = bundle.loadClass(className);
//				if (klass != null){
//					typesCache.put(featureExt.getTypeID(), klass);
//				}
//			}
//			else 
//				klass = typesCache.get(featureExt.getTypeID());
//			
//			return klass;
//			if (!IMetaDataEnabledFeature.class.isAssignableFrom(klass)){
//				JSFCorePlugin.log(IStatus.INFO, className + " is not a IMetaDataEnabledFeature. " + featureExt.getBundleID() +" : " + featureExt.getTypeID());
//			} 
//			else if (klass != null && processingFeature.isAssignableFrom(klass)){
//				IMetaDataEnabledFeature obj = (IMetaDataEnabledFeature)klass.newInstance();
//				return obj;
//			}
//						
//		} catch (ClassNotFoundException e) {
//			JSFCorePlugin.log(IStatus.ERROR, className + " was not found in " + featureExt.getBundleID() +" for " + featureExt.getTypeID());
//		} catch (InstantiationException e) {
//			JSFCorePlugin.log(IStatus.ERROR, "InstantiationException: " + className + " in " + featureExt.getBundleID() +" for " + featureExt.getTypeID());
//		} catch (IllegalAccessException e) {
//			JSFCorePlugin.log(IStatus.ERROR,  "IllegalAccessException: " + className + " in " + featureExt.getBundleID() +" for " + featureExt.getTypeID());
//		}
//		return null;
//	}
	
	private IMetaDataEnabledFeature createFeature(IMetaDataEnabledFeatureExtension featureExt, Class processingFeature){
		String className = featureExt.getClassName();
		Class klass = null;
		try {
			if (! typesCache.containsKey(featureExt.getTypeID())){
				Bundle bundle =Platform.getBundle(featureExt.getBundleID());
				if (bundle == null){
					JSFCorePlugin.log(IStatus.ERROR, featureExt.getBundleID() + " could not be created to load " + className); //$NON-NLS-1$
					return null;
				}
				klass = bundle.loadClass(className);
				if (klass != null){
					typesCache.put(featureExt.getTypeID(), klass);
				}
			}
			else 
				klass = typesCache.get(featureExt.getTypeID());
			
			if (!IMetaDataEnabledFeature.class.isAssignableFrom(klass)){
				JSFCorePlugin.log(IStatus.INFO, className + " is not a IMetaDataEnabledFeature. " + featureExt.getBundleID() +" : " + featureExt.getTypeID()); //$NON-NLS-1$ //$NON-NLS-2$
			} 
			else if (klass != null && processingFeature.isAssignableFrom(klass)){
				IMetaDataEnabledFeature obj = (IMetaDataEnabledFeature)klass.newInstance();
				return obj;
			}
						
		} catch (ClassNotFoundException e) {
			JSFCorePlugin.log(IStatus.ERROR, className + " was not found in " + featureExt.getBundleID() +" for " + featureExt.getTypeID()); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (InstantiationException e) {
			JSFCorePlugin.log(IStatus.ERROR, "InstantiationException: " + className + " in " + featureExt.getBundleID() +" for " + featureExt.getTypeID()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} catch (IllegalAccessException e) {
			JSFCorePlugin.log(IStatus.ERROR,  "IllegalAccessException: " + className + " in " + featureExt.getBundleID() +" for " + featureExt.getTypeID()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		return null;
	}

}
