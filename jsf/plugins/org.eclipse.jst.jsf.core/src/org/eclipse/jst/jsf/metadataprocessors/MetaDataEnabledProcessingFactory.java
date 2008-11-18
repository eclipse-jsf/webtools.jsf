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
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.dom.AttributeIdentifier;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.metadataprocessors.internal.AttributeValueRuntimeTypeFactory;

/**
 * Singleton class that will produce <code>IMetaDataEnabledFeature</code>s
 * that the caller can use for processing.
 * <p>
 * <b>Provisional API - subject to change</b>
 * </p>
 * 
 * @author Gerry Kessler - Oracle
 * 
 */
public final class MetaDataEnabledProcessingFactory {
	private static MetaDataEnabledProcessingFactory INSTANCE;

	/**
	 * Name of property in annotation file to use when applying a runtime type
	 * to an attribute value
	 */
	public static final String ATTRIBUTE_VALUE_RUNTIME_TYPE_PROP_NAME = "attribute-value-runtime-type"; //$NON-NLS-1$

	/**
	 * @return singleton instance
	 */
	public static MetaDataEnabledProcessingFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MetaDataEnabledProcessingFactory();
		}
		return INSTANCE;
	}

	private MetaDataEnabledProcessingFactory() {
		super();
	}

	/**
	 * Returns list of <code>IMetaDataEnabledFeature</code> adapters for the
	 * given Taglibrary attribute.
	 * 
	 * Adapters will be scanned for first by uri, element, attribute and if not
	 * found, uri, "*", attribute and if still not found by "*", "*", attribute.
	 * 
	 * @param featureType
	 *            feature type. eg. <code>IPossibleValues</code>,
	 *            <code>IValidValues</code>, etc. Must be subclass of
	 *            IMetaDataEnabledFeature.
	 * @param sdContext
	 * @param uri
	 *            annotation file uri
	 * @param elementName
	 * @param attributeName
	 * @return returns null - if the metadata was not found <br>
	 *         returns empty list - if not a
	 *         <code>IMetaDataEnabledFeature</code> processor or is not valid
	 *         or does not support the specified feature
	 * 
	 * @see MetaDataEnabledProcessingFactory#ATTRIBUTE_VALUE_RUNTIME_TYPE_PROP_NAME
	 */
	public List<IMetaDataEnabledFeature> getAttributeValueRuntimeTypeFeatureProcessors(
			Class featureType, IStructuredDocumentContext sdContext,
			String uri, String elementName, String attributeName) {
		
		// look up the attribute's runtime type from MD
		IProject _project = null;
		if (sdContext != null) {
			IWorkspaceContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
					.getWorkspaceContextResolver(sdContext);
			_project = resolver != null ? resolver.getProject() : null;
		}
		String _elem = elementName + "/" + attributeName; //$NON-NLS-1$
		String _uri = uri;
		ITaglibDomainMetaDataModelContext modelContext = TaglibDomainMetaDataQueryHelper
				.createMetaDataModelContext(_project, _uri);
		Entity attrEntity = TaglibDomainMetaDataQueryHelper.getEntity(modelContext,
				_elem);

		if (attrEntity != null) 
			return getAttributeValueRuntimeTypeFeatureProcessors(featureType, sdContext, attrEntity);
		
		return Collections.EMPTY_LIST;

	}

	/**
	 * A convenience method fully equivalent to:
	 * 
	 *     getAttributeValueRuntimeTypeFeatureProcessors
     *      (featureType, sdContext, attributeId.getTagIdentifier().getUri()
     *           , attributeId.getTagIdentifier().getTagName(), 
     *           attributeId.getName())
	 * @param featureType
	 * @param sdContext
	 * @param attributeId
	 * @return the meta-data enabled feature
	 */
	public List<IMetaDataEnabledFeature> getAttributeValueRuntimeTypeFeatureProcessors(
            Class featureType, IStructuredDocumentContext sdContext,
            AttributeIdentifier attributeId)
    {
	    return getAttributeValueRuntimeTypeFeatureProcessors
	    (featureType, sdContext, attributeId.getTagIdentifier().getUri()
	            , attributeId.getTagIdentifier().getTagName(), 
	            attributeId.getName());
    }
	
	/**
	 * @param featureType
	 * @param sdContext
	 * @param attrEntity
	 * @return returns null - if the meta data was not found <br>
	 *         returns empty list - if not a
	 *         <code>IMetaDataEnabledFeature</code> processor or is not valid
	 *         or does not support the specified feature
	 */
	public List<IMetaDataEnabledFeature> getAttributeValueRuntimeTypeFeatureProcessors(
			Class featureType, IStructuredDocumentContext sdContext,
			Entity attrEntity) {

		Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(attrEntity,
				ATTRIBUTE_VALUE_RUNTIME_TYPE_PROP_NAME);

		if (trait == null) {
			return Collections.EMPTY_LIST;
		}
		
		List<IMetaDataEnabledFeature> retList = new ArrayList<IMetaDataEnabledFeature>(2);
		String typeId = TraitValueHelper.getValueAsString(trait);

		// get the implementing class for the type
		ITypeDescriptor type = AttributeValueRuntimeTypeFactory.getInstance()
				.getType(typeId);
		if (type != null) {
			MetaDataContext context = new MetaDataContext(attrEntity, trait);
			// get all the feature adapters (IMetaDataEnabledFeature) for this
			// type
			List<IMetaDataEnabledFeature> featureAdapters = type.getFeatureAdapters(featureType);
			for (int j = 0; j < featureAdapters.size(); j++) {
				// set the context in the feature
				featureAdapters.get(j).setMetaDataContext(context);
				featureAdapters.get(j).setStructuredDocumentContext(sdContext);
				retList.add(featureAdapters.get(j));
			}

		}
		// return list of IMetaDataEnabledFeatures for this type
		return Collections.unmodifiableList(retList);
	}

}
