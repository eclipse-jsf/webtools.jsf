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

import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.CMAnnotationHelper;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.internal.provisional.IStructuredDocumentContext;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IPossibleValues;
import org.osgi.framework.Bundle;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Simple abstract class that implementers of IMetaDataEnabledFeature can subclass
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public abstract class AbstractMetaDataEnabledFeature implements IMetaDataEnabledFeature{
	
	private CMAnnotationContext cmContext;
	private IStructuredDocumentContext sdContext;
	private String bundleID;
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.provisional.IMetaDataEnabledFeature#setContentModelContext(org.eclipse.jst.jsf.metadataprocessors.internal.provisional.CMAnnotationContext)
	 */
	public void setContentModelContext(CMAnnotationContext context) {
		this.cmContext = context;		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.provisional.IMetaDataEnabledFeature#getCMAnnotationContext()
	 */
	public CMAnnotationContext getCMAnnotationContext() {
		return cmContext;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.provisional.IMetaDataEnabledFeature#setFeatureImplementer(org.eclipse.jst.jsf.metadataprocessors.internal.provisional.IType)
	 */
	public void setBundleID(String bundleID) {
		this.bundleID = bundleID;		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.provisional.IMetaDataEnabledFeature#getFeatureImplementer()
	 */
	public String getBundleID() {
		return bundleID;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.provisional.IMetaDataEnabledFeature#setStructuredDocumentContext(org.eclipse.wtp.jsf.context.structureddocument.IStructuredDocumentContext)
	 */
	public void setStructuredDocumentContext(IStructuredDocumentContext context) {
		this.sdContext = context;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.provisional.IMetaDataEnabledFeature#getStructuredDocumentContext()
	 */
	public IStructuredDocumentContext getStructuredDocumentContext() {
		return sdContext;
	}

	
	//common metadata accessors
	/**
	 * Return the single expected String value for a given property.
	 * 
	 * Since the CMAnnotationContext, where the type-id was found, 
	 * may have come from a wild card, or the property being looked up now is,
	 * we should first look for the named property on the specific element and
	 * only if not found, look for it on the * element.
	 * 
	 * @param propName property name
	 * @return String value
	 */
	protected String getCMAttributePropertyValue(String propName){			
		//first look for specific element
		String elemName = getCMAnnotationContext().getElementName().equals("*") ? getDocContextElementName() : getCMAnnotationContext().getElementName();
		String val = CMAnnotationHelper.getCMAttributePropertyValue(getCMAnnotationContext().getBundleId(), getCMAnnotationContext().getUri(),
				elemName, 
				getCMAnnotationContext().getAttributeName(),
				propName);
		
		if (val != null)
			return val;
		
		//now look for the '*' element annotations
		return CMAnnotationHelper.getCMAttributePropertyValue(getCMAnnotationContext().getBundleId(), getCMAnnotationContext().getUri(),
			"*", 
			getCMAnnotationContext().getAttributeName(),
			propName);

	}
	
	/**
	 * Return the List of values for a given property.
	 * 
	 * Since the CMAnnotationContext, where the type-id was found, 
	 * may have come from a wild card, or the property being looked up now is,
	 * we should first look for the named property on the specific element and
	 * only if not found, look for it on the * element.
	 * 
	 * @param propName property name
	 * @return List of CMAnnotationPropertyValues
	 */
	protected List getCMAttributePropertyValues(String propName){
		//first look for specific element
		String elemName = getCMAnnotationContext().getElementName().equals("*") ? getDocContextElementName() : getCMAnnotationContext().getElementName();
		List val = CMAnnotationHelper.getCMAttributePropertyValues(getCMAnnotationContext().getBundleId(), getCMAnnotationContext().getUri(),
				elemName,
				getCMAnnotationContext().getAttributeName(),
				propName);
		
		if (!val.isEmpty())
			return val;
		
		// now look for the '*' element annotations
		return CMAnnotationHelper.getCMAttributePropertyValues(getCMAnnotationContext().getBundleId(), getCMAnnotationContext().getUri(),
				"*",
				getCMAnnotationContext().getAttributeName(),
				propName);

	}
	
	private String getDocContextElementName() {
		IDOMContextResolver dom = IStructuredDocumentContextResolverFactory.INSTANCE.getDOMContextResolver(getStructuredDocumentContext());
		if (dom == null)
			return null;
		
		if (dom.getNode().getNodeType() == Node.ATTRIBUTE_NODE){
			Attr anode = (Attr)dom.getNode();
			Element elem = anode.getOwnerElement();
			return elem.getLocalName();
		}
        return dom.getNode().getLocalName();
			
	}

	/**
	 * @return small-icon name 
	 */
	protected String getCMSmallIcon() {
		return getCMAttributePropertyValue(IPossibleValues.POSSIBLE_VALUES_SMALL_ICON_PROP_NAME);
	}
	
	/**
	 * @return ImageDescriptor from the small-icon property for annotation from the same
	 * bundle as the annotation
	 */
	protected ImageDescriptor getImage() {
		String smallIcon = getCMSmallIcon();
		if (smallIcon == null)
			return null;
		
		try {
			Bundle bundle = Platform.getBundle(getCMAnnotationContext().getBundleId());
			URL url= FileLocator.find(bundle,new Path(smallIcon), null);
			return ImageDescriptor.createFromURL(url);
		} catch (RuntimeException e) {		
			//TODO: log error?
			return null;
		}
	}
}
