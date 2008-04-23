/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.common.metadata.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * Default implementation of StandardMetaDataSourceFileLocator that will locate standard metadata files
 * and resource bundles relative to the plugin that registered the files using the 
 * <code>org.eclipse.jst.jsf.common.StandardMetaDataFiles</code> ext-pt.
 *
 */
public class PluginRelativeStandardMetaDataSourceFileLocator extends StandardMetaDataSourceFileLocator{
	
	private ResourceBundle resourceBundle;

	/* 
	 * Returns InputStream of standard metadata file from plugin relative location.
	 * 	 
	 */
	public InputStream getInputStream() throws IOException {
		URL url = getURL();
		if (url != null) 
			return url.openStream();
		
        String  fileName = Path.fromOSString(fileInfo.getLocation()).toString() + " (Plugin: " + fileInfo.getBundleId()+")" ;
        throw new FileNotFoundException("Metadata file not found: "+ fileName);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.StandardMetaDataSourceFileLocator#getURL()
	 */
	public URL getURL() {
	    final Bundle bundle = Platform.getBundle(fileInfo.getBundleId());
	    if (bundle == null)
	    {
	        return null;
	    }
	    final IPath fromOSString = Path.fromOSString(fileInfo.getLocation());
		return FileLocator.find(bundle, fromOSString, null);
	}

	
	/* 
	 * Returns property resource bundle if it exists.  May return null.
	 * This implementation assumes that the basename of the bundle is the same 
	 * as the source file name and in the same directory.
	 * 
	 */
	public ResourceBundle getResourceBundle() throws IOException, MalformedURLException {
		if (resourceBundle == null){
			URL bundleURL = getStandardMetaDataSourceFileBasenameURL();
			if (bundleURL == null)
				return null;
			
			resourceBundle = ResourceBundleHelper.getResourceBundle(bundleURL);
		}
		return resourceBundle;
	}
	
	private URL getStandardMetaDataSourceFileBasenameURL()  {
		IPath annotationPath = Path.fromOSString(fileInfo.getLocation()); 
		IPath annotationFolder = annotationPath.removeLastSegments(1);
		IPath propertiesLocation = annotationPath.removeFirstSegments(annotationPath.segmentCount() - 1).removeFileExtension();
		// append location of properties file
		IPath propertiesFile = annotationFolder.append(propertiesLocation);
	
		// append .properties extension if needed
		if (propertiesFile.getFileExtension() == null)
			propertiesFile = propertiesFile.addFileExtension("properties"); //$NON-NLS-1$
		// create a URL out of the properties file location
		return FileLocator.find(Platform.getBundle(fileInfo.getBundleId()),
				propertiesFile, null);
	}

}
