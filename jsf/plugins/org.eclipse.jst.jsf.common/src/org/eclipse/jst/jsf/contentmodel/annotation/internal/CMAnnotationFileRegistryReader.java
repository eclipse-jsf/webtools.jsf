/*******************************************************************************
 * Copyright (c) 2002, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Jens Lukowski/Innoopract - initial renaming/restructuring
 *   Gerry Kessler/Oracle - code borrowed and repurposed for JSF subproject
 *
 *******************************************************************************/
package org.eclipse.jst.jsf.contentmodel.annotation.internal;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.osgi.util.NLS;


/**
 * This class reads the Activator manifests and adds each specified grammar
 * annotation file to the CMAnnotationsRegistry.
 * 
 * @see CMAnnotationFileRegistry
 * also see org.eclipse.jst.jsf.contentmodel.annotations.annotationFiles extension-point
 * @deprecated see common.metadata package
 */
public class CMAnnotationFileRegistryReader {
	private static final String EXTENSION_POINT_ID = "annotationFiles"; //$NON-NLS-1$
	private static final String TAG_NAME = "annotationFile"; //$NON-NLS-1$
	private static final String ATT_URI = "uri"; //$NON-NLS-1$
	private static final String ATT_LOCATION = "location"; //$NON-NLS-1$
	private static final String ATT_LOCATOR = "locator"; //$NON-NLS-1$
	private static final String ATT_PARSER = "parser"; //$NON-NLS-1$

	private CMAnnotationFileRegistry annotationFileRegistry;

	/**
	 * @param annotationFileRegistry
	 */
	public CMAnnotationFileRegistryReader(CMAnnotationFileRegistry annotationFileRegistry) {
		this.annotationFileRegistry = annotationFileRegistry;
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
		if (element.getName().equals(TAG_NAME)) {
			String uri = element.getAttribute(ATT_URI);
			String location = element.getAttribute(ATT_LOCATION);
			String locator = element.getAttribute(ATT_LOCATOR);
			String parser = element.getAttribute(ATT_PARSER);
			String bundleId = null;
			if (uri != null && location != null) {
				try {
					bundleId = element.getContributor().getName();
					CMAnnotationFileInfo fileInfo = new CMAnnotationFileInfo(location, bundleId, parser, locator);
					annotationFileRegistry.addAnnotationFileInfo(uri, fileInfo);
				}
				catch (Exception e) {
					JSFCommonPlugin.log(e, NLS.bind(Messages.CMAnnotationFileRegistryReader_problem, new String[]{bundleId, uri, location})); 
				}
			}
		}
	}
}
