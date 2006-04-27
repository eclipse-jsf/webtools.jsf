/*
 * Copyright (c) 2002, 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *   IBM - Initial API and implementation
 *   Jens Lukowski/Innoopract - initial renaming/restructuring
 *   Gerry Kessler/Oracle - code borrowed and repurposed for JSF subproject
 */
package org.eclipse.jst.jsf.core.internal.contentmodel.annotation;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;


/**
 * This class reads the plugin manifests and adds each specified grammar
 * annotation file to the CMAnnotationsRegistry.
 * 
 * @see CMAnnotationFileRegistry
 * @see org.eclipse.jst.jsf.core.annotationFiles extension-point
 */
public class CMAnnotationFileRegistryReader {
	protected static final String EXTENSION_POINT_ID = "annotationFiles"; //$NON-NLS-1$
	protected static final String TAG_NAME = "annotationFile"; //$NON-NLS-1$
	protected static final String ATT_URI = "uri"; //$NON-NLS-1$
	protected static final String ATT_LOCATION = "location"; //$NON-NLS-1$
	protected static final String ATT_LOCATOR = "locator"; //$NON-NLS-1$
	protected static final String ATT_PARSER = "parser"; //$NON-NLS-1$

	private CMAnnotationFileRegistry annotationFileRegistry;

	public CMAnnotationFileRegistryReader(CMAnnotationFileRegistry annotationFileRegistry) {
		this.annotationFileRegistry = annotationFileRegistry;
	}

	/**
	 * read from plugin registry and parse it.
	 */
	public void readRegistry() {
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		String pluginId = "org.eclipse.jst.jsf.core"; //$NON-NLS-1$
		IExtensionPoint point = extensionRegistry.getExtensionPoint(pluginId, EXTENSION_POINT_ID);
		if (point != null) {
			IConfigurationElement[] elements = point.getConfigurationElements();
			for (int i = 0; i < elements.length; i++) {
				readElement(elements[i]);
			}
		}
	}

	protected void readElement(IConfigurationElement element) {
		if (element.getName().equals(TAG_NAME)) {
			String uri = element.getAttribute(ATT_URI);
			String location = element.getAttribute(ATT_LOCATION);
			String locator = element.getAttribute(ATT_LOCATOR);
			String parser = element.getAttribute(ATT_PARSER);
			String bundleId = null;
			if (uri != null && location != null) {
				try {
					bundleId = element.getNamespaceIdentifier();
					CMAnnotationFileInfo fileInfo = new CMAnnotationFileInfo(location, bundleId, parser, locator);
					annotationFileRegistry.addAnnotationFileInfo(uri, fileInfo);
				}
				catch (Exception e) {
					JSFCorePlugin.log(e, "Problem adding annotation file: bundle = " + bundleId + ", uri = " +uri + ", location = " + location); //$NON-NLS-1$
				}
			}
		}
	}
}
