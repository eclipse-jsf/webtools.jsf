/**
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport;

import org.eclipse.osgi.util.NLS;

/**
 * String resource handler.
 * 
 * @author Ian Trimble - Oracle
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME =
		"org.eclipse.jst.jsf.apache.trinidad.tagsupport.messages"; //$NON-NLS-1$

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	/**
	 * See messages.properties.
	 */
	public static String BreadCrumbsOperation_EmptyBreadCrumbsTag;

	/**
	 * See messages.properties.
	 */
	public static String PanelTabbedOperation_EmptyPanelTabbedTag;

	/**
	 * See messages.properties.
	 */
	public static String ShowDetailItemOperation_EmptyShowDetailItemTag;

	/**
	 * See messages.properties.
	 */
	public static String SelectItemModel_SampleItem1Label;

	/**
	 * See messages.properties.
	 */
	public static String SelectItemModel_SampleItem1Value;

	/**
	 * See messages.properties.
	 */
	public static String SelectItemModel_SampleItem2Label;

	/**
	 * See messages.properties.
	 */
	public static String SelectItemModel_SampleItem2Value;

	/**
	 * See messages.properties.
	 */
	public static String SelectItemModel_SampleItem3Label;

	/**
	 * See messages.properties.
	 */
	public static String SelectItemModel_SampleItem3Value;

	/**
	 * See messages.properties.
	 */
	public static String SelectShuttleOperation_Description;

	/**
	 * See messages.properties.
	 */
	public static String SelectShuttleOperation_LinkMove;

	/**
	 * See messages.properties.
	 */
	public static String SelectShuttleOperation_LinkMoveAll;

	/**
	 * See messages.properties.
	 */
	public static String SelectShuttleOperation_LinkRemove;

	/**
	 * See messages.properties.
	 */
	public static String SelectShuttleOperation_LinkRemoveAll;

	/**
	 * See messages.properties.
	 */
	public static String SelectShuttleOperation_LinkTop;

	/**
	 * See messages.properties.
	 */
	public static String SelectShuttleOperation_LinkUp;

	/**
	 * See messages.properties.
	 */
	public static String SelectShuttleOperation_LinkDown;

	/**
	 * See messages.properties.
	 */
	public static String SelectShuttleOperation_LinkBottom;

	/**
	 * See messages.properties.
	 */
	public static String TrinidadDTResourceProvider_StyleSheetNotLocated;

}
