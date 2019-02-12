/*******************************************************************************
 * Copyright (c) 2002, 2006, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Jens Lukowski/Innoopract - initial renaming/restructuring
 *   Gerry Kessler/Oracle - code borrowed and repurposed for JSF subproject
 *
 *******************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;


/**
 * This class reads the standardMetaDataFiles extensions adds each specified standard metadata
 * file to the StandardMetaDataFileRegistry.
 * 
 * @see StandardMetaDataFileRegistry
 * also see StandardMetaDataFile extension-point
 */
public class StandardMetaDataFileRegistryReader {
	/**
	 * EXTENSION POINT ID
	 */
	protected static final String EXTENSION_POINT_ID = "standardMetaDataFiles"; //$NON-NLS-1$
	/**
	 * IConfigurationElement element name
	 */
	protected static final String TAG_NAME = "StandardMetaDataFile"; //$NON-NLS-1$
	/**
	 * Uri attribute name for IConfigurationElement
	 */
	protected static final String ATT_URI = "uri"; //$NON-NLS-1$
	/**
	 * Location attribute name for IConfigurationElement
	 */
	protected static final String ATT_LOCATION = "location"; //$NON-NLS-1$
	/**
	 * Locator attribute name for IConfigurationElement
	 */
	protected static final String ATT_LOCATOR = "locator"; //$NON-NLS-1$

	private StandardMetaDataFileRegistry annotationFileRegistry;

	/**
	 * Constructor
	 * @param registry
	 */
	public StandardMetaDataFileRegistryReader(StandardMetaDataFileRegistry registry) {
		this.annotationFileRegistry = registry;
	}

	/**
	 * read the registery
	 */
	public void readRegistry() {
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IExtensionPoint point = extensionRegistry.getExtensionPoint(JSFCommonPlugin.PLUGIN_ID, EXTENSION_POINT_ID);
		if (point != null) {
			IConfigurationElement[] elements = point.getConfigurationElements();
			for (int i = 0; i < elements.length; i++) {
				readElement(elements[i]);
			}
		}
	}

	private void readElement(IConfigurationElement element) {
		if (element.getName().equalsIgnoreCase(TAG_NAME)) {
			String uri = element.getAttribute(ATT_URI);
			String location = element.getAttribute(ATT_LOCATION);
			String locator = element.getAttribute(ATT_LOCATOR);
			String bundleId = null;
			if (uri != null && location != null) {
				try {
					bundleId = element.getContributor().getName();
					StandardMetaDataFileInfo fileInfo = new StandardMetaDataFileInfo(location, bundleId, locator);
					annotationFileRegistry.addStandardMetaDataFileInfo(uri, fileInfo);
				}
				catch (Exception e) {
					JSFCommonPlugin.log(IStatus.ERROR, "Internal Error: exception occurred while reading StandardMetaDataFile ext-pt: "+location + ":"+uri, e); //$NON-NLS-1$ //$NON-NLS-2$		}
				}
			}
		}
	}
}
