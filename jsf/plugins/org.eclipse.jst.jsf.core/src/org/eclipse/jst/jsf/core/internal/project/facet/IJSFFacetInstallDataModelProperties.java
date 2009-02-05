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
	public static final String IMPLEMENTATION_TYPE_PROPERTY_NAME = "IJSFFacetInstallDataModelProperties.IMPLEMENTATION_TYPE_PROPERTY_NAME"; //$NON-NLS-1$
	
    /**
     * TODO:
     */
    public static final String IMPLEMENTATION = "IJSFFacetInstallDataModelProperties.IMPLEMENTATION"; //$NON-NLS-1$

    /**
     * TODO:
     */
    public static final String DEPLOY_IMPLEMENTATION = "IJSFFacetInstallDataModelProperties.DEPLOY_IMPLEMENTATION"; //$NON-NLS-1$

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
    public static final String DEFAULT_IMPLEMENTATION_LIBRARY = "IJSFFacetInstallDataModelProperties.DEFAULT_IMPLEMENTATION_LIBRARY"; //$NON-NLS-1$

    /**
     * TODO:
     */
    public static final String IMPLEMENTATION_LIBRARIES = "IJSFFacetInstallDataModelProperties.IMPLEMENTATION_LIBRARIES"; //$NON-NLS-1$

    /**
     * TODO:
     */
    public static final String COMPONENT_LIBRARIES = "IJSFFacetInstallDataModelProperties.COMPONENT_LIBRARIES"; //$NON-NLS-1$	 
    
    /**
     * Enumeration of implementation types
     */
    public enum IMPLEMENTATION_TYPE {
    	
    	/**
    	 * Unknown
    	 */
    	UNKNOWN,
    	/**
    	 * Server supplied
    	 */
    	SERVER_SUPPLIED,
    	/**
    	 * Not supplied by the server.  The user is specifiying.
    	 */
    	USER_SPECIFIED,
    	
    	/**
    	 * Not supplied by the server.  The user is specifiying.  Same as USER_SPECIFIED.
    	 * @deprecated use USER_SPECIFIED
    	 */
    	CLIENT_SUPPLIED;
    	
    	/**
    	 * @param type
    	 * @return String value
    	 */
    	public static String getStringValue(final IMPLEMENTATION_TYPE type){
    		if (type == null)
    			return "UNKNOWN"; //$NON-NLS-1$
    		if (type ==  SERVER_SUPPLIED)
    			return "SERVER_SUPPLIED";//$NON-NLS-1$
    		if (type == USER_SPECIFIED || type ==CLIENT_SUPPLIED )
    			return "USER_SPECIFIED";//$NON-NLS-1$
    		return "UNKNOWN"; //$NON-NLS-1$
    	}
    	
    	/**
    	 * @param type
    	 * @return IMPLEMENTATION_TYPE
    	 */
    	public static IMPLEMENTATION_TYPE getValue(final String type){
    		if (type == null)
    			return UNKNOWN;
    		if (type.equals("SERVER_SUPPLIED"))//$NON-NLS-1$
    			return SERVER_SUPPLIED;
    		if (type.equals("USER_SPECIFIED") || type.equals("CLIENT_SUPPLIED"))//$NON-NLS-1$// $NON-NLS-2$
    			return USER_SPECIFIED;
    		return UNKNOWN; 
    	}
    	
    }
}
