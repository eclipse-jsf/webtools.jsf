/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
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

/**
 * Contains information about annotation files.  
 */
public final class StandardMetaDataFileInfo implements IStandardMetaDataSourceInfo{
		
	private final String fileLocation;
	private final String fileLocatorClassname;
	private final String bundleId;

	/**
	 * Constructor
	 * @param fileLocation
	 * @param bundleId
	 * @param fileLocatorClassname
	 */
	public StandardMetaDataFileInfo( String fileLocation, String bundleId, String fileLocatorClassname) {
		this.fileLocation = fileLocation.trim();
		this.bundleId = bundleId.trim();
		this.fileLocatorClassname = fileLocatorClassname;
	}

	/**
	 * Get the location of the annotation file as originally specified.
	 * 
	 * @return String
	 */
	public String getLocation() {
		return fileLocation;
	}
	
	/**
	 * Get the annotation file locator classname that must implement ICMAnnotationSourceFileLocator.
	 * Can return null or empty string.   Caller should provide default.
	 * 
	 * @return String
	 */
	public String getLocatorClassname() {
		return fileLocatorClassname != null ? fileLocatorClassname.trim() : null;
	}

	/**
	 * Get the bundle id of the plugin where the annotation file is located.
	 * 
	 * @return String
	 */
	public String getBundleId() {
		return bundleId;
	}

	public String toString(){
		StringBuffer buf = new StringBuffer("StandardMetaDataFileInfo"); //$NON-NLS-1$
		buf.append(": "); //$NON-NLS-1$
		buf.append(bundleId);
		buf.append("/"); //$NON-NLS-1$
		buf.append(fileLocation);
		if (getLocatorClassname() != null){
			buf.append(": "); //$NON-NLS-1$
			buf.append(getLocatorClassname());
		}
		return buf.toString();
	}
}
