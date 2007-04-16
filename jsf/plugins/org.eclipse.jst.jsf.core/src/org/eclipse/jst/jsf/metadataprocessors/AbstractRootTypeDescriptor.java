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

package org.eclipse.jst.jsf.metadataprocessors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jst.jsf.metadataprocessors.internal.IMetaDataEnabledFeatureExtension;
import org.eclipse.jst.jsf.metadataprocessors.internal.MetaDataEnabledFeatureAdapterFactory;
import org.eclipse.jst.jsf.metadataprocessors.internal.MetaDataEnabledFeatureRegistry;


/**
 * Default implementation that a metadata type should strongly consider for using as it's root.
 * This implementation allows for a class implementing {@link ITypeDescriptor} to also contain features so
 * that it is not necessary to declare the features using the MetaDataEnabledFeatures ext-pt 
 * 
 * @author Gerry Kessler - Oracle
 */
public abstract class AbstractRootTypeDescriptor extends AbstractMetaDataEnabledFeature
		implements ITypeDescriptor {
	
	private IType type;
	
	/**
	 * Default implementation that will return <code>this</code> if it supports the processing feature,
	 * and all {@link IMetaDataEnabledFeature}s that also support this feature on this type.   The caller can determine
	 * which one or more of the returned processors to use.
	 *    
	 * @param processingFeature class
	 * @return List of <code>IMetaDataEnabledFeature</code>s that support the
	 * specified featureType interface.  
	 * 
	 * @see org.eclipse.jst.jsf.metadataprocessors.ITypeDescriptor#getFeatureAdapters(java.lang.Class)
	 */
	public List getFeatureAdapters(Class processingFeature) {
		Assert.isTrue(processingFeature.isInterface());
		List ret = new ArrayList(3);
		if (processingFeature.isInstance(this)) {
//			((IMetaDataEnabledFeature)this).setBundleID(type.getBundleID());
			ret.add(this);
		}
		
		//add extensions here
		ret.addAll(findMetaDataEnabledFeaturesForThisType(processingFeature));
		return ret;

	}
	
	/**
	 * This implementation relies on the the feature implementor using a subclass of AbstractRootTypeDescriptor 
	 * which implements {@link IMetaDataEnabledFeature}.
	 * 
	 * @param processingFeature
	 * @return list of <code>IMetaDataEnabledFeature</code>s
	 */
	protected final List findMetaDataEnabledFeaturesForThisType(Class processingFeature) {
		List ret = new ArrayList(3); 
		List extensions = MetaDataEnabledFeatureRegistry.getInstance()
							.getFeatures(getTypeExtension().getTypeID());

		if (!extensions.isEmpty()){
			for (int i=0;i<extensions.size();i++){
				IMetaDataEnabledFeatureExtension aFeature = (IMetaDataEnabledFeatureExtension)extensions.get(i);
				IMetaDataEnabledFeature feature = MetaDataEnabledFeatureAdapterFactory.getInstance().getFeatureAdapter(aFeature, processingFeature);
				if (feature != null && processingFeature.isInstance(feature)){
					ret.add(feature);				
				}
			}
		}
		return ret;
	}	

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.provisional.ITypeDescriptor#getRuntimeType()
	 */
	public IType getTypeExtension() {
		return type;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.provisional.ITypeDescriptor#setRuntimeType(org.eclipse.jst.jsf.metadataprocessors.internal.AbstractMetaDataEnabledType)
	 */
	public void setTypeExtension(IType type) {
		this.type = type;
	}

	

}
