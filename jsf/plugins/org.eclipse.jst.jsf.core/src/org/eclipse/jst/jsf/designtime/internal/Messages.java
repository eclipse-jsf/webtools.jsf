/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.designtime.internal;

import org.eclipse.osgi.util.NLS;

/**
 * NLS messages.
 * 
 * @author cbateman
 *
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.designtime.internal.messages"; //$NON-NLS-1$
	/**
	 * see messages.properties
	 */
	public static String CompositeTagResolvingStrategy_DisplayName;
	/**
	 * see messages.properties
	 */
	public static String DefaultJSPTagResolver_DisplayName;
	/**
	 * see messages.properties
	 */
	public static String PersistedDataTagStrategy_DisplayName;
	/**
	 * see messages.properties
	 */
	public static String TagIntrospectingStrategy_DisplayName;
	/**
	 * messages.properties
	 */
	public static String TLDRegistryManager_DisplayName;
	/**
	 * messages.properties
	 */
	public static String TLDTagRegistry_RefreshJob;
	/**
	 * messages.properties
	 */
	public static String TLDTagRegistry_UpdateJob;
	/**
	 * messages.properties
	 */
	public static String UnresolvedJSPTagResolvingStrategy_DisplayName;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
