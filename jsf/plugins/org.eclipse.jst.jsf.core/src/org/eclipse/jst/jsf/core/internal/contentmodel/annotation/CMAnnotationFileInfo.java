/*
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *   IBM - Initial API and implementation
 *   Jens Lukowski/Innoopract - initial renaming/restructuring
 *   Gerry Kessler/Oracle - code borrowed and repurposed for JSF subproject
 * 
 */
package org.eclipse.jst.jsf.core.internal.contentmodel.annotation;

import org.eclipse.jst.jsf.core.internal.provisional.contentmodel.annotation.ICMAnnotationSourceFileInfo;

/**
 * Contains information about annotation files.  
 */
public final class CMAnnotationFileInfo implements ICMAnnotationSourceFileInfo{
	private String annotationFileLocation;
	private String annotationFileLocatorClassname;
	private String bundleId;
	private String parserClassname;

	public CMAnnotationFileInfo(String annotationFileLocation, String bundleId, String parserClassname, String annotationFileLocatorClassname) {
		this.annotationFileLocation = annotationFileLocation.trim();
		this.bundleId = bundleId.trim();
		this.parserClassname = parserClassname;
		this.annotationFileLocatorClassname = annotationFileLocatorClassname;
	}

	/**
	 * Get the location of the annotation file as originally specified.
	 * 
	 * @return String
	 */
	public String getAnnotationFileLocation() {
		return annotationFileLocation;
	}
	
	/**
	 * Get the annotation file locator classname that must implement ICMAnnotationSourceFileLocator.
	 * Can return null or empty string.   Caller should provide default.
	 * 
	 * @return String
	 */
	public String getAnnotationFileLocatorClassname() {
		return annotationFileLocatorClassname != null ? annotationFileLocatorClassname.trim() : null;
	}

	/**
	 * Get the bundle id of the plugin where the annotation file is located.
	 * 
	 * @return String
	 */
	public String getBundleId() {
		return bundleId;
	}
	
	/**
	 * Return classname for the annotation file parser (ICMAnnotationFileParser) to use.
	 * Can return null or empty string.   If it does, the caller should provide a default.
	 * 
	 * @return String
	 */
	public String getParserClassName(){
		return parserClassname != null ? parserClassname.trim() : null;
	}


}
