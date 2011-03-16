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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.common.dom.AttributeIdentifier;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
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
	 * @param tagName
	 * @param attributeName
	 * @return returns null - if the metadata was not found <br>
	 *         returns empty list - if not a
	 *         <code>IMetaDataEnabledFeature</code> processor or is not valid
	 *         or does not support the specified feature
	 * 
	 * @see MetaDataEnabledProcessingFactory#ATTRIBUTE_VALUE_RUNTIME_TYPE_PROP_NAME
	 */
	public List<IMetaDataEnabledFeature> getAttributeValueRuntimeTypeFeatureProcessors(
			final Class featureType, final IStructuredDocumentContext sdContext,
			final String uri, final String tagName, final String attributeName) {
		
		String attrKey = tagName + "/" + attributeName; //$NON-NLS-1$
		
    	final IMetaDataDomainContext modelContext = getMetaDataDomainContext(sdContext);
		final ITaglibDomainMetaDataQuery query = MetaDataQueryFactory.getInstance().createQuery(modelContext);

		Entity attrEntity = query.getQueryHelper().getEntity(uri, attrKey);

		if (attrEntity != null) 
			return getAttributeValueRuntimeTypeFeatureProcessors(featureType, sdContext, attrEntity);
		
		return Collections.EMPTY_LIST;

	}

	private IProject getProject(final IStructuredDocumentContext sdContext) {
		IProject project = null;
		if (sdContext != null) {
			final IWorkspaceContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
					.getWorkspaceContextResolver(sdContext);
			project = resolver != null ? resolver.getProject() : null;
		}
		return project;
	}

	private IFile getFile(final IStructuredDocumentContext sdContext) {
		IFile file = null;
		if (sdContext != null) {
			final IWorkspaceContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
					.getWorkspaceContextResolver(sdContext);
			final IResource res = resolver != null ? resolver.getResource() : null;
			if (res instanceof IFile)
				file = (IFile)res;
		}
		return file;
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
			final Class featureType, final IStructuredDocumentContext sdContext,
			final AttributeIdentifier attributeId)
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
			final Class featureType, final IStructuredDocumentContext sdContext,
			final Entity attrEntity) {
		
		final IMetaDataDomainContext modelContext 	= getMetaDataDomainContext(sdContext);
		final ITaglibDomainMetaDataQuery query 		= MetaDataQueryFactory.getInstance().createQuery(modelContext);

		Trait trait = query.findTrait(attrEntity,
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

	private IMetaDataDomainContext getMetaDataDomainContext(
			final IStructuredDocumentContext sdContext) {
		final IFile file = getFile(sdContext);
		if (file != null)
			return MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(file);

		return MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(getProject(sdContext));
	}

}
