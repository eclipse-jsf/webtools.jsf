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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.wst.common.componentcore.internal.impl.WTPResourceFactoryRegistry;
import org.eclipse.wst.common.internal.emf.resource.FileNameResourceFactoryRegistry;
import org.eclipse.wst.common.internal.emf.resource.Renderer;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResourceFactory;

/**
 * @author xjiang
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class FacesConfigResourceFactory extends TranslatorResourceFactory {

	/**
	 * @param aRendererFactory
	 */
	public FacesConfigResourceFactory(RendererFactory aRendererFactory) {
		super(aRendererFactory);
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
		//J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.WEBAPP_SYSTEMID_2_2, "web-app_2_2.dtd"); //$NON-NLS-1$
		//J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.WEBAPP_ALT_SYSTEMID_2_2, "web-app_2.2.dtd");		//$NON-NLS-1$
		//J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.WEBAPP_SYSTEMID_2_3, "web-app_2_3.dtd"); //$NON-NLS-1$
		//J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.WEB_APP_SCHEMA_LOC_2_4, "web-app_2_4.xsd"); //$NON-NLS-1$
		//J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.JSP_SCHEMA_LOC_2_0, "jsp_2_0.xsd"); //$NON-NLS-1$
	}

	/**
	 * register using the default renderer factory.
	 * @see #registerWith(String, RendererFactory)
	 */
	public static void register() {
		register((String)null);
	}

	/**
	 * Register the sFileName with the default renderer factory
	 * @param sFileName
	 */
	public static void register(String sFileName) {
		registerWith(sFileName, FacesRendererFactory.INSTANCE/*RendererFactory.getDefaultRendererFactory()*/);
	}

	/**
	 * register using the default renderer factory.
	 * @param aRegistry 
	 * @see #registerWith(String, RendererFactory)
	 */
	public static void register(FileNameResourceFactoryRegistry aRegistry) {
		aRegistry.registerLastFileSegment("faces-config.xml", new FacesConfigResourceFactory(RendererFactory.getDefaultRendererFactory()));//$NON-NLS-1$
	}
	
	/**
	 * Register myself with the Resource.Factory.Registry
	 * @param sFileName 
	 * @param aRendererFactory 
	 */
	public static void registerWith(String sFileName, RendererFactory aRendererFactory) {
		if (sFileName != null) {
			WTPResourceFactoryRegistry.INSTANCE.registerLastFileSegment(sFileName, new FacesConfigResourceFactory(aRendererFactory)); //$NON-NLS-1$
		} else {
			WTPResourceFactoryRegistry.INSTANCE.registerLastFileSegment("faces-config.xml", new FacesConfigResourceFactory(aRendererFactory)); //$NON-NLS-1$
		}
	}

	/**
	 * @param uri
	 * @return the the factory associated with uri
	 */
	public static Resource.Factory getRegisteredFactory(URI uri) {
		return WTPResourceFactoryRegistry.INSTANCE.getFactory(uri);
	}
}
