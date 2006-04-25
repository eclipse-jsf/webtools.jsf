/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.util;

/**
 * The tag name constants of the deployment description
 * 
 * @author hfsu, bmeng
 * @version 1.0
 */
public interface WebDotXMLTagConstants {
	public static final String AUTH_CONSTRAINT_TAG = "auth-constraint";

	public static final String AUTH_METHOD_TAG = "auth-method";

	public static final String CONTEXT_PARAM_TAG = "context-param";

	public static final String DESCRIPTION_TAG = "description";

	public static final String DISPLAY_NAME_TAG = "display-name";

	public static final String DISTRIBUTABLE_TAG = "distributable";

	public static final String EJB_LINK_TAG = "ejb-link";

	public static final String EJB_LOCAL_REF_TAG = "ejb-local-ref";

	public static final String EJB_REF_NAME_TAG = "ejb-ref-name";

	public static final String EJB_REF_TAG = "ejb-ref";

	public static final String EJB_REF_TYPE_TAG = "ejb-ref-type";

	public static final String ENV_ENTRY_NAME_TAG = "env-entry-name";

	public static final String ENV_ENTRY_TAG = "env-entry";

	public static final String ENV_ENTRY_TYPE_TAG = "env-entry-type";

	public static final String ENV_ENTRY_VALUE_TAG = "env-entry-value";

	public static final String ERROR_CODE_TAG = "error-code";

	public static final String ERROR_PAGE_TAG = "error-page";

	public static final String EXCEPTION_TYPE_TAG = "exception-type";

	public static final String EXTENSION_TAG = "extension";

	public static final String FILTER_CLASS_TAG = "filter-class";

	public static final String FILTER_MAPPING_TAG = "filter-mapping";

	public static final String FILTER_NAME_TAG = "filter-name";

	public static final String FILTER_TAG = "filter";

	public static final String FORM_ERROR_PAGE_TAG = "form-error-page";

	public static final String FORM_LOGIN_CONFIG_TAG = "form-login-config";

	public static final String FORM_LOGIN_PAGE_TAG = "form-login-page";

	public static final String HOME_TAG = "home";

	public static final String HTTP_METHOD_TAG = "http-method";

	public static final String ICON_TAG = "icon";

	public static final String INIT_PARAM_TAG = "init-param";

	public static final String JSP_FILE_TAG = "jsp-file";

	public static final String LARGE_ICON_TAG = "large-icon";

	public static final String LISTENER_CLASS_TAG = "listener-class";

	public static final String LISTENER_TAG = "listener";

	public static final String LOAD_ON_STARTUP_TAG = "load-on-startup";

	public static final String LOCAL_HOME_TAG = "local-home";

	public static final String LOCAL_TAG = "local";

	public static final String LOCATION_TAG = "location";

	public static final String LOGIN_CONFIG_TAG = "login-config";

	public static final String MIME_MAPPING_TAG = "mime-mapping";

	public static final String MIME_TYPE_TAG = "mime-type";

	public static final String PARAM_NAME_TAG = "param-name";

	public static final String PARAM_VALUE_TAG = "param-value";

	public static final String REALM_NAME_TAG = "realm-name";

	public static final String REMOTE_TAG = "remote";

	public static final String RES_AUTH_TAG = "res-auth";

	public static final String RES_REF_NAME_TAG = "res-ref-name";

	public static final String RES_SHARING_SCOPE_TAG = "res-sharing-scope";

	public static final String RES_TYPE_TAG = "res-type";

	public static final String RESOURCE_ENV_REF_NAME_TAG = "resource-env-ref-name";

	public static final String RESOURCE_ENV_REF_TAG = "resource-env-ref";

	public static final String RESOURCE_ENV_REF_TYPE_TAG = "resource-env-ref-type";

	public static final String RESOURCE_REF_TAG = "resource-ref";

	public static final String ROLE_LINK_TAG = "role-link";

	public static final String ROLE_NAME_TAG = "role-name";

	public static final String RUN_AS_TAG = "run-as";

	public static final String SECURITY_CONSTRAINT_TAG = "security-constraint";

	public static final String SECURITY_ROLE_REF_TAG = "security-role-ref";

	public static final String SECURITY_ROLE_TAG = "security-role";

	public static final String SERVLET_CLASS_TAG = "servlet-class";

	public static final String SERVLET_MAPPING_TAG = "servlet-mapping";

	public static final String SERVLET_NAME_TAG = "servlet-name";

	public static final String SERVLET_TAG = "servlet";

	public static final String SESSION_CONFIG_TAG = "session-config";

	public static final String SESSION_TIME_OUT_TAG = "session-timeout";

	public static final String SMALL_ICON_TAG = "small-icon";

	public static final String TAGLIB_LOCATION_TAG = "taglib-location";

	public static final String TAGLIB_TAG = "taglib";

	public static final String TAGLIB_URI_TAG = "taglib-uri";

	public static final String TRANSPORT_GUARANTEE_TAG = "transport-guarantee";

	public static final String URL_PATTERN_TAG = "url-pattern";

	public static final String USER_DATA_CONSTRAINT_TAG = "user-data-constraint";

	public static final String WEB_APP_TAG = "web-app";

	public static final String WEB_RESOURCE_COLLECTION_TAG = "web-resource-collection";

	public static final String WEB_RESOURCE_NAME_TAG = "web-resource-name";

	public static final String WELCOME_FILE_LIST_TAG = "welcome-file-list";

	public static final String WELCOME_FILE_TAG = "welcome-file";

	/** The context param for faces config where config file list is stored */
	public static final String CONFIG_FILES_CONTEXT_PARAM = "javax.faces.CONFIG_FILES"; //$NON-NLS-1$

	/**
	 * The context param for faces config where config file list is stored only
	 * for JSF 1.0
	 */
	public static final String APPLICATION_CONFIG_FILES_CONTEXT_PARAM = "javax.faces.application.CONFIG_FILES"; //$NON-NLS-1$

}
