/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.tagregistry;

import org.eclipse.osgi.util.NLS;

/**
 * String resource handler	
 *
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.ui.internal.tagregistry.messages"; //$NON-NLS-1$
	/**
	 * see message.properties
	 */
	public static String ComponentDetailSubForm_InterfaceInfo;
	/**
	 * see message.properties
	 */
	public static String ComponentDetailSubForm_TypeInfo;
	/**
	 * see message.properties
	 */
	public static String ConverterDetailsForm_Class;
	/**
	 * see message.properties
	 */
	public static String ConverterDetailsForm_Converterid;
	/**
	 * see message.properties
	 */
	public static String ConverterDetailsForm_ConverterInfo;
	/**
	 * see message.properties
	 */
	public static String NamespaceDetailsForm_SectionLabel;
	/**
	 * see message.properties
	 */
	public static String NamespaceDetailsForm_SectionText;
	/**
	 * see message.properties
	 */
	public static String TaglibContentProvider_Calculating;
	/**
	 * see message.properties
	 */
	public static String TaglibContentProvider_JobDesc;
	/**
	 * see message.properties
	 */
	public static String TaglibContentProvider_NamespaceErrorDescription;
	/**
	 * see message.properties
	 */
	public static String TaglibContentProvider_NamespaceErrorTitle;
	/**
	 * see message.properties
	 */
	public static String TaglibContentProvider_TagCalculatingWaitMessage;
	/**
	 * see message.properties
	 */
	public static String TagRegistryDetailsForm_SectionText;
	/**
	 * see message.properties
	 */
	public static String TagRegistryDetailsForm_Namespace;
	/**
	 * see message.properties
	 */
	public static String TagRegistryMasterForm_FlushCacheMessage;
	/**
	 * see message.properties
	 */
	public static String TagRegistryMasterForm_FlushCacheQuestion;
	/**
	 * see message.properties
	 */
	public static String TagRegistryMasterForm_Project;
	/**
	 * see message.properties
	 */
	public static String TagRegistryMasterForm_RefreshRegistry;
	/**
	 * see message.properties
	 */
	public static String ValidatorDetailsForm_SectionLabel;
	/**
	 * see message.properties
	 */
	public static String ValidatorDetailsForm_SectionText;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
