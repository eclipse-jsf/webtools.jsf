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
package org.eclipse.jst.jsf.facesconfig;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigPackageImpl;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigResourceFactory;
import org.osgi.framework.BundleContext;


/**
 * The main plugin class to be used in the desktop.
 */
public class FacesConfigPlugin extends EMFPlugin {

	public static final String FACES_CONFIG_EDITOR_ID = "org.eclipse.jst.jsf.facesconfig.internal.presentation.FacesConfigEditor";

	public static final FacesConfigPlugin INSTANCE = new FacesConfigPlugin();
	private static Implementation plugin;

    private FacesConfigPlugin() {
	    super(new ResourceLocator[] {});
    }

    public ResourceLocator getPluginResourceLocator() {
      return plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * @return the singleton instance.
     */
    public static Implementation getPlugin() {
      return plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     */
    public static void write(Exception exception)
    {
      INSTANCE.log(exception);
    }

	/**
	 * The actual implementation of the Eclipse <b>Plugin</b>.
	 */
	public static class Implementation extends EMFPlugin.EclipsePlugin {
		public Implementation() {
			super();
			// Remember the static instance.
			plugin = this;
		}
		public void start(BundleContext context) throws Exception {
			super.start(context);
			// Init Faces Config Package
			FacesConfigPackageImpl.init();

			getFacesconfigPackage();
			
			// TODO: XN: no sure whether this line is needed
			FacesConfigResourceFactory.registerDtds();
			
			FacesConfigResourceFactory.register();
		}

		public FacesConfigPackage getFacesconfigPackage() {
			return FacesConfigPackage.eINSTANCE;
		}
		
		public FacesConfigFactory getFacesconfigFactory() {
			return (FacesConfigFactory) getFacesconfigPackage().getEFactoryInstance();
		}

	}
}

