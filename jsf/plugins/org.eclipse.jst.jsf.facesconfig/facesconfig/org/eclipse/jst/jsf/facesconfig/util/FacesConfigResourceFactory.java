/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.wst.common.componentcore.internal.impl.WTPResourceFactoryRegistry;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMRendererFactory;
import org.eclipse.wst.common.internal.emf.resource.Renderer;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResourceFactory;

/**
 * May be referenced but should NOT be extended by clients
 * 
 * @author xjiang
 *
 */
public final class FacesConfigResourceFactory extends TranslatorResourceFactory 
{
    /**
     * @return a faces config resource factory for use with faces-config
     * files contained in JARs
     */
    public static FacesConfigResourceFactory createResourceFactoryForJar()
    {
        return new FacesConfigResourceFactory(EMF2DOMRendererFactory.INSTANCE);
    }
    
	/**
	 * Construct a faces resource factory.
	 * 
	 * @param rendererFactory 
	 */
	protected FacesConfigResourceFactory(RendererFactory rendererFactory) {
		super(rendererFactory);
	}


	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.TranslatorResourceFactory#createResource(org.eclipse.emf.common.util.URI)
	 */
	protected TranslatorResource createResource(URI uri, Renderer aRenderer) {
		return new FacesConfigResourceImpl(uri, aRenderer);
	}
	
	/**
	 * Method registerDtds.
	 */
	public static void registerDtds() {
	    // TODO: should we be registering dtd/xsd here?
	    // how does MyEntityResolver in the resource affect this (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=154439)
	}

	/**
	 * register using the default renderer factory.
	 * @see #registerWith(String, FacesRendererFactory)
	 */
	public static void register() {
		register((String)null);
	}

	/**
	 * Register the sFileName with the default renderer factory
	 * @param sFileName
	 */
	public static void register(String sFileName) {
		registerWith(sFileName, FacesRendererFactory.INSTANCE);
	}

	/**
	 * Register myself with the Resource.Factory.Registry
	 * @param sFileName 
	 * @param aRendererFactory 
	 */
	private static void registerWith(String sFileName, FacesRendererFactory aRendererFactory) {
		if (sFileName != null) {
			WTPResourceFactoryRegistry.INSTANCE.registerLastFileSegment(sFileName, new FacesConfigResourceFactory(FacesRendererFactory.INSTANCE));
		} else {
			WTPResourceFactoryRegistry.INSTANCE.registerLastFileSegment("faces-config.xml", new FacesConfigResourceFactory(FacesRendererFactory.INSTANCE)); //$NON-NLS-1$
		}
	}
}
