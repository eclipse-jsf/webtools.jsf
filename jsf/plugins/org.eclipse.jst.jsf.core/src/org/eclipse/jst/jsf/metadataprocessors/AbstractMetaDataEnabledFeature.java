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

package org.eclipse.jst.jsf.metadataprocessors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IImageDescriptorProvider;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;

/**
 * Simple abstract class that implementers of {@link IMetaDataEnabledFeature} can subclass in the <b>TagLibDomain</b> of metadata
 * <p><b>Provisional API - subject to change</b></p>
 * @author Gerry Kessler - Oracle
 * 
 *
 */
public abstract class AbstractMetaDataEnabledFeature implements IMetaDataEnabledFeature{
	
	private MetaDataContext mdContext;
	private IStructuredDocumentContext sdContext;
	
	private static final List EMPTY_LIST = new ArrayList(0);
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature#setMetaDataContext(org.eclipse.jst.jsf.metadataprocessors.MetaDataContext)
	 */
	public void setMetaDataContext(final MetaDataContext context) {
		this.mdContext = context;		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature#getMetaDataContext()
	 */
	public MetaDataContext getMetaDataContext() {
		return mdContext;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature#setStructuredDocumentContext(org.eclipse.wtp.jsf.context.structureddocument.IStructuredDocumentContext)
	 */
	public void setStructuredDocumentContext(final IStructuredDocumentContext context) {
		this.sdContext = context;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature#getStructuredDocumentContext()
	 */
	public IStructuredDocumentContext getStructuredDocumentContext() {
		return sdContext;
	}
	
//	private IProject getProject(){
//		if (_project == null){
//			_project = IStructuredDocumentContextResolverFactory.INSTANCE.getWorkspaceContextResolver(sdContext).getProject();
//		}
//		return _project;
//	}

	
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
		Trait t = getTraitForEntityUsingContext(traitName);
		if (t != null){
			return TraitValueHelper.getValueAsString(t);
		}
				
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
		Trait t = getTraitForEntityUsingContext(traitName);
		if (t != null){
			return TraitValueHelper.getValueAsListOfStrings(t);
		}
			
		return EMPTY_LIST;
	}

	private Trait getTraitForEntityUsingContext(final String traitName) {
		//look for trait on given entity
		final Entity entity = ((TaglibMetadataContext)getMetaDataContext()).getEntity();
		return TaglibDomainMetaDataQueryHelper.getTrait(entity, traitName);
	}

	/**
	 * @return small-icon name 
	 */
	protected String getSmallIcon() {
		return getTraitValueAsString(IPossibleValues.POSSIBLE_VALUES_SMALL_ICON_PROP_NAME);
	}
	
	/**
	 * @return ImageDescriptor from the small-icon property for annotation from the same
	 * source model provider as the trait
	 */
	protected ImageDescriptor getImage() {
		final String smallIcon = getSmallIcon();
		if (smallIcon == null)
			return null;
		
		try {
			Trait t = getMetaDataContext().getTrait();
			IMetaDataSourceModelProvider provider = t.getSourceModelProvider();
			IImageDescriptorProvider ip = (IImageDescriptorProvider)provider.getAdapter(IImageDescriptorProvider.class);
			if (ip != null){
				return ip.getImageDescriptor(smallIcon);
			}
		} catch (RuntimeException e) {		
			//TODO: log error?

		}
		return null;
	}
}
