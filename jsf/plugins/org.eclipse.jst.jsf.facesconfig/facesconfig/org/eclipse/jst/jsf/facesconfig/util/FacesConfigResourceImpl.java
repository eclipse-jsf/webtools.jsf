/***************************************************************************************************
 * Copyright (c) 2005, 2008 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.util;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.internal.translator.FacesConfigTranslator;
import org.eclipse.wst.common.internal.emf.resource.Renderer;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResourceImpl;
import org.eclipse.wst.common.uriresolver.internal.provisional.URIResolver;
import org.eclipse.wst.common.uriresolver.internal.provisional.URIResolverPlugin;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package - NOT intended for external use.
 * Should not be used or implemented by external clients
 * 
 * <!-- end-user-doc -->
 * @see FacesConfigResourceFactory
 * @generated
 */
public class FacesConfigResourceImpl extends TranslatorResourceImpl implements IFacesConfigResource {

	/**
	 * Local entity resolver used to help loading entities - NOT intended for external use.
	 * Should not be used or implemented by external clients
	 */
	public static class MyEntityResolver implements EntityResolver {

		private final String baseLocation;
		private URIResolver uriResolver = null;
		
		/**
		 * @param baseLocation
		 */
		public MyEntityResolver(String baseLocation) {
			super();
			this.baseLocation = baseLocation;
		}

		public InputSource resolveEntity(String publicId, String systemId)
				throws SAXException, IOException {
			if (uriResolver == null) {
				uriResolver = URIResolverPlugin.createResolver();
			}
			String logicalLocation = uriResolver.resolve(baseLocation, publicId, systemId);
			String physicalLocation= uriResolver.resolvePhysicalLocation(baseLocation, publicId, logicalLocation);
			return new InputSource(physicalLocation);		
		}

	}
	
	private EntityResolver entityResolver = null;
	
	/**
	 * @param aRenderer
	 */
	public FacesConfigResourceImpl(Renderer aRenderer) {
		super(aRenderer);
	}
	/**
	 * @param uri
	 * @param aRenderer
	 */
	public FacesConfigResourceImpl(URI uri, Renderer aRenderer) {
		super(uri, aRenderer);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.TranslatorResource#getDoctype()
	 */
	public String getDoctype() {
		return null;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.TranslatorResource#getRootTranslator()
	 */
	public Translator getRootTranslator() {
		return FacesConfigTranslator.INSTANCE; 

	}
    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.emf.facesconfig.xml.FacesConfigResource#getFacesConfig()
     */
    public FacesConfigType getFacesConfig() {
		return (FacesConfigType) getRootObject();
    }
    
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.TranslatorResourceImpl#getDefaultPublicId()
	 */
	protected String getDefaultPublicId() {
		return "-//Sun Microsystems, Inc.//DTD JavaServer Faces Config 1.0//EN"; //$NON-NLS-1$
	}
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.TranslatorResourceImpl#getDefaultSystemId()
	 */
	protected String getDefaultSystemId() {
		return "http://java.sun.com/dtd/web-facesconfig_1_0.dtd"; //$NON-NLS-1$
	}
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.TranslatorResourceImpl#getDefaultVersionID()
	 */
	protected int getDefaultVersionID() {
		return 0;
	}
	public EntityResolver getEntityResolver() {
		if (entityResolver == null) {
			String baseLocation = getURI().toString();
			entityResolver = new MyEntityResolver(baseLocation);
		}
		return entityResolver;
	}
	public void setURI(URI arg0) {
		super.setURI(arg0);
		entityResolver = null;
	}
} //FacesConfigResourceFactoryImpl
