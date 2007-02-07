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

package org.eclipse.jst.jsf.metadataprocessors.internal.provisional;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.internal.DomainLoadingStrategyRegistry;
import org.eclipse.jst.jsf.common.metadata.internal.MetaDataModelContextImpl;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Model;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.MetaDataQueryHelper;
import org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.CMAnnotationHelper;
import org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.CMAnnotationPropertyValue;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.internal.provisional.IStructuredDocumentContext;
import org.eclipse.jst.jsf.metadataprocessors.internal.AttributeValueRuntimeTypeFactory;


/**
 * Singleton class that will produce <code>IMetaDataEnabledFeature</code>s that the
 * caller can use for processing.
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public final class MetaDataEnabledProcessingFactory {
	private static MetaDataEnabledProcessingFactory INSTANCE;
	
	/**
	 * Name of property in annotation file to use when applying a runtime type to an attribute value 
	 */
	public static final String ATTRIBUTE_VALUE_RUNTIME_TYPE_PROP_NAME = "attribute-value-runtime-type";
	
	/**
	 * @return singleton instance
	 */
	public static MetaDataEnabledProcessingFactory getInstance(){
		if (INSTANCE == null){
			INSTANCE = new MetaDataEnabledProcessingFactory();	
		}
		return INSTANCE;
	}
	
	private MetaDataEnabledProcessingFactory(){
		super();
	}
	
	/**
	 * Returns list of <code>IMetaDataEnabledFeature</code> adapters for the given Taglibrary attribute.  
	 * 
	 * Adapters will be scanned for first by uri, element, attribute and if not found,
	 * 	uri, "*", attribute and if still not found by "*", "*", attribute.
	 * @param featureType feature type.  eg. <code>IPossibleValues</code>, <code>IValidValues</code>, etc.  Must be subclass of IMetaDataEnabledFeature. 
	 * @param sdContext 
	 * @param uri annotation file uri
	 * @param elementName
	 * @param attributeName
	 * @return	returns null - if the metadata was not found 
	 * 			<br>returns empty list - if not a <code>IMetaDataEnabledFeature</code> proccessor or is not valid or does not support the specified feature
	 * 
	 * @see MetaDataEnabledProcessingFactory.ATTRIBUTE_VALUE_RUNTIME_TYPE_PROP_NAME
	 */
	public List getAttributeValueRuntimeTypeFeatureProcessors(Class featureType, IStructuredDocumentContext sdContext, String uri, String elementName, String attributeName){
		List retList = new ArrayList(2);		
		//look up the attribute's runtime type from MD
		IProject _project = null; 
		if (sdContext !=null){
			IWorkspaceContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE.getWorkspaceContextResolver(sdContext);
			_project = resolver != null ? resolver.getProject() : null; 
		}
		String _elem = elementName + "/" + attributeName;
		String _uri = uri;
		IMetaDataModelContext modelContext = createModelContext(_project, DomainLoadingStrategyRegistry.TAGLIB_DOMAIN, _uri);
		Entity entity = MetaDataQueryHelper.getEntity(modelContext, _elem);
		Trait trait = null;
		if (entity != null){
			trait = MetaDataQueryHelper.getTrait(entity, ATTRIBUTE_VALUE_RUNTIME_TYPE_PROP_NAME);
		}
		
		if (trait==null){
			return retList;
		}
		
		String typeId = TraitValueHelper.getValueAsString(trait);
		
		//get the implementing class for the type
		ITypeDescriptor type = AttributeValueRuntimeTypeFactory.getInstance().getType(typeId);
		if (type != null){
			TaglibMetadataContext context = new TaglibMetadataContext(uri, elementName, attributeName, entity, trait);
			//get all the feature adapters (IMetaDataEnabledFeature) for this type
			List aList = type.getFeatureAdapters(featureType);
			for (int j=0;j<aList.size();j++){
				//set the context in the feature
				((IMetaDataEnabledFeature)aList.get(j)).setMetaDataContext(context);
				((IMetaDataEnabledFeature)aList.get(j)).setStructuredDocumentContext(sdContext);
				retList.add(aList.get(j));
			}

		}
		//return list of IMetaDataEnabledFeatures for this type
		return retList;

	}
	
	private IMetaDataModelContext createModelContext(IProject project, String taglibDomain,
			String uri) {		
		return new MetaDataModelContextImpl(project, taglibDomain, uri);
		
	}

}
