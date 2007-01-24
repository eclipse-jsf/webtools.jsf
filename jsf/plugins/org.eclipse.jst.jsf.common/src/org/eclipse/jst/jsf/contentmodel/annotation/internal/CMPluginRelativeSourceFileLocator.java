/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.contentmodel.annotation.internal;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.CMAnnotationSourceFileLocator;

/**
 * Default implementation of CMAnnotationSourceFileLocator that will locate annotations
 * and resource bundles relative to the plugin that extends annotationFiles.
 * 
 * @author Gerry Kessler - Oracle
 * @deprecated see common.metadata package
 */
public class CMPluginRelativeSourceFileLocator extends CMAnnotationSourceFileLocator {
	
	/* 
	 * Returns InputStream of annotation file from plugin relative location.
	 * 
	 * @see org.eclipse.jst.jsf.contentmodel.annotations.internal.contentmodel.internal.annotation.AbstractCMSourceFileLocator#getAnnotationSourceInputStream()
	 */
	public InputStream getAnnotationSourceInputStream() throws IOException {
		URL url = FileLocator.find(Platform.getBundle(fileInfo.getBundleId()), Path.fromOSString(fileInfo.getAnnotationFileLocation()), null);
		if (url != null) {
			return url.openStream();
		}
		return null;
	}

	/* 
	 * Returns property resource bundle if it exists.  May return null.
	 * This implementation assumes that the basename of the bundle is the same 
	 * as the source file name and in the same directory.
	 * 
	 * @see org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.ICMAnnotationSourceFileLocator#getResourceBundle()
	 */
	public ResourceBundle getResourceBundle() throws IOException, MalformedURLException {
		URL bundleURL = getAnnotationPropertiesFileBasenameURL();
		ResourceBundle resourceBundle = ResourceBundleHelper.getResourceBundle(getBaseNameURL(bundleURL));
		return resourceBundle;
	}
	
	private URL getAnnotationPropertiesFileBasenameURL()  {
		IPath annotationPath = Path.fromOSString(fileInfo.getAnnotationFileLocation()); 
		IPath annotationFolder = annotationPath.removeLastSegments(1);
		IPath propertiesLocation = annotationPath.removeFirstSegments(annotationPath.segmentCount() - 1).removeFileExtension();
		// append location of propertiles file
		IPath propertiesFile = annotationFolder.append(propertiesLocation);
	
		// append .properties extension if needed
		if (propertiesFile.getFileExtension() == null)
			propertiesFile = propertiesFile.addFileExtension("properties"); //$NON-NLS-1$
		// create a URL out of the properties file location
		return FileLocator.find(Platform.getBundle(fileInfo.getBundleId()),
				propertiesFile, null);
	}

	
	private String getBaseNameURL(URL bundleURL) {
		IPath url = new Path(bundleURL.toExternalForm());
		if (url.getFileExtension() != null)
			url = url.removeFileExtension();
		return url.toString();
	}
}
