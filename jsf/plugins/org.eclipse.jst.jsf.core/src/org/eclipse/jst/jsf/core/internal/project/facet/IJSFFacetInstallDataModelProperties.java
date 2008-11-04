/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.project.facet;

import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetInstallDataModelProperties;

/**
 * Defines properties used by the JSF facet install data model.
 * 
 * @author Gerry Kessler - Oracle
 */
public interface IJSFFacetInstallDataModelProperties extends
        IFacetInstallDataModelProperties {
	
    /**
     * TODO:
     */
    public static final String CONFIG_PATH = "IJSFFacetInstallDataModelProperties.CONFIG_PATH"; //$NON-NLS-1$

    /**
     * TODO:
     */
    public static final String SERVLET_NAME = "IJSFFacetInstallDataModelProperties.SERVLET_NAME"; //$NON-NLS-1$

    /**
     * TODO:
     */
    public static final String SERVLET_CLASSNAME = "IJSFFacetInstallDataModelProperties.SERVLET_CLASSNAME"; //$NON-NLS-1$

    /**
     * TODO:
     */
    public static final String SERVLET_URL_PATTERNS = "IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS"; //$NON-NLS-1$

    /**
     * TODO:
     */
    public static final String WEBCONTENT_DIR = "IJSFFacetInstallDataModelProperties.WEBCONTENT_DIR"; //$NON-NLS-1$

    /**
     * TODO:
     */
    public static final String LIBRARY_PROVIDER_DELEGATE = "IJSFFacetInstallDataModelProperties.LIBRARY_PROVIDER_DELEGATE"; //$NON-NLS-1$    

    /**
     * TODO:
     */
    public static final String COMPONENT_LIBRARIES = "IJSFFacetInstallDataModelProperties.COMPONENT_LIBRARIES"; //$NON-NLS-1$	 
    
}
