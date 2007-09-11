/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Jens Lukowski/Innoopract - initial renaming/restructuring
 *     Gerry Kessler/Oracle - copied from org.eclipse.wst.sse.core.internal.encoding.util and modified heavily
 *******************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * ResourceBundleHelper
 *
 */
public class ResourceBundleHelper {

	/**
	 * @param resourceURL
	 * @return ResourceBundle
	 * @throws MalformedURLException - may return null
	 * @throws IOException
	 */
	public static ResourceBundle getResourceBundle(URL resourceURL) throws MalformedURLException, IOException {
		return getResourceBundle(resourceURL, Locale.getDefault());
	}

	/**
	 * @param resourceURL
	 * @param targetLocale
	 * @return ResourceBundle - may return null
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static ResourceBundle getResourceBundle(URL resourceURL, Locale targetLocale) throws MalformedURLException, IOException {
		// try to load bundle from the location specified in the resourceURL
		//
		String protocol	= resourceURL.getProtocol();
		String host		= resourceURL.getHost();
		String file		= resourceURL.getFile();
		IPath path 		= new Path(file);
		
		String dir = "./";
		String bundleName = path.removeFileExtension().segment(path.segmentCount() - 1);
		if (path.segmentCount() > 1)
			dir = path.removeLastSegments(1).toString();
		
		// create a class loader with a class path that points to the resource
		// bundle's location
		//         
		URL[] classpath = new URL[1];
		classpath[0] = FileLocator.resolve(new URL(protocol, host, dir));
		ClassLoader resourceLoader = new URLClassLoader(classpath, null);

		return ResourceBundle.getBundle(bundleName, targetLocale, resourceLoader);
	}
}

