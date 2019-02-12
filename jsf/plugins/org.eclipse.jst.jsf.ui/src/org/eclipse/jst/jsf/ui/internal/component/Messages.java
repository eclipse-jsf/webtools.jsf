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
package org.eclipse.jst.jsf.ui.internal.component;

import org.eclipse.osgi.util.NLS;

/**
 * String resource handler
 *
 */
public class Messages extends NLS {
	
	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.ui.internal.component.messages"; //$NON-NLS-1$
	
	/**
	 * see messages.properties
	 */
	public static String ComponentInstanceDetailsForm_ComponentClass;
	
	/**
	 * see messages.properties
	 */
	public static String ComponentInstanceDetailsForm_ComponentFamily;
	
	/**
	 * see messages.properties
	 */
	public static String ComponentInstanceDetailsForm_ComponentType;
	/**
	 * see messages.properties
	 */
	public static String ComponentInstanceDetailsForm_Decorators;
	/**
	 * see messages.properties
	 */
	public static String ComponentInstanceDetailsForm_Id;

	/**
	 * see messages.properties
	 */
	public static String ComponentInstanceDetailsForm_InstanceInfo;
	/**
	 * see messages.properties
	 */
	public static String ComponentInstanceDetailsForm_Interfaces;
	/**
	 * see messages.properties
	 */
	public static String ComponentInstanceDetailsForm_Name;

	/**
	 * see messages.properties
	 */
	public static String ComponentInstanceDetailsForm_None;

	/**
	 * see messages.properties
	 */
	public static String ComponentInstanceDetailsForm_ParentId;

	/**
	 * see messages.properties
	 */
	public static String ComponentInstanceDetailsForm_Properties;
	/**
	 * see messages.properties
	 */
	public static String ComponentInstanceDetailsForm_RenderType;
	/**
	 * see messages.properties
	 */
	public static String ComponentInstanceDetailsForm_TypeInstanceInfo;
	/**
	 * see messages.properties
	 */
	public static String ComponentInstanceDetailsForm_Converter;
	/**
	 * see messages.properties
	 */
	public static String ComponentInstanceDetailsForm_Validator;
	/**
	 * see messages.properties
	 */
	public static String ComponentInstanceDetailsForm_Facet;
	/**
	 * see messages.properties
	 */
	public static String ComponentMasterForm_CalculatingView;
	/**
	 * see messages.properties
	 */
	public static String ComponentMasterForm_RefreshView;
	/**
	 * see messages.properties
	 */
	public static String ComponentMasterForm_TreePlaceHolderText;
	/**
	 * see messages.properties
	 */	
	public static String ComponentMasterForm_Unknown;
	/**
	 * see messages.properties
	 */
	public static String ComponentMasterForm_UnknownViewIdString;
	/**
	 * see messages.properties
	 */
	public static String ComponentMasterForm_View;
	/**
	 * see messages.properties
	 */
	public static String ComponentMasterForm_HeadAreaText;
	/**
	 * see messages.properties
	 */
	public static String ComponentTreeView_NothingToDisplayMessage;
	/**
	 * see messages.properties
	 */
	public static String DTJSFViewModel_JobDesc;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
