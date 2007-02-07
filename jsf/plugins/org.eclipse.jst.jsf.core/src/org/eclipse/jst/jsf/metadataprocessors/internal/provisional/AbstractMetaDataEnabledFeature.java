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

package org.eclipse.jst.jsf.metadataprocessors.internal.provisional;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.jsf.common.metadata.internal.DomainLoadingStrategyRegistry;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.MetaDataQueryHelper;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.internal.provisional.IStructuredDocumentContext;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IPossibleValues;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Simple abstract class that implementers of {@link IMetaDataEnabledFeature} can subclass in the <b>TagLibDomain</b>
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public abstract class AbstractMetaDataEnabledFeature implements IMetaDataEnabledFeature{
	
	private MetaDataContext mdContext;
	private IStructuredDocumentContext sdContext;
	private IProject _project;
	private static final List EMPTY_LIST = new ArrayList(0);
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.provisional.IMetaDataEnabledFeature#setMetaDataContext(org.eclipse.jst.jsf.metadataprocessors.internal.provisional.MetaDataContext)
	 */
	public void setMetaDataContext(final MetaDataContext context) {
		this.mdContext = context;		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.provisional.IMetaDataEnabledFeature#getMetaDataContext()
	 */
	public MetaDataContext getMetaDataContext() {
		return mdContext;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.provisional.IMetaDataEnabledFeature#setStructuredDocumentContext(org.eclipse.wtp.jsf.context.structureddocument.IStructuredDocumentContext)
	 */
	public void setStructuredDocumentContext(final IStructuredDocumentContext context) {
		this.sdContext = context;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.provisional.IMetaDataEnabledFeature#getStructuredDocumentContext()
	 */
	public IStructuredDocumentContext getStructuredDocumentContext() {
		return sdContext;
	}
	
	private IProject getProject(){
		if (_project == null){
			_project = IStructuredDocumentContextResolverFactory.INSTANCE.getWorkspaceContextResolver(sdContext).getProject();
		}
		return _project;
	}

	
	//common metadata accessors
	/**
	 * Return the single expected String value for a given property.
	 * 
	 * Since the MetaDataContext, where the type-id was found, 
	 * may have come from a wild card, or the property being looked up now is,
	 * we should first look for the named property on the specific element and
	 * only if not found, look for it on the * element.
	 * 
	 * @param traitName property name
	 * @return String value
	 */
	protected String getTraitValueAsString(final String traitName){	
		//look for trait on given entity
		final TaglibMetadataContext context = (TaglibMetadataContext)getMetaDataContext();
		final IMetaDataModelContext modelContext = MetaDataQueryHelper.createMetaDataModelContext(getProject(), DomainLoadingStrategyRegistry.TAGLIB_DOMAIN, ((TaglibMetadataContext)getMetaDataContext()).getUri());
		
		final String entityKey = context.getTagName() +"/"+ context.getAttributeName();
		final Trait t = MetaDataQueryHelper.getTrait(modelContext, entityKey, traitName);
		if (t != null){
			return TraitValueHelper.getValueAsString(t);
		}
		
//		//if not present look on */attributeName 				
//		entityKey = "*/"+ ((TaglibMetadataContext)getMetaDataContext()).getAttributeName();
//		t = MetaDataQueryHelper.getTrait(modelContext, entityKey, traitName);
//
//		if (t!= null){
//			return TraitValueHelper.getValueAsString(t);
//		}
//		
//		//now look for the '*' entity
//		entityKey = "*";
//		t = MetaDataQueryHelper.getTrait(modelContext, entityKey, traitName);
//
//		if (t!= null){
//			return TraitValueHelper.getValueAsString(t);
//		}
				
		return null;

	}
	
	/**
	 * Return the List of values for a given property.
	 * 
	 * Since the MetaDataContext, where the type-id was found, 
	 * may have come from a wild card, or the property being looked up now is,
	 * we should first look for the named property on the specific element and
	 * only if not found, look for it on the * element.
	 * 
	 * @param traitName trait name
	 * @return List of String values
	 */
	protected List getTraitValueAsListOfStrings(final String traitName){
		//look for trait on given entity
		final TaglibMetadataContext context = (TaglibMetadataContext)getMetaDataContext();		
		final IMetaDataModelContext modelContext = MetaDataQueryHelper.createMetaDataModelContext(getProject(), DomainLoadingStrategyRegistry.TAGLIB_DOMAIN, ((TaglibMetadataContext)getMetaDataContext()).getUri());
		
		final String entityKey = context.getTagName() +"/"+ context.getAttributeName();
		final Entity entity = ((TaglibMetadataContext)getMetaDataContext()).getEntity();
		final Trait t = MetaDataQueryHelper.getTrait(entity, traitName);
		if (t != null){
			return TraitValueHelper.getValueAsListOfStrings(t);
		}
			
		return EMPTY_LIST;
	}

	private String getDocContextElementName() {
		final IDOMContextResolver dom = IStructuredDocumentContextResolverFactory.INSTANCE.getDOMContextResolver(getStructuredDocumentContext());
		if (dom == null)
			return null;
		
		if (dom.getNode().getNodeType() == Node.ATTRIBUTE_NODE){
			final Attr anode = (Attr)dom.getNode();
			final Element elem = anode.getOwnerElement();
			return elem.getLocalName();
		}
        return dom.getNode().getLocalName();
			
	}

	/**
	 * @return small-icon name 
	 */
	protected String getSmallIcon() {
		return getTraitValueAsString(IPossibleValues.POSSIBLE_VALUES_SMALL_ICON_PROP_NAME);
	}
	
	/**
	 * @return ImageDescriptor from the small-icon property for annotation from the same
	 * bundle as the trait
	 */
//	FIX ME!!!  How do we get an image descriptor from a trait value???
	protected ImageDescriptor getImage() {
		final String smallIcon = getSmallIcon();
		if (smallIcon == null)
			return null;
		
//		try {
//			Trait t = getMetaDataContext().getTrait();
//			ClassLoader cl = t.getSourceModel().getSourceModelProvider().getResourceBundle().getClass().getClassLoader();
////			Bundle bundle = Platform.getBundle();
//			URL url= URLClassLoader FileLocator.find(bundle,new Path(smallIcon), null);
//			return ImageDescriptor.createFromURL(url);
//		} catch (RuntimeException e) {		
//			//TODO: log error?
//
//		}
		return null;
	}
}
