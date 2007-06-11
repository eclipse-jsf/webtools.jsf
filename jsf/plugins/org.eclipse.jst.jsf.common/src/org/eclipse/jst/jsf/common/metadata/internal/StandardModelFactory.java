/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.jst.jsf.common.metadata.internal.util.MetadataResourceImpl;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;

/**
 * Singelton that produces and loads standard metadata models.  
 * All models are loaded into the same ResourceSet 
 *
 * see Model
 */
public class StandardModelFactory {
	private static StandardModelFactory INSTANCE;
	private ExtendedMetaData extendedMetaData;
	private ResourceSet resourceSet;
	
	
	/**
	 * @return singleton instance of the metadata model factory
	 */
	public synchronized static StandardModelFactory getInstance(){
		if (INSTANCE == null){
			INSTANCE = new StandardModelFactory();
			INSTANCE.init();			
		}
		return INSTANCE;
	}

	private void init() {
		resourceSet = new ResourceSetImpl();
		
	    extendedMetaData = new BasicExtendedMetaData(resourceSet.getPackageRegistry());
		
		// Register the appropriate resource factory to handle all file extentions.
		//
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
			(Resource.Factory.Registry.DEFAULT_EXTENSION, 
			 new XMLResourceFactoryImpl());

		//relying on the org.eclipse.emf.ecore.generated_package ext-pt to register traits
	}

//	private void registerTraitTypes(Registry packageRegistry) {
//		for(Iterator/*<EPackage>*/ it=TraitTypeRegistry.getInstance().getEPackages().iterator();it.hasNext();){
//			EPackage pkg = (EPackage)it.next();
//			packageRegistry.put(pkg.getNsURI(), pkg);
//		}
//	}

	private StandardModelFactory() {		
		super();
	}
	
	/**
	 * Factory method that probably belongs somewhere else!
	 * @param key
	 * @param strategy
	 * @return an empty MetaDataModel
	 */
	public MetaDataModel createModel(ModelKeyDescriptor key, IDomainLoadingStrategy strategy){
		return new MetaDataModel(key, strategy);
	}

	/**
	 * Factory method that probably belongs somewhere else!
	 * @param modelContext 
	 * @return a ModelKeyDescriptor for the context
	 */
	public ModelKeyDescriptor createModelKeyDescriptor(final ITaglibDomainMetaDataModelContext modelContext) {
		return new ModelKeyDescriptor(modelContext.getProject(), modelContext.getDomainID(), modelContext.getURI());
	}
	
	/**
	 * @param inputStream
	 * @param provider
	 * @return the root of the standard model from the resource as an EList
	 * @throws IOException
	 */
	public EList loadStandardFileResource(InputStream inputStream, IMetaDataSourceModelProvider provider) throws IOException {
		XMLResource res = new MetadataResourceImpl(provider); 
		resourceSet.getResources().add(res);
		setLoadOptions(res);
		res.load(inputStream, null);
		EList root = res.getContents();		
		return root;	
	}

	/**
	 * Sets default load options for the resource
	 * @param resource 
	 */
	protected void setLoadOptions(XMLResource resource) {
		Map options = resource.getDefaultLoadOptions();
//		options.put(XMLResource.OPTION_SAVE_TYPE_INFORMATION, true);				
		options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		options.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
		options.put(XMLResource.OPTION_RESOURCE_HANDLER, resource);
		options.put(XMLResource.OPTION_LAX_FEATURE_PROCESSING, Boolean.TRUE);
		options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
//		options.put(XMLResource.OPTION_DOM_USE_NAMESPACES_IN_SCOPE, Boolean.TRUE);
	}


}
