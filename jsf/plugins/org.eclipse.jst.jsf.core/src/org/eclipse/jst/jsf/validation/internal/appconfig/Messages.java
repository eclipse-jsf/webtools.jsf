/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.validation.internal.appconfig;

import org.eclipse.osgi.util.NLS;

class Messages extends NLS {
    private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.validation.internal.appconfig.messages"; //$NON-NLS-1$




    static {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    /**
     * see messages.properties
     */
    public static String AppConfigValidationUtil_0;

    /**
     * see messages.properties
     */
    public static String EL_EXPR_MUST_BE_IN_HASH_BRACES_ID;
    
    /**
     * see messages.properties
     */
    public static String SYNTAX_ERROR_IN_EL_ID;
    
    /**
     * see messages.properties
     */
    public static String CANNOT_FIND_CLASS_NAME_ID;
    
    /**
     * see messages.properties
     */
    public static String FULLY_QUALIFIED_NAME_MUST_BE_A_CLASS_ID;
    
    /**
     * see messages.properties
     */
    public static String CLASS_MUST_BE_INSTANCE_OF_ID;

    /**
     * see messages.properties
     */
    public static String CLASS_MUST_BE_CONCRETE_ID;

    /**
     * see messages.properties
     */
    public static String API_DEPRECATED_AFTER_VERSION_ID;

    /**
     * see messages.properties
     */
    public static String BEAN_PROPERTY_NOT_FOUND_ID;

    /**
     * see messages.properties
     */
    public static String MUST_BE_A_VALID_JAVA_IDENT_ID;

    /**
     * see messages.properties
     */
    public static String BEAN_SCOPE_NOT_VALID_ID;

    /**
     * see messages.properties
     */
    public static String MAP_ENTRIES_CAN_ONLY_BE_SET_ON_MAP_TYPE_ID;

    /**
     * see messages.properties
     */
    public static String LIST_ENTRIES_CAN_ONLY_BE_SET_ON_LIST_TYPE_ID;
    
    /**
     * see messages.properties
     */
    public static String API_NOT_AVAILABLE_BEFORE_VERSION_ID;
    
    /**
     * see messages.properties
     */
    public static String APP_CONFIG_IS_NEWER_THAN_JSF_VERSION_ID;

    /**
     * see messages.properties 
     */
    public static String APP_CONFIG_IS_OLDER_THAN_JSF_VERSION_ID;

    /**
     * see messages.properties
     */
    public static String LOCALE_FORMAT_NOT_VALID_ID;

    /**
     * see messages.properties
     */
    public static String CONFIG_FILE_NOT_UNDER_WEBCONTENT_FOLDER;
}
