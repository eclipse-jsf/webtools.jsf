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

package org.eclipse.jst.jsf.contentmodel.annotation;

import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * Used to supply an InputStream to the parser of an annotation 
 * meta-data file and the URL to the resource bundle for a properties file, if any.
 * 
 * Implementors must provide a zero-argument constructor.
 * 
 * @author Gerry Kessler - Oracle
 * @deprecated see common.metadata package
 */ 
public abstract class CMAnnotationSourceFileLocator{
	protected ICMAnnotationSourceFileInfo fileInfo;
	
	/**
	 * Set the ICMAnnotationSourceFileInfo for locator
	 * @param fileInfo
	 */
	public final void setFileInfo(ICMAnnotationSourceFileInfo fileInfo){
		this.fileInfo = fileInfo;
	}
	
	/**
	 * @return ICMAnnotationSourceFileInfo for the locator
	 */
	protected final ICMAnnotationSourceFileInfo getFileInfo(){
		return fileInfo;
	}
	/**
	 * Return InputStream to the meta-data annotation.  
	 * Callers are responsble for closing the stream.
	 * @return InputStream
	 * @throws IOException
	 */
	public abstract InputStream getAnnotationSourceInputStream() throws IOException;
	/**
	 * Return ResourceBundle for the property files if there are any.  Return null if not.
	 * 
	 * @return java.util.ResourceBundle
	 * @throws IOException
	 * @throws MissingResourceException
	 */
	public abstract ResourceBundle getResourceBundle() throws IOException, MissingResourceException;
}
